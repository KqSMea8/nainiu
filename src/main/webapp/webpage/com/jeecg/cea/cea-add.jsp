<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>主表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  $(document).ready(function(){
	$('#tt').tabs({
	   onSelect:function(title){
	       $('#tt .panel-body').css('width','auto');
		}
	});
	$(".tabs-wrap").css('width','100%');
  });
 </script>
 </head>
 <body style="overflow-x: hidden;">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1" action="ceaController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${ceaPage.id }">
					<input id="createName" name="createName" type="hidden" value="${ceaPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${ceaPage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${ceaPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${ceaPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${ceaPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${ceaPage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${ceaPage.sysOrgCode }">
					<input id="remarks" name="remarks" type="hidden" value="${ceaPage.remarks }">
					<input id="delFlag" name="delFlag" type="hidden" value="${ceaPage.delFlag }">
					<input id="bpmStatus" name="bpmStatus" type="hidden" value="${ceaPage.bpmStatus }">
	<table cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">标题:</label>
			</td>
			<td class="value">
		     	 <input id="title" name="title" type="text" style="width: 150px" class="inputxt" 
				ignore="ignore"
		     	 >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">标题</label>
			</td>
			<td align="right">
				<label class="Validform_label">内容:</label>
			</td>
			<td class="value">
		     	 <input id="contents" name="contents" type="text" style="width: 150px" class="inputxt" 
				ignore="ignore"
		     	 >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">内容</label>
			</td>
		</tr>
	</table>
			<div style="width: auto;height: 200px;">
				<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
				<div style="width:800px;height:1px;"></div>
				<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
				 <t:tab href="ceaController.do?cebList&id=${ceaPage.id}" icon="icon-search" title="测试" id="ceb"></t:tab>
				</t:tabs>
			</div>
			</t:formvalid>
			<!-- 添加 附表明细 模版 -->
	<table style="display:none">
	<tbody id="add_ceb_table_template">
		<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left">
					  	<input name="cebList[#index#].title" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;" 
									ignore="ignore"
					  		>
					  <label class="Validform_label" style="display: none;">标题1</label>
				  </td>
				  <td align="left">
					  	<input name="cebList[#index#].pkId" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;" 
									ignore="ignore"
					  		>
					  <label class="Validform_label" style="display: none;">关联</label>
				  </td>
			</tr>
		 </tbody>
		</table>
 </body>
 <script src = "webpage/com/jeecg/cea/cea.js"></script>
	