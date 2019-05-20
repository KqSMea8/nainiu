<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
 <head>
 <script type="text/javascript" src="plug-in/jquery/jquery-1.8.0.min.js"></script>
 <script>
 $(document).ready(function() {
	 is_mobileQQ();
 });
	 function is_mobileQQ() {
		 var ua = navigator.userAgent.toLowerCase();
		 if(ua.match(/MicroMessenger/i) == "micromessenger"){
			 if (/iphone/i.test(ua) == false) {
			//	 alert("1"+/iphone/i.test(ua));
					var btn = document.getElementById('J_weixin');
					btn.style.display = 'block';
					 var android_btn = document.getElementById('android');
					     android_btn.style.display='block';  
				     var ios_btn = document.getElementById('ios');
				         ios_btn.style.display='none';   
			 }else{
				//alert("2"+/iphone/i.test(ua));
				 var btn = document.getElementById('J_weixin');
					btn.style.display = 'block';
					 var android_btn = document.getElementById('android');
				     android_btn.style.display='none';  
			     var ios_btn = document.getElementById('ios');
			         ios_btn.style.display='block';  
			 }
		 }else{
			 if(/iphone/i.test(ua) == true){
				   var btn = document.getElementById('J_weixin');
					btn.style.display='none';  
					 document.location="${dow_ios}";
				 
			 }else{
				 var btn = document.getElementById('J_weixin');
					btn.style.display='none';  
				 document.location='${dow_android}';
			 }
			 
			
		 }
/* 		 if (ua.match(/\sQQ/i) == " qq"|| ua.match(/MicroMessenger/i) == "micromessenger" && /iphone/i.test(ua) == false) {
//			 document.location='${rc.getContextPath()}/resources/${download_url}';
				var btn = document.getElementById('J_weixin');
				btn.style.display = 'block';
		 } else {
				var btn = document.getElementById('J_weixin');
				btn.style.display='none';  
			 document.location='${dow_android}';
		 } */
		 }
	 

</script>
 </head>
 <body>
 <div id="J_weixin" style="width:100%;height:100%;display:none;">
 <img id="android" style="width:100%;height:100%;"src="images/bg.png" />
 <img id="ios" style="width:100%;height:100%;"src="images/iphone_bg.png" />
 </div>
 </body>
</html>
