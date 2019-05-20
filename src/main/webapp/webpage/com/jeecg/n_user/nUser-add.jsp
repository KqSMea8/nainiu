<!DOCTYPE html>
<!-- saved from url=(0056)http://localhost:8180/jeecg/demo/jpDemoOrderMain.do?list -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<html lang="en">
<head>
<title>件开发平台</title>
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
<script src="webpage/com/jeecg/n_user/fileupload.js"></script>
</head>
<body style='overflow:scroll;overflow-x:hidden'>
	<div class="container bs-docs-container" style="width:100%;">
		<div class="row">
			<div class="panel panel-default">
				<div class="panel-heading">编辑</div>
				<div class="panel-body">
					<form class="form-horizontal" role="form" id="dailogForm" action="nUserController.do?doAdd" method="POST">
						<input id="id" name="id" type="hidden" value="${nUserPage.id }" />
						<input id="createName" name="createName" type="hidden" value="${nUserPage.createName }" />
						<input id="createBy" name="createBy" type="hidden" value="${nUserPage.createBy }" />
						<input id="updateName" name="updateName" type="hidden" value="${nUserPage.updateName }" />
						<input id="updateBy" name="updateBy" type="hidden" value="${nUserPage.updateBy }" />
						<input id="updateDate" name="updateDate" type="hidden" value="${nUserPage.updateDate }" />
						<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${nUserPage.sysOrgCode }" />
						<input id="bpmStatus" name="bpmStatus" type="hidden" value="${nUserPage.bpmStatus }" />
						<input id="createDate" name="createDate" type="hidden" value="${nUserPage.createDate }" />
						<input id="accountStatus" name="accountStatus" type="hidden" value="0" />
						<div class="form-group mno">
						<label  class="col-sm-2 control-label" style="text-align:center;">用户头像:</label>
						<div class="col-sm-4">
							<img style="width: 109px;height: 109px; overflow: hidden;" id="cardZUrlimg"
							src="${nUserPage.usernameurl}">
							<a id="cardZUrlUrl"  target="_blank" style="display: none;">预览</a>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<a id="cardZUrldelete" href="javascript:del_pic('cardZUrlUrl','cardZUrlwu','cardZUrldelete','usernameurl','cardZUrlimg')" style="display: none;">删除</a>
							<input id="usernameurl" name="usernameurl" type="hidden" value="${nUserPage.usernameurl}" datatype="*"> </a>
							<span class="Validform_checktip"></span>
							</br>
							<a href="javascript:;" style="padding: 4px 10px;line-height: 20px;position: relative;cursor: pointer; color: #454343;border: 1px solid #ddd; border-radius: 4px; overflow: hidden; display: inline-block;">
								选择文件
								<input type="file" name="cardZUrlfile" id="cardZUrlfile" 
								onchange="Javascript:checkFileChange('30','30000','30000','cardZUrlfile','cardZUrlUrl','cardZUrlwu','cardZUrldelete','usernameurl','cardZUrlimg');" 
								style="position: absolute; font-size: 100px; right: 0;  top: 0; opacity: 0;cursor: pointer; width: 100%;  height: 100%;">
							</a>
						</div>
						</div>
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">用户昵称:</label>
							<div class="col-sm-4"  >
							<input  type="text" id="realname" name="realname" class="form-control" datatype="*1-100" value="${nUserPage.realname}" />
							<span class="Validform_checktip"></span>
							</div>
						</div>
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">账号(手机号):</label>
							<div class="col-sm-4" >
							<input  type="text" id="username" name="username" class="form-control" datatype="m" value="${nUserPage.username }" />
							<span class="Validform_checktip"></span>
							</div>
							<label  class="col-sm-2 control-label" style="text-align:center;">手机号:</label>
							<div class="col-sm-4" >
								<input  type="text" id="phone" name="phone" class="form-control" datatype="m" value="${nUserPage.phone }" />
								<span class="Validform_checktip"></span>
							</div>
						</div>
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">账号金额:</label>
							<div class="col-sm-4"  >
							   <input  type="text" id="price" name="price" class="form-control" datatype="ns4" value="${nUserPage.price }" />
								<span class="Validform_checktip"></span>
							</div>
							<label  class="col-sm-2 control-label" style="text-align:center;">性别:</label>
							<div class="col-sm-4"  >
								<t:dictSelect field="sex" type="list"
								    typeGroupCode="sex" defaultVal="${nUserPage.sex}" hasLabel="false" 
								    title="sex"  datatype="*">
								</t:dictSelect> 
							</div>
						</div>
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">生日:</label>
							<div class="col-sm-4" >
							 <input type="text" value='${nUserPage.birth}' name="birth" id="birth" class="form-control" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" datatype="*" style="background: url('plug-in/ui/images/datetime.png') no-repeat scroll right center transparent;"/>
								<span class="Validform_checktip"></span>
							</div>
							<label  class="col-sm-2 control-label" style="text-align:center;">用户cid:</label>
							<div class="col-sm-4"  >
								<input  type="text" id="cid" name="cid" class="form-control" datatype="*1-100" value="${nUserPage.cid }" />
								<span class="Validform_checktip"></span>
							</div>
						</div>

					<div class="form-group mno">
						<label  class="col-sm-2 control-label" style="text-align:center;">微信唯一标识:</label>
						<div class="col-sm-4"  >
							<input  type="text" id="unionid" name="unionid" class="form-control" datatype="*1-100" value="${nUserPage.unionid }" />
							<span class="Validform_checktip"></span>
						</div>
					
						<label  class="col-sm-2 control-label" style="text-align:center;">微信qq标识:</label>
						<div class="col-sm-4"  >
							<input  type="text" id="openid" name="openid" class="form-control" datatype="*1-100" value="${nUserPage.openid }" />
							<span class="Validform_checktip"></span>
						</div>
					</div>	
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">常住地:</label>
							<div class="col-sm-4" style="width:60%;" >
								<textarea placeholder="建议200字以内" id="address" name="address"  class="form-control" style="height:100px;" datatype="*1-200" >${nUserPage.address }</textarea>
								
								<span class="Validform_checktip"></span>
							</div>
						</div>
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">个性签名:</label>
							<div class="col-sm-4" style="width:60%;" >
								<textarea placeholder="建议200字以内" id="details" name="details"  class="form-control" style="height:100px;" datatype="*1-200" >${nUserPage.details }</textarea>
								
								<span class="Validform_checktip"></span>
							</div>
						</div>
			
									<div class="form-group mno">
											<div class="col-sm-offset-1 col-sm-6">
												<button type="button" class="btn btn-default" id="formReturn" data-dismiss="modal" onclick="doUrl('nUserController.do?list')">返回</button>
	        									<button type="button" class="btn btn-primary" id="formSubmit">提交</button>
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

</script> 
