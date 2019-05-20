<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>规格表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="nStandardController.do?doAdd" >
		<input id="id" name="id" type="hidden" value="${nStandardPage.id }"/>
		<input id="createName" name="createName" type="hidden" value="${nStandardPage.createName }"/>
		<input id="createBy" name="createBy" type="hidden" value="${nStandardPage.createBy }"/>
		<input id="createDate" name="createDate" type="hidden" value="${nStandardPage.createDate }"/>
		<input id="updateName" name="updateName" type="hidden" value="${nStandardPage.updateName }"/>
		<input id="updateBy" name="updateBy" type="hidden" value="${nStandardPage.updateBy }"/>
		<input id="updateDate" name="updateDate" type="hidden" value="${nStandardPage.updateDate }"/>
		<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${nStandardPage.sysOrgCode }"/>
		<input id="remarks" name="remarks" type="hidden" value="${nStandardPage.remarks }"/>
		<input id="delFlag" name="delFlag" type="hidden" value="${nStandardPage.delFlag }"/>
		<input id="bpmStatus" name="bpmStatus" type="hidden" value="${nStandardPage.bpmStatus }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							规格类型:
						</label>
					</td>
					<td class="value">
					     	 <input id="standardType" name="standardType" type="text" style="width: 150px" class="inputxt" 
					     	  
					     	  ignore="ignore"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">规格类型</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							商品:
						</label>
					</td>
					<td class="value">
					     	 <input id="goodsId" name="goodsId" type="text" style="width: 150px" class="inputxt" 
					     	  
					     	  ignore="ignore"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">商品</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							规格名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="title" name="title" type="text" style="width: 150px" class="inputxt" 
					     	  
					     	  ignore="ignore"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">规格名称</label>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/n_standard/nStandard.js"></script>		
