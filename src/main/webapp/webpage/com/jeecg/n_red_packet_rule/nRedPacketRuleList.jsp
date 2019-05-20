<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="nRedPacketRuleList" checkbox="false" pagination="true" fitColumns="false" title="微信红包发放规则" actionUrl="nRedPacketRuleController.do?datagrid" idField="id" fit="true" queryMode="group">
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
    <t:dgCol title="商家id"  field="merchantId"    queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="红包祝福语"  field="wishing"    queryMode="single"  width="200"></t:dgCol>
    <t:dgCol title="活动名称"  field="actName"    queryMode="single"  width="200"></t:dgCol>
    <t:dgCol title="备注"  field="remark"    queryMode="single"  width="200"></t:dgCol>
    <t:dgCol title="红包金额的大小"  field="ruleTotalAmount"    queryMode="single"  width="120"></t:dgCol>
   <t:dgToolBar title="编辑" icon="icon-edit" url="nRedPacketRuleController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="nRedPacketRuleController.do?goUpdate" funname="detail"></t:dgToolBar>

  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/jeecg/n_red_packet_rule/nRedPacketRuleList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'nRedPacketRuleController.do?upload', "nRedPacketRuleList");
}

//导出
function ExportXls() {
	JeecgExcelExport("nRedPacketRuleController.do?exportXls","nRedPacketRuleList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("nRedPacketRuleController.do?exportXlsByT","nRedPacketRuleList");
}

 </script>