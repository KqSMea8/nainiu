<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>n_marketing_home_details</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="nMarketingHomeDetailsController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${nMarketingHomeDetailsPage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								创建人名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="createName" name="createName" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nMarketingHomeDetailsPage.createName}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">创建人名称</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								创建人登录名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="createBy" name="createBy" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nMarketingHomeDetailsPage.createBy}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">创建人登录名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								创建日期:
							</label>
						</td>
						<td class="value">
									  <input id="createDate" name="createDate" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()"  
									  ignore="ignore"
									    value='<fmt:formatDate value='${nMarketingHomeDetailsPage.createDate}' type="date" pattern="yyyy-MM-dd"/>'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">创建日期</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								更新人名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="updateName" name="updateName" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nMarketingHomeDetailsPage.updateName}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">更新人名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								更新人登录名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="updateBy" name="updateBy" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nMarketingHomeDetailsPage.updateBy}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">更新人登录名称</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								更新日期:
							</label>
						</td>
						<td class="value">
									  <input id="updateDate" name="updateDate" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()"  
									  ignore="ignore"
									    value='<fmt:formatDate value='${nMarketingHomeDetailsPage.updateDate}' type="date" pattern="yyyy-MM-dd"/>'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">更新日期</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								所属部门:
							</label>
						</td>
						<td class="value">
						     	 <input id="sysOrgCode" name="sysOrgCode" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nMarketingHomeDetailsPage.sysOrgCode}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">所属部门</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								备注信息:
							</label>
						</td>
						<td class="value">
						     	 <input id="remarks" name="remarks" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nMarketingHomeDetailsPage.remarks}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注信息</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								删除标记:
							</label>
						</td>
						<td class="value">
						     	 <input id="delFlag" name="delFlag" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nMarketingHomeDetailsPage.delFlag}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">删除标记</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								流程状态:
							</label>
						</td>
						<td class="value">
						     	 <input id="bpmStatus" name="bpmStatus" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nMarketingHomeDetailsPage.bpmStatus}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">流程状态</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								商家:
							</label>
						</td>
						<td class="value">
						     	 <input id="merchantid" name="merchantid" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nMarketingHomeDetailsPage.merchantid}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">商家</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								商品:
							</label>
						</td>
						<td class="value">
						     	 <input id="goodsid" name="goodsid" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nMarketingHomeDetailsPage.goodsid}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">商品</label>
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
						     	 value='${nMarketingHomeDetailsPage.audittype}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">审核信息</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								商品名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="goodsname" name="goodsname" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nMarketingHomeDetailsPage.goodsname}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">商品名称</label>
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
						     	 value='${nMarketingHomeDetailsPage.unitprices}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">单买价范围</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								商家状态:
							</label>
						</td>
						<td class="value">
						     	 <input id="shoptype" name="shoptype" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nMarketingHomeDetailsPage.shoptype}'>
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
						     	 value='${nMarketingHomeDetailsPage.tuanprices}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">团购价范围</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								商家名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="merchantname" name="merchantname" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nMarketingHomeDetailsPage.merchantname}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">商家名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								活动id:
							</label>
						</td>
						<td class="value">
						     	 <input id="oneid" name="oneid" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nMarketingHomeDetailsPage.oneid}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">活动id</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								顺序:
							</label>
						</td>
						<td class="value">
						     	 <input id="orders" name="orders" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nMarketingHomeDetailsPage.orders}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">顺序</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/n_marketing_home_details/nMarketingHomeDetails.js"></script>		
