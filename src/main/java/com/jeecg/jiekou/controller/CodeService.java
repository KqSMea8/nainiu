package com.jeecg.jiekou.controller;

import java.util.Date;

import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;

import test.CodeUtil;

@Service("codeService")
public class CodeService {
	
	/*@Autowired
	private QzCodeServiceI codeService;*/
	@Autowired
	private SystemService systemService;
	
	public String sendMsg(String phone,String code) throws ClientException,Exception{
	/*	QzCodeEntity entity = new QzCodeEntity();
		entity.setCreateDate(new Date());
		entity.setPhone(phone);
		entity.setCode(code);*/
		String status = "0";
		SendSmsResponse response = CodeUtil.sendSms(phone,code);
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
	
}
