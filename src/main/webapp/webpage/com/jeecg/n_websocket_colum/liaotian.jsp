<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<html lang="en">
<head>
		<title>聊天</title>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, width=device-width">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-touch-fullscreen" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="format-detection" content="telephone=no">
		<meta name="format-detection" content="address=no">
		<link rel="stylesheet" type="text/css" href="plug-in/mui/css/common.css">
		<link rel="stylesheet" type="text/css" href="plug-in/mui/css/mui.min.css"/>
		<link rel="stylesheet" href="plug-in/mui/css/reset.css">
		<link rel="stylesheet" href="plug-in/mui/css/talk.css">

		<style>
    	.mui-bar-nav {
	top: 0;
	-webkit-box-shadow: 0 0px 0px #ccc;
	box-shadow: 0 0px 0px #fff;
	border-bottom:1px solid #ddd;
}
.mui-table-view-cell{margin-top: -55px;padding: 11px 10px;}
.mui-table-view-cell.mui-collapse.mui-active{margin-top: -55px;}
  .mui-table-view-cell.mui-active {
	background-color:transparent; 
}  	
.mui-grid-view.mui-grid-9{
	background-color:transparent; 
}
.mui-grid-view.mui-grid-9 .mui-table-view-cell{
	border: 1px solid #eee;
padding: 0px;
border-radius: 10%;
margin:.06rem;

}
.mui-table-view-cell.mui-collapse .mui-table-view .mui-table-view-cell{
	padding-left: 0px;
}
.mui-table-view-cell.mui-collapse .mui-collapse-content{
	margin: 0px;
padding: 0px;
}
.mui-table-view-cell.mui-collapse .mui-table-view {
	
	margin-top: 11px;
	margin-right: 0px;
margin-bottom: 0px;
margin-left: 0px;
	border: 0;
}
.mui-table-view-cell.mui-collapse .mui-table-view .mui-table-view-cell::after {
	
	 left: 0px; 
	 height: 0px; 
	content: '';
}
.mui-table-view.mui-grid-view .mui-table-view-cell .mui-media-body{
	font-size: 12px;
}

    </style>
	</head>
	<body style="width: 1000px;">
		<header class="mui-bar mui-bar-nav" style="width: 1000px;">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left">返回上一页</a>
			
			<h1 class="mui-title">聊天</h1>
		</header>
		<div class="mui-content" style="padding-bottom: 100px;">
		<div class="huifu clearfloat box-s" id="main" >
			
			
		</div>
		
		<div class="hfctent clearfloat box-s" id="footer" style="width: 1000px;">
			<input type="text" name="message" id="message" value="" onchange="demo()" class="text" style="position: relative;z-index: 9999;">
			<input type="button" name="sendmess" id="sendmess" value="" onclick="opnePic()" class="fr btn" />
		
			<div id="sendpic" style="display: none;">
				<ul class="mui-table-view mui-grid-view mui-grid-9" style="border: 0;">
		            
		            <li class="mui-table-view-cell mui-media mui-col-xs-3 mui-col-sm-3">
		            	 <input id="fileimgpic" type="file"  onchange="Javascript:standardChange('fileimgpic');" style="display: none;">
		            	<a href="javascript:filebtn();">
							<span class="mui-icon iconfont icon-tupian"></span>
		                    <div class="mui-media-body"  >图片</div>
		            	</a>
		            </li>
		           </ul> 
							
						</div>
					</li>
		</div>
		</div>
		<div id="outerdiv" style="position:fixed;top:0;background:rgba(0,0,0,0.7);z-index:2000;width:200%;height:200%;display:none;">
    <div id="innerdiv" style="position:absolute;width:200%;height:200%;">
    <img id="bigimg" style="border:5px solid #fff;" src="" /></div></div> 
		<script src="plug-in/mui/js/jquery-1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript" src="plug-in/mui/js/mui.min.js"></script>
		<script type="text/javascript" src="plug-in/mui/js/jquery.flexslider-min.js"></script>
		<script src="plug-in/mui/js/hmt.js" type="text/javascript"></script>
		<script src="plug-in/mui/js/swiper.min.js" type="text/javascript" ></script>
	</body>
<script type="text/javascript">
	
	
	
	var sendid='${sendid}';
	var acceptid='${acceptid}';
	var sendname='${sendname}';
	var acceptname='${acceptname}';
	var socketid='${socketid}';
	var sendurl="${sendurl}";
	var accepturl="${accepturl}";
    var userid='${userid}';
