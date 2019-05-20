package com.jeecg.weixin;

import org.apache.log4j.Logger;
import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.InputStreamReader;  
import java.io.OutputStream;  
import java.net.ConnectException;  
import java.net.URL;  
  
import javax.net.ssl.HttpsURLConnection;  
import javax.net.ssl.SSLContext;  
import javax.net.ssl.SSLSocketFactory;  
import javax.net.ssl.TrustManager;  
  
import net.sf.json.JSONObject;  
  
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;  
import org.apache.commons.httpclient.HttpClient;  
import org.apache.commons.httpclient.HttpException;  
import org.apache.commons.httpclient.HttpStatus;  
import org.apache.commons.httpclient.NameValuePair;  
import org.apache.commons.httpclient.methods.GetMethod;  
import org.apache.commons.httpclient.methods.PostMethod;  
import org.apache.commons.httpclient.params.HttpMethodParams; 

public class HttpClientUtil {
	/**
	 * Logger for this class
	 */
	 private static final Logger logger = Logger.getLogger(HttpClientUtil.class);  
	
	    /** 
	     * http get 方式交互 
	     */  
	    public static String getHTTP(String URL) {  
	        String responseMsg = "";  
	  
	        // 构造HTTPClient的实例  
	        HttpClient httpClient = new HttpClient(); 
	        
	        GetMethod getmethod = new GetMethod(URL);
	  
	        // 使用系统系统的默认的恢复策略  
	        getmethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());  
	        getmethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");  
	        try {  
	            // ִ执行 HTTPClient方法，调用HTTP接口 
	            httpClient.executeMethod(getmethod);  
	            // 读取返回的内容  
	            String responseBody = getmethod.getResponseBodyAsString();  
	            System.out.println("String==="+responseBody);
	            // 处理返回内容  
//	            responseMsg = new String(responseBody.getBytes("ISO-8859-1"),"utf-8");  
	            responseMsg = responseBody;
	            // 返回结果显示  
	             System.out.println("HTTP GET 方式执行结果："+responseMsg);  
	        } catch (HttpException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } finally {  
	            // 释放操作  
	            getmethod.releaseConnection();  
	        }  
	        return responseMsg;  
	    }  
	  
	    public static String getHTTP_New(String URL) {  
	        String responseMsg = "";  
	        HttpClient httpClient = new HttpClient();  
	        // 创建GET方法的实例  
	        GetMethod getMethod = new GetMethod(URL);  
	        // 此处可以在getMethod上添加请求参数  
	        try {  
	            // 执行getMethod  
	            int statusCode = httpClient.executeMethod(getMethod);  
	            if (statusCode != HttpStatus.SC_OK) {  
	                System.err.println("Method failed: " + getMethod.getStatusLine());  
	            }  
	            // 读取内容  
	            byte[] responseBody = getMethod.getResponseBody();  
	            // 处理内容  
	            responseMsg = new String(responseBody);  
	        } catch (HttpException e) {  
	            // 发生致命的异常，可能是协议不对或者返回的内容有问题  
	            System.out.println("Please check your provided http address!");  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            // 发生网络异常  
	            e.printStackTrace();  
	        } finally {  
	            // 释放连接  
	            getMethod.releaseConnection();  
	        }  
	        return responseMsg;  
	    }  
	  
	    // HTTP 通过POST方式交互  
	    /** 
	     * http post 方式交互 
	     */  
	    public static String postHTTP(String URL, String uid, String pwd, String tos, String content, String otime) {  
	        String ResultStrMsg = "";  
	  
	        // 1.构造HttpClient的实例  
	        HttpClient httpClient = new HttpClient();  
	        httpClient.getParams().setContentCharset("GB2312");  
	  
	        PostMethod method = new PostMethod(URL);  
	  
	        // 把参数值放入到PostMethod对象中  
	  
	        // 方式一  
	        NameValuePair[] dataparam = { new NameValuePair("id", uid), new NameValuePair("pwd", pwd),   
	                new NameValuePair("to", tos), new NameValuePair("content", content), new NameValuePair("time", otime) };  
	  
	        method.addParameters(dataparam);  
	  
	        // 方式二  
	        // method.addParameter("", "");  
	        // method.addParameter("", "");  
	  
	        try {  
	            // 执行接口方法，调用接口方法  
	            httpClient.executeMethod(method);  
	            // 读取返回的值ֵ  
	            ResultStrMsg = method.getResponseBodyAsString().trim();  
	  
	            // System.out.println("HTTP POST 方式执行结果："+ResultStrMsg);  
	        } catch (HttpException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        } finally {  
	            method.releaseConnection();  
	        }  
	        return ResultStrMsg;  
	    }  
	  
	    public static String postHTTP_new(String URL, String uid, String pwd, String tos, String content, String otime) {  
	        String ResultStrMsg = "";  
	  
	        // 1.构造HttpClient的实例  
	        HttpClient httpClient = new HttpClient();  
	        httpClient.getParams().setContentCharset("GB2312");  
	  
	        PostMethod method = new PostMethod(URL);  
	  
	        // 把参数值放入到PostMethod对象中  
	  
	        // 方式一  
	        NameValuePair[] dataparam = { new NameValuePair("account", uid), new NameValuePair("pswd", pwd),   
	                new NameValuePair("mobile", tos), new NameValuePair("msg", content), new NameValuePair("needstatus", otime) };  
	  
	        method.addParameters(dataparam);  
	  
	        // 方式二  
	        // method.addParameter("", "");  
	        // method.addParameter("", "");  
	  
	        try {  
	            // 执行接口方法，调用接口方法  
	            httpClient.executeMethod(method);  
	            // 读取返回的值ֵ  
	            ResultStrMsg = method.getResponseBodyAsString().trim();  
	  
	            // System.out.println("HTTP POST 方式执行结果："+ResultStrMsg);  
	        } catch (HttpException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        } finally {  
	            method.releaseConnection();  
	        }  
	        return ResultStrMsg;  
	    }  
	  
	
	
	 
}
