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
		<script type="text/javascript" src="webpage/com/jeecg/n_merchant/nMerchantList_money.js"></script>
		<script type="text/javascript" src="plug-in/ui/js/page.js"></script>

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
								<div class="form-group col-sm-3">
									<label  class="control-label col-sm-3 line34">店铺名称</label>
									<div class="col-sm-8">
										<input type="text" name="company" id="company" value="" class="form-control">
									</div>
								</div>
								<div class="form-group col-sm-3">
									<label  class="control-label col-sm-3 line34">入驻手机号</label>
									<div class="col-sm-8">
										<input type="text" name="phone" id="phone" value="" class="form-control">
									</div>
								</div>
								<div class="form-group col-sm-3">
									<label  class="control-label col-sm-3 line34">开店类别</label>
									<div class="col-sm-8">
										<select name="shoptype" id="shoptype" class="form-control">
										    <option value="">请选择</option>
										    <option value="0">个人</option>
											<option value="1">企业</option>
										</select>
									</div>
								</div>
								
								<div class="form-group col-sm-3">
									<label  class="control-label col-sm-3 line34">开店性质</label>
									<div class="col-sm-8">
										<select name="startshoptype" id="startshoptype" class="form-control">
										    <option value="">请选择</option>
										    <option value="0">vip商家</option>
											<option value="1">普通商家</option>
										</select>
									</div>
								</div>
								<div class="form-group col-sm-3">
										<label  class="control-label col-sm-3 line34">缴费</label>
										<div class="col-sm-8">
											<select name="ispay" id="ispay" class="form-control">
											    <option value="">请选择</option>
											    <option value="0">是</option>
												<option value="1">否</option>
											</select>
										</div>
								</div>
								<div class="form-group col-sm-3">
										<label  class="control-label col-sm-3 line34">账号状态</label>
										<div class="col-sm-8">
											<select name="accountStatus" id="accountStatus" class="form-control">
											   <option value="">请选择</option>
												<option value="0">正常</option>
												<option value="1">禁用</option>
												<option value="2">销户</option>
											</select>
										</div>
								</div>
								<div class="form-group col-sm-3">
										<label  class="control-label col-sm-3 line34">审核信息</label>
										<div class="col-sm-8">
											<select name="auditType" id="auditType" class="form-control">
											    <option value="">请选择</option>
												<option value="0">审核中</option>
												<option value="1">通过</option>
												<option value="2">不通过</option>
											</select>
										</div>
								</div>
								<button type="buton" class="btn btn-primary" onclick="serchcontent()">查询</button>
								<div class="clearfix"></div>
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