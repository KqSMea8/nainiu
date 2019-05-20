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
  <title>赚赚拼团</title>
   <link rel="shortcut icon" href="images/favicon.ico">
   <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
   <link href="webpage/login/css/login.css" rel="stylesheet" type="text/css" />
	</head>
	<body >      
<div class="login">
			<div class="login-head">
				<div class="login-head-a">
					<img src="webpage/login/img/a.png" title="" alt=""/>
						<!--<p>我的</p>-->
				</div>
				<div class="login-head-b">
					商户后台管理
				</div>
			</div>
			<div class="login-con">
				<div class="login-a">
					<form id="loinForm"  check="loginController.do?checkuser"  role="form" action="loginController.do?login"  method="post">
                      <div class="login-b">
                      	<div class="login-b-a">用户登陆</div>
                      	<div class="login-b-b">
                      		<ul>
                      			<li>
                      				<label for="userName" >用户名：</label>
                      				<input type="text" id="userName" name="userName"  placeholder="请输入用户名" />
                      				<h1 id="m-tip"></h1>
                      			</li>
                      			<li>
                      				<label for="password">密 &nbsp;&nbsp;码：</label>
                      				<input type="password" id="password" name="password" placeholder="请输入密码" />
                      				<h1 id="p-tip"></h1>
                      			</li>
                      			<li>
                      				<label >验证码：</label>   
                      				<input type="text" style="width:150px" name="randCode" class="form-control" placeholder="请输入验证码"  id="randCode"/>
                                    <span class="input-group-addon" style="padding: 0px;">
                                    <img id="randCodeImage" src="randCodeImage"  />
                                    </span>
                                    <h1 id="c-tip"></h1>
                      					
                      				<!--输入错误时的输入框样式<input type="text" name="textfield" id="textfield" class="receSum-error" />-->
                                   <!--当输入的手机号码发生错误时，出现如下提示信息
                                   <p class="error-tips-1 spanRed">在此处显示错误的信息提示——如：对不起，您输入的手机号码格式有误，请重新输入！</p>-->
                      				
                      			</li>
                      			
                      			<li class="login-x">
		                          <div class="login-x-a">
		                          <div id="showErrMsg" style="color: #ff0000;"></div>
		                          <input type="button" name="登录"value="登录" id="but_login"  onclick="checkUser()"  />
		                          <div class="x"></div></div>
		                         
	                            </li>
                      			
                      		</ul>
                      	</div>
                      	<div class="login-b-c">
                      		<a href="http://zzz.nainiupt.com/ruzhu1.html" target="_blank">新店铺入驻</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="loginController.do?forgetpwd" >忘记登录密码？</a>
                      	</div>
                      </div>
					</form>
				</div>
			</div>
			
		</div>

