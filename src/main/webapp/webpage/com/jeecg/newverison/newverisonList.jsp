<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="newverisonList" checkbox="false" pagination="true" fitColumns="false" title="版本更新" actionUrl="newverisonController.do?datagrid" idField="id" fit="true" queryMode="group">
    <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="更新日期"  field="updateDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="流程状态"  field="bpmStatus"  hidden="true"  queryMode="single" dictionary="bpm_status" width="120"></t:dgCol>
    <t:dgCol title="类型"  field="flg"    queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="版本编号"  field="versioncode"    queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="版面名称"  field="versionname"    queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="下载地址"  field="downloadurl"    queryMode="single"  width="200"></t:dgCol>
    <t:dgCol title="创建日期"  field="createDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="newverisonController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <%-- 
   <t:dgToolBar title="录入" icon="icon-add" url="newverisonController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="newverisonController.do?goUpdate" funname="update"></t:dgToolBar>
   --%>
   <t:dgToolBar title="编辑" icon="icon-edit" url="newverisonController.do?goUpdate" funname="update"></t:dgToolBar>
     <t:dgToolBar title="新增" icon="icon-add"  funname="adddate"></t:dgToolBar>
     <t:dgToolBar title="编辑" icon="icon-edit" funname="updatedate"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="newverisonController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="newverisonController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/jeecg/newverison/newverisonList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'newverisonController.do?upload', "newverisonList");
}

//导出
function ExportXls() {
	JeecgExcelExport("newverisonController.do?exportXls","newverisonList");
}

//导出
function adddate() {
	window.location.href="newverisonController.do?goAdd";
}
//导出
function updatedate() {
	//gridname=id;
	var rowsData = $('#newverisonList').datagrid('getSelections');
	if (!rowsData || rowsData.length==0) {
		tip('请选择编辑项目');
		return;
	}
	if (rowsData.length>1) {
		tip('请选择一条记录再编辑');
		return;
	}
	var url='newverisonController.do?goUpdate&id='+rowsData[0].id;
	
	//"newverisonController.do?goUpdat";
	//alert(url);
	window.location.href=url;
}
//修改
function update(title,url, id){
	updateNotCreateWin("修改",url, "newverisonList",false);
}
//模板下载
function ExportXlsByT() {
	JeecgExcelExport("newverisonController.do?exportXlsByT","newverisonList");
}

 </script>