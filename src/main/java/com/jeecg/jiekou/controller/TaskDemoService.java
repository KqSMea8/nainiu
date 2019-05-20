package com.jeecg.jiekou.controller;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.security.KeyStore;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.net.ssl.SSLContext;

import com.jeecg.n_goods_detaisl.entity.NGoodsDetaislEntity;
import com.jeecg.wepay.util.payUtils.WXPayUtil;
import com.utils.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.LogUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextAutonumberBullet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.jeecg.n_merchant.entity.NMerchantEntity;
import com.jeecg.n_order.entity.NOrderEntity;
import com.jeecg.n_user.entity.NUserEntity;
import com.jeecg.s_tuisong.TuisongDictService;
import com.jeecg.weixin.util.WeixinService;
import com.jeecg.weixin_pay.entity.WeixinPayEntity;
import com.jeecg.weixin_pay.util.NWeixinPayService;
import com.jeecg.wepay.util.RequestHandler;
import com.jeecg.wepay.util.TenpayUtil;
import com.jeecg.wepay.util.http.HttpClientConnectionManager;

@Service("taskDemoService")
public class TaskDemoService  {

	@Autowired
	private SystemService systemService;
	@Autowired
	private TuisongDictService tuisongdictservice;
	@Autowired
	private WeixinService weixinservice;
	@Autowired
	private NWeixinPayService nweixinpayservice;
	//支付宝相关
	private static final String APP_ID = "2017120100293365";
	private static final String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCPzl+qbk/hrT+QbYzDctSpWyFGZYGuLyUWsXfhyVfNX+NNUSKWaQbzyOONuT9VGxfYA6220ALvXB6rPe/DhnW8GClYXWdH7BLKg4rnzos7YSVNaXCN58sTpJgHXbjtuvhMLZUqN0HXdZwFJpL50aIgb7XepQUxUvJodStGCf5iHk1SKKiNyKY9hSvVmvFDrfQc58hdWAI3t2nCd+EuZMfcJTP7xZrazLeE9AacasZ0DE7Kujl66zkXBta3Z6Rf+wvbBCUebVDz3bV6qp6YSbg/gk+Y+2zYXvIBghM5xJFBiELdJG85hxdcngNTag1Q6zmoNhXzObv2IHAdQmCeAFvDAgMBAAECggEAZmlCF82XddA7hE9//3C3oiQT/l4rjDBm6VJZXaJVtRykc1tkllkVameWJkfWonU49c0o2Rgp/uxLqwfgyA3pqppKV3OtKbslZrNnKM4euZrlRcvhLC32oXaGDjjgieytBxMvN3FCon5PLhvab66rFw53Jqe+mvHHUDyhJK/ZSWXZuTzNdawEPQrsOD9xZb+pLLI3GdA2BSZzCR1q9zGYYvoHpQX8+NK4z/39VmUoRU7Ny0OvfL/4L7wnScYnu2irWCMjTZHjfq1tnW3/lsYlW72AXEjnaZWLg0ZHwvSQnAEHI/hy6p9P5mAbRrehrgenWW/Cg1fiX4WQCcrSC4QMgQKBgQDC6Cv4YQ2SGqy6xT7gNf+6armMhyJ+lONcci7HfZAfmP17OmAW8io0Qldm/g/W9lBvpjMU2LNKPesdWE6PMfqnn/A71HskhAngvWPLq2AMWBp/kbAmYQmp6JBKZTQCvR/SxjfG1CsbYb/L7KYR72Mp/pfAEJRnJI5JK+X3nHRLTwKBgQC84b7eD3RJ4lhvSpG5Q3j1yebGbwyeST5JQc1sEsqRJ++VewZe97tAomjyWoCBNJb+rnsPkYv7OVvW06i9aOMhQ6/M8CM58vAcSwkOZsgq+Nq2sxR96u58LejcLMj3vXGis1FIR8WA6EM4gUDoezHDIO9cicY14tD937/HZw27TQKBgHKNbjpfIFC8qMRk5V11n0V7MG6thdKLw000NtY8sBZCHsjsOEmELtXkH+aCb+DRh9j2/5LDAi0iUys+GX4Dy+P1FoazjWSazgtuhFbR9HOM3JYZlEQaSEm6TAPNk1IAwdFpeqK7VFKVktpRzhFAdzHZVmsl03MDgzTyPgjXxWn1AoGAFs8D53jiSBHHMBlHI6IcN0IcVhYO5gZeOSZzEfvq7kBuVBS5Hjq4KAP0vF9laTTajwKu5aBj0QCKMJT6qXTDCL9NuWe+OT8285O0EkMjJN1MPAfAD7yQ8/nvRrc5xYDg+g7BYAMavIhPpcEl/2zxA2k0vm38u4EaT633ULMHG3kCgYEAryCPTXg2LBtd+vFye80sHc1GkZnMtg7Gbil7skImH793eOhBr1kx49AE+wciyy+VDpyOd73vHgLfinMAZeXwUPlQyatFXEhd1nGzxUqp/7R3BnaoQRQI7VGLz5qVsXSRdAJZvSGuHWyXmYwHKpuxUImOjGrJKCpLTKedeTnEb4k=";
	private static final String CHARSET = "utf-8";
	private static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhSwkHjotgK5YM5/Q/LsiO8xfeXFvrm5iMZzXmNlaIlwMMldDzQLtlFa4UMCt3GFvWq3KgM/Sd8MFgQviGRdcQ+1n6TqVaVgYqewL0KIEI6ggDFsB15rxxnZ/PTbAdXww1MMQ2X5iE/GPy7vOnEZgC04/jjGjBNgvVX2LX+mbwbNTwtAX1tlyafSJOQ0Hp+qVi2qRuQXwmzdy73paRIDpnppx0oGmXdevlmNTwkev4I6zUEmGTi9wEudOSmY6eU87m5QxbO74L6FUlY0CBzYTWO9fCdnGz871D+Ni252SduvmBDNAexuOLC6YMWUGDUUrZxlZCj/3YgM2+g9x+E/sxwIDAQAB";
	private static final String ALIPAY_URL = "https://openapi.alipay.com/gateway.do";
	private static final String SIGN_TYPE= "RSA2";


	/*//微信app相关
	//微信支付商户开通后 微信会提供appid和appsecret和商户号partner
	private static final String wxappid = "wx2364e2025d750163";
	private static final String wxappsecret = "0f210b94647b4dbbe910e9a053b494b4";
	private static final String wxpartner = "1495222432";
	//这个参数partnerkey是在商户后台配置的一个32位的key,微信商户平台-账户设置-安全设置-api安全
	private static final String wxpartnerkey = "yaowei1987yaowei1987yaowei1987ya";
	*/
	//微信公众号相关
	//微信支付商户开通后 微信会提供appid和appsecret和商户号partner
/*	private static final String gzappid = "wx8d5f593b39c52c6f";
	private static final String gzappsecret = "f0bc2d092fdc5b89b3ac7362b2105156";
//	private static final String gzpartner = "1493251372";
	//微信另一个商户号
			private static String gzpartner = "1495222432";
	//这个参数partnerkey是在商户后台配置的一个32位的key,微信商户平台-账户设置-安全设置-api安全
	private static final String gzpartnerkey = "yaowei1987yaowei1987yaowei1987ya";*/



