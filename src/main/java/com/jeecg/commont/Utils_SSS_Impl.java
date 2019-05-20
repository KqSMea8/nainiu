package com.jeecg.commont;

import com.jeecg.n_crab.entiry.NCrabDetailsEntity;
import com.jeecg.n_crab.entiry.NCrabEntity;
import com.jeecg.n_goods_detaisl.entity.NGoodsDetaislEntity;
import com.jeecg.n_order.entity.NOrderEntity;
import com.jeecg.n_user.entity.NUserEntity;
import com.jeecg.n_user.entity.NUserMemberEntity;
import com.jeecg.n_user_price.entity.NUserPriceEntity;
import com.jeecg.weixin.util.WeixinService;
import com.jeecg.weixin_pay.entity.WeixinPayEntity;
import com.jeecg.weixin_pay.util.NWeixinPayService;
import com.utils.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.LogUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ClassName utils
 * @Desprition TODO
 * @Author shishanshan
 * @Date 2018/11/19 16:09
 * @Version 1.0
 **/
@Component
public class Utils_SSS_Impl implements Utils_SSS{
    @Resource
    private  SystemService systemService;
    @Resource
    private WeixinService weixinservice;
    @Resource
    private NWeixinPayService nweixinpayservice;
     /**
      * @Author shishanshan
      * @Desprition 增加会员标识
      * @Date 2018/11/19 16:30
      * @Param
      * @Return
      **/
    public void addMemberFlag(NOrderEntity nOrder) {
        String userId = nOrder.getUserId();
        String upUserId = nOrder.getCreateBy();
        NUserEntity entity = systemService.getEntity(NUserEntity.class, userId);
        try {
            String goodsId = nOrder.getGoodsId();
            NGoodsDetaislEntity nGoodsDetaislEntity = systemService.getEntity(NGoodsDetaislEntity.class, goodsId);
            String oneLevel = nGoodsDetaislEntity.getOneLevel();
            String twoLevel = nGoodsDetaislEntity.getTwoLevel();
            String threeLevel = nGoodsDetaislEntity.getThreeLevel();
            entity.setIsMember("Y");
            entity.setMemberStart(DateUtils.getDate());
            entity.setMemberEnd(DateUtils.getNextYear());
            systemService.saveOrUpdate(entity);
            LogUtil.info("店铺开通成功:"+userId);
            //1判断是否记录过
            String isRecord = "select id from n_user_member where user_id = '"+userId+"' and  type = '1' ";
            List<Object> list = systemService.findListbySql(isRecord);
            if( list != null && list.size()>0 ){
            }else{
                //赠送龙虾
                giveCrabForMember(nGoodsDetaislEntity.getCrabNumber(),entity,"1","1");
                // 给当前会员发送模板消息
                weixinservice.sendTemplate_member(userId,"您的VIP会员已经开通成功","店主VIP会员!",DateUtils.formatDate(entity.getMemberStart()),DateUtils.formatDate(entity.getMemberEnd()),"","感谢的您的支持");
            }
            //三级
            if(StringUtils.isNotBlank(upUserId)){
                NUserEntity user = systemService.getEntity(NUserEntity.class, upUserId);
                if (user!=null){
                    String isRecord1 = "select  id  from n_user_member  where user_id = '"+userId+"' and p_user_id = '"+upUserId+"' and type = '1'";
                    List<Object> list1 = systemService.findListbySql(isRecord1);
                    //1添加钱包记录
                    if (list1 != null  && list1.size()>0){
                    }else{
                        WeixinPayEntity weixinPayEntity = nweixinpayservice.getWeixinPayEntity();
                        NUserMemberEntity nUserMemberEntity = new NUserMemberEntity();
                        nUserMemberEntity.setUserId(userId);
                        nUserMemberEntity.setUserName(entity.getRealname());
                        nUserMemberEntity.setUserUrl(entity.getUsernameurl());
                        nUserMemberEntity.setType("1");
                        nUserMemberEntity.setpUserId(user.getId());
                        nUserMemberEntity.setpUserName(user.getRealname());
                        nUserMemberEntity.setpUserUrl(user.getUsernameurl());
                        nUserMemberEntity.setCreateDate(new Date());
                        systemService.save(nUserMemberEntity);
                        giveCrabForMember(weixinPayEntity.getSysOrgCode(),user,"2","2");
                        /*//判断推荐人是金牌还是银牌
							*//*String ext3 = user.getExt3();
							boolean flag1 = false;
							if(StringUtils.isNotBlank(ext3) && "Y".equals(ext3)){
								flag1 = true;//true 为银牌会员
							}*//*
                        NUserPriceEntity userPriceEntityRefferrer = new NUserPriceEntity();
                        userPriceEntityRefferrer.setId(StringUtil.getId());
                        userPriceEntityRefferrer.setUserId(user.getId());
                        userPriceEntityRefferrer.setRealname(user.getRealname());
                        userPriceEntityRefferrer.setCreateDate(new Date());
                        userPriceEntityRefferrer.setUserPriceType("金牌推荐奖励");
                        userPriceEntityRefferrer.setOrderid("7");
							*//*if(flag1){
								userPriceEntityRefferrer.setPrice(weixinPayEntity.getUpdateName());
							}else{*//*
                        userPriceEntityRefferrer.setPrice(weixinPayEntity.getRemarks());
                        //}
                        //被推荐人
                        userPriceEntityRefferrer.setUpdateBy(entity.getId());
                        userPriceEntityRefferrer.setUpdateName(entity.getRealname());
                        systemService.save(userPriceEntityRefferrer);*/
                        if(StringUtils.isNotBlank(threeLevel) && !"0".equals(threeLevel)){
                            addUserMoneyPrice(user, threeLevel);
                        }
                        //二级奖励
                        String twoSql = "select p_user_id from n_user_member  where user_id = '"+upUserId+"' and type = '1'";
                        List<Object> listbySql = systemService.findListbySql(twoSql);
                        if(listbySql!=null && listbySql.size()>0 ){
                            String  twoUserId = (String) listbySql.get(0);
                            if(StringUtils.isNotBlank(twoUserId)) {
                                NUserEntity nUserEntity = systemService.getEntity(NUserEntity.class, twoUserId);
                                if(StringUtils.isNotBlank(twoLevel) && !"0".equals(twoLevel)){
                                    addUserMoneyPrice(nUserEntity, twoLevel);
                                }
                                //一级奖励
                                String oneSql = "select p_user_id from n_user_member  where user_id = '"+twoUserId+"' and type = '1'";
                                List<Object> oneList = systemService.findListbySql(oneSql);
                                if(oneList != null && oneList.size()>0){
                                    String oneUserId = (String) oneList.get(0);
                                    if(StringUtils.isNotBlank(oneUserId)){
                                        NUserEntity userEntity = systemService.getEntity(NUserEntity.class, oneUserId);
                                        if(StringUtils.isNotBlank(oneLevel) && !"0".equals(oneLevel)){
                                            addUserMoneyPrice(userEntity, oneLevel);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     /**
      * @Author shishanshan
      * @Desprition 买会员商品赠送龙虾
      * @Date 2018/11/21 20:02
      * @Param giveType  1:表示冲会员 赠送   2:表示推荐会员赠送
      * @Return
      **/
    private void giveCrabForMember(String crabNumber,NUserEntity entity,String type,String giveType) {
        try {
            if(StringUtils.isNotBlank(crabNumber) && !"0".equals(crabNumber)){
                WeixinPayEntity weixinPayEntity = nweixinpayservice.getWeixinPayEntity();
                NCrabEntity nCrabEntity = systemService.findUniqueByProperty(NCrabEntity.class, "userId", entity.getId());
                Integer carbId;
                Date date = new Date();
                if (nCrabEntity != null){
                    carbId = nCrabEntity.getId();
                    String earningsType = nCrabEntity.getEarningsType();
                    if(StringUtils.isNotBlank(earningsType) && "1".equals(earningsType)){//普通虾 //更换虾的类型
                        nCrabEntity.setCrabNumber(Integer.valueOf(crabNumber));
                        nCrabEntity.setCreateDate(date);
                        nCrabEntity.setEarningsType("0");
                        String ext1 = weixinPayEntity.getExt1();
                        nCrabEntity.setRandomPrice(CommonUtils.getRandomString(ext1));
                    }else{//已有会员虾 增加数量
                        nCrabEntity.setCrabNumber(nCrabEntity.getCrabNumber()+Integer.valueOf(crabNumber));
                    }
                    systemService.saveOrUpdate(nCrabEntity);
                }else{
                    NCrabEntity nCrabEntity1 = new NCrabEntity();
                    nCrabEntity1.setCreateDate(date);
                    nCrabEntity1.setCrabNumber(Integer.valueOf(crabNumber));
                    nCrabEntity1.setEarningsType("0");//收益类型会员
                    nCrabEntity1.setCarbEarnings("0");//收益金额
                    nCrabEntity1.setUserId(entity.getId());
                    nCrabEntity1.setUserName(entity.getRealname());
                    nCrabEntity1.setUserUrl(entity.getUsernameurl());
                    nCrabEntity1.setFeedDate(date);
                    nCrabEntity1.setOpenClose("0");//螃蟹收取喂养开关 openColse 0:表示已喂养  1:标识需要喂养
                    String randomPrice = CommonUtils.getRandomString(weixinPayEntity.getExt1());
                    nCrabEntity1.setRandomPrice(randomPrice);
                    systemService.save(nCrabEntity1);
                    carbId = nCrabEntity1.getId();
                }
                NCrabDetailsEntity nCrabDetailsEntity = new NCrabDetailsEntity();
                nCrabDetailsEntity.setCrabId(carbId);
                nCrabDetailsEntity.setCrabSource(type);
                nCrabDetailsEntity.setCreateDate(date);
                nCrabDetailsEntity.setNumber(Integer.valueOf(crabNumber));
                nCrabDetailsEntity.setRecordType("0");
                systemService.save(nCrabDetailsEntity);
                //推送消息
                if("1".equals(giveType)){
                    weixinservice.sendTemplate_crab(entity.getId(),"您的海洋世界螃蟹品种发生了改变","螃蟹","螃蟹升级金螃蟹","由于您成为VIP店主，您的海洋世界系统赠送螃蟹收回,增加"+crabNumber+"只金螃蟹","","如何疑问请联系客服人员");
                }else {
                    weixinservice.sendTemplate_crab(entity.getId(),"您的海洋世界金螃蟹数量发生了变动","金螃蟹","增加","由于您推荐成功，您的海洋世界金螃蟹增加"+crabNumber+"只","","如何疑问请联系客服人员");
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
          LogUtil.error("赠送螃蟹异常",e);
        }
    }

    public boolean   addUserMoneyPrice(NUserEntity user,String priceMoney){
        boolean flag = false;
        try {
           if(user != null){
               flag = true;
               //推荐成功发送推送消息
               weixinservice.sendTemplate_refferrer(user.getId(),"店铺收入到账!",priceMoney,"店铺扩容.","",DateUtils.formatTimehsm(new Date()),"点击查看钱包详情，如有疑问直接咨询在线客服。");
               //2.添加金额增加
               String price = user.getPrice();
               if (!StringUtils.isNotBlank(price)){
                   price = "0";
               }
               price =  CommonUtils.add(priceMoney,price);
               user.setPrice(price);
               String ext1 = user.getExt1();
               if(!StringUtils.isNotBlank(ext1)){
                   ext1 = "0";
               }
               ext1 =  CommonUtils.add(priceMoney,ext1);
               user.setExt1(ext1);
               systemService.saveOrUpdate(user);
               NUserPriceEntity userPriceEntityRefferrer = new NUserPriceEntity();
               userPriceEntityRefferrer.setId(StringUtil.getId());
               userPriceEntityRefferrer.setUserId(user.getId());
               userPriceEntityRefferrer.setRealname(user.getRealname());
               userPriceEntityRefferrer.setCreateDate(new Date());
               userPriceEntityRefferrer.setUserPriceType("店铺扩容收入");
               userPriceEntityRefferrer.setPrice(priceMoney);
               userPriceEntityRefferrer.setOrderid("10");//会员充值收入
               //被推荐人
               systemService.save(userPriceEntityRefferrer);
           }
        }catch (Exception e){
            LogUtil.error("用户金额增加异常",e);
        }
        return  flag;
    }
    /**
     * 获取上一级用户id
     * @param id
     * @return
     */
    public String getLevelUser(String userId) {
        String levelUserId = "";
        try {
            String sql = "select p_user_id from n_user_member where user_id = '"+userId+"' and type = '2'";
            List<Object> listbySql = systemService.findListbySql(sql);
            if(listbySql != null && listbySql.size()>0){
                levelUserId = (String) listbySql.get(0);
            }
        }catch (Exception e){
            LogUtil.error("获取上一级用户异常",e);
        }
        return levelUserId;
    }
    /**
     * @Author shishanshan
     * @Desprition 普通用户参团 给予层级奖励
     * @Date 2018/11/16 10:03
     * @Param
     * @Return
     **/
    public void giveLevelPrice(String orderid, String oneLevelPriceFinal, String twoLevelPriceFinal, String threeLevelPriceFinal) {
        //获取购物等级
        String checkMemberSql = "select three_level_user,two_level_user,one_level_user from  n_order  where orderid = '"+orderid+"' and  joinordertype = '0' and paystatus = '0' and goods_detaisl_type = '2'";
        List<Object[]> listbySql = systemService.findListbySql(checkMemberSql);
        if(listbySql != null && listbySql.size()>0){
            Object[] objects = listbySql.get(0);
            Object object = objects[0];
            if(object != null){
                String threeLevelUser = object.toString();
                if(StringUtils.isNotBlank(threeLevelUser)){
                    if(!"1".equals(threeLevelUser)){
                        addUserPriceUnfree(threeLevelUser,threeLevelPriceFinal);
                    }
                    Object object1 = objects[1];
                    if(object1 != null){
                        String twoLevelUser = object1.toString();
                        if(StringUtils.isNotBlank(twoLevelUser)){
                            addUserPriceUnfree(twoLevelUser,twoLevelPriceFinal);
                            Object object2 = objects[2];
                            if(object2 != null){
                                String oneLevelUser = object2.toString();
                                if(StringUtils.isNotBlank(oneLevelUser)){
                                    addUserPriceUnfree(oneLevelUser,oneLevelPriceFinal);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    /**
     * @Author shishanshan
     * @Desprition 传参用户 id 金额  增加用户冻结金额
     * @Date 2018/11/16 10:15
     * @Param
     * @Return
     **/
    public void  addUserPriceUnfree(String userId,String price){
        try {
            NUserEntity userEntity = systemService.getEntity(NUserEntity.class,userId);
            if(userEntity!=null){
                String ext5 = userEntity.getExt5();
                if(!StringUtils.isNotBlank(ext5)){
                    ext5 = "0";
                }
                ext5 = CommonUtils.add(price,ext5);
                userEntity.setExt5(ext5);
                systemService.saveOrUpdate(userEntity);
                NUserPriceEntity userPriceEntityRefferrer = new NUserPriceEntity();
                userPriceEntityRefferrer.setId(StringUtil.getId());
                userPriceEntityRefferrer.setUserId(userEntity.getId());
                userPriceEntityRefferrer.setRealname(userEntity.getRealname());
                userPriceEntityRefferrer.setCreateDate(new Date());
                userPriceEntityRefferrer.setUserPriceType("店铺收入");
                userPriceEntityRefferrer.setPrice(price);
                userPriceEntityRefferrer.setOrderid("9");//店铺收入冻结金额
                //被推荐人
                systemService.save(userPriceEntityRefferrer);
                //2.微信推送
                weixinservice.sendTemplate_refferrer(userId,"店铺收入(冻结金额),用户签收后,金额自动解冻!",price+"元","店铺收入","",DateUtils.formatTimehsm(new Date()),"点击查看钱包详情，如有疑问直接咨询在线客服。");
            }
        }catch (Exception e){
            LogUtil.error("用户金额增加异常",e);
        }
    }
}
