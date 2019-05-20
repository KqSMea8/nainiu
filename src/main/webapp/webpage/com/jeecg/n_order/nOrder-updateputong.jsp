<!DOCTYPE html>
<!-- saved from url=(0056)http://localhost:8180/jeecg/demo/jpDemoOrderMain.do?list -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<html lang="en">

	<head>
		<title></title>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<t:base type="jquery,tools"></t:base>
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
		<script src="webpage/com/jeecg/n_merchant/add.js"></script>
		<script src="webpage/com/jeecg/n_merchant/fileupload.js"></script>

	</head>

	<body style='overflow:scroll;overflow-x:hidden'>
		<div class="container bs-docs-container" style="width:100%;">
			<div class="row">
				<div class="panel panel-default">
					<div class="panel-heading">订单详情</div>
					<div class="panel-body">
						<form class="form-horizontal" role="form" id="dailogForm" action="nOrderController.do?doDetails" method="POST">
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
							<div class="panel-heading" style="background-color: #f5f5f5;margin-bottom:15px;margin-top:15px;">用户订单信息</div>
							
							<div class="form-group mno">
								<label  class="col-sm-2 control-label" style="text-align:center;">订单号:</label>
								<div class="col-sm-4">
									<input  type="text" id="id" name="id" class="form-control" value="${nOrderPage.id }" />
									<span class="Validform_checktip"></span>
								</div>
								<label  class="col-sm-2 control-label" style="text-align:center;">用户名称</label>
								<div class="col-sm-4">
								<input  type="text" id="realname" name="realname" class="form-control" value="${nOrderPage.realname }" />
								<span class="Validform_checktip"></span>
								</div>
								
							</div>
							<div class="form-group mno">
							
							<label  class="col-sm-2 control-label" style="text-align:center;">订单状态</label>
							<div class="col-sm-4">
								<t:dictSelect field="orderStatus" id="orderStatus" type="list"
								    typeGroupCode="orderStatus" defaultVal="${nOrderPage.orderStatus}" hasLabel="false" 
								    title="orderStatus"  >
								</t:dictSelect> 
							</div>
								<label  class="col-sm-2 control-label" style="text-align:center;">售后状态</label>
								<div class="col-sm-4">
									<t:dictSelect field="aftersaleStatus" id="aftersaleStatus" type="list"
									    typeGroupCode="aftersaleStatus" defaultVal="${nOrderPage.aftersaleStatus}" hasLabel="false" 
									    title="aftersaleStatus"  >
									</t:dictSelect> 
								</div>
							</div>
							<div class="form-group mno">
								<label  class="col-sm-2 control-label" style="text-align:center;">收件人名称:</label>
								<div class="col-sm-4">
									<input  type="text" id="acceptname" name="acceptname" class="form-control" value="${nOrderPage.acceptname }" />
									<span class="Validform_checktip"></span>
								</div>
								<label  class="col-sm-2 control-label" style="text-align:center;">收件人手机号</label>
								<div class="col-sm-4">
								<input  type="text" id="phone" name="phone" class="form-control" value="${nOrderPage.phone}" />
								<span class="Validform_checktip"></span>
								</div>
							</div>
							<div class="form-group mno">
								<label  class="col-sm-2 control-label" style="text-align:center;">收件人地址:</label>
								<div class="col-sm-4">
									<input  type="text" id="area" name="area" class="form-control"  value="${nOrderPage.area }" />
									<span class="Validform_checktip"></span>
								</div>
								<label  class="col-sm-2 control-label" style="text-align:center;">收件人详细地址:</label>
								<div class="col-sm-4">
									<input  type="text" id="address" name="address" class="form-control"  value="${nOrderPage.address}" />
									 <span class="Validform_checktip"></span>
								</div>
							</div>
							<div class="form-group mno"  style="margin-left:1180px;" >
								<div class="col-sm-4"  >
									<button type="button" id="guigeonebtn" class="btn btn-default"  data-dismiss="modal" style="background-color:#dcd6dc;" onclick="changeUserAddress()">更改用户信息</button>
								</div>
							</div>
							<div class="panel-heading" style="background-color: #f5f5f5;margin-bottom:15px;margin-top:15px;">商品信息</div>
							<div class="form-group mno">
								<label  class="col-sm-2 control-label" style="text-align:center;">商品名称:</label>
								<div class="col-sm-4">
									<input  type="text" id="goodsname" name="goodsname" class="form-control" value="${nOrderPage.goodsname }" />
									<span class="Validform_checktip"></span>
								</div>
								<label  class="col-sm-2 control-label" style="text-align:center;">规格信息:</label>
								<div class="col-sm-4">
									<input  type="text" id="standardname" name="standardname" class="form-control" value="${nOrderPage.standardname }" />
									<span class="Validform_checktip"></span>
								</div>
							</div>
							<div class="form-group mno">
								<label  class="col-sm-2 control-label" style="text-align:center;">商品数量:</label>
								<div class="col-sm-4">
									<input  type="text" id="numbers" name="numbers" class="form-control" value="${nOrderPage.numbers }" />
									<span class="Validform_checktip"></span>
								</div>
								<label  class="col-sm-2 control-label" style="text-align:center;">商品总价格</label>
								<div class="col-sm-4">
								<input  type="text" id="goodsSum" name="goodsSum" class="form-control" value="${nOrderPage.goodsSum }" />
								<span class="Validform_checktip"></span>
								</div>
							</div>
							<div class="form-group mno">
								<label  class="col-sm-2 control-label" style="text-align:center;">支付金额:</label>
								<div class="col-sm-4">
									<input  type="text" id="paySum" name="paySum" class="form-control" value="${nOrderPage.paySum }" />
									<span class="Validform_checktip"></span>
								</div>
								<label  class="col-sm-2 control-label" style="text-align:center;">支付类型</label>
								<div class="col-sm-4">
									<t:dictSelect field="payorderstatus" type="list"
									    typeGroupCode="payorderstatus" defaultVal="${nOrderPage.payorderstatus}" hasLabel="false" 
									    title="payorderstatus"  datatype="*">
									</t:dictSelect> 
								</div>
							</div>
							<div class="form-group mno">
								<label  class="col-sm-2 control-label" style="text-align:center;">拼单类型:</label>
								<div class="col-sm-4">
									<t:dictSelect field="joinordertype" type="list"
									    typeGroupCode="joinordertype" defaultVal="${nOrderPage.joinordertype}" hasLabel="false" 
									    title="joinordertype" >
									</t:dictSelect> 
								</div>
								<label  class="col-sm-2 control-label" style="text-align:center;">优惠券id</label>
								<div class="col-sm-4">
								<input  type="text" id="couponid" name="couponid" class="form-control" value="${nOrderPage.couponid }" />
								<span class="Validform_checktip"></span>
								</div>
							</div>
							
							<div class="panel-heading" style="background-color: #f5f5f5;margin-bottom:15px;margin-top:15px;">
							快递信息(快递信息修改即已发货状态)
							</div>
							<div class="form-group mno">
								
								<label  class="col-sm-2 control-label" style="text-align:center;">快递单号:</label>
								<div class="col-sm-4">
									<input  type="text" id="expressNub" name="expressNub" class="form-control" datatype="*1-100"
									value="${nOrderPage.expressNub }" />
									<span class="Validform_checktip"></span>
								</div>
								<label  class="col-sm-2 control-label" style="text-align:center;">快递名称:</label>
								<div class="col-sm-4">
									<t:dictSelect id="expressName" field="expressName" type="list"
									    typeGroupCode="expressName" defaultVal="${nOrderPage.expressName}" hasLabel="false" 
									    title="expressName"  datatype="*">
									</t:dictSelect> 
									<span class="Validform_checktip"></span>
								</div>
							</div>
							<div class="form-group mno">
								<div class="col-sm-offset-1 col-sm-6">
									<button type="button" class="btn btn-default" id="formReturn" data-dismiss="modal" onclick="doUrl('nOrderController.do?listputong')">返回</button>
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
    	$("#acceptname").removeAttr("disabled");
    	$("#phone").removeAttr("disabled");
   	 	$("#area").removeAttr("disabled");
	    $("#address").removeAttr("disabled");
	    $("#guigeonebtn").removeAttr("disabled");
	   if(location.href.indexOf("load=detail")!=-1 && location.href.indexOf("hoad=detail")!=-1){
			$("#formSubmit").hide();
			 $('#formReturn').removeAttr("disabled");
			//setTimeout(hidedisabled(),10000);
			
		}
	   if(location.href.indexOf("doad=detail")!=-1 && location.href.indexOf("load=detail")!=-1){
			//$("#formSubmit").hide();
			$('#id').removeAttr("disabled");
			 $('#formSubmit').removeAttr("disabled");
			 $('#formReturn').removeAttr("disabled");
			 $('#expressName').removeAttr("disabled");
			 $('#expressNub').removeAttr("disabled");
 		}
});
function	hidedisabled(){
	// document.getElementById(formSubmit).removeAttr("disabled");
	// $('#formReturn').removeAttr("disabled");
	// alert("12");
}
function	selectChange(){
//	var selectvalue=document.getElementById("jingying").value;
	var selectvalue=$("#jingying").find("option:selected").text(); 
	document.getElementById("jingyingname").value=selectvalue;
}
function changeUserAddress() {
    var id = "${nOrderPage.id }";
	var acceptname = $("input[name=acceptname]").val();
    var phone = $("input[name=phone]").val();
    var area = $("input[name=area]").val();
    var address = $("input[name=address]").val();
    if($.trim(acceptname) == ""){
        alert("请填写接收人");
        return;
	}
    if($.trim(phone) == ""){
        alert("请填写接收人手机号");
        return;
    }
    if($.trim(area) == ""){
        alert("请填写接收人地址");
        return;
    }
    if($.trim(address) == ""){
        alert("请填写接收人详细地址");
        return;
    }
    layer.confirm('确定更改地址吗？', {
        btn: ['确定','取消'] //按钮
//	    shade: [0.3, '#393D49'] //不显示遮罩
    }, function(){
        $.ajax({
		url:"nOrderController.do?changeUserAddress",
		data:{id:id,acceptname:acceptname,phone:phone,area:area,address:address},
		type:"POST",
		dataType:"json",
		success:function (date) {
			if(date == "Y"){
                alert("更新成功")
                layer.close(layer.index);
                window.location.href = window.location.href;
            }else{
			    alert("更新失败")
            }
        }
	});
    }, function(){
    });
}
</script>