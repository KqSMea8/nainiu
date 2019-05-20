<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="nRedPacketList" checkbox="true" pagination="true" fitColumns="false" title="n_red_packet" actionUrl="nRedPacketController.do?datagrid" idField="id" fit="true" queryMode="group">
    <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="创建人名称"  field="createName"    queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="创建人登录名称"  field="createBy"    queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="更新人名称"  field="updateName"    queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="更新人登录名称"  field="updateBy"    queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="更新日期"  field="updateDate" formatter="yyyy-MM-dd"   queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="所属部门"  field="sysOrgCode"    queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="备注信息"  field="remarks"    queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="删除标记"  field="delFlag"    queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="流程状态"  field="bpmStatus"    queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="商家id"  field="merchantId"    queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="订单id(关联商城订单)"  field="orderId"    queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="用户openid"  field="openid"    queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="红包金额"  field="totalAmount"    queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="业务结果"  field="resultCode"    queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="返回码"  field="errorCode"    queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="返回消息"  field="result"    queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="返回说明"  field="reason"    queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="创建日期"  field="createDate" formatter="yyyy-MM-dd"   queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="nRedPacketController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="nRedPacketController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="nRedPacketController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="nRedPacketController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="nRedPacketController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/jeecg/n_red_packet/nRedPacketList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'nRedPacketController.do?upload', "nRedPacketList");
}

//导出
function ExportXls() {
	JeecgExcelExport("nRedPacketController.do?exportXls","nRedPacketList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("nRedPacketController.do?exportXlsByT","nRedPacketList");
}

 </script>