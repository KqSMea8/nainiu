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
					<form class="form-horizontal" role="form" id="dailogForm" action="nCouponController.do?doAdd" method="POST">
						<input id="id" name="id" type="hidden" value="${nCouponPage.id }" />
						<input id="createName" name="createName" type="hidden" value="${nCouponPage.createName }" />
						<input id="createBy" name="createBy" type="hidden" value="${nCouponPage.createBy }" />
						<input id="updateName" name="updateName" type="hidden" value="${nCouponPage.updateName }" />
						<input id="updateBy" name="updateBy" type="hidden" value="${nCouponPage.updateBy }" />
						<input id="updateDate" name="updateDate" type="hidden" value="${nCouponPage.updateDate }" />
						<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${nCouponPage.sysOrgCode }" />
						<input id="remarks" name="remarks" type="hidden" value="${nCouponPage.remarks }" />
						<input id="delFlag" name="delFlag" type="hidden" value="0" />
						<input id="bpmStatus" name="bpmStatus" type="hidden" value="${nCouponPage.bpmStatus }" />
						<input id="createDate" name="createDate" type="hidden" value="${nCouponPage.createDate }" />
						
					
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">优惠劵名称:</label>
							<div class="col-sm-4" style="width:60%;" >
							<input  type="text" id="couponName" name="couponName" class="form-control" datatype="*1-100" value="${nCouponPage.couponName }" />
							<span class="Validform_checktip"></span>
							</div>
						</div>
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">优惠金额:</label>
							<div class="col-sm-4" style="width:60%;" >
							<input  type="text" id="money" name="money" class="form-control" datatype="ns4" value="${nCouponPage.money }" />
							<span class="Validform_checktip"></span>
							</div>
						</div>
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">满多少:</label>
							<div class="col-sm-4" style="width:60%;" >
								<input  type="text" id="fullMoney" name="fullMoney" class="form-control" datatype="ns4" value="${nCouponPage.fullMoney }" />
							<span class="Validform_checktip"></span>
							</div>
						</div>
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">发放数量:</label>
							<div class="col-sm-4" style="width:60%;" >
							<input  type="text" id="numbers" name="numbers" class="form-control" datatype="n" value="${nCouponPage.numbers }" />
							<span class="Validform_checktip"></span>
							</div>
						</div>
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">开始时间:</label>
							<div class="col-sm-4" style="width:60%;" >
							  <input type="text" value='${nCouponPage.startTime}' name="startTime" id="startTime" class="form-control" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" datatype="*" style="background: url('plug-in/ui/images/datetime.png') no-repeat scroll right center transparent;"/>
							<span class="Validform_checktip"></span>
							</div>
						</div>
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">结束时间:</label>
							<div class="col-sm-4" style="width:60%;" >
							  <input type="text" value='${nCouponPage.endTime}' name="endTime" id="endTime" class="form-control" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" datatype="*" style="background: url('plug-in/ui/images/datetime.png') no-repeat scroll right center transparent;"/>
							<span class="Validform_checktip"></span>
							</div>
						</div>
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">优惠劵类型:</label>
							<div class="col-sm-4">
							<t:dictSelect field="couponType" type="list"
							    typeGroupCode="couponType" defaultVal="${nCouponPage.couponType}" hasLabel="false" 
							    title="couponType"  datatype="*">
							</t:dictSelect> 
							</div>
							
						</div>
					
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;" >使用规则:</label>
								<div class="col-sm-4" style="margin-bottom: 20px;" >
								<textarea placeholder="建议300字以内" id="details" name="details"  class="form-control" style="height:100px;" datatype="*1-300" >${nCouponPage.details }</textarea>
									<span class="Validform_checktip"></span>
								</div>
						</div>
	                   

										
									<div class="form-group mno">
											<div class="col-sm-offset-1 col-sm-6">
												<button type="button" class="btn btn-default" id="formReturn" data-dismiss="modal" onclick="doUrl('nCouponController.do?list')">返回</button>
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
