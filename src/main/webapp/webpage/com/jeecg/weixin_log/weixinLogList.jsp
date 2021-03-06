<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="weixinLogList" checkbox="false" pagination="true" fitColumns="false" title="微信数据日记" actionUrl="weixinLogController.do?datagrid" idField="id" fit="true" queryMode="group">
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
    <t:dgCol title="数据详情"  field="details"    queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="失败数据"  field="status"    queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建日期"  field="createDate"  query="true"  formatter="yyyy-MM-dd"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="weixinLogController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
  
   <t:dgToolBar title="查看" icon="icon-search" url="weixinLogController.do?goUpdate" funname="detail"></t:dgToolBar>

  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/jeecg/weixin_log/weixinLogList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'weixinLogController.do?upload', "weixinLogList");
}

//导出
function ExportXls() {
	JeecgExcelExport("weixinLogController.do?exportXls","weixinLogList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("weixinLogController.do?exportXlsByT","weixinLogList");
}

 </script>