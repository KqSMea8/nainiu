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
				<div class="panel-heading">编辑</div>
				<div class="panel-body">
					<form class="form-horizontal" role="form" id="dailogForm" action="weixinPayController.do?doAdd" method="POST">
						<input id="id" name="id" type="hidden" value="${weixinPayPage.id }" />
						<input id="createName" name="createName" type="hidden" value="${weixinPayPage.createName }" />
						<input id="createBy" name="createBy" type="hidden" value="${weixinPayPage.createBy }" />
						<input id="updateName" name="updateName" type="hidden" value="${weixinPayPage.updateName }" />
						<input id="updateBy" name="updateBy" type="hidden" value="${weixinPayPage.updateBy }" />
						<input id="updateDate" name="updateDate" type="hidden" value="${weixinPayPage.updateDate }" />
						<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${weixinPayPage.sysOrgCode }" />
						<input id="remarks" name="remarks" type="hidden" value="${weixinPayPage.remarks }" />
						<input id="delFlag" name="delFlag" type="hidden" value="${weixinPayPage.delFlag }" />
						<input id="bpmStatus" name="bpmStatus" type="hidden" value="${weixinPayPage.bpmStatus }" />
						<input id="createDate" name="createDate" type="hidden" value="${weixinPayPage.createDate }" />
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">标题:</label>
							<div class="col-sm-4" style="width:60%;" >
							<input  type="text" id="title" name="title" class="form-control" datatype="*1-300" value="${weixinPayPage.title }" />
							<span class="Validform_checktip"></span>
							</div>
						</div>
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">开发者ID:</label>
							<div class="col-sm-4" style="width:60%;" >
							<input  type="text" id="appid" name="appid" class="form-control" datatype="*1-300" value="${weixinPayPage.appid }" />
							<span class="Validform_checktip"></span>
							</div>
						</div>
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">开发者密码appsecret:</label>
							<div class="col-sm-4" style="width:60%;" >
							<input  type="text" id="appsecret" name="appsecret" class="form-control" datatype="*1-300" value="${weixinPayPage.appsecret }" />
							<span class="Validform_checktip"></span>
							</div>
						</div>
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">商户号partner:</label>
							<div class="col-sm-4" style="width:60%;" >
							<input  type="text" id="partner" name="partner" class="form-control" datatype="*1-300" value="${weixinPayPage.partner }" />
							<span class="Validform_checktip"></span>
							</div>
						</div>
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">微信商户平台api安全partnerkey:</label>
							<div class="col-sm-4" style="width:60%;" >
							<input  type="text" id="partnerkey" name="partnerkey" class="form-control" datatype="*1-300" value="${weixinPayPage.partnerkey }" />
							<span class="Validform_checktip"></span>
							</div>
						</div>
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">微信通知地址:</label>
							<div class="col-sm-4" style="width:60%;" >
							<input  type="text" id="notifyurl" name="notifyurl" class="form-control" datatype="*1-300" value="${weixinPayPage.notifyurl }" />
							<span class="Validform_checktip"></span>
							</div>
						</div>
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">公众号或者app:</label>
							<div class="col-sm-4" style="width:60%;" >
							<t:dictSelect field="wxpaytype" id="wxpaytype" type="list"
							    typeGroupCode="wxpaytype" defaultVal="${weixinPayPage.wxpaytype}" hasLabel="false" 
							    title="wxpaytype"  datatype="*">
							</t:dictSelect> 
							<span class="Validform_checktip"></span>
							</div>
						</div>
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">是否生效:</label>
							<div class="col-sm-4" style="width:60%;" >
							<t:dictSelect field="wxpaystatus" id="wxpaystatus" type="list"
							    typeGroupCode="wxpaystatus" defaultVal="${weixinPayPage.wxpaystatus}" hasLabel="false" 
							    title="wxpaystatus"  datatype="*">
							</t:dictSelect> 
							<span class="Validform_checktip"></span>
							</div>
						</div>
										
									<div class="form-group mno">
											<div class="col-sm-offset-1 col-sm-6">
												<button type="button" class="btn btn-default" id="formReturn" data-dismiss="modal" onclick="doUrl('weixinPayController.do?list')">返回</button>
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
function	selectChange(){
	var selectvalue=document.getElementById("priceStatu").value;
	//var selectvalue=$("#standard").find("option:selected").text(); 
	console.log(selectvalue);
	if(selectvalue=="0"){
		 document.getElementById("pricetype").style.display="none";//隐藏
	}else if(selectvalue=="1"){
		 document.getElementById("pricetype").style.display="block";//显示
	}
}
</script> 
