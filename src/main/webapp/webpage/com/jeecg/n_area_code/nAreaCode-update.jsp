<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>地区编号表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="nAreaCodeController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${nAreaCodePage.id }">
					<input id="createName" name="createName" type="hidden" value="${nAreaCodePage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${nAreaCodePage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${nAreaCodePage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${nAreaCodePage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${nAreaCodePage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${nAreaCodePage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${nAreaCodePage.sysOrgCode }">
					<input id="remarks" name="remarks" type="hidden" value="${nAreaCodePage.remarks }">
					<input id="delFlag" name="delFlag" type="hidden" value="${nAreaCodePage.delFlag }">
					<input id="bpmStatus" name="bpmStatus" type="hidden" value="${nAreaCodePage.bpmStatus }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								地区名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="areaname" name="areaname" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore"  datatype="*1-100"
						     	 value='${nAreaCodePage.areaname}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">地区名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								地区编号:
							</label>
						</td>
						<td class="value">
						     	 <input id="areaid" name="areaid" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore"  datatype="*1-100"
						     	 value='${nAreaCodePage.areaid}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">地区编号</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/n_area_code/nAreaCode.js"></script>		
