<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>聊天目录</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="nWebsocketColumController.do?doAdd" >
		<input id="id" name="id" type="hidden" value="${nWebsocketColumPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							创建日期:
						</label>
					</td>
					<td class="value">
							   <input id="createDate" name="createDate" type="text" style="width: 150px" class="Wdate" onClick="WdatePicker()" 
					      						 
					      						ignore="ignore"
					      						/>    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">创建日期</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							发送人的id:
						</label>
					</td>
					<td class="value">
					     	 <input id="sendid" name="sendid" type="text" style="width: 150px" class="inputxt" 
					     	  
					     	  ignore="ignore"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">发送人的id</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							接收人id:
						</label>
					</td>
					<td class="value">
					     	 <input id="acceptid" name="acceptid" type="text" style="width: 150px" class="inputxt" 
					     	  
					     	  ignore="ignore"
					     	  />
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
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">商品id</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							聊天内容:
						</label>
					</td>
					<td class="value">
						  	 <textarea style="width:600px;" class="inputxt" rows="6" id="details" name="details" 
						  	 ignore="ignore"
						  	 ></textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">聊天内容</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							接收人名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="acceptname" name="acceptname" type="text" style="width: 150px" class="inputxt" 
					     	  
					     	  ignore="ignore"
					     	  />
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
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否查看</label>
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
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">发送人名称</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							通讯标识:
						</label>
					</td>
					<td class="value">
					     	 <input id="socketid" name="socketid" type="text" style="width: 150px" class="inputxt" 
					     	  
					     	  ignore="ignore"
					     	  />
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
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">发送人头像</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							接收人头像:
						</label>
					</td>
					<td class="value">
					     	 <input id="accepturl" name="accepturl" type="text" style="width: 150px" class="inputxt" 
					     	  
					     	  ignore="ignore"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">接收人头像</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							推送状态:
						</label>
					</td>
					<td class="value">
					     	 <input id="pushtype" name="pushtype" type="text" style="width: 150px" class="inputxt" 
					     	  
					     	  ignore="ignore"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">推送状态</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							消息类型:
						</label>
					</td>
					<td class="value">
					     	 <input id="type" name="type" type="text" style="width: 150px" class="inputxt" 
					     	  
					     	  ignore="ignore"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">消息类型</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							标题:
						</label>
					</td>
					<td class="value">
					     	 <input id="title" name="title" type="text" style="width: 150px" class="inputxt" 
					     	  
					     	  ignore="ignore"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">标题</label>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/n_websocket_colum/nWebsocketColum.js"></script>		
