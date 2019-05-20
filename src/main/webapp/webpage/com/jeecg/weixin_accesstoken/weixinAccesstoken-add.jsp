<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>获取access_token</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinAccesstokenController.do?doAdd" >
		<input id="id" name="id" type="hidden" value="${weixinAccesstokenPage.id }"/>
		<input id="createName" name="createName" type="hidden" value="${weixinAccesstokenPage.createName }"/>
		<input id="createBy" name="createBy" type="hidden" value="${weixinAccesstokenPage.createBy }"/>
		<input id="createDate" name="createDate" type="hidden" value="${weixinAccesstokenPage.createDate }"/>
		<input id="updateName" name="updateName" type="hidden" value="${weixinAccesstokenPage.updateName }"/>
		<input id="updateBy" name="updateBy" type="hidden" value="${weixinAccesstokenPage.updateBy }"/>
		<input id="updateDate" name="updateDate" type="hidden" value="${weixinAccesstokenPage.updateDate }"/>
		<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${weixinAccesstokenPage.sysOrgCode }"/>
		<input id="remarks" name="remarks" type="hidden" value="${weixinAccesstokenPage.remarks }"/>
		<input id="delFlag" name="delFlag" type="hidden" value="${weixinAccesstokenPage.delFlag }"/>
		<input id="bpmStatus" name="bpmStatus" type="hidden" value="${weixinAccesstokenPage.bpmStatus }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							凭证:
						</label>
					</td>
					<td class="value">
					     	 <input id="accessToken" name="accessToken" type="text" style="width: 150px" class="inputxt" 
					     	  
					     	  ignore="ignore"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">凭证</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							凭证有效时间:
						</label>
					</td>
					<td class="value">
					     	 <input id="expiresIn" name="expiresIn" type="text" style="width: 150px" class="inputxt" 
					     	  
					     	  ignore="ignore"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">凭证有效时间</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							添加时间:
						</label>
					</td>
					<td class="value">
					     	 <input id="addtime" name="addtime" type="text" style="width: 150px" class="inputxt" 
					     	  
					     	  ignore="ignore"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">添加时间</label>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/weixin_accesstoken/weixinAccesstoken.js"></script>		
