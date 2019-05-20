<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.jeecgframework.core.util.SysThemesUtil,org.jeecgframework.core.enums.SysThemesEnum"%>
<%@include file="/context/mytags.jsp"%>
<%
  session.setAttribute("lang","zh-cn");
  SysThemesEnum sysTheme = SysThemesUtil.getSysTheme(request);
  String lhgdialogTheme = SysThemesUtil.getLhgdialogTheme(sysTheme);
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta charset="utf-8" />
  <title>闪购拼团</title>
		<link href="webpage/login/css/login.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="plug-in/ui/js/jquery-1.9.1.js"></script>
	</head>
	<body  style="background: #fcfcfc;">      
<div class="login" style="background: #f9cc71;padding-bottom: 10px;">
			<div class="login-head" style="margin: 0px auto;">
				<div class="login-head-a">
					<a href="index.html" > 
					<img src="webpage/login/img/a.png" style="border: 0;" title="" alt=""/></a>
						<!--<p>我的</p>-->
				</div>
				<div class="login-head-b" style="border-left: 1px solid #512113;color:#512113;margin-top: 3px;">
					商户后台管理
				</div>
			</div>
			</div>
			<div id="formbox" style="background: #fff;">
			<form id="loinForm"  check="loginController.do?updatepwd"  role="form" action="loginController.do?login"  method="post">
	<div class="form">
		<h3>找回密码</h3>
		<div class="item">
			<span class="label">店铺名称：</span>
			<div class="fl">
				<input type="text" id="username" name="username" class="text" tabindex="1"   />
				<label id="username_succeed" class="blank"></label>
				<span class="clear"></span>
				<div id="username_error"></div>
			</div>
		</div><!--item end-->
		<div class="item">
			<span class="label"><span class="red">*</span>手机：</span>
			<div class="fl">
				<input type="text" id="phone" name="phone" class="text" value="" tabindex="10" />
				<label id="mobile_succeed" class="blank"></label>
				<span class="clear"></span>
				<label id="mobile_error"></label>
			</div>
		</div><!--item end-->
		<div class="item">
			<span class="label"><span class="red">*</span>校验码：</span>
			<div class="fl">
				<input type="text" id="code" name="code" class="text text-1" autocomplete="off" MaxLength="6" tabindex="6" />
				<input class="get_code" id="passButton" onclick="runtime()" value="获取验证码" type="button">
			</div>
		</div><!--item end-->
		<div id="o-password">
			<div class="item">
				<span class="label"><span class="red">*</span>新密码：</span>
				<div class="fl">
					<input type="password" id="password" name="password" class="text" tabindex="2"/>
					<label id="pwd_succeed" class="blank"></label>
				
				
				</div>
			</div><!--item end-->
			
			<div class="item">
				<span class="label"><span class="red">*</span>确认新密码：</span>
				<div class="fl">
					<input type="password" id="password2" name="password2" class="text" tabindex="3"/>
					<label id="pwd2_succeed" class="blank"></label>
					<span class="clear"></span>
					<label id="pwd2_error"></label>
				</div>
			</div><!--item end-->
		
			<div class="item">
			<span class="label">&nbsp;</span>
			<div id="showErrMsg" style="color: #ff0000;"></div>
			</div>
		<div class="item">
		  
		<span class="label">&nbsp;</span>
		<input type="button" class="yellow_button " onclick="checkUser()"  id="but_login" value="提交注册信息"  />
		</div><!--item end-->
		
	</div>
	</form>
</div><!--formbox end-->
			
		</div>

</body>    
</html>
<script type="text/javascript" src="plug-in/ui/layer/layer.js" language="javascript"></script>
<link rel="stylesheet" href="plug-in/ui/layer/skin/layer.css" id="layui_layer_skinlayercss">
  <script type="text/javascript" >
	var isChange = false;
  $(function(){
//document.getElementById("Validform_msg").style.display="none";
  });
  //登录提示消息显示
  function showErrorMsg(msg){	
    $("#errMsgContiner").show();
    $("#showErrMsg").html(msg);    
    window.setTimeout(optErrMsg,3000); 
  }
  function optErrMsg(){
		$("#showErrMsg").html('');
		$("#errMsgContiner").hide();
	}
  //验证用户信息
  function checkUser(){
	 // debugger;
    if(!validForm()){
      return false;
    }
    newLogin();
  }
  //表单验证
  function validForm(){
	  if(!isChange){
	      showErrorMsg("请获取验证码");
	      return false;
	    }
    if($.trim($("#phone").val()).length==0){
      showErrorMsg("请输入手机号");
      return false;
    }
    if($.trim($("#code").val()).length==0){
        showErrorMsg("请输入验证码");
        return false;
      }
    if($.trim($("#code").val()).length!=6){
        showErrorMsg("请输入正确验证码");
        return false;
      }
    if($.trim($("#password").val()).length==0){
      showErrorMsg("请输入密码");
      return false;
    }
    if($.trim($("#password2").val()).length==0){
      showErrorMsg("请再次输入密码");
      return false;
    }
    if($.trim($("#password").val())!=$.trim($("#password2").val())){
        showErrorMsg("两次密码不一致");
        return false;
      }
    return true;
  }
  //登录处理函数
  function newLogin(orgId) {
    //setCookie();
    var actionurl=$('form').attr('action');//提交路径
    var checkurl=$('form').attr('check');//验证路径
    var formData = new Object();
    var data=$(":input").each(function() {
      formData[this.name] =$("#"+this.name ).val();
    });
    $.ajax({
        async : false,
        cache : false,
        type : 'POST',
        url : checkurl,// 请求的action路径
        data : formData,
        error : function() {// 请求失败处理函数
        },
        success : function(data) {
        	var data = JSON.parse(data);
        	 parent.layer.alert(data.message, {
     	        icon: 1,
     	        shadeClose: false,
     	        title: '提示'
     	    },function(index){
     	    	//document.getElementById('formSubmit').submit();
     	    	// serchcontent();
     	    	parent.layer.close(index);
     	    	var  success=data.status;
     	    	if(success=="success"){
     	    	   window.location.href = actionurl;
     	    	}
     	    });
        	
        }
    });
  }
  var num = 120;
	function runtime() {
		var phone=$.trim(document.getElementById("phone").value);
		  var myreg =/^1(2|3|4|5|6|7|8|9)\d{9}$/;
	    if(!myreg.test(phone)){
	    	 alert('请输入有效的手机号码！');
		} else {
			isChange=true;
			code();
			//验证码接口
			$.ajax({
				type: "post",
				url: "codecontroller.do?phocode",
				async: true,
				datatype: "json",
				data: {
					phone: phone
				},
				success: function(data) {
				//	alert(data);
					var data = JSON.parse(data);
				/*	var data = JSON.parse(data);
					datetime = data.date;*/
				//	mui.toast(data.message);
					var status=data.status;
					var message=data.message;
					if(status=="success"){
						// runtime();
					}else{
						document.getElementById("passButton").disabled = false;
						alert(message);
					}
				},
				error: function() {
					alert("失败");
				}
			});

		
		}
	}
	
	  
		function code() {
				document.getElementById("passButton").disabled = "disabled";
				timeRun = setInterval(function() {
					document.getElementById("passButton").value = "已发送(" + --num + ")";
					if(num == 0) {
						document.getElementById("passButton").disabled = false;
						clearInterval(timeRun);
						document.getElementById("passButton").value = "重新发送";
					}
				}, 1000);
		}
</script>