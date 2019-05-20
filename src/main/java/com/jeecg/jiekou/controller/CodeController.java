package com.jeecg.jiekou.controller;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.PasswordUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSRole;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import test.CodeUtil;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.jeecg.n_goods_detaisl.entity.NGoodsDetaislEntity;
import com.jeecg.n_home_banner.entity.NHomeBannerEntity;
import com.jeecg.n_phone_code.entity.NPhoneCodeEntity;
import com.jeecg.n_standard_details.entity.NStandardDetailsEntity;
import com.jeecg.n_user.entity.NUserEntity;
import com.jeecg.newverison.entity.NewverisonEntity;
import com.jeecg.s_tuisong.entity.STuisongEntity;

/**
 * @Title: Controller
 * @Description: 接口
 * @author onlineGenerator
 * @date 2017-08-18 17:45:08
 * @version V1.0
 *
 */
@Controller
@RequestMapping("/codecontroller")
public class CodeController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(CodeController.class);

	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;

	

	/* 修改密码 */
	@RequestMapping(params = "phocode")
	public void updatepassword(HttpServletRequest request,
			HttpServletResponse response, String phone ) {
		JSONObject object = new JSONObject();
		String message = "获得验证码成功";
		String success = "success";
		String code = getRandomStr(6, 0);
		try {
			//0验证码1注册验证码，2提现验证码、3广告资讯
			NPhoneCodeEntity nPhoneCode=new NPhoneCodeEntity();
			nPhoneCode.setId(StringUtil.getId());
			nPhoneCode.setCreateDate(new Date());
			nPhoneCode.setPhone(phone);
			nPhoneCode.setPhoneType("0");
			nPhoneCode.setCode(code);
			systemService.save(nPhoneCode);
			String type=sendMsg(phone, code);
			if("1".equals(type)){
				success="fail";
				message="获得验证码失败";
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		object.put("status", success);
		object.put("message", message);
		TagUtil.getSendJson(response, object.toString());
	}
	public String sendMsg(String phone,String code) throws ClientException,Exception{
		String status = "0";
		SendSmsResponse response = CodeUtil.sendSms(phone,code);
		System.out.println("response.getCode()=="+response.getCode());
		if (response.getCode() != null && response.getCode().equals("OK")) {
			//codeService.save(entity);
			systemService.addLog("发送成功", Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}else{
			if("isv.MOBILE_NUMBER_ILLEGAL".equals(response.getCode())){
				status = "1";
			}
			systemService.addLog("发送失败。"+"Code=" + response.getCode()+"Message=" + response.getMessage(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		return status;
	} 
	public String sendSuccess(String phone,String name,String pwd) throws ClientException,Exception{
		String status = "0";
		SendSmsResponse response = CodeUtil.sendSuccess(phone,name,pwd);
		System.out.println("response.getCode()=="+response.getCode());
		if (response.getCode() != null && response.getCode().equals("OK")) {
			//codeService.save(entity);
			systemService.addLog("发送成功", Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}else{
			if("isv.MOBILE_NUMBER_ILLEGAL".equals(response.getCode())){
				status = "1";
			}
			systemService.addLog("发送失败。"+"Code=" + response.getCode()+"Message=" + response.getMessage(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		return status;
	} 
	public String sendFail(String phone,String name) throws ClientException,Exception{
		String status = "0";
		SendSmsResponse response = CodeUtil.sendFail(phone,name);
		System.out.println("response.getCode()=="+response.getCode());
		if (response.getCode() != null && response.getCode().equals("OK")) {
			//codeService.save(entity);
			systemService.addLog("发送成功", Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}else{
			if("isv.MOBILE_NUMBER_ILLEGAL".equals(response.getCode())){
				status = "1";
			}
			systemService.addLog("发送失败。"+"Code=" + response.getCode()+"Message=" + response.getMessage(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		return status;
	} 
	public static String getRandomStr(int length, int type)
    {
        String str = "";
        int beginChar = 'a';
        int endChar = 'z';
        // 只有数字
        if (type == 0)
        {
            beginChar = 'z' + 1;
            endChar = 'z' + 10;
        }
        // 只有小写字母
        else if (type == 2)
        {
            beginChar = 'a';
            endChar = 'z';
        }
        // 有数字和字母
        else
        {
            beginChar = 'a';
            endChar = 'z' + 10;
        }
 
        // 生成随机类
 
        Random random = new Random();
        for (int i = 0; i < length; i++)
        {
            int tmp = (beginChar + random.nextInt(endChar - beginChar));
            // 大于'z'的是数字
            if (tmp > 'z')
            {
                tmp = '0' + (tmp - 'z');
            }
            str += (char) tmp;
        }
 
        return str;
    }
}
