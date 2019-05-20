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
				<div class="panel-heading">客服电话</div>
				<div class="panel-body">
					<form class="form-horizontal" role="form" id="dailogForm" action="nMerchantController.do?doUpdate_tel" method="POST">
					<input id="id" name="id" type="hidden" value="${nMerchantPage.id }">
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">客服电话(手机或者固话):</label>
							  <input   id="tel_tel" name="tel_tel" type="hidden" class="form-control" value=""  datatype="*" />
							<div class="col-sm-4" style="width:60%;" >
							   <input  type="text" id="tel" name="tel" class="form-control" value="${nMerchantPage.tel }"   datatype="*"/>
								<span class="Validform_checktip"></span>
							</div>
						</div>
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">聊天编码sysNum:</label>
							<div class="col-sm-4" style="width:60%;" >
							   <input  type="text" id="sysNum" name="sysNum" class="form-control" value="${nMerchantPage.sysNum }"   datatype="*"/>
								<span class="Validform_checktip"></span>
							</div>
						</div>
							<div class="form-group mno">
									<div class="col-sm-offset-1 col-sm-6">
										<button type="button" class="btn btn-default" id="formReturn" data-dismiss="modal" onclick="doUrl('nMerchantController.do?list_tel')">返回</button>
    									<button type="button" class="btn btn-primary"  onclick="geSpan()" id="formSubmit">提交</button>
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
      var myregphone =/^1(2|3|4|5|6|7|8|9)\d{9}$/;
	   var myregtel =/^0\d{2,3}-?\d{7,8}$/;
		function geSpan(){
			var phone=$.trim(document.getElementById("tel").value);
			//alert(myregphone.test(phone)  +"="+myregtel.test(phone) );
			if(myregphone.test(phone) || myregtel.test(phone) ){
				document.getElementById("tel_tel").value=phone;
			}else{
			   alert("电话格式不正确");
			}
		}
</script> 
