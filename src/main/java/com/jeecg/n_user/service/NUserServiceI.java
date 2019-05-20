package com.jeecg.n_user.service;
import com.jeecg.n_user.entity.NUserEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface NUserServiceI extends CommonService{
	
 	public void delete(NUserEntity entity) throws Exception;
 	
 	public Serializable save(NUserEntity entity) throws Exception;
 	
 	public void saveOrUpdate(NUserEntity entity) throws Exception;
 	
}
