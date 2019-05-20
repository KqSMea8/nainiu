<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>充值返回信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="nPhoneRechargeController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${nPhoneRechargePage.id }">
					<input id="createName" name="createName" type="hidden" value="${nPhoneRechargePage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${nPhoneRechargePage.createBy }">
					<input id="updateName" name="updateName" type="hidden" value="${nPhoneRechargePage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${nPhoneRechargePage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${nPhoneRechargePage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${nPhoneRechargePage.sysOrgCode }">
					<input id="remarks" name="remarks" type="hidden" value="${nPhoneRechargePage.remarks }">
					<input id="delFlag" name="delFlag" type="hidden" value="${nPhoneRechargePage.delFlag }">
					<input id="bpmStatus" name="bpmStatus" type="hidden" value="${nPhoneRechargePage.bpmStatus }">
					<input id="createDate" name="createDate" type="hidden" value="${nPhoneRechargePage.createDate }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								商家id:
							</label>
						</td>
						<td class="value">
						     	 <input id="merchantId" name="merchantId" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nPhoneRechargePage.merchantId}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">商家id</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								订单id(关联商城订单):
							</label>
						</td>
						<td class="value">
						     	 <input id="orderId" name="orderId" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nPhoneRechargePage.orderId}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">订单id(关联商城订单)</label>
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
						     	 value='${nPhoneRechargePage.phone}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">手机号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								充值金额:
							</label>
						</td>
						<td class="value">
						     	 <input id="cardnum" name="cardnum" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nPhoneRechargePage.cardnum}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">充值金额</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								充值状态:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="gameState" type="list"
										typeGroupCode="" defaultVal="${nPhoneRechargePage.gameState}" hasLabel="false"  title="充值状态"  
										></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">充值状态</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								返回码:
							</label>
						</td>
						<td class="value">
						     	 <input id="errorCode" name="errorCode" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nPhoneRechargePage.errorCode}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">返回码</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								返回消息:
							</label>
						</td>
						<td class="value">
									<script type="text/javascript"  charset="utf-8" src="plug-in/ueditor/ueditor.config.js"></script>
									<script type="text/javascript"  charset="utf-8" src="plug-in/ueditor/ueditor.all.min.js"></script>
							    	<textarea name="result" id="result" style="width: 650px;height:300px"></textarea>
								    <script type="text/javascript">
								        var editor = UE.getEditor('result');
								    </script>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">返回消息</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/n_phone_recharge/nPhoneRecharge.js"></script>		
