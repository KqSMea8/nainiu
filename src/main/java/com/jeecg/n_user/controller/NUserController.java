package com.jeecg.n_user.controller;
import com.jeecg.n_user.entity.NUserEntity;
import com.jeecg.n_user.service.NUserServiceI;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.core.util.ResourceUtil;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Map;

import org.jeecgframework.core.util.ExceptionUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
 * @Description: 用户信息
 * @author onlineGenerator
 * @date 2017-12-19 10:51:32
 * @version V1.0
 *
 */
@Controller
@RequestMapping("/nUserController")
public class NUserController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(NUserController.class);

	@Autowired
	private NUserServiceI nUserService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;


	 /**
	  * @Author shishanshan
	  * @Desprition 获取会员组织信息
	  * @Date 2018/9/27 15:40
	  * @Param
	  * @Return
	  */
	 @RequestMapping(params = "memberGroup")
	 public String memberGroup(ModelMap modelMap){
		 StringBuffer sb = new StringBuffer();
		 try {
			 sb.append("<script type=\"text/javascript\">\r\n");
			 sb.append(" d = new dTree('d');\r\n");
			 sb.append("d.add(0, -1, '会员组织架构');");
			 // id - pid
			// sb.append("d.add(1, 0, '历史报表按日期', 'javascript:void(0)', \"clickCydxTree();\", '历史报表按日期');");
			 //树拼接逻辑
			 //1获取所有会员信息
			 String allSql = "select user_id,id from n_user_price  where orderid = '1'  order by create_date desc";
			 List<Object[]> listbySql = systemService.findListbySql(allSql);
			 if(listbySql != null && listbySql.size()>0){
				 for (int i = 1; i < listbySql.size()+1; i++) {
					 Object[] objects = listbySql.get(i-1);
					 String userId = objects[0].toString();
					 //String s = dgDtree(userId, id, i);
					 //sb.append(s);
                     String sqlCheck = "select distinct(update_by),user_id from n_user_price where orderid = '7' and user_id = '"+userId+"' ";
                     List<Object[]> listbySql1 = systemService.findListbySql(sqlCheck);
                     if(listbySql1 != null && listbySql1.size()>0){
                         NUserEntity nUserEntity = systemService.getEntity(NUserEntity.class, userId);
                         sb.append("d.add("+userId+", 0, '"+nUserEntity.getRealname()+"', 'javascript:void(0)', 'javascript:void(0)', '"+nUserEntity.getRealname()+"');");
                         for (int j = 0; j < listbySql1.size(); j++) {
                             Object[] objects1 =  listbySql1.get(j);
                             String userId1 =  objects1[0].toString();//被推荐人Id
                             NUserEntity nUserEntity1 = systemService.getEntity(NUserEntity.class, userId1);
                             sb.append("d.add("+userId1+", "+objects1[1].toString()+", '"+nUserEntity1.getRealname()+"', 'javascript:void(0)', 'javascript:void(0)', '"+nUserEntity1.getRealname()+"');");
                             String sqlCheck1 = "select distinct(update_by),user_id from n_user_price where orderid = '7'  and user_id = '"+userId1+"' ";
                             List<Object[]> listbySql2 = systemService.findListbySql(sqlCheck1);
                           if(listbySql2 != null && listbySql2.size()>0){
                                 for (int k = 0; k < listbySql2.size(); k++) {
                                     Object[] objects2 =  listbySql2.get(k);
                                     String userId2 =  objects2[0].toString();//被推荐人Id
                                     NUserEntity nUserEntity2 = systemService.getEntity(NUserEntity.class, userId2);
                                     sb.append("d.add("+userId2+", "+objects2[1].toString()+", '"+nUserEntity2.getRealname()+"', 'javascript:void(0)', 'javascript:void(0)', '"+nUserEntity2.getRealname()+"');");
                                     String sqlCheck2 = "select distinct(update_by),user_id from n_user_price where orderid = '7' and user_id = '"+userId2+"' ";
                                     List<Object[]> listbySql3 = systemService.findListbySql(sqlCheck2);
                                     if(listbySql3 != null && listbySql3.size()>0){
                                         for (int l = 0; l < listbySql3.size(); l++) {
                                             Object[] objects3 =  listbySql3.get(l);
                                             String userId3 =  objects3[0].toString();//被推荐人Id
                                             NUserEntity nUserEntity3 = systemService.getEntity(NUserEntity.class, userId3);
                                             sb.append("d.add("+userId3+", "+objects3[1].toString()+", '"+nUserEntity3.getRealname()+"', 'javascript:void(0)', 'javascript:void(0)', '"+nUserEntity3.getRealname()+"');");
                                             String sqlCheck3 = "select distinct(update_by),user_id from n_user_price where orderid = '7' and user_id = '"+userId3+"' ";
                                             List<Object[]> listbySql4 = systemService.findListbySql(sqlCheck3);
                                             if(listbySql4 != null && listbySql4.size()>0){
                                                 for (int m = 0; m < listbySql4.size(); m++) {
                                                     Object[] objects4 =  listbySql4.get(m);
                                                     String userId4 =  objects4[0].toString();//被推荐人Id
                                                     NUserEntity nUserEntity4 = systemService.getEntity(NUserEntity.class, userId4);
                                                     sb.append("d.add("+userId4+", "+objects4[1].toString()+", '"+nUserEntity4.getRealname()+"', 'javascript:void(0)', 'javascript:void(0)', '"+nUserEntity4.getRealname()+"');");
                                                     String sqlCheck4 = "select distinct(update_by),user_id from n_user_price where orderid = '7' and user_id = '"+userId4+"' ";
                                                     List<Object[]> listbySql5 = systemService.findListbySql(sqlCheck4);
                                                     if(listbySql5 != null && listbySql5.size()>0){
                                                         for (int n = 0; n < listbySql5.size(); n++) {
                                                             Object[] objects5 =  listbySql5.get(n);
                                                             String userId5 =  objects5[0].toString();//被推荐人Id
                                                             NUserEntity nUserEntity5 = systemService.getEntity(NUserEntity.class, userId5);
                                                             sb.append("d.add("+userId5+", "+objects5[1].toString()+", '"+nUserEntity5.getRealname()+"', 'javascript:void(0)', 'javascript:void(0)', '"+nUserEntity5.getRealname()+"');");
                                                             String sqlCheck5 = "select distinct(update_by),user_id from n_user_price where orderid = '7' and user_id = '"+userId5+"' ";
                                                             List<Object[]> listbySql6 = systemService.findListbySql(sqlCheck5);
                                                             if(listbySql6 != null && listbySql6.size()>0){
                                                                 for (int o = 0; o < listbySql6.size(); o++) {
                                                                     Object[] objects6 =  listbySql6.get(o);
                                                                     String userId6 =  objects6[0].toString();//被推荐人Id
                                                                     NUserEntity nUserEntity6 = systemService.getEntity(NUserEntity.class, userId6);
                                                                     sb.append("d.add("+userId6+", "+objects6[1].toString()+", '"+nUserEntity6.getRealname()+"', 'javascript:void(0)', 'javascript:void(0)', '"+nUserEntity6.getRealname()+"');");
                                                                 }
                                                             }
                                                         }
                                                     }
                                                 }
                                             }
                                         }
                                     }
                                 }
                             }
                         }
                     }
				 }
			 }
			 sb.append(" document.write(d);\r\n");
			 sb.append("</script>\r\n");
		 }catch (Exception e){
			 logger.error(e);
			 e.printStackTrace();
		 }
		 //System.out.println(sb.toString());
		 modelMap.put("dtreeJson",sb.toString());
		 return  "/memberGroup/memberGroup";
	 }
	 /**
	  * @Author shishanshan
	  * @Desprition 递归查询树
	  * @Date 2018/9/27 16:20
	  * @Param
	  * @Return
	  */
	public  String  dgDtree(String userid,int id,int pid){
		System.out.println("时珊珊递归循环");
		boolean flag = false;
		StringBuffer sb = new StringBuffer();
		int id1 = id+100;
		//判断用户是否推荐过会员
		String sqlCheck = "select update_by,user_id from n_user_price where user_price_type = '推荐奖励' and user_id = '"+userid+"' and price = '30'";
		List<Object[]> listbySql1 = systemService.findListbySql(sqlCheck);
		 if (listbySql1 != null && listbySql1.size()>0){
			 for (int i = 0; i < listbySql1.size(); i++) {
				 Object[] objects = listbySql1.get(i);
				 String userId2 =  objects[0].toString();
				 String userId1 =  objects[1].toString();
				 System.out.println(sb.toString());
				 if(!flag){
					 NUserEntity nUserEntity = systemService.getEntity(NUserEntity.class, userId1);
					 sb.append("d.add("+id1+", "+pid+", '"+nUserEntity.getRealname()+"', 'javascript:void(0)', \"clickCydxTree();\", '"+nUserEntity.getRealname()+"');");
					 flag = true;
					String s = dgDtree(userId2, id1, id);
				 }
			 }
		}else{
			 return "";
		 }
		return sb.toString();
	}
	/**
	 * 用户信息列表 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/n_user/nUserList");
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
	public void datagrid(NUserEntity nUser,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String realname = nUser.getRealname();
		String username = nUser.getUsername();
		if (!StringUtil.isEmpty(realname)) {
			nUser.setRealname("*"+realname+"*");
		}
		if (!StringUtil.isEmpty(username)) {
			nUser.setUsername("*"+username+"*");
		}
		CriteriaQuery cq = new CriteriaQuery(NUserEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, nUser, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.nUserService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除用户信息
	 *
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(NUserEntity nUser, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		String accountStatus=nUser.getAccountStatus();
		nUser = systemService.getEntity(NUserEntity.class, nUser.getId());
		message = "用户信息删除成功";
		try{
			//nUserService.delete(nUser);
			nUser.setAccountStatus(accountStatus);
			nUserService.saveOrUpdate(nUser);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "用户信息删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除用户信息
	 *
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "用户信息删除成功";
		try{
			for(String id:ids.split(",")){
				NUserEntity nUser = systemService.getEntity(NUserEntity.class,
				id
				);
				nUserService.delete(nUser);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "用户信息删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加用户信息
	 *
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(NUserEntity nUser, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "用户信息添加成功";
		try{
			String id=UUID.randomUUID().toString();
			nUser.setId(id);
			nUserService.save(nUser);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "用户信息添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 更新用户信息
	 *
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(NUserEntity nUser, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "用户信息更新成功";
		NUserEntity t = nUserService.get(NUserEntity.class, nUser.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(nUser, t);
			nUserService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "用户信息更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 用户信息新增页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(NUserEntity nUser, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(nUser.getId())) {
			nUser = nUserService.getEntity(NUserEntity.class, nUser.getId());
			req.setAttribute("nUserPage", nUser);
		}
		return new ModelAndView("com/jeecg/n_user/nUser-add");
	}
	/**
	 * 用户信息编辑页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(NUserEntity nUser, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(nUser.getId())) {
			nUser = nUserService.getEntity(NUserEntity.class, nUser.getId());
			req.setAttribute("nUserPage", nUser);
		}
		return new ModelAndView("com/jeecg/n_user/nUser-update");
	}

	/**
	 * 导入功能跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","nUserController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

	/**
	 * 导出excel
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(NUserEntity nUser,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(NUserEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, nUser, request.getParameterMap());
		List<NUserEntity> nUsers = this.nUserService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"用户信息");
		modelMap.put(NormalExcelConstants.CLASS,NUserEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("用户信息列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,nUsers);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(NUserEntity nUser,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"用户信息");
    	modelMap.put(NormalExcelConstants.CLASS,NUserEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("用户信息列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
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
				List<NUserEntity> listNUserEntitys = ExcelImportUtil.importExcel(file.getInputStream(),NUserEntity.class,params);
				for (NUserEntity nUser : listNUserEntitys) {
					nUserService.save(nUser);
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
	public List<NUserEntity> list() {
		List<NUserEntity> listNUsers=nUserService.getList(NUserEntity.class);
		return listNUsers;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		NUserEntity task = nUserService.get(NUserEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody NUserEntity nUser, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<NUserEntity>> failures = validator.validate(nUser);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			nUserService.save(nUser);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = nUser.getId();
		URI uri = uriBuilder.path("/rest/nUserController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody NUserEntity nUser) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<NUserEntity>> failures = validator.validate(nUser);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			nUserService.saveOrUpdate(nUser);
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
		nUserService.deleteEntityById(NUserEntity.class, id);
	}
}
