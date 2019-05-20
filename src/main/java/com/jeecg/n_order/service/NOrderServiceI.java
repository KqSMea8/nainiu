package com.jeecg.n_order.service;
import com.jeecg.n_order.entity.NOrderEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface NOrderServiceI extends CommonService{
	
 	public void delete(NOrderEntity entity) throws Exception;
 	
 	public Serializable save(NOrderEntity entity) throws Exception;
 	
 	public void saveOrUpdate(NOrderEntity entity) throws Exception;
 	
}
