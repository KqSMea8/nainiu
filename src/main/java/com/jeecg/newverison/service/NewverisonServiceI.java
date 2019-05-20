package com.jeecg.newverison.service;
import com.jeecg.newverison.entity.NewverisonEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface NewverisonServiceI extends CommonService{
	
 	public void delete(NewverisonEntity entity) throws Exception;
 	
 	public Serializable save(NewverisonEntity entity) throws Exception;
 	
 	public void saveOrUpdate(NewverisonEntity entity) throws Exception;
 	
}
