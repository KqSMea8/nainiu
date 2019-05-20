<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>

<link rel="stylesheet" type="text/css" href="plug-in/ztree/css/zTreeStyle.css">
<script type="text/javascript" src="plug-in/ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="plug-in/ztree/js/jquery.ztree.excheck-3.5.min.js"></script>
<script type="text/javascript">

</script>
<div class="easyui-layout" style="width:600px;height:550px;">
  
    <div data-options="region:'center'">
        <t:datagrid  name="userList1" title="商品详情" actionUrl="nGoodsDetaislController.do?datagrid&joinHuodong=0&goodsDetaislStatus=2&merchantId=${merchantId}"
                    fit="true" fitColumns="true" idField="id" queryMode="group"  checkbox="true" sortName="createDate" sortOrder="desc">
            <t:dgCol title="common.id" field="id" hidden="true"></t:dgCol>
            <t:dgCol title="商品名称"  field="title"   query="true" queryMode="single"  width="180"></t:dgCol>
            <t:dgCol title="团购价范围"  field="tuanprices"    queryMode="single"  width="60"></t:dgCol>
            <t:dgCol title="单买价范围"  field="unitprices"    queryMode="single"  width="60"></t:dgCol>
            <t:dgCol title="总库存"  field="sumNumbers"    queryMode="single"  width="60"></t:dgCol>
           
        </t:datagrid>
    </div>
</div>

<script type="text/javascript">

</script>
