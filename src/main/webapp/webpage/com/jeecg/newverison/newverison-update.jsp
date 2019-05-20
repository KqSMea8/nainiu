<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>版本更新</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="newverisonController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${newverisonPage.id }">
					<input id="createName" name="createName" type="hidden" value="${newverisonPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${newverisonPage.createBy }">
					<input id="updateName" name="updateName" type="hidden" value="${newverisonPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${newverisonPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${newverisonPage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${newverisonPage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${newverisonPage.sysCompanyCode }">
					<input id="bpmStatus" name="bpmStatus" type="hidden" value="${newverisonPage.bpmStatus }">
					<input id="createDate" name="createDate" type="hidden" value="${newverisonPage.createDate }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								类型:
							</label>
						</td>
						<td class="value">
						     	 <input id="flg" name="flg" type="text" style="width: 150px" class="inputxt"  datatype="*" 
						     	 ignore="checked" 
						     	 value='${newverisonPage.flg}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">类型</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								版本编号:
							</label>
						</td>
						<td class="value">
						     	 <input id="versioncode" name="versioncode" type="text" style="width: 150px" class="inputxt"  datatype="*" 
						     	 ignore="checked" 
						     	 value='${newverisonPage.versioncode}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">版本编号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								版面名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="versionname" name="versionname" type="text" style="width: 150px" class="inputxt"  datatype="*" 
						     	 ignore="checked" 
						     	 value='${newverisonPage.versionname}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">版面名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								下载地址:
							</label>
						</td>
						<td class="value">
						     	 <input id="downloadurl" name="downloadurl" type="text" style="width: 150px" class="inputxt"  datatype="*" 
						     	 ignore="checked" 
						     	 value='${newverisonPage.downloadurl}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">下载地址</label>
						</td>
					</tr>
					   <tr>
						  <td colspan="2">
						    	   <div style="white-space: nowrap;padding: 15px 8px;text-align: center;background-color: #FFF;">
								<input type="submit" value="确定" style="text-shadow: 0 -1px 1px #1c6a9e;height: 30px;letter-spacing: 3px;background: #18a689 none repeat scroll 0 0;border: 1px solid #18a689;color: #fff;padding: 3px 10px 3px 12px;">
								<input type="button" value="返回" onclick="history.go(-1)" style="border: 1px solid #999;height: 30px;letter-spacing: 3px;margin-left: 6px;padding: 3px 10px 3px 12px;">
						 </td>
						</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/newverison/newverison.js"></script>		
