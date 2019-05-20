package com.jeecg.weixin_pay.service;
import com.jeecg.weixin_pay.entity.WeixinPayEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface WeixinPayServiceI extends CommonService{
	
 	public void delete(WeixinPayEntity entity) throws Exception;
 	
 	public Serializable save(WeixinPayEntity entity) throws Exception;
 	
 	public void saveOrUpdate(WeixinPayEntity entity) throws Exception;
 	
}
