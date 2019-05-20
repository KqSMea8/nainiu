package com.jeecg.n_order.service.impl;
import com.jeecg.n_order.service.NOrderServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.jeecg.n_order.entity.NOrderEntity;
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

@Service("nOrderService")
@Transactional
public class NOrderServiceImpl extends CommonServiceImpl implements NOrderServiceI {

	
 	public void delete(NOrderEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(NOrderEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(NOrderEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(NOrderEntity t) throws Exception{
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
	private void doUpdateBus(NOrderEntity t) throws Exception{
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
	private void doDelBus(NOrderEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(NOrderEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("create_name", t.getCreateName());
		map.put("create_by", t.getCreateBy());
		map.put("create_date", t.getCreateDate());
		map.put("update_name", t.getUpdateName());
		map.put("update_by", t.getUpdateBy());
		map.put("update_date", t.getUpdateDate());
		map.put("sys_org_code", t.getSysOrgCode());
		map.put("remarks", t.getRemarks());
		map.put("del_flag", t.getDelFlag());
		map.put("bpm_status", t.getBpmStatus());
		map.put("user_id", t.getUserId());
		map.put("realname", t.getRealname());
		map.put("usernameurl", t.getUsernameurl());
		map.put("marketing_one", t.getMarketingOne());
		map.put("goods_detaisl_type", t.getGoodsDetaislType());
		map.put("merchant_id", t.getMerchantId());
		map.put("goods_id", t.getGoodsId());
		map.put("goodsname", t.getGoodsname());
		map.put("order_status", t.getOrderStatus());
		map.put("order_type", t.getOrderType());
		map.put("aftersale_status", t.getAftersaleStatus());
		map.put("aftersale_type", t.getAftersaleType());
		map.put("goods_sum", t.getGoodsSum());
		map.put("numbers", t.getNumbers());
		map.put("pay_sum", t.getPaySum());
		map.put("express_nub", t.getExpressNub());
		map.put("express_name", t.getExpressName());
		map.put("details", t.getDetails());
		map.put("merchant_back", t.getMerchantBack());
		map.put("merchantname", t.getMerchantname());
		map.put("joinordertype", t.getJoinordertype());
		map.put("joinorderstatus", t.getJoinorderstatus());
		map.put("orderid", t.getOrderid());
		map.put("ptstatus", t.getPtstatus());
		map.put("end_time", t.getEndTime());
		map.put("payorderstatus", t.getPayorderstatus());
		map.put("couponid", t.getCouponid());
		map.put("userprice", t.getUserprice());
		map.put("paystatus", t.getPaystatus());
		map.put("standardid", t.getStandardid());
		map.put("standardname", t.getStandardname());
		map.put("picurl", t.getPicurl());
		map.put("paymode", t.getPaymode());
		map.put("pdtime", t.getPdtime());
		map.put("applytime", t.getApplytime());
		map.put("sendtime", t.getSendtime());
		map.put("moneytime", t.getMoneytime());
		map.put("acceptime", t.getAcceptime());
		map.put("addressid", t.getAddressid());
		map.put("area", t.getArea());
		map.put("areacode", t.getAreacode());
		map.put("address", t.getAddress());
		map.put("phone", t.getPhone());
		map.put("acceptname", t.getAcceptname());
		map.put("isvirtual", t.getIsvirtual());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,NOrderEntity t){
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
 		sql  = sql.replace("#{user_id}",String.valueOf(t.getUserId()));
 		sql  = sql.replace("#{realname}",String.valueOf(t.getRealname()));
 		sql  = sql.replace("#{usernameurl}",String.valueOf(t.getUsernameurl()));
 		sql  = sql.replace("#{marketing_one}",String.valueOf(t.getMarketingOne()));
 		sql  = sql.replace("#{goods_detaisl_type}",String.valueOf(t.getGoodsDetaislType()));
 		sql  = sql.replace("#{merchant_id}",String.valueOf(t.getMerchantId()));
 		sql  = sql.replace("#{goods_id}",String.valueOf(t.getGoodsId()));
 		sql  = sql.replace("#{goodsname}",String.valueOf(t.getGoodsname()));
 		sql  = sql.replace("#{order_status}",String.valueOf(t.getOrderStatus()));
 		sql  = sql.replace("#{order_type}",String.valueOf(t.getOrderType()));
 		sql  = sql.replace("#{aftersale_status}",String.valueOf(t.getAftersaleStatus()));
 		sql  = sql.replace("#{aftersale_type}",String.valueOf(t.getAftersaleType()));
 		sql  = sql.replace("#{goods_sum}",String.valueOf(t.getGoodsSum()));
 		sql  = sql.replace("#{numbers}",String.valueOf(t.getNumbers()));
 		sql  = sql.replace("#{pay_sum}",String.valueOf(t.getPaySum()));
 		sql  = sql.replace("#{express_nub}",String.valueOf(t.getExpressNub()));
 		sql  = sql.replace("#{express_name}",String.valueOf(t.getExpressName()));
 		sql  = sql.replace("#{details}",String.valueOf(t.getDetails()));
 		sql  = sql.replace("#{merchant_back}",String.valueOf(t.getMerchantBack()));
 		sql  = sql.replace("#{merchantname}",String.valueOf(t.getMerchantname()));
 		sql  = sql.replace("#{joinordertype}",String.valueOf(t.getJoinordertype()));
 		sql  = sql.replace("#{joinorderstatus}",String.valueOf(t.getJoinorderstatus()));
 		sql  = sql.replace("#{orderid}",String.valueOf(t.getOrderid()));
 		sql  = sql.replace("#{ptstatus}",String.valueOf(t.getPtstatus()));
 		sql  = sql.replace("#{end_time}",String.valueOf(t.getEndTime()));
 		sql  = sql.replace("#{payorderstatus}",String.valueOf(t.getPayorderstatus()));
 		sql  = sql.replace("#{couponid}",String.valueOf(t.getCouponid()));
 		sql  = sql.replace("#{userprice}",String.valueOf(t.getUserprice()));
 		sql  = sql.replace("#{paystatus}",String.valueOf(t.getPaystatus()));
 		sql  = sql.replace("#{standardid}",String.valueOf(t.getStandardid()));
 		sql  = sql.replace("#{standardname}",String.valueOf(t.getStandardname()));
 		sql  = sql.replace("#{picurl}",String.valueOf(t.getPicurl()));
 		sql  = sql.replace("#{paymode}",String.valueOf(t.getPaymode()));
 		sql  = sql.replace("#{pdtime}",String.valueOf(t.getPdtime()));
 		sql  = sql.replace("#{applytime}",String.valueOf(t.getApplytime()));
 		sql  = sql.replace("#{sendtime}",String.valueOf(t.getSendtime()));
 		sql  = sql.replace("#{moneytime}",String.valueOf(t.getMoneytime()));
 		sql  = sql.replace("#{acceptime}",String.valueOf(t.getAcceptime()));
 		sql  = sql.replace("#{addressid}",String.valueOf(t.getAddressid()));
 		sql  = sql.replace("#{area}",String.valueOf(t.getArea()));
 		sql  = sql.replace("#{areacode}",String.valueOf(t.getAreacode()));
 		sql  = sql.replace("#{address}",String.valueOf(t.getAddress()));
 		sql  = sql.replace("#{phone}",String.valueOf(t.getPhone()));
 		sql  = sql.replace("#{acceptname}",String.valueOf(t.getAcceptname()));
 		sql  = sql.replace("#{isvirtual}",String.valueOf(t.getIsvirtual()));
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
					javaInter.execute("n_order",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}