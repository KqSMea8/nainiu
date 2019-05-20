<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>限时秒杀商品</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="nMarketingOneDetailsController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${nMarketingOneDetailsPage.id }">
					<input id="createName" name="createName" type="hidden" value="${nMarketingOneDetailsPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${nMarketingOneDetailsPage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${nMarketingOneDetailsPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${nMarketingOneDetailsPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${nMarketingOneDetailsPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${nMarketingOneDetailsPage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${nMarketingOneDetailsPage.sysOrgCode }">
					<input id="remarks" name="remarks" type="hidden" value="${nMarketingOneDetailsPage.remarks }">
					<input id="delFlag" name="delFlag" type="hidden" value="${nMarketingOneDetailsPage.delFlag }">
					<input id="bpmStatus" name="bpmStatus" type="hidden" value="${nMarketingOneDetailsPage.bpmStatus }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								商家:
							</label>
						</td>
						<td class="value">
						     	 <input id="merchantid" name="merchantid" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nMarketingOneDetailsPage.merchantid}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">商家</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								商家名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="merchantname" name="merchantname" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nMarketingOneDetailsPage.merchantname}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">商家名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								商品:
							</label>
						</td>
						<td class="value">
						     	 <input id="goodsid" name="goodsid" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nMarketingOneDetailsPage.goodsid}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">商品</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								商品名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="goodsname" name="goodsname" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nMarketingOneDetailsPage.goodsname}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">商品名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								审核信息:
							</label>
						</td>
						<td class="value">
						     	 <input id="audittype" name="audittype" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nMarketingOneDetailsPage.audittype}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">审核信息</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								商家状态:
							</label>
						</td>
						<td class="value">
						     	 <input id="shoptype" name="shoptype" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nMarketingOneDetailsPage.shoptype}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">商家状态</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								团购价范围:
							</label>
						</td>
						<td class="value">
						     	 <input id="tuanprices" name="tuanprices" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nMarketingOneDetailsPage.tuanprices}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">团购价范围</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								单买价范围:
							</label>
						</td>
						<td class="value">
						     	 <input id="unitprices" name="unitprices" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nMarketingOneDetailsPage.unitprices}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">单买价范围</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/n_marketing_one_details/nMarketingOneDetails.js"></script>		
