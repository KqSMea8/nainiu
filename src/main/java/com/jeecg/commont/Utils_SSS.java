package com.jeecg.commont;


import com.jeecg.n_order.entity.NOrderEntity;

public interface Utils_SSS {
      /**
       * @Author shishanshan
       * @Desprition 添加会员
       * @Date 2018/11/19 16:29
       * @Param
       * @Return
       **/
     void addMemberFlag(NOrderEntity nOrder);
      /**
       * @Author shishanshan
       * @Desprition 普通用户参团 给予层级奖励
       * @Date 2018/11/19 21:48
       * @Param
       * @Return
       **/
     void giveLevelPrice(String orderid, String oneLevelPriceFinal, String twoLevelPriceFinal, String threeLevelPriceFinal);
      /**
       * @Author shishanshan
       * @Desprition 获取用户上级 购物组织结构  非会员组织结构
       * @Date 2018/11/19 21:49
       * @Param
       * @Return
       **/
     String getLevelUser(String userId);
}
