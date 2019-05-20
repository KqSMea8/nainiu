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
<script src="webpage/com/jeecg/n_home_banner_one/fileupload.js"></script>
</head>
<body style='overflow:scroll;overflow-x:hidden'>
	<div class="container bs-docs-container" style="width:100%;">
		<div class="row">
			<div class="panel panel-default">
				<div class="panel-heading">编辑</div>
				<div class="panel-body">
					<form class="form-horizontal" role="form" id="dailogForm" action="nHomeBannerOneController.do?doAdd" method="POST">
						<input id="id" name="id" type="hidden" value="${nHomeBannerOnePage.id }" />
						<input id="createName" name="createName" type="hidden" value="${nHomeBannerOnePage.createName }" />
						<input id="createBy" name="createBy" type="hidden" value="${nHomeBannerOnePage.createBy }" />
						<input id="updateName" name="updateName" type="hidden" value="${nHomeBannerOnePage.updateName }" />
						<input id="updateBy" name="updateBy" type="hidden" value="${nHomeBannerOnePage.updateBy }" />
						<input id="updateDate" name="updateDate" type="hidden" value="${nHomeBannerOnePage.updateDate }" />
						<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${nHomeBannerOnePage.sysOrgCode }" />
						<input id="remarks" name="remarks" type="hidden" value="${nHomeBannerOnePage.remarks }" />
						<input id="delFlag" name="delFlag" type="hidden" value="${nHomeBannerOnePage.delFlag }" />
						<input id="bpmStatus" name="bpmStatus" type="hidden" value="${nHomeBannerOnePage.bpmStatus }" />
						<input id="createDate" name="createDate" type="hidden" value="${nHomeBannerOnePage.createDate }" />
						
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">活动名称:</label>
							<div class="col-sm-4" style="width:60%;" >
							<input  type="text" id="title" name="title" class="form-control" datatype="*1-100" value="${nHomeBannerOnePage.title }" />
							<span class="Validform_checktip"></span>
							</div>
						</div>
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;" >活动地址:</label>
								<div class="col-sm-4" style="width:195px;margin-bottom: 20px;" >
									<input  type="text" id="activityurl" name="activityurl" class="form-control" datatype="*1-100" value="${nHomeBannerOnePage.activityurl }" />
									<span class="Validform_checktip"></span>
								</div>
						</div>
	                   <div class="form-group mno">
								
								<label  class="col-sm-2 control-label" style="text-align:center;">图片</label>
								<div class="col-sm-4">
									<img style="width: 109px;height: 109px; overflow: hidden;" id="picurlimg"
									src="${nHomeBannerOnePage.picurl}">
									<a id="picurlUrl"  target="_blank" style="display: none;">预览</a>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<a id="picurldelete" href="javascript:del_pic('picurlUrl','picurlwu','picurldelete','picurl','picurlimg')" style="display: none;">删除</a>
									<input id="picurl" name="picurl" type="hidden" value=""> </a>
									</br>
									<a href="javascript:;" style="padding: 4px 10px;line-height: 20px;position: relative;cursor: pointer; color: #454343;border: 1px solid #ddd; border-radius: 4px; overflow: hidden; display: inline-block;">
										选择文件
										<input type="file" name="picurlfile" id="picurlfile" 
										onchange="Javascript:checkFileChange('30','30000','30000','picurlfile','picurlUrl','picurlwu','picurldelete','picurl','picurlimg');" 
										style="position: absolute; font-size: 100px; right: 0;  top: 0; opacity: 0;cursor: pointer; width: 100%;  height: 100%;">
									</a>

								</div>
							</div>
					

										
									<div class="form-group mno">
											<div class="col-sm-offset-1 col-sm-6">
												<button type="button" class="btn btn-default" id="formReturn" data-dismiss="modal" onclick="doUrl('nHomeBannerOneController.do?list')">返回</button>
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
