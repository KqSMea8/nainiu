<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<html lang="en">
<head>
<title>件开发平台</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<script type="text/javascript" src="plug-in/ui/js/jquery-1.9.1.js"></script>
</head>
<body>
<input id="name" type="text"/><br/>
    <input id="text" type="text"/>
    <button onclick="send()">发送消息</button> 
    <input id="fileimgpic" type="file" 
    onchange="Javascript:standardChange('fileimgpic');" >
    <hr/>
    <!-- <button onclick="closeWebSocket()">关闭WebSocket连接</button> -->
    <hr/>
    <div id="message"></div>
    <!-- 放大后的图片 -->  
    <div id="outerdiv" style="position:fixed;top:0;background:rgba(0,0,0,0.7);z-index:2000;width:200%;height:200%;display:none;">
    <div id="innerdiv" style="position:absolute;width:200%;height:200%;">
    <img id="bigimg" style="border:5px solid #fff;" src="" /></div></div>  
</body>

<script type="text/javascript">
var socketid="${socketid}";
var sendid="${sendid}";
var acceptid="${acceptid}";
var name="";
var tname="2";
var websocket = null;
$(document).ready(function(){ 
    // 初始化内容
	document.getElementById("name").value=sendid;
});  

function standardChange(id){
	 var fileEl=document.getElementById(id);//显示图片的对象 
	 standardtestGeshi(fileEl,id);
}

//验证格式
 function standardtestGeshi(fileEl,id){
 	 var txtImg_url=fileEl.value.toLowerCase();
	 if(txtImg_url.indexOf("jpg")!=-1 || txtImg_url.indexOf("png")!=-1){  
	 	
		    var formData = new FormData();
	         formData.append("file",$("#"+id)[0].files[0]);
	                 $.ajax({
	                  url: 'http://localhost:8080/jeecg/uplodfil.do?uplodfil/img',
	                  type: 'post',
	                  data: formData,
	                  async: true,
	                  cache: false,
	                  contentType: false,
	                  processData: false,
	                  success: function (data) {
	                   var json = eval('(' + data + ')');
	                 //  var url_path=json.url_path;
	                  // document.getElementById(picurlid).value=url_path;
	                   var tml='<img id="img" onclick="imgShow(this)" style="width:40%;height:40%;"src="'+json.url_path+'"/>';
	                   websocket.send(JSON.stringify({
	                		 type: 'send' //随便定义，用于在服务端区分消息类型
	                	      , data: {"sendid":sendid,"sendname":"","acceptid":acceptid,"acceptname":"","details":tml,
	                 		"goodid":"","flag":"","socketid":socketid}
	                    }));
	                  },
	                  error: function (data) {
	                      alert("文件上传出错！");
	                  }
	             });
	 }else{
		alert("格式错误");
	}
 }
 function imgShow(e){  
	// alert(e.id);
   //  var src = e.attr("src");//获取当前点击的pimg元素中的src属性    
     var src =e.src;//获取当前点击的pimg元素中的src属性    
//     var src = document.getElementById("img").src;//获取当前点击的pimg元素中的src属性    
     $('#outerdiv').attr('display','block');  
     $(bigimg).attr("src", src);//设置#bigimg元素的src属性    
      $('#outerdiv').fadeIn("fast");  
 }
 $('#outerdiv').click(function(){//再次点击淡出消失弹出层    
     $(this).fadeOut("fast");    
 }); 
    //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://localhost:8080/jeecg/websocket");
    }
    else {
        alert('当前浏览器 Not support websocket')
    }

    //连接发生错误的回调方法
    websocket.onerror = function () {
        setMessageInnerHTML("WebSocket连接发生错误");
    };

    //连接成功建立的回调方法
    websocket.onopen = function () {
    	websocket.send(JSON.stringify({
   		 type: 'open' //随便定义，用于在服务端区分消息类型
   	      , data: {"sendid":sendid,"sendname":"","acceptid":acceptid,"acceptname":"","details":tname,
    		"goodid":"","flag":"","socketid":socketid}
       }));
        setMessageInnerHTML("连接成功");
    }

    //接收到消息的回调方法
    websocket.onmessage = function (event) {
        setMessageInnerHTML(event.data);
    }

    //连接关闭的回调方法
    websocket.onclose = function () {
        setMessageInnerHTML("WebSocket连接关闭");
    }

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        closeWebSocket();
    }

    //将消息显示在网页上
    function setMessageInnerHTML(data) {
        document.getElementById('message').innerHTML += data + '<br/>';
    }

    //关闭WebSocket连接
  /*  function closeWebSocket() {
    	 websocket.send(JSON.stringify({
      		 type: 'send' //随便定义，用于在服务端区分消息类型
      	      , data: {"sendid":sendid,"sendname":"","acceptid":acceptid,"acceptname":"","details":"关闭了",
       		"goodid":"","flag":"","socketid":socketid}
          }));
        websocket.close();
    }*/

    //发送消息
    function send() {
        var message = document.getElementById('text').value;
        websocket.send(JSON.stringify({
      		 type: 'send' //随便定义，用于在服务端区分消息类型
      	      , data: {"sendid":sendid,"sendname":"","acceptid":acceptid,"acceptname":"","details":message,
       		"goodid":"","flag":"","socketid":socketid}
          }));
    }
</script>
</html>