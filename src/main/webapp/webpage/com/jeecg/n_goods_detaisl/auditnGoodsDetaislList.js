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
	var parms="configId=n_goods_detaisl&field=id,createName,createBy,createDate,update_name,updateBy,update_date,sys_org_code,remarks,del_flag,bpm_status,goodsDetaislStatus,merchantId,merchantname,goodsClassifyId,title,duanTitle,starttype,weight,bazaarPrice,contents,sendTime,post,returns,fine,picOne,standard,standardOne,"
			+ "standardTwo,sumNumbers,codes,groupPurchase,setBounds,xiangou,reject,orders,shangpintype,joinHuodong,"
			+ "carousel,details,goodsone,goodstwo,goodsthree,standardOnelist,standardTwolist,tuanprices,unitprices,sellnumbs,";
	var title=document.getElementById("title").value;
	var starttype=document.getElementById("starttype").value;
	var shangpintype=document.getElementById("shangpintype").value;
	var goods_detaisl_status=document.getElementById("goods_detaisl_status").value;
	
	//var t=$("#shoptype").val(); 
	//alert(shoptype_value+"="+t);
	  $.ajax({
    		async : false,
    		cache : false,
    		type : 'POST',
    		datatype:"json",
    		url : 'nGoodsDetaislController.do?datagrid&'+parms,// 请求的action路径
    		data : {page:strate_page,rows:10,sort:'createDate',
			          order:'desc', 
			          title:title,
			          starttype:starttype,
			          shangpintype:shangpintype,
			          goodsDetaislStatus:goods_detaisl_status
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
			   +'<th>商家名称</th>'
		       +'<th>商品名称</th>'
		       +'<th>团购价</th>'
		       +'<th>单购价</th>'
		       +'<th>库存</th>'
		       +'<th>销售状态</th>'
		       +'<th>商品类型</th>'
		       +'<th>审核信息</th>'
		       +'<th>已销售</th>'
		      // +'<th>创建时间</th>'
		       +'<th>操作</th>';
	  $("#tabletitle").html(tabletitle);
	 var htmcontent='';
		for ( var i = 0;i<date_list.length;i++) {
			var intege=i+1;
			var obj = date_list[i];
		//	var ce=is_null(obj.ce);
			//console.log("intege13="+dictionaryarray[40] );
			
			var starttype=is_null(obj.starttype);
			var shangpintype=is_null(obj.shangpintype);
			var goods_detaisl_status=is_null(obj.goodsDetaislStatus);
			
			if(starttype=="1"){
				starttype="助力团";
			}else{
				starttype="普通团";
			}
			if(shangpintype=="0"){
				shangpintype="已上架";
			}else if(shangpintype=="2"){
				shangpintype="已下架";
			}else{
				shangpintype="售罄";
			}
			
			if(goods_detaisl_status=="0"){
				goods_detaisl_status="发布中";
			}else if(goods_detaisl_status=="1"){
				goods_detaisl_status="已驳回";
			}else{
				goods_detaisl_status="发布成功";
			}
			htmcontent+='<tr >	'
					  +'<td>'+intege+'</td>'
					  +'<td>'+is_null(obj.merchantname)+'</td>'
					  +'<td>'+is_null(obj.title)+'</td>'
			          +'<td>'+is_null(obj.tuanprices)+'</td>'
			          +'<td>'+is_null(obj.unitprices)+'</td>'
			          +'<td>'+is_null(obj.sumNumbers)+'</td>'
			          +'<td>'+shangpintype+'</td>'
			          +'<td>'+starttype+'</td>'
			          +'<td>'+goods_detaisl_status+'</td>'
			          +'<td>'+is_null(obj.sellnumbs)+'</td>'
			   //       +'<td>'+is_time(obj.createDate)+'</td>'
			          +'<td class="last">'
			          +'<a href="javascript:doUrl(&#39;nGoodsDetaislController.do?auditeditgoUpdate&amp;id='+obj.id+'&#39;)">&nbsp;&nbsp;编辑&nbsp;&nbsp;</a>'
					  +'<a href="javascript:delDataT(&#39;nGoodsDetaislController.do?doDel&amp;id='+obj.id+'&#39;)">&nbsp;&nbsp;删除&nbsp;&nbsp;</a>'
					  +'<a href="javascript:doUrl(&#39;nGoodsDetaislController.do?auditeditgoUpdate&amp;load=detail&amp;id='+obj.id+'&#39;)">&nbsp;&nbsp;详情&nbsp;&nbsp;</a>'
					  +'<a href="javascript:doUrl(&#39;nGoodsDetaislController.do?auditgoUpdate&amp;load=detail&amp;id='+obj.id+'&#39;)">&nbsp;&nbsp;审核信息&nbsp;&nbsp;</a>'
					  +'<a href="javascript:openUserSelect(&#39;'+obj.id+'&#39;)">&nbsp;&nbsp;设置销量&nbsp;&nbsp;</a>'
					  +'</td>'
			       +'</tr>';
          }
		
		  $("#htmcontent").html(htmcontent);
}

var windowapi;
try{
	windowapi = frameElement.api, 
	W = windowapi.opener;
	}catch(e){
	}
var tid="";
function openUserSelect(id) {
	tid=id;
	    //numbid=numb_id;
	    //numbname=numb_name;
		$.dialog.setting.zIndex = getzIndex(); 
		$.dialog({content: 'url:nGoodsDetaislController.do?inputlist', zIndex: getzIndex(), title: '用户列表', 
				lock: true,parent:windowapi, width: '462px', height: '255px', opacity: 0.4,button: [
		   {name: '确定', callback: callbackDepartmentSelect, focus: true},
		   {name: '取消', callback: function (){}}
	   ]}).zindex();
	}
		
	function callbackDepartmentSelect() {
	//	alert(tid);
		  var iframe = this.iframe.contentWindow;
			var rowsData =iframe.document.getElementById("phone").value;
		  //alert(rowsData);
			if(eval(rowsData)>eval(0)){
				$.ajax({
				      url: 'nGoodsDetaislController.do?doSellnumbs',
				      cache: false,
					  dataType: "json",
					  data: {id:tid,sellnumbs:rowsData},
				      success: function(data){
						//  layeralert(data,0);
				    	  	if(data.success){
				    	  		parent.layer.alert(data.msg, {
				        	        icon: 1,
				        	        shadeClose: false,
				        	        title: '提示'
				        	    },function(index){
				        	    	//document.getElementById('formSubmit').submit();
				        	    	 serchcontent();
				        	    	parent.layer.close(index);
				        	    });
				    	  	}
		    	  	 },  
				        error: function(data, status, e){  
				        	if(data.status == "401"){
				        		layeralert("没有权限！",0);
								return;
							}
				        }
				    });
			}
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
