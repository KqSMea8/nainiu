<!DOCTYPE html>
<!-- saved from url=(0056)http://localhost:8180/jeecg/demo/jpDemoOrderMain.do?list -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>发送推广消息</title>

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
		<script src = "webpage/com/jeecg/n_news/nNewsList.js"></script>	
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
						<div class="panel-heading">发送推广消息</div>
						<div class="panel-body">
							<div class="search">
						  	<div class="clearfix"></div>
								<div class="form-group col-sm-3">
									<label  class="control-label col-sm-3 line34">标题名称</label>
									<div class="col-sm-8">
										<input type="text" name="title" id="title" value="" class="form-control">
									</div>
								</div>
							<div class="form-group col-sm-3">
								<label  class="control-label col-sm-3 line34" >申请时间</label>
								<div class="col-sm-8"  >
								  <input type="text"   ' name="starttime" id="starttime" class="form-control" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" datatype="*" style="background: url('plug-in/ui/images/datetime.png') no-repeat scroll right center transparent;"/>
								 </div>
						  	</div>
							<div class="form-group col-sm-3" style="width:3%;"> -	</div>
								<div class="form-group col-sm-3">
								<div class="col-sm-8" >
								    <input type="text"   name="endtime" id="endtime" class="form-control" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" datatype="*" style="background: url('plug-in/ui/images/datetime.png') no-repeat scroll right center transparent;"/>
								 </div>
						  	</div>
							<div class="form-group col-sm-3">
							    	<label  class="control-label col-sm-3 line34">是否发送</label>
									<div class="col-sm-8">
										<t:dictSelect field="nnewtype" id="nnewtype" type="list"
										    typeGroupCode="nnewtype" defaultVal="" hasLabel="false" 
										    title="nnewtype"  datatype="*">
										</t:dictSelect> 
								    </div>
							</div>
	
								<button type="buton" class="btn btn-primary" onclick="serchcontent()">查询</button>
									<div class="clearfix"></div>
								</div>
								<div align="right">
								<span> 
							
									<input type="checkbox" 
									id="selAll" onclick="selectAll();" /> <label class="btn btn-primary" for="selAll">全选&nbsp;&nbsp;</label>
									&nbsp;
									<input class="btn btn-primary" name="delall" value="批量所选" type="button"
									onclick="hs()">
									
									</span>
								</div>
								<div id="legend">
								<legend class="le"><button type="button" class="btn btn-primary" onclick="doUrl(&#39;nNewsController.do?goAdd&#39;)">新增</button></legend>
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