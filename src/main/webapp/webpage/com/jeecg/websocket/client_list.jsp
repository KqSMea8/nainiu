<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>闪购拼团客服平台</title>

<link rel="stylesheet" type="text/css" href="plug-in/websocket/css/qq.css"/>
</head>
<body>

<div class="qqBox">
	<div class="BoxHead">
		<div class="headImg">
			<img src="${sendurl}"/>
		</div>
		<div class="internetName">${sendname}</div>
		<ul>
		<li onclick="javascript:getColseSocket()">退出聊天系统</li>
	</div>
	<div class="context">
		<div class="conLeft">
			<ul id="client_list">
			
			</ul>
		</div>
		<div class="conRight">
			<div class="Righthead">
				<div class="headName"></div>
				<div class="headConfig">
					<ul>
					<li onclick="javascript:getColse()">关闭当前聊天</li>
					<%--
						<li><img src="plug-in/websocket/img/20170926103645_06.jpg"/></li>
						<li><img src="plug-in/websocket/img/20170926103645_08.jpg"/></li>
						<li><img src="plug-in/websocket/img/20170926103645_10.jpg"/></li>
						<li><img src="plug-in/websocket/img/20170926103645_12.jpg"/></li>
						--%>
					</ul>
				</div>
			</div>
			<div class="RightCont">
				<ul class="newsList">
				
				</ul>
			</div>
			<div class="RightFoot">
				<div class="emjon">
					<ul>
						<li><img src="plug-in/websocket/img/em_02.jpg"/></li>
						<li><img src="plug-in/websocket/img/em_05.jpg"/></li>
						<li><img src="plug-in/websocket/img/em_07.jpg"/></li>
						<li><img src="plug-in/websocket/img/em_12.jpg"/></li>
						<li><img src="plug-in/websocket/img/em_14.jpg"/></li>
						<li><img src="plug-in/websocket/img/em_16.jpg"/></li>
						<li><img src="plug-in/websocket/img/em_20.jpg"/></li>
						<li><img src="plug-in/websocket/img/em_23.jpg"/></li>
						<li><img src="plug-in/websocket/img/em_25.jpg"/></li>
						<li><img src="plug-in/websocket/img/em_30.jpg"/></li>
						<li><img src="plug-in/websocket/img/em_31.jpg"/></li>
						<li><img src="plug-in/websocket/img/em_33.jpg"/></li>
						<li><img src="plug-in/websocket/img/em_37.jpg"/></li>
						<li><img src="plug-in/websocket/img/em_38.jpg"/></li>
						<li><img src="plug-in/websocket/img/em_40.jpg"/></li>
						<li><img src="plug-in/websocket/img/em_45.jpg"/></li>
						<li><img src="plug-in/websocket/img/em_47.jpg"/></li>
						<li><img src="plug-in/websocket/img/em_48.jpg"/></li>
						<li><img src="plug-in/websocket/img/em_52.jpg"/></li>
						<li><img src="plug-in/websocket/img/em_54.jpg"/></li>
						<li><img src="plug-in/websocket/img/em_55.jpg"/></li>
					</ul>
				</div>
				<div class="footTop">
					<ul>
					<li class="ExP"><img src="plug-in/websocket/img/20170926103645_33.jpg"/></li>
					<li>
					 <input id="fileimgpic" type="file"  onchange="Javascript:standardChange('fileimgpic');" style="display: none;">
					 <img onclick="javascript:filebtn();" src="plug-in/websocket/img/20170926103645_43.jpg"/>
					</li>
					
					<%--
						<li><img src="plug-in/websocket/img/20170926103645_31.jpg"/></li>
						<li class="ExP"><img src="plug-in/websocket/img/20170926103645_33.jpg"/></li>
						<li><img src="plug-in/websocket/img/20170926103645_35.jpg"/></li>
						<li><img src="plug-in/websocket/img/20170926103645_37.jpg"/></li>
						<li><img src="plug-in/websocket/img/20170926103645_39.jpg"/></li>
						<li><img src="plug-in/websocket/img/20170926103645_41.jpg" alt="" /></li>
						<li><img src="plug-in/websocket/img/20170926103645_43.jpg"/></li>
						<li><img src="plug-in/websocket/img/20170926103645_45.jpg"/></li>
						--%>
					</ul>
				</div>
				<div class="inputBox">
					<textarea id="dope" style="width: 99%;height: 75px; border: none;outline: none;" name="" rows="" cols=""></textarea>
					<button class="sendBtn">发送(s)</button>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="outerdiv" style="position:fixed;top:0;background:rgba(0,0,0,0.7);z-index:2000;width:100%;height:100%;display:none;">
