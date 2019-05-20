<!DOCTYPE html>
<!-- saved from url=(0056)http://localhost:8180/jeecg/demo/jpDemoOrderMain.do?list -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<html lang="en">

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<t:base type="jquery,easyui,tools"></t:base>
		<title>店铺保证金</title>
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
		var merchantId="${merchantId}";
		</script>
		<script src = "webpage/com/jeecg/n_cash_deposit/ShopnCashDepositList.js"></script>	
		<script type="text/javascript" src="plug-in/ui/js/page.js"></script>
		<style>
		.bond-cate {
border: 1px solid #e8e8e8;
border-top: 1px solid #fff;
height: 184px;
margin-bottom: 20px;
}
.bond-cate ul {
height: 140px;
margin-top: 20px;
display: -webkit-box;
display: -ms-flexbox;
display: flex;
}
.bond-cate ul li {
display: inline-block;
flex: 1;
padding-left: 20px;
}
.bond-cate ul li h3 {
margin-top: 0;
font-size: 14px;
display: inline-block;
margin-right: 20px;
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
margin-top: 10px;
}
.bond-cate ul li .num strong {
font-size: 30px;
font-weight: 700;
margin-right: 20px;
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
		<div class="container bs-docs-container" style="width:100%;">
			<div class="row">
		
				<div class="col-md-10" style="width:100%">
					<div class="panel panel-default">
						<div class="panel-heading">店铺保证金</div>
						<div class="panel-body">
						<div class="bond-cate" style="background: #fff;">
						<ul>
							<li>
								<h3><a href="javascript:goPay();">店铺保证金</a></h3>
								 <c:if test="${ispay==0}"> 
								 <c:if test="${is_apply==1}"> 
									<a class="cate-link" style="color: rgb(51, 122, 183);" href="javascript:goTrans();">
									      我要退店
									</a>
								  </c:if>
								  <c:if test="${is_apply==0}"> 
									<a class="cate-link" style="color: rgb(51, 122, 183);" href="javascript:;">
									     已提交申请，请等待审核
									</a>
								  </c:if>
							    </c:if>
								<div id="pay">
								  <c:if test="${ispay==0}"> 
									<div class="num">已缴纳保证金<strong>${depositMoney}</strong>元
									</div>
									   </c:if>
								  <c:if test="${ispay==1}"> 
									<div class="num">需缴纳保证金<strong>${deposit}</strong>元
									</div>
									<div class="btn-group">
										<a class="action-button" style="display: inline-block;"  href="javascript:frepage();">充值</a>
									</div>
									</c:if>
								</div>
								<div id="trans" style="display: none;height: 75px;margin-top: 10px;">
									<div>
										<input id="aliAccount" type="text" style="height: 50px;line-height: 40px;font-size: 18px;" placeholder="请输入支付宝账户"  /><br/>
										<a class="action-button" style="margin-top: 20px;" href="javascript:doTrans('${merchantId}')">退款</a>
									</div>
								</div>
							</li>
					
							
						</ul>

					</div>
						<div class="search">
							<div class="form-group col-sm-3">
								<label  class="control-label col-sm-3 line34">资金类型</label>
								<div class="col-sm-8">
									
									<t:dictSelect field="cashDepositStatus" id="cashDepositStatus" type="list"
									    typeGroupCode="cashDepositStatus" defaultVal="" hasLabel="false" 
									    title="cashDepositStatus"  datatype="*">
									</t:dictSelect> 
								</div>
							</div>
							<div class="form-group col-sm-3">
							<label  class="control-label col-sm-3 line34">资金状态</label>
								<div class="col-sm-8">
									<t:dictSelect field="withdrawDeposit" id="withdrawDeposit" type="list"
									    typeGroupCode="withdrawDeposit" defaultVal="" hasLabel="false" 
									    title="withdrawDeposit"  datatype="*">
									</t:dictSelect> 
								</div>
							</div>
							<div class="form-group col-sm-3">
							<label  class="control-label col-sm-3 line34">审核状态</label>
								<div class="col-sm-8">
									<t:dictSelect field="accountDeposit" id="accountDeposit" type="list"
									    typeGroupCode="accountDeposit" defaultVal="" hasLabel="false" 
									    title="accountDeposit"  datatype="*">
									</t:dictSelect> 
								</div>
							</div>
							
							
				
						<button type="buton" class="btn btn-primary" onclick="serchcontent()">查询</button>
						<div class="clearfix"></div>
					</div>
					<div id="legend">
							<legend class="le"><button type="button" class="btn btn-primary" >
							缴费记录</button></legend>
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

function frepage(url){
	var url="aliPayController.do?getDepositForm&jingying=${jingying}&id=${merchantId}";
	window.open(url);    
	parent.layer.confirm('确认充值是否成功？', {
	    btn: ['确定','取消'] //按钮
//	    shade: [0.3, '#393D49'] //不显示遮罩
	}, function(index){
		location.reload();
		parent.layer.close(index);
//		window.location.href ="nCashDepositController.do?shoplist";
	}, function(index){
		location.reload();
		parent.layer.close(index);
//		  window.location.href ="nCashDepositController.do?shoplist";
	});
	/*parent.layer.alert("确认充值是否成功", {
        icon: 1,
        shadeClose: false,
        title: '提示'
    },function(index){
    	//document.getElementById('formSubmit').submit();
    	 serchcontent();
    	parent.layer.close(index);
    });*/
	
}
</script>