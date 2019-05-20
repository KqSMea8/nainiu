package com.jeecg.n_news.service;
import com.jeecg.n_news.entity.NNewsEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface NNewsServiceI extends CommonService{
	
 	public void delete(NNewsEntity entity) throws Exception;
 	
 	public Serializable save(NNewsEntity entity) throws Exception;
 	
 	public void saveOrUpdate(NNewsEntity entity) throws Exception;
 	
}
