<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>评价目录</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="nEvaluateColumController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${nEvaluateColumPage.id }">
					<input id="createName" name="createName" type="hidden" value="${nEvaluateColumPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${nEvaluateColumPage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${nEvaluateColumPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${nEvaluateColumPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${nEvaluateColumPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${nEvaluateColumPage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${nEvaluateColumPage.sysOrgCode }">
					<input id="remarks" name="remarks" type="hidden" value="${nEvaluateColumPage.remarks }">
					<input id="bpmStatus" name="bpmStatus" type="hidden" value="${nEvaluateColumPage.bpmStatus }">
					<input id="delFlag" name="delFlag" type="hidden" value="${nEvaluateColumPage.delFlag }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								商品id:
							</label>
						</td>
						<td class="value">
						     	 <input id="goodsId" name="goodsId" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nEvaluateColumPage.goodsId}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">商品id</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								商品名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="title" name="title" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nEvaluateColumPage.title}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">商品名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								栏目名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="typename" name="typename" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nEvaluateColumPage.typename}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">栏目名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								关联id:
							</label>
						</td>
						<td class="value">
						     	 <input id="evaluateid" name="evaluateid" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nEvaluateColumPage.evaluateid}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">关联id</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/n_evaluate_colum/nEvaluateColum.js"></script>		
