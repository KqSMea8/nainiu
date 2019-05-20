<!DOCTYPE html>
<!-- saved from url=(0056)http://localhost:8180/jeecg/demo/jpDemoOrderMain.do?list -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<html lang="en">
	<head>
		<title></title>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<t:base type="jquery,easyui,tools"></t:base>
		<!-- ztree -->
		<link rel="stylesheet" type="text/css" href="plug-in/ztree/css/zTreeStyle.css"></link>
		<script type="text/javascript" src="plug-in/ztree/js/jquery.ztree.core-3.5.min.js" ></script>
		<script type="text/javascript" src="plug-in/ztree/js/jquery.ztree.excheck-3.5.min.js"></script>
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
		 var merchantid="${merchantid}";
		 var oneid="${oneid}";
	     </script>

	</head>

	<body style="overflow:scroll;overflow-x:hidden">
	 
	  
	
		<div class="container bs-docs-container" style="width:100%;">
			<div class="row">
					<div class="panel panel-default">
						<div class="panel-heading">商品信息</div>
						<div class="panel-body">
						 <form role="form" id="dailogForm" class="form-horizontal"  action="nMarketingThreeDetailsController.do?dojoinlistadd" method="post">
							<div id="legend">
								<legend class="le">
								<button type="button" class="btn btn-primary" onclick="openUserSelect()">新增</button>
								<button type="button" style="margin-left: 60%;"class="btn btn-primary" id="formReturn" data-dismiss="modal" onclick="history.go(-1)">返回活动</button>
								<button type="button" style="margin-left: 10px;" class="btn btn-primary" id="formSubmit">提交</button>
								</legend>
							</div>
							<div class="form-group mno">
							
							<table class="table table-striped">
								<thead>
								<tr id="tabletitle">
								     <th>商品名称</th>
								     <th>团购价范围 </th>
								     <th>单买价范围</th>
								     <th>总库存</th>
								    
								  </tr>
								</thead>
								<tbody id="htmcontent">
								</tbody>
							</table>
							 
							<div class="text-right">
				             <ul class="pagination" id="page">
				             </ul>
				             </div>
					 </form >
						</div>
					</div>
				</div>  
			

	</div>
	
</body></html>
<script type="text/javascript" src="plug-in/ui/js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="plug-in/ui/js/forminit.p3.js"></script>
<script type="text/javascript" >
var sum=0;

var windowapi;
try{
	windowapi = frameElement.api, 
	W = windowapi.opener;
	}catch(e){
	}
function openUserSelect() {
	    //numbid=numb_id;
	    //numbname=numb_name;
		$.dialog.setting.zIndex = getzIndex(); 
		$.dialog({content: 'url:nGoodsDetaislController.do?selectlist', zIndex: getzIndex(), title: '商品信息', 
				lock: true,parent:windowapi, width: '600px', height: '550px', opacity: 0.4,button: [
		   {name: '确定', callback: callbackDepartmentSelect, focus: true},
		   {name: '取消', callback: function (){}}
	   ]}).zindex();
	}
		
	function callbackDepartmentSelect() {
		  var iframe = this.iframe.contentWindow;
			var rowsData = iframe.$('#userList1').datagrid('getSelections');
			if (!rowsData || rowsData.length==0) {
			tip('<t:mutiLang langKey="common.please.select.edit.item"/>');return;
			} 
			if(rowsData.length>0){
				var date="";
				for(var i=0;rowsData.length>i;i++){
					//var intege=i+1;
					var id=rowsData[i].id;
					var t=document.getElementById(id);
					console.log(t);
				
					if(t==null){
						
					date+='<tr >	'
					      +'<input id="'+rowsData[i].id+'" type="hidden" value="'+rowsData[i].id+'" />'
						//  +'<td>'+intege+'</td>'
					      +'<td>'
							 +'<input id="mode['+sum+'].oneid" name="mode['+sum+'].oneid" type="hidden" value="'+oneid+'" />'
							 +'<input id="mode['+sum+'].merchantid" name="mode['+sum+'].merchantid" type="hidden" value="'+merchantid+'" />'
							 +'<input id="mode['+sum+'].goodsid" name="mode['+sum+'].goodsid" type="hidden" value="'+rowsData[i].id+'" />'
						     +'<input id="mode['+sum+'].goodsname" name="mode['+sum+'].goodsname" type="hidden" value="'+rowsData[i].title+'" />'
						     +rowsData[i].title
						  +'</td>'
						  +'<td>'+rowsData[i].unitprices
						     +'<input id="mode['+sum+'].unitprices" name="mode['+sum+'].unitprices" type="hidden" value="'+rowsData[i].unitprices+'" />'
						  +'</td>'
						  +'<td>'+rowsData[i].tuanprices
						     +'<input id="mode['+sum+'].tuanprices" name="mode['+sum+'].tuanprices" type="hidden" value="'+rowsData[i].tuanprices+'" />'
						  +'</td>'
						  +'<td>'+rowsData[i].sumNumbers
						    +'<input  id="mode['+sum+'].sumNumbers" name="mode['+sum+'].sumNumbers" type="hidden" value="'+rowsData[i].sumNumbers+'" />'
						  +'</td>'
//						  +'<td>'
//						    +'<input style="width:100%;" id="mode['+sum+'].numbers" name="mode['+sum+'].numbers" type="text"  datatype="n" value=""  /> <span class="Validform_checktip"></span> '
//						  +'</td>'
						  
				       +'</tr>';
					   sum+=1;
					}
						
				}
				  $("#htmcontent").append(date);
			//	var form = new FormData(document.getElementById("loinForm"));
			/*	$.ajax({
			  		async : false,
			  		cache : false,
			  		type : 'POST',
			  		datatype:"json",
			  		url : 'nMarketingThreeDetailsController.do?dooneAdd',// 请求的action路径
			  		data : form,//多条件排序sort:createDate,userNameorder:asc,desc
			  	    processData:false,
	                contentType:false,
			  		error : function() {// 请求失败处理函数
			  		},
			  		success : function(data) {
			  			var m=eval('(' + data + ')').message;
			  			parent.layer.alert(m, {
		        	        icon: 1,
		        	        shadeClose: false,
		        	        title: '提示'
		        	    },function(index){
		        	    	 serchcontent();
		        	    	parent.layer.close(index);
		        	    });
			  		}
			      });*/
			}
		}
</script> 