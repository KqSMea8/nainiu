        var Max_Size = 30; //2M
        var Max_Width = 30000; //100px
        var Max_Height = 30000; //200px
function checkFileChange(MaxSize,MaxWidth,MaxHeight,index,id,yulanurl,yulanurlwu,yulanurldelete,picurlid,picurlimg){
	  Max_Size=MaxSize;
      Max_Width=MaxWidth;
      Max_Height=MaxHeight;
	 var fileEl=document.getElementById(id);//显示图片的对象 
	   testMaxSize(fileEl);
       testWidthHeight(fileEl);
      testGeshi(fileEl);
      up_pic(index,id,yulanurl,yulanurlwu,yulanurldelete,picurlid,picurlimg);
  
}
      var tishi='';
       var tishiboolean = true;
        //验证格式
         function testGeshi(fileEl){
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
function testMaxSize(file){

        
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
  function testWidthHeight(file){
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
            }else{
                html = '大小未通过，要求'+ Max_Size +'M.</br>';
                tishiboolean=false;
            }
            tishi+=html;
           
            $("#t1").html(html);
        }

        function showTip2(isAllow){
          

            if(isAllow){
               // html += '宽高通过';
            }else{
            	
                html += '宽高未通过，要求width:'+ Max_Width +', height:'+ Max_Height;
            }
             tishi+=html;
            $("#t2").html(html);
        }
    function up_pic(index,id,yulanurl,yulanurlwu,yulanurldelete,picurlid,picurlimg){
			if(tishiboolean){
				uploadpic(index,id,yulanurl,yulanurlwu,yulanurldelete,picurlid,picurlimg);
			}else{
				alert(tishi);
				
			}
			tishi="";
		}
    function uploadpic(index,id,yulanurl,yulanurlwu,yulanurldelete,picurlid,picurlimg){
         var formData = new FormData();
         //formData.append("sqlfile",$("#sqlfile")[0].files[0]);
        // formData.append("warfile",$("#warfile")[0].files[0]);
         formData.append("file",$("#"+id)[0].files[0]);
        // formData.append("handle",$("#handle").val());
//alert(index+"="+picurlid);
                 $.ajax({
                  url: 'uplodfil.do?uplodfil/img',
                  type: 'post',
                  data: formData,
                  async: false,
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
                      //document.getElementById("fileimg").src=url_path;
                 // console.log(yulanurl);
                  // document.getElementById(picurlid).value=url_path;
                  var aryya=  document.getElementsByName(picurlid);
             	  index--;
             	  aryya[index].value=url_path;
                   document.getElementById(yulanurl).href=url_path; 
                   document.getElementById(picurlimg).src=url_path;
                   document.getElementById(yulanurl).style.display="inline-block";//显示
                   document.getElementById(yulanurldelete).style.display="inline-block";//显示
               //    document.getElementById(yulanurlwu).style.display="none";//隐藏
                   carouselappde();
                  },
                  error: function (data) {
                      alert("文件上传出错！");
                  }
             });
		
 }
    function del_pic(yulanurl,yulanurlwu,yulanurldelete,picurlid,picurlimg){
    	//document.getElementById(yulanurlwu).style.display="inline-block";//显示
    	document.getElementById(yulanurl).style.display="none";//隐藏
    	document.getElementById(yulanurldelete).style.display="none";//隐藏
		   document.getElementById(picurlid).value="";
           document.getElementById(yulanurl).href = "";
           document.getElementById(picurlimg).src="";
	  }
    var  carouselnumb=1;
    var  carouselsum=1;
    var carouseltype=false;
    function carouseldel_pic(carouseldiv,pic_url,index){
    	
    	document.getElementById(carouseldiv).style.display="none";//隐藏disabled="disabled"
    	 var aryya=  document.getElementsByName(pic_url);
	   	  index--;
	   	  //aryya[index].value="0";
	   	  aryya[index].disabled=true;
	   	  console.log(carouselsum);
	  	if(carouselsum==3){
	  		if(carouseltype){
	  			carouselsum--;
	  			carouseltype=false;
	  		}else{
	  			carouseltype=true;
		  		carouselnumb++;
		  		var htl="";
		  		 htl+='<div class="col-sm-4"  id="carouseldiv'+carouselnumb+'" style="width: 20%;">'
			    		 +'<img style="width: 109px;height: 109px; overflow: hidden;" id="picurlimg'+carouselnumb+'"'
						 +'src="${nEvaluatePage.picurl}">'
			             +'<a id="picurlUrl'+carouselnumb+'"  target="_blank" style="display: none;">预览</a>'
						 +'&nbsp;&nbsp;&nbsp;&nbsp;'
						 +'<a id="picurldelete'+carouselnumb+'" href="javascript:carouseldel_pic(\'carouseldiv'+carouselnumb+'\',\'evaluatepic\','+carouselnumb+')" style="display: none;">删除</a>'
						 +'<input id="evaluatepic" name="evaluatepic" type="hidden" value=""> </a>'
						 +'</br>'
						 +'<a href="javascript:;" style="padding: 4px 10px;line-height: 20px;position: relative;cursor: pointer; color: #454343;border: 1px solid #ddd; border-radius: 4px; overflow: hidden; display: inline-block;">'
							 +'选择文件'
							 +'<input type="file" name="picurlfile'+carouselnumb+'" id="picurlfile'+carouselnumb+'" '
							 +'onchange="Javascript:checkFileChange(\'30\',\'30000\',\'30000\','+carouselnumb+',\'picurlfile'+carouselnumb+'\',\'picurlUrl'+carouselnumb+'\',\'picurlwu'+carouselnumb+'\',\'picurldelete'+carouselnumb+'\',\'evaluatepic\',\'picurlimg'+carouselnumb+'\');" '
							 +'style="position: absolute; font-size: 100px; right: 0;  top: 0; opacity: 0;cursor: pointer; width: 100%;  height: 100%;">'
					     +'</a>'
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
    	
    	if(carouselsum<3){
    		carouselnumb++;
        	carouselsum++;
	    	var htl="";
	    	 htl+='<div class="col-sm-4"  id="carouseldiv'+carouselnumb+'" style="width: 20%;">'
		    		 +'<img style="width: 109px;height: 109px; overflow: hidden;" id="picurlimg'+carouselnumb+'"'
					 +'src="${nEvaluatePage.picurl}">'
		             +'<a id="picurlUrl'+carouselnumb+'"  target="_blank" style="display: none;">预览</a>'
					 +'&nbsp;&nbsp;&nbsp;&nbsp;'
					 +'<a id="picurldelete'+carouselnumb+'" href="javascript:carouseldel_pic(\'carouseldiv'+carouselnumb+'\',\'evaluatepic\','+carouselnumb+')" style="display: none;">删除</a>'
					 +'<input id="evaluatepic" name="evaluatepic" type="hidden" value=""> </a>'
					 +'</br>'
					 +'<a href="javascript:;" style="padding: 4px 10px;line-height: 20px;position: relative;cursor: pointer; color: #454343;border: 1px solid #ddd; border-radius: 4px; overflow: hidden; display: inline-block;">'
						 +'选择文件'
						 +'<input type="file" name="picurlfile'+carouselnumb+'" id="picurlfile'+carouselnumb+'" '
						 +'onchange="Javascript:checkFileChange(\'30\',\'30000\',\'30000\','+carouselnumb+',\'picurlfile'+carouselnumb+'\',\'picurlUrl'+carouselnumb+'\',\'picurlwu'+carouselnumb+'\',\'picurldelete'+carouselnumb+'\',\'evaluatepic\',\'picurlimg'+carouselnumb+'\');" '
						 +'style="position: absolute; font-size: 100px; right: 0;  top: 0; opacity: 0;cursor: pointer; width: 100%;  height: 100%;">'
				     +'</a>'
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
				 htl+='<div class="col-sm-4"  id="carouseldiv'+carouselnumb+'" style="width: 20%;">'
			    		 +'<img style="width: 109px;height: 109px; overflow: hidden;" id="picurlimg'+carouselnumb+'"'
						 +'src="'+carousellist[i]+'">'
			             +'<a id="picurlUrl'+carouselnumb+'"  target="_blank" style="display: none;">预览</a>'
						 +'&nbsp;&nbsp;&nbsp;&nbsp;'
						 +'<a id="picurldelete'+carouselnumb+'" href="javascript:carouseldel_pic(\'carouseldiv'+carouselnumb+'\',\'evaluatepic\','+carouselnumb+')" style="display: none;">删除</a>'
						 +'<input id="evaluatepic" name="evaluatepic" type="hidden" value="'+carousellist[i]+'"> </a>'
						 +'</br>'
						 +'<a href="javascript:;" style="padding: 4px 10px;line-height: 20px;position: relative;cursor: pointer; color: #454343;border: 1px solid #ddd; border-radius: 4px; overflow: hidden; display: inline-block;">'
							 +'选择文件'
							 +'<input type="file" name="picurlfile'+carouselnumb+'" id="picurlfile'+carouselnumb+'" '
							 +'onchange="Javascript:checkFileChange(\'30\',\'30000\',\'30000\','+carouselnumb+',\'picurlfile'+carouselnumb+'\',\'picurlUrl'+carouselnumb+'\',\'picurlwu'+carouselnumb+'\',\'picurldelete'+carouselnumb+'\',\'evaluatepic\',\'picurlimg'+carouselnumb+'\');" '
							 +'style="position: absolute; font-size: 100px; right: 0;  top: 0; opacity: 0;cursor: pointer; width: 100%;  height: 100%;">'
					     +'</a>'
				 +'</div>'; 
			    if(carousellist.length<3){
			    	carouselnumb+=1;
			    	 htl+='<div class="col-sm-4"  id="carouseldiv'+carouselnumb+'" style="width: 20%;">'
				    		 +'<img style="width: 109px;height: 109px; overflow: hidden;" id="picurlimg'+carouselnumb+'"'
							 +'src="${nEvaluatePage.picurl}">'
				             +'<a id="picurlUrl'+carouselnumb+'"  target="_blank" style="display: none;">预览</a>'
							 +'&nbsp;&nbsp;&nbsp;&nbsp;'
							 +'<a id="picurldelete'+carouselnumb+'" href="javascript:carouseldel_pic(\'carouseldiv'+carouselnumb+'\',\'evaluatepic\','+carouselnumb+')" style="display: none;">删除</a>'
							 +'<input id="evaluatepic" name="evaluatepic" type="hidden" value=""> </a>'
							 +'</br>'
							 +'<a href="javascript:;" style="padding: 4px 10px;line-height: 20px;position: relative;cursor: pointer; color: #454343;border: 1px solid #ddd; border-radius: 4px; overflow: hidden; display: inline-block;">'
								 +'选择文件'
								 +'<input type="file" name="picurlfile'+carouselnumb+'" id="picurlfile'+carouselnumb+'" '
								 +'onchange="Javascript:checkFileChange(\'30\',\'30000\',\'30000\','+carouselnumb+',\'picurlfile'+carouselnumb+'\',\'picurlUrl'+carouselnumb+'\',\'picurlwu'+carouselnumb+'\',\'picurldelete'+carouselnumb+'\',\'evaluatepic\',\'picurlimg'+carouselnumb+'\');" '
								 +'style="position: absolute; font-size: 100px; right: 0;  top: 0; opacity: 0;cursor: pointer; width: 100%;  height: 100%;">'
						     +'</a>'
					 +'</div>'; 
			    }
			   // $("#carouselist").append(htl);
			   
			}
			 $("#carouselist").html(htl);
		}
		
	}); 
