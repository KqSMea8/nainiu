<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>平台进行保证金额设置</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="nDepositSetController.do?doAdd" >
		<input id="id" name="id" type="hidden" value="${nDepositSetPage.id }"/>
		<input id="createName" name="createName" type="hidden" value="${nDepositSetPage.createName }"/>
		<input id="createBy" name="createBy" type="hidden" value="${nDepositSetPage.createBy }"/>
		<input id="createDate" name="createDate" type="hidden" value="${nDepositSetPage.createDate }"/>
		<input id="updateName" name="updateName" type="hidden" value="${nDepositSetPage.updateName }"/>
		<input id="updateBy" name="updateBy" type="hidden" value="${nDepositSetPage.updateBy }"/>
		<input id="updateDate" name="updateDate" type="hidden" value="${nDepositSetPage.updateDate }"/>
		<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${nDepositSetPage.sysOrgCode }"/>
		<input id="remarks" name="remarks" type="hidden" value="${nDepositSetPage.remarks }"/>
		<input id="delFlag" name="delFlag" type="hidden" value="${nDepositSetPage.delFlag }"/>
		<input id="bpmStatus" name="bpmStatus" type="hidden" value="${nDepositSetPage.bpmStatus }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							金额:
						</label>
					</td>
					<td class="value">
					     	 <input id="price" name="price" type="text" style="width: 150px" class="inputxt" 
					     	  datatype="n" 
					     	  ignore="checked"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">金额</label>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/n_deposit_set/nDepositSet.js"></script>		
