package com.jeecg.jiekou.controller;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.jeecg.commont.Utils_SSS;
import com.jeecg.n_goods_join_record.entry.NGoodsJoinRecord;
import com.jeecg.n_merchant_account.entity.NMerchantAccountEntity;
import com.jeecg.n_phone_code.entity.NPhoneCodeEntity;
import com.jeecg.n_picture_propaganda.entity.NPicturePropaganda;
import com.jeecg.n_turntable_activity.entity.NTurntableActivityEntity;
import com.jeecg.n_turntable_activity.entity.NTurntableActivityRecordEntity;
import com.jeecg.n_turntable_activity.util.ActiveMathRandom;
import com.jeecg.n_user.entity.NUserMemberEntity;
import com.jeecg.n_user_price.entity.NUserPriceEntity;
import com.utils.CommonUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.*;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSRole;
import org.jeecgframework.web.system.pojo.base.TSRoleUser;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.pojo.base.TSUserOrg;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jeecg.n_deposit_user.entity.NDepositUserEntity;
import com.jeecg.n_evaluate.entity.NEvaluateEntity;
import com.jeecg.n_evaluate_colum.entity.NEvaluateColumEntity;
import com.jeecg.n_evaluate_type.entity.NEvaluateTypeEntity;
import com.jeecg.n_faq.entity.NFaqEntity;
import com.jeecg.n_feeback.entity.NFeebackEntity;
import com.jeecg.n_goods_detaisl.entity.NGoodsDetaislEntity;
import com.jeecg.n_marketing_banner.entity.NMarketingBannerEntity;
import com.jeecg.n_merchant.entity.NMerchantEntity;
import com.jeecg.n_order.entity.NOrderEntity;
import com.jeecg.n_phone_recharge.entity.NPhoneRechargeEntity;
import com.jeecg.n_phone_recharge.util.MD5Util;
import com.jeecg.n_rule.entity.NRuleEntity;
import com.jeecg.n_standard_details.entity.NStandardDetailsEntity;
import com.jeecg.n_user.entity.NUserEntity;
import com.jeecg.n_user_address.entity.NUserAddressEntity;
import com.jeecg.n_user_collect.entity.NUserCollectEntity;
import com.jeecg.n_user_coupon.entity.NUserCouponEntity;
import com.jeecg.n_user_search.entity.NUserSearchEntity;
import com.jeecg.n_websocket.entity.NWebsocketEntity;
import com.jeecg.newverison.entity.NewverisonEntity;
import com.jeecg.s_tuisong.TuisongDictService;
import com.jeecg.s_tuisong.entity.STuisongEntity;
import com.jeecg.weixin.util.WeixinService;
import test.CodeUtil;

/**
 * @author onlineGenerator
 * @version V1.0
 * @Title: Controller
 * @Description: 接口
 * @date 2017-08-18 17:45:08
 */
@Controller
@RequestMapping("/jiekoucontroller")
public class JiekouController extends BaseController {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(JiekouController.class);

    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;
    @Autowired
    private CodeService codeService;
    @Autowired
    private WeixinService weixinservice;
    @Autowired
    private TuisongDictService tuisongdictservice;
    @Resource
    private Utils_SSS utilsSss;

    /* 活动图片 */
    @RequestMapping(params = "nmarketingbanner")
    public void nmarketingbanner(HttpServletRequest request,
                                 HttpServletResponse response, String marktype) {
        JSONObject object = new JSONObject();
        String message = "成功";
        String success = "success";
        StringBuffer sql = new StringBuffer();
        sql.append("from NMarketingBannerEntity where title='")
                .append(marktype)
                .append("'")
                .append(" ORDER BY create_date desc ");
        List<NMarketingBannerEntity> mode_lsit = systemService.findHql(sql
                .toString());
        String picurl = " ";
        if (mode_lsit != null && mode_lsit.size() > 0) {
            NMarketingBannerEntity mode = mode_lsit.get(0);
            if (mode != null) {
                picurl = mode.getPicurl();
            }

        }
        object.put("picurl", picurl);
        object.put("status", success);
        object.put("message", message);
        TagUtil.getSendJson(response, object.toString());
    }

    /* 商家入驻的登陆 */
    @RequestMapping(params = "applylogin")
    public void applylogin(HttpServletRequest request,
                           HttpServletResponse response, String phone, String code) {
        JSONObject object = new JSONObject();
        String message = "成功";
        String success = "success";
        //String detaisl = "审核";
        StringBuffer sql = new StringBuffer(
                "select id,phone,code from n_phone_code  where phone='");
        sql.append(phone).append("' and code='").append(code)
                .append("' and date_add(create_date,interval 5 minute)>now() ORDER BY create_date desc ");
        List<Object[]> org_object = systemService.findListbySql(sql
                .toString());
        if (org_object.size() < 1) {
            message = "验证码错误";
            success = "fail";
        }
        System.out.println("phone==" + phone);
        NMerchantEntity t = systemService.findUniqueByProperty(NMerchantEntity.class, "phone", phone);
        if (t != null) {
            System.out.println("phone==" + t.getPhone() + "==" + t.getId() + "==" + t.getCompany());
            String auditType = t.getAuditType();//0审核中1审核通过2审核不通过
            String accountStatus = t.getAccountStatus();//0正常1禁用2销户
            if ("2".equals(accountStatus)) {
                success = "jinyong";
                message = "用户被销户";
            } else if ("0".equals(auditType)) {
                success = "noupdate";
                message = "审核中,资料不准修改";
            } else if ("1".equals(auditType)) {
                success = "nologin";
                message = "审核通过,请登录后台";
                //message = t.getAuditContent();
            } else if ("2".equals(auditType)) {
                success = "noaudit";
                message = t.getAuditContent();
            }
        }

        object.put("status", success);
        object.put("message", message);
        TagUtil.getSendJson(response, object.toString());
    }


    /* 商家入驻的商家信息 */
    @RequestMapping(params = "applymerchant")
    public void applymerchant(HttpServletRequest request,
                              HttpServletResponse response, String phone) {
        JSONObject object = new JSONObject();
        String message = "成功";
        String success = "success";
        NMerchantEntity t = systemService.findUniqueByProperty(NMerchantEntity.class, "phone", phone);
        if (t != null) {
            String company = t.getCompany();
            String joinname = t.getJoinname();
            String email = t.getEmail();
            //String phone=t.getCompany();
            String card = t.getCard();
            String urgencyName = t.getUrgencyName();
            String urgencyPhone = t.getUrgencyPhone();
            String jingying = t.getJingying();
            String jingyingname = t.getJingyingname();
            String startshoptype = t.getStartshoptype();
            String shifou = t.getShifou();
            String cardZUrl = t.getCardZUrl();
            String cardFUrl = t.getCardFUrl();
            String cardBUrl = t.getCardBUrl();
            String bossPhone = t.getBossPhone();
            String bossEmail = t.getBossEmail();
            String merchantlogo = t.getMerchantlogo();
            String details = t.getDetails();
            String org = t.getOrg();
            String orgUrl = t.getOrgUrl();
            String bankUrl = t.getBankUrl();
            String accountType = t.getAccountType();
            String auditType = t.getAuditType();//0、审核中1审核通过2审核不通过
            String accountStatus = t.getAccountStatus();//0正常1禁用2销户
            if (!"0".equals(accountStatus)) {
                success = "jinyong";
            } else if ("1".equals(auditType)) {
                success = "audit";
            } else {
                object.put("company", company);
                object.put("joinname", joinname);
                object.put("email", email);
                object.put("card", card);
                object.put("urgencyName", urgencyName);
                object.put("urgencyPhone", urgencyPhone);
                object.put("jingyingname", jingyingname);
                object.put("jingying", jingying);
                object.put("startshoptype", startshoptype);
                object.put("merchantlogo", merchantlogo);
                object.put("details", details);
                object.put("shifou", shifou);
                object.put("cardZUrl", cardZUrl);
                object.put("cardFUrl", cardFUrl);
                object.put("cardBUrl", cardBUrl);
                object.put("bossPhone", bossPhone);
                object.put("bossEmail", bossEmail);
                object.put("org", org);
                object.put("orgUrl", orgUrl);
                object.put("bankUrl", bankUrl);
                object.put("accountType", accountType);
                object.put("auditType", auditType);
                object.put("accountStatus", accountStatus);
            }

        } else {
            success = "wu";
        }
        object.put("status", success);
        object.put("message", message);
        TagUtil.getSendJson(response, object.toString());
    }


    /* 保存商家信息 */
    @RequestMapping(params = "savemerchant")
    public void savemerchant(HttpServletRequest request,
                             HttpServletResponse response, NMerchantEntity nMerchant) {
        System.out.println("12==");
        JSONObject object = new JSONObject();
        String message = "申请成功";
        String success = "success";
        String phone = nMerchant.getPhone();
        nMerchant.setDelFlag("0");
        nMerchant.setIspay("1");//0是1否
        nMerchant.setAccountType("0");//0主账户1子账户
        nMerchant.setRole("0");//0管理员1客服2运营
        nMerchant.setAccountStatus("0");//0正常1禁用2销户
        nMerchant.setAuditType("0");//0、审核中1审核通过2审核不通过
        nMerchant.setDepositMoney("0.00");//保证金金额
        nMerchant.setUpdateDate(new Date());
        //String username=nMerchant.getPhone();
        String realname = nMerchant.getCompany();
        NMerchantEntity t = systemService.findUniqueByProperty(NMerchantEntity.class, "phone", phone);

		/*
		List<TSUser> users = systemService.findByProperty(TSUser.class,"userName",phone);
		if(users.size()!=0){
			message = "商家已经存在";
		}else{*/
        try {
            if (t != null) {
                String accountstatus = t.getAccountStatus();
                String audittype = t.getAuditType();
                if ("0".equals(accountstatus) && !"1".equals(audittype)) {
                    MyBeanUtils.copyBeanNotNull2Bean(nMerchant, t);
                    systemService.saveOrUpdate(t);
                    String id = t.getId();
                    TSUser user = systemService.findUniqueByProperty(TSUser.class, "pkid", id);
                    user.setUserName(phone);
                    user.setRealName(realname);
                    user.setStatus(new Short("0"));//1正常0锁定
                    user.setDeleteFlag(new Short("1"));//1删除0正常
                    systemService.saveOrUpdate(user);
                } else {
                    message = "该商家不允许修改资料";
                }
            } else {
                String id = UUID.randomUUID().toString().replaceAll("-", "");
                System.out.println("id-==" + id);
                nMerchant.setCreateDate(new Date());
                boolean flg = Insertuser(phone, realname, id);
                //List<TSUser> users_list = systemService.findByProperty(TSUser.class,"userName",phone);
                if (flg) {
                    nMerchant.setId(id);
                    systemService.save(nMerchant);
                    systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
                } else {
                    success = "fail";
                    message = "该商家不允许注册,该商家已经存在";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "异常";
        }
        object.put("status", success);
        object.put("message", message);
        TagUtil.getSendJson(response, object.toString());
    }

    public boolean Insertuser(String username, String realname, String pkid) {
//		String username=t.getPhone();
//		String realname=t.getCompany();
        List<TSUser> users = systemService.findByProperty(TSUser.class, "userName", username);
        TSUser tsUser = new TSUser();
//		tsUser.setId(nMerchant.getId());
        tsUser.setEducation("1");
        tsUser.setShoptype("1");//1是商户null是平台
        tsUser.setUserName(username);
        tsUser.setRealName(realname);
        tsUser.setPassword(PasswordUtil.encrypt(username, "123456", PasswordUtil.getStaticSalt()));
        //	tsUser.setDepartid("A06A01");
        tsUser.setStatus(new Short("0"));//1正常0锁定
        tsUser.setDeleteFlag(new Short("1"));
        tsUser.setPkid(pkid);
        if (users.size() != 0) {
            return false;
        } else {
            System.out.println("id===" + tsUser.getId());
            tsUser.setDepartid(null);
            systemService.save(tsUser);
            List<TSRole> roleList = systemService.findByProperty(TSRole.class, "roleCode", "shop");
            TSRoleUser tsRoleUser = new TSRoleUser();
            tsRoleUser.setTSUser(tsUser);
            tsRoleUser.setTSRole(roleList.get(0));
            systemService.save(tsRoleUser);
            List<TSDepart> departList_org = systemService.findByProperty(TSDepart.class, "orgCode", "A06A01");
            TSUserOrg tsUserOrg = new TSUserOrg();
            tsUserOrg.setTsDepart(departList_org.get(0));
            tsUserOrg.setTsUser(tsUser);
            systemService.save(tsUserOrg);
        }
        return true;
    }

    /**
     * 发送验证码
     * @param phone
     * @param req
     * @param resp
     *//*
	@RequestMapping(params="sendMessage")
	public void sendMessage(String phone,HttpServletRequest req,HttpServletResponse resp){
		JSONObject json = new JSONObject();
		String code = CodeUtil.getRandomStr(6, 0);
		String message = "发送成功";
		String phoneIllegal = "0";
		String status = "0";
		try {
			phoneIllegal = codeService.sendMsg(phone, code);
		} catch (ClientException e) {
			status = "2";
			message = "发送验证码失败.";
			e.printStackTrace();
		} catch (Exception e) {
			status = "1";
			message = "存储验证码失败.";
			e.printStackTrace();
		}
		if("1".equals(phoneIllegal)){
			status = "3";
			message = "发送验证码失败.非法手机号.";
		}
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE,5);
		Date date = now.getTime();
		json.put("status", status);
		json.put("message", message);
		json.put("date", date);
		TagUtil.getSendJson(resp, json.toString());
	}*/

    /**
     * 手机登陆
     */
    @SuppressWarnings("unused")
    @RequestMapping(params = "login")
    public void login(HttpServletRequest request, HttpServletResponse response, String username, String code, String cid) {
        JSONObject object = new JSONObject();
        // JSONArray jsonarray=new JSONArray();
        String message = null;
        boolean flag = true;
        String status = "success";
        if (StringUtil.isEmpty(username)) {
            flag = false;
            status = "fail";
            message = "用户名不能为空";
        }
        if (StringUtil.isEmpty(code)) {
            flag = false;
            status = "fail";
            message = "验证码不能为空";
        }
        if (flag) {

            StringBuffer sql = new StringBuffer(
                    "select id,phone,code from n_phone_code  where phone='");
            sql.append(username).append("' and code='").append(code)
                    .append("' and to_days(create_date) = to_days(now()) ORDER BY create_date desc ");
            List<Object[]> org_object = systemService.findListbySql(sql
                    .toString());
            if (org_object.size() > 0) {
                NUserEntity user = systemService.findUniqueByProperty(
                        NUserEntity.class, "username", username);
                if (user != null) {
                    message = "登陆成功";
                    String userid = user.getId();
                    String realname = user.getRealname();
                    String sex = user.getSex();
                    String address = user.getAddress();
                    String addresscode = user.getAddresscode();
                    Date birth = user.getBirth();
                    String details = user.getDetails();
                    String usernameurl = user.getUsernameurl();
                    String price = user.getPrice();
                    String unionid = user.getUnionid();
                    String openid = user.getOpenid();
                    String account_status = user.getAccountStatus();//0正常1禁用2销户
                    if (!"0".equals(account_status)) {
                        status = "fail";
                        message = "用户被禁用";
                    }
                    object.put("userid", userid);
                    object.put("username", username);
                    object.put("realname", realname);
                    object.put("address", address);
                    object.put("birth", birth);
                    object.put("details", details);
                    object.put("usernameurl", usernameurl);
                    object.put("price", price);
                    object.put("sex", sex);
                    object.put("openid", openid);
                    object.put("unionid", unionid);
                    if (StringUtil.isEmpty(userid))
                        object.put("userid", " ");
                    if (StringUtil.isEmpty(realname))
                        object.put("realname", " ");
                    if (StringUtil.isEmpty(sex))
                        object.put("sex", " ");
                    if (StringUtil.isEmpty(address))
                        object.put("address", " ");
                    if (StringUtil.isEmpty(details))
                        object.put("details", " ");
                    if (StringUtil.isEmpty(usernameurl))
                        object.put("usernameurl", " ");
                    if (StringUtil.isEmpty(price))
                        object.put("price", " ");
                    if (StringUtil.isEmpty(unionid))
                        object.put("unionid", " ");
                    if (StringUtil.isEmpty(openid))
                        object.put("openid", " ");
                    if (!StringUtil.is_khEmpty(cid)) {
                        int i = systemService
                                .updateBySqlString("update n_user set cid='" + cid
                                        + "' where id='" + userid + "'");
                        if (i == 1) {
                            System.out.println("绑定成功");
                        }
                    }

                } else {
					/*flag = false;
					message = "用户不存在";*/
                    NUserEntity nuserentity = new NUserEntity();
                    String id = StringUtil.getId();
                    nuserentity.setId(id);
                    nuserentity.setCreateDate(new Date());
                    nuserentity.setRealname(username);
                    nuserentity.setUsername(username);
                    nuserentity.setPhone(username);
                    nuserentity.setPrice("0");
                    nuserentity.setSex("1");
                    nuserentity.setAccountStatus("0");
                    nuserentity.setCid(cid);
                    systemService.save(nuserentity);
                    status = "add";
                    message = "新增用户";
                    object.put("userid", id);
                    object.put("username", username);
                    object.put("realname", username);
                }
            } else {
                status = "fail";
                message = "验证码错误";
            }

        }

        object.put("status", "success");
        object.put("message", message);
        TagUtil.getSendJson(response, object.toString());
    }

    /**
     * 获得用户消息
     */
    @SuppressWarnings("unused")
    @RequestMapping(params = "userinfo")
    public void userinfo(HttpServletRequest request, HttpServletResponse response,
                         String userid) {
        JSONObject object = new JSONObject();
        // JSONArray jsonarray=new JSONArray();
        String message = null;
        boolean flag = true;
        String status = "success";

        NUserEntity user = systemService.getEntity(
                NUserEntity.class, userid);
        if (user != null) {
            message = "登陆成功";
            String username = user.getUsername();
            String realname = user.getRealname();
            String sex = user.getSex();
            String address = user.getAddress();
            String addresscode = user.getAddresscode();
            Date birth = user.getBirth();
            String details = user.getDetails();
            String usernameurl = user.getUsernameurl();
            String price = user.getPrice();
            String cid = user.getCid();
            String openid = user.getOpenid();
            String unionid = user.getUnionid();
            String account_status = user.getAccountStatus();//0正常1禁用2销户
            if (!"0".equals(account_status)) {
                status = "fail";
                message = "用户被禁用";
            }
            object.put("userid", userid);
            object.put("username", username);
            object.put("realname", realname);
            object.put("sex", sex);
            object.put("address", address);
            object.put("birth", birth);
            object.put("details", details);
            object.put("usernameurl", usernameurl);
            object.put("price", price);
            object.put("cid", cid);
            object.put("openid", openid);
            object.put("unionid", unionid);
            if (StringUtil.isEmpty(userid))
                object.put("userid", " ");
            if (StringUtil.isEmpty(realname))
                object.put("realname", " ");
            if (StringUtil.isEmpty(sex))
                object.put("sex", " ");
            if (StringUtil.isEmpty(address))
                object.put("address", " ");
            if (StringUtil.isEmpty(details))
                object.put("details", " ");
            if (StringUtil.isEmpty(usernameurl))
                object.put("usernameurl", " ");
            if (StringUtil.isEmpty(price))
                object.put("price", " ");
            if (StringUtil.isEmpty(cid))
                object.put("cid", " ");
            if (StringUtil.isEmpty(openid))
                object.put("openid", " ");
            if (StringUtil.isEmpty(unionid))
                object.put("unionid", " ");

        }

        object.put("status", "success");
        object.put("message", message);
        TagUtil.getSendJson(response, object.toString());
    }

    /**
     * 微信或者QQ登陆
     */
    @SuppressWarnings("unused")
    @RequestMapping(params = "new_login")
    public void new_login(HttpServletRequest request, HttpServletResponse response, String openid, String unionid, String nickname, String sex, String cid, String headimgurl, NUserEntity user_b) {
        JSONObject object = new JSONObject();
        // JSONArray jsonarray=new JSONArray();
        String message = null;
        boolean flag = true;
        String status = "success";
        NUserEntity user = null;
        System.out.println("时珊珊打印用户登录名称==================nickname=:"+nickname);
        if (!StringUtil.is_khEmpty(unionid)) {
            user = systemService.findUniqueByProperty(NUserEntity.class, "unionid", unionid);
        } else {
            user = systemService.findUniqueByProperty(NUserEntity.class, "openid", openid);
        }
        if (!StringUtil.is_khEmpty(openid)) {
            if (user != null) {
                try {
                    MyBeanUtils.copyBeanNotNull2Bean(user_b, user);
                    user.setRealname(nickname);
                    systemService.saveOrUpdate(user);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                message = "登陆成功";
                String userid = user.getId();
                String username = user.getUsername();
                String realname = user.getRealname();
                sex = user.getSex();
                String address = user.getAddress();
                String addresscode = user.getAddresscode();
                Date birth = user.getBirth();
                String details = user.getDetails();
                String usernameurl = user.getUsernameurl();
                String price = user.getPrice();
                //String openid = user.getOpenid();
                //String unionid = user.getUnionid();
                String account_status = user.getAccountStatus();//0正常1禁用2销户
                if (!"0".equals(account_status)) {
                    status = "fail";
                    message = "用户被禁用";
                }
                object.put("userid", userid);
                object.put("username", username);
                object.put("realname", realname);
                object.put("sex", sex);
                object.put("address", address);
                object.put("birth", birth);
                object.put("details", details);
                object.put("usernameurl", usernameurl);
                object.put("price", price);
                object.put("openid", openid);
                object.put("unionid", unionid);
                if (StringUtil.isEmpty(userid))
                    object.put("userid", " ");
                if (StringUtil.isEmpty(realname))
                    object.put("realname", " ");
                if (StringUtil.isEmpty(sex))
                    object.put("sex", " ");
                if (StringUtil.isEmpty(address))
                    object.put("address", " ");
                if (StringUtil.isEmpty(details))
                    object.put("details", " ");
                if (StringUtil.isEmpty(usernameurl))
                    object.put("usernameurl", " ");
                if (StringUtil.isEmpty(price))
                    object.put("price", " ");
                if (StringUtil.isEmpty(openid))
                    object.put("openid", " ");
                if (StringUtil.isEmpty(unionid))
                    object.put("unionid", " ");
                if (!StringUtil.is_khEmpty(cid)) {
                    int i = systemService
                            .updateBySqlString("update n_user set cid='" + cid
                                    + "' where id='" + userid + "'");
                    if (i == 1) {
                        System.out.println("绑定成功");
                    }
                }

            } else {
					/*flag = false;
					message = "用户不存在";*/
                NUserEntity nuserentity = new NUserEntity();
                String id = StringUtil.getId();
                nuserentity.setId(id);
                nuserentity.setCreateDate(new Date());
                nuserentity.setRealname(nickname);
                nuserentity.setUsernameurl(headimgurl);
                nuserentity.setOpenid(openid);
                nuserentity.setUnionid(unionid);
                //  nuserentity.setUsername(username);
                //	nuserentity.setPhone(username);
                nuserentity.setPrice("0");
                nuserentity.setSex(sex);
                nuserentity.setAccountStatus("0");
                nuserentity.setCid(cid);
                systemService.save(nuserentity);
                //status="add";
                status = "success";
                message = "新增用户";
                object.put("userid", id);
                object.put("realname", nickname);
                object.put("usernameurl", headimgurl);
                object.put("sex", sex);
                object.put("openid", openid);
                object.put("unionid", unionid);
                object.put("price", "0");
            }
        } else {
            status = "fail";
            message = "请上传正确的用户信息";
        }
        object.put("status", status);
        object.put("message", message);
        TagUtil.getSendJson(response, object.toString());
    }

    /* 修改资料*/
    @RequestMapping(params = "updateuser")
    public void updateuser(HttpServletRequest request,
                           HttpServletResponse response, NUserEntity nUser) {
        JSONObject object = new JSONObject();
        String message = "修改成功";
        String success = "success";
        try {
            NUserEntity t = systemService.get(NUserEntity.class, nUser.getId());
            MyBeanUtils.copyBeanNotNull2Bean(nUser, t);
            systemService.saveOrUpdate(t);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            success = "fail";
            e.printStackTrace();
        }


        object.put("status", success);
        object.put("message", message);
        TagUtil.getSendJson(response, object.toString());
    }


    /**
     * 上传头像
     *
     * @param houseDisposalEntity
     * @param wyHouseDisposalPage
     * @param req
     * @param resp
     */
    @RequestMapping(params = "uploduserpic")
    public void uploduserpic(String userid, HttpServletRequest req,
                             HttpServletResponse resp) {
        String cupath = req.getRequestURL() + "";
        String propath = req.getContextPath();
        String t1 = cupath.substring(0, cupath.indexOf(propath));
        String t2 = t1 + propath;

        CommonsMultipartResolver cmr = new CommonsMultipartResolver(
                req.getServletContext());
        if (cmr.isMultipart(req)) {
            MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) (req);
            Iterator<String> files = mRequest.getFileNames();

            while (files.hasNext()) {
                MultipartFile mFile = mRequest.getFile(files.next());
                if (!mFile.isEmpty()) {
                    String basePath = req.getServletContext().getRealPath(
                            "/plug-in/upload/image");
                    SimpleDateFormat sFormat = new SimpleDateFormat("yyyyMMdd");
                    String dPath = "/" + sFormat.format(new Date()) + "/";
                    File dir = new File(basePath + dPath);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    String originFileName = mFile.getOriginalFilename();
                    String suffix = originFileName.split("\\.")[originFileName
                            .split("\\.").length - 1];
                    String base64Name = UUID.randomUUID().toString();
                    File file = new File(basePath + dPath, base64Name + "."
                            + suffix);
                    // String
                    // url_path=t2+"/plug-in/ueditor/jsp/upload/image"+dPath+base64Name+"."+suffix;
                    String url_path = t2 + "/plug-in/upload/image" + dPath
                            + base64Name + "." + suffix;
                    // 获得用户信息并保存
                    NUserEntity user = systemService.getEntity(
                            NUserEntity.class, userid);
                    user.setUsernameurl(url_path);
                    systemService.updateEntitie(user);
                    try {
                        FileUtils.copyInputStreamToFile(mFile.getInputStream(),
                                file);// 存储文件
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        String message = "添加成功";
        JSONObject json = new JSONObject();
        json.put("status", "success");
        json.put("message", message);
        TagUtil.getSendJson(resp, json.toString());

    }

    /**
     * 下载app 页面跳转
     *
     * @return
     */
    @RequestMapping(params = "appdowload=")
    public ModelAndView appdowloada(HttpServletRequest request) {
        String cupath = request.getRequestURL() + "";
        String propath = request.getContextPath();
        String t1 = cupath.substring(0, cupath.indexOf(propath));
        String t2 = t1 + propath;
        NewverisonEntity newverison = systemService.findUniqueByProperty(
                NewverisonEntity.class, "flg", "android");
        if (newverison != null) {
            request.setAttribute("dow_android",
                    t2 + newverison.getDownloadurl());
        }
        NewverisonEntity newverison_ios = systemService.findUniqueByProperty(
                NewverisonEntity.class, "flg", "ios");
        if (newverison_ios != null) {
            request.setAttribute("dow_ios", newverison_ios.getDownloadurl());
        }
        return new ModelAndView("com/jeecg/appdowload/dowload");
    }

    /**
     * 推送信息
     *
     * @return
     */
    @RequestMapping(params = "tuisong")
    public void tuisong(HttpServletRequest request,
                        HttpServletResponse response, String userid, Integer page,
                        Integer rows) {
        Integer start = (page - 1) * rows;
        if (start > 0) {
            if (page > 0) {
                page = page - 1;
                start = page * rows;
            } else {
                start = 0;
            }
        }

        JSONObject object = new JSONObject();
        StringBuffer sql = new StringBuffer(
                "select * from "
                        + " (select t1.id,t1.title,t1.userid,t1.tsstatus,t1.qtstatus,t1.create_date,details "
                        + " from s_tuisong   t1  where t1.qtstatus='1' ORDER BY t1.create_date desc ) as t1 "
                        + " UNION"
                        + " select * from "
                        + " (select t1.id,t1.title,t1.userid,t1.tsstatus,t1.qtstatus,t1.create_date,details from s_tuisong   t1  where t1.qtstatus='2' and t1.userid='"
                        + userid + "' "
                        + "  ORDER BY t1.create_date desc ) as t2 "
                        + " ORDER BY create_date desc ");
        sql.append(" limit " + start + "," + rows + " ");
        JSONArray jsonarray = new JSONArray();
        List<Object[]> mode_list = systemService.findListbySql(sql.toString());
        for (int i = 0; i < mode_list.size(); i++) {
            JSONObject json = new JSONObject();
            Object[] mode = mode_list.get(i);
            json.put("id", mode[0]);
            json.put("title", mode[1]);
            json.put("userid", mode[2]);
            json.put("tsstatus", mode[3]);
            json.put("qtstatus", mode[4]);
            json.put("create_date", mode[5]);
            json.put("details", mode[6]);
            jsonarray.add(json);
        }

        object.put("content", jsonarray);
        object.put("status", "success");
        object.put("message", "获得推送信息成功");
        TagUtil.getSendJson(response, object.toString());
    }

    /* 推送信息详情 */
    @RequestMapping(params = "tuisongdetails")
    public void tuisongdetails(HttpServletRequest request,
                               HttpServletResponse response, String id) {
        JSONObject object = new JSONObject();
        STuisongEntity mode = systemService.getEntity(STuisongEntity.class, id);
        object.put("details", mode.getDetails());
        object.put("status", "success");
        object.put("message", "获取信息成功");
        TagUtil.getSendJson(response, object.toString());
    }

    /* 首页banner图片 n_home_banner */
    @RequestMapping(params = "n_home_banner")
    public void n_home_banner(HttpServletRequest request, HttpServletResponse response) {
        JSONObject object = new JSONObject();
        JSONArray arry = new JSONArray();
        StringBuffer sql = new StringBuffer();
        sql.append("select id,title,picurl,orders from n_home_banner  ORDER BY orders asc");
        List<Object[]> mode_list = systemService.findListbySql(sql.toString());
        for (int i = 0; i < mode_list.size(); i++) {
            Object[] mode = mode_list.get(i);
            JSONObject object_mode = new JSONObject();
            object_mode.put("id", mode[0]);
            object_mode.put("title", mode[1]);
            object_mode.put("picurl", mode[2]);
            object_mode.put("orders", mode[3]);
            arry.add(object_mode);
        }
        object.put("details", arry);
        object.put("status", "success");
        object.put("message", "获取信息成功");
        TagUtil.getSendJson(response, object.toString());
    }

    /* 首页营销专场 n_home_banner_one */
    @RequestMapping(params = "n_home_banner_one")
    public void n_home_banner_one(HttpServletRequest request,
                                  HttpServletResponse response) {
        JSONObject object = new JSONObject();
        StringBuffer sql = new StringBuffer();
        sql.append("select  id,title,activityurl,picurl from n_home_banner_one  ORDER BY create_date desc ");
        List<Object[]> mode_list = systemService.findListbySql(sql.toString());
        if (mode_list.size() > 0) {
            Object[] mode = mode_list.get(0);
            object.put("id", mode[0]);
            object.put("title", mode[1]);
            object.put("activityurl", mode[2]);
            object.put("picurl", mode[3]);
        }
        object.put("status", "success");
        object.put("message", "获取信息成功");
        TagUtil.getSendJson(response, object.toString());
    }

    /* 首页商品商品n_marketing_home_details
     * starttype	商品类型	c	50		1任务团2普通团*/
   /* @RequestMapping(params = "n_marketing_home")
    public void n_marketing_home(HttpServletRequest request, HttpServletResponse response, Integer page, Integer rows) {
        Integer start = (page - 1) * rows;
        if (start > 0) {
            if (page > 0) {
                page = page - 1;
                start = page * rows;
            } else {
                start = 0;
            }
        }
        JSONObject object = new JSONObject();
        JSONArray arry = new JSONArray();
        JSONArray two = new JSONArray();
        StringBuffer sql = new StringBuffer();
        sql.append(" select t1.id as onedetailsid , t1.oneid,")
                .append("t2.id,t2.goods_detaisl_status,t2.merchant_id, ")
                .append("t2.goods_classify_id,t2.title,t2.duan_title,")
                .append("t2.starttype,t2.weight,t2.bazaar_price,t2.contents,")
                .append("t2.send_time,t2.post,t2.returns,t2.fine,t2.pic_one,")
                .append("t2.standard,t2.standard_one,t2.standard_two,")
                .append("t2.sum_numbers,t2.codes,t2.group_purchase,")
                .append("t2.set_bounds,t2.xiangou,t2.reject,t2.orders,")
                .append("t2.shangpintype,t2.join_huodong,t2.carousel,")
                .append("t2.details,t2.goodsone,t2.goodstwo,t2.goodsthree,")
                .append("t2.standard_onelist,t2.standard_twolist,t2.tuanprices,t2.unitprices,t2.marketing_one ")
                .append(",t1.onename,t1.orders as morders,t1.detailsorders ,t2.pic_two,t2.sellnumbs,t2.memberprices,t2.three_level ")
                .append(" from ")
                //		+ "(select *from n_marketing_one_details where audittype='1' and shoptype='0'  ORDER BY orders asc LIMIT 0,10 ) t1 ")
                .append(" n_goods_detaisl t2   ")
                .append("  ,(  ")
                .append(" select * from ")
                .append(" (select t1.oneid,t2.title as onename, t2.orders,")
                .append(" t1.id,t1.goodsid,t1.tuanprices,t1.unitprices,t1.orders as detailsorders ")
                .append(" from n_marketing_home_details t1 LEFT JOIN n_marketing_home t2 on  t1.oneid=t2.id  ")
                .append("  where t1.audittype='1' and  t1.shoptype='0'   and t2.end_time >= now()  ")
                .append("  and t2.orders=1 ")
                .append(" ORDER BY  t1.orders asc LIMIT " + start + "," + rows + ")as t1 ")
                .append("     union ")
                .append(" select * from ")
                .append(" (select t1.oneid,t2.title as onename,t2.orders,")
                .append(" t1.id,t1.goodsid,t1.tuanprices,t1.unitprices,t1.orders as detailsorders ")
                .append(" from n_marketing_home_details t1 LEFT JOIN n_marketing_home t2 on  t1.oneid=t2.id  ")
                .append("  where t1.audittype='1' and  t1.shoptype='0'  and t2.end_time >= now()  ")
                .append(" and t2.orders=2 ")
                .append(" ORDER BY  t1.orders asc ")
                .append(" LIMIT ")
                .append(start)
                .append(",")
                .append(rows)
                .append(" )as t2 ")
                .append(" ) t1 ")
                .append("  where t2.id=t1.goodsid   and t2.goods_detaisl_status='2'  ")
                .append(" and t2.shangpintype='0'  and t2.join_huodong='1' ")
                .append(" ORDER BY  t1.orders,t1.detailsorders asc");
        System.out.println("sql==" + sql.toString());
        List<Object[]> mode_list = systemService.findListbySql(sql.toString());
        for (int i = 0; i < mode_list.size(); i++) {
            Object[] mode = mode_list.get(i);
            JSONObject object_mode = new JSONObject();
            JSONObject two_mode = new JSONObject();
            //专题顺序
            String orders = mode[40] + "";
            if ("1".equals(orders)) {
                object_mode.put("onedetailsid", mode[0]);
                object_mode.put("oneid", mode[1]);
                object_mode.put("id", mode[2]);
                object_mode.put("title", mode[6]);
                object_mode.put("duan_title", mode[7]);
                object_mode.put("starttype", mode[8]);
                object_mode.put("pic_one", mode[16]);
                String tuanprices = mode[36] + "";
                if (tuanprices.indexOf("~") != -1)
                    tuanprices = tuanprices.substring(0, tuanprices.indexOf("~"));
                object_mode.put("tuanprices", tuanprices);
                Object mp = mode[44];
                if(mp != null){
                    String memberprices = mp.toString();
                    if (memberprices.indexOf("~") != -1) {
                        memberprices = memberprices.substring(0, memberprices.indexOf("~"));
                    }
                    object_mode.put("memberprices",memberprices);
                }
                object_mode.put("threeLevle", mode[45]);
                object_mode.put("marketing_one", mode[38]);
                object_mode.put("onename", mode[39]);
                object_mode.put("detailsorders", mode[41]);
                object_mode.put("pic_two", mode[42]);
                String sellnumbs = mode[43] + "";
                if (StringUtil.is_khEmpty(sellnumbs)) {
                    sellnumbs = "0";
                }
                object_mode.put("sellnumbs", sellnumbs);
                arry.add(object_mode);
            } else {
                two_mode.put("onedetailsid", mode[0]);
                two_mode.put("oneid", mode[1]);
                two_mode.put("id", mode[2]);
                two_mode.put("title", mode[6]);
                two_mode.put("duan_title", mode[7]);
                two_mode.put("starttype", mode[8]);
                two_mode.put("pic_one", mode[16]);
                String tuanprices = mode[37] + "";
                if (tuanprices.indexOf("~") != -1)
                    tuanprices = tuanprices.substring(0, tuanprices.indexOf("~"));
                two_mode.put("tuanprices", tuanprices);
                //.append(",t1.onename,t1.price_statu,t1.price,t1.orders ")
                two_mode.put("marketing_one", mode[38]);
                two_mode.put("onename", mode[39]);
                two_mode.put("detailsorders", mode[41]);
                two_mode.put("pic_two", mode[42]);
                String sellnumbs = mode[43] + "";
                if (StringUtil.is_khEmpty(sellnumbs)) {
                    sellnumbs = "0";
                }
                two_mode.put("sellnumbs", sellnumbs);
                two.add(two_mode);
            }
        }
        object.put("details_two", two);
        object.put("details", arry);
        object.put("status", "success");
        object.put("message", "获取信息成功");
        TagUtil.getSendJson(response, object.toString());
    }*/
    @RequestMapping(params = "n_marketing_home")
    public void n_marketing_home(HttpServletRequest request, HttpServletResponse response, Integer page, Integer rows) {
        Integer start = (page - 1) * rows;
        if (start > 0) {
            if (page > 0) {
                page = page - 1;
                start = page * rows;
            } else {
                start = 0;
            }
        }
        JSONObject object = new JSONObject();
        JSONArray arry = new JSONArray();
        String sql = "SELECT t1.id as onedetailsid,t1.oneid,t2.id,t2.title,t2.duan_title,t2.starttype,t2.RETURNS, " +
                " t2.tuanprices,t2.unitprices,t2.marketing_one,t3.title as title1,t1.orders,t2.pic_two,t2.sellnumbs,t2.memberprices, " +
                " t2.three_level FROM n_goods_detaisl t2 LEFT JOIN n_marketing_home_details t1 on t1.goodsid = t2.id LEFT JOIN n_marketing_home t3 on t1.oneid = t3.id " +
                " where t1.audittype = '1' AND t1.shoptype = '0' AND t3.end_time >= now() " +
                " AND t2.goods_detaisl_status = '2' AND t2.shangpintype = '0' AND t2.join_huodong = '1' " +
                " ORDER BY t1.orders,t1.update_date  limit "+start+","+rows+";";
        LogUtil.info("首页活动商品sql="+sql);
        List<Object[]> mode_list = systemService.findListbySql(sql);
        for (int i = 0; i < mode_list.size(); i++) {
            Object[] mode = mode_list.get(i);
            JSONObject object_mode = new JSONObject();
                object_mode.put("onedetailsid", mode[0]);
                object_mode.put("oneid", mode[1]);
                object_mode.put("id", mode[2]);
                object_mode.put("title", mode[3]);
                object_mode.put("duan_title", mode[4]);
                object_mode.put("starttype", mode[5]);
                object_mode.put("isReturn", mode[6]);
                //object_mode.put("pic_one", mode[16]);
                String tuanprices = mode[7] + "";
                if (tuanprices.indexOf("~") != -1) {
                    tuanprices = tuanprices.substring(0, tuanprices.indexOf("~"));
                }
                object_mode.put("tuanprices", tuanprices);
                Object mp1 = mode[14];
                if(mp1 != null){
                    String mPrices = mp1.toString();
                    if (mPrices.indexOf("~") != -1) {
                        mPrices = mPrices.substring(0, mPrices.indexOf("~"));
                    }
                    object_mode.put("memberprices",mPrices);
                }
                object_mode.put("threeLevle", mode[15]);
                object_mode.put("marketing_one", mode[9]);
                object_mode.put("onename", mode[10]);
                object_mode.put("detailsorders", mode[11]);
                object_mode.put("pic_two", mode[12]);
                String sellnumbs = mode[13] + "";
                if (StringUtil.is_khEmpty(sellnumbs)) {
                    sellnumbs = "0";
                }
                object_mode.put("sellnumbs", sellnumbs);
                arry.add(object_mode);
        }
        object.put("details", arry);
        object.put("status", "success");
        object.put("message", "获取信息成功");
        TagUtil.getSendJson(response, object.toString());
    }
    /* 限时秒杀商品n_marketing_one_details*/
	/*@RequestMapping(params = "n_marketing_one")
	public void n_marketing_one(HttpServletRequest request,HttpServletResponse response,Integer page,Integer rows) {
		Integer start = (page - 1) * rows;
		if (start > 0) {
			if (page > 0) {
				page = page - 1;
				start = page * rows;
			} else {
				start = 0;
			}
		}
		JSONObject object = new JSONObject();
		JSONArray arry = new JSONArray();
		StringBuffer sql = new StringBuffer();
		sql.append(" select t1.id as onedetailsid , t1.oneid,")
				.append("t2.id,t2.goods_detaisl_status,t2.merchant_id, ")
				.append("t2.goods_classify_id,t2.title,t2.duan_title,")
				.append("t2.starttype,t2.weight,t2.bazaar_price,t2.contents,")
				.append("t2.send_time,t2.post,t2.returns,t2.fine,t2.pic_one,")
				.append("t2.standard,t2.standard_one,t2.standard_two,")
				.append("t2.sum_numbers,t2.codes,t2.group_purchase,")
				.append("t2.set_bounds,t2.xiangou,t2.reject,t2.orders,")
				.append("t2.shangpintype,t2.join_huodong,t2.carousel,")
				.append("t2.details,t2.goodsone,t2.goodstwo,t2.goodsthree,")
				.append("t2.standard_onelist,t2.standard_twolist,t2.tuanprices,t2.unitprices,t2.marketing_one ")
				.append(",t1.onename,t1.orders as morders,t1.detailsorders,t2.pic_two,t2.sellnumbs ")
				.append(" from ")
				.append(" n_goods_detaisl t2   ")
				.append("  ,(  ")
				.append(" select t1.oneid,t2.title as onename, t2.orders,")
				.append(" t1.id,t1.goodsid,t1.tuanprices,t1.unitprices,t1.orders as detailsorders  ")
				.append(" from n_marketing_one_details t1 LEFT JOIN n_marketing_one t2 on  t1.oneid=t2.id  ")
				.append("  where t1.audittype='1' and  t1.shoptype='0'  and t2.end_time >= now() ")
				.append( " ORDER BY   t2.orders,t1.orders asc ")
				.append(" LIMIT ")
				.append(start)
				.append(",")
				.append(rows)
				.append(")as t1 ")
				.append("  where t2.id=t1.goodsid   and t2.goods_detaisl_status='2'  ")
				.append(" and t2.shangpintype='0' and t2.join_huodong='1' ")
				.append(" ORDER BY  t1.orders,t1.detailsorders asc");
		System.out.println("sql=="+sql.toString());
		List<Object[]> mode_list = systemService.findListbySql(sql.toString());
		for(int i=0; i<mode_list.size(); i++) {
			Object[] mode = mode_list.get(i);
			JSONObject object_mode = new JSONObject();
			object_mode.put("onedetailsid", mode[0]);
			object_mode.put("oneid", mode[1]);
			object_mode.put("id", mode[2]);
			object_mode.put("title", mode[6]);
			object_mode.put("duan_title", mode[7]);
			object_mode.put("starttype", mode[8]);
			object_mode.put("pic_one", mode[16]);
			String tuanprices=mode[36]+"";
			if(tuanprices.indexOf("~")!=-1)
				tuanprices=tuanprices.substring(0,tuanprices.indexOf("~"));
			object_mode.put("tuanprices", tuanprices);
			object_mode.put("marketing_one", mode[38]);
			object_mode.put("onename", mode[39]);
			object_mode.put("detailsorders", mode[41]);
			object_mode.put("pic_two", mode[42]);
			String sellnumbs=mode[43]+"";
			if(StringUtil.is_khEmpty(sellnumbs)){
				sellnumbs="0";
			}
			object_mode.put("sellnumbs", sellnumbs);
			arry.add(object_mode);

		}
		object.put("details", arry);
		object.put("status", "success");
		object.put("message", "获取信息成功");
		TagUtil.getSendJson(response, object.toString());
	}*/

    /**
     * @Author shishanshan
     * @Desprition 获取会员商品信息
     * @Date 2018/9/11 17:05
     * @Param
     * @Return
     */
    @RequestMapping(params = "n_marketing_one")
    public void n_marketing_one(HttpServletRequest request, HttpServletResponse response, Integer page, Integer rows) {
        JSONObject object = new JSONObject();
        JSONArray arry = new JSONArray();
        StringBuffer sql = new StringBuffer();
        sql.append("select  id,pic_two,title,tuanprices,sellnumbs from n_goods_detaisl  where is_member = '0' and goods_detaisl_status = '2'  and shangpintype = '0'  ORDER BY orders LIMIT 1,50");
        List<Object[]> mode_list = systemService.findListbySql(sql.toString());
        for (int i = 0; i < mode_list.size(); i++) {
            Object[] mode = mode_list.get(i);
            JSONObject object_mode = new JSONObject();
            object_mode.put("id", mode[0]);
            object_mode.put("pic_two", mode[1]);
            object_mode.put("title", mode[2]);
            String tuanprices = (String) mode[3];
            if (tuanprices.indexOf("~") != -1) {
                tuanprices = tuanprices.substring(0, tuanprices.indexOf("~"));
            }
            object_mode.put("tuanprices", tuanprices);
            object_mode.put("sellnumbs", mode[4]);
            arry.add(object_mode);

        }
        object.put("details", arry);
        object.put("status", "success");
        object.put("message", "获取信息成功");
        TagUtil.getSendJson(response, object.toString());
    }

    /* 9.9特卖商品n_marketing_two_details*/
    @RequestMapping(params = "n_marketing_two")
    public void n_marketing_two(HttpServletRequest request,
                                HttpServletResponse response, Integer page, Integer rows) {
        Integer start = (page - 1) * rows;
        if (start > 0) {
            if (page > 0) {
                page = page - 1;
                start = page * rows;
            } else {
                start = 0;
            }
        }
        JSONObject object = new JSONObject();
        JSONArray arry = new JSONArray();
        StringBuffer sql = new StringBuffer();
        sql.append(" select t2.id,t2.title as onename,t2.orders,t2.details ")
                .append(" from ")
                .append(" n_marketing_two t2  where   to_days(t2.end_time) >= to_days(now())  ")
                .append(" ORDER BY  t2.orders asc ")
                .append(" LIMIT ")
                .append(start)
                .append(",")
                .append(rows);
        System.out.println("sql==" + sql.toString());
        List<Object[]> mode_list = systemService.findListbySql(sql.toString());
        for (int i = 0; i < mode_list.size(); i++) {
            Object[] mode = mode_list.get(i);
            JSONObject object_mode = new JSONObject();
            object_mode.put("id", mode[0]);
            object_mode.put("onename", mode[1]);
            object_mode.put("orders", mode[2]);
            //object_mode.put("details", mode[5]);
            arry.add(object_mode);
        }
        object.put("details", arry);
        object.put("status", "success");
        object.put("message", "获取信息成功");
        TagUtil.getSendJson(response, object.toString());
    }

    /* 9.9特卖商品n_marketing_two_details*/
    @RequestMapping(params = "n_marketing_two_details")
    public void n_marketing_two_details(HttpServletRequest request,
                                        HttpServletResponse response, Integer page, Integer rows) {
        Integer start = (page - 1) * rows;
        if (start > 0) {
            if (page > 0) {
                page = page - 1;
                start = page * rows;
            } else {
                start = 0;
            }
        }
        JSONObject object = new JSONObject();
        JSONArray arry = new JSONArray();
        StringBuffer sql = new StringBuffer();
        sql.append(" select t1.id as onedetailsid , t1.oneid,")
                .append("t2.id,t2.goods_detaisl_status,t2.merchant_id, ")
                .append("t2.goods_classify_id,t2.title,t2.duan_title,")
                .append("t2.starttype,t2.weight,t2.bazaar_price,t2.contents,")
                .append("t2.send_time,t2.post,t2.returns,t2.fine,t2.pic_one,")
                .append("t2.standard,t2.standard_one,t2.standard_two,")
                .append("t2.sum_numbers,t2.codes,t2.group_purchase,")
                .append("t2.set_bounds,t2.xiangou,t2.reject,t2.orders,")
                .append("t2.shangpintype,t2.join_huodong,t2.carousel,")
                .append("t2.details,t2.goodsone,t2.goodstwo,t2.goodsthree,")
                .append("t2.standard_onelist,t2.standard_twolist,t2.tuanprices,t2.unitprices,t2.marketing_one ")
                .append(",t1.onename,t1.orders as morders,t1.detailsorders,t2.pic_two,t2.sellnumbs ")
                .append(" from ")
                .append(" n_goods_detaisl t2   ")
                .append("  ,(  ")
                .append(" select t1.oneid,t2.title as onename, t2.orders,")
                .append(" t1.id,t1.goodsid,t1.tuanprices,t1.unitprices,t1.orders as detailsorders  ")
                .append(" from n_marketing_two_details t1 LEFT JOIN n_marketing_two t2 on  t1.oneid=t2.id  ")
                .append("  where t1.audittype='1' and  t1.shoptype='0'  and t2.end_time >= now() ")
                .append(" ORDER BY   t2.orders,t1.orders asc ")
                .append(" LIMIT ")
                .append(start)
                .append(",")
                .append(rows)
                .append(")as t1 ")
                .append("  where t2.id=t1.goodsid   and t2.goods_detaisl_status='2'  ")
                .append(" and t2.shangpintype='0' and t2.join_huodong='1' ")
                .append(" ORDER BY  t1.orders,t1.detailsorders asc");
        System.out.println("sql==" + sql.toString());
        List<Object[]> mode_list = systemService.findListbySql(sql.toString());
        for (int i = 0; i < mode_list.size(); i++) {
            Object[] mode = mode_list.get(i);
            JSONObject object_mode = new JSONObject();
            object_mode.put("onedetailsid", mode[0]);
            object_mode.put("oneid", mode[1]);
            object_mode.put("id", mode[2]);
            object_mode.put("title", mode[6]);
            object_mode.put("duan_title", mode[7]);
            object_mode.put("starttype", mode[8]);
            object_mode.put("pic_one", mode[16]);
            String tuanprices = mode[36] + "";
            if (tuanprices.indexOf("~") != -1)
                tuanprices = tuanprices.substring(0, tuanprices.indexOf("~"));
            object_mode.put("tuanprices", tuanprices);
            object_mode.put("marketing_one", mode[38]);
            object_mode.put("onename", mode[39]);
            object_mode.put("detailsorders", mode[41]);
            object_mode.put("pic_two", mode[42]);
            String sellnumbs = mode[43] + "";
            if (StringUtil.is_khEmpty(sellnumbs)) {
                sellnumbs = "0";
            }
            object_mode.put("sellnumbs", sellnumbs);
            arry.add(object_mode);

        }
        object.put("details", arry);
        object.put("status", "success");
        object.put("message", "获取信息成功");
        TagUtil.getSendJson(response, object.toString());
    }

    /* 限时抽奖 n_marketing_three_details*/
    @RequestMapping(params = "n_marketing_three")
    public void n_marketing_three(HttpServletRequest request,
                                  HttpServletResponse response, Integer page, Integer rows) {
        Integer start = (page - 1) * rows;
        if (start > 0) {
            if (page > 0) {
                page = page - 1;
                start = page * rows;
            } else {
                start = 0;
            }
        }
        JSONObject object = new JSONObject();
        JSONArray arry = new JSONArray();
        StringBuffer sql = new StringBuffer();
        sql.append(" select t1.id as onedetailsid , t1.oneid,")
                .append("t2.id,t2.goods_detaisl_status,t2.merchant_id, ")
                .append("t2.goods_classify_id,t2.title,t2.duan_title,")
                .append("t2.starttype,t2.weight,t2.bazaar_price,t2.contents,")
                .append("t2.send_time,t2.post,t2.returns,t2.fine,t2.pic_one,")
                .append("t2.standard,t2.standard_one,t2.standard_two,")
                .append("t2.sum_numbers,t2.codes,t2.group_purchase,")
                .append("t2.set_bounds,t2.xiangou,t2.reject,t2.orders,")
                .append("t2.shangpintype,t2.join_huodong,t2.carousel,")
                .append("t2.details,t2.goodsone,t2.goodstwo,t2.goodsthree,")
                .append("t2.standard_onelist,t2.standard_twolist,t2.tuanprices,t2.unitprices,t2.marketing_one ")
                .append(",t1.onename,t1.orders as morders,t1.detailsorders,t2.pic_two,t2.sellnumbs  ")
                .append(" from ")
                .append(" n_goods_detaisl t2   ")
                .append("  ,(  ")
                .append(" select t1.oneid,t2.title as onename, t2.orders,")
                .append(" t1.id,t1.goodsid,t1.tuanprices,t1.unitprices,t1.orders as detailsorders  ")
                .append(" from n_marketing_three_details t1 LEFT JOIN n_marketing_three t2 on  t1.oneid=t2.id  ")
                .append("  where t1.audittype='1' and  t1.shoptype='0'  and t2.end_time >=now() ")
                .append(" ORDER BY   t2.orders,t1.orders asc  ")
                .append(" LIMIT ")
                .append(start)
                .append(",")
                .append(rows)
                .append(" )as t1 ")
                .append("  where t2.id=t1.goodsid   and t2.goods_detaisl_status='2'  ")
                .append(" and t2.shangpintype='0'  and t2.join_huodong='1' ")
                .append(" ORDER BY  t1.orders,t1.detailsorders asc");
        System.out.println("sql==" + sql.toString());
        List<Object[]> mode_list = systemService.findListbySql(sql.toString());
        for (int i = 0; i < mode_list.size(); i++) {
            Object[] mode = mode_list.get(i);
            JSONObject object_mode = new JSONObject();
            object_mode.put("onedetailsid", mode[0]);
            object_mode.put("oneid", mode[1]);
            object_mode.put("id", mode[2]);
            object_mode.put("title", mode[6]);
            object_mode.put("duan_title", mode[7]);
            object_mode.put("starttype", mode[8]);
            object_mode.put("pic_one", mode[16]);
            String tuanprices = mode[36] + "";
            if (tuanprices.indexOf("~") != -1)
                tuanprices = tuanprices.substring(0, tuanprices.indexOf("~"));
            object_mode.put("tuanprices", tuanprices);
            object_mode.put("marketing_one", mode[38]);
            object_mode.put("onename", mode[39]);
            object_mode.put("detailsorders", mode[41]);
            object_mode.put("pic_two", mode[42]);
            String sellnumbs = mode[43] + "";
            if (StringUtil.is_khEmpty(sellnumbs)) {
                sellnumbs = "0";
            }
            object_mode.put("sellnumbs", sellnumbs);
            arry.add(object_mode);

        }
        object.put("details", arry);
        object.put("status", "success");
        object.put("message", "获取信息成功");
        TagUtil.getSendJson(response, object.toString());
    }

    /* 新品推荐 n_marketing_four_details */
    @RequestMapping(params = "n_marketing_four")
    public void n_marketing_four(HttpServletRequest request,
                                 HttpServletResponse response, Integer page, Integer rows) {
        Integer start = (page - 1) * rows;
        if (start > 0) {
            if (page > 0) {
                page = page - 1;
                start = page * rows;
            } else {
                start = 0;
            }
        }
        JSONObject object = new JSONObject();
        JSONArray arry = new JSONArray();
        StringBuffer sql = new StringBuffer();
        sql.append(" select t2.id,t2.title as onename,t2.orders,t2.details ")
                .append(" from ")
                .append(" n_marketing_four t2  where   to_days(t2.end_time) >= to_days(now())  ")
                .append(" ORDER BY  t2.orders asc ")
                .append(" LIMIT ")
                .append(start)
                .append(",")
                .append(rows);
        System.out.println("sql==" + sql.toString());
        List<Object[]> mode_list = systemService.findListbySql(sql.toString());
        for (int i = 0; i < mode_list.size(); i++) {
            Object[] mode = mode_list.get(i);
            JSONObject object_mode = new JSONObject();
            object_mode.put("id", mode[0]);
            object_mode.put("onename", mode[1]);
            object_mode.put("orders", mode[2]);
            //object_mode.put("details", mode[5]);
            arry.add(object_mode);
        }
        object.put("details", arry);
        object.put("status", "success");
        object.put("message", "获取信息成功");
        TagUtil.getSendJson(response, object.toString());
    }

    /* 新品推荐 n_marketing_four_details */
    @RequestMapping(params = "n_marketing_four_details")
    public void n_marketing_four_details(HttpServletRequest request,
                                         HttpServletResponse response, Integer page, Integer rows) {
        Integer start = (page - 1) * rows;
        if (start > 0) {
            if (page > 0) {
                page = page - 1;
                start = page * rows;
            } else {
                start = 0;
            }
        }
        JSONObject object = new JSONObject();
        JSONArray arry = new JSONArray();
        StringBuffer sql = new StringBuffer();
        sql.append(" select t1.id as onedetailsid , t1.oneid,")
                .append("t2.id,t2.goods_detaisl_status,t2.merchant_id, ")
                .append("t2.goods_classify_id,t2.title,t2.duan_title,")
                .append("t2.starttype,t2.weight,t2.bazaar_price,t2.contents,")
                .append("t2.send_time,t2.post,t2.returns,t2.fine,t2.pic_one,")
                .append("t2.standard,t2.standard_one,t2.standard_two,")
                .append("t2.sum_numbers,t2.codes,t2.group_purchase,")
                .append("t2.set_bounds,t2.xiangou,t2.reject,t2.orders,")
                .append("t2.shangpintype,t2.join_huodong,t2.carousel,")
                .append("t2.details,t2.goodsone,t2.goodstwo,t2.goodsthree,")
                .append("t2.standard_onelist,t2.standard_twolist,t2.tuanprices,t2.unitprices,t2.marketing_one ")
                .append(",t1.onename,t1.orders as morders,t1.detailsorders,t2.pic_two,t2.sellnumbs  ")
                .append(" from ")
                .append(" n_goods_detaisl t2   ")
                .append("  ,(  ")
                .append(" select t1.oneid,t2.title as onename, t2.orders,")
                .append(" t1.id,t1.goodsid,t1.tuanprices,t1.unitprices,t1.orders as detailsorders  ")
                .append(" from n_marketing_four_details t1 LEFT JOIN n_marketing_four t2 on  t1.oneid=t2.id  ")
                .append("  where t1.audittype='1' and  t1.shoptype='0'  and t2.end_time >=now() ")
                .append(" ORDER BY   t2.orders,t1.orders asc  ")
                .append(" LIMIT ")
                .append(start)
                .append(",")
                .append(rows)
                .append(" )as t1 ")
                .append("  where t2.id=t1.goodsid   and t2.goods_detaisl_status='2'  ")
                .append(" and t2.shangpintype='0'  and t2.join_huodong='1' ")
                .append(" ORDER BY  t1.orders,t1.detailsorders asc");
        System.out.println("sql==" + sql.toString());
        List<Object[]> mode_list = systemService.findListbySql(sql.toString());
        for (int i = 0; i < mode_list.size(); i++) {
            Object[] mode = mode_list.get(i);
            JSONObject object_mode = new JSONObject();
            object_mode.put("onedetailsid", mode[0]);
            object_mode.put("oneid", mode[1]);
            object_mode.put("id", mode[2]);
            object_mode.put("title", mode[6]);
            object_mode.put("duan_title", mode[7]);
            object_mode.put("starttype", mode[8]);
            object_mode.put("pic_one", mode[16]);
            String tuanprices = mode[36] + "";
            if (tuanprices.indexOf("~") != -1)
                tuanprices = tuanprices.substring(0, tuanprices.indexOf("~"));
            object_mode.put("tuanprices", tuanprices);
            object_mode.put("marketing_one", mode[38]);
            object_mode.put("onename", mode[39]);
            object_mode.put("detailsorders", mode[41]);
            object_mode.put("pic_two", mode[42]);
            String sellnumbs = mode[43] + "";
            if (StringUtil.is_khEmpty(sellnumbs)) {
                sellnumbs = "0";
            }
            object_mode.put("sellnumbs", sellnumbs);
            arry.add(object_mode);

        }
        object.put("details", arry);
        object.put("status", "success");
        object.put("message", "获取信息成功");
        TagUtil.getSendJson(response, object.toString());
    }

    /* 赚客 n_marketing_five */
    @RequestMapping(params = "n_marketing_five")
    public void n_marketing_five(HttpServletResponse response, Integer page, Integer rows) {
        Integer start = (page - 1) * rows;
        if (start > 0) {
            if (page > 0) {
                page = page - 1;
                start = page * rows;
            } else {
                start = 0;
            }
        }
        JSONObject object = new JSONObject();
        JSONArray arry = new JSONArray();
        StringBuffer sql = new StringBuffer();
        sql.append(" select t1.id as onedetailsid , t1.oneid,")
                .append("t2.id,t2.goods_detaisl_status,t2.merchant_id, ")
                .append("t2.goods_classify_id,t2.title,t2.duan_title,")
                .append("t2.starttype,t2.weight,t2.bazaar_price,t2.contents,")
                .append("t2.send_time,t2.post,t2.returns,t2.fine,t2.pic_one,")
                .append("t2.standard,t2.standard_one,t2.standard_two,")
                .append("t2.sum_numbers,t2.codes,t2.group_purchase,")
                .append("t2.set_bounds,t2.xiangou,t2.reject,t2.orders,")
                .append("t2.shangpintype,t2.join_huodong,t2.carousel,")
                .append("t2.details,t2.goodsone,t2.goodstwo,t2.goodsthree,")
                .append("t2.standard_onelist,t2.standard_twolist,t2.tuanprices,t2.unitprices,t2.marketing_one ")
                .append(",t1.onename,t1.orders as morders,t1.detailsorders,t2.pic_two,t2.sellnumbs,t2.memberprices,t2.three_level ")
                .append(" from ")
                .append(" n_goods_detaisl t2   ")
                .append("  ,(  ")
                .append(" select t1.oneid,t2.title as onename, t2.orders,")
                .append(" t1.id,t1.goodsid,t1.tuanprices,t1.unitprices,t1.orders as detailsorders  ")
                .append(" from n_marketing_five_details t1 LEFT JOIN n_marketing_five t2 on  t1.oneid=t2.id  ")
                .append("  where t1.audittype='1' and  t1.shoptype='0'  and t2.end_time >= now() ")
                .append(" ORDER BY   t2.orders,t1.orders asc ")
                .append(" LIMIT ")
                .append(start)
                .append(",")
                .append(rows)
                .append(" )as t1 ")
                .append("  where t2.id=t1.goodsid   and t2.goods_detaisl_status='2'  ")
                .append(" and t2.shangpintype='0'  and t2.join_huodong='1' ")
                .append(" ORDER BY  t1.orders,t1.detailsorders asc");
        System.out.println("sql==" + sql.toString());
        List<Object[]> mode_list = systemService.findListbySql(sql.toString());
        for (int i = 0; i < mode_list.size(); i++) {
            Object[] mode = mode_list.get(i);
            JSONObject object_mode = new JSONObject();
            object_mode.put("onedetailsid", mode[0]);
            object_mode.put("oneid", mode[1]);
            object_mode.put("id", mode[2]);
            object_mode.put("title", mode[6]);
            object_mode.put("duan_title", mode[7]);
            object_mode.put("starttype", mode[8]);
            object_mode.put("pic_one", mode[16]);
            String tuanprices = mode[36] + "";
            if (tuanprices.indexOf("~") != -1)
                tuanprices = tuanprices.substring(0, tuanprices.indexOf("~"));
            object_mode.put("tuanprices", tuanprices);
            Object mp = mode[44];
            if(mp != null){
                String memberprices = mp.toString();
                if (memberprices.indexOf("~") != -1) {
                    memberprices = memberprices.substring(0, memberprices.indexOf("~"));
                }
                object_mode.put("memberprices",memberprices);
            }
            object_mode.put("threeLevel", mode[45]);
            object_mode.put("marketing_one", mode[38]);
            object_mode.put("onename", mode[39]);
            object_mode.put("detailsorders", mode[41]);
            object_mode.put("pic_two", mode[42]);
            String sellnumbs = mode[43] + "";
            if (StringUtil.is_khEmpty(sellnumbs)) {
                sellnumbs = "0";
            }
            object_mode.put("sellnumbs", sellnumbs);
            arry.add(object_mode);

        }
        object.put("details", arry);
        object.put("status", "success");
        object.put("message", "获取信息成功");
        TagUtil.getSendJson(response, object.toString());
    }
     /**
      * @Author shishanshan
      * @Desprition 会员商品首页活动
      * @Date 2018/11/20 12:34
      * @Param
      * @Return
      **/
    @RequestMapping(params = "n_marketing_member")
    public void n_marketing_member(HttpServletResponse response, Integer page, Integer rows,String memberType) {
        Integer start = (page - 1) * rows;
        if (start > 0) {
            if (page > 0) {
                page = page - 1;
                start = page * rows;
            } else {
                start = 0;
            }
        }
        JSONObject object = new JSONObject();
        JSONArray arry = new JSONArray();
        StringBuffer sql = new StringBuffer();
        sql.append(" select t1.id as onedetailsid , t1.oneid,")
                .append("t2.id,t2.goods_detaisl_status,t2.merchant_id, ")
                .append("t2.goods_classify_id,t2.title,t2.duan_title,")
                .append("t2.starttype,t2.weight,t2.bazaar_price,t2.contents,")
                .append("t2.send_time,t2.post,t2.returns,t2.fine,t2.pic_one,")
                .append("t2.standard,t2.standard_one,t2.standard_two,")
                .append("t2.sum_numbers,t2.codes,t2.group_purchase,")
                .append("t2.set_bounds,t2.xiangou,t2.reject,t2.orders,")
                .append("t2.shangpintype,t2.join_huodong,t2.carousel,")
                .append("t2.details,t2.goodsone,t2.goodstwo,t2.goodsthree,")
                .append("t2.standard_onelist,t2.standard_twolist,t2.tuanprices,t2.unitprices,t2.marketing_one ")
                .append(",t1.onename,t1.orders as morders,t1.detailsorders,t2.pic_two,t2.sellnumbs,t2.is_member,t2.memberprices,t2.three_level ")
                .append(" from ")
                .append(" n_goods_detaisl t2   ")
                .append("  ,(  ")
                .append(" select t1.oneid,t2.title as onename, t2.orders,")
                .append(" t1.id,t1.goodsid,t1.tuanprices,t1.unitprices,t1.orders as detailsorders  ")
                .append(" from n_marketing_member_details t1 LEFT JOIN n_marketing_member t2 on  t1.oneid=t2.id  ")
                .append("  where t1.audittype='1' and  t1.shoptype='0'  and t2.end_time >= now() ")
                .append(" ORDER BY   t2.orders,t1.orders asc ")
                .append(" LIMIT ")
                .append(start)
                .append(",")
                .append(rows)
                .append(" )as t1 ")
                .append("  where t2.id=t1.goodsid   and t2.goods_detaisl_status='2'  ")
                .append(" and t2.shangpintype='0'  and t2.join_huodong='1' ")
                .append(" and t2.is_member = '"+memberType+"'")
                .append(" ORDER BY  t1.orders,t1.detailsorders asc");
        System.out.println("会员商品首页活动sql==" + sql.toString());
        List<Object[]> mode_list = systemService.findListbySql(sql.toString());
        for (int i = 0; i < mode_list.size(); i++) {
            Object[] mode = mode_list.get(i);
            JSONObject object_mode = new JSONObject();
            object_mode.put("onedetailsid", mode[0]);
            object_mode.put("oneid", mode[1]);
            object_mode.put("id", mode[2]);
            object_mode.put("title", mode[6]);
            object_mode.put("duan_title", mode[7]);
            object_mode.put("starttype", mode[8]);
            object_mode.put("pic_one", mode[16]);
            String tuanprices = mode[36] + "";
            if (tuanprices.indexOf("~") != -1) {
                tuanprices = tuanprices.substring(0, tuanprices.indexOf("~"));
            }
            object_mode.put("tuanprices", tuanprices);
            Object mp = mode[45];
            if(mp !=null){
                String memberprices = mp.toString();
                if (memberprices.indexOf("~") != -1) {
                    memberprices = memberprices.substring(0, memberprices.indexOf("~"));
                }
                object_mode.put("memberprices",memberprices);
            }
            object_mode.put("threeLevel", mode[46]);
            object_mode.put("marketing_one", mode[38]);
            object_mode.put("onename", mode[39]);
            object_mode.put("detailsorders", mode[41]);
            object_mode.put("pic_two", mode[42]);
            String sellnumbs = mode[43] + "";
            if (StringUtil.is_khEmpty(sellnumbs)) {
                sellnumbs = "0";
            }
            object_mode.put("sellnumbs", sellnumbs);
            arry.add(object_mode);

        }
        object.put("details", arry);
        object.put("status", "success");
        object.put("message", "获取信息成功");
        TagUtil.getSendJson(response, object.toString());
    }

    /* 普通团   starttype 1任务团2普通团*/
    @RequestMapping(params = "ptstarttypept")
    public void starttype(HttpServletRequest request,HttpServletResponse response, Integer page, Integer rows) {
        Integer start = (page - 1) * rows;
        if (start > 0) {
            if (page > 0) {
                page = page - 1;
                start = page * rows;
            } else {
                start = 0;
            }
        }
        JSONObject object = new JSONObject();
        JSONArray arry = new JSONArray();
        StringBuffer sql = new StringBuffer();
        sql.append(" select ")
                .append("t2.id,t2.goods_detaisl_status,t2.merchant_id, ")
                .append("t2.goods_classify_id,t2.title,t2.duan_title,")
                .append("t2.starttype,t2.weight,t2.bazaar_price,t2.contents,")
                .append("t2.send_time,t2.post,t2.returns,t2.fine,t2.pic_one,")
                .append("t2.standard,t2.standard_one,t2.standard_two,")
                .append("t2.sum_numbers,t2.codes,t2.group_purchase,")
                .append("t2.set_bounds,t2.xiangou,t2.reject,t2.orders,")
                .append("t2.shangpintype,t2.join_huodong,t2.carousel,")
                .append("t2.details,t2.goodsone,t2.goodstwo,t2.goodsthree,")
                .append("t2.standard_onelist,t2.standard_twolist,t2.tuanprices,t2.unitprices,t2.pic_two,t2.sellnumbs,t2.memberprices,t2.three_level ")
                .append("from n_goods_detaisl t2  where t2.goods_detaisl_status='2' and t2.starttype='2'")
                //.append(" and t2.join_huodong='0' and t2.shangpintype='0' ORDER BY t2.orders asc,t2.title asc  ")
                .append(" and t2.join_huodong='0' and t2.shangpintype='0' ORDER BY rand()  ")
                .append(" LIMIT ")
                .append(start)
                .append(",")
                .append(rows);
        /**普通团不参加活动*/
        List<Object[]> mode_list = systemService.findListbySql(sql.toString());
        for (int i = 0; i < mode_list.size(); i++) {
            Object[] mode = mode_list.get(i);
            JSONObject object_mode = new JSONObject();
            object_mode.put("id", mode[0]);
            object_mode.put("title", mode[4]);
            object_mode.put("duan_title", mode[5]);
            object_mode.put("starttype", mode[6]);
            object_mode.put("pic_one", mode[14]);
            String tuanprices = mode[34] + "";
            if (tuanprices.indexOf("~") != -1){
                tuanprices = tuanprices.substring(0, tuanprices.indexOf("~"));
            }
            object_mode.put("tuanprices", tuanprices);
            object_mode.put("marketing_one", "0");
            object_mode.put("pic_two", mode[36]);
            String sellnumbs = mode[37] + "";
            if (StringUtil.is_khEmpty(sellnumbs)) {
                sellnumbs = "0";
            } else {
            }
            object_mode.put("sellnumbs", sellnumbs);
            Object mp = mode[38];
            if(mp !=null){
                String memberprices = mp.toString();
                if (memberprices.indexOf("~") != -1) {
                    memberprices = memberprices.substring(0, memberprices.indexOf("~"));
                }
                object_mode.put("memberprices",memberprices);
            }
            object_mode.put("threeLevel", mode[39]);
            arry.add(object_mode);
        }
        object.put("details", arry);
        object.put("status", "success");
        object.put("message", "获取信息成功");
        TagUtil.getSendJson(response, object.toString());
    }


    /* 搜索-分类*/
    @RequestMapping(params = "goodsclassify")
    public void goodsclassify(HttpServletRequest request,
                              HttpServletResponse response) {
        JSONObject object = new JSONObject();
        JSONArray arry = new JSONArray();
        StringBuffer sql = new StringBuffer();
        sql.append(" select  id,departname,parentdepartid,org_code ")
                .append(" from  n_goods_classify  where del_flag='0'  ")
                .append(" and parentdepartid='0' ORDER BY create_date asc ");

        List<Object[]> mode_list = systemService.findListbySql(sql.toString());
        for (int i = 0; i < mode_list.size(); i++) {
            Object[] mode = mode_list.get(i);
            JSONObject object_mode = new JSONObject();
            JSONArray parry = new JSONArray();
            object_mode.put("id", mode[0]);
            object_mode.put("name", mode[1]);
            object_mode.put("pid", mode[3]);
            object_mode.put("org_code", mode[3]);
            StringBuffer pidsql = new StringBuffer();
            pidsql.append(" select  id,departname,parentdepartid,org_code,picurl ")
                    .append(" from  n_goods_classify  where  del_flag='0' ")
                    .append(" and parentdepartid='")
                    .append(mode[0])
                    .append("'   ORDER BY create_date asc ");
            List<Object[]> p_list = systemService.findListbySql(pidsql.toString());
            for (int j = 0; j < p_list.size(); j++) {
                Object[] pmode = p_list.get(j);
                JSONObject p_mode = new JSONObject();
                p_mode.put("id", pmode[0]);
                p_mode.put("name", pmode[1]);
                p_mode.put("pid", pmode[3]);
                p_mode.put("org_code", pmode[3]);
                p_mode.put("picurl", pmode[4]);
                parry.add(p_mode);
            }
            object_mode.put("plist", parry);
            arry.add(object_mode);
        }
        object.put("details", arry);
        object.put("status", "success");
        object.put("message", "获取信息成功");
        TagUtil.getSendJson(response, object.toString());
    }

    /* 搜索-分类子集*/
    @RequestMapping(params = "goodsclassifypid")
    public void goodsclassifypid(HttpServletRequest request,
                                 HttpServletResponse response, String id) {
        JSONObject object = new JSONObject();
        JSONArray parry = new JSONArray();
        StringBuffer pidsql = new StringBuffer();
        pidsql.append(" select  id,departname,parentdepartid,org_code,picurl ")
                .append(" from  n_goods_classify  where  del_flag='0' ");
        if (!StringUtil.is_khEmpty(id)) {
            pidsql.append(" and parentdepartid='")
                    .append(id)
                    .append("' ");
        }
        pidsql.append(" ORDER BY create_date asc ");
        List<Object[]> p_list = systemService.findListbySql(pidsql.toString());
        for (int j = 0; j < p_list.size(); j++) {
            Object[] pmode = p_list.get(j);
            JSONObject p_mode = new JSONObject();
            p_mode.put("id", pmode[0]);
            p_mode.put("name", pmode[1]);
            p_mode.put("pid", pmode[3]);
            p_mode.put("org_code", pmode[3]);
            p_mode.put("picurl", pmode[4]);
            parry.add(p_mode);
        }
        object.put("details", parry);
        object.put("status", "success");
        object.put("message", "获取信息成功");
        TagUtil.getSendJson(response, object.toString());
    }

    /* 搜索-更多 starttype 1任务团2普通团
     * id更多
     * pid自己点击
     * */
    @RequestMapping(params = "goodsclassify_more")
    public void goodsclassify_more(HttpServletRequest request,
                                   HttpServletResponse response, String goodsone, String goodstwo, String goodsthree,
                                   String starttype, Integer page, Integer rows) {
        Integer start = (page - 1) * rows;
        if (start > 0) {
            if (page > 0) {
                page = page - 1;
                start = page * rows;
            } else {
                start = 0;
            }
        }
        JSONObject object = new JSONObject();
        JSONArray arry = new JSONArray();
        StringBuffer sql = new StringBuffer();
        sql.append(" select ")
                .append("t2.id,t2.goods_detaisl_status,t2.merchant_id, ")
                .append("t2.goods_classify_id,t2.title,t2.duan_title,")
                .append("t2.starttype,t2.weight,t2.bazaar_price,t2.contents,")
                .append("t2.send_time,t2.post,t2.returns,t2.fine,t2.pic_one,")
                .append("t2.standard,t2.standard_one,t2.standard_two,")
                .append("t2.sum_numbers,t2.codes,t2.group_purchase,")
                .append("t2.set_bounds,t2.xiangou,t2.reject,t2.orders,")
                .append("t2.shangpintype,t2.join_huodong,t2.carousel,")
                .append("t2.details,t2.goodsone,t2.goodstwo,t2.goodsthree,")
                .append("t2.standard_onelist,t2.standard_twolist,t2.tuanprices,t2.unitprices,t2.marketing_one,t2.pic_two,t2.sellnumbs ")
                .append(" from n_goods_detaisl t2  where t2.goods_detaisl_status='2' and t2.shangpintype='0' ")
        ;
        if (!StringUtil.is_khEmpty(starttype)) {
            sql.append(" and t2.starttype='")
                    .append(starttype)
                    .append("'");
        }
        if (!StringUtil.is_khEmpty(goodsone)) {
            sql.append(" and t2.goodsone='")
                    .append(goodsone)
                    .append("'");
        }
        if (!StringUtil.is_khEmpty(goodstwo)) {
            sql.append(" and t2.goodstwo='")
                    .append(goodstwo)
                    .append("'");
        }
        if (!StringUtil.is_khEmpty(goodsthree)) {
            sql.append(" and t2.goodsthree='")
                    .append(goodsthree)
                    .append("'");
        }
        sql.append(" ORDER BY t2.marketing_one desc,t2.orders asc  ")
                .append(" LIMIT ")
                .append(start)
                .append(",")
                .append(rows);
        List<Object[]> mode_list = systemService.findListbySql(sql.toString());
        for (int i = 0; i < mode_list.size(); i++) {
            Object[] mode = mode_list.get(i);
            JSONObject object_mode = new JSONObject();

            object_mode.put("id", mode[0]);
            object_mode.put("title", mode[4]);
            object_mode.put("duan_title", mode[5]);
            object_mode.put("starttype", mode[6]);
            object_mode.put("pic_one", mode[14]);
            String tuanprices = mode[34] + "";
            if (tuanprices.indexOf("~") != -1)
                tuanprices = tuanprices.substring(0, tuanprices.indexOf("~"));
            object_mode.put("tuanprices", tuanprices);
            object_mode.put("marketing_one", mode[36]);
            object_mode.put("pic_two", mode[37]);
            String sellnumbs = mode[38] + "";
            if (StringUtil.is_khEmpty(sellnumbs)) {
                sellnumbs = "0";
            }
            object_mode.put("sellnumbs", sellnumbs);
            arry.add(object_mode);
        }
        object.put("details", arry);
        object.put("status", "success");
        object.put("message", "获取信息成功");
        TagUtil.getSendJson(response, object.toString());
    }

    /* 搜索-名称 starttype 1任务团2普通团
     *
     * */
    @RequestMapping(params = "goodsclassify_serach")
    public void goodsclassify_serach(HttpServletRequest request,
                                     HttpServletResponse response, String searchname, String starttype, String userId
            , Integer page, Integer rows) {
        Integer start = (page - 1) * rows;
        if (start > 0) {
            if (page > 0) {
                page = page - 1;
                start = page * rows;
            } else {
                start = 0;
            }
        }
        JSONObject object = new JSONObject();
        JSONArray arry = new JSONArray();
        StringBuffer sql = new StringBuffer();
        sql.append(" select ")
                .append("t2.id,t2.goods_detaisl_status,t2.merchant_id, ")
                .append("t2.goods_classify_id,t2.title,t2.duan_title,")
                .append("t2.starttype,t2.weight,t2.bazaar_price,t2.contents,")
                .append("t2.send_time,t2.post,t2.returns,t2.fine,t2.pic_one,")
                .append("t2.standard,t2.standard_one,t2.standard_two,")
                .append("t2.sum_numbers,t2.codes,t2.group_purchase,")
                .append("t2.set_bounds,t2.xiangou,t2.reject,t2.orders,")
                .append("t2.shangpintype,t2.join_huodong,t2.carousel,")
                .append("t2.details,t2.goodsone,t2.goodstwo,t2.goodsthree,")
                .append("t2.standard_onelist,t2.standard_twolist,t2.tuanprices,t2.unitprices,t2.marketing_one,t2.pic_two,t2.sellnumbs  ")
                .append(" from n_goods_detaisl t2  where t2.goods_detaisl_status='2' and t2.shangpintype='0' ")
        ;
        if (!StringUtil.is_khEmpty(starttype)) {
            sql.append(" and t2.starttype='")
                    .append(starttype)
                    .append("'");
        }
        if (!StringUtil.is_khEmpty(searchname)) {
            sql.append(" and t2.title like '%")
                    .append(searchname)
                    .append("%'");
        }

        sql.append(" ORDER BY t2.marketing_one desc,t2.orders asc  ")
                .append(" LIMIT ")
                .append(start)
                .append(",")
                .append(rows);
        //System.out.println("sql="+sql.toString());
        List<Object[]> mode_list = systemService.findListbySql(sql.toString());
        for (int i = 0; i < mode_list.size(); i++) {
            Object[] mode = mode_list.get(i);
            JSONObject object_mode = new JSONObject();

            object_mode.put("id", mode[0]);
            object_mode.put("title", mode[4]);
            object_mode.put("duan_title", mode[5]);
            object_mode.put("starttype", mode[6]);
            object_mode.put("pic_one", mode[14]);
            String tuanprices = mode[34] + "";
            if (tuanprices.indexOf("~") != -1)
                tuanprices = tuanprices.substring(0, tuanprices.indexOf("~"));
            object_mode.put("tuanprices", tuanprices);
            object_mode.put("marketing_one", mode[36]);
            object_mode.put("pic_two", mode[37]);
            String sellnumbs = mode[38] + "";
            if (StringUtil.is_khEmpty(sellnumbs)) {
                sellnumbs = "0";
            } else {

            }
            object_mode.put("sellnumbs", sellnumbs);
            arry.add(object_mode);
        }
        final String t_title = searchname;
        final String t_userId = userId;
        Thread t1 = new Thread("t1") {
            public void run() {
                getSaveSearch(t_title, t_userId);
            }
        };
        t1.start();
        object.put("details", arry);
        object.put("status", "success");
        object.put("message", "获取信息成功");
        TagUtil.getSendJson(response, object.toString());
    }


    public void getSaveSearch(String title, String userId) {
        StringBuffer sql = new StringBuffer();
        sql.append("from NUserSearchEntity where 1=1")
                .append(" and userId='")
                .append(userId)
                .append("'")
                .append(" and title='")
                .append(title)
                .append("'");
        List<NUserSearchEntity> t_list = systemService.findHql(sql.toString());
        if (t_list != null && t_list.size() > 0) {

        } else {
            NUserSearchEntity nUserSearch = new NUserSearchEntity();
            nUserSearch.setCreateDate(new Date());
            nUserSearch.setTitle(title);
            nUserSearch.setUserId(userId);
            systemService.save(nUserSearch);
        }
    }

    /*获得用户收索历史 */
    @RequestMapping(params = "n_user_search")
    public void n_user_search(HttpServletRequest request,
                              HttpServletResponse response, String userId,
                              Integer page, Integer rows) {
        Integer start = (page - 1) * rows;
        if (start > 0) {
            if (page > 0) {
                page = page - 1;
                start = page * rows;
            } else {
                start = 0;
            }
        }
        JSONObject object = new JSONObject();
        JSONArray arry = new JSONArray();
        StringBuffer sql = new StringBuffer();
        sql.append("from NUserSearchEntity where 1=1")
                .append(" and userId='")
                .append(userId)
                .append("'")
                .append(" ORDER BY create_date desc  ")
                .append(" LIMIT ")
                .append(start)
                .append(",")
                .append(rows);
        List<NUserSearchEntity> t_list = systemService.findHql(sql.toString());
        for (int i = 0; i < t_list.size(); i++) {
            NUserSearchEntity mode = t_list.get(i);
            JSONObject mode_object = new JSONObject();
            mode_object.put("id", mode.getId());
            mode_object.put("title", mode.getTitle());
            arry.add(mode_object);
        }
        object.put("details", arry);
        object.put("status", "success");
        object.put("message", "获取信息成功");
        TagUtil.getSendJson(response, object.toString());
    }

    /*商品详情n_goods_detaisl */
    @RequestMapping(params = "n_goods_detaisl")
    public void n_goods_detaisl(HttpServletRequest request, HttpServletResponse response, String id) {
        JSONObject object = new JSONObject();
        NGoodsDetaislEntity mode = systemService.getEntity(NGoodsDetaislEntity.class, id);
        if (mode != null) {
            object.put("id", mode.getId());
            object.put("merchant_id", mode.getMerchantId());
            object.put("goods_classify_id", mode.getGoodsClassifyId());
            object.put("title", mode.getTitle());
            String hit = mode.getHit();
            String duanHit = mode.getDuanHit();
            if (!StringUtil.is_khEmpty(hit)) {
                object.put("hit", hit);
            } else {
                object.put("hit", mode.getTitle());
            }
            if (!StringUtil.is_khEmpty(duanHit)) {
                object.put("duanHit", duanHit);
            } else {
                object.put("duanHit", "0");
            }
            object.put("duan_title", mode.getDuanTitle());
            object.put("goods_detaisl_status", mode.getGoodsDetaislStatus());
            object.put("starttype", mode.getStarttype());
            object.put("isvirtual", mode.getIsvirtual());//0是1否
            object.put("weight", mode.getWeight());
            object.put("bazaar_price", mode.getBazaarPrice());
            object.put("contents", mode.getContents());
            object.put("send_time", mode.getSendTime());
            object.put("post", mode.getPost());
            object.put("returns", mode.getReturns());
            object.put("fine", mode.getFine());
            object.put("pic_one", mode.getPicOne());
            //String standard=mode.getStandard();
            object.put("standard", mode.getStandard());
            object.put("standard_one", mode.getStandardOne());
            object.put("standard_two", mode.getStandardTwo());
            object.put("sum_numbers", mode.getSumNumbers());
            object.put("codes", mode.getCodes());
            object.put("group_purchase", mode.getGroupPurchase());
            object.put("set_bounds", mode.getSetBounds());
            String xiangou = mode.getXiangou();
            if (StringUtil.is_khEmpty(xiangou)) {
                xiangou = "2";
            }
            object.put("xiangou", xiangou);
            object.put("memberShop", mode.getIsMember());//是否会员商品
            object.put("referralBonus", mode.getReferralBonus());//是否会员商品
            object.put("reject", mode.getReject());
            object.put("orders", mode.getOrders());
            object.put("shangpintype", mode.getShangpintype());
            object.put("join_huodong", mode.getJoinHuodong());
            object.put("carousel", mode.getCarousel());
            object.put("details", mode.getDetails());
            object.put("goodsone", mode.getGoodsone());
            object.put("goodstwo", mode.getGoodstwo());
            object.put("goodsthree", mode.getGoodsthree());
            object.put("standard_onelist", mode.getStandardOnelist());
            object.put("standard_twolist", mode.getStandardTwolist());
            object.put("merchant_id", mode.getMerchantId());
            object.put("merchantname", mode.getMerchantname());
            object.put("isOnlyOne", mode.getIsOnlyOne());
            NMerchantEntity entity = systemService.getEntity(NMerchantEntity.class, mode.getMerchantId());
            object.put("merchantlogo", entity.getMerchantlogo());

            String tuanprices = mode.getTuanprices();
            if (tuanprices.indexOf("~") != -1) {
                tuanprices = tuanprices.substring(0, tuanprices.indexOf("~"));
            }
            object.put("tuanprices", tuanprices);

            String unitprices = mode.getUnitprices();
            if (unitprices.indexOf("~") != -1) {
                unitprices = unitprices.substring(0, unitprices.indexOf("~"));
            }
            object.put("unitprices", unitprices);
            String memberprices = mode.getMemberprices();
            if(memberprices != null){
                if (memberprices.indexOf("~") != -1) {
                    memberprices = memberprices.substring(0, memberprices.indexOf("~"));
                }
            }
            object.put("memberprices", memberprices);
            object.put("threeLevel", mode.getThreeLevel());
            object.put("marketing_one", mode.getMarketingOne());
            object.put("pic_two", mode.getPicTwo());
            String sellnumbs = mode.getSellnumbs();
            if (StringUtil.is_khEmpty(sellnumbs)) {
                sellnumbs = "0";
            } else {

            }
            object.put("sellnumbs", sellnumbs);
            //
        }
        object.put("status", "success");
        object.put("message", "获取信息成功");
        TagUtil.getSendJson(response, object.toString());
    }

    /*商家信息表  n_merchant,获得商家销量*/
    @RequestMapping(params = "merchantnumb")
    public void merchantnumb(HttpServletRequest request,
                             HttpServletResponse response, String merchantId) {
        //	JSONObject object = new JSONObject();
        //	JSONArray arry = new JSONArray();
        JSONObject object_mode = new JSONObject();
        //	NMerchantEntity t = systemService.getEntity(NMerchantEntity.class,merchantId);
        StringBuffer sql = new StringBuffer();
        sql.append("select id,COUNT(*)as numbers from n_order")
                .append(" where merchant_id='")
                .append(merchantId)
                .append("' and order_status='3' GROUP BY merchant_id ");
        List<Object[]> mode_list =
                systemService.findListbySql(sql.toString());
        if (mode_list != null && mode_list.size() > 0) {
            System.out.println("mode_list.get(0)[1]=" + mode_list.get(0)[0]);
            object_mode.put("merchantnumb", mode_list.get(0)[1]);
        } else {
            object_mode.put("merchantnumb", "100");
        }
        //object.put("details", arry);
        object_mode.put("status", "success");
        object_mode.put("message", "获取信息成功");
        TagUtil.getSendJson(response, object_mode.toString());
    }

    /*商家信息表  n_merchant,获得商家订单数量*/
    @RequestMapping(params = "n_merchant_info")
    public void n_merchant_info(HttpServletRequest request,
                                HttpServletResponse response, String merchantId) {
        //	JSONObject object = new JSONObject();
        //	JSONArray arry = new JSONArray();
        NMerchantEntity t = systemService.getEntity(NMerchantEntity.class, merchantId);
        StringBuffer sql = new StringBuffer();
        sql.append("select id,COUNT(*)as numbers from n_order")
                .append(" where paystatus='0' and merchant_id='")
                .append(merchantId)
                .append("'  GROUP BY merchant_id ");
        List<Object[]> mode_list =
                systemService.findListbySql(sql.toString());
        JSONObject object_mode = new JSONObject();
        if (t != null) {
            object_mode.put("id", t.getId());
            object_mode.put("merchantname", t.getCompany());
            object_mode.put("merchantlogo", t.getMerchantlogo());
            object_mode.put("jingying", t.getJingying());
            object_mode.put("jingyingname", t.getJingyingname());
            if (mode_list != null && mode_list.size() > 0) {
                object_mode.put("merchantnumb", mode_list.get(0)[1]);
            } else {
                object_mode.put("merchantnumb", "100");
            }

        }
        //object.put("details", arry);
        object_mode.put("status", "success");
        object_mode.put("message", "获取信息成功");
        TagUtil.getSendJson(response, object_mode.toString());
    }

    /*商家信息表  n_merchant,商家名称，商家信息*/
    @RequestMapping(params = "n_merchant_info_store")
    public void n_merchant_info_store(HttpServletRequest request,
                                      HttpServletResponse response, String merchantId) {
        //	JSONObject object = new JSONObject();
        //	JSONArray arry = new JSONArray();
        NMerchantEntity t = systemService.getEntity(NMerchantEntity.class, merchantId);

        JSONObject object_mode = new JSONObject();
        if (t != null) {
            object_mode.put("id", t.getId());
            object_mode.put("merchantname", t.getCompany());
            object_mode.put("merchantlogo", t.getMerchantlogo());
            String tel = t.getTel();
            if (!StringUtil.is_khEmpty(tel)) {
                object_mode.put("tel", tel);
            } else {
                object_mode.put("tel", " ");
            }
            String sysNum = t.getSysNum();
            if (!StringUtil.is_khEmpty(sysNum)) {
                object_mode.put("sysNum", sysNum);
            } else {
                object_mode.put("sysNum", "0");
            }

            object_mode.put("jingying", t.getJingying());
            object_mode.put("jingyingname", t.getJingyingname());
        }
        //object.put("details", arry);
        object_mode.put("status", "success");
        object_mode.put("message", "获取信息成功");
        TagUtil.getSendJson(response, object_mode.toString());
    }

    /*商家商品  n_merchant*/
    @RequestMapping(params = "merchant_good")
    public void merchant_good(HttpServletRequest request,
                              HttpServletResponse response, String merchantId,
                              Integer page, Integer rows) {
        Integer start = (page - 1) * rows;
        if (start > 0) {
            if (page > 0) {
                page = page - 1;
                start = page * rows;
            } else {
                start = 0;
            }
        }
        JSONObject object = new JSONObject();
        JSONArray arry = new JSONArray();
        StringBuffer sql = new StringBuffer();
        sql.append(" select ")
                .append("t2.id,t2.goods_detaisl_status,t2.merchant_id, ")
                .append("t2.goods_classify_id,t2.title,t2.duan_title,")
                .append("t2.starttype,t2.weight,t2.bazaar_price,t2.contents,")
                .append("t2.send_time,t2.post,t2.returns,t2.fine,t2.pic_one,")
                .append("t2.standard,t2.standard_one,t2.standard_two,")
                .append("t2.sum_numbers,t2.codes,t2.group_purchase,")
                .append("t2.set_bounds,t2.xiangou,t2.reject,t2.orders,")
                .append("t2.shangpintype,t2.join_huodong,t2.carousel,")
                .append("t2.details,t2.goodsone,t2.goodstwo,t2.goodsthree,")
                .append("t2.standard_onelist,t2.standard_twolist,t2.tuanprices,t2.unitprices,t2.marketing_one,t2.pic_two,t2.sellnumbs   ")
                .append(" from n_goods_detaisl t2  where t2.goods_detaisl_status='2' and t2.shangpintype='0' ")
        ;

        if (!StringUtil.is_khEmpty(merchantId)) {
            sql.append(" and t2.merchant_id='")
                    .append(merchantId)
                    .append("'");
        }
        sql.append(" ORDER BY t2.marketing_one desc,t2.orders asc  ")
                .append(" LIMIT ")
                .append(start)
                .append(",")
                .append(rows);
        List<Object[]> mode_list = systemService.findListbySql(sql.toString());
        for (int i = 0; i < mode_list.size(); i++) {
            Object[] mode = mode_list.get(i);
            JSONObject object_mode = new JSONObject();

            object_mode.put("id", mode[0]);
            object_mode.put("title", mode[4]);
            object_mode.put("duan_title", mode[5]);
            object_mode.put("starttype", mode[6]);
            object_mode.put("pic_one", mode[14]);
            String tuanprices = mode[35] + "";
            if (tuanprices.indexOf("~") != -1)
                tuanprices = tuanprices.substring(0, tuanprices.indexOf("~"));
            object_mode.put("tuanprices", tuanprices);
            object_mode.put("marketing_one", mode[36]);
            object_mode.put("pic_two", mode[37]);
            String sellnumbs = mode[38] + "";
            if (StringUtil.is_khEmpty(sellnumbs)) {
                sellnumbs = "0";
            } else {

            }
            object_mode.put("sellnumbs", sellnumbs);
            arry.add(object_mode);
        }
        object.put("details", arry);
        object.put("status", "success");
        object.put("message", "获取信息成功");
        TagUtil.getSendJson(response, object.toString());
    }


    /*规格组合商品详情n_standard_details */
    @RequestMapping(params = "n_standard_details")
    public void n_standard_details(HttpServletRequest request,
                                   HttpServletResponse response, String goods_id) {
        JSONObject object = new JSONObject();
        JSONArray arry = new JSONArray();
        if (!StringUtil.is_khEmpty(goods_id)) {
            StringBuffer sql = new StringBuffer();
            sql.append(" select id,goods_id,standard_onename,standard_twoname,sum_number")
                    .append(" ,tuan_price ,unit_price,pic_url,shangxiatype,member_price ")
                    .append("from n_standard_details  where shangxiatype='0' and ")
                    .append(" goods_id='").append(goods_id).append("'");
            List<Object[]> mode_list = systemService.findListbySql(sql.toString());
            for (int i = 0; i < mode_list.size(); i++) {
                Object[] mode = mode_list.get(i);
                JSONObject object_mode = new JSONObject();
                object_mode.put("id", mode[0]);
                object_mode.put("goods_id", goods_id);
//		    		String standard_onename=mode[2]+"";
//		    		if(!StringUtil.is_khEmpty(standard_onename)){
                object_mode.put("standard_onename", mode[2]);
                object_mode.put("standard_twoname", mode[3]);
		    		/*}else{
		    			object_mode.put("standard_onename", "单品");
		    		}*/

                object_mode.put("sum_number", mode[4]);
                object_mode.put("tuan_price", mode[5]);
                object_mode.put("unit_price", mode[6]);
                object_mode.put("pic_url", mode[7]);
                object_mode.put("shangxiatype", mode[8]);
                object.put("memberPrice",mode[9]);
                arry.add(object_mode);
            }
        }
        object.put("details", arry);
        object.put("status", "success");
        object.put("message", "获取信息成功");
        TagUtil.getSendJson(response, object.toString());
    }


    /*获取列表目录栏商品拼团信息*/
    @RequestMapping(params = "list_ptorder_info")
    public void list_ptorder_info(HttpServletRequest request,
                                  HttpServletResponse response, NOrderEntity mode, String goodsId
            , String id, Integer page, Integer rows) {
        Integer start = (page - 1) * rows;
        if (start > 0) {
            if (page > 0) {
                page = page - 1;
                start = page * rows;
            } else {
                start = 0;
            }
        }
        JSONObject object = new JSONObject();
        JSONArray arry = new JSONArray();
        if (!StringUtil.is_khEmpty(goodsId)) {
            StringBuffer sql = new StringBuffer();
            sql.append("select  t1.id,t1.goods_id,t1.goodsname,t1.user_id,t1.realname,t1.usernameurl, ")
                    .append(" t1.marketing_one,t1.goods_detaisl_type,t1.merchant_id,t1.merchantname, ")
                    .append(" t1.order_status,t1.order_type,t1.aftersale_status,t1.aftersale_type, ")
                    .append(" t1.goods_sum,t1.numbers,t1.pay_sum,t1.express_nub, ")
                    .append(" t1.express_name,t1.details,t1.merchant_back,t1.joinordertype, ")
                    .append(" t1.joinorderstatus,t1.orderid,t1.ptstatus,t1.create_date,t1.end_time, ")
                    .append(" from n_order t1 ")
                    .append("  where  t1.joinordertype='0' and t1.joinorderstatus='0' and t1.paystatus='0'  ");
            if (!StringUtil.is_khEmpty(goodsId)) {
                sql.append("   and t1.goods_id='")
                        .append(goodsId)
                        .append("'");
            }
            //主要用于某一个拼单信息
			 /*  if(!StringUtil.is_khEmpty(id)){
				  sql.append("' and id='")
				   .append(id)
				   .append("'");
		       }*/
            // sql.append("  GROUP BY orderid   ORDER BY create_date asc   ")
            sql.append("    ORDER BY t1.create_date asc   ")
                    .append(" LIMIT ")
                    .append(start)
                    .append(",")
                    .append(rows);
            List<Object[]> mode_list = systemService.findListbySql(sql.toString());
            for (int i = 0; i < mode_list.size(); i++) {
                Object[] t = mode_list.get(i);
                JSONObject object_mode = new JSONObject();
                object_mode.put("id", t[0]);
                object_mode.put("goods_id", t[1]);
                object_mode.put("goodsname", t[2]);
                object_mode.put("user_id", t[3]);
                object_mode.put("realname", t[4]);
                object_mode.put("usernameurl", t[5]);
                object_mode.put("goodsDetaislType", t[7]);
                object_mode.put("merchantId", t[8]);
                object_mode.put("merchantname", t[9]);
                object_mode.put("orderid", t[23]);
                object_mode.put("create_date", t[25]);
                object_mode.put("end_time", t[26]);
                arry.add(object_mode);
            }
        }
        object.put("details", arry);
        object.put("status", "success");
        object.put("message", "获取信息成功");
        TagUtil.getSendJson(response, object.toString());
    }


    /*获取商品拼团信息*/
    @RequestMapping(params = "ptorder_info")
    public void ptorder_info(HttpServletRequest request,
                             HttpServletResponse response, NOrderEntity mode, String goodsId
            , String id, Integer page, Integer rows) {
        Integer start = (page - 1) * rows;
        if (start > 0) {
            if (page > 0) {
                page = page - 1;
                start = page * rows;
            } else {
                start = 0;
            }
        }
        JSONObject object = new JSONObject();
        JSONArray arry = new JSONArray();
        if (!StringUtil.is_khEmpty(goodsId)) {
            StringBuffer sql = new StringBuffer();
            sql.append("select  t1.id,t1.goods_id,t1.goodsname,t1.user_id,t1.realname,t1.usernameurl, ")
                    .append(" t1.marketing_one,t1.goods_detaisl_type,t1.merchant_id,t1.merchantname, ")
                    .append(" t1.order_status,t1.order_type,t1.aftersale_status,t1.aftersale_type, ")
                    .append(" t1.goods_sum,t1.numbers,t1.pay_sum,t1.express_nub, ")
                    .append(" t1.express_name,t1.details,t1.merchant_back,t1.joinordertype, ")
                    .append(" t1.joinorderstatus,t1.orderid,t1.ptstatus,t1.create_date,t1.end_time,t2.ptnumbs ")
                    .append(" from n_order t1 ")
                    .append(" ,(select *,count(*)as ptnumbs from n_order where paystatus='0' and order_type='0' and order_status='1' ");
            if (!StringUtil.is_khEmpty(goodsId)) {
                sql.append("   and goods_id='")
                        .append(goodsId)
                        .append("'");
            }
            sql.append("  GROUP BY orderid  )t2 where t1.orderid=t2.orderid  and t1.goods_id=t2.goods_id ")
                    .append(" and t1.joinordertype='0' and t1.joinorderstatus='0' and t1.order_type='0' and t1.order_status='1' and t1.paystatus='0'  ");
            if (!StringUtil.is_khEmpty(goodsId)) {
                sql.append("   and t1.goods_id='")
                        .append(goodsId)
                        .append("'");
            }
            //主要用于某一个拼单信息
			 /*  if(!StringUtil.is_khEmpty(id)){
				  sql.append("' and id='")
				   .append(id)
				   .append("'");
		       }*/
            // sql.append("  GROUP BY orderid   ORDER BY create_date asc   ")
            sql.append("    ORDER BY RAND()   ")
                    .append(" LIMIT ")
                    .append(start)
                    .append(",")
                    .append(rows);
            List<Object[]> mode_list = systemService.findListbySql(sql.toString());
            for (int i = 0; i < mode_list.size(); i++) {
                Object[] t = mode_list.get(i);
                JSONObject object_mode = new JSONObject();
                object_mode.put("id", t[0]);
                object_mode.put("goods_id", t[1]);
                object_mode.put("goodsname", t[2]);
                object_mode.put("user_id", t[3]);
                object_mode.put("realname", t[4]);
                object_mode.put("usernameurl", t[5]);
                object_mode.put("goodsDetaislType", t[7]);
                object_mode.put("merchantId", t[8]);
                object_mode.put("merchantname", t[9]);
                object_mode.put("orderid", t[23]);
                object_mode.put("create_date", t[25]);
                object_mode.put("end_time", t[26]);
                object_mode.put("ptnumbs", t[27]);
                arry.add(object_mode);
            }
        }
        object.put("details", arry);
        object.put("status", "success");
        object.put("message", "获取信息成功");
        TagUtil.getSendJson(response, object.toString());
    }

    /*订单管理 n_order,*/
    @RequestMapping(params = "getUuid")
    public void getUuid(HttpServletRequest request,
                        HttpServletResponse response) {
        JSONObject object = new JSONObject();
        String id = StringUtil.getId();
        object.put("id", id);
        object.put("status", "success");
        object.put("message", "获取信息成功");
        TagUtil.getSendJson(response, object.toString());
    }

    /*订单管理 n_order,删除订单,ptPerson拼团人数*/
    @RequestMapping(params = "n_order_delete")
    public void n_order_delete(HttpServletRequest request,
                               HttpServletResponse response, String id) {
        JSONObject object = new JSONObject();
        //String id=StringUtil.getId();
        if (!StringUtil.is_khEmpty(id)) {
            systemService.deleteEntityById(NOrderEntity.class, id);
        }
        object.put("status", "success");
        object.put("message", "获取信息成功");
        TagUtil.getSendJson(response, object.toString());
    }

    /*订单管理 n_order,删除订单,ptPerson拼团人数*/
    @RequestMapping(params = "n_order_delete_userid")
    public void n_order_delete_userid(HttpServletRequest request, HttpServletResponse response, String userId) {
        JSONObject object = new JSONObject();
        //String id=StringUtil.getId();
        if (!StringUtil.is_khEmpty(userId)) {
            StringBuffer sql = new StringBuffer();
            sql.append("delete from n_order where  order_status='0' and paystatus='1'  and user_id='")
                    .append(userId)
                    .append("'");
            systemService.updateBySqlString(sql.toString());
        }
        object.put("status", "success");
        object.put("message", "获取信息成功");
        TagUtil.getSendJson(response, object.toString());
    }

    /*订单管理 n_order 根据订单id，获取订单详情*/
    @RequestMapping(params = "order_info_details")
    public void order_info_details(HttpServletRequest request,HttpServletResponse response,String norderid, String couponid) {

        JSONObject object = new JSONObject();
        NOrderEntity t = systemService.get(NOrderEntity.class, norderid);
        if (t != null) {
            object.put("id", t.getId());
            object.put("goods_id", t.getGoodsId());
            object.put("goodsname", t.getGoodsname());
            object.put("user_id", t.getUserId());
            object.put("realname", t.getRealname());
            object.put("usernameurl", t.getUsernameurl());
            object.put("marketingOne", t.getMarketingOne());
            object.put("goodsDetaislType", t.getGoodsDetaislType());
            object.put("merchantId", t.getMerchantId());
            object.put("merchantname", t.getMerchantname());
					/*object.put("orderStatus", t.getOrderStatus());
					object.put("orderType", t.getOrderType());*/
					/*object.put("aftersaleStatus", t[12]);
					object.put("aftersaleType", t[13]);*/
            object.put("goodsSum", t.getGoodsSum());
            object.put("numbers", t.getNumbers());
            object.put("standardid", t.getStandardid());
            object.put("standardname", t.getStandardname());
            object.put("picurl", t.getPicurl());
            object.put("couponid", couponid);
            object.put("addressid", t.getAddressid());
            object.put("phone", t.getPhone());
            object.put("acceptname", t.getAcceptname());
            object.put("area", t.getArea());
            object.put("areacode", t.getAreacode());
            object.put("address", t.getAddress());
					/*String addressid=t.getAddressid();
					String addressid1=t.getAddress();*/
        }
        object.put("status", "success");
        object.put("message", "获取信息成功");
        TagUtil.getSendJson(response, object.toString());
    }

    /*任务团参团或者开团使用优惠券*/
    @RequestMapping(params = "n_order_couponid")
    public void n_order_couponid(HttpServletRequest request, HttpServletResponse response,
                                 String userId, String orderid, String goodsId, String couponType) {
        /**coupon_type	优惠劵类型		0抵用卷、1开团卷、2参团卷*/
        JSONObject object = new JSONObject();
        StringBuffer sql = new StringBuffer();
        // sql.append("select id,user_id,joinordertype,paystatus ,orderid,create_date,end_time ")
        /**couponstatus 0未使用1已使用,3过期*/
        sql.append("from NUserCouponEntity where couponstatus='0' ");
        if (!StringUtil.is_khEmpty(userId)) {
            sql.append(" and userId='")
                    .append(userId)
                    .append("'");
        }
        if (!StringUtil.is_khEmpty(orderid)) {
            sql.append(" and ptorderid='")
                    .append(orderid)
                    .append("'");
        }
        if (!StringUtil.is_khEmpty(goodsId)) {
            sql.append(" and goodsId='")
                    .append(goodsId)
                    .append("'");
        }
        if (!StringUtil.is_khEmpty(couponType)) {
            sql.append(" and couponType='")
                    .append(couponType)
                    .append("'");
        }
        sql.append(" order by  createDate desc");
        List<NUserCouponEntity> nOrder_list = systemService.findHql(sql.toString());
        String status = "success";
        if (nOrder_list != null && nOrder_list.size() > 0) {
            NUserCouponEntity mode = nOrder_list.get(0);
            if (mode != null) {
                object.put("norderid", mode.getNorderid());
                object.put("couponid", mode.getId());
                object.put("couponType", mode.getCouponType());
            } else {
                status = "fail";
            }

        } else {
            status = "fail";
        }
        object.put("status", status);
        object.put("message", "获取信息成功");
        TagUtil.getSendJson(response, object.toString());
    }
    /*订单管理 n_order,新增订单,ptPerson拼团人数*/
/*	@RequestMapping(params = "n_order_save")
	public void n_order_save(HttpServletRequest request,
			HttpServletResponse response,NOrderEntity mode,String id,
			String userId,String realname,String usernameurl,String goodsDetaislType,
			String merchantId,String merchantname,
			String goodsId,String goodsname,
			String goodsSum,String numbers,
			String paySum,String joinordertype,
			 String orderid,String addressid,
			String payorderstatus,String marketingOne,
			String couponid,String userprice,String paystatus,
			String ptPerson,String standardid,String standardname,String paymode,String isvirtual) {
		    String message="已经参加拼团";
		    System.out.println("mode=="+mode.toString());
		    //paymode 0微信、1支付宝、2无支付、3公众号支付
	        Date today = new Date();
	        Calendar c = Calendar.getInstance();
	        c.setTime(today);
	        c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
	        Date tomorrow = c.getTime();
	    	JSONObject object = new JSONObject();
	    	mode.setApplytime(today);
	    	mode.setId(id);
	    	mode.setOrderType("0");//order_type	订单类型0正常 1售后处理中
			mode.setGatheringstatus("0");//gatheringstatus	商家收款状态	0未收款 1冻结中2已收款
			//paystatus 0 付款成功 1付款失败
			if("0".equals(paystatus)){
				mode.setOrderStatus("1");   //0待付款1待拼单2待发货3已发货4已签收5待评价6售后处理中
			   System.out.println("paystatus=getOrderStatus="+mode.getOrderStatus());
		     }else{
		    	mode.setOrderStatus("0");   //0待付款1待拼单2待发货3已发货4已签收5待评价6售后处理中
		     }
			mode.setJoinorderstatus("0");//	拼单状态0待完成，1已完成、2订单取消
			String isjoin="0";  //0可以参团 1不能参团
			//goods_detaisl_type	商品类型1任务，2普通/     任务团没有时间限制，普通团24小时
			String couponType="2";//couponType 0抵用卷、1开团卷、2参团卷
				//joinordertype	拼单类型 0开团、1拼团，
			if("0".equals(joinordertype)){
				mode.setCreateDate(today);
				mode.setEndTime(tomorrow);
				mode.setOrderid(id);//拼单id,同一拼单的唯一关联
				//couponType 0抵用卷、1开团卷、2参团卷
				if("0".equals(paystatus))
					couponType="2";
			}else{
			    orderid=mode.getOrderid();
			    mode.setOrderid(orderid);//拼单id,同一拼单的唯一关联
			    //isjoin  0可以参团 1不能参团
			    StringBuffer sql=new StringBuffer();
				       sql.append("from NOrderEntity where paystatus='0' and orderid='")
				       .append(orderid)
				       .append("'")
				       .append(" order by  joinordertype asc");
				       //joinordertype	拼单类型0开团、1拼团，3单买
				 List<NOrderEntity> nOrder_list= systemService.findHql(sql.toString());
			    if(nOrder_list.size()>=Integer.parseInt(ptPerson)){
			    	 isjoin="1";
			    	 message="拼团已满，请另行参团";
			    }
			  //paystatus 0 付款成功 1付款失败
				if("0".equals(paystatus)){
					 isjoin="0";
					 couponType="1";
				}
			    for(int i=0;i<nOrder_list.size();i++){
			    	NOrderEntity t=nOrder_list.get(i);
			    	//joinordertype	拼单类型 0开团、1拼团，
			    	String p_joinordertype=t.getJoinordertype();
			    	String userId_old=t.getUserId();
			    	if(!StringUtil.is_khEmpty(userId_old)){
			    	  if(userId_old.equals(userId)){
			    		  isjoin="1";
			    	  }
			    	}
		    		if("0".equals(p_joinordertype)){
		    			  mode.setCreateDate(t.getCreateDate());
						  mode.setEndTime(t.getEndTime());
		    		}
				  }

			}
			if(!StringUtil.is_khEmpty(couponid)){
				  mode.setPtstatus("1");
			}else{
				mode.setPtstatus("0");//ptstatus	拼团状态 0未完成1已完成（主要用于任务团）
			}
			 System.out.println("1id="+id);
	     if(!StringUtil.is_khEmpty(id)){
	    	 try {
	    		 NOrderEntity t = systemService.get(NOrderEntity.class, id);
	    		 System.out.println("id="+id);
	    		 if(t!=null){
	    			  //0 付款成功 1付款失败
			    	String old_paystatus=t.getPaystatus();
			    	 System.out.println("told_paystatus="+old_paystatus);
			    	if("0".equals(old_paystatus)){
			    		  isjoin="1";
	    		    }else{
	    		    System.out.println("t1=getOrderStatus="+t.getOrderStatus());
	    		    System.out.println("mode1=getOrderStatus="+mode.getOrderStatus());
	    		    System.out.println("pt1=getOrderStatus="+t.getPaystatus());
	    		    System.out.println("pmode1=getOrderStatus="+mode.getPaystatus());
				     MyBeanUtils.copyBeanNotNull2Bean(mode, t);
				       //  isjoin="0";
				     System.out.println("t2=getOrderStatus="+t.getOrderStatus());
		    		 System.out.println("mode2=getOrderStatus="+mode.getOrderStatus());
		    		 System.out.println("p2=getOrderStatus="+t.getPaystatus());
		    		 System.out.println("pmode2=getOrderStatus="+mode.getPaystatus());
				      systemService.saveOrUpdate(t);
	    		    }
	    		 }else{
	    			if("0".equals(isjoin))
	    			systemService.save(mode);
	    		 }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	     }

	 	final String t_standardid=standardid;
	 	final String t_standardname=standardname;
	 	final String t_orderid=mode.getOrderid();
	 	final String t_ptPerson=ptPerson;
	 	final String t_goodsDetaislType=goodsDetaislType;
	 	final String t_marketingOne=marketingOne;
	 	final String t_paySum=paySum;
	 	final String t_paystatus=paystatus;
	 	final String t_userId=userId;
	 	final String t_userprice=userprice;
	 	final String t_couponid=couponid;
	 	final String t_id=id;
	 	final Date t_today=today;
	 	final Date t_tomorrow=tomorrow;
	 	final Date createdate=mode.getCreateDate();
		final Date endtime=mode.getEndTime();
	 	final String t_realname=realname;
	 	final String t_goodsId=goodsId;
	 	final String t_merchantId=merchantId;
	 	final String t_goodsname=goodsname;
	 	final String t_couponType=couponType;
	 	final String t_isjoin=isjoin;
	 	final String t_joinordertype=joinordertype;
	 	final String t_goods_sum=goodsSum;
	     Thread t1 = new Thread("t1"){
	            public void run(){
	            	if("0".equals(t_isjoin)){
		            	if("0".equals(t_paystatus)){

		            	//	1任务，2普通
		            		if(!"1".equals(t_goodsDetaislType)){
		            			  Format f = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		            			  if("1".equals(t_joinordertype)){
		            				//参团成功通知
		            		     weixinservice.sendTemplate_two(t_userId, "恭喜您参团成功，请等待成团。",
		            				t_goodsname+"团购", t_goodsname,t_goods_sum, t_ptPerson,"拼团时间："
		            		      +f.format(createdate)+"-"+f.format(endtime));
		            			  }else  if("0".equals(t_joinordertype)){
		            				//开团成功通知
		            				  weixinservice.sendTemplate_three(t_userId, "您好，已成功发起一个拼团",
		            						  t_goodsname, t_goods_sum, t_ptPerson,
		            						  "24小时", "	查看订单详情");
		            			  }
		            		}
			            	*//***减去余额***//*
							getUpdatePrice(t_userId, t_userprice);
							*//***优惠券***//*
							if(!StringUtil.is_khEmpty(t_couponid)){
								getUpdatecouponid(t_couponid);

							}else{
								if("1".equals(t_goodsDetaislType)){
									*//**发卷*//*
									getNUserCoupon(t_id,t_orderid,t_today,t_tomorrow,t_userId,t_realname,
											t_goodsId,t_merchantId,t_goodsname,t_couponType);
				    			}
							}
		            	}
	            	}

	            	// joinordertype 0开团、1拼团，3单买
	                if("1".equals(t_joinordertype)){
	            	 getUpdateOrder(t_orderid, t_ptPerson, t_goodsDetaislType,t_marketingOne,t_paySum,t_standardid);
	                //用于任务团
	        	     getUpdateptstatus(t_orderid, t_ptPerson,t_goodsDetaislType,t_marketingOne,t_paySum,t_goodsId,t_standardid,t_standardname,t_couponid );
	                }
	            }
	        };
	        t1.start();
	    	if("0".equals(isjoin)){
	           object.put("status", "success");
	   		   object.put("message", "获取信息成功");
	          }else{
	        	  object.put("status", "fail");
		   		  object.put("message", message);
	          }
	        System.out.println("23424===");

		TagUtil.getSendJson(response, object.toString());
    }
	*/
    /*订单管理 n_order,新增订单,ptPerson拼团人数*/
	/*@RequestMapping(params = "postsaveorder")
	public void postsaveorder(HttpServletRequest request,HttpServletResponse response,NOrderEntity mode,String id,String userId,String realname,String usernameurl,String goodsDetaislType,
							  String merchantId,String merchantname,String goodsId,String goodsname,String goodsSum,String numbers,
							  String paySum,String joinordertype,
							  String orderid,String addressid,
							  String payorderstatus,String marketingOne,
							  String couponid,String userprice,String paystatus,
							  String ptPerson,String standardid,String standardname,String paymode,String isvirtual) {
		String message="已经参加拼团";
		System.out.println("mode=="+mode.toString());
		//paymode 0微信、1支付宝、2无支付、3公众号支付
		Date today = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(today);
		c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
		Date tomorrow = c.getTime();
		JSONObject object = new JSONObject();
		mode.setApplytime(today);
		mode.setId(id);
		mode.setOrderType("0");//order_type	订单类型0正常 1售后处理中
		mode.setGatheringstatus("0");//gatheringstatus	商家收款状态	0未收款 1冻结中2已收款
		//paystatus 0 付款成功 1付款失败
		if("0".equals(paystatus)){
			mode.setOrderStatus("1");   //0待付款1待拼单2待发货3已发货4已签收5待评价6售后处理中
			System.out.println("paystatus=getOrderStatus="+mode.getOrderStatus());
		}else{
			mode.setOrderStatus("0");   //0待付款1待拼单2待发货3已发货4已签收5待评价6售后处理中
		}
		mode.setJoinorderstatus("0");//	拼单状态0待完成，1已完成、2订单取消
		String isjoin="0";  //0可以参团 1不能参团
		//goods_detaisl_type	商品类型1任务，2普通/     任务团没有时间限制，普通团24小时
		String couponType="2";//couponType 0抵用卷、1开团卷、2参团卷
		//joinordertype	拼单类型 0开团、1拼团，
		if("0".equals(joinordertype)){
			mode.setCreateDate(today);
			mode.setEndTime(tomorrow);
			mode.setOrderid(id);//拼单id,同一拼单的唯一关联
			//couponType 0抵用卷、1开团卷、2参团卷
			if("0".equals(paystatus))
				couponType="2";
		}else{
			orderid=mode.getOrderid();
			mode.setOrderid(orderid);//拼单id,同一拼单的唯一关联
			//isjoin  0可以参团 1不能参团
			StringBuffer sql=new StringBuffer();
			sql.append("from NOrderEntity where paystatus='0' and orderid='")
					.append(orderid)
					.append("'")
					.append(" order by  joinordertype asc");
			//joinordertype	拼单类型0开团、1拼团，3单买
			List<NOrderEntity> nOrder_list= systemService.findHql(sql.toString());
			if(nOrder_list.size()>=Integer.parseInt(ptPerson)){
				isjoin="1";
				message="拼团已满，请另行参团";
			}
			//paystatus 0 付款成功 1付款失败
			if("0".equals(paystatus)){
				isjoin="0";
				couponType="1";
			}
			for(int i=0;i<nOrder_list.size();i++){
				NOrderEntity t=nOrder_list.get(i);
				//joinordertype	拼单类型 0开团、1拼团，
				String p_joinordertype=t.getJoinordertype();
				String userId_old=t.getUserId();
				if(!StringUtil.is_khEmpty(userId_old)){
					if(userId_old.equals(userId)){
						isjoin="1";
					}
				}
				if("0".equals(p_joinordertype)){
					mode.setCreateDate(t.getCreateDate());
					mode.setEndTime(t.getEndTime());
				}
			}

		}
		if(!StringUtil.is_khEmpty(couponid)){
			mode.setPtstatus("1");
		}else{
			mode.setPtstatus("0");//ptstatus	拼团状态 0未完成1已完成（主要用于任务团）
		}
		System.out.println("1id="+id);
		if(!StringUtil.is_khEmpty(id)){
			try {
				NOrderEntity t = systemService.get(NOrderEntity.class, id);
				System.out.println("id="+id);
				if(t!=null){
					//0 付款成功 1付款失败
					String old_paystatus=t.getPaystatus();
					System.out.println("told_paystatus="+old_paystatus);
					if("0".equals(old_paystatus)){
						isjoin="1";
					}else{
						System.out.println("t1=getOrderStatus="+t.getOrderStatus());
						System.out.println("mode1=getOrderStatus="+mode.getOrderStatus());
						System.out.println("pt1=getOrderStatus="+t.getPaystatus());
						System.out.println("pmode1=getOrderStatus="+mode.getPaystatus());
						MyBeanUtils.copyBeanNotNull2Bean(mode, t);
						//  isjoin="0";
						System.out.println("t2=getOrderStatus="+t.getOrderStatus());
						System.out.println("mode2=getOrderStatus="+mode.getOrderStatus());
						System.out.println("p2=getOrderStatus="+t.getPaystatus());
						System.out.println("pmode2=getOrderStatus="+mode.getPaystatus());
						systemService.saveOrUpdate(t);
					}
				}else{
					if("0".equals(isjoin))
						systemService.save(mode);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		final String t_standardid=standardid;
		final String t_standardname=standardname;
		final String t_orderid=mode.getOrderid();
		final String t_ptPerson=ptPerson;
		final String t_goodsDetaislType=goodsDetaislType;
		final String t_marketingOne=marketingOne;
		final String t_paySum=paySum;
		final String t_paystatus=paystatus;
		final String t_userId=userId;
		final String t_userprice=userprice;
		final String t_couponid=couponid;
		final String t_id=id;
		final Date t_today=today;
		final Date t_tomorrow=tomorrow;
		final Date createdate=mode.getCreateDate();
		final Date endtime=mode.getEndTime();
		final String t_realname=realname;
		final String t_goodsId=goodsId;
		final String t_merchantId=merchantId;
		final String t_goodsname=goodsname;
		final String t_couponType=couponType;
		final String t_isjoin=isjoin;
		final String t_joinordertype=joinordertype;
		final String t_goods_sum=goodsSum;
		Thread t1 = new Thread("t1"){
			public void run(){
				if("0".equals(t_isjoin)){
					if("0".equals(t_paystatus)){

						//	1任务，2普通
						if(!"1".equals(t_goodsDetaislType)){
							Format f = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
							if("1".equals(t_joinordertype)){
								//参团成功通知
								weixinservice.sendTemplate_two(t_userId, "恭喜您参团成功，请等待成团。",
										t_goodsname+"团购", t_goodsname,t_goods_sum, t_ptPerson,"拼团时间："
												+f.format(createdate)+"-"+f.format(endtime));
							}else  if("0".equals(t_joinordertype)){
								//开团成功通知
								weixinservice.sendTemplate_three(t_userId, "您好，已成功发起一个拼团",
										t_goodsname, t_goods_sum, t_ptPerson,
										"24小时", "	查看订单详情");
							}
						}
						*//***减去余额***//*
						getUpdatePrice(t_userId, t_userprice);
						*//***优惠券***//*
						if(!StringUtil.is_khEmpty(t_couponid)){
							getUpdatecouponid(t_couponid);

						}else{
							if("1".equals(t_goodsDetaislType)){
								*//**发卷*//*
								getNUserCoupon(t_id,t_orderid,t_today,t_tomorrow,t_userId,t_realname,
										t_goodsId,t_merchantId,t_goodsname,t_couponType);
							}
						}
					}
				}

				// joinordertype 0开团、1拼团，3单买
				if("1".equals(t_joinordertype)){
					getUpdateOrder(t_orderid, t_ptPerson, t_goodsDetaislType,t_marketingOne,t_paySum,t_standardid);
					//用于任务团
					getUpdateptstatus(t_orderid, t_ptPerson,t_goodsDetaislType,t_marketingOne,t_paySum,t_goodsId,t_standardid,t_standardname,t_couponid );
				}
			}
		};
		t1.start();
		if("0".equals(isjoin)){
			object.put("status", "success");
			object.put("message", "获取信息成功");
		}else{
			object.put("status", "fail");
			object.put("message", message);
		}
		System.out.println("23424===");

		TagUtil.getSendJson(response, object.toString());
	}*/

    /**
     * @Author shishanshan
     * @Desprition sss 复制上面方法  订单保存方法    修改业务逻辑  变为普通团
     * @Date 2018/9/3 9:32
     * @Param mode : 订单实体类
     * id	:
     * userId :用户id
     * realname : 用户微信名称
     * usernameurl :  用户微信头像链接
     * goodsDetaislType :  商品类型 1.助力团 2.任务团
     * merchantId : 商家id
     * merchantname : 商家名称
     * goodsId : 商品id
     * goodsname : 商品名称
     * goodsSum : 商品总价
     * numbers : 数量
     * paySum : 支付总价
     * joinordertype : 拼单类型;  0 开团 1.拼团 3 单买
     * orderid : 拼单ID
     * addressid : 邮寄地址id
     * payorderstatus : 支付类型  0微信、1支付宝、2优惠券，3、优惠券结合使用   4余额结合
     * marketingOne :  -- 暂无使用
     * couponid :  优惠券id
     * userprice : 个人账户金额
     * paystatus :  付款状态 0 付款成功 1付款失败,
     * ptPerson :  拼团人数
     * standardid :  规格id
     * standardname : 规格名称
     * paymode : 支付方式  0微信、1支付宝、2无支付、3公众号支付、4支付宝wap支付
     * isvirtual : 是否虚拟  0是，1否
     * @Return
     */
    @RequestMapping(params = "postsaveorder", method = RequestMethod.POST)
    public void postsaveorder(HttpServletRequest request, HttpServletResponse response, NOrderEntity mode, String id, String userId, String realname, String goodsDetaislType,
                              String merchantId, String goodsId, String goodsname, String goodsSum,
                              String paySum, String joinordertype, String marketingOne, String userprice, String paystatus,
                              String ptPerson, String standardid, String standardname,String orderid) {
        String message = "已经参加拼团";
        JSONObject object = new JSONObject();
        boolean cerateOrderFlag = true;
        String isjoin = "0";
        try {
            LogUtil.info(mode.toString());
            NGoodsDetaislEntity nGoodsDetaislEntity = systemService.getEntity(NGoodsDetaislEntity.class, goodsId);
            String isMemberGoods = nGoodsDetaislEntity.getIsMember();
            String oneLevel = nGoodsDetaislEntity.getOneLevel();//一级奖励
            String twoLevel = nGoodsDetaislEntity.getTwoLevel();//二级奖励
            String threeLevel = nGoodsDetaislEntity.getThreeLevel();//三级奖励
            //1.从新获取金额
            mode.setPtPerson(nGoodsDetaislEntity.getGroupPurchase());
            mode.setRedResultCode("N");
            //获取用户信息
            NUserEntity nUserEntity = systemService.getEntity(NUserEntity.class, userId);
            String isMember = nUserEntity.getIsMember();//是否会员
            //用户等级绑定
            //**********************  等级绑定开始  *************************//
            if(!"0".equals(isMemberGoods) && "0".equals(joinordertype) && "1".equals(paystatus) && "2".equals(goodsDetaislType)){
                if(StringUtils.isNotBlank(oneLevel) && StringUtils.isNotBlank(twoLevel) && StringUtils.isNotBlank(threeLevel) && ! "0".equals(oneLevel) && !"0".equals(twoLevel) && !"0".equals(threeLevel)){
                    String createBy = mode.getCreateBy();
                    boolean flag =false;
                    if(!StringUtils.isNotBlank(mode.getCreateBy()) ||  "undefined".equals(mode.getCreateBy())){
                        if("Y".equals(isMember)){
                            createBy = nUserEntity.getId();
                            flag  = true;
                        }
                    }
                    if(StringUtils.isNotBlank(createBy) && !"undefined".equals(createBy)){
                        //判断当前用户是否是会员 如果是会员 该用户为三级
                        NUserEntity nUserEntity1 = systemService.getEntity(NUserEntity.class, createBy);
                        if(nUserEntity1 !=null){
                            if(StringUtils.isNotBlank(nUserEntity1.getIsMember()) && "Y".equals(nUserEntity1.getIsMember())){
                                //三级
                                if(flag || userId.equals(createBy)){
                                    mode.setThreeLevelUser("1");
                                }else{
                                    mode.setThreeLevelUser(createBy);
                                }
                                //二级
                                String levelUserId = utilsSss.getLevelUser(nUserEntity1.getId());
                                if(StringUtils.isNotBlank(levelUserId)){
                                    mode.setTwoLevelUser(levelUserId);
                                    //一级
                                    levelUserId = utilsSss.getLevelUser(levelUserId);
                                    if(StringUtils.isNotBlank(levelUserId)){
                                        mode.setOneLevelUser(levelUserId);
                                    }
                                }
                            }else{
                                //三级
                                String levelUser = utilsSss.getLevelUser(nUserEntity1.getId());
                                if(StringUtils.isNotBlank(levelUser)){
                                    mode.setThreeLevelUser(levelUser);
                                    levelUser = utilsSss.getLevelUser(levelUser);
                                    //二级
                                    if(StringUtils.isNotBlank(levelUser)){
                                        mode.setTwoLevelUser(levelUser);
                                        levelUser = utilsSss.getLevelUser(levelUser);
                                        //一级
                                        if(StringUtils.isNotBlank(levelUser)){
                                            mode.setOneLevelUser(levelUser);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            //********************** 等级绑定结束 *************************//
            if(StringUtils.isBlank(isMember)){
                isMember = "N";
            }
            mode.setIsMemberUser(isMember);
            mode.setIsMemberGoods(isMemberGoods);
            //获取返现金额
            String returnMoney = nGoodsDetaislEntity.getReturnMoney();
            String isOnlyOne = nGoodsDetaislEntity.getIsOnlyOne();
            String referralBonus = nGoodsDetaislEntity.getReferralBonus();//推荐奖励
            String isReferral = mode.getIsReferral();//用户是否选择推荐奖励 // Y是 N否  1.是:拿佣金(0元开元) 2.否:拿物品 会员价开团
            //**********************  金额校验开始  *************************//
            NStandardDetailsEntity standardDetailsEntity = systemService.getEntity(NStandardDetailsEntity.class, mode.getStandardid());
            String tuanPrice = standardDetailsEntity.getTuanPrice();//普通人价格
            String memberPrice = standardDetailsEntity.getMemberPrice();//团购价格
            //1.
            if(!"0".equals(isMemberGoods) && "1".equals(paystatus) && "2".equals(goodsDetaislType)){
                //开团
                if("0".equals(joinordertype)){
                    if(StringUtils.isNotBlank(isMember) && "Y".equals(isMember)){//会员
                    }else{//非会员
                        mode.setPaySum(tuanPrice);
                    }
                }else{//参团
                    if(StringUtils.isNotBlank(isMember) && "Y".equals(isMember)){//会员
                        mode.setPaySum(memberPrice);
                    }else{//非会员
                        mode.setPaySum(tuanPrice);
                    }
                }
            }
            //**********************  金额校验结束  *************************//
            //助力团 固定商品只能开团一次判断
            if(StringUtils.isNotBlank(isOnlyOne) && "0".equals(isOnlyOne) && "1".equals(joinordertype)  && "1".equals(paystatus)){
                String sql2 = "select t1.id from n_order t1 LEFT JOIN n_goods_detaisl t2 on t1.goods_id = t2.id where t1.user_id = '"+userId+"' and (t1.joinorderstatus = '0' or t1.joinorderstatus= '1')  and t1.paystatus = '0' and t1.joinordertype = '1' and t2.is_only_one = '0'";//sss超级皮卡丘 判断参团次数 会员普通团商品只能参加一次
                List<Object> listbySql = systemService.findListbySql(sql2);
                String checkMemberJoin = "select t1.id  from n_goods_join_record t1 LEFT JOIN n_goods_detaisl t2 on t1.goods_id = t2.id where t1.record_type = '2' and t1.user_id = '"+userId+"'  and t2.is_only_one = '0'";
                List<Object> listbySql1 = systemService.findListbySql(checkMemberJoin);
                if((listbySql != null && listbySql.size() > 0) || (listbySql1!=null && listbySql.size()>0)){
                    isjoin = "1";
                    message = "您已经参过一次团了，再去首页挑件跟小伙伴成团吧！";
                }else{
                    cerateOrderFlag = true;
                }
            }else {
                cerateOrderFlag = true;
            }
            boolean checkMember = true;
            //判断0元商品类型
            if("1".equals(paystatus) && "2".equals(goodsDetaislType) && "0".equals(paySum)){
                //只有用户是会员并且选择推荐才能0元拿商品
                if(StringUtils.isNotBlank(isMember) && "Y".equals(isMember) && "Y".equals(isReferral)){
                }else{
                    checkMember = false;
                }
            }
            if (cerateOrderFlag && checkMember) {
                System.out.println("拼团参团参数列表:mode==" + mode.toString());
                //paymode 0微信、1支付宝、2无支付、3公众号支付
                Date today = new Date();
                Calendar c = Calendar.getInstance();
                c.setTime(today);
                c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
                Date tomorrow = c.getTime();
                mode.setApplytime(today);//下单时间
                mode.setId(id);
                mode.setOrderType("0");//order_type	订单类型0正常 1售后处理中
                mode.setGatheringstatus("0");//gatheringstatus	商家收款状态	0未收款 1冻结中	2已收款
                //paystatus 0 付款成功 1付款失败
                if ("0".equals(paystatus)) {
                    mode.setOrderStatus("1");   //	0待付款	1待拼单	2待发货	3已发货	4已签收	5待评价	6售后处理中
                    if("0".equals(joinordertype) && "Y".equals(isReferral) && StringUtils.isNotBlank(isMember) && "Y".equals(isMember)){
                        mode.setOrderReferralBonus(referralBonus);
                    }
                    System.out.println("拼单付款成功");
                } else {
                    mode.setOrderStatus("0");   //0待付款1待拼单2待发货3已发货4已签收5待评价6售后处理中
                }
                mode.setJoinorderstatus("0");//	拼单状态:	0待完成，1已完成、2订单取消
                isjoin = "0";  //0可以参团 1不能参团
                //goods_detaisl_type	商品类型1助力团，2普通/     任务团没有时间限制，普通团24小时
                //joinordertype	拼单类型 0开团、1拼团，3 单买
                if ("0".equals(joinordertype)) {
                    mode.setCreateDate(today);
                    mode.setEndTime(tomorrow);
                    mode.setOrderid(id);//拼单id,同一拼单的唯一关联
                } else {
                    orderid = mode.getOrderid();
                    //mode.setOrderid(orderid);//拼单id,同一拼单的唯一关联
                    //isjoin  0可以参团 1不能参团
                    StringBuffer sql = new StringBuffer();
                    sql.append("from NOrderEntity where paystatus='0' and orderid='")
                            .append(orderid)
                            .append("'")
                            .append(" order by  joinordertype asc");
                    //joinordertype	拼单类型0开团、1拼团，3单买
                    List<NOrderEntity> nOrder_list = systemService.findHql(sql.toString());
                    if (nOrder_list.size() >= Integer.parseInt(ptPerson)) {
                        isjoin = "1";
                        message = "拼团已满，请另行参团";
                    }
                    //paystatus 0 付款成功 1付款失败
                    if ("0".equals(paystatus) && !"0".equals(paySum)) {
                        isjoin = "0";
                    }
                    for (int i = 0; i < nOrder_list.size(); i++) {
                        NOrderEntity t = nOrder_list.get(i);
                        //joinordertype	拼单类型 0开团、1拼团，
                        String p_joinordertype = t.getJoinordertype();
                        String userId_old = t.getUserId();
                        //判断当前用户 是否已参加过团
                        if (StringUtils.isNotBlank(userId_old)) {
                            if (userId_old.equals(userId)) {
                                isjoin = "1";
                            }
                        }
                        //二次开团
                        if ("0".equals(p_joinordertype)) {
                            mode.setCreateDate(t.getCreateDate());
                            mode.setEndTime(t.getEndTime());
                        }
                    }

                }
                mode.setPtstatus("1");//ptstatus	拼团状态 0未完成1已完成（主要用于任务团）
                if (StringUtils.isNotBlank(id)) {
                    try {
                        NOrderEntity t = systemService.get(NOrderEntity.class, id);
                        if (t != null) {
                            //0 付款成功 1付款失败
                            String old_paystatus = t.getPaystatus();
                            System.out.println("told_paystatus=" + old_paystatus);
                            if ("0".equals(old_paystatus)) {
                                isjoin = "0";
                            } else {
                                MyBeanUtils.copyBeanNotNull2Bean(mode, t);
                                systemService.saveOrUpdate(t);
                            }
                        } else {
                            if ("0".equals(isjoin)) {
                                    systemService.save(mode);
                            }
                        }
                    } catch (Exception e) {
                        logger.error(e);
                        e.printStackTrace();
                    }

                }

                final String t_standardid = standardid;
                final String t_standardname = standardname;
                final String t_orderid = mode.getOrderid();
                final String t_ptPerson = ptPerson;
                final String t_goodsDetaislType = goodsDetaislType;
                final String t_marketingOne = marketingOne;
                final String t_paySum = paySum;
                final String t_paystatus = paystatus;
                final String t_userId = userId;
                final String t_userprice = userprice;
                final String t_id = id;
                final Date t_today = today;
                final Date t_tomorrow = tomorrow;
                final Date createdate = mode.getCreateDate();
                final Date endtime = mode.getEndTime();
                final String t_realname = realname;
                final String t_goodsId = goodsId;
                final String t_merchantId = merchantId;
                final String t_goodsname = goodsname;
                final String t_isjoin = isjoin;
                final String t_joinordertype = joinordertype;
                final String t_goods_sum = goodsSum;
                final String isOnlyOneFinal = isOnlyOne;
                final String returnMoneyFinal = returnMoney;
                final String isMemberFinal = isMember;//是否会员
                final String referralBonusFinal = referralBonus;//推荐奖励
                final String oneLevelPriceFinal = oneLevel;//一级奖励
                final String twoLevelPriceFinal = twoLevel;//二级奖励
                final String threeLevelPriceFinal = threeLevel;//三级奖励
                final String isMemberGoodsFinal = isMemberGoods;
                System.out.println("时珊珊=t_isjoin=" + t_isjoin + "-------t_paystatus=" + t_paystatus);
                Thread t1 = new Thread("t1") {
                    public void run() {
                        if ("0".equals(t_isjoin)) {
                            if ("0".equals(t_paystatus)) {
                                //	1任务，2普通
                                if (!"1".equals(t_goodsDetaislType)) {
                                    Format f = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                                    if ("1".equals(t_joinordertype)) {
                                        //参团成功通知
                                        weixinservice.sendTemplate_two(t_userId, "恭喜您参团成功，请等待成团。",t_goodsname + "团购", t_goodsname, t_goods_sum, t_ptPerson, "拼团时间："+ f.format(createdate) + "-" + f.format(endtime));
                                        //sss 参团给团长奖励 奖励为冻结金额金额冻结
                                        if(StringUtils.isNotBlank(isOnlyOneFinal) && "0".equals(isOnlyOneFinal) && StringUtils.isNotBlank(returnMoneyFinal) && !"0".equals(returnMoneyFinal)){
                                            giveRewardToFirst(t_orderid,returnMoneyFinal);
                                        }
                                        //普通人参团有奖励 条件:1.非会员 2.该物品有返现-用户选择返现
                                        if( !"0".equals(isMemberGoodsFinal) && "N".equals(isMemberFinal) && StringUtils.isNotBlank(oneLevelPriceFinal) && !"0".equals(oneLevelPriceFinal)
                                                && StringUtils.isNotBlank(twoLevelPriceFinal) && !"0".equals(twoLevelPriceFinal) && StringUtils.isNotBlank(threeLevelPriceFinal) && !"0".equals(threeLevelPriceFinal)){
                                            //给予层级奖励
                                            utilsSss.giveLevelPrice(t_orderid,oneLevelPriceFinal,twoLevelPriceFinal,threeLevelPriceFinal);
                                        }
                                    } else if ("0".equals(t_joinordertype)) {
                                        //开团成功通知
                                        weixinservice.sendTemplate_three(t_userId, "您好，已成功发起一个拼团", t_goodsname, t_goods_sum, t_ptPerson, "24小时", "查看订单详情");
                                    }
                                } else {
                                    //开团成功通知
                                    weixinservice.sendTemplate_three(t_userId, "您好，已成功发起一个拼团", t_goodsname, t_goods_sum, t_ptPerson, "24小时", "查看订单详情");
                                }
                                /***减去余额***/
                                // getUpdatePrice(t_userId, t_userprice);
                                /***优惠券***/
                            }
                        }
                        // joinordertype 0开团、1拼团，3单买
                        if ("1".equals(t_joinordertype) && "2".equals(t_goodsDetaislType)) {
                            getUpdateOrder(t_orderid, t_ptPerson, t_goodsDetaislType, t_marketingOne, t_paySum, t_standardid,isOnlyOneFinal,returnMoneyFinal);
                        }
                    }
                };
                t1.start();
            }
            if ("0".equals(isjoin)) {
                object.put("status", "success");
                object.put("message", message);
            } else {
                object.put("status", "fail");
                object.put("message", message);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        TagUtil.getSendJson(response, object.toString());
    }
    /**
      * @Author shishanshan
      * @Desprition 给团长增加邀请奖励-冻结金额
      * @Date 2018/10/20 14:07
      * @Param
      * @Return
      */
    public void giveRewardToFirst(String t_orderid,String returnMoneyFinal) {
        try {
            if (StringUtils.isNotBlank(t_orderid)){
                String sql = "select user_id from n_order  where orderid = '"+t_orderid+"' and joinordertype = '0' and  joinorderstatus = '0' ";
                List<Object> listbySql = systemService.findListbySql(sql);
                if(listbySql!=null && listbySql.size()>0){
                    String  userId = (String) listbySql.get(0);
                    if(StringUtils.isNotBlank(userId)){
                        //1.给团长增加冻结金额
                        NUserEntity nUserEntity = systemService.getEntity(NUserEntity.class, userId);
                        String ext5 = nUserEntity.getExt5();
                        if(!StringUtils.isNotBlank(ext5)){
                            ext5 = "0";
                        }
                        ext5 = CommonUtils.add(returnMoneyFinal,ext5);
                        nUserEntity.setExt5(ext5);
                        systemService.saveOrUpdate(nUserEntity);
                        //2.微信推送
                        weixinservice.sendTemplate_refferrer(userId,"团长奖励",returnMoneyFinal+"元","红包(成团后即可提现)","",DateUtils.formatTimehsm(new Date()),"点击查看钱包详情，如有疑问直接咨询在线客服。");
                    }
                }
            }
        }catch (Exception e){
            logger.error(e);
            e.printStackTrace();
        }
    }
    /**
     * @Author shishanshan
     * @Desprition 给团长增加邀请奖励-解冻金额
     * @Date 2018/10/20 14:07
     * @Param
     * @Return
     */
    public void giveRewardToFirstForUnfreeze(String userId,String ptPerson,String returnMoneyFinal) {
        try {
            if (StringUtils.isNotBlank(userId)){
                NUserEntity nUserEntity = systemService.getEntity(NUserEntity.class, userId);
                if(nUserEntity != null){
                    //1.冻结金额减少
                    String ext5 = nUserEntity.getExt5();//冻结金额
                    if(!StringUtils.isNotBlank(ext5)){
                        ext5 = "0";
                    }
                    String ptPrice = Integer.toString((Integer.parseInt(ptPerson)-1)*Integer.parseInt(returnMoneyFinal));
                    String decre = CommonUtils.decre(ext5,ptPrice);
                    Double aDouble = Double.valueOf(decre);
                    if(aDouble <0){
                        ext5 = "0";
                    }else{
                        ext5 = decre;
                    }
                    //2.钱包金额增加
                    String price = nUserEntity.getPrice();
                    if(!StringUtils.isNotBlank(price)){
                        price = "0";
                    }
                    price = CommonUtils.add(price,ptPrice);
                    //3.总金额增加
                    String ext1 = nUserEntity.getExt1();
                    if(!StringUtils.isNotBlank(ext1)){
                        ext1 = "0";
                    }
                    ext1 = CommonUtils.add(ext1,ptPrice);
                    nUserEntity.setPrice(price);
                    nUserEntity.setExt1(ext1);
                    nUserEntity.setExt5(ext5);
                    systemService.saveOrUpdate(nUserEntity);
                    //4.微信推送成团奖励
                    weixinservice.sendTemplate_refferrer(userId,"团长成团奖励",ptPrice+"元","成团解冻金额","",DateUtils.formatTimehsm(new Date()),"点击查看钱包详情，如有疑问直接咨询在线客服。");

                }            }
        }catch (Exception e){
            logger.error(e);
            e.printStackTrace();
        }
    }
    public void saveNGoodsJoinRecord(String userid,String id,String goodsid,String type){
        if (StringUtils.isNotBlank(userid)){
            NUserEntity nUserEntity = systemService.getEntity(NUserEntity.class, userid);
            if (nUserEntity != null){
                NGoodsJoinRecord nGoodsJoinRecord = new NGoodsJoinRecord();
                nGoodsJoinRecord.setCreateDate(new Date());
                nGoodsJoinRecord.setGoodsId(goodsid);
                nGoodsJoinRecord.setOpenId(nUserEntity.getOpenid());
                nGoodsJoinRecord.setOrderId(id);
                nGoodsJoinRecord.setRealName(nUserEntity.getRealname());
                nGoodsJoinRecord.setRecordType(type);
                nGoodsJoinRecord.setUserId(userid);
                nGoodsJoinRecord.setUserNameUrl(nUserEntity.getUsernameurl());
                systemService.save(nGoodsJoinRecord);
            }
        }
    }
    /**
     * @Author shishanshan
     * @Desprition 助力团- 会员团  -  拼团
     * @Date 2018/9/3 14:13
     * @Param userid
     * orderid
     * goodsid
     * @Return
     */
    @RequestMapping(params = "joinOrderForMember", method = RequestMethod.POST)
    public void joinOrderForMember(HttpServletRequest request, HttpServletResponse response, String userid, String orderid, String goodsid, String mainuserid) {
        String message = "参团成功!";
        String status = "Y";
        JSONObject object = new JSONObject();
        try {
            if (StringUtils.isNotBlank(userid) && StringUtils.isNotBlank(orderid) && StringUtils.isNotBlank(goodsid)) {
                //判断当前人是否可以参团
                NOrderEntity nOrderEntity = systemService.getEntity(NOrderEntity.class, orderid);
                String checkMemberJoin22 = "select id from n_goods_join_record where user_id = '" + userid + "' and  goods_id  = '" + goodsid + "' and record_type =  '2'";
                List<Object> listbySql2 = systemService.findListbySql(checkMemberJoin22);
                //1.判断库存是否为0 为0不能参与助力
                NUserEntity nUserEntity = systemService.getEntity(NUserEntity.class, userid);
                NGoodsDetaislEntity nGoodsDetaislEntity = systemService.getEntity(NGoodsDetaislEntity.class, goodsid);
                String isOnlyOne = nGoodsDetaislEntity.getIsOnlyOne();
                String isMember = nGoodsDetaislEntity.getIsMember();
                NStandardDetailsEntity nStandardDetailsEntity1 = systemService.getEntity(NStandardDetailsEntity.class, nOrderEntity.getStandardid());
                boolean sumFlag = false;
                boolean joinFlag = true;
                if (nStandardDetailsEntity1 != null) {
                    double v = Double.parseDouble(nStandardDetailsEntity1.getSumNumber());
                    if (v <= 0) {
                        sumFlag = true;
                    }
                }
                //listbySql1 != null && listbySql1.size() > 0
                if (StringUtils.isNotBlank(isMember) && "0" .equals(isMember)) {
                    String checkMemberJoin = "select t1.id  from n_goods_join_record t1 LEFT JOIN n_goods_detaisl t2 on t1.goods_id = t2.id where t1.record_type = '2' and t1.user_id = '"+userid+"'  and t2.is_member = '0'";
                    List<Object> listbySql1 = systemService.findListbySql(checkMemberJoin);
                    if(listbySql1 != null && listbySql1.size() > 0){
                        joinFlag =false;
                        message = "此类商品，您已经帮助过好友了，无法再次助力！";
                        status = "N";
                    }
                }else if(StringUtils.isNotBlank(isMember) && "2" .equals(isMember)){
                    String checkMemberJoin = "select t1.id  from n_goods_join_record t1 LEFT JOIN n_goods_detaisl t2 on t1.goods_id = t2.id where t1.record_type = '2' and t1.user_id = '"+userid+"'  and t2.is_member = '2'";
                    List<Object> listbySql1 = systemService.findListbySql(checkMemberJoin);
                    if(listbySql1 != null && listbySql1.size() > 0){
                        joinFlag =false;
                        message = "此类商品，您已经帮助过好友了，无法再次助力！";
                        status = "N";
                    }
                }else if(StringUtils.isNotBlank(isOnlyOne) && "0".equals(isOnlyOne)){
                    String checkMemberJoin = "select t1.id  from n_goods_join_record t1 LEFT JOIN n_goods_detaisl t2 on t1.goods_id = t2.id where t1.record_type = '2' and t1.user_id = '"+userid+"'  and t2.is_only_one = '0'";
                     List<Object>  listbySql1 = systemService.findListbySql(checkMemberJoin);
                     String sql222 = "select t1.id from n_order t1 LEFT JOIN n_goods_detaisl t2 on t1.goods_id = t2.id where t1.user_id = '"+userid+"' and (t1.joinorderstatus = '0' or t1.joinorderstatus= '1') and  t1.paystatus = '0' and t1.joinordertype = '1' and t2.is_only_one = '0'";//sss超级皮卡丘 判断参团次数 会员普通团商品只能参加一次
                     List<Object> listbySql222 = systemService.findListbySql(sql222);
                     if((listbySql1 != null && listbySql1.size() > 0)||(listbySql222!=null && listbySql222.size()>0)){
                         joinFlag =false;
                         message = "您已经参过一次团了，再去首页挑件跟小伙伴成团吧！";
                         status = "N";
                     }
                }else if(listbySql2 != null && listbySql2.size() > 0 && "1".equals(nGoodsDetaislEntity.getIsMember())){
                    joinFlag =false;
                    message = "您已经参过一次团了，再去首页挑件跟小伙伴成团吧！";
                    status = "N";
                } else if (StringUtils.isNotBlank(userid) && StringUtils.isNotBlank(mainuserid) && userid.equals(mainuserid)) {
                    joinFlag =false;
                    message = "您是开团人员不能参与拼团!";
                    status = "N";
                } else if (sumFlag) {
                    joinFlag =false;
                    message = "商品库存不足,此商品不能助力.";
                    status = "N";
                } /*else if(StringUtils.isNotBlank(nUserEntity.getExt3())&&"Y".equals(nUserEntity.getExt3())&& "0".equals(nGoodsDetaislEntity.getIsMember())){
                    message = "您是银牌会员,无参团资格!";
                    status = "N";
                }*/
                if(joinFlag){
                    //1.查看当前拼团人数是否已满
                    String ptPerson = nOrderEntity.getPtPerson();
                    //获取当前记录数
                    String countSql = "select id from n_goods_join_record where order_id = '" + orderid + "'";
                    List<Object> listbySql = systemService.findListbySql(countSql);
                    if (listbySql != null && listbySql.size() >= Integer.parseInt(ptPerson)) {
                        status = "N";
                        message = "拼团已满，请另行参团";
                    } else {
                        //保存参团信息
                        saveNGoodsJoinRecord(userid, orderid, goodsid, "2");
                        System.out.println("sss打印拼团人数=" + Integer.parseInt(ptPerson) + ";已经拼团人数:=" + listbySql.size());
                        int flag = Integer.parseInt(ptPerson) - listbySql.size();
                        //还差当前拼团人员 拼团成功
                        if (flag == 1) {
                            StringBuffer sql2 = new StringBuffer();
                            sql2.append("update n_order t1 ")
                                    .append(" set  t1.pdtime= now() ,t1.joinorderstatus='1',")
                                    .append("t1.gatheringstatus='1',t1.order_status='2' ")
                                    .append(" where t1.id='")
                                    .append(nOrderEntity.getId())
                                    .append("'");
                            systemService.updateBySqlString(sql2.toString());
                            //拼团成功  修改商品库存

                            if (nGoodsDetaislEntity != null) {
                                String sumNumbers = nGoodsDetaislEntity.getSumNumbers();
                                if (sumNumbers != null && !"0".equals(sumNumbers)) {
                                    int i = Integer.parseInt(sumNumbers);
                                    nGoodsDetaislEntity.setSumNumbers(Integer.toString(i - 1));
                                    systemService.saveOrUpdate(nGoodsDetaislEntity);
                                }
                            }
                            NStandardDetailsEntity nStandardDetailsEntity = systemService.getEntity(NStandardDetailsEntity.class, nOrderEntity.getStandardid());
                            if (nStandardDetailsEntity != null) {
                                String sumNumber = nStandardDetailsEntity.getSumNumber();
                                if (sumNumber != null && !"0".equals(sumNumber)) {
                                    int i = Integer.parseInt(sumNumber);
                                    nStandardDetailsEntity.setSumNumber(Integer.toString(i - 1));
                                    systemService.saveOrUpdate(nStandardDetailsEntity);
                                }
                            }
                            //团购成功提醒
                            weixinservice.sendTemplate_seven(mainuserid, "恭喜您团购成功，您的团购订单已完成付款， 商家将即时为您发货", nOrderEntity.getGoodsSum(), nOrderEntity.getGoodsname(), nOrderEntity.getArea() + nOrderEntity.getAddress(), nOrderEntity.getOrderid(), "查看订单详情");
                        } else {
                            Format f = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                            weixinservice.sendTemplate_two(userid, "恭喜您参团成功，请等待成团。", nOrderEntity.getGoodsSum() + "团购", nOrderEntity.getGoodsname(), nOrderEntity.getGoodsSum(), ptPerson, "拼团时间：" + f.format(nOrderEntity.getCreateDate()) + "-" + f.format(nOrderEntity.getEndTime()));
                        }
                    }
                }
                }else{
                    object.put("status", "N");
                    object.put("message", "参数异常");
                }
            } catch(Exception e){
                object.put("status", "N");
                object.put("message", "参团失败");
                logger.error(e);
                e.printStackTrace();
            }
            object.put("message", message);
            object.put("status", status);
            TagUtil.getSendJson(response, object.toString());

        }
        /**修改商品销售数量*/
/*	public void getUpdateSellnumbs(String goodsId){
		NGoodsDetaislEntity	nGoodsDetaisl =
				systemService.getEntity(NGoodsDetaislEntity.class, goodsId);
		String old_sellnumbs=nGoodsDetaisl.getSellnumbs();
		 BigDecimal b1 = new BigDecimal(old_sellnumbs);
		 BigDecimal b2 = new BigDecimal(1);
		String sellnumbs= b1.add(b2).toString();
		StringBuffer updatesql=new StringBuffer();
	    updatesql.append("update n_order set  ")
	    		.append("sellnumbs='")
			    .append(sellnumbs)
			    .append("'")
	    	    .append(" where  id='")
	            .append(goodsId)
			    .append("'");
        systemService.updateBySqlString(updatesql.toString());
	}*/

        /**
         * 修改用户关联订单状态
         */
        public void getUpdateOrder (String orderid, String ptPerson, String goods_detaisl_type, String marketingOne,
                String t_paySum, String standardid,String isOnlyOneFinal,String returnMoneyFinal){
            /**任务团 人数达到拼团人数修改拼团状态*/
            /**查看拼团人数是否完成,0完成，1未完成,ptstatus 0未完成1已完成（主要用于任务团，发放的劵使用）*/
            /**goods_detaisl_type1任务，2普通
             * marketing_one	订单所属活动 0正常1、限时秒杀、2、9.9特卖、3抽奖、4新品推荐、5赚客、6首页
             *gatheringstatus	商家收款状态 0未收款 1冻结中2已收款
             * order_status 0待付款1待拼单，2待发货3已发货4已签收5待评价6售后处理中
             * joinorderstatus	拼单状态 0待完成，1已完成、2订单取消
             * ptstatus	拼团状态	0未完成1已完成（主要用于任务团，发放的劵使用）*/

            /**order_status 0待付款1待拼单，2待发货3已发货4已签收5待评价6售后处理中*/
            /**marketing_one	订单所属活动 0正常1、限时秒杀、2、9.9特卖、3抽奖、4新品推荐、5赚客、6首页
             * joinorderstatus	拼单状态 0待完成，1已完成、2订单取消*/
            /**普通团 人数达到拼团人数修改拼团状态 抽奖  随机抽奖的效果放到定时器了*/


            /**普通团 人数达到拼团人数修改拼团状态 其他活动*/
            StringBuffer sql2_f = new StringBuffer();
            sql2_f.append("select t1.id,t1.orderid,t1.marketing_one ,")
                    .append(" t1.standardid,t1.numbers ,t1.user_id,t1.goods_sum,t1.goodsname,t1.area ,t1.address,t1.joinordertype")
                    .append(" from  n_order t1 ,")
                    .append("(select *,COUNT(*)as pt_numbs from n_order where ")
                    .append("  paystatus='0' and goods_detaisl_type='2' ");
            //	+ "and marketing_one in('0','1','2','4','6') ");
            if (StringUtils.isNotBlank(orderid)) {
                sql2_f.append(" and orderid='")
                        .append(orderid)
                        .append("'");
            }
            sql2_f.append("   GROUP BY orderid  )t2 ")
                    .append(" where t1.orderid=t2.orderid and t1.pt_person<=t2.pt_numbs ");
            if (!StringUtil.is_khEmpty(orderid)) {
                sql2_f.append(" and t1.orderid='")
                        .append(orderid)
                        .append("'");
            }
            sql2_f.append(" and t1.paystatus='0' and t1.goods_detaisl_type='2' ")
                    .append(" and  t1.joinorderstatus='0'");
            //	   joinorderstatus	拼单状态	c	36		0待完成，1已完成、2订单取消
            // 		+ " and t1.marketing_one in('0','1','2','4','6') ");
            List<Object[]> mode_list = systemService.findListbySql(sql2_f.toString());
            for (int i = 0; i < mode_list.size(); i++) {
                Object[] t = mode_list.get(i);
                String id = t[0] + "";
                String user_id = t[5] + "";
                String goods_sum = t[6] + "";
                String goodsname = t[7] + "";
                String area = t[8] + "";
                String address = t[9] + "";
                String hstandardid = t[3] + "";
                String hpsum = t[4] + "";
                String joinordertype = t[10] + "";
                if (StringUtils.isNotBlank(hstandardid) && !"undefined".equals(hstandardid)) {
                    NStandardDetailsEntity nstandarddetails = systemService.getEntity(NStandardDetailsEntity.class, hstandardid);
                    if (nstandarddetails != null) {
                        String sumNumber = nstandarddetails.getSumNumber();
                        if (StringUtils.isNotBlank(sumNumber)) {
                            BigDecimal e1 = new BigDecimal(sumNumber);
                            BigDecimal e2 = new BigDecimal(hpsum);
                            String sm = e1.subtract(e2).setScale(2, BigDecimal.ROUND_HALF_DOWN).toString();
                            nstandarddetails.setSumNumber(sm);
                            systemService.saveOrUpdate(nstandarddetails);
                        }
                    }
                }
                StringBuffer sql2 = new StringBuffer();
                sql2.append("update n_order t1 ")
                        .append(" set  t1.pdtime= now() ,t1.joinorderstatus='1',")
                        .append("t1.gatheringstatus='1',t1.order_status='2' ")
                        .append(" where t1.id='")
                        .append(id)
                        .append("'");
                systemService.updateBySqlString(sql2.toString());
                //团购成功提醒
               /* if("0".equals(joinordertype) && StringUtils.isNotBlank(isOnlyOneFinal) && "0".equals(isOnlyOneFinal) && StringUtils.isNotBlank(returnMoneyFinal) && !"0".equals(returnMoneyFinal)){
                    giveRewardToFirstForUnfreeze(user_id,ptPerson,returnMoneyFinal);
                }*/
                weixinservice.sendTemplate_seven(user_id, "恭喜您团购成功，您的团购订单已完成付款， 商家将即时为您发货",goods_sum, goodsname, area + address, id, "查看订单详情");
            }

        }


        /**
         * 根据拼团状态，修改用户订单信息 ptstatus
         */
        public void getUpdateptstatus (String orderid, String ptPerson,
                String goods_detaisl_type, String marketingOne,
                String paySum, String goodsId, String standardid, String standardname, String couponid){

            /**查看参团人数是否够*/
            /**查看拼团人数是否完成,0完成，1未完成,ptstatus 0未完成1已完成（主要用于任务团，发放的劵使用）*/
            /**     joinordertype 0开团、1拼团，3单买
             * goods_detaisl_type1任务，2普通
             * marketing_one	订单所属活动 0正常1、限时秒杀、2、9.9特卖、3抽奖、4新品推荐、5赚客、6首页
             *gatheringstatus	商家收款状态 0未收款 1冻结中2已收款
             * order_status 0待付款1待拼单，2待发货3已发货4已签收5待评价6售后处理中
             * joinorderstatus	拼单状态 0待完成，1已完成、2订单取消
             * ptstatus	拼团状态	0未完成1已完成（主要用于任务团，发放的劵使用）
             * paystatus	付款状态 	c	32		0 付款成功 1付款失败,
             * */

            StringBuffer sql = new StringBuffer();
            sql.append("select paystatus,COUNT(*)as pt_numbs from n_order where ")
                    .append("  paystatus='0' and goods_detaisl_type='1' ")
                    .append(" and orderid='")
                    .append(orderid)
                    .append("'")
                    .append("   GROUP BY orderid  ");
            List<Object[]> mode_list = systemService.findListbySql(sql.toString());
            System.out.println("参团====" + mode_list.size() + "=ptPerson=" + ptPerson);
            if (mode_list != null && mode_list.size() > 0) {
                int pt_numbs = Integer.parseInt(mode_list.get(0)[1].toString());
                int t_ptPerson = Integer.parseInt(ptPerson);
                if (pt_numbs >= t_ptPerson) {
                    System.out.println(pt_numbs + "==pt_numbs>=t_ptPerson=" + t_ptPerson);
                    StringBuffer sqltwo = new StringBuffer();
                    sqltwo.append("select id,couponid,payorderstatus,goods_detaisl_type  ")
                            .append(" from n_order ")
                            .append(" where  orderid='")
                            .append(orderid)
                            .append("'")
                            .append(" and paystatus='0' and goods_detaisl_type='1' ")
                            .append(" and  joinorderstatus='0' ");
                    List<Object[]> two_list = systemService.findListbySql(sqltwo.toString());

                    //用户拼团成功，改变状态
                    StringBuffer sqlone = new StringBuffer();
                    sqlone.append("update n_order ")
                            .append(" set  pdtime= now() ,joinorderstatus='1'")
                            .append(" where  orderid='")
                            .append(orderid)
                            .append("'")
                            .append(" and paystatus='0' and goods_detaisl_type='1' ")
                            .append(" and  joinorderstatus='0' ");
                    systemService.updateBySqlString(sqlone.toString());

                    for (int i = 0; i < two_list.size(); i++) {
                        Object[] obj = two_list.get(i);
                        String id = obj[0] + "";
                        String two_couponid = obj[1] + "";
                        //不是空，证明用户使用优惠劵参团
                        if (!StringUtil.is_khEmpty(two_couponid)) {
                            //改变拼团状态
                            //查看开团人是否已经拼完，劵的id等于开团人的id
                            StringBuffer hsql = new StringBuffer();
                            hsql.append("select id,orderid,marketing_one ,standardid,numbers from n_order ")
                                    .append(" where id='")
                                    .append(two_couponid)
                                    .append("' and  paystatus='0' and joinorderstatus='1' ")
                                    .append(" and goods_detaisl_type='1' and  ptstatus='1' and  order_status='1' ");
                            List<Object[]> hmode_list = systemService.findListbySql(hsql.toString());
                            if (hmode_list != null && hmode_list.size() > 0) {
                                //开团商品状态改变
                                StringBuffer updatesql = new StringBuffer();
                                updatesql.append("update n_order set order_status='2',gatheringstatus='1' where ")
                                        .append("  paystatus='0' ")
                                        .append("  and id='")
                                        .append(two_couponid)
                                        .append("'");
                                systemService.updateBySqlString(updatesql.toString());
                                //拼团也发货商品状态改变
                                StringBuffer ptupdatesql = new StringBuffer();
                                ptupdatesql.append("update n_order set order_status='2',gatheringstatus='1' where ")
                                        .append("  paystatus='0' ")
                                        .append("  and id='")
                                        .append(id)
                                        .append("'");
                                systemService.updateBySqlString(ptupdatesql.toString());
                                String hstandardid = hmode_list.get(0)[3] + "";
                                String hpsum = hmode_list.get(0)[4] + "";
                                if (!StringUtil.is_khEmpty(hstandardid) && !"undefined".equals(hstandardid)) {
                                    NStandardDetailsEntity nstandarddetails = systemService.getEntity(NStandardDetailsEntity.class, standardid);
                                    if (nstandarddetails != null) {
                                        String sumNumber = nstandarddetails.getSumNumber();
                                        if (!StringUtil.is_khEmpty(sumNumber)) {
                                            BigDecimal e1 = new BigDecimal(sumNumber);
                                            BigDecimal e2 = new BigDecimal(hpsum);
                                            String sm = e1.subtract(e2).subtract(e2).setScale(2, BigDecimal.ROUND_HALF_DOWN).toString();
                                            nstandarddetails.setSumNumber(sm);
                                            systemService.saveOrUpdate(nstandarddetails);
                                        }
                                    }
                                }
                            }
                        } else {
                            //是空，花钱参团

                            StringBuffer hsql = new StringBuffer();
                            hsql.append("select id,orderid,marketing_one ,standardid,numbers from n_order ")
                                    .append(" where couponid='")
                                    .append(id)
                                    .append("' and paystatus='0' and joinorderstatus='1' ")
                                    .append(" and goods_detaisl_type='1'  and  order_status='1' ");
                            List<Object[]> hmode_list = systemService.findListbySql(hsql.toString());
                            if (hmode_list != null && hmode_list.size() > 0) {
                                //开团商品状态改变
                                StringBuffer updatesql = new StringBuffer();
                                updatesql.append("update n_order set order_status='2',gatheringstatus='1' where ")
                                        .append("  paystatus='0' ")
                                        .append("  and couponid='")
                                        .append(id)
                                        .append("'");
                                systemService.updateBySqlString(updatesql.toString());
                                //拼团也发货商品状态改变
                                StringBuffer ptupdatesql = new StringBuffer();
                                ptupdatesql.append("update n_order set order_status='2',gatheringstatus='1' where ")
                                        .append("  paystatus='0' ")
                                        .append("  and id='")
                                        .append(id)
                                        .append("'");
                                systemService.updateBySqlString(ptupdatesql.toString());
                                String hstandardid = hmode_list.get(0)[3] + "";
                                String hpsum = hmode_list.get(0)[4] + "";
                                if (!StringUtil.is_khEmpty(hstandardid) && !"undefined".equals(hstandardid)) {
                                    NStandardDetailsEntity nstandarddetails = systemService.getEntity(NStandardDetailsEntity.class, standardid);
                                    if (nstandarddetails != null) {
                                        String sumNumber = nstandarddetails.getSumNumber();
                                        if (!StringUtil.is_khEmpty(sumNumber)) {
                                            BigDecimal e1 = new BigDecimal(sumNumber);
                                            BigDecimal e2 = new BigDecimal(hpsum);
                                            String sm = e1.subtract(e2).subtract(e2).setScale(2, BigDecimal.ROUND_HALF_DOWN).toString();
                                            nstandarddetails.setSumNumber(sm);
                                            systemService.saveOrUpdate(nstandarddetails);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }


            }

        }


        /**
         * 修改用户余额
         */
        public void getUpdatePrice (String userId, String userprice){
            NUserEntity user = systemService.getEntity(NUserEntity.class, userId);
            if (!"0".equals(userprice) && !StringUtil.is_khEmpty(userprice)) {
                BigDecimal b1 = new BigDecimal(userprice);
                String price = user.getPrice();
                BigDecimal b2 = new BigDecimal(price);
                String new_price = b2.subtract(b1).setScale(2, BigDecimal.ROUND_HALF_DOWN).toString();
                user.setPrice(new_price);
                systemService.saveOrUpdate(user);
            }
        }

        /**
         * 修改用户优惠劵
         */
        public void getUpdatecouponid (String couponid){
            System.out.println("23424===1");
            /**0未使用1已使用,3过期*/
            if (!StringUtil.is_khEmpty(couponid)) {
                StringBuffer updatesql = new StringBuffer();
                updatesql.append("update n_user_coupon set couponstatus='1' where id='")
                        .append(couponid)
                        .append("'");
                systemService.updateBySqlString(updatesql.toString());
                final String t_couponid = couponid;
                Thread t1 = new Thread("t1") {
                    public void run() {
                        getPayupdateptstatus(t_couponid);
                    }
                };
                t1.start();
            }
        }

        /**
         * 根据优惠劵使用情况，把用户拼团的劵使用情况改变0未完成1已完成（主要用于任务团，发放的劵使用）
         */
        public void getPayupdateptstatus (String couponid){
            NUserCouponEntity nusercoupon = systemService.getEntity(NUserCouponEntity.class, couponid);
            if (nusercoupon != null) {
                String norderid = nusercoupon.getNorderid();
                StringBuffer updatesql = new StringBuffer();
                updatesql.append("update n_order set ptstatus='1' where id='")
                        .append(norderid)
                        .append("'");
                systemService.updateBySqlString(updatesql.toString());
            }
        }

        /**
         * 订单作废，退还余额
         */
	/*public void getExitPrice(String userId,String userprice){
		NUserEntity user=systemService.getEntity(NUserEntity.class, userId);
		if(!"0".equals(userprice) && !StringUtil.is_khEmpty(userprice)){
			   BigDecimal b1 = new BigDecimal(userprice);
			   String  price=user.getPrice();
		       BigDecimal b2 = new BigDecimal(price);
		      // String new_price= b2.subtract(b1).toString();
		       String new_price= b1.add(b2).toString();
		       user.setPrice(new_price);
		       systemService.saveOrUpdate(user);
		}
	}*/
        //给用户添加优惠劵
        public void getNUserCoupon (String id, String ptorderid, Date today, Date tomorrow, String userId, String
        realname, String goodsId, String merchantId, String goodsname, String couponType){
            NUserCouponEntity nusercouponentity = new NUserCouponEntity();
            nusercouponentity.setId(id);
            nusercouponentity.setPtorderid(ptorderid);
            nusercouponentity.setNorderid(id);
            nusercouponentity.setCreateDate(today);
            nusercouponentity.setUserId(userId);
            nusercouponentity.setRealname(realname);
            nusercouponentity.setGoodsId(goodsId);
            nusercouponentity.setMerchantId(merchantId);

            nusercouponentity.setStartTime(today);
            nusercouponentity.setEndTime(tomorrow);

            nusercouponentity.setCouponType(couponType);//0抵用卷、1开团卷、2参团卷
            if ("1".equals(couponType)) {
                nusercouponentity.setCouponName("开团卷,仅限参加" + goodsname + "商品");
                nusercouponentity.setDetails("开团卷,仅限参加" + goodsname + "商品,免除开团费");
            } else {
                nusercouponentity.setCouponName("参团劵,仅限参加" + goodsname + "商品");
                nusercouponentity.setDetails("参团劵,仅限参加" + goodsname + "商品,免除参团费");
            }
            nusercouponentity.setMoney("0");
            nusercouponentity.setFullMoney("0");
            nusercouponentity.setGrantType("1");//0商家1平台
            nusercouponentity.setCouponstatus("0");//0未使用1已使用
            systemService.save(nusercouponentity);
        }


        /*订单管理 n_order,售后*/
        @RequestMapping(params = "order_aftersale")
        public void order_aftersale (HttpServletResponse response, String id,String order_status, String order_type,String aftersale_status, String aftersale_type){
            /**order_status	订单状态	0待付款1待拼单，2待发货3已发货4已签收5已评价6售后处理中
             * order_type	订单类型	0正常 1售后处理中
             * aftersale_status	售后状态		0申请退款1退款成功，2驳回退款3平台强制退款4平台拒绝退款5退款失败
             * aftersale_type	售后类型		0仅退款 1退货退款（买家选择）
             * 1已签收   id，order_statu=4
             * 2已评价id，order_statu=5
             * 3售后处理中   id，order_type=1 ，aftersale_status=0，aftersale_type=0
             * */
            JSONObject object = new JSONObject();
            if (StringUtils.isNotBlank(id)) {
                try {
                    Format f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date today = new Date();
                    String tday = f.format(today);
                    StringBuffer sql = new StringBuffer();
                    sql.append("update n_order set update_date='").append(tday).append("'");
                    if (StringUtils.isNotBlank(order_status)) {
                        sql.append(",order_status='").append(order_status).append("'");
                        if ("4".equals(order_status)) {
                            sql.append(",acceptime='").append(tday).append("'").append(" ,gatheringstatus='2' ");
                        }
                    }
                    if (!StringUtil.is_khEmpty(order_type)) {
                        sql.append(",order_type='").append(order_type).append("' ");
                    }
                    if (!StringUtil.is_khEmpty(aftersale_status)) {
                        sql.append(",aftersale_status='")
                                .append(aftersale_status)
                                .append("' ");
                    }
                    if (!StringUtil.is_khEmpty(aftersale_type)) {
                        sql.append(",aftersale_type='").append(aftersale_type).append("' ").append(",aftersaletime='").append(tday).append("'");
                    }
                    sql.append(" where id='").append(id).append("'");
                    LogUtil.info("订单管理sql=" + sql.toString());
                    systemService.updateBySqlString(sql.toString());
                   if("4".equals(order_status)){
                       final  String finalId = id;
                       new Thread(){
                           public void run(){
                               try {
                                   NOrderEntity nOrderEntity = systemService.getEntity(NOrderEntity.class, finalId);
                                   if(nOrderEntity != null ){
                                       String goodsDetaislType = nOrderEntity.getGoodsDetaislType();
                                       String isMemberGoods = nOrderEntity.getIsMemberGoods();
                                       String joinordertype = nOrderEntity.getJoinordertype();
                                       String isMemberUser = nOrderEntity.getIsMemberUser();
                                       if("2".equals(goodsDetaislType) && "1".equals(isMemberGoods) && "1".equals(joinordertype) &&"N".equals(isMemberUser)){
                                           String sql = "select id from n_order where orderid = '"+nOrderEntity.getOrderid()+"' and paystatus = '0' and goods_detaisl_type='2'  and joinordertype = '0'";
                                           List<Object> listbySql = systemService.findListbySql(sql);
                                           if(listbySql != null && listbySql.size()>0){
                                               String newId = (String) listbySql.get(0);
                                               NOrderEntity orderEntity = systemService.getEntity(NOrderEntity.class, newId);
                                               String threeLevelUser = orderEntity.getThreeLevelUser();
                                               if(StringUtils.isNotBlank(threeLevelUser)){
                                                   String twoLevelUser = orderEntity.getTwoLevelUser();
                                                   String oneLevelUser = orderEntity.getOneLevelUser();
                                                   NGoodsDetaislEntity nGoodsDetaislEntity = systemService.getEntity(NGoodsDetaislEntity.class, orderEntity.getGoodsId());
                                                   if(nGoodsDetaislEntity != null){
                                                       String oneLevelPrice = nGoodsDetaislEntity.getOneLevel();
                                                       String twoLevelPrice = nGoodsDetaislEntity.getTwoLevel();
                                                       String threeLevelPrice = nGoodsDetaislEntity.getThreeLevel();
                                                       if(StringUtils.isNotBlank(oneLevelPrice) && StringUtils.isNotBlank(twoLevelPrice) && StringUtils.isNotBlank(threeLevelPrice) && !"0".equals(oneLevelPrice) && !"0".equals(twoLevelPrice) && !"0".equals(threeLevelPrice)){
                                                           if(StringUtils.isNotBlank(threeLevelUser)){
                                                               if(!"1".equals(threeLevelUser)){//三级
                                                                   unFreePriceOfLevel(threeLevelUser,threeLevelPrice);
                                                               }
                                                               if(StringUtils.isNotBlank(twoLevelUser)){
                                                                   unFreePriceOfLevel(twoLevelUser,twoLevelPrice);
                                                                   if(StringUtils.isNotBlank(oneLevelUser)){
                                                                       unFreePriceOfLevel(oneLevelUser,oneLevelPrice);
                                                                   }
                                                               }
                                                           }
                                                       }
                                                   }
                                               }
                                           }
                                       }
                                   }
                               } catch (Exception e) {
                                   LogUtil.error("用户签收解冻团长冻结金额异常",e);
                               }
                           }
                       }.start();
                   }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            object.put("status", "success");
            object.put("message", "获取信息成功");
            TagUtil.getSendJson(response, object.toString());
        }
    /**
     * @Author shishanshan
     * @Desprition 发货解冻金额
     * @Date 2018/11/24 17:12
     * @Param
     * @Return
     **/
    public void unFreePriceOfLevel(String userId,String unfreePrice) {
        try {
            if (StringUtils.isNotBlank(userId)){
                NUserEntity nUserEntity = systemService.getEntity(NUserEntity.class, userId);
                if(nUserEntity != null){
                    //1.冻结金额减少
                    String ext5 = CommonUtils.isZeroForBlank(nUserEntity.getExt5());
                    String decre = CommonUtils.decre(ext5,unfreePrice);
                    Double aDouble = Double.valueOf(decre);
                    if(aDouble <0){
                        ext5 = "0";
                    }else{
                        ext5 = decre;
                    }
                    //2.钱包金额增加
                    String price = CommonUtils.isZeroForBlank(nUserEntity.getPrice());
                    price = CommonUtils.add(price,unfreePrice);
                    //3.总金额增加
                    String ext1 = CommonUtils.isZeroForBlank(nUserEntity.getExt1());
                    ext1 = CommonUtils.add(ext1,unfreePrice);

                    nUserEntity.setPrice(price);
                    nUserEntity.setExt1(ext1);
                    nUserEntity.setExt5(ext5);
                    systemService.saveOrUpdate(nUserEntity);
                    NUserPriceEntity userPriceEntityRefferrer = new NUserPriceEntity();
                    userPriceEntityRefferrer.setId(StringUtil.getId());
                    userPriceEntityRefferrer.setUserId(nUserEntity.getId());
                    userPriceEntityRefferrer.setRealname(nUserEntity.getRealname());
                    userPriceEntityRefferrer.setCreateDate(new Date());
                    userPriceEntityRefferrer.setUserPriceType("店铺收入");
                    userPriceEntityRefferrer.setOrderid("11");//店铺收入冻结金额
                    //4.微信推送成团奖励
                    weixinservice.sendTemplate_refferrer(userId,"店铺收益到账了.",unfreePrice+"元","客户已确认收货(冻结金额解冻)","",DateUtils.formatTimehsm(new Date()),"点击查看钱包详情，如有疑问直接咨询在线客服。");

                }
            }
        }catch (Exception e){
            logger.error(e);
            e.printStackTrace();
        }
    }
    /**
          * @Author shishanshan
          * @Desprition 售后退款
          * @Date 2018/11/19 11:36
          * @Param
          * @Return
          **/
        @RequestMapping(params = "aftersale_exit_money")
        public void aftersale_exit_money (HttpServletResponse response, String norderid,String order_type,String aftersale_status,String aftersale_type,
                String order_status,String aftersaleDetails,String aftersalePhone,String aftersalePic,String ptjoin){
            /**order_status	订单状态	C	36		0待付款1待拼单，2待发货3已发货4已签收5已评价6售后处理中
             * order_type	订单类型	0正常 1售后处理中
             * aftersale_status	售后状态		0申请退款1退款成功，2驳回退款3平台强制退款4平台拒绝退款5退款失败
             * aftersale_type	售后类型		0仅退款 1退货退款（买家选择）
             * 3售后处理中   id，order_type=1 ，aftersale_status=0，aftersale_type=0  order_status=6
             * 申请说明 aftersaleDetails;
             * 	联系电话 aftersalePhone;
             * 	申请图片 aftersalePic;
             * ptjoin	申请平台介入	c	32		0是，1否
             * */
            JSONObject object = new JSONObject();
            if (!StringUtil.is_khEmpty(norderid)) {
                try {
                    Format f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date today = new Date();
                    String tday = f.format(today);
                    StringBuffer sql = new StringBuffer();
                    sql.append("update n_order set update_date='")
                            .append(tday)
                            .append("'");

                    if (!StringUtil.is_khEmpty(ptjoin)) {
                        sql.append(",ptjoin='")
                                .append(ptjoin)
                                .append("' ");
                    }
                    if (!StringUtil.is_khEmpty(order_type)) {
                        sql.append(",order_type='")
                                .append(order_type)
                                .append("' ");
                    }
                    if (!StringUtil.is_khEmpty(aftersaleDetails)) {
                        sql.append(",aftersale_details='")
                                .append(aftersaleDetails)
                                .append("' ");
                    }
                    if (!StringUtil.is_khEmpty(aftersalePhone)) {
                        sql.append(",aftersale_phone='")
                                .append(aftersalePhone)
                                .append("' ");
                    }
                    if (!StringUtil.is_khEmpty(aftersalePic)) {
                        sql.append(",aftersale_pic='")
                                .append(aftersalePic)
                                .append("' ");
                    }
                    if (!StringUtil.is_khEmpty(aftersale_status)) {
                        sql.append(",aftersale_status='")
                                .append(aftersale_status)
                                .append("' ");
                    }
                    if (!StringUtil.is_khEmpty(aftersale_type)) {
                        sql.append(",aftersale_type='")
                                .append(aftersale_type)
                                .append("' ")
                                .append(",aftersaletime='")
                                .append(tday)
                                .append("'");
                    }
                    sql.append(" where id='")
                            .append(norderid)
                            .append("'");
                    System.out.println("sql=" + sql.toString());
                    systemService.updateBySqlString(sql.toString());
                    //退款减少层级冻结金额 1.助力团 2.非团长退款 3.非会员退款
                    NOrderEntity nOrderEntity = systemService.getEntity(NOrderEntity.class, norderid);
                    if(nOrderEntity != null && "2".equals(nOrderEntity.getGoodsDetaislType()) && "1".equals(nOrderEntity.getJoinordertype()) && "N".equals(nOrderEntity.getIsMemberUser())){
                        String orderid = nOrderEntity.getOrderid();
                        String sql1 = "select id from n_order where orderid = '"+orderid+"' and paystatus = '0' and goods_detaisl_type='2'  and joinordertype = '0'";
                        List<Object> listbySql = systemService.findListbySql(sql1);
                        if(listbySql != null && listbySql.size()>0){
                            String id = (String) listbySql.get(0);
                            NOrderEntity nOrderEntity1 = systemService.getEntity(NOrderEntity.class, id);
                            if(nOrderEntity1 != null && StringUtils.isNotBlank(nOrderEntity1.getThreeLevelUser())){
                                String oneLevelUser = nOrderEntity1.getOneLevelUser();
                                String twoLevelUser = nOrderEntity1.getTwoLevelUser();
                                String threeLevelUser = nOrderEntity1.getThreeLevelUser();
                                String goodsId = nOrderEntity1.getGoodsId();
                                NGoodsDetaislEntity nGoodsDetaislEntity = systemService.getEntity(NGoodsDetaislEntity.class, goodsId);
                                if(nGoodsDetaislEntity != null && !"0".equals(nGoodsDetaislEntity.getIsMember()) && StringUtils.isNotBlank(threeLevelUser)){
                                    String oneLevelPrice = nGoodsDetaislEntity.getOneLevel();
                                    String twoLevelPrice = nGoodsDetaislEntity.getTwoLevel();
                                    String threeLevelPrice = nGoodsDetaislEntity.getThreeLevel();
                                    if(StringUtils.isNotBlank(oneLevelPrice) && StringUtils.isNotBlank(twoLevelPrice) && StringUtils.isNotBlank(threeLevelPrice) && !"0".equals(oneLevelPrice) && !"0".equals(twoLevelPrice) && !"0".equals(threeLevelPrice)){
                                        if(StringUtils.isNotBlank(threeLevelUser)){ //三级
                                            if(!"1".equals(threeLevelUser)){
                                                descUserPriceUnfree(threeLevelUser, threeLevelPrice);
                                            }
                                            if(StringUtils.isNotBlank(twoLevelUser)){ // 二级
                                                descUserPriceUnfree(twoLevelUser, twoLevelPrice);
                                                if(StringUtils.isNotBlank(oneLevelUser)){// 一级
                                                    descUserPriceUnfree(oneLevelUser, oneLevelPrice);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    LogUtil.error("售后退款异常",e);
                    e.printStackTrace();
                }
            }
            object.put("status", "success");
            object.put("message", "获取信息成功");
            TagUtil.getSendJson(response, object.toString());
        }
    /**
     * @Author shishanshan
     * @Desprition 传参用户 id 金额  增加用户冻结金额
     * @Date 2018/11/16 10:15
     * @Param
     * @Return
     **/
    public boolean  descUserPriceUnfree(String userId,String price){
        boolean flag = false;
        try {
            NUserEntity userEntity = systemService.getEntity(NUserEntity.class,userId);
            if(userEntity!=null){
                flag = true;
                String ext5 = userEntity.getExt5();
                if(!StringUtils.isNotBlank(ext5)){
                    ext5 = "0";
                }
                ext5 = CommonUtils.decre(ext5,price);
                Double aDouble = Double.valueOf(ext5);
                if(aDouble<0){
                    ext5 = "0";
                }
                userEntity.setExt5(ext5);
                systemService.saveOrUpdate(userEntity);
                //2.微信推送
                weixinservice.sendTemplate_refferrer(userId,"用户退款,店铺(冻结金额)减少!",price+"元","用户退款","",DateUtils.formatTimehsm(new Date()),"点击查看钱包详情，如有疑问直接咨询在线客服。");
            }
        }catch (Exception e){
            LogUtil.error("用户金额增加异常",e);
        }
        return  flag;
    }
        /*订单管理 n_order 根据订单id，获取订单详情*/
        @RequestMapping(params = "aftersale_details")
        public void aftersale_details (HttpServletRequest request,
                HttpServletResponse response,
                String norderid){
            /**order_status	订单状态	C	36		0待付款1待拼单，2待发货3已发货4已签收5已评价6售后处理中
             * order_type	订单类型	0正常 1售后处理中
             * aftersale_status	售后状态		0申请退款1退款成功，2驳回退款3平台强制退款4平台拒绝退款5退款失败
             * aftersale_type	售后类型		0仅退款 1退货退款（买家选择）
             * 3售后处理中   id，order_type=1 ，aftersale_status=0，aftersale_type=0  order_status=6
             * 申请说明 aftersaleDetails;
             * 	联系电话 aftersalePhone;
             * 	申请图片 aftersalePic;
             *  ptjoin	申请平台介入	c	32		0是，1否
             * */
            JSONObject object = new JSONObject();
            NOrderEntity t = systemService.get(NOrderEntity.class, norderid);
            if (t != null) {
                object.put("id", t.getId());
                object.put("goods_id", t.getGoodsId());
                object.put("goodsname", t.getGoodsname());
                object.put("user_id", t.getUserId());

                object.put("paySum", t.getPaySum());
                object.put("aftersaleStatus", t.getAftersaleStatus());
                object.put("aftersaleType", t.getAftersaleType());
                object.put("aftersaleDetails", t.getAftersaleDetails());
                object.put("aftersalePhone", t.getAftersalePhone());
                object.put("aftersalePic", t.getAftersalePic());
                object.put("ptjoin", t.getPtjoin());
                String merchantId = t.getMerchantId();
                NMerchantEntity mode = systemService.getEntity(NMerchantEntity.class, merchantId);
                if (mode != null) {
                    object.put("merchantId", merchantId);
                    object.put("merchantname", mode.getCompany());
                    object.put("merchantlogo", mode.getMerchantlogo());
                }

            }
            object.put("status", "success");
            object.put("message", "获取信息成功");
            TagUtil.getSendJson(response, object.toString());
        }

        public void addActionMoney (String t_id){
            /*增加商品可提现金额*/
            NOrderEntity t = systemService.get(NOrderEntity.class, t_id);
            if (t != null) {
                /**查询商家信息*/
                String goods_sum = t.getGoodsSum();
                String merchantid = t.getMerchantId();
                if (StringUtil.is_khEmpty(goods_sum)) {
                    goods_sum = "0";
                }
                NMerchantEntity nMerchant = systemService.getEntity(NMerchantEntity.class, merchantid);
                BigDecimal b1 = new BigDecimal(goods_sum);
                String actionMoney = nMerchant.getActionMoney();
                if (StringUtil.is_khEmpty(actionMoney)) {
                    actionMoney = "0";
                }
                BigDecimal b2 = new BigDecimal(actionMoney);
                // String new_price= b2.subtract(b1).toString();
                String new_price = b1.add(b2).toString();
                nMerchant.setActionMoney(new_price);
                systemService.saveOrUpdate(nMerchant);
            }
        }

        /*订单管理 n_order 获得售后信息,售后 order_type	订单类型、0正常 1售后处理中*/
        @RequestMapping(params = "aftersale_order_info")
        public void aftersale_order_info (HttpServletRequest request, HttpServletResponse response, String
        userId, Integer page, Integer rows){
            Integer start = (page - 1) * rows;
            JSONObject object = null;
            JSONArray arry = null;
            try {
                if (start > 0) {
                    if (page > 0) {
                        page = page - 1;
                        start = page * rows;
                    } else {
                        start = 0;
                    }
                }

                object = new JSONObject();
                arry = new JSONArray();
                StringBuffer sql = new StringBuffer();
                sql.append("select  id,goods_id,goodsname,user_id,realname,usernameurl, ")
                        .append(" marketing_one,goods_detaisl_type,merchant_id,merchantname, ")
                        .append(" order_status,order_type,aftersale_status,aftersale_type, ")
                        .append(" goods_sum,numbers,pay_sum,express_nub, ")
                        .append(" express_name,details,merchant_back,joinordertype, ")
                        .append(" joinorderstatus,orderid,ptstatus,end_time,")
                        .append(" payorderstatus,couponid,addressid,userprice,paystatus,")
                        .append(" standardid,standardname,picurl,isvirtual ")
                        .append(" from n_order  where order_type='1' and ")
                        .append("    user_id='")
                        .append(userId)
                        .append("'")
                        .append("    and order_type='1'");

                sql.append("    ORDER BY applytime desc    ")
                        //			   sql.append(" GROUP BY orderid   ORDER BY end_time asc    ")
                        .append(" LIMIT ")
                        .append(start)
                        .append(",")
                        .append(rows);
                System.out.println(sql.toString());
                List<Object[]> mode_list = systemService.findListbySql(sql.toString());
                for (int i = 0; i < mode_list.size(); i++) {
                    Object[] t = mode_list.get(i);
                    JSONObject object_mode = new JSONObject();
                    object_mode.put("id", t[0]);
                    object_mode.put("goods_id", t[1]);
                    object_mode.put("goodsname", t[2]);
                    object_mode.put("user_id", t[3]);
                    object_mode.put("realname", t[4]);
                    object_mode.put("usernameurl", t[5]);
                    object_mode.put("marketingOne", t[6]);
                    object_mode.put("goodsDetaislType", t[7]);
                    object_mode.put("merchantId", t[8]);
                    object_mode.put("merchantname", t[9]);
                    object_mode.put("orderStatus", t[10]);
                    object_mode.put("orderType", t[11]);
                    object_mode.put("aftersaleStatus", t[12]);
                    object_mode.put("aftersaleType", t[13]);
                    object_mode.put("goodsSum", t[14]);
                    object_mode.put("numbers", t[15]);
                    object_mode.put("paySum", t[16]);
                    object_mode.put("expressNub", t[17]);
                    object_mode.put("expressName", t[18]);
                    object_mode.put("details", t[19]);
                    object_mode.put("merchantBack", t[20]);
                    /***1待拼单    任务团，
                     * joinorderstatus 1已完成，order_status 1待拼单，正面订单已经完成50%，剩下优惠券的使用*/
                    object_mode.put("joinordertype", t[21]);//0开团、1拼团，3单买
                    object_mode.put("joinorderstatus", t[22]);//0待完成，1已完成、2订单取消
                    object_mode.put("orderid", t[23]);
                    object_mode.put("ptstatus", t[24]);
                    object_mode.put("endTime", t[25]);
                    object_mode.put("payorderstatus", t[26]);
                    object_mode.put("couponid", t[27]);
                    object_mode.put("addressid", t[28]);
                    object_mode.put("userprice", t[29]);
                    object_mode.put("paystatus", t[30]);
                    object_mode.put("standardid", t[31]);
                    object_mode.put("standardname", t[32]);
                    object_mode.put("picurl", t[33]);
                    object_mode.put("isvirtual", t[34]);
                    arry.add(object_mode);
                }
            } catch (Exception e) {
                logger.error(e);
                e.printStackTrace();
            }
            object.put("details", arry);
            object.put("status", "success");
            object.put("message", "获取信息成功");
            TagUtil.getSendJson(response, object.toString());
        }

        /*订单管理 n_order 获得售后信息  分享的时候获取用户信息*/
        @RequestMapping(params = "order_info_share", method = RequestMethod.POST)
        public void order_info_share (HttpServletRequest request,
                HttpServletResponse response,
                String orderid, Integer page, Integer rows){
		/*Integer start = (page - 1) * rows;
		if (start > 0) {
			if (page > 0) {
				page = page - 1;
				start = page * rows;
			} else {
				start = 0;
			}
		}*/
            JSONObject object = new JSONObject();
            JSONArray arry = new JSONArray();
            //String orderid="";
	/*	if(!StringUtil.is_khEmpty(id)){
		NOrderEntity	nOrder = systemService.getEntity(NOrderEntity.class, id);
		       if(nOrder!=null){
		    	   Format f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			         // Date today = new Date();
			          //String pdtime=f.format(today);
		           orderid=nOrder.getOrderid();
		        String ptPerson= nOrder.getPtPerson();
		        String goodsDetaislType= nOrder.getGoodsDetaislType();
		        Date create_date= nOrder.getCreateDate();
		        Date end_time= nOrder.getEndTime();
//		        String createDate=f.format(create_date);
//		        String endTime=f.format(end_time);
		        object.put("ptPerson", ptPerson);
		        object.put("goodsDetaislType",goodsDetaislType);
		        object.put("createDate", create_date.getTime());
		        object.put("endTime", end_time.getTime());
		       }
		}*/

            StringBuffer sql = new StringBuffer();
            sql.append("select  id,goods_id,goodsname,user_id,realname,usernameurl, ")
                    .append(" marketing_one,goods_detaisl_type,merchant_id,merchantname, ")
                    .append(" order_status,order_type,aftersale_status,aftersale_type, ")
                    .append(" goods_sum,numbers,pay_sum,express_nub, ")
                    .append(" express_name,details,merchant_back,joinordertype, ")
                    .append(" joinorderstatus,orderid,ptstatus,create_date,end_time,pt_person,")
                    .append(" payorderstatus,couponid,addressid,userprice,paystatus,")
                    .append(" standardid,standardname,picurl,isvirtual ")
                    .append(" from n_order  where paystatus='0' and ")
                    .append("    orderid='")
                    .append(orderid)
                    .append("'");
            // .append("    and order_type='1'");

            sql.append("    ORDER BY applytime asc    ");
//			   sql.append(" GROUP BY orderid   ORDER BY end_time asc    ")
			  /* .append(" LIMIT ")
			   .append(start)
			   .append(",")
			   .append(rows);*/
            System.out.println(sql.toString());
            List<Object[]> mode_list = systemService.findListbySql(sql.toString());
            for (int i = 0; i < mode_list.size(); i++) {
                Object[] t = mode_list.get(i);

                String joinordertype = t[21] + "";
                if ("0".equals(joinordertype)) {
                    String create_date = t[25] + "";
                    String end_time = t[26] + "";
                    String ptPerson = t[27] + "";
                    System.out.println(create_date + "=create_date=" + end_time);
                    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
                        String createDate = f.format(f.parse(create_date));
                        String endTime = f.format(f.parse(end_time));
                        System.out.println(createDate + "=create_date=" + endTime);
                        object.put("id", t[0]);
                        object.put("goods_id", t[1]);
                        object.put("goodsname", t[2]);
                        object.put("user_id", t[3]);
                        object.put("realname", t[4]);
                        object.put("usernameurl", t[5]);
                        object.put("ptPerson", ptPerson);
                        object.put("goodsDetaislType", t[7]);
                        object.put("createDate", createDate);
                        object.put("endTime", endTime);
                        object.put("isvirtual", t[36]);
                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                } else {
                    JSONObject object_mode = new JSONObject();
                    object_mode.put("id", t[0]);
                    object_mode.put("goods_id", t[1]);
                    object_mode.put("goodsname", t[2]);
                    object_mode.put("user_id", t[3]);
                    object_mode.put("realname", t[4]);
                    object_mode.put("usernameurl", t[5]);
                    object_mode.put("goodsDetaislType", t[7]);
                    object_mode.put("joinordertype", t[21]);//0开团、1拼团，3单买
                    object_mode.put("isvirtual", t[36]);
                    arry.add(object_mode);
                }
					/*object_mode.put("marketingOne", t[6]);
					object_mode.put("goodsDetaislType", t[7]);
					object_mode.put("merchantId", t[8]);
					object_mode.put("merchantname", t[9]);
					object_mode.put("orderStatus", t[10]);
					object_mode.put("orderType", t[11]);
					object_mode.put("aftersaleStatus", t[12]);
					object_mode.put("aftersaleType", t[13]);
					object_mode.put("goodsSum", t[14]);
					object_mode.put("numbers", t[15]);
					object_mode.put("paySum", t[16]);
					object_mode.put("expressNub", t[17]);
					object_mode.put("expressName", t[18]);
					object_mode.put("details", t[19]);
					object_mode.put("merchantBack", t[20]);*/
                /***1待拼单    任务团，
                 * joinorderstatus 1已完成，order_status 1待拼单，正面订单已经完成50%，剩下优惠券的使用*/
				/*	object_mode.put("joinordertype", t[21]);//0开团、1拼团，3单买
					object_mode.put("joinorderstatus", t[22]);//0待完成，1已完成、2订单取消
					object_mode.put("orderid", t[23]);
					object_mode.put("ptstatus", t[24]);
					object_mode.put("endTime", t[25]);
					object_mode.put("payorderstatus", t[26]);
					object_mode.put("couponid", t[27]);
					object_mode.put("addressid", t[28]);
					object_mode.put("userprice", t[29]);
					object_mode.put("paystatus", t[30]);
					object_mode.put("standardid", t[31]);
					object_mode.put("standardname", t[32]);
					object_mode.put("picurl", t[33]);*/

            }
            //sss 新加获取会员商品参团人员头像orderid
            JSONArray arrayMember = new JSONArray();
            //String jionRecordSql = "select * from  n_goods_join_record where order_id = '"+orderid+"' order by id ";
            List<NGoodsJoinRecord> nGoodsJoinRecords = systemService.findByProperty(NGoodsJoinRecord.class, "orderId", orderid);
            //List<NGoodsJoinRecord> list = systemService.findListbySql(jionRecordSql);
            if (nGoodsJoinRecords != null && nGoodsJoinRecords.size() > 0) {
                for (int i = 0; i < nGoodsJoinRecords.size(); i++) {
                    NGoodsJoinRecord nGoodsJoinRecord = nGoodsJoinRecords.get(i);
                    JSONObject jsonRecord = new JSONObject();
                    jsonRecord.put("usernameurl", nGoodsJoinRecord.getUserNameUrl());
                    arrayMember.add(jsonRecord);
                }
            }
            object.put("details", arry);
            object.put("detailsMember", arrayMember);
            object.put("status", "success");
            object.put("message", "获取信息成功");
            TagUtil.getSendJson(response, object.toString());
        }

        /*订单管理 order_info_address 获得订单信息*/
        @RequestMapping(params = "order_info_address")
        public void order_info_address (HttpServletResponse response, String norderid){
            JSONObject object = new JSONObject();
            try {
                if(StringUtils.isNotBlank(norderid)){
                    NOrderEntity t = systemService.get(NOrderEntity.class, norderid);
                    String good_id = t.getGoodsId();
                    NGoodsDetaislEntity nGoodsDetaisl = systemService.getEntity(NGoodsDetaislEntity.class, good_id);
                    object.put("id", t.getId());
                    object.put("addressid", t.getAddressid());
                    object.put("area", t.getArea() + t.getAddress());
                    object.put("phone", t.getPhone());
                    object.put("acceptname", t.getAcceptname());
                    //	System.out.println("acceptname="+t.getAcceptname());
                    object.put("applytime", t.getApplytime() + "");
                    /**paymode	支付方式	c	36		0微信、1支付宝、2无支付、3公众号支付*/
                    object.put("paymode", t.getPaymode());
                    /**是否虚拟	0是，1否*/
                    if (nGoodsDetaisl != null)
                        object.put("isvirtual", nGoodsDetaisl.getIsvirtual());

                    object.put("paystatus", t.getPaystatus());
                    object.put("status", "success");
                    object.put("message", "获取信息成功");
                }
            }catch (Exception e){
                LogUtil.error("获得订单信息",e);
            }
            TagUtil.getSendJson(response, object.toString());
        }

        /*推荐商品 recommend_goods*/
        @RequestMapping(params = "recommend_goods")
        public void recommend_goods (HttpServletRequest request,
                HttpServletResponse response,
                String goodsId, Integer page, Integer rows){
            Integer start = (page - 1) * rows;
            if (start > 0) {
                if (page > 0) {
                    page = page - 1;
                    start = page * rows;
                } else {
                    start = 0;
                }
            }
            JSONObject object = new JSONObject();
            JSONArray arry = new JSONArray();
            StringBuffer sql = new StringBuffer();
            sql.append(" select t2.id as oneid , t2.id as tig,")
                    .append("t2.id,t2.goods_detaisl_status,t2.merchant_id, ")
                    .append("t2.goods_classify_id,t2.title,t2.duan_title,")
                    .append("t2.starttype,t2.weight,t2.bazaar_price,t2.contents,")
                    .append("t2.send_time,t2.post,t2.returns,t2.fine,t2.pic_one,")
                    .append("t2.standard,t2.standard_one,t2.standard_two,")
                    .append("t2.sum_numbers,t2.codes,t2.group_purchase,")
                    .append("t2.set_bounds,t2.xiangou,t2.reject,t2.orders,")
                    .append("t2.shangpintype,t2.join_huodong,t2.carousel,")
                    .append("t2.details,t2.goodsone,t2.goodstwo,t2.goodsthree,")
                    .append("t2.standard_onelist,t2.standard_twolist,t2.tuanprices,t2.unitprices,t2.marketing_one ")
                    .append(",t2.pic_two,t2.sellnumbs ")
                    .append(" from ")
                    .append(" n_goods_detaisl t2   ")
                    /*		.append("  ,(  ")
                            .append(" select goodsthree from  n_goods_detaisl where id='")
                            .append(goodsId)
                            .append("' ")
                            .append(")as t1 ")*/
//			.append("  where t2.goodsthree=t1.goodsthree   and t2.goods_detaisl_status='2'  ")
                    .append("  where  t2.goods_detaisl_status='2'  ")
                    .append(" and t2.shangpintype='0' and t2.join_huodong='1' ")
                    .append(" ORDER BY  t2.orders asc , t2.sellnumbs asc")
                    .append(" LIMIT ")
                    .append(start)
                    .append(",")
                    .append(rows);
            System.out.println("sql==" + sql.toString());
            List<Object[]> mode_list = systemService.findListbySql(sql.toString());
            for (int i = 0; i < mode_list.size(); i++) {
                Object[] mode = mode_list.get(i);
                JSONObject object_mode = new JSONObject();
//					object_mode.put("onedetailsid", mode[0]);
//					object_mode.put("oneid", mode[1]);
                object_mode.put("id", mode[2]);
                object_mode.put("title", mode[6]);
                object_mode.put("duan_title", mode[7]);
                object_mode.put("starttype", mode[8]);
                object_mode.put("pic_one", mode[16]);
                String tuanprices = mode[36] + "";
                if (tuanprices.indexOf("~") != -1)
                    tuanprices = tuanprices.substring(0, tuanprices.indexOf("~"));
                object_mode.put("tuanprices", tuanprices);
                object_mode.put("pic_two", mode[39]);
                String sellnumbs = mode[40] + "";
                if (StringUtil.is_khEmpty(sellnumbs)) {
                    sellnumbs = "0";
                }
                object_mode.put("sellnumbs", sellnumbs);
                arry.add(object_mode);

            }
            object.put("details", arry);
            object.put("status", "success");
            object.put("message", "获取信息成功");
            TagUtil.getSendJson(response, object.toString());
        }

        /*订单管理 n_order 根据订单id，获取订单信息*/
        @RequestMapping(params = "order_info_one")
        public void order_info_one (HttpServletRequest request,
                HttpServletResponse response,
                String id){

            JSONObject object = new JSONObject();
            JSONArray arry = new JSONArray();
            StringBuffer sql = new StringBuffer();
            sql.append("select  id,goods_id,goodsname,user_id,realname,usernameurl, ")
                    .append(" marketing_one,goods_detaisl_type,merchant_id,merchantname, ")
                    .append(" order_status,order_type,aftersale_status,aftersale_type, ")
                    .append(" goods_sum,numbers,pay_sum,express_nub, ")
                    .append(" express_name,details,merchant_back,joinordertype, ")
                    .append(" joinorderstatus,orderid,ptstatus,end_time,")
                    .append(" payorderstatus,couponid,addressid,userprice,paystatus,")
                    .append(" standardid,standardname,picurl ")
                    .append(" from n_order  where  ")
                    .append("    id='")
                    .append(id)
                    .append("'");

            System.out.println(sql.toString());
            List<Object[]> mode_list = systemService.findListbySql(sql.toString());
            for (int i = 0; i < mode_list.size(); i++) {
                Object[] t = mode_list.get(i);
                JSONObject object_mode = new JSONObject();
                object_mode.put("id", t[0]);
                object_mode.put("goods_id", t[1]);
                object_mode.put("goodsname", t[2]);
                object_mode.put("user_id", t[3]);
                object_mode.put("realname", t[4]);
                object_mode.put("usernameurl", t[5]);
                object_mode.put("marketingOne", t[6]);
                object_mode.put("goodsDetaislType", t[7]);
                object_mode.put("merchantId", t[8]);
                object_mode.put("merchantname", t[9]);
                object_mode.put("orderStatus", t[10]);
                object_mode.put("orderType", t[11]);
                object_mode.put("aftersaleStatus", t[12]);
                object_mode.put("aftersaleType", t[13]);
                object_mode.put("goodsSum", t[14]);
                object_mode.put("numbers", t[15]);
                object_mode.put("paySum", t[16]);
                object_mode.put("expressNub", t[17]);
                object_mode.put("expressName", t[18]);
                object_mode.put("details", t[19]);
                object_mode.put("merchantBack", t[20]);
                /***1待拼单    任务团，
                 * joinorderstatus 1已完成，order_status 1待拼单，正面订单已经完成50%，剩下优惠券的使用*/
                object_mode.put("joinordertype", t[21]);//0开团、1拼团，3单买
                object_mode.put("joinorderstatus", t[22]);//0待完成，1已完成、2订单取消
                object_mode.put("orderid", t[23]);
                object_mode.put("ptstatus", t[24]);
                object_mode.put("endTime", t[25]);
                object_mode.put("payorderstatus", t[26]);
                object_mode.put("couponid", t[27]);
                object_mode.put("addressid", t[28]);
                object_mode.put("userprice", t[29]);
                object_mode.put("paystatus", t[30]);
                object_mode.put("standardid", t[31]);
                object_mode.put("standardname", t[32]);
                object_mode.put("picurl", t[33]);
                arry.add(object_mode);
            }
            object.put("details", arry);
            object.put("status", "success");
            object.put("message", "获取信息成功");
            TagUtil.getSendJson(response, object.toString());
        }

        /*订单管理 n_order,订单状态  单买*/
/*	@RequestMapping(params = "n_order_one")
	public void n_order_one(HttpServletRequest request,
			HttpServletResponse response,NOrderEntity mode,String id,
			String userId,String realname,String usernameurl,String goodsDetaislType,
			String merchantId,String merchantname,
			String goodsId,String goodsname,
			String goodsSum,String numbers,
			String paySum,String joinordertype,
		    String addressid,String payorderstatus,
		    String marketingOne,	String couponid,
		    String userprice,String paystatus,String standardid) {

	        Date today = new Date();
	    	mode.setCreateDate(today);
	    	mode.setOrderType("0");//order_type	订单类型0正常 1售后处理中
			mode.setEndTime(today);

			//paystatus 0 付款成功 1付款失败
			if("0".equals(paystatus)){
				mode.setGatheringstatus("1");//gatheringstatus	商家收款状态	0未收款 1冻结中2已收款
				mode.setOrderStatus("2");   //0待付款1待拼单2待发货3已发货4已签收5待评价6售后处理中
				//减去余额
				getUpdatePrice(userId, userprice);
		     }else{
		    	mode.setGatheringstatus("0");//gatheringstatus	商家收款状态	0未收款 1冻结中2已收款
		    	mode.setOrderStatus("0");   //0待付款1待拼单2待发货3已发货4已签收5待评价6售后处理中
		     }
			mode.setJoinorderstatus("1");//	拼单状态0待完成，1已完成、2作废订单
			mode.setPtstatus("1");//ptstatus	拼团状态 0未完成1已完成（主要用于任务团）
			JSONObject object = new JSONObject();
				//joinordertype	拼单类型	c	36		0开团、1拼团，3单买
			mode.setJoinordertype("3");
			String orderid=StringUtil.getId();//随机生成
			mode.setOrderid(orderid);//拼单id,同一拼单的唯一关联
			mode.setApplytime(new Date());
        if(!StringUtil.is_khEmpty(id)){
	    	 try {
	    		 NOrderEntity t = systemService.get(NOrderEntity.class, id);
	    		 if(t!=null){
				 MyBeanUtils.copyBeanNotNull2Bean(mode, t);
				 systemService.saveOrUpdate(t);
	    		 }else{
	    			 systemService.save(mode);
	    		 }
	    	 } catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	 }
		object.put("status", "success");
		object.put("message", "获取信息成功");
		TagUtil.getSendJson(response, object.toString());
  }*/

        /*订单管理 n_order,订单状态  单买*/
        @RequestMapping(params = "postsaveorderone")
        public void postsaveorderone (HttpServletRequest request, HttpServletResponse response, NOrderEntity mode, String id, String paystatus){
            LogUtil.info(mode.toString());
            Date today = new Date();
            mode.setCreateDate(today);
            mode.setOrderType("0");//order_type	订单类型0正常 1售后处理中
            mode.setEndTime(today);
            mode.setRedResultCode("N");
            if("1".equals(paystatus)){
                NGoodsDetaislEntity nGoodsDetaislEntity = systemService.getEntity(NGoodsDetaislEntity.class, mode.getGoodsId());
                //获取用户信息
                NUserEntity nUserEntity = systemService.getEntity(NUserEntity.class, mode.getUserId());
                String isMember = nUserEntity.getIsMember();//是否会员
                if(StringUtils.isBlank(isMember)){
                    isMember = "N";
                }
                mode.setIsMemberUser(isMember);
                mode.setIsMemberGoods(nGoodsDetaislEntity.getIsMember());
                //防止单买截包 修改金额
                NStandardDetailsEntity nStandardDetailsEntity = systemService.getEntity(NStandardDetailsEntity.class, mode.getStandardid());
                mode.setPaySum(nStandardDetailsEntity.getUnitPrice());
            }
            //paystatus 0 付款成功 1付款失败
            if ("0".equals(paystatus)) {
                mode.setGatheringstatus("1");//gatheringstatus	商家收款状态	0未收款 1冻结中2已收款
                mode.setOrderStatus("2");   //0待付款1待拼单2待发货3已发货4已签收5待评价6售后处理中
                //减去余额
                //getUpdatePrice(userId, userprice);
            } else {
                mode.setGatheringstatus("0");//gatheringstatus	商家收款状态	0未收款 1冻结中2已收款
                mode.setOrderStatus("0");   //0待付款1待拼单2待发货3已发货4已签收5待评价6售后处理中
            }
            mode.setJoinorderstatus("1");//	拼单状态0待完成，1已完成、2作废订单
            mode.setPtstatus("1");//ptstatus	拼团状态 0未完成1已完成（主要用于任务团）
            JSONObject object = new JSONObject();
            //joinordertype	拼单类型	c	36		0开团、1拼团，3单买
            mode.setJoinordertype("3");
            String orderid = StringUtil.getId();//随机生成
            mode.setOrderid(orderid);//拼单id,同一拼单的唯一关联
            mode.setApplytime(today);
            if (StringUtils.isNotBlank(id)) {
                try {
                    NOrderEntity t = systemService.get(NOrderEntity.class, id);
                    if (t != null) {
                        MyBeanUtils.copyBeanNotNull2Bean(mode, t);
                        systemService.saveOrUpdate(t);
                    } else {
                        systemService.save(mode);
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            object.put("status", "success");
            object.put("message", "获取信息成功");
            TagUtil.getSendJson(response, object.toString());
        }

        /*用户购买商品的数量*/
        @RequestMapping(params = "n_good_sum")
        public void n_order_one (HttpServletRequest request,
                HttpServletResponse response, NOrderEntity mode, String id,
                String userId, String goodsId){
            JSONObject object = new JSONObject();
            StringBuffer sql = new StringBuffer();
            sql.append("select  id,goods_id,goodsname,user_id,COUNT(goods_id)as goods_sum  ")
                    .append(" from n_order  where ")
                    .append("    user_id='")
                    .append(userId)
                    .append("'")
                    .append("  and   goods_id='")
                    .append(goodsId)
                    .append("' ")
                    .append("  and paystatus='0' GROUP BY user_id,goods_id ");
            System.out.println("sql===" + sql.toString());
            List<Object[]> mode_list = systemService.findListbySql(sql.toString());
            if (mode_list != null && mode_list.size() > 0) {
                Object[] t = mode_list.get(0);
                //	JSONObject object_mode = new JSONObject();
//			object.put("id", t[0]);
//			object.put("goods_id", t[1]);
//			object.put("goodsname", t[2]);
//			object.put("user_id", t[3]);
                String goods_sum = t[4] + "";
                if (StringUtil.is_khEmpty(goods_sum)) {
                    goods_sum = "0";
                }
                object.put("goods_sum", goods_sum);
            } else {
                object.put("goods_sum", "0");
            }
            object.put("status", "success");
            object.put("message", "获取信息成功");
            TagUtil.getSendJson(response, object.toString());
        }

        /*订单管理 n_order,获取订单状态goods_detaisl_type*/
        @RequestMapping(params = "order_info")
        public void order_info (HttpServletRequest request, HttpServletResponse response, NOrderEntity mode, String
        paystatus,
                String userId, String order_status, Integer page, Integer rows, String goodsDetaislType){
            //System.out.println("order_info==="+userId);
            Integer start = (page - 1) * rows;
            if (start > 0) {
                if (page > 0) {
                    page = page - 1;
                    start = page * rows;
                } else {
                    start = 0;
                }
            }

            /**order_status 0待付款1待拼单，2待发货3已发货4已签收5待评价6售后处理中*/
            JSONObject object = new JSONObject();
            JSONArray arry = new JSONArray();
            StringBuffer sql = new StringBuffer();
            sql.append("select  id,goods_id,goodsname,user_id,realname,usernameurl, ")
                    .append(" marketing_one,goods_detaisl_type,merchant_id,merchantname, ")
                    .append(" order_status,order_type,aftersale_status,aftersale_type, ")
                    .append(" goods_sum,numbers,pay_sum,express_nub, ")
                    .append(" express_name,details,merchant_back,joinordertype, ")
                    .append(" joinorderstatus,orderid,ptstatus,end_time,")
                    .append(" payorderstatus,couponid,addressid,userprice,paystatus,")
                    .append(" standardid,standardname,picurl,pt_person,isvirtual,acceptime  ")
                    .append(" from n_order  where ")
                    .append("    user_id='")
                    .append(userId)
                    .append("'");
            if (!StringUtil.is_khEmpty(order_status)) {
                sql.append("   and order_status='")
                        .append(order_status)
                        .append("'");
            }
            if (!StringUtil.is_khEmpty(paystatus)) {
                sql.append("   and paystatus='")
                        .append(paystatus)
                        .append("'");
            }
            if (!StringUtil.is_khEmpty(goodsDetaislType)) {
			/*sql.append("   and goods_detaisl_type='")
					.append(goodsDetaislType)
					.append("'");*/
                if ("1".equals(goodsDetaislType)) {
                    // 0微信、1支付宝、2优惠券，3、优惠券结合使用   4余额结合
                    sql.append("   and payorderstatus  in ('0','1') ");
                }
            }
            sql.append(" and goods_id != '00000000000000000000000000000001' and goods_id != '00000000000000000000000000000002' ");
            sql.append("    ORDER BY create_date desc    ")
//			   sql.append(" GROUP BY orderid   ORDER BY end_time asc    ")
                    .append(" LIMIT ")
                    .append(start)
                    .append(",")
                    .append(rows);
            System.out.println(sql.toString());
            List<Object[]> mode_list = systemService.findListbySql(sql.toString());
            for (int i = 0; i < mode_list.size(); i++) {
                Object[] t = mode_list.get(i);
                JSONObject object_mode = new JSONObject();
                String merchantid = t[8] + "";
                NMerchantEntity nMerchant = systemService.getEntity(NMerchantEntity.class, merchantid);
                if (nMerchant != null) {
                    String merchantlogo = nMerchant.getMerchantlogo();
                    String merchantname = nMerchant.getCompany();
                    object_mode.put("merchantlogo", merchantlogo);
                    object_mode.put("merchantname", merchantname);
                } else {
                    object_mode.put("merchantlogo", " ");
                    object_mode.put("merchantname", t[9]);
                }
                object_mode.put("merchantId", t[8]);
                object_mode.put("id", t[0]);
                object_mode.put("goods_id", t[1]);
                object_mode.put("goodsname", t[2]);
                object_mode.put("user_id", t[3]);
                object_mode.put("realname", t[4]);
                object_mode.put("usernameurl", t[5]);
                object_mode.put("marketing_one", t[6]);
                object_mode.put("goodsDetaislType", t[7]);
                object_mode.put("orderStatus", t[10]);
                object_mode.put("orderType", t[11]);
                object_mode.put("aftersaleStatus", t[12]);
                object_mode.put("aftersaleType", t[13]);
                object_mode.put("goodsSum", t[14]);
                object_mode.put("numbers", t[15]);
                object_mode.put("paySum", t[16]);
                object_mode.put("expressNub", t[17]);
                object_mode.put("expressName", t[18]);
                object_mode.put("details", t[19]);
                object_mode.put("merchantBack", t[20]);
                /***1待拼单    任务团，
                 * joinorderstatus 1已完成，order_status 1待拼单，正面订单已经完成50%，剩下优惠券的使用*/
                object_mode.put("joinordertype", t[21]);//0开团、1拼团，3单买
                object_mode.put("joinorderstatus", t[22]);//0待完成，1已完成、2订单取消
                object_mode.put("orderid", t[23]);
                object_mode.put("ptstatus", t[24]);
                object_mode.put("endTime", t[25]);
                object_mode.put("payorderstatus", t[26]);
                object_mode.put("couponid", t[27]);
                object_mode.put("addressid", t[28]);
                object_mode.put("userprice", t[29]);
                object_mode.put("paystatus", t[30]);
                object_mode.put("standardid", t[31]);
                object_mode.put("standardname", t[32]);
                object_mode.put("picurl", t[33]);
                object_mode.put("ptPerson", t[34]);
                object_mode.put("isvirtual", t[35]);
                //System.out.println("isvirtual=t[35]==" + t[35]);
                object_mode.put("acceptime", t[36]);
                arry.add(object_mode);
            }
            object.put("details", arry);
            object.put("status", "success");
            object.put("message", "获取信息成功");
            TagUtil.getSendJson(response, object.toString());
        }


        /*订单管理 n_order,获取订单优惠券的使用信息*/
        @RequestMapping(params = "order_info_pt")
        public void order_info_pt (HttpServletRequest request,
                HttpServletResponse response, String norderid, String userId){

            /**order_status 0待付款1待拼单，2待发货3已发货4已签收5待评价6售后处理中*/
            JSONObject object = new JSONObject();
            //	JSONArray arry = new JSONArray();
            StringBuffer sql = new StringBuffer();
			/*sql.append("select  t2.id,COUNT(*)as ptsum,t2.orderid as c_ptorderid  from n_order t1,")
			   .append(" (select *from  n_order   where    paystatus='0' and ")
			   .append("    user_id='")
			   .append(userId)
			   .append("'")
			   .append("   and  couponid='")
			   .append(norderid)
			   .append("'")
			    .append(" ) t2 ")
			   .append("  where t1.orderid=t2.orderid  and  t1.paystatus='0'   GROUP BY t1.orderid  ");*/

            sql.append("select  t2.id,COUNT(*)as ptsum,t2.orderid as c_ptorderid  from n_order t1,")
                    .append(" n_order t2  where t1.orderid=t2.orderid   and t1.paystatus=t2.paystatus ")
                    .append("  and  t2.paystatus='0' ")
                    .append("  and t2.user_id='")
                    .append(userId)
                    .append("'   and  t2.couponid='")
                    .append(norderid)
                    .append("'    GROUP BY t1.orderid  ");
            System.out.println(sql.toString());
            List<Object[]> mode_list = systemService.findListbySql(sql.toString());
            if (mode_list != null && mode_list.size() > 0) {
                Object[] t = mode_list.get(0);
                object.put("ptsum", t[1] + "");
                object.put("c_ptorderid", t[2] + "");
            } else {
                object.put("ptsum", "0");
                object.put("c_ptorderid", "0");
            }

            object.put("status", "success");
            object.put("message", "获取信息成功");
            TagUtil.getSendJson(response, object.toString());
        }


        /*订单管理 n_order,获取拼团人数*/
        @RequestMapping(params = "order_info_person")
        public void order_info_person (HttpServletRequest request,
                HttpServletResponse response, String orderid){

            /**order_status 0待付款1待拼单，2待发货3已发货4已签收5待评价6售后处理中*/
            JSONObject object = new JSONObject();
            JSONArray arry = new JSONArray();
            StringBuffer sql = new StringBuffer();
            sql.append("select  id,goods_id,goodsname,user_id,realname,usernameurl, ")
                    .append(" marketing_one,goods_detaisl_type,merchant_id,merchantname, ")
                    .append(" order_status,order_type,aftersale_status,aftersale_type, ")
                    .append(" goods_sum,numbers,pay_sum,express_nub, ")
                    .append(" express_name,details,merchant_back,joinordertype, ")
                    .append(" joinorderstatus,orderid,ptstatus,end_time,")
                    .append(" payorderstatus,couponid,addressid,userprice,paystatus,")
                    .append(" standardid,standardname,picurl ")
                    .append(" from n_order  where ")
                    .append("    orderid='")
                    .append(orderid)
                    .append("'");

            sql.append("   and paystatus='0'  ORDER BY create_date desc    ");
//			   sql.append(" GROUP BY orderid   ORDER BY end_time asc    ")

            System.out.println(sql.toString());
            List<Object[]> mode_list = systemService.findListbySql(sql.toString());
            for (int i = 0; i < mode_list.size(); i++) {
                Object[] t = mode_list.get(i);
                JSONObject object_mode = new JSONObject();
                object_mode.put("id", t[0]);
                object_mode.put("goods_id", t[1]);
                object_mode.put("goodsname", t[2]);
                object_mode.put("user_id", t[3]);
                object_mode.put("realname", t[4]);
                object_mode.put("usernameurl", t[5]);
                object_mode.put("goodsDetaislType", t[7]);
                object_mode.put("merchantId", t[8]);
                object_mode.put("merchantname", t[9]);
                object_mode.put("orderStatus", t[10]);
                object_mode.put("orderType", t[11]);
                object_mode.put("aftersaleStatus", t[12]);
                object_mode.put("aftersaleType", t[13]);
                object_mode.put("goodsSum", t[14]);
                object_mode.put("numbers", t[15]);
                object_mode.put("paySum", t[16]);
                object_mode.put("expressNub", t[17]);
                object_mode.put("expressName", t[18]);
                object_mode.put("details", t[19]);
                object_mode.put("merchantBack", t[20]);
                /***1待拼单    任务团，
                 * joinorderstatus 1已完成，order_status 1待拼单，正面订单已经完成50%，剩下优惠券的使用*/
                object_mode.put("joinordertype", t[21]);//0开团、1拼团，3单买
                object_mode.put("joinorderstatus", t[22]);//0待完成，1已完成、2订单取消
                object_mode.put("orderid", t[23]);
                object_mode.put("ptstatus", t[24]);
                object_mode.put("endTime", t[25]);
                object_mode.put("payorderstatus", t[26]);
                object_mode.put("couponid", t[27]);
                object_mode.put("addressid", t[28]);
                object_mode.put("userprice", t[29]);
                object_mode.put("paystatus", t[30]);
                object_mode.put("standardid", t[31]);
                object_mode.put("standardname", t[32]);
                object_mode.put("picurl", t[33]);
                arry.add(object_mode);
            }
            object.put("details", arry);
            object.put("status", "success");
            object.put("message", "获取信息成功");
            TagUtil.getSendJson(response, object.toString());
        }

        /*订单管理 n_order,限时抽奖，中奖人名单*/
        @RequestMapping(params = "order_Winners_person")
        public void order_Winners_person (HttpServletRequest request,
                HttpServletResponse response, String orderid){

            /**order_status 0待付款1待拼单，2待发货3已发货4已签收5待评价6售后处理中*/
            JSONObject object = new JSONObject();
            JSONArray arry = new JSONArray();
            StringBuffer sql = new StringBuffer();
            sql.append("select  id,user_id,realname,usernameurl, ")
                    .append(" marketing_one,orderid ")
                    .append(" from n_order  where  marketing_one='3' and ")
                    .append(" orderid='")
                    .append(orderid)
                    .append("'");

            sql.append("    and paystatus='0'   ORDER BY create_date desc    ");
//			   sql.append(" GROUP BY orderid   ORDER BY end_time asc    ")

            System.out.println(sql.toString());
            List<Object[]> mode_list = systemService.findListbySql(sql.toString());
            for (int i = 0; i < mode_list.size(); i++) {
                Object[] t = mode_list.get(i);
                JSONObject object_mode = new JSONObject();
                object_mode.put("id", t[0]);
                object_mode.put("user_id", t[1]);
                object_mode.put("realname", t[2]);
                object_mode.put("usernameurl", t[3]);
                object_mode.put("orderid", t[5]);
                arry.add(object_mode);
            }
            object.put("details", arry);
            object.put("status", "success");
            object.put("message", "获取信息成功");
            TagUtil.getSendJson(response, object.toString());
        }
         /**
          * @Author shishanshan
          * @Desprition 添加修改收货地址异常
          * @Date 2018/11/27 15:08
          * @Param
          * @Return
          **/
        @RequestMapping(params = "n_user_address")
        public void n_user_address (HttpServletResponse response, NUserAddressEntity mode, String id){
            JSONObject object = new JSONObject();
            String status = "success";
            try {
                if (!StringUtil.is_khEmpty(id)) {
                    NUserAddressEntity t = systemService.get(NUserAddressEntity.class, id);
                    MyBeanUtils.copyBeanNotNull2Bean(mode, t);
                    systemService.saveOrUpdate(t);
                } else {
                    mode.setCreateDate(new Date());
                    systemService.save(mode);
                }
            } catch (Exception e) {
                status = "fail";
                LogUtil.error("添加修改收货地址异常",e);
            }
            object.put("status", status);
            object.put("message", "获取信息成功");
            TagUtil.getSendJson(response, object.toString());
        }
         /**
          * @Author shishanshan
          * @Desprition 删除用户订单信息
          * @Date 2018/11/27 15:20
          * @Param
          * @Return
          **/
        @RequestMapping(params = "deleteUserAddress")
        public void deleteUserAddress (HttpServletResponse response,String id){
            JSONObject object = new JSONObject();
            String status = "";
            try {
                if(StringUtils.isNotBlank(id)){
                    NUserAddressEntity nUserAddressEntity = systemService.getEntity(NUserAddressEntity.class, id);
                    if(nUserAddressEntity != null){
                        status = "Y";
                        systemService.deleteEntityById(NUserAddressEntity.class,id);
                    }else{
                        status = "002";//找不到对应的用户信息
                    }
                }else{
                    status = "001";//参数为空
                }
            }catch (Exception e){
                LogUtil.error("删除用户订单信息异常",e);
            }
            object.put("status",status);
            TagUtil.getSendJson(response, object.toString());
        }
        /*收货地址 n_user_address ,获取收货地址列表*/
        @RequestMapping(params = "n_user_address_list")
        public void n_user_address_list (HttpServletResponse response,String userId){
            JSONObject object = new JSONObject();
            JSONArray arry = new JSONArray();
            List<NUserAddressEntity> mode_list = systemService.findByProperty(NUserAddressEntity.class, "userId", userId);
            for (int i = 0; i < mode_list.size(); i++) {
                NUserAddressEntity t = mode_list.get(i);
                JSONObject object_mode = new JSONObject();
                object_mode.put("id", t.getId());
                object_mode.put("userId", t.getUserId());
                object_mode.put("realname", t.getRealname());
                object_mode.put("acceptname", t.getAcceptname());
                object_mode.put("phone", t.getPhone());
                object_mode.put("area", t.getArea());
                object_mode.put("areacode", t.getAreacode());
                object_mode.put("address", t.getAddress());
                object_mode.put("isdef", t.getIsDef());
                arry.add(object_mode);
            }
            object.put("details", arry);
            object.put("status", "success");
            object.put("message", "获取信息成功");
            TagUtil.getSendJson(response, object.toString());
        }

        /*收货地址 n_user_address */
        @RequestMapping(params = "n_user_address_info")
        public void n_user_address_info (HttpServletRequest request,
                HttpServletResponse response, NUserAddressEntity mode, String id,
                String userId){
            JSONObject object = new JSONObject();
            JSONArray arry = new JSONArray();
            NUserAddressEntity t = systemService.get(NUserAddressEntity.class, id);
            if (t != null) {
                JSONObject object_mode = new JSONObject();
                object_mode.put("id", t.getId());
                object_mode.put("userId", t.getUserId());
                object_mode.put("realname", t.getRealname());
                object_mode.put("acceptname", t.getAcceptname());
                object_mode.put("phone", t.getPhone());
                object_mode.put("area", t.getArea());
                object_mode.put("areacode", t.getAreacode());
                object_mode.put("address", t.getAddress());
                arry.add(object_mode);
            }
            object.put("details", arry);
            object.put("status", "success");
            object.put("message", "获取信息成功");
            TagUtil.getSendJson(response, object.toString());
        }

        /*评价管理保存 n_evaluate_save*/
        @RequestMapping(params = "n_evaluate_save")
        public void n_evaluate_save (HttpServletRequest request,
                HttpServletResponse response, NEvaluateEntity mode,
                String title, String detaisl, String evaluateType,
                String evaluatepic, String goodsId){
            JSONObject object = new JSONObject();
            /**评价类别 n_evaluate_type*/
            String evaluateid = UUID.randomUUID().toString();
            List<NEvaluateTypeEntity> nevaluatetype_list = systemService.loadAll(NEvaluateTypeEntity.class);
            for (int i = 0; i < nevaluatetype_list.size(); i++) {
                NEvaluateTypeEntity nevaluatetype = nevaluatetype_list.get(i);
                String typename = nevaluatetype.getTypename();
                if (!"有图".equals(typename) || !"追加".equals(typename)) {
                    if (detaisl.contains(typename)) {
                        NEvaluateColumEntity nEvaluateColum = new NEvaluateColumEntity();
                        nEvaluateColum.setCreateDate(new Date());
                        nEvaluateColum.setGoodsId(goodsId);
                        nEvaluateColum.setTitle(title);
                        nEvaluateColum.setTypename(typename);
                        nEvaluateColum.setEvaluateid(evaluateid);
                        systemService.save(nEvaluateColum);
                    }
                }
            }
            if (!StringUtil.is_khEmpty(evaluatepic)) {
                NEvaluateColumEntity nEvaluateColum = new NEvaluateColumEntity();
                nEvaluateColum.setCreateDate(new Date());
                nEvaluateColum.setGoodsId(goodsId);
                nEvaluateColum.setTitle(title);
                nEvaluateColum.setTypename("有图");
                nEvaluateColum.setEvaluateid(evaluateid);
                systemService.save(nEvaluateColum);
            }
            //0,正常1追加
            if ("1".equals(evaluateType)) {
                NEvaluateColumEntity nEvaluateColum = new NEvaluateColumEntity();
                nEvaluateColum.setCreateDate(new Date());
                nEvaluateColum.setGoodsId(goodsId);
                nEvaluateColum.setTitle(title);
                nEvaluateColum.setTypename("追加");
                nEvaluateColum.setEvaluateid(evaluateid);
                systemService.save(nEvaluateColum);
            }
            mode.setCreateDate(new Date());
            mode.setEvaluateStatus("0");//0正常1删除
            mode.setEvaluateid(evaluateid);
            systemService.save(mode);
            object.put("status", "success");
            object.put("message", "获取信息成功");
            TagUtil.getSendJson(response, object.toString());
        }

        /*评价目录 n_evaluate_colum*/
        @RequestMapping(params = "n_evaluate_colum")
        public void n_evaluate_colum (HttpServletRequest request,
                HttpServletResponse response, String goodsId){
            JSONObject object = new JSONObject();
            JSONArray arry = new JSONArray();
            StringBuffer sql = new StringBuffer();
            sql.append("select t2.id,t2.evaluateid,t2.typename,t2.create_date,count(*)as numb ")
                    .append(" from n_evaluate t1,n_evaluate_colum t2  ")
                    .append(" where t1.goods_id=t2.goods_id and evaluate_status='0'  ");
            if (!StringUtil.is_khEmpty(goodsId)) {
                sql.append(" and t2.goods_id='")
                        .append(goodsId)
                        .append("'");
            }
            sql.append(" GROUP BY t2.id ORDER BY t2.create_date asc ");
            System.out.println(sql.toString());
            List<Object[]> mode_list = systemService.findListbySql(sql.toString());
            for (int i = 0; i < mode_list.size(); i++) {
                Object[] t = mode_list.get(i);
                JSONObject object_mode = new JSONObject();
                object_mode.put("id", t[0]);
                object_mode.put("evaluateid", t[1]);
                object_mode.put("typename", t[2]);
                object_mode.put("create_date", t[3]);
                object_mode.put("numb", t[4]);
                arry.add(object_mode);
            }
            object.put("details", arry);
            object.put("status", "success");
            object.put("message", "获取信息成功");
            TagUtil.getSendJson(response, object.toString());
        }

        /*评价管理 n_evaluate*/
        @RequestMapping(params = "n_evaluate")
        public void n_evaluate (HttpServletRequest request,
                HttpServletResponse response, String evaluateid,
                String goodsId, Integer page, Integer rows){
            Integer start = (page - 1) * rows;
            if (start > 0) {
                if (page > 0) {
                    page = page - 1;
                    start = page * rows;
                } else {
                    start = 0;
                }
            }
            JSONObject object = new JSONObject();
            JSONArray arry = new JSONArray();
            StringBuffer sql = new StringBuffer();
            sql.append("select  id,create_date,goods_id,title,user_id,realname,usernameurl, ")
                    .append(" detaisl,evaluatepic,evaluate_type ")
                    .append(" from  n_evaluate where evaluate_status='0' ");
            if (!StringUtil.is_khEmpty(evaluateid)) {
                sql.append(" and evaluateid='")
                        .append(evaluateid)
                        .append("'");
            }
            if (!StringUtil.is_khEmpty(goodsId)) {
                sql.append(" and goods_id='")
                        .append(goodsId)
                        .append("'");
            }
            sql.append(" ORDER BY create_date desc  ")
                    .append(" LIMIT ")
                    .append(start)
                    .append(",")
                    .append(rows);
            System.out.println(sql.toString());
            List<Object[]> mode_list = systemService.findListbySql(sql.toString());
            for (int i = 0; i < mode_list.size(); i++) {
                Object[] t = mode_list.get(i);
                JSONObject object_mode = new JSONObject();
                object_mode.put("id", t[0]);
                object_mode.put("create_date", t[1]);
                object_mode.put("goods_id", t[2]);
                object_mode.put("goodsname", t[3]);
                object_mode.put("user_id", t[4]);
                object_mode.put("realname", t[5]);
                object_mode.put("usernameurl", t[6]);
                object_mode.put("detaisl", t[7]);
                object_mode.put("evaluatepic", t[8]);
                object_mode.put("evaluate_type", t[9]);
                arry.add(object_mode);
            }
            object.put("details", arry);
            object.put("status", "success");
            object.put("message", "获取信息成功");
            TagUtil.getSendJson(response, object.toString());
        }


        /*保存我的收藏  n_user_collect */
        @RequestMapping(params = "n_user_collect")
        public void n_user_collect (HttpServletRequest request,
                HttpServletResponse response, NUserCollectEntity mode){
            Format f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date today = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(today);
            c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
            Date tomorrow = c.getTime();
            mode.setCreateDate(today);
            StringBuffer sql = new StringBuffer();
            sql.append(" from NUserCollectEntity where ")
                    .append(" userId='")
                    .append(mode.getUserId())
                    .append("'")
                    .append(" and goodsId='")
                    .append(mode.getGoodsId())
                    .append("'");
            //System.out.println("sql=="+sql.toString());
            List<NUserCollectEntity> list = systemService.findHql(sql.toString());
            String message = "收藏成功";
            if (list != null && list.size() > 0) {
                message = "已收藏";
            } else {
                systemService.save(mode);
            }
            JSONObject object = new JSONObject();
            object.put("status", "success");
            object.put("message", message);
            TagUtil.getSendJson(response, object.toString());
        }

        /*删除我的收藏  n_user_collect */
        @RequestMapping(params = "n_user_collect_delete")
        public void n_user_collect_delete (HttpServletRequest request,
                HttpServletResponse response, String id){
            System.out.println(id);
            NUserCollectEntity userCollectEntity = systemService.getEntity(NUserCollectEntity.class, id);
            systemService.delete(userCollectEntity);
            JSONObject object = new JSONObject();
            object.put("status", "success");
            object.put("message", "获取信息成功");
            TagUtil.getSendJson(response, object.toString());
        }

        /*获取我的收藏  n_user_collect 信息*/
        @RequestMapping(params = "n_user_collect_info")
        public void n_user_collect_info (HttpServletRequest request,
                HttpServletResponse response,
                String userId, Integer page, Integer rows){
            Integer start = (page - 1) * rows;
            if (start > 0) {
                if (page > 0) {
                    page = page - 1;
                    start = page * rows;
                } else {
                    start = 0;
                }
            }
            JSONObject object = new JSONObject();
            JSONArray arry = new JSONArray();
            StringBuffer sql = new StringBuffer();
            sql.append(" select ")
                    .append("t2.id,t2.goods_detaisl_status,t2.merchant_id, ")
                    .append("t2.goods_classify_id,t2.title,t2.duan_title,")
                    .append("t2.starttype,t2.weight,t2.bazaar_price,t2.contents,")
                    .append("t2.send_time,t2.post,t2.returns,t2.fine,t2.pic_one,")
                    .append("t2.standard,t2.standard_one,t2.standard_two,")
                    .append("t2.sum_numbers,t2.codes,t2.group_purchase,")
                    .append("t2.set_bounds,t2.xiangou,t2.reject,t2.orders,")
                    .append("t2.shangpintype,t2.join_huodong,t2.carousel,")
                    .append("t2.details,t2.goodsone,t2.goodstwo,t2.goodsthree,")
                    .append("t2.standard_onelist,t2.standard_twolist,t2.tuanprices,t2.unitprices,t2.ptnumbs,t1.id as collect_id ")
                    .append("from n_goods_detaisl t2,n_user_collect t1  where t2.id=t1.goods_id ")
                    .append("  and t2.goods_detaisl_status='2'  and t2.shangpintype='0' ")
                    .append(" and t1.user_id='")
                    .append(userId)
                    .append("'")
                    .append(" ORDER BY orders asc  ")
                    .append(" LIMIT ")
                    .append(start)
                    .append(",")
                    .append(rows);
            List<Object[]> mode_list = systemService.findListbySql(sql.toString());
            for (int i = 0; i < mode_list.size(); i++) {
                Object[] mode = mode_list.get(i);
                JSONObject object_mode = new JSONObject();
                object_mode.put("id", mode[0]);
                object_mode.put("title", mode[4]);
                object_mode.put("duan_title", mode[5]);
                object_mode.put("pic_one", mode[14]);
                String tuanprices = mode[35] + "";
                if (tuanprices.indexOf("~") != -1)
                    tuanprices = tuanprices.substring(0, tuanprices.indexOf("~"));
                object_mode.put("tuanprices", tuanprices);
                object_mode.put("ptnumbs", mode[36]);
                object_mode.put("collect_id", mode[37]);
                arry.add(object_mode);
            }
            object.put("details", arry);
            object.put("status", "success");
            object.put("message", "获取信息成功");
            TagUtil.getSendJson(response, object.toString());
        }


        /*用户优惠券n_user_coupon*/
        @RequestMapping(params = "n_user_coupon")
        public void n_user_coupon (HttpServletRequest request,
                HttpServletResponse response,
                String userId, Integer page, Integer rows){
            Integer start = (page - 1) * rows;
            if (start > 0) {
                if (page > 0) {
                    page = page - 1;
                    start = page * rows;
                } else {
                    start = 0;
                }
            }
            JSONObject object = new JSONObject();
            JSONArray arry = new JSONArray();
            StringBuffer sql = new StringBuffer();
            sql.append(" select ")
                    .append("id,user_id,realname,merchant_id,goods_id,coupon_id,coupon_name,")
                    .append("money,start_time,end_time,details,coupon_type,full_money,")
                    .append("grant_type,couponstatus,norderid")
                    .append(" from n_user_coupon where  1=1")
                    .append(" and user_id='")
                    .append(userId)
                    .append("'")
                    .append("  ORDER BY couponstatus asc ,create_date desc ")
                    .append(" LIMIT ")
                    .append(start)
                    .append(",")
                    .append(rows);
            //  List<NUserCouponEntity> mode_list=systemService.findByProperty(NUserCouponEntity.class, "userId", userId);
//		for(int i=0; i<mode_list.size(); i++) {
//			NUserCouponEntity  t= mode_list.get(i);
            //System.out.println("sql.toString()=="+sql.toString());
            List<Object[]> mode_list = systemService.findListbySql(sql.toString());
            for (int i = 0; i < mode_list.size(); i++) {
                //	System.out.println(i);
                Object[] mode = mode_list.get(i);
                JSONObject object_mode = new JSONObject();
                object_mode.put("id", mode[0]);
                object_mode.put("userId", mode[1]);
                object_mode.put("realname", mode[2]);
                object_mode.put("merchantId", mode[3]);
                object_mode.put("goodsId", mode[4]);
                object_mode.put("couponId", mode[5]);
                object_mode.put("couponName", mode[6]);
                object_mode.put("money", mode[7]);
                object_mode.put("startTime", mode[8]);
                object_mode.put("endTime", mode[9]);
                object_mode.put("details", mode[10]);
                object_mode.put("couponType", mode[11]);
                object_mode.put("fullMoney", mode[12]);
                object_mode.put("grantType", mode[13]);
                object_mode.put("couponstatus", mode[14]);//0未使用1已使用,3过期
                object_mode.put("norderid", mode[15]);
                arry.add(object_mode);
            }
            object.put("details", arry);
            object.put("status", "success");
            object.put("message", "获取信息成功");
            TagUtil.getSendJson(response, object.toString());
        }

        /*用户钱包流水n_user_price*/
        @RequestMapping(params = "n_user_price")
        public void n_user_price (HttpServletRequest request, HttpServletResponse response, String userId, String
        user_price_type, Integer page, Integer rows){
            Integer start = (page - 1) * rows;
            if (start > 0) {
                if (page > 0) {
                    page = page - 1;
                    start = page * rows;
                } else {
                    start = 0;
                }
            }
            JSONObject object = new JSONObject();
            JSONArray arry = new JSONArray();
            StringBuffer sql = new StringBuffer();
            sql.append(" select ")
                    .append(" id,create_date,user_id,realname,user_price_type,price,")
                    .append(" orderid,goods_id,goodsname ")
                    .append(" from n_user_price where 1=1")
                    .append(" and user_id='")
                    .append(userId)
                    .append("'  and user_price_type='")
                    .append(user_price_type)
                    .append("' ")
                    .append(" ORDER BY create_date desc  ")
                    .append(" LIMIT ")
                    .append(start)
                    .append(",")
                    .append(rows);
            List<Object[]> mode_list = systemService.findListbySql(sql.toString());
            for (int i = 0; i < mode_list.size(); i++) {
                Object[] mode = mode_list.get(i);
                JSONObject object_mode = new JSONObject();

                object_mode.put("id", mode[0]);
                object_mode.put("createDate", mode[1]);
                object_mode.put("userId", mode[2]);
                object_mode.put("realname", mode[3]);
                object_mode.put("userPriceType", mode[4]);
                object_mode.put("price", mode[5]);
                object_mode.put("orderid", mode[6]);
                object_mode.put("goodsId", mode[7]);
                object_mode.put("goodsname", mode[8]);
                arry.add(object_mode);
            }
            object.put("details", arry);
            object.put("status", "success");
            object.put("message", "获取信息成功");
            TagUtil.getSendJson(response, object.toString());
        }


        /*用户钱包流水n_user_price*/
        @RequestMapping(params = "n_user_price_save")
        public void n_user_price_save (HttpServletRequest request,
                HttpServletResponse response,
                String userId){
            JSONObject object = new JSONObject();
            JSONArray arry = new JSONArray();
            List<NUserCouponEntity> mode_list = systemService.findByProperty(NUserCouponEntity.class, "userId", userId);
            for (int i = 0; i < mode_list.size(); i++) {
                NUserCouponEntity t = mode_list.get(i);
                JSONObject object_mode = new JSONObject();
                object_mode.put("id", t.getId());
                object_mode.put("userId", t.getUserId());
                object_mode.put("realname", t.getRealname());
                object_mode.put("merchantId", t.getMerchantId());
                object_mode.put("goodsId", t.getGoodsId());
                object_mode.put("couponId", t.getCouponId());
                object_mode.put("couponName", t.getCouponName());
                object_mode.put("money", t.getMoney());
                object_mode.put("startTime", t.getStartTime());
                object_mode.put("endTime", t.getEndTime());
                object_mode.put("details", t.getDetails());
                object_mode.put("couponType", t.getCouponType());
                object_mode.put("fullMoney", t.getFullMoney());
                object_mode.put("grantType", t.getGrantType());
                object_mode.put("couponstatus", t.getCouponstatus());
                object_mode.put("norderid", t.getNorderid());
                arry.add(object_mode);
            }
            object.put("details", arry);
            object.put("status", "success");
            object.put("message", "获取信息成功");
            TagUtil.getSendJson(response, object.toString());
        }

        /**
         * 获取用户默认收货地址
         *
         * @param req
         * @param resp
         * @param userid
         */
        @RequestMapping(params = "getDefAddress")
        public void getDefAddress (HttpServletRequest req, HttpServletResponse resp, String userid){
            String sql = "select id from n_user_address where user_id = '" + userid + "' order by isdef asc;";
            JSONObject json = new JSONObject();
            List<String> list = systemService.findListbySql(sql);
            if (list.size() > 0) {
                NUserAddressEntity entity = systemService.getEntity(NUserAddressEntity.class, list.get(0));
                json.put("address", entity);
                json.put("status", "0");
            } else {
                json.put("status", "1");
            }
            TagUtil.getSendJson(resp, json.toString());
        }
        /**
         * 设置默认收货地址
         *
         * @param req
         * @param resp
         * @param userid 用户id
         * @param id     收货地址id
         */
        @RequestMapping(params = "setDefAddress")
        public void setDefAddress (HttpServletRequest req, HttpServletResponse resp, String userid, String id){
            systemService.updateBySqlString("update n_user_address set isdef='1' where user_id='" + userid + "';");
            systemService.updateBySqlString("update n_user_address set isdef='0' where id='" + id + "';");
            JSONObject json = new JSONObject();
            json.put("message", "设置成功！");
            TagUtil.getSendJson(resp, json.toString());
        }
         /**
          * @Author shishanshan
          * @Desprition 根据地址ID获取地址详情
          * @Date 2018/9/18 16:08
          * @Param
          * @Return
          */
        @RequestMapping(params = "getAddressById")
        public void getAddressById(HttpServletResponse resp, String addressId){
            JSONObject json = new JSONObject();
            String status = "Y";
            try {
                if (StringUtils.isNotBlank(addressId)){
                    NUserAddressEntity entity = systemService.getEntity(NUserAddressEntity.class, addressId);
                    if(entity != null ){
                        json.put("acceptname",entity.getAcceptname());
                        json.put("address",entity.getAddress());
                        json.put("area",entity.getArea());
                        json.put("phone",entity.getPhone());
                    }else{
                        status = "N";
                    }
                }else{
                    status = "N";
                }
            }catch (Exception e){
                status = "N";
                logger.error(e);
                e.printStackTrace();
            }
            json.put("status",status);
            TagUtil.getSendJson(resp, json.toString());
        }
        /**
         * 发起提现申请
         *
         * @param req
         * @param resp
         * @param entity
         */
        @RequestMapping(params = "addDeposit")
        public void addDeposit (HttpServletRequest req, HttpServletResponse resp, NDepositUserEntity entity){
            entity.setCreateDate(new Date());
            JSONObject json = new JSONObject();
            Serializable id = systemService.save(entity);
            if (StringUtil.isEmpty(id.toString())) {
                json.put("message", "申请失败，请稍后重试.");
            } else {
                json.put("message", "申请成功，请等待审核.");
            }
            TagUtil.getSendJson(resp, json.toString());
        }

        /**
         * 快递包邮模板  n_express_money
         *
         * @param req
         * @param resp
         * @param entity
         */
        @RequestMapping(params = "n_express_money")
        public void n_express_money (HttpServletRequest req, HttpServletResponse response, String merchantid){
            JSONObject object = new JSONObject();
            JSONArray arry = new JSONArray();
            StringBuffer sql = new StringBuffer();
            sql.append("select id,create_date,areaname,areaid,")
                    .append("merchantid,merchantname,onemoney,nextmoney,fullnumbers ")
                    .append(" from n_express_money where merchantid='")
                    .append(merchantid)
                    .append("' order by create_date desc ");
            List<Object[]> mode_list = systemService.findListbySql(sql
                    .toString());
            for (int i = 0; i < mode_list.size(); i++) {
                Object[] t = mode_list.get(i);
                JSONObject object_mode = new JSONObject();
                object_mode.put("id", t[0]);
                //object_mode.put("create_date", t[0]);
                object_mode.put("areaname", t[2]);
                object_mode.put("areaid", t[3]);
                object_mode.put("onemoney", t[6]);
                object_mode.put("nextmoney", t[7]);
                object_mode.put("fullnumbers", t[8]);
                arry.add(object_mode);
            }
            object.put("details", arry);
            object.put("status", "success");
            object.put("message", "获取信息成功");
            TagUtil.getSendJson(response, object.toString());
        }

        /**
         * 拼团规则协议
         * n_rule
         */
        @RequestMapping(params = "n_rule")
        public void n_rule (HttpServletRequest req, HttpServletResponse response, String ruleType){
            JSONObject object = new JSONObject();
            //JSONArray arry = new JSONArray();
            NRuleEntity t = systemService.findUniqueByProperty(NRuleEntity.class, "ruleType", ruleType);
            if (t != null) {
                object.put("details", StringUtil.getUnhtml_notisEmpty(t.getDetails()));
            }
            //	object.put("details", arry);
            object.put("status", "success");
            object.put("message", "获取信息成功");
            TagUtil.getSendJson(response, object.toString());
        }

        /**
         * 常见问题
         * n_rule
         */
        @RequestMapping(params = "n_faq")
        public void n_faq (HttpServletRequest req, HttpServletResponse response){
            JSONObject object = new JSONObject();
            //JSONArray arry = new JSONArray();
            NFaqEntity t = systemService.get(NFaqEntity.class, "4028b3ec63ecaf4c0163ecdb482c0001");
            if (t != null) {
                object.put("details", StringUtil.getUnhtml_notisEmpty(t.getDetails()));
            }
            object.put("status", "success");
            object.put("message", "获取信息成功");
            TagUtil.getSendJson(response, object.toString());
        }

        /**
         * 意见反馈
         * n_feeback
         */
        @RequestMapping(params = "n_feeback")
        public void n_feeback (HttpServletRequest req, HttpServletResponse response, NFeebackEntity nFeeback){
            JSONObject object = new JSONObject();
            nFeeback.setCreateDate(new Date());
            systemService.save(nFeeback);
            object.put("status", "success");
            object.put("message", "获取信息成功");
            TagUtil.getSendJson(response, object.toString());
        }


        /**
         * 订单数量
         * goods_detaisl_type	商品类型		1任务，2普通
         * order_status	订单状态		0待付款1待拼单，2待发货3已发货4已签收5已评价6售后处理中
         */
        @RequestMapping(params = "order_sum")
        public void order_sum (HttpServletRequest req, HttpServletResponse response,
                String user_id){
            JSONObject object = new JSONObject();
            StringBuffer sql = new StringBuffer();
            sql.append("select id ,goods_detaisl_type,order_status,COUNT(*)as nub from n_order "
                    + "  where islook='1' ");
            if (!StringUtil.is_khEmpty(user_id)) {
                sql.append(" and  user_id='")
                        .append(user_id)
                        .append("'");
            }
            sql.append(" GROUP BY  goods_detaisl_type,order_status ");
            List<Object[]> mode_list = systemService.findListbySql(sql
                    .toString());
            JSONArray arry = new JSONArray();
            if (mode_list != null && mode_list.size() > 0) {
                for (int i = 0; i < mode_list.size(); i++) {
                    JSONObject object_mode = new JSONObject();
                    object_mode.put("goods_detaisl_type", mode_list.get(i)[1]);
                    object_mode.put("order_status", mode_list.get(i)[2]);
                    object_mode.put("order_sum", mode_list.get(i)[3]);
                    arry.add(object_mode);
                }
            } else {
                JSONObject object_mode = new JSONObject();
                object_mode.put("goods_detaisl_type", "0");
                object_mode.put("order_status", "0");
                object_mode.put("order_sum", "0");
                arry.add(object_mode);
            }
            object.put("details", arry);
            object.put("status", "success");
            object.put("message", "获取信息成功");
            TagUtil.getSendJson(response, object.toString());
        }

        /**
         * 订单数量
         * goods_detaisl_type	商品类型		1任务，2普通
         * order_status	订单状态		0待付款1待拼单，2待发货3已发货4已签收5已评价6售后处理中
         */
        @RequestMapping(params = "update_order_sum")
        public void update_order_sum (HttpServletRequest req, HttpServletResponse response,
                String user_id, String goods_detaisl_type, String order_status){
            JSONObject object = new JSONObject();
            StringBuffer sql = new StringBuffer();
		/*sql.append("select id ,goods_detaisl_type,order_status,COUNT(*)as nub from n_order "
				+ " where islook='1' ");
		 if(!StringUtil.is_khEmpty(user_id)){
		 sql.append(" and  user_id='")
		    .append(user_id)
		    .append("'");
		 }*/
            sql.append("update n_order set islook='0' ")
                    .append(" where  islook='1' ");
            if (!StringUtil.is_khEmpty(user_id)) {
                sql.append(" and  user_id='")
                        .append(user_id)
                        .append("'");
            }
            if (!StringUtil.is_khEmpty(goods_detaisl_type)) {
                sql.append(" and  goods_detaisl_type='")
                        .append(goods_detaisl_type)
                        .append("'");
            }
            if (!StringUtil.is_khEmpty(order_status)) {
                sql.append(" and  order_status='")
                        .append(order_status)
                        .append("'");
            }
            systemService.updateBySqlString(sql.toString());
            object.put("status", "success");
            object.put("message", "获取信息成功");
            TagUtil.getSendJson(response, object.toString());
        }


        /**
         * 聊天目录
         */
        @RequestMapping(params = "n_websocket")
        public void n_websocket (HttpServletRequest req, HttpServletResponse response,
                String userId, String usernameurl, String realname, String merchantId,
                String merchantlogo, String merchantname, String sysNum, NWebsocketEntity nwebsocket){

            StringBuffer sql = new StringBuffer();
            sql.append("select id,user_id,usernameurl from n_websocket where 1=1 ")
                    .append(" and user_id='").append(userId).append("'")
                    .append(" and merchant_id='").append(merchantId).append("'");
            List<Object[]> mode_list = systemService.findListbySql(sql.toString());


            if (mode_list != null && mode_list.size() > 0) {

                String id = mode_list.get(0)[0] + "";
                NWebsocketEntity t = systemService.get(NWebsocketEntity.class, id);
                try {
                    MyBeanUtils.copyBeanNotNull2Bean(nwebsocket, t);
                    t.setUpdateDate(new Date());
                    systemService.saveOrUpdate(t);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                nwebsocket.setCreateDate(new Date());
                nwebsocket.setUpdateDate(new Date());
                systemService.save(nwebsocket);
            }
            JSONObject object = new JSONObject();
            object.put("status", "success");
            object.put("message", "获取信息成功");
            TagUtil.getSendJson(response, object.toString());
        }


        @RequestMapping(params = "n_websocket_list")
        public void n_websocket_list (HttpServletRequest req, HttpServletResponse response,
                String userId, Integer page, Integer rows){
            Integer start = (page - 1) * rows;
            if (start > 0) {
                if (page > 0) {
                    page = page - 1;
                    start = page * rows;
                } else {
                    start = 0;
                }
            }
            JSONObject object = new JSONObject();
            StringBuffer sql = new StringBuffer();
            sql.append("select id ,user_id, usernameurl, realname, ")
                    .append(" merchant_id,merchantlogo, merchantname, sysNum,create_date ,update_date ")
                    .append("from n_websocket where 1=1 ");
            if (!StringUtil.is_khEmpty(userId)) {
                sql.append(" and  user_id='")
                        .append(userId)
                        .append("'");
            }
            sql.append("  order by update_date desc ")
                    .append(" LIMIT ")
                    .append(start)
                    .append(",")
                    .append(rows);
            ;


            List<Object[]> mode_list = systemService.findListbySql(sql
                    .toString());
            JSONArray arry = new JSONArray();
            for (int i = 0; i < mode_list.size(); i++) {
                Object[] obj = mode_list.get(i);
                JSONObject object_mode = new JSONObject();
                object_mode.put("id", obj[0]);
                object_mode.put("userId", obj[1]);
                object_mode.put("usernameurl", obj[2]);
                object_mode.put("realname", obj[3]);
                object_mode.put("merchantId", obj[4]);
                object_mode.put("merchantlogo", obj[5]);
                object_mode.put("merchantname", obj[6]);
                object_mode.put("sysNum", obj[7]);
                object_mode.put("update_date", obj[9]);
                arry.add(object_mode);
            }
            object.put("details", arry);
            object.put("status", "success");
            object.put("message", "获取信息成功");
            TagUtil.getSendJson(response, object.toString());
        }

        /**
         * 话费充值方法回调
         */
        //@RequestMapping(params="callerBack")
        @ResponseBody
        @RequestMapping("/callerBack")
        public void callerBack (HttpServletRequest req, HttpServletResponse response){
            String appkey = "5bfad9293d2b5edf153fde6c9b5e715a";//申请的接口Appkey
            String sporder_id = req.getParameter("sporder_id");//聚合订单号
            String orderid = req.getParameter("orderid");//商家订单号
            String sta = req.getParameter("sta");//充值状态
            String sign = req.getParameter("sign"); //校验值
            StringBuffer sql = new StringBuffer();
            sql.append("select id,order_id,merchant_id from n_phone_recharge  where order_id='")
                    .append(orderid)
                    .append("'  order by create_date desc  LIMIT 1");
            List<Object[]> mode_list = systemService.findListbySql(sql
                    .toString());
            //NPhoneRechargeEntity nphonerecharge = systemService.getEntity(NPhoneRechargeEntity.class,orderid);
            StringBuffer result = new StringBuffer();
            result.append("聚合订单号sporder_id=")
                    .append(sporder_id)
                    .append(";商家订单号orderid=")
                    .append(orderid)
                    .append(";充值状态sta=")
                    .append(sta)
                    .append(";校验值sign=")
                    .append(sign);
            if (mode_list != null && mode_list.size() > 0) {
                Object[] obj = mode_list.get(0);
                String merchant_id = obj[2] + "";
                NPhoneRechargeEntity nPhoneRecharg = new NPhoneRechargeEntity();
                nPhoneRecharg.setId(StringUtil.getId());
                nPhoneRecharg.setOrderId(orderid);
                nPhoneRecharg.setMerchantId(merchant_id);
                nPhoneRecharg.setResult(result.toString());
                nPhoneRecharg.setGameState(sta);
                nPhoneRecharg.setErrorCode("0");
                nPhoneRecharg.setSendType("2");//0充值1查询2回调
                nPhoneRecharg.setCreateDate(new Date());
                nPhoneRecharg.setReason("接口回调");
                systemService.save(nPhoneRecharg);
                String local_sign = MD5Util.strToMD5(appkey + sporder_id + orderid);//本地sign校验值
                if (sign.equals(local_sign)) {
                    NOrderEntity nOrder = systemService.getEntity(NOrderEntity.class, orderid);
                    nOrder.setGameState(sta);
                    if ("1".equals(sta)) {
                        //充值成功，根据自身业务逻辑进行后续处理
                        callSuccess(nOrder);
                    } else if ("9".equals(sta)) {
                        //充值失败,根据自身业务逻辑进行后续处理
                        systemService.saveOrUpdate(nOrder);
                    }
                }
            }
            JSONObject object = new JSONObject();
            object.put("status", "success");
            object.put("message", "获取信息成功");
            TagUtil.getSendJson(response, object.toString());
        }

        public void callSuccess (NOrderEntity nOrder){
            /*订单状态order_status0待付款1待拼单，2待发货3已发货4已签收5待评价6售后处理中
             * gatheringstatus	商家收款状态	C	32		0未收款 1冻结中2已收款、3退款*/
            String order_status = nOrder.getOrderStatus();
            String goodsId = nOrder.getGoodsId();
            nOrder.setOrderStatus("3");
            nOrder.setSendtime(new Date());
            nOrder.setGatheringstatus("2");
            systemService.saveOrUpdate(nOrder);
            if ("2".equals(order_status)) {
                final String merchantid = nOrder.getMerchantId();
                final String goods_sum = nOrder.getGoodsSum();
                Thread t1 = new Thread("t1") {
                    public void run() {
                        //商家金额
                        disposeMerchant(merchantid, goods_sum);
                    }
                };
                t1.start();
                NGoodsDetaislEntity nGoodsDetaisl =
                        systemService.getEntity(NGoodsDetaislEntity.class, goodsId);
                if (nGoodsDetaisl != null) {
                    String old_sellnumbs = nGoodsDetaisl.getSellnumbs();
                    if (StringUtil.is_khEmpty(old_sellnumbs)) {
                        old_sellnumbs = "0";
                    }
                    Integer t_sellnumbs = 1;
                    BigDecimal b1 = new BigDecimal(old_sellnumbs);
                    BigDecimal b2 = new BigDecimal(t_sellnumbs);
                    String sellnumbs = b1.add(b2).toString();
                    StringBuffer updatesql = new StringBuffer();
                    updatesql.append("update n_goods_detaisl set  ")
                            .append("sellnumbs='")
                            .append(sellnumbs)
                            .append("'")
                            .append(" where  id='")
                            .append(goodsId)
                            .append("'");
                    systemService.updateBySqlString(updatesql.toString());
                }
                final String userid = nOrder.getUserId();
                final String first = "你有一笔" + nOrder.getGoodsname() + "订单已发货！";
                final String keyword1 = nOrder.getId();
                Format f = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                final String keyword2 = f.format(nOrder.getApplytime());
                final String keyword3 = nOrder.getPaySum();
                final String keyword4 = nOrder.getGoodsname() + "-" + nOrder.getStandardname();
                final String remark = "查看更多订单信息，请点击下方【详情】";
                final String goodsname_f = nOrder.getGoodsname();
                final String standardname_f = nOrder.getStandardname();
                final String t_goods_detaisl_type = nOrder.getGoodsDetaislType();
                if (!StringUtil.is_khEmpty(userid)) {

                    Thread t2 = new Thread("t2") {
                        public void run() {
                            tuisongdictservice.getInsert("订单状态", "你的商品:" + goodsname_f + "  " + standardname_f + ";商家已经发货了", userid);
                            //	weixinservice.sendTemplate(userid, first, keyword1, keyword2, keyword3, keyword4, remark);
                            weixinservice.sendTemplate_four(t_goods_detaisl_type, userid, first, keyword1, keyword2, keyword3, keyword4, remark);
                        }
                    };
                    t2.start();
                }
            }
        }

        //进行商家金额的处理
        public void disposeMerchant (String merchantid, String goods_sum){
            /**查询商家信息*/
            NMerchantEntity nMerchant = systemService.getEntity(NMerchantEntity.class, merchantid);

            BigDecimal b1 = new BigDecimal(goods_sum);
            String actionMoney = nMerchant.getActionMoney();
            if (StringUtil.is_khEmpty(actionMoney)) {
                actionMoney = "0";
            }
            BigDecimal b2 = new BigDecimal(actionMoney);
            // String new_price= b2.subtract(b1).toString();
            String new_price = b1.add(b2).setScale(2, BigDecimal.ROUND_HALF_DOWN).toString();
            nMerchant.setActionMoney(new_price);
            systemService.saveOrUpdate(nMerchant);
        }

        @RequestMapping(params = "tuisongces")
        public void tuisongces (HttpServletRequest request,
                HttpServletResponse response, String userid,
                String goodsid, String orderid, String goodsPrice, String stand1,
                String stand2, String paySum, String goodsImg,
                String goodsTitle, String goodsSum){
            NGoodsDetaislEntity mode = systemService.getEntity(NGoodsDetaislEntity.class, goodsid);
            if (mode != null) {
                String title = mode.getTitle();
                String duanHit = mode.getDuanHit();
                String picOne = mode.getPicOne();
        if (StringUtil.is_khEmpty(duanHit)) {
                    duanHit = mode.getContents();
                }
                String urlString = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+WebUtils.APP_ID+"&redirect_uri=https%3a%2f%2fshangou.zzpt.top%2forder_doing.html%3fgoodsid%3d" + goodsid + "%26orderid%3d" + orderid + "%26goodsPrice%3d" +
                        goodsPrice + "%26userid%3d" + userid + "%26stand1%3d" + stand1 + "%26stand2%3d" + stand2 + "%26paySum%3d" + paySum +
                        "%26goodsImg%3d" + goodsImg + "%26goodsTitle%3d" + goodsTitle + "%26goodsSum%3d" + goodsSum
                        + "&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
                weixinservice.sendarticles(goodsid, userid, "new", title, duanHit, picOne, urlString);
            }
            JSONObject object = new JSONObject();
            object.put("status", "success");
            object.put("message", "获取信息成功");
            TagUtil.getSendJson(response, object.toString());
        }

        /**
         * @Author shishanshan
         * @Desprition 判断当前用户是否是会员
         * @Date 2018/8/27 15:58
         * @Param
         * @Return
         */
        @RequestMapping(params = "checkMember")
        public void checkMember (HttpServletResponse response, String userid){
            JSONObject object = new JSONObject();
            if (jodd.util.StringUtil.isNotBlank(userid)) {
                NUserEntity nu = systemService.getEntity(NUserEntity.class, userid);
                if (nu != null && "Y".equals(nu.getIsMember())) {
                    object.put("status", "Y");
                } else {
                    if(StringUtils.isNotBlank(nu.getExt3()) && "Y".equals(nu.getExt3())){
                        object.put("status", "YY");
                    }else{
                        object.put("status", "N");
                    }
                }
                if(nu != null && StringUtils.isNotBlank(nu.getPhone())){
                    object.put("phoneStatus", "Y");
                }else{
                    object.put("phoneStatus", "N");
                }
                if(nu != null && StringUtils.isNotBlank(nu.getExt4())){
                    object.put("codeStatus", "Y");
                }else{
                    object.put("codeStatus", "N");
                }
            } else {
                object.put("status", "N");
            }
            TagUtil.getSendJson(response, object.toString());
        }

        /**
         * @Author shishanshan
         * @Desprition 判断当前会员是否可以开团或者参团该商品
         * @Date 2018/9/3 19:13
         * @Param userid 人员id
         * goodsid 商品ID
         * type 1.开团 2.参团
         * @Return
         */
        @RequestMapping(params = "checkOpenOrJoinMember", method = RequestMethod.POST)
        public void checkOpenMember (HttpServletResponse response, String userid, String
        goodsid, String goodsDetaislType, String paySum, String type){
            JSONObject jsonObject = new JSONObject();
            String status = "Y";
            String message = "";
            try {
                if (StringUtils.isNotBlank(userid) && StringUtils.isNotBlank(goodsid) && StringUtils.isNotBlank(goodsDetaislType) && StringUtils.isNotBlank(paySum)) {
                    NGoodsDetaislEntity nGoodsDetaislEntity = systemService.getEntity(NGoodsDetaislEntity.class, goodsid);
                    NUserEntity nUserEntity = systemService.getEntity(NUserEntity.class, userid);
                    if (StringUtils.isNotBlank(goodsDetaislType) && "1".equals(goodsDetaislType) && ("a3f79ad19bc74e07a676c436045a3933".equals(goodsid)||"6d04d3c139b545d1916423f3392a8254".equals(goodsid) || "4188a12a151e40bdbbead4904ee0cf17".equals(goodsid)||"f363e6ac643146778223ce83f4b42a8f".equals(goodsid))) {//助力团  超级皮卡丘  只判断是否是 话费 红包三个商品
                        String sql = "select id from n_goods_join_record where user_id = '" + userid + "' and  goods_id  = '" + goodsid + "' and record_type =  '1' ";
                        List<Object> listbySql = systemService.findListbySql(sql);
                        if (listbySql != null && listbySql.size() > 0) {
                            status = "N";
                            message = "此商品已开团,不能重复开团!";
                        }//超级皮卡丘 一下注释暂时去掉 可以无限开团
                    } else if (StringUtils.isNotBlank(nGoodsDetaislEntity.getTuanprices()) && "0".equals(nGoodsDetaislEntity.getTuanprices()) && StringUtils.isNotBlank(goodsDetaislType) && "2".equals(goodsDetaislType)&&("a3f79ad19bc74e07a676c436045a3933".equals(goodsid) ||"f363e6ac643146778223ce83f4b42a8f".equals(goodsid) || "6d04d3c139b545d1916423f3392a8254".equals(goodsid) || "4188a12a151e40bdbbead4904ee0cf17".equals(goodsid))) {
                        String sql2 = "select id  from n_order  where user_id = '" + userid + "' and goods_id = '" + goodsid + "' and (joinorderstatus = '0' or joinorderstatus= '1') and goods_detaisl_type = '2' and paystatus = '0' and goods_sum = '0'";
                        List<Object> listbySql = systemService.findListbySql(sql2);
                        if (listbySql != null && listbySql.size() > 0) {
                            status = "N";
                            message = "您已参团或拼团一次,不能重复购买";
                        }
                    }else if("4188a12a151e40bdbbead4904ee0cf17".equals(goodsid)){//
                        String sql2 = "select id  from n_order  where user_id = '" + userid + "' and goods_id = '" + goodsid + "' and joinordertype = '0' and (joinorderstatus = '0' or joinorderstatus= '1') and goods_detaisl_type = '2' and paystatus = '0' ";
                        List<Object> listbySql1 = systemService.findListbySql(sql2);
                        if(listbySql1 != null && listbySql1.size() > 1){
                            status = "N";
                            message = "您已经参过一次团了，再去首页挑件跟小伙伴成团吧！";
                        }
                    } else {
                        jsonObject.put("status", "Y");  //Y标识可以开团
                    }

                } else {
                    status = "N";
                    message = "参数异常";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            jsonObject.put("status", status);
            jsonObject.put("message", message);
            TagUtil.getSendJson(response, jsonObject.toString());
        }

        /**
         * @Author shishanshan
         * @Desprition 会员签到逻辑
         * @Date 2018/9/4 16:10
         * @Param
         * @Return
         */
        @RequestMapping(params = "gotoSignIn", method = RequestMethod.POST)
        public void gotoSignIn (HttpServletResponse response, String userid){
            JSONObject jsonObject = new JSONObject();
            String status = "Y";
            String message = "签到成功";
            Date date1 = new Date();
            try {
                if (StringUtils.isNotBlank(userid)) {
                    NUserEntity nUserEntity = systemService.getEntity(NUserEntity.class, userid);
                    boolean isSameDate = false;
                    //判断用户今日是否
                    Date date2 = nUserEntity.getUpdateDate();
                    if (date2 != null) {
                        isSameDate = DateUtils.isSameDate(date1, date2);
                    }
                    if (isSameDate) {
                        status = "N";
                        message = "今天您已签到,请明天再来";
                    } else {
                        Random random = new Random();
                        int i = random.nextInt(9);
                        if (i == 0) {
                            i = 1;
                        }
                        nUserEntity.setUpdateDate(date1);
                        String ext2 = nUserEntity.getExt2();
                        if (!StringUtils.isNotBlank(ext2)) {
                            ext2 = "0";
                        }
                        int sumNum = Integer.parseInt(ext2) + i;
                        nUserEntity.setExt2(Integer.toString(sumNum));
                        systemService.saveOrUpdate(nUserEntity);
                        message += ",今日增加积分" + i + "点";
                    }
                }
            } catch (Exception e) {
                logger.error(e);
                status = "N";
                message = "签到失败";
                e.printStackTrace();
            }
            jsonObject.put("status", status);
            jsonObject.put("message", message);
            TagUtil.getSendJson(response, jsonObject.toString());
        }

        /**
         * @Author shishanshan
         * @Desprition 获取用户钱包余额及用户签到积分
         * @Date 2018/9/5 16:57
         * @Param
         * @Return
         */
        @RequestMapping(params = "getUserPriceAndPoint", method = RequestMethod.POST)
        public void getUserPriceAndPoint (HttpServletRequest request, HttpServletResponse response, String userid){
            JSONObject jsonObject = new JSONObject();
            String price = "";
            String ext2 = "";
            String totalPrice  = "";
            try {
                if (StringUtils.isNotBlank(userid)) {
                    NUserEntity nUserEntity = systemService.getEntity(NUserEntity.class, userid);
                    if (nUserEntity != null) {
                        price = nUserEntity.getPrice();
                        ext2 = nUserEntity.getExt2();
                        totalPrice = nUserEntity.getExt1();
                        String ext4 = nUserEntity.getExt4();
                        String ext5 = nUserEntity.getExt5();
                        if (!StringUtils.isNotBlank(price)) {
                            price = "0";
                        }
                        if (!StringUtils.isNotBlank(ext2)) {
                            ext2 = "0";
                        }
                        if(!StringUtils.isNotBlank(totalPrice)){
                            totalPrice = "0";
                        }
                        if(StringUtils.isNotBlank(ext4)){
                            jsonObject.put("referrelCode",ext4);
                        }
                        if(StringUtils.isNotBlank(ext5)){
                            jsonObject.put("unfreePrice",ext5);
                        }
                        jsonObject.put("phone",nUserEntity.getPhone());

                    }
                }
            } catch (Exception e) {
                logger.error(e);
                e.printStackTrace();
            }
            jsonObject.put("price", price);
            jsonObject.put("point", ext2);
            jsonObject.put("totalPrice",totalPrice);
            TagUtil.getSendJson(response, jsonObject.toString());
        }

        /**
         * @Author shishanshan
         * @Desprition 展示用户钱包信息
         * @Date 2018/9/5 15:45
         * @Param userid  用户
         *         type 1: 账单明细 2:提现记录
         * @Return
         */
        @RequestMapping(params = "showPriceDetails", method = RequestMethod.POST)
        public void showPriceDetails (HttpServletRequest request, HttpServletResponse response, String userid,String type){
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            try {
                if (StringUtils.isNotBlank(userid) && StringUtils.isNotBlank(type)) {
                    String sql = "select id,realname,DATE_FORMAT(create_date,'%Y-%m-%d') as createDate,orderid,price,update_by,update_name from n_user_price where user_id = '" + userid + "'  ";
                    if("1".equals(type)){
                        sql += " and orderid != '0'  and orderid != '01'  and orderid != '02' ";
                    }else{
                        sql += " and (orderid = '0' or orderid = '01' or orderid = '02') ";
                    }
                    sql += "order by create_date";
                    List<Object[]> listbySql = systemService.findListbySql(sql);
                    if (listbySql != null && listbySql.size() > 0) {
                        for (int i = 0; i < listbySql.size(); i++) {
                            Object[] objects = listbySql.get(i);
                            JSONObject object_mode = new JSONObject();
                            object_mode.put("id", objects[0]);
                            object_mode.put("realname", objects[1]);
                            object_mode.put("createDate", objects[2]);
                            object_mode.put("userPriceType", objects[3]);
                            object_mode.put("price", objects[4]);
                            object_mode.put("updateBy", objects[5]);
                            object_mode.put("updateName", objects[6]);
                            jsonArray.add(object_mode);
                        }
                    }
                }
            } catch (Exception e) {
                logger.error(e);
                e.printStackTrace();
            }
            jsonObject.put("array", jsonArray);
            TagUtil.getSendJson(response, jsonObject.toString());
        }
        /**
         * @Author shishanshan
         * @Desprition 用户提现接口支付宝
         * @Date 2018/9/5 20:30
         * @Param userid 用户id
         * account 用户支付账号
         * phone 手机号
         * @Return
         */
        @RequestMapping(params = "userWithdrawDeposit", method = RequestMethod.POST)
        public void userWithdrawDeposit (HttpServletRequest request, HttpServletResponse response, String userid, String account, String phone,String payeeRealName){
            JSONObject jsonObject = new JSONObject();
            String status = "Y";
            String message = "提现成功!系统将会在1-3个工作日发送到您的支付宝账号,请注意查收!";
            System.out.println("payeeRealName"+payeeRealName);
            try {
                if (StringUtils.isNotBlank(userid) && StringUtils.isNotBlank(payeeRealName)) {
                    NUserEntity userEntity = systemService.getEntity(NUserEntity.class, userid);
                    //
                    if (userEntity != null) {
                        String price = userEntity.getPrice();
                        if (StringUtils.isNotBlank(price)) {
                            //int i = Integer.parseInt(price);
                            double aDouble = Double.parseDouble(price);
                            if (aDouble > 0) {
                                //1.记录用户钱包操作信息
                                Date date = new Date();
                                NUserPriceEntity nUserPriceEntity = new NUserPriceEntity();
                                String id = StringUtil.getId();
                                nUserPriceEntity.setId(id);
                                nUserPriceEntity.setUserId(userid);
                                nUserPriceEntity.setRealname(userEntity.getRealname());
                                nUserPriceEntity.setCreateDate(date);
                                nUserPriceEntity.setUserPriceType("提现中");
                                nUserPriceEntity.setPrice(Double.toString(aDouble));
                                nUserPriceEntity.setOrderid("0");
                                systemService.save(nUserPriceEntity);
                                //2.设置用户余额为0
                                userEntity.setPrice("0");
                                systemService.save(userEntity);
                                //3.将用户提现信息添加到流水账号中
                                NMerchantAccountEntity nMerchantAccountEntity = new NMerchantAccountEntity();
                                nMerchantAccountEntity.setId(StringUtil.getId());
                                nMerchantAccountEntity.setCreateDate(date);
                                nMerchantAccountEntity.setAccountDeposit("0");
                                nMerchantAccountEntity.setAccount(account);
                                nMerchantAccountEntity.setPrice(Double.toString(aDouble));
                                nMerchantAccountEntity.setCreateBy(userid);
                                nMerchantAccountEntity.setSysOrgCode(phone);//手机号
                                nMerchantAccountEntity.setRemarks("withdrawDeposit");
                                nMerchantAccountEntity.setAccountStatusType("0");
                                nMerchantAccountEntity.setMerchantid(id);
                                nMerchantAccountEntity.setMerchantname(payeeRealName);
                                systemService.save(nMerchantAccountEntity);
                            } else {
                                message = "用户余额不足!";
                            }
                        } else {
                            message = "用户余额.提现失败,请联系客服!";
                        }
                    } else {
                        message = "用户不存在.提现失败,请联系客服!";
                    }
                } else {
                    message = "参数异常.提现失败,请联系客服!";
                }
            } catch (Exception e) {
                message = "服务器.提现失败,请联系客服!";
                logger.error(e);
                e.printStackTrace();
            }
            jsonObject.put("message", message);
            TagUtil.getSendJson(response, jsonObject.toString());
        }

        /**
         * @Author shishanshan
         * @Desprition 根据商品ID获取评价信息
         * @Date 2018/9/12 9:29
         * @Param
         * @Return
         */
        @RequestMapping(params = "getCommentInfoBygooodsId")
        public void getCommentInfoBygooodsId (HttpServletResponse response, String goodsid){
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            try {
                if (StringUtils.isNotBlank(goodsid)) {
                    String sql = "select realname,remarks from  n_evaluate  where goods_id = '" + goodsid + "' and evaluate_status = '0'  order by create_date desc";
                    List<Object[]> listbySql = systemService.findListbySql(sql);
                    if (listbySql != null && listbySql.size() > 0) {
                        for (int i = 0; i < listbySql.size(); i++) {
                            Object[] objects = listbySql.get(i);
                            JSONObject object_mode = new JSONObject();
                            object_mode.put("realname", objects[0]);
                            object_mode.put("remarks", objects[1]);
                            jsonArray.add(object_mode);
                        }
                    }
                }
            } catch (Exception e) {
                logger.error(e);
                e.printStackTrace();
            }
            jsonObject.put("array", jsonArray);
            TagUtil.getSendJson(response, jsonObject.toString());
        }

        /**
         * @Author shishanshan
         * @Desprition 获取首页评论信息
         * @Date 2018/9/12 15:12
         * @Param
         * @Return
         */
        @RequestMapping(params = "getHomeComment")
        public void getHomeComment (HttpServletResponse response){
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            try {

                String sql = "select create_name,comment from  n_comment order by RAND() ";
                List<Object[]> listbySql = systemService.findListbySql(sql);
                if (listbySql != null && listbySql.size() > 0) {
                    for (int i = 0; i < listbySql.size(); i++) {
                        Object[] objects = listbySql.get(i);
                        JSONObject mode_object = new JSONObject();
                        mode_object.put("createName", objects[0]);
                        mode_object.put("comment", objects[1]);
                        jsonArray.add(mode_object);
                    }
                }
            } catch (Exception e) {
                logger.error(e);
                e.printStackTrace();
            }
            jsonObject.put("array", jsonArray);
            TagUtil.getSendJson(response, jsonObject.toString());
        }
         /**
          * @Author shishanshan
          * @Desprition 通过userid获取自己下面的成员
          * @Date 2018/9/20 22:40
          * @Param userId:用户id
          *         selectType:查询类型 1:会员树结构  2:购物树结构
          * @Return
          */
        @RequestMapping(params = "getTeamByUserId")
        public void  getTeamByUserId(HttpServletResponse response,String userId,String selectType){
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            String status = "";
            try {
                if(StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(selectType)){
                    String sql = "select user_id,user_name,user_url,DATE_FORMAT(create_date,'%Y-%m-%d') create_date from n_user_member  where p_user_id = '"+userId+"'  and type = '"+selectType+"' ORDER BY create_date";
                    List<Object[]> listbySql = systemService.findListbySql(sql);
                    if(listbySql != null && listbySql.size()>0){
                        status = "Y";
                       for (int i = 0 ; i<listbySql.size(); i++){
                           JSONObject jsonObjectMode = new JSONObject();
                           Object[] objects = listbySql.get(i);
                           String userId1 = objects[0].toString();
                           jsonObjectMode.put("userId",userId1);
                           jsonObjectMode.put("userName",objects[1]);
                           jsonObjectMode.put("userUrl",objects[2]);
                           jsonObjectMode.put("createDate",objects[3]);
                           String sqlCount = "select id from n_user_member  where p_user_id = '"+userId1+"'  and type = '"+selectType+"'";
                           List<Object> listbySql1 = systemService.findListbySql(sqlCount);
                           if(listbySql1 != null  &&listbySql1.size()>0){
                               jsonObjectMode.put("count",listbySql1.size());
                           }else{
                               jsonObjectMode.put("count","0");
                           }
                           jsonArray.add(jsonObjectMode);
                       }
                    }else{
                        status = "002";//该用户无下级
                    }
                }else{
                    status = "001";//参数为空
                }
            }catch (Exception e){
                LogUtil.error("通过userid获取自己下面的成员异常",e);
                e.printStackTrace();
            }
            jsonObject.put("array", jsonArray);
            jsonObject.put("status",status);
            TagUtil.getSendJson(response,jsonObject.toString());
        }
         /**
          * @Author shishanshan
          * @Desprition 获取会员团员- 购物团员的数量
          * @Date 2018/11/27 10:43
          * @Param
          * @Return
          **/
        @RequestMapping(params = "getCountOfTeam",method = RequestMethod.POST)
        public void  getCountOfTeam(HttpServletResponse response,String userId){
            JSONObject jsonObject = new JSONObject();
            String status = "";
            try {
                if(StringUtils.isNotBlank(userId)){
                    status = "Y";
                    String memberTreeCountSql = "select id from n_user_member  where p_user_id = '"+userId+"'  and type = '1'";
                    List<Object> memberTreeList = systemService.findListbySql(memberTreeCountSql);
                    if(memberTreeList != null && memberTreeList.size()>0){
                        jsonObject.put("memberTreeCount",memberTreeList.size());
                    }else{
                        jsonObject.put("memberTreeCount","0");
                    }
                    String shopTreeCountSql = "select id from n_user_member  where p_user_id = '"+userId+"'  and type = '2'";
                    List<Object> shopTreeList = systemService.findListbySql(shopTreeCountSql);
                    if(shopTreeList != null && shopTreeList.size()>0){
                        jsonObject.put("shopTreeCount",shopTreeList.size());
                    }else{
                        jsonObject.put("shopTreeCount","0");
                    }
                }else{
                    status = "001";//用户id参数为空
                }
            }catch (Exception e){
                LogUtil.error("获取会员团员- 购物团员的数量",e);
            }
            jsonObject.put("status",status);
            TagUtil.getSendJson(response,jsonObject.toString());
        }
         /**
          * @Author shishanshan
          * @Desprition 赠送会员接口
          * @Date 2018/9/29 10:18
          * @Param
          * @Return
          */
    @RequestMapping(params = "sendMember")
    public void  sendMember(HttpServletResponse response,String userId){
        JSONObject jsonObject = new JSONObject();
        String message = "系统已升级,领取失败!";
        String status =  "N";
            try {
                /*if(StringUtils.isNotBlank(userId)){
                    NUserEntity nUserEntity = systemService.getEntity(NUserEntity.class, userId);
                    if (nUserEntity != null){
                        String isMember = nUserEntity.getIsMember();
                        if (StringUtils.isNotBlank(isMember) && "Y".equals(isMember)){
                            status = "N";
                            message = "您已经是金牌会员,无需领取!";
                        }else{
                            String ext3 = nUserEntity.getExt3();
                            if(StringUtils.isNotBlank(ext3) && "Y".equals(ext3)){
                                status = "N";
                                message = "您已领取,不可重复领取!";
                            }else {
                                String weixinPayId = "90106221a9db11e8beea54bf642d3cbf";
                                WeixinPayEntity weixinPayEntity = systemService.getEntity(WeixinPayEntity.class, weixinPayId);
                                String bpmStatus = weixinPayEntity.getBpmStatus();
                                int anInt = Integer.parseInt(bpmStatus);
                                String sql = "select id  from n_user  where ext3 = 'Y'";
                                List<Object> listbySql = systemService.findListbySql(sql);

                                if(listbySql!=null && listbySql.size()>anInt){
                                    status = "N";
                                    message = "银牌会员已赠送完毕,请等下次活动进行领取!";
                                }else{
                                    status = "Y";
                                    nUserEntity.setExt3("Y");
                                    systemService.saveOrUpdate(nUserEntity);
                                    NUserPriceEntity nUserPriceEntity = new NUserPriceEntity();
                                    nUserPriceEntity.setId(StringUtil.getId());
                                    nUserPriceEntity.setCreateDate(new Date());
                                    nUserPriceEntity.setUserId(nUserEntity.getId());
                                    nUserPriceEntity.setUserPriceType("银牌会员");
                                    nUserPriceEntity.setPrice("0");
                                    nUserPriceEntity.setRealname(nUserEntity.getRealname());
                                    systemService.save(nUserPriceEntity);
                                }
                            }
                        }
                    }else{
                        status = "N";
                        message = "用户信息异常";
                    }
                }else{
                    status = "N";
                    message = "用户信息异常";
                }*/
            }catch (Exception e){
                message = "用户信息异常";
                status = "N";
                logger.error(e);
                e.printStackTrace();
            }
            jsonObject.put("message",message);
            jsonObject.put("status",status);
            TagUtil.getSendJson(response,jsonObject.toString());
     }
      /**
       * @Author shishanshan
       * @Desprition 获取用户手机验证码
       * @Date 2018/10/8 14:28
       * @Param
       * @Return
       */
     @RequestMapping(params = "getPhoneCode")
     public void getPhoneCode(HttpServletResponse response,String phone){
         String status = "Y";
         String message = "获取验证码成功";
         JSONObject jsonObject = new JSONObject();
         try {
             if(StringUtils.isNotBlank(phone)){
                //1.判断该手机号是否被注册过
                 phone = StringUtils.trim(phone);
                 List<NUserEntity> nUserEntities = systemService.findByProperty(NUserEntity.class, "phone", phone);
                 if(nUserEntities!=null && nUserEntities.size()>0){
                     status = "N";
                     message = "此号码已被注册过,请使用其他手机号!";
                 }else {
                     //2.
                     String verifyCode = String.valueOf(new Random().nextInt(899999)+100000);
                     SendSmsResponse sendSmsResponse = CodeUtil.sendPhoneCode(phone, verifyCode);
                     if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")){
                         //0验证码1注册验证码，2提现验证码、3广告资讯
                         NPhoneCodeEntity nPhoneCode=new NPhoneCodeEntity();
                         String id = StringUtil.getId();
                         nPhoneCode.setId(id);
                         nPhoneCode.setCreateDate(new Date());
                         nPhoneCode.setPhone(phone);
                         nPhoneCode.setPhoneType("0");
                         nPhoneCode.setCode(verifyCode);
                         systemService.save(nPhoneCode);
                         jsonObject.put("phoneCodeId",id);
                     }else{
                         status = "N";
                        if(sendSmsResponse.getCode().equals("isv.MOBILE_NUMBER_ILLEGAL")){
                            message = "非法手机号!";
                        }else if(sendSmsResponse.getCode().equals("isv.MOBILE_COUNT_OVER_LIMIT")){
                            message = "手机号码数量超过限制!";
                        }else if(sendSmsResponse.getCode().equals("isv.BUSINESS_LIMIT_CONTROL")){
                            message = "请不要频繁获取,一分钟后再试!";
                        }else{
                            message = "获取验证码失败";
                        }
                     }
                 }
             }else{
                 status = "N";
                 message = "获取验证码失败";
             }
         }catch (Exception e){
            status = "N";
            message = "获取验证码失败";
            e.printStackTrace();
         }
         jsonObject.put("status",status);
         jsonObject.put("message",message);
         TagUtil.getSendJson(response,jsonObject.toString());
     }
      /**
       * @Author shishanshan
       * @Desprition 验证码判断
       * @Date 2018/10/8 16:02
       * @Param
       * @Return
       */
    @RequestMapping(params = "checkPhoneCode")
    public void checkPhoneCode(HttpServletResponse response,String phone,String phoneCode,String phoneCodeId,String userId){
        JSONObject jsonObject = new JSONObject();
        String status = "Y";
        String message = "身份验证成功!";
        try {
            if(StringUtils.isNotBlank(phoneCode) && StringUtils.isNotBlank(phoneCodeId) && StringUtils.isNotBlank(phone) && StringUtils.isNotBlank(userId)){
                NPhoneCodeEntity nPhoneCodeEntity = systemService.getEntity(NPhoneCodeEntity.class, phoneCodeId);
                if(nPhoneCodeEntity!=null){
                    String code = nPhoneCodeEntity.getCode();
                    String phone1 = nPhoneCodeEntity.getPhone();
                    phoneCode = StringUtils.trim(phoneCode);
                    if(phone.equals(phone1)){
                        if(code.equals(phoneCode)){
                            if(new Date().getTime()-nPhoneCodeEntity.getCreateDate().getTime()>65*1000){
                                status = "N";
                                message = "验证码超时,请重新获取!";
                            }else{
                                NUserEntity nUserEntity = systemService.getEntity(NUserEntity.class, userId);
                                if(nUserEntity!=null){
                                    nUserEntity.setPhone(phone);
                                    systemService.saveOrUpdate(nUserEntity);
                                }else{
                                    status = "N";
                                    message = "用户信息错误!";
                                }
                            }
                        }else{
                            status = "N";
                            message = "验证码错误!";
                        }
                    }else{
                        status = "N";
                        message = "手机号填写错误!";
                    }
                }else{
                    status = "N";
                    message = "身份验证失败!";
                }
            }else{
                status = "N";
                message = "参数异常!";
            }
        }catch (Exception e){
            status = "N";
            message = "身份验证失败!";
            e.printStackTrace();
        }
        jsonObject.put("status",status);
        jsonObject.put("message",message);
        TagUtil.getSendJson(response,jsonObject.toString());
    }
     /**
      * @Author shishanshan
      * @Desprition  获取推荐码
      * @Date 2018/10/12 11:57
      * @Param
      * @Return
      */
    @RequestMapping(params = "getReferrelCode")
    public void getReferrelCode(HttpServletResponse response,String userId){
        JSONObject jsonObject = new JSONObject();
        String message = "领取成功";
        String status = "Y";
        try {
            if(StringUtils.isNotBlank(userId)){
                NUserEntity nUserEntity = systemService.getEntity(NUserEntity.class, userId);
                if(nUserEntity != null){
                    String ext4 = nUserEntity.getExt4();
                    if(StringUtils.isNotBlank(ext4)){
                        status = "N";

                        message = "每人只能有一个邀请码,不能重复领取!";
                    }else{
                        int length = 6;
                        String stringForLength = StringUtil.getStringForLength(length);
                        nUserEntity.setExt4(stringForLength);
                        systemService.saveOrUpdate(nUserEntity);
                        jsonObject.put("newReferrelCode",stringForLength);
                    }
                }else{
                    status = "N";

                    message = "用户信息错误!";
                }
            }else{
                status = "N";
                message = "用户信息错误!";
            }
        }catch (Exception e){
            message = "领取失败";
            status = "N";
            e.printStackTrace();
        }
        jsonObject.put("message",message);
        jsonObject.put("status",status);
        TagUtil.getSendJson(response,jsonObject.toString());
    }
     /**
      * @Author shishanshan
      * @Desprition 检验验证码是否存在
      * @Date 2018/10/12 15:21
      * @Param
      * @Return
      */
    @RequestMapping(params = "checkReferrelCode")
    public void checkReferrelCode(HttpServletResponse response,String referrelCode){
        JSONObject jsonObject = new JSONObject();
        String message = "验证成功";
        String status = "Y";
        try {
            if(StringUtils.isNotBlank(referrelCode)){
                List<NUserEntity> entities = systemService.findByProperty(NUserEntity.class, "ext4", referrelCode);
                if(entities != null && entities.size()>0){
                }else{
                    status = "N";
                    message = "邀请码不存在,请重新输入!";
                }
            }else{
                status = "N";
                message = "邀请码不能为空!";
            }
        }catch (Exception e){
            message = "验证失败";
            status = "N";
            e.printStackTrace();
        }
        jsonObject.put("message",message);
        jsonObject.put("status",status);
        TagUtil.getSendJson(response,jsonObject.toString());
    }
     /**
      * @Author shishanshan
      * @Desprition 临时任务上传
      * @Date 2018/10/13 13:20
      * @Param
      * @Return
      */
    @RequestMapping(params = "uploadTask")
    public void uploadTask(HttpServletResponse response,String userId){
        JSONObject jsonObject = new JSONObject();
        String message = "上传失败!";
        String status = "N";
        try {
            if(StringUtils.isNotBlank(userId)){
                NUserEntity nUserEntity = systemService.getEntity(NUserEntity.class, userId);
                if(nUserEntity!=null){
                    String isMember = nUserEntity.getIsMember();
                    String ext3 = nUserEntity.getExt3();
                    if((StringUtils.isNotBlank(isMember) && "Y".equals(isMember)) || (StringUtils.isNotBlank(ext3) && "Y".equals(ext3))){
                        //判断今天是够领取奖励
                        String sql = "select date_format(create_date,'%Y-%m-%d %H:%i:%s') create_date,id from n_picture_propaganda where user_id = '"+userId+"' order by create_date desc ";
                        List<Object[]> listbySql = systemService.findListbySql(sql);
                        if(listbySql!=null && listbySql.size()>0){
                            Object[] objects = listbySql.get(0);
                            String creatDate = objects[0].toString();
                            SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date parse = datetimeFormat.parse(creatDate);
                            boolean isSameDate = DateUtils.isSameDate(parse, new Date());
                            if(isSameDate){//今日已领取
                                message = "今日已领取,请明天做完任务再来!";
                            }else{
                                status = "Y";
                                message = "上传成功!";
                                NPicturePropaganda nPicturePropaganda = new NPicturePropaganda();
                                nPicturePropaganda.setCreateDate(new Date());
                                nPicturePropaganda.setUserId(userId);
                                nPicturePropaganda.setUsernameurl(nUserEntity.getUsernameurl());
                                systemService.save(nPicturePropaganda);
                                //增加奖励金
                                String price = nUserEntity.getPrice();
                                Integer totalNumber = nUserEntity.getTotalNumber();
                                if (totalNumber == null){
                                    totalNumber = 2;
                                }
                                if(!StringUtils.isNotBlank(price)){
                                    price = "0";
                                }
                                price = CommonUtils.add(price,Integer.toString(totalNumber));
                                String userExt1 = nUserEntity.getExt1();
                                if (!StringUtils.isNotBlank(userExt1)){
                                    userExt1 = "0";
                                }
                                userExt1 = CommonUtils.add(Integer.toString(totalNumber),userExt1);
                                nUserEntity.setExt1(userExt1);
                                nUserEntity.setPrice(price);
                                systemService.saveOrUpdate(nUserEntity);
                                NUserPriceEntity userPriceEntity = new NUserPriceEntity();
                                userPriceEntity.setId(StringUtil.getId());
                                userPriceEntity.setUserId(userId);
                                userPriceEntity.setRealname(nUserEntity.getRealname());
                                userPriceEntity.setCreateDate(new Date());
                                userPriceEntity.setUserPriceType("任务奖励");
                                userPriceEntity.setPrice(Integer.toString(totalNumber));
                                userPriceEntity.setOrderid("3");
                                systemService.save(userPriceEntity);
                                weixinservice.sendTemplate_refferrer(userId,"任务奖励",Integer.toString(totalNumber),"任务奖励","",DateUtils.formatTimehsm(new Date()),"点击查看钱包详情，如有疑问直接咨询在线客服。");
                            }
                        }else{
                            status = "Y";
                            message = "上传成功!";
                            NPicturePropaganda nPicturePropaganda = new NPicturePropaganda();
                            nPicturePropaganda.setCreateDate(new Date());
                            nPicturePropaganda.setUserId(userId);
                            nPicturePropaganda.setUsernameurl(nUserEntity.getUsernameurl());
                            systemService.save(nPicturePropaganda);
                            //增加奖励金
                            String price = nUserEntity.getPrice();
                            Integer totalNumber = nUserEntity.getTotalNumber();
                            if (totalNumber == null){
                                totalNumber = 2;
                            }
                            if(!StringUtils.isNotBlank(price)){
                                price = "0";
                            }
                            price =  CommonUtils.add(price,Integer.toString(totalNumber));
                            nUserEntity.setPrice(price);
                            String userExt1 = nUserEntity.getExt1();
                            if (!StringUtils.isNotBlank(userExt1)){
                                userExt1 = "0";
                            }
                            userExt1 = CommonUtils.add(Integer.toString(totalNumber),userExt1);
                            nUserEntity.setExt1(userExt1);
                            systemService.saveOrUpdate(nUserEntity);
                            NUserPriceEntity userPriceEntity = new NUserPriceEntity();
                            userPriceEntity.setId(StringUtil.getId());
                            userPriceEntity.setUserId(userId);
                            userPriceEntity.setRealname(nUserEntity.getRealname());
                            userPriceEntity.setCreateDate(new Date());
                            userPriceEntity.setUserPriceType("任务奖励");
                            userPriceEntity.setPrice(Integer.toString(totalNumber));
                            userPriceEntity.setOrderid("3");
                            systemService.save(userPriceEntity);
                            weixinservice.sendTemplate_refferrer(userId,"任务奖励",Integer.toString(totalNumber),"任务奖励","",DateUtils.formatTimehsm(new Date()),"点击查看钱包详情，如有疑问直接咨询在线客服。");
                        }
                    }else{
                        message = "您不是VIP会员,不能上传任务!";
                    }
                }else{
                    message = "用户信息获取失败!";
                }
            }else{
                message = "用户信息获取失败!";
            }
        }catch (Exception e){
            message = "上传失败!";
            e.printStackTrace();
        }
        jsonObject.put("message",message);
        jsonObject.put("status",status);
        TagUtil.getSendJson(response,jsonObject.toString());
    }
      /**
       * @Author shishanshan
       * @Desprition 大转盘活动
       * @Date 2018/10/28 17:26
       * @Param
       * @Return
       **/
    @RequestMapping(params = "getTurntableActivityPrice")
    public void getTurntableActivityPrice(HttpServletResponse response,String userId){
        JSONObject jsonObject = new JSONObject();
        String status = "N";
        try {
            if(StringUtils.isNotBlank(userId)){
                NUserEntity nUserEntity = systemService.getEntity(NUserEntity.class, userId);
                if(nUserEntity != null){
                    String isMember = nUserEntity.getIsMember();
                    if(StringUtils.isNotBlank(isMember) && "Y".equals(isMember)){
                        String oldMemberSql = "select id from n_user_price where user_price_type = '会员充值' and create_date BETWEEN  '2018-09-17 17:29:57' and '2018-09-30 19:51:20' and user_id = '"+userId+"'";
                        List<Object> listbySql1 = systemService.findListbySql(oldMemberSql);
                        //判断当天抽奖次数
                        String sql = "select date_format(create_date,'%Y-%m-%d %H:%i:%s') create_date,id from n_turntable_activity_record where user_id = '"+userId+"' order by create_date desc ";
                        //String sql = "select create_date,id from n_turntable_activity_record where user_id = '"+userId+"' order by create_date desc ";
                        List<Object[]> listbySql = systemService.findListbySql(sql);
                        boolean flag = false;
                        if(listbySql != null && listbySql.size()>0){
                            Object[] objects = listbySql.get(0);
                            String creatDate = objects[0].toString();
                            SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date parse = datetimeFormat.parse(creatDate);
                            boolean isSameDate = DateUtils.isSameDate(parse, new Date());
                            if(isSameDate){
                                //今日已领取
                                status = "003";
                            }else{
                                flag = true;
                            }
                        }else{
                            flag = true;
                        }
                        if(listbySql1 !=null && listbySql1.size()>0){
                            //老会员参加
                            status = "004";
                            flag = false;
                        }
                        if (flag){
                            String turntableSql = "from NTurntableActivityEntity";
                            List<NTurntableActivityEntity> list = systemService.findHql(turntableSql);
                            if(list!=null && list.size()>0){
                                boolean whileFlag = true;
                                status = "Y";
                                while (whileFlag){
                                    int prizeIndex = ActiveMathRandom.getPrizeIndex(list);
                                    NTurntableActivityEntity nTurntableActivityEntity = list.get(prizeIndex);
                                    int amount = nTurntableActivityEntity.getPriceAmount();
                                    //System.out.println(nUserEntity.getRealname()+"抽中奖励"+nTurntableActivityEntity.getPriceName());
                                    if(amount>0){
                                        whileFlag = false;
                                        Date date = new Date();
                                        //System.out.println(nUserEntity.getRealname()+"抽中奖励"+nTurntableActivityEntity.getPriceName());
                                        jsonObject.put("priceName",nTurntableActivityEntity.getPriceName());
                                        jsonObject.put("priceId",nTurntableActivityEntity.getPriceId());
                                        nTurntableActivityEntity.setPriceAmount(amount-1);
                                        systemService.saveOrUpdate(nTurntableActivityEntity);
                                        //转盘活动抽奖记录
                                        NTurntableActivityRecordEntity nTurntableActivityRecordEntity = new NTurntableActivityRecordEntity();
                                        nTurntableActivityRecordEntity.setCreateDate(date);
                                        nTurntableActivityRecordEntity.setPriceId(nTurntableActivityEntity.getPriceId());
                                        nTurntableActivityRecordEntity.setPriceName(nTurntableActivityEntity.getPriceName());
                                        nTurntableActivityRecordEntity.setUserId(nUserEntity.getId());
                                        nTurntableActivityRecordEntity.setRealname(nUserEntity.getRealname());
                                        systemService.save(nTurntableActivityRecordEntity);
                                        //钱包记录
                                        NUserPriceEntity userPriceEntity = new NUserPriceEntity();
                                        userPriceEntity.setId(StringUtil.getId());
                                        userPriceEntity.setUserId(userId);
                                        userPriceEntity.setRealname(nUserEntity.getRealname());
                                        userPriceEntity.setCreateDate(date);
                                        userPriceEntity.setUserPriceType("会员大转盘");
                                        userPriceEntity.setPrice(nTurntableActivityEntity.getPriceName());
                                        userPriceEntity.setOrderid("5");
                                        systemService.save(userPriceEntity);
                                        //增加奖励金
                                        String price = nUserEntity.getPrice();
                                        if(!StringUtils.isNotBlank(price)){
                                            price = "0";
                                        }
                                        price =  CommonUtils.add(price,nTurntableActivityEntity.getPriceName());
                                        nUserEntity.setPrice(price);
                                        String userExt1 = nUserEntity.getExt1();
                                        if (!StringUtils.isNotBlank(userExt1)){
                                            userExt1 = "0";
                                        }
                                        userExt1 =CommonUtils.add(nTurntableActivityEntity.getPriceName(),userExt1);
                                        nUserEntity.setExt1(userExt1);
                                        systemService.saveOrUpdate(nUserEntity);
                                        weixinservice.sendTemplate_refferrer(userId,"会员大转盘",nTurntableActivityEntity.getPriceName(),"会员大转盘","",DateUtils.formatTimehsm(new Date()),"点击查看钱包详情，如有疑问直接咨询在线客服。");
                                    }
                                }

                            }
                        }
                    }else{
                        //非会员用户 无法进行抽奖
                        status ="002";
                    }
                }else {
                    //参数异常
                    status ="001";
                }
            }else{
                //参数异常
                status ="001";
            }
        }catch (Exception e){
            //系统异常
            status ="005";
            e.printStackTrace();
            logger.error(e);
        }
        jsonObject.put("status",status);
        TagUtil.getSendJson(response,jsonObject.toString());
    }
     /**
      * @Author shishanshan
      * @Desprition 获取大转盘中奖信息
      * @Date 2018/10/29 13:56
      * @Param
      * @Return
      **/
    @RequestMapping(params = "getTurntableActivityDetails")
    public void getTurntableActivityDetails(HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        try {
            String sql = " select realname,price_name,date_format(create_date,'%Y-%m-%d %H:%i:%s') create_date from  n_turntable_activity_record order by create_date desc limit 50 ";
            List<Object[]> listbySql = systemService.findListbySql(sql);
            if(listbySql!=null && listbySql.size()>0){
                for (int i = 0; i < listbySql.size(); i++) {
                    Object[] objects = listbySql.get(i);
                    JSONObject mode_object = new JSONObject();
                    mode_object.put("realname", objects[0]);
                    mode_object.put("priceName", objects[1]);
                    mode_object.put("createDate",objects[2]);
                    jsonArray.add(mode_object);
                }
            }
        }catch (Exception e){
            logger.error(e);
            e.printStackTrace();
        }
        jsonObject.put("array",jsonArray);
        TagUtil.getSendJson(response,jsonObject.toString());
    }

    /**
     * @Author xJiang
     * @Desprition 获取会员排行
     * @Date 2018/9/20 22:40
     * @Param
     * @Return
     */
    @RequestMapping(params = "getOrderByGold")
    public void  getOrderByGold(HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        String status = "Y";
        try {
                String sql = "select t1.p_user_id,t1.p_user_name,t1.p_user_url,COUNT(*) 'count' from n_user_member t1  where " +
                        " type = '1'  and DATE_FORMAT(t1.create_date, '%Y%m') = DATE_FORMAT(CURDATE(), '%Y%m') GROUP BY  t1.p_user_id ORDER BY count DESC LIMIT 10 ";
                List<Object[]> listbySql = systemService.findListbySql(sql);//拿取数据
                if (listbySql != null && listbySql.size() > 0) {//循环遍历
                    for (int i = 0; i < listbySql.size(); i++) {
                        Object[] objects = listbySql.get(i);
                        JSONObject mode_object = new JSONObject();
                        mode_object.put("userId", objects[0]);
                        mode_object.put("realname", objects[1]);
                        mode_object.put("usernameurl", objects[2]);
                        mode_object.put("count", objects[3]);
                        jsonArray.add(mode_object);
                    }
                }
        }catch (Exception e){
          LogUtil.error("获取会员排行异常",e);
        }
        jsonObject.put("array", jsonArray);
        jsonObject.put("status",status);
        TagUtil.getSendJson(response,jsonObject.toString());
    }
     /**
      * @Author shishanshan
      * @Desprition 层级绑定
      * @Date 2018/11/17 22:14
      * @Param
      * @Return
      **/
    @RequestMapping(params = "levelsOfbinding")
    public void levelsOfbinding(HttpServletResponse response,String upUserId,String userId){
        JSONObject jsonObject = new JSONObject();
        String status = "";
        try {
            LogUtil.info("层级绑定关系--上级:"+upUserId+";userId:"+userId);
            if(StringUtils.isNotBlank(upUserId) && StringUtils.isNotBlank(userId)){
                String checkSql = "select id from n_user_member  where user_id = '"+userId+"' and type = '2'";
                List<Object> listbySql = systemService.findListbySql(checkSql);
                if(listbySql != null && listbySql.size()>0){
                    status = "001";//该用户已绑定层级关系
                }else{
                    String sql = "select id from n_user_member  where user_id = '"+upUserId+"' and p_user_id = '"+userId+"' and type = '2'";
                    List<Object> listbySql1 = systemService.findListbySql(sql);
                    if(listbySql1 != null && listbySql1.size()>0){
                        status = "005";//该用户的下级为绑定用户
                    }else{
                        NUserEntity upUserEntity = systemService.getEntity(NUserEntity.class, upUserId);
                        NUserEntity userEntity = systemService.getEntity(NUserEntity.class, userId);
                        if(upUserEntity != null && userEntity != null){
                            if(StringUtils.isNotBlank(upUserEntity.getIsMember()) && "Y".equals(upUserEntity.getIsMember())){
                                NUserMemberEntity nUserMemberEntity = new NUserMemberEntity();
                                nUserMemberEntity.setType("2");
                                nUserMemberEntity.setCreateDate(new Date());
                                nUserMemberEntity.setUserUrl(userEntity.getUsernameurl());
                                nUserMemberEntity.setUserName(userEntity.getRealname());
                                nUserMemberEntity.setUserId(userEntity.getId());
                                nUserMemberEntity.setpUserUrl(upUserEntity.getUsernameurl());
                                nUserMemberEntity.setpUserName(upUserEntity.getRealname());
                                nUserMemberEntity.setpUserId(upUserEntity.getId());
                                systemService.save(nUserMemberEntity);
                                status = "Y";
                            }else{
                                status = "003";//上级非会员绑定失败
                            }
                        }else{
                            status = "002";//参数异常
                        }
                    }
                }
            }
        }catch (Exception e){
            LogUtil.error("层级绑定异常",e);
        }
        jsonObject.put("status",status);
        TagUtil.getSendJson(response,jsonObject.toString());
    }
     /**
      * @Author shishanshan
      * @Desprition 获取上级
      * @Date 2018/11/18 11:18
      * @Param
      * @Return
      **/
    @RequestMapping(params = "getlevelsOfbinding")
    public void getlevelsOfbinding(HttpServletResponse response,String userId){
        JSONObject jsonObject = new JSONObject();
        String status = "";
        try {
            if(StringUtils.isNotBlank(userId)){
                String sql = "select p_user_id,p_user_name,p_user_url from n_user_member where user_id = '"+userId+"' and type = '2' ";
                List<Object[]> listbySql = systemService.findListbySql(sql);
                if(listbySql != null && listbySql.size()>0){
                    Object[] objects = listbySql.get(0);
                    jsonObject.put("pUserId",objects[0]);
                    jsonObject.put("pUserName",objects[1]);
                    jsonObject.put("pUserUrl",objects[2]);
                    status = "Y";
                }else{
                    status = "002";//无上级
                }
            }else{
                status = "001";//参数为空
            }
        }catch (Exception e){
            LogUtil.error("获取上级异常",e);
        }
        jsonObject.put("status",status);
        TagUtil.getSendJson(response,jsonObject.toString());
    }
}