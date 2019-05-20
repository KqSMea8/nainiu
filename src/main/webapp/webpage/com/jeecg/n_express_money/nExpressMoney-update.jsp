<!DOCTYPE html>
<!-- saved from url=(0056)http://localhost:8180/jeecg/demo/jpDemoOrderMain.do?list -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<html lang="en">
<head>
<title>快递包邮模板</title>
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
					<form class="form-horizontal" role="form" id="dailogForm" action="nExpressMoneyController.do?doUpdate" method="POST">
						<input id="id" name="id" type="hidden" value="${nExpressMoneyPage.id }" />
						<input id="createName" name="createName" type="hidden" value="${nExpressMoneyPage.createName }" />
						<input id="createBy" name="createBy" type="hidden" value="${nExpressMoneyPage.createBy }" />
						<input id="updateName" name="updateName" type="hidden" value="${nExpressMoneyPage.updateName }" />
						<input id="updateBy" name="updateBy" type="hidden" value="${nExpressMoneyPage.updateBy }" />
						<input id="updateDate" name="updateDate" type="hidden" value="${nExpressMoneyPage.updateDate }" />
						<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${nExpressMoneyPage.sysOrgCode }" />
						<input id="bpmStatus" name="bpmStatus" type="hidden" value="${nExpressMoneyPage.bpmStatus }" />
						<input id="createDate" name="createDate" type="hidden" value="${nExpressMoneyPage.createDate }" />
						<input id="areaname" name="areaname" type="hidden" value="${nExpressMoneyPage.areaname }" />
						<input id="areaid" name="areaid" type="hidden" value="${nExpressMoneyPage.areaid }" />
					
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">配送区域:</label>
							<div class="col-sm-8" style="font-size: 20px;">
							 ${checkboxPage}
							</div>
						</div>
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">首件费:</label>
							<div class="col-sm-4"  >
							<input  type="text" id="onemoney" name="onemoney" class="form-control" datatype="ns2" value="${nExpressMoneyPage.onemoney}" />
							<span class="Validform_checktip"></span>
							</div>
						</div>
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">续费/件:</label>
							<div class="col-sm-4" >
							<input  type="text" id="nextmoney" name="nextmoney" class="form-control" datatype="ns2" value="${nExpressMoneyPage.nextmoney }" />
							<span class="Validform_checktip"></span>
							</div>
						</div>
						<div class="form-group mno">
							<label  class="col-sm-2 control-label" style="text-align:center;">满几件包邮:</label>
							<div class="col-sm-4"  >
							   <input  type="text" id="fullnumbers" name="fullnumbers" class="form-control" datatype="n" value="${nExpressMoneyPage.price }" />
								<span class="Validform_checktip"></span>
							</div>
							
						</div>
						<div class="form-group mno">
								<div class="col-sm-offset-1 col-sm-6">
									<button type="button" class="btn btn-default" id="formReturn" data-dismiss="modal" onclick="doUrl('nExpressMoneyController.do?list')">返回</button>
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
function clickBjjb() {  
    var checkboxValue= new Array();  
    var checkboxText= new Array();  
    var checkboxStr=document.getElementsByName("bjjb");    
    for(var i=0; i<checkboxStr.length; i++){  
     if(checkboxStr[i].checked){  
             //alert(checkboxStr[i].value+","+checkboxStr[i].nextSibling.nodeValue);  
           checkboxValue.push(checkboxStr[i].value);  
           checkboxText.push(checkboxStr[i].nextSibling.nodeValue);  
      }  
     }   
     //输出值和文本  
      alert("checkboxValue:"+checkboxValue);  
          alert("checkboxText:"+checkboxText);  
        //把获得的数据转换为字符串传递到后台             
//        checkboxValue=checkboxValue.toString();  
//      checkboxText=checkboxText.toString();  
//      window.location='某Action/netWorkingUpdate?checkboxValue='+checkboxValue+"&checkboxText="+checkboxText;  

}   
function  Goodsone(){
	 var checkboxValue= new Array();  
	    var checkboxText= new Array();  
	    var checkboxStr=document.getElementsByName("bjjb");    
	    for(var i=0; i<checkboxStr.length; i++){  
	     if(checkboxStr[i].checked){  
	             //alert(checkboxStr[i].value+","+checkboxStr[i].nextSibling.nodeValue);  
	           checkboxValue.push(checkboxStr[i].value);  
	           checkboxText.push(checkboxStr[i].nextSibling.nodeValue);  
	      }  
	     }   
	     //输出值和文本  
	      //alert("checkboxValue:"+checkboxValue);  
	      //  alert("checkboxText:"+checkboxText);  
	      document.getElementById("areaid").value=checkboxValue;
	      document.getElementById("areaname").value=checkboxText;
}
</script> 
