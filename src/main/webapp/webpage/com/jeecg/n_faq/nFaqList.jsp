<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="nFaqList" checkbox="false" pagination="true" fitColumns="false" title="常见问题" actionUrl="nFaqController.do?datagrid" idField="id" fit="true" queryMode="group">
    <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建日期"  field="createDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="更新日期"  field="updateDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="备注信息"  field="remarks"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="删除标记"  field="delFlag"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="流程状态"  field="bpmStatus"  hidden="true"  queryMode="single" dictionary="bpm_status" width="120"></t:dgCol>
    <t:dgCol title="标题"  field="title"    queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="详情"  field="details"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgToolBar title="编辑" icon="icon-edit" url="nFaqController.do?goUpdate" funname="update"></t:dgToolBar>
    <t:dgToolBar title="查看" icon="icon-search" url="nFaqController.do?goUpdate" funname="detail"></t:dgToolBar>
    <%--
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="nFaqController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="nFaqController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="nFaqController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="nFaqController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="nFaqController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
   --%>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/jeecg/n_faq/nFaqList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'nFaqController.do?upload', "nFaqList");
}

//导出
function ExportXls() {
	JeecgExcelExport("nFaqController.do?exportXls","nFaqList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("nFaqController.do?exportXlsByT","nFaqList");
}

 </script>