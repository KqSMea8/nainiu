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
					<form class="form-horizontal" role="form" id="dailogForm" action="nMarketingMemberController.do?doUpdate" method="POST">
						<input id="id" name="id" type="hidden" value="${nMarketingMemberPage.id }" />
						<input id="createName" name="createName" type="hidden" value="${nMarketingMemberPage.createName }" />
						<input id="createBy" name="createBy" type="hidden" value="${nMarketingMemberPage.createBy }" />
						<input id="updateName" name="updateName" type="hidden" value="${nMarketingMemberPage.updateName }" />
						<input id="updateBy" name="updateBy" type="hidden" value="${nMarketingMemberPage.updateBy }" />
						<input id="updateDate" name="updateDate" type="hidden" value="${nMarketingMemberPage.updateDate }" />
						<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${nMarketingMemberPage.sysOrgCode }" />
						<input id="remarks" name="remarks" type="hidden" value="${nMarketingMemberPage.remarks }" />
						<input id="delFlag" name="delFlag" type="hidden" value="${nMarketingMemberPage.delFlag }" />
						<input id="bpmStatus" name="bpmStatus" type="hidden" value="${nMarketingMemberPage.bpmStatus }" />
						<input id="createDate" name="createDate" type="hidden" value="${nMarketingMemberPage.createDate }" />
						<input id="aduitnumb" name="aduitnumb" type="hidden" value="${nMarketingMemberPage.aduitnumb }" />
						<input id="joinnumb" name="joinnumb" type="hidden" value="${nMarketingMemberPage.joinnumb }" />
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">活动名称:</label>
							<div class="col-sm-4" style="width:60%;" >
							<input  type="text" id="title" name="title" class="form-control" datatype="*1-100" value="${nMarketingMemberPage.title }" />
							<span class="Validform_checktip"></span>
							</div>
						</div>
						<!--
						<div class="form-group mno" >
							<label  class="col-sm-2 control-label" style="text-align:center;">统一价格:</label>
							<div class="col-sm-4" style="width:195px;margin-bottom: 20px;"  >
							   <select name="priceStatu" id="priceStatu" class="form-control" onchange="Javascript:selectChange();">
							    <option value="0">否</option>
							    <option value="1">是</option>
								</select>
								<span class="Validform_checktip"></span>
							</div>
							<div id="pricetype" style="display: none;">
								<label  class="col-sm-2 control-label" style="text-align:center;" >价格:</label>
								<div class="col-sm-4" style="width:195px;margin-bottom: 20px;" >
									<input  type="text" id="price" name="price" class="form-control" datatype="n" value="${nMarketingMemberPage.price }" />
									<span class="Validform_checktip"></span>
								</div>
							</div>
						</div>
						-->
						<div class="form-group mno">
						    <label for="inputEmail3" class="col-sm-2 control-label" style="text-align:center;">开始时间:</label>
						    <div class="col-sm-4" style="width:195px;margin-bottom: 20px;">
						      <input type="text"   value='${nMarketingMemberPage.startTime}' name="startTime" id="startTime" class="form-control" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" datatype="*" style="background: url('plug-in/ui/images/datetime.png') no-repeat scroll right center transparent;"/>
						    </div>
						    <label for="inputEmail3" class="col-sm-2 control-label" style="text-align:center;">结束时间:</label>
						    <div class="col-sm-4"  style="width:195px;margin-bottom: 20px;">
						      <input type="text"  value='${nMarketingMemberPage.endTime}' name="endTime" id="endTime" class="form-control" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" datatype="*" style="background: url('plug-in/ui/images/datetime.png') no-repeat scroll right center transparent;"/>
						    </div>
						</div>
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;" >排列顺序:</label>
							<div class="col-sm-4" style="width:195px;margin-bottom: 20px;" >
								<input  type="text" id="orders" name="orders" class="form-control" datatype="n" value="${nMarketingMemberPage.orders }" />
								<span class="Validform_checktip"></span>
							</div>
						</div>
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">活动规则 :</label>
							<div class="col-sm-4" style="width:60%;" >
							<textarea placeholder="建议300字以内" id="details" name="details"  class="form-control" style="height:100px;" datatype="*1-300" >${nMarketingMemberPage.details }</textarea>
							<span class="Validform_checktip"></span>
							</div>
						</div>
										
									<div class="form-group mno">
											<div class="col-sm-offset-1 col-sm-6">
												<button type="button" class="btn btn-default" id="formReturn" data-dismiss="modal" onclick="history.go(-1)">返回</button>
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
$(function(){
/*	var priceStatu="${nMarketingMemberPage.priceStatu }";
	$("#priceStatu").val(priceStatu);  
	if(priceStatu=="0"){
		 document.getElementById("pricetype").style.display="none";//隐藏
	}else if(priceStatu=="1"){
		 document.getElementById("pricetype").style.display="block";//显示
	}*/
	   if(location.href.indexOf("load=detail")!=-1){
			$("#formSubmit").hide();
			 $('#formReturn').removeAttr("disabled");
			
		}
})
</script> 
