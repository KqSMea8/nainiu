  
$(function () {  
	  var html='<input id="userid22" name="userid" type="hidden" value="2c9a606b5e7061b1015e7073686a003f=杨  帆" >' ;
	     +'<input readonly="readonly" type="text" id="auditperson'+orders+'" name="auditperson'+orders+'" style="width: 150px"'
         +'class="inputxt" datatype="*"  onclick="openUserSelect(\'userid'+orders+'\',\'auditperson'+orders+'\')"  />  '
		  $("#details").append(html);
})

var windowapi;
    try{
		windowapi = frameElement.api, 
		W = windowapi.opener;
		}catch(e){
		}
		var numbid,numbname;
	function openUserSelect(numb_id,numb_name) {
		    numbid=numb_id;
		    numbname=numb_name;
			$.dialog.setting.zIndex = getzIndex(); 
			$.dialog({content: 'url:userController.do?szs_userSelect', zIndex: getzIndex(), title: '用户列表', 
					lock: true,parent:windowapi, width: '1000px', height: '600px', opacity: 0.4,button: [
			   {name: '确定', callback: callbackDepartmentSelect, focus: true},
			   {name: '取消', callback: function (){}}
		   ]}).zindex();
		}
			
		function callbackDepartmentSelect() {
			  var iframe = this.iframe.contentWindow;
				var rowsData = iframe.$('#userList1').datagrid('getSelections');
				if (!rowsData || rowsData.length==0) {
				tip('<t:mutiLang langKey="common.please.select.edit.item"/>');return;
				} 
				if(rowsData.length>0){
					var node = rowsData[0];
				  $('#'+numbname).val(node.realName);
				  $('#'+numbname).blur();
				  $('#'+numbid).val(node.id+"="+node.realName);	 
				}
				  addperson();
			}
		
		  var orders=1;
		  	function Addaudit() {
		  	orders=Number(orders)+Number(1);
			  var html='<div id="per'+orders+'">'
			         +'<br>'
			          +'<input id="userid'+orders+'" name="userid" type="hidden" >'
			          +'<input readonly="readonly" type="text" id="auditperson'+orders+'" name="auditperson'+orders+'" style="width: 150px"'
			          +'class="inputxt" datatype="*"  onclick="openUserSelect(\'userid'+orders+'\',\'auditperson'+orders+'\')"  />  '
			          +'<span class="Validform_checktip"></span>'
			        //  +'<label class="Validform_label" style="display: none;">审核人</label>'
			          +'<button type="button" class="btn btn-primary" onclick="deleteaudit(\'per'+orders+'\')">删除</button>'
			          +'</div>';
			  $("#addperson").append(html);
			
		   }
			function deleteaudit(id) {
				$("#"+id).remove();
				addperson();
			}
			function addperson() {
				var els =document.getElementsByName("userid");
				var pserid="";
				console.log("els.length=="+els.length);
				for (var i = 0, j = els.length; i < j; i++){
				 var	t=i+Number(1);
					var pser_id=els[i].value;
					console.log("pser_id=="+pser_id);
					if(pser_id!=null && pser_id!=""){
						pserid=pserid+","+t+"="+pser_id;
					}
				}
				if(pserid.length>0){
					pserid=pserid.substring(1);
				}
				  $('#audit_person').val(pserid);
				console.log("pserid=="+pserid);
			}