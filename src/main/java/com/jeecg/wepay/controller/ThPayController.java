package com.jeecg.wepay.controller;

import java.io.*;
import java.math.BigDecimal;
import java.security.KeyStore;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeecg.commont.Utils_SSS;
import com.jeecg.n_goods_detaisl.entity.NGoodsDetaislEntity;
import com.jeecg.n_goods_join_record.entry.NGoodsJoinRecord;
import com.jeecg.n_user.entity.NUserEntity;
import com.jeecg.n_user.entity.NUserMemberEntity;
import com.jeecg.n_user_price.entity.NUserPriceEntity;
import com.jeecg.wepay.util.http.HttpClientConnectionManager;
import com.jeecg.wepay.util.payUtils.WXPayUtil;
import com.utils.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.LogUtil;
import org.jeecgframework.core.util.NOrderUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecg.n_order.entity.NOrderEntity;
import com.jeecg.n_standard_details.entity.NStandardDetailsEntity;
import com.jeecg.n_user_coupon.entity.NUserCouponEntity;
import com.jeecg.weixin.HttpClientUtil;
import com.jeecg.weixin.util.WeixinService;
import com.jeecg.weixin.util.WeixinUtil;
import com.jeecg.weixin_pay.entity.WeixinPayEntity;
import com.jeecg.weixin_pay.util.NWeixinPayService;
import com.jeecg.wepay.util.GetWxOrderno;
import com.jeecg.wepay.util.RequestHandler;
import com.jeecg.wepay.util.Sha1Util;
import com.jeecg.wepay.util.TenpayUtil;


/**
 * @author ex_yangxiaoyi
 *
 */
@Controller
@RequestMapping("/thPayController")
public class ThPayController extends BaseController {
/*	//微信支付商户开通后 微信会提供appid和appsecret和商户号partner
	private static String appid = "wx8d5f593b39c52c6f";
	private static String appsecret = "f0bc2d092fdc5b89b3ac7362b2105156";
	//private static String partner = "1493251372";
	//这个参数partnerkey是在商户后台配置的一个32位的key,微信商户平台-账户设置-安全设置-api安全
	private static String partnerkey = "yaowei1987yaowei1987yaowei1987ya";
	//openId 是微信用户针对公众号的标识，授权的部分这里不解释
	private static String openId = "";
	//微信支付成功后通知地址 必须要求80端口并且地址不能带参数
	private static String notifyurl = "http://admin.nainiupt.com/nainiupt/thPayController/notify.do";// Key
//	private static String notifyurl = "http://shop.nainiupt.com/nainiupt/thPayController/notify.do";// Key

//微信另一个商户号
//	private static String two_partner = "1495222432";
	//private static String two_appid = "wx8d5f593b39c52c6f";
	//第三个1510630121
	private static String two_partner = "1510630121";
//	private static String appid = "wx6319f129c69fb18d";
//	private static String appsecret = "878bc52abb9b5d92034ceb9d833f706d";


	*/

