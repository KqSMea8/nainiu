<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>手机验证码</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="nPhoneCodeController.do?doAdd" >
		<input id="id" name="id" type="hidden" value="${nPhoneCodePage.id }"/>
		<input id="createName" name="createName" type="hidden" value="${nPhoneCodePage.createName }"/>
		<input id="createBy" name="createBy" type="hidden" value="${nPhoneCodePage.createBy }"/>
		<input id="createDate" name="createDate" type="hidden" value="${nPhoneCodePage.createDate }"/>
		<input id="updateName" name="updateName" type="hidden" value="${nPhoneCodePage.updateName }"/>
		<input id="updateBy" name="updateBy" type="hidden" value="${nPhoneCodePage.updateBy }"/>
		<input id="updateDate" name="updateDate" type="hidden" value="${nPhoneCodePage.updateDate }"/>
		<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${nPhoneCodePage.sysOrgCode }"/>
		<input id="remarks" name="remarks" type="hidden" value="${nPhoneCodePage.remarks }"/>
		<input id="delFlag" name="delFlag" type="hidden" value="${nPhoneCodePage.delFlag }"/>
		<input id="bpmStatus" name="bpmStatus" type="hidden" value="${nPhoneCodePage.bpmStatus }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							类型:
						</label>
					</td>
					<td class="value">
					     	 <input id="phoneType" name="phoneType" type="text" style="width: 150px" class="inputxt" 
					     	  
					     	  ignore="ignore"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">类型</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							手机号:
						</label>
					</td>
					<td class="value">
					     	 <input id="phone" name="phone" type="text" style="width: 150px" class="inputxt" 
					     	  
					     	  ignore="ignore"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">手机号</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							验证码:
						</label>
					</td>
					<td class="value">
					     	 <input id="code" name="code" type="text" style="width: 150px" class="inputxt" 
					     	  
					     	  ignore="ignore"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">验证码</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							内容:
						</label>
					</td>
					<td class="value">
					     	 <input id="details" name="details" type="text" style="width: 150px" class="inputxt" 
					     	  
					     	  ignore="ignore"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">内容</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							IP地址:
						</label>
					</td>
					<td class="value">
					     	 <input id="ipaddress" name="ipaddress" type="text" style="width: 150px" class="inputxt" 
					     	  
					     	  ignore="ignore"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">IP地址</label>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/n_phone_code/nPhoneCode.js"></script>		
