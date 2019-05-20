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
			+ "merchantid,merchantname,,accountStatusType,priceStatusType,price,account,accountDeposit,moneytime,";
	var merchantname=document.getElementById("merchantname").value;
	var accountStatusType=document.getElementById("accountStatusType").value;
	var priceStatusType=document.getElementById("priceStatusType").value;
	var accountDeposit=document.getElementById("accountDeposit").value;
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
    		url : 'nMerchantAccountController.do?datagrid&'+parms,// 请求的action路径
    		data : {page:strate_page,rows:10,sort:'accountDeposit,createDate',
			          order:'asc,desc', 
			          merchantname:merchantname,
			          accountStatusType:accountStatusType,
			          accountDeposit:accountDeposit,
			          priceStatusType:priceStatusType
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
    tabletitle+='<th></th>'
        	+'<th>序号</th>'
			+'<th>商户编号</th>'
		    +'<th>用户ID</th>'
		    +'<th>提现实名</th>'
		    +'<th>账号类型</th>'
		    +'<th>类型</th>'
		    +'<th>金额</th>'
		    +'<th>提现账户</th>'
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
			var accountStatusType=is_null(obj.accountStatusType);
			var priceStatusType=is_null(obj.priceStatusType);
			var accountDeposit=is_null(obj.accountDeposit);
			
			var accountStatusTypename="";
			var inform = document.getElementById("accountStatusType"); 
			for(var j=0;j <inform.options.length;j++){ 
				  var values=inform.options[j].value//参数值 
				  var texts=inform.options[j].text//内容值 
				//  console.log(values+"="+texts);
				  if(accountStatusType!=null && accountStatusType!="")
				  if(accountStatusType==values){
					  accountStatusTypename=texts;
				  }
			}
			var priceStatusTypename="";
			var informtwo = document.getElementById("priceStatusType"); 
			for(var j=0;j <informtwo.options.length;j++){ 
				  var values=informtwo.options[j].value//参数值 
				  var texts=informtwo.options[j].text//内容值 
				//  console.log(values+"="+texts);
				if(priceStatusType!=null && priceStatusType!="")
				  if(priceStatusType==values){
					  priceStatusTypename=texts;
				  }
			}
			var accountDepositname="";
			var informthree = document.getElementById("accountDeposit"); 
			for(var j=0;j <informthree.options.length;j++){ 
				var values=informthree.options[j].value//参数值 
				var texts=informthree.options[j].text//内容值 
						//  console.log(values+"="+texts);
						if(accountDeposit!=null && accountDeposit!="")
						if(accountDeposit==values){
							accountDepositname=texts;
						}
			}
			
			
			htmcontent+='<tr >	'
               		 +'<td height="20" bgcolor="#FFFFFF"><div align="center"><input type="checkbox" name="checkAll[]" id="checkAll" onclick="setSelectAll();" value='+is_null(obj.id)+'></td>'
					  +'<td>'+intege+'</td>'
               		 +'<td>'+is_null(obj.id)+'</td>'
                	 +'<td>'+is_null(obj.createBy)+'</td>'
					  +'<td>'+is_null(obj.merchantname)+'</td>'
			          +'<td>'+accountStatusTypename+'</td>'
			          +'<td>'+priceStatusTypename+'</td>'
			          +'<td>'+is_null(obj.price)+'</td>'
			          +'<td>'+is_null(obj.account)+'</td>'
			          +'<td>'+accountDepositname+'</td>'
			          +'<td>'+is_time(obj.createDate)+'</td>'
			          +'<td class="last">'
//			          +'<a href="javascript:doUrl(&#39;nMerchantAccountController.do?goUpdate&amp;id='+obj.id+'&#39;)">编辑</a>'
					 // +'<a href="javascript:delDataT(&#39;nMerchantAccountController.do?doDel&amp;id='+obj.id+'&#39;)">删除</a>'
			            +'<a href="javascript:delDataT(&#39;同意提现吗&#39;,&#39;nMerchantAccountController.do?doDel&amp;type=1&amp;id='+obj.id+'&#39;)">同意</a>'
					  +'<a href="javascript:delDataT(&#39;不同意提现吗&#39;,&#39;nMerchantAccountController.do?doDel&amp;type=2&amp;id='+obj.id+'&#39;)">不同意</a>'
					 // +'<a href="javascript:doUrl(&#39;nMerchantAccountController.do?goUpdate&amp;load=detail&amp;id='+obj.id+'&#39;)">详情</a>'
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
var selAll = document.getElementById("selAll");
function selectAll() {
    var obj = document.getElementsByName("checkAll[]");
    if (document.getElementById("selAll").checked == false) {
        for (var i = 0; i < obj.length; i++) {
            obj[i].checked = false;
        }
    } else {
        for (var i = 0; i < obj.length; i++) {
            obj[i].checked = true;
        }
    }

}

function setSelectAll() {
    var obj = document.getElementsByName("checkAll[]");
    var count = obj.length;
    var selectCount = 0;

    for (var i = 0; i < count; i++) {
        if (obj[i].checked == true) {
            selectCount++;
        }
    }
    if (count == selectCount) {
        document.all.selAll.checked = true;
    } else {
        document.all.selAll.checked = false;
    }
}

function hs() {
    var s = document.getElementsByName("checkAll[]");
    var s2 = "";
    for (var i = 0; i < s.length; i++) {
        if (s[i].checked) {
            s2 += s[i].value + ",";
        }
    }
    s2 = s2.substr(0, s2.length - 1);
    delDataT('确定打款吗?','nMerchantAccountController.do?doDelBatch&ids='+s2);
}
function rehs() {
    var s = document.getElementsByName("checkAll[]");
    var s2 = "";
    for (var i = 0; i < s.length; i++) {
        if (s[i].checked) {
            s2 += s[i].value + ",";
        }
    }
    s2 = s2.substr(0, s2.length - 1);
    //delDataT('nOrderController.do?send_red_packet&ids='+s2,'批量发红包吗?');
}