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
					<form class="form-horizontal" role="form" id="dailogForm" action="nOrderController.do?doAddWinners" method="POST">
						
						<input type="hidden" id="goodsSum" name="goodsSum"  value="0" />
						<input  type="hidden" id="numbers" name="numbers" value="0"  />
					    <input  type="hidden" id="paySum" name="paySum"  value="0" />
					    <input  type="hidden" id="paymode" name="paymode"  value="2" />
					    <input  type="hidden" id="goodsId" name="goodsId"  value="${nOrderPage.goodsId}" />
					    <input  type="hidden" id="merchantId" name="merchantId"  value="${nOrderPage.merchantId}" />
					    <input  type="hidden" id="merchantname" name="merchantname"  value="${nOrderPage.merchantname}" />
					    <input  type="hidden" id="marketingOne" name="marketingOne"  value="${nOrderPage.marketingOne}" />
					    <input  type="hidden" id="orderStatus" name="orderStatus"  value="3" />
					    <input  type="hidden" id="orderType" name="orderType"  value="0" />
					    <input  type="hidden" id="joinorderstatus" name="joinorderstatus"  value="1" />
					    <input  type="hidden" id="goodsDetaislType" name="goodsDetaislType"  value="${nOrderPage.goodsDetaislType}" />
					    <input  type="hidden" id="orderid" name="orderid"  value="${nOrderPage.orderid}" />
					    <input  type="hidden" id="paystatus" name="paystatus"  value="0" />
					    <input  type="hidden" id="gatheringstatus" name="gatheringstatus"  value="1" />
					    
					    
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">商品名称:</label>
							<div class="col-sm-4" style="width:60%;" >
							<input  type="text" id="goodsname" name="goodsname" class="form-control"   readonly datatype="*1-200" value="${nOrderPage.goodsname}" />
							<span class="Validform_checktip"></span>
							</div>
						</div>
				
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">用户名称:</label>
							<div class="col-sm-4" style="width:60%;" >
							<input  type="text" id="realname" name="realname" class="form-control" datatype="*1-100" value="" />
							<span class="Validform_checktip"></span>
							</div>
						</div>

				
									<div class="form-group mno">
											<div class="col-sm-offset-1 col-sm-6">
												<button type="button" class="btn btn-default" id="formReturn" data-dismiss="modal" onclick="doUrl('nOrderController.do?Threepersonlist&merchantid=${nOrderPage.merchantId}&orderid=${nOrderPage.orderid}&id=${nOrderPage.id}')">返回</button>
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
