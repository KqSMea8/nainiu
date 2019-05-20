        var detailsMax_Size = 30; //2M
        var detailsMax_Width = 960; //100px
        var detailsMax_Height = 1500; //200px
        var detailstishi='';
        var detailstishiboolean = true;
        var detailstishiwhboolean = true;
function detailsChange(MaxSize,MaxWidth,MaxHeight,index,id,picurlid,picurlimg,skuimgclose){
	  detailsMax_Size=MaxSize;
//      detailsMax_Width=MaxWidth;
//      detailsMax_Height=MaxHeight;
	 var fileEl=document.getElementById(id);//显示图片的对象 
	 detailstestGeshi(fileEl);
	 detailstestMaxSize(fileEl);
	 detailstestWidthHeight(fileEl,index,id,picurlid,picurlimg,skuimgclose);

     // detailsup_pic(index,id,picurlid,picurlimg,skuimgclose);
  
}
        //验证格式
         function detailstestGeshi(fileEl){
         	 var txtImg_url=fileEl.value.toLowerCase();
	         if(txtImg_url.indexOf("jpg")!=-1 || txtImg_url.indexOf("png")!=-1){  
	         	//alert("格式错误");
	        	 detailstishiboolean=true;
	         }else{
	         detailstishiboolean=false;
	         	detailstishi+="格式错误,";
	         }
        }
