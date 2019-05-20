<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>订单导入</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="nOrderImportxlsController.do?doAdd" >
		<input id="id" name="id" type="hidden" value="${nOrderImportxlsPage.id }"/>
		<input id="createName" name="createName" type="hidden" value="${nOrderImportxlsPage.createName }"/>
		<input id="createBy" name="createBy" type="hidden" value="${nOrderImportxlsPage.createBy }"/>
		<input id="createDate" name="createDate" type="hidden" value="${nOrderImportxlsPage.createDate }"/>
		<input id="updateName" name="updateName" type="hidden" value="${nOrderImportxlsPage.updateName }"/>
		<input id="updateBy" name="updateBy" type="hidden" value="${nOrderImportxlsPage.updateBy }"/>
		<input id="updateDate" name="updateDate" type="hidden" value="${nOrderImportxlsPage.updateDate }"/>
		<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${nOrderImportxlsPage.sysOrgCode }"/>
		<input id="remarks" name="remarks" type="hidden" value="${nOrderImportxlsPage.remarks }"/>
		<input id="delFlag" name="delFlag" type="hidden" value="${nOrderImportxlsPage.delFlag }"/>
		<input id="bpmStatus" name="bpmStatus" type="hidden" value="${nOrderImportxlsPage.bpmStatus }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							订单id:
						</label>
					</td>
					<td class="value">
					     	 <input id="norderid" name="norderid" type="text" style="width: 150px" class="inputxt" 
					     	  
					     	  ignore="ignore"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">订单id</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							快递单号:
						</label>
					</td>
					<td class="value">
					     	 <input id="expressNub" name="expressNub" type="text" style="width: 150px" class="inputxt" 
					     	  
					     	  ignore="ignore"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">快递单号</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							快递名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="expressName" name="expressName" type="text" style="width: 150px" class="inputxt" 
					     	  
					     	  ignore="ignore"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">快递名称</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							快递编号:
						</label>
					</td>
					<td class="value">
					     	 <input id="expressCode" name="expressCode" type="text" style="width: 150px" class="inputxt" 
					     	  
					     	  ignore="ignore"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">快递编号</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							导入状态:
						</label>
					</td>
					<td class="value">
					     	 <input id="importxlsType" name="importxlsType" type="text" style="width: 150px" class="inputxt" 
					     	  
					     	  ignore="ignore"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">导入状态</label>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/n_order_importxls/nOrderImportxls.js"></script>		
