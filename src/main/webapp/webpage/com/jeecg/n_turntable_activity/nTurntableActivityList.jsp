<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="nTurntableActivityList" checkbox="false" pagination="true" fitColumns="false" title="大转盘奖品发放规则" actionUrl="nTurntableActivityController.do?datagrid" idField="id" fit="true" queryMode="group">
      <%--<t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"    queryMode="single"  width="120"></t:dgCol>--%>
      <t:dgCol title="奖品id"  field="priceId"    queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="奖品名称"  field="priceName"    queryMode="single"  width="200"></t:dgCol>
      <t:dgCol title="奖品数量"  field="priceAmount"    queryMode="single"  width="200"></t:dgCol>
      <t:dgCol title="奖品权重"  field="priceWeight"    queryMode="single"  width="200"></t:dgCol>
      <t:dgToolBar title="添加" icon="icon-add" url="nTurntableActivityController.do?goAdd" funname="add"></t:dgToolBar>
      <t:dgToolBar title="编辑" icon="icon-edit" url="nTurntableActivityController.do?goUpdate" funname="update"></t:dgToolBar>
      <t:dgToolBar title="查看" icon="icon-search" url="nTurntableActivityController.do?goUpdate" funname="detail"></t:dgToolBar>
      <t:dgToolBar title="删除" icon="icon-delete" url="nTurntableActivityController.do?godel" funname="delete"></t:dgToolBar>

  </t:datagrid>
  </div>
 </div>
 <%--<script src = "webpage/com/jeecg/n_red_packet_rule/nRedPacketRuleList.js"></script>		--%>
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'nTurntableActivityController.do?upload', "nTurntableActivityList");
}

//导出
function ExportXls() {
	JeecgExcelExport("nTurntableActivityController.do?exportXls","nTurntableActivityList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("nTurntableActivityController.do?exportXlsByT","nTurntableActivityList");
}

 </script>