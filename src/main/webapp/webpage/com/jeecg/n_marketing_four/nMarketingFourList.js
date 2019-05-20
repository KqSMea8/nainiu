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
	var parms="configId=n_marketing_one&field=id,createName,createBy,createDate,updateName,updateBy,updateDate,sys_org_code,remarks,del_flag,bpm_status,"
			+ "title,priceStatu,price,startTime,endTime,details,aduitnumb,joinnumb,";
	var title=document.getElementById("title").value;
	
	//var t=$("#shoptype").val(); 
	//alert(shoptype_value+"="+t);
	  $.ajax({
    		async : false,
    		cache : false,
    		type : 'POST',
    		datatype:"json",
    		url : 'nMarketingFourController.do?datagrid&'+parms,// 请求的action路径
    		data : {page:strate_page,rows:10,sort:'createDate',
			          order:'desc', 
			          title:title
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
var doAuditNumberarray;
function asyncdic() {
	//数字字典
	  $.ajax({
  		async : false,
  		cache : false,
  		type : 'POST',
  		datatype:"json",
  		url : 'nMarketingThreeController.do?doNumber',// 请求的action路径
  		data : {},//多条件排序sort:createDate,userNameorder:asc,desc
  		error : function() {// 请求失败处理函数
  		},
  		success : function(data) {
  			dictionaryarray=eval('(' + data + ')');
  		}
      });
		//数字字典
	  $.ajax({
  		async : false,
  		cache : false,
  		type : 'POST',
  		datatype:"json",
  		url : 'nMarketingThreeController.do?doAuditNumber',// 请求的action路径
  		data : {},//多条件排序sort:createDate,userNameorder:asc,desc
  		error : function() {// 请求失败处理函数
  		},
  		success : function(data) {
  			doAuditNumberarray=eval('(' + data + ')');
  		}
      });
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
	
}
function contentdetails(date_list){
	console.log("dd="+date_list);
	 var tabletitle='';
	 tabletitle+='<th>序号</th>'
		       +'<th>活动名称</th>'
		    //   +'<th>统一价格</th>'
		       +'<th>活动商品数量</th>'
		       +'<th>待审核商品数量</th>'
		       +'<th>活动开始时间</th>'
		       +'<th>活动结束时间</th>'
		       +'<th>活动添加时间</th>'
		      
		      // +'<th>创建时间</th>'
		       +'<th>操作</th>';
	  $("#tabletitle").html(tabletitle);
	 var htmcontent='';
	
		for ( var i = 0;i<date_list.length;i++) {
			var intege=i+1;
			var obj = date_list[i];
		//	var ce=is_null(obj.ce);
			//console.log("intege13="+dictionaryarray[40] );
			
		/*var priceStatu=is_null(obj.priceStatu);
		var price=is_null(obj.price);
		if(priceStatu=="0"){
			priceStatu="不统一价格";
		}else{
			priceStatu="￥"+price;
		}*/
			var joinnumb="0";
			var aduitnumb="0";
			 var id=obj.id;
			 var joinnumbdate=dictionaryarray.details;
				for ( var j = 0;j<joinnumbdate.length;j++) {
					var objnumbs = joinnumbdate[j];
					var oneid=objnumbs.oneid;
			        var numbers=objnumbs.numbers;
			        if(id==oneid){
			        	joinnumb=numbers;
			        }
				}
				 var aduitnumbdate=doAuditNumberarray.details;
					for ( var j = 0;j<aduitnumbdate.length;j++) {
						var objnumbs = aduitnumbdate[j];
						var oneid=objnumbs.oneid;
				        var numbers=objnumbs.numbers;
				        if(id==oneid){
				        	aduitnumb=numbers;
				        }
					}
					 if(joinnumb==undefined || joinnumb=="" || joinnumb==null){
						 joinnumb="0";
					 }
					 if(aduitnumb==undefined || aduitnumb=="" || aduitnumb==null){
						 aduitnumb="0";
					 }
			htmcontent+='<tr >	'
					  +'<td>'+intege+'</td>'
					  +'<td>'+is_null(obj.title)+'</td>'
					 // +'<td>'+priceStatu+'</td>'
			          +'<td>'+joinnumb+'</td>'
			          +'<td>'+aduitnumb+'</td>'
			          +'<td>'+is_time(obj.startTime)+'</td>'
			          +'<td>'+is_time(obj.endTime)+'</td>'
			          +'<td>'+is_time(obj.createDate)+'</td>'
			        
			          +'<td class="last">'
			          +'<a href="javascript:doUrl(&#39;nMarketingFourController.do?goUpdate&amp;id='+obj.id+'&#39;)">编辑</a>'
					 // +'<a href="javascript:delData(&#39;nMarketingFourController.do?doDel&amp;id='+obj.id+'&#39;)">删除</a>'
					  +'<a href="javascript:doUrl(&#39;nMarketingFourController.do?goUpdate&amp;load=detail&amp;id='+obj.id+'&#39;)">详情</a>'
					  +'<a href="javascript:doUrl(&#39;nMarketingFourDetailsController.do?list&amp;id='+obj.id+'&#39;)">查看活动商品</a>'
					  +'</td>'
			       +'</tr>';
          }
		
		  $("#htmcontent").html(htmcontent);
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
