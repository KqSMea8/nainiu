package com.jeecg.ce.service;
import com.jeecg.ce.entity.CeEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface CeServiceI extends CommonService{
	
 	public void delete(CeEntity entity) throws Exception;
 	
 	public Serializable save(CeEntity entity) throws Exception;
 	
 	public void saveOrUpdate(CeEntity entity) throws Exception;
 	
}
