<!DOCTYPE html>
<!-- saved from url=(0056)http://localhost:8180/jeecg/demo/jpDemoOrderMain.do?list -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>订单管理</title>

		<meta http-equiv="X-UA-Compatible" content="IE=edge">
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
		<script src = "webpage/com/jeecg/n_phone_recharge/pt_nPhoneRechargeList.js"></script>	
		<script type="text/javascript" src="plug-in/ui/js/page.js"></script>
		<script type="text/javascript">
		var orderId="${orderId}";
		</script>
		<style>

		</style>

	</head>

	<body style="overflow:scroll;overflow-x:hidden">
		<div class="container bs-docs-container" style="width:100%;">
			<div class="row">
		
				<div class="col-md-10" style="width:100%">
					<div class="panel panel-default">
						<div class="panel-heading">订单管理-话费充值详情列表</div>
						<div class="panel-body">
						<div id="legend">
							<legend class="le">
							<button type="button" class="btn btn-primary" onclick="doUrl(&#39;nOrderController.do?phonelist&#39;)">返回上一页</button>
							</legend>
						</div>
								
							<table class="table table-striped">
								<thead>
									<tr id="tabletitle">
									</tr>
								</thead>
								<tbody id="htmcontent">
								</tbody>
							</table>
							<div class="text-right">
             <ul class="pagination" id="page">
             </ul>
        <script type="text/javascript">
    	</script>
							</div>
						</div>
					</div>
				</div>  
			
		</div>
	</div>
</body></html>