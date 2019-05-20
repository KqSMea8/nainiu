<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="nGoodsClassifyList" checkbox="false" pagination="false" treegrid="true" treeField="departname" fitColumns="false" title="商品分类" actionUrl="nGoodsClassifyController.do?datagrid" idField="id" fit="true" queryMode="group">
    <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建日期"  field="createDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="更新日期"  field="updateDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="商品分类名称"  field="departname"    queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="详情"  field="description"    queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="保证金"  field="deposit"    queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="父部门ID"  field="parentdepartid"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="编号"  field="orgCode"    queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="删除标记"  field="delFlag"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="排序"  field="orgType"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="顺序"  field="departOrder"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="nGoodsClassifyController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="nGoodsClassifyController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="nGoodsClassifyController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="nGoodsClassifyController.do?goUpdate" funname="detail"></t:dgToolBar>
   <%--
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="nGoodsClassifyController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="nGoodsClassifyController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
   --%>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/jeecg/n_goods_classify/nGoodsClassifyList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
		$("#nGoodsClassifyList").treegrid({
 				 onExpand : function(row){
 					var children = $("#nGoodsClassifyList").treegrid('getChildren',row.id);
 					 if(children.length<=0){
 					 	row.leaf=true;
 					 	$("#nGoodsClassifyList").treegrid('refresh', row.id);
 					 }
 				}
 		});
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'nGoodsClassifyController.do?upload', "nGoodsClassifyList");
}

//导出
function ExportXls() {
	JeecgExcelExport("nGoodsClassifyController.do?exportXls","nGoodsClassifyList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("nGoodsClassifyController.do?exportXlsByT","nGoodsClassifyList");
}

/**
 * 获取表格对象
 * @return 表格对象
 */
function getDataGrid(){
	var datagrid = $('#'+gridname);
	return datagrid;
}
 </script>