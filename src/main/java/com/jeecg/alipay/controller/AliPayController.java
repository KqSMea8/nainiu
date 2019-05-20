package com.jeecg.alipay.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.jeecg.commont.Utils_SSS;
import com.jeecg.n_goods_detaisl.entity.NGoodsDetaislEntity;
import com.utils.CommonUtils;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeecg.n_goods_join_record.entry.NGoodsJoinRecord;
import com.jeecg.n_user.entity.NUserEntity;
import com.jeecg.weixin_pay.entity.WeixinPayEntity;
import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.LogUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.WebUtils;
import org.jeecgframework.p3.core.utils.common.ApplicationContextUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.jeecg.n_cash_deposit.entity.NCashDepositEntity;
import com.jeecg.n_deposit_user.entity.NDepositUserEntity;
import com.jeecg.n_goods_classify.entity.NGoodsClassifyEntity;
import com.jeecg.n_merchant.entity.NMerchantEntity;
import com.jeecg.n_order.entity.NOrderEntity;
import com.jeecg.n_random.entity.NRandomEntity;
import com.jeecg.n_standard_details.entity.NStandardDetailsEntity;
import com.jeecg.n_user_coupon.entity.NUserCouponEntity;
import com.jeecg.weixin.util.WeixinService;
import sun.beans.editors.BooleanEditor;


@Controller
@RequestMapping("/aliPayController")
public class AliPayController extends BaseController{

	private SystemService systemService;


	private static final String APP_ID = "2017120100293365";
	private static final String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCPzl+qbk/hrT+QbYzDctSpWyFGZYGuLyUWsXfhyVfNX+NNUSKWaQbzyOONuT9VGxfYA6220ALvXB6rPe/DhnW8GClYXWdH7BLKg4rnzos7YSVNaXCN58sTpJgHXbjtuvhMLZUqN0HXdZwFJpL50aIgb7XepQUxUvJodStGCf5iHk1SKKiNyKY9hSvVmvFDrfQc58hdWAI3t2nCd+EuZMfcJTP7xZrazLeE9AacasZ0DE7Kujl66zkXBta3Z6Rf+wvbBCUebVDz3bV6qp6YSbg/gk+Y+2zYXvIBghM5xJFBiELdJG85hxdcngNTag1Q6zmoNhXzObv2IHAdQmCeAFvDAgMBAAECggEAZmlCF82XddA7hE9//3C3oiQT/l4rjDBm6VJZXaJVtRykc1tkllkVameWJkfWonU49c0o2Rgp/uxLqwfgyA3pqppKV3OtKbslZrNnKM4euZrlRcvhLC32oXaGDjjgieytBxMvN3FCon5PLhvab66rFw53Jqe+mvHHUDyhJK/ZSWXZuTzNdawEPQrsOD9xZb+pLLI3GdA2BSZzCR1q9zGYYvoHpQX8+NK4z/39VmUoRU7Ny0OvfL/4L7wnScYnu2irWCMjTZHjfq1tnW3/lsYlW72AXEjnaZWLg0ZHwvSQnAEHI/hy6p9P5mAbRrehrgenWW/Cg1fiX4WQCcrSC4QMgQKBgQDC6Cv4YQ2SGqy6xT7gNf+6armMhyJ+lONcci7HfZAfmP17OmAW8io0Qldm/g/W9lBvpjMU2LNKPesdWE6PMfqnn/A71HskhAngvWPLq2AMWBp/kbAmYQmp6JBKZTQCvR/SxjfG1CsbYb/L7KYR72Mp/pfAEJRnJI5JK+X3nHRLTwKBgQC84b7eD3RJ4lhvSpG5Q3j1yebGbwyeST5JQc1sEsqRJ++VewZe97tAomjyWoCBNJb+rnsPkYv7OVvW06i9aOMhQ6/M8CM58vAcSwkOZsgq+Nq2sxR96u58LejcLMj3vXGis1FIR8WA6EM4gUDoezHDIO9cicY14tD937/HZw27TQKBgHKNbjpfIFC8qMRk5V11n0V7MG6thdKLw000NtY8sBZCHsjsOEmELtXkH+aCb+DRh9j2/5LDAi0iUys+GX4Dy+P1FoazjWSazgtuhFbR9HOM3JYZlEQaSEm6TAPNk1IAwdFpeqK7VFKVktpRzhFAdzHZVmsl03MDgzTyPgjXxWn1AoGAFs8D53jiSBHHMBlHI6IcN0IcVhYO5gZeOSZzEfvq7kBuVBS5Hjq4KAP0vF9laTTajwKu5aBj0QCKMJT6qXTDCL9NuWe+OT8285O0EkMjJN1MPAfAD7yQ8/nvRrc5xYDg+g7BYAMavIhPpcEl/2zxA2k0vm38u4EaT633ULMHG3kCgYEAryCPTXg2LBtd+vFye80sHc1GkZnMtg7Gbil7skImH793eOhBr1kx49AE+wciyy+VDpyOd73vHgLfinMAZeXwUPlQyatFXEhd1nGzxUqp/7R3BnaoQRQI7VGLz5qVsXSRdAJZvSGuHWyXmYwHKpuxUImOjGrJKCpLTKedeTnEb4k=";
	private static final String CHARSET = "utf-8";
	private static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhSwkHjotgK5YM5/Q/LsiO8xfeXFvrm5iMZzXmNlaIlwMMldDzQLtlFa4UMCt3GFvWq3KgM/Sd8MFgQviGRdcQ+1n6TqVaVgYqewL0KIEI6ggDFsB15rxxnZ/PTbAdXww1MMQ2X5iE/GPy7vOnEZgC04/jjGjBNgvVX2LX+mbwbNTwtAX1tlyafSJOQ0Hp+qVi2qRuQXwmzdy73paRIDpnppx0oGmXdevlmNTwkev4I6zUEmGTi9wEudOSmY6eU87m5QxbO74L6FUlY0CBzYTWO9fCdnGz871D+Ni252SduvmBDNAexuOLC6YMWUGDUUrZxlZCj/3YgM2+g9x+E/sxwIDAQAB";
	private static final String ALIPAY_URL = "https://openapi.alipay.com/gateway.do";
	private static final String SIGN_TYPE= "RSA2";
	private static final String FORMAT = "json";
	private static AlipayTradeAppPayResponse response;
	@Resource
	private WeixinService weixinservice;
	@Resource
	private Utils_SSS utilsSss;
	private  Logger logger = Logger.getLogger(AliPayController.class);
	@ResponseBody
	@RequestMapping(params="doAppPay")
	public String doAppPay(HttpServletRequest req,HttpServletResponse resp,String orderid) {
		systemService = ApplicationContextUtil.getContext().getBean(SystemService.class);
		System.out.println("app:orderid="+orderid);
		NOrderEntity entity = systemService.getEntity(NOrderEntity.class, orderid);
		System.out.println("entity="+entity);
		//实例化客户端
		AlipayClient alipayClient = new DefaultAlipayClient(ALIPAY_URL, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY,SIGN_TYPE);
		//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		//SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		model.setBody("闪购商城");
		model.setSubject("闪购商城"+orderid);
		//请保证OutTradeNo值每次保证唯一
		model.setOutTradeNo(orderid);
		model.setTimeoutExpress("30m");
		model.setTotalAmount(entity.getPaySum());
		model.setProductCode("QUICK_MSECURITY_PAY");
		request.setBizModel(model);
		request.setNotifyUrl("https://syszz.zzpt.top/nainiupt/aliPayController/checkResult.do");
		try {
		        //这里和普通的接口调用不同，使用的是sdkExecute
		        response = alipayClient.sdkExecute(request);
		        System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
		    } catch (AlipayApiException e) {
		        e.printStackTrace();
		}
		String orderStr = response.getBody();
		return orderStr;
	}


