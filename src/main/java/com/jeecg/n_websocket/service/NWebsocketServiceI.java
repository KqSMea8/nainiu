package com.jeecg.n_websocket.service;
import com.jeecg.n_websocket.entity.NWebsocketEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface NWebsocketServiceI extends CommonService{
	
 	public void delete(NWebsocketEntity entity) throws Exception;
 	
 	public Serializable save(NWebsocketEntity entity) throws Exception;
 	
 	public void saveOrUpdate(NWebsocketEntity entity) throws Exception;
 	
}
