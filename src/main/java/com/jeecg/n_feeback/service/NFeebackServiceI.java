package com.jeecg.n_feeback.service;
import com.jeecg.n_feeback.entity.NFeebackEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface NFeebackServiceI extends CommonService{
	
 	public void delete(NFeebackEntity entity) throws Exception;
 	
 	public Serializable save(NFeebackEntity entity) throws Exception;
 	
 	public void saveOrUpdate(NFeebackEntity entity) throws Exception;
 	
}
