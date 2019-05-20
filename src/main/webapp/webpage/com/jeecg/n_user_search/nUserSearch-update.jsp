<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>用户收索历史</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="nUserSearchController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${nUserSearchPage.id }">
					<input id="createName" name="createName" type="hidden" value="${nUserSearchPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${nUserSearchPage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${nUserSearchPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${nUserSearchPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${nUserSearchPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${nUserSearchPage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${nUserSearchPage.sysOrgCode }">
					<input id="remarks" name="remarks" type="hidden" value="${nUserSearchPage.remarks }">
					<input id="delFlag" name="delFlag" type="hidden" value="${nUserSearchPage.delFlag }">
					<input id="bpmStatus" name="bpmStatus" type="hidden" value="${nUserSearchPage.bpmStatus }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								用户id:
							</label>
						</td>
						<td class="value">
						     	 <input id="userId" name="userId" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nUserSearchPage.userId}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户id</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								收索名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="title" name="title" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nUserSearchPage.title}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">收索名称</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/n_user_search/nUserSearch.js"></script>		
