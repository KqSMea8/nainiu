        var Max_Size = 30; //2M
        var Max_Width = 750; //100px
        var Max_Height = 220; //200px
        var tishi='';
        var tishiboolean = true;
        var tishiwhboolean = true;
function checkFileChange(MaxSize,MaxWidth,MaxHeight,id,yulanurl,yulanurlwu,yulanurldelete,picurlid,picurlimg){
	  Max_Size=MaxSize;
//      Max_Width=MaxWidth;
//      Max_Height=MaxHeight;
	 var fileEl=document.getElementById(id);//显示图片的对象 
	    testGeshi(fileEl);
	   testMaxSize(fileEl);
       testWidthHeight(fileEl,id,yulanurl,yulanurlwu,yulanurldelete,picurlid,picurlimg);
  
     // up_pic(id,yulanurl,yulanurlwu,yulanurldelete,picurlid,picurlimg);
  
}
     
        //验证格式
         function testGeshi(fileEl){
         	 var txtImg_url=fileEl.value.toLowerCase();
         //	 var exp = /.\.jpg|.\.gif|.\.png|.\.bmp/i; 
        // var exp = /.\.jpg|.\.png|/i; 
         //alert(txtImg_url.test("jpg"));
	         if(txtImg_url.indexOf("jpg")!=-1 || txtImg_url.indexOf("png")!=-1){  
	         	//alert("格式错误");
	        	 tishiboolean=true;
	         }else{
	         tishiboolean=false;
	         	tishi+="格式错误,";
	        }
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
  function testWidthHeight(file,id,yulanurl,yulanurlwu,yulanurldelete,picurlid,picurlimg){
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
                        isAllow = parseInt(width) == parseInt(Max_Width) && parseInt(height) == parseInt(Max_Height);
                        showTip2(isAllow,id,yulanurl,yulanurlwu,yulanurldelete,picurlid,picurlimg);
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
                  isAllow = parseInt(width) == parseInt(Max_Width) && parseInt(height) == parseInt(Max_Height);
                showTip2(isAllow,id,yulanurl,yulanurlwu,yulanurldelete,picurlid,picurlimg);
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
           
          //  $("#t1").html(html);
        }

        function showTip2(isAllow,id,yulanurl,yulanurlwu,yulanurldelete,picurlid,picurlimg){
          

            if(isAllow){
               // html += '宽高通过';
            	tishiwhboolean=true;
            }else{
            	tishiwhboolean=false;
                html += '宽高未通过，要求width:'+ Max_Width +', height:'+ Max_Height;
            }
             tishi+=html;
             up_pic(id,yulanurl,yulanurlwu,yulanurldelete,picurlid,picurlimg);
        }
    function up_pic(id,yulanurl,yulanurlwu,yulanurldelete,picurlid,picurlimg){
			if(tishiboolean && tishiwhboolean){
				uploadpic(id,yulanurl,yulanurlwu,yulanurldelete,picurlid,picurlimg);
			}else{
				alert(tishi);
				
			}
			tishi="";
		}
    function uploadpic(id,yulanurl,yulanurlwu,yulanurldelete,picurlid,picurlimg){
         var formData = new FormData();
         //formData.append("sqlfile",$("#sqlfile")[0].files[0]);
        // formData.append("warfile",$("#warfile")[0].files[0]);
         formData.append("file",$("#"+id)[0].files[0]);
        // formData.append("handle",$("#handle").val());

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
                  console.log(yulanurl);
                   document.getElementById(picurlid).value=url_path;
                   document.getElementById(yulanurl).href=url_path; 
                   document.getElementById(picurlimg).src=url_path;
                   document.getElementById(yulanurl).style.display="inline-block";//显示
                   document.getElementById(yulanurldelete).style.display="inline-block";//显示
               //    document.getElementById(yulanurlwu).style.display="none";//隐藏
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
    function uploadFile(){
    	 //document.getElementById("fileimg").src="http://localhost:8180/jeecg/plug-in/ueditor/jsp/upload/image/20171109/61655cbf-da56-4a7c-8ff1-1e5dfea92a72.jpg";
    }
