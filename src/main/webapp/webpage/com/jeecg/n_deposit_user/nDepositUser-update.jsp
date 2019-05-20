<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>用户提现记录</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="nDepositUserController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${nDepositUserPage.id }">
					<input id="createName" name="createName" type="hidden" value="${nDepositUserPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${nDepositUserPage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${nDepositUserPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${nDepositUserPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${nDepositUserPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${nDepositUserPage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${nDepositUserPage.sysOrgCode }">
					<input id="remarks" name="remarks" type="hidden" value="${nDepositUserPage.remarks }">
					<input id="delFlag" name="delFlag" type="hidden" value="${nDepositUserPage.delFlag }">
					<input id="bpmStatus" name="bpmStatus" type="hidden" value="${nDepositUserPage.bpmStatus }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								用户id:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="userid" type="list"
										dictTable="n_user" dictField="id" dictText="realname" defaultVal="${nDepositUserPage.userid}" hasLabel="false"  title="用户id"  
										></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户id</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								用户支付宝账户:
							</label>
						</td>
						<td class="value">
						     	 <input id="account" name="account" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nDepositUserPage.account}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户支付宝账户</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								提现金额:
							</label>
						</td>
						<td class="value">
						     	 <input id="amount" name="amount" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nDepositUserPage.amount}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">提现金额</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								提现状态:
							</label>
						</td>
						<td class="value">
						     	 <input id="status" name="status" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nDepositUserPage.status}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">提现状态</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/n_deposit_user/nDepositUser.js"></script>		