var websocket = null;
var type="1"//1图片2发送
$(document).ready(function(){ 
    // 初始化内容
	//document.getElementById("name").value=sendid;
	//获得谈话历史
		$.ajax({
					url:"websocketcontroller.do?socketidinfo",
					type:"POST",
					async:true,
					data:{socketid:socketid,sendid:sendid,acceptid:acceptid,page: 1,rows: 100},
					datatype:"json",
					success :function (data){
						var json = eval('(' + data + ')');   
						findlist(json);
						
					},error:function(xhr,type,errorThrown){
						mui.toast(type);
					}
				});
				
				
				 //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://192.168.51.108:8080/jeecg/websocket");
    }
    else {
        alert('当前浏览器 Not support websocket')
    }

    //连接发生错误的回调方法
    websocket.onerror = function () {
     //   setMessageInnerHTML("WebSocket连接发生错误");
    };

    //连接成功建立的回调方法
    websocket.onopen = function () {
    	websocket.send(JSON.stringify({
   		 type: 'open' //随便定义，用于在服务端区分消息类型
   	      , data: {"sendid":sendid,"sendname":sendname,"acceptid":acceptid,"acceptname":acceptname,"details":"",
    		"goodid":"","flag":"","socketid":socketid,"sendurl":sendurl,"accepturl":accepturl}
       }));
    //    setMessageInnerHTML("连接成功");
    }

    //接收到消息的回调方法
    websocket.onmessage = function (event) {
        setMessageInnerHTML(event.data);
    }

    //连接关闭的回调方法
    websocket.onclose = function () {
        //setMessageInnerHTML("WebSocket连接关闭");
    }

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        closeWebSocket();
    }

});  
    //关闭WebSocket连接
    function closeWebSocket() {
    	  websocket.send(JSON.stringify({
    		  type: 'colse' //随便定义，用于在服务端区分消息类型
        	     , data: {"sendid":sendid,"sendname":sendname,"acceptid":acceptid,"acceptname":acceptname,"details":message,
			"goodid":"","flag":"","socketid":socketid,"sendurl":sendurl,"accepturl":accepturl}
          }));
        websocket.close();
    }
			
			//将消息显示在网页上
    function setMessageInnerHTML(data) {
        //document.getElementById('main').innerHTML += data + '<br/>';
      var json = eval('(' + data + ')');   
        	var old_sendid=json.sendid;
        	var old_sendurl=json.sendurl;
        	var old_accepturl=json.accepturl;
        	var create_date=json.create_date;
        	var message=json.message;
        	
        var htmcontent='';
          if(userid==old_sendid){
		     	      	 htmcontent+='<div class="list listtwo clearfloat fl">'
				                         +'<p class="time">'+create_date+'</p>'
				                         +'<div class="xia clearfloat">'
											 +'<i class="iconfont icon-shanchu icon-shanchuone fl"></i>'
											 +'<div class="youctent clearfloat fl box-s">'
						                             +'<samp><img src="img/27.png"/></samp>'
													 +message
											 +'</div>'
											 +'<div class="xiaoxitu clearfloat fr">'
													 +'<div class="tu clearfloat fl">'
														 +'<span></span>'
														 +'<img src="'+old_sendurl+'"/>'
													 +'</div>'
											 +'</div>'
										+'</div>'
								    +'</div>';
		     	      }else{
		     	      	 htmcontent+='<div class="list clearfloat fl">'
										+'<p class="time">'+create_date+'</p>'
										+'<div class="xia clearfloat">'
											+'<div class="xiaoxitu clearfloat fl">'
												+'<div class="tu clearfloat fl">'
													+'<span></span>'
													+'<img src="'+old_accepturl+'"/>'
												+'</div>'
											+'</div>'
											+'<i class="iconfont icon-shanchu fr"></i>'
											+'<div class="youctent clearfloat fr box-s">'
												+'<samp><img src="img/25.png"/></samp>'
											    +message
											+'</div>'
										+'</div>'
									+'</div>';
								    
								    
	
		     	      }
		   $("#main").append(htmcontent);
		   var h = $(document).height()-$(window).height();
           $(document).scrollTop(h);
    }



    //发送消息
    function send() {
        var message = document.getElementById('text').value;
        websocket.send(JSON.stringify({
      		 type: 'send' //随便定义，用于在服务端区分消息类型
      	      , data: {"sendid":sendid,"sendname":sendname,"acceptid":acceptid,"acceptname":acceptname,"details":message,
       		"goodid":"","flag":"","socketid":socketid,"sendurl":sendurl,"accepturl":accepturl}
          }));
    }
				function findlist(date) {
					var ary=date.details;
					var htmcontent='';
				   for ( var i = 0;i<ary.length;i++) {
				   	   var obj = ary[i];
		     	      var sendid=obj.sendid;

		     	      if(sendid==userid){
		     	      	 htmcontent+='<div class="list listtwo clearfloat fl">'
				                         +'<p class="time">'+obj.create_date+'</p>'
				                         +'<div class="xia clearfloat">'
											 +'<i class="iconfont icon-shanchu icon-shanchuone fl"></i>'
											 +'<div class="youctent clearfloat fl box-s">'
						                             +'<samp><img src="img/27.png"/></samp>'
													 +obj.details
											 +'</div>'
											 +'<div class="xiaoxitu clearfloat fr">'
													 +'<div class="tu clearfloat fl">'
														 +'<span></span>'
														 +'<img src="'+obj.sendurl+'"/>'
													 +'</div>'
											 +'</div>'
										+'</div>'
								    +'</div>';
		     	      }else{
		     	      	 htmcontent+='<div class="list clearfloat fl">'
										+'<p class="time">'+obj.create_date+'</p>'
										+'<div class="xia clearfloat">'
											+'<div class="xiaoxitu clearfloat fl">'
												+'<div class="tu clearfloat fl">'
													+'<span></span>'
													+'<img src="'+obj.accepturl+'"/>'
												+'</div>'
											+'</div>'
											+'<i class="iconfont icon-shanchu fr"></i>'
											+'<div class="youctent clearfloat fr box-s">'
												+'<samp><img src="img/25.png"/></samp>'
											    +obj.details
											+'</div>'
										+'</div>'
									+'</div>';
								    
								    
	
		     	      }
				  
				   }
				     $("#main").html(htmcontent);
				}
		function filebtn(){
			var fileimg = document.getElementById("fileimgpic"); 
			fileimg.click(); 
		}
		/**
var socketid="${socketid}";
var sendid="${sendid}";
var acceptid="${acceptid}";
**/

