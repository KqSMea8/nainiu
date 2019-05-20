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
			+ "merchantid,merchantname,cashDepositType,oneid,marketingname,price,recharge,cashDepositStatus,account,serviceCharge,withdrawDeposit,details,accountDeposit,moneytime,";
	var merchantname=document.getElementById("merchantname").value;
	var cashDepositStatus=document.getElementById("cashDepositStatus").value;
	var withdrawDeposit=document.getElementById("withdrawDeposit").value;
	var accountDeposit=document.getElementById("accountDeposit").value;
	//var marketingname=document.getElementById("marketingname").value;
//	var starttype=document.getElementById("starttype").value;
//	var shangpintype=document.getElementById("shangpintype").value;
//	var goods_detaisl_status=document.getElementById("goods_detaisl_status").value;
	
	//var t=$("#shoptype").val(); 
	//alert(shoptype_value+"="+t);
	  $.ajax({
    		async : false,
    		cache : false,
    		type : 'POST',
    		datatype:"json",
    		url : 'nCashDepositController.do?datagrid&'+parms,// 请求的action路径
    		data : {page:strate_page,rows:10,sort:'withdrawDeposit,createDate',
			          order:'desc,desc', 
			          merchantname:merchantname,
			          cashDepositStatus:cashDepositStatus,
			          withdrawDeposit:withdrawDeposit,
			          accountDeposit:accountDeposit,
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
		       +'<th>商家名称</th>'
		    //   +'<th>活动名称</th>'
		       +'<th>保证金类型</th>'
		       +'<th>资金类型</th>'
		       +'<th>资金状态</th>'
		       +'<th>金额</th>'
		       +'<th>审核状态</th>'
		       +'<th>创建时间</th>'
		       +'<th>操作</th>';
	  $("#tabletitle").html(tabletitle);
	 var htmcontent='';
		for ( var i = 0;i<date_list.length;i++) {
			var intege=i+1;
			var obj = date_list[i];
		//	var ce=is_null(obj.ce);
			//console.log("intege13="+dictionaryarray[40] );
			var cashDepositType=is_null(obj.cashDepositType);
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
			}

			var withdrawDepositname="";
			var informwithdrawDeposit = document.getElementById("withdrawDeposit"); 
			for(var j=0;j <informwithdrawDeposit.options.length;j++){ 
				  var values=informwithdrawDeposit.options[j].value//参数值 
				  var texts=informwithdrawDeposit.options[j].text//内容值 
				//  console.log(values+"="+texts);
				  if(withdrawDeposit!=null && withdrawDeposit!="")
				  if(withdrawDeposit==values){
					  withdrawDepositname=texts;
				  }
			}
			var accountDepositname="";
			var informaccountDeposit = document.getElementById("accountDeposit"); 
			for(var j=0;j <informaccountDeposit.options.length;j++){ 
				var values=informaccountDeposit.options[j].value//参数值 
				var texts=informaccountDeposit.options[j].text//内容值 
						//  console.log(values+"="+texts);
						if(accountDeposit!=null && accountDeposit!="")
						if(accountDeposit==values){
							accountDepositname=texts;
						}
			}
			
			htmcontent+='<tr >	'
					  +'<td>'+intege+'</td>'
					  +'<td>'+is_null(obj.merchantname)+'</td>'
			        //  +'<td>'+is_null(obj.marketingname)+'</td>'
			          +'<td>'+cashDepositType+'</td>'
			          +'<td>'+cashDepositStatusname+'</td>';
			          if(withdrawDeposit=="6"){
			        	  htmcontent+='<td style="color:red;">'+withdrawDepositname+'</td>';
			          }else{
			        	  htmcontent+='<td >'+withdrawDepositname+'</td>';
			          }
			       htmcontent+='<td>'+is_null(obj.price)+'</td>'
			          +'<td>'+accountDepositname+'</td>'
			          +'<td>'+is_time(obj.createDate)+'</td>'
			          +'<td class="last">';
//			          +'<a href="javascript:doUrl(&#39;nCashDepositController.do?goUpdate&amp;id='+obj.id+'&#39;)">编辑</a>'
			       if(accountDeposit==0){
			        htmcontent+='<a href="javascript:delDataT(&#39;同意退款吗&#39;,&#39;nCashDepositController.do?doDel&amp;type=1&amp;id='+obj.id+'&#39;)">同意</a>'
					  +'<a href="javascript:delDataT(&#39;不同意退款吗&#39;,&#39;nCashDepositController.do?doDel&amp;type=2&amp;id='+obj.id+'&#39;)">不同意</a>'
					  ;
			       }
			       htmcontent+='<a href="javascript:doUrl(&#39;nCashDepositController.do?goUpdate&amp;load=detail&amp;id='+obj.id+'&#39;)">详情</a>'
					  +'</td>'
			       +'</tr>';
          }
		
		  $("#htmcontent").html(htmcontent);
}

function delDataT(title,url){
	parent.layer.confirm(title, {
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
