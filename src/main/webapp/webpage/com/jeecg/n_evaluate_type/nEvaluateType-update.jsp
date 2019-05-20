<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>评价类别</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="nEvaluateTypeController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${nEvaluateTypePage.id }">
					<input id="createName" name="createName" type="hidden" value="${nEvaluateTypePage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${nEvaluateTypePage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${nEvaluateTypePage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${nEvaluateTypePage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${nEvaluateTypePage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${nEvaluateTypePage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${nEvaluateTypePage.sysOrgCode }">
					<input id="remarks" name="remarks" type="hidden" value="${nEvaluateTypePage.remarks }">
					<input id="delFlag" name="delFlag" type="hidden" value="${nEvaluateTypePage.delFlag }">
					<input id="bpmStatus" name="bpmStatus" type="hidden" value="${nEvaluateTypePage.bpmStatus }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								类别名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="typename" name="typename" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nEvaluateTypePage.typename}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">类别名称</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/n_evaluate_type/nEvaluateType.js"></script>		
