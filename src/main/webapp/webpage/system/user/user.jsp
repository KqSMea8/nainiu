<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>用户信息</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
    <script>
<%-- //        update-start--Author:zhangguoming  Date:20140826 for：将combobox修改为combotree
        function setOrgIds() {
//            var orgIds = $("#orgSelect").combobox("getValues");
            var orgIds = $("#orgSelect").combotree("getValues");
            $("#orgIds").val(orgIds);
        }
        $(function() {
            $("#orgSelect").combotree({
                onChange: function(n, o) {
                    if($("#orgSelect").combotree("getValues") != "") {
                        $("#orgSelect option").eq(1).attr("selected", true);
                    } else {
                        $("#orgSelect option").eq(1).attr("selected", false);
                    }
                }
            });
            $("#orgSelect").combobox("setValues", ${orgIdList});
            $("#orgSelect").combotree("setValues", ${orgIdList});
        }); --%>


        function openDepartmentSelect() {
			$.dialog.setting.zIndex = getzIndex(); 
			var orgIds = $("#orgIds").val();
			$.dialog({content: 'url:departController.do?departoneSelect&orgIds='+orgIds, zIndex: 2100, title: '组织机构列表', lock: true, width: '400px', height: '350px', opacity: 0.4, button: [
			   {name: '<t:mutiLang langKey="common.confirm"/>', callback: callbackDepartmentSelect, focus: true},
			   {name: '<t:mutiLang langKey="common.cancel"/>', callback: function (){}}
		   ]}).zindex();
		}
			
		function callbackDepartmentSelect() {
			  var iframe = this.iframe.contentWindow;
			  var treeObj = iframe.$.fn.zTree.getZTreeObj("departSelect");
			  var nodes = treeObj.getCheckedNodes(true);
			  if(nodes.length>0){
			  var ids='',names='';
			  for(i=0;i<nodes.length;i++){
			     var node = nodes[i];
			     ids= node.id;
			    names= node.name;
			 }
			 $('#departname').val(names);
			 $('#departname').blur();		
			 $('#orgIds').val(ids);		
			}
		}
		
		function callbackClean(){
			$('#departname').val('');
			 $('#orgIds').val('');	
		}
		
		function setOrgIds() {}
		$(function(){
			$("#departname").prev().hide();
		});
    </script>
</head>
  <%--
