package com.jeecg.jiekou.controller;

import com.alibaba.fastjson.JSONObject;
import com.jeecg.n_crab.entiry.NCrabDetailsEntity;
import com.jeecg.n_crab.entiry.NCrabEntity;
import com.jeecg.n_user.entity.NUserEntity;
import com.jeecg.weixin.util.WeixinService;
import com.jeecg.weixin_pay.entity.WeixinPayEntity;
import com.jeecg.weixin_pay.util.NWeixinPayService;
import com.utils.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.LogUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @ClassName NewJsonController
 * @Desprition TODO
 * @Author shishanshan
 * @Date 2018/11/21 21:49
 * @Version 1.0
 **/
@Controller
@RequestMapping(value = "/newJsonController")
public class NewJsonController {
    @Resource
    private SystemService systemService;
    @Resource
    private WeixinService weixinservice;
    @Resource
    private NWeixinPayService nweixinpayservice;
     /**
      * @Author shishanshan
      * @Desprition 获取体验(免费螃蟹)
      * @Date 2018/11/21 22:37
      * @Param
      * @Return
      **/
    @RequestMapping(params = "getFreeCrab",method = RequestMethod.POST)
    public  void getFreeCrab(HttpServletResponse response,String userId){
        JSONObject jsonObject = new JSONObject();
        String status = "";
        try {
            if(StringUtils.isNotBlank(userId)){
                NCrabEntity nCrabEntity1 = systemService.findUniqueByProperty(NCrabEntity.class, "userId", userId);
                if(nCrabEntity1 != null){
                    status = "002";//已经有螃蟹
                }else{
                    status = "Y";
                    NUserEntity nUserEntity = systemService.getEntity(NUserEntity.class, userId);
                    Date date = new Date();
                    NCrabEntity nCrabEntity = new NCrabEntity();
                    nCrabEntity.setUserUrl(nUserEntity.getUsernameurl());
                    nCrabEntity.setUserId(userId);
                    nCrabEntity.setUserName(nUserEntity.getRealname());
                    nCrabEntity.setCarbEarnings("0");
                    nCrabEntity.setCreateDate(date);
                    nCrabEntity.setEarningsType("1");//收益类型 0会员 1普通
                    nCrabEntity.setOpenClose("1"); // 螃蟹收取喂养开关 openColse 0:表示已喂养  1:标识需要喂养
                    nCrabEntity.setCrabNumber(1);
                    systemService.save(nCrabEntity);
                    Integer crabId= nCrabEntity.getId();
                    NCrabDetailsEntity nCrabDetailsEntity = new NCrabDetailsEntity();
                    nCrabDetailsEntity.setCrabId(crabId);
                    nCrabDetailsEntity.setRecordType("0");//记录类型 0: 螃蟹来源 1:螃蟹收益
                    nCrabDetailsEntity.setCrabSource("0");////螃蟹来源 "0" :系统赠送, "1" 购买会员赠送,
                    nCrabDetailsEntity.setNumber(1);
                    nCrabDetailsEntity.setCreateDate(date);
                    systemService.save(nCrabDetailsEntity);
                    //推送消息
                    weixinservice.sendTemplate_crab(userId,"恭喜您领取了一只螃蟹","螃蟹","增加","您好,为庆祝双12的来临,特为您送来海鲜螃蟹一只,请开始喂养吧!","","如何疑问请联系客服人员");
                }
            }else{
                status = "001";//参数异常
            }
        }catch (Exception e){
            e.printStackTrace();
            LogUtil.error("获取体验(免费螃蟹)异常",e);
        }
        jsonObject.put("status",status);
        TagUtil.getSendJson(response,jsonObject.toString());
    }
     /**
      * @Author shishanshan
      * @Desprition 更新收益时间
      * @Date 2018/11/21 23:03
      * @Param
      * @Return
      **/
    @RequestMapping(params = "updateCollectionDate",method = RequestMethod.POST)
    public  void updateCollectionDate(HttpServletResponse response,String userId){
        JSONObject jsonObject = new JSONObject();
        String status = "";
        try {
            if(StringUtils.isNotBlank(userId)){
                NCrabEntity nCrabEntity = systemService.findUniqueByProperty(NCrabEntity.class, "userId", userId);
                if(nCrabEntity != null){
                    Date feedDate = nCrabEntity.getFeedDate();
                    boolean gtOneDay = DateUtils.isGtOneDay(feedDate);
                    if(gtOneDay){
                        String openClose = nCrabEntity.getOpenClose();
                        if("0".equals(openClose)){//螃蟹收取喂养开关 openColse  0:表示已喂养  1:标识需要喂养
                            status = "Y";
                            Integer crabNumber = nCrabEntity.getCrabNumber();
                            String earnings = nCrabEntity.getRandomPrice();
                            double v = crabNumber * Double.valueOf(earnings);
                            earnings = Double.toString(v);
                            //1.修改螃蟹状态
                            Date date = new Date();
                            nCrabEntity.setOpenClose("1");//螃蟹收取喂养开关 openColse  0:表示已喂养  1:标识需要喂养
                            nCrabEntity.setCollectDate(date);
                            String crabTotalEarnings = CommonUtils.isZeroForBlank(nCrabEntity.getCrabTotalEarnings());
                            nCrabEntity.setCrabTotalEarnings(CommonUtils.add(earnings,crabTotalEarnings));
                            nCrabEntity.setCarbEarnings(earnings);
                            systemService.saveOrUpdate(nCrabEntity);
                            //2.记录螃蟹详情
                            NCrabDetailsEntity nCrabDetailsEntity = new NCrabDetailsEntity();
                            nCrabDetailsEntity.setRecordType("1");
                            nCrabDetailsEntity.setTodayEarnings(earnings);
                            nCrabDetailsEntity.setCreateDate(date);
                            nCrabDetailsEntity.setCrabId(nCrabEntity.getId());
                            systemService.saveOrUpdate(nCrabDetailsEntity);
                            //3.用户金额添加
                            NUserEntity userEntity = systemService.getEntity(NUserEntity.class, userId);
                            String price = CommonUtils.isZeroForBlank(userEntity.getPrice());
                            String ext5 = CommonUtils.isZeroForBlank(userEntity.getExt5());
                            userEntity.setPrice(CommonUtils.add(price,earnings));
                            userEntity.setExt5(CommonUtils.add(ext5,earnings));
                            //4.推送消息
                            weixinservice.sendTemplate_refferrer(userId,"螃蟹收益提醒!",earnings+"元","螃蟹产出收益","",DateUtils.formatTimehsm(new Date()),"点击查看钱包详情，如有疑问直接咨询在线客服。");
                        }else{
                            status = "004";//螃蟹没有喂养.
                        }
                    }else {
                        status = "003";//收益时间还未到
                    }
                }else{
                    status = "002";//该用户没有螃蟹
                }
            }else{
                status = "001";//userId为空
            }
        }catch (Exception e){
            e.printStackTrace();
            LogUtil.error("更新收益时间异常",e);
        }
        jsonObject.put("status",status);
        TagUtil.getSendJson(response,jsonObject.toString());
    }
     /**
      * @Author shishanshan
      * @Desprition 更新养殖时间
      * @Date 2018/11/21 23:04
      * @Param
      * @Return
      **/
    @RequestMapping(params = "updateFeedDate",method = RequestMethod.POST)
    public  void updateFeedDate(HttpServletResponse response,String userId){
        JSONObject jsonObject = new JSONObject();
        String status = "";
        try {
            if(StringUtils.isNotBlank(userId)){
                NCrabEntity nCrabEntity = systemService.findUniqueByProperty(NCrabEntity.class, "userId", userId);
                if(nCrabEntity != null){
                    String openClose = nCrabEntity.getOpenClose();//螃蟹收取喂养开关 openColse  0:表示已喂养  1:表示需要喂养
                    if("1".equals(openClose)){
                        status = "Y";
                        WeixinPayEntity weixinPayEntity = nweixinpayservice.getWeixinPayEntity();
                        //1.修改螃蟹状态
                        Date date = new Date();
                        nCrabEntity.setFeedDate(date);
                        nCrabEntity.setOpenClose("0");
                        String randomPrice = "0";
                        if("0".equals(nCrabEntity.getEarningsType())){
                            randomPrice = CommonUtils.getRandomString(weixinPayEntity.getExt1());
                        }else{
                            randomPrice = CommonUtils.getRandomString(weixinPayEntity.getExt2());
                        }
                        nCrabEntity.setRandomPrice(randomPrice);
                        systemService.saveOrUpdate(nCrabEntity);
                        //2.记录螃蟹详情
                        NCrabDetailsEntity nCrabDetailsEntity = new NCrabDetailsEntity();
                        nCrabDetailsEntity.setCrabId(nCrabEntity.getId());
                        nCrabDetailsEntity.setCreateDate(date);
                        nCrabDetailsEntity.setRecordType("2");
                        systemService.save(nCrabDetailsEntity);
                    }else{
                        status = "003";//螃蟹已喂养
                    }
                }else{
                    status = "002";//该用户没有螃蟹
                }
            }else{
                status = "001";//userId为空
            }
        }catch (Exception e){
            e.printStackTrace();
            LogUtil.error("更新养殖时间异常",e);
        }
        jsonObject.put("status",status);
        TagUtil.getSendJson(response,jsonObject.toString());
    }
     /**
      * @Author shishanshan
      * @Desprition 获取饲养螃蟹状态
      * @Date 2018/11/21 23:06
      * @Param
      * @Return
      **/
    @RequestMapping(params = "getCrabStatusByUserId")
    public  void get(HttpServletResponse response,String userId){
        JSONObject jsonObject = new JSONObject();
        String status = "";
        try {
            if(StringUtils.isNotBlank(userId)) {
                NCrabEntity nCrabEntity = systemService.findUniqueByProperty(NCrabEntity.class, "userId", userId);
                if(nCrabEntity != null){
                    status = "Y";
                    WeixinPayEntity weixinPayEntity = nweixinpayservice.getWeixinPayEntity();
                    jsonObject.put("crabNumber",nCrabEntity.getCrabNumber());
                    jsonObject.put("openClose",nCrabEntity.getOpenClose());//螃蟹收取喂养开关 openColse 0:表示已喂养  1:标识需要喂养
                    jsonObject.put("userId",nCrabEntity.getUserId());
                    jsonObject.put("userName",nCrabEntity.getUserName());
                    jsonObject.put("userUrl",nCrabEntity.getUserUrl());
                    jsonObject.put("feedDate",nCrabEntity.getFeedDate());
                    Date feedDate = nCrabEntity.getFeedDate();
                    Date tomorrowDate = DateUtils.getTomorrowDate(feedDate);
                    jsonObject.put("remainDate",tomorrowDate.getTime()-new Date().getTime());
                    String earningsType = nCrabEntity.getEarningsType();
                    jsonObject.put("earningsType",earningsType);
                    jsonObject.put("earningsPrice",nCrabEntity.getRandomPrice());
                    jsonObject.put("crabEarnings",nCrabEntity.getCarbEarnings());//今日收益
                    jsonObject.put("crabTotalEarnings",nCrabEntity.getCrabTotalEarnings());//总收益
                }else{
                    status = "002";//该用户没有螃蟹
                }
            }else{
                status = "001";//userId为空
            }
        }catch (Exception e){
            e.printStackTrace();
            LogUtil.error("获取剩余时间异常",e);
        }
        jsonObject.put("status",status);
        TagUtil.getSendJson(response,jsonObject.toString());
    }
}
