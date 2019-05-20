<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="nPhoneRechargeList" checkbox="false" pagination="true" fitColumns="false" title="充值返回信息" actionUrl="nPhoneRechargeController.do?datagrid" idField="id" fit="true" queryMode="group">
    <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="更新日期"  field="updateDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="备注信息"  field="remarks"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="删除标记"  field="delFlag"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="流程状态"  field="bpmStatus"  hidden="true"  queryMode="single" dictionary="bpm_status" width="120"></t:dgCol>
    <t:dgCol title="商家id"  field="merchantId"   query="true" queryMode="single" dictionary="n_merchant,id,company"  width="120"></t:dgCol>
    <t:dgCol title="订单id(关联商城订单)"  field="orderId"   query="true" queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="手机号"  field="phone"   query="true" queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="充值金额"  field="cardnum"   query="true" queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="充值状态"  field="gameState"   query="true" queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="返回码"  field="errorCode"   query="true" queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="返回消息"  field="result"  hidden="true"  queryMode="single"  width="500"></t:dgCol>
    <t:dgCol title="创建日期"  field="createDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="nPhoneRechargeController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="nPhoneRechargeController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="nPhoneRechargeController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="nPhoneRechargeController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="nPhoneRechargeController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/jeecg/n_phone_recharge/nPhoneRechargeList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'nPhoneRechargeController.do?upload', "nPhoneRechargeList");
}

//导出
function ExportXls() {
	JeecgExcelExport("nPhoneRechargeController.do?exportXls","nPhoneRechargeList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("nPhoneRechargeController.do?exportXlsByT","nPhoneRechargeList");
}

 </script>