package com.jeecg.n_standard.service;
import com.jeecg.n_standard.entity.NStandardEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface NStandardServiceI extends CommonService{
	
 	public void delete(NStandardEntity entity) throws Exception;
 	
 	public Serializable save(NStandardEntity entity) throws Exception;
 	
 	public void saveOrUpdate(NStandardEntity entity) throws Exception;
 	
}
