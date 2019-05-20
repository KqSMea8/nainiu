<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>规格组合商品详情</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="nStandardDetailsController.do?doAdd" >
		<input id="id" name="id" type="hidden" value="${nStandardDetailsPage.id }"/>
		<input id="createName" name="createName" type="hidden" value="${nStandardDetailsPage.createName }"/>
		<input id="createBy" name="createBy" type="hidden" value="${nStandardDetailsPage.createBy }"/>
		<input id="createDate" name="createDate" type="hidden" value="${nStandardDetailsPage.createDate }"/>
		<input id="updateName" name="updateName" type="hidden" value="${nStandardDetailsPage.updateName }"/>
		<input id="updateBy" name="updateBy" type="hidden" value="${nStandardDetailsPage.updateBy }"/>
		<input id="updateDate" name="updateDate" type="hidden" value="${nStandardDetailsPage.updateDate }"/>
		<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${nStandardDetailsPage.sysOrgCode }"/>
		<input id="remarks" name="remarks" type="hidden" value="${nStandardDetailsPage.remarks }"/>
		<input id="delFlag" name="delFlag" type="hidden" value="${nStandardDetailsPage.delFlag }"/>
		<input id="bpmStatus" name="bpmStatus" type="hidden" value="${nStandardDetailsPage.bpmStatus }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							商品:
						</label>
					</td>
					<td class="value">
					     	 <input id="goodsId" name="goodsId" type="text" style="width: 150px" class="inputxt" 
					     	  
					     	  ignore="ignore"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">商品</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							规格1:
						</label>
					</td>
					<td class="value">
					     	 <input id="standardOne" name="standardOne" type="text" style="width: 150px" class="inputxt" 
					     	  
					     	  ignore="ignore"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">规格1</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							规格2:
						</label>
					</td>
					<td class="value">
					     	 <input id="standardTwo" name="standardTwo" type="text" style="width: 150px" class="inputxt" 
					     	  
					     	  ignore="ignore"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">规格2</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							库存增减:
						</label>
					</td>
					<td class="value">
					     	 <input id="sumNumber" name="sumNumber" type="text" style="width: 150px" class="inputxt" 
					     	  
					     	  ignore="ignore"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">库存增减</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							团购价:
						</label>
					</td>
					<td class="value">
					     	 <input id="tuanPrice" name="tuanPrice" type="text" style="width: 150px" class="inputxt" 
					     	  
					     	  ignore="ignore"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">团购价</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							单买价:
						</label>
					</td>
					<td class="value">
					     	 <input id="unitPrice" name="unitPrice" type="text" style="width: 150px" class="inputxt" 
					     	  
					     	  ignore="ignore"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">单买价</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							商家编码:
						</label>
					</td>
					<td class="value">
					     	 <input id="goodsCode" name="goodsCode" type="text" style="width: 150px" class="inputxt" 
					     	  
					     	  ignore="ignore"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">商家编码</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							SKU预览图:
						</label>
					</td>
					<td class="value">
					     	 <input id="picUrl" name="picUrl" type="text" style="width: 150px" class="inputxt" 
					     	  
					     	  ignore="ignore"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">SKU预览图</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							状态:
						</label>
					</td>
					<td class="value">
					     	 <input id="shangpintype" name="shangpintype" type="text" style="width: 150px" class="inputxt" 
					     	  
					     	  ignore="ignore"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">状态</label>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/n_standard_details/nStandardDetails.js"></script>		
