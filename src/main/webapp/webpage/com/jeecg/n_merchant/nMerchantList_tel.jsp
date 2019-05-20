<!DOCTYPE html>
<!-- saved from url=(0056)http://localhost:8180/jeecg/demo/jpDemoOrderMain.do?list -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>商家信息</title>

		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<link href="plug-in/ui/css/bootstrap.css" rel="stylesheet">
		<link href="plug-in/ui/css/style.css" rel="stylesheet">
		<link href="plug-in/ui/css/zTreeStyle.css" type="text/css" rel="stylesheet">
		<link href="plug-in/ui/css/kongjian.css" rel="stylesheet">
		<link rel="stylesheet" href="plug-in/ui/css/font-awesome.css">
		<script type="text/javascript" src="plug-in/ui/js/iFrameResize.js"></script>
		<script type="text/javascript" src="plug-in/ui/js/jquery-1.9.1.js"></script>
		<script type="text/javascript" src="plug-in/ui/js/respond.min.js"></script>
		<script type="text/javascript" src="plug-in/ui/js/bootstrap.js"></script>
		<script type="text/javascript" src="plug-in/ui/js/common.js"></script>
		<script type="text/javascript" src="plug-in/ui/js/jquery.form.js"></script>
		<script type="text/javascript" src="plug-in/ui/js/scrollNav.js"></script>
		<script type="text/javascript" src="plug-in/ui/js/WdatePicker.js" language="javascript"></script>
		<link href="plug-in/ui/css/WdatePicker.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="plug-in/ui/js/layer.js" language="javascript"></script>
		<link rel="stylesheet" href="plug-in/ui/css/layer.css" id="layui_layer_skinlayercss">
		<script type="text/javascript" src="webpage/com/jeecg/n_merchant/nMerchantList_tel.js"></script>
		<script type="text/javascript" src="plug-in/ui/js/page.js"></script>
              <script type="text/javascript">
              var merchantid="${merchantid}";
      		</script>
		<style>

		</style>

	</head>

	<body style="overflow:scroll;overflow-x:hidden">
		<div class="container bs-docs-container" style="width:100%;">
			<div class="row">
				<div class="col-md-10" style="width:100%">
					<div class="panel panel-default">
						<div class="panel-heading">商家信息</div>
						<div class="panel-body">
							<div class="search">
							</div>
							<div id="legend">
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