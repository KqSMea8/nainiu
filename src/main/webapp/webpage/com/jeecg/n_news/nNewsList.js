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
			+ "title,description,details,picurl,numbs,nnewtype,";
	var title=document.getElementById("title").value;
	
	var starttime=document.getElementById("starttime").value;
	var endtime=document.getElementById("endtime").value;
	var nnewtype=document.getElementById("nnewtype").value;
	
	  $.ajax({
    		async : false,
    		cache : false,
    		type : 'POST',
    		datatype:"json",
    		url : 'nNewsController.do?datagrid&'+parms,// 请求的action路径
    		data : {page:strate_page,rows:10,sort:'createDate',
			          order:'desc', 
			          starttime:starttime,
			          endtime:endtime,
			          title:title,
			          nnewtype:nnewtype
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
			   +'<th>标题</th>'
			   +'<th>是否发送</th>'
			   +'<th>阅读人数</th>'
		       +'<th>创建时间</th>'
		       +'<th>操作</th>';
	  $("#tabletitle").html(tabletitle);
	 var htmcontent='';
		for ( var i = 0;i<date_list.length;i++) {
			var intege=i+1;
			var obj = date_list[i];
			var nnewtype=is_null(obj.nnewtype);
			var nnewtypename="";
			var inform = document.getElementById("nnewtype"); 
			for(var j=0;j <inform.options.length;j++){ 
				  var values=inform.options[j].value//参数值 
				  var texts=inform.options[j].text//内容值 
				//  console.log(values+"="+texts);
				  if(nnewtype==values){
					  nnewtypename=texts;
				  }
			}
			htmcontent+='<tr >	'
					+'<td height="20" bgcolor="#FFFFFF"><div align="center"><input type="checkbox" name="checkAll[]" id="checkAll" onclick="setSelectAll();" value='+is_null(obj.id)+'></td>'
					  +'<td>'+intege+'</td>'
					  +'<td>'+is_null(obj.title)+'</td>'
					  +'<td>'+nnewtypename+'</td>'
					  +'<td>'+is_null(obj.numbs)+'</td>'
			          +'<td>'+is_time(obj.createDate)+'</td>'
			          +'<td class="last">'
			          +'<a href="javascript:doUrl(&#39;nNewsController.do?goUpdate&amp;id='+obj.id+'&#39;)">编辑</a>'
			            +'<a href="javascript:doUrl(&#39;nNewsController.do?goptDetails&amp;load=detail&amp;id='+obj.id+'&#39;)">详情</a>'
			       +'</tr>';
          }
		
		  $("#htmcontent").html(htmcontent);
}
function doSearch(url,com,num){
	if(com!="" && num!=""){
		$.ajax({
			url: url,
		    cache: false,
			dataType: "json",
			data:{com:com,num:num},
			success:function(data){
				if(data.message=="ok"){
					var msg = "<tr style='height:25px;line-height:25px;margin-bottom:10px;'><th style='width:200px;margin-right:50px;'>更新时间</th><th style='width:500px;margin-right:20px;'>物流详情</th></tr>";
					for(var i=0;i<data.data.length;i++){
						msg+="<tr style='height:25px;line-height:25px;margin-bottom:10px;'><td style='width:200px;margin-right:50px;'>"+data.data[i].time+"</td><td style='width:500px;margin-right:20px;'>"+data.data[i].context+"</td></tr>";
					}
					$.dialog({
						content:"<table>"+msg+"</table>",
						width:800,
						height:600,
						title:"物流信息",
						lock: true,
						button: [{name: '确定', callback: function (){}}]
					}).zindex();
				}else{
					layeralert("没有查到快递信息！",1);
				}
			},
			error:function(){
				layeralert("系统异常！",0);
				return;
			}
		});
	}else{
		layeralert("请先添加快递信息！",1);
		return;
	}
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
	delDataT('nNewsController.do?pt_exit_money&ids='+s2,'批量发送吗?'); 
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
	delDataT('nNewsController.do?send_red_packet&ids='+s2,'批量发送吗?'); 
}