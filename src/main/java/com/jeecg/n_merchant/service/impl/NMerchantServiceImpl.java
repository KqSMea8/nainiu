package com.jeecg.n_merchant.service.impl;
import com.jeecg.n_merchant.service.NMerchantServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.jeecg.n_merchant.entity.NMerchantEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.io.Serializable;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;

@Service("nMerchantService")
@Transactional
public class NMerchantServiceImpl extends CommonServiceImpl implements NMerchantServiceI {

	
 	public void delete(NMerchantEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(NMerchantEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(NMerchantEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(NMerchantEntity t) throws Exception{
		//-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(NMerchantEntity t) throws Exception{
		//-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(NMerchantEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(NMerchantEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("create_name", t.getCreateName());
		map.put("create_by", t.getCreateBy());
		map.put("update_name", t.getUpdateName());
		map.put("update_by", t.getUpdateBy());
		map.put("update_date", t.getUpdateDate());
		map.put("sys_org_code", t.getSysOrgCode());
		map.put("remarks", t.getRemarks());
		map.put("del_flag", t.getDelFlag());
		map.put("bpm_status", t.getBpmStatus());
		map.put("company", t.getCompany());
		map.put("shoptype", t.getShoptype());
		map.put("joinname", t.getJoinname());
		map.put("email", t.getEmail());
		map.put("phone", t.getPhone());
		map.put("card", t.getCard());
		map.put("urgency_name", t.getUrgencyName());
		map.put("urgency_phone", t.getUrgencyPhone());
		map.put("startshoptype", t.getStartshoptype());
		map.put("shifou", t.getShifou());
		map.put("card_z_url", t.getCardZUrl());
		map.put("card_f_url", t.getCardFUrl());
		map.put("card_b_url", t.getCardBUrl());
		map.put("boss_phone", t.getBossPhone());
		map.put("boss_email", t.getBossEmail());
		map.put("org", t.getOrg());
		map.put("org_url", t.getOrgUrl());
		map.put("bank_url", t.getBankUrl());
		map.put("ispay", t.getIspay());
		map.put("account_type", t.getAccountType());
		map.put("pid", t.getPid());
		map.put("role", t.getRole());
		map.put("action_money", t.getActionMoney());
		map.put("freeze_money", t.getFreezeMoney());
		map.put("account_status", t.getAccountStatus());
		map.put("audit_type", t.getAuditType());
		map.put("create_date", t.getCreateDate());
		map.put("jingying", t.getJingying());
		map.put("jingyingname", t.getJingyingname());
		map.put("details", t.getDetails());
		map.put("merchantlogo", t.getMerchantlogo());
		map.put("audit_content", t.getAuditContent());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,NMerchantEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{remarks}",String.valueOf(t.getRemarks()));
 		sql  = sql.replace("#{del_flag}",String.valueOf(t.getDelFlag()));
 		sql  = sql.replace("#{bpm_status}",String.valueOf(t.getBpmStatus()));
 		sql  = sql.replace("#{company}",String.valueOf(t.getCompany()));
 		sql  = sql.replace("#{shoptype}",String.valueOf(t.getShoptype()));
 		sql  = sql.replace("#{joinname}",String.valueOf(t.getJoinname()));
 		sql  = sql.replace("#{email}",String.valueOf(t.getEmail()));
 		sql  = sql.replace("#{phone}",String.valueOf(t.getPhone()));
 		sql  = sql.replace("#{card}",String.valueOf(t.getCard()));
 		sql  = sql.replace("#{urgency_name}",String.valueOf(t.getUrgencyName()));
 		sql  = sql.replace("#{urgency_phone}",String.valueOf(t.getUrgencyPhone()));
 		sql  = sql.replace("#{startshoptype}",String.valueOf(t.getStartshoptype()));
 		sql  = sql.replace("#{shifou}",String.valueOf(t.getShifou()));
 		sql  = sql.replace("#{card_z_url}",String.valueOf(t.getCardZUrl()));
 		sql  = sql.replace("#{card_f_url}",String.valueOf(t.getCardFUrl()));
 		sql  = sql.replace("#{card_b_url}",String.valueOf(t.getCardBUrl()));
 		sql  = sql.replace("#{boss_phone}",String.valueOf(t.getBossPhone()));
 		sql  = sql.replace("#{boss_email}",String.valueOf(t.getBossEmail()));
 		sql  = sql.replace("#{org}",String.valueOf(t.getOrg()));
 		sql  = sql.replace("#{org_url}",String.valueOf(t.getOrgUrl()));
 		sql  = sql.replace("#{bank_url}",String.valueOf(t.getBankUrl()));
 		sql  = sql.replace("#{ispay}",String.valueOf(t.getIspay()));
 		sql  = sql.replace("#{account_type}",String.valueOf(t.getAccountType()));
 		sql  = sql.replace("#{pid}",String.valueOf(t.getPid()));
 		sql  = sql.replace("#{role}",String.valueOf(t.getRole()));
 		sql  = sql.replace("#{action_money}",String.valueOf(t.getActionMoney()));
 		sql  = sql.replace("#{freeze_money}",String.valueOf(t.getFreezeMoney()));
 		sql  = sql.replace("#{account_status}",String.valueOf(t.getAccountStatus()));
 		sql  = sql.replace("#{audit_type}",String.valueOf(t.getAuditType()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{jingying}",String.valueOf(t.getJingying()));
 		sql  = sql.replace("#{jingyingname}",String.valueOf(t.getJingyingname()));
 		sql  = sql.replace("#{details}",String.valueOf(t.getDetails()));
 		sql  = sql.replace("#{merchantlogo}",String.valueOf(t.getMerchantlogo()));
 		sql  = sql.replace("#{audit_content}",String.valueOf(t.getAuditContent()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
 	
 	/**
	 * 执行JAVA增强
	 */
 	private void executeJavaExtend(String cgJavaType,String cgJavaValue,Map<String,Object> data) throws Exception {
 		if(StringUtil.isNotEmpty(cgJavaValue)){
			Object obj = null;
			try {
				if("class".equals(cgJavaType)){
					//因新增时已经校验了实例化是否可以成功，所以这块就不需要再做一次判断
					obj = MyClassLoader.getClassByScn(cgJavaValue).newInstance();
				}else if("spring".equals(cgJavaType)){
					obj = ApplicationContextUtil.getContext().getBean(cgJavaValue);
				}
				if(obj instanceof CgformEnhanceJavaInter){
					CgformEnhanceJavaInter javaInter = (CgformEnhanceJavaInter) obj;
					javaInter.execute("n_merchant",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}