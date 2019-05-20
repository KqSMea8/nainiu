        var Max_Size = 30; //2M
        var Max_Width = 30000; //100px
        var Max_Height = 30000; //200px
function carouselChange(MaxSize,MaxWidth,MaxHeight,index,id,picurlid,picurlimg,skuimgclose){
	  Max_Size=MaxSize;
      Max_Width=MaxWidth;
      Max_Height=MaxHeight;
	 var fileEl=document.getElementById(id);//显示图片的对象 
	 carouseltestMaxSize(fileEl);
	 carouseltestWidthHeight(fileEl);
	 carouseltestGeshi(fileEl);
      carouselup_pic(index,id,picurlid,picurlimg,skuimgclose);
  
}
      var tishi='';
       var tishiboolean = true;
        //验证格式
         function carouseltestGeshi(fileEl){
         	 var txtImg_url=fileEl.value.toLowerCase();
         //	 var exp = /.\.jpg|.\.gif|.\.png|.\.bmp/i; 
        // var exp = /.\.jpg|.\.png|/i; 
         //alert(txtImg_url.test("jpg"));
         if(txtImg_url.indexOf("jpg")!=-1 || txtImg_url.indexOf("png")!=-1){  
         	//alert("格式错误");
        	 tishiboolean=true;
         }else
         tishiboolean=false;
         	tishi+="格式错误,";
        }
