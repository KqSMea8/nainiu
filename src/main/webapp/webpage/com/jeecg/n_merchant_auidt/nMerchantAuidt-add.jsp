<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>审核详情</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="nMerchantAuidtController.do?doAdd" >
		<input id="id" name="id" type="hidden" value="${nMerchantAuidtPage.id }"/>
		<input id="createName" name="createName" type="hidden" value="${nMerchantAuidtPage.createName }"/>
		<input id="createBy" name="createBy" type="hidden" value="${nMerchantAuidtPage.createBy }"/>
		<input id="createDate" name="createDate" type="hidden" value="${nMerchantAuidtPage.createDate }"/>
		<input id="updateName" name="updateName" type="hidden" value="${nMerchantAuidtPage.updateName }"/>
		<input id="updateBy" name="updateBy" type="hidden" value="${nMerchantAuidtPage.updateBy }"/>
		<input id="updateDate" name="updateDate" type="hidden" value="${nMerchantAuidtPage.updateDate }"/>
		<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${nMerchantAuidtPage.sysOrgCode }"/>
		<input id="remarks" name="remarks" type="hidden" value="${nMerchantAuidtPage.remarks }"/>
		<input id="delFlag" name="delFlag" type="hidden" value="${nMerchantAuidtPage.delFlag }"/>
		<input id="bpmStatus" name="bpmStatus" type="hidden" value="${nMerchantAuidtPage.bpmStatus }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							商家:
						</label>
					</td>
					<td class="value">
					     	 <input id="merchantId" name="merchantId" type="text" style="width: 150px" class="inputxt" 
					     	  
					     	  ignore="ignore"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">商家</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							审核内容:
						</label>
					</td>
					<td class="value">
					     	 <input id="auditContent" name="auditContent" type="text" style="width: 150px" class="inputxt" 
					     	  
					     	  ignore="ignore"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">审核内容</label>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/n_merchant_auidt/nMerchantAuidt.js"></script>		
