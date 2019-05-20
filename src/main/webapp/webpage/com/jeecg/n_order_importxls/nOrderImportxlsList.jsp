<!DOCTYPE html>
<!-- saved from url=(0056)http://localhost:8180/jeecg/demo/jpDemoOrderMain.do?list -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>批量发货</title>
		<t:base type="jquery,easyui,tools"></t:base>
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

		<script type="text/javascript">
		var merchantid="${merchantid}";
		</script>
		 <script src = "webpage/com/jeecg/n_order_importxls/nOrderImportxlsList.js"></script>	
		<script type="text/javascript" src="plug-in/ui/js/page.js"></script>
		<style>

		</style>

	</head>

	<body style="overflow:scroll;overflow-x:hidden">
		<div class="container bs-docs-container" style="width:100%;">
			<div class="row">
		
				<div class="col-md-10" style="width:100%">
					<div class="panel panel-default">
						<div class="panel-heading">批量发货</div>
						<div class="panel-body">
							<div class="search">
								<div class="form-group col-sm-3">
									<label  class="control-label col-sm-3 line34">订单编号</label>
									<div class="col-sm-8">
										<input type="text" name="norderid" id="norderid" value="" class="form-control">
									</div>
								</div>
								<div class="form-group col-sm-3">
										<label  class="control-label col-sm-3 line34">订单状态</label>
										<div class="col-sm-8">
											<t:dictSelect field="importxlsType" id="importxlsType" type="list"
											    typeGroupCode="importxlsType" defaultVal="" hasLabel="false" 
											    title="importxlsType"  datatype="*">
											</t:dictSelect> 
									    </div>
								</div>

								<button type="buton" class="btn btn-primary" onclick="serchcontent()">查询</button>
								<div class="clearfix"></div>
							</div>
							<div id="legend">
								<legend class="le">
									<button type="button" class="btn btn-primary" onclick="ImportXls()">
									       批量发货
									 </button>
									<button type="button" class="btn btn-primary" onclick="ExportXlsOrder()">
									         导出发货模板
									 </button>
									
								</legend>
							</div>
							<div align="right">
							<span> 
								<input type="checkbox" 
								id="selAll" onclick="selectAll();" /> <label class="btn btn-primary" for="selAll">全选&nbsp;&nbsp;</label>
								&nbsp;
								<input class="btn btn-primary" name="delall" value="批量删除所选" type="button"
								onclick="hs()">
								</span>
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
<script type="text/javascript">
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'nOrderImportxlsController.do?upload', "nOrderImportxlsList");
	
}

//导出
function ExportXlsOrder() {
	//JeecgExcelExport("nOrderImportxlsController.do?exportXls","nOrderImportxlsList");
	window.location.href ="nOrderImportxlsController.do?exportXlsByT";
}

//模板下载
function ExportXlsOrderByT() {
//	JeecgExcelExport("nOrderImportxlsController.do?exportXlsByT","nOrderImportxlsList");
	window.location.href = "nOrderController.do?exportXlsexpress";
}
</script>