<div id="innerdiv" style="position:absolute;width:100%;height:100%;">
<img id="bigimg" style="border:5px solid #fff;width:100%;height:100%;" src="" /></div></div> 
</body>
<script type="text/javascript" src="plug-in/websocket/js/jquery.min.js"></script>
<script type="text/javascript" src="plug-in/websocket/js/chat.js"></script>
<script type="text/javascript">
var socket = null;
//var socketid="${socketid}";
//var socketid="${sendid}";
var sendid="${sendid}";
var sendname="${sendname}";
var sendurl="${sendurl}";
var merchantId="${merchantId}";
//客户列表信息
var acceptid="";
var acceptname="";
var accepturl="";
var goodid="";
$(document).ready(function(){ 
	getClient_list(sendid);
});
//获得谈话历史
function   getHistory_list(client_id){
	$.ajax({
		url:"websocketcontroller.do?socketidinfo",
		type:"POST",
		async:true,
		data:{socketid:merchantId+client_id,acceptid:client_id,merchantId:merchantId,page:1,rows: 100},
		datatype:"json",
		success :function (data){
			var json = eval('(' + data + ')');   
			findlist(json);
			
		},error:function(xhr,type,errorThrown){
			mui.toast(type);
		}
	});
	
}
//获得客户列表
function   getClient_list(){
	$.ajax({
		type:"post",
		url : 'websocketcontroller.do?client_list',// 请求的action路径
		async:false,
		dataType:"json",
		data : {service_id:sendid },
		success:function(data){
			//alert(data.message);	
			contentdetails(data.details);
		}
	});
}
function contentdetails(date_list){
	
	 var htmcontent='';
		for ( var i = 0;i<date_list.length;i++) {
			var obj = date_list[i];
			var service_flag=is_null(obj.service_flag);
			console.log("dd="+service_flag);
			htmcontent+='<li id="'+obj.sendid+'" class="bg" onclick="javascript:getContact(&#39;'+obj.sendid+'&#39;,&#39;'+obj.sendname+'&#39;,&#39;'+obj.sendurl+'&#39;);" >'
					    +'<div class="liLeft"><img style="width: 43px;height: 43px;display:contents;" src="'+is_null(obj.sendurl)+'"/>'
					    ;
			if(service_flag==1){
				htmcontent+=' <i  id="red'+obj.sendid+'"  style="display: inline-block;font-style: normal;width: 1.429em;height: 1.429em;background: #e7141a;color: #fff;font-size: 0.5em;text-align: center;position: relative;top: -3.8rem;right: -.2rem;border-radius: 50%;line-height: 1.429em;float: right;">1</i>'
					    ;
		        }
			htmcontent+='</div>'
						+'<div class="liRight" style="padding-left:.5rem;">'
							+'<span  class="intername" style="overflow: hidden;white-space: nowrap; text-overflow: ellipsis;width: 100px;">'+is_null(obj.sendname)+'</span>'
							+'<span  class="intername" style="overflow: hidden;white-space: nowrap; text-overflow: ellipsis;width: 100px;">'+timestampToTime(is_null(obj.update_date))+'</span>'
						+'</div>'
					+'</li>';
		}

	$("#client_list").html(htmcontent);
}
//获得商品详情
function   getGoods_detaisl(goodid){
	$.ajax({
		type:"post",
		url : 'jiekoucontroller.do?n_goods_detaisl',// 请求的action路径
		async:true,
		dataType:"json",
		data : {id:goodid },
		success:function(data){
			//alert(data.message);	
			//contentdetails(data.details);
			getStandard_details(goodid,data.title,data.tuanprices,data.unitprices);
		}
	});
}
//获得商品规格
function   getStandard_details(goodid,title,tuanprices,unitprices){
	$.ajax({
		type:"post",
		url : 'jiekoucontroller.do?n_standard_details',// 请求的action路径
		async:true,
		dataType:"json",
		data : {goods_id:goodid },
		success:function(data){
			var tm="";
			var dat_list=data.details;
			   for ( var i = 0;i<dat_list.length;i++) {
			   	   var obj = dat_list[i];
			   	tm+=obj.standard_onename+"/"+obj.standard_twoname+" 团购价:"+obj.standard_twoname+", 单买价:"+obj.unit_price+"</br>";
			   }
			
			var htmcontent='';
			   if(tm!=""){
			    htmcontent+='<li>'+
				   '<div class="nesHead"><img src="'+accepturl+'"/></div>'+
					'<div class="news"><img class="jiao" src="plug-in/websocket/img/20170926103645_03_02.jpg">'
					    +"商品:"  +title+"</br>"+tm
				      +'</div>'+
				    '</li>';
			   }else{
				   htmcontent+='<li>'+
						   '<div class="nesHead"><img src="'+accepturl+'"/></div>'+
							'<div class="news"><img class="jiao" src="plug-in/websocket/img/20170926103645_03_02.jpg">'
							   +"商品:" +title+"</br>"+" 团购价:"+tuanprices+", 单买价:"+unitprices
						      +'</div>'+
						    '</li>';
			   }
//			   alert(htmcontent);
			$('.newsList').append(htmcontent);
			$('.RightCont').scrollTop($('.RightCont')[0].scrollHeight );
		}
	});
}
//历史记录数据
function findlist(date) {
	var ary=date.details;
	var htmcontent='';
   for ( var i = 0;i<ary.length;i++) {
   	   var obj = ary[i];
	      var old_sendid=obj.sendid;
	      if(sendid==old_sendid){
	    	  htmcontent+='<li>'+
						'<div class="answerHead"><img src="' +obj.sendurl+'"/></div>'+
						'<div class="answers"><img class="jiao" src="plug-in/websocket/img/jiao.jpg">'
						 +obj.details+'</div>'+
					'</li>';
	    	
  	      }else{
  	    	  htmcontent+='<li>'+
	  					'<div class="nesHead"><img src="'+obj.sendurl+'"/></div>'+
	  					'<div class="news"><img class="jiao" src="plug-in/websocket/img/20170926103645_03_02.jpg">'
	  					    +obj.details
	  				      +'</div>'+
	  				    '</li>';
	    	  if(obj.goodid!=null && obj.goodid!="" ){
	    		  getGoods_detaisl(obj.goodid);
	    	  }
  	    	
  	      }
   }
	$('.newsList').html(htmcontent);
//	$('.conLeft').find('li.bg').children('.liRight').children('.infor').text(news);
	$('.RightCont').scrollTop($('.RightCont')[0].scrollHeight );
}

