package com.jeecg.wepay.controller;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Timestamp;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.p3.core.utils.common.ApplicationContextUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.gexin.fastjson.JSON;
import com.jeecg.n_order.entity.NOrderEntity;
import com.jeecg.weixin.HttpClientUtil;
import com.jeecg.weixin_pay.entity.WeixinPayEntity;
import com.jeecg.weixin_pay.util.NWeixinPayService;
import com.jeecg.wepay.util.GetWxOrderno;
import com.jeecg.wepay.util.RequestHandler;
import com.jeecg.wepay.util.Sha1Util;
import com.jeecg.wepay.util.TenpayUtil;
import com.jeecg.wepay.util.http.HttpClientConnectionManager;


/**
 * @author ex_yangxiaoyi
 * 
 */
@Controller
@RequestMapping("/wxPayController")
public class WxPayController extends BaseController {
/*	//微信支付商户开通后 微信会提供appid和appsecret和商户号partner
	private static String appid = "wx8d5f593b39c52c6f";
	private static String appsecret = "f0bc2d092fdc5b89b3ac7362b2105156";
	//private static String partner = "1495222432";
	//微信另一个商户号
//		private static String two_partner = "1495222432";
	//第二个
//		private static String partner = "1495222432";
	//第三个
//	private static String appid = "wx6319f129c69fb18d";
//	private static String appsecret = "878bc52abb9b5d92034ceb9d833f706d";
		private static String partner = "1510630121";
	//这个参数partnerkey是在商户后台配置的一个32位的key,微信商户平台-账户设置-安全设置-api安全
	private static String partnerkey = "yaowei1987yaowei1987yaowei1987ya";
	//openId 是微信用户针对公众号的标识，授权的部分这里不解释
	private static String openId = "";
	//微信支付成功后通知地址 必须要求80端口并且地址不能带参数
	//private static String notifyurl = "http://admin.nainiupt.com/nainiupt";// Key
	private static String notifyurl = "http://admin.nainiupt.com/nainiupt/thPayController/notify.do";// Key	
//	private static String PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArT82k67xybiJS9AD8nNAeuDYdrtCRaxkS6cgs8L9h83eqlDTlrdwzBVSv5V4imTq/URbXn4K0V/KJ1TwDrqOI8hamGB0fvU13WW1NcJuv41RnJVua0QAlS3tS1JzOZpMS9BEGeFvyFF/epbi/m9+lkUWG94FccArNnBtBqqvFncXgQsm98JB3a42NbS1ePP/hMI7Kkz+JNMyYsWkrOUFDCXAbSZkWBJekY4nGZtK1erqGRve8JbxTWirAm/s08rUrjOuZFA21/EI2nea3DidJMTVnXVPY2qcAjF+595shwUKyTjKB8v1REPB3hPF1Z75O6LwuLfyPiCrCTmVoyfqjwIDAQAB";
//	public static final String CIPHER_ALGORITHM = "RSA/ECB/OAEPWITHSHA-1ANDMGF1PADDING";
*/	@Autowired
	private NWeixinPayService nweixinpayservice;
    @Autowired
	private SystemService systemService;
	/**
	 * 用户支付
	 * @param req
	 * @param resp
	 */
	@RequestMapping(params = "doPay")
	@ResponseBody
	public void doPay(HttpServletRequest req,HttpServletResponse resp,String orderid){
		systemService = ApplicationContextUtil.getContext().getBean(SystemService.class);
		WxPayDto tpWxPay = new WxPayDto();
		NOrderEntity entity = systemService.getEntity(NOrderEntity.class, orderid);
		tpWxPay.setBody("闪购拼团"+orderid);
		tpWxPay.setOrderId(orderid);
		tpWxPay.setSpbillCreateIp("127.0.0.1");
		tpWxPay.setTotalFee(entity.getPaySum());
		JSONObject json = getPackage(tpWxPay);
	    TagUtil.getSendJson(resp,json.toString());
	} 
	
	
	@RequestMapping(params="doRefund")
	public void doRefund(HttpServletRequest req,HttpServletResponse resp,String orderid){
		  WeixinPayEntity  weixinpayentity=nweixinpayservice.getWeH();
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
		//systemService = ApplicationContextUtil.getContext().getBean(SystemService.class);
		NOrderEntity entity = systemService.getEntity(NOrderEntity.class, orderid);
		String nonceStr = getNonceStr();
        /*-----  1.生成预支付订单需要的的package数据-----*/
        SortedMap<String, String> packageParams = new TreeMap<String, String>();
        
        packageParams.put("appid", appid);  
        packageParams.put("mch_id", partner);  
        packageParams.put("nonce_str", nonceStr);  
        packageParams.put("out_trade_no", orderid);  
        packageParams.put("out_refund_no", orderid);  
        packageParams.put("total_fee",getMoney(entity.getPaySum()));  
        packageParams.put("refund_fee", getMoney(entity.getPaySum()));  
        /*----2.根据package生成签名sign---- */
        RequestHandler reqHandler = new RequestHandler(null, null);
        reqHandler.init(appid, appsecret, partnerkey);
        String sign = reqHandler.createSign(packageParams);

        /*----3.拼装需要提交到微信的数据xml---- */
        String xml="<xml>"
        +"<appid>"+appid+"</appid>"
        + "<mch_id>"+partner+"</mch_id>"
        + "<nonce_str>"+nonceStr+"</nonce_str>"
        + "<out_trade_no>"+orderid+"</out_trade_no>"
        + "<out_refund_no>"+orderid+"</out_refund_no>"
        + "<refund_fee>"+getMoney(entity.getPaySum())+"</refund_fee>"
        + "<total_fee>"+getMoney(entity.getPaySum())+"</total_fee>"
        + "<sign>"+sign+"</sign>"
        +"</xml>";
         try {
             /*----4.读取证书文件,这一段是直接从微信支付平台提供的demo中copy的，所以一般不需要修改---- */
             KeyStore keyStore  = KeyStore.getInstance("PKCS12");
             FileInputStream instream = new FileInputStream(new File("C:\\apiclient_cert_app.p12"));
             try {
                 keyStore.load(instream, partner.toCharArray());
             } finally {
                 instream.close();
             }
             // Trust own CA and all self-signed certs
             SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, partner.toCharArray()).build();
             // Allow TLSv1 protocol only
             SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext,new String[] { "TLSv1" },null,
                     SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
             CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();

             /*----5.发送数据到微信的退款接口---- */
             String url="https://api.mch.weixin.qq.com/secapi/pay/refund";
             HttpPost httpost= HttpClientConnectionManager.getPostMethod(url);
             httpost.setEntity(new StringEntity(xml, "UTF-8"));
             HttpResponse weixinResponse = httpClient.execute(httpost);
             String jsonStr = EntityUtils.toString(weixinResponse.getEntity(), "UTF-8");
             System.out.println(jsonStr);
             Map map = GetWxOrderno.doXMLParse(jsonStr);
             if("success".equalsIgnoreCase((String) map.get("return_code"))){
                 System.out.println("returncode"+"="+"ok");
                 System.out.println("returninfo"+"="+"退款成功");
             }else{
            	 System.out.println("returncode"+"="+"error");
            	 System.out.println("returninfo"+"="+"退款失败");
            	 System.out.println((String) map.get("return_msg"));
             }
        } catch (Exception e) {
        	System.out.println("returncode"+"="+"exception"+e.toString());
        	System.out.println("returninfo"+"="+"退款失败");
//        	e.printStackTrace();
        }
         
	}
    
    
	/**
	 * 获取请求预支付id报文
	 * @return
	 */
	@SuppressWarnings("static-access")
	public JSONObject getPackage(WxPayDto tpWxPayDto) {
		//systemService = ApplicationContextUtil.getContext().getBean(SystemService.class);
		  WeixinPayEntity  weixinpayentity=nweixinpayservice.getWeH();
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
		
		String openId = tpWxPayDto.getOpenId();
		// 1 参数
		// 订单号
		String orderId = tpWxPayDto.getOrderId();
		// 附加数据 原样返回
		//String attach = "";
		// 总金额以分为单位，不带小数点
		String totalFee = getMoney(tpWxPayDto.getTotalFee());
		System.out.println(totalFee);
		
		// 订单生成的机器 IP
		String spbill_create_ip = tpWxPayDto.getSpbillCreateIp();
		// 这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
		String notify_url = notifyurl;
		String trade_type = "APP";

		// ---必须参数
		// 商户号
		String mch_id = partner;
		// 随机字符串
		String nonce_str = getNonceStr();

		// 商品描述根据情况修改
		String body = tpWxPayDto.getBody();

		// 商户订单号
		String out_trade_no = orderId;

		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", appid);
		packageParams.put("body", body);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("notify_url", notify_url);
		packageParams.put("out_trade_no", out_trade_no);

		// 这里写的金额为1 分到时修改
		packageParams.put("spbill_create_ip", spbill_create_ip);
		packageParams.put("total_fee", totalFee);
		packageParams.put("fee_type", "CNY");

		packageParams.put("trade_type", trade_type);

		RequestHandler reqHandler = new RequestHandler(null, null);
		reqHandler.init(appid, appsecret, partnerkey);
		String sign = reqHandler.createSign(packageParams);
		System.out.println("prepay-sign==="+sign);
		
		String xml = "<xml>" 
						+ "<appid>" + appid + "</appid>" 
						+ "<body><![CDATA[" + body + "]]></body>" 
						+ "<mch_id>" + mch_id + "</mch_id>" 
						+ "<nonce_str>" + nonce_str + "</nonce_str>" 
						+ "<notify_url>" + notify_url + "</notify_url>" 
						+ "<out_trade_no>" + out_trade_no + "</out_trade_no>"
						+ "<spbill_create_ip>" + spbill_create_ip + "</spbill_create_ip>" 
						+ "<sign>" + sign + "</sign>"
						+ "<total_fee>" + totalFee + "</total_fee>"
						+ "<fee_type>" + "CNY" + "</fee_type>"
						+ "<trade_type>" + trade_type + "</trade_type>" 
					+ "</xml>";
		
		String prepay_id = "";
		String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		System.out.println("xml==="+xml);
		
		System.out.println("开始发送请求");
		prepay_id = new GetWxOrderno().getPayNo(createOrderURL, xml);

		System.out.println("获取到的预支付ID：" + prepay_id);
		
		
		//获取prepay_id后，拼接最后请求支付所需要的package
		
		SortedMap<String, String> finalpackage = new TreeMap<String, String>();
		String timestamp = Sha1Util.getTimeStamp();
		Long tm = Long.valueOf(timestamp.substring(0, 10));
		System.out.println(tm);
		finalpackage.put("appid", appid);  
		finalpackage.put("noncestr", nonce_str);
		finalpackage.put("package", "Sign=WXPay");
		finalpackage.put("partnerid", partner);  
		finalpackage.put("prepayid", prepay_id);  
		finalpackage.put("timestamp", timestamp);  
		//要签名
		String finalsign = reqHandler.createSign(finalpackage);

//		SortedMap<String, String> obj = new TreeMap<String, String>();
//		obj.put("appid", appid);
//		obj.put("noncestr", nonce_str);
//		obj.put("package", "Sign=WXPay");
//		obj.put("partnerid", partner);
//		obj.put("prepayid", prepay_id);
//		obj.put("sign", finalsign.toUpperCase());
//		obj.put("timestamp", timestamp); 
		
		JSONObject json = new JSONObject(true);
		json.put("appid", appid);
		json.put("noncestr", nonce_str);
		json.put("package", "Sign=WXPay");
		json.put("partnerid", partner);
		json.put("prepayid", prepay_id);
		json.put("sign", finalsign.toUpperCase());
		json.put("timestamp", tm);
		return json;
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
}
