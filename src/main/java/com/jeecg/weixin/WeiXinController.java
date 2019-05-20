package com.jeecg.weixin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jeecgframework.core.util.*;
import org.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.common.TreeChildCount;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.vo.TemplateExcelConstants;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Map;
import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.jeecgframework.core.beanvalidator.BeanValidators;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

import com.alibaba.fastjson.JSONArray;
import com.jeecg.weixin.util.WeixinUtil;

/**   
 * @Title: Controller  
 * @Description: 天华用户
 * @author onlineGenerator
 * @date 2017-07-11 13:56:59
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/weixin")
public class WeiXinController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WeiXinController.class);
	@Autowired
	private SystemService systemService;
	@Autowired
	private WeixinUtil weixinutil;
	 String appId= WebUtils.APP_ID;
	 String APPSECRET= WebUtils.APPSECRET;
	/**
	 * 获得签名
	 */
	@RequestMapping(params = "GetWxJsApi")
	@ResponseBody
	public void GetWxJsApi(HttpSession httpSession, HttpServletRequest req,HttpServletResponse resp,String sendurl){
		 JSONObject json = new JSONObject();
		 //公众号的唯一标识
		// String appId="wxcb34a82963580078";
	
		 /**获取access_token
		  * https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
		  * */
		/*  String  access_token =null;
		 if (null == httpSession.getAttribute("access_token")) {  
				 String result1 = HttpClientUtil.getHTTP("https://api.weixin.qq.com/cgi-bin/token"
				 		+"?grant_type=client_credential&appid="+appId+"&secret="+APPSECRET);
				 JSONObject obj1 = new JSONObject(result1);  
				 System.out.println(obj1.toString());
		          access_token = obj1.get("access_token").toString();  
		          httpSession.setAttribute("access_token", access_token);  
	        } else {  
	            access_token = httpSession.getAttribute("access_token").toString();  
	        }  
		 */
			//String access_token=WeixinUtil.getAccessToken(systemService).getAccessToken();
			String access_token=weixinutil.getAccessToken().getAccessToken();
		  String jsapi_ticket = null;  
	        if (null == httpSession.getAttribute("jsapi_ticket")) {  
	            // 第二步根据token得到jsapi_ticket存入全局缓存  
	            String result2 = HttpClientUtil.getHTTP("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + access_token + "&type=jsapi");  
	            JSONObject obj2 = new JSONObject(result2);  
	            jsapi_ticket = obj2.get("ticket").toString();  
	            httpSession.setAttribute("jsapi_ticket", jsapi_ticket);  
	        } else {  
	            jsapi_ticket = httpSession.getAttribute("jsapi_ticket").toString();  
	        }  
	        // 获取请求的地址  
	        StringBuffer url = req.getRequestURL();  
	        String contextUrl = url.delete(url.length() - req.getRequestURI().length(), url.length()).toString();  
	        String httpUrl = contextUrl + req.getRequestURI();  
	        System.out.println("httpUrl=="+httpUrl);
	     // 签名算法  
	        System.out.println("sendurl"+sendurl);
	        Map<String, String> map = Sign.sign(jsapi_ticket, sendurl);  
	        
		 json.put("appId", appId);
		 json.put("jsapi_ticket", map.get("jsapi_ticket"));
		 json.put("timestamp", map.get("timestamp"));
		 json.put("noncestr", map.get("nonceStr"));
		 json.put("signature", map.get("signature"));
		 json.put("newDate", new Date().getTime());
      //  TagUtil.getSendJson(resp, json.toString());
			resp.setContentType("application/json");
			resp.setHeader("Access-Control-Allow-Origin", "*");
			PrintWriter pw = null;
			try {
				pw=resp.getWriter();
				pw.write(json.toString());
				pw.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					pw.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	}
	
/*	  public  String getOnlyToken(HttpSession httpSession) throws Exception {  
	        String access_token = null;  
	        if (null == httpSession.getAttribute("access_token")) {  
	            // 第一步获取token存入全局缓存，  
	        	String result1 = HttpClientUtil.getHTTP(" https://api.weixin.qq.com/cgi-bin/token"
				 		+"?grant_type=client_credential&appid="+appId+"&secret="+APPSECRET);
	            org.json.JSONObject obj1 = new JSONObject(result1);  
	            access_token = obj1.get("access_token").toString();  
	            httpSession.setAttribute("access_token", access_token);  
	        } else {  
	            access_token = httpSession.getAttribute("access_token").toString();  
	        }  
	          
	        return access_token;  
	    }  
*/
	  
	  
	  /**
		 * 发送信息
		 */
		@RequestMapping(params = "sendMess")
		@ResponseBody
		public void sendMess(HttpSession httpSession, HttpServletRequest req,
				HttpServletResponse resp,String openid){
			try {
			   	String td="{\"msgid\":304829031218741251,\"errmsg\":\"ok\",\"errcode\":0}";
			   	JSONObject ejson=new JSONObject(td);
			   	System.out.println("ejson=="+ejson.get("errcode"));
			//	String access_token=getOnlyToken(httpSession);
				//获取access_token
			//	String access_token=WeixinUtil.getAccessToken(systemService).getAccessToken();
				String access_token=weixinutil.getAccessToken().getAccessToken();
				System.out.println("access_token=="+access_token);
				String url="https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+access_token;
				/***{
    "touser":"OPENID",
    "msgtype":"text",
    "text":
    {
         "content":"Hello World"
    }
}*/   
				System.out.println("openid==="+openid);
				JSONObject json=new JSONObject();
				json.put("touser", openid);
				json.put("msgtype", "text");
			//	JSONArray arry = new JSONArray();
				JSONObject text_json=new JSONObject();
				text_json.put("content", "Hello World");
			//	arry.add(text_json);
				json.put("text", text_json);
				System.out.println("json=="+json.toString());
				System.out.println("url=="+url);
				JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", json.toString());
	        	System.out.println("...jsonObject..."+jsonObject.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		  /**
			 * 发送模板消息
			 */
			@RequestMapping(params = "sendTemplate")
			@ResponseBody
			public void sendTemplate(HttpSession httpSession, HttpServletRequest req,
					HttpServletResponse resp,String openid){
				try {
					//String access_token=getOnlyToken(httpSession);
					//获取access_token
				//	String access_token=WeixinUtil.getAccessToken(systemService).getAccessToken();
					String access_token=weixinutil.getAccessToken().getAccessToken();
					System.out.println("access_token=="+access_token);
					String url="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token;
					/***  {
           "touser":"OPENID",
           "template_id":"ngqIpbwh8bUfcSsECmogfXcV14J0tQlEpBO27izEYtY",
           "url":"http://weixin.qq.com/download",          
           "data":{
                   "first": {
                       "value":"恭喜你购买成功！",
                       "color":"#173177"
                   },
                   "keyword1":{
                       "value":"巧克力",
                       "color":"#173177"
                   },
                   "keyword2": {
                       "value":"39.8元",
                       "color":"#173177"
                   },
                   "keyword3": {
                       "value":"2014年9月22日",
                       "color":"#173177"
                   },
                   "remark":{
                       "value":"欢迎再次购买！",
                       "color":"#173177"
                   }
           }
       }*/   
					System.out.println("openid==="+openid);
					String first="first";
					String keyword1="keyword1";
					String keyword2="keyword2";
					String keyword3="keyword3";
					String keyword4="keyword4";
					String remark="remark";
					StringBuffer str=new StringBuffer();
					str.append("{\"touser\":\"")
					   .append(openid)
					   .append("\",")
					   .append("\"template_id\":\"3U6lDeRPv5RzR-_eLdDyNztJizfxKOpqBQDP8rWbE_8\",")
					   .append("\"url\":\"")
					   .append(url)
					   .append("\",")
					   .append("\"data\":{")
								   .append("\"first\":{")
		                                     .append("\"value\":\"")
		                                     .append(first)
		            		                 .append("\",")
		            		                .append("\"color\":\"#173177\"")
		                           .append("},")
		                             .append("\"keyword1\":{")
		                                     .append("\"value\":\"")
		                                     .append(keyword1)
		            		                 .append("\",")
		            		                .append("\"color\":\"#173177\"")
		                           .append("},")
		                             .append("\"keyword2\":{")
		                                     .append("\"value\":\"")
		                                     .append(keyword2)
		            		                 .append("\",")
		            		                .append("\"color\":\"#173177\"")
		                           .append("},")
								    .append("\"keyword3\":{")
		                                     .append("\"value\":\"")
		                                     .append(keyword3)
		            		                 .append("\",")
		            		                .append("\"color\":\"#173177\"")
		                           .append("},")
		                               .append("\"keyword4\":{")
		                                     .append("\"value\":\"")
		                                     .append(keyword4)
		            		                 .append("\",")
		            		                .append("\"color\":\"#173177\"")
		                           .append("},")
		                             .append("\"remark\":{")
		                                     .append("\"value\":\"")
		                                     .append(remark)
		            		                 .append("\",")
		            		                .append("\"color\":\"#173177\"")
		                           .append("}")
					     .append("}")
					  .append("}");
					System.out.println("str==="+str.toString());
					
					JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", str.toString());
		        	System.out.println("...jsonObject..."+jsonObject.toString());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			
			
		
	  /** 
     *  
     * 根据文件id下载文件 
     *  
     *  
     *  
     * @param mediaId 
     *  
     *            媒体id 
     *  
     * @throws Exception 
     */  
  
    public  InputStream getInputStream(String accessToken, String mediaId) {  
    	
        InputStream is = null;  
        String url = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token="  
                + accessToken + "&media_id=" + mediaId;  
        try {  
            URL urlGet = new URL(url);  
            HttpURLConnection http = (HttpURLConnection) urlGet  
                    .openConnection();  
            http.setRequestMethod("GET"); // 必须是get方式请求  
            http.setRequestProperty("Content-Type",  
                    "application/x-www-form-urlencoded");  
            http.setDoOutput(true);  
            http.setDoInput(true);  
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒  
            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒  
            http.connect();  
            // 获取文件转化为byte流  
            is = http.getInputStream();  
  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return is;  
  
    }  
  
    /** 
     *  
     * 获取下载图片信息（jpg） 
     *  
     *  
     *  
     * @param mediaId 
     *  
     *            文件的id 
     *  
     * @throws Exception 
     */  
  
    public  void saveImageToDisk(String accessToken, String mediaId, String picName, String picPath)  
            throws Exception {  
        InputStream inputStream = getInputStream(accessToken, mediaId);  
        byte[] data = new byte[10240];  
        int len = 0;  
        FileOutputStream fileOutputStream = null;  
        try {  
            fileOutputStream = new FileOutputStream(picPath+picName+".jpg");  
            while ((len = inputStream.read(data)) != -1) {  
                fileOutputStream.write(data, 0, len);  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (inputStream != null) {  
                try {  
                    inputStream.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
            if (fileOutputStream != null) {  
                try {  
                    fileOutputStream.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }  
    
    
}