	/*0/5 * * * * ? 每隔5秒
	 * 0 0/5 * * * ?    每隔5分钟
      0 0/15 * * * ?  每隔15分钟
      0 0 0/2 * * ?  每隔2个小时
      0 0 0 24 * ?   每天的24点执行
	 * http://cron.qqe2.com/
	 * @Scheduled(cron="0 0/1 * * * ?")*/
	public void run() {
		LogUtil.info("=====订单作废==============消息中间件定时任务开始===================");
		/**进行订单作废操作*/
		updateorder();
		LogUtil.info("=====助力订单作废==============消息中间件定时任务开始===================");
		updateorderForMember();
		LogUtil.info("============中奖订单=======消息中间件定时任务开始===================");
		/**进行中奖订单操作操作*/
	//	getExitThree();
		LogUtil.info("============确认收货3天后货款=======消息中间件定时任务开始===================");
		/**确认收货3天后货款打入商家账户*/
		//getPaymerchant();
		LogUtil.info("============用户未确认收货=======消息中间件定时任务开始===================");
		/***已发货15天，用户未确认收货，自动确认，货款打入商家账户*/
		//getPtPaymerchant();
		/***营销活动结束，商品退出活动*/
		//getMarketing();
		//冻结金额解冻
		unfreePrice();
	}
	 /**
	  * @Author shishanshan
	  * @Desprition 用户成团冻结金额解冻
	  * @Date 2018/11/11 23:16
	  * @Param
	  * @Return
	  **/
	public void unfreePrice() {
		String sql = "select t1.id,t1.orderid,t1.user_id,t2.return_money from n_order t1 LEFT JOIN n_goods_detaisl t2 on t1.goods_id = t2.id where " +
				" t1.paystatus='0' and t1.order_type = '0' and t1.order_status = '2' and t1.joinordertype = '0' and t1.islook is null  and t1.end_time < (NOW() + INTERVAL -3 DAY) and t1.end_time > '2018-11-08 15:00:00'" +
				" and t2.is_only_one = '0' and  t2.return_money != '0' and   t2.return_money is not NULL  ";
		List<Object[]> listbySql = systemService.findListbySql(sql);
		if(listbySql!=null && listbySql.size()>0){
			for(int i=0; i<listbySql.size(); i++){
				Object[] objects = listbySql.get(i);
				String id = objects[0].toString();
				String orderid = objects[1].toString();
				String userId = objects[2].toString();
				String returnMoney = objects[3].toString();
				//判断剩余未退款人数
				String sqlPt = "select id from n_order where order_type = '0' and joinordertype = '1' and paystatus ='0' and orderid = '"+orderid+"'";
				List<Object> list = systemService.findListbySql(sqlPt);
				if(list!=null && list.size()>0){
					int totalPrice = list.size() * Integer.valueOf(returnMoney);
					NUserEntity nUserEntity = systemService.getEntity(NUserEntity.class, userId);
					if(nUserEntity!=null){
						String price = nUserEntity.getPrice();
						String ext5 = nUserEntity.getExt5();
						String ext1 = nUserEntity.getExt1();
						if(!StringUtils.isNotBlank(ext5)){
							ext5 = "0";
						}
						if(!StringUtils.isNotBlank(price)){
							price = "0";
						}
						if(!StringUtils.isNotBlank(ext1)){
							ext1 = "0";
						}
						String decre = CommonUtils.decre(ext5, Integer.toString(totalPrice));
						Double aDouble = Double.valueOf(decre);
						String realPrice = "";
						if(aDouble <0){
							ext5 = "0";
							realPrice = ext5;
						}else{
							ext5 = decre;
							realPrice = Integer.toString(totalPrice);
						}
						nUserEntity.setExt5(ext5);
						nUserEntity.setPrice(CommonUtils.add(price,realPrice));
						nUserEntity.setExt1(CommonUtils.add(ext1,realPrice));
						systemService.saveOrUpdate(nUserEntity);
						weixinservice.sendTemplate_refferrer(userId,"团长成团奖励金额解冻!",realPrice+"元","双11成团奖励","", DateUtils.formatTimehsm(new Date()),"点击查看钱包详情，如有疑问直接咨询在线客服。");
						String updateSql = "update n_order set islook = 'YT' where id ='"+id+"'";
						systemService.updateBySqlString(updateSql);
					}
				}
			}
		}
	}

