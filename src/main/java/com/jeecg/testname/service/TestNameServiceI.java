package com.jeecg.testname.service;
import com.jeecg.testname.entity.TestNameEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface TestNameServiceI extends CommonService{
	
 	public void delete(TestNameEntity entity) throws Exception;
 	
 	public Serializable save(TestNameEntity entity) throws Exception;
 	
 	public void saveOrUpdate(TestNameEntity entity) throws Exception;
 	
}
