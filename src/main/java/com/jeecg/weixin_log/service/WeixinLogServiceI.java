package com.jeecg.weixin_log.service;
import com.jeecg.weixin_log.entity.WeixinLogEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface WeixinLogServiceI extends CommonService{
	
 	public void delete(WeixinLogEntity entity) throws Exception;
 	
 	public Serializable save(WeixinLogEntity entity) throws Exception;
 	
 	public void saveOrUpdate(WeixinLogEntity entity) throws Exception;
 	
}
