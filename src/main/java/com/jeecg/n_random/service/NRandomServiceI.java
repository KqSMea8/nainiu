package com.jeecg.n_random.service;
import com.jeecg.n_random.entity.NRandomEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface NRandomServiceI extends CommonService{
	
 	public void delete(NRandomEntity entity) throws Exception;
 	
 	public Serializable save(NRandomEntity entity) throws Exception;
 	
 	public void saveOrUpdate(NRandomEntity entity) throws Exception;
 	
}
