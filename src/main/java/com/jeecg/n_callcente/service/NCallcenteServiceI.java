package com.jeecg.n_callcente.service;
import com.jeecg.n_callcente.entity.NCallcenteEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface NCallcenteServiceI extends CommonService{
	
 	public void delete(NCallcenteEntity entity) throws Exception;
 	
 	public Serializable save(NCallcenteEntity entity) throws Exception;
 	
 	public void saveOrUpdate(NCallcenteEntity entity) throws Exception;
 	
}
