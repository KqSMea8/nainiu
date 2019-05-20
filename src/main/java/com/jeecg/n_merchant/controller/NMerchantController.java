package com.jeecg.n_merchant.controller;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.jeecg.n_merchant.entity.NMerchantEntity;
import com.jeecg.n_merchant.service.NMerchantServiceI;
import com.jeecg.n_merchant_auidt.entity.NMerchantAuidtEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
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
import org.jeecgframework.core.util.PasswordUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSRole;
import org.jeecgframework.web.system.pojo.base.TSRoleUser;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.pojo.base.TSUserOrg;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import java.io.OutputStream;

import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.vo.TemplateExcelConstants;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.core.util.ResourceUtil;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Map;
import java.util.HashMap;

import org.jeecgframework.core.util.ExceptionUtil;
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

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import java.net.URI;

import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

import test.CodeUtil;

/**   
 * @Title: Controller  
 * @Description: 商家信息表
 * @author onlineGenerator
 * @date 2017-11-21 09:51:43
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/nMerchantController")
public class NMerchantController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(NMerchantController.class);

	@Autowired
	private NMerchantServiceI nMerchantService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 商家信息表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/n_merchant/nMerchantList");
	}
	/**
	 * 商家流水列表 页面跳转
	 * actionMoney
	 * @return
	 */
	@RequestMapping(params = "list_money")
	public ModelAndView list_money(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/n_merchant/nMerchantList_money");
	}
	/**
	 * 商家流水列表 页面跳转
	 * actionMoney
	 * @return
	 */
	@RequestMapping(params = "list_tel")
	public ModelAndView list_tel(HttpServletRequest request) {
		TSUser tsuser=ResourceUtil.getSessionUserName();
		String id=tsuser.getPkid();
		request.setAttribute("merchantid", id);
		return new ModelAndView("com/jeecg/n_merchant/nMerchantList_tel");
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
	public void datagrid(NMerchantEntity nMerchant,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String company = nMerchant.getCompany();
		if (!StringUtil.isEmpty(company)) {
			nMerchant.setCompany("*"+company+"*");
		}
		nMerchant.setDelFlag("0");
		CriteriaQuery cq = new CriteriaQuery(NMerchantEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, nMerchant, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.nMerchantService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除商家信息表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(NMerchantEntity nMerchant, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		nMerchant = systemService.getEntity(NMerchantEntity.class, nMerchant.getId());
		TSUser tsuser = systemService.getEntity(TSUser.class, nMerchant.getId());
		message = "商家信息表删除成功";
		try{
			//nMerchantService.delete(nMerchant);
			nMerchant.setDelFlag("1");//0正常1删除
			tsuser.setDeleteFlag(new Short("1"));//1删除0正常
			tsuser.setStatus(new Short("0"));//1正常0锁定
			nMerchantService.saveOrUpdate(nMerchant);
			systemService.saveOrUpdate(tsuser);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "商家信息表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除商家信息表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "商家信息表删除成功";
		try{
			for(String id:ids.split(",")){
				NMerchantEntity nMerchant = systemService.getEntity(NMerchantEntity.class, 
				id
				);
				nMerchantService.delete(nMerchant);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "商家信息表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加商家信息表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(NMerchantEntity nMerchant, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "商家信息表添加成功";
		try{
			nMerchant.setDelFlag("0");
			nMerchant.setIspay("1");//0是1否
			nMerchant.setAccountType("0");//0主账户1子账户
			nMerchant.setRole("0");//0管理员1客服2运营
			nMerchant.setAccountStatus("0");//0正常1禁用2销户
			nMerchant.setAuditType("0");//0、审核中1审核通过2审核不通过
			String username=nMerchant.getPhone();
			String realname=nMerchant.getCompany();
			String id=UUID.randomUUID().toString();
			NMerchantEntity t = systemService.findUniqueByProperty(NMerchantEntity.class,"phone" ,username);
			//List<TSUser> users = systemService.findByProperty(TSUser.class,"userName",username);
			if(t !=null){
				message = "商家已经存在";
			}else{
				
				boolean flg=Insertuser( username, realname,id);
				if(flg){
				nMerchant.setId(id);
				nMerchantService.save(nMerchant);
				systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
				}else{
					message = "该商家不允许注册";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "商家信息表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新商家信息表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(NMerchantEntity nMerchant, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "商家信息表更新成功";
		NMerchantEntity t = nMerchantService.get(NMerchantEntity.class, nMerchant.getId());
		try {
			nMerchant.setDelFlag("0");
			//nMerchant.setIspay("1");//0是1否
			//nMerchant.setAccountType("0");//0主账户1子账户
		//	nMerchant.setRole("0");//0管理员1客服2运营
			//nMerchant.setAccountStatus("0");//0正常1禁用2销户
			nMerchant.setAuditType("0");//0、审核中1审核通过2审核不通过
			MyBeanUtils.copyBeanNotNull2Bean(nMerchant, t);
			nMerchantService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			String id=t.getId();
			String username=t.getPhone();
			String realname=t.getCompany();
			//List<TSUser> users = systemService.findByProperty(TSUser.class,"userName",username);
			TSUser user=systemService.findUniqueByProperty(TSUser.class,"id",id);
			String oldusername=user.getUserName();
			if(username.equals(oldusername)){
				//TSUser user = users.get(0);
				user.setUserName(username);
				user.setRealName(realname);
				user.setStatus(new Short("0"));//1正常0锁定
				user.setDeleteFlag(new Short("1"));//1删除0正常
				systemService.saveOrUpdate(user);
			}else{
				List<TSUser> users = systemService.findByProperty(TSUser.class,"userName",username);
				if(users.size()!=0){
					message = "商家已经存在";
				}else{
					user.setUserName(username);
					user.setRealName(realname);
					systemService.saveOrUpdate(user);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "商家信息表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 更新商家信息表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate_tel")
	@ResponseBody
	public AjaxJson doUpdate_tel(NMerchantEntity nMerchant, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "商家信息表更新成功";
		NMerchantEntity t = nMerchantService.get(NMerchantEntity.class, nMerchant.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(nMerchant, t);
			nMerchantService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "商家信息表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 更新商家信息表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAudit")
	@ResponseBody
	public AjaxJson doAudit(NMerchantEntity nMerchant,String auditContent,  HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "商家信息表更新成功";
		NMerchantEntity t = nMerchantService.get(NMerchantEntity.class, nMerchant.getId());
		try {
			//MyBeanUtils.copyBeanNotNull2Bean(nMerchant, t);
			String accountstatus=nMerchant.getAccountStatus();
			String audittype=nMerchant.getAuditType();
			String phone=t .getPhone();
			String company=t .getCompany();
			t.setAccountStatus(accountstatus);
			t.setAuditType(audittype);
			t.setAuditContent(auditContent);
			nMerchantService.saveOrUpdate(t);
			NMerchantAuidtEntity nMerchantAuidt=new NMerchantAuidtEntity();
			nMerchantAuidt.setMerchantId(nMerchant.getId());
			nMerchantAuidt.setAuditContent(auditContent);
			systemService.save(nMerchantAuidt);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			String username=t.getPhone();
			String realname=t.getCompany();
			List<TSUser> users = systemService.findByProperty(TSUser.class,"userName",username);
			if("0".equals(accountstatus) && "1".equals(audittype) ){
				TSUser user = users.get(0);
				user.setUserName(username);
				user.setRealName(realname);
				user.setDeleteFlag(new Short("0"));//1删除0正常
				user.setStatus(new Short("1"));//1正常0锁定
				user.setShoptype(null);
				systemService.saveOrUpdate(user);
				SendSmsResponse sendresponse =CodeUtil.sendSuccess(phone, company,"123456");
				if (sendresponse.getCode() != null && sendresponse.getCode().equals("OK")) {
					message = "商家信息表更新成功,短信通知发送成功";
				}else{
					message = "商家信息表更新成功,短信通知发送失败";
				}
			}else{
				TSUser user = users.get(0);
				user.setUserName(username);
				user.setRealName(realname);
				user.setDeleteFlag(new Short("1"));//1删除0正常
				user.setStatus(new Short("0"));//1正常0锁定
				systemService.saveOrUpdate(user);
				SendSmsResponse sendresponse =CodeUtil.sendFail(phone, company);
				if (sendresponse.getCode() != null && sendresponse.getCode().equals("OK")) {
					message = "商家信息表更新成功,短信通知发送成功";
				}else{
					message = "商家信息表更新成功,短信通知发送失败";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "商家信息表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 删除商家信息表
	 * 
	 * @return
	 */
	@RequestMapping(params = "sendcode")
	@ResponseBody
	public AjaxJson sendcode(NMerchantEntity nMerchant, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		nMerchant = systemService.getEntity(NMerchantEntity.class, nMerchant.getId());
		message = "短信通知发送成功";
		try{
			String accountstatus=nMerchant.getAccountStatus();
			String audittype=nMerchant.getAuditType();
			String phone=nMerchant.getPhone();
			String company=nMerchant.getCompany();
			if("0".equals(accountstatus) && "1".equals(audittype) ){
				SendSmsResponse sendresponse =CodeUtil.sendSuccess(phone, company,"123456");
				if (sendresponse.getCode() != null && sendresponse.getCode().equals("OK")) {
					message = "短信通知发送成功";
				}else{
					message = "短信通知发送失败";
				}
			}else{
				SendSmsResponse sendresponse =CodeUtil.sendFail(phone, company);
				if (sendresponse.getCode() != null && sendresponse.getCode().equals("OK")) {
					message = "短信通知发送成功";
				}else{
					message = "短信通知发送失败";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "短信通知发送失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 设置vip商家
	 * 
	 * @return
	 */
	@RequestMapping(params = "setvip")
	@ResponseBody
	public AjaxJson setvip(NMerchantEntity nMerchant, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		nMerchant = systemService.getEntity(NMerchantEntity.class, nMerchant.getId());
		message = "设置vip商家成功";
		try{
			nMerchant.setIspay("0");
			String depositMoney=nMerchant.getDepositMoney();
			if(StringUtil.is_khEmpty(depositMoney))
				depositMoney="0.00";
		/***	startshoptype	开店性质修改0vip商家1普通商家*/
			nMerchant.setStartshoptype("0");
			systemService.saveOrUpdate(nMerchant);
		}catch(Exception e){
			e.printStackTrace();
			message = "设置vip商家失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 商家信息表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(NMerchantEntity nMerchant, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(nMerchant.getId())) {
			nMerchant = nMerchantService.getEntity(NMerchantEntity.class, nMerchant.getId());
			req.setAttribute("nMerchantPage", nMerchant);
		}
		StringBuffer sql=new StringBuffer(" select id,departname from n_goods_classify ");
		sql.append("where del_flag='0' and parentdepartid='0'");
		  List<Object[]>list=systemService.
		    		 findListbySql(sql.toString());
		     StringBuffer htm=new StringBuffer();
		     for(int i=0;i<list.size();i++){
		    	 Object[] obj=list.get(i);
		    	 htm.append("<option value=\"").append(obj[0]).append("\">").append(obj[1]).append("</option>");
		     }
		     req.setAttribute("goods", htm.toString());
		return new ModelAndView("com/jeecg/n_merchant/nMerchant-add");
	}
	/**
	 * 商家信息表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(NMerchantEntity nMerchant, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(nMerchant.getId())) {
			nMerchant = nMerchantService.getEntity(NMerchantEntity.class, nMerchant.getId());
			req.setAttribute("nMerchantPage", nMerchant);
			StringBuffer sql=new StringBuffer(" select id,departname from n_goods_classify ");
			sql.append(" where del_flag='0' and parentdepartid='0'");
			  List<Object[]>list=systemService.
			    		 findListbySql(sql.toString());
			  StringBuffer htm=new StringBuffer();
			  if(nMerchant!=null){
			     String  jingying_id=nMerchant.getJingying();
			     for(int i=0;i<list.size();i++){
			    	 Object[] obj=list.get(i);
			    	 String jingying=obj[0]+"";
			    	 System.out.println(jingying_id+"="+jingying);
			    	 if(jingying_id.equals(jingying)){
			    	     htm.append("<option selected=\"selected\" value=\"").append(jingying).append("\">").append(obj[1]).append("</option>");
			    	 }else{
			    	 	 htm.append("<option value=\"").append(jingying).append("\">").append(obj[1]).append("</option>");
			    	 }
			     }
			     req.setAttribute("goods", htm.toString());
			  }
		}
		
		return new ModelAndView("com/jeecg/n_merchant/nMerchant-update");
	}
	
	/**
	 * 商家信息表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate_tel")
	public ModelAndView goUpdate_tel(NMerchantEntity nMerchant, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(nMerchant.getId())) {
			nMerchant = nMerchantService.getEntity(NMerchantEntity.class, nMerchant.getId());
			req.setAttribute("nMerchantPage", nMerchant);
		}
		return new ModelAndView("com/jeecg/n_merchant/tel-update");
	}
	/**
	 * 商家信息表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate_money")
	public ModelAndView goUpdate_money(NMerchantEntity nMerchant, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(nMerchant.getId())) {
			nMerchant = nMerchantService.getEntity(NMerchantEntity.class, nMerchant.getId());
			req.setAttribute("nMerchantPage", nMerchant);
			StringBuffer sql=new StringBuffer(" select id,departname from n_goods_classify ");
			sql.append(" where del_flag='0' and parentdepartid='0'");
			  List<Object[]>list=systemService.
			    		 findListbySql(sql.toString());
			  StringBuffer htm=new StringBuffer();
			  if(nMerchant!=null){
			     String  jingying_id=nMerchant.getJingying();
			     for(int i=0;i<list.size();i++){
			    	 Object[] obj=list.get(i);
			    	 String jingying=obj[0]+"";
			    	 System.out.println(jingying_id+"="+jingying);
			    	 if(jingying_id.equals(jingying)){
			    	     htm.append("<option selected=\"selected\" value=\"").append(jingying).append("\">").append(obj[1]).append("</option>");
			    	 }else{
			    	 	 htm.append("<option value=\"").append(jingying).append("\">").append(obj[1]).append("</option>");
			    	 }
			     }
			     req.setAttribute("goods", htm.toString());
			  }
		}
		
		return new ModelAndView("com/jeecg/n_merchant/nMerchant-update_money");
	}
	/**
	 * 商家信息表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAudit")
	public ModelAndView goAudit(NMerchantEntity nMerchant, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(nMerchant.getId())) {
			nMerchant = nMerchantService.getEntity(NMerchantEntity.class, nMerchant.getId());
			req.setAttribute("nMerchantPage", nMerchant);
			StringBuffer sql=new StringBuffer(" select id,departname from n_goods_classify ");
			sql.append(" where del_flag='0' and parentdepartid='0'");
			  List<Object[]>list=systemService.
			    		 findListbySql(sql.toString());
			  StringBuffer htm=new StringBuffer();
			  if(nMerchant!=null){
			     String  jingying_id=nMerchant.getJingying();
			     for(int i=0;i<list.size();i++){
			    	 Object[] obj=list.get(i);
			    	 String jingying=obj[0]+"";
			    	 System.out.println(jingying_id+"="+jingying);
			    	 if(jingying_id.equals(jingying)){
			    	     htm.append("<option selected=\"selected\" value=\"").append(jingying).append("\">").append(obj[1]).append("</option>");
			    	 }else{
			    	 	 htm.append("<option value=\"").append(jingying).append("\">").append(obj[1]).append("</option>");
			    	 }
			     }
			     req.setAttribute("goods", htm.toString());
			  }
		}
		
		return new ModelAndView("com/jeecg/n_merchant/nMerchant-audit");
	}
	
	/**
	 * 商家信息表
	 * 
	 * @return
	 */
	@RequestMapping(params = "goindex")
	public ModelAndView goindex( HttpServletRequest req) {
		//用户信息获得
		TSUser tsuser=ResourceUtil.getSessionUserName();
		String id=tsuser.getPkid();
		if (StringUtil.isNotEmpty(id)) {
			NMerchantEntity	nMerchant = nMerchantService.getEntity(NMerchantEntity.class, id);
			req.setAttribute("nMerchantPage", nMerchant);
			StringBuffer sql=new StringBuffer(" select id,departname from n_goods_classify ");
			sql.append(" where del_flag='0' and parentdepartid='0'");
			  List<Object[]>list=systemService.
			    		 findListbySql(sql.toString());
			  StringBuffer htm=new StringBuffer();
			  if(nMerchant!=null){
			     String  jingying_id=nMerchant.getJingying();
			     for(int i=0;i<list.size();i++){
			    	 Object[] obj=list.get(i);
			    	 String jingying=obj[0]+"";
			    	 System.out.println(jingying_id+"="+jingying);
			    	 if(jingying_id.equals(jingying)){
			    	     htm.append("<option selected=\"selected\" value=\"").append(jingying).append("\">").append(obj[1]).append("</option>");
			    	 }else{
			    	 	 htm.append("<option value=\"").append(jingying).append("\">").append(obj[1]).append("</option>");
			    	 }
			     }
			     req.setAttribute("goods", htm.toString());
			  }
		}
		
		return new ModelAndView("com/jeecg/n_merchant/nMerchant-index");
	}
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","nMerchantController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(NMerchantEntity nMerchant,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(NMerchantEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, nMerchant, request.getParameterMap());
		List<NMerchantEntity> nMerchants = this.nMerchantService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"商家信息表");
		modelMap.put(NormalExcelConstants.CLASS,NMerchantEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("商家信息表列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,nMerchants);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(NMerchantEntity nMerchant,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"商家信息表");
    	modelMap.put(NormalExcelConstants.CLASS,NMerchantEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("商家信息表列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
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
				List<NMerchantEntity> listNMerchantEntitys = ExcelImportUtil.importExcel(file.getInputStream(),NMerchantEntity.class,params);
				for (NMerchantEntity nMerchant : listNMerchantEntitys) {
					nMerchantService.save(nMerchant);
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
	public List<NMerchantEntity> list() {
		List<NMerchantEntity> listNMerchants=nMerchantService.getList(NMerchantEntity.class);
		return listNMerchants;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		NMerchantEntity task = nMerchantService.get(NMerchantEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody NMerchantEntity nMerchant, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<NMerchantEntity>> failures = validator.validate(nMerchant);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			nMerchantService.save(nMerchant);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = nMerchant.getId();
		URI uri = uriBuilder.path("/rest/nMerchantController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody NMerchantEntity nMerchant) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<NMerchantEntity>> failures = validator.validate(nMerchant);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			nMerchantService.saveOrUpdate(nMerchant);
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
		nMerchantService.deleteEntityById(NMerchantEntity.class, id);
	}
	
	public boolean Insertuser(String username,String realname,String pkid){
//		String username=t.getPhone();
//		String realname=t.getCompany();
		List<TSUser> users = systemService.findByProperty(TSUser.class,"userName",username);
		TSUser tsUser=new TSUser();
//		tsUser.setId(nMerchant.getId());
		tsUser.setEducation("1");
		tsUser.setShoptype("1");//1是商户null是平台
		tsUser.setUserName(username);
		tsUser.setRealName(realname);
		tsUser.setPassword(PasswordUtil.encrypt(username, "123456", PasswordUtil.getStaticSalt()));
	//	tsUser.setDepartid("A06A01");
		tsUser.setStatus(new Short("0"));//1正常0锁定
		tsUser.setDeleteFlag(new Short("1"));
		tsUser.setPkid(pkid);
		if(users.size()!=0){
/*			try {
				TSUser user = users.get(0);
				MyBeanUtils.copyBeanNotNull2Bean(tsUser,user);
			    systemService.saveOrUpdate(user);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			return false;
		}else{
			System.out.println("id==="+tsUser.getId());
			tsUser.setDepartid(null);
			systemService.save(tsUser);
			List<TSRole> roleList = systemService.findByProperty(TSRole.class,"roleCode","shop");
			TSRoleUser tsRoleUser = new TSRoleUser();
			tsRoleUser.setTSUser(tsUser);
			tsRoleUser.setTSRole(roleList.get(0));
			systemService.save(tsRoleUser);
			List<TSDepart> departList_org = systemService.findByProperty(TSDepart.class,"orgCode","A06A01");
			TSUserOrg tsUserOrg = new TSUserOrg();
			tsUserOrg.setTsDepart(departList_org.get(0));
			tsUserOrg.setTsUser(tsUser);
			systemService.save(tsUserOrg);
		}
		return true;
	}
}