function detailstestMaxSize(file){

        
            if(file.files && file.files[0]){
                var fileData = file.files[0];
                var size = fileData.size;   //注意，这里读到的是字节数
                var isAllow = false;
                if(!size) isAllow = false;
                 console.log("字节数="+size);
                var maxSize = detailsMax_Size;
                maxSize = maxSize * 1024*1024;   //转化为字节
               // isAllow = size <= maxSize;
               isAllow = parseInt(size) <= parseInt(maxSize);
                 console.log("isAllow="+isAllow+"==size"+size+"==maxSize=="+maxSize);
                 detailsshowTip1(isAllow);
            }else{
                /*... ie9下用iframe上传*/
                /*
                // 或者用以面的方式实现
                // var img = new Image();
                // 再用img.src=filepath,再用img.fileSize用取，这里不写啦，读者自行实践一下
                */

            }

        }
  function detailstestWidthHeight(file,index,id,picurlid,picurlimg,skuimgclose){
            var isAllow = false;
           // debugger;

            if(file.files && file.files[0]){
                var fileData = file.files[0];

                //读取图片数据
                var reader = new FileReader();
                reader.onload = function (e) {
                    var data = e.target.result;
                    //加载图片获取图片真实宽度和高度
                    var image = new Image();
                    image.onload=function(){
                        var width = image.width;
                        var height = image.height;
                      //   console.log("width="+width+"+width="+height);
                     //    console.log("width="+detailsMax_Width+"+width="+detailsMax_Height);
                      //   var t=parseInt(width)<parseInt(detailsMax_Width);
                       //  console.log("a="+t);
                          //  console.log(parseInt(width)<parseInt(detailsMax_Width));
                      //  isAllow = width >= detailsMax_Width && height >= detailsMax_Height;
                       //parseInt(num2)
                        //isAllow = parseInt(width) == parseInt(detailsMax_Width) && parseInt(height) <= parseInt(detailsMax_Height);
                        isAllow = parseInt(width) >= parseInt(480) && parseInt(width) <= parseInt(1200)
                        		&& parseInt(height) >= parseInt(1)&& parseInt(height) <= parseInt(1500);
                        detailsshowTip2(isAllow,index,id,picurlid,picurlimg,skuimgclose);
                    };
                    image.src= data;
                };
                reader.readAsDataURL(fileData);

            }else{
                //IE下使用滤镜来处理图片尺寸控制
                //文件name中IE下是完整的图片本地路径
              //  var input = D.get('#uploader');
              var input=document.getElementById("uploader");//显示图片的对象 
                //var input = uploader.get('target').all('input').getDOMNode();
                input.select();
                //确保IE9下，不会出现因为安全问题导致无法访问
                input.blur();
                var src = document.selection.createRange().text;
                var img = $('<img style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);width:300px;visibility:hidden;"  />').appendTo('body').getDOMNode();
                img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
                var width = img.offsetWidth;
                var height = img.offsetHeight;
                $(img).remove();
             //   isAllow = width >= detailsMax_Width && height >= detailsMax_Height;
               //parseInt(num2)
                  isAllow = parseInt(width) == parseInt(detailsMax_Width) && parseInt(height) <= parseInt(detailsMax_Height);
                  detailsshowTip2(isAllow,index,id,picurlid,picurlimg,skuimgclose);
            }

        }

        function detailsshowTip1(isAllow){
           
                    html = '';
            if(isAllow){
               // html = '大小通过</br>';
            	 detailstishiboolean=true;
            }else{
                html = '大小未通过，要求'+ detailsMax_Size +'M.</br>';
                detailstishiboolean=false;
            }
            detailstishi+=html;
           
          //  $("#t1").html(html);
        }

        function detailsshowTip2(isAllow,index,id,picurlid,picurlimg,skuimgclose){
          

            if(isAllow){
               // html += '宽高通过';
            	detailstishiwhboolean=true;
            }else{
            	
       //         html += '宽高未通过，要求width:'+ detailsMax_Width +', height:'+ detailsMax_Height;
                html += '宽高未通过，要求width:480~1200, height:1~1500';
                detailstishiwhboolean=false;
            }
             detailstishi+=html;
             detailsup_pic(index,id,picurlid,picurlimg,skuimgclose);
          //  $("#t2").html(html);
        }
    function detailsup_pic(index,id,picurlid,picurlimg,skuimgclose){
			if(detailstishiboolean && detailstishiwhboolean){
				detailsuploadpic(index,id,picurlid,picurlimg,skuimgclose);
			}else{
				alert(detailstishi);
				
			}
			detailstishi="";
		}
    function detailsuploadpic(index,id,picurlid,picurlimg,skuimgclose){
         var formData = new FormData();
         formData.append("file",$("#"+id)[0].files[0]);

                 $.ajax({
                  url: 'uplodfil.do?uplodfil/img',
                  type: 'post',
                  data: formData,
                  async: true,
                  cache: false,
                  contentType: false,
                  processData: false,
                  success: function (data) {
                     /* $("#sqlfile").val("");
                      $("#warfile").val("");
                      $("#icofile").val("");*/
                   //   alert("文件上传成功！");
                   var json = eval('(' + data + ')');
                   var url_path=json.url_path;
                  // console.log(yulanurl);
                   var aryya=  document.getElementsByName(picurlid);
	             	  index--;
	             	  aryya[index].value=url_path;
                  // document.getElementById(picurlid).value=url_path;
                   document.getElementById(picurlimg).src=url_path;
                   document.getElementById(skuimgclose).style.display="inline-block";//显示
                   detailsappde();
                  },
                  error: function (data) {
                      alert("文件上传出错！");
                  }
             });
		
 }
    var  detailsnumb=1;
    var  detailssum=1;
    var detailstype=false;
    function detailsdel_pic(detailsdiv,pic_url,index){
    	
    	//document.getElementById(yulanurlwu).style.display="inline-block";//显示
    	/*document.getElementById(skuimgclose).style.display="none";//隐藏
    	  var aryya=  document.getElementsByName(pic_url);
    	  index--;
    	  aryya[index].value="";
		 //  document.getElementById(pic_url).value="";
           document.getElementById(pic_urlimg).src="plug-in/ui/images/shangchuan.png";*/
    	//document.getElementById(detailsdiv).parentNode.removeChild(my);
        /*var my = document.getElementById(detailsdiv);
        if (my != null)
            my.parentNode.removeChild(my);*/
    	document.getElementById(detailsdiv).style.display="none";//隐藏
    	 var aryya=  document.getElementsByName(pic_url);
	   	  index--;
	   	aryya[index].disabled=true;
	   	  console.log(detailssum);
	  	if(detailssum==20){
	  		if(detailstype){
	  			detailssum--;
	  			detailstype=false;
	  		}else{
	  			detailstype=true;
		  		detailsnumb++;
		  		var htl="";
			    htl+='<div id="detailsdiv'+detailsnumb+'" style=" width: 109px;height: 109px;float: left;display: inline-block;margin-right: 2%;margin-bottom: 2%;">'
						 +'<div class="hidden">'
					      +'<input id="details" name="details" type="hidden" value="">'
					      +'<input id="fileimgdetails'+detailsnumb+'" type="file" onchange="Javascript:detailsChange(\'30\',\'30000\',\'30000\','+detailsnumb+',\'fileimgdetails'+detailsnumb+'\',\'details\',\'imgdetails'+detailsnumb+'\',\'closedetails'+detailsnumb+'\');" style="display: none;">'
					     +'</div>'
						 +'<div class="goods-sku-s-b-r-c-img" style="display: inline-block;">'
							+'<a href="javascript:filebtn(\'fileimgdetails'+detailsnumb+'\');" style="cursor: pointer; display: inline-block; width: 109px;height: 109px;">'
						    	+'<img id="imgdetails'+detailsnumb+'" src="plug-in/ui/images/shangchuan.png" style="width: 109px;height: 109px; overflow: hidden;" >'
							+'</a>'
						    +'<span  id="closedetails'+detailsnumb+'" onclick="detailsdel_pic(\'detailsdiv'+detailsnumb+'\',\'details\','+detailsnumb+');" class="goods-sku-s-b-r-c-img-close" style="right:-74px;display:none;" />'
						    +'</div>'
				 +'</div>'; 
			    $("#detailslist").append(htl);
	  		}
	  	}else{
	  		detailssum--;
	  		detailstype=false;
	  	}
	  	
	  }
    function uploadFile(){
    	 //document.getElementById("fileimg").src="http://localhost:8180/jeecg/plug-in/ueditor/jsp/upload/image/20171109/61655cbf-da56-4a7c-8ff1-1e5dfea92a72.jpg";
    }
   
    function detailsappde(){
    	
    	if(detailssum<20){
    		detailsnumb++;
        	detailssum++;
	    	var htl="";
		    htl+='<div id="detailsdiv'+detailsnumb+'" style=" width: 109px;height: 109px;float: left;display: inline-block;margin-right: 2%;margin-bottom: 2%;">'
					 +'<div class="hidden">'
				      +'<input id="details" name="details" type="hidden" value="">'
				      +'<input id="fileimgdetails'+detailsnumb+'" type="file" onchange="Javascript:detailsChange(\'30\',\'30000\',\'30000\','+detailsnumb+',\'fileimgdetails'+detailsnumb+'\',\'details\',\'imgdetails'+detailsnumb+'\',\'closedetails'+detailsnumb+'\');" style="display: none;">'
				     +'</div>'
					 +'<div class="goods-sku-s-b-r-c-img" style="display: inline-block;">'
						+'<a href="javascript:filebtn(\'fileimgdetails'+detailsnumb+'\');" style="cursor: pointer; display: inline-block; width: 109px;height: 109px;">'
					    	+'<img id="imgdetails'+detailsnumb+'" src="plug-in/ui/images/shangchuan.png" style="width: 109px;height: 109px; overflow: hidden;" >'
						+'</a>'
					    +'<span  id="closedetails'+detailsnumb+'" onclick="detailsdel_pic(\'detailsdiv'+detailsnumb+'\',\'details\','+detailsnumb+');" class="goods-sku-s-b-r-c-img-close" style="right:-74px;display:none;" />'
					    +'</div>'
			 +'</div>'; 
		    $("#detailslist").append(htl);
    	}else{
    		detailstype=false;
    	}
    }
    
	$(function(){
		if(details.length>0){
			details=details.substring(0,details.length-1);
			var details_list=details.split(",");
			detailssum=details_list.length;
			var htl="";
			for (i=0;i<details_list.length;i++){
			//	alert(i);
				detailsnumb=i+1;
				 htl+='<div id="detailsdiv'+detailsnumb+'" style=" width: 109px;height: 109px;float: left;display: inline-block;margin-right: 2%;margin-bottom: 2%;">'
						 +'<div class="hidden">'
					      +'<input id="details" name="details" type="hidden" value="'+details_list[i]+'">'
					      +'<input id="fileimgdetails'+detailsnumb+'" type="file" onchange="Javascript:detailsChange(\'30\',\'30000\',\'30000\','+detailsnumb+',\'fileimgdetails'+detailsnumb+'\',\'details\',\'imgdetails'+detailsnumb+'\',\'closedetails'+detailsnumb+'\');" style="display: none;">'
					     +'</div>'
						 +'<div class="goods-sku-s-b-r-c-img" style="display: inline-block;">'
							+'<a href="javascript:filebtn(\'fileimgdetails'+detailsnumb+'\');" style="cursor: pointer; display: inline-block; width: 109px;height: 109px;">'
						    	+'<img id="imgdetails'+detailsnumb+'" src="'+details_list[i]+'" style="width: 109px;height: 109px; overflow: hidden;" >'
							+'</a>'
						    +'<span  id="closedetails'+detailsnumb+'" onclick="detailsdel_pic(\'detailsdiv'+detailsnumb+'\',\'details\','+detailsnumb+');" class="goods-sku-s-b-r-c-img-close" style="right:-74px;" />'
						    +'</div>'
				 +'</div>'; 
			  
			   // $("#detailslist").append(htl);
			  
			}
			  if(detailssum<20){
			    	detailsnumb+=1;
			    	 htl+='<div id="detailsdiv'+detailsnumb+'" style=" width: 109px;height: 109px;float: left;display: inline-block;margin-right: 2%;margin-bottom: 2%;">'
							 +'<div class="hidden">'
						      +'<input id="details" name="details" type="hidden" value="">'
						      +'<input id="fileimgdetails'+detailsnumb+'" type="file" onchange="Javascript:detailsChange(\'30\',\'30000\',\'30000\','+detailsnumb+',\'fileimgdetails'+detailsnumb+'\',\'details\',\'imgdetails'+detailsnumb+'\',\'closedetails'+detailsnumb+'\');" style="display: none;">'
						     +'</div>'
							 +'<div class="goods-sku-s-b-r-c-img" style="display: inline-block;">'
								+'<a href="javascript:filebtn(\'fileimgdetails'+detailsnumb+'\');" style="cursor: pointer; display: inline-block; width: 109px;height: 109px;">'
							    	+'<img id="imgdetails'+detailsnumb+'" src="plug-in/ui/images/shangchuan.png" style="width: 109px;height: 109px; overflow: hidden;" >'
								+'</a>'
							    +'<span  id="closedetails'+detailsnumb+'" onclick="detailsdel_pic(\'detailsdiv'+detailsnumb+'\',\'details\','+detailsnumb+');" class="goods-sku-s-b-r-c-img-close" style="right:-74px;display:none;" />'
							    +'</div>'
					 +'</div>'; 
			    }
			  $("#detailslist").html(htl);
		}
		
	}); 