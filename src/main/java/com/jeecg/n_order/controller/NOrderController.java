package com.jeecg.n_order.controller;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.jeecg.demo.controller.FileController;
import com.jeecg.n_goods_detaisl.entity.NGoodsDetaislEntity;
import com.jeecg.n_merchant.entity.NMerchantEntity;
import com.jeecg.n_merchant_coupon.entity.NMerchantCouponEntity;
import com.jeecg.n_order.entity.ExpressEntity;
import com.jeecg.n_order.entity.NOrderEntity;
import com.jeecg.n_order.service.NOrderServiceI;
import com.jeecg.n_phone_recharge.entity.NPhoneRechargeEntity;
import com.jeecg.n_phone_recharge.service.CostService;
import com.jeecg.n_red_packet.entity.NRedPacketEntity;
import com.jeecg.n_red_packet_rule.entity.NRedPacketRuleEntity;
import com.jeecg.n_user.entity.NUserEntity;
import com.jeecg.n_user_address.entity.NUserAddressEntity;
import com.jeecg.n_user_price.entity.NUserPriceEntity;
import com.jeecg.s_tuisong.TuisongDictService;
import com.jeecg.weixin.util.WeixinService;
import com.jeecg.weixin_pay.entity.WeixinPayEntity;
import com.jeecg.weixin_pay.util.NWeixinPayService;
import com.jeecg.wepay.util.GetWxOrderno;
import com.jeecg.wepay.util.RequestHandler;
import com.jeecg.wepay.util.TenpayUtil;
import com.jeecg.wepay.util.http.HttpClientConnectionManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.text.Format;
import java.text.SimpleDateFormat;

import javax.net.ssl.SSLContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeecg.wepay.util.payUtils.WXPayUtil;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.apache.poi.util.SystemOutLogger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.core.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.common.TreeChildCount;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSTypegroup;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;

import java.io.OutputStream;

import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.vo.TemplateExcelConstants;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Map;
import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.jeecgframework.core.beanvalidator.BeanValidators;

import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import java.math.BigDecimal;
import java.net.URI;
import java.security.KeyStore;

import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @Title: Controller
 * @Description: 订单管理
 * @author onlineGenerator
 * @date 2017-12-19 11:04:29
 * @version V1.0
 *
 */
@Controller
@RequestMapping("/nOrderController")
public class NOrderController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(NOrderController.class);

	@Autowired
	private NOrderServiceI nOrderService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private TuisongDictService tuisongdictservice;
	@Autowired
	private WeixinService weixinservice;
	@Autowired
	private Validator validator;
	@Autowired
	private CostService costservice;
	@Autowired
	private NWeixinPayService nweixinpayservice;
	//支付宝相关
	private static final String APP_ID = "2017120100293365";
	private static final String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCPzl+qbk/hrT+QbYzDctSpWyFGZYGuLyUWsXfhyVfNX+NNUSKWaQbzyOONuT9VGxfYA6220ALvXB6rPe/DhnW8GClYXWdH7BLKg4rnzos7YSVNaXCN58sTpJgHXbjtuvhMLZUqN0HXdZwFJpL50aIgb7XepQUxUvJodStGCf5iHk1SKKiNyKY9hSvVmvFDrfQc58hdWAI3t2nCd+EuZMfcJTP7xZrazLeE9AacasZ0DE7Kujl66zkXBta3Z6Rf+wvbBCUebVDz3bV6qp6YSbg/gk+Y+2zYXvIBghM5xJFBiELdJG85hxdcngNTag1Q6zmoNhXzObv2IHAdQmCeAFvDAgMBAAECggEAZmlCF82XddA7hE9//3C3oiQT/l4rjDBm6VJZXaJVtRykc1tkllkVameWJkfWonU49c0o2Rgp/uxLqwfgyA3pqppKV3OtKbslZrNnKM4euZrlRcvhLC32oXaGDjjgieytBxMvN3FCon5PLhvab66rFw53Jqe+mvHHUDyhJK/ZSWXZuTzNdawEPQrsOD9xZb+pLLI3GdA2BSZzCR1q9zGYYvoHpQX8+NK4z/39VmUoRU7Ny0OvfL/4L7wnScYnu2irWCMjTZHjfq1tnW3/lsYlW72AXEjnaZWLg0ZHwvSQnAEHI/hy6p9P5mAbRrehrgenWW/Cg1fiX4WQCcrSC4QMgQKBgQDC6Cv4YQ2SGqy6xT7gNf+6armMhyJ+lONcci7HfZAfmP17OmAW8io0Qldm/g/W9lBvpjMU2LNKPesdWE6PMfqnn/A71HskhAngvWPLq2AMWBp/kbAmYQmp6JBKZTQCvR/SxjfG1CsbYb/L7KYR72Mp/pfAEJRnJI5JK+X3nHRLTwKBgQC84b7eD3RJ4lhvSpG5Q3j1yebGbwyeST5JQc1sEsqRJ++VewZe97tAomjyWoCBNJb+rnsPkYv7OVvW06i9aOMhQ6/M8CM58vAcSwkOZsgq+Nq2sxR96u58LejcLMj3vXGis1FIR8WA6EM4gUDoezHDIO9cicY14tD937/HZw27TQKBgHKNbjpfIFC8qMRk5V11n0V7MG6thdKLw000NtY8sBZCHsjsOEmELtXkH+aCb+DRh9j2/5LDAi0iUys+GX4Dy+P1FoazjWSazgtuhFbR9HOM3JYZlEQaSEm6TAPNk1IAwdFpeqK7VFKVktpRzhFAdzHZVmsl03MDgzTyPgjXxWn1AoGAFs8D53jiSBHHMBlHI6IcN0IcVhYO5gZeOSZzEfvq7kBuVBS5Hjq4KAP0vF9laTTajwKu5aBj0QCKMJT6qXTDCL9NuWe+OT8285O0EkMjJN1MPAfAD7yQ8/nvRrc5xYDg+g7BYAMavIhPpcEl/2zxA2k0vm38u4EaT633ULMHG3kCgYEAryCPTXg2LBtd+vFye80sHc1GkZnMtg7Gbil7skImH793eOhBr1kx49AE+wciyy+VDpyOd73vHgLfinMAZeXwUPlQyatFXEhd1nGzxUqp/7R3BnaoQRQI7VGLz5qVsXSRdAJZvSGuHWyXmYwHKpuxUImOjGrJKCpLTKedeTnEb4k=";
	private static final String CHARSET = "utf-8";
	private static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhSwkHjotgK5YM5/Q/LsiO8xfeXFvrm5iMZzXmNlaIlwMMldDzQLtlFa4UMCt3GFvWq3KgM/Sd8MFgQviGRdcQ+1n6TqVaVgYqewL0KIEI6ggDFsB15rxxnZ/PTbAdXww1MMQ2X5iE/GPy7vOnEZgC04/jjGjBNgvVX2LX+mbwbNTwtAX1tlyafSJOQ0Hp+qVi2qRuQXwmzdy73paRIDpnppx0oGmXdevlmNTwkev4I6zUEmGTi9wEudOSmY6eU87m5QxbO74L6FUlY0CBzYTWO9fCdnGz871D+Ni252SduvmBDNAexuOLC6YMWUGDUUrZxlZCj/3YgM2+g9x+E/sxwIDAQAB";
	private static final String ALIPAY_URL = "https://openapi.alipay.com/gateway.do";
	private static final String SIGN_TYPE= "RSA2";


	//微信app相关
	//微信支付商户开通后 微信会提供appid和appsecret和商户号partner
	/*private static final String wxappid = "wx2364e2025d750163";
	private static final String wxappsecret = "0f210b94647b4dbbe910e9a053b494b4";
	private static final String wxpartner = "1495222432";
	//这个参数partnerkey是在商户后台配置的一个32位的key,微信商户平台-账户设置-安全设置-api安全
	private static final String wxpartnerkey = "yaowei1987yaowei1987yaowei1987ya";*/

	//微信公众号相关
	//微信支付商户开通后 微信会提供appid和appsecret和商户号partner
//	private static final String gzappid = "wx8d5f593b39c52c6f";
//	private static final String gzappsecret = "f0bc2d092fdc5b89b3ac7362b2105156";
//	private static final String gzpartner = "1493251372";
	//微信另一个商户号
//		private static String gzpartner = "1495222432";
		//微信另一个商户号
