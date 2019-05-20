package com.jeecg.n_faq.service;
import com.jeecg.n_faq.entity.NFaqEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface NFaqServiceI extends CommonService{
	
 	public void delete(NFaqEntity entity) throws Exception;
 	
 	public Serializable save(NFaqEntity entity) throws Exception;
 	
 	public void saveOrUpdate(NFaqEntity entity) throws Exception;
 	
}
