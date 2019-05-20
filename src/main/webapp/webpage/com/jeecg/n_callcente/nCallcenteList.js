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
	var parms="configId=n_home_banner&field=id,createName,createBy,createDate,update_name,updateBy,update_date,sys_org_code,remarks,del_flag,bpm_status,"
			+ "account,password,callstatus,";
	var account=document.getElementById("account").value;
	
	  $.ajax({
    		async : false,
    		cache : false,
    		type : 'POST',
    		datatype:"json",
    		url : 'nCallcenteController.do?datagrid&'+parms,// 请求的action路径
    		data : {page:strate_page,rows:10,sort:'createDate',
			          order:'desc', 
			          merchantid:merchantid, 
			          account:account
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
	//console.log("dd="+date_list);
	 var tabletitle='';
	 tabletitle+='<th>序号</th>'
		       +'<th>账号</th>'
		       +'<th>密码</th>'
		       +'<th>状态</th>'
		       +'<th>创建时间</th>'
		       +'<th>操作</th>';
	  $("#tabletitle").html(tabletitle);
	 var htmcontent='';
		for ( var i = 0;i<date_list.length;i++) {
			var intege=i+1;
			var obj = date_list[i];
			var callstatus=is_null(obj.callstatus);
			if(callstatus=="0"){
				callstatus="正常";
			}else{
				callstatus="禁用";
			}
		//	var ce=is_null(obj.ce);
			//console.log("intege13="+dictionaryarray[40] );
			/*var cashDepositType=is_null(obj.cashDepositType);
			var cashDepositStatus=is_null(obj.cashDepositStatus);
			var withdrawDeposit=is_null(obj.withdrawDeposit);
			var accountDeposit=is_null(obj.accountDeposit);
			if(cashDepositType=="0"){
				cashDepositType="店铺保证金";
			}else{
				cashDepositType="活动保证金";
			}
			var cashDepositStatusname="";
			var inform = document.getElementById("cashDepositStatus"); 
			for(var j=0;j <inform.options.length;j++){ 
				  var values=inform.options[j].value//参数值 
				  var texts=inform.options[j].text//内容值 
				//  console.log(values+"="+texts);
				  if(cashDepositStatus!=null && cashDepositStatus!="")
				  if(cashDepositStatus==values){
					  cashDepositStatusname=texts;
				  }
			}*/

		
			htmcontent+='<tr >	'
					  +'<td>'+intege+'</td>'
					  +'<td>'+is_null(obj.account)+'</td>'
			          +'<td>'+is_null(obj.password)+'</td>'
			          +'<td>'+callstatus+'</td>'
			          +'<td>'+is_time(obj.createDate)+'</td>'
			          +'<td class="last">'
					  +'<a href="javascript:doUrl(&#39;nCallcenteController.do?goUpdate&amp;load=detail&amp;id='+obj.id+'&#39;)">详情</a>'
					  +'<a href="javascript:delDataT(&#39;nCallcenteController.do?doUpdateDel&amp;callstatus=0&amp;id='+obj.id+'&#39;)">启用</a>'
					  +'<a href="javascript:delDataT(&#39;nCallcenteController.do?doUpdateDel&amp;callstatus=1&amp;id='+obj.id+'&#39;)">禁用</a>'
					  +'</td>'
			       +'</tr>';
          }
		
		  $("#htmcontent").html(htmcontent);
}

function delDataT(url){
	parent.layer.confirm("修改账号状态", {
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
