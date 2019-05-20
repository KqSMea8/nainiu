<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>大转盘奖品发放规则</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="nTurntableActivityController.do?doAdd" >
		<input id="id" name="id" type="hidden" value="${nTurntableActivityPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							奖品id:
						</label>
					</td>
					<td class="value">
					     	 <input id="priceId" name="priceId" type="text" style="width: 150px" class="inputxt"
					     	  
					     	  ignore="ignore"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">奖品id</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							奖品名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="priceName" name="priceName" type="text" style="width: 150px" class="inputxt"
					     	  datatype="*6-16" 
					     	  ignore="checked"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">奖品名称</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							奖品数量:
						</label>
					</td>
					<td class="value">
					     	 <input id="priceAmount" name="priceAmount" type="text" style="width: 150px" class="inputxt"
					     	  datatype="*" 
					     	  ignore="checked"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">奖品数量</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							奖品权重:
						</label>
					</td>
					<td class="value">
					     	 <input id="priceWeight" name="priceWeight" type="text" style="width: 150px" class="inputxt"
					     	  datatype="*" 
					     	  ignore="checked"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">奖品权重</label>
						</td>
				</tr>

			</table>
		</t:formvalid>
 </body>
  <%--<script src = "webpage/com/jeecg/n_turntable_activity/nRedPacketRule.js"></script>--%>
