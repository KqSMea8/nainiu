<!DOCTYPE html>
<!-- saved from url=(0056)http://localhost:8180/jeecg/demo/jpDemoOrderMain.do?list -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<html>

	<head>
     <meta charset="utf-8">
	  <meta name="viewport" content="width=device-width, initial-scale=1.0">
	  
		<title>首页</title>
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
		<script type="text/javascript">
		var merchantid="${merchantid}";
		</script>
	   <script src = "webpage/com/jeecg/n_order/home/hplushnOrderList.js"></script>	
		<script type="text/javascript" src="plug-in/ui/js/page.js"></script>
		
		
		  <link rel="shortcut icon" href="images/favicon.ico">
		    <link href="plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
		    <link href="plug-in-ui/hplus/css/font-awesome.css?v=4.4.0" rel="stylesheet">

		    <link href="plug-in-ui/hplus/css/animate.css" rel="stylesheet">
		    <link href="plug-in-ui/hplus/css/style.css?v=4.1.0" rel="stylesheet">
		    <!-- 全局js -->
		    <script src="plug-in-ui/hplus/js/jquery.min.js?v=2.1.4"></script>
		    <script src="plug-in-ui/hplus/js/bootstrap.min.js?v=3.3.6"></script>
		    <script src="plug-in-ui/hplus/js/plugins/layer/layer.min.js"></script>

		    <!-- 自定义js -->
		    <script src="plug-in-ui/hplus/js/content.js"></script>
		<style>
			.bond-cate {
	border: 1px solid #e8e8e8;
	border-top: 1px solid #fff;
	height: 184px;
	margin-bottom: 20px;

}
.bond-cate ul {
	height: 120px;
	margin-top: 20px;
	display: -webkit-box;
	display: -ms-flexbox;
	display: flex;
}
.bond-cate ul li {
    display: inline-block;
    flex: 1;
    margin-left: 20px;
    background: #f7f7f8;
   padding: 20px 20px 0px 40px;
    border-radius: 10px;
}
.bond-cate ul li h3 {
	margin-top: 0;
	font-size: 14px;
	display: inline-block;
	margin-right: 20px;
	color: #f04b31;
}
.bond-cate ul li .cate-link {
	font-size: 12px;
	margin-right: 5px;
	margin-left: 5px;
}
.bond-cate ul li .cate-link {
	font-size: 12px;
	margin-right: 5px;
	margin-left: 5px;
}
.bond-cate ul li .num {
	height: 70px;
	margin-top: 25px;
}
.bond-cate ul li .num strong {
	font-size: 30px;
	font-weight: 700;
	margin-right: 20px;
	color: #666;
}
em, i, strong {
	font-weight: 400;
	font-style: normal !important;
}
.bond-cate ul li .num .arrears-info {
	position: relative;
	font-size: 12px;
	color: #666;
}
.bond-cate ul li .num .arrears-info .tips-div {
	display: inline-block;
	position: absolute;
	margin: 0 7px;
}
.pdd-tips-icon.pdd-tips-icon-bg {
	margin-top: 3px !important;
	margin-left: 2px !important;
}
.pdd-tips-icon-bg {
	background: url(//imsproductionimg.yangkeduo.com/f930890de255cc03d812b72ee54645d9.png) no-repeat;
	background-position: 100%;
}
.pdd-tips-icon {
	min-width: 15px;
	min-height: 15px;
	cursor: pointer;
	color: #337ab7;
	font-size: 13px;
	line-height: 15px;
	display: inline-block;
}
.pdd-tips-icon-bg {
	background: url(//imsproductionimg.yangkeduo.com/f930890de255cc03d812b72ee54645d9.png) no-repeat;
	background-position: 100%;
}

.bond-cate ul li .num .arrears-info a {
	margin-left: 35px;
	color: #337ab7;
}
.bond-cate ul li .num .arrears-info a {
	margin-left: 35px;
	color: #337ab7;
}
.btn-group, .btn-group-vertical {
	position: relative;
	display: inline-block;
	vertical-align: middle;
}
.action-button {
	width: 64px;
	height: 26px;
	line-height: 26px;
	font-size: 13px;
	color: #333;
	cursor: pointer;
	text-align: center;
	display: inline-block;
	border-radius: 3px;
	border: 1px solid #ababab;
	background: linear-gradient(#fff,#f8f8f8);
	outline: none;
}
.btn-group a {
	margin-right: 20px;
}
.action-button {
	width: 64px;
	height: 26px;
	line-height: 26px;
	font-size: 13px;
	color: #333;
	cursor: pointer;
	text-align: center;
	display: inline-block;
	border-radius: 3px;
	border: 1px solid #ababab;
	background: linear-gradient(#fff,#f8f8f8);
	outline: none;
}
.action-button {
	width: 64px;
	height: 26px;
	line-height: 26px;
	font-size: 13px;
	color: #333;
	cursor: pointer;
	text-align: center;
	display: inline-block;
	border-radius: 3px;
	border: 1px solid #ababab;
	background: linear-gradient(#fff,#f8f8f8);
	outline: none;
}
.btn-group a {
	margin-right: 20px;
}
.action-button {
	width: 64px;
	height: 26px;
	line-height: 26px;
	font-size: 13px;
	color: #333;
	cursor: pointer;
	text-align: center;
	display: inline-block;
	border-radius: 3px;
	border: 1px solid #ababab;
	background: linear-gradient(#fff,#f8f8f8);
	outline: none;
}
.bond-cate .ing {
	margin-bottom: 0;
	font-size: 12px;
	color: #999;
	position: relative;
	top: 5px;
}
.btn-link, .btn-link.active, .btn-link:active, .btn-link[disabled], fieldset[disabled] .btn-link {
	background-color: transparent;
	box-shadow: none;
}
.btn-link, .btn-link:active, .btn-link:focus, .btn-link:hover {
	border-color: transparent;
}
.btn-link {
	color: #337ab7;
	font-weight: 400;
	border-radius: 0;
}
.bond-cate .ing {
	margin-bottom: 0;
	font-size: 12px;
	color: #999;
	position: relative;
	top: 5px;
}
div, span {
	font-family: simsun,arial,Hiragino Sans GB,\\5B8B体,sans-serif;
}
div, span {
	font-family: simsun,arial,Hiragino Sans GB,\\5B8B体,sans-serif;
}
*, ::after, ::before {
	box-sizing: border-box;
}
.bond-cate ul li:nth-of-type(2) {
	border-right: 1px solid #e8e8e8;
	border-left: 1px solid #e8e8e8;
}
	</style>
	</head>

	<body style="overflow:scroll;overflow-x:hidden">
		<div class="container bs-docs-container" style="width: 1174px;">
			<div class="row">
			<div style= "display: none;" >
				<t:dictSelect field="orderStatus"   id="orderStatus" type="list"
				    typeGroupCode="orderStatus" defaultVal="" hasLabel="false" 
				    title="orderStatus"  datatype="*">
				</t:dictSelect> 
			</div>
				<div class="col-md-10" style="width:100%">
					<div class="panel panel-default">
						<div class="panel-heading">首页</div>
						<div class="panel-body">
					<div class="bond-cate" style="background: #fff;">
		<ul>
		
			<li onclick="Opencontent('nOrderController.do?homelist')" style="margin-left:0px;border-radius: 0px;background:url(images/dendai-icon.png) bottom right #f7f7f8 no-repeat;">
				<h3>待发货订单
					<span id="1" style="padding-right: 0px; background-size: 15px 15px;" class="pdd-tips-icon pdd-tips-icon-bg"></span>
				</h3>
				<div class="num" id="sendgoods">
					
						
				</div>
				
			</li>
			<li onclick="Opencontent('nOrderController.do?homeaftersalelist')"  style="margin-left:20px;border-radius: 0px 10px 10px 0px;border-radius: 0px;background:url(images/tuikuan-icon.png) bottom right #f7f7f8 no-repeat;">
				<h3>退款/退货处理</h3>
				<div class="num" id="exitgood">
					
						
					
				</div>
				
			</li>
			<!--
			<li style="background:url(images/xiaoxi-icon.png) bottom right #f7f7f8 no-repeat;">
				<h3>系统消息</h3>
				<div class="num">
					<strong>0</strong>条
						
					
				</div>
				
			</li>
			-->
			<li  onclick="OpenWebsocket()" style="background:url(images/kefu-icon.png) bottom right #f7f7f8 no-repeat;">
				<h3>聊天客服</h3>
				<div class="num" id="websocketnmber">
					
						
					
				</div>
				
			</li>
		</ul>

	</div>
					<div id="legend">
							<legend class="le"><button type="button" class="btn btn-primary" >
							待发货订单</button></legend>
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
        function Opencontent(url){
        	//if(type=="2")
        		window.location.href=url;
        }
        function OpenWebsocket(){
        	window.open("https://www.sobot.com/console/login");  
        }
        $(function(){  
        	  $.ajax({
          		async : true,
          		cache : false,
          		type : 'POST',
          		datatype:"json",
          		url : 'nOrderController.do?sendgoods',// 请求的action路径
          		data : {},
          		error : function() {// 请求失败处理函数
          		},
          		success : function(data) {
		          	  data=eval('(' + data + ')');
		  			 var ht="<strong >"+data.numbs+"</strong>单";
          			 $("#sendgoods").html(ht);
          		}
              });
        	  $.ajax({
            		async : true,
            		cache : false,
            		type : 'POST',
            		datatype:"json",
            		url : 'nOrderController.do?exitgood',// 请求的action路径
            		data : {},
            		error : function() {// 请求失败处理函数
            		},
            		success : function(data) {
            		     data=eval('(' + data + ')');
            			 var ht="<strong >"+data.numbs+"</strong>单";
            			 $("#exitgood").html(ht);
            		}
                });
        	  $.ajax({
          		async : true,
          		cache : false,
          		type : 'POST',
          		datatype:"json",
          		url : 'nWebsocketColumController.do?websocketnmber',// 请求的action路径
          		data : {},
          		error : function() {// 请求失败处理函数
          		},
          		success : function(data) {
          		     data=eval('(' + data + ')');
          			 var ht="<strong >"+data.numbs+"</strong>条";
          			 $("#websocketnmber").html(ht);
          		}
              });
        });
    	</script>
							</div>
						</div>
					</div>
				</div>  
			
		</div>
	</div>
</body></html>