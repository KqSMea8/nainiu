<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>问题反馈</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="nFeebackController.do?doAdd" >
		<input id="id" name="id" type="hidden" value="${nFeebackPage.id }"/>
		<input id="createName" name="createName" type="hidden" value="${nFeebackPage.createName }"/>
		<input id="createBy" name="createBy" type="hidden" value="${nFeebackPage.createBy }"/>
		<input id="createDate" name="createDate" type="hidden" value="${nFeebackPage.createDate }"/>
		<input id="updateName" name="updateName" type="hidden" value="${nFeebackPage.updateName }"/>
		<input id="updateBy" name="updateBy" type="hidden" value="${nFeebackPage.updateBy }"/>
		<input id="updateDate" name="updateDate" type="hidden" value="${nFeebackPage.updateDate }"/>
		<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${nFeebackPage.sysOrgCode }"/>
		<input id="remarks" name="remarks" type="hidden" value="${nFeebackPage.remarks }"/>
		<input id="delFlag" name="delFlag" type="hidden" value="${nFeebackPage.delFlag }"/>
		<input id="bpmStatus" name="bpmStatus" type="hidden" value="${nFeebackPage.bpmStatus }"/>
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
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户id</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							用户名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="realname" name="realname" type="text" style="width: 150px" class="inputxt" 
					     	  
					     	  ignore="ignore"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户名称</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							反馈内容:
						</label>
					</td>
					<td class="value">
					     	 <input id="details" name="details" type="text" style="width: 150px" class="inputxt" 
					     	  
					     	  ignore="ignore"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">反馈内容</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							反馈图片:
						</label>
					</td>
					<td class="value">
					     	 <input id="pic" name="pic" type="text" style="width: 150px" class="inputxt" 
					     	  
					     	  ignore="ignore"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">反馈图片</label>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/n_feeback/nFeeback.js"></script>		
