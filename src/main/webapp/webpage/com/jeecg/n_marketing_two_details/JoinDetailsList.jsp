<!DOCTYPE html>
<!-- saved from url=(0056)http://localhost:8180/jeecg/demo/jpDemoOrderMain.do?list -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>9.9特卖商品</title>
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
		 <script src = "webpage/com/jeecg/n_marketing_two_details/JoinDetailsList.js"></script>	
		<script type="text/javascript" src="plug-in/ui/js/page.js"></script>
	
		<style>

		</style>

	</head>

	<body style="overflow:scroll;overflow-x:hidden">
	  <form id="loinForm" class="form-horizontal"   method="post">
	  <div id="form">
	  </div>
	  </form >
		<div class="container bs-docs-container" style="width:100%;">
			<div class="row">
		
				<div class="col-md-10" style="width:100%">
					<div class="panel panel-default">
						<div class="panel-heading">商品信息</div>
						<div class="panel-body">
							<div class="search">
							
								<div class="form-group col-sm-3">
									<label  class="control-label col-sm-3 line34">商品名称</label>
									<div class="col-sm-8">
										<input type="text" name="goodsname" id="goodsname" value="" class="form-control">
									</div>
								</div>
								
								
								<button type="buton" class="btn btn-primary" onclick="serchcontent()">查询</button>
								<div class="clearfix"></div>
							</div>
							<div id="legend">
								<legend class="le">
								<button type="button" class="btn btn-primary" onclick="doUrl(&#39;nMarketingTwoController.do?joinlist&#39;)">返回活动</button>
								<!--
								<button type="button" class="btn btn-primary" onclick="doUrl(&#39;nMarketingTwoDetailsController.do?goAdd&oneid=${oneid}&merchantid=${merchantid}&#39;)">新增</button>
								-->
								<button type="button" class="btn btn-primary" onclick="openUserSelect()">新增</button>
								</legend>
							</div>
							
							<table class="table table-striped">
								<thead>
									<tr id="tabletitle">
									</tr>
								</thead>
								<tbody id="htmcontent">
								</tbody>
							</table>
							<div class="text-right">
             <ul class="pagination" id="page">
             </ul>
        <script type="text/javascript">
    	</script>
							</div>
						</div>
					</div>
				</div>  
			
		</div>
	</div>
</body></html>
<script type="text/javascript" src="plug-in/ui/js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="plug-in/ui/js/forminit.p3.js"></script>
<script type="text/javascript" >


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
		$.dialog({content: 'url:nGoodsDetaislController.do?selectlistpt', zIndex: getzIndex(), title: '用户列表', 
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
				/*var node = rowsData[0];
			  $('#'+numbname).val(node.title);
			  $('#'+numbname).blur();
			  $('#'+numbid).val(node.id+"="+node.title);	 */
				  var formData = new Object();
				var date="";
				for(var i=0;rowsData.length>i;i++){
					 date+='<input id="mode['+i+'].goodsid" name="mode['+i+'].goodsid" type="hidden" value="'+rowsData[i].id+'" />'
						  +'<input id="mode['+i+'].goodsname" name="mode['+i+'].goodsname" type="hidden" value="'+rowsData[i].title+'" />'
						  +'<input id="mode['+i+'].unitprices" name="mode['+i+'].unitprices" type="hidden" value="'+rowsData[i].unitprices+'" />'
						  +'<input id="mode['+i+'].tuanprices" name="mode['+i+'].tuanprices" type="hidden" value="'+rowsData[i].tuanprices+'" />'
                         +'<input id="mode['+i+'].memberprices" name="mode['+i+'].memberprices" type="hidden" value="'+rowsData[i].memberprices+'" />'
						  +'<input id="mode['+i+'].sumNumbers" name="mode['+i+'].sumNumbers" type="hidden" value="'+rowsData[i].sumNumbers+'" />'
						  +'<input id="mode['+i+'].oneid" name="mode['+i+'].oneid" type="hidden" value="'+oneid+'" />'
						  +'<input id="mode['+i+'].merchantid" name="mode['+i+'].merchantid" type="hidden" value="'+merchantid+'" />'
						  
						
				}
				  $("#form").html(date);
				var form = new FormData(document.getElementById("loinForm"));
				$.ajax({
			  		async : false,
			  		cache : false,
			  		type : 'POST',
			  		datatype:"json",
			  		url : 'nMarketingTwoDetailsController.do?dooneAdd',// 请求的action路径
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
			      });
			}
		}
</script> 