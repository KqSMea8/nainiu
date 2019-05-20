<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>我的收藏</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="nUserCollectController.do?doAdd" >
		<input id="id" name="id" type="hidden" value="${nUserCollectPage.id }"/>
		<input id="createName" name="createName" type="hidden" value="${nUserCollectPage.createName }"/>
		<input id="createBy" name="createBy" type="hidden" value="${nUserCollectPage.createBy }"/>
		<input id="createDate" name="createDate" type="hidden" value="${nUserCollectPage.createDate }"/>
		<input id="updateName" name="updateName" type="hidden" value="${nUserCollectPage.updateName }"/>
		<input id="updateBy" name="updateBy" type="hidden" value="${nUserCollectPage.updateBy }"/>
		<input id="updateDate" name="updateDate" type="hidden" value="${nUserCollectPage.updateDate }"/>
		<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${nUserCollectPage.sysOrgCode }"/>
		<input id="remarks" name="remarks" type="hidden" value="${nUserCollectPage.remarks }"/>
		<input id="delFlag" name="delFlag" type="hidden" value="${nUserCollectPage.delFlag }"/>
		<input id="bpmStatus" name="bpmStatus" type="hidden" value="${nUserCollectPage.bpmStatus }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							用户id:
						</label>
					</td>
					<td class="value">
					     	 <input id="userId" name="userId" type="text" style="width: 150px" class="inputxt" 
					     	  
					     	  ignore="ignore"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户id</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							用户名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="realname" name="realname" type="text" style="width: 150px" class="inputxt" 
					     	  
					     	  ignore="ignore"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户名称</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							商品id:
						</label>
					</td>
					<td class="value">
					     	 <input id="goodsId" name="goodsId" type="text" style="width: 150px" class="inputxt" 
					     	  
					     	  ignore="ignore"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">商品id</label>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/n_user_collect/nUserCollect.js"></script>		
