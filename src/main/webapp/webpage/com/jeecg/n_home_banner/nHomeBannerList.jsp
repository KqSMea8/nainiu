<!DOCTYPE html>
<!-- saved from url=(0056)http://localhost:8180/jeecg/demo/jpDemoOrderMain.do?list -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>首页banner图片</title>

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
		 <script src = "webpage/com/jeecg/n_home_banner/nHomeBannerList.js"></script>	
		<script type="text/javascript" src="plug-in/ui/js/page.js"></script>
		<script type="text/javascript">
		</script>
		<style>

		</style>

	</head>

	<body style="overflow:scroll;overflow-x:hidden">
		<div class="container bs-docs-container" style="width:100%;">
			<div class="row">
		
				<div class="col-md-10" style="width:100%">
					<div class="panel panel-default">
						<div class="panel-heading">首页banner图片</div>
						<div class="panel-body">
							<div class="search">
								<div class="form-group col-sm-3">
									<label  class="control-label col-sm-3 line34">图片名称</label>
									<div class="col-sm-8">
										<input type="text" name="title" id="title" value="" class="form-control">
									</div>
								</div>
								<button type="buton" class="btn btn-primary" onclick="serchcontent()">查询</button>
								<div class="clearfix"></div>
							</div>
							<div id="legend">
								<legend class="le"><button type="button" class="btn btn-primary" onclick="doUrl(&#39;nHomeBannerController.do?goAdd&#39;)">新增</button></legend>
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