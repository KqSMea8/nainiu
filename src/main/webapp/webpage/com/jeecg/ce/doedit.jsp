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
<link rel="stylesheet" href="plug-in/tools/css/metrole/common.css" >
<script src = "webpage/com/jeecg/s_dangjiang_zx_content/add.js"></script>	
<script type="text/javascript" src="plug-in/lhgDialog/lhgdialog.min.js?skin=metrole"></script>
<script type="text/javascript" src="plug-in/tools/syUtil.js"></script>

</head>
<body style='overflow:scroll;overflow-x:hidden'>
	<div class="container bs-docs-container" style="width:100%;">
		<div class="row">
			<div class="panel panel-default">
				<div class="panel-heading">编辑</div>
				<div class="panel-body">
					<form class="form-horizontal" role="form" id="dailogForm" action="/jeecg/p3/wxActInvite.do?doEdit" method="POST">
						 <input type="hidden" id="btn_sub" class="btn_sub" />
						 <input type="hidden" value="95B6BF61945043039008299AA53A2991" name="id" id="id" />
												<div class="form-group mno">
												    <label for="inputEmail3" class="col-sm-2 control-label" style="text-align:left;">活动名称</label>
												    <div class="col-sm-8">
												      <input type="text" value="2" name="name" id="name" class="form-control" datatype="*"/>
												    </div>
												</div>
												<div class="form-group mno">
													<label for="inputEmail3" class="col-sm-2 control-label" style="text-align:center;">顾客类型</label>
													<div class="col-sm-4">
														<select name="usertype" id="usertype" class="form-control">
			                                                <option value="">--请选择--</option>
															<option value="1" selected="selected">普通客户</option>
															<option value="2">签约客户</option>
														</select>
													</div>
													<label for="inputEmail3" class="col-sm-2 control-label" style="text-align:center;">联系人</label>
													<div class="col-sm-4">
														<input type="text" value="秦风" maxlength="16" name="goContactName" id="goContactName" class="form-control" datatype="*">
													<span class="Validform_checktip"></span>
													</div>
											</div>
											<div class="form-group mno">
													<label for="inputEmail3" class="col-sm-2 control-label" style="text-align:center;">顾客类型</label>
													<div class="col-sm-4">
													<t:dictSelect field="ce" type="list"
													typeGroupCode="education" defaultVal="${cePage.ce}" hasLabel="false"  title="ce"  datatype="*" 
													></t:dictSelect>   
													</div>
													<label for="inputEmail3" class="col-sm-2 control-label" style="text-align:center;">联系人</label>
													<div class="col-sm-4">
														
														<input id="userid1" name="userid" type="hidden" >
												     	<input readonly="readonly" type="text" id="auditperson1" 
												     	name="auditperson1" class="form-control"
												     	datatype="*"  onclick="openUserSelect('userid1','auditperson1')"  />  
													<span class="Validform_checktip"></span>
													</div>
											</div>
												<div class="form-group mno">
												    <label for="inputEmail3" class="col-sm-2 control-label" style="text-align:center;">活动开始时间</label>
												    <div class="col-sm-4">
												      <input type="text" value="2016-03-26 15:50:32" name="beginTime" id="beginTime" class="form-control" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" datatype="*" style="background: url('plug-in/ui/images/datetime.png') no-repeat scroll right center transparent;"/>
												    </div>
												    <label for="inputEmail3" class="col-sm-2 control-label" style="text-align:center;">活动开始时间</label>
												    <div class="col-sm-4">
												      <input type="text" value="" name="beginTime" id="beginTime" class="form-control" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" datatype="*" style="background: url('plug-in/ui/images/datetime.png') no-repeat scroll right center transparent;"/>
												    </div>
												</div>
												<div class="form-group mno">
												    <label for="inputEmail3" class="col-sm-2 control-label" style="text-align:center;"> 活动结束时间</label>
												    <div class="col-sm-4">
												      <input type="text" value="2016-03-26 15:50:34" name="endTime" id="endTime" class="form-control" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" datatype="*"  style="background: url('plug-in/ui/images/datetime.png') no-repeat scroll right center transparent;"/>
												    </div>
												</div>
												<div class="form-group mno">
												    <label for="inputEmail3" class="col-sm-2 control-label" style="text-align:center;">入口地址</label>
												    <div class="col-sm-8">
													<textarea name="content" id="content" class="form-control"  style="height:100px"></textarea>
												    </div>
												</div>
												<div class="form-group mno">
											    <label for="inputEmail3" class="col-sm-2 control-label" style="text-align:center;">入口地址</label>
											    <div class="col-sm-8">
												<textarea name="details" id="details" style="width: 650px;height:300px"></textarea>
											    </div>
											    <script type="text/javascript"  charset="utf-8" src="plug-in/ueditor/ueditor.config.js"></script>
												<script type="text/javascript"  charset="utf-8" src="plug-in/ueditor/ueditor.all.js"></script>
												 <script type="text/javascript">
											        var editor = UE.getEditor('details');
											    </script>
											</div>
									<div class="form-group mno">
											<div class="col-sm-offset-1 col-sm-6">
												<button type="button" class="btn btn-default" id="formReturn" data-dismiss="modal" onclick="doUrl('/jeecg/p3/wxActInvite.do?list')">返回</button>
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
