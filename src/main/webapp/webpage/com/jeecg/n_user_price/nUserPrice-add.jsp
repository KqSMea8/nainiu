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
					<form class="form-horizontal" role="form" id="dailogForm" action="nCashDepositController.do?doAdd" method="POST">
						<input id="id" name="id" type="hidden" value="${nCashDepositPage.id }" />
						<input id="createName" name="createName" type="hidden" value="${nCashDepositPage.createName }" />
						<input id="createBy" name="createBy" type="hidden" value="${nCashDepositPage.createBy }" />
						<input id="updateName" name="updateName" type="hidden" value="${nCashDepositPage.updateName }" />
						<input id="updateBy" name="updateBy" type="hidden" value="${nCashDepositPage.updateBy }" />
						<input id="updateDate" name="updateDate" type="hidden" value="${nCashDepositPage.updateDate }" />
						<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${nCashDepositPage.sysOrgCode }" />
						<input id="remarks" name="remarks" type="hidden" value="${nCashDepositPage.remarks }" />
						<input id="delFlag" name="delFlag" type="hidden" value="${nCashDepositPage.delFlag }" />
						<input id="bpmStatus" name="bpmStatus" type="hidden" value="${nCashDepositPage.bpmStatus }" />
						<input id="createDate" name="createDate" type="hidden" value="${nCashDepositPage.createDate }" />
						
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">商家名称:</label>
							<div class="col-sm-4" style="width:60%;" >
							<input  type="text" id="merchantname" name="merchantname" class="form-control" datatype="*1-500" value="${nCashDepositPage.merchantname}" />
							<span class="Validform_checktip"></span>
							</div>
						</div>
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">活动名称:</label>
							<div class="col-sm-4" style="width:60%;" >
							<input  type="text" id="marketingname" name="marketingname" class="form-control" datatype="*1-500" value="${nCashDepositPage.marketingname }" />
							<span class="Validform_checktip"></span>
							</div>
						</div>
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">保证金类型:</label>
							<div class="col-sm-4">
							<t:dictSelect field="cashDepositType" type="list"
							    typeGroupCode="cashDepositType" defaultVal="${nCashDepositPage.cashDepositType}" hasLabel="false" 
							    title="cashDepositType"  datatype="*">
							</t:dictSelect> 
							</div>
							<label  class="col-sm-2 control-label" style="text-align:center;">充值渠道:</label>
							<div class="col-sm-4">
							<t:dictSelect field="recharge" type="list"
							    typeGroupCode="recharge" defaultVal="${nCashDepositPage.recharge}" hasLabel="false" 
							    title="recharge"  datatype="*">
							</t:dictSelect> 
							</div>
						</div>
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">资金类型:</label>
							<div class="col-sm-4">
							<t:dictSelect field="cashDepositType" type="list"
							    typeGroupCode="cashDepositStatus" defaultVal="${nCashDepositPage.cashDepositStatus}" hasLabel="false" 
							    title="cashDepositStatus"  datatype="*">
							</t:dictSelect> 
							</div>
							<label  class="col-sm-2 control-label" style="text-align:center;">提现状态:</label>
							<div class="col-sm-4">
							<t:dictSelect field="withdrawDeposit" type="list"
							    typeGroupCode="withdrawDeposit" defaultVal="${nCashDepositPage.withdrawDeposit}" hasLabel="false" 
							    title="withdrawDeposit"  datatype="*">
							</t:dictSelect> 
							</div>
						</div>
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;" >金额:</label>
								<div class="col-sm-4" style="width:195px;margin-bottom: 20px;" >
									<input  type="text" id="price" name="price" class="form-control" datatype="n" value="${nCashDepositPage.price }" />
									<span class="Validform_checktip"></span>
								</div>
						</div>
	                   

										
									<div class="form-group mno">
											<div class="col-sm-offset-1 col-sm-6">
												<button type="button" class="btn btn-default" id="formReturn" data-dismiss="modal" onclick="doUrl('nCashDepositController.do?list')">返回</button>
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

</script> 
