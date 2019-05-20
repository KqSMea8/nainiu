<!DOCTYPE html>
<!-- saved from url=(0056)http://localhost:8180/jeecg/demo/jpDemoOrderMain.do?list -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>订单类型</title>
	
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<link href="plug-in/ui/css/bootstrap.css" rel="stylesheet">
    <link href="plug-in/ui/css/style.css" rel="stylesheet">
    <link href="plug-in/ui/css/zTreeStyle.css" type="text/css" rel="stylesheet">
	<link href="plug-in/ui/css/kongjian.css" rel="stylesheet">
    <link rel="stylesheet" href="plug-in/ui/css/font-awesome.css">
    <script type="text/javascript" src="plug-in/ui/js/iFrameResize.js"></script>
   	<script type="text/javascript" src="plug-in/ui/js/jquery-1.9.1.js"></script>
   	<script type="text/javascript" src="plug-in/ui/js/respond.min.js"></script>
   	<script type="text/javascript" src="plug-in/ui/js/bootstrap.js"></script>
   	<script type="text/javascript" src="plug-in/ui/js/common.js"></script>
   	<script type="text/javascript" src="plug-in/ui/js/jquery.form.js"></script>
   	<script type="text/javascript" src="plug-in/ui/js/scrollNav.js"></script>
   	<script type="text/javascript" src="plug-in/ui/js/WdatePicker.js" language="javascript"></script>
   	<link href="plug-in/ui/css/WdatePicker.css" rel="stylesheet" type="text/css">
   	<script type="text/javascript" src="plug-in/ui/js/layer.js" language="javascript"></script>
   	<link rel="stylesheet" href="plug-in/ui/css/layer.css" id="layui_layer_skinlayercss">
	<script type="text/javascript" src = "webpage/com/jeecg/ce/index.js"></script>		
	<script type="text/javascript" src = "plug-in/ui/js/page.js"></script>	
 	
	<style>

    </style>
    
   



