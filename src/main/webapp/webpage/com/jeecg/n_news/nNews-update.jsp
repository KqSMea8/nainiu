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
<script type="text/javascript">
var details="${nNewsPage.details}";
</script>
<script src="webpage/com/jeecg/n_news/standardupload.js"></script>
<script src="webpage/com/jeecg/n_news/detailsupload.js"></script>

</head>
<body style='overflow:scroll;overflow-x:hidden'>
	<div class="container bs-docs-container" style="width:100%;">
		<div class="row">
			<div class="panel panel-default">
				<div class="panel-heading">编辑</div>
				<div class="panel-body">
					<form class="form-horizontal" role="form" id="dailogForm" action="nNewsController.do?doUpdate" method="POST">
						<input id="id" name="id" type="hidden" value="${nNewsPage.id }" />
						<input id="createName" name="createName" type="hidden" value="${nNewsPage.createName }" />
						<input id="createBy" name="createBy" type="hidden" value="${nNewsPage.createBy }" />
						<input id="updateName" name="updateName" type="hidden" value="${nNewsPage.updateName }" />
						<input id="updateBy" name="updateBy" type="hidden" value="${nNewsPage.updateBy }" />
						<input id="updateDate" name="updateDate" type="hidden" value="${nNewsPage.updateDate }" />
						<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${nNewsPage.sysOrgCode }" />
						<input id="remarks" name="remarks" type="hidden" value="${nNewsPage.remarks }" />
						<input id="delFlag" name="delFlag" type="hidden" value="${nNewsPage.delFlag }" />
						<input id="bpmStatus" name="bpmStatus" type="hidden" value="${nNewsPage.bpmStatus }" />
						<input id="createDate" name="createDate" type="hidden" value="${nNewsPage.createDate }" />
						<input id="numbs" name="numbs" type="hidden" value="${nNewsPage.numbs }" />
						<input id="nnewtype" name="nnewtype" type="hidden" value="${nNewsPage.nnewtype }" />
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">标题:</label>
							<div class="col-sm-4" style="width:60%;" >
							<input  type="text" id="title" name="title" class="form-control" datatype="*1-200" value="${nNewsPage.title }" />
							<span class="Validform_checktip"></span>
							</div>
						</div>
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">描述:</label>
							<div class="col-sm-4" style="width:60%;" >
								<input  type="text" id="description" name="description" class="form-control" datatype="*1-200" value="${nNewsPage.description }" />
								<span class="Validform_checktip"></span>
							</div>
						</div>
					
						
						<div class="form-group mno">
								<label  class="col-sm-2 control-label" style="text-align:center;">标题主图:
									 <input id="picurl" name="picurl" datatype="*" type="hidden" value="${nNewsPage.picurl }"></label>
								<div class="col-sm-4" style="width: 50%;">
								 <div style=" width: 139px;height: 139px;float: left;display: inline-block;margin-right: 10%;margin-bottom: 2%;">
										 <div class="hidden">
	     							      <input id="fileimgpicurl" type="file" onchange="Javascript:standardChange('30','750','352',1,'fileimgpicurl','picurl','picurlimg','picurlclose');" style="display: none;">
	     							    </div>
	         							<div class="goods-sku-s-b-r-c-img" style="display: inline-block;">
			          							<a href="javascript:filebtn('fileimgpicurl');" style="cursor: pointer; display: inline-block; width: 139px;height: 139px;">
				  							        <img id="picurlimg" src="${nNewsPage.picurl }" style="width: 139px;height: 139px; overflow: hidden;" >
				  							    </a>
	         							       <span  id="picurlclose" onclick="del_pic('picurlclose','picurlimg','picurl',1);" class="goods-sku-s-b-r-c-img-close" style="right: -105px;display:none;" />
	         							 </div>
         							</div>
         							
         							 <div style="  width: 50%;float: left;display: inline-block;margin-right: 2%;margin-bottom: 2%;">
         							<p style="margin-bottom: 20px;">标题主图 </p><p><!-- react-text: 11864 -->a. 尺寸<!-- /react-text --><span style="color: red;">750 x 352px</span></p><p><!-- react-text: 11867 -->b. 大小<!-- /react-text --><span style="color: red;">100k</span><!-- react-text: 11869 -->以内<!-- /react-text --></p><p><!-- react-text: 11871 -->c. 图片格式仅支持<!-- /react-text --><span style="color: red;">JPG,PNG</span><!-- react-text: 11873 -->格式<!-- /react-text --></p><p>d. 图片背景应以纯白为主, 图案居中显示</p><p>e. 图片不可以添加任何品牌相关文字或logo</p>
    								
         							</div>
								</div>
								
						</div>
						

						
						<div class="form-group mno" style="margin-top: 20px;">
						<label  class="col-sm-2 control-label" style="text-align:center;">商品详情图: 
							<input id="detailsOne" name="detailsOne" datatype="*" type="hidden"  value="">
						</label>
						
						<div class="col-sm-4" id="detailslist">
						   <div id="detailsdiv1" style=" width: 109px;height: 109px;float: left;display: inline-block;margin-right: 2%;margin-bottom: 2%;">
								 <div class="hidden">
							      <input id="details" name="details" type="hidden" value="">
							      <input id="fileimgdetails1" type="file" onchange="Javascript:detailsChange('30','30000','30000',1,'fileimgdetails1','details','imgdetails1','closedetails1');" style="display: none;">
							    </div>
    							<div class="goods-sku-s-b-r-c-img" style="display: inline-block;">
	          							<a href="javascript:filebtn('fileimgdetails1');" style="cursor: pointer; display: inline-block; width: 109px;height: 109px;">
		  							        <img id="imgdetails1" src="plug-in/ui/images/shangchuan.png" style="width: 109px;height: 109px; overflow: hidden;" >
		  							    </a>
    							       <span  id="closedetails1" onclick="detailsdel_pic('detailsdiv1','details',1);" class="goods-sku-s-b-r-c-img-close" style="right:-74px;display:none;" />
    							 </div>
							 </div> 
						</div>
						<div class="col-sm-4">
						<p>商品详情图</p><p><!-- react-text: 11943 -->a. 尺寸要求宽度处于<!-- /react-text --><span style="color: red;">480~1200px</span><!-- react-text: 11945 -->，高度<!-- /react-text --><span style="color: red;">1~1500px</span><!-- react-text: 11947 -->之间<!-- /react-text --></p><p><!-- react-text: 11949 -->b. 大小<!-- /react-text --><span style="color: red;">1M</span><!-- react-text: 11951 -->以内<!-- /react-text --></p><p><!-- react-text: 11953 -->c. 数量限制在<!-- /react-text --><span style="color: red;">20张</span><!-- react-text: 11955 -->之间<!-- /react-text --></p><p><!-- react-text: 11957 -->d. 图片格式仅支持<!-- /react-text --><span style="color: red;">JPG,PNG</span><!-- react-text: 11959 -->格式<!-- /react-text --></p><p><!-- react-text: 11961 -->e. 点击上传时，支持<!-- /react-text --><span style="color: red;">批量上传</span><!-- react-text: 11963 -->详情图<!-- /react-text --></p>
						</div>
						
				</div>
										
									<div class="form-group mno">
											<div class="col-sm-offset-1 col-sm-6">
												<button type="button" class="btn btn-default" id="formReturn" data-dismiss="modal" onclick="doUrl('nNewsController.do?list')">返回</button>
	        									<button type="button" class="btn btn-primary" onclick="geSpan()" id="formSubmit">提交</button>
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

function geSpan(){
	var detailslist=document.getElementsByName("details");
	if(detailslist[0].value=="" || detailslist[0].value==null){
		//alert("请上传商品详情图");
	}else{
		document.getElementById("detailsOne").value="123";
	}
}
function filebtn(id){
	
	var fileimg = document.getElementById(id); 
	fileimg.click(); 
}
</script> 
