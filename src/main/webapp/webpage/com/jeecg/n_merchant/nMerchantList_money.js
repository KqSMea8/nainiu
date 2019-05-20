//var strate_page="1";
$(function(){
	asyncdic();
	 var strate_page = jQuery('#pageNo').val();
	 console.log("strate_page0="+strate_page );
	 if(strate_page==""|| strate_page==null || strate_page=="undefined"){
		 strate_page="1";
	 }
	 console.log("strate_page1="+strate_page );
	 content(strate_page);
  });

function serchcontent() {
	 var strate_page = jQuery('#pageNo').val();
	 content(strate_page);
}
function pageSizeChange(number) {
    jQuery('#pageNo').val(number);
    //document.getElementById('formSubmit').submit();
    content(number);
}
function pageNoChange() {
    var gotoPageValue = jQuery('#gotoPage').val();
    if (gotoPageValue == null || gotoPageValue == '') {
	jQuery('#pageNo').val(1);
    }else{
		jQuery('#pageNo').val(gotoPageValue);
    }
    content(gotoPageValue);
}
//内容解析
function content(strate_page){
	var parms="&configId=n_merchant&field=id,create_name,create_by,update_name,update_by,update_date,sys_org_code,remarks,delFlag,bpm_status,"
			+ "company,shoptype,joinname,email,phone,card,urgencyName,urgencyPhone,startshoptype,"
			+ "shifou,cardZUrl,cardFUrl,cardBUrl,bossPhone,bossEmail,org,orgUrl,bankUrl,ispay,"
			+ "accountType,pid,role,actionMoney,freezeMoney,accountStatus,auditType,"
			+ "createDate,depositMoney,";
	var company=document.getElementById("company").value;
	var phone=document.getElementById("phone").value;
	var shoptype=document.getElementById("shoptype").value;
	var startshoptype=document.getElementById("startshoptype").value;
	var ispay=document.getElementById("ispay").value;
	var accountStatus=document.getElementById("accountStatus").value;
	var auditType=document.getElementById("auditType").value;
	//var t=$("#shoptype").val(); 
	//alert(shoptype_value+"="+t);
	  $.ajax({
    		async : false,
    		cache : false,
    		type : 'POST',
    		datatype:"json",
    		url : 'nMerchantController.do?datagrid&'+parms,// 请求的action路径
    		data : {page:strate_page,rows:10,sort:'createDate',
		          order:'desc', 
		          company:company,
		          phone:phone,
		          shoptype:shoptype,
		          startshoptype:startshoptype,
		          ispay:ispay,
		          accountStatus:accountStatus,
		          auditType:auditType
		           },//多条件排序sort:createDate,userNameorder:asc,desc
    		error : function() {// 请求失败处理函数
    		},
    		success : function(data) {
    			var total=data.total;
    			var rows=data.rows;
    		  //  var nowpage=jQuery('#pageNo').val();
    		    var sumpage=Math.ceil(total/10);
    		    contentdetails(rows);
    			 page(total,strate_page,sumpage);
    			 
    		}
        });
}
var dictionaryarray;
function asyncdic() {
	//数字字典
	  /*$.ajax({
  		async : false,
  		cache : false,
  		type : 'POST',
  		datatype:"json",
  		url : 'uplodfil.do?dictionary',// 请求的action路径
  		data : {typegroupcode:'education'},//多条件排序sort:createDate,userNameorder:asc,desc
  		error : function() {// 请求失败处理函数
  		},
  		success : function(data) {
  			dictionaryarray=eval('(' + data + ')');
  		}
      });*/
	  //其他表
	 /* $.ajax({
	  		async : false,
	  		cache : false,
	  		type : 'POST',
	  		url : 'uplodfil.do?dictable',// 请求的action路径
	  		data : {table:'education'，ids:""},//多条件排序sort:createDate,userNameorder:asc,desc
	  		error : function() {// 请求失败处理函数
	  		},
	  		success : function(data) {
	  			var total=data.total;
	  		}
	      });*/
}
function contentdetails(date_list){
	console.log("dd="+date_list);
	 var tabletitle='';
	 tabletitle+='<th>序号</th>'
		       +'<th>店铺名称</th>'
		       +'<th>入驻姓名</th>'
		       +'<th>入驻手机号</th>'
		       +'<th>开店类别</th>'
		       +'<th>开店性质</th>'
		       +'<th>缴费</th>'
		       +'<th>保证金</th>'
		       +'<th>账号状态</th>'
		       +'<th>可提现金额</th>'
		       +'<th>待结算金额</th>'
		       +'<th>冻结金额</th>'
		     //  +'<th>创建时间</th>'
		       +'<th>操作</th>';
	  $("#tabletitle").html(tabletitle);
	 var htmcontent='';
		for ( var i = 0;i<date_list.length;i++) {
			var intege=i+1;
			var obj = date_list[i];
		//	var ce=is_null(obj.ce);
			//console.log("intege13="+dictionaryarray[40] );
			var shoptype=is_null(obj.shoptype);
			var startshoptype=is_null(obj.startshoptype);
			var ispay=is_null(obj.ispay);
			var accountStatus=is_null(obj.accountStatus);
			var auditType=is_null(obj.auditType);
			if(shoptype=="0"){
				shoptype="个人";
			}else{
				shoptype="企业";
			}
			if(startshoptype=="0"){
				startshoptype="vip商家";
			}else{
				startshoptype="普通商家";
			}
			if(ispay=="0"){
				ispay="是";
			}else{
				ispay="否";
			}
			if(accountStatus=="0"){
				accountStatus="正常";
			}else if(accountStatus=="1"){
				accountStatus="禁用";
			}else{
				accountStatus="销户";
			}
		/*	if(auditType=="0"){
				auditType="审核中";
			}else if(auditType=="1"){
				auditType="通过";
			}else{
				auditType="不通过";
			}*/
			getFreeze(obj.id,obj.id);
			htmcontent+='<tr >	'
					  +'<td>'+intege+'</td>'
					  +'<td>'+is_null(obj.company)+'</td>'
			          +'<td>'+is_null(obj.joinname)+'</td>'
			          +'<td>'+is_null(obj.phone)+'</td>'
			          +'<td>'+shoptype+'</td>'
			          +'<td>'+startshoptype+'</td>'
			          +'<td>'+ispay+'</td>'
			          +'<td>'+is_null(obj.depositMoney)+'</td>'
			          +'<td>'+accountStatus+'</td>'
			          +'<td>'+is_null(obj.actionMoney)+'</td>'
			          +'<td id="'+obj.id+'" name="'+obj.id+'" ></td>'
			          +'<td id="f'+obj.id+'" name="f'+obj.id+'" ></td>'
			       //   +'<td>'+is_time(obj.createDate)+'</td>'
			          +'<td class="last">'
			          //+'<a href="javascript:doUrl(&#39;nMerchantController.do?goUpdate&amp;id='+obj.id+'&#39;)">编辑</a>'
					//  +'<a href="javascript:delDataT(&#39;nMerchantController.do?doDel&amp;id='+obj.id+'&#39;)">删除</a>'
					  +'<a href="javascript:doUrl(&#39;nMerchantController.do?goUpdate_money&amp;load=detail&amp;id='+obj.id+'&#39;)">详情</a>'
					  +'<a href="javascript:doUrl(&#39;nMerchantFreezeController.do?list_merchant&amp;merchantid='+obj.id+'&#39;)">冻结资金</a>'
					 // +'<a href="javascript:doUrl(&#39;nMerchantController.do?goAudit&amp;load=detail&amp;id='+obj.id+'&#39;)">审核</a>'
					 //  +'<a href="javascript:SetVip(&#39;nMerchantController.do?setvip&amp;id='+obj.id+'&#39;)">设置vip商家</a>'
					 // +'<a href="javascript:Sendcode(&#39;nMerchantController.do?sendcode&amp;id='+obj.id+'&#39;)">短信通知</a>'
					  +'</td>'
			       +'</tr>';
          }
	//	htmcontent+='<div id="b" ></div>';
		  $("#htmcontent").html(htmcontent);
}
function getFreeze(id,merchantid) {
$.ajax({
	async : true,
	cache : false,
	type : 'POST',
	datatype:"json",
	url : 'nMerchantFreezeController.do?freezemerchantMoney',// 请求的action路径
	data : {merchantid:merchantid},//多条件排序sort:createDate,userNameorder:asc,desc
	error : function() {// 请求失败处理函数
	},
	success : function(data) {
		
		data=eval('(' + data + ')');
	//	alert(data.freezeMoney+"="+id);
		
//		document.getElementById(id).innerHTML(data.freezeMoney);
//		  $('#f3af1eacafd14bf9a49c5044a7101a98').html(data.freezeMoney);
//		  $("#a").html(data.freezeMoney);
		  $("#f"+id).html(data.freezeMoney);
	}
});

$.ajax({
	async : true,
	cache : false,
	type : 'POST',
	datatype:"json",
	url : 'nMerchantAccountController.do?freezeMoney',// 请求的action路径
	data : {merchantid:merchantid},//多条件排序sort:createDate,userNameorder:asc,desc
	error : function() {// 请求失败处理函数
	},
	success : function(data) {
		
		data=eval('(' + data + ')');
	//	alert(data.freezeMoney+"="+id);
		
//		document.getElementById(id).innerHTML(data.freezeMoney);
//		  $('#f3af1eacafd14bf9a49c5044a7101a98').html(data.freezeMoney);
//		  $("#a").html(data.freezeMoney);
		  $("#"+id).html(data.freezeMoney);
	}
});
}
function delDataT(url){
	parent.layer.confirm('确认删除吗？', {
	    btn: ['确定','取消'] //按钮
//	    shade: [0.3, '#393D49'] //不显示遮罩
	}, function(){
		$.ajax({
		      url: url,
		      cache: false,
			  dataType: "json",
		      success: function(data){
		    	  	if(data.success){
//		    	  		alert(data.msg);
		    	  		parent.layer.alert(data.msg, {
		        	        icon: 1,
		        	        shadeClose: false,
		        	        title: '提示'
		        	    },function(index){
		        	    	//document.getElementById('formSubmit').submit();
		        	    	 serchcontent();
		        	    	parent.layer.close(index);
		        	    });
		        	}else{
		        		layeralert(data.msg,0);
		        	}
		      },  
		        error: function(data, status, e){  
		        	if(data.status == "401"){
		        		layeralert("没有权限！",0);
						return;
					}
		        }
		    });
	}, function(){
	   
	});
}

