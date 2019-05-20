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
		<form  method="post" id="ipnutlist" name="ipnutlist" >
			<div class="form-group mno" >
			    <label  class="col-sm-2 control-label" style="text-align:center;">已拼团数量:</label>
				<div class="col-sm-4" >
				<input type="text" name="phone" id="phone" datatype="n" value="" class="form-control">
				<span class="Validform_checktip"></span>
				</div>
			</div>
		</form>
	</body>

</html>
<script type="text/javascript" src="plug-in/ui/js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="plug-in/ui/js/forminit.p3.js"></script>
<script type="text/javascript" >
$(document).ready(function(){  
    bindKeyEvent($("#phone"));  
   
});  
function bindKeyEvent(obj){  
    obj.keyup(function () {  
        var reg = $(this).val().match(/^\d+$/);  
        var txt = '';  
        if (reg != null) {  
            txt = reg[0];  
        }  
        $(this).val(txt);  
    }).change(function () {  
        $(this).keypress();  
        var v = $(this).val();  
        if (/\.$/.test(v))  
        {  
            $(this).val(v.substr(0, v.length - 1));  
        }  
    });  
} 
</script>