function carouseltestMaxSize(file){

        
            if(file.files && file.files[0]){
                var fileData = file.files[0];
                var size = fileData.size;   //注意，这里读到的是字节数
                var isAllow = false;
                if(!size) isAllow = false;
                 console.log("字节数="+size);
                var maxSize = Max_Size;
                maxSize = maxSize * 1024*1024;   //转化为字节
               // isAllow = size <= maxSize;
               isAllow = parseInt(size) <= parseInt(maxSize);
                 console.log("isAllow="+isAllow+"==size"+size+"==maxSize=="+maxSize);
                showTip1(isAllow);
            }else{
                /*... ie9下用iframe上传*/
                /*
                // 或者用以面的方式实现
                // var img = new Image();
                // 再用img.src=filepath,再用img.fileSize用取，这里不写啦，读者自行实践一下
                */

            }

        }
  function carouseltestWidthHeight(file){
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
                         console.log("width="+width+"+width="+height);
                         console.log("width="+Max_Width+"+width="+Max_Height);
                         var t=parseInt(width)<parseInt(Max_Width);
                         console.log("a="+t);
                            console.log(parseInt(width)<parseInt(Max_Width));
                      //  isAllow = width >= Max_Width && height >= Max_Height;
                       //parseInt(num2)
                        isAllow = parseInt(width) <= parseInt(Max_Width) && parseInt(height) <= parseInt(Max_Height);
                        showTip2(isAllow);
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
   console.log("1==width="+width+"+width="+height);
             //   isAllow = width >= Max_Width && height >= Max_Height;
               //parseInt(num2)
                  isAllow = parseInt(width) <= parseInt(Max_Width) && parseInt(height) <= parseInt(Max_Height);
                showTip2(isAllow);
            }

        }

        function showTip1(isAllow){
           
                    html = '';
            if(isAllow){
               // html = '大小通过</br>';
            	 tishiboolean=true;
            }else{
                html = '大小未通过，要求'+ Max_Size +'M.</br>';
                tishiboolean=false;
            }
            tishi+=html;
           
          //  $("#t1").html(html);
        }

        function showTip2(isAllow){
          

            if(isAllow){
               // html += '宽高通过';
            	 tishiboolean=true;
            }else{
            	
                html += '宽高未通过，要求width:'+ Max_Width +', height:'+ Max_Height;
                tishiboolean=false;
            }
             tishi+=html;
          //  $("#t2").html(html);
        }
    function carouselup_pic(index,id,picurlid,picurlimg,skuimgclose){
			if(tishiboolean){
				carouseluploadpic(index,id,picurlid,picurlimg,skuimgclose);
			}else{
				alert(tishi);
				
			}
			tishi="";
		}
    function carouseluploadpic(index,id,picurlid,picurlimg,skuimgclose){
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
                   carouselappde();
                  },
                  error: function (data) {
                      alert("文件上传出错！");
                  }
             });
		
 }
    var  carouselnumb=1;
    var  carouselsum=1;
    var carouseltype=false;
    function carouseldel_pic(carouseldiv,pic_url,index){
    	
    	//document.getElementById(yulanurlwu).style.display="inline-block";//显示
    	/*document.getElementById(skuimgclose).style.display="none";//隐藏
    	  var aryya=  document.getElementsByName(pic_url);
    	  index--;
    	  aryya[index].value="";
		 //  document.getElementById(pic_url).value="";
           document.getElementById(pic_urlimg).src="plug-in/ui/images/shangchuan.png";*/
    	//document.getElementById(carouseldiv).parentNode.removeChild(my);
        /*var my = document.getElementById(carouseldiv);
        if (my != null)
            my.parentNode.removeChild(my);*/
    	document.getElementById(carouseldiv).style.display="none";//隐藏
    	 var aryya=  document.getElementsByName(pic_url);
	   	  index--;
	   	aryya[index].disabled=true;
	   	  console.log(carouselsum);
	  	if(carouselsum==10){
	  		if(carouseltype){
	  			carouselsum--;
	  			carouseltype=false;
	  		}else{
	  			carouseltype=true;
		  		carouselnumb++;
		  		var htl="";
			    htl+='<div id="carouseldiv'+carouselnumb+'" style=" width: 109px;height: 109px;float: left;display: inline-block;margin-right: 2%;margin-bottom: 2%;">'
						 +'<div class="hidden">'
					      +'<input id="carousel" name="carousel" type="hidden" value="">'
					      +'<input id="fileimgcarousel'+carouselnumb+'" type="file" onchange="Javascript:carouselChange(\'30\',\'30000\',\'30000\','+carouselnumb+',\'fileimgcarousel'+carouselnumb+'\',\'carousel\',\'imgcarousel'+carouselnumb+'\',\'closecarousel'+carouselnumb+'\');" style="display: none;">'
					     +'</div>'
						 +'<div class="goods-sku-s-b-r-c-img" style="display: inline-block;">'
							+'<a href="javascript:filebtn(\'fileimgcarousel'+carouselnumb+'\');" style="cursor: pointer; display: inline-block; width: 109px;height: 109px;">'
						    	+'<img id="imgcarousel'+carouselnumb+'" src="plug-in/ui/images/shangchuan.png" style="width: 109px;height: 109px; overflow: hidden;" >'
							+'</a>'
						    +'<span  id="closecarousel'+carouselnumb+'" onclick="carouseldel_pic(\'carouseldiv'+carouselnumb+'\',\'carousel\','+carouselnumb+');" class="goods-sku-s-b-r-c-img-close" style="right:-74px;display:none;" />'
						    +'</div>'
				 +'</div>'; 
			    $("#carouselist").append(htl);
	  		}
	  	}else{
	  		carouselsum--;
	  		carouseltype=false;
	  	}
	  	
	  }
    function uploadFile(){
    	 //document.getElementById("fileimg").src="http://localhost:8180/jeecg/plug-in/ueditor/jsp/upload/image/20171109/61655cbf-da56-4a7c-8ff1-1e5dfea92a72.jpg";
    }
   
    function carouselappde(){
    	
    	if(carouselsum<10){
    		carouselnumb++;
        	carouselsum++;
	    	var htl="";
		    htl+='<div id="carouseldiv'+carouselnumb+'" style=" width: 109px;height: 109px;float: left;display: inline-block;margin-right: 2%;margin-bottom: 2%;">'
					 +'<div class="hidden">'
				      +'<input id="carousel" name="carousel" type="hidden" value="">'
				      +'<input id="fileimgcarousel'+carouselnumb+'" type="file" onchange="Javascript:carouselChange(\'30\',\'30000\',\'30000\','+carouselnumb+',\'fileimgcarousel'+carouselnumb+'\',\'carousel\',\'imgcarousel'+carouselnumb+'\',\'closecarousel'+carouselnumb+'\');" style="display: none;">'
				     +'</div>'
					 +'<div class="goods-sku-s-b-r-c-img" style="display: inline-block;">'
						+'<a href="javascript:filebtn(\'fileimgcarousel'+carouselnumb+'\');" style="cursor: pointer; display: inline-block; width: 109px;height: 109px;">'
					    	+'<img id="imgcarousel'+carouselnumb+'" src="plug-in/ui/images/shangchuan.png" style="width: 109px;height: 109px; overflow: hidden;" >'
						+'</a>'
					    +'<span  id="closecarousel'+carouselnumb+'" onclick="carouseldel_pic(\'carouseldiv'+carouselnumb+'\',\'carousel\','+carouselnumb+');" class="goods-sku-s-b-r-c-img-close" style="right:-74px;display:none;" />'
					    +'</div>'
			 +'</div>'; 
		    $("#carouselist").append(htl);
    	}else{
    		carouseltype=false;
    	}
    }
    
	$(function(){
		if(carousel.length>0){
			carousel=carousel.substring(0,carousel.length-1);
			var carousellist=carousel.split(",");
			carouselsum=carousellist.length;
			var htl="";
			for (i=0;i<carousellist.length;i++){
			//	alert(i);
				carouselnumb=i+1;
			    htl+='<div id="carouseldiv'+carouselnumb+'" style=" width: 109px;height: 109px;float: left;display: inline-block;margin-right: 2%;margin-bottom: 2%;">'
						 +'<div class="hidden">'
					      +'<input id="carousel" name="carousel" type="hidden" value="'+carousellist[i]+'">'
					      +'<input id="fileimgcarousel'+carouselnumb+'" type="file" onchange="Javascript:carouselChange(\'30\',\'30000\',\'30000\','+carouselnumb+',\'fileimgcarousel'+carouselnumb+'\',\'carousel\',\'imgcarousel'+carouselnumb+'\',\'closecarousel'+carouselnumb+'\');" style="display: none;">'
					     +'</div>'
						 +'<div class="goods-sku-s-b-r-c-img" style="display: inline-block;">'
							+'<a href="javascript:filebtn(\'fileimgcarousel'+carouselnumb+'\');" style="cursor: pointer; display: inline-block; width: 109px;height: 109px;">'
						    	+'<img id="imgcarousel'+carouselnumb+'" src="'+carousellist[i]+'" style="width: 109px;height: 109px; overflow: hidden;" >'
							+'</a>'
						    +'<span  id="closecarousel'+carouselnumb+'" onclick="carouseldel_pic(\'carouseldiv'+carouselnumb+'\',\'carousel\','+carouselnumb+');" class="goods-sku-s-b-r-c-img-close" style="right:-74px;" />'
						    +'</div>'
				 +'</div>'; 
			    if(carousellist.length<10){
			    	carouselnumb+=1;
			    	 htl+='<div id="carouseldiv'+carouselnumb+'" style=" width: 109px;height: 109px;float: left;display: inline-block;margin-right: 2%;margin-bottom: 2%;">'
							 +'<div class="hidden">'
						      +'<input id="carousel" name="carousel" type="hidden" value="">'
						      +'<input id="fileimgcarousel'+carouselnumb+'" type="file" onchange="Javascript:carouselChange(\'30\',\'30000\',\'30000\','+carouselnumb+',\'fileimgcarousel'+carouselnumb+'\',\'carousel\',\'imgcarousel'+carouselnumb+'\',\'closecarousel'+carouselnumb+'\');" style="display: none;">'
						     +'</div>'
							 +'<div class="goods-sku-s-b-r-c-img" style="display: inline-block;">'
								+'<a href="javascript:filebtn(\'fileimgcarousel'+carouselnumb+'\');" style="cursor: pointer; display: inline-block; width: 109px;height: 109px;">'
							    	+'<img id="imgcarousel'+carouselnumb+'" src="plug-in/ui/images/shangchuan.png" style="width: 109px;height: 109px; overflow: hidden;" >'
								+'</a>'
							    +'<span  id="closecarousel'+carouselnumb+'" onclick="carouseldel_pic(\'carouseldiv'+carouselnumb+'\',\'carousel\','+carouselnumb+');" class="goods-sku-s-b-r-c-img-close" style="right:-74px;display:none;" />'
							    +'</div>'
					 +'</div>'; 
			    }
			   // $("#carouselist").append(htl);
			   
			}
			 $("#carouselist").html(htl);
		}
		
	}); 