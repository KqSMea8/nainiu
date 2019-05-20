//var strate_page="1";
$(function(){
	asyncdic();
	 var strate_page = jQuery('#pageNo').val();
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
	var parms="field=id,createName,createBy,createDate,updateName,updateBy,updateDate,sysOrgCode,sysCompanyCode,bpmStatus,ce,joinTime,timeT,userId,content,";
	var ce=document.getElementById("ce"),selectedValue;
	  $.ajax({
    		async : false,
    		cache : false,
    		type : 'POST',
    		datatype:"json",
    		url : 'ceController.do?datagrid&'+parms,// 请求的action路径
    		data : {page:strate_page,rows:10,sort:'createDate',order:'desc',
    			ce:ce},//多条件排序sort:createDate,userNameorder:asc,desc
    		error : function() {// 请求失败处理函数
    		},
    		success : function(data) {
    			var total=data.total;
    			var rows=data.rows;
    		    var nowpage=jQuery('#pageNo').val();
    		    var sumpage=Math.ceil(total/10);
    		    contentdetails(rows);
    			 page(total,nowpage,sumpage);
    			 
    		}
        });
}
var dictionaryarray;
function asyncdic() {
	//数字字典
	  $.ajax({
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
	console.log("dd="+date_list);
	 var tabletitle='';
	 tabletitle+='<th>序号</th>'
		       +'<th>ce</th>'
		       +'<th>join_time</th>'
		       +'<th>time_t</th>'
		       +'<th>user_id</th>'
		       +'<th>操作</th>';
	  $("#tabletitle").html(tabletitle);
	 var htmcontent='';
		for ( var i = 0;i<date_list.length;i++) {
			var intege=i+1;
			var obj = date_list[i];
			var ce=is_null(obj.ce);
			//console.log("intege13="+dictionaryarray[40] );
			htmcontent+='<tr >	'
					  +'<td>'+intege+'</td>'
					  +'<td>'+dictionaryarray[ce]+'</td>'
			          +'<td>'+is_null(obj.joinTime)+'</td>'
			          +'<td>'+is_time(obj.timeT)+'</td>'
			          +'<td>'+is_null(obj.userId)+'</td>'
			          +'<td class="last">'
			          +'<a href="javascript:doUrl(&#39;ceController.do?goedit&amp;id='+obj.id+'&#39;)">编辑</a>'
					  +'<a href="javascript:delData(&#39;ceController.do?doDelete&amp;id='+obj.id+'&#39;)">删除</a>'
					  +'<a href="javascript:doUrl(&#39;ceController.do?toDetail&amp;id='+obj.id+'&#39;)">详情</a>'
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
