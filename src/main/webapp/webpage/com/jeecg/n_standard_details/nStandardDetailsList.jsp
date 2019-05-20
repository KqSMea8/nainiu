<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="nStandardDetailsList" checkbox="false" pagination="true" fitColumns="false" title="规格组合商品详情" actionUrl="nStandardDetailsController.do?datagrid" idField="id" fit="true" queryMode="group">
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
    <t:dgCol title="商品"  field="goodsId"    queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="规格1"  field="standardOne"    queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="规格2"  field="standardTwo"    queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="库存增减"  field="sumNumber"    queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="团购价"  field="tuanPrice"    queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="单买价"  field="unitPrice"    queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="商家编码"  field="goodsCode"    queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="SKU预览图"  field="picUrl"    queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="状态"  field="shangpintype"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="nStandardDetailsController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="nStandardDetailsController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="nStandardDetailsController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="nStandardDetailsController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="nStandardDetailsController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/jeecg/n_standard_details/nStandardDetailsList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'nStandardDetailsController.do?upload', "nStandardDetailsList");
}

//导出
function ExportXls() {
	JeecgExcelExport("nStandardDetailsController.do?exportXls","nStandardDetailsList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("nStandardDetailsController.do?exportXlsByT","nStandardDetailsList");
}

 </script>