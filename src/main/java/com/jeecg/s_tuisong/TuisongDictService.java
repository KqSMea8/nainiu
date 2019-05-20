package com.jeecg.s_tuisong;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.PushMsgInfoHelper;

import com.jeecg.n_user.entity.NUserEntity;
import com.jeecg.s_tuisong.entity.STuisongEntity;
import com.jeecg.s_tuisong.service.STuisongServiceI;

/**
 * 描述：
 * @author：scott
 * @since：2017-4-12 下午05:15:04
 * @version:1.0
 */
@Service("tuisongDictService")
public class TuisongDictService {
	 //采用"Java SDK 快速入门"， "第二步 获取访问凭证 "中获得的应用配置，用户可以自行替换
//    private   String appId = "82zYCkmoGJ8U405tXOjRZ1";
//    private   String appKey = "st4Poggqys8ny3qH4Jpec3";
//    private   String masterSecret = " LuDHQNmYwi9wlVrobg3Vi8";
//
//    static String CID = "ae894ee3fa4d78e46a746debf3b37145";
    //别名推送方式
    // static String Alias = "";
//    static String host = "http://sdk.open.api.igexin.com/apiex.htm";
	@Autowired
	private STuisongServiceI sTuisongService;
	@Autowired
	private SystemService systemService;
/*	
	public  void getInsert(String title,String details,String userid){
		STuisongEntity sTuisong=new STuisongEntity();
		sTuisong.setTitle(title);
		sTuisong.setDetails(details);
		sTuisong.setUserid(userid);
		sTuisong.setQtstatus("2");//1全推2个推（精确到人）
		sTuisong.setTsstatus("1");//1未推送2已推送
		String id=UUID.randomUUID().toString();
		sTuisong.setId(id);
		TSUser user = systemService.getEntity(TSUser.class, userid);
		try {
			sTuisongService.save(sTuisong);
			systemService.addLog("推送消息添加成功", Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			// getSendMess(user.getCid(), "title", "detaisl",id);
			if(title.length()>100){
    			title=title.substring(0,100);
    		}
			Map<String,Object> map=PushMsgInfoHelper.singlePushByTransmission(user.getCid(), "查看通知:"+title, "您有一个审核！", "您有一个审核！");
			String result=(String) map.get("result");
    		System.out.println("result="+result);
    		
    		if("ok".equals(result)){
	    		STuisongEntity mode=systemService.getEntity(STuisongEntity.class, id);
	    		mode.setTsstatus("2");//1未推送2已推送
	    		systemService.saveOrUpdate(mode);
    		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/
	
	public  void getInsert(String title,String details,String userid){
		System.out.println("userid="+userid+"=="+userid.length());
		STuisongEntity sTuisong=new STuisongEntity();
		sTuisong.setTitle(title);
		sTuisong.setDetails(details);
		sTuisong.setUserid(userid);
		sTuisong.setQtstatus("2");//1全推2个推（精确到人）
		sTuisong.setTsstatus("1");//1未推送2已推送
		String id=UUID.randomUUID().toString();
		sTuisong.setId(id);
		NUserEntity user = systemService.getEntity(NUserEntity.class, userid);
		try {
			if(user!=null){
				sTuisongService.save(sTuisong);
				//systemService.addLog("推送消息添加成功", Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
				// getSendMess(user.getCid(), "title", "detaisl",id);
				if(title.length()>100){
	    			title=title.substring(0,100);
	    		}
				Map<String,Object> map=PushMsgInfoHelper.singlePushByTransmission(user.getCid(), "查看通知:"+title, details, details);
				String result=(String) map.get("result");
	    		System.out.println("result="+result);
	    		
	    		if("ok".equals(result)){
		    		STuisongEntity mode=systemService.getEntity(STuisongEntity.class, id);
		    		mode.setTsstatus("2");//1未推送2已推送
		    		systemService.saveOrUpdate(mode);
	    		}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	   


	    /**全部推送*/
	    public  void getAllSendMess(String id,String title,String  detaisl){

	    	try {
	    		if(title.length()>100){
	    			title=title.substring(0,100);
	    		}
	    		Map<String,Object> android_map=PushMsgInfoHelper.pushAllAndroid("查看通知:"+title, id, id);
	    		Map<String,Object>  ios_map=PushMsgInfoHelper.pushAllIOS("查看通知:"+title, id);
	    		String android_result=(String) android_map.get("result");
	    		String ios_result=(String) ios_map.get("result");
	    		System.out.println("android_result="+android_result+"=="+ios_result);
	    		if("ok".equals(android_result)&&"ok".equals(ios_result)){
		    		STuisongEntity mode=systemService.getEntity(STuisongEntity.class, id);
		    		mode.setTsstatus("2");//1未推送2已推送
		    		systemService.saveOrUpdate(mode);
	    		}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	 
	    }
	    
	
}