<body style="overflow-y: hidden" scroll="no">
--%>
<body >
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="userController.do?saveUser" beforeSubmit="setOrgIds">
	<input id="id" name="id" type="hidden" value="${user.id }">
	<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" width="25%" nowrap>
                <label class="Validform_label">  <t:mutiLang langKey="common.username"/>:  </label>
            </td>
			<td class="value" width="85%">
                <c:if test="${user.id!=null }"> ${user.userName } </c:if>
                <c:if test="${user.id==null }">
                    <input id="userName" class="inputxt" name="userName" validType="t_s_base_user,userName,id" value="${user.userName }" datatype="s2-11" />
                    <span class="Validform_checktip"> <t:mutiLang langKey="username.rang2to10"/></span>
                </c:if>
            </td>
		</tr>
		<tr>
			<td align="right" width="10%" nowrap><label class="Validform_label"> <t:mutiLang langKey="common.real.name"/>: </label></td>
			<td class="value" width="10%">
                <input id="realName" class="inputxt" name="realName" value="${user.realName }" datatype="s2-10">
                <span class="Validform_checktip"><t:mutiLang langKey="fill.realname"/></span>
            </td>
		</tr>
		<c:if test="${user.id==null }">
			<tr>
				<td align="right"><label class="Validform_label"> <t:mutiLang langKey="common.password"/>: </label></td>
				<td class="value">
                    <input type="password" class="inputxt" value="" name="password" plugin="passwordStrength" datatype="*6-18" errormsg="" />
                    <span class="passwordStrength" style="display: none;">
                        <span><t:mutiLang langKey="common.weak"/></span>
                        <span><t:mutiLang langKey="common.middle"/></span>
                        <span class="last"><t:mutiLang langKey="common.strong"/></span>
                    </span>
                    <span class="Validform_checktip"> <t:mutiLang langKey="password.rang6to18"/></span>
                </td>
			</tr>
			<tr>
				<td align="right"><label class="Validform_label"> <t:mutiLang langKey="common.repeat.password"/>: </label></td>
				<td class="value">
                    <input id="repassword" class="inputxt" type="password" value="${user.password}" recheck="password" datatype="*6-18" errormsg="两次输入的密码不一致！">
                    <span class="Validform_checktip"><t:mutiLang langKey="common.repeat.password"/></span>
                </td>
			</tr>
		</c:if>
		<tr>
			<td align="right"><label class="Validform_label"> <t:mutiLang langKey="common.department"/>: </label></td>
			<td class="value">
                <input id="departname" name="departname" type="text" readonly="readonly" class="inputxt" datatype="*" value="${departname}">
                <input id="orgIds" name="orgIds" type="hidden" value="${orgIds}">
                <a href="#" class="easyui-linkbutton" plain="true" icon="icon-search" id="departSearch" onclick="openDepartmentSelect()">选择</a>
                <a href="#" class="easyui-linkbutton" plain="true" icon="icon-redo" id="departRedo" onclick="callbackClean()">清空</a>
                <span class="Validform_checktip"><t:mutiLang langKey="please.muti.department"/></span>
            </td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> <t:mutiLang langKey="common.role"/>: </label></td>
			<td class="value" nowrap>
                <input id="roleid" name="roleid" type="hidden" value="${id}"/>
                <input name="roleName" id="roleName" class="inputxt" value="${roleName }" readonly="readonly" datatype="*" />
                <t:choose hiddenName="roleid" hiddenid="id" textname="roleName" url="userController.do?roles" name="roleList" icon="icon-search" title="common.role.list" isclear="true" isInit="true"></t:choose>
                <span class="Validform_checktip"><t:mutiLang langKey="role.muti.select"/></span>
            </td>
		</tr>
		<tr>
			<td align="right" nowrap><label class="Validform_label">  <t:mutiLang langKey="common.phone"/>: </label></td>
			<td class="value">
                <input class="inputxt" name="mobilePhone" value="${user.mobilePhone}" datatype="m" errormsg="手机号码不正确" ignore="ignore">
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		  <%--  
		<tr>
			<td align="right"><label class="Validform_label"> <t:mutiLang langKey="common.tel"/>: </label></td>
			<td class="value">
                <input class="inputxt" name="officePhone" value="${user.officePhone}" datatype="n" errormsg="办公室电话不正确" ignore="ignore">
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> <t:mutiLang langKey="common.common.mail"/>: </label></td>
			<td class="value">
                <input class="inputxt" name="email" value="${user.email}" datatype="e" errormsg="邮箱格式不正确!" ignore="ignore">
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		  --%>
		  <%--  
		<tr>
			<td align="right">
				<label class="Validform_label">
					出生日期:
				</label>
			</td>
			<td class="value">
					   <input id="birth" name="birth" type="text" style="width: 150px" value="${user.birth}" class="Wdate" onClick="WdatePicker()" 
			      						 ignore="ignore"
			      						/>    
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">出生日期</label>
				</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					参加工作时间:
				</label>
			</td>
			<td class="value">
					   <input id="joindate" name="joindate" type="text" style="width: 150px" value="${user.joindate}" class="Wdate" onClick="WdatePicker()" 
						   ignore="ignore"
			      						/>    
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">参加工作时间</label>
				</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					学历:
				</label>
			</td>
			<td class="value">
					  <t:dictSelect field="education" type="list"
							typeGroupCode="education" defaultVal="${user.education}" hasLabel="false"  title="学历"  datatype="*"  
							></t:dictSelect>     
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">学历</label>
				</td>
		</tr>
		<tr>
			<td align="right">
				 <label class="Validform_label">
				 身份证号码
				</label>
			</td>
			<td class="value">
			     	 <input id="idcard" name="idcard" type="text" style="width: 150px" class="inputxt" 
			     	  datatype="*" 
			     	  ignore="checked"
			     	  value='${user.idcard}'>
			     	
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">身份证号码</label>
				</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					入党时间:
				</label>
			</td>
			<td class="value">
					   <input id="rdtime" name="rdtime" type="text" style="width: 150px" value="${user.rdtime}" class="Wdate" onClick="WdatePicker()" 
						   ignore="ignore"
			      						/>    
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">入党时间</label>
				</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					转正时间:
				</label>
			</td>
			<td class="value">
					   <input id="zhuanztime" name="zhuanztime" type="text" style="width: 150px" value="${user.zhuanztime}" class="Wdate" onClick="WdatePicker()" 
						   ignore="ignore"
			      						/>    
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">转正时间</label>
				</td>
		</tr>
		 --%>
		<tr>
			<td align="right">
				 <label class="Validform_label">
				 备注
				</label>
			</td>
			<td class="value">
			     	 <input id="remark" name="remark" type="text" style="width: 150px" class="inputxt" 
			     	   ignore="ignore"
			     	  	 value='${user.remark}'>
			     
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">备注</label>
				</td>
		</tr>
	</table>
</t:formvalid>
</body>