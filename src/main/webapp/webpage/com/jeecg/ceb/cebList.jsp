<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addCebBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delCebBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addCebBtn').bind('click', function(){   
 		 var tr =  $("#add_ceb_table_template tr").clone();
	 	 $("#add_ceb_table").append(tr);
	 	 resetTrNum('add_ceb_table');
	 	 return false;
    });  
	$('#delCebBtn').bind('click', function(){   
      	$("#add_ceb_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_ceb_table'); 
        return false;
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    	if(location.href.indexOf("load=detail")!=-1){
			$(":input").attr("disabled","true");
			$(".datagrid-toolbar").hide();
		}
		//将表格的表头固定
	    $("#ceb_table").createhftable({
	    	height:'300px',
			width:'auto',
			fixFooter:false
			});
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addCebBtn" href="#">添加</a> <a id="delCebBtn" href="#">删除</a> 
</div>
<table border="0" cellpadding="2" cellspacing="0" id="ceb_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">序号</td>
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">操作</td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						标题1
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						关联
				  </td>
	</tr>
	<tbody id="add_ceb_table">
	<c:if test="${fn:length(cebList)  > 0 }">
		<c:forEach items="${cebList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
					<input name="cebList[${stuts.index }].id" type="hidden" value="${poVal.id }"/>
					<input name="cebList[${stuts.index }].createName" type="hidden" value="${poVal.createName }"/>
					<input name="cebList[${stuts.index }].createBy" type="hidden" value="${poVal.createBy }"/>
					<input name="cebList[${stuts.index }].createDate" type="hidden" value="${poVal.createDate }"/>
					<input name="cebList[${stuts.index }].updateName" type="hidden" value="${poVal.updateName }"/>
					<input name="cebList[${stuts.index }].updateBy" type="hidden" value="${poVal.updateBy }"/>
					<input name="cebList[${stuts.index }].updateDate" type="hidden" value="${poVal.updateDate }"/>
					<input name="cebList[${stuts.index }].sysOrgCode" type="hidden" value="${poVal.sysOrgCode }"/>
					<input name="cebList[${stuts.index }].remarks" type="hidden" value="${poVal.remarks }"/>
					<input name="cebList[${stuts.index }].delFlag" type="hidden" value="${poVal.delFlag }"/>
					<input name="cebList[${stuts.index }].bpmStatus" type="hidden" value="${poVal.bpmStatus }"/>
				   <td align="left">
					  	<input name="cebList[${stuts.index }].title" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.title }">
					  <label class="Validform_label" style="display: none;">标题1</label>
				   </td>
				   <td align="left">
					  	<input name="cebList[${stuts.index }].pkId" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.pkId }">
					  <label class="Validform_label" style="display: none;">关联</label>
				   </td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
