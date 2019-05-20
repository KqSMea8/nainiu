package com.jeecg.n_rule.service;
import com.jeecg.n_rule.entity.NRuleEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface NRuleServiceI extends CommonService{
	
 	public void delete(NRuleEntity entity) throws Exception;
 	
 	public Serializable save(NRuleEntity entity) throws Exception;
 	
 	public void saveOrUpdate(NRuleEntity entity) throws Exception;
 	
}