	/***营销活动结束，商品退出活动*/
	private void getMarketing() {
		// TODO Auto-generated method stub
		sql("n_marketing_home","n_marketing_home_details");
		sql("n_marketing_one","n_marketing_one_details");
		sql("n_marketing_two","n_marketing_two_details");
		sql("n_marketing_three","n_marketing_three_details");
		sql("n_marketing_four","n_marketing_four_details");
		sql("n_marketing_five","n_marketing_five_details");
	}
	private void sql(String table,String table_details){
		StringBuffer sql=new StringBuffer();
		sql.append("select id,title from ")
				.append(table)
				.append(" where end_time < now()");
			List<Object[]> mode_list = systemService.findListbySql(sql.toString());
			for(int i=0; i<mode_list.size(); i++) {
				Object[]  t= mode_list.get(i);
				String id=t[0].toString();
				StringBuffer update=new StringBuffer();
				update.append("update n_goods_detaisl t1,")
					  .append(table_details)
					  .append(" t2 set join_huodong='0'")
					  .append(" where  t1.id=t2.goodsid   and audittype='1' and oneid='")
					  .append(id)
					  .append("'");
				systemService.updateBySqlString(update.toString());
				StringBuffer delete =new StringBuffer();
				delete.append("delete from  ")
				      .append(table_details)
				      .append(" where  oneid='")
					  .append(id)
					  .append("'");
				systemService.updateBySqlString(delete.toString());
			}
	}
	public void runthree() {
		long start = System.currentTimeMillis();
		LogUtil.info("============中奖订单=======消息中间件定时任务开始===================");
		/**进行中奖订单操作操作*/
		getExitThree();

	}
	public void paymoney() {
		long start = System.currentTimeMillis();
		LogUtil.info("============确认收货3天后货款=======消息中间件定时任务开始===================");
		/**确认收货3天后货款打入商家账户*/
		getPaymerchant();

	}
	public void Ptpaymoney() {
		long start = System.currentTimeMillis();
		LogUtil.info("============用户未确认收货=======消息中间件定时任务开始===================");
		/***已发货15天，用户未确认收货，自动确认，货款打入商家账户*/
		getPtPaymerchant();
	}
	/**确认收货3天后货款打入商家账户
	 * order_type	订单类型		0正常 1售后处理中
	 * sendtime	发货时间
	 * acceptime	确认收货时间
	 * gatheringstatus	商家收款状态	0未收款 1冻结中2已收款
	 * */
	private void getPaymerchant() {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer();
		 sql.append("select id,user_id,merchant_id,merchantname,")
		    .append(" goods_id,goodsname,goods_sum,numbers,pay_sum,acceptime ")
			.append(" from n_order where paystatus='0' and TO_DAYS( NOW( ) ) - TO_DAYS(acceptime) >= 3  ")
			.append(" and gatheringstatus='1' and order_type='0' ORDER BY acceptime asc    limit 0,1000 ");
		List<Object[]> mode_list = systemService.findListbySql(sql.toString());
		for(int i=0; i<mode_list.size(); i++) {
			Object[]  t= mode_list.get(i);
			String id=t[0].toString();
			String merchant_id=t[2].toString();
			String goods_sum=t[6].toString();/**商品总价*/
			StringBuffer updatesql=new StringBuffer();
			 Format f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	          Date today = new Date();
	          String tday=f.format(today);
			 updatesql.append("update n_order set gatheringstatus='2'")
			          .append(",acceptime='")
    	    		     .append(tday)
    	    		     .append("'")
    	    		    .append(" ,update_date='")
	    		     .append(tday)
	    		     .append("'")
			 		 .append(" where id='")
             .append(id)
             .append("'");
			/*增加商品可提现金额*/
			 addNMerchantMoney(merchant_id, goods_sum);
		}
	}
	/***已发货15天，用户未确认收货，自动确认，货款打入商家账户
	 * order_type	订单类型		0正常 1售后处理中
	 * sendtime	发货时间
	 * acceptime	确认收货时间
	 * gatheringstatus	商家收款状态	0未收款 1冻结中2已收款
	 * order_status	订单状态		0待付款1待拼单，2待发货3已发货4已签收5已评价6售后处理中
	 * */
	private void getPtPaymerchant() {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer();
		 sql.append("select id,user_id,merchant_id,merchantname,")
		    .append(" goods_id,goodsname,goods_sum,numbers,pay_sum,acceptime ")
			.append(" from n_order where paystatus='0' and  TO_DAYS( NOW( ) ) - TO_DAYS(sendtime) >=15  ")
			.append(" and  order_status='3'and gatheringstatus='1' and order_type='0' ORDER BY sendtime asc    limit 0,1000 ");
		List<Object[]> mode_list = systemService.findListbySql(sql.toString());
		for(int i=0; i<mode_list.size(); i++) {
			Object[]  t= mode_list.get(i);
			String id=t[0].toString();
			String merchant_id=t[2].toString();
			String goods_sum=t[6].toString();/**商品总价*/

			 Format f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	          Date today = new Date();
	          String acceptime=f.format(today);
			StringBuffer updatesql=new StringBuffer();
			 updatesql.append("update n_order set gatheringstatus='2',order_status='4'  ")
			 .append(" ,acceptime='")
			 .append(acceptime)
			 .append("' where id='")
             .append(id)
             .append("'");
			/*增加商品可提现金额*/
			 addNMerchantMoney(merchant_id, goods_sum);
		}
	}
	/*增加商品可提现金额*/
	public void   addNMerchantMoney(String id,String goods_sum){
		 /**查询商家信息*/
		 NMerchantEntity nMerchant = systemService.getEntity(NMerchantEntity.class, id);
		  BigDecimal b1 = new BigDecimal(goods_sum);
		   String actionMoney=nMerchant.getActionMoney();
		   if(StringUtil.is_khEmpty(actionMoney)){
			   actionMoney="0";
		   }
	       BigDecimal b2 = new BigDecimal(actionMoney);
	      // String new_price= b2.subtract(b1).toString();
	       String new_price= b1.add(b2).setScale(2,BigDecimal.ROUND_HALF_DOWN).toString();
	       nMerchant.setActionMoney(new_price);
	       systemService.saveOrUpdate(nMerchant);
	}

