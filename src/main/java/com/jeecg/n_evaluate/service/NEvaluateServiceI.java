package com.jeecg.n_evaluate.service;
import com.jeecg.n_evaluate.entity.NEvaluateEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface NEvaluateServiceI extends CommonService{
	
 	public void delete(NEvaluateEntity entity) throws Exception;
 	
 	public Serializable save(NEvaluateEntity entity) throws Exception;
 	
 	public void saveOrUpdate(NEvaluateEntity entity) throws Exception;
 	
}
