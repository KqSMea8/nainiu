<!DOCTYPE html>
<!-- saved from url=(0056)http://localhost:8180/jeecg/demo/jpDemoOrderMain.do?list -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<html lang="en">
<head>
<title>件开发平台</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<t:base type="jquery,easyui,tools"></t:base>
<link href="plug-in/ui/css/bootstrap.css" rel="stylesheet">
<link href="plug-in/ui/css/style.css" rel="stylesheet">
<link href="plug-in/ui/css/zTreeStyle/zTreeStyle.css" type="text/css" rel="stylesheet">
<link href="plug-in/ui/css/mycss/kongjian.css" rel="stylesheet">
<link rel="stylesheet" href="plug-in/ui/hplus/css/font-awesome.css">
<script type="text/javascript" src="plug-in/ui/js/iFrameResize.js"></script>
<script type="text/javascript" src="plug-in/ui/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="plug-in/ui/js/respond.min.js"></script>
<script type="text/javascript" src="plug-in/ui/js/bootstrap.js"></script>
<script type="text/javascript" src="plug-in/ui/js/common.js"></script>
<script type="text/javascript" src="plug-in/ui/js/jquery.form.js"></script>
<script type="text/javascript" src="plug-in/ui/js/scrollNav.js"></script>
<script type="text/javascript" src="plug-in/ui/js/My97DatePicker/WdatePicker.js" language="javascript"></script>
<link href="plug-in/ui/js/My97DatePicker/skin/WdatePicker.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="plug-in/ui/layer/layer.js" language="javascript"></script>
<link rel="stylesheet" href="plug-in/ui/layer/skin/layer.css" id="layui_layer_skinlayercss">
<link rel="stylesheet" href="plug-in/tools/css/metrole/common.css" >
<script src = "webpage/com/jeecg/ce/add.js"></script>	
<script type="text/javascript" src="plug-in/lhgDialog/lhgdialog.min.js?skin=metrole"></script>
<script type="text/javascript" src="plug-in/tools/syUtil.js"></script>

</head>
<body style='overflow:scroll;overflow-x:hidden'>
	<div class="container bs-docs-container" style="width:100%;">
		<div class="row">
			<div class="panel panel-default">
				<div class="panel-heading">编辑</div>
				<div class="panel-body">
					<form class="form-horizontal" role="form" id="dailogForm" action="ceController.do?doAdd" method="POST">
					<input id="id" name="id" type="hidden" value="${cePage.id }"/>
					<input id="createName" name="createName" type="hidden" value="${cePage.createName }"/>
					<input id="createBy" name="createBy" type="hidden" value="${cePage.createBy }"/>
					<input id="createDate" name="createDate" type="hidden" value="${cePage.createDate }"/>
					<input id="updateName" name="updateName" type="hidden" value="${cePage.updateName }"/>
					<input id="updateBy" name="updateBy" type="hidden" value="${cePage.updateBy }"/>
					<input id="updateDate" name="updateDate" type="hidden" value="${cePage.updateDate }"/>
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${cePage.sysOrgCode }"/>
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${cePage.sysCompanyCode }"/>
					<input id="bpmStatus" name="bpmStatus" type="hidden" value="${cePage.bpmStatus }"/>
											
											<div class="form-group mno">
													<label for="inputEmail3" class="col-sm-2 control-label" style="text-align:center;">顾客类型</label>
													<div class="col-sm-4">
													<t:dictSelect field="ce" type="list"
													typeGroupCode="education" defaultVal="${cePage.ce}" hasLabel="false"  title="ce"  datatype="*" 
													></t:dictSelect>   
													</div>
													<label for="inputEmail3" class="col-sm-2 control-label" style="text-align:center;">联系人</label>
													<div class="col-sm-4">
														
														<input id="userid1" name="userid" type="hidden" >
												     	<input readonly="readonly" type="text" id="auditperson1" 
												     	name="auditperson1" class="form-control"
												     	datatype="*"  onclick="openUserSelect('userid1','auditperson1')"  />  
													<span class="Validform_checktip"></span>
													</div>
											</div>
												<div class="form-group mno">
												    <label for="inputEmail3" class="col-sm-2 control-label" style="text-align:center;">timeT</label>
												    <div class="col-sm-4">
												      <input type="text" value='${cePage.timeT}' name="timeT" id="timeT" class="form-control" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" datatype="*" style="background: url('plug-in/ui/images/datetime.png') no-repeat scroll right center transparent;"/>
												    </div>
												    
												</div>
												<div class="form-group mno">
											    <label  class="col-sm-2 control-label" style="text-align:center;">图片</label>
											    <div class="col-sm-4">
												    <script type="text/javascript" src="plug-in/ui/js/fileupload.js"></script>
												    <a id="apicUrl" name="apicUrl"   target="_blank"  style="display: none;" >预览</a>
												       &nbsp;&nbsp;&nbsp;&nbsp;
												    <a id="apicUrldelet" name="apicUrldelet"  href="javascript:del_pic()"  style="display: none;">删除</a>
												  
												    <a id="apicUrlwu" href="javascript:;" >无</a>
												   
										     		<input  id="picUrl" name="picUrl" type="hidden" value=""> </a>
										     		</br>
													<a href="javascript:;" style="padding: 4px 10px;line-height: 20px;position: relative;cursor: pointer; color: #454343;border: 1px solid #ddd; border-radius: 4px; overflow: hidden; display: inline-block;">		
														选择文件
														<input type="file" name="file" id="file" onchange="Javascript:checkFileChange('30','30000','30000','file');"style="position: absolute; font-size: 100px; right: 0;  top: 0; opacity: 0;cursor: pointer; width: 100%;  height: 100%;">
													</a>
										
											    </div>
											    
											</div>
												<div class="form-group mno">
											    <label for="inputEmail3" class="col-sm-2 control-label" style="text-align:center;">入口地址</label>
											    <div class="col-sm-8">
												<textarea name="content" id="content" style="width: 650px;height:300px">${cePage.content}</textarea>
											    </div>
											    <script type="text/javascript"  charset="utf-8" src="plug-in/ueditor/ueditor.config.js"></script>
												<script type="text/javascript"  charset="utf-8" src="plug-in/ueditor/ueditor.all.js"></script>
												 <script type="text/javascript">
											        var editor = UE.getEditor('content');
											    </script>
											</div>
									<div class="form-group mno">
											<div class="col-sm-offset-1 col-sm-6">
												<button type="button" class="btn btn-default" id="formReturn" data-dismiss="modal" onclick="doUrl('ceController.do?index')">返回</button>
	        									<button type="button" class="btn btn-primary" id="formSubmit">提交</button>
        									 </div>
        							</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript" src="plug-in/ui/js/Validform_v5.3.2.js"></script> 
<script type="text/javascript" src="plug-in/ui/js/forminit.p3.js"></script>  
