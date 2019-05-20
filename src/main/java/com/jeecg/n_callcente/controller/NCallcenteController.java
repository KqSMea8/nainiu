package com.jeecg.n_callcente.controller;
import com.jeecg.n_callcente.entity.NCallcenteEntity;
import com.jeecg.n_callcente.service.NCallcenteServiceI;

import java.util.ArrayList;
import java.util.List;
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

/**   
 * @Title: Controller  
 * @Description: 客服账号添加
 * @author onlineGenerator
 * @date 2018-06-23 11:37:15
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/nCallcenteController")
public class NCallcenteController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(NCallcenteController.class);

	@Autowired
	private NCallcenteServiceI nCallcenteService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 客服账号添加列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		TSUser tsuser=ResourceUtil.getSessionUserName();
		String id=tsuser.getPkid();
		request.setAttribute("merchantid", id);
		return new ModelAndView("com/jeecg/n_callcente/nCallcenteList");
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
	public void datagrid(NCallcenteEntity nCallcente,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String account = nCallcente.getAccount();
		if (!StringUtil.isEmpty(account)) {
			nCallcente.setAccount("*"+account+"*");
		}
		CriteriaQuery cq = new CriteriaQuery(NCallcenteEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, nCallcente, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.nCallcenteService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	/**
	 * 修改客服账号添加
	 * 
	 * @return
	 */
	@RequestMapping(params = "doUpdateDel")
	@ResponseBody
	public AjaxJson doUpdateDel(NCallcenteEntity nCallcente, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "客服账号状态更新成功";
		NCallcenteEntity t = nCallcenteService.get(NCallcenteEntity.class, nCallcente.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(nCallcente, t);
			nCallcenteService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			String callstatus=t.getCallstatus();//0正常1禁用
			String account=t.getAccount();
			TSUser user=systemService.findUniqueByProperty(TSUser.class,"userName",account);
			if("0".equals(callstatus)){
				user.setStatus(new Short("1"));//1正常0锁定
				user.setDeleteFlag(new Short("0"));//1删除0正常
			}else{
				user.setStatus(new Short("0"));//1正常0锁定
				user.setDeleteFlag(new Short("0"));//1删除0正常
			}
			systemService.saveOrUpdate(user);
		} catch (Exception e) {
			e.printStackTrace();
			message = "客服账号状态更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 删除客服账号添加
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(NCallcenteEntity nCallcente, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		nCallcente = systemService.getEntity(NCallcenteEntity.class, nCallcente.getId());
		message = "客服账号添加删除成功";
		try{
			nCallcenteService.delete(nCallcente);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "客服账号添加删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除客服账号添加
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "客服账号添加删除成功";
		try{
			for(String id:ids.split(",")){
				NCallcenteEntity nCallcente = systemService.getEntity(NCallcenteEntity.class, 
				id
				);
				nCallcenteService.delete(nCallcente);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "客服账号添加删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

		public void Insertuser(String account,String password,String pkid){
			TSUser tsUser=new TSUser();
			tsUser.setEducation("1");
			tsUser.setShoptype("1");//1是商户null是平台
			tsUser.setUserName(account);
			tsUser.setRealName("客服"+account);
			tsUser.setPassword(PasswordUtil.encrypt(account, password, PasswordUtil.getStaticSalt()));
			tsUser.setStatus(new Short("1"));//1正常0锁定
			tsUser.setDeleteFlag(new Short("0"));//1删除0正常
			tsUser.setPkid(pkid);
			tsUser.setDepartid(null);
			systemService.save(tsUser);
			List<TSRole> roleList = systemService.findByProperty(TSRole.class,"roleCode","callcenter");
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
	/**
	 * 添加客服账号添加
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(NCallcenteEntity nCallcente, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "客服账号添加添加成功";
		try{
			TSUser user=systemService.findUniqueByProperty(TSUser.class,"userName",nCallcente.getAccount());
			if(user!=null){
				message = "客服账号已经存在，请重新添加";
			}else{
			TSUser tsuser=ResourceUtil.getSessionUserName();
			String merchantid=tsuser.getPkid();
			nCallcente.setMerchantid(merchantid);
			nCallcenteService.save(nCallcente);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			Insertuser(nCallcente.getAccount(), nCallcente.getPassword(), merchantid);
		   }
			
		}catch(Exception e){
			e.printStackTrace();
			message = "客服账号添加添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新客服账号添加
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(NCallcenteEntity nCallcente, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "客服账号添加更新成功";
		NCallcenteEntity t = nCallcenteService.get(NCallcenteEntity.class, nCallcente.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(nCallcente, t);
			nCallcenteService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "客服账号添加更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 客服账号添加新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(NCallcenteEntity nCallcente, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(nCallcente.getId())) {
			nCallcente = nCallcenteService.getEntity(NCallcenteEntity.class, nCallcente.getId());
			req.setAttribute("nCallcentePage", nCallcente);
		}
		return new ModelAndView("com/jeecg/n_callcente/nCallcente-add");
	}
	/**
	 * 客服账号添加编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(NCallcenteEntity nCallcente, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(nCallcente.getId())) {
			nCallcente = nCallcenteService.getEntity(NCallcenteEntity.class, nCallcente.getId());
			req.setAttribute("nCallcentePage", nCallcente);
		}
		return new ModelAndView("com/jeecg/n_callcente/nCallcente-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","nCallcenteController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(NCallcenteEntity nCallcente,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(NCallcenteEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, nCallcente, request.getParameterMap());
		List<NCallcenteEntity> nCallcentes = this.nCallcenteService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"客服账号添加");
		modelMap.put(NormalExcelConstants.CLASS,NCallcenteEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("客服账号添加列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,nCallcentes);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(NCallcenteEntity nCallcente,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"客服账号添加");
    	modelMap.put(NormalExcelConstants.CLASS,NCallcenteEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("客服账号添加列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
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
				List<NCallcenteEntity> listNCallcenteEntitys = ExcelImportUtil.importExcel(file.getInputStream(),NCallcenteEntity.class,params);
				for (NCallcenteEntity nCallcente : listNCallcenteEntitys) {
					nCallcenteService.save(nCallcente);
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
	public List<NCallcenteEntity> list() {
		List<NCallcenteEntity> listNCallcentes=nCallcenteService.getList(NCallcenteEntity.class);
		return listNCallcentes;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		NCallcenteEntity task = nCallcenteService.get(NCallcenteEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody NCallcenteEntity nCallcente, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<NCallcenteEntity>> failures = validator.validate(nCallcente);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			nCallcenteService.save(nCallcente);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = nCallcente.getId();
		URI uri = uriBuilder.path("/rest/nCallcenteController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody NCallcenteEntity nCallcente) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<NCallcenteEntity>> failures = validator.validate(nCallcente);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			nCallcenteService.saveOrUpdate(nCallcente);
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
		nCallcenteService.deleteEntityById(NCallcenteEntity.class, id);
	}
}
