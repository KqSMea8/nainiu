<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>ce</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="ceController.do?doAdd" >
		<input id="id" name="id" type="hidden" value="${cePage.id }"/>
		<input id="createName" name="createName" type="hidden" value="${cePage.createName }"/>
		<input id="createBy" name="createBy" type="hidden" value="${cePage.createBy }"/>
		<input id="createDate" name="createDate" type="hidden" value="${cePage.createDate }"/>
		<input id="updateName" name="updateName" type="hidden" value="${cePage.updateName }"/>
		<input id="updateBy" name="updateBy" type="hidden" value="${cePage.updateBy }"/>
		<input id="updateDate" name="updateDate" type="hidden" value="${cePage.updateDate }"/>
		<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${cePage.sysOrgCode }"/>
		<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${cePage.sysCompanyCode }"/>
		<input id="bpmStatus" name="bpmStatus" type="hidden" value="${cePage.bpmStatus }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							ce:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="ce" type="list"
									typeGroupCode="education" defaultVal="${cePage.ce}" hasLabel="false"  title="ce"  datatype="*"  
									></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">ce</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							join_time:
						</label>
					</td>
					<td class="value">
							   <input id="joinTime" name="joinTime" type="text" style="width: 150px" class="Wdate" onClick="WdatePicker()" 
					      						 datatype="*"  
					      						ignore="checked"
					      						/>    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">join_time</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							time_t:
						</label>
					</td>
					<td class="value">
							   <input id="timeT" name="timeT" type="text" style="width: 150px" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
					      						 
					      						ignore="ignore"
					      						/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">time_t</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							user_id:
						</label>
					</td>
					<td class="value">
					     	 <input id="userId" name="userId" type="text" style="width: 150px" class="inputxt" 
					     	  
					     	  ignore="ignore"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">user_id</label>
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
						    	<textarea name="content" id="content" style="width: 650px;height:300px"></textarea>
							    <script type="text/javascript">
							        var editor = UE.getEditor('content');
							    </script>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">内容</label>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/ce/ce.js"></script>		
