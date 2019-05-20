<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>聊天记录</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="nWebsocketController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${nWebsocketPage.id }">
					<input id="createName" name="createName" type="hidden" value="${nWebsocketPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${nWebsocketPage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${nWebsocketPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${nWebsocketPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${nWebsocketPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${nWebsocketPage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${nWebsocketPage.sysOrgCode }">
					<input id="remarks" name="remarks" type="hidden" value="${nWebsocketPage.remarks }">
					<input id="delFlag" name="delFlag" type="hidden" value="${nWebsocketPage.delFlag }">
					<input id="bpmStatus" name="bpmStatus" type="hidden" value="${nWebsocketPage.bpmStatus }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								发送人的id:
							</label>
						</td>
						<td class="value">
						     	 <input id="sendid" name="sendid" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nWebsocketPage.sendid}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">发送人的id</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								接收人id:
							</label>
						</td>
						<td class="value">
						     	 <input id="acceptid" name="acceptid" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nWebsocketPage.acceptid}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">接收人id</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								商品id:
							</label>
						</td>
						<td class="value">
						     	 <input id="goodid" name="goodid" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nWebsocketPage.goodid}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">商品id</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								聊天内容:
							</label>
						</td>
						<td class="value">
						     	 <input id="details" name="details" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nWebsocketPage.details}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">聊天内容</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								发送人名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="sendname" name="sendname" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nWebsocketPage.sendname}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">发送人名称</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								接收人名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="acceptname" name="acceptname" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nWebsocketPage.acceptname}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">接收人名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								是否查看:
							</label>
						</td>
						<td class="value">
						     	 <input id="flag" name="flag" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nWebsocketPage.flag}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否查看</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								通讯标识:
							</label>
						</td>
						<td class="value">
						     	 <input id="socketid" name="socketid" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nWebsocketPage.socketid}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">通讯标识</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								发送人头像:
							</label>
						</td>
						<td class="value">
						     	 <input id="sendurl" name="sendurl" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nWebsocketPage.sendurl}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">发送人头像</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								接收人头像:
							</label>
						</td>
						<td class="value">
						     	 <input id="accepturl" name="accepturl" type="text" style="width: 150px" class="inputxt"  
						     	 ignore="ignore" 
						     	 value='${nWebsocketPage.accepturl}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">接收人头像</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/n_websocket/nWebsocket.js"></script>		
