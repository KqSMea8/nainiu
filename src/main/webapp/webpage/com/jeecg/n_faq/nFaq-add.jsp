<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>常见问题</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="nFaqController.do?doAdd" >
		<input id="id" name="id" type="hidden" value="${nFaqPage.id }"/>
		<input id="createName" name="createName" type="hidden" value="${nFaqPage.createName }"/>
		<input id="createBy" name="createBy" type="hidden" value="${nFaqPage.createBy }"/>
		<input id="createDate" name="createDate" type="hidden" value="${nFaqPage.createDate }"/>
		<input id="updateName" name="updateName" type="hidden" value="${nFaqPage.updateName }"/>
		<input id="updateBy" name="updateBy" type="hidden" value="${nFaqPage.updateBy }"/>
		<input id="updateDate" name="updateDate" type="hidden" value="${nFaqPage.updateDate }"/>
		<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${nFaqPage.sysOrgCode }"/>
		<input id="remarks" name="remarks" type="hidden" value="${nFaqPage.remarks }"/>
		<input id="delFlag" name="delFlag" type="hidden" value="${nFaqPage.delFlag }"/>
		<input id="bpmStatus" name="bpmStatus" type="hidden" value="${nFaqPage.bpmStatus }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							标题:
						</label>
					</td>
					<td class="value">
					     	 <input id="title" name="title" type="text" style="width: 150px" class="inputxt" 
					     	  datatype="*" 
					     	  ignore="ignore"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">标题</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							详情:
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
  <script src = "webpage/com/jeecg/n_faq/nFaq.js"></script>		
