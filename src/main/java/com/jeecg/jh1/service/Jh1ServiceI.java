package com.jeecg.jh1.service;
import com.jeecg.jh1.entity.Jh1Entity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface Jh1ServiceI extends CommonService{
	
 	public void delete(Jh1Entity entity) throws Exception;
 	
 	public Serializable save(Jh1Entity entity) throws Exception;
 	
 	public void saveOrUpdate(Jh1Entity entity) throws Exception;
 	
}
