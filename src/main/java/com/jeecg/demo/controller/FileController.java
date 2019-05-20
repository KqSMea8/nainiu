package com.jeecg.demo.controller;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;

import com.jeecg.n_picture_propaganda.entity.NPicturePropaganda;
import com.jeecg.n_user.entity.NUserEntity;
import com.jeecg.n_user_price.entity.NUserPriceEntity;
import com.jeecg.weixin.util.WeixinService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.util.*;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSTypegroup;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jeecg.n_evaluate.entity.NEvaluateEntity;
import com.jeecg.n_evaluate_colum.entity.NEvaluateColumEntity;
import com.jeecg.n_evaluate_type.entity.NEvaluateTypeEntity;

/**
 * @Title: Controller
 * @Description: 接口
 * @author onlineGenerator
 * @date 2017-08-18 17:45:08
 * @version V1.0
 *
 */
@Controller
@RequestMapping("/uplodfil")
public class FileController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(FileController.class);

	@Autowired
	private SystemService systemService;
    @Resource
    private WeixinService weixinservice;
	@Autowired
	private Validator validator;
	private String basePath_img = "E:/ResourceData/nainiu/img";
	private String basePath_file = "E:/ResourceData/nainiu/file";
	private String http_img = WebUtils.IMG_WEB+"nainiu/img";
	private String http_file = WebUtils.IMG_WEB+"nainiu/file";

	 /**
	  * @Author shishanshan
	  * @Desprition 上传宣传图片
	  * @Date 2018/10/12 17:00
	  * @Param
	  * @Return
	  */
	@RequestMapping(params = "uploadPicturePropaganda")
	public void uploadPicturePropaganda(String userId,HttpServletRequest request,HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        String message = "上传失败";
        String status = "N";
        String urlPath="";
        try {
			String mFilePath = request.getSession().getServletContext().getRealPath("");
			System.out.println("时珊珊"+mFilePath);
            if(StringUtils.isNotBlank(userId)){
                NUserEntity nUserEntity = systemService.getEntity(NUserEntity.class, userId);
                if(nUserEntity!=null){
                    String isMember = nUserEntity.getIsMember();
                    String ext3 = nUserEntity.getExt3();
                    if((StringUtils.isNotBlank(isMember) && "Y".equals(isMember)) || (StringUtils.isNotBlank(ext3) && "Y".equals(ext3))){
                        //判断今天是够领取奖励
                        String sql = "select date_format(create_date,'%Y-%m-%d %H:%i:%s') create_date,id from n_picture_propaganda where user_id = '"+userId+"' order by create_date desc ";
                        List<Object[]> listbySql = systemService.findListbySql(sql);
                        boolean flag = false;
                        if(listbySql!=null && listbySql.size()>0){
                            Object[] objects = listbySql.get(0);
                            String creatDate = objects[0].toString();
                            SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date parse = datetimeFormat.parse(creatDate);
                            boolean isSameDate = DateUtils.isSameDate(parse, new Date());
                            if(isSameDate){//今日已领取
                                message = "今日已领取,请明天做完任务再来!";
                            }else{
                                flag = true;
                            }
                        }else{
                            flag =true;
                        }
                        if (flag){
                            MultipartHttpServletRequest muRequest = (MultipartHttpServletRequest)(request);
                            List<MultipartFile> imgFile = muRequest.getFiles("imgFile");
                            if (imgFile != null && imgFile.size()>0){
                                for (int i = 0; i<imgFile.size(); i++){
                                    SimpleDateFormat sFormat = new SimpleDateFormat("yyyyMMdd");
                                    String dPath = "/" + sFormat.format(new Date()) + "/";
                                    File dir = new File(basePath_img + dPath);
                                    if (!dir.exists()) {
                                        dir.mkdirs();
                                    }
                                    MultipartFile multipartFile = imgFile.get(i);
                                    String fileName=multipartFile.getOriginalFilename();//获取文件名加后缀
                                    //String suffix=fileName.split("\\.")[fileName.split("\\.").length-1];
									String suffix= "jpg";
                                    String base64Name=UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
                                    File file =  new File(basePath_img+dPath,base64Name+"."+suffix);
                                    String url_path=http_img+dPath+base64Name+"."+suffix;
                                    if("".equals(urlPath)){
										urlPath = url_path;
                                    }else{
										urlPath += ";"+url_path;
                                    }
                                    try {
                                        FileUtils.copyInputStreamToFile(multipartFile.getInputStream(),file);//存储文件
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                                status = "Y";
                                message = "上传成功!";
                                NPicturePropaganda nPicturePropaganda = new NPicturePropaganda();
                                nPicturePropaganda.setCreateDate(new Date());
                                nPicturePropaganda.setUserId(userId);
                                nPicturePropaganda.setRealname(nUserEntity.getRealname());
                                nPicturePropaganda.setUsernameurl(nUserEntity.getUsernameurl());
                                nPicturePropaganda.setUrl(urlPath);
                                nPicturePropaganda.setType("0");
                                systemService.save(nPicturePropaganda);
                                //增加奖励金
                                String price = nUserEntity.getPrice();
                                Integer totalNumber = nUserEntity.getTotalNumber();
                                if (totalNumber == null){
                                    totalNumber = 2;
                                }
                                if(!StringUtils.isNotBlank(price)){
                                    price = "0";
                                }
                                price = add(price,Integer.toString(totalNumber));
                                String userExt1 = nUserEntity.getExt1();
                                if (!StringUtils.isNotBlank(userExt1)){
                                    userExt1 = "0";
                                }
                                userExt1 =add(Integer.toString(totalNumber),userExt1);
                                nUserEntity.setExt1(userExt1);
                                nUserEntity.setPrice(price);
                                systemService.saveOrUpdate(nUserEntity);
                                NUserPriceEntity userPriceEntity = new NUserPriceEntity();
                                userPriceEntity.setId(StringUtil.getId());
                                userPriceEntity.setUserId(userId);
                                userPriceEntity.setRealname(nUserEntity.getRealname());
                                userPriceEntity.setCreateDate(new Date());
                                userPriceEntity.setUserPriceType("任务奖励");
                                userPriceEntity.setPrice(Integer.toString(totalNumber));
                                userPriceEntity.setOrderid("3");
                                systemService.save(userPriceEntity);
                                weixinservice.sendTemplate_refferrer(userId,"任务奖励",Integer.toString(totalNumber),"任务奖励","",DateUtils.formatTimehsm(new Date()),"点击查看钱包详情，如有疑问直接咨询在线客服。");
                            }
                        }
                    }else{
                        message = "您不是VIP会员,不能上传任务!";
                    }
                }else{
                    message = "用户信息获取失败!";
                }
            }else{
                message = "用户信息获取失败!";
            }
        }catch (Exception e){
            logger.error(e);
            e.printStackTrace();
        }
        jsonObject.put("urlPath",urlPath);
        jsonObject.put("message",message);
	    jsonObject.put("status",status);
        TagUtil.getSendJson(response,jsonObject.toString());
    }
    public String add(String number1, String number2) {
        BigDecimal b1 = new BigDecimal(number1);
        BigDecimal b2 = new BigDecimal(number2);
        return b1.add(b2).toString();
    }
	/**
	 * 上传图片或者文件
	 * @param houseDisposalEntity
	 * @param wyHouseDisposalPage
	 * @param req
	 * @param resp
	 */
	@RequestMapping(params="img")
	public void img(String userid,HttpServletRequest req,HttpServletResponse resp){
		System.out.println("23122");
		String cupath=req.getRequestURL()+"";
		String propath=req.getContextPath();
		String  t1=cupath.substring(0, cupath.indexOf(propath));
		String  t2=t1+propath;
		String url_path="";
		  CommonsMultipartResolver cmr = new CommonsMultipartResolver(
					req.getServletContext());
			if (cmr.isMultipart(req)) {
				MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) (req);
				Iterator<String> files = mRequest.getFileNames();

				while (files.hasNext()) {
					MultipartFile mFile = mRequest.getFile(files.next());
					if(!mFile.isEmpty()){
						System.out.println(111);
					/*	String basePath = req.getServletContext().getRealPath("/plug-in/upload/image");
						SimpleDateFormat sFormat = new SimpleDateFormat("yyyyMMdd" );
						String dPath ="/"+sFormat.format(new Date())+"/";
						File dir = new File(basePath+dPath);
						if(!dir.exists()){
							dir.mkdirs();
						} */
					//	String basePath = "C:/ResourceData";
						SimpleDateFormat sFormat = new SimpleDateFormat("yyyyMMdd");
						String dPath = "/" + sFormat.format(new Date()) + "/";
						File dir = new File(basePath_img + dPath);
						if (!dir.exists()) {
							dir.mkdirs();
						}
						String originFileName=mFile.getOriginalFilename();
						String suffix=originFileName.split("\\.")[originFileName.split("\\.").length-1];
						String base64Name=UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
						File file =  new File(basePath_img+dPath,base64Name+"."+suffix);
						// url_path=basePath+dPath+base64Name+"."+suffix;
						 url_path=http_img+dPath+base64Name+"."+suffix;
						try {
							FileUtils.copyInputStreamToFile(mFile.getInputStream(),file);//存储文件
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
			String message = "添加成功";
			try{
				//disposalServiceI.addMain(houseDisposalEntity, wyHousePicList);
				//systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			}catch(Exception e){
				e.printStackTrace();
				message = "添加失败";

			}
			System.out.println("url_path="+url_path);
			JSONObject json = new JSONObject();
			json.put("status", "success");
			json.put("url_path",url_path);
			json.put("message", message);
			TagUtil.getSendJson(resp, json.toString());

	}

	/**
	 * 上传图片或者文件
	 * @param houseDisposalEntity
	 * @param wyHouseDisposalPage
	 * @param req
	 * @param resp
	 */
	@RequestMapping(params="uplodfil/img")
	public void uplodimg(String userid,HttpServletRequest req,HttpServletResponse resp){
		System.out.println("uplodfil/img");
		String cupath=req.getRequestURL()+"";
		String propath=req.getContextPath();
		String  t1=cupath.substring(0, cupath.indexOf(propath));
		String  t2=t1+propath;
		String url_path="";
		  CommonsMultipartResolver cmr = new CommonsMultipartResolver(req.getServletContext());
			if (cmr.isMultipart(req)) {
				MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) (req);
				Iterator<String> files = mRequest.getFileNames();

				while (files.hasNext()) {
					MultipartFile mFile = mRequest.getFile(files.next());
					if(!mFile.isEmpty()){
						System.out.println(111);
					/*	String basePath = req.getServletContext().getRealPath("/plug-in/upload/image");
						SimpleDateFormat sFormat = new SimpleDateFormat("yyyyMMdd" );
						String dPath ="/"+sFormat.format(new Date())+"/";
						File dir = new File(basePath+dPath);
						if(!dir.exists()){
							dir.mkdirs();
						} */
					//	String basePath = "C:/ResourceData";
						SimpleDateFormat sFormat = new SimpleDateFormat("yyyyMMdd");
						String dPath = "/" + sFormat.format(new Date()) + "/";
						File dir = new File(basePath_img + dPath);
						if (!dir.exists()) {
							dir.mkdirs();
						}
						String originFileName=mFile.getOriginalFilename();
						String suffix=originFileName.split("\\.")[originFileName.split("\\.").length-1];
						String base64Name=UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
						File file =  new File(basePath_img+dPath,base64Name+"."+suffix);
						// url_path=basePath+dPath+base64Name+"."+suffix;
						 url_path=http_img+dPath+base64Name+"."+suffix;
						try {
							FileUtils.copyInputStreamToFile(mFile.getInputStream(),file);//存储文件
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
			String message = "添加成功";
			try{
				//disposalServiceI.addMain(houseDisposalEntity, wyHousePicList);
				//systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			}catch(Exception e){
				e.printStackTrace();
				message = "添加失败";

			}
			System.out.println("url_path="+url_path);
			JSONObject json = new JSONObject();
			json.put("status", "success");
			json.put("url_path",url_path);
			json.put("message", message);
			TagUtil.getSendJson(resp, json.toString());
	}


	/**
	 * 上传图片或者文件
	 * @param houseDisposalEntity
	 * @param wyHouseDisposalPage
	 * @param req
	 * @param resp
	 */
	@RequestMapping(params="uplodfil/evaluateimg")
	public void evaluateimg(String userid,HttpServletRequest req,HttpServletResponse resp,NEvaluateEntity mode,String title,String detaisl,String evaluateType,String evaluatepic,String goodsId){
		System.out.println("时珊珊打印evaluateimg");
		String cupath=req.getRequestURL()+"";
		String propath=req.getContextPath();
		String  t1=cupath.substring(0, cupath.indexOf(propath));
		String  t2=t1+propath;
		String url_path="";
		  CommonsMultipartResolver cmr = new CommonsMultipartResolver(
					req.getServletContext());
			if (cmr.isMultipart(req)) {
				MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) (req);
				Iterator<String> files = mRequest.getFileNames();

				while (files.hasNext()) {
					MultipartFile mFile = mRequest.getFile(files.next());
					if(!mFile.isEmpty()){
						System.out.println(111);
					/*	String basePath = req.getServletContext().getRealPath("/plug-in/upload/image");
						SimpleDateFormat sFormat = new SimpleDateFormat("yyyyMMdd" );
						String dPath ="/"+sFormat.format(new Date())+"/";
						File dir = new File(basePath+dPath);
						if(!dir.exists()){
							dir.mkdirs();
						}
						String originFileName=mFile.getOriginalFilename();
						String suffix=originFileName.split("\\.")[originFileName.split("\\.").length-1];
						String base64Name=UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
						File file =  new File(basePath+dPath,base64Name+"."+suffix);
						 url_path+=t2+"/plug-in/upload/image"+dPath+base64Name+"."+suffix+",";*/
						SimpleDateFormat sFormat = new SimpleDateFormat("yyyyMMdd");
						String dPath = "/" + sFormat.format(new Date()) + "/";
						File dir = new File(basePath_img + dPath);
						if (!dir.exists()) {
							dir.mkdirs();
						}
						String originFileName=mFile.getOriginalFilename();
						String suffix=originFileName.split("\\.")[originFileName.split("\\.").length-1];
						String base64Name=UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
						File file =  new File(basePath_img+dPath,base64Name+"."+suffix);
						// url_path=basePath+dPath+base64Name+"."+suffix;
						 url_path=http_img+dPath+base64Name+"."+suffix;
						try {
							FileUtils.copyInputStreamToFile(mFile.getInputStream(),file);//存储文件
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					System.out.println(url_path);
				}
			}
			String message = "添加成功";
			url_path = url_path.substring(0, url_path.length()-1);
			try{
				//disposalServiceI.addMain(houseDisposalEntity, wyHousePicList);
				//systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			}catch(Exception e){
				e.printStackTrace();
				message = "添加失败";

			}
			JSONObject object = new JSONObject();
			/**评价类别 n_evaluate_type*/
			String evaluateid=UUID.randomUUID().toString();
			List<NEvaluateTypeEntity> nevaluatetype_list = systemService.loadAll(NEvaluateTypeEntity.class);
			for(int i=0; i<nevaluatetype_list.size(); i++) {
				NEvaluateTypeEntity nevaluatetype=nevaluatetype_list.get(i);
				String typename=nevaluatetype.getTypename();
				if(!"有图".equals(typename) && !"追加".equals(typename) ){
					List<String> list = systemService.findListbySql("select evaluateid from n_evaluate where goods_id='"+goodsId+"' and typename='"+typename+"'");
					if(detaisl.contains(typename) && list.size()<1){
						NEvaluateColumEntity nEvaluateColum=new NEvaluateColumEntity();
						nEvaluateColum.setCreateDate(new Date());
						nEvaluateColum.setGoodsId(goodsId);
						nEvaluateColum.setTitle(title);
						nEvaluateColum.setTypename(typename);
						nEvaluateColum.setEvaluateid(evaluateid);
						systemService.save(nEvaluateColum);
					}else{
						evaluateid=list.get(0);
					}
				}
			}
			if(!StringUtil.is_khEmpty(url_path)){
				NEvaluateColumEntity nEvaluateColum=new NEvaluateColumEntity();
				nEvaluateColum.setCreateDate(new Date());
				nEvaluateColum.setGoodsId(goodsId);
				nEvaluateColum.setTitle(title);
				nEvaluateColum.setTypename("有图");
				nEvaluateColum.setEvaluateid(evaluateid);
				systemService.save(nEvaluateColum);
			}
			//0,正常1追加
			if("1".equals(evaluateType)){
				NEvaluateColumEntity nEvaluateColum=new NEvaluateColumEntity();
				nEvaluateColum.setCreateDate(new Date());
				nEvaluateColum.setGoodsId(goodsId);
				nEvaluateColum.setTitle(title);
				nEvaluateColum.setTypename("追加");
				nEvaluateColum.setEvaluateid(evaluateid);
				systemService.save(nEvaluateColum);
			}
			mode.setCreateDate(new Date());
			mode.setEvaluateStatus("0");//0正常1删除
			mode.setEvaluateid(evaluateid);
			mode.setEvaluatepic(url_path);
			systemService.save(mode);
			object.put("status", "success");
			object.put("message", "评论成功");
			TagUtil.getSendJson(resp, object.toString());

	}

	/**
	 * 上传图片或者文件
	 * @param houseDisposalEntity
	 * @param wyHouseDisposalPage
	 * @param req
	 * @param resp
	 */
	@RequestMapping(params="uplodfil/file")
	public void uplodfile(String userid,HttpServletRequest req,HttpServletResponse resp){
		System.out.println("时珊珊打印uplodfil/file");
		String cupath=req.getRequestURL()+"";
		String propath=req.getContextPath();
		String  t1=cupath.substring(0, cupath.indexOf(propath));
		String  t2=t1+propath;
		String url_path="";
		  CommonsMultipartResolver cmr = new CommonsMultipartResolver(
					req.getServletContext());
			if (cmr.isMultipart(req)) {
				MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) (req);
				Iterator<String> files = mRequest.getFileNames();

				while (files.hasNext()) {
					MultipartFile mFile = mRequest.getFile(files.next());
					if(!mFile.isEmpty()){
					/*	String basePath = req.getServletContext().getRealPath("/plug-in/upload/file");
						SimpleDateFormat sFormat = new SimpleDateFormat("yyyyMMdd" );
						String dPath ="/"+sFormat.format(new Date())+"/";
						File dir = new File(basePath+dPath);
						if(!dir.exists()){
							dir.mkdirs();
						}
						String originFileName=mFile.getOriginalFilename();
						String suffix=originFileName.split("\\.")[originFileName.split("\\.").length-1];
						String base64Name=UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
						File file =  new File(basePath+dPath,base64Name+"."+suffix);
						 url_path=t2+"/plug-in/upload/file"+dPath+base64Name+"."+suffix;*/
						SimpleDateFormat sFormat = new SimpleDateFormat("yyyyMMdd");
						String dPath = "/" + sFormat.format(new Date()) + "/";
						File dir = new File(basePath_file+ dPath);
						if (!dir.exists()) {
							dir.mkdirs();
						}
						String originFileName=mFile.getOriginalFilename();
						String suffix=originFileName.split("\\.")[originFileName.split("\\.").length-1];
						String base64Name=UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
						File file =  new File(basePath_file+dPath,base64Name+"."+suffix);
						// url_path=basePath+dPath+base64Name+"."+suffix;
						 url_path=http_file+dPath+base64Name+"."+suffix;
						try {
							FileUtils.copyInputStreamToFile(mFile.getInputStream(),file);//存储文件
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
			String message = "添加成功";
			try{
				//disposalServiceI.addMain(houseDisposalEntity, wyHousePicList);
				//systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			}catch(Exception e){
				e.printStackTrace();
				message = "添加失败";

			}
			System.out.println("url_path="+url_path);
			JSONObject json = new JSONObject();
			json.put("status", "success");
			json.put("url_path",url_path);
			json.put("message", message);
			TagUtil.getSendJson(resp, json.toString());

	}
	/**
	 * 数字字典
	 * @param req
	 * @param resp
	 */
	@RequestMapping(params="dictionary")
	public void dictionary(String table,String typegroupcode,HttpServletRequest req,HttpServletResponse resp){
		TSTypegroup mode = systemService.findUniqueByProperty(TSTypegroup.class, "typegroupcode", typegroupcode);
		JSONObject json = new JSONObject();
		JSONArray jsonarray=new JSONArray();
		if(mode!=null){
			System.out.println("mode="+mode.getTypegroupname());
			List<TSType> TSTypes=mode.getTSTypes();

			for(int i=0;i<TSTypes.size();i++){
				TSType tstype=TSTypes.get(i);
				System.out.println(tstype.getTypecode()+"=="+tstype.getTypename());
				//JSONObject obj = new JSONObject();
				json.put(tstype.getTypecode(), tstype.getTypename());
			//	jsonarray.add(obj);
			}

		}
		json.put("content", jsonarray);
		json.put("status", "success");
		TagUtil.getSendJson(resp, json.toString());
	}
	/**
	 * 数字字典
	 * @param req
	 * @param resp
	 */
	@RequestMapping(params="dictionarray")
	public void dictionarray(String table,String typegroupcode,HttpServletRequest req,HttpServletResponse resp){
		TSTypegroup mode = systemService.findUniqueByProperty(TSTypegroup.class, "typegroupcode", typegroupcode);
		JSONObject json = new JSONObject();
		JSONArray jsonarray=new JSONArray();
		if(mode!=null){
			System.out.println("mode="+mode.getTypegroupname());
			List<TSType> TSTypes=mode.getTSTypes();

			for(int i=0;i<TSTypes.size();i++){
				TSType tstype=TSTypes.get(i);
				System.out.println(tstype.getTypecode()+"=="+tstype.getTypename());
				JSONObject obj = new JSONObject();
				obj.put("code",tstype.getTypecode());
				obj.put("codename",tstype.getTypename());
				jsonarray.add(obj);
			}

		}
		json.put("content", jsonarray);
		json.put("status", "success");
		TagUtil.getSendJson(resp, json.toString());
	}
	/**
	 * 其他表
	 * @param req
	 * @param resp
	 */
	@RequestMapping(params="dictable")
	public void dictable(String table,String ids,HttpServletRequest req,HttpServletResponse resp){
		JSONObject json = new JSONObject();
		JSONArray jsonarray=new JSONArray();
	//	String[] str=ids.split(ids);
		String sql="select "+ids+" from "+table;
		List<Object[]> mode_list = systemService.findListbySql(sql.toString());
			for(int i=0;i<mode_list.size();i++){
				Object[] mode=mode_list.get(i);
				System.out.println(mode[0]+"=="+mode[1]);
				JSONObject obj = new JSONObject();
				obj.put((String) mode[0], mode[1]);
				jsonarray.add(obj);
			}

		json.put("content", jsonarray);
		TagUtil.getSendJson(resp, json.toString());
	}
}
