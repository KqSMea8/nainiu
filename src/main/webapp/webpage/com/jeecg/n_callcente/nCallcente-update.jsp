<!DOCTYPE html>
<!-- saved from url=(0056)http://localhost:8180/jeecg/demo/jpDemoOrderMain.do?list -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<html lang="en">
<head>
<title>客服账号添加</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<t:base type="jquery,easyui,tools"></t:base>
<!-- ztree -->
<link rel="stylesheet" type="text/css" href="plug-in/ztree/css/zTreeStyle.css"></link>
<script type="text/javascript" src="plug-in/ztree/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="plug-in/ztree/js/jquery.ztree.excheck-3.5.min.js"></script>
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
<link rel="stylesheet" href="plug-in/tools/css/metrole/common.css">
<script type="text/javascript" src="plug-in/lhgDialog/lhgdialog.min.js?skin=metrole"></script>

</head>
<body style='overflow:scroll;overflow-x:hidden'>
	<div class="container bs-docs-container" style="width:100%;">
		<div class="row">
			<div class="panel panel-default">
				<div class="panel-heading">客服账号添加</div>
				<div class="panel-body">
					<form class="form-horizontal" role="form" id="dailogForm" action="nCallcenteController.do?doUpdate" method="POST">
					<input id="id" name="id" type="hidden" value="${nCallcentePage.id }">
					<input id="createName" name="createName" type="hidden" value="${nCallcentePage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${nCallcentePage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${nCallcentePage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${nCallcentePage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${nCallcentePage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${nCallcentePage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${nCallcentePage.sysOrgCode }">
					<input id="bpmStatus" name="bpmStatus" type="hidden" value="${nCallcentePage.bpmStatus }">
					<input id="callstatus" name="callstatus" type="hidden" value="${nCallcentePage.callstatus }">

						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">账号:</label>
							<div class="col-sm-4" style="width:60%;" >
								   <input  type="text" id="account" name="account" class="form-control" datatype="*1-40" value="${nCallcentePage.account }" />
								<span class="Validform_checktip"></span>
							</div>
						</div>
					
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;" >密码:</label>
							<div class="col-sm-4" style="width:60%;" >
							   <input  type="text" id="password" name="password" class="form-control" datatype="*1-40" value="${nCallcentePage.password }" />
								<span class="Validform_checktip"></span>
							</div>
						</div>
						<div class="form-group mno">
								<div class="col-sm-offset-1 col-sm-6">
									<button type="button" class="btn btn-default" id="formReturn" data-dismiss="modal" onclick="doUrl('nCallcenteController.do?list')">返回</button>
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
<script type="text/javascript" >

$(function(){

	   if(location.href.indexOf("load=detail")!=-1){
			$("#formSubmit").hide();
			 $('#formReturn').removeAttr("disabled");
		}
	 
})
</script> 
