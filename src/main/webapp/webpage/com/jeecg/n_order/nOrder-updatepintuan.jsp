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
							<div class="panel-heading" style="background-color: #f5f5f5;margin-bottom:15px;margin-top:15px;">商品信息</div>
							<div class="form-group mno">
								<label  class="col-sm-2 control-label" style="text-align:center;">商品名称:</label>
								<div class="col-sm-4">
									<input  type="text" id="goodsname" name="goodsname" class="form-control" value="${nOrderPage.goodsname }" />
									<span class="Validform_checktip"></span>
								</div>
								<label  class="col-sm-2 control-label" style="text-align:center;">拼团人数:</label>
								<div class="col-sm-4">
									<input  type="text" id="ptPerson" name="ptPerson" class="form-control" value="${nOrderPage.ptPerson }" />
									<span class="Validform_checktip"></span>
								</div>
							</div>
							<div class="form-group mno">
								<label  class="col-sm-2 control-label" style="text-align:center;">已助力人数:</label>
								<div class="col-sm-4">
									<input  type="text" id="joinNum" name="joinNum" class="form-control" value="${joinNum}" />
									<span class="Validform_checktip"></span>
								</div>
							</div>
							<div class="form-group col-sm-3" style="display: none;">
									<label  class="control-label col-sm-3 line34">订单状态</label>
									<div class="col-sm-8">
									<t:dictSelect field="orderStatus" id="orderStatus" type="list"
									    typeGroupCode="orderStatus" defaultVal="" hasLabel="false" 
									    title="orderStatus"  datatype="*">
									</t:dictSelect> 
								    </div>
							</div>
							<div class="panel-heading" style="background-color: #f5f5f5;margin-bottom:15px;margin-top:15px;">用户信息</div>
							<div class="form-group mno" style="margin-top:20px;margin-bottom:30px;">
							   <table class="table table-striped">
								<thead>
									<tr id="tabletitle">
									<th>订单号</th>
									<th>用户姓名</th>
									<th>订单状态</th>
									<th>支付方式</th>
									<th>收货地址</th>
									<th>查看订单</th>
									</tr>
								</thead>
								<tbody >
									<c:if test="${fn:length(pt_nOrderPage)> 0 }">
									<c:forEach items="${pt_nOrderPage}" var="poVal" varStatus="stuts">
											<tr>	
											  <td> ${poVal.id } </td>
											  <td> ${poVal.realname } </td>
											  <td id="pt_orderStatus${stuts.index+1 }"> 
											  <script type="text/javascript" >
											  var orderStatus=${poVal.orderStatus };
												var inform = document.getElementById("orderStatus"); 
												for(var j=0;j <inform.options.length;j++){ 
													  var values=inform.options[j].value//参数值 
													  var texts=inform.options[j].text//内容值 
													  console.log(values+"="+texts+"="+orderStatus);
													  if(orderStatus==values){
														//  orderStatusname=texts;
														 // return texts;
														//  document.getElementById("pt_orderStatus").html=texts;
														  $("#pt_orderStatus${stuts.index+1 }").html(texts);
													  }
												}
											  
											 
											  </script>
											  </td>
											  <td> 
												  <c:if test="${poVal.paymode==0}">微信支付</c:if>
												  <c:if test="${poVal.paymode==1}">支付宝支付</c:if>
												  <c:if test="${poVal.paymode==2}">优惠券支付</c:if>
												  <c:if test="${poVal.paymode==3}">微信公众号支付</c:if>
											   </td>
											  <td> ${poVal.area }${poVal.address } </td>
											  <td> 
											  <a href="javascript:doUrl(&#39;nOrderController.do?goDetails&amp;load=detail&amp;hoad=detail&amp;id=${poVal.id }&#39;)">详情</a>
											  </td>
											<tr>	
									</c:forEach>
									</c:if>	
								 </tbody>
							    </table>
							 </div>
							<div class="form-group mno">
								<div class="col-sm-offset-1 col-sm-6">
								<button type="button" class="btn btn-default" id="formReturn" data-dismiss="modal" onclick="history.go(-1)">返回</button>
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
   if(location.href.indexOf("load=detail")!=-1 && location.href.indexOf("hoad=detail")!=-1){
		$("#formSubmit").hide();
		 $('#formReturn').removeAttr("disabled");
		//setTimeout(hidedisabled(),10000);
		
	}

});
</script>