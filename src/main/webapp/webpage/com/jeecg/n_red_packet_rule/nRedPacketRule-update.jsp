<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>微信红包发放规则</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="nRedPacketRuleController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${nRedPacketRulePage.id }">
					<input id="createName" name="createName" type="hidden" value="${nRedPacketRulePage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${nRedPacketRulePage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${nRedPacketRulePage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${nRedPacketRulePage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${nRedPacketRulePage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${nRedPacketRulePage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${nRedPacketRulePage.sysOrgCode }">
					<input id="remarks" name="remarks" type="hidden" value="${nRedPacketRulePage.remarks }">
					<input id="delFlag" name="delFlag" type="hidden" value="${nRedPacketRulePage.delFlag }">
					<input id="bpmStatus" name="bpmStatus" type="hidden" value="${nRedPacketRulePage.bpmStatus }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								活动名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="actName" name="actName" type="text" style="width: 250px" class="inputxt"  datatype="*1-31" 
						     	 ignore="checked" 
						     	 value='${nRedPacketRulePage.actName}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">活动名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								红包祝福语:
							</label>
						</td>
						<td class="value">
						     	 <textarea id="wishing" name="wishing" style="width: 250px" class="inputxt"  datatype="*1-127" 
						     	 ignore="checked" 
						     	 >${nRedPacketRulePage.wishing}</textarea>
						     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">红包祝福语</label>
						</td>
					</tr>

					<tr>
						<td align="right">
							<label class="Validform_label">
								备注:
							</label>
						</td>
						<td class="value">
						 <textarea id="remark" name="remark" style="width: 250px" class="inputxt"  datatype="*1-256" 
				     	 ignore="checked" >${nRedPacketRulePage.remark}</textarea>
						     	
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								红包金额的大小:
							</label>
						</td>
						<td class="value">
						     	 <input id="ruleTotalAmount" name="ruleTotalAmount" type="text" style="width: 250px" class="inputxt"   
						     	 ignore="checked" datatype="*1-200"
						     	 value='${nRedPacketRulePage.ruleTotalAmount}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">红包金额的大小</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/n_red_packet_rule/nRedPacketRule.js"></script>		