	/**支付宝支付 paymode	支付方式0微信、1支付宝、2无支付、3公众号支付*/
    public void  getAlipay(String type,String id,String orderid,String paySum,String userId,String userprice,String goodsname,String joinOrderType,String goodsId,String oneLevelUser,String twoLevelUser,String threeLevelUser){
		AlipayClient alipayClient = new DefaultAlipayClient(ALIPAY_URL, APP_ID,APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
		AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
		request.setBizContent("{" + "\"out_trade_no\":\""+id+"\","+ "\"refund_amount\":"+paySum+""+ "}");
		AlipayTradeRefundResponse response;
		try {
			if(StringUtils.isNotBlank(paySum) && !"0".equals(paySum)){
				System.out.println("orderid="+orderid+"=id="+id);
				response = alipayClient.execute(request);
				if (response.isSuccess()) {
					System.out.println("调用成功");
					/**订单作废
					 * joinorderstatus	拼单状态 0待完成，1已完成、2订单取消
					 * 退款的状态 aftersale_status	售后状态	0申请退款1退款成功，2驳回退款3平台强制退款4平台拒绝退款5退款失败*/
					StringBuffer updatesql=new StringBuffer();
					if("1".equals(type)){
						//gatheringstatus0未收款 1冻结中2已收款、3退款
						updatesql.append("update n_order set joinorderstatus='2',gatheringstatus0='3',aftersale_status='1',exitmoneystatus='0' where id='")
								.append(id)
								.append("'");
						weixinservice.sendTemplate_six(userId, "您的订单退款已到账", "¥"+paySum,"退款-"+goodsname, "退回支付卡",id, "如有问题，拨打客服");
					}else if("2".equals(type)){
						updatesql.append("update n_order set joinorderstatus='2',aftersale_status='1',exitmoneystatus='0' where id='")
								.append(id)
								.append("'");
					}
					systemService.updateBySqlString(updatesql.toString());
					// 退款团长冻结金额减少
					if(StringUtils.isNotBlank(joinOrderType) && "0".equals(joinOrderType) && StringUtils.isNotBlank(goodsId)){
						decreTeamLeaderUnfreePrice(goodsId,orderid,userId,goodsname,id);
						//层级奖励金额减少
						if(StringUtils.isNotBlank(threeLevelUser)){
							decreLevelsOfbindingUnfreePrice(goodsId,orderid,userId,goodsname,id,oneLevelUser,twoLevelUser,threeLevelUser);
						}
					}
				} else {
					StringBuffer updatesql=new StringBuffer();
					if("1".equals(type)){
						updatesql.append("update n_order set joinorderstatus='2',aftersale_status='5',exitmoneystatus='0' where id='")
								.append(id)
								.append("'");
					}else if("2".equals(type)){
						updatesql.append("update n_order set joinorderstatus='2',aftersale_status='5',exitmoneystatus='0' where id='")
								.append(id)
								.append("'");
					}
					systemService.updateBySqlString(updatesql.toString());
					System.out.println("调用失败");
				}
				getExitPrice(userId, userprice);
			}else{
				String sql = "update n_order set joinorderstatus='2',gatheringstatus0='3',aftersale_status='1',exitmoneystatus='0' where id='"+id+"'";
				systemService.updateBySqlString(sql);
			}
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
	}
	/**微信支付 paymode	支付方式0微信、1支付宝、2无支付、3公众号支付*/
	public void  getWeixinpay(String type,String id,String orderid,String paySum,String userId,String userprice,String goodsname,String joinOrderType,String goodsId,String oneLevelUser,String twoLevelUser,String threeLevelUser){
		 WeixinPayEntity  weixinpayentity=nweixinpayservice.getWeH();
		  String wxappid=null;
		  String wxappsecret=null;
		  String wxpartner=null;
		  String wxpartnerkey=null;
		  String wxnotifyurl=null;
		 if(weixinpayentity!=null){
			 wxappid=weixinpayentity.getAppid();
			 wxappsecret=weixinpayentity.getAppsecret();
			 wxpartner=weixinpayentity.getPartner();
			 wxpartnerkey=weixinpayentity.getPartnerkey();
			 wxnotifyurl=weixinpayentity.getNotifyurl();
		 }
		String nonceStr = getNonceStr();
        /*-----  1.生成预支付订单需要的的package数据-----*/
        SortedMap<String, String> packageParams = new TreeMap<String, String>();
        String paySum1 = paySum;
        paySum=new BigDecimal(100).multiply(new BigDecimal(paySum)).setScale(0,BigDecimal.ROUND_HALF_DOWN).toString();
        packageParams.put("appid", wxappid);
        packageParams.put("mch_id", wxpartner);
        packageParams.put("nonce_str", nonceStr);
        packageParams.put("out_trade_no", id);
        packageParams.put("out_refund_no", id);
        packageParams.put("total_fee",paySum);
        packageParams.put("refund_fee", paySum);
        /*----2.根据package生成签名sign---- */
        RequestHandler reqHandler = new RequestHandler(null, null);
        reqHandler.init(wxappid, wxappsecret, wxpartnerkey);
        String sign = reqHandler.createSign(packageParams);

        /*----3.拼装需要提交到微信的数据xml-total_fee  支付金额，refund_fee 退的金额--- */
        String xml="<xml>"
        +"<appid>"+wxappid+"</appid>"
        + "<mch_id>"+wxpartner+"</mch_id>"
        + "<nonce_str>"+nonceStr+"</nonce_str>"
        + "<out_trade_no>"+id+"</out_trade_no>"
        + "<out_refund_no>"+id+"</out_refund_no>"
        + "<refund_fee>"+paySum+"</refund_fee>"
        + "<total_fee>"+paySum+"</total_fee>"
        + "<sign>"+sign+"</sign>"
        +"</xml>";
         try {
             /*----4.读取证书文件,这一段是直接从微信支付平台提供的demo中copy的，所以一般不需要修改---- */
             KeyStore keyStore  = KeyStore.getInstance("PKCS12");
             FileInputStream instream = new FileInputStream(new File("C:\\apiclient_cert_app.p12"));
             try {
                 keyStore.load(instream, wxpartner.toCharArray());
             } finally {
                 instream.close();
             }
             // Trust own CA and all self-signed certs
             SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, wxpartner.toCharArray()).build();
             // Allow TLSv1 protocol only
             SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext,new String[] { "TLSv1" },null,
                     SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
             CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
			 if(StringUtils.isNotBlank(paySum) && !"0".equals(paySum)){
				 /*----5.发送数据到微信的退款接口---- */
				 String url="https://api.mch.weixin.qq.com/secapi/pay/refund";
				 HttpPost httpost= HttpClientConnectionManager.getPostMethod(url);
				 httpost.setEntity(new StringEntity(xml, "UTF-8"));
				 HttpResponse weixinResponse = httpClient.execute(httpost);
				 String jsonStr = EntityUtils.toString(weixinResponse.getEntity(), "UTF-8");
				 LogUtil.info("微信退款接口返回数据:"+jsonStr);
				 //Map map = GetWxOrderno.doXMLParse(jsonStr);
				 Map<String, String> stringStringMap = WXPayUtil.xmlToMap(jsonStr);
				 if("success".equalsIgnoreCase(stringStringMap.get("return_code"))){
					 System.out.println("returncode"+"="+"ok"+"returninfo"+"="+"退款成功");
					 /**订单作废
					  * joinorderstatus	拼单状态 0待完成，1已完成、2订单取消
					  * 退款的状态 aftersale_status	售后状态	0申请退款1退款成功，2驳回退款3平台强制退款4平台拒绝退款5退款失败*/
					 StringBuffer updatesql=new StringBuffer();
					 Format f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					 Date today = new Date();
					 String pdtime=f.format(today);
					 if("1".equals(type)){
						 updatesql.append("update n_order set joinorderstatus='2',gatheringstatus0='3',aftersale_status='1',exitmoneystatus='0' ")
								 .append(",update_date='")
								 .append(pdtime)
								 .append("' ")
								 .append(" where id='")
								 .append(id)
								 .append("'");
					 }else if("2".equals(type)){
						 updatesql.append("update n_order set joinorderstatus='2',gatheringstatus0='3',aftersale_status='1',exitmoneystatus='0'")
								 .append(",update_date='")
								 .append(id)
								 .append("' ")
								 .append(" where id='")
								 .append(id)
								 .append("'");
					 }
					 systemService.updateBySqlString(updatesql.toString());
					 if(StringUtils.isNotBlank(paySum) && !"0".equals(paySum)){
						 weixinservice.sendTemplate_six(userId, "您的订单退款已到账", "¥"+paySum1,"退款-"+goodsname, "退回支付卡", id, "如有问题，拨打客服");
					 }
					 // 退款团长冻结金额减少
					 if(StringUtils.isNotBlank(joinOrderType) && "0".equals(joinOrderType) && StringUtils.isNotBlank(goodsId)){
						 decreTeamLeaderUnfreePrice(goodsId,orderid,userId,goodsname,id);
						 //层级金额减少
						 if(StringUtils.isNotBlank(threeLevelUser)){
							 decreLevelsOfbindingUnfreePrice(goodsId,orderid,userId,goodsname,id,oneLevelUser,twoLevelUser,threeLevelUser);
						 }
					 }


				 }else{
					 StringBuffer updatesql=new StringBuffer();
					 if("1".equals(type)){
						 updatesql.append("update n_order set joinorderstatus='2',aftersale_status='5',exitmoneystatus='0' where id='")
								 .append(id)
								 .append("'");
					 }else if("2".equals(type)){
						 updatesql.append("update n_order set joinorderstatus='2',aftersale_status='5',exitmoneystatus='0' where id='")
								 .append(id)
								 .append("'");
					 }

					 systemService.updateBySqlString(updatesql.toString());
					 System.out.println("returncode"+"="+"error="+"returninfo"+"="+"退款失败");
				 }
				 /**成功或者失败，余额都退*/
				 //getExitPrice(userId, userprice);
			 }else{
				 StringBuffer updatesql=new StringBuffer();
				 if("1".equals(type)){
					 updatesql.append("update n_order set joinorderstatus='2',aftersale_status='5',exitmoneystatus='0' where id='")
							 .append(id)
							 .append("'");
				 }else if("2".equals(type)){
					 updatesql.append("update n_order set joinorderstatus='2',aftersale_status='5',exitmoneystatus='0' where id='")
							 .append(id)
							 .append("'");
				 }
				 systemService.updateBySqlString(updatesql.toString());
			 }
        } catch (Exception e) {
        	 StringBuffer updatesql=new StringBuffer();
     		if("1".equals(type)){
 			    updatesql.append("update n_order set joinorderstatus='2',aftersale_status='5',exitmoneystatus='0' where orderid='")
 			             .append(orderid)
 			             .append("'");
 				}else if("2".equals(type)){
 					 updatesql.append("update n_order set joinorderstatus='2',aftersale_status='5',exitmoneystatus='0' where id='")
 		             .append(id)
 		             .append("'");
 				}

		        systemService.updateBySqlString(updatesql.toString());
        	System.out.println("returncode"+"="+e.toString());
        	System.out.println("returninfo"+"="+"退款失败");
        }
	}
	/**
	 * @Author shishanshan
	 * @Desprition 层级奖励金额减少
	 * @Date 2018/11/18 21:21
	 * @Param
	 * @Return
	 **/
	private void decreLevelsOfbindingUnfreePrice(String goodsId, String orderid, String userId, String goodsname, String id, String oneLevelUser, String twoLevelUser, String threeLevelUser) {
		NGoodsDetaislEntity nGoodsDetaislEntity = systemService.getEntity(NGoodsDetaislEntity.class, goodsId);
		try {
			if(nGoodsDetaislEntity!=null){
				String oneLevelPrice = nGoodsDetaislEntity.getOneLevel();
				String twoLevelPrice = nGoodsDetaislEntity.getTwoLevel();
				String threeLevelPrice = nGoodsDetaislEntity.getThreeLevel();
				if(!"0".equals(nGoodsDetaislEntity.getIsMember())){
					if(StringUtils.isNotBlank(oneLevelPrice) && StringUtils.isNotBlank(twoLevelPrice) && StringUtils.isNotBlank(threeLevelPrice) && !"0".equals(oneLevelPrice) && !"0".equals(twoLevelPrice) && !"0".equals(threeLevelPrice)){
						String sql = "select id from  n_order  where orderid = '"+orderid+"' and paystatus='0'  and goods_detaisl_type='2' and order_status = '1' and joinordertype = '1' and is_member_user = 'N'";
						List<Object> listbySql = systemService.findListbySql(sql);
						if(listbySql != null && listbySql.size()>0){
							int ptCommonNum = listbySql.size();//团里普通人人数
							if(StringUtils.isNotBlank(threeLevelUser)){
								if(!"1".equals(threeLevelUser)){
									decreUserPriceUnfree(threeLevelPrice, ptCommonNum, threeLevelUser,goodsname,id);
								}
								if(StringUtils.isNotBlank(twoLevelUser)){
									decreUserPriceUnfree(twoLevelPrice, ptCommonNum, twoLevelUser,goodsname,id);
									if(StringUtils.isNotBlank(oneLevelUser)){
										decreUserPriceUnfree(oneLevelPrice, ptCommonNum, oneLevelUser,goodsname,id);
									}
								}
							}
						}
					}
				}
			}
		}catch (Exception e){
			LogUtil.error("层级奖励金额减少异常",e);
			e.printStackTrace();
		}
	}
	 /**
	  * @Author shishanshan
	  * @Desprition  层级奖励金额减少
	  * @Date 2018/11/18 21:57
	  * @Param
	  * @Return
	  **/
	private boolean decreUserPriceUnfree(String price, int num, String userId,String goodsname,String id) {
		boolean flag = false;
		try {
			NUserEntity nUserEntity = systemService.getEntity(NUserEntity.class, userId);
			if(nUserEntity != null){
				flag =true;
				double totalPrice = Double.valueOf(price) * num;
				String ext5 = CommonUtils.isZeroForBlank(nUserEntity.getExt5());//冻结金额
				String decrePrice = CommonUtils.decre(ext5, Double.toString(totalPrice));
				Double aDouble = Double.valueOf(decrePrice);
				if(aDouble<0){
					ext5 = "0";
				}else{
					ext5 = decrePrice;
				}
				nUserEntity.setExt5(ext5);
				systemService.saveOrUpdate(nUserEntity);
				weixinservice.sendTemplate_six(userId, "由于您店铺客户拼团失败,您的店铺冻结金额减少.", "¥"+price+"(冻结金额减少.)","退款-"+goodsname, "店铺冻结金额减少!.", id, "如有问题，拨打客服");
			}
		}catch (Exception e){
			LogUtil.error("层级奖励金额减少方法异常",e);
		}
		return  flag;
	}

	/**
	  * @Author shishanshan
	  * @Desprition 减少团长佣金
	  * @Date 2018/11/18 21:26
	  * @Param
	  * @Return
	  **/
	private void decreTeamLeaderUnfreePrice(String goodsId,String orderid,String userId,String goodsname,String id) {
		NGoodsDetaislEntity nGoodsDetaislEntity = systemService.getEntity(NGoodsDetaislEntity.class, goodsId);
		if(nGoodsDetaislEntity!=null && "0".equals(nGoodsDetaislEntity.getIsOnlyOne()) && StringUtils.isNotBlank(nGoodsDetaislEntity.getReturnMoney()) && !"0".equals(nGoodsDetaislEntity) ){
			String sql = "select id from  n_order  where orderid = '"+orderid+"' and paystatus='0'  and goods_detaisl_type='2' and order_status = '1' and joinordertype = '1'";
			List<Object> listbySql = systemService.findListbySql(sql);
			if(listbySql !=null && listbySql.size()>0){
				NUserEntity nUserEntity = systemService.getEntity(NUserEntity.class, userId);
				if (nUserEntity != null){
					String ext5 = nUserEntity.getExt5();
					boolean flag = true;
					if(!StringUtils.isNotBlank(ext5)){
						ext5="0";
						flag =false;
					}
					String ptPrice = Integer.toString(listbySql.size() * Integer.valueOf(nGoodsDetaislEntity.getReturnMoney()));
					String decre = CommonUtils.decre(ext5, ptPrice);
					Double aDouble = Double.valueOf(decre);
					if(aDouble<0){
						ext5 = "0";
					}else{
						ext5 = decre;
					}
					if(flag){
						nUserEntity.setExt5(ext5);
						systemService.saveOrUpdate(nUserEntity);
						weixinservice.sendTemplate_six(userId, "拼团失败,您的冻结金额相应减少.", "¥"+ptPrice,"退款-"+goodsname, "个人中心冻结金额减少.", id, "如有问题，拨打客服");
					}
				}
			}
		}
	}

	/**公众号支付 paymode	支付方式0微信、1支付宝、2无支付、3公众号支付*/
    public void  getWeixinHpay(String type,String id,String orderid,String paySum,String userId,String userprice,String goodsname){
		  WeixinPayEntity  weixinpayentity=nweixinpayservice.getWeH();
		  String gzappid=null;
		  String gzappsecret=null;
		  String gzpartner=null;
		  String gzpartnerkey=null;
		  String gznotifyurl=null;
		 if(weixinpayentity!=null){
			 gzappid=weixinpayentity.getAppid();
			 gzappsecret=weixinpayentity.getAppsecret();
			 gzpartner=weixinpayentity.getPartner();
			 gzpartnerkey=weixinpayentity.getPartnerkey();
			 gznotifyurl=weixinpayentity.getNotifyurl();
		 }
    	  Format f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	          Date today = new Date();
	          String pdtime=f.format(today);
    	String nonceStr = getNonceStr();
        /*-----  1.生成预支付订单需要的的package数据-----*/
        SortedMap<String, String> packageParams = new TreeMap<String, String>();
        paySum=new BigDecimal(100).multiply(new BigDecimal(paySum))
        		.setScale(0,BigDecimal.ROUND_HALF_DOWN).toString();
        packageParams.put("appid", gzappid);
        packageParams.put("mch_id", gzpartner);
        packageParams.put("nonce_str", nonceStr);
        packageParams.put("out_trade_no", id);
        packageParams.put("out_refund_no", id);
        packageParams.put("total_fee",paySum);
        packageParams.put("refund_fee", paySum);
        /*----2.根据package生成签名sign---- */
        RequestHandler reqHandler = new RequestHandler(null, null);
        reqHandler.init(gzappid, gzappsecret, gzpartnerkey);
        String sign = reqHandler.createSign(packageParams);

        /*----3.拼装需要提交到微信的数据xml---- */
        String xml="<xml>"
        +"<appid>"+gzappid+"</appid>"
        + "<mch_id>"+gzpartner+"</mch_id>"
        + "<nonce_str>"+nonceStr+"</nonce_str>"
        + "<out_trade_no>"+id+"</out_trade_no>"
        + "<out_refund_no>"+id+"</out_refund_no>"
        + "<refund_fee>"+paySum+"</refund_fee>"
        + "<total_fee>"+paySum+"</total_fee>"
        + "<sign>"+sign+"</sign>"
        +"</xml>";
         try {
             /*----4.读取证书文件,这一段是直接从微信支付平台提供的demo中copy的，所以一般不需要修改---- */
             KeyStore keyStore  = KeyStore.getInstance("PKCS12");
             FileInputStream instream = new FileInputStream(new File("C:\\apiclient_cert_wap.p12"));
             try {
                 keyStore.load(instream, gzpartner.toCharArray());
             } finally {
                 instream.close();
             }
             // Trust own CA and all self-signed certs
             SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, gzpartner.toCharArray()).build();
             // Allow TLSv1 protocol only
             SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext,new String[] { "TLSv1" },null,
                     SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
             CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();

             /*----5.发送数据到微信的退款接口---- */
             String url="https://api.mch.weixin.qq.com/secapi/pay/refund";
             HttpPost httpost= HttpClientConnectionManager.getPostMethod(url);
             httpost.setEntity(new StringEntity(xml, "UTF-8"));
             HttpResponse weixinResponse = httpClient.execute(httpost);
             String jsonStr = EntityUtils.toString(weixinResponse.getEntity(), "UTF-8");
             System.out.println(jsonStr);
            // Map map = GetWxOrderno.doXMLParse(jsonStr);
			 Map<String, String> stringStringMap = WXPayUtil.xmlToMap(jsonStr);
			 System.out.println("时珊珊打印退款状态"+stringStringMap.get("return_code"));
			 if("success".equalsIgnoreCase(stringStringMap.get("return_code"))){
                 System.out.println("returncode"+"="+"ok");
                 System.out.println("returninfo"+"="+"退款成功");
                 /**订单作废
                  * joinorderstatus	拼单状态 0待完成，1已完成、2订单取消
                  * 退款的状态 aftersale_status	售后状态	0申请退款1退款成功，2驳回退款3平台强制退款4平台拒绝退款5退款失败*/

                     StringBuffer updatesql=new StringBuffer();
       					if("1".equals(type)){
       					    updatesql.append("update n_order set joinorderstatus='2',gatheringstatus0='3',aftersale_status='1',exitmoneystatus='0' ")
	       					    	 .append(",update_date='")
	  						         .append(pdtime)
	  						         .append("' ")
       					    		 .append(" where orderid='")
       					             .append(orderid)
       					             .append("'");
       						}else if("2".equals(type)){
       							 updatesql.append("update n_order set joinorderstatus='2',gatheringstatus0='3',aftersale_status='1',exitmoneystatus='0' ")
	       					    	 .append(",update_date='")
	  						         .append(pdtime)
	  						         .append("' ")
       					    		 .append(" where id='")
	       				             .append(id)
	       				             .append("'");
       						}
       			        systemService.updateBySqlString(updatesql.toString());
       			 	weixinservice.sendTemplate_six(userId, "您的订单退款已到账", "¥"+paySum,"退款-"+goodsname, "退回支付卡",id, "如有问题，拨打客服");
                 }else{
                   	 StringBuffer updatesql=new StringBuffer();
                   		if("1".equals(type)){
               			    updatesql.append("update n_order set joinorderstatus='2',aftersale_status='5',exitmoneystatus='0'  ")
	       					    	 .append(",update_date='")
	  						         .append(pdtime)
	  						         .append("' ")
       					    		 .append(" where orderid='")
               			             .append(orderid)
               			             .append("'");
               				}else if("2".equals(type)){
               					 updatesql.append("update n_order set joinorderstatus='2',aftersale_status='5',exitmoneystatus='0' ")
	       					    	 .append(",update_date='")
	  						         .append(pdtime)
	  						         .append("' ")
       					    		 .append(" where id='")
               		             .append(id)
               		             .append("'");
               				}
			        systemService.updateBySqlString(updatesql.toString());
            	 System.out.println("returncode"+"="+"error");
            	 System.out.println("returninfo"+"="+"退款失败");
             }
             /**成功或者失败，余额都退*/
             getExitPrice(userId, userprice);
        } catch (Exception e) {
        	StringBuffer updatesql=new StringBuffer();
       		if("1".equals(type)){
   			    updatesql.append("update n_order set joinorderstatus='2',aftersale_status='5',exitmoneystatus='0'  ")
					    	 .append(",update_date='")
					         .append(pdtime)
					         .append("' ")
				    		 .append(" where orderid='")
   			             .append(orderid)
   			             .append("'");
   				}else if("2".equals(type)){
   					 updatesql.append("update n_order set joinorderstatus='2',aftersale_status='5',exitmoneystatus='0' ")
					    	 .append(",update_date='")
					         .append(pdtime)
					         .append("' ")
				    		 .append(" where id='")
   		             .append(id)
   		             .append("'");
   				}
        systemService.updateBySqlString(updatesql.toString());
        	System.out.println("returncode"+"="+"exception"+e.toString());
        	System.out.println("returninfo"+"="+"退款失败");
//        	e.printStackTrace();
        }
	}
	/**定时器操作  进行订单作废操作 joinorderstatus	拼单状态 0待完成，1已完成、2订单取消*/
	public void updateorder(){
		StringBuffer sql=new StringBuffer();
			      sql.append("select id,orderid,userprice,user_id,pay_sum,paymode,goods_sum,expressmoney,goodsname,joinordertype,goods_id,one_level_user,two_level_user,three_level_user from n_order  ")
					 .append(" where paystatus='0' and joinorderstatus='0' and goods_detaisl_type='2' and order_status = '1' ")
					 .append(" and  end_time < now() ");
			  	List<Object[]> org_object = systemService.findListbySql(sql.toString());
				for (int i = 0; i < org_object.size(); i++) {
					Object[] mode = org_object.get(i);
					String id=mode[0]+"";
					String orderid=mode[1]+"";
					String userprice=mode[2]+"";
					String userId=mode[3]+"";
					String paySum=mode[4]+"";
					String payWay=mode[5]+"";
					String goods_sum=mode[6]+"";//商品总价
					String goodsname=mode[8]+"";
					String joinordertype=mode[9]+"";
					String goodsId=mode[10]+"";
				//	String goods_sum=mode[6].toString();//商品总价
				//  String expressmoney=mode[7].toString();//快递费
					Object one = mode[11];
					String oneLevelUser = "";
					if(one != null){
						oneLevelUser = one.toString();
					}
					String twoLevelUser = "";
					Object two = mode[12];
					if(two != null){
						twoLevelUser = two.toString();
					}
					String threeLevelUser = "";
					Object three = mode[13];
					if(three != null){
						threeLevelUser = three.toString();
					}
					/**支付宝支付 paymode	支付方式0微信、1支付宝、2无支付、3公众号支付*/
					System.out.println("payWay=="+payWay);
					if("1".equals(payWay)){
						getAlipay("1",id, orderid, paySum, userId, userprice,goodsname,joinordertype,goodsId,oneLevelUser,twoLevelUser,threeLevelUser);
					}else if("0".equals(payWay)){
						getWeixinpay("1",id, orderid, paySum, userId, userprice,goodsname,joinordertype,goodsId,oneLevelUser,twoLevelUser,threeLevelUser);
					}else if("3".equals(payWay)){
						getWeixinHpay("1",id, orderid, paySum, userId, userprice,goodsname);
					}

				//	String userid=nOrder.getUserId();
					if(StringUtils.isNotBlank(userId)){
					tuisongdictservice.getInsert("你的订单作废", "商品名称:"+goodsname+",订单号:"+id+"因为没有拼团成功，你的订单作废", userId);
					if(StringUtils.isNotBlank(paySum) && !"0".equals(paySum)){
						weixinservice.sendTemplate_five(userId, "您参加的拼团由于团已过期，拼团失败", goodsname,goods_sum, paySum, "", "您的退款已经提交微信审核，感谢您的参与！");
					}else{
						weixinservice.sendTemplate_five(userId, "您参加的拼团由于团已过期，拼团失败", goodsname,goods_sum, paySum, "", "感谢您的参与！");
					}

					}
				}
	}
	 /**
	  * @Author shishanshan
	  * @Desprition 作废助力团订单
	  * @Date 2018/9/3 21:37
	  * @Param
	  * 		paystatus : 付款状态  0 已支付  1支付失败
	  * 		joinorderstatus : 参团状态  0待完成 1已完成 2订单取消
	  * 		goods_detaisl_type :  1 助力团 2普通团
	  * 		joinordertype : 拼单类型 0 开团 1拼团
	  * @Return
	  */
	public void updateorderForMember(){
		StringBuffer sql=new StringBuffer();
		sql.append("select id,user_id,goodsname,goods_sum,pay_sum,orderid,paymode,userprice from n_order  ")
				.append(" where paystatus='0' and joinorderstatus='0' and goods_detaisl_type='1' and joinordertype = '0' and order_status = '1' ")
				.append(" and  end_time < now() ");
		List<Object[]> org_object = systemService.findListbySql(sql.toString());
		for (int i = 0; i < org_object.size(); i++) {
			Object[] mode = org_object.get(i);
			String id=mode[0]+"";
			String userId=mode[1]+"";
			String goodsname=mode[2]+"";
			String goods_sum=mode[3]+"";
			String paySum=mode[4]+"";
			String orderid = mode[5]+"";
			String payWay = mode[6]+"";
			String userprice = mode[7]+"";
			//	String goods_sum=mode[6].toString();//商品总价
			//  String expressmoney=mode[7].toString();//快递费
			/**支付宝支付 paymode	支付方式0微信、1支付宝、2无支付、3公众号支付*/

			//	String userid=nOrder.getUserId();
			if(StringUtils.isNotBlank(userId)){
				tuisongdictservice.getInsert("你的订单作废", "商品名称:"+goodsname+",订单号:"+id+" 因为没有拼团成功，你的订单作废", userId);
			}
			StringBuffer updatesql=new StringBuffer();
			Format f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date today = new Date();
			String pdtime=f.format(today);
			updatesql.append("update n_order set joinorderstatus='2',gatheringstatus0='3',aftersale_status='1',exitmoneystatus='0' ")
					.append(",update_date='")
					.append(pdtime)
					.append("' ")
					.append(" where orderid='")
					.append(id)
					.append("'");
			systemService.updateBySqlString(updatesql.toString());
			//删除会员商品记录表信息
			String sql2 = "delete from n_goods_join_record where order_id = '"+id+"'";
			systemService.updateBySqlString(sql2);
		/*	if("1".equals(payWay)){
				getAlipay("1",id, orderid, paySum, userId, userprice,goodsname,null,null);
			}else if("0".equals(payWay)){
				getWeixinpay("1",id, orderid, paySum, userId, userprice,goodsname,null,null);
			}else if("3".equals(payWay)){
				getWeixinHpay("1",id, orderid, paySum, userId, userprice,goodsname);
			}*/
			//发送消息
			weixinservice.sendTemplate_five(userId, "您参加的拼团由于团已过期，拼团失败", goodsname,goods_sum, paySum, "", "感谢您的参与！");
		}
	}

