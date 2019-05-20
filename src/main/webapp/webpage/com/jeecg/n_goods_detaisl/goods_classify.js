$(function(){
			//省市区下拉
	//var pid='${pid}';
	//alert('${pid}');
			$.ajax({
		  		async : true,
		  		cache : false,
		  		type : 'POST',
		  		datatype:"json",
		  		url : 'nGoodsDetaislController.do?regionSelect',// 请求的action路径
		  		data : {pid:pid,type:1},//多条件排序sort:createDate,userNameorder:asc,desc
		  		error : function() {    // 请求失败处理函数
		  		},
		  		success : function(data) {
		  		var 	data=eval('(' + data + ')');
		  		
		  		var standardarray=data.content;
		  		var standhtml='<option value="" selected="selected">请选择</option>';
		  		for ( var i = 0;i<standardarray.length;i++) {
		  			var obj = standardarray[i];
		  			//alert(obj.code+"="+obj.codename);
		  			standhtml+=' <option value="'+obj.id+'" >'+obj.name+'</option>';
		  		}
				$("#goodsone").html(standhtml);
		  		}
		      });
			
});

			function  Goodsone(){
				var pid=document.getElementById("goodsone").value;
				//var selectvalue=$("#standard").find("option:selected").text(); 
			//	alert(pid);
				if(pid!=null && pid!=""){
				 $.post(
		                 	'nGoodsDetaislController.do?regionSelect',
		                 	{pid:pid},
		                 	function(result){
		                         //getList(result,type);
		                 		var standardarray=result.content;
		                 		var standhtml='';
		                 		for ( var i = 0;i<standardarray.length;i++) {
						  			var obj = standardarray[i];
						  			//alert(obj.code+"="+obj.codename);
						  			standhtml+=' <option value="'+obj.id+'" >'+obj.name+'</option>';
						  		}
								$("#goodstwo").html(standhtml);
								 Goodstwo();
		                     },'json');
				}else{
					$("#goodstwo").html("");
					$("#goodsthree").html("");
				}
			}
			function  Goodstwo(){
				var pid=document.getElementById("goodstwo").value;
				 $.post(
		                 	'nGoodsDetaislController.do?regionSelect',
		                 	{pid:pid},
		                 	function(result){
		                         //getList(result,type);
		                 		var standardarray=result.content;
		                 		var standhtml='';
		                 		for ( var i = 0;i<standardarray.length;i++) {
						  			var obj = standardarray[i];
						  			//alert(obj.code+"="+obj.codename);
						  			standhtml+=' <option value="'+obj.id+'" >'+obj.name+'</option>';
						  		}
								$("#goodsthree").html(standhtml);
		                     },'json');
			}
			function  Goodsthree(){
			//	var goodsone=document.getElementById("goodstwo").value
			}
