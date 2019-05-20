<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>WebSocket即时通讯</title>
	<!-- 引入CSS文件 -->
	<link rel="stylesheet" type="text/css" href="http://localhost:8380/jeecg/plug-in/ext4/resources/css/ext-all.css">
	<link rel="stylesheet" type="text/css" href="http://localhost:8380/jeecg/plug-in/ext4/shared/example.css" />
	<link rel="stylesheet" type="text/css" href="css/websocket.css" />
	
	<!-- 映入Ext的JS开发包，及自己实现的webscoket. -->
	<script type="text/javascript" src="http://localhost:8380/jeecg/plug-in/ext4/ext-all-debug.js"></script>
	<script type="text/javascript" src="http://localhost:8380/jeecg/webpage/com/jeecg/websocket/websocket.js"></script>
	<script type="text/javascript">
		var user = "FH110";
		function setUser(){
			var guser = document.getElementById("user").value;
			if(guser == ''){
				alert("请输入昵称,别与其它昵称重复");
			}else{
				user = guser;
				creatw();
			}
		}
	</script>
</head>

<body>
	<div id="websocket_button"></div>
	<div>昵称：<input name="user" id="user" type="text" value="" />别重复</div>
	<div><button onclick="setUser();">启动聊天窗口</button></div>
</body>
</html>
