package com.jeecg.n_area_code.service;
import com.jeecg.n_area_code.entity.NAreaCodeEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface NAreaCodeServiceI extends CommonService{
	
 	public void delete(NAreaCodeEntity entity) throws Exception;
 	
 	public Serializable save(NAreaCodeEntity entity) throws Exception;
 	
 	public void saveOrUpdate(NAreaCodeEntity entity) throws Exception;
 	
}
