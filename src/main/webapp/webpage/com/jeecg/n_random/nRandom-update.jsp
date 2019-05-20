<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>随机id过度表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="nRandomController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${nRandomPage.id }">
					<input id="createName" name="createName" type="hidden" value="${nRandomPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${nRandomPage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${nRandomPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${nRandomPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${nRandomPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${nRandomPage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${nRandomPage.sysOrgCode }">
					<input id="remarks" name="remarks" type="hidden" value="${nRandomPage.remarks }">
					<input id="delFlag" name="delFlag" type="hidden" value="${nRandomPage.delFlag }">
					<input id="bpmStatus" name="bpmStatus" type="hidden" value="${nRandomPage.bpmStatus }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								关联对应表的id:
							</label>
						</td>
						<td class="value">
						     	 <input id="pkid" name="pkid" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nRandomPage.pkid}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">关联对应表的id</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								随机生成:
							</label>
						</td>
						<td class="value">
						     	 <input id="random" name="random" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nRandomPage.random}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">随机生成</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/n_random/nRandom.js"></script>		
