package com.jeecg.websocket.controller;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.AESUtils;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.PasswordUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSRole;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import test.CodeUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.jeecg.n_goods_detaisl.entity.NGoodsDetaislEntity;
import com.jeecg.n_home_banner.entity.NHomeBannerEntity;
import com.jeecg.n_phone_code.entity.NPhoneCodeEntity;
import com.jeecg.n_standard_details.entity.NStandardDetailsEntity;
import com.jeecg.n_user.entity.NUserEntity;
import com.jeecg.newverison.entity.NewverisonEntity;
import com.jeecg.s_tuisong.entity.STuisongEntity;

/**
 * @Title: Controller
 * @Description: 接口
 * @author onlineGenerator
 * @date 2017-08-18 17:45:08
 * @version V1.0
 *
 */
@Controller
@RequestMapping("/websocketcontroller")
public class WebsocketController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(WebsocketController.class);

	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;

	/**
	 * 打开聊天界面
	 * 
	 * @return
	 */
	@RequestMapping(params = "celist")
	public ModelAndView celist(HttpServletRequest request, String sendid,
			String acceptid, String socketid) {

		request.setAttribute("sendid", sendid);
		request.setAttribute("acceptid", acceptid);
		request.setAttribute("socketid", socketid);
		return new ModelAndView("com/jeecg/websocket/websocket");
	}
	/**
	 * 打开聊天界面
	 * 
	 * @return
	 */
	@RequestMapping(params = "celistsocket")
	public ModelAndView celistsocket(HttpServletRequest request, String sendid,
			String acceptid, String socketid) {

		request.setAttribute("sendid", sendid);
		request.setAttribute("acceptid", acceptid);
		request.setAttribute("socketid", socketid);
		return new ModelAndView("com/jeecg/websocket/websocketto");
	}
	/**
	 * 打开聊天界面
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request, String data) {
		data = AESUtils.decryptData(data);
		System.out.println("==" + data);
		JSONObject json = JSON.parseObject(data);
		String sendid = json.get("sendid").toString();
		String sendname = json.get("sendname").toString();
		String acceptid = json.get("acceptid").toString();
		String acceptname = json.get("acceptname").toString();
		request.setAttribute("sendid", sendid);
		request.setAttribute("sendname", sendname);
		request.setAttribute("acceptid", acceptid);
		request.setAttribute("sendid", sendid);
		request.setAttribute("acceptname", acceptname);
		return new ModelAndView("com/jeecg/websocket/websocket");
	}



	// 获得聊天信息目录
	@RequestMapping(params = "socketidcloumn")
	public void socketidcloumn(HttpServletRequest request,
			HttpServletResponse response, String userid, Integer page,
			Integer rows) {
		Integer start = (page - 1) * rows;
		if (start > 0) {
			if (page > 0) {
				page = page - 1;
				start = page * rows;
			} else {
				start = 0;
			}
		}
		JSONObject object = new JSONObject();
		JSONArray jsonarray = new JSONArray();
		Format f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuffer sql = new StringBuffer();
	/*	sql.append("   select *from( ")
				.append(" select  t1.id,t1.sendid,t1.sendname,t1.acceptid,t1.acceptname ,")
				.append("t1.goodid,t1.details,t1.flag,t1.socketid,t1.sendurl,t1.accepturl,t1.create_date,")
				.append("t1.title,t1.type,t2.fnmber from ")
				.append(" n_websocket_colum t1  LEFT JOIN ")
				.append(" (select *,COUNT(*)as fnmber from n_websocket ")
				.append(" where flag='0'and (acceptid='0' or acceptid='")
				.append(userid)
				.append("')")
				.append("  GROUP BY flag ) t2 ")
				.append(" on    t1.acceptid=t2.acceptid ")
				.append(" where  t1.type='1' and t1.acceptid='")
				.append(userid)
				.append("' ORDER BY t1.create_date desc ) t1 ")

				.append(" UNION ")
		        .append(" select *from( ")*/
				sql.append(" select  t1.id,t1.sendid,t1.sendname,t1.acceptid,t1.acceptname ,")
				.append("t1.goodid,t1.details,t1.flag,t1.socketid,t1.sendurl,t1.accepturl,t1.create_date,")
				.append(" t1.title,t1.type,t2.fnmber from ")
				.append(" n_websocket_colum t1  LEFT JOIN ")
				.append(" (select *,COUNT(*)as fnmber from n_websocket where flag='0'  and sendid='")
				.append(userid)
				.append("'  ")
				.append(" GROUP BY flag ) t2 ")
				.append(" on   t1.sendid=t2.sendid and  t1.acceptid=t2.acceptid ")
				.append(" where t1.type='0'and t1.sendid='").append(userid)
				.append("' ORDER BY t1.create_date desc ")
			//			+ ")t2 ")
			//	.append("  ORDER BY create_date desc")
				.append(" LIMIT ")
				.append(start).append(",").append(rows);
				System.out.println(sql.toString());
		List<Object[]> mode_list = systemService.findListbySql(sql.toString());
		for (int i = mode_list.size() - 1; i >= 0; i--) {
			JSONObject json = new JSONObject();
			Object[] mode = mode_list.get(i);
			json.put("id", mode[0]);
			json.put("sendid", mode[1]);
			json.put("sendname", mode[2]);
			json.put("acceptid", mode[3]);
			json.put("acceptname", mode[4]);
			json.put("goodid", mode[5]);
			json.put("details", mode[6]);
			json.put("flag", mode[7]);
			json.put("socketid", mode[8]);
			json.put("sendurl", mode[9]);
			json.put("accepturl", mode[10]);
			json.put("create_date", f.format(mode[11]));
			json.put("title", mode[12]);
			json.put("type", mode[13]);
			json.put("fnmber", mode[14]);
			jsonarray.add(json);
		}
		object.put("details", jsonarray);
		object.put("status", "success");
		object.put("message", "获得信息成功");
		TagUtil.getSendJson(response, object.toString());
	
	}

	// 获得聊天信息
	@RequestMapping(params = "socketidinfo")
	public void socketidinfo(HttpServletRequest request,
			HttpServletResponse response, String socketid, Integer page,
			Integer rows,String sendid,String acceptid) {
		Integer start = (page - 1) * rows;
		if (start > 0) {
			if (page > 0) {
				page = page - 1;
				start = page * rows;
			} else {
				start = 0;
			}
		}
		JSONObject object = new JSONObject();
		JSONArray jsonarray = new JSONArray();
		Format f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuffer sql = new StringBuffer();
		sql.append("select id,sendid,sendname,acceptid,acceptname")
				.append(",goodid,details,flag,socketid,sendurl,accepturl,create_date ")
				.append("from n_websocket where 1=1");
		if (!StringUtil.is_khEmpty(socketid)) {
			sql.append(" and socketid='").append(socketid).append("'");
		}

		sql.append("  ORDER BY create_date desc").append(" LIMIT ")
				.append(start).append(",").append(rows);
		List<Object[]> mode_list = systemService.findListbySql(sql.toString());
		for (int i = mode_list.size() - 1; i >= 0; i--) {
			JSONObject json = new JSONObject();
			Object[] mode = mode_list.get(i);
		
			json.put("id", mode[0]);
			json.put("sendid", mode[1]);
			json.put("sendname", mode[2]);
			json.put("acceptid", mode[3]);
			json.put("acceptname", mode[4]);
			json.put("goodid", mode[5]);
			json.put("details", mode[6]);
			json.put("flag", mode[7]);
			json.put("socketid", mode[8]);
			json.put("sendurl", mode[9]);
			json.put("accepturl", mode[10]);
			json.put("create_date", f.format(mode[11]));
			jsonarray.add(json);
		}
		object.put("details", jsonarray);
		object.put("status", "success");
		object.put("message", "获得信息成功");
		TagUtil.getSendJson(response, object.toString());
		updateinfo(sendid, acceptid);
	}

	// 查看是否存在目录
	@RequestMapping(params = "getsocketinfo")
	public void getsocketinfo(HttpServletRequest request,
			HttpServletResponse response, String sendid, String acceptid) {
		Format f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuffer sql = new StringBuffer();
		sql.append("select id,sendid,sendname,acceptid,acceptname")
				.append(",goodid,details,flag,socketid,sendurl,accepturl,create_date ")
				.append("from n_websocket_colum where type='0' ");
		if (!StringUtil.is_khEmpty(sendid)) {
			sql.append(" and sendid='").append(sendid).append("'");
		}
		if (!StringUtil.is_khEmpty(acceptid)) {
			sql.append(" and acceptid='").append(acceptid).append("'");
		}
		sql.append("  ORDER BY create_date desc").append(" LIMIT ").append(0)
				.append(",").append(10);
		System.out.println("sql.toString()="+sql.toString());
		List<Object[]> mode_list = systemService.findListbySql(sql.toString());
		String status = "success";
		JSONObject json = new JSONObject();
		if (mode_list.size() > 0) {

			Object[] mode = mode_list.get(0);
			json.put("id", mode[0]);
			json.put("sendid", mode[1]);
			json.put("acceptid", mode[3]);
			/*json.put("sendname", mode[2]);
			
			json.put("acceptname", mode[4]);
			json.put("goodid", mode[5]);
			json.put("details", mode[6]);
			json.put("flag", mode[7]);*/
			json.put("socketid", mode[8]);
		/*	json.put("sendurl", mode[9]);
			json.put("accepturl", mode[10]);
			json.put("create_date", f.format(mode[11]));
			*/
		} else {
			status = "failed";
			json.put("socketid", StringUtil.getId());

		}
		json.put("status", status);
		json.put("message", "获得信息成功");
		TagUtil.getSendJson(response, json.toString());
	}

	// 修改聊天信息已读
	public void updateinfo(String sendid, String acceptid) {
		StringBuffer sql = new StringBuffer();
		sql.append("update  n_websocket set flag='1' ").append(" where  flag='0' ");
		if (!StringUtil.is_khEmpty(sendid)) {
			sql.append(" and sendid='").append(sendid).append("'");
		}
		if (!StringUtil.is_khEmpty(acceptid)) {
			sql.append(" and acceptid='").append(acceptid).append("'");
		}
		systemService.updateBySqlString(sql.toString());
		System.out.println("开始====");
	}
	/*
	 * @RequestMapping(params = "updateinfo") public void
	 * updateinfo(HttpServletRequest request, HttpServletResponse response,
	 * String sendid, String acceptid) { JSONObject object = new JSONObject();
	 * StringBuffer sql = new StringBuffer();
	 * sql.append("update  n_websocket set flag='1' ").append(" where 1=1"); if
	 * (!StringUtil.is_khEmpty(sendid)) {
	 * sql.append(" and sendid='").append(sendid).append("'"); } if
	 * (!StringUtil.is_khEmpty(acceptid)) {
	 * sql.append(" and acceptid='").append(acceptid).append("'"); }
	 * systemService.updateBySqlString(sql.toString()); object.put("status",
	 * "success"); object.put("message", "获得信息成功");
	 * TagUtil.getSendJson(response, object.toString()); }
	 */
}
