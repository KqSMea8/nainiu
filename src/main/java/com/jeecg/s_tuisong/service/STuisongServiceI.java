package com.jeecg.s_tuisong.service;
import com.jeecg.s_tuisong.entity.STuisongEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface STuisongServiceI extends CommonService{
	
 	public void delete(STuisongEntity entity) throws Exception;
 	
 	public Serializable save(STuisongEntity entity) throws Exception;
 	
 	public void saveOrUpdate(STuisongEntity entity) throws Exception;
 	
}
