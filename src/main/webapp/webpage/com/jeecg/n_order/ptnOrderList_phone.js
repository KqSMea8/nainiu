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
			+ "userId,realname,marketingOne,goodsDetaislType,merchantId,merchantname,goodsId,goodsname,orderStatus,"
			+ "orderType,aftersaleStatus,aftersaleType,goodsSum,numbers,paySum,expressNub,expressName,"
			+ "details,merchant_back,isvirtual,bazaarPrice,gameState,aftersalePhone";
	var id=document.getElementById("id").value;
	var goodsname=document.getElementById("goodsname").value;
	var orderStatus=document.getElementById("orderStatus").value;
	//var aftersaleStatus=document.getElementById("aftersaleStatus").value;
	var starttime=document.getElementById("starttime").value;
	var endtime=document.getElementById("endtime").value;
	var goodsDetaislType=document.getElementById("goodsDetaislType").value;
	var gameState=document.getElementById("gameState").value;
	var redResultCode=document.getElementById("redResultCode").value;
	//var t=$("#shoptype").val(); 
	//alert(shoptype_value+"="+t);
	  $.ajax({
    		async : false,
    		cache : false,
    		type : 'POST',
    		datatype:"json",
    		url : 'nOrderController.do?datagrid&'+parms,// 请求的action路径
    		data : {page:strate_page,rows:10,sort:'gameState,createDate',
			          order:'asc,desc', 
			          id:id,
			          goodsname:goodsname,
			          orderStatus:orderStatus,
			          gameState:gameState,
			          goodsDetaislType:goodsDetaislType,
			     //     aftersaleStatus:aftersaleStatus,
			          orderType:'0',
			          starttime:starttime,
			          endtime:endtime,
			          isvirtual:'0',
			          redResultCode:redResultCode
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
			   +'<th>订单编号</th>'
		       +'<th>商品名称</th>'
		       +'<th>用户名称</th>'
		       +'<th>订单状态</th>'
		        +'<th>商品类型</th>'
		       +'<th>商品总价</th>'
		       +'<th>数量</th>'
		       +'<th>支付金额</th>'
		       +'<th>充值金额</th>'
		       +'<th>充值状态</th>'
		       +'<th>红包状态</th>'
		       +'<th>开团时间</th>'
		       +'<th>操作</th>';
	  $("#tabletitle").html(tabletitle);
	 var htmcontent='';
		for ( var i = 0;i<date_list.length;i++) {
			console.log("i="+i+"=="+date_list.length);
			var intege=i+1;
			var obj = date_list[i];
			var orderStatus=is_null(obj.orderStatus);
			var paystatus=is_null(obj.paystatus);
		//	var aftersaleStatus=is_null(obj.aftersaleStatus);
			var goodsDetaislType=is_null(obj.goodsDetaislType);
			var gameState=is_null(obj.gameState);
			var redResultCode=is_null(obj.redResultCode);
			var orderStatusname="";
			var inform = document.getElementById("orderStatus"); 
			for(var j=0;j <inform.options.length;j++){ 
				  var values=inform.options[j].value//参数值 
				  var texts=inform.options[j].text//内容值 
				//  console.log(values+"="+texts);
				  if(orderStatus==values){
					  orderStatusname=texts;
				  }
			}
			
			var goodsDetaislTypename="";
			var informgoodsDetaislType = document.getElementById("goodsDetaislType"); 
			for(var j=0;j <informgoodsDetaislType.options.length;j++){ 
				    var values=informgoodsDetaislType.options[j].value//参数值 
					var texts=informgoodsDetaislType.options[j].text//内容值 
						//  console.log(values+"="+texts);
						if(goodsDetaislType==values){
							goodsDetaislTypename=texts;
						}
			}
			var gameStatename="";
			var informgameState = document.getElementById("gameState"); 
			for(var j=0;j <informgameState.options.length;j++){ 
				var values=informgameState.options[j].value//参数值 
						var texts=informgameState.options[j].text//内容值 
						//  console.log(values+"="+texts);
						if(gameState==values){
							gameStatename=texts;
						}
			}
			var redResultCodename="";
			var informredResultCode = document.getElementById("redResultCode"); 
			for(var j=0;j <informredResultCode.options.length;j++){ 
				var values=informredResultCode.options[j].value//参数值 
						var texts=informredResultCode.options[j].text//内容值 
						//  console.log(values+"="+texts);
						if(redResultCode==values){
							redResultCodename=texts;
						}
			}
		/*	var aftersaleStatusname="";
			var two = document.getElementById("aftersaleStatus"); 
			for(var k=0;k <two.options.length;k++){ 
				var values=two.options[k].value//参数值 
				var texts=two.options[k].text//内容值 
				//		console.log(values+"="+texts);
				if(aftersaleStatus==values){
					aftersaleStatusname=texts;
				}
			}*/
			
			htmcontent+='<tr >	'
					+'<td height="20" bgcolor="#FFFFFF"><div align="center"><input type="checkbox" name="checkAll[]" id="checkAll" onclick="setSelectAll();" value='+is_null(obj.id)+'></td>'
					  +'<td>'+intege+'</td>'
					  +'<td>'+is_null(obj.id)+'</td>'
					  +'<td>'+is_null(obj.goodsname)+'</td>'
					  +'<td>'+is_null(obj.realname)+'</td>'
			          +'<td>'+orderStatusname+'</td>'
			          +'<td>'+goodsDetaislTypename+'</td>'
			          +'<td>'+is_null(obj.goodsSum)+'</td>'
			          +'<td>'+is_null(obj.numbers)+'</td>'
			          +'<td>'+is_null(obj.paySum)+'</td>'
			          +'<td>'+is_null(obj.bazaarPrice)+'</td>'
			          +'<td>'+gameStatename+'</td>'
			             +'<td>'+redResultCodename+'</td>'
			          +'<td>'+is_time(obj.createDate)+'</td>'
			          +'<td class="last">'
			        //  +'<a href="javascript:doUrl(&#39;nOrderController.do?goUpdate&amp;id='+obj.id+'&#39;)">编辑</a>'
					 // +'<a href="javascript:delDataT(&#39;nOrderController.do?doDel&amp;id='+obj.id+'&#39;)">删除</a>'
					//  +'<a href="javascript:doUrl(&#39;nOrderController.do?goUpdate&amp;load=detail&amp;id='+obj.id+'&#39;)">详情</a>'
			            +'<a href="javascript:doUrl(&#39;nOrderController.do?goptDetails&amp;load=detail&amp;id='+obj.id+'&#39;)">详情</a>'
			            +'<a href="javascript:delDataT(&#39;nOrderController.do?phone_Recharge_orderSta&amp;id='+obj.id+'&#39;,&#39;确认更新充值?&#39;)">更新充值</a>'
			            +'<a href="javascript:doUrl(&#39;nPhoneRechargeController.do?ptlist&amp;id='+obj.id+'&#39;)">充值详情</a>'
	                  +'</td>'
			       +'</tr>';
          }
		
		  $("#htmcontent").html(htmcontent);
}

   var flag=true;
function delDataT(url,title){
	parent.layer.confirm(title, {
	    btn: ['确定','取消'] //按钮
//	    shade: [0.3, '#393D49'] //不显示遮罩
	}, function(){
		if(flag){
			flag=false;
		$.ajax({
		      url: url,
		      cache: false,
			  dataType: "json",
		      success: function(data){
			      flag=true;
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
		    	  flag=true;
		        	if(data.status == "401"){
		        		layeralert("没有权限！",0);
						return;
					}
		        }
		    });
		}
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
	delDataT('nOrderController.do?phone_Recharge&ids='+s2,'确认充值?'); 
}