</head>
<body style="overflow:scroll;overflow-x:hidden">
	<div class="container bs-docs-container" style="width:100%;">
		<div class="row">
		
				<div class="col-md-10" style="width:100%">
					<div class="panel panel-default">
						<div class="panel-heading">例子</div>
						<div class="panel-body">
							<div class="search">
										 <div class="form-group col-sm-3">
											<label for="goContactName" class="control-label col-sm-3 line34">订单类型</label>
											<div class="col-sm-8">
												<input type="text" name="goContactName" id="goContactName" value="" class="form-control">
											</div>
										 </div>
										  <div class="form-group col-sm-3">
											<label for="goTelphone" class="control-label col-sm-3 line34">订单类型</label>
											<div class="col-sm-8">
												<input type="text" name="goTelphone" id="goTelphone" value="" class="form-control">
											</div>
										 </div>
										 <div class="form-group col-sm-3">
											<label for="goderType" class="control-label col-sm-3 line34">订单类型</label>
											<div class="col-sm-8">
												<!-- //update-begin--Author:zhangjiaqiang  Date:20160906 forï¼ãæä»¶demoãä¸å¯¹å¤ï¼æç»è¡é´è·å¤ªå¤§ -->
												<select name="goderType" id="goderType" class="form-control">
                                                    <option value="">订单类型</option>
    												<option value="1">订单类型</option>
    												<option value="2">订单类型</option>
    											</select>
												<!-- //update-end--Author:zhangjiaqiang  Date:20160906 forï¼ãæä»¶demoãä¸å¯¹å¤ï¼æç»è¡é´è·å¤ªå¤§ -->
											</div>
										 </div>
										 <div class="form-group col-sm-3">
											<label for="goOrderCode" class="control-label col-sm-3 line34">订单类型</label>
											<div class="col-sm-8">
												<input type="text" name="goOrderCode" id="goOrderCode" value="" class="form-control">
											</div>
										 </div>
										 <div class="form-group col-sm-3">
											<label for="goOrderCount" class="control-label col-sm-3 line34">订单类型</label>
											<div class="col-sm-8">
												<input type="text" name="goOrderCount" id="goOrderCount" value="" class="form-control">
											</div>
										 </div>
										 <div class="form-group col-sm-3">
											<label for="usertype" class="control-label col-sm-3 line34">订单类型</label>
											<div class="col-sm-8">
												<!-- //update-begin--Author:zhangjiaqiang  Date:20160906 forï¼ãæä»¶demoãä¸å¯¹å¤ï¼æç»è¡é´è·å¤ªå¤§ -->
												<select name="usertype" id="usertype" class="form-control">
                                                    <option value="">订单类型</option>
    												<option value="1">订单类型</option>
    												<option value="2">订单类型</option>
												</select>
												<!-- //update-end--Author:zhangjiaqiang  Date:20160906 forï¼ãæä»¶demoãä¸å¯¹å¤ï¼æç»è¡é´è·å¤ªå¤§ -->
											</div>
										 </div>
										 <div class="form-group col-sm-3">
											<label for="goContent" class="control-label col-sm-3 line34">订单类型</label>
											<div class="col-sm-8">
												<input type="text" name="goContent" id="goContent" value="" class="form-control">
											</div>
										 </div>
								<button type="buton" class="btn btn-primary" onclick="serchcontent()">查询</button>
								<div class="clearfix"></div>
							</div>
							<div id="legend">
								<legend class="le"><button type="button" class="btn btn-primary" onclick="doUrl(&#39;ceController.do?goedit&#39;)">新增</button></legend> 
							</div>
							<table class="table table-striped">
								<thead>
											
									<tr id="tabletitle">
											<th>订单类型</th>
											<th>订单类型</th>
											<th>订单类型</th>
											<th>订单类型</th>
											<th>订单类型</th>
											<th>订单类型</th>
											<th>订单类型</th>
											<th>订单类型</th>
											<th>订单类型</th>
											<th>订单类型</th>
								        	<th>操作</th>
								     </tr>
								</thead>
								<tbody id="htmcontent">
											<tr >			
														
														<td>201703070102</td>
														<td>订单类型</td>
														<td>订单类型</td>
														<td>18611700000</td>
														<td>10</td>
														<td>æ订单类型</td>
														<td>500.00</td>
														<td>1.00</td>
														<td>2016-09-03</td>
														<td>0</td>
											<td class="last">
											<a href="javascript:doUrl('ceController.do?goedit&id=20E478EF3B38444EBE6D9BF6CF7AD60B&#39;)">编辑</a>
											<a href="javascript:delData(ceController.do?doDelete&"id=20E478EF3B38444EBE6D9BF6CF7AD60B&#39;)">删除</a>
											<a href="javascript:doUrl('ceController.do?detail&id=20E478EF3B38444EBE6D9BF6CF7AD60B&#39;)">详情</a>
											</td>
										</tr>
									 </tbody>
							</table>
							<div class="text-right">
								<!--å¬ç¨ç¿»é¡µä»£ç -->
																
             <ul class="pagination" id="page">
        	<input type="hidden" value="1" name="pageNo" id="pageNo">
        	<li><span>共2条</span></li>
		    <li><span>跳转到<input type="text" value="1" id="gotoPage" onchange="pageNoChange();"></span></li>
		    <li><span>每页显示10条
			  </span>
		    </li>
        
                       
                   <li><a href="http://localhost:8180/jeecg/demo/jpDemoOrderMain.do?list#" class="disable">«</a></li>
                     <li><span>1</span></li>
                          <li><a href="javascript:jQuery('#pageNo').val('2');document.getElementById('formSubmit').submit();">2</a></li>
                      <li><a href="http://localhost:8180/jeecg/demo/jpDemoOrderMain.do?list#" class="disable">»</a></li>
                        
        </ul>
        <script type="text/javascript">

    	</script>
    								
							</div>
						</div>
					</div>
				</div>  

		</div>
	</div>


</body></html>