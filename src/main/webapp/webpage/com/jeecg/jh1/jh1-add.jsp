<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>jh1</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script src="plug-in/layer/layer.js"></script>
 <script>
	function btn_ok(){
		$("#btnsub").click();
	}
	function callback(data){
		
		
		if(data.success){
			layer.alert(data.msg, function(index){
				window.location.href="jh1Controller.do?list"
				layer.close(index);
			});       
		}
		else{
			layer.alert(data.msg);
		}
	}
</script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="false" usePlugin="password" layout="table" action="jh1Controller.do?doAdd" tiptype="1" callback="callback">
					<input id="id" name="id" type="hidden" value="${jh1Page.id }">
					<input id="createName" name="createName" type="hidden" value="${jh1Page.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${jh1Page.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${jh1Page.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${jh1Page.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${jh1Page.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${jh1Page.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${jh1Page.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${jh1Page.sysCompanyCode }">
					<input id="bpmStatus" name="bpmStatus" type="hidden" value="${jh1Page.bpmStatus }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							标题:
						</label>
					</td>
					<td class="value">
					     	 <input id="title" name="title" type="text" style="width: 150px" 
									ignore="ignore"
					     	 class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">标题</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							短标题:
						</label>
					</td>
					<td class="value">
					     	 <input id="duantitle" name="duantitle" type="text" style="width: 150px" 
									ignore="ignore"
					     	 class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">短标题</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							详情:
						</label>
					</td>
					<td class="value">
					     	 <input id="deta" name="deta" type="text" style="width: 150px" 
									ignore="ignore"
					     	 class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">详情</label>
						</td>
				</tr>
				<tr>
					<td height="50px" align="center" colspan="2">
						<a style="margin-left:80px" href="#" class="easyui-linkbutton l-btn"  plain="true" iconcls="icon-le-back" onclick="history.go(-1)">返回</a>
						<div style="display:none"><input type="submit" id ="btnsub" value=""/></div>
						<a  href="#" class="easyui-linkbutton l-btn"  iconcls="icon-le-ok" onclick="btn_ok()">提交</a>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/jh1/jh1.js"></script>		
