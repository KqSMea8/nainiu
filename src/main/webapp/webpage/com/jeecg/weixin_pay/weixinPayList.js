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
	var parms="configId=n_phone_code&field=id,createName,createBy,createDate,update_name,updateBy,update_date,sys_org_code,remarks,del_flag,bpm_status,"
			+ "title,appid,appsecret,partner,partnerkey,notifyurl,wxpaystatus,wxpaytype,";
	var appid=document.getElementById("appid").value;
	var wxpaystatus=document.getElementById("wxpaystatus").value;
	var wxpaytype=document.getElementById("wxpaytype").value;
//	var goods_detaisl_status=document.getElementById("goods_detaisl_status").value;
	
	//var t=$("#shoptype").val(); 
	//alert(shoptype_value+"="+t);
	  $.ajax({
    		async : false,
    		cache : false,
    		type : 'POST',
    		datatype:"json",
    		url : 'weixinPayController.do?datagrid&'+parms,// 请求的action路径
    		data : {page:strate_page,rows:10,sort:'createDate',
			          order:'desc', 
			          appid:appid,
			          wxpaystatus:wxpaystatus,
			          wxpaytype:wxpaytype,
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
			   +'<th>标题</th>'
		       +'<th>开发者ID</th>'
		       +'<th>开发者密码</th>'
		       +'<th>商户号</th>'
		       +'<th>微信商户平台api安全</th>'
		       +'<th>公众号或者app</th>'
		       +'<th>是否生效</th>'
		       +'<th>创建时间</th>'
		       +'<th>操作</th>';
	  $("#tabletitle").html(tabletitle);
	 var htmcontent='';
		for ( var i = 0;i<date_list.length;i++) {
			var intege=i+1;
			var obj = date_list[i];
		//	var ce=is_null(obj.ce);
			//console.log("intege13="+dictionaryarray[40] );
			var wxpaystatus=is_null(obj.wxpaystatus);
			var wxpaytype=is_null(obj.wxpaytype);
		
			var wxpaystatusname="";
			var informwxpaystatus = document.getElementById("wxpaystatus"); 
			for(var j=0;j <informwxpaystatus.options.length;j++){ 
				    var values=informwxpaystatus.options[j].value//参数值 
					var texts=informwxpaystatus.options[j].text//内容值 
						//  console.log(values+"="+texts);
						if(wxpaystatus==values){
							wxpaystatusname=texts;
						}
			}
			var wxpaytypename="";
			var informwxpaytype = document.getElementById("wxpaytype"); 
			for(var j=0;j <informwxpaytype.options.length;j++){ 
				    var values=informwxpaytype.options[j].value//参数值 
					var texts=informwxpaytype.options[j].text//内容值 
						//  console.log(values+"="+texts);
						if(wxpaytype==values){
							wxpaytypename=texts;
						}
			}
			
			
			htmcontent+='<tr >	'
					  +'<td>'+intege+'</td>'
					  +'<td>'+is_null(obj.title)+'</td>'
					  +'<td>'+is_null(obj.appid)+'</td>'
			          +'<td>'+is_null(obj.appsecret)+'</td>'
			          +'<td>'+is_null(obj.partner)+'</td>'
			          +'<td>'+is_null(obj.partnerkey)+'</td>'
			          +'<td>'+wxpaytypename+'</td>'
			          +'<td>'+wxpaystatusname+'</td>'
			          +'<td>'+is_time(obj.createDate)+'</td>'
			          +'<td class="last">'
			          +'<a href="javascript:doUrl(&#39;weixinPayController.do?goUpdate&amp;id='+obj.id+'&#39;)">编辑</a>'
					 // +'<a href="javascript:delDataT(&#39;weixinPayController.do?doDel&amp;id='+obj.id+'&#39;)">删除</a>'
					  +'<a href="javascript:doUrl(&#39;weixinPayController.do?goUpdate&amp;load=detail&amp;id='+obj.id+'&#39;)">详情</a>'
					  +'</td>'
			       +'</tr>';
          }
		
		  $("#htmcontent").html(htmcontent);
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
