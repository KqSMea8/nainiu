package com.jeecg.cea.service.impl;
import com.jeecg.cea.service.CeaServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.jeecg.cea.entity.CeaEntity;
import com.jeecg.ceb.entity.CebEntity;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import java.util.ArrayList;
import java.util.UUID;
import java.io.Serializable;


@Service("ceaService")
@Transactional
public class CeaServiceImpl extends CommonServiceImpl implements CeaServiceI {
	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((CeaEntity)entity);
 	}
	
	public void addMain(CeaEntity cea,
	        List<CebEntity> cebList){
			//保存主信息
			this.save(cea);
		
			/**保存-测试*/
			for(CebEntity ceb:cebList){
				//外键设置
				ceb.setPkId(cea.getId());
				this.save(ceb);
			}
			//执行新增操作配置的sql增强
 			this.doAddSql(cea);
	}

	
	public void updateMain(CeaEntity cea,
	        List<CebEntity> cebList) {
		//保存主表信息
		this.saveOrUpdate(cea);
		//===================================================================================
		//获取参数
		Object id0 = cea.getId();
		//===================================================================================
		//1.查询出数据库的明细数据-测试
	    String hql0 = "from CebEntity where 1 = 1 AND pK_ID = ? ";
	    List<CebEntity> cebOldList = this.findHql(hql0,id0);
		//2.筛选更新明细数据-测试
		if(cebList!=null&&cebList.size()>0){
		for(CebEntity oldE:cebOldList){
			boolean isUpdate = false;
				for(CebEntity sendE:cebList){
					//需要更新的明细数据-测试
					if(oldE.getId().equals(sendE.getId())){
		    			try {
							MyBeanUtils.copyBeanNotNull2Bean(sendE,oldE);
							this.saveOrUpdate(oldE);
						} catch (Exception e) {
							e.printStackTrace();
							throw new BusinessException(e.getMessage());
						}
						isUpdate= true;
		    			break;
		    		}
		    	}
	    		if(!isUpdate){
		    		//如果数据库存在的明细，前台没有传递过来则是删除-测试
		    		super.delete(oldE);
	    		}
	    		
			}
			//3.持久化新增的数据-测试
			for(CebEntity ceb:cebList){
				if(oConvertUtils.isEmpty(ceb.getId())){
					//外键设置
					ceb.setPkId(cea.getId());
					this.save(ceb);
				}
			}
		}
		//执行更新操作配置的sql增强
 		this.doUpdateSql(cea);
	}

	
	public void delMain(CeaEntity cea) {
		//删除主表信息
		this.delete(cea);
		//===================================================================================
		//获取参数
		Object id0 = cea.getId();
		//===================================================================================
		//删除-测试
	    String hql0 = "from CebEntity where 1 = 1 AND pK_ID = ? ";
	    List<CebEntity> cebOldList = this.findHql(hql0,id0);
		this.deleteAllEntitie(cebOldList);
	}
	
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(CeaEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(CeaEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(CeaEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,CeaEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{remarks}",String.valueOf(t.getRemarks()));
 		sql  = sql.replace("#{del_flag}",String.valueOf(t.getDelFlag()));
 		sql  = sql.replace("#{bpm_status}",String.valueOf(t.getBpmStatus()));
 		sql  = sql.replace("#{title}",String.valueOf(t.getTitle()));
 		sql  = sql.replace("#{contents}",String.valueOf(t.getContents()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}