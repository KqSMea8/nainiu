package com.jeecg.n_user_price.service;
import com.jeecg.n_user_price.entity.NUserPriceEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface NUserPriceServiceI extends CommonService{
	
 	public void delete(NUserPriceEntity entity) throws Exception;
 	
 	public Serializable save(NUserPriceEntity entity) throws Exception;
 	
 	public void saveOrUpdate(NUserPriceEntity entity) throws Exception;
 	
}