function getContact(id,t_acceptname,t_accepturl){
	//alert(id);
	 acceptid=id;
	 acceptname=t_acceptname;
	 accepturl=t_accepturl;
	$('.headName').text(t_acceptname);
	$('.newsList').html("");
	 document.getElementById("red"+id).style.display="none";
	 getHistory_list(id);
}
/**关闭当前聊天*/
function getColse(){
//	 document.body.removeChild(node)
	 //document.body.removeChild(document.getElementById(acceptid));
	if(acceptid!=null && acceptid!=""){
		 acceptid="";
		 acceptname="";
		 accepturl="";
		$("#"+acceptid).remove();
		$('.newsList').html("");
		$('.headName').text("");
	}else{
		alert("请选择客户");
	}
}
/**退出聊天系统*/
 function getColseSocket(){
	closeWebSocket() ;
 }
/**发送消息*/
$('.sendBtn').on('click',function(){
			var news=$('#dope').val();
			if(news==''){
				alert('不能为空');
			}else{
				$('#dope').val('');
			var str='';
			str+='<li>'+
					'<div class="answerHead"><img src="'+sendurl+'"/></div>'+
					'<div class="answers"><img class="jiao" src="plug-in/websocket/img/20170926103645_03_02.jpg">'+news+'</div>'+
				'</li>';
			$('.newsList').append(str);
			//setTimeout(answers,1000); 
			$('.conLeft').find('li.bg').children('.liRight').children('.infor').text(news);
			$('.RightCont').scrollTop($('.RightCont')[0].scrollHeight );
			socket.send(JSON.stringify({
		        type: 'friend' //随便定义，用于在服务端区分消息类型
		        ,data: {"details":news,
				        "merchantId":merchantId,
				        "goodid":goodid,
				        "socketid":merchantId+acceptid,//组合永远是商家和用户
				        "type":"service",
				        
				        "sendid":sendid,
				        "sendname":sendname,
				        "sendurl":sendurl,
				        
				        "acceptid":acceptid,
				        "acceptname":acceptname,
				        "accepturl":accepturl}
		    }));
		}

   })
   
   /**接收消息*/
