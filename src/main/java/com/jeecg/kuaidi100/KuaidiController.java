package com.jeecg.kuaidi100;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
@RequestMapping("/kuaidiController")
public class KuaidiController extends BaseController {

	private static final String CUSTOMER = "7D384811C35C48BF1DA5F36FD1852B5A";
	private static final String KEY = "LPwqIHRq3737";

	@RequestMapping(params="doSearch")
	public void doSearch(HttpServletRequest req, HttpServletResponse resp, String com,String num) {
		try {
			System.out.println("超级皮卡丘++++++++++++++++++++++++++++++");
			String param = "{\"com\":\""+com+"\",\"num\":\""+num+"\",\"from\":\"\",\"to\":\"\"}";
			System.out.println(param);
			HashMap<String,String> params = new HashMap<String, String>();
			params.put("param", param);
			String sign = MD5.encode(param+KEY+CUSTOMER);
			System.out.println(sign);
			params.put("sign", sign);
			params.put("customer", CUSTOMER);
			String return_msg = new HttpRequest().postData("http://poll.kuaidi100.com/poll/query.do", params, "utf-8").toString();
			System.out.println(return_msg);
			TagUtil.getSendJson(resp, return_msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public String md5(String str) throws UnsupportedEncodingException, NoSuchAlgorithmException{
		MessageDigest md5=MessageDigest.getInstance("MD5");
		String reStr = md5.digest(str.getBytes("utf-8")).toString();
		return reStr;
	}
	

}
