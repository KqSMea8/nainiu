<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>n_red_packet</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="nRedPacketController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${nRedPacketPage.id }">
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
						     	 value='${nRedPacketPage.createName}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">创建人名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								创建人登录名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="createBy" name="createBy" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nRedPacketPage.createBy}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">创建人登录名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								更新人名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="updateName" name="updateName" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nRedPacketPage.updateName}'>
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
						     	 value='${nRedPacketPage.updateBy}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">更新人登录名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								更新日期:
							</label>
						</td>
						<td class="value">
									  <input id="updateDate" name="updateDate" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()"  
									  ignore="ignore"
									    value='<fmt:formatDate value='${nRedPacketPage.updateDate}' type="date" pattern="yyyy-MM-dd"/>'>
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
						     	 value='${nRedPacketPage.sysOrgCode}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">所属部门</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								备注信息:
							</label>
						</td>
						<td class="value">
						     	 <input id="remarks" name="remarks" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nRedPacketPage.remarks}'>
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
						     	 value='${nRedPacketPage.delFlag}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">删除标记</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								流程状态:
							</label>
						</td>
						<td class="value">
						     	 <input id="bpmStatus" name="bpmStatus" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nRedPacketPage.bpmStatus}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">流程状态</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								商家id:
							</label>
						</td>
						<td class="value">
						     	 <input id="merchantId" name="merchantId" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nRedPacketPage.merchantId}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">商家id</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								订单id(关联商城订单):
							</label>
						</td>
						<td class="value">
						     	 <input id="orderId" name="orderId" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nRedPacketPage.orderId}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">订单id(关联商城订单)</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								用户openid:
							</label>
						</td>
						<td class="value">
						     	 <input id="openid" name="openid" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nRedPacketPage.openid}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户openid</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								红包金额:
							</label>
						</td>
						<td class="value">
						     	 <input id="totalAmount" name="totalAmount" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nRedPacketPage.totalAmount}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">红包金额</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								业务结果:
							</label>
						</td>
						<td class="value">
						     	 <input id="resultCode" name="resultCode" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nRedPacketPage.resultCode}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">业务结果</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								返回码:
							</label>
						</td>
						<td class="value">
						     	 <input id="errorCode" name="errorCode" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nRedPacketPage.errorCode}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">返回码</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								返回消息:
							</label>
						</td>
						<td class="value">
						  	 	<textarea id="result" style="width:600px;" class="inputxt" rows="6" name="result" 
						  	 	ignore="ignore"
						  	 	>${nRedPacketPage.result}</textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">返回消息</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								返回说明:
							</label>
						</td>
						<td class="value">
						     	 <input id="reason" name="reason" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nRedPacketPage.reason}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">返回说明</label>
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
									    value='<fmt:formatDate value='${nRedPacketPage.createDate}' type="date" pattern="yyyy-MM-dd"/>'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">创建日期</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/n_red_packet/nRedPacket.js"></script>		
