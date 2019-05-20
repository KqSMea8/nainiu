<!DOCTYPE html>
<!-- saved from url=(0056)http://localhost:8180/jeecg/demo/jpDemoOrderMain.do?list -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<html lang="en">
<head>
<title>提现</title>
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
					<form class="form-horizontal" role="form" id="dailogForm" action="nMerchantAccountController.do?doAdd" method="POST">
						<input id="id" name="id" type="hidden" value="${nMerchantAccountPage.id }" />
						<input id="createName" name="createName" type="hidden" value="${nMerchantAccountPage.createName }" />
						<input id="createBy" name="createBy" type="hidden" value="${nMerchantAccountPage.createBy }" />
						<input id="updateName" name="updateName" type="hidden" value="${nMerchantAccountPage.updateName }" />
						<input id="updateBy" name="updateBy" type="hidden" value="${nMerchantAccountPage.updateBy }" />
						<input id="updateDate" name="updateDate" type="hidden" value="${nMerchantAccountPage.updateDate }" />
						<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${nMerchantAccountPage.sysOrgCode }" />
						<input id="remarks" name="remarks" type="hidden" value="${nMerchantAccountPage.remarks }" />
						<input id="delFlag" name="delFlag" type="hidden" value="${nMerchantAccountPage.delFlag }" />
						<input id="bpmStatus" name="bpmStatus" type="hidden" value="${nMerchantAccountPage.bpmStatus }" />
						<input id="createDate" name="createDate" type="hidden" value="${nMerchantAccountPage.createDate }" />
						<input id="merchantid" name="merchantid" type="hidden" value="${merchantid }" />
						<input id="merchantname" name="merchantname" type="hidden" value="${merchantname }" />
						<input id="actionmoney" name="actionmoney" type="hidden" value="${actionmoney }" />

						
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;" >金额(单位元，提现金额不能小于0.1元):</label>
								<div class="col-sm-4" style="width:195px;margin-bottom: 20px;" >
									<input  type="text" id="price" name="price" onchange="Javascript:priceChange()" class="form-control" datatype="ns2" value="${nMerchantAccountPage.price }" />
									<span class="Validform_checktip"></span>
								</div>
						</div>
						<div class="form-group mno">
								<label  class="col-sm-2 control-label" style="text-align:center;" >支付宝账户(请仔细确认账户信息):</label>
								<div class="col-sm-4" style="width:195px;margin-bottom: 20px;" >
									<input  type="text" id="account" name="account" class="form-control" datatype="*" value="${account}" />
									<span class="Validform_checktip"></span>
								</div>
						</div>
	                   

										
									<div class="form-group mno">
											<div class="col-sm-offset-1 col-sm-6">
												<button type="button" class="btn btn-default" id="formReturn" data-dismiss="modal" onclick="doUrl('nMerchantAccountController.do?list')">返回</button>
	        									<button type="button"  class="btn btn-primary" id="formSubmit" disabled="disabled" >提交</button>
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
   var actionmoney="${actionmoney}";
   var ispay="${ispay}";
    function  priceChange(){
    	var price=document.getElementById("price").value;
    	//console.log("actionmoney="+actionmoney+"="+price );
    //	console.log(eval(price)>eval(actionmoney) );
    	//alert(price>actionmoney);
    	if(ispay!="0"){
         $("#formSubmit").attr("disabled",true);
         alert("不缴纳保证金不准提现");
       }else if(parseFloat(price)>parseFloat(actionmoney)){
    	//if(eval(price)>eval(actionmoney)){
    		 $("#formSubmit").attr("disabled",true);
    		 alert("提取金额不能大于余额");
    	}else if(parseFloat(price)<parseFloat(0.1)){
   		     $("#formSubmit").attr("disabled",true);
   		     alert("提取金额不能小于0.1元");
   	    }else{
    		 $('#formSubmit').removeAttr("disabled");
    	}
   
    }
    $(document).ready(function(){  
        bindKeyEvent($("#price"));  
       
    });  
    function bindKeyEvent(obj){  
        obj.keyup(function () {  
            var reg = $(this).val().match(/^(([1-9]\d*)|\d)(\.\d{0,2})?$/);  
            var txt = '';  
            if (reg != null) {  
                txt = reg[0];  
            }  
            $(this).val(txt);  
        }).change(function () {  
            $(this).keypress();  
            var v = $(this).val();  
            if (/\.$/.test(v))  
            {  
                $(this).val(v.substr(0, v.length - 1));  
            }  
        });  
    } 
</script> 
