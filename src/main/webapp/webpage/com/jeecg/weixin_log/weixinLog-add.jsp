<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>微信数据日记</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinLogController.do?doAdd" >
		<input id="id" name="id" type="hidden" value="${weixinLogPage.id }"/>
		<input id="createName" name="createName" type="hidden" value="${weixinLogPage.createName }"/>
		<input id="createBy" name="createBy" type="hidden" value="${weixinLogPage.createBy }"/>
		<input id="createDate" name="createDate" type="hidden" value="${weixinLogPage.createDate }"/>
		<input id="updateName" name="updateName" type="hidden" value="${weixinLogPage.updateName }"/>
		<input id="updateBy" name="updateBy" type="hidden" value="${weixinLogPage.updateBy }"/>
		<input id="updateDate" name="updateDate" type="hidden" value="${weixinLogPage.updateDate }"/>
		<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${weixinLogPage.sysOrgCode }"/>
		<input id="remarks" name="remarks" type="hidden" value="${weixinLogPage.remarks }"/>
		<input id="delFlag" name="delFlag" type="hidden" value="${weixinLogPage.delFlag }"/>
		<input id="bpmStatus" name="bpmStatus" type="hidden" value="${weixinLogPage.bpmStatus }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							数据详情:
						</label>
					</td>
					<td class="value">
					     	 <input id="details" name="details" type="text" style="width: 150px" class="inputxt" 
					     	  
					     	  ignore="ignore"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">数据详情</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							失败数据:
						</label>
					</td>
					<td class="value">
					     	 <input id="status" name="status" type="text" style="width: 150px" class="inputxt" 
					     	  
					     	  ignore="ignore"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">失败数据</label>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/weixin_log/weixinLog.js"></script>		
