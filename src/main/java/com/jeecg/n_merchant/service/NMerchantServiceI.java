package com.jeecg.n_merchant.service;
import com.jeecg.n_merchant.entity.NMerchantEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface NMerchantServiceI extends CommonService{
	
 	public void delete(NMerchantEntity entity) throws Exception;
 	
 	public Serializable save(NMerchantEntity entity) throws Exception;
 	
 	public void saveOrUpdate(NMerchantEntity entity) throws Exception;
 	
}
