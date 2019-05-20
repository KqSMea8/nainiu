<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>拼团规则协议</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="nRuleController.do?doAdd" >
		<input id="id" name="id" type="hidden" value="${nRulePage.id }"/>
		<input id="createName" name="createName" type="hidden" value="${nRulePage.createName }"/>
		<input id="createBy" name="createBy" type="hidden" value="${nRulePage.createBy }"/>
		<input id="createDate" name="createDate" type="hidden" value="${nRulePage.createDate }"/>
		<input id="updateName" name="updateName" type="hidden" value="${nRulePage.updateName }"/>
		<input id="updateBy" name="updateBy" type="hidden" value="${nRulePage.updateBy }"/>
		<input id="updateDate" name="updateDate" type="hidden" value="${nRulePage.updateDate }"/>
		<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${nRulePage.sysOrgCode }"/>
		<input id="remarks" name="remarks" type="hidden" value="${nRulePage.remarks }"/>
		<input id="delFlag" name="delFlag" type="hidden" value="${nRulePage.delFlag }"/>
		<input id="bpmStatus" name="bpmStatus" type="hidden" value="${nRulePage.bpmStatus }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="title" name="title" type="text" style="width: 150px" class="inputxt" 
					     	  
					     	  ignore="checked"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">名称</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							类型:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="ruleType" type="list"
									typeGroupCode="ruleType" defaultVal="${nRulePage.ruleType}" hasLabel="false"  title="类型"  
									></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">类型</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							内容:
						</label>
					</td>
					<td class="value">
								<script type="text/javascript"  charset="utf-8" src="plug-in/ueditor/ueditor.config.js"></script>
								<script type="text/javascript"  charset="utf-8" src="plug-in/ueditor/ueditor.all.min.js"></script>
						    	<textarea name="details" id="details" style="width: 650px;height:300px"></textarea>
							    <script type="text/javascript">
							        var editor = UE.getEditor('details');
							    </script>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">内容</label>
						</td>
				</tr>

			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/n_rule/nRule.js"></script>		
