package com.jeecg.wepay.controller;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.security.KeyStore;
import java.sql.Timestamp;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.net.ssl.SSLContext;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import org.antlr.analysis.LL1Analyzer;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import com.jeecg.wepay.util.RequestHandler;
import com.jeecg.wepay.util.TenpayUtil;
import com.jeecg.wepay.util.http.HttpClientConnectionManager;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import test.CodeUtil;

public class Test {

	public static DefaultHttpClient httpclient;
	String APP_ID = "xxxxxx";
	String APP_PRIVATE_KEY = "xxxxxx+qbk/hrT+QbYzDctSpWyFGZYGuLyUWsXfhyVfNX+NNUSKWaQbzyOONuT9VGxfYA6220ALvXB6rPe/DhnW8GClYXWdH7BLKg4rnzos7YSVNaXCN58sTpJgHXbjtuvhMLZUqN0HXdZwFJpL50aIgb7XepQUxUvJodStGCf5iHk1SKKiNyKY9hSvVmvFDrfQc58hdWAI3t2nCd+EuZMfcJTP7xZrazLeE9AacasZ0DE7Kujl66zkXBta3Z6Rf+wvbBCUebVDz3bV6qp6YSbg/gk+Y+2zYXvIBghM5xJFBiELdJG85hxdcngNTag1Q6zmoNhXzObv2IHAdQmCeAFvDAgMBAAECggEAZmlCF82XddA7hE9//3C3oiQT/l4rjDBm6VJZXaJVtRykc1tkllkVameWJkfWonU49c0o2Rgp/uxLqwfgyA3pqppKV3OtKbslZrNnKM4euZrlRcvhLC32oXaGDjjgieytBxMvN3FCon5PLhvab66rFw53Jqe+mvHHUDyhJK/ZSWXZuTzNdawEPQrsOD9xZb+pLLI3GdA2BSZzCR1q9zGYYvoHpQX8+NK4z/39VmUoRU7Ny0OvfL/4L7wnScYnu2irWCMjTZHjfq1tnW3/lsYlW72AXEjnaZWLg0ZHwvSQnAEHI/hy6p9P5mAbRrehrgenWW/Cg1fiX4WQCcrSC4QMgQKBgQDC6Cv4YQ2SGqy6xT7gNf+6armMhyJ+lONcci7HfZAfmP17OmAW8io0Qldm/g/W9lBvpjMU2LNKPesdWE6PMfqnn/A71HskhAngvWPLq2AMWBp/kbAmYQmp6JBKZTQCvR/SxjfG1CsbYb/L7KYR72Mp/pfAEJRnJI5JK+X3nHRLTwKBgQC84b7eD3RJ4lhvSpG5Q3j1yebGbwyeST5JQc1sEsqRJ++VewZe97tAomjyWoCBNJb+rnsPkYv7OVvW06i9aOMhQ6/M8CM58vAcSwkOZsgq+Nq2sxR96u58LejcLMj3vXGis1FIR8WA6EM4gUDoezHDIO9cicY14tD937/HZw27TQKBgHKNbjpfIFC8qMRk5V11n0V7MG6thdKLw000NtY8sBZCHsjsOEmELtXkH+aCb+DRh9j2/5LDAi0iUys+GX4Dy+P1FoazjWSazgtuhFbR9HOM3JYZlEQaSEm6TAPNk1IAwdFpeqK7VFKVktpRzhFAdzHZVmsl03MDgzTyPgjXxWn1AoGAFs8D53jiSBHHMBlHI6IcN0IcVhYO5gZeOSZzEfvq7kBuVBS5Hjq4KAP0vF9laTTajwKu5aBj0QCKMJT6qXTDCL9NuWe+OT8285O0EkMjJN1MPAfAD7yQ8/nvRrc5xYDg+g7BYAMavIhPpcEl/2zxA2k0vm38u4EaT633ULMHG3kCgYEAryCPTXg2LBtd+vFye80sHc1GkZnMtg7Gbil7skImH793eOhBr1kx49AE+wciyy+VDpyOd73vHgLfinMAZeXwUPlQyatFXEhd1nGzxUqp/7R3BnaoQRQI7VGLz5qVsXSRdAJZvSGuHWyXmYwHKpuxUImOjGrJKCpLTKedeTnEb4k=";
	String CHARSET = "utf-8";
	String ALIPAY_PUBLIC_KEY = "xxxxxxxxxx/Q/LsiO8xfeXFvrm5iMZzXmNlaIlwMMldDzQLtlFa4UMCt3GFvWq3KgM/Sd8MFgQviGRdcQ+1n6TqVaVgYqewL0KIEI6ggDFsB15rxxnZ/PTbAdXww1MMQ2X5iE/GPy7vOnEZgC04/jjGjBNgvVX2LX+mbwbNTwtAX1tlyafSJOQ0Hp+qVi2qRuQXwmzdy73paRIDpnppx0oGmXdevlmNTwkev4I6zUEmGTi9wEudOSmY6eU87m5QxbO74L6FUlY0CBzYTWO9fCdnGz871D+Ni252SduvmBDNAexuOLC6YMWUGDUUrZxlZCj/3YgM2+g9x+E/sxwIDAQAB";
	String ALIPAY_URL = "https://openapi.alipay.com/gateway.do";
	String SIGN_TYPE= "RSA2";
	String FORMAT = "json";

