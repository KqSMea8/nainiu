<!DOCTYPE html>
<!-- saved from url=(0056)http://localhost:8180/jeecg/demo/jpDemoOrderMain.do?list -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<html lang="en">
<head>
<title>件开发平台</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<t:base type="jquery"></t:base>
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

	<body>
	<div class="bond-cate" style="background: #fff;">
		<ul>
			<li>
				<h3>店铺保证金</h3>
				<a class="cate-link" href="#/FundManagement/eReceipts" target="_blank" style="color: rgb(51, 122, 183); margin-left: 80px;">查看电子收据</a>
				<a class="cate-link" target="_blank" style="color: rgb(51, 122, 183);">我要退店</a>
				<div class="num"><strong>0.00</strong><!-- react-text: 1526 -->元<!-- /react-text -->
					<div class="arrears-info" style="display: none;">
						
					<!-- react-text: 1528 -->欠费账单：<!-- /react-text -->
					<i style="color: rgb(255, 84, 84);">0.00</i>
						
					<!-- react-text: 1530 -->元<!-- /react-text -->
					<div class="tips-div">
						<span id="PddTips15160112915970620965260713555Pdd" style="padding-right: 0px; background-size: 15px 15px;" class="pdd-tips-icon pdd-tips-icon-bg"></span>
					</div>
					<a>补缴</a>
					<a href="#/recharge/enter_deposit/pending_bill" style="margin-left: 12px;">欠费账单</a>
					</div>
				</div>
				<div class="btn-group">
					<a class="action-button" style="display: inline-block;">充值</a>
					<a class="action-button" style="display: none;">提现</a>
				</div>
				<p class="ing" style="display: none;">
					
				<!-- react-text: 1539 -->您有一笔充值正在进行中<!-- /react-text -->
				<a class="btn-link" style="color: rgb(51, 122, 183);">点击查看</a>
				</p>
				<p class="ing" style="display: none;">
					
				<!-- react-text: 1542 -->您有一笔补缴正在进行中<!-- /react-text -->
				<a class="btn-link" href="#/recharge/enter_deposit/offline?stage=1" style="color: rgb(51, 122, 183);">点击查看</a>
				</p>
			</li>
			<li>
				<h3><!-- react-text: 1546 -->活动保证金<!-- /react-text -->
					<span id="PddTips1516011291598006347453507647582Pdd" style="padding-right: 0px; background-size: 15px 15px;" class="pdd-tips-icon pdd-tips-icon-bg"></span>
				</h3>
				<a style="float: right; margin-right: 20px;">明细</a>
				<span style="display: none; float: right; margin-right: 10px;">
					
				<!-- react-text: 1550 -->当前：undefined档<!-- /react-text -->
				</span><div class="num">
					<strong>0.00</strong>
						
					<!-- react-text: 1553 -->元<!-- /react-text -->
				</div>
				<div class="btn-group">
					<a class="action-button">充值</a>
					<a class="action-button" style="margin-right: 5px;">提现</a>
				</div>
				<p class="ing" style="display: none;"><!-- react-text: 1558 -->您有一笔充值正在进行中<!-- /react-text -->
					<a class="btn-link" style="color: rgb(51, 122, 183);">点击查看</a>
				</p>
			</li>
			<li>
				<h3>限免保证金</h3>
				<div class="num">
					<strong>0.00</strong>
						
					<!-- react-text: 1564 -->元<!-- /react-text -->
				</div>
				<div class="btn-group">
					<a class="action-button">提现</a>
				</div>
			</li>
		</ul>

	</div>
	<script src="js/mui.min.js"></script>
		<script type="text/javascript">
			mui.init()
		</script>
	</body>

</html>