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
</head>
<body style='overflow:scroll;overflow-x:hidden'>
	<div class="container bs-docs-container" style="width:100%;">
		<div class="row">
			<div class="panel panel-default">
				<div class="panel-heading">编辑</div>
				<div class="panel-body">
					<form class="form-horizontal" role="form" id="dailogForm" action="nOrderController.do?doUpdate" method="POST">
						<input id="id" name="id" type="hidden" value="${nOrderPage.id }" />
						<input id="createName" name="createName" type="hidden" value="${nOrderPage.createName }" />
						<input id="createBy" name="createBy" type="hidden" value="${nOrderPage.createBy }" />
						<input id="updateName" name="updateName" type="hidden" value="${nOrderPage.updateName }" />
						<input id="updateBy" name="updateBy" type="hidden" value="${nOrderPage.updateBy }" />
						<input id="updateDate" name="updateDate" type="hidden" value="${nOrderPage.updateDate }" />
						<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${nOrderPage.sysOrgCode }" />
						<input id="remarks" name="remarks" type="hidden" value="${nOrderPage.remarks }" />
						<input id="delFlag" name="delFlag" type="hidden" value="${nOrderPage.delFlag }" />
						<input id="bpmStatus" name="bpmStatus" type="hidden" value="${nOrderPage.bpmStatus }" />
						<input id="createDate" name="createDate" type="hidden" value="${nOrderPage.createDate }" />
						
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">用户名称:</label>
							<div class="col-sm-4" style="width:60%;" >
							<input  type="text" id="realname" name="realname" class="form-control" datatype="*1-100" value="${nOrderPage.realname}" />
							<span class="Validform_checktip"></span>
							</div>
						</div>
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">商品名称:</label>
							<div class="col-sm-4" style="width:60%;" >
							<input  type="text" id="goodsname" name="goodsname" class="form-control" datatype="*1-200" value="${nOrderPage.goodsname}" />
							<span class="Validform_checktip"></span>
							</div>
						</div>
				
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">订单状态</label>
							<div class="col-sm-4">
								<t:dictSelect field="orderStatus" type="list"
								    typeGroupCode="orderStatus" defaultVal="${nOrderPage.orderStatus}" hasLabel="false" 
								    title="orderStatus"  datatype="*">
								</t:dictSelect> 
							</div>
							
						</div>

						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">商品总价:</label>
							<div class="col-sm-4" style="width:60%;" >
							<input  type="text" id="goodsSum" name="goodsSum" class="form-control" datatype="ns4" value="${nOrderPage.goodsSum}" />
							<span class="Validform_checktip"></span>
							</div>
						</div>
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">数量:</label>
							<div class="col-sm-4" style="width:60%;" >
							<input  type="text" id="numbers" name="numbers" class="form-control" datatype="n" value="${nOrderPage.numbers}" />
							<span class="Validform_checktip"></span>
							</div>
						</div>	
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">支付金额:</label>
							<div class="col-sm-4" style="width:60%;" >
							<input  type="text" id="paySum" name="paySum" class="form-control" datatype="ns4" value="${nOrderPage.paySum}" />
							<span class="Validform_checktip"></span>
							</div>
						</div>

						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">售后原因:</label>
							<div class="col-sm-4" style="width:60%;" >
							<textarea placeholder="建议500字以内" id="details" name="details"  class="form-control" style="height:100px;" datatype="*1-500" >${nOrderPage.details }</textarea>
							
							<span class="Validform_checktip"></span>
							</div>
						</div>
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">快递单号:</label>
							<div class="col-sm-4" style="width:60%;" >
							<input  type="text" id="expressNub" name="expressNub" class="form-control" datatype="*1-100" value="${nOrderPage.expressNub}" />
							<span class="Validform_checktip"></span>
							</div>
						</div>
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">快递名称:</label>
							<div class="col-sm-4" style="width:60%;" >
								<t:dictSelect id="expressName" field="expressName" type="list"
								    typeGroupCode="expressName" defaultVal="${nOrderPage.expressName}" hasLabel="false" 
								    title="expressName"  datatype="*">
								</t:dictSelect> 
								<span class="Validform_checktip"></span>
							</div>
						</div>
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">售后状态</label>
							<div class="col-sm-4">
								<t:dictSelect field="aftersaleStatus" id="aftersaleStatus" type="list"
								    typeGroupCode="aftersaleStatus" defaultVal="${nMerchantPage.aftersaleStatus}" hasLabel="false" 
								    title="aftersaleStatus"  datatype="*">
								</t:dictSelect> 
							</div>
							
						</div>
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">商家回复:</label>
							<div class="col-sm-4" style="width:60%;" >
							<textarea placeholder="建议500字以内" id="merchantBack" name="merchantBack"  class="form-control" style="height:100px;" datatype="*1-500" >${nOrderPage.merchantBack }</textarea>
							<span class="Validform_checktip"></span>
							</div>
						</div>
									<div class="form-group mno">
											<div class="col-sm-offset-1 col-sm-6">
												<button type="button" class="btn btn-default" id="formReturn" data-dismiss="modal" onclick="doUrl('nOrderController.do?ptlist')">返回</button>
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
$(function(){
	   if(location.href.indexOf("load=detail")!=-1){
//			$("#formSubmit").hide();
			 $('#formReturn').removeAttr("disabled");
			 $('#formSubmit').removeAttr("disabled");
			//setTimeout(hidedisabled(),10000);
			 $('#merchantBack').removeAttr("disabled");
			 $('#expressNub').removeAttr("disabled");
			 $('#expressName').removeAttr("disabled");
			 $('#id').removeAttr("disabled");
			 $('#aftersaleStatus').removeAttr("disabled");
		}
	});
</script> 