	static {
		httpclient = new DefaultHttpClient();
		httpclient = (DefaultHttpClient) HttpClientConnectionManager.getSSLInstance(httpclient);
	}

	// 微信支付商户开通后 微信会提供appid和appsecret和商户号partner
	private static String appid = "xxxx";
	private static String appsecret = "xxxx";
	private static String partner = "xxxxx";
	// 这个参数partnerkey是在商户后台配置的一个32位的key,微信商户平台-账户设置-安全设置-api安全
	private static String partnerkey = "xxxxxxxx";
	// openId 是微信用户针对公众号的标识，授权的部分这里不解释
	private static String openId = "";
	// 微信支付成功后通知地址 必须要求80端口并且地址不能带参数

	public static void main(String[] args) {

		String str = getNonceStr();
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("mch_id", partner);
		packageParams.put("nonce_str", str);
		packageParams.put("sign_type", "MD5");

		RequestHandler reqHandler = new RequestHandler(null, null);
		reqHandler.init(appid, appsecret, partnerkey);
		String sign = reqHandler.createSign(packageParams);
		System.out.println(sign);
		String xmlParam = "<xml>" + "<mch_id>" + partner + "</mch_id>" + "<nonce_str>" + str + "</nonce_str>" + "<sign>"
				+ sign + "</sign>" + "</xml>";

		try {
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			FileInputStream instream = new FileInputStream(new File("E:\\apiclient_cert.p12"));
			try {
				keyStore.load(instream, partner.toCharArray());
			} finally {
				instream.close();
			}
			// Trust own CA and all self-signed certs
			SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, partner.toCharArray()).build();
			// Allow TLSv1 protocol only
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" },
					null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
			CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();

			String url = "https://fraud.mch.weixin.qq.com/risk/getpublickey";
			HttpPost httpost = HttpClientConnectionManager.getPostMethod(url);

			httpost.setEntity(new StringEntity(xmlParam, "UTF-8"));
			HttpResponse response = httpClient.execute(httpost);
			String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
			System.out.println("jsonStr==" + jsonStr);
			if (jsonStr.indexOf("FAIL") != -1) {
				System.out.println("FAIL");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取随机字符串
	 *
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
	@org.junit.Test
	public  void  test(){
		String a = null;
		System.out.println("0".equals(a));
	}
	@org.junit.Test
	public  void  test1(){
        Date date = new Date();
        System.out.println(date);
        String  a = "2018-11-21 16:09:00";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date parse = simpleDateFormat.parse(a);
            long time = date.getTime();
            System.out.println(time);
            long desc = date.getTime() - parse.getTime();
            System.out.println("shi"+desc);
            Double l1 = desc * 1.0 / (60 * 60 * 1000);
            if(l1 >= 24){
                System.out.println(l1);
            }
        } catch (ParseException e) {

        }
    }
    @org.junit.Test
    public  void  test2(){
	    Integer a = 2;
	    String b = "0.03";
        double v = a * Double.parseDouble(b);
        System.out.println(Double.toString(v));
    }
    @org.junit.Test
    public  void  test3(){
        String  a = "2018-11-21 16:09:00";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date today = null;
        try {
            today = simpleDateFormat.parse(a);
            Calendar c = Calendar.getInstance();
            c.setTime(today);
            c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
            Date tomorrow = c.getTime();
            System.out.println(simpleDateFormat.format(tomorrow));
        } catch (ParseException e) {
        }
    }
    @org.junit.Test
    public void test4(){
		String a = "";
		String[] split = a.split(";");
		for (int i = 0; i < 100; i++) {
			Random random = new Random();
			int i1 = random.nextInt(split.length);
			System.out.println(split[i1]);
		}
	}
}