	@Autowired
	private SystemService systemService;
	@Autowired
	private WeixinService weixinservice;
	@Autowired
	private NWeixinPayService nweixinpayservice;
	@Autowired
	private WeixinUtil weixinutil;
	@Resource
	private Utils_SSS utilsSss;
	private static final Logger logger = Logger.getLogger(ThPayController.class);
	/**
	 * 用户支付
	 * @param req
	 * @param resp
	 */
	@RequestMapping(params = "doPay")
	@ResponseBody
	public void doPay(HttpServletRequest req,HttpServletResponse resp,String openid,String orderid){
		//	systemService = ApplicationContextUtil.getContext().getBean(SystemService.class);
		LogUtil.info("时珊珊打印openid="+openid+"orderid;"+orderid);
		JSONObject obj = null;
		try {
			NOrderEntity entity = systemService.getEntity(NOrderEntity.class, orderid);
			//System.out.println(entity.toString());
			WxPayDto tpWxPay = new WxPayDto();
			tpWxPay.setOpenId(openid);
			tpWxPay.setBody("闪购商城");
			tpWxPay.setOrderId(orderid);
			tpWxPay.setSpbillCreateIp("127.0.0.1");
			tpWxPay.setTotalFee(entity.getPaySum());
			//测试使用数据
			//tpWxPay.setTotalFee("0.01");
			obj = getPackage(tpWxPay);
		} catch (Exception e) {
			e.printStackTrace();
		}
		TagUtil.getSendJson(resp, obj.toString());
	}
	/**
	 * @Author shishanshan
	 * @Desprition 会员充值接口
	 * @Date 2018/8/30 0:10
	 * @Param
	 * @Return
	 */
	@RequestMapping(params = "doPayForMember")
	@ResponseBody
	public void doPayForMember(HttpServletRequest req,HttpServletResponse resp,String userid,String referrerUserid){
		//	systemService = ApplicationContextUtil.getContext().getBean(SystemService.class);

		JSONObject obj = null;
		LogUtil.info("时珊珊打印referrerUserid="+referrerUserid);
		/*try {
			if(StringUtils.isNotBlank(referrerUserid)){
				NUserEntity nUserEntity = systemService.getEntity(NUserEntity.class, referrerUserid);
				if(nUserEntity !=null){
					String isMember = nUserEntity.getIsMember();
					if(StringUtils.isNotBlank(isMember) && "Y".equals(isMember)){
					}else{
						referrerUserid="";
					}
				}else{
					referrerUserid="";
				}
			}
			//1.获取用户信息
			NUserEntity userEntity = systemService.getEntity(NUserEntity.class,userid);
			//2.生成虚拟
			String orderId = StringUtil.getId();
			WeixinPayEntity weixinPayEntity = nweixinpayservice.getWeixinPayEntity();
			NOrderEntity nOrderEntity = NOrderUtils.getNOrderEntiry(userid,orderId,referrerUserid,userEntity,weixinPayEntity.getSysOrgCode(),"1");
			systemService.save(nOrderEntity);
			WxPayDto tpWxPay = new WxPayDto();
			tpWxPay.setOpenId(userEntity.getOpenid());
			tpWxPay.setBody("VIP礼包购买");
			tpWxPay.setOrderId(orderId);
			tpWxPay.setSpbillCreateIp("127.0.0.1");
			//tpWxPay.setTotalFee(entity.getPaySum());
            tpWxPay.setTotalFee(weixinPayEntity.getSysOrgCode());
			obj = getPackage(tpWxPay);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		TagUtil.getSendJson(resp, obj.toString());
	}
	/**
	 * @Author shishanshan
	 * @Desprition 银牌会员充值接口
	 * @Date 2018/8/30 0:10
	 * @Param
	 * @Return
	 */
	@RequestMapping(params = "doPayForSliverMember")
	@ResponseBody
	public void doPayForSliverMember(HttpServletRequest req,HttpServletResponse resp,String userid,String referrerUserid){
		JSONObject obj = null;
		LogUtil.info("银牌referrerUserid="+referrerUserid);
		try {
			/*if(StringUtils.isNotBlank(referrerUserid)){
				//1.获取当前用户信息
                    NUserEntity nUserEntity = systemService.getEntity(NUserEntity.class, referrerUserid);
                    if(nUserEntity !=null){
                        String ext3 = nUserEntity.getExt3();
                        if(StringUtils.isNotBlank(ext3) && "Y".equals(ext3)){
                        }else{
                            referrerUserid="";
                        }
                    }else{
                        referrerUserid="";
                 	}
			    }
				//2.生成虚拟
				NUserEntity userEntity = systemService.getEntity(NUserEntity.class, userid);
				String orderId = StringUtil.getId();
				String weixinPayId = "90106221a9db11e8beea54bf642d3cbf";
				WeixinPayEntity weixinPayEntity = systemService.get(WeixinPayEntity.class, weixinPayId);
				NOrderEntity nOrderEntity = NOrderUtils.getNOrderEntiry(userid,orderId,referrerUserid,userEntity,weixinPayEntity.getExt1(),"2");
				systemService.save(nOrderEntity);
				WxPayDto tpWxPay = new WxPayDto();
				tpWxPay.setOpenId(userEntity.getOpenid());
				tpWxPay.setBody("银牌VIP保证金缴纳");
				tpWxPay.setOrderId(orderId);
				tpWxPay.setSpbillCreateIp("127.0.0.1");
				//tpWxPay.setTotalFee(entity.getPaySum());
				tpWxPay.setTotalFee(weixinPayEntity.getExt1());
				obj = getPackage(tpWxPay);*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		TagUtil.getSendJson(resp, obj.toString());
	}
	@RequestMapping(params="getOpenId")
	public String getOpenId(String code,HttpServletRequest req,HttpServletResponse resp) {
		//WeixinPayEntity  weixinpayentity=nweixinpayservice.getWeH();
		String weixinPayId = "90106221a9db11e8beea54bf642d3cbf";
		WeixinPayEntity weixinpayentity = systemService.get(WeixinPayEntity.class, weixinPayId);
		String appid=null;
		String appsecret=null;
		if(weixinpayentity!=null){
			appid=weixinpayentity.getAppid();
			appsecret=weixinpayentity.getAppsecret();
		}
		String openid = "";
		String result1 = HttpClientUtil.getHTTP("https://api.weixin.qq.com/sns/oauth2/access_token?"+ "appid="+appid+"&secret="+appsecret+"&code="+code+"&grant_type=authorization_code");
		org.json.JSONObject obj1 = new JSONObject(result1);
       //System.out.println("obj1="+obj1.toString());
        //openid = obj1.get("openid").toString();
		TagUtil.getSendJson(resp, obj1.toString());
		return openid;
	}

	@RequestMapping(params="getUserInfo")
	public String getUserInfo(String openid,String access_token,HttpServletRequest req,HttpServletResponse resp) {

		String result1 = HttpClientUtil.getHTTP("https://api.weixin.qq.com/sns/userinfo?"
				+ "access_token="+access_token+"&openid="+openid+"&lang=en");
		System.out.println("0===="+result1);
		org.json.JSONObject obj1 = new JSONObject(result1);
		TagUtil.getSendJson(resp, obj1.toString());
		return openid;
	}

	@RequestMapping(params="isFocus")
	public void isFocus(String openid,HttpServletRequest req,HttpServletResponse resp) {
		//systemService = ApplicationContextUtil.getContext().getBean(SystemService.class);
		/*String result = HttpClientUtil.getHTTP("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+appsecret);
		System.out.println("isFocus="+result);
		org.json.JSONObject obj = new JSONObject(result); */
		String access_token=weixinutil.getAccessToken().getAccessToken();
		String result1 = HttpClientUtil.getHTTP("https://api.weixin.qq.com/cgi-bin/user/info?access_token="+access_token+"&openid="+openid+"&lang=zh_CN");
		org.json.JSONObject obj1 = new JSONObject(result1);
		TagUtil.getSendJson(resp, obj1.toString());
	}

	/**
	 * 获取请求预支付id报文
	 * @return
	 */
	@SuppressWarnings("static-access")
	public  JSONObject getPackage(WxPayDto tpWxPayDto) {
		//WeixinPayEntity  weixinpayentity=nweixinpayservice.getWeH();
		WeixinPayEntity weixinpayentity = systemService.get(WeixinPayEntity.class, "90106221a9db11e8beea54bf642d3cbf");
		String appid=null;
		String appsecret=null;
		String partner=null;
		String partnerkey=null;
		String notifyurl=null;
		if(weixinpayentity!=null){
			appid=weixinpayentity.getAppid();
			appsecret=weixinpayentity.getAppsecret();
			partner=weixinpayentity.getPartner();
			partnerkey=weixinpayentity.getPartnerkey();
			notifyurl=weixinpayentity.getNotifyurl();
		}
		String timestamp="";
		String nonce_str="";
		String packages="";
		String finalsign="";
		try {
			String openId = tpWxPayDto.getOpenId();
			// 1 参数
			// 订单号
			String orderId = tpWxPayDto.getOrderId();
			// 附加数据 原样返回
			//String attach = "";
			// 总金额以分为单位，不带小数点
			LogUtil.info("总金额:"+tpWxPayDto.getTotalFee());
			String totalFee = getMoney(tpWxPayDto.getTotalFee());
			// 订单生成的机器 IP
			String spbill_create_ip = tpWxPayDto.getSpbillCreateIp();
			// 这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
			String notify_url = notifyurl;
			String trade_type = "JSAPI";

			// ---必须参数
			// 商户号
			String mch_id = partner;
			// 随机字符串
			nonce_str = getNonceStr();

			// 商品描述根据情况修改
			String body = tpWxPayDto.getBody();

			// 商户订单号
			String out_trade_no = orderId;

			SortedMap<String, String> packageParams = new TreeMap<>();
			packageParams.put("appid", appid);
			packageParams.put("body", body);
			packageParams.put("mch_id", mch_id);
			packageParams.put("nonce_str", nonce_str);
			packageParams.put("notify_url", notify_url);
			packageParams.put("out_trade_no", out_trade_no);

			// 这里写的金额为1 分到时修改
			packageParams.put("openid", openId);
			packageParams.put("spbill_create_ip", spbill_create_ip);
			packageParams.put("total_fee", totalFee);
			packageParams.put("fee_type", "CNY");

			packageParams.put("trade_type", trade_type);

			RequestHandler reqHandler = new RequestHandler(null, null);
			reqHandler.init(appid, appsecret, partnerkey);
			String sign = reqHandler.createSign(packageParams);
			String xml = "<xml>"
					+ "<appid>" + appid + "</appid>"
					+ "<body><![CDATA[" + body + "]]></body>"
					+ "<mch_id>" + mch_id + "</mch_id>"
					+ "<nonce_str>" + nonce_str + "</nonce_str>"
					+ "<notify_url>" + notify_url + "</notify_url>"
					+ "<openid>" + openId + "</openid>"
					+ "<out_trade_no>" + out_trade_no + "</out_trade_no>"
					+ "<spbill_create_ip>" + spbill_create_ip + "</spbill_create_ip>"
					+ "<sign>" + sign + "</sign>"
					+ "<total_fee>" + totalFee + "</total_fee>"
					+ "<fee_type>" + "CNY" + "</fee_type>"
					+ "<trade_type>" + trade_type + "</trade_type>"
					+ "</xml>";

			String prepay_id = "";
			String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
			LogUtil.info("开始发送请求xml:"+xml);
			prepay_id = new GetWxOrderno().getPayNo(createOrderURL, xml);
			LogUtil.info("获取到的预支付ID：" + prepay_id);

			//获取prepay_id后，拼接最后请求支付所需要的package

			SortedMap<String, String> finalpackage = new TreeMap<String, String>();
			timestamp = Sha1Util.getTimeStamp();
			packages = "prepay_id="+prepay_id;
			finalpackage.put("appId", appid);
			finalpackage.put("timeStamp", timestamp);
			finalpackage.put("nonceStr", nonce_str);
			finalpackage.put("package", packages);
			finalpackage.put("signType", "MD5");
			//要签名
			finalsign = reqHandler.createSign(finalpackage);

			String finaPackage = "\"appId\":\"" + appid + "\",\"timeStamp\":\"" + timestamp
					+ "\",\"nonceStr\":\"" + nonce_str + "\",\"prepayId\":\""
					+ packages + "\",\"signType\" : \"MD5" + "\",\"paySign\":\""
					+ finalsign + "\"";

			System.out.println("V3 jsApi package:"+finaPackage);
		}catch (Exception e){
			LogUtil.error("支付错误",e);
			e.printStackTrace();
		}
		JSONObject object = new JSONObject();
		object.put("appId", appid);
		object.put("timeStamp", timestamp);
		object.put("nonceStr", nonce_str);
		object.put("packages", packages);
		object.put("paySign", finalsign);
		return object;
	}
	/**
	 *微信异步通知
	 */
	//@ResponseBody
	@RequestMapping("/notify")
	public void notify(HttpServletRequest req,HttpServletResponse response) {
		try {
			//WeixinPayEntity  weixinpayentity=nweixinpayservice.getWeH();
            WeixinPayEntity weixinpayentity = systemService.get(WeixinPayEntity.class, "90106221a9db11e8beea54bf642d3cbf");
			String appid=null;
			String appsecret=null;
			String partner=null;
			String partnerkey=null;
			String notifyurl=null;
			if(weixinpayentity!=null){
				appid=weixinpayentity.getAppid();
				appsecret=weixinpayentity.getAppsecret();
				partner=weixinpayentity.getPartner();
				partnerkey=weixinpayentity.getPartnerkey();
				notifyurl=weixinpayentity.getNotifyurl();
			}
			StringBuffer sb = new StringBuffer();
			InputStream inputStream = req.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			String result=sb.toString();
			LogUtil.info("微信回调返回数据:"+result);
			//将字符串解析成XML
			//Map map = GetWxOrderno.doXMLParse(result);
			Map<String, String> stringStringMap = WXPayUtil.xmlToMap(result);

			if("success".equalsIgnoreCase(stringStringMap.get("return_code"))){
				System.out.println("return_code==success");
				// 公众账号ID appid 是 String(32) wx8888888888888888 微信分配的公众账号ID
				//	 商户号 mch_id 是 String(32) 1900000109 微信支付分配的商户号
				if(partner.equalsIgnoreCase(stringStringMap.get("mch_id"))&&appid.equalsIgnoreCase(stringStringMap.get("appid"))){
					String out_trade_no=stringStringMap.get("out_trade_no");
					if(StringUtils.isNotBlank(out_trade_no)){
						NOrderEntity nOrder = systemService.getEntity(NOrderEntity.class,out_trade_no);
						if(nOrder!=null){
							//获取商户ID
							String paystatus=nOrder.getPaystatus();     //	付款状态 	c	32		0 付款成功 1付款失败,
							String order_status=nOrder.getOrderStatus();//0待付款1待拼单，2待发货3已发货4已签收5已评价6售后处理中
							if("1".equals(paystatus)&&"0".equals(order_status)){
								NGoodsDetaislEntity nGoodsDetaislEntity = systemService.getEntity(NGoodsDetaislEntity.class, nOrder.getGoodsId());
								String isOnlyOne = nGoodsDetaislEntity.getIsOnlyOne();
								//用户只能参团一次时  判断当前用户是否支付过
								boolean checkFalg = true;
								if(nGoodsDetaislEntity!=null && StringUtils.isNotBlank(isOnlyOne) && "0".equals(isOnlyOne)){
									String checkSql = "select t1.id from n_order t1 LEFT JOIN n_goods_detaisl t2 on t1.goods_id = t2.id where t1.user_id = '"+nOrder.getUserId()+"' and (t1.joinorderstatus = '0' or t1.joinorderstatus= '1')  and t1.paystatus = '0' and t1.joinordertype = '1' and t2.is_only_one = '0'";
									List<Object> listbySql = systemService.findListbySql(checkSql);
									if(listbySql != null && listbySql.size()>0){
										checkFalg = false;
									}
								}
								System.out.println("时珊珊打印checkFalg:"+checkFalg+"哈哈哈"+nOrder.getIsMemberGoods());
								if(checkFalg){
									if(StringUtils.isNotBlank(nOrder.getIsMemberGoods()) && "0".equals(nOrder.getIsMemberGoods())){
										utilsSss.addMemberFlag(nOrder);
									}
									nOrder.setPaystatus("0");
									nOrder.setOrderStatus("1");
									systemService.saveOrUpdate(nOrder);
								}else {
									getWeixinpay(nOrder.getId(), nOrder.getPaySum(), nOrder.getUserId(),nOrder.getGoodsname());
								}
                                //开团信息记录
                                if(StringUtils.isNotBlank(nOrder.getGoodsDetaislType()) && "1".equals(nOrder.getGoodsDetaislType())){
                                    saveNGoodsJoinRecord(nOrder.getUserId(),nOrder.getId(),nOrder.getGoodsId(),"1");
                                }
								/*final String t_standardid=nOrder.getStandardid();
								final String t_standardname=nOrder.getStandardname();
								final String t_orderid=nOrder.getOrderid();
								final String t_ptPerson=nOrder.getPtPerson();
								final String t_goodsDetaislType=nOrder.getGoodsDetaislType();
								final String t_paySum=nOrder.getPaySum();
								final String t_paystatus=paystatus;
								final String t_userId=nOrder.getUserId();
								final String t_userprice=nOrder.getUserprice();
								final String t_couponid=nOrder.getCouponid();
								final String t_id=nOrder.getId();
								final Date t_today=nOrder.getApplytime();
								final Date t_tomorrow=nOrder.getEndTime();
								final Date createdate=nOrder.getCreateDate();
								final Date endtime=nOrder.getEndTime();
								final String t_realname=nOrder.getRealname();
								final String t_goodsId=nOrder.getGoodsId();
								final String t_merchantId=nOrder.getMerchantId();
								final String t_goodsname=nOrder.getGoodsname();
								final String t_joinordertype=nOrder.getJoinordertype();
								final String t_goods_sum=nOrder.getGoodsSum();*/
								/*Thread t1 = new Thread("t1"){
									public void run(){
										String couponType="2";//couponType 0抵用卷、1开团卷、2参团卷
										//joinordertype	拼单类型 0开团、1拼团，
										if("0".equals(t_joinordertype)){
											couponType="2";
										}else{
											couponType="1";
										}
									*//*	*//**//***优惠券***//**//*
										if(!StringUtil.is_khEmpty(t_couponid)){
											getUpdatecouponid(t_couponid);

										}else{
											if("1".equals(t_goodsDetaislType)){
												*//**//**发卷*//**//*
												getNUserCoupon(t_id,t_orderid,t_today,t_tomorrow,t_userId,t_realname,
														t_goodsId,t_merchantId,t_goodsname,couponType);
											}
										}*//*
									}
								};
								t1.start();*/
							}
						}
					}
				}
				LogUtil.info("正确返回:<xml><return_code><![CDATA[SUCCESS]]></return_code></xml>");
				makeRes("SUCCESS","OK",response);
			}else{
				LogUtil.info("错误返回: <xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[return_code不正确]]></return_msg></xml>");
				makeRes("FAIL","return_code不正确",response);
			}
			/*SortedMap<String, String> packageParams = new TreeMap<>();
			packageParams.put("return_code", "SUCCESS");
			packageParams.put("return_msg", "OK");
			RequestHandler reqHandler = new RequestHandler(null, null);
			reqHandler.init(appid, appsecret, partnerkey);
			String sign = reqHandler.createSign(packageParams);*/
			//通知微信支付系统接收到信息
			/*return "<xml><return_code><![CDATA[SUCCESS]]></return_code>"
					+"<return_msg><![CDATA[OK]]></return_msg>"
					//+ "<sign><![CDATA["+sign+"]]></sign>"
					+ "</xml>" ;*/
			//return "<xml><return_code><![CDATA[SUCCESS]]></return_code></xml>" ;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		//如果失败返回错误，微信会再次发送支付信息
		//return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[return_code不正确]]></return_msg></xml>";
	}
	private void makeRes(String code , String msg, HttpServletResponse response){
		try {
			//response.reset();
			PrintWriter printWriter = response.getWriter();
			printWriter.write("<xml><return_code>"+code+"</return_code><return_msg>"+msg+"</return_msg></xml>");
			printWriter.flush();
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void  getWeixinpay(String id,String paySum,String userId,String goodsname){
		WeixinPayEntity weixinpayentity = systemService.get(WeixinPayEntity.class, "90106221a9db11e8beea54bf642d3cbf");
		String wxappid=null;
		String wxappsecret=null;
		String wxpartner=null;
		String wxpartnerkey=null;
		String wxnotifyurl=null;
		if(weixinpayentity!=null){
			wxappid=weixinpayentity.getAppid();
			wxappsecret=weixinpayentity.getAppsecret();
			wxpartner=weixinpayentity.getPartner();
			wxpartnerkey=weixinpayentity.getPartnerkey();
			wxnotifyurl=weixinpayentity.getNotifyurl();
		}
		String nonceStr = getNonceStr();
		/*-----  1.生成预支付订单需要的的package数据-----*/
		SortedMap<String, String> packageParams = new TreeMap<>();
		paySum=new BigDecimal(100).multiply(new BigDecimal(paySum)).setScale(0,BigDecimal.ROUND_HALF_DOWN).toString();
		packageParams.put("appid", wxappid);
		packageParams.put("mch_id", wxpartner);
		packageParams.put("nonce_str", nonceStr);
		packageParams.put("out_trade_no", id);
		packageParams.put("out_refund_no", id);
		packageParams.put("total_fee",paySum);
		packageParams.put("refund_fee", paySum);
		/*----2.根据package生成签名sign---- */
		RequestHandler reqHandler = new RequestHandler(null, null);
		reqHandler.init(wxappid, wxappsecret, wxpartnerkey);
		String sign = reqHandler.createSign(packageParams);
		/*----3.拼装需要提交到微信的数据xml-total_fee  支付金额，refund_fee 退的金额--- */
		String xml="<xml>"
				+"<appid>"+wxappid+"</appid>"
				+ "<mch_id>"+wxpartner+"</mch_id>"
				+ "<nonce_str>"+nonceStr+"</nonce_str>"
				+ "<out_trade_no>"+id+"</out_trade_no>"
				+ "<out_refund_no>"+id+"</out_refund_no>"
				+ "<refund_fee>"+paySum+"</refund_fee>"
				+ "<total_fee>"+paySum+"</total_fee>"
				+ "<sign>"+sign+"</sign>"
				+"</xml>";
		try {
			/*----4.读取证书文件,这一段是直接从微信支付平台提供的demo中copy的，所以一般不需要修改---- */
			KeyStore keyStore  = KeyStore.getInstance("PKCS12");
			FileInputStream instream = new FileInputStream(new File("C:\\apiclient_cert_app.p12"));
			try {
				keyStore.load(instream, wxpartner.toCharArray());
			} finally {
				instream.close();
			}
			// Trust own CA and all self-signed certs
			SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, wxpartner.toCharArray()).build();
			// Allow TLSv1 protocol only
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext,new String[] { "TLSv1" },null,
					SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
			CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
			if(StringUtils.isNotBlank(paySum) && !"0".equals(paySum)){
				/*----5.发送数据到微信的退款接口---- */
				String url="https://api.mch.weixin.qq.com/secapi/pay/refund";
				HttpPost httpost= HttpClientConnectionManager.getPostMethod(url);
				httpost.setEntity(new StringEntity(xml, "UTF-8"));
				HttpResponse weixinResponse = httpClient.execute(httpost);
				String jsonStr = EntityUtils.toString(weixinResponse.getEntity(), "UTF-8");
				LogUtil.info("微信退款接口返回数据:"+jsonStr);
				Map<String, String> stringStringMap = WXPayUtil.xmlToMap(jsonStr);
				if("success".equalsIgnoreCase(stringStringMap.get("return_code"))){
					//退款成功删除订单
					weixinservice.sendTemplate_six(userId, "支付失败!", "¥"+paySum+"元","退款-"+goodsname, "原路退回.", id, "请勿重复支付,如有问题，拨打客服");
					String sql = "delete from n_order where id ='"+id+"'";
					systemService.updateBySqlString(sql);
				}

			}
		} catch (Exception e) {
			LogUtil.error("退款失败信息",e);
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
	 * @Desprition 增加会员标识
	 * @Date 2018/8/30 13:43
	 * @Param
	 * @Return
	 *//*
	public void addMemberFlag(NOrderEntity nOrder) {
		String userId = nOrder.getUserId();
		String createName = nOrder.getCreateName();
		NUserEntity entity = systemService.getEntity(NUserEntity.class, userId);
			try {
				String weixinPayId = "90106221a9db11e8beea54bf642d3cbf";
				WeixinPayEntity weixinPayEntity = systemService.getEntity(WeixinPayEntity.class, weixinPayId);
				entity.setIsMember("Y");
				entity.setMemberStart(DateUtils.getDate());
				entity.setMemberEnd(DateUtils.getNextYear());
				systemService.saveOrUpdate(entity);
				System.out.println("增加会员标识成功");
				//增加钱包流水信息
				//1判断是否记录过
				String isRecord = "select id from n_user_price where user_id = '"+userId+"' and  orderid = '1' ";
				List<Object> list = systemService.findListbySql(isRecord);
				if( list != null && list.size()>0 ){
				}else{
					// 给当前会员发送模板消息
					weixinservice.sendTemplate_member(userId,"您的金牌会员资格已开通成功啦","VIP",DateUtils.formatDate(entity.getMemberStart()),DateUtils.formatDate(entity.getMemberEnd()),"","感谢的您的支持");
					NUserPriceEntity userPriceEntity = new NUserPriceEntity();
					userPriceEntity.setId(StringUtil.getId());
					userPriceEntity.setUserId(userId);
					userPriceEntity.setRealname(entity.getRealname());
					userPriceEntity.setCreateDate(new Date());
					userPriceEntity.setUserPriceType("金牌会员充值");
					userPriceEntity.setPrice(weixinPayEntity.getSysOrgCode());
					userPriceEntity.setOrderid("1");
					systemService.save(userPriceEntity);
				}
				//推荐会员奖励
				if(StringUtils.isNotBlank(createName)){
					NUserEntity user = systemService.getEntity(NUserEntity.class, createName);
					if (user!=null){
						String isRecord1 = "select id from n_user_price where user_id = '"+createName+"' and update_by = '"+userId+"' and  orderid = '7' ";
						List<Object> list1 = systemService.findListbySql(isRecord1);
						//1添加钱包记录
						if (list1 != null  && list1.size()>0){
						}else{
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
							//判断推荐人是金牌还是银牌
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
							systemService.save(userPriceEntityRefferrer);
							//推荐成功发送推送消息
						*//*	if(flag1){
								weixinservice.sendTemplate_refferrer(createName,"成团奖励收入提醒",weixinPayEntity.getUpdateName(),"成团奖励(会员解冻金额)","",DateUtils.formatTimehsm(new Date()),"点击查看钱包详情，如有疑问直接咨询在线客服。");
							}else{*//*
								weixinservice.sendTemplate_refferrer(createName,"成团奖励收入提醒",weixinPayEntity.getRemarks(),"押金解冻!","",DateUtils.formatTimehsm(new Date()),"点击查看钱包详情，如有疑问直接咨询在线客服。");
							//}
							//2.添加金额增加
							String price = user.getPrice();
							if (!StringUtils.isNotBlank(price)){
								price = "0";
							}
							*//*if(flag1){
								price = add(weixinPayEntity.getUpdateName(),price);
							}else{*//*
								price =  CommonUtils.add(weixinPayEntity.getRemarks(),price);
							//}
							user.setPrice(price);
							String ext1 = user.getExt1();
							if(!StringUtils.isNotBlank(ext1)){
								ext1 = "0";
							}
							*//*if(flag1){
								ext1 = add(weixinPayEntity.getUpdateName(),ext1);
							}else{*//*
								ext1 =  CommonUtils.add(weixinPayEntity.getRemarks(),ext1);
							//}
							user.setExt1(ext1);
							//systemService.saveOrUpdate(user);
							//二级奖励
							String twoSql = "select create_name from n_order  where user_id = '"+createName+"' and goods_id = '00000000000000000000000000000001' and order_status = '3'";
							List<Object> listbySql = systemService.findListbySql(twoSql);
							if(listbySql!=null && listbySql.size()>0){
								String  firstUserId = (String) listbySql.get(0);
								System.out.println("时珊珊打印第一个金牌奖励人员=" + firstUserId);
								if(StringUtils.isNotBlank(firstUserId)){
									NUserEntity firstUser = systemService.getEntity(NUserEntity.class, firstUserId);
									if(firstUser != null){
										*//*String ext31 = firstUser.getExt3();
										boolean falg2 = false;
										if(StringUtils.isNotBlank(ext31) && "Y".equals(ext31)){
											falg2 = true;
										}*//*
										NUserPriceEntity firstUserPriceEntity = new NUserPriceEntity();
										firstUserPriceEntity.setId(StringUtil.getId());
										firstUserPriceEntity.setUserId(firstUser.getId());
										firstUserPriceEntity.setRealname(firstUser.getRealname());
										firstUserPriceEntity.setCreateDate(new Date());
										firstUserPriceEntity.setUserPriceType("金牌推荐奖励");
										*//*if(falg2){
											firstUserPriceEntity.setPrice(weixinPayEntity.getUpdateBy());
										}else{*//*
											firstUserPriceEntity.setPrice(weixinPayEntity.getTitle());
										//}
										firstUserPriceEntity.setSysOrgCode("1");//表示下级推荐奖励标识
										//被推荐人
										firstUserPriceEntity.setUpdateBy(user.getId());
										firstUserPriceEntity.setUpdateName(user.getRealname());
										firstUserPriceEntity.setOrderid("7");
										systemService.save(firstUserPriceEntity);
										//推荐成功发送推送消息
										*//*if(falg2){
											weixinservice.sendTemplate_refferrer(firstUserId,"成团奖励收入提醒",weixinPayEntity.getUpdateBy(),"成团奖励(会员解冻金额)","",DateUtils.formatTimehsm(new Date()),"点击查看钱包详情，如有疑问直接咨询在线客服。");
										}else{*//*
											weixinservice.sendTemplate_refferrer(firstUserId,"成团奖励收入提醒",weixinPayEntity.getTitle(),"押金解冻!","",DateUtils.formatTimehsm(new Date()),"点击查看钱包详情，如有疑问直接咨询在线客服。");
										//}
										//2.添加金额增加
										String firstPrice = firstUser.getPrice();
										if (!StringUtils.isNotBlank(firstPrice)){
											firstPrice = "0";
										}
										*//*if(falg2){
											firstPrice =  CommonUtils.add(weixinPayEntity.getUpdateBy(),firstPrice);
										}else{*//*
											firstPrice =  CommonUtils.add(weixinPayEntity.getTitle(),firstPrice);
										//}
										firstUser.setPrice(firstPrice);
										//总余额
										String userExt1 = firstUser.getExt1();
										if (!StringUtils.isNotBlank(userExt1)){
											userExt1 = "0";
										}
										*//*if(falg2){
											userExt1 =  CommonUtils.CommonUtils.add(weixinPayEntity.getUpdateBy(),userExt1);
										}else{*//*
											userExt1 = CommonUtils.add(weixinPayEntity.getTitle(),userExt1);
										//}
										firstUser.setExt1(userExt1);
										//systemService.saveOrUpdate(firstUser);
									}
								}
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	}*/
	 /**
	  * @Author shishanshan
	  * @Desprition 增加银牌会员标识
	  * @Date 2018/10/12 14:15
	  * @Param
	  * @Return
	  */
	public void addSilverMemberFlag(NOrderEntity nOrder) {
		String userId = nOrder.getUserId();
		System.out.println("sss打印增加银牌会员标识");
		String createName = nOrder.getCreateName();
		NUserEntity entity = systemService.getEntity(NUserEntity.class, userId);
		try {
			String weixinPayId = "90106221a9db11e8beea54bf642d3cbf";
			WeixinPayEntity weixinPayEntity = systemService.getEntity(WeixinPayEntity.class, weixinPayId);
			entity.setExt3("Y");
			entity.setMemberStart(DateUtils.getDate());
			entity.setMemberEnd(DateUtils.getNextYear());
			systemService.saveOrUpdate(entity);
			System.out.println("增加银牌会员标识成功");
			//增加钱包流水信息
			//1判断是否记录过
			String isRecord = "select id from n_user_price where user_id = '"+userId+"' and  orderid = '2' ";
			List<Object> list = systemService.findListbySql(isRecord);
			if( list != null && list.size()>0 ){
			}else{
				// 给当前会员发送模板消息
				weixinservice.sendTemplate_member(userId,"您的抢购押金已支付!","双11防刷单押金",DateUtils.formatDate(entity.getMemberStart()),DateUtils.formatDate(entity.getMemberEnd()),"","感谢的您的支持");
				NUserPriceEntity userPriceEntity = new NUserPriceEntity();
				userPriceEntity.setId(StringUtil.getId());
				userPriceEntity.setUserId(userId);
				userPriceEntity.setRealname(entity.getRealname());
				userPriceEntity.setCreateDate(new Date());
				userPriceEntity.setUserPriceType("双11防刷单押金");
				userPriceEntity.setPrice(weixinPayEntity.getExt1());
				userPriceEntity.setOrderid("2");
				systemService.save(userPriceEntity);
			}
			//推荐会员奖励
			if(StringUtils.isNotBlank(createName)){
				NUserEntity user = systemService.getEntity(NUserEntity.class, createName);
				if (user!=null){
					String isRecord1 = "select id from n_user_price where user_id = '"+createName+"' and update_by = '"+userId+"' and  orderid = '8' ";
					List<Object> list1 = systemService.findListbySql(isRecord1);
					//1添加钱包记录
					if (list1 != null  && list1.size()>0){
					}else{
						NUserPriceEntity userPriceEntityRefferrer = new NUserPriceEntity();
						userPriceEntityRefferrer.setId(StringUtil.getId());
						userPriceEntityRefferrer.setUserId(user.getId());
						userPriceEntityRefferrer.setRealname(user.getRealname());
						userPriceEntityRefferrer.setCreateDate(new Date());
						userPriceEntityRefferrer.setUserPriceType("成团奖励");
						userPriceEntityRefferrer.setPrice(weixinPayEntity.getRewardOne());
						//被推荐人
						userPriceEntityRefferrer.setUpdateBy(entity.getId());
						userPriceEntityRefferrer.setUpdateName(entity.getRealname());
						userPriceEntityRefferrer.setOrderid("8");
						systemService.save(userPriceEntityRefferrer);
						//推荐成功发送推送消息
						weixinservice.sendTemplate_refferrer(createName,"成团奖励!",weixinPayEntity.getRewardOne(),"押金解冻!","",DateUtils.formatTimehsm(new Date()),"点击查看钱包详情，如有疑问直接咨询在线客服。");
						//2.添加金额增加
						String price = user.getPrice();
						if (!StringUtils.isNotBlank(price)){
							price = "0";
						}
						price =  CommonUtils.add(weixinPayEntity.getRewardOne(),price);
						user.setPrice(price);
						String ext1 = user.getExt1();
						if(!StringUtils.isNotBlank(ext1)){
							ext1 = "0";
						}
						ext1 =  CommonUtils.add(weixinPayEntity.getRewardOne(),ext1);
						user.setExt1(ext1);
						//给推荐人在分享奖励基础上加二元
						/*Integer totalNumber = user.getTotalNumber();
						if(totalNumber == null){
							totalNumber = 2;
						}
						totalNumber += 2;
						user.setTotalNumber(totalNumber);*/
						//systemService.saveOrUpdate(user);
						//二级奖励
						String twoSql = "select create_name from n_order  where user_id = '"+createName+"' and goods_id = '00000000000000000000000000000002' and order_status = '3'";
						List<Object> listbySql = systemService.findListbySql(twoSql);
						if(listbySql!=null && listbySql.size()>0){
							String  firstUserId = (String) listbySql.get(0);
							System.out.println("时珊珊打印第一个银牌奖励人员=" + firstUserId);
							if(StringUtils.isNotBlank(firstUserId)){
								NUserEntity firstUser = systemService.getEntity(NUserEntity.class, firstUserId);
								if(firstUser != null){
									NUserPriceEntity firstUserPriceEntity = new NUserPriceEntity();
									firstUserPriceEntity.setId(StringUtil.getId());
									firstUserPriceEntity.setUserId(firstUser.getId());
									firstUserPriceEntity.setRealname(firstUser.getRealname());
									firstUserPriceEntity.setCreateDate(new Date());
									firstUserPriceEntity.setUserPriceType("成团奖励");
									firstUserPriceEntity.setPrice(weixinPayEntity.getRewardTwo());
									firstUserPriceEntity.setSysOrgCode("1");//表示下级推荐奖励标识
									//被推荐人
									firstUserPriceEntity.setUpdateBy(user.getId());
									firstUserPriceEntity.setUpdateName(user.getRealname());
									firstUserPriceEntity.setOrderid("8");
									systemService.save(firstUserPriceEntity);
									//推荐成功发送推送消息
									weixinservice.sendTemplate_refferrer(firstUserId,"成团奖励!",weixinPayEntity.getRewardTwo(),"押金解冻!","",DateUtils.formatTimehsm(new Date()),"点击查看钱包详情，如有疑问直接咨询在线客服。");
									//2.添加金额增加
									String firstPrice = firstUser.getPrice();
									if (!StringUtils.isNotBlank(firstPrice)){
										firstPrice = "0";
									}
									firstPrice =  CommonUtils.add(weixinPayEntity.getRewardTwo(),firstPrice);
									firstUser.setPrice(firstPrice);
									//总余额
									String userExt1 = firstUser.getExt1();
									if (!StringUtils.isNotBlank(userExt1)){
										userExt1 = "0";
									}
									userExt1 = CommonUtils.add(weixinPayEntity.getRewardTwo(),userExt1);
									firstUser.setExt1(userExt1);
									/*Integer totalNumber1 = firstUser.getTotalNumber();
									if(totalNumber1 == null){
										totalNumber1 = 2;
									}
									totalNumber1 += 1;
									firstUser.setTotalNumber(totalNumber1);*/
									//systemService.saveOrUpdate(firstUser);
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
	 * 获取随机字符串
	 * @return
	 */
	public static String getNonceStr() {
		// 随机数
		String currTime = TenpayUtil.getCurrTime();
		// 8位日期
		String strTime = currTime.substring(8, currTime.length());
		// 四位随机数
		String strRandom = TenpayUtil.buildRandom(4) + "";
		// 10位序列号,可以自行调整。
		return strTime + strRandom;
	}

	/**
	 * 元转换成分
	 * @param money
	 * @return
	 */
	public static String getMoney(String amount) {
		if(amount==null){
			return "";
		}
		// 金额转化为分为单位
		String currency =  amount.replaceAll("\\$|\\￥|\\,", "");  //处理包含, ￥ 或者$的金额
		int index = currency.indexOf(".");
		int length = currency.length();
		Long amLong = 0l;
		if(index == -1){
			amLong = Long.valueOf(currency+"00");
		}else if(length - index >= 3){
			amLong = Long.valueOf((currency.substring(0, index+3)).replace(".", ""));
		}else if(length - index == 2){
			amLong = Long.valueOf((currency.substring(0, index+2)).replace(".", "")+0);
		}else{
			amLong = Long.valueOf((currency.substring(0, index+1)).replace(".", "")+"00");
		}
		return amLong.toString();
	}



	/**
	 * Map转Xml
	 * @param arr
	 * @return
	 */
	public  String MapToXml(Map<String, String> arr) {
		String xml = "<xml>";
		Iterator<Entry<String, String>> iter = arr.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, String> entry = iter.next();
			String key = entry.getKey();
			String val = entry.getValue();
			if (IsNumeric(val)) {
				xml += "<" + key + ">" + val + "</" + key + ">";
			} else
				xml += "<" + key + "><![CDATA[" + val + "]]></" + key + ">";
		}
		xml += "</xml>";
		return xml;
	}
	private  boolean IsNumeric(String str) {
		if (str.matches("\\d *")) {
			return true;
		} else {
			return false;
		}
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
	public void getUpdateOrder(String orderid,String ptPerson,String goods_detaisl_type,String marketingOne,
							   String t_paySum,String standardid){
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
		StringBuffer sql2_f=new StringBuffer();
		sql2_f.append("select t1.id,t1.orderid,t1.marketing_one ,")
				.append(" t1.standardid,t1.numbers ,t1.user_id,t1.goods_sum,t1.goodsname,t1.area ,t1.address")
				.append(" from  n_order t1 ,")
				.append("(select *,COUNT(*)as pt_numbs from n_order where ")
				.append("  paystatus='0' and goods_detaisl_type='2' ");
		//	+ "and marketing_one in('0','1','2','4','6') ");
		if(!StringUtil.is_khEmpty(orderid)){
			sql2_f.append(" and orderid='")
					.append(orderid)
					.append("'");
		}
		sql2_f.append("   GROUP BY orderid  )t2 ")
				.append(" where t1.orderid=t2.orderid and t1.pt_person<=t2.pt_numbs ");
		if(!StringUtil.is_khEmpty(orderid)){
			sql2_f.append(" and t1.orderid='")
					.append(orderid)
					.append("'");
		}
		sql2_f.append(" and t1.paystatus='0' and t1.goods_detaisl_type='2' ")
				.append(" and  t1.joinorderstatus='0'");
		//	   joinorderstatus	拼单状态	c	36		0待完成，1已完成、2订单取消
		// 		+ " and t1.marketing_one in('0','1','2','4','6') ");
		List<Object[]> mode_list = systemService.findListbySql(sql2_f.toString());
		for(int i=0; i<mode_list.size(); i++) {
			Object[]  t= mode_list.get(i);
			String id=t[0]+"";
			String user_id=t[5]+"";
			String goods_sum=t[6]+"";
			String goodsname=t[7]+"";
			String area=t[8]+"";
			String address=t[9]+"";
			String hstandardid=t[3]+"";
			String hpsum=t[4]+"";
			if(!StringUtil.is_khEmpty(hstandardid) && !"undefined".equals(hstandardid)){
				NStandardDetailsEntity	nstandarddetails = systemService.getEntity(NStandardDetailsEntity.class, hstandardid);
				if(nstandarddetails!=null){
					String sumNumber=nstandarddetails.getSumNumber();
					if(!StringUtil.is_khEmpty(sumNumber)){
						BigDecimal e1 = new BigDecimal(sumNumber);
						BigDecimal e2 = new BigDecimal(hpsum);
						String sm=e1.subtract(e2).setScale(2,BigDecimal.ROUND_HALF_DOWN).toString();
						nstandarddetails.setSumNumber(sm);
						systemService.saveOrUpdate(nstandarddetails);
					}
				}
			}
			StringBuffer sql2=new StringBuffer();
			sql2.append("update n_order t1 ")
					.append(" set  t1.pdtime= now() ,t1.joinorderstatus='1',")
					.append("t1.gatheringstatus='1',t1.order_status='2' ")
					.append(" where t1.id='")
					.append(id)
					.append("'");
			systemService.updateBySqlString(sql2.toString());
			//团购成功提醒
			weixinservice.sendTemplate_seven(user_id, "恭喜您团购成功，您的团购订单已完成付款， 商家将即时为您发货",
					goods_sum, goodsname, area+address, id, "查看订单详情");
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
