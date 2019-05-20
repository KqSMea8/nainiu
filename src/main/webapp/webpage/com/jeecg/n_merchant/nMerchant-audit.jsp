<!DOCTYPE html>
<!-- saved from url=(0056)http://localhost:8180/jeecg/demo/jpDemoOrderMain.do?list -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<html lang="en">

	<head>
		<title></title>
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
		<!--<script src="webpage/com/jeecg/n_merchant/add.js"></script>
		-->
		<script src="webpage/com/jeecg/n_merchant/fileupload.js"></script>

	</head>

	<body style='overflow:scroll;overflow-x:hidden'>
		<div class="container bs-docs-container" style="width:100%;">
			<div class="row">
				<div class="panel panel-default">
					<div class="panel-heading">编辑</div>
					<div class="panel-body">
						<form class="form-horizontal" role="form" id="dailogForm" action="nMerchantController.do?doAudit" method="POST">
							<input id="id" name="id" type="hidden" value="${nMerchantPage.id }" />
							<input id="createName" name="createName" type="hidden" value="${nMerchantPage.createName }" />
							<input id="createBy" name="createBy" type="hidden" value="${nMerchantPage.createBy }" />
							<input id="updateName" name="updateName" type="hidden" value="${nMerchantPage.updateName }" />
							<input id="updateBy" name="updateBy" type="hidden" value="${nMerchantPage.updateBy }" />
							<input id="updateDate" name="updateDate" type="hidden" value="${nMerchantPage.updateDate }" />
							<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${nMerchantPage.sysOrgCode }" />
							<input id="remarks" name="remarks" type="hidden" value="${nMerchantPage.remarks }" />
							<input id="delFlag" name="delFlag" type="hidden" value="${nMerchantPage.delFlag }" />
							<input id="bpmStatus" name="bpmStatus" type="hidden" value="${nMerchantPage.bpmStatus }" />
							<input id="createDate" name="createDate" type="hidden" value="${nMerchantPage.createDate }" />
							<div class="panel-heading" style="background-color: #f5f5f5;margin-bottom:15px;margin-top:15px;">店铺基本信息</div>
							
							<div class="form-group mno">
								<label  class="col-sm-2 control-label" style="text-align:center;">店铺名称:</label>
								<div class="col-sm-4">
									<input  type="text" id="company" name="company" class="form-control" datatype="*6-300" value="${nMerchantPage.company }" />
									<span class="Validform_checktip"></span>
								</div>
								<label  class="col-sm-2 control-label" style="text-align:center;">店铺logo</label>
								<div class="col-sm-4">
								<img style="width: 109px;height: 109px; overflow: hidden;" id="merchantlogoimg"
								src="${nMerchantPage.merchantlogo}">
								<a id="merchantlogoUrl"  target="_blank"  href="${nMerchantPage.merchantlogo}">预览</a>

								</div>
							</div>
							<div class="form-group mno">
								<label  class="col-sm-2 control-label" style="text-align:center;">店铺详情:</label>
								<div class="col-sm-4">
								<input  type="text" id="details" name="details" class="form-control" datatype="*6-300" value="${nMerchantPage.details }" />
								<span class="Validform_checktip"></span>
								</div>
								
							</div>
							<div class="form-group mno">
								<label  class="col-sm-2 control-label" style="text-align:center;">主营类目:</label>
								<div class="col-sm-4">
								<input id="jingyingname" name="jingyingname" type="hidden" value="${nMerchantPage.jingyingname }">
								<select name="jingying" id="jingying" datatype="*" onchange="Javascript:selectChange()"> 
							
								</select>
										<span class="Validform_checktip"></span>
									
								</div>
								<label  class="col-sm-2 control-label" style="text-align:center;">第三方平台店铺:</label>
								<div class="col-sm-4">
								<t:dictSelect field="shifou" type="list"
								    typeGroupCode="shifou" defaultVal="${nMerchantPage.shifou}" hasLabel="false" 
								    title="shifou"  datatype="*">
								</t:dictSelect> 
								</div>
							</div>
							<div class="panel-heading" style="background-color: #f5f5f5;margin-bottom:15px;margin-top:15px;">入驻人基本信息</div>
							
							<div class="form-group mno">
								<label  class="col-sm-2 control-label" style="text-align:center;">入驻姓名:</label>
								<div class="col-sm-4">
								<input  type="text" id="joinname" name="joinname" class="form-control" datatype="s1-10" value="${nMerchantPage.joinname }" />
								<span class="Validform_checktip"></span>
								</div>
								<label  class="col-sm-2 control-label" style="text-align:center;">入驻邮箱:</label>
								<div class="col-sm-4">
								<input  type="text" id="email" name="email" class="form-control" datatype="e" value="${nMerchantPage.email }" />
								<span class="Validform_checktip"></span>
								</div>
							</div>
							<div class="form-group mno">
								<label  class="col-sm-2 control-label" style="text-align:center;">入驻手机号:</label>
								<div class="col-sm-4">
								<input  type="text" id="phone" name="phone" class="form-control" datatype="m" value="${nMerchantPage.phone }" />
								<span class="Validform_checktip"></span>
								</div>
								<label  class="col-sm-2 control-label" style="text-align:center;">身份证号:</label>
								<div class="col-sm-4">
								<input  type="text" id="card" name="card" class="form-control" datatype="s15-18" value="${nMerchantPage.card }" />
								<span class="Validform_checktip"></span>
								</div>
							</div>
							<div class="form-group mno">
								<label  class="col-sm-2 control-label" style="text-align:center;">紧急联系人:</label>
								<div class="col-sm-4">
								<input  type="text" id="urgencyName" name="urgencyName" class="form-control" datatype="s1-10" value="${nMerchantPage.urgencyName }" />
								<span class="Validform_checktip"></span>
								</div>
								<label  class="col-sm-2 control-label" style="text-align:center;">紧急联系人手机:</label>
								<div class="col-sm-4">
								<input  type="text" id="urgencyPhone" name="urgencyPhone" class="form-control" datatype="m" value="${nMerchantPage.urgencyPhone }" />
								<span class="Validform_checktip"></span>
								</div>
							</div>
							<div class="panel-heading" style="background-color: #f5f5f5;margin-bottom:15px;margin-top:15px;">入驻人证件信息</div>
							
							<div class="form-group mno">
									<label  class="col-sm-2 control-label" style="text-align:center;">入驻人身份证正面:</label>
									<div class="col-sm-4">
										<img style="width: 109px;height: 109px; overflow: hidden;" id="cardZUrlimg"
										src="${nMerchantPage.cardZUrl}">
										<a id="cardZUrlUrl"  target="_blank" href="${nMerchantPage.cardZUrl}" >预览</a>
									</div>
									<label  class="col-sm-2 control-label" style="text-align:center;">入驻人身份证背面:</label>
									<div class="col-sm-4">
										<img style="width: 109px;height: 109px; overflow: hidden;" id="cardFUrlimg"
										src="${nMerchantPage.cardFUrl}"  href="${nMerchantPage.cardFUrl}" >
										<a id="cardFUrlUrl"  target="_blank" href="${nMerchantPage.cardFUrl}" >预览</a>

									</div>
							</div>
							<div class="form-group mno">
								<label  class="col-sm-2 control-label" style="text-align:center;">手持身份证半身照:</label>
									<div class="col-sm-4">
									<img style="width: 109px;height: 109px; overflow: hidden;" id="cardBUrlimg"
									src="${nMerchantPage.cardBUrl}">
									<a id="cardBUrlUrl"  target="_blank"  href="${nMerchantPage.cardBUrl}">预览</a>

									</div>
							</div>
							<div class="form-group mno">
								<label  class="col-sm-2 control-label" style="text-align:center;">开店类别</label>
								<div class="col-sm-4">
									<t:dictSelect field="shoptype" type="list"
									    typeGroupCode="shoptype" defaultVal="${nMerchantPage.shoptype}" hasLabel="false" 
									    title="shifou"  datatype="*">
									</t:dictSelect> 
								</div>
								<label  class="col-sm-2 control-label" style="text-align:center;">开店性质</label>
								<div class="col-sm-4">
									<t:dictSelect field="startshoptype" type="list"
									    typeGroupCode="startshoptype" defaultVal="${nMerchantPage.startshoptype}" hasLabel="false" 
									    title="startshoptype"  datatype="*">
									</t:dictSelect> 
								</div>
							</div>
							<c:if test="${(nMerchantPage.shoptype) eq (1)}">
							<div class="form-group mno">
								<label  class="col-sm-2 control-label" style="text-align:center;">法人手机号</label>
								<div class="col-sm-4">
								<input  type="text" id="bossPhone" name="bossPhone" class="form-control" value="${nMerchantPage.bossPhone }" />
								<span class="Validform_checktip"></span>
								</div>
								<label  class="col-sm-2 control-label" style="text-align:center;">法人邮箱</label>
								<div class="col-sm-4">
								<input  type="text" id="bossEmail" name="bossEmail" class="form-control" value="${nMerchantPage.bossEmail }" />
								<span class="Validform_checktip"></span>
								</div>
							</div>
							<div class="form-group mno">
								<label  class="col-sm-2 control-label" style="text-align:center;">统一社会信用代码</label>
								<div class="col-sm-4">
								<input  type="text" id="org" name="org" class="form-control" value="${nMerchantPage.org }" />
								<span class="Validform_checktip"></span>
								</div>
								
							</div>
							<div class="form-group mno">
								<label  class="col-sm-2 control-label" style="text-align:center;">营业执照:</label>
									<div class="col-sm-4">
										<img style="width: 109px;height: 109px; overflow: hidden;" id="orgUrlimg"
										src="${nMerchantPage.orgUrl}">
										<a id="orgUrlUrl"  target="_blank"  href="${nMerchantPage.orgUrl}" >预览</a>
	
									</div>
									<label  class="col-sm-2 control-label" style="text-align:center;">开户行许可证:</label>
									<div class="col-sm-4">
										<img style="width: 109px;height: 109px; overflow: hidden;" id="bankUrlimg"
										src="${nMerchantPage.bankUrl}">
										<a id="bankUrlUrl"  target="_blank"  href="${nMerchantPage.bankUrl}">预览</a>
										
										
	
									</div>
						</div>
						</c:if>
						<div class="panel-heading" style="background-color: #f5f5f5;margin-bottom:15px;margin-top:15px;">审核信息</div>
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">账号状态:</label>
							<div class="col-sm-4">
							<t:dictSelect field="accountStatus" id="accountStatus" type="list"
							    typeGroupCode="account_status" defaultVal="${nMerchantPage.accountStatus}" hasLabel="false" 
							    title="accountStatus"  datatype="*">
							</t:dictSelect> 
							</div>
							<label  class="col-sm-2 control-label" style="text-align:center;">审核状态:</label>
							<div class="col-sm-4">
								<t:dictSelect field="auditType" id="auditType" type="list"
								    typeGroupCode="audit_type" defaultVal="${nMerchantPage.auditType}" hasLabel="false" 
								    title="auditType"  datatype="*">
								</t:dictSelect> 
							</div>
						</div>
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">审核信息:</label>
							<div class="col-sm-4" style="width:60%;" >
							<textarea placeholder="建议300字以内" id="auditContent" name="auditContent"  class="form-control" style="height:100px;" datatype="*1-300" >${nGoodsDetaislPage.auditContent }</textarea>
							<span class="Validform_checktip"></span>
							</div>
						</div>
							<div class="form-group mno">
								<div class="col-sm-offset-1 col-sm-6">
									<button type="button" class="btn btn-default" id="formReturn" data-dismiss="modal" onclick="doUrl('nMerchantController.do?list')">返回</button>
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
   $("#jingying").append('${goods}');
   if(location.href.indexOf("load=detail")!=-1){
		//$("#formSubmit").hide();
		 $('#formReturn').removeAttr("disabled");
		 $('#formSubmit').removeAttr("disabled");
		 $('#accountStatus').removeAttr("disabled");
		 $('#auditType').removeAttr("disabled");
		 $('#auditContent').removeAttr("disabled");
		 $('#id').removeAttr("disabled");
		//setTimeout(hidedisabled(),10000);
		
	}
});
function	hidedisabled(){
	// document.getElementById(formSubmit).removeAttr("disabled");
	// $('#formReturn').removeAttr("disabled");
	// alert("12");
}
function	selectChange(){
//	var selectvalue=document.getElementById("jingying").value;
	var selectvalue=$("#jingying").find("option:selected").text(); 
	document.getElementById("jingyingname").value=selectvalue;
}
</script>