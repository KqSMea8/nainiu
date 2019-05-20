<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>商品分类</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script src="webpage/com/jeecg/n_goods_classify/fileupload.js"></script>
  <style type="text/css">
  	.combo_self{height: 22px !important;width: 150px !important;}
  	.layout-header .btn {
	    margin:0;
	   float: none !important;
	}
	.btn-default {
	    height: 35px;
	    line-height: 35px;
	    font-size:14px;
	}
  </style>
  
  <script type="text/javascript">
	$(function(){
		$(".combo").removeClass("combo").addClass("combo combo_self");
		$(".combo").each(function(){
			$(this).parent().css("line-height","0px");
		});   
	});
  		
  		 /**树形列表数据转换**/
  function convertTreeData(rows, textField) {
      for(var i = 0; i < rows.length; i++) {
          var row = rows[i];
          row.text = row[textField];
          if(row.children) {
          	row.state = "open";
              convertTreeData(row.children, textField);
          }
      }
  }
  /**树形列表加入子元素**/
  function joinTreeChildren(arr1, arr2) {
      for(var i = 0; i < arr1.length; i++) {
          var row1 = arr1[i];
          for(var j = 0; j < arr2.length; j++) {
              if(row1.id == arr2[j].id) {
                  var children = arr2[j].children;
                  if(children) {
                      row1.children = children;
                  }
                  
              }
          }
      }
  }
  </script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="nGoodsClassifyController.do?doAdd" >
		<input id="id" name="id" type="hidden" value="${nGoodsClassifyPage.id }"/>
		<input id="createName" name="createName" type="hidden" value="${nGoodsClassifyPage.createName }"/>
		<input id="createBy" name="createBy" type="hidden" value="${nGoodsClassifyPage.createBy }"/>
		<input id="createDate" name="createDate" type="hidden" value="${nGoodsClassifyPage.createDate }"/>
		<input id="updateName" name="updateName" type="hidden" value="${nGoodsClassifyPage.updateName }"/>
		<input id="updateBy" name="updateBy" type="hidden" value="${nGoodsClassifyPage.updateBy }"/>
		<input id="updateDate" name="updateDate" type="hidden" value="${nGoodsClassifyPage.updateDate }"/>
		<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${nGoodsClassifyPage.sysOrgCode }"/>
		<input id="orgCode" name="orgCode" type="hidden" value="${nGoodsClassifyPage.orgCode }"/>
		<input id="delFlag" name="delFlag" type="hidden" value="0"/>
		<input id="orgType" name="orgType" type="hidden" value="${nGoodsClassifyPage.orgType }"/>
		<input id="departOrder" name="departOrder" type="hidden" value="${nGoodsClassifyPage.departOrder }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							商品分类名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="departname" name="departname" type="text" style="width: 150px" class="inputxt" 
					     	  datatype="*" 
					     	  ignore="checked"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">商品分类名称</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							详情:
						</label>
					</td>
					<td class="value">
					     	 <input id="description" name="description" type="text" style="width: 150px" class="inputxt" 
					     	  datatype="*" 
					     	  ignore="checked"
					     	  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">详情</label>
						</td>
				</tr>
				<tr>
				<td align="right">
					<label class="Validform_label">
						保证金:
					</label>
				</td>
				<td class="value">
				     	 <input id="deposit" name="deposit" type="text" style="width: 150px" class="inputxt" 
				     	  datatype="n" 
				     	  ignore="checked"
				     	  />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">详情</label>
					</td>
			</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
						上一级:
						</label>
					</td>
					<td class="value">
							<input id="parentdepartid" name="parentdepartid" type="text" style="width: 150px" class="inputxt easyui-combotree" 
							
							ignore="ignore"
							data-options="panelHeight:'220',
				                    url: 'nGoodsClassifyController.do?datagrid&field=id,departname',  
				                    loadFilter: function(data) {
				                    	var rows = data.rows || data;
				                    	var win = frameElement.api.opener;
				                    	var listRows = win.getDataGrid().treegrid('getData');
				                    	joinTreeChildren(rows, listRows);
				                    	convertTreeData(rows, 'departname');
				                    	return rows; 
				                    },
				                    onLoadSuccess: function() {
				                    	var win = frameElement.api.opener;
				                    	var currRow = win.getDataGrid().treegrid('getSelected');
				                    	if(!'${nGoodsClassifyPage.id}') {
				                    		//增加时，选择当前父菜单
				                    		if(currRow) {
				                    			$('#parentdepartid').combotree('setValue', currRow.id);
				                    		}
				                    	}else {
				                    		//编辑时，选择当前父菜单
				                    		if(currRow) {
				                    			$('#parentdepartid').combotree('setValue', currRow.parentdepartid);
				                    		}
				                    	}
				                    }">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">父部门ID</label>
						</td>
				</tr>
				
				<tr>
					<td align="right">
						<label class="Validform_label">
							上传图标:
						</label>
					</td>
					<td class="value">
						<img style="width: 109px;height: 109px; overflow: hidden;" id="picurlimg"
						src="${nHomeBannerPage.picurl}">
						<a id="picurlUrl"  target="_blank" style="display: none;">预览</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a id="picurldelete" href="javascript:del_pic('picurlUrl','picurlwu','picurldelete','picurl','picurlimg')" style="display: none;">删除</a>
						<input id="picurl" name="picurl" type="hidden" value=""> </a>
						</br>
						<a href="javascript:;" style="padding: 4px 10px;line-height: 20px;position: relative;cursor: pointer; color: #454343;border: 1px solid #ddd; border-radius: 4px; overflow: hidden; display: inline-block;">
							选择文件
							<input type="file" name="picurlfile" id="picurlfile" 
							onchange="Javascript:checkFileChange('30','30000','30000','picurlfile','picurlUrl','picurlwu','picurldelete','picurl','picurlimg');" 
							style="position: absolute; font-size: 100px; right: 0;  top: 0; opacity: 0;cursor: pointer; width: 100%;  height: 100%;">
						</a>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/n_goods_classify/nGoodsClassify.js"></script>		
