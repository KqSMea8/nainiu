<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="sTuisongList" checkbox="false" pagination="true" fitColumns="false" title="推送消息" actionUrl="sTuisongController.do?datagrid" idField="id" fit="true" queryMode="group"  sortOrder="desc" sortName="createDate">
    <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="更新日期"  field="updateDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="流程状态"  field="bpmStatus"  hidden="true"  queryMode="single" dictionary="bpm_status" width="120"></t:dgCol>
    <t:dgCol title="标题"  field="title"    queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="详情"  field="details"  hidden="true"  queryMode="single"  width="500"></t:dgCol>
    <t:dgCol title="用户"  field="userid"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="推送状态"  field="tsstatus"    queryMode="single" dictionary="tsstatus" width="120"></t:dgCol>
    <t:dgCol title="全推状态"  field="qtstatus"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建日期"  field="createDate" formatter="yyyy-MM-dd"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="sTuisongController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="sTuisongController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="sTuisongController.do?goUpdate" funname="detail"></t:dgToolBar>
 
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
 
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/jeecg/s_tuisong/sTuisongList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'sTuisongController.do?upload', "sTuisongList");
}

//导出
function ExportXls() {
	JeecgExcelExport("sTuisongController.do?exportXls","sTuisongList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("sTuisongController.do?exportXlsByT","sTuisongList");
}

 </script>