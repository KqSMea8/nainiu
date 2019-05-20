package com.jeecg.cea.service;
import com.jeecg.cea.entity.CeaEntity;
import com.jeecg.ceb.entity.CebEntity;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import java.io.Serializable;

public interface CeaServiceI extends CommonService{
	
 	public <T> void delete(T entity);
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(CeaEntity cea,
	        List<CebEntity> cebList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(CeaEntity cea,
	        List<CebEntity> cebList);
	public void delMain (CeaEntity cea);
	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(CeaEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(CeaEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(CeaEntity t);
}