/*   	function answers(){
		var arr=["你好","今天天气很棒啊","你吃饭了吗？","我最美我最美","我是可爱的僵小鱼","你们忍心这样子对我吗？","spring天下无敌，实习工资850","我不管，我最帅，我是你们的小可爱","段友出征，寸草不生","一入段子深似海，从此节操是路人","馒头：嗷","突然想开个车","段子界混的最惨的两个狗：拉斯，普拉达。。。"];
		var aa=Math.floor((Math.random()*arr.length));
		var answer='';
		answer+='<li>'+
					'<div class="answerHead"><img src="img/tou.jpg"/></div>'+
					'<div class="answers"><img class="jiao" src="img/jiao.jpg">'+arr[aa]+'</div>'+
				'</li>';
		$('.newsList').append(answer);	
		$('.RightCont').scrollTop($('.RightCont')[0].scrollHeight );
	}*/
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
function timestampToTime(timestamp) {
    var date = new Date(timestamp * 1000);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
  //  Y = date.getFullYear() + '-';
    M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
    D = date.getDate() + ' ';
    h = date.getHours() + ':';
//    m = date.getMinutes() + ':';
    m = date.getMinutes();
    s = date.getSeconds();
    return M+D+h+m;
}
//timestampToTime(1403058804);
//判断当前浏览器是否支持WebSocket
if ('WebSocket' in window) {
	socket = new WebSocket("ws://192.168.51.108:8080/jeecg/WebSockete/"+sendid);
}
else {
    alert('当前浏览器 Not support websocket')
}

//连接发生错误的回调方法
socket.onerror = function () {
    setMessageInnerHTML("WebSocket连接发生错误");
};

//连接成功建立的回调方法
socket.onopen = function () {
	/*websocket.send(JSON.stringify({
		 type: 'open' //随便定义，用于在服务端区分消息类型
	      , data: {"sendid":sendid,"sendname":"","acceptid":acceptid,"acceptname":"","details":tname,
		"goodid":"","flag":"","socketid":socketid}
   }));*/
	 //更多情况下，一般是传递一个对象
   /* socket.send(JSON.stringify({
        type: 'friend' //随便定义，用于在服务端区分消息类型
        ,data: {"msg":"mine.content","from":sendid,"to":socketid,"fromName":"mine.username","toName":"to.username"}
    }));*/
    //setMessageInnerHTML("连接成功");
}

//接收到消息的回调方法
socket.onmessage = function (event) {
	   var json = JSON.parse(event.data);
	//   alert("json="+json);
    setMessageInnerHTML(json);
}

//连接关闭的回调方法
socket.onclose = function () {
   // setMessageInnerHTML("WebSocket连接关闭");
    alert("WebSocket连接关闭");
    closeWebSocket();
}

//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
window.onbeforeunload = function () {
    closeWebSocket();
}

