package com.jeecg.n_coupon.service;
import com.jeecg.n_coupon.entity.NCouponEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface NCouponServiceI extends CommonService{
	
 	public void delete(NCouponEntity entity) throws Exception;
 	
 	public Serializable save(NCouponEntity entity) throws Exception;
 	
 	public void saveOrUpdate(NCouponEntity entity) throws Exception;
 	
}