/*	private static String gzappid = "wx6319f129c69fb18d";
	private static String gzappsecret = "878bc52abb9b5d92034ceb9d833f706d";
				private static String gzpartner = "1510630121";
	//这个参数partnerkey是在商户后台配置的一个32位的key,微信商户平台-账户设置-安全设置-api安全
	private static final String gzpartnerkey = "yaowei1987yaowei1987yaowei1987ya";*/


	/**
	 *
	 * 虚拟商品充值
	 * @return
	 */
	@RequestMapping(params = "phonelist")
	public ModelAndView phonelist(HttpServletRequest request) {
		TSUser tsuser=ResourceUtil.getSessionUserName();
		String merchantId=tsuser.getPkid();
		tsuser.getRealName();
		request.setAttribute("merchantid", merchantId);
		return new ModelAndView("com/jeecg/n_order/ptnOrderList_phone");
	}
	/**
	 * 结算中心
	 * 用户账单信息
	 * @return
	 */
	@RequestMapping(params = "moneylist")
	public ModelAndView moneylist(HttpServletRequest request) {
		TSUser tsuser=ResourceUtil.getSessionUserName();
		String merchantId=tsuser.getPkid();
		tsuser.getRealName();
		request.setAttribute("merchantid", merchantId);
		return new ModelAndView("com/jeecg/n_order/money/nOrderList");
	}
	/**
	 * 结算中心
	 * 用户账单信息
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		TSUser tsuser=ResourceUtil.getSessionUserName();
		String merchantId=tsuser.getPkid();
		tsuser.getRealName();
		request.setAttribute("merchantid", merchantId);
		return new ModelAndView("com/jeecg/n_order/nOrderList");
	}
	/**
	 * 订单管理列表 页面跳转  限时抽奖订单   限时抽奖目录
	 * NMarketingThreeController
	 * @return
	 */
	@RequestMapping(params = "Threelist")
	public ModelAndView Threelist(HttpServletRequest request) {
		TSUser tsuser=ResourceUtil.getSessionUserName();
		String merchantId=tsuser.getPkid();
		tsuser.getRealName();
		request.setAttribute("merchantid", merchantId);
		return new ModelAndView("com/jeecg/n_order/threenOrderList");
	}
	/**
	 * 订单管理列表 页面跳转  限时抽奖订单   限时抽奖人员名单
	 * NMarketingThreeController
	 * @return
	 */
	@RequestMapping(params = "Threepersonlist")
	public ModelAndView Threepersonlist(HttpServletRequest request,
			String merchantid,String orderid,String id) {
		request.setAttribute("merchantid", merchantid);
		request.setAttribute("orderid", orderid);
		request.setAttribute("id", id);
		return new ModelAndView("com/jeecg/n_order/threepersonList");
	}
	/**
	 * 订单管理列表 页面跳转  正常订单
	 * 图通团
	 * order_type	订单类型0正常 1售后处理中
	 * @return
	 */
	@RequestMapping(params = "listputong")
	public ModelAndView listputong(HttpServletRequest request) {
		TSUser tsuser=ResourceUtil.getSessionUserName();
		String merchantId=tsuser.getPkid();
		tsuser.getRealName();
		request.setAttribute("merchantid", merchantId);
		return new ModelAndView("com/jeecg/n_order/nOrderListputong");
	}

	/**
	 * 订单管理列表 页面跳转  正常订单
	 * 任务团
	 * order_type	订单类型0正常 1售后处理中
	 * @return
	 */
	@RequestMapping(params = "listrenwu")
	public ModelAndView listrenwu(HttpServletRequest request) {
		TSUser tsuser=ResourceUtil.getSessionUserName();
		String merchantId=tsuser.getPkid();
		tsuser.getRealName();
		request.setAttribute("merchantid", merchantId);
		return new ModelAndView("com/jeecg/n_order/nOrderListrenwu");
	}

	/**
	 * 订单管理列表 页面跳转 首页查看待发货订单
	 * order_status	订单状态			0待付款1待拼单，2待发货3已发货4已签收5待评价6售后处理中
	 * @return
	 */
	@RequestMapping(params = "homelist")
	public ModelAndView homelist(HttpServletRequest request) {
		TSUser tsuser=ResourceUtil.getSessionUserName();
		String merchantId=tsuser.getPkid();
		tsuser.getRealName();
		request.setAttribute("merchantid", merchantId);
		return new ModelAndView("com/jeecg/n_order/home/homenOrderList");
	}
	/**
	 * 订单管理列表 页面跳转  首页页查看售后订单
	 * order_type	订单类型0正常 1售后处理中
	 * @return
	 */
	@RequestMapping(params = "homeaftersalelist")
	public ModelAndView homeaftersalelist(HttpServletRequest request) {
		TSUser tsuser=ResourceUtil.getSessionUserName();
		String merchantId=tsuser.getPkid();
		tsuser.getRealName();
		request.setAttribute("merchantid", merchantId);
		return new ModelAndView("com/jeecg/n_order/home/homeaftersalenOrderList");
	}

	/**
	 * 订单管理列表 页面跳转  售后订单
	 * order_type	订单类型0正常 1售后处理中
	 * @return
	 */
	@RequestMapping(params = "aftersalelist")
	public ModelAndView aftersalelist(HttpServletRequest request) {
		TSUser tsuser=ResourceUtil.getSessionUserName();
		String merchantId=tsuser.getPkid();
		tsuser.getRealName();
		request.setAttribute("merchantid", merchantId);
		return new ModelAndView("com/jeecg/n_order/aftersalenOrderList");
	}

	/**
	 * 订单管理列表 页面跳转  平台查看售后订单
	 * order_type	订单类型0正常 1售后处理中
	 * @return
	 */
	@RequestMapping(params = "ptaftersalelist")
	public ModelAndView ptaftersalelist(HttpServletRequest request) {
		/*TSUser tsuser=ResourceUtil.getSessionUserName();
		String merchantId=tsuser.getPkid();
		tsuser.getRealName();
		request.setAttribute("merchantid", merchantId);*/
		return new ModelAndView("com/jeecg/n_order/ptaftersalenOrderList");
	}
	/**
	 * 订单管理列表 页面跳转平台查看信息
	 *
	 * @return
	 */
	@RequestMapping(params = "ptlist")
	public ModelAndView ptlist(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/n_order/ptnOrderList");
	}
	/**
	 * 订单管理列表 页面跳转平台查看信息
	 *
	 * @return
	 */
	@RequestMapping(params = "ptlist_exit")
	public ModelAndView ptlist_exit(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/n_order/ptnOrderList_exit");
	}
	@RequestMapping(params = "plist_member")
	public ModelAndView plist_member(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/n_order/ptnOrderList_member");
	}

	/*查询待发货商品*/
	@RequestMapping(params = "sendgoods")
	public void sendgoods(HttpServletRequest request,HttpServletResponse response) {
		TSUser tsuser=ResourceUtil.getSessionUserName();
		String merchantId=tsuser.getPkid();
		StringBuffer sql=new StringBuffer();
		sql.append("select COUNT(*)as numbs,id from n_order where order_status='2' and order_type='0' ")
			.append(" and merchant_id='")
			.append(merchantId)
			.append("'");
		List<Object[]> mode_list = systemService.findListbySql(sql.toString());
		   String numbs="0";
		   if(mode_list.size()>0){
			   Object[] t= mode_list.get(0);
			   numbs=t[0].toString();
		   }
		   JSONObject object = new JSONObject();
			object.put("numbs", numbs);
			object.put("status", "success");
			object.put("message", "获取信息成功");
			TagUtil.getSendJson(response, object.toString());
	  }
	/*查询退款商品*/
	@RequestMapping(params = "exitgood")
	public void exitgood(HttpServletRequest request,
			HttpServletResponse response) {
		TSUser tsuser=ResourceUtil.getSessionUserName();
		String merchantId=tsuser.getPkid();
		StringBuffer sql=new StringBuffer();
		sql.append("select COUNT(*)as numbs,id from n_order where aftersale_status='0' and order_type='1' ")
			.append(" and merchant_id='")
			.append(merchantId)
			.append("'");
		List<Object[]> mode_list = systemService.findListbySql(sql.toString());
		   String numbs="0";
		   if(mode_list.size()>0){
			   numbs=mode_list.get(0)[0].toString();
		   }
		   JSONObject object = new JSONObject();
			object.put("numbs", numbs);
			object.put("status", "success");
			object.put("message", "获取信息成功");
			TagUtil.getSendJson(response, object.toString());
	  }
	/**
	 * easyui AJAX请求数据
	 *
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(NOrderEntity nOrder,String starttime,String endtime,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid,String all) {
		String goodsname = nOrder.getGoodsname();
		String id=nOrder.getId();
		String realname = nOrder.getRealname();
		if (!StringUtil.isEmpty(goodsname)) {
			nOrder.setGoodsname("*"+goodsname+"*");
		}
		if (!StringUtil.isEmpty(id)) {
			nOrder.setId("*"+id+"*");
		}
		if(StringUtils.isNotBlank(realname)){
			nOrder.setRealname("*"+realname+"*");
		}
		if(StringUtils.isNotBlank(nOrder.getRedResultCode()) && "qita".equals(nOrder.getRedResultCode())){
			nOrder.setRedResultCode("N");
		}
		CriteriaQuery cq = new CriteriaQuery(NOrderEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, nOrder, request.getParameterMap());
		try{
		//自定义追加查询条件

			//cq.between("createDate", starttime, endtime);
		   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.is_khEmpty(starttime)){
				cq.ge("createDate", sdf.parse(starttime));
			}
			if(!StringUtil.is_khEmpty(endtime)){
				cq.le("createDate",  sdf.parse(endtime));
			}

			//主要用于给平台展示全部的订单
			if(StringUtil.is_khEmpty(all)){
				//goods_detaisl_type	商品类型	c	50		1任务，2普通
				String goodsDetaislType=nOrder.getGoodsDetaislType();
				if("1".equals(goodsDetaislType)){
					 // 0微信、1支付宝、2优惠券，3、优惠券结合使用   4余额结合
					String [] payorderstatus= {"0","1","4"};
					cq.in("payorderstatus",payorderstatus);
				}
			}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.nOrderService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	/**
	 * easyui AJAX请求数据
	 *
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "liushuidatagrid")
	public void liushuidatagrid(NOrderEntity nOrder,String starttime,String endtime,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String goodsname = nOrder.getGoodsname();
		String id=nOrder.getId();
		if (!StringUtil.isEmpty(goodsname)) {
			nOrder.setGoodsname("*"+goodsname+"*");
		}
		if (!StringUtil.isEmpty(id)) {
			nOrder.setId("*"+id+"*");
		}
		CriteriaQuery cq = new CriteriaQuery(NOrderEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, nOrder, request.getParameterMap());
		try{
		//自定义追加查询条件

			//cq.between("createDate", starttime, endtime);
		   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.is_khEmpty(starttime))
			cq.ge("createDate", sdf.parse(starttime));
			if(!StringUtil.is_khEmpty(endtime))
			cq.le("createDate",  sdf.parse(endtime));
			String [] orderStatus= {"4","5"};
			cq.in("orderStatus",orderStatus);
		/*	String goodsDetaislType=nOrder.getGoodsDetaislType();
			if("1".equals(goodsDetaislType)){
				 // 0微信、1支付宝、2优惠券，3、优惠券结合使用   4余额结合
				String [] payorderstatus= {"0","1","4"};
				cq.in("payorderstatus",payorderstatus);
			}*/
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.nOrderService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	/**
	 * easyui AJAX请求数据
	 *
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "homedatagrid")
	public void homedatagrid(NOrderEntity nOrder,String starttime,String endtime,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String goodsname = nOrder.getGoodsname();
		String id=nOrder.getId();
		if (!StringUtil.isEmpty(goodsname)) {
			nOrder.setGoodsname("*"+goodsname+"*");
		}
		if (!StringUtil.isEmpty(id)) {
			nOrder.setId("*"+id+"*");
		}
		TSUser tsuser=ResourceUtil.getSessionUserName();
		String merchantId=tsuser.getPkid();
		nOrder.setMerchantId(merchantId);
		CriteriaQuery cq = new CriteriaQuery(NOrderEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, nOrder, request.getParameterMap());
		try{
		//自定义追加查询条件

			//cq.between("createDate", starttime, endtime);
			  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.is_khEmpty(starttime))
			cq.ge("createDate", sdf.parse(starttime));
			if(!StringUtil.is_khEmpty(endtime))
			cq.le("createDate",  sdf.parse(endtime));
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.nOrderService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}





	/**
	 * 删除订单管理
	 *
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(NOrderEntity nOrder, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		nOrder = systemService.getEntity(NOrderEntity.class, nOrder.getId());
		message = "订单管理删除成功";
		try{
			nOrderService.delete(nOrder);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "订单管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 批量充值
	 *
	 * @return
	 */
	 @RequestMapping(params = "phone_Recharge")
	@ResponseBody
	public AjaxJson phone_Recharge(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "充值成功";
		try{
			if (!StringUtil.isEmpty(ids)) {
				for(String id:ids.split(",")){
					NOrderEntity nOrder = systemService.getEntity(NOrderEntity.class,id);
					//String phone=nOrder.getPhone(); sss修改手机号为aftersalePhone
					String phone = nOrder.getAftersalePhone();
					String bazaarPrice=nOrder.getBazaarPrice();
					Integer cardnum=Integer.parseInt(bazaarPrice);
					String result=costservice.onlineOrder(phone, cardnum, id);
					System.out.println("result="+result);
					JSONObject	json=JSONObject.parseObject(result);
					String result_get=json.getString("result");
					String  errorCode=json.getString("error_code");
					String  reason=json.getString("reason");
					String gameState="10";
					if("0".equals(errorCode)){
					JSONObject	result_get_json=JSONObject.parseObject(result_get);
					 gameState=result_get_json.getString("game_state");
					}
					nOrder.setGameState(gameState);
					systemService.saveOrUpdate(nOrder);
					NPhoneRechargeEntity nPhoneRecharg=new NPhoneRechargeEntity();
					nPhoneRecharg.setId(StringUtil.getId());
					nPhoneRecharg.setOrderId(id);
					nPhoneRecharg.setMerchantId(nOrder.getMerchantId());
					nPhoneRecharg.setCardnum(bazaarPrice);
					nPhoneRecharg.setPhone(phone);
					nPhoneRecharg.setResult(result);
					nPhoneRecharg.setGameState(gameState);
					nPhoneRecharg.setErrorCode(errorCode);
					nPhoneRecharg.setSendType("0");//0充值1查询
					nPhoneRecharg.setCreateDate(new Date());
					nPhoneRecharg.setReason(reason);
					systemService.save(nPhoneRecharg);
					systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
				}
			}else{
				message = "请选择想要充值的记录";
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "充值失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

		/**
		 * 批量充值和发红吧
		 *
		 * @return
		 */
		 @RequestMapping(params = "phone_Recharge_red")
		@ResponseBody
		public AjaxJson phone_Recharge_red(String ids,HttpServletRequest request){
			String message = null;
			AjaxJson j = new AjaxJson();
			message = "充值成功";
			try{
				NRedPacketRuleEntity  nredpacketrule=
						systemService.findUniqueByProperty(NRedPacketRuleEntity.class, "merchantId", "1");
				 String ruletotalamount=nredpacketrule.getRuleTotalAmount();
				  String[]arr=ruletotalamount.split(",");
				if (!StringUtil.isEmpty(ids)) {
					for(String id:ids.split(",")){
						int index=(int)(Math.random()*arr.length);
						 String totalAmount=arr[index];
                      //  String totalAmount=ran.nextInt(Integer.parseInt(ruletotalamount))+"."+ran.nextInt(9);
						NOrderEntity nOrder = systemService.getEntity(NOrderEntity.class,id);
						//String phone=nOrder.getPhone();sss修改手机号为aftersalePhone
						String phone=nOrder.getAftersalePhone();
						String bazaarPrice=nOrder.getBazaarPrice();
						Integer cardnum=Integer.parseInt(bazaarPrice);
						String result=costservice.onlineOrder(phone, cardnum, id);
						System.out.println("result="+result);
						JSONObject	json=JSONObject.parseObject(result);
						String result_get=json.getString("result");
						String  errorCode=json.getString("error_code");
						String  reason=json.getString("reason");
						String gameState="10";
						if("0".equals(errorCode)){
						JSONObject	result_get_json=JSONObject.parseObject(result_get);
						 gameState=result_get_json.getString("game_state");
						}
						nOrder.setGameState(gameState);
						String userid=nOrder.getUserId();
                    	String openid=null;
        				if(!StringUtil.is_khEmpty(userid)){
        					NUserEntity nUser = systemService.getEntity(NUserEntity.class, userid);
        					if(nUser!=null){
        						openid=nUser.getOpenid();
        					}
        				}
        				 String redResultCode="qita";//用户没有openid
        				if(!StringUtil.is_khEmpty(openid)){
        					result=SendRedPacket(totalAmount,openid, nOrder, nredpacketrule);
        				}
                        nOrder.setRedResultCode(redResultCode);
                        nOrder.setTotalAmount(totalAmount);
						systemService.saveOrUpdate(nOrder);
						NPhoneRechargeEntity nPhoneRecharg=new NPhoneRechargeEntity();
						nPhoneRecharg.setId(StringUtil.getId());
						nPhoneRecharg.setOrderId(id);
						nPhoneRecharg.setMerchantId(nOrder.getMerchantId());
						nPhoneRecharg.setCardnum(bazaarPrice);
						nPhoneRecharg.setPhone(phone);
						nPhoneRecharg.setResult(result);
						nPhoneRecharg.setGameState(gameState);
						nPhoneRecharg.setErrorCode(errorCode);
						nPhoneRecharg.setSendType("0");//0充值1查询
						nPhoneRecharg.setCreateDate(new Date());
						nPhoneRecharg.setReason(reason);
						systemService.save(nPhoneRecharg);
						systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
					}
				}else{
					message = "请选择想要充值的记录";
				}
			}catch(Exception e){
				e.printStackTrace();
				message = "充值失败";
				throw new BusinessException(e.getMessage());
			}
			j.setMsg(message);
			return j;
		}
	  /**
	   * @Author shishanshan
	   * @Desprition 批量发送红包
	   * @Date 2018/10/17 14:57
	   * @Param
	   * @Return
	   */
	 @RequestMapping(params = "send_red_packet")
	 @ResponseBody
	 public AjaxJson send_red_packet(String ids,HttpServletRequest request){
		String message = "";
		AjaxJson ajaxJson = new AjaxJson();
		message = "发放成功";
		try{
			NRedPacketRuleEntity  nRedPacketRuleEntity = systemService.findUniqueByProperty(NRedPacketRuleEntity.class, "merchantId", "1");
			String ruleTotalAmount = nRedPacketRuleEntity.getRuleTotalAmount();
			String[]arr=ruleTotalAmount.split(";");
			if (!StringUtil.isEmpty(ids)) {
				for(String id:ids.split(",")){
					//获取随机红包金额
					int index=(int)(Math.random()*arr.length);
					String totalAmount=arr[index];
					//获取订单信息
                    String red_result_code = "FAIL";
					NOrderEntity nOrder = systemService.getEntity(NOrderEntity.class,id);
                    String  redResultCode = nOrder.getRedResultCode();
                    if(StringUtils.isNotBlank(redResultCode) && "SUCCESS".equals(redResultCode)){
                    }else{
                        String userId=nOrder.getUserId();
                        NUserEntity nUserEntity = systemService.getEntity(NUserEntity.class, userId);
                        if (nUserEntity!=null){
                            red_result_code = "SUCCESS";
                            //1.钱包添加记录信息
                            NUserPriceEntity userPriceEntity = new NUserPriceEntity();
                            userPriceEntity.setId(StringUtil.getId());
                            userPriceEntity.setUserId(userId);
                            userPriceEntity.setRealname(nUserEntity.getRealname());
                            userPriceEntity.setCreateDate(new Date());
                            userPriceEntity.setUserPriceType("红包奖励");
                            userPriceEntity.setPrice(totalAmount);
                            userPriceEntity.setOrderid("4");
                            systemService.save(userPriceEntity);
                            //2.增加用户金额及总金额
                            String price = nUserEntity.getPrice();
                            if (!StringUtils.isNotBlank(price)){
                                price = "0";
                            }
                            price = add(totalAmount,price);
                            nUserEntity.setPrice(price);
                            //总余额
                            String totalPtice = nUserEntity.getExt1();
                            if (!StringUtils.isNotBlank(totalPtice)){
                                totalPtice = "0";
                            }
                            totalPtice =add(totalAmount,totalPtice);
                            nUserEntity.setExt1(totalPtice);
                            systemService.saveOrUpdate(nUserEntity);
                            //3.给用户发推送消息
                            weixinservice.sendTemplate_refferrer(userId,"领红包啦!",totalAmount,"闪购拼团红包奖励!","", DateUtils.formatTimehsm(new Date()),"点击查看钱包详情，如有疑问直接咨询在线客服。");
                        }
                    }

					/* String red_result_code="qita";//用户没有openid
					if(!StringUtil.is_khEmpty(openid)){
						red_result_code=SendRedPacket(totalAmount,openid, nOrder, nRedPacketRuleEntity);
					}*/
					//4修改订单状态
					nOrder.setRedResultCode(red_result_code);
                    nOrder.setTotalAmount(totalAmount);
					systemService.saveOrUpdate(nOrder);
					//systemService.save(nPhoneRecharg);
				}
			}else{
				message = "请选择要发放的订单!";
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "发放失败";
			throw new BusinessException(e.getMessage());
		}
		 ajaxJson.setMsg(message);
		return ajaxJson;
	 }
    public String add(String number1, String number2) {
        BigDecimal b1 = new BigDecimal(number1);
        BigDecimal b2 = new BigDecimal(number2);
        return b1.add(b2).toString();
    }
	/**
	 * @Author shishanshan
	 * @Desprition 两数相减
	 * @Date 2018/10/20 23:05
	 * @Param
	 * @Return
	 */
	public String decre(String number1, String number2) {
		BigDecimal b1 = new BigDecimal(number1);
		BigDecimal b2 = new BigDecimal(number2);
		return b1.subtract(b2).toString();
	}
	 /**
	  * @Author shishanshan
	  * @Desprition 红包发放接口
	  * @Date 2018/10/17 14:53
	  * @Param
	  * @Return
	  */
	public String SendRedPacket(String price,String openid,NOrderEntity nOrder,NRedPacketRuleEntity  nredpacketrule){
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
		String merchant_id=nOrder.getMerchantId();
		// ---必须参数
				// 商户号
				String mch_id = gzpartner;
				// 随机字符串
				String nonce_str = getNonceStr();
				//商户订单号
				String id=StringUtil.getId();
				String mch_billno=id.substring(0, 28);
				//公众账号appid wxappid
				String wxappid=gzappid;
				//商户名称 send_name
				String send_name="闪购拼团";
				//用户openid re_openid
				String re_openid=openid;
				//付款金额 total_amount  是 1000 int 付款金额，单位分
				String total_amount=new BigDecimal(100).multiply(new BigDecimal(price))
						.setScale(0,BigDecimal.ROUND_HALF_DOWN).toString();
				//红包发放总人数 total_num 是 1 int 红包发放总人数 total_num=1
				String total_num="1";
				//红包祝福语 wishing 是 感谢您参加猜灯谜活动，祝您元宵节快乐！ String(128) 红包祝福语
				String wishing=nredpacketrule.getWishing();
				//Ip地址 client_ip 是 192.168.0.1 String(15) 调用接口的机器Ip地址
				String client_ip="127.0.0.1";
				//活动名称 act_name 是 猜灯谜抢红包活动 String(32) 活动名称
				String act_name=nredpacketrule.getActName();
				//备注 remark 是 猜越多得越多，快来抢！ String(256) 备注信息
				String remark=nredpacketrule.getRemark();
				//场景id scene_id 否 PRODUCT_8 String(32)  PRODUCT_1:商品促销
				String scene_id="PRODUCT_1";
				//
				/*-----  1.生成预支付订单需要的的package数据-----*/
				SortedMap<String, String> packageParams = new TreeMap<String, String>();
				packageParams.put("mch_id", mch_id);
				packageParams.put("nonce_str", nonce_str);
				packageParams.put("mch_billno", mch_billno);
				packageParams.put("wxappid", wxappid);
				packageParams.put("send_name", send_name);
				packageParams.put("re_openid", re_openid);
				packageParams.put("total_amount", total_amount);
				packageParams.put("total_num", total_num);
				packageParams.put("wishing", wishing);
				packageParams.put("client_ip", client_ip);
				packageParams.put("act_name", act_name);
				packageParams.put("remark", remark);
				packageParams.put("scene_id", scene_id);
//				        packageParams.put("risk_info", "posttime%3d123123412%26clientversion%3d234134%26mobile%3d122344545%26deviceid%3dIOS");
//				        packageParams.put("consume_mch_id", "10000097");
				packageParams.put("risk_info", "");
				packageParams.put("consume_mch_id", "");
				/*----2.根据package生成签名sign---- */
				RequestHandler reqHandler = new RequestHandler(null, null);
				reqHandler.init(gzappid, gzappsecret, gzpartnerkey);
				String sign = reqHandler.createSign(packageParams);
				/*----3.拼装需要提交到微信的数据xml---- */
				packageParams.put("sign", sign);
				String xml=MapToXml(packageParams);

				System.out.println("xml==="+xml);
				try {
					 /*----4.读取证书文件,这一段是直接从微信支付平台提供的demo中copy的，所以一般不需要修改---- */
					 KeyStore keyStore  = KeyStore.getInstance("PKCS12");
					 FileInputStream instream = new FileInputStream(new File("C:\\apiclient_cert.p12"));
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

					 HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack");
					 StringEntity  reqEntity  = new StringEntity(xml, "utf-8");
					 // 设置类型
					 reqEntity.setContentType("application/x-www-form-urlencoded");

					 httpPost.setEntity(reqEntity);
					 System.out.println("executing request" + httpPost.getRequestLine());
					 HttpResponse response = httpClient.execute(httpPost);
					 try {
						 HttpEntity entity = response.getEntity();
						 System.out.println(response.getStatusLine());
						 if (entity != null) {
							// 将返回的输入流转换成字符串
							 InputStream inputStream = entity.getContent();
							// InputStream inputStream = entity.getInputStream();
							 InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
							 BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

							 StringBuffer sb = new StringBuffer();
							String line;
							while ((line = bufferedReader.readLine()) != null) {
								sb.append(line);
							}
							 bufferedReader.close();
							  inputStreamReader.close();
							  // 释放资源
							  inputStream.close();
							  inputStream = null;
							  System.out.println("sb=="+sb.toString());
							  String  txm=sb.toString();
								  txm=txm.replaceAll("<!\\[CDATA\\[", "");
								 txm=txm.replaceAll("\\]\\]>", "");
								 System.out.println("t1xm="+txm);
								 System.out.println(txm.indexOf("<err_code_des>")+"="+txm.indexOf("</err_code_des>"));
								// String err_code_des=txm.substring(txm.indexOf("<consume_mch_id>"));
								// System.out.println("err_code_des="+err_code_des);
								 String result_code="SUCCESS";
								 System.out.println("result_code=="+txm.indexOf("<result_code>FAIL</result_code>"));
								 if(txm.indexOf("<result_code>FAIL</result_code>")>0){
									 result_code="FAIL";
								 }
							  //Map map = GetWxOrderno.doXMLParse(sb.toString());
							   //  String return_code=(String) map.get("return_code");
								 //String return_msg=(String) map.get("return_msg");
							  //   String result_code=(String) map.get("result_code");
								 //String err_code=(String) map.get("err_code");
								 NRedPacketEntity nRedPacket=new NRedPacketEntity();
								 nRedPacket.setId(mch_billno);
								 nRedPacket.setMerchantId(merchant_id);
								 nRedPacket.setOrderId(nOrder.getId());
								 nRedPacket.setOpenid(re_openid);
								 nRedPacket.setTotalAmount(price);
								// nRedPacket.setErrorCode(err_code);
								 nRedPacket.setResultCode(result_code);
							   //  nRedPacket.setReason(return_msg);
								 nRedPacket.setResult(sb.toString());
								 nRedPacket.setCreateDate(new Date());
								 systemService.save(nRedPacket);
								 return result_code;
						 }
						 EntityUtils.consume(entity);

					 } catch (Exception e) {
							//e.toString();
							System.out.println("returninfo"+"="+"1红包发放异常");
							e.printStackTrace();
							   return "fail";
						}




					 /*----5.发送数据到微信的退款接口---- */
				   /*  String url="https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";
					 HttpPost httpost= HttpClientConnectionManager.getPostMethod(url);
					 httpost.setEntity(new StringEntity(xml, "UTF-8"));
					 HttpResponse weixinResponse = httpClient.execute(httpost);
				 //    String jsonStr = EntityUtils.toString(weixinResponse.getEntity(),"UTF8");
					 String jsonStr = EntityUtils.toString(weixinResponse.getEntity());
					 jsonStr = jsonStr.replaceAll(" ", "");
					 jsonStr = jsonStr.replaceAll(" ", "");
					 jsonStr = jsonStr.replaceAll(" ", "");
					 jsonStr = jsonStr.replaceAll(" ", "");
					 System.out.println("UTF8="+jsonStr);

					 String  txm=jsonStr.replaceAll("<!\\[CDATA\\[", "");
					 txm=txm.replaceAll("\\]\\]>", "");
					 System.out.println("t1xm="+txm);
					 System.out.println(txm.indexOf("<err_code_des>")+"="+txm.indexOf("</err_code_des>"));
					 String err_code_des=txm.substring(txm.indexOf("<consume_mch_id>"));
					 System.out.println("err_code_des="+err_code_des);
					 Map map = GetWxOrderno.doXMLParse(txm);
					 String return_code=(String) map.get("return_code");
					 String return_msg=(String) map.get("return_msg");

					 NRedPacketEntity nRedPacket=new NRedPacketEntity();
					 nRedPacket.setId(mch_billno);
					 nRedPacket.setMerchantId(merchant_id);
					 nRedPacket.setOrderId(nOrder.getId());
					 nRedPacket.setOpenid(re_openid);
					 nRedPacket.setTotalAmount(total_amount);

					 nRedPacket.setReason(return_msg);
					 nRedPacket.setResult(jsonStr);

					 if("success".equalsIgnoreCase(return_code)){
						   String result_code=(String) map.get("result_code");
						   String err_code=(String) map.get("err_code");
						   nRedPacket.setErrorCode(err_code);
						   nRedPacket.setResultCode(result_code);
					 }
					 nRedPacket.setCreateDate(new Date());
					 systemService.save(nRedPacket);*/

				} catch (Exception e) {
					//e.toString();
					System.out.println("returninfo"+"="+"红包发放异常");
					e.printStackTrace();

				}
				return null;
	}



	 /**
		 * 查询充值情況
		 *
		 * @return
		 */
		 @RequestMapping(params = "phone_Recharge_orderSta")
		@ResponseBody
		public AjaxJson phone_Recharge_orderSta(String id,HttpServletRequest request){
			String message = null;
			AjaxJson j = new AjaxJson();
			message = "查询成功";
			try{
				if (!StringUtil.isEmpty(id)) {
						NOrderEntity nOrder = systemService.getEntity(NOrderEntity.class,id);
						//String phone=nOrder.getPhone();sss修改手机号为aftersalePhone
					    String phone=nOrder.getAftersalePhone();
						String result=costservice.orderSta(id);
						System.out.println("result="+result);
						JSONObject	json=JSONObject.parseObject(result);
						String result_get=json.getString("result");
						String  errorCode=json.getString("error_code");
						String  reason=json.getString("reason");
						String gameState="10";
						if("0".equals(errorCode)){
						JSONObject	result_get_json=JSONObject.parseObject(result_get);
						 gameState=result_get_json.getString("game_state");
						 nOrder.setGameState(gameState);
						   if("1".equals(gameState)){
							  callSuccess(nOrder);
						   }else if("9".equals(gameState)){
							  systemService.saveOrUpdate(nOrder);
						   }
						}
						/*nOrder.setGameState(gameState);
						systemService.saveOrUpdate(nOrder);*/

						NPhoneRechargeEntity nPhoneRecharg=new NPhoneRechargeEntity();
						nPhoneRecharg.setId(StringUtil.getId());
						nPhoneRecharg.setOrderId(id);
						nPhoneRecharg.setMerchantId(nOrder.getMerchantId());
						nPhoneRecharg.setPhone(phone);
						nPhoneRecharg.setResult(result);
						nPhoneRecharg.setGameState(gameState);
						nPhoneRecharg.setErrorCode(errorCode);
						nPhoneRecharg.setSendType("0");//0充值1查询
						nPhoneRecharg.setCreateDate(new Date());
						nPhoneRecharg.setReason(reason);
						systemService.save(nPhoneRecharg);
						systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
				}else{
					message = "请选择想要查询的记录";
				}
			}catch(Exception e){
				e.printStackTrace();
				message = "查询失败";
				throw new BusinessException(e.getMessage());
			}
			j.setMsg(message);
			return j;
		}


			public void callSuccess(  NOrderEntity nOrder){
				/*订单状态order_status0待付款1待拼单，2待发货3已发货4已签收5待评价6售后处理中
				 * gatheringstatus	商家收款状态	C	32		0未收款 1冻结中2已收款、3退款*/
				String order_status=nOrder.getOrderStatus();
				String goodsId=nOrder.getGoodsId();
				nOrder.setOrderStatus("3");
				nOrder.setSendtime(new Date());
				nOrder.setGatheringstatus("2");
				systemService.saveOrUpdate(nOrder);
				if("2".equals(order_status)){
					final String merchantid=nOrder.getMerchantId();
					final String goods_sum=nOrder.getGoodsSum();
					  Thread t1 = new Thread("t1"){
				            public void run(){
				            	//商家金额
				            	disposeMerchant(merchantid, goods_sum);
				            }
				        };
				        t1.start();
					NGoodsDetaislEntity	nGoodsDetaisl =
							systemService.getEntity(NGoodsDetaislEntity.class, goodsId);
						if(nGoodsDetaisl!=null){
						String old_sellnumbs=nGoodsDetaisl.getSellnumbs();
						if(StringUtil.is_khEmpty(old_sellnumbs)){
							old_sellnumbs="0";
						}
						Integer t_sellnumbs=1;
						BigDecimal b1 = new BigDecimal(old_sellnumbs);
						BigDecimal b2 = new BigDecimal(t_sellnumbs);
						String sellnumbs= b1.add(b2).toString();
						StringBuffer updatesql=new StringBuffer();
					    updatesql.append("update n_goods_detaisl set  ")
					    		.append("sellnumbs='")
							    .append(sellnumbs)
							    .append("'")
					    	    .append(" where  id='")
					            .append(goodsId)
							    .append("'");
				        systemService.updateBySqlString(updatesql.toString());
						}
					final String userid=nOrder.getUserId();
			    	final String first="你有一笔"+nOrder.getGoodsname()+"订单已发货！";
			    	final String keyword1=nOrder.getId();
			    	  Format f = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
			    	final String keyword2=f.format(nOrder.getApplytime());
			    	final String keyword3=nOrder.getPaySum();
			    	final String keyword4=nOrder.getGoodsname()+"-"+nOrder.getStandardname();
			    	final String remark="查看更多订单信息，请点击下方【详情】";
			    	final String goodsname_f=nOrder.getGoodsname();
			    	final String standardname_f=nOrder.getStandardname();
			    	final String t_goods_detaisl_type=nOrder.getGoodsDetaislType();
					if(!StringUtil.is_khEmpty(userid)){

					  Thread t2 = new Thread("t2"){
				            public void run(){
				            	tuisongdictservice.getInsert("订单状态", "你的商品:"+goodsname_f+"  "+standardname_f+";商家已经发货了", userid);
				            //	weixinservice.sendTemplate(userid, first, keyword1, keyword2, keyword3, keyword4, remark);
				            	weixinservice.sendTemplate_four(t_goods_detaisl_type,userid, first, keyword1, keyword2, keyword3, keyword4, remark);
				            }
				        };
				        t2.start();
					}
				}
			}
			//进行商家金额的处理
			public  void disposeMerchant(String merchantid,String goods_sum){
				 /**查询商家信息*/
				NMerchantEntity nMerchant = systemService.getEntity(NMerchantEntity.class, merchantid);

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




	/**
	 * 批量删除订单管理
	 *
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "订单管理删除成功";
		try{
			for(String id:ids.split(",")){
				NOrderEntity nOrder = systemService.getEntity(NOrderEntity.class,
				id
				);
				nOrderService.delete(nOrder);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "订单管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加订单管理
	 *
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(NOrderEntity nOrder, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "订单管理添加成功";
		try{
			String  id=StringUtil.getId();//随机生成
			nOrder.setId(id);
			nOrderService.save(nOrder);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "订单管理添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 更新订单管理
	 *
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(NOrderEntity nOrder, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "订单管理更新成功";
		NOrderEntity t = nOrderService.get(NOrderEntity.class, nOrder.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(nOrder, t);
			nOrderService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "订单管理更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 修改发货信息状态
	 *
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doOrderStatus")
	@ResponseBody
	public AjaxJson doOrderStatus(NOrderEntity nOrder, String type ,HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "订单状态修改成功";
		NOrderEntity t = nOrderService.get(NOrderEntity.class, nOrder.getId());
		String goodsname=t.getGoodsname();
		String standardname=t.getStandardname();
		try {
		//	MyBeanUtils.copyBeanNotNull2Bean(nOrder, t);
			/*订单状态order_status0待付款1待拼单，2待发货3已发货4已签收5待评价6售后处理中*/
			String order_status=t.getOrderStatus();
			if("3".equals(type))
			t.setOrderStatus(type);
			t.setSendtime(new Date());
			nOrderService.saveOrUpdate(t);

			Integer t_sellnumbs=1;
			NOrderEntity c_nOrder = nOrderService.findUniqueByProperty
					(NOrderEntity.class,"couponid", t.getId());
			if(c_nOrder!=null){
				/*订单状态order_status0待付款1待拼单，2待发货3已发货4已签收5待评价6售后处理中*/
				c_nOrder.setOrderStatus("3");
				c_nOrder.setSendtime(new Date());
				nOrderService.saveOrUpdate(c_nOrder);
				 t_sellnumbs=2;
			}
		String goodsId=t.getGoodsId();
		if("2".equals(order_status)){
			NGoodsDetaislEntity	nGoodsDetaisl =
					systemService.getEntity(NGoodsDetaislEntity.class, goodsId);
			if(nGoodsDetaisl!=null){
			String old_sellnumbs=nGoodsDetaisl.getSellnumbs();
			if(StringUtil.is_khEmpty(old_sellnumbs)){
				old_sellnumbs="0";
			}
			 BigDecimal b1 = new BigDecimal(old_sellnumbs);
			 BigDecimal b2 = new BigDecimal(t_sellnumbs);
			String sellnumbs= b1.add(b2).setScale(0,BigDecimal.ROUND_HALF_DOWN).toString();
			StringBuffer updatesql=new StringBuffer();
		    updatesql.append("update n_goods_detaisl set  ")
		    		.append("sellnumbs='")
				    .append(sellnumbs)
				    .append("'")
		    	    .append(" where  id='")
		            .append(goodsId)
				    .append("'");
	        systemService.updateBySqlString(updatesql.toString());
			}
		}
	   	final String userid=t.getUserId();
    	final String first="你有一笔"+goodsname+"订单已发货！";
    	final String keyword1=nOrder.getId();
    	  Format f = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
    	final String keyword2=f.format(t.getApplytime());
    	final String keyword3=t.getPaySum();
    	final String keyword4=goodsname+"-"+standardname;
    	final String remark="查看更多订单信息，请点击下方【详情】";
    	final String goodsname_f=goodsname;
    	final String standardname_f=standardname;
    	final String t_goods_detaisl_type=t.getGoodsDetaislType();
		if(!StringUtil.is_khEmpty(userid)){

		  Thread t1 = new Thread("t1"){
	            public void run(){
	            	tuisongdictservice.getInsert("订单状态", "你的商品:"+goodsname_f+"  "+standardname_f+";商家已经发货了", userid);
	            //	weixinservice.sendTemplate(userid, first, keyword1, keyword2, keyword3, keyword4, remark);
	            	weixinservice.sendTemplate_four(t_goods_detaisl_type,userid, first, keyword1, keyword2, keyword3, keyword4, remark);

	            }
	        };
	        t1.start();
		}

	    systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "订单状态修改失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * aftersale_status	售后状态0申请退款1退款成功，2驳回退款3平台强制退款4平台拒绝退款5退款失败
	 * 平台订单进入售后状态
	 * 平台进行批量退款
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "pt_exit_money")
	@ResponseBody
	public AjaxJson pt_exit_money(String ids, String type ,HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "退款状态修改成功";
		System.out.println("ids=="+ids);
		try {
			if (!StringUtil.isEmpty(ids)) {
				for(String id:ids.split(",")){
				NOrderEntity t = nOrderService.get(NOrderEntity.class, id);
				//0微信、1支付宝、2优惠券，3、优惠券结合使用   4余额结合
				String payorderstatus=t.getPayorderstatus();
				//0微信、1支付宝、2无支付、3公众号支付
				String paymode=t.getPaymode();
				String userid=t.getUserId();
				System.out.println("payorderstatus="+payorderstatus);
				if(!"2".equals(payorderstatus) && !"3".equals(payorderstatus)){
					doRefund1(t,"1");
					if(StringUtils.isNotBlank(userid)) {
						tuisongdictservice.getInsert("售后状态", "商品名称:" + t.getGoodsname() + ",订单号:" + t.getId() + ";平台针对你的订单进行退款操作", userid);
					}
					systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
				}
			}
			}else{
				message = "请选择想要退款的记录";
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "退款状态修改失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * aftersale_status	售后状态0申请退款1退款成功，2驳回退款3平台强制退款4平台拒绝退款5退款失败
	 * 平台订单进入售后状态
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "ptdoAftersale")
	@ResponseBody
	public AjaxJson ptdoAftersale(NOrderEntity nOrder, String type ,HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "售后状态修改成功";
		NOrderEntity t = nOrderService.get(NOrderEntity.class, nOrder.getId());
		try {
		//	MyBeanUtils.copyBeanNotNull2Bean(nOrder, t);
//			t.setAftersaleStatus(type);
//			nOrderService.saveOrUpdate(t);
		//	t.setOrderStatus("6");/**0待付款1待拼单，2待发货3已发货4已签收5已评价6售后处理中*/
		//	t.setOrderType("1");//0正常 1售后处理中
			/**执行退款操作*/
		//	t.setMoneytime(new Date());//同意退款或者不同意的时间
			if("1".equals(type)){
				/**商家同意**/
					t.setExitmoneystatus("1");
					doRefund1(t,"1");//
					String userid=t.getUserId();
					if(!StringUtil.is_khEmpty(userid))
					tuisongdictservice.getInsert("售后状态", "商品名称:"+t.getGoodsname()+",订单号:"+t.getId()+" 你申请的退款商家同意了", userid);
			}else if("2".equals(type)){
				/**商家不同意**/
				t.setExitmoneystatus("1");
				t.setAftersaleStatus(type);
				nOrderService.saveOrUpdate(t);
				String userid=t.getUserId();
				if(!StringUtil.is_khEmpty(userid))
				tuisongdictservice.getInsert("售后状态", "商品名称:"+t.getGoodsname()+",订单号:"+t.getId()+"你申请的退款商家不同意", userid);
			}else if("3".equals(type)){
				/**平台同意**/
					t.setExitmoneystatus("2");
					doRefund1(t,"1");
					String userid=t.getUserId();
					if(!StringUtil.is_khEmpty(userid))
					tuisongdictservice.getInsert("售后状态","商品名称:"+t.getGoodsname()+",订单号:"+t.getId()+"你申请的退款平台同意了", userid);
			}else if("4".equals(type)){
				/**平台不同意**/
				t.setExitmoneystatus("2");
				t.setAftersaleStatus(type);
				nOrderService.saveOrUpdate(t);
				String userid=t.getUserId();
				if(!StringUtil.is_khEmpty(userid))
				tuisongdictservice.getInsert("售后状态", "商品名称:"+t.getGoodsname()+",订单号:"+t.getId()+" 你申请的退款平台不同意", userid);
			}

			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "售后状态修改失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * aftersale_status	售后状态0申请退款1退款成功，2驳回退款3平台强制退款4平台拒绝退款5退款失败
	 *
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAftersale")
	@ResponseBody
	public AjaxJson doAftersale(NOrderEntity nOrder, String type ,HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "售后状态修改成功";
		NOrderEntity t = nOrderService.get(NOrderEntity.class, nOrder.getId());
		try {
		//	MyBeanUtils.copyBeanNotNull2Bean(nOrder, t);
//			t.setAftersaleStatus(type);
//			nOrderService.saveOrUpdate(t);
			/**执行退款操作*/
			t.setMoneytime(new Date());//同意退款或者不同意的时间
			if("1".equals(type)){
				/**商家同意**/
					t.setExitmoneystatus("1");
					doRefund1(t,"0");
					String userid=t.getUserId();
					if(!StringUtil.is_khEmpty(userid))
					tuisongdictservice.getInsert("售后状态", "商品名称:"+t.getGoodsname()+",订单号:"+t.getId()+" 你申请的退款商家同意了", userid);
			}else if("2".equals(type)){
				/**商家不同意**/
				t.setExitmoneystatus("1");
				t.setAftersaleStatus(type);
				nOrderService.saveOrUpdate(t);
				String userid=t.getUserId();
				if(!StringUtil.is_khEmpty(userid))
				tuisongdictservice.getInsert("售后状态", "商品名称:"+t.getGoodsname()+",订单号:"+t.getId()+" 你申请的退款商家不同意", userid);
			}else if("3".equals(type)){
				/**平台同意**/

					t.setExitmoneystatus("2");
					doRefund1(t,"0");
					String userid=t.getUserId();
					if(!StringUtil.is_khEmpty(userid))
					tuisongdictservice.getInsert("售后状态", "商品名称:"+t.getGoodsname()+",订单号:"+t.getId()+" 你申请的退款平台同意了", userid);
			}else if("4".equals(type)){
				/**平台不同意**/
				t.setExitmoneystatus("2");
				t.setAftersaleStatus(type);
				nOrderService.saveOrUpdate(t);
				String userid=t.getUserId();
				if(!StringUtil.is_khEmpty(userid))
				tuisongdictservice.getInsert("售后状态","商品名称:"+t.getGoodsname()+",订单号:"+t.getId()+" 你申请的退款平台不同意", userid);
			}

			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "售后状态修改失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * exitmoneystatus	退款操作状态		0系统自动，1商家操作，2平台操作
	 * aftersale_status	售后状态			0申请退款1退款成功，2驳回退款3平台强制退款4平台拒绝退款5退款失败
	 * @param ids
	 * @return
	 */
	public void doRefund(NOrderEntity nOrder,String exit_type){
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

		  WeixinPayEntity  weixinpayentityapp=nweixinpayservice.getWeApp();
		  String wxappid=null;
		  String wxappsecret=null;
		  String wxpartner=null;
		  String wxpartnerkey=null;
		  String wxnotifyurl=null;
		 if(weixinpayentityapp!=null){
			 wxappid=weixinpayentityapp.getAppid();
			 wxappsecret=weixinpayentityapp.getAppsecret();
			 wxpartner=weixinpayentityapp.getPartner();
			 wxpartnerkey=weixinpayentityapp.getPartnerkey();
			 wxnotifyurl=weixinpayentityapp.getNotifyurl();
		 }
	    String aftersale_status="1";
		String payWay = nOrder.getPaymode();
		String paySum = nOrder.getPaySum();
		String paySum1 = paySum;
		String id = nOrder.getId();
		String merchant_id=nOrder.getMerchantId();
		String nonceStr = getNonceStr();
		String userId=nOrder.getUserId();
		/**用户余额的金额*/
		String userprice=nOrder.getUserprice();
		/**商品总价*/
		String goods_sum=nOrder.getGoodsSum();
		/**快递费*/
		String expressmoney=nOrder.getExpressmoney();
		if(StringUtil.is_khEmpty(expressmoney)){
			expressmoney="0";
		}
		if(StringUtil.is_khEmpty(userprice)){
			userprice="0";
		}
		 BigDecimal b1 = new BigDecimal(goods_sum);
		 BigDecimal b2 = new BigDecimal(userprice);
		 BigDecimal b3 = new BigDecimal(expressmoney);
		 /**退款金额=支付金额+余额-快递费*/
		 String exitmoney=b1.add(b2).subtract(b3).setScale(2,BigDecimal.ROUND_HALF_DOWN).toString();
		 if(StringUtil.is_khEmpty(paySum)){
			 paySum="0";
			 payWay="2";
		 }
	      String   n_paySum=new BigDecimal(100).multiply(new BigDecimal(paySum))
	        		.setScale(0,BigDecimal.ROUND_HALF_DOWN).toString();
	      String   n_exitmoney=new BigDecimal(100).multiply(new BigDecimal(exitmoney))
	        		.setScale(0,BigDecimal.ROUND_HALF_DOWN).toString();
	      System.out.println("payWay=="+payWay+"=退款==n_paySum="+n_paySum+"=n_exitmoney="+n_exitmoney);
		//  paymode	支付方式0微信、1支付宝、2无支付、3公众号支付
		if("1".equals(payWay)){
			AlipayClient alipayClient = new DefaultAlipayClient(ALIPAY_URL, APP_ID,
					APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
			AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
			request.setBizContent("{" + "\"out_trade_no\":\""+id+"\","
					+ "\"refund_amount\":"+exitmoney+""
					+ "}");
			AlipayTradeRefundResponse response;
			try {
				response = alipayClient.execute(request);
				if (response.isSuccess()) {
					System.out.println("调用成功");
					 aftersale_status="1";
				} else {
					System.out.println("调用失败");
					 aftersale_status="5";
				}

			} catch (AlipayApiException e) {
				e.printStackTrace();
			}
		}else if("0".equals(payWay)){
	        /*-----  1.生成预支付订单需要的的package数据-----*/
	        SortedMap<String, String> packageParams = new TreeMap<String, String>();

	        packageParams.put("appid", wxappid);
	        packageParams.put("mch_id", wxpartner);
	        packageParams.put("nonce_str", nonceStr);
	        packageParams.put("out_trade_no", id);
	        packageParams.put("out_refund_no", id);
	        packageParams.put("total_fee",n_paySum);
	        packageParams.put("refund_fee", n_exitmoney);
	        /*----2.根据package生成签名sign---- */
	        RequestHandler reqHandler = new RequestHandler(null, null);
	        reqHandler.init(wxappid, wxappsecret, wxpartnerkey);
	        String sign = reqHandler.createSign(packageParams);

	        /*----3.拼装需要提交到微信的数据xml---- */
	        String xml="<xml>"
	        +"<appid>"+wxappid+"</appid>"
	        + "<mch_id>"+wxpartner+"</mch_id>"
	        + "<nonce_str>"+nonceStr+"</nonce_str>"
	        + "<out_trade_no>"+id+"</out_trade_no>"
	        + "<out_refund_no>"+id+"</out_refund_no>"
	        + "<refund_fee>"+n_exitmoney+"</refund_fee>"
	        + "<total_fee>"+n_paySum+"</total_fee>"
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

	             /*----5.发送数据到微信的退款接口---- */
	             String url="https://api.mch.weixin.qq.com/secapi/pay/refund";
	             HttpPost httpost= HttpClientConnectionManager.getPostMethod(url);
	             httpost.setEntity(new StringEntity(xml, "UTF-8"));
	             HttpResponse weixinResponse = httpClient.execute(httpost);
	             String jsonStr = EntityUtils.toString(weixinResponse.getEntity(), "UTF-8");
	             System.out.println(jsonStr);
	             Map map = GetWxOrderno.doXMLParse(jsonStr);
	             if("success".equalsIgnoreCase((String) map.get("return_code"))){
	                 System.out.println("returncode"+"="+"ok");
	                 System.out.println("returninfo"+"="+"退款成功");
	                 aftersale_status="1";
	             }else{
	            	 System.out.println("returncode"+"="+"error");
	            	 System.out.println("returninfo"+"="+"退款失败");
	            	 System.out.println((String) map.get("return_msg"));
	            	 aftersale_status="5";
	             }
	        } catch (Exception e) {
	        	System.out.println("returncode"+"="+"exception");
	        	System.out.println("returninfo"+"="+"退款失败");
//	        	e.printStackTrace();
	        }
		}else if("3".equals(payWay)){
	        /*-----  1.生成预支付订单需要的的package数据-----*/
	        SortedMap<String, String> packageParams = new TreeMap<String, String>();

	        packageParams.put("appid", gzappid);
	        packageParams.put("mch_id", gzpartner);
	        packageParams.put("nonce_str", nonceStr);
	        packageParams.put("out_trade_no", id);
	        packageParams.put("out_refund_no", id);
	        packageParams.put("total_fee",n_paySum);
	        packageParams.put("refund_fee", n_exitmoney);
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
	        + "<refund_fee>"+n_exitmoney+"</refund_fee>"
	        + "<total_fee>"+n_paySum+"</total_fee>"
	        + "<sign>"+sign+"</sign>"
	        +"</xml>";
	         try {
				 /*----4.读取证书文件,这一段是直接从微信支付平台提供的demo中copy的，所以一般不需要修改---- */
				 KeyStore keyStore  = KeyStore.getInstance("PKCS12");
				 FileInputStream instream = new FileInputStream(new File("C:/apiclient_cert_app.p12"));
				 try {
					 keyStore.load(instream, wxpartner.toCharArray());
				 } finally {
					 instream.close();
				 }

				 // Trust own CA and all self-signed certs
				 SSLContext sslcontext = org.apache.http.conn.ssl.SSLContexts.custom()
						 .loadKeyMaterial(keyStore, wxpartner.toCharArray())
						 .build();
				 // Allow TLSv1 protocol only
				 SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
						 sslcontext,
						 new String[] { "TLSv1" },
						 null,
						 SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
				 CloseableHttpClient httpclient = HttpClients.custom()
						 .setSSLSocketFactory(sslsf)
						 .build();
				 /**********************************************************************/
	             /*----5.发送数据到微信的退款接口---- */
	             String url="https://api.mch.weixin.qq.com/secapi/pay/refund";
	             HttpPost httpost= HttpClientConnectionManager.getPostMethod(url);
	             httpost.setEntity(new StringEntity(xml, "UTF-8"));
	             HttpResponse weixinResponse = httpclient.execute(httpost);
	             String jsonStr = EntityUtils.toString(weixinResponse.getEntity(), "UTF-8");
	             System.out.println(jsonStr);
	             Map map = GetWxOrderno.doXMLParse(jsonStr);
	             if("success".equalsIgnoreCase((String) map.get("return_code"))){
	                 System.out.println("returncode"+"="+"ok");
	                 System.out.println("returninfo"+"="+"退款成功");
	                 aftersale_status="1";
	             }else{
	            	 aftersale_status="5";
	            	 System.out.println("returncode"+"="+"error");
	            	 System.out.println("returninfo"+"="+"退款失败");
	            	 System.out.println((String) map.get("return_msg"));
	             }

	        } catch (Exception e) {
	        	System.out.println("returncode"+"="+"exception"+e.toString());
	        	System.out.println("returninfo"+"="+"退款失败");
//	        	e.printStackTrace();
	        }
		}

		try {
			System.out.println("aftersale_status=="+aftersale_status);
			if("1".equals(aftersale_status)){
				//0是商家1是平台
				if("1".equals(exit_type)){
					/**0系统自动，1商家操作，2平台操作**/
					nOrder.setExitmoneystatus("2");
				}else{
					/**平台同意**/
					nOrder.setExitmoneystatus("1");
				}
				//gatheringstatus 0未收款 1冻结中2已收款、3退款
				/*String gatheringstatus=nOrder.getGatheringstatus();
					if("2".equals(gatheringstatus)){
					subtractNMerchantMoney(merchant_id, goods_sum);
					}*/
				//getExitPrice(userId, userprice);
				weixinservice.sendTemplate_six(userId, "您的订单退款已到账", "¥"+paySum1,
   			 			"退款-"+nOrder.getGoodsname(), "退回支付卡",id, "如有问题，拨打客服");
			}
			nOrder.setGatheringstatus("3");//gatheringstatus0未收款 1冻结中2已收款、3退款
			nOrder.setOrderStatus("6");/**0待付款1待拼单，2待发货3已发货4已签收5已评价6售后处理中*/
			nOrder.setOrderType("1");//0正常 1售后处理中
			/**执行退款操作*/
			nOrder.setMoneytime(new Date());//同意退款或者不同意的时间
			nOrder.setAftersaleStatus(aftersale_status);
			nOrderService.saveOrUpdate(nOrder);
			//goods_detaisl_type	商品类型	c	50		1任务，2普通
			//String goods_detaisl_type=nOrder.getGoodsDetaislType();
			/*if("1".equals(goods_detaisl_type)){
				//把使用优惠券的订单作废
				StringBuffer sql=new StringBuffer();
				sql.append("update n_order set  ")
				   .append(" moneytime=now(),")
				   .append("aftersale_status='")
				   .append(aftersale_status)
				    .append("',order_type='1',order_status='6',gatheringstatus='3' ");
				if("1".equals(exit_type)){
					*//**0系统自动，1商家操作，2平台操作**//*
					sql.append(",exitmoneystatus='2'");
				}else{
					sql.append(",exitmoneystatus='1'");
				}
				sql.append(" where couponid='")
				   .append(nOrder.getId())
				    .append("'");
				System.out.println("优惠券的订单作废===="+sql.toString());
				 systemService.updateBySqlString(sql.toString());
			}*/
			/*if("1".equals(aftersale_status)&& "0".equals(exit_type)){
				subtractNMerchantMoney(merchant_id, goods_sum);
			}*/

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 /**
	  * @Author shishanshan
	  * @Desprition 用户退款
	  * @Date 2018/10/16 12:32
	  * @Param
	  * @Return
	  */
	public void doRefund1(NOrderEntity nOrder,String exit_type){
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
		String aftersale_status="1";
		String payWay = nOrder.getPaymode();
		String paySum1 = nOrder.getPaySum();

		if("1".equals(payWay)){
			AlipayClient alipayClient = new DefaultAlipayClient(ALIPAY_URL, APP_ID,
					APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
			AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
			request.setBizContent("{" + "\"out_trade_no\":\""+nOrder.getId()+"\","
					+ "\"refund_amount\":"+paySum1+""
					+ "}");
			AlipayTradeRefundResponse response;
			try {
				response = alipayClient.execute(request);
				if (response.isSuccess()) {
					System.out.println("支付宝调用成功");
					aftersale_status="1";
				} else {
					System.out.println("支付宝调用失败");
					aftersale_status="5";
				}

			} catch (AlipayApiException e) {
				e.printStackTrace();
			}
		}else{
			/*-----  1.生成预支付订单需要的的package数据-----*/
			SortedMap<String, String> packageParams = new TreeMap<String, String>();
			String paySum=new BigDecimal(100).multiply(new BigDecimal(nOrder.getPaySum())).setScale(0,BigDecimal.ROUND_HALF_DOWN).toString();
			packageParams.put("appid", wxappid);
			packageParams.put("mch_id", wxpartner);
			packageParams.put("nonce_str", nonceStr);
			packageParams.put("out_trade_no", nOrder.getId());
			packageParams.put("out_refund_no", nOrder.getId());
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
					+ "<out_trade_no>"+nOrder.getId()+"</out_trade_no>"
					+ "<out_refund_no>"+nOrder.getId()+"</out_refund_no>"
					+ "<refund_fee>"+paySum+"</refund_fee>"
					+ "<total_fee>"+paySum+"</total_fee>"
					+ "<sign>"+sign+"</sign>"
					+"</xml>";
			try {
				/*----4.读取证书文件,这一段是直接从微信支付平台提供的demo中copy的，所以一般不需要修改---- */
				KeyStore keyStore  = KeyStore.getInstance("PKCS12");
				FileInputStream instream = new FileInputStream(new File("C:/apiclient_cert_app.p12"));
				try {
					keyStore.load(instream, wxpartner.toCharArray());
				} finally {
					instream.close();
				}
				// Trust own CA and all self-signed certs
				SSLContext sslcontext = org.apache.http.conn.ssl.SSLContexts.custom()
						.loadKeyMaterial(keyStore, wxpartner.toCharArray())
						.build();
				// Allow TLSv1 protocol only
				SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
						sslcontext,
						new String[] { "TLSv1" },
						null,
						SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
				CloseableHttpClient httpclient = HttpClients.custom()
						.setSSLSocketFactory(sslsf)
						.build();
				/**********************************************************************/
				/*----5.发送数据到微信的退款接口---- */
				String url="https://api.mch.weixin.qq.com/secapi/pay/refund";
				HttpPost httpost= HttpClientConnectionManager.getPostMethod(url);
				httpost.setEntity(new StringEntity(xml, "UTF-8"));
				HttpResponse weixinResponse = httpclient.execute(httpost);
				String jsonStr = EntityUtils.toString(weixinResponse.getEntity(), "UTF-8");
				System.out.println(jsonStr);
				Map<String, String> stringStringMap = WXPayUtil.xmlToMap(jsonStr);
				System.out.println("时珊珊打印退款状态"+stringStringMap.get("return_code"));
				if("success".equalsIgnoreCase(stringStringMap.get("return_code"))){
					System.out.println("退款成功");
				}else{
					aftersale_status="5";
					System.out.println("退款失败");
				}
				/**成功或者失败，余额都退*/
				//getExitPrice(userId, userprice);
			} catch (Exception e) {
				System.out.println("returncode"+"="+e.toString());
				e.printStackTrace();
			}
		}
		try {
			if("1".equals(aftersale_status)){
				//0是商家1是平台
				if("1".equals(exit_type)){
					/**0系统自动，1商家操作，2平台操作**/
					nOrder.setExitmoneystatus("2");
				}else{
					/**平台同意**/
					nOrder.setExitmoneystatus("1");
				}
				//gatheringstatus 0未收款 1冻结中2已收款、3退款
				/*String gatheringstatus=nOrder.getGatheringstatus();
					if("2".equals(gatheringstatus)){
					subtractNMerchantMoney(merchant_id, goods_sum);
					}*/
				//getExitPrice(userId, userprice);
				weixinservice.sendTemplate_six(nOrder.getUserId(), "您的订单退款已到账", "¥"+paySum1,"退款-"+nOrder.getGoodsname(), "退回支付卡",nOrder.getId(), "如有问题，拨打客服");
				//退款减解冻金额
				String goodsId = nOrder.getGoodsId();
				if(StringUtils.isNotBlank(goodsId)){
					NGoodsDetaislEntity nGoodsDetaislEntity = systemService.getEntity(NGoodsDetaislEntity.class, goodsId);
					if(nGoodsDetaislEntity != null){
						String isOnlyOne = nGoodsDetaislEntity.getIsOnlyOne();
						String returnMoney = nGoodsDetaislEntity.getReturnMoney();
						//限购1件商品  并且返现金不是空 和 0 并且是待发货状态
						if(StringUtils.isNotBlank(isOnlyOne) && StringUtils.isNotBlank(returnMoney) && "0".equals(isOnlyOne) && !"0".equals(returnMoney) && "2".equals(nOrder.getOrderStatus()) && "2".equals(nOrder.getGoodsDetaislType())){
							decreUnfreeMoney(nOrder,nGoodsDetaislEntity);
						}
					}
				}
			}
			nOrder.setGatheringstatus("3");//gatheringstatus0未收款 1冻结中2已收款、3退款
			nOrder.setOrderStatus("6");/**0待付款1待拼单，2待发货3已发货4已签收5已评价6售后处理中*/
			nOrder.setOrderType("1");//0正常 1售后处理中
			/**执行退款操作*/
			nOrder.setMoneytime(new Date());//同意退款或者不同意的时间
			nOrder.setAftersaleStatus(aftersale_status);
			nOrderService.saveOrUpdate(nOrder);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	 /**
	  * @Author shishanshan
	  * @Desprition 退款减少团长相应金额
	  * @Date 2018/11/8 16:10
	  * @Param
	  * @Return
	  **/
	public void  decreUnfreeMoney(NOrderEntity nOrderEntity,NGoodsDetaislEntity nGoodsDetaislEntity){
		String joinordertype = nOrderEntity.getJoinordertype();// joinordertype 0.开团 1.参团
		//团长退款
		try {
			if("0".equals(joinordertype)){
				//判断剩余未退款人员数量
				String islook = nOrderEntity.getIslook();
				if(StringUtils.isNotBlank(islook) && "YT".equals(islook)){
				}else{
					String sql = " select id from n_order where (order_type = '0' or (order_type = '1' and aftersale_status = '0' )) and paystatus ='0' and orderid = '"+nOrderEntity.getOrderid()+"'";
					List<Object> listbySql = systemService.findListbySql(sql);
					if(listbySql != null && listbySql.size()>0){
						int size = listbySql.size();
						NUserEntity nUserEntity = systemService.getEntity(NUserEntity.class, nOrderEntity.getUserId());
						int ptPrice = size * Integer.valueOf(nGoodsDetaislEntity.getReturnMoney());
						//1.冻结金额减少
						String ext5 = nUserEntity.getExt5();//冻结金额
						if(!StringUtils.isNotBlank(ext5)){
							ext5 = "0";
						}
						String decre = decre(ext5, Integer.toString(ptPrice));
						Double aDouble = Double.valueOf(decre);
						if(aDouble <0){
							ext5 = "0";
						}else{
							ext5 = decre;
						}
						nUserEntity.setExt5(ext5);
						weixinservice.sendTemplate_unfreePrice(nUserEntity.getId(),"您好,由于您的退款,您的解冻金额相应减少!",Integer.toString(ptPrice),ext5,nUserEntity.getPrice(),DateUtils.formatTimehsm(new Date()),"点击查看钱包详情，如有疑问直接咨询在线客服。");
					}
				}

			}else{
				//1判断团长是否退款
				String sql = " select user_id,id from n_order where order_type = '0' and paystatus ='0' and orderid = '"+nOrderEntity.getOrderid()+"' and joinordertype = '0' and islook is null";
				List<Object[]> listbySql = systemService.findListbySql(sql);
				if(listbySql!=null && listbySql.size()>0){
					Object[] objects = listbySql.get(0);
					String userId = objects[0].toString();
					NUserEntity nUserEntity = systemService.getEntity(NUserEntity.class, userId);
					String ext5 = nUserEntity.getExt5();
					if(!StringUtils.isNotBlank(ext5)){
						ext5 = "0";
					}
					String returnMoney = nGoodsDetaislEntity.getReturnMoney();
					String decre = decre(ext5, returnMoney);
					Double aDouble = Double.valueOf(decre);
					if(aDouble <0){
						ext5 = "0";
					}else{
						ext5 = decre;
					}
					nUserEntity.setExt5(ext5);
					weixinservice.sendTemplate_unfreePrice(nUserEntity.getId(),"您好,由于您的团员退款,您的解冻金额相应减少!",returnMoney,ext5+"(成团三日后可提现)",nUserEntity.getPrice(),DateUtils.formatTimehsm(new Date()),"点击查看钱包详情，如有疑问直接咨询在线客服。");
				}
			}
		}catch (Exception e){
			LogUtil.error("退款减团长金额",e);
		}
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
	/*减去商家可提现金额*/
	public void   subtractNMerchantMoney(String id,String goods_sum){
		 /**查询商家信息*/
		 NMerchantEntity nMerchant = systemService.getEntity(NMerchantEntity.class, id);
		  BigDecimal b1 = new BigDecimal(goods_sum);
		   String actionMoney=nMerchant.getActionMoney();
		   if(StringUtil.is_khEmpty(actionMoney)){
			   actionMoney="0";
		   }
	       BigDecimal b2 = new BigDecimal(actionMoney);
	       String new_price= b2.subtract(b1).setScale(2,BigDecimal.ROUND_HALF_DOWN).toString();
//	       String new_price= b1.add(b2).toString();
	       nMerchant.setActionMoney(new_price);
	       systemService.saveOrUpdate(nMerchant);
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

	/**
	 * 更新订单管理
	 *
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doDetails")
	@ResponseBody
	public AjaxJson doDetails(NOrderEntity nOrder, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "订单管理更新成功";
		NOrderEntity t = nOrderService.get(NOrderEntity.class, nOrder.getId());
		String goodsname=t.getGoodsname();
		String standardname=t.getStandardname();
		try {
		//	MyBeanUtils.copyBeanNotNull2Bean(nOrder, t);
			/*express_nub	快递单号
			express_name	快递名称
			*/
			String order_status=t.getOrderStatus();
			 t.setExpressNub(nOrder.getExpressNub());
			 t.setExpressName(nOrder.getExpressName());
			/*订单状态order_status0待付款1待拼单，2待发货3已发货4已签收5待评价6售后处理中*/
			 t.setOrderStatus("3");
			 t.setSendtime(new Date());
			nOrderService.saveOrUpdate(t);
			Integer t_sellnumbs=1;
				NOrderEntity c_nOrder = nOrderService.findUniqueByProperty
						(NOrderEntity.class,"couponid", t.getId());
				if(c_nOrder!=null){
					c_nOrder.setExpressNub(nOrder.getExpressNub());
					c_nOrder.setExpressName(nOrder.getExpressName());
					/*订单状态order_status0待付款1待拼单，2待发货3已发货4已签收5待评价6售后处理中*/
					c_nOrder.setOrderStatus("3");
					c_nOrder.setSendtime(new Date());
					nOrderService.saveOrUpdate(c_nOrder);
					 t_sellnumbs=2;
				}
			String goodsId=t.getGoodsId();
			if("2".equals(order_status)){
				NGoodsDetaislEntity	nGoodsDetaisl =
						systemService.getEntity(NGoodsDetaislEntity.class, goodsId);
				if(nGoodsDetaisl!=null){
				String old_sellnumbs=nGoodsDetaisl.getSellnumbs();
				if(StringUtil.is_khEmpty(old_sellnumbs)){
					old_sellnumbs="0";
				}
				 BigDecimal b1 = new BigDecimal(old_sellnumbs);
				 BigDecimal b2 = new BigDecimal(t_sellnumbs);
				String sellnumbs= b1.add(b2).toString();
				StringBuffer updatesql=new StringBuffer();
			    updatesql.append("update n_goods_detaisl set  ")
			    		.append("sellnumbs='")
					    .append(sellnumbs)
					    .append("'")
			    	    .append(" where  id='")
			            .append(goodsId)
					    .append("'");
		        systemService.updateBySqlString(updatesql.toString());
				}
			}
	    	final String userid=t.getUserId();
	    	final String first="你有一笔"+goodsname+"订单已发货！";
	    	final String keyword1=nOrder.getId();
	    	  Format f = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
	    	final String keyword2=f.format(t.getApplytime());
	    	final String keyword3=t.getPaySum();
	    	final String keyword4=goodsname+"-"+standardname;
	    	final String remark="查看更多订单信息，请点击下方【详情】";
	    	final String goodsname_f=goodsname;
	    	final String standardname_f=standardname;
	    	final String t_goods_detaisl_type=t.getGoodsDetaislType();
			if(!StringUtil.is_khEmpty(userid)){

			  Thread t1 = new Thread("t1"){
		            public void run(){
		            	tuisongdictservice.getInsert("订单状态", "你的商品:"+goodsname_f+"  "+standardname_f+";商家已经发货了", userid);
		            //	weixinservice.sendTemplate(userid, first, keyword1, keyword2, keyword3, keyword4, remark);
		            	weixinservice.sendTemplate_four(t_goods_detaisl_type,userid, first, keyword1, keyword2, keyword3, keyword4, remark);
		            }
		        };
		        t1.start();
			}
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

		} catch (Exception e) {
			e.printStackTrace();
			message = "订单管理更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 订单管理新增页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(NOrderEntity nOrder, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(nOrder.getId())) {
			nOrder = nOrderService.getEntity(NOrderEntity.class, nOrder.getId());
			req.setAttribute("nOrderPage", nOrder);
		}
		return new ModelAndView("com/jeecg/n_order/nOrder-add");
	}
	/**
	 * 订单管理编辑页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "goUpdaterenwu")
	public ModelAndView goUpdaterenwu(NOrderEntity nOrder, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(nOrder.getId())) {
			nOrder = nOrderService.getEntity(NOrderEntity.class, nOrder.getId());
			req.setAttribute("nOrderPage", nOrder);
		/*	String addressid=nOrder.getAddressid();
			if(!StringUtil.is_khEmpty(addressid)){
				NUserAddressEntity t = systemService.get(NUserAddressEntity.class, addressid);
				req.setAttribute("nUserAddersspage", t);
			}*/
			NOrderEntity c_nOrder = nOrderService.findUniqueByProperty(NOrderEntity.class,"couponid", nOrder.getId());
			req.setAttribute("c_nOrderPage", c_nOrder);
		}

		return new ModelAndView("com/jeecg/n_order/nOrder-updaterenwu");
	}
	/**
	 * 订单管理编辑页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "goUpdatepintuan")
	public ModelAndView goUpdatepintuan(NOrderEntity nOrder, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(nOrder.getId())) {
			nOrder = nOrderService.getEntity(NOrderEntity.class, nOrder.getId());
			req.setAttribute("nOrderPage", nOrder);
		/*	String addressid=nOrder.getAddressid();
			if(!StringUtil.is_khEmpty(addressid)){
				NUserAddressEntity t = systemService.get(NUserAddressEntity.class, addressid);
				req.setAttribute("nUserAddersspage", t);
			}*/
			String goodsDetaislType = nOrder.getGoodsDetaislType();
			if(StringUtils.isNotBlank(goodsDetaislType) && "1".equals(goodsDetaislType)){
				String sql = "select id from `n_goods_join_record`   where  order_id = '"+nOrder.getId()+"' and  record_type = '2'";
				List<Object> listbySql = systemService.findListbySql(sql);
				if(listbySql!=null) {
					req.setAttribute("joinNum", listbySql.size());
				}else{
					req.setAttribute("joinNum", "0");
				}
			}
			List<NOrderEntity> pt_nOrderPage = nOrderService.findByProperty(NOrderEntity.class,"orderid", nOrder.getOrderid());
			req.setAttribute("pt_nOrderPage", pt_nOrderPage);
		}

		return new ModelAndView("com/jeecg/n_order/nOrder-updatepintuan");
	}
	/**
	 * 订单管理编辑页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "goUpdateputong")
	public ModelAndView goUpdateputong(NOrderEntity nOrder, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(nOrder.getId())) {
			nOrder = nOrderService.getEntity(NOrderEntity.class, nOrder.getId());
			req.setAttribute("nOrderPage", nOrder);
			/*String addressid=nOrder.getAddressid();
			if(!StringUtil.is_khEmpty(addressid)){
				NUserAddressEntity t = systemService.get(NUserAddressEntity.class, addressid);
				req.setAttribute("nUserAddersspage", t);
			}*/
		}

		return new ModelAndView("com/jeecg/n_order/nOrder-updateputong");
	}
	/**
	 * 订单管理编辑页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(NOrderEntity nOrder, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(nOrder.getId())) {
			nOrder = nOrderService.getEntity(NOrderEntity.class, nOrder.getId());
			req.setAttribute("nOrderPage", nOrder);
			String addressid=nOrder.getAddressid();
			if(!StringUtil.is_khEmpty(addressid)){
				NUserAddressEntity t = systemService.get(NUserAddressEntity.class, addressid);
				req.setAttribute("nUserAddersspage", t);
			}
		}

		return new ModelAndView("com/jeecg/n_order/nOrder-update");
	}
	/**
	 * 订单管理详情
	 *
	 * @return
	 */
	@RequestMapping(params = "goDetails")
	public ModelAndView goDetails(NOrderEntity nOrder, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(nOrder.getId())) {
			nOrder = nOrderService.getEntity(NOrderEntity.class, nOrder.getId());
			req.setAttribute("nOrderPage", nOrder);
			/*String addressid=nOrder.getAddressid();
			if(!StringUtil.is_khEmpty(addressid)){
			  NUserAddressEntity t = systemService.get(NUserAddressEntity.class, addressid);
				req.setAttribute("nUserAddersspage", t);
			}*/
		}
		return new ModelAndView("com/jeecg/n_order/nOrder-details");
	}
	/**
	 * 订单管理详情
	 *
	 * @return
	 */
	@RequestMapping(params = "goptDetails")
	public ModelAndView goptDetails(NOrderEntity nOrder, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(nOrder.getId())) {
			nOrder = nOrderService.getEntity(NOrderEntity.class, nOrder.getId());
			req.setAttribute("nOrderPage", nOrder);
			String addressid=nOrder.getAddressid();
			if(!StringUtil.is_khEmpty(addressid)){
				NUserAddressEntity t = systemService.get(NUserAddressEntity.class, addressid);
				req.setAttribute("nUserAddersspage", t);
			}
		}
		return new ModelAndView("com/jeecg/n_order/ptnOrder-details");
	}
	/**
	 * 订单管理详情  首页的订单homenOrder-update
	 *
	 * @return
	 */
	@RequestMapping(params = "gohomeDetails")
	public ModelAndView gohomeDetails(NOrderEntity nOrder, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(nOrder.getId())) {
			nOrder = nOrderService.getEntity(NOrderEntity.class, nOrder.getId());
			req.setAttribute("nOrderPage", nOrder);
			String addressid=nOrder.getAddressid();
			if(!StringUtil.is_khEmpty(addressid)){
				NUserAddressEntity t = systemService.get(NUserAddressEntity.class, addressid);
				req.setAttribute("nUserAddersspage", t);
			}
		}
		return new ModelAndView("com/jeecg/n_order/home/homenOrder-update");
	}
	/**
	 * 订单管理编辑页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "replygoUpdate")
	public ModelAndView replygoUpdate(NOrderEntity nOrder, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(nOrder.getId())) {
			nOrder = nOrderService.getEntity(NOrderEntity.class, nOrder.getId());
			req.setAttribute("nOrderPage", nOrder);
		}
		return new ModelAndView("com/jeecg/n_order/replynOrder-update");
	}
	/**
	 * 订单管理编辑页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "ptreplygoUpdate")
	public ModelAndView ptreplygoUpdate(NOrderEntity nOrder, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(nOrder.getId())) {
			nOrder = nOrderService.getEntity(NOrderEntity.class, nOrder.getId());
			req.setAttribute("nOrderPage", nOrder);
		}
		return new ModelAndView("com/jeecg/n_order/ptreplynOrder-update");
	}
	/**
	 * 导入功能跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","nOrderController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

	/**
	 * 导出excel
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(NOrderEntity nOrder,String starttime,String endtime,String selectType,String all,HttpServletRequest request, DataGrid dataGrid,ModelMap modelMap) {
		try{
		String goodsname = nOrder.getGoodsname();
		String id = nOrder.getId();
		if (!StringUtil.isEmpty(goodsname)) {
			goodsname=new String(goodsname.getBytes("iso8859-1"),"UTF-8");
			System.out.println("goodsname编码后==="+goodsname);
			nOrder.setGoodsname("*"+goodsname+"*");
		}
		if(StringUtils.isNotBlank(id)){
			nOrder.setId("*"+id+"*");
		}
		CriteriaQuery cq = new CriteriaQuery(NOrderEntity.class, dataGrid);
		HqlGenerateUtil.installHql(cq, nOrder, request.getParameterMap());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(StringUtils.isNotBlank(starttime)) {
				cq.ge("createDate", sdf.parse(starttime));
			}
			if(StringUtils.isNotBlank(endtime)) {
				cq.le("createDate", sdf.parse(endtime));
			}

			//主要用于给平台展示全部的订单
			if(StringUtils.isNotBlank(all)){
				//goods_detaisl_type	商品类型	c	50		1任务，2普通
				String goodsDetaislType=nOrder.getGoodsDetaislType();
				if("1".equals(goodsDetaislType)){
					 // 0微信、1支付宝、2优惠券，3、优惠券结合使用   4余额结合
					String [] payorderstatus= {"0","1","4"};
					cq.in("payorderstatus",payorderstatus);
				}
			}
			//售后订单导出
			HashMap<String, Object> stringObjectHashMap = new HashMap<>();
			stringObjectHashMap.put("userId","asc");
			cq.setOrder(stringObjectHashMap);
			cq.add();
			List<NOrderEntity> nOrders = this.nOrderService.getListByCriteriaQuery(cq,false);
			//获取订单数量大于2的用户
			StringBuffer sb = new StringBuffer();
			HashMap<String, String> hashMap = new HashMap<>();
			List<NOrderEntity> nOrders1 = new ArrayList<>();
			if(StringUtils.isNotBlank(selectType) && "two".equals(selectType)){
				String sql="";
				if(StringUtils.isNotBlank(endtime) && StringUtils.isNotBlank(starttime)){
					 sql = "select t1.user_id,count(*) as count  from n_order t1 where t1.order_status='2' and (create_date between '"+starttime+"' and '"+endtime+"')  and t1.aftersale_status is null and  t1.joinorderstatus='1' and t1.goods_detaisl_type = '2'   GROUP BY t1.user_id  having count>1";
				}else{
					 sql = "select t1.user_id,count(*) as count  from n_order t1 where t1.order_status='2' and t1.aftersale_status is null and  t1.joinorderstatus='1' and t1.goods_detaisl_type = '2'  GROUP BY t1.user_id  having count>1";
				}
				List<Object[]> listbySql = systemService.findListbySql(sql);
				if(listbySql !=null &&listbySql.size()>0){
					for(int i=0;i<listbySql.size();i++){
						String s = listbySql.get(i)[0].toString();
						sb.append(s);
					}
				}
				//获取团剩余人数
				String ptPerson="";
				if(StringUtils.isNotBlank(endtime) && StringUtils.isNotBlank(starttime)){
					 ptPerson = "select t1.orderid,count(*) as count  from n_order t1 where t1.order_status='2' and (create_date between '"+starttime+"' and '"+endtime+"') and t1.aftersale_status is null and  t1.joinorderstatus='1' and t1.goods_detaisl_type = '2'  GROUP BY t1.orderid";
				}else{
					 ptPerson = "select t1.orderid,count(*) as count  from n_order t1 where t1.order_status='2' and t1.aftersale_status is null and  t1.joinorderstatus='1' and t1.goods_detaisl_type = '2'  GROUP BY t1.orderid";
				}
				List<Object[]> listbySql1 = systemService.findListbySql(ptPerson);
				if(listbySql1 != null && listbySql.size()>0){
					for(int i=0;i<listbySql1.size();i++){
						hashMap.put(listbySql1.get(i)[0].toString(),listbySql1.get(i)[1].toString());
					}
				}
				for(int i=0;i<nOrders.size();i++){
					NOrderEntity nOrderEntity = nOrders.get(i);
					if(sb.indexOf(nOrderEntity.getUserId())>0){
						String value = hashMap.get(nOrderEntity.getOrderid());
						if(StringUtils.isNotBlank(value)){
							nOrderEntity.setOpenid(value);
						}
						String joinordertype = nOrderEntity.getJoinordertype();
						if("0".equals(joinordertype)){
							nOrderEntity.setJoinordertype("开团");
						}else if("1".equals(joinordertype)){
							nOrderEntity.setJoinordertype("参团");
						}
						nOrders1.add(nOrderEntity);
					}
				}
			}

			/*for(int i=0;i<nOrders.size();i++){
			NOrderEntity norder= nOrders.get(i);
			String userid=norder.getUserId();
			if(!StringUtil.is_khEmpty(userid)){
				NUserEntity nUser = systemService.getEntity(NUserEntity.class, userid);
				if(nUser!=null){
					String tOpenid=nUser.getOpenid();
					if(!StringUtil.is_khEmpty(tOpenid))
					norder.setOpenid(nUser.getOpenid());
				}
			//	req.setAttribute("nUserAddersspage", t);
				*//*norder.setPhone(t.getPhone());
				norder.setAcceptname(t.getAcceptname());
				norder.setArea(t.getArea());
				norder.setAddress(t.getAddress());*//*
			}
		}*/
		modelMap.put(NormalExcelConstants.FILE_NAME,"订单管理");
		modelMap.put(NormalExcelConstants.CLASS,NOrderEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("订单管理列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		if(StringUtils.isNotBlank(selectType) && "two".equals(selectType)){
			modelMap.put(NormalExcelConstants.DATA_LIST,nOrders1);
		}else{
			modelMap.put(NormalExcelConstants.DATA_LIST,nOrders);
		}
		}catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	 /**
	  * @Author shishanshan
	  * @Desprition 导出申请退款或者已退款订单
	  * @Date 2018/11/2 15:22
	  * @Param
	  * @Return
	  **/
	@RequestMapping(params = "exportXlsForAltersale")
	public String exportXlsForAltersale(String starttime,String endtime,String selectType,String all,String aftersale,HttpServletRequest request, DataGrid dataGrid,ModelMap modelMap,HttpServletResponse response) {
		try{
			String sql = "from NOrderEntity where orderType = '1'";
			if(StringUtils.isNotBlank(starttime) && StringUtils.isNotBlank(endtime)){
				sql += " and aftersaletime between '"+starttime+"' and  '"+endtime+"'";
			}
			Object[] arr = new Object[0];
			List<NOrderEntity> nOrders = systemService.findHql(sql, arr);
			modelMap.put(NormalExcelConstants.FILE_NAME,"订单管理");
			modelMap.put(NormalExcelConstants.CLASS,NOrderEntity.class);
			modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("订单管理列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"));
			modelMap.put(NormalExcelConstants.DATA_LIST,nOrders);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 快递模板
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsexpress")
	public String exportXlsexpress(HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		List<ExpressEntity> expresss = new ArrayList<ExpressEntity>();
		TSTypegroup mode = systemService.findUniqueByProperty(TSTypegroup.class, "typegroupcode", "expressName");
		List<TSType> TSTypes=mode.getTSTypes();
		for(int i=0;i<TSTypes.size();i++){
			TSType tstype=TSTypes.get(i);
			if(tstype!=null){
			ExpressEntity expressentity=new ExpressEntity();
			expressentity.setExpressNub(tstype.getTypecode());
			expressentity.setExpressName(tstype.getTypename());
			expresss.add(expressentity);
			}
		}

    	modelMap.put(NormalExcelConstants.FILE_NAME,"快递信息");
    	modelMap.put(NormalExcelConstants.CLASS,ExpressEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("快递信息列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
    	"导出信息"));
    	modelMap.put(NormalExcelConstants.DATA_LIST,expresss);
    	return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(NOrderEntity nOrder,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"订单管理");
    	modelMap.put(NormalExcelConstants.CLASS,NOrderEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("订单管理列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
    	"导出信息"));
    	modelMap.put(NormalExcelConstants.DATA_LIST,new ArrayList());
    	return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<NOrderEntity> listNOrderEntitys = ExcelImportUtil.importExcel(file.getInputStream(),NOrderEntity.class,params);
				for (NOrderEntity nOrder : listNOrderEntitys) {
					nOrderService.save(nOrder);
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}finally{
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return j;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<NOrderEntity> list() {
		List<NOrderEntity> listNOrders=nOrderService.getList(NOrderEntity.class);
		return listNOrders;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		NOrderEntity task = nOrderService.get(NOrderEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody NOrderEntity nOrder, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<NOrderEntity>> failures = validator.validate(nOrder);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			nOrderService.save(nOrder);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = nOrder.getId();
		URI uri = uriBuilder.path("/rest/nOrderController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody NOrderEntity nOrder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<NOrderEntity>> failures = validator.validate(nOrder);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			nOrderService.saveOrUpdate(nOrder);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		nOrderService.deleteEntityById(NOrderEntity.class, id);
	}



	/**支付宝支付 paymode	支付方式0微信、1支付宝、2无支付、3公众号支付*/
    public void  getAlipay(String type,String id,String orderid,String paySum,String userId,String userprice){
		AlipayClient alipayClient = new DefaultAlipayClient(ALIPAY_URL, APP_ID,
				APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
		AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
		request.setBizContent("{" + "\"out_trade_no\":\""+id+"\","
				+ "\"refund_amount\":"+paySum+""
				+ "}");
		AlipayTradeRefundResponse response;
		try {
			response = alipayClient.execute(request);
			if (response.isSuccess()) {
				System.out.println("调用成功");
				 /**订单作废
                 * joinorderstatus	拼单状态 0待完成，1已完成、2订单取消
                 * 退款的状态 aftersale_status	售后状态	0申请退款1退款成功，2驳回退款3平台强制退款4平台拒绝退款5退款失败*/
				StringBuffer updatesql=new StringBuffer();
				if("1".equals(type)){
			    updatesql.append("update n_order set joinorderstatus='2',aftersale_status='1',exitmoneystatus='0' where orderid='")
			             .append(orderid)
			             .append("'");
				}else if("2".equals(type)){
					 updatesql.append("update n_order set joinorderstatus='2',aftersale_status='1',exitmoneystatus='0' where id='")
		             .append(id)
		             .append("'");
				}
		        systemService.updateBySqlString(updatesql.toString());

			} else {
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
				System.out.println("调用失败");
			}
			  getExitPrice(userId, userprice);
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
	}
	/**微信支付 paymode	支付方式0微信、1支付宝、2无支付、3公众号支付*/
	public void  getWeixinpay(String type,String id,String orderid,String paySum,String userId,String userprice){
		String nonceStr = getNonceStr();
		  WeixinPayEntity  weixinpayentityapp=nweixinpayservice.getWeApp();
		  String wxappid=null;
		  String wxappsecret=null;
		  String wxpartner=null;
		  String wxpartnerkey=null;
		  String wxnotifyurl=null;
		 if(weixinpayentityapp!=null){
			 wxappid=weixinpayentityapp.getAppid();
			 wxappsecret=weixinpayentityapp.getAppsecret();
			 wxpartner=weixinpayentityapp.getPartner();
			 wxpartnerkey=weixinpayentityapp.getPartnerkey();
			 wxnotifyurl=weixinpayentityapp.getNotifyurl();
		 }
        /*-----  1.生成预支付订单需要的的package数据-----*/
        SortedMap<String, String> packageParams = new TreeMap<String, String>();
        paySum=new BigDecimal(100).multiply(new BigDecimal(paySum))
        		.setScale(0,BigDecimal.ROUND_HALF_DOWN).toString();
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

             /*----5.发送数据到微信的退款接口---- */
             String url="https://api.mch.weixin.qq.com/secapi/pay/refund";
             HttpPost httpost= HttpClientConnectionManager.getPostMethod(url);
             httpost.setEntity(new StringEntity(xml, "UTF-8"));
             HttpResponse weixinResponse = httpClient.execute(httpost);
             String jsonStr = EntityUtils.toString(weixinResponse.getEntity(), "UTF-8");
             System.out.println(jsonStr);
             Map map = GetWxOrderno.doXMLParse(jsonStr);
             if("success".equalsIgnoreCase((String) map.get("return_code"))){
                 System.out.println("returncode"+"="+"ok"+"returninfo"+"="+"退款成功");
                 /**订单作废
                  * joinorderstatus	拼单状态 0待完成，1已完成、2订单取消
                  * 退款的状态 aftersale_status	售后状态	0申请退款1退款成功，2驳回退款3平台强制退款4平台拒绝退款5退款失败*/
                 StringBuffer updatesql=new StringBuffer();
					if("1".equals(type)){
					    updatesql.append("update n_order set joinorderstatus='2',aftersale_status='1',exitmoneystatus='0' where orderid='")
					             .append(orderid)
					             .append("'");
						}else if("2".equals(type)){
							 updatesql.append("update n_order set joinorderstatus='2',aftersale_status='1',exitmoneystatus='0' where id='")
				             .append(id)
				             .append("'");
						}
			        systemService.updateBySqlString(updatesql.toString());

             }else{
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
            	 System.out.println("returncode"+"="+"error="+"returninfo"+"="+"退款失败");
            	 System.out.println((String) map.get("return_msg"));
             }
             /**成功或者失败，余额都退*/
             getExitPrice(userId, userprice);
        } catch (Exception e) {
        	System.out.println("returncode"+"="+"exception"+e.toString());
        	System.out.println("returninfo"+"="+"退款失败");
//        	e.printStackTrace();
        }
	}
	/**公众号支付 paymode	支付方式0微信、1支付宝、2无支付、3公众号支付*/
    public void  getWeixinHpay(String type,String id,String orderid,String paySum,String userId,String userprice){
    	String nonceStr = getNonceStr();
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
             Map map = GetWxOrderno.doXMLParse(jsonStr);
             if("success".equalsIgnoreCase((String) map.get("return_code"))){
                 System.out.println("returncode"+"="+"ok");
                 System.out.println("returninfo"+"="+"退款成功");
                 /**订单作废
                  * joinorderstatus	拼单状态 0待完成，1已完成、2订单取消
                  * 退款的状态 aftersale_status	售后状态	0申请退款1退款成功，2驳回退款3平台强制退款4平台拒绝退款5退款失败*/
                     StringBuffer updatesql=new StringBuffer();
       					if("1".equals(type)){
       					    updatesql.append("update n_order set joinorderstatus='2',aftersale_status='1',exitmoneystatus='0' where orderid='")
       					             .append(orderid)
       					             .append("'");
       						}else if("2".equals(type)){
       							 updatesql.append("update n_order set joinorderstatus='2',aftersale_status='1',exitmoneystatus='0' where id='")
       				             .append(id)
       				             .append("'");
       						}
       			        systemService.updateBySqlString(updatesql.toString());

                 }else{
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
            	 System.out.println("returncode"+"="+"error");
            	 System.out.println("returninfo"+"="+"退款失败");
            	 System.out.println((String) map.get("return_msg"));
             }
             /**成功或者失败，余额都退*/
             getExitPrice(userId, userprice);
        } catch (Exception e) {
        	System.out.println("returncode"+"="+"exception"+e.toString());
        	System.out.println("returninfo"+"="+"退款失败");
//        	e.printStackTrace();
        }
	}


    /**
     * Map转Xml
     * @param arr
     * @return
     */
    public   String MapToXml(Map<String, String> arr) {
        String xml = "<xml>";
        Iterator<Entry<String, String>> iter = arr.entrySet().iterator();
        while (iter.hasNext()) {
            Entry<String, String> entry = iter.next();
            String key = entry.getKey();
            String val = entry.getValue();
            if (IsNumeric(val)) {
                xml += "<" + key + ">" + val + "</" + key + ">";
            } else
                xml += "<" + key + "><![CDATA[" + val + "]]></" + key + ">";
        }
        xml += "</xml>";
        return xml;
    }
    private   boolean IsNumeric(String str) {
        if (str.matches("\\d *")) {
            return true;
        } else {
            return false;
        }
    }
     /**
      * @Author shishanshan
      * @Desprition 更改用户收货地址
      * @Date 2018/10/31 16:19
      * @Param
      * @Return
      **/
    @RequestMapping(params = "changeUserAddress",method = RequestMethod.POST)
    @ResponseBody
    public String changeUserAddress(String id,String acceptname,String phone,String area,String address){
        String status = "N";
        try {
            if(StringUtils.isNotBlank(id) && StringUtils.isNotBlank(acceptname) && StringUtils.isNotBlank(phone) && StringUtils.isNotBlank(area) && StringUtils.isNotBlank(address)){
                NOrderEntity nOrderEntity = systemService.getEntity(NOrderEntity.class, id);
                if(nOrderEntity!=null){
                    nOrderEntity.setAcceptname(acceptname);
                    nOrderEntity.setPhone(phone);
                    nOrderEntity.setArea(area);
                    nOrderEntity.setAddress(address);
                    systemService.saveOrUpdate(nOrderEntity);
                    status = "Y";
                }
            }
        }catch (Exception e){
            logger.error(e);
        }
        return status;
    }
}