//将消息显示在网页上
function setMessageInnerHTML(json) {
	var send_sendid=json.data.sendid;
	var send_sendname=json.data.sendname;
	var send_sendurl=json.data.sendurl;
	var send_acceptid=json.data.acceptid;
	var send_acceptname=json.data.acceptname;
	var send_accepturl=json.data.accepturl;
	var send_details=json.data.details;
	var send_goodid=json.data.goodid;
	 if(send_goodid!=null && send_goodid!="" ){
		  getGoods_detaisl(send_goodid);
	  }
	 var htmcontent="";
	 if(send_sendid==acceptid){
	  htmcontent='<li>'+
				'<div class="nesHead"><img src="'+send_sendurl+'"/></div>'+
				'<div class="news"><img class="jiao" src="plug-in/websocket/img/20170926103645_03_02.jpg">'
				    +send_details
			      +'</div>'+
			    '</li>';
	 
	 }else{
	//其他客户的消息	
		 //id="201803171134037ooixrezj4sgwyk6uq"
				 $("#"+send_sendid).remove();
				var	clinet_htmcontent='<li id="'+send_sendid+'" class="bg" onclick="javascript:getContact(&#39;'+send_sendid+'&#39;,&#39;'+send_sendname+'&#39;,&#39;'+send_sendurl+'&#39;);" >'
								    +'<div class="liLeft"><img style="width: 43px;height: 43px;display:contents;" src="'+send_sendurl+'"/>'
							        +'<i  id="red'+send_sendid+'"  style="display: inline-block;font-style: normal;width: 1.429em;height: 1.429em;background: #e7141a;color: #fff;font-size: 0.5em;text-align: center;position: relative;top: -3.8rem;right: -.2rem;border-radius: 50%;line-height: 1.429em;float: right;">1</i>'
					           	    +'</div>'
									+'<div class="liRight" style="padding-left:.5rem;">'
										+'<span  class="intername" style="overflow: hidden;white-space: nowrap; text-overflow: ellipsis;width: 100px;">'+send_sendname+'</span>'
										+'<span  class="intername" style="overflow: hidden;white-space: nowrap; text-overflow: ellipsis;width: 100px;">'+getNowFormatDate()+'</span>'
									+'</div>'
								   +'</li>';
				 
				 
				   $("#client_list").prepend(clinet_htmcontent);  
	 }
	  $('.newsList').append(htmcontent);
	  $('.RightCont').scrollTop($('.RightCont')[0].scrollHeight );
}
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    //var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
    var currentdate =month + seperator1 + strDate
            + " " + date.getHours() + seperator2+date.getMinutes();
        //    + seperator2 + date.getSeconds();
    return currentdate;
}
//关闭WebSocket连接
  function closeWebSocket() {
/*	 websocket.send(JSON.stringify({
  		 type: 'send' //随便定义，用于在服务端区分消息类型
  	      , data: {"sendid":sendid,"sendname":"","acceptid":acceptid,"acceptname":"","details":"关闭了",
   		"goodid":"","flag":"","socketid":socketid}
      }));*/
		socket.send(JSON.stringify({
	        type: 'friend' //随便定义，用于在服务端区分消息类型
	        ,data: {"details":"退出聊天",
			        "merchantId":merchantId,
			        "goodid":goodid,
			        "socketid":merchantId+acceptid,//组合永远是商家和用户
			        "type":"close",
			        
			        "sendid":sendid,
			        "sendname":sendname,
			        "sendurl":sendurl,
			        
			        "acceptid":acceptid,
			        "acceptname":acceptname,
			        "accepturl":accepturl}
	    }));
		socket.close();
}




function filebtn(){
	var fileimg = document.getElementById("fileimgpic"); 
	fileimg.click(); 
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
	                   var answer="";
	                   answer+='<li>'
	        				+'<div class="answerHead"><img src="img/6.jpg"/></div>'
	        				+'<div class="answers"><img class="jiao" src="plug-in/websocket/img/20170926103645_03_02.jpg">'
	        				   +'<img style="width:40%;height:40%;"  onclick="imgShow(this)" src="'+json.url_path+'" data-preview-src="" data-preview-group="2">'
	                         +'</div>'
	        			   +'</li>';
	               	$('.newsList').append(answer);	
	        		$('.RightCont').scrollTop($('.RightCont')[0].scrollHeight );
	        		socket.send(JSON.stringify({
	    		        type: 'friend' //随便定义，用于在服务端区分消息类型
	    		        ,data: {"details":answer,
	    				        "merchantId":merchantId,
	    				        "goodid":goodid,
	    				        "socketid":merchantId+acceptid,//组合永远是商家和用户
	    				        "type":"service",
	    				        
	    				        "sendid":sendid,
	    				        "sendname":sendname,
	    				        "sendurl":sendurl,
	    				        
	    				        "acceptid":acceptid,
	    				        "acceptname":acceptname,
	    				        "accepturl":accepturl}
	    		    }));
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
    var src =e.src;//获取当前点击的pimg元素中的src属性    
    $('#outerdiv').attr('display','block');  
    $("#bigimg").attr("src", src);//设置#bigimg元素的src属性    
     $('#outerdiv').fadeIn("fast");  
}
$('#outerdiv').click(function(){//再次点击淡出消失弹出层    
    $(this).fadeOut("fast");    
}); 
</script>
</html>