	/**
	 * 获取随机字符串
	 * @return
	 */
	public static String getNonceStr() {
		// 随机数
		String currTime = TenpayUtil.getCurrTime();
		// 8位日期
		String strTime = currTime.substring(8, currTime.length());
		// 四位随机数
		String strRandom = TenpayUtil.buildRandom(4) + "";
		// 10位序列号,可以自行调整。
		return strTime + strRandom;
	}

	/**订单作废，退还余额*/
	public void getExitPrice(String userId,String userprice){
		NUserEntity user=systemService.getEntity(NUserEntity.class, userId);
		if(!"0".equals(userprice) && !StringUtil.is_khEmpty(userprice)){
			   BigDecimal b1 = new BigDecimal(userprice);
			   String  price=user.getPrice();
		       BigDecimal b2 = new BigDecimal(price);
		      // String new_price= b2.subtract(b1).toString();
		       String new_price= b1.add(b2).setScale(2,BigDecimal.ROUND_HALF_DOWN).toString();
		       user.setPrice(new_price);
		       systemService.saveOrUpdate(user);
		}
	}

	/**定时器 操作    进行抽奖活动的定时器，拼单1天以后，没有制定抽奖人，进行指定
	 *退款的状态 aftersale_status	售后状态	c	36		0申请退款1退款成功，2驳回退款3平台强制退款4平台拒绝退款5退款失败
	 *marketing_one	订单所属活动	c	50		0正常1、限时秒杀、2、9.9特卖、3抽奖、4新品推荐、5赚客、6首页
	 *order_status 0待付款1待拼单，2待发货3已发货4已签收5待评价6售后处理中
	 *marketing_one	订单所属活动 0正常1、限时秒杀、2、9.9特卖、3抽奖、4新品推荐、5赚客、6首页
	 * joinorderstatus	拼单状态 0待完成，1已完成、2订单取消
	 * ptstatus	拼团状态	c	36		0未完成1已完成（主要用于任务团，发放的劵使用）*/
	public void getExitThree(){
		//String ptPerson="";
	//	String orderid="";
		 StringBuffer ptsuccessql=new StringBuffer();
		 ptsuccessql.append(" select id,orderid,ptPerson from  ")
		            .append("(select id,orderid,COUNT(*)as ptPerson from n_order ")
		            .append(" where  TO_DAYS( NOW( ) ) - TO_DAYS(pdtime) >= 1  and joinorderstatus='1' and order_status='1' ")
		            .append(" and goods_detaisl_type='1' and ptstatus='1'and marketing_one='3' GROUP BY orderid) as t1 ")
		            .append(" UNION ALL ")
		            .append(" select id,orderid,ptPerson from ")
					.append("(select id,orderid,COUNT(*)as ptPerson from n_order ")
			        .append(" where  TO_DAYS( NOW( ) ) - TO_DAYS(pdtime) >= 1  and joinorderstatus='1' and order_status='1' ")
			        .append(" and goods_detaisl_type='2' and marketing_one='3' GROUP BY orderid  ) as t2  ");
		 /**获得符合条件的拼团进行抽奖*/
		 List<Object[]>  ptsucces_list = systemService.findListbySql(ptsuccessql.toString());
	   for(int i=0; i<ptsucces_list.size(); i++) {
		        Object[] ptsucces_object=ptsucces_list.get(i);
		        if(ptsucces_object!=null){
		          String orderid=ptsucces_object[1]+"";
		          /***查询改拼团下的所有人*/
		          String  threesql=getthreesql(orderid);
		          List<Object[]>  three_list = systemService.findListbySql(threesql);
		          /**抽奖,进行随机取数**/
	    	      Random random = new Random();
		          int tmp = random.nextInt(Integer.valueOf(three_list.size()));
				for(int k=0; k<three_list.size(); k++) {
					   Object[]  threeObject= three_list.get(k);
						String n_orderid=threeObject[3].toString();
						String user_id=threeObject[4].toString();
					if(k==tmp){
						/**中奖人*/
						updatethreesql_t(n_orderid);
						if(!StringUtil.is_khEmpty(user_id))
						tuisongdictservice.getInsert("你被抽中了", "你参与的抽奖活动，你被抽中了", user_id);
					}else{
						/**未中奖人*/
						updatethreesql(n_orderid);
					}
				}
		   }
	   }
	}
	public String getthreesql(String orderid){
		 StringBuffer threesql=new StringBuffer();
		 threesql.append("select t1.id,t1.username,t1.price,t2.id as n_orderid,user_id ")
				 .append("from n_user t1,n_order t2 where  t1.id=t2.user_id   and t2.joinorderstatus='1' ")
				 .append( " and TO_DAYS( NOW( ) ) - TO_DAYS(t2.pdtime) >= 1 ")
	    		 .append(" and t2.orderid='")
	             .append(orderid)
	             .append("'");
		return threesql.toString();
	}
	/**中奖人*/
	/***joinorderstatus	拼单状态 0待完成，1已完成、2订单取消
	 * order_status 0待付款1待拼单，2待发货3已发货4已签收5待评价6售后处理中
	 * gatheringstatus	商家收款状态 0未收款 1冻结中2已收款*/
	public void updatethreesql_t(String n_orderid){
		StringBuffer updatethreesql=new StringBuffer();
	     updatethreesql.append("update n_order set ");
	     updatethreesql.append(" joinorderstatus='1',order_status='2',gatheringstatus='1' where ")
        .append("  paystatus='0' and  ")
		 .append("  id='")
        .append(n_orderid)
        .append("'");
		 systemService.updateBySqlString(updatethreesql.toString());
	}
	/**未中奖人*/
	public void updatethreesql(String n_orderid){
		/***joinorderstatus	拼单状态 0待完成，1已完成、2订单取消
		 *  payorderstatus   支付类型			0微信、1支付宝、2优惠券，3、优惠券结合使用   4余额结合
         *  paymode	支付方式		0微信、1支付宝、2无支付
		 *  goods_sum	商品总价
		 * 	numbers	数量
		 * 	pay_sum	支付金额
		 * 	expressmoney	快递费用
		 *  userprice	个人账户金额
		 * */
		NOrderEntity norder=systemService.getEntity(NOrderEntity.class, n_orderid);
		/**支付宝支付 paymode	支付方式0微信、1支付宝、2无支付、3公众号支付*/
		String paymode=norder.getPaymode();
		String id=norder.getId();
		String orderid=norder.getOrderid();
		String paySum=norder.getPaySum();
		String userId=norder.getUserId();
		String userprice=norder.getUserprice();
		/*if("1".equals(paymode)){
			getAlipay("2",id, orderid, paySum, userId, userprice);
		}else if("0".equals(paymode)){
			getWeixinpay("2",id, orderid, paySum, userId, userprice);
		}else if("3".equals(paymode)){
			getWeixinHpay("2",id, orderid, paySum, userId, userprice);
		}*/
		if(!StringUtil.is_khEmpty(userId))
			tuisongdictservice.getInsert("你未被抽中", "你参与的抽奖活动，没有被抽中", userId);
	}
}
