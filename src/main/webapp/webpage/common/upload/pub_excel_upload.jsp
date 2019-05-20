<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>通用Excel导入${controller_name}</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
<t:formvalid formid="formobj" dialog="true"  layout="table" action="${controller_name}.do?${paramValue}" >

<%-- <t:formvalid formid="formobj" layout="div" dialog="true" beforeSubmit="upload" action="${controller_name}.do?importExcel">
--%>
  <script type="text/javascript" src="plug-in/uploadifive/jquery-1.8.0.min.js"></script>
						<script type="text/javascript" src="plug-in/uploadifive/jquery.uploadifive.min.js"></script>
						<link rel="stylesheet" type="text/css" href="plug-in/uploadifive/uploadifive.css">
						<script type="text/javascript">
						$(function(){
							 $('#picUrl_pic').uploadifive({
								 //'auto' : false,   //取消自动上传 
						        'uploadScript' : 'plug-in/ueditor/jsp/controller.jsp?action=uploadfile',  //处理上传文件Action路径
						        'fileObjName' : 'file',        //文件对象
							    'buttonText'   : '选择文件',   //按钮显示文字 
							    'queueID'      : 'tip-queue', //提示信息放置目标 
							    'fileType'     : 'file',   //允许上传文件类型 
							  /*   'uploadLimit'   : 1,
						        'removeTimeout' : 0, */
						        'queueSizeLimit' : 1,
							    'onUploadComplete' : function(file, data) { //文件上传成功后执行 
									 console.info('The file ' + file.name + ' uploaded successfully.'+data.url);
									    var obj = eval('(' + data + ')');
									    console.info(obj.url);
									    var pic_url=obj.url;
									
									    $("#picUrl").attr("value",pic_url);
						       }
						    });
						})
							</script>
							
						<input id="picUrl" name="picUrl" type="hidden" value=""/>
					
					<input type="file" name="picUrl_pic" id="picUrl_pic">
				
					<div id="tip-queue"></div>
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">图片</label>
</t:formvalid>
</body>
</html>