function demo(){ 
	var message=$.trim(document.getElementById("message").value);

	if(message!=""){
        type="2";
		//替换类
	var classVal =document.getElementById("sendmess").getAttribute('class');
        classVal = classVal.replace("fr btn","fr sendbtn");
         document.getElementById("sendmess").setAttribute("class",classVal );
	     document.getElementById("sendpic").style.display="none";
	}else{
        type="1";
			//替换类
			
	var classVal =document.getElementById("sendmess").getAttribute('class');
        classVal = classVal.replace("fr sendbtn","fr btn");
        document.getElementById("sendmess").setAttribute("class",classVal );
		 // document.getElementById("sendpic").style.display="block";
	}
}
var pictype="1";
function opnePic(){
	if(type=="1"){
      document.getElementById("sendpic").style.display="block";
      if(pictype=="2"){
    	  document.getElementById("sendpic").style.display="none";
    	  pictype="1";
      }else{
    	   pictype="2";
      }
	}else{
		 document.getElementById("sendpic").style.display="none";
		var message=document.getElementById("message").value;
  websocket.send(JSON.stringify({
			 type: 'send' //随便定义，用于在服务端区分消息类型
			  , data: {"sendid":sendid,"sendname":sendname,"acceptid":acceptid,"acceptname":acceptname,"details":message,
			"goodid":"","flag":"","socketid":socketid,"sendurl":sendurl,"accepturl":accepturl}
	   }));
	   document.getElementById("message").value="";
	     type="1";
			//替换类	
	var classVal =document.getElementById("sendmess").getAttribute('class');
        classVal = classVal.replace("fr sendbtn","fr btn");
        document.getElementById("sendmess").setAttribute("class",classVal );
	}
}
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
	                  url: 'uplodfil.do?uplodfil/img',
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
	                	      , data: {"sendid":sendid,"sendname":sendname,"acceptid":acceptid,"acceptname":acceptname,"details":tml,
	                 		"goodid":"","flag":"","socketid":socketid,"sendurl":sendurl,"accepturl":accepturl}
	                    }));
	                    	 document.getElementById("sendpic").style.display="none";
	                  },
	                  error: function (data) {
	                      alert("文件上传出错！");
	                      document.getElementById("sendpic").style.display="none";
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
     $("#bigimg").attr("src", src);//设置#bigimg元素的src属性    
      $('#outerdiv').fadeIn("fast");  
 }
 $('#outerdiv').click(function(){//再次点击淡出消失弹出层    
     $(this).fadeOut("fast");    
 }); 
   

    
</script>
</html>
