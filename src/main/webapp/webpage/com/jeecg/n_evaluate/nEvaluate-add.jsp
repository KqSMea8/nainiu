<!DOCTYPE html>
<!-- saved from url=(0056)http://localhost:8180/jeecg/demo/jpDemoOrderMain.do?list -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<html lang="en">
<head>
<title>评价管理3333</title>
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
<script type="text/javascript" >
var carousel="${nEvaluatePage.evaluatepic}";
</script> 

<script src="webpage/com/jeecg/n_evaluate/fileupload.js"></script>
</head>
<body style='overflow:scroll;overflow-x:hidden'>
	<div class="container bs-docs-container" style="width:100%;">
		<div class="row">
			<div class="panel panel-default">
				<div class="panel-heading">评价新增编辑</div>
				<div class="panel-body">
					<form class="form-horizontal" role="form" id="dailogForm" action="nEvaluateController.do?doAdd" method="POST">
						<input id="id" name="id" type="hidden" value="${nEvaluatePage.id }" />
						<input id="createName" name="createName" type="hidden" value="${nEvaluatePage.createName }" />
						<input id="createBy" name="createBy" type="hidden" value="${nEvaluatePage.createBy }" />
						<input id="updateName" name="updateName" type="hidden" value="${nEvaluatePage.updateName }" />
						<input id="updateBy" name="updateBy" type="hidden" value="${nEvaluatePage.updateBy }" />
						<input id="updateDate" name="updateDate" type="hidden" value="${nEvaluatePage.updateDate }" />
						<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${nEvaluatePage.sysOrgCode }" />
						<%--<input id="remarks" name="remarks" type="hidden" value="${nEvaluatePage.remarks }" />--%>
						<input id="delFlag" name="delFlag" type="hidden" value="${nEvaluatePage.delFlag }" />
						<input id="bpmStatus" name="bpmStatus" type="hidden" value="${nEvaluatePage.bpmStatus }" />
						<input id="createDate" name="createDate" type="hidden" value="${nEvaluatePage.createDate }" />
						<input id="evaluateStatus" name="evaluateStatus" type="hidden" value="0" />
						
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">商品名称:</label>
							<div class="col-sm-4" style="width:60%;" >
							<input id="goodsId" name="goodsId" type="hidden" value="${nEvaluatePage.goodsId }" />
							<input  type="text" id="title" name="title" readonly="readonly"onclick="openUserSelect()" class="form-control" datatype="*" value="${nEvaluatePage.title }" />
							<span class="Validform_checktip"></span>
							</div>
						</div>
						<%--<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">用户名称:</label>
							<div class="col-sm-4" style="width:60%;" >
							<input id="userId" name="userId" type="hidden" value="${nEvaluatePage.userId }" />
							<input id="usernameurl" name="usernameurl" type="hidden" value="${nEvaluatePage.usernameurl }" />
							<input  type="text" id="realname" name="realname" readonly="readonly"
							onclick="openUserSelect()"  class="form-control"  value="${nEvaluatePage.realname }" />
							<span class="Validform_checktip"></span>
							</div>
						</div>--%>
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">用户名称:</label>
							<div class="col-sm-4" style="width:60%;" >
								<input  type="text" id="realname" name="realname" class="form-control"  value="${nEvaluatePage.realname }" />
								<span class="Validform_checktip"></span>
							</div>
						</div>
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">评价内容:</label>
							<div class="col-sm-4" style="width:60%;" >
							<textarea placeholder="建议300字以内" id="remarks" name="remarks"  class="form-control" style="height:100px;" datatype="*1-300" >${nGoodsDetaislPage.remarks}</textarea>
							<span class="Validform_checktip"></span>
							</div>
						</div>
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;" >描述相符(星级):</label>
								<div class="col-sm-4" style="width:195px;margin-bottom: 20px;" >
									<input  type="text" id="miaosu" name="miaosu" class="form-control"  value="${nEvaluatePage.miaosu }" />
								</div>
						</div>
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;" >物流服务(星级):</label>
								<div class="col-sm-4" style="width:195px;margin-bottom: 20px;" >
									<input  type="text" id="logistics" name="logistics" class="form-control"  value="${nEvaluatePage.logistics }" />
								</div>
						</div>
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;" >服务态度(星级):</label>
								<div class="col-sm-4" style="width:195px;margin-bottom: 20px;" >
									<input  type="text" id="serve" name="serve" class="form-control"  value="${nEvaluatePage.serve }" />
								</div>
						</div>
	                   <div class="form-group mno"   id="carouselist" >
	                           
								<label  class="col-sm-2 control-label" style="text-align:center;">图片</label>
								<div class="col-sm-4"  id="carouseldiv1" style="width: 20%;">
									<img style="width: 109px;height: 109px; overflow: hidden;" id="picurlimg1"
									src="${nEvaluatePage.evaluatepic}">
									<a id="picurlUrl1"  target="_blank" style="display: none;">预览</a>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<a id="picurldelete1" href="javascript:carouseldel_pic('carouseldiv1','evaluatepic',1)"  style="display: none;">删除</a>
									<input id="evaluatepic" name="evaluatepic" type="hidden" value="${nEvaluatePage.evaluatepic}"> </a>
									</br>
									<a href="javascript:;" style="padding: 4px 10px;line-height: 20px;position: relative;cursor: pointer; color: #454343;border: 1px solid #ddd; border-radius: 4px; overflow: hidden; display: inline-block;">
										选择文件
										<input type="file" name="picurlfile1" id="picurlfile1" 
										onchange="Javascript:checkFileChange('30','30000','30000',1,'picurlfile1','picurlUrl1','picurlwu1','picurldelete1','evaluatepic','picurlimg1');" 
										style="position: absolute; font-size: 100px; right: 0;  top: 0; opacity: 0;cursor: pointer; width: 100%;  height: 100%;">
									</a>

								</div>
								
							</div>
					

										
									<div class="form-group mno">
											<div class="col-sm-offset-1 col-sm-6">
												<button type="button" class="btn btn-default" id="formReturn" data-dismiss="modal" onclick="doUrl('nEvaluateController.do?list')">返回</button>
	        									<button type="button" class="btn btn-primary"  id="formSubmit">提交</button>
        									 </div>
        							</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
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
		$.dialog({content: 'url:nGoodsDetaislController.do?selectAlllist', zIndex: getzIndex(), title: '商品列表', 
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
				var node = rowsData[0];
			  $('#title').val(node.title);
			  $('#title').blur();
			  $('#goodsId').val(node.id);	 
				 
			}
		}
</script> 
