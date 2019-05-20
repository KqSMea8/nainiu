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
		<div id="outerdiv" style="position:fixed;top:0;background:rgba(0,0,0,0.7);z-index:2000;width:100%;height:100%;display:none;">
		<div id="innerdiv" style="position:absolute;width:100%;height:100%;">
		<img id="bigimg" style="border:5px solid #fff;width:100%;height:100%;" src="" /></div></div> 
		<script src="plug-in/mui/js/jquery-1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript" src="plug-in/mui/js/mui.min.js"></script>
		<script type="text/javascript" src="plug-in/mui/js/jquery.flexslider-min.js"></script>
		<script src="plug-in/mui/js/hmt.js" type="text/javascript"></script>
		<script src="plug-in/mui/js/swiper.min.js" type="text/javascript" ></script>
	</body>
<script type="text/javascript">
//客户列表信息
var sendid="${sendid}";
var sendname="${sendname}";
var sendurl="${sendurl}";
var merchantId="${merchantId}";
//服务端商家信息  
var acceptid="${acceptid}";
var acceptname="";
var accepturl="";
var goodid="${goodid}";

var websocket = null;
var type="1"//1图片2发送
$(document).ready(function(){ 
    // 初始化内容
	//document.getElementById("name").value=sendid;
	//获得谈话历史
	//getHistory_list();
	getHelp_list();
	});
				
		//获得谈话历史
		function   getHistory_list(){
			$.ajax({
				url:"websocketcontroller.do?socketidinfo",
				type:"POST",
				async:true,
				data:{socketid:merchantId+sendid,acceptid:acceptid,merchantId:merchantId,page:1,rows: 100},
				datatype:"json",
				success :function (data){
					var json = eval('(' + data + ')');   
					findlist(json);
					
				},error:function(xhr,type,errorThrown){
					mui.toast(type);
				}
			});
			
		}	
		//获得帮助列表
		function   getHelp_list(){
			var htmcontent='<div class="list clearfloat fl"><div class="xia clearfloat">'
                            +'<div class="youctent clearfloat box-s">'
	                            +'<span onclick="getRemind(\'1\')"  >帮助提醒1</span><br>'
	                            +'<span  onclick="getRemind(\'2\')"  >帮助提醒2</span><br>'
                            +'</div>'
                            +' </div>'
                            +'</div>';
		    $("#main").append(htmcontent);
           $('.mui-content').scrollTop($('.mui-content')[0].scrollHeight );
		}
		//获得帮助列表
				function   getRemind(type){
					var htmcontent="";
					if(type=="1"){
						 htmcontent+='<div class="list clearfloat fl"><div class="xia clearfloat">'
		                            +'<div class="youctent clearfloat box-s">'
			                            +'<span   >你按照这个操作就行了，帮助1的内容</span><br>'
		                            +'</div>'
		                            +' </div>'
		                            +'</div>';
					}else if(type=="2"){
						 htmcontent+='<div class="list clearfloat fl"><div class="xia clearfloat">'
	                            +'<div class="youctent clearfloat box-s">'
		                            +'<span   >你按照这个操作就行了，帮助2的内容</span><br>'
	                            +'</div>'
	                            +' </div>'
	                            +'</div>';
					  
					}
					  $("#main").append(htmcontent);
			           $('.mui-content').scrollTop($('.mui-content')[0].scrollHeight );
				}
				 //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://192.168.51.108:8080/jeecg/WebSockete/"+sendid);
      //  alert("23");
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
    	
    //    setMessageInnerHTML("连接成功");
    }

    //接收到消息的回调方法
    websocket.onmessage = function (event) {
       // setMessageInnerHTML(event.data);
    	   var json = JSON.parse(event.data);
    	   alert("json="+json);
        setMessageInnerHTML(json);
    }

  //连接关闭的回调方法
    websocket.onclose = function () {
        alert("WebSocket连接关闭");
        closeWebSocket();
    }

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        closeWebSocket();
    }
 
    //关闭WebSocket连接
    function closeWebSocket() {
        websocket.close();
    }
			
			//将消息显示在网页上
    function setMessageInnerHTML(json) {
        //document.getElementById('main').innerHTML += data + '<br/>';
	  //	var json = eval('(' + data + ')');   
	  	var send_sendid=json.data.sendid;
	  	var send_sendname=json.data.sendname;
	  	var send_sendurl=json.data.sendurl;
	  	var send_acceptid=json.data.acceptid;
	  	var send_acceptname=json.data.acceptname;
	  	var send_accepturl=json.data.accepturl;
	  	var send_merchantId=json.data.merchantId;
	  	var send_details=json.data.details;
	  	var send_goodid=json.data.goodid;
        	
        var htmcontent='';
        if(send_acceptid==sendid){
		     	      	 htmcontent+='<div class="list clearfloat fl">'
										//+'<p class="time">'+create_date+'</p>'
										+'<div class="xia clearfloat">'
											+'<div class="xiaoxitu clearfloat fl">'
												+'<div class="tu clearfloat fl">'
													+'<span></span>'
													+'<img src="'+send_sendurl+'"/>'
												+'</div>'
											+'</div>'
											+'<i class="iconfont icon-shanchu fr"></i>'
											+'<div class="youctent clearfloat fr box-s">'
												+'<samp><img src="plug-in/mui/img/25.png"/></samp>'
											    +send_details
											+'</div>'
										+'</div>'
									+'</div>';
								    
	
		     	      }
		   $("#main").append(htmcontent);
		  /* var h = $(document).height()-$(window).height();
           $(document).scrollTop(h);*/
           $('.mui-content').scrollTop($('.mui-content')[0].scrollHeight );
    }



				function findlist(date) {
					var ary=date.details;
					var htmcontent='';
				   for ( var i = 0;i<ary.length;i++) {
				   	   var obj = ary[i];
		     	      var send_sendid=obj.sendid;
		     	      if(send_sendid==sendid){
		     	      	 htmcontent+='<div class="list listtwo clearfloat fl">'
				                         +'<p class="time">'+obj.create_date+'</p>'
				                         +'<div class="xia clearfloat">'
											 +'<i class="iconfont icon-shanchu icon-shanchuone fl"></i>'
											 +'<div class="youctent clearfloat fl box-s">'
						                             +'<samp><img src="plug-in/mui/img/27.png"/></samp>'
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
		     	      	 
		     	      	 
		     	        if(obj.goodid!=null && obj.goodid!="" ){
			  	    		  getGoods_detaisl(htmcontent,obj.goodid);
			  	    	    }
		     	      }else{
		     	      	 htmcontent+='<div class="list clearfloat fl">'
										+'<p class="time">'+obj.create_date+'</p>'
										+'<div class="xia clearfloat">'
											+'<div class="xiaoxitu clearfloat fl">'
												+'<div class="tu clearfloat fl">'
													+'<span></span>'
													+'<img src="'+obj.sendurl+'"/>'
												+'</div>'
											+'</div>'
											+'<i class="iconfont icon-shanchu fr"></i>'
											+'<div class="youctent clearfloat fr box-s">'
												+'<samp><img src="plug-in/mui/img/25.png"/></samp>'
											    +obj.details
											+'</div>'
										+'</div>'
									+'</div>';
		     	      
								    
	
		     	      }
				  
				   }
				    if(obj.goodid!=null && obj.goodid!="" ){
		  	    	//	  getGoods_detaisl(htmcontent,obj.goodid);
		  	    	  }else{
				        $("#main").html(htmcontent);
		  	    	  }
				}
				
				//获得商品详情
				function   getGoods_detaisl(htmcontent,goodid){
					$.ajax({
						type:"post",
						url : 'jiekoucontroller.do?n_goods_detaisl',// 请求的action路径
						async:false,
						dataType:"json",
						data : {id:goodid },
						success:function(data){
							//alert(data.message);	
							//contentdetails(data.details);
							getStandard_details(htmcontent,goodid,data.title,data.tuanprices,data.unitprices);
						}
					});
				}
				//获得商品规格
				function   getStandard_details(htmcontent,goodid,title,tuanprices,unitprices){
					$.ajax({
						type:"post",
						url : 'jiekoucontroller.do?n_standard_details',// 请求的action路径
						async:false,
						dataType:"json",
						data : {goods_id:goodid },
						success:function(data){
							var tm="";
							var dat_list=data.details;
							   for ( var i = 0;i<dat_list.length;i++) {
							   	   var obj = dat_list[i];
							   	tm+=is_null(obj.standard_onename)+"/"+is_null(obj.standard_twoname)+" 团购价:"+obj.standard_twoname+", 单买价:"+obj.unit_price+"</br>";
							   }
							
						//	var htmcontent='';
							   if(tm!=""){
									 htmcontent+='<div class="list listtwo clearfloat fl">'
					                        // +'<p class="time">'+obj.create_date+'</p>'
					                         +'<div class="xia clearfloat">'
												 +'<i class="iconfont icon-shanchu icon-shanchuone fl"></i>'
												 +'<div class="youctent clearfloat fl box-s">'
							                             +'<samp><img src="plug-in/mui/img/27.png"/></samp>'
													+"商品:"  +title+"</br>"+tm
												 +'</div>'
												 +'<div class="xiaoxitu clearfloat fr">'
														 +'<div class="tu clearfloat fl">'
															 +'<span></span>'
															 +'<img src="'+sendurl+'"/>'
														 +'</div>'
												 +'</div>'
											+'</div>'
									    +'</div>';
							   
							   }else{
									 htmcontent+='<div class="list listtwo clearfloat fl">'
						                        // +'<p class="time">'+obj.create_date+'</p>'
						                         +'<div class="xia clearfloat">'
													 +'<i class="iconfont icon-shanchu icon-shanchuone fl"></i>'
													 +'<div class="youctent clearfloat fl box-s">'
								                             +'<samp><img src="plug-in/mui/img/27.png"/></samp>'
													   +"商品:" +title+"</br>"+" 团购价:"+tuanprices+", 单买价:"+unitprices
													 +'</div>'
													 +'<div class="xiaoxitu clearfloat fr">'
															 +'<div class="tu clearfloat fl">'
																 +'<span></span>'
																 +'<img src="'+sendurl+'"/>'
															 +'</div>'
													 +'</div>'
												+'</div>'
										    +'</div>';
							
							   }
							  // alert("htmcontent");
							   $("#main").append(htmcontent);
							   var h = $(document).height()-$(window).height();
					           $(document).scrollTop(h);
						}
					});
				}		
				
				
				
				
				
				
		function filebtn(){
			var fileimg = document.getElementById("fileimgpic"); 
			fileimg.click(); 
		}

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
		var htmcontent='<div class="list listtwo clearfloat fl">'
                 +'<div class="xia clearfloat">'
					 +'<i class="iconfont icon-shanchu icon-shanchuone fl"></i>'
					 +'<div class="youctent clearfloat fl box-s">'
                             +'<samp><img src="plug-in/mui/img/27.png"/></samp>'
							 +message
					 +'</div>'
					 +'<div class="xiaoxitu clearfloat fr">'
							 +'<div class="tu clearfloat fl">'
								 +'<span></span>'
								 +'<img src="'+sendurl+'"/>'
							 +'</div>'
					 +'</div>'
				+'</div>'
		    +'</div>';
		  $("#main").append(htmcontent);
		   var h = $(document).height()-$(window).height();
          $(document).scrollTop(h);
          if(goodid!=null && goodid!=""){
            getGoods_detaisl(goodid);
          }
		   websocket.send(JSON.stringify({
		        type: 'friend' //随便定义，用于在服务端区分消息类型
		        ,data: {"details":message,
				        "merchantId":merchantId,
				        "goodid":goodid,
				        "socketid":merchantId+sendid,//组合永远是商家和用户
				        "type":"client",
				        
				        "sendid":sendid,
				        "sendname":sendname,
				        "sendurl":sendurl,
				        
				        "acceptid":acceptid,
				        "acceptname":acceptname,
				        "accepturl":accepturl}
		    }));
	   document.getElementById("message").value="";
	     type="1";
	     goodid="";
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
	                   var tml='<img id="img" onclick="imgShow(this)" style="width:40%;height:40%;"src="'+json.url_path+'"/>';
	                   $("#main").append(tml);
					   var h = $(document).height()-$(window).height();
			           $(document).scrollTop(h);
	                   websocket.send(JSON.stringify({
	       		        type: 'friend' //随便定义，用于在服务端区分消息类型
	       		        ,data: {"details":tml,
	       				        "merchantId":merchantId,
	       				        "goodid":goodid,
	       				        "socketid":merchantId+sendid,//组合永远是商家和用户
	       				        "type":"client",
	       				        
	       				        "sendid":sendid,
	       				        "sendname":sendname,
	       				        "sendurl":sendurl,
	       				        
	       				        "acceptid":acceptid,
	       				        "acceptname":acceptname,
	       				        "accepturl":accepturl}
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
//为空判断
function is_null(str){
	if(str==null ||str=="" || str.length==0)
	{
		return ""; 
	}
	return str; 
}	

//时间格式校正
function is_time(str){
	if(str==null ||str=="" || str.length==0)
	{
		return ""; 
	}else if( str.length>19){
		str=str.substring(0,19);
	}
	return str; 
}	

    
</script>
</html>