	@ResponseBody
	@RequestMapping(params="doWapPay")
	public void doWapPay(HttpServletRequest req,HttpServletResponse resp,String orderid) {
		systemService = ApplicationContextUtil.getContext().getBean(SystemService.class);
		System.out.println("orderid="+orderid);
		NOrderEntity entity = systemService.getEntity(NOrderEntity.class, orderid);
		//实例化客户端
		AlipayClient alipayClient = new DefaultAlipayClient(ALIPAY_URL, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY,SIGN_TYPE);
		//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
		AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
		alipayRequest.setReturnUrl("https://shangou.zzpt.top/zhifubao_return.html");
		alipayRequest.setNotifyUrl("https://syszz.zzpt.top/nainiupt/aliPayController/checkResult.do");
		/*alipayRequest.setReturnUrl("http://zzz.nainiupt.com/zhifubao_return.html");
		alipayRequest.setNotifyUrl("http://zz.nainiupt.com/nainiupt/aliPayController/checkResult.do");*/
		alipayRequest.setBizContent("{" +
				" \"out_trade_no\":\""+orderid+"\"," +
				" \"total_amount\":\""+entity.getPaySum()+"\"," +
				" \"subject\":\"闪购商城"+orderid+"\"," +
				" \"product_code\":\"QUICK_WAP_PAY\"" +
				" }");
		String form="";
		try {
			form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
			resp.setContentType("text/html;charset=" + CHARSET);
			resp.getWriter().write(form);//直接将完整的表单html输出到页面
			resp.getWriter().flush();
			resp.getWriter().close();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@ResponseBody
	@RequestMapping("/checkResult")
	public void checkResult(HttpServletRequest req,HttpServletResponse resp){
		systemService = ApplicationContextUtil.getContext().getBean(SystemService.class);
		//将异步通知中收到的所有参数都存放到map中
		System.out.println("支付结果异步通知");
		Map<String,String> params = new HashMap<>();
	    Map<String,String[]> requestParams  = req.getParameterMap();
	    for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
	        String name = (String) iter.next();
	        String[] values = (String[]) requestParams.get(name);
	        String valueStr = "";
	        for (int i = 0; i < values.length; i++) {
	            valueStr = (i == values.length - 1) ? valueStr + values[i]
	                    : valueStr + values[i] + ",";
	        }
	        if(name.equals("trade_status")){
	        	System.out.println("交易状态为："+valueStr);
	        }
	        //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
	        //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
	        params.put(name, valueStr);
	    }
		try {
			boolean signVerified = AlipaySignature.rsaCheckV1(params, ALIPAY_PUBLIC_KEY, CHARSET, SIGN_TYPE); //调用SDK验证签名
			if(signVerified){
				if ("TRADE_SUCCESS".equals(params.get("trade_status"))) {
					//支付成功，改变订单支付状态
					System.out.println("验签成功时珊珊:out_trade_no="+params.get("out_trade_no"));
					if(!StringUtil.isEmpty(params.get("out_trade_no"))){
						NOrderEntity nOrder = systemService.getEntity(NOrderEntity.class, params.get("out_trade_no"));
						/*entity.setPaystatus("0");
						systemService.saveOrUpdate(entity);*/
						System.out.println("时珊珊:nOrder="+nOrder);
					    if(nOrder!=null){
							String goodsId = nOrder.getGoodsId();
							NGoodsDetaislEntity nGoodsDetaislEntity = systemService.getEntity(NGoodsDetaislEntity.class, goodsId);
							String isOnlyOne = nGoodsDetaislEntity.getIsOnlyOne();
							String isMemberGoods = nGoodsDetaislEntity.getIsMember();
							String oneLevel = nGoodsDetaislEntity.getOneLevel();
							String twoLevel = nGoodsDetaislEntity.getTwoLevel();
							String threeLevel = nGoodsDetaislEntity.getThreeLevel();
							String referralBonus = nGoodsDetaislEntity.getReferralBonus();//推荐奖励
							String isMemberUser = nOrder.getIsMemberUser();
							String isReferral = nOrder.getIsReferral();
							//用户只能参团一次时  判断当前用户是否支付过
							boolean checkFalg = true;
							if(nGoodsDetaislEntity!=null && StringUtils.isNotBlank(isOnlyOne) && "0".equals(isOnlyOne)){
								String checkSql = "select t1.id from n_order t1 LEFT JOIN n_goods_detaisl t2 on t1.goods_id = t2.id where t1.user_id = '"+nOrder.getUserId()+"' and (t1.joinorderstatus = '0' or t1.joinorderstatus= '1')  and t1.paystatus = '0' and t1.joinordertype = '1' and t2.is_only_one = '0'";
								List<Object> listbySql = systemService.findListbySql(checkSql);
								if(listbySql != null && listbySql.size()>0){
									checkFalg = false;
								}
							}
							if(checkFalg){
								String paystatus=nOrder.getPaystatus();     //	付款状态 	c	32		0 付款成功 1付款失败,
								String order_status=nOrder.getOrderStatus();//0待付款1待拼单，2待发货3已发货4已签收5已评价6售后处理中
								System.out.println("时珊珊:paystatus="+paystatus+";order_status="+paystatus);
								if("1".equals(paystatus)&&"0".equals(order_status)){
									String joinordertype = nOrder.getJoinordertype();
									String goodsDetaislType = nOrder.getGoodsDetaislType();
									//**********************  等级绑定开始  *************************//
									if("0".equals(joinordertype) && "Y".equals(isReferral) && StringUtils.isNotBlank(isMemberUser) && "Y".equals(isMemberUser)){
										nOrder.setOrderReferralBonus(referralBonus);
									}
									nOrder.setPaystatus("0");
									nOrder.setOrderStatus("1");
									systemService.saveOrUpdate(nOrder);
									//会员商品--店铺开通
									if(StringUtils.isNotBlank(nOrder.getIsMemberGoods()) && "0".equals(nOrder.getIsMemberGoods())){
										utilsSss.addMemberFlag(nOrder);
									}
									//开团信息记录
									if(StringUtils.isNotBlank(nOrder.getGoodsDetaislType()) && "1".equals(nOrder.getGoodsDetaislType())){
										saveNGoodsJoinRecord(nOrder.getUserId(),nOrder.getId(),nOrder.getGoodsId(),"1");
									}
									final String t_standardid=nOrder.getStandardid();
									final String t_standardname=nOrder.getStandardname();
									final String t_orderid=nOrder.getOrderid();
									final String t_ptPerson=nOrder.getPtPerson();
									final String t_goodsDetaislType=goodsDetaislType;
									final String t_paySum=nOrder.getPaySum();
									final String t_paystatus=paystatus;
									final String t_userId=nOrder.getUserId();
									final String t_userprice=nOrder.getUserprice();
									final String t_id=nOrder.getId();
									final Date t_today=nOrder.getApplytime();
									final Date t_tomorrow=nOrder.getEndTime();
									final Date createdate=nOrder.getCreateDate();
									final Date endtime=nOrder.getEndTime();
									final String t_realname=nOrder.getRealname();
									final String t_goodsId=nOrder.getGoodsId();
									final String t_merchantId=nOrder.getMerchantId();
									final String t_goodsname=nOrder.getGoodsname();
									final String t_joinordertype=joinordertype;
									final String t_goods_sum=nOrder.getGoodsSum();
									final String isOnlyOneFinal = nGoodsDetaislEntity.getIsOnlyOne();
									final String returnMoney = nGoodsDetaislEntity.getReturnMoney();
									final String isMemberGoodsFinal = isMemberGoods;
									final String isMemberFinal = isMemberUser;//是否会员
									final String oneLevelPriceFinal = oneLevel;//一级奖励
									final String twoLevelPriceFinal = twoLevel;//二级奖励
									final String threeLevelPriceFinal = threeLevel;//三级奖励
									Thread t1 = new Thread("t1") {
										public void run() {
											if (!"1".equals(t_goodsDetaislType)) {
												Format f = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
												if ("1".equals(t_joinordertype)) {
													//参团成功通知
													weixinservice.sendTemplate_two(t_userId, "恭喜您参团成功，请等待成团。",t_goodsname + "团购", t_goodsname, t_goods_sum, t_ptPerson, "拼团时间："+ f.format(createdate) + "-" + f.format(endtime));
													//sss 参团给团长奖励 奖励为冻结金额金额冻结
													if(StringUtils.isNotBlank(isOnlyOneFinal) && "0".equals(isOnlyOneFinal) && StringUtils.isNotBlank(returnMoney) && !"0".equals(returnMoney)){
														giveRewardToFirst(t_orderid,returnMoney);
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
											// joinordertype 0开团、1拼团，3单买
											if ("1".equals(t_joinordertype) && "2".equals(t_goodsDetaislType)) {
												getUpdateOrder(t_orderid, t_ptPerson, t_goodsDetaislType, "", t_paySum, t_standardid,isOnlyOneFinal,returnMoney);
											}
										}
									};
									t1.start();
								}
							}else {
								//支付宝退款
								getAlipay(nOrder.getId(), nOrder.getOrderid(), nOrder.getPaySum(), nOrder.getUserId(),nOrder.getGoodsname());
							}
            		    }
					}
				}
			}else{
				System.out.println("验签失败。");
			}
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}

	}

	public void  getAlipay(String id,String orderid,String paySum,String userId,String goodsname){
		AlipayClient alipayClient = new DefaultAlipayClient(ALIPAY_URL, APP_ID,APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
		AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
		request.setBizContent("{" + "\"out_trade_no\":\""+id+"\","+ "\"refund_amount\":"+paySum+""+ "}");
		AlipayTradeRefundResponse response;
		try {
			if(StringUtils.isNotBlank(paySum) && !"0".equals(paySum)){
				response = alipayClient.execute(request);
				if (response.isSuccess()) {
					//退款成功删除订单
					weixinservice.sendTemplate_six(userId, "支付失败!", "¥"+paySum+"元","退款-"+goodsname, "退回支付卡.", id, "请勿重复支付,如有问题，拨打客服");
					String sql = "delete from n_order where id ='"+id+"'";
					systemService.updateBySqlString(sql);
				}
			}
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
	}
	public void giveRewardToFirst(String t_orderid,String returnMoney) {
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
						ext5 = CommonUtils.add(returnMoney,ext5);
						nUserEntity.setExt5(ext5);
						systemService.saveOrUpdate(nUserEntity);
						//2.微信推送
						weixinservice.sendTemplate_refferrer(userId,"团长奖励",returnMoney,"红包(成团后即可提现)","", DateUtils.formatTimehsm(new Date()),"点击查看钱包详情，如有疑问直接咨询在线客服。");
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
	public void giveRewardToFirstForUnfreeze(String t_orderid,String ptPerson,String returnMoney) {
		try {
			if (StringUtils.isNotBlank(t_orderid)){
				NUserEntity nUserEntity = systemService.getEntity(NUserEntity.class, t_orderid);
				if(nUserEntity != null){
					//1.冻结金额减少
					String ext5 = nUserEntity.getExt5();//冻结金额
					if(!StringUtils.isNotBlank(ext5)){
						ext5 = "0";
					}
					String ptPrice = Integer.toString((Integer.parseInt(ptPerson)-1)*Integer.parseInt(returnMoney));
					String decre = CommonUtils.decre(ext5, ptPrice);
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
					weixinservice.sendTemplate_refferrer(t_orderid,"团长成团奖励",ptPrice,"成团解冻金额","",DateUtils.formatTimehsm(new Date()),"点击查看钱包详情，如有疑问直接咨询在线客服。");

				}            }
		}catch (Exception e){
			logger.error(e);
			e.printStackTrace();
		}
	}
	 /**
	  * @Author shishanshan
	  * @Desprition 保存会员拼团信息
	  * @Date 2018/10/30 14:43
	  * @Param
	  * @Return
	  **/
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
	@ResponseBody
	@RequestMapping(params = "getDepositForm")
	public void getDepositForm(HttpServletRequest req, HttpServletResponse resp,String id,String jingying) {
		systemService = ApplicationContextUtil.getContext().getBean(SystemService.class);
//		NDepositSetEntity entity = systemService.getEntity(NDepositSetEntity.class, id);
		String random=getRandomFileName();
		NRandomEntity nRandom=new NRandomEntity();
		nRandom.setCreateDate(new Date());
		nRandom.setRandom(random);
		nRandom.setPkid(id);
		systemService.save(nRandom);
		NGoodsClassifyEntity	nGoodsClassify = systemService.getEntity(NGoodsClassifyEntity.class,jingying);
		String deposit=nGoodsClassify.getDeposit();
		System.out.println("deposit="+deposit+"=jingying="+jingying);
		AlipayClient alipayClient = new DefaultAlipayClient(ALIPAY_URL, APP_ID,
				APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE); // 获得初始化的AlipayClient
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();// 创建API对应的request
		alipayRequest.setReturnUrl(WebUtils.ADMIN_WEB+"nainiupt/loginController.do?login");
		alipayRequest.setNotifyUrl(WebUtils.ADMIN_WEB+"nainiupt/aliPayController/checkDepResult.do");// 在公共参数中设置回跳和通知地址
	    alipayRequest.setBizContent("{" +
	            						"\"out_trade_no\":\""+random+"\"," +
	            						"\"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
	            						"\"total_amount\":\""+deposit+"\"," +
	            						"\"subject\":\"平台订金\"" +
	            					"}");//填充业务参数
		String form = "";
		try {
			form = alipayClient.pageExecute(alipayRequest).getBody(); // 调用SDK生成表单
			resp.setContentType("text/html;charset=" + CHARSET);
			resp.getWriter().write(form);// 直接将完整的表单html输出到页面
			resp.getWriter().flush();
			resp.getWriter().close();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@ResponseBody
	@RequestMapping("/checkDepResult")
	public void checkDepResult(HttpServletRequest req,HttpServletResponse resp){
		systemService = ApplicationContextUtil.getContext().getBean(SystemService.class);
		//将异步通知中收到的所有参数都存放到map中
		System.out.println("支付结果异步通知");
		Map<String,String> params = new HashMap<String,String>();
	    System.out.println("异步通知参数：");
	    Map<String,String[]> requestParams  = req.getParameterMap();
	    for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
	        String name = (String) iter.next();
	        String[] values = (String[]) requestParams.get(name);
	        String valueStr = "";
	        for (int i = 0; i < values.length; i++) {
	            valueStr = (i == values.length - 1) ? valueStr + values[i]
	                    : valueStr + values[i] + ",";
	        }
	        if(name.equals("trade_status")){
	        	System.out.println("交易状态为："+valueStr);
	        }
	        //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
	        //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
	        params.put(name, valueStr);
	    }
		try {
			boolean signVerified = AlipaySignature.rsaCheckV1(params, ALIPAY_PUBLIC_KEY, CHARSET, SIGN_TYPE); //调用SDK验证签名
			if(signVerified){
				System.out.println("验签成功。");
				if ("TRADE_SUCCESS".equals(params.get("trade_status"))) {
					//支付成功，改变订单支付状态
					if(!StringUtil.isEmpty(params.get("out_trade_no"))){
						NRandomEntity entity = systemService.findUniqueByProperty(NRandomEntity.class, "random",params.get("out_trade_no"));
						NMerchantEntity nMerchant = systemService.getEntity(NMerchantEntity.class,entity.getPkid());
						nMerchant.setDepositMoney(params.get("total_amount"));
						nMerchant.setIspay("0");//ispay	缴费	0是1否
						systemService.saveOrUpdate(nMerchant);
						NCashDepositEntity ncashdeposit=new NCashDepositEntity();
						ncashdeposit.setMerchantid(nMerchant.getId());
						ncashdeposit.setMerchantname(nMerchant.getCompany());
						ncashdeposit.setCashDepositType("0");
						ncashdeposit.setPrice(params.get("total_amount"));
						ncashdeposit.setRecharge("1");
						ncashdeposit.setCashDepositStatus("0");
						ncashdeposit.setWithdrawDeposit("1");
						ncashdeposit.setCreateDate(new Date());
						systemService.save(ncashdeposit);
					}
				}
			}else{
				System.out.println("验签失败。");
			}
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}

	}

/**提现*/
	@ResponseBody
	@RequestMapping(params="doDesTransfer")
	public void doTransfer(HttpServletRequest req,HttpServletResponse resp,String account,String id){
		systemService = ApplicationContextUtil.getContext().getBean(SystemService.class);
		NMerchantEntity entity = systemService.getEntity(NMerchantEntity.class, id);
		JSONObject json = new JSONObject();
		String amount = entity.getDepositMoney();
		AlipayClient alipayClient = new DefaultAlipayClient(ALIPAY_URL,APP_ID,APP_PRIVATE_KEY,FORMAT,CHARSET,ALIPAY_PUBLIC_KEY,SIGN_TYPE);
		AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
		/**out_biz_no  订单唯一*/
		request.setBizContent("{" +
		"    \"out_biz_no\":\""+id+"\"," +
		"    \"payee_type\":\"ALIPAY_LOGONID\"," +
		"    \"payee_account\":\""+account+"\"," +
		"    \"amount\":\""+amount+"\"," +
		"  }");
		AlipayFundTransToaccountTransferResponse response;
		try {
			response = alipayClient.execute(request);
			if(response.isSuccess()){
				System.out.println("调用成功");
				entity.setDepositMoney("0");
				entity.setIspay("1");//ispay	缴费	0是1否
				systemService.saveOrUpdate(entity);
				NCashDepositEntity ncashdeposit=new NCashDepositEntity();
				ncashdeposit.setCashDepositType("0");
				ncashdeposit.setRecharge("1");
				ncashdeposit.setCashDepositStatus("1");
				ncashdeposit.setWithdrawDeposit("2");
				ncashdeposit.setCreateDate(new Date());
				systemService.save(ncashdeposit);
				json.put("message", "转账成功，请注意查询您的支付宝。");
				TagUtil.getSendJson(resp, json.toString());
			} else {
				System.out.println("调用失败");
				json.put("message", "转账失败，请联系平台管理员。");
			}
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}

	}


	@ResponseBody
	@RequestMapping(params="doDesTransferUser")
	public void doDesTransferUser(HttpServletRequest req,HttpServletResponse resp,String id){
		systemService = ApplicationContextUtil.getContext().getBean(SystemService.class);
		NDepositUserEntity entity = systemService.getEntity(NDepositUserEntity.class, id);
		AlipayClient alipayClient = new DefaultAlipayClient(ALIPAY_URL,APP_ID,APP_PRIVATE_KEY,FORMAT,CHARSET,ALIPAY_PUBLIC_KEY,SIGN_TYPE);
		AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
		request.setBizContent("{" +
		"    \"out_biz_no\":\""+id+"\"," +
		"    \"payee_type\":\"ALIPAY_LOGONID\"," +
		"    \"payee_account\":\""+entity.getAccount()+"\"," +
		"    \"amount\":\""+entity.getAmount()+"\"," +
		"  }");
		AlipayFundTransToaccountTransferResponse response;
		try {
			response = alipayClient.execute(request);
			System.out.println(response.getBody());
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
	}


	@ResponseBody
	@RequestMapping(params="returnUrl")
	public ModelAndView returnUrl(HttpServletRequest req,HttpServletResponse resp,String orderid){
		if (StringUtil.isNotEmpty(orderid)) {
			NOrderEntity entity = systemService.getEntity(NGoodsClassifyEntity.class, orderid);
			req.setAttribute("nGoodsClassifyPage", entity);
		}
		return new ModelAndView("com/jeecg/alipay/return_url");
	}

	@ResponseBody
	@RequestMapping(params = "doRefund")
	public void refund(HttpServletRequest req, HttpServletResponse resp) {
		systemService = ApplicationContextUtil.getContext().getBean(SystemService.class);
		AlipayClient alipayClient = new DefaultAlipayClient(ALIPAY_URL, APP_ID,
				APP_PRIVATE_KEY,FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
		AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
		request.setBizContent("{" + "\"out_trade_no\":\"5187020180108\","
				+ "\"trade_no\":\"2018010821001004250574155833\","
				+ "\"refund_amount\":0.01"
				+ "}");
		AlipayTradeRefundResponse response;
		try {
			response = alipayClient.execute(request);
			if (response.isSuccess()) {
				System.out.println("调用成功");
			} else {
				System.out.println("调用失败");
			}
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
	}
	public static String getRandomFileName() {

		  SimpleDateFormat simpleDateFormat;

		  simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

		  Date date = new Date();

		  String str = simpleDateFormat.format(date);

		  Random random = new Random();

		  int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数

		  return rannum + str;
	}
	   /**修改用户优惠劵*/
		public void getUpdatecouponid(String couponid){
			 System.out.println("23424===1");
			 /**0未使用1已使用,3过期*/
			 if(!StringUtil.is_khEmpty(couponid)){
				StringBuffer updatesql=new StringBuffer();
				    updatesql.append("update n_user_coupon set couponstatus='1' where id='")
				             .append(couponid)
				             .append("'");
			   systemService.updateBySqlString(updatesql.toString());
				final String t_couponid=couponid;
			   Thread t1 = new Thread("t1"){
		            public void run(){
		            	getPayupdateptstatus(t_couponid);
		            }
		        };
		        t1.start();
			 }
		}
		/**根据优惠劵使用情况，把用户拼团的劵使用情况改变0未完成1已完成（主要用于任务团，发放的劵使用）*/
		public void getPayupdateptstatus(String couponid){
			NUserCouponEntity nusercoupon=systemService.getEntity(NUserCouponEntity.class, couponid);
			if(nusercoupon!=null){
				String norderid=nusercoupon.getNorderid();
				StringBuffer updatesql=new StringBuffer();
				 updatesql.append("update n_order set ptstatus='1' where id='")
	             .append(norderid)
	             .append("'");
		      systemService.updateBySqlString(updatesql.toString());
			}
		}

		//给用户添加优惠劵
				public void getNUserCoupon(String id,String ptorderid,Date today,Date tomorrow,String userId,String realname,String goodsId,String merchantId,String goodsname,String couponType){
					NUserCouponEntity nusercouponentity=new NUserCouponEntity();
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
					if("1".equals(couponType)){
						nusercouponentity.setCouponName("开团卷,仅限参加"+goodsname+"商品");
						nusercouponentity.setDetails("开团卷,仅限参加"+goodsname+"商品,免除开团费");
					}else{
						nusercouponentity.setCouponName("参团劵,仅限参加"+goodsname+"商品");
						nusercouponentity.setDetails("参团劵,仅限参加"+goodsname+"商品,免除参团费");
					}
					nusercouponentity.setMoney("0");
					nusercouponentity.setFullMoney("0");
					nusercouponentity.setGrantType("1");//0商家1平台
					nusercouponentity.setCouponstatus("0");//0未使用1已使用
					systemService.save(nusercouponentity);
				}

				/**修改用户关联订单状态*/
				public void getUpdateOrder (String orderid, String ptPerson, String goods_detaisl_type, String marketingOne,
											String t_paySum, String standardid,String isOnlyOneFinal,String returnMoney){
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
						/*if("0".equals(joinordertype) && StringUtils.isNotBlank(isOnlyOneFinal) && "0".equals(isOnlyOneFinal) && StringUtils.isNotBlank(returnMoney) && !"0".equals(returnMoney)){
							giveRewardToFirstForUnfreeze(user_id,ptPerson,returnMoney);
						}*/
						weixinservice.sendTemplate_seven(user_id, "恭喜您团购成功，您的团购订单已完成付款， 商家将即时为您发货",goods_sum, goodsname, area + address, id, "查看订单详情");
					}

				}


				/**根据拼团状态，修改用户订单信息 ptstatus*/
				public void getUpdateptstatus(String orderid,String ptPerson,
						String goods_detaisl_type,String marketingOne,
						String paySum,String goodsId,String standardid,String  standardname,String couponid){

					/**查看参团人数是否够*/
					/**查看拼团人数是否完成,0完成，1未完成,ptstatus 0未完成1已完成（主要用于任务团，发放的劵使用）*/
				  	/**	 joinordertype 0开团、1拼团，3单买
				  	 * goods_detaisl_type1任务，2普通
				  	 * marketing_one	订单所属活动 0正常1、限时秒杀、2、9.9特卖、3抽奖、4新品推荐、5赚客、6首页
					 *gatheringstatus	商家收款状态 0未收款 1冻结中2已收款
					 * order_status 0待付款1待拼单，2待发货3已发货4已签收5待评价6售后处理中
					 * joinorderstatus	拼单状态 0待完成，1已完成、2订单取消
					 * ptstatus	拼团状态	0未完成1已完成（主要用于任务团，发放的劵使用）
					 * paystatus	付款状态 	c	32		0 付款成功 1付款失败,
					 * */

					StringBuffer sql=new StringBuffer();
					sql.append("select paystatus,COUNT(*)as pt_numbs from n_order where ")
					   .append("  paystatus='0' and goods_detaisl_type='1' ")
					   .append(" and orderid='")
				       .append(orderid)
				       .append("'")
				       .append("   GROUP BY orderid  ");
					 List<Object[]> mode_list = systemService.findListbySql(sql.toString());
					 System.out.println("参团===="+mode_list.size()+"=ptPerson="+ptPerson);
					if(mode_list!=null && mode_list.size()>0){
						int pt_numbs=Integer.parseInt(mode_list.get(0)[1].toString());
						int t_ptPerson=Integer.parseInt(ptPerson);
						if(pt_numbs>=t_ptPerson){
							System.out.println(pt_numbs+"==pt_numbs>=t_ptPerson="+t_ptPerson);
								StringBuffer sqltwo=new StringBuffer();
								sqltwo.append("select id,couponid,payorderstatus,goods_detaisl_type  " )
								      .append(" from n_order ")
								      .append(" where  orderid='")
								      .append(orderid)
								      .append("'")
						              .append(" and paystatus='0' and goods_detaisl_type='1' ")
						              .append(" and  joinorderstatus='0' ");
								 List<Object[]> two_list = systemService.findListbySql(sqltwo.toString());

									//用户拼团成功，改变状态
								 StringBuffer sqlone=new StringBuffer();
									sqlone.append("update n_order " )
								      .append(" set  pdtime= now() ,joinorderstatus='1'")
								      .append(" where  orderid='")
								      .append(orderid)
								      .append("'")
						              .append(" and paystatus='0' and goods_detaisl_type='1' ")
						              .append(" and  joinorderstatus='0' ");
									systemService.updateBySqlString(sqlone.toString());

								 for(int i=0;i<two_list.size();i++){
									 Object[] obj=two_list.get(i);
									 String  id=obj[0]+"";
									 String two_couponid=obj[1]+"";
									 //不是空，证明用户使用优惠劵参团
									 if(!StringUtil.is_khEmpty(two_couponid)){
										 //改变拼团状态
										 //查看开团人是否已经拼完，劵的id等于开团人的id
											StringBuffer hsql=new StringBuffer();
											hsql.append("select id,orderid,marketing_one ,standardid,numbers from n_order ")
											   .append(" where id='")
											   .append(two_couponid)
											   .append("' and  paystatus='0' and joinorderstatus='1' ")
											   .append(" and goods_detaisl_type='1' and  ptstatus='1' and  order_status='1' ") ;
									         List<Object[]> hmode_list = systemService.findListbySql(hsql.toString());
									            if(hmode_list!=null && hmode_list.size()>0){
									        	 //开团商品状态改变
									 	        		StringBuffer updatesql=new StringBuffer();
									 				    updatesql.append("update n_order set order_status='2',gatheringstatus='1' where ")
									 				             .append("  paystatus='0' ")
									 				    		 .append("  and id='")
									 				             .append(two_couponid)
									 				             .append("'");
									 			          systemService.updateBySqlString(updatesql.toString());
									 			         //拼团也发货商品状态改变
									 	        		StringBuffer ptupdatesql=new StringBuffer();
									 	        		ptupdatesql.append("update n_order set order_status='2',gatheringstatus='1' where ")
									 				             .append("  paystatus='0' ")
									 				    		 .append("  and id='")
									 				             .append(id)
									 				             .append("'");
									 			          systemService.updateBySqlString(ptupdatesql.toString());
								 			        	  String hstandardid=hmode_list.get(0)[3]+"";
								 			        	  String hpsum=hmode_list.get(0)[4]+"";
									 					  	if(!StringUtil.is_khEmpty(hstandardid) && !"undefined".equals(hstandardid)){
									 							NStandardDetailsEntity	nstandarddetails = systemService.getEntity(NStandardDetailsEntity.class, standardid);
									 							if(nstandarddetails!=null){
									 								String sumNumber=nstandarddetails.getSumNumber();
									 								if(!StringUtil.is_khEmpty(sumNumber)){
									 									  BigDecimal e1 = new BigDecimal(sumNumber);
									 								      BigDecimal e2 = new BigDecimal(hpsum);
									 								      String sm=e1.subtract(e2).subtract(e2).setScale(2,BigDecimal.ROUND_HALF_DOWN).toString();
									 								      nstandarddetails.setSumNumber(sm);
									 								      systemService.saveOrUpdate(nstandarddetails);
									 								}
									 							}
									 						}
									 			          }
									         }else{
									        	 //是空，花钱参团
									        		StringBuffer hsql=new StringBuffer();
													hsql.append("select id,orderid,marketing_one ,standardid,numbers from n_order ")
													   .append(" where couponid='")
													   .append(id)
													   .append("' and paystatus='0' and joinorderstatus='1' ")
													   .append(" and goods_detaisl_type='1'  and  order_status='1' ") ;
											         List<Object[]> hmode_list = systemService.findListbySql(hsql.toString());
											            if(hmode_list!=null && hmode_list.size()>0){
											        	 //开团商品状态改变
											 	        		StringBuffer updatesql=new StringBuffer();
											 				    updatesql.append("update n_order set order_status='2',gatheringstatus='1' where ")
											 				             .append("  paystatus='0' ")
											 				    		 .append("  and couponid='")
											 				             .append(id)
											 				             .append("'");
											 			          systemService.updateBySqlString(updatesql.toString());
											 			         //拼团也发货商品状态改变
											 	        		StringBuffer ptupdatesql=new StringBuffer();
											 	        		ptupdatesql.append("update n_order set order_status='2',gatheringstatus='1' where ")
											 				             .append("  paystatus='0' ")
											 				    		 .append("  and id='")
											 				             .append(id)
											 				             .append("'");
											 			          systemService.updateBySqlString(ptupdatesql.toString());
										 			        	  String hstandardid=hmode_list.get(0)[3]+"";
										 			        	  String hpsum=hmode_list.get(0)[4]+"";
											 					  	if(!StringUtil.is_khEmpty(hstandardid) && !"undefined".equals(hstandardid)){
											 							NStandardDetailsEntity	nstandarddetails = systemService.getEntity(NStandardDetailsEntity.class, standardid);
											 							if(nstandarddetails!=null){
											 								String sumNumber=nstandarddetails.getSumNumber();
											 								if(!StringUtil.is_khEmpty(sumNumber)){
											 									  BigDecimal e1 = new BigDecimal(sumNumber);
											 								      BigDecimal e2 = new BigDecimal(hpsum);
											 								      String sm=e1.subtract(e2).subtract(e2).setScale(2,BigDecimal.ROUND_HALF_DOWN).toString();
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
}