function Sendcode(url){
	parent.layer.confirm('确认重新发送短信通知吗？', {
	    btn: ['确定','取消'] //按钮
//	    shade: [0.3, '#393D49'] //不显示遮罩
	}, function(){
		$.ajax({
		      url: url,
		      cache: false,
			  dataType: "json",
		      success: function(data){
		    	  	if(data.success){
//		    	  		alert(data.msg);
		    	  		parent.layer.alert(data.msg, {
		        	        icon: 1,
		        	        shadeClose: false,
		        	        title: '提示'
		        	    },function(index){
		        	    	//document.getElementById('formSubmit').submit();
		        	    	 serchcontent();
		        	    	parent.layer.close(index);
		        	    });
		        	}else{
		        		layeralert(data.msg,0);
		        	}
		      },  
		        error: function(data, status, e){  
		        	if(data.status == "401"){
		        		layeralert("没有权限！",0);
						return;
					}
		        }
		    });
	}, function(){
	   
	});
}

function SetVip(url){
	parent.layer.confirm('确认设置成VIP商家吗？', {
	    btn: ['确定','取消'] //按钮
//	    shade: [0.3, '#393D49'] //不显示遮罩
	}, function(){
		$.ajax({
		      url: url,
		      cache: false,
			  dataType: "json",
		      success: function(data){
		    	  	if(data.success){
//		    	  		alert(data.msg);
		    	  		parent.layer.alert(data.msg, {
		        	        icon: 1,
		        	        shadeClose: false,
		        	        title: '提示'
		        	    },function(index){
		        	    	//document.getElementById('formSubmit').submit();
		        	    	 serchcontent();
		        	    	parent.layer.close(index);
		        	    });
		        	}else{
		        		layeralert(data.msg,0);
		        	}
		      },  
		        error: function(data, status, e){  
		        	if(data.status == "401"){
		        		layeralert("没有权限！",0);
						return;
					}
		        }
		    });
	}, function(){
	   
	});
}

//为空判断
function is_null(str){
	if(str==null ||str=="" || str.length==0)
	{
		return ""; 
	}
	return str; 
}	

  //时间格式校正
function is_time(str){
	if(str==null ||str=="" || str.length==0)
	{
		return ""; 
	}else if( str.length>19){
		str=str.substring(0,19);
	}
	return str; 
}	
