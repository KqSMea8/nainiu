<!DOCTYPE html>
<!-- saved from url=(0056)http://localhost:8180/jeecg/demo/jpDemoOrderMain.do?list -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<html lang="en">

	<head>
		<title>订单导入</title>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<t:base type="jquery,tools"></t:base>
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
		<script src="webpage/com/jeecg/n_merchant/add.js"></script>
		<script src="webpage/com/jeecg/n_merchant/fileupload.js"></script>

	</head>

	<body style='overflow:scroll;overflow-x:hidden'>
		<div class="container bs-docs-container" style="width:100%;">
			<div class="row">
				<div class="panel panel-default">
					<div class="panel-heading">订单导入</div>
					<div class="panel-body">
						<form class="form-horizontal" role="form" id="dailogForm" action="nOrderImportxlsController.do?doUpdate" method="POST">
							<input id="id" name="id" type="hidden" value="${nOrderImportxlsPage.id }" />
							<div class="panel-heading" style="background-color: #f5f5f5;margin-bottom:15px;margin-top:15px;">店铺基本信息</div>
							
							<div class="form-group mno">
								<label  class="col-sm-2 control-label" style="text-align:center;">订单号:</label>
								<div class="col-sm-4">
									<input  type="text" id="norderid" name="norderid" readonly class="form-control" value="${nOrderImportxlsPage.norderid }" />
									<span class="Validform_checktip"></span>
								</div>
								
								
							</div>
							
							<div class="panel-heading" style="background-color: #f5f5f5;margin-bottom:15px;margin-top:15px;">
							快递信息(快递信息修改即已发货状态)
							</div>
							<div class="form-group mno">
								
								<label  class="col-sm-2 control-label" style="text-align:center;">快递单号:</label>
								<div class="col-sm-4">
									<input  type="text" id="expressNub" name="expressNub" class="form-control" datatype="*1-100"
									value="${nOrderImportxlsPage.expressNub }" />
									<span class="Validform_checktip"></span>
								</div>
								<label  class="col-sm-2 control-label" style="text-align:center;">快递名称:</label>
								<div class="col-sm-4">
									<t:dictSelect id="expressCode" field="expressCode" type="list"
									    typeGroupCode="expressName" defaultVal="${nOrderImportxlsPage.expressCode}" hasLabel="false" 
									    title="expressCode"  datatype="*">
									</t:dictSelect> 
									<span class="Validform_checktip"></span>
								</div>
							</div>
							<div class="form-group mno">
								<div class="col-sm-offset-1 col-sm-6">
									<button type="button" class="btn btn-default" id="formReturn" data-dismiss="modal" onclick="doUrl('nOrderImportxlsController.do?list')">返回</button>
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
 /*  if(location.href.indexOf("load=detail")!=-1){
		$("#formSubmit").hide();
		 $('#formReturn').removeAttr("disabled");
		//setTimeout(hidedisabled(),10000);
		
	}*/
 
});

</script>