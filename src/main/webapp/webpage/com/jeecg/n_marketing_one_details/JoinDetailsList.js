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
			+ "merchantid,merchantname,goodsid,goodsname,audittype,shoptype,tuanprices,unitprices,";
	//var merchantname=document.getElementById("merchantname").value;
	var goodsname=document.getElementById("goodsname").value;
	
	//var t=$("#shoptype").val(); 
	//alert(shoptype_value+"="+t);
	  $.ajax({
    		async : false,
    		cache : false,
    		type : 'POST',
    		datatype:"json",
    		url : 'nMarketingOneDetailsController.do?datagrid&'+parms,// 请求的action路径
    		data : {page:strate_page,rows:10,sort:'createDate',
			          order:'desc', 
			          merchantid:merchantid,
			          oneid:oneid,
			          goodsname:goodsname
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
		     //  +'<th>所属店铺</th>'
		       +'<th>商品名称</th>'
		       +'<th>审核信息</th>'
		       +'<th>当前状态</th>'
		       +'<th>团购价</th>'
		       +'<th>单购价</th>'
		       +'<th>申请时间</th>'
		      
		      
		      // +'<th>创建时间</th>'
		       +'<th>操作</th>';
	  $("#tabletitle").html(tabletitle);
	 var htmcontent='';
		for ( var i = 0;i<date_list.length;i++) {
			var intege=i+1;
			var obj = date_list[i];
		//	var ce=is_null(obj.ce);
			//console.log("intege13="+dictionaryarray[40] );
			
		var audittype=is_null(obj.audittype);
		var shoptype=is_null(obj.shoptype);
		if(audittype=="0"){
			audittype="审核中";
		}else if(audittype=="1"){
			audittype="审核通过";
		}else{
			audittype="未通过";
		}
		if(shoptype=="0"){
			shoptype="参加活动";
		}else{
			shoptype="退出活动";
		}
				
			htmcontent+='<tr >	'
					  +'<td>'+intege+'</td>'
					 // +'<td>'+is_null(obj.merchantname)+'</td>'
					   +'<td>'+is_null(obj.goodsname)+'</td>'
			          +'<td>'+audittype+'</td>'
			          +'<td>'+shoptype+'</td>'
			          +'<td>'+is_null(obj.tuanprices)+'</td>'
			          +'<td>'+is_null(obj.unitprices)+'</td>'
			          +'<td>'+is_time(obj.createDate)+'</td>'
			          +'<td class="last">'
			          +'<a href="javascript:exit(&#39;'+obj.id+'&#39;)">退出活动</a>'
					 // +'<a href="javascript:delData(&#39;nMarketingOneController.do?doDel&amp;id='+obj.id+'&#39;)">删除</a>'
					  +'<a href="javascript:doUrl(&#39;nGoodsDetaislController.do?ptonegoUpdate&amp;load=detail&amp;id='+obj.goodsid+'&#39;)">详情</a>'
					//  +'<a href="javascript:doUrl(&#39;nMarketingOneDetailsController.do?goUpdate&amp;load=detail&amp;id='+obj.id+'&#39;)">查看活动商品</a>'
					  +'</td>'
			       +'</tr>';
          }
		
		  $("#htmcontent").html(htmcontent);
}
function exit(id){
$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		url : 'nMarketingOneDetailsController.do?exit',// 请求的action路径
		data : {id:id},//多条件排序sort:createDate,userNameorder:asc,desc
		error : function() {// 请求失败处理函数
		},
		success : function(data) {
			//var total=data.total;
			var m=eval('(' + data + ')').msg;
  			parent.layer.alert(m, {
    	        icon: 1,
    	        shadeClose: false,
    	        title: '提示'
    	    },function(index){
    	    	 serchcontent();
    	    	parent.layer.close(index);
    	    });
		}
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