<script type="text/javascript" src="plug-in/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="plug-in/jquery/jquery.cookie.js"></script>
<script type="text/javascript" src="plug-in/mutiLang/en.js"></script>
<script type="text/javascript" src="plug-in/mutiLang/zh-cn.js"></script>
<script type="text/javascript" src="plug-in/login/js/jquery.tipsy.js"></script>
<script type="text/javascript" src="plug-in/login/js/iphone.check.js"></script>
<script type="text/javascript">
	$(function(){
		optErrMsg();
	});
	$("#errMsgContiner").hide();
	function optErrMsg(){
		$("#showErrMsg").html('');
		$("#errMsgContiner").hide();
	}
	$('#randCodeImage').click(function(){
	    reloadRandCodeImage();
	});
	/**
	 * 刷新验证码
	 */
	function reloadRandCodeImage() {
	    var date = new Date();
	    var img = document.getElementById("randCodeImage");
	    img.src='randCodeImage?a=' + date.getTime();
	}
   //输入验证码，回车登录
  $(document).keydown(function(e){
  	if(e.keyCode == 13) {
  		$("#but_login").click();
  	}
  });

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
    if($.trim($("#userName").val()).length==0){
      showErrorMsg("请输入用户名");
    
      return false;
    }

    if($.trim($("#password").val()).length==0){
      showErrorMsg("请输入密码");
      return false;
    }
    if($.trim($("#randCode").val()).length==0){
      showErrorMsg("请输入验证码");
      return false;
    }
    return true;
  }

  //登录处理函数
  function newLogin(orgId) {
    setCookie();
    var actionurl=$('form').attr('action');//提交路径
    var checkurl=$('form').attr('check');//验证路径
    var formData = new Object();
    var data=$(":input").each(function() {
      formData[this.name] =$("#"+this.name ).val();
    });
    formData['orgId'] = orgId ? orgId : "";
    //语言
    formData['langCode']=$("#langCode").val();
    formData['langCode'] = $("#langCode option:selected").val();
    $.ajax({
      async : false,
      cache : false,
      type : 'POST',
      url : checkurl,// 请求的action路径
      data : formData,
      error : function() {// 请求失败处理函数
      },
      success : function(data) {
        var d = $.parseJSON(data);
        if (d.success) {
          if (d.attributes.orgNum > 1) {
          	  //用户拥有多个部门，需选择部门进行登录
        	  var title, okButton;
              if($("#langCode").val() == 'en') {
                title = "Please select Org";
                okButton = "Ok";
              } else {
                title = "请选择组织机构";
                okButton = "确定";
              }
            $.dialog({
              id: 'LHG1976D',
              title: title,
              max: false,
              min: false,
              drag: false,
              resize: false,
              content: 'url:userController.do?userOrgSelect&userId=' + d.attributes.user.id,
              lock:true,
              button : [ {
                name : okButton,
                focus : true,
                callback : function() {
                  iframe = this.iframe.contentWindow;
                  var orgId = $('#orgId', iframe.document).val();

                  formData['orgId'] = orgId ? orgId : "";
                  $.ajax({
              		async : false,
              		cache : false,
              		type : 'POST',
              		url : 'loginController.do?changeDefaultOrg',// 请求的action路径
              		data : formData,
              		error : function() {// 请求失败处理函数
              		},
              		success : function(data) {
              			window.location.href = actionurl;
              		}
                  });

                  this.close();
                  return false;
                }
              }],
              close: function(){
                setTimeout("window.location.href='"+actionurl+"'", 10);
              }
            });
          } else {
            window.location.href = actionurl;
          }
       } else {
    	   var dm=d.msg;
    	   if(dm.indexOf("zzz.nainiupt.com")!=-1){
    		   alert("审核未通过,请重新填写信息");
    		window.location.href="http://www.nainiupt.com/ruzhu1.html";
    	   }else{
			showErrorMsg(d.msg);
    	   }

		  	if(d.msg === "用户名或密码错误" || d.msg === "验证码错误")
		  		reloadRandCodeImage();

        }
      }
    });
  }
  //登录提示消息显示
  function showErrorMsg(msg){	
    $("#errMsgContiner").show();
    $("#showErrMsg").html(msg);    
    window.setTimeout(optErrMsg,3000); 
  }
  /**
   * 刷新验证码
   */
$('#randCodeImage').click(function(){
    reloadRandCodeImage();
});
function reloadRandCodeImage() {
    var date = new Date();
    var img = document.getElementById("randCodeImage");
    img.src='randCodeImage?a=' + date.getTime();
}

  function darkStyle(){
    $('body').attr('class', 'login-layout');
    $('#id-text2').attr('class', 'red');
    $('#id-company-text').attr('class', 'blue');
    e.preventDefault();
  }
  function lightStyle(){
    $('body').attr('class', 'login-layout light-login');
    $('#id-text2').attr('class', 'grey');
    $('#id-company-text').attr('class', 'blue');

    e.preventDefault();
  }
  function blurStyle(){
    $('body').attr('class', 'login-layout blur-login');
    $('#id-text2').attr('class', 'white');
    $('#id-company-text').attr('class', 'light-blue');

    e.preventDefault();
  }
//设置cookie
  function setCookie()
  {
  	if ($('#on_off').val() == '1') {
  		$("input[iscookie='true']").each(function() {
  			$.cookie(this.name, $("#"+this.name).val(), "/",24);
  			$.cookie("COOKIE_NAME","true", "/",24);
  		});
  	} else {
  		$("input[iscookie='true']").each(function() {
  			$.cookie(this.name,null);
  			$.cookie("COOKIE_NAME",null);
  		});
  	}
  }
  //读取cookie
  function getCookie()
  {
  	var COOKIE_NAME=$.cookie("COOKIE_NAME");
  	if (COOKIE_NAME !=null) {
  		$("input[iscookie='true']").each(function() {
  			$($("#"+this.name).val( $.cookie(this.name)));
              if("admin" == $.cookie(this.name)) {
                  $("#randCode").focus();
              } else {
                  $("#password").val("");
                  $("#password").focus();
              }
          });
  		$("#on_off").attr("checked", true);
  		$("#on_off").val("1");
  	} 
  	else
  	{
  		$("#on_off").attr("checked", false);
  		$("#on_off").val("0");
        $("#randCode").focus();
  	}
  }
</script>
<%=lhgdialogTheme %>
</body>
</html>
