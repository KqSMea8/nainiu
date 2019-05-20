package com.jeecg.weixin_pay.util;

import java.util.List;

import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeecg.weixin_pay.entity.WeixinPayEntity;

/**
 * 描述：
 * @author：jh
 * @since：2018-5-31 下午15:15:04
 * @version:1.0
 */
@Service("nweixinPayService")
public class NWeixinPayService {
	@Autowired
	private SystemService systemService;
	//微信公众号wxpaystatus	0生效，1未生效   wxpaytype	公众号或者app	c	32		0公众号1app

	public WeixinPayEntity getWeH(){
	    StringBuffer sql=new StringBuffer();
	       sql.append("from WeixinPayEntity where wxpaystatus='0' and wxpaytype='0' ")
	       .append(" order by  updateDate desc");
	       //joinordertype	拼单类型0开团、1拼团，3单买
	       WeixinPayEntity  weixinpayentity=null;
		 List<WeixinPayEntity> WeixinPay_list= systemService.findHql(sql.toString());
		 if(WeixinPay_list!=null && WeixinPay_list.size()>0){
			 weixinpayentity=WeixinPay_list.get(0);
		 }
	   return weixinpayentity;

	}
	//微信APP
	public WeixinPayEntity getWeApp(){
		   StringBuffer sql=new StringBuffer();
	       sql.append("from WeixinPayEntity where wxpaystatus='0' and wxpaytype='1' ")
	       .append(" order by  updateDate desc");
		//joinordertype	拼单类型0开团、1拼团，3单买
	       WeixinPayEntity  weixinpayentity=null;
		 List<WeixinPayEntity> WeixinPay_list= systemService.findHql(sql.toString());
		 if(WeixinPay_list!=null && WeixinPay_list.size()>0){
			 weixinpayentity=WeixinPay_list.get(0);
		 }
	   return weixinpayentity;
	}
	//获取
	public WeixinPayEntity getWeixinPayEntity(){
		WeixinPayEntity weixinPayEntity = systemService.getEntity(WeixinPayEntity.class, "90106221a9db11e8beea54bf642d3cbf");
		return  weixinPayEntity;
	}
}
