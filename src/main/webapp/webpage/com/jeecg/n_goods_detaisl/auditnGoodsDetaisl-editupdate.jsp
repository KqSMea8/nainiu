<!DOCTYPE html>
<!-- saved from url=(0056)http://localhost:8180/jeecg/demo/jpDemoOrderMain.do?list -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<html lang="en">

	<head>
		<title>件开发平台</title>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<t:base type="jquery,easyui,tools"></t:base>
		<!-- ztree -->
		<link rel="stylesheet" type="text/css" href="plug-in/ztree/css/zTreeStyle.css"></link>
		<script type="text/javascript" src="plug-in/ztree/js/jquery.ztree.core-3.5.min.js" ></script>
		<script type="text/javascript" src="plug-in/ztree/js/jquery.ztree.excheck-3.5.min.js"></script>
		<link href="plug-in/ui/css/bootstrap.css" rel="stylesheet">
		<link href="plug-in/ui/css/style.css" rel="stylesheet">
		<link href="plug-in/ui/css/zTreeStyle/zTreeStyle.css" type="text/css" rel="stylesheet">
		<link href="plug-in/ui/css/mycss/kongjian.css" rel="stylesheet">
		<link rel="stylesheet" href="plug-in/ui/hplus/css/font-awesome.css">
		<script type="text/javascript" src="plug-in/ui/js/iFrameResize.js"></script>
		<script type="text/javascript" src="plug-in/ui/js/jquery-1.9.1.js"></script>
		<script type="text/javascript" src="plug-in/ui/js/respond.min.js"></script>
		<script type="text/javascript" src="plug-in/ui/js/bootstrap.js"></script>
		<script type="text/javascript" src="plug-in/ui/js/common.js"></script>
		<script type="text/javascript" src="plug-in/ui/js/jquery.form.js"></script>
		<script type="text/javascript" src="plug-in/ui/js/scrollNav.js"></script>
		<script type="text/javascript" src="plug-in/ui/js/My97DatePicker/WdatePicker.js" language="javascript"></script>
		<link href="plug-in/ui/js/My97DatePicker/skin/WdatePicker.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="plug-in/ui/layer/layer.js" language="javascript"></script>
		<link rel="stylesheet" href="plug-in/ui/layer/skin/layer.css" id="layui_layer_skinlayercss">
		<link rel="stylesheet" href="plug-in/tools/css/metrole/common.css">
		<script type="text/javascript" src="plug-in/lhgDialog/lhgdialog.min.js?skin=metrole"></script>
		<script src="webpage/com/jeecg/n_merchant/add.js"></script>
		<script src="webpage/com/jeecg/n_goods_detaisl/nGoodsDetaisl.js"></script>
		
		
		<link href="plug-in/ui/css/shang.css" rel="stylesheet" type="text/css">
		<script type="text/javascript">
				//var pid="${pid}";
				var goodsone="${nGoodsDetaislPage.goodsone}";
				var goodstwo="${nGoodsDetaislPage.goodstwo}";
				var goodsthree="${nGoodsDetaislPage.goodsthree}";
				var carousel="${nGoodsDetaislPage.carousel}";
				var details="${nGoodsDetaislPage.details}";
				//alert(carousel);
				
		</script>
		<script src="webpage/com/jeecg/n_goods_detaisl/goods_classifyupdate.js"></script>
		<script src="webpage/com/jeecg/n_goods_detaisl/standardupload.js"></script>
		<script src="webpage/com/jeecg/n_goods_detaisl/detailsupload.js"></script>
		<script src="webpage/com/jeecg/n_goods_detaisl/carouselupload.js"></script>
	</head>

	<body style='overflow:scroll;overflow-x:hidden'>
		<div class="container bs-docs-container" style="width:100%;">
			<div class="row">
				<div class="panel panel-default">
					<div class="panel-heading">编辑</div>
					<div class="panel-body">
						<form class="form-horizontal" role="form" id="dailogForm" action="nGoodsDetaislController.do?doUpdate" method="POST">
							<input id="id" name="id" type="hidden" value="${nGoodsDetaislPage.id }" />
							<input id="createName" name="createName" type="hidden" value="${nGoodsDetaislPage.createName }" />
							<input id="createBy" name="createBy" type="hidden" value="${nGoodsDetaislPage.createBy }" />
							<input id="updateName" name="updateName" type="hidden" value="${nGoodsDetaislPage.updateName }" />
							<input id="updateBy" name="updateBy" type="hidden" value="${nGoodsDetaislPage.updateBy }" />
							<input id="updateDate" name="updateDate" type="hidden" value="${nGoodsDetaislPage.updateDate }" />
							<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${nGoodsDetaislPage.sysOrgCode }" />
							<input id="remarks" name="remarks" type="hidden" value="${nGoodsDetaislPage.remarks }" />
							<input id="delFlag" name="delFlag" type="hidden" value="${nGoodsDetaislPage.delFlag }" />
							<input id="bpmStatus" name="bpmStatus" type="hidden" value="${nGoodsDetaislPage.bpmStatus }" />
							<input id="createDate" name="createDate" type="hidden" value="${nGoodsDetaislPage.createDate }" />
							<div class="panel-heading" style="background-color:#f5f5f5;margin-bottom:15px;margin-top:15px;"">商品关键信息--商品审核编辑</div>
							
								
							<div class="form-group mno">
								<label  class="col-sm-2 control-label" style="text-align:center;">主营类目:</label>
								<div class="col-sm-4" style="width:50%">
								<select class="form-control goodsone" id="goodsone"  datatype="*" name="goodsone" onchange="Javascript:Goodsone();" style="display:inline-block;width:149px">
								</select>
								<select class="form-control goodstwo"  id="goodstwo" datatype="*" name="goodstwo" onchange="Javascript:Goodstwo();" style="display:inline-block;width:149px">
								</select>
								<select class="form-control goodsthree" id="goodsthree"  datatype="*" name="goodsthree" onchange="Javascript:Goodsthree();" style="display:inline-block;width:149px">
								</select>

						     	<span class="Validform_checktip"></span>
								</div>
							</div>
							<div class="form-group mno">
								<label  class="col-sm-2 control-label" style="text-align:center;">商品类型:</label>
								<div class="col-sm-4">
								<%--
								<t:dictSelect field="starttype" type="list"
								    typeGroupCode="starttype" defaultVal="${nGoodsDetaislPage.starttype}" hasLabel="false" 
								    title="starttype"  datatype="*"  onchange="Javascript:starttypeChange();">
								</t:dictSelect> 
								--%>
								   <select name="starttype" id="starttype" style="width:157px;"class="form-control" 
								        onchange="Javascript:starttypeChange();">
									    <option value="1">助力团</option>
										<option value="2">普通团</option>
									</select>
								</div>
							</div>
							<div class="form-group mno">
								<label  class="col-sm-2 control-label" style="text-align:center;">发货时间承诺:</label>
								<div class="col-sm-4">
										<t:dictSelect field="sendTime" type="list"
										    typeGroupCode="sendTime" defaultVal="${nGoodsDetaislPage.sendTime}" hasLabel="false" 
										    title="sendTime"  datatype="*">
										</t:dictSelect> 
								</div>
								<label  class="col-sm-2 control-label" style="text-align:center;">全场包邮:</label>
								<div class="col-sm-4">
								<t:dictSelect field="post" type="list"
								    typeGroupCode="post" defaultVal="${nGoodsDetaislPage.post}" hasLabel="false" 
								    title="post"  datatype="*">
								</t:dictSelect> 
								</div>
							</div>
							<div class="form-group mno">
								<label  class="col-sm-2 control-label" style="text-align:center;">7天无理由退换:</label>
								<div class="col-sm-4">
								<t:dictSelect field="returns" type="list"
								    typeGroupCode="returns" defaultVal="${nGoodsDetaislPage.returns}" hasLabel="false" 
								    title="returns"  datatype="*">
								</t:dictSelect> 
								</div>
								<label  class="col-sm-2 control-label" style="text-align:center;">假一赔十:</label>
								<div class="col-sm-4">
								<t:dictSelect field="fine" type="list"
								    typeGroupCode="fine" defaultVal="${nGoodsDetaislPage.fine}" hasLabel="false" 
								    title="fine"  datatype="*">
								</t:dictSelect> 
								</div>
							</div>
							<div class="form-group mno">
								<label  class="col-sm-2 control-label" style="text-align:center;">是否会员专享:</label>
								<div class="col-sm-4">
									<t:dictSelect field="isMember" type="list" id ="isMember"
												  typeGroupCode="isMember" defaultVal="${nGoodsDetaislPage.isMember}" hasLabel="false"
												  title="isMember"  datatype="*">
									</t:dictSelect>
								</div>
								<label  class="col-sm-2 control-label" id="isOnlyOneLable" style="text-align:center;display: none">是否只能参团一次:</label>
								<div class="col-sm-4" id="isOnlyOneDiv" style="display: none">
									<t:dictSelect field="isOnlyOne" type="list" id="isOnlyOne"
												  typeGroupCode="isOnlyOne" defaultVal="${nGoodsDetaislPage.isOnlyOne}" hasLabel="false"
												  title="isOnlyOne"  datatype="*">
									</t:dictSelect>
								</div>
								<label  class="col-sm-2 control-label" id="crabNumberLable" style="text-align:center;display: none">赠送螃蟹数量:</label>
								<div class="col-sm-4" id="crabNumberDiv" style="display: none">
									<t:dictSelect field="crabNumber" type="list" id="crabNumber"
												  typeGroupCode="crabNumber" defaultVal="${nGoodsDetaislPage.crabNumber}" hasLabel="false"
												  title="crabNumber"  datatype="*">
									</t:dictSelect>
								</div>
							</div>
							<div class="form-group mno" style="display: none" id = "returnMoneyDiv">
								<label  class="col-sm-2 control-label" style="text-align:center;">商品返现金额:</label>
								<div class="col-sm-4">
									<t:dictSelect field="returnMoney" type="list" id="returnMoney"
												  typeGroupCode="returnMoney" defaultVal="${nGoodsDetaislPage.returnMoney}" hasLabel="false"
												  title="returnMoney"  datatype="*" >
									</t:dictSelect>
								</div>
							</div>
							<div class="panel-heading" style="background-color: #f5f5f5;margin-bottom:15px;margin-top:15px;">商品详情信息</div>
							<div class="form-group mno" id = "levelDiv" style="display: none">
								<label  class="col-sm-2 control-label" style="text-align:center;">等级奖励:</label>
								<div class="col-sm-4" style="width:80%">
									一级奖励&nbsp;&nbsp;:&nbsp;&nbsp;<input  type="text" id="oneLevel" name="oneLevel"  datatype="*" value="${nGoodsDetaislPage.oneLevel }" />
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp二级奖励&nbsp;&nbsp;:&nbsp;&nbsp;<input  type="text" id="twoLevel" name="twoLevel"  datatype="*" value="${nGoodsDetaislPage.twoLevel }" />
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp三级奖励&nbsp;&nbsp;:&nbsp;&nbsp;<input  type="text" id="threeLevel" name="threeLevel"  datatype="*" value="${nGoodsDetaislPage.threeLevel }" />
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp推荐奖金&nbsp;&nbsp;:&nbsp;&nbsp;<input  type="text" id="referralBonus" name="referralBonus"  datatype="*" value="${nGoodsDetaislPage.referralBonus }" />
								</div>
							</div>
							<div class="form-group mno">
								<label  class="col-sm-2 control-label" style="text-align:center;">商品标题:</label>
								<div class="col-sm-4" style="width:60%;" >
								<input  type="text" id="title" name="title" class="form-control" datatype="*1-100" value="${nGoodsDetaislPage.title }" />
								<span class="Validform_checktip"></span>
								</div>
							</div>
							<div class="form-group mno">
								<label  class="col-sm-2 control-label" style="text-align:center;">短标题:</label>
								<div class="col-sm-4">
								<input  type="text" id="duanTitle" name="duanTitle" class="form-control" datatype="*1-20" value="${nGoodsDetaislPage.duanTitle }" />
								<span class="Validform_checktip"></span>
								</div>
							</div>
							<div class="form-group mno">
								<label  class="col-sm-2 control-label" style="text-align:center;">商品分享标题:</label>
								<div class="col-sm-4" style="width:60%;" >
									<input  type="text" id="duanHit" name="duanHit" class="form-control" placeholder="建议300字以内" datatype="*0-300" value="${nGoodsDetaislPage.duanHit }" />
									<span class="Validform_checktip"></span>
								</div>
							</div>
							<div class="form-group mno">
								<label  class="col-sm-2 control-label" style="text-align:center;">商品分享提示语:</label>
								<div class="col-sm-4" style="width:60%;" >
								<input  type="text" id="hit" name="hit" class="form-control" placeholder="建议300字以内" datatype="*1-300" value="${nGoodsDetaislPage.hit }" />
								<span class="Validform_checktip"></span>
								</div>
							</div>
							<div class="form-group mno">
								<label  class="col-sm-2 control-label" style="text-align:center;">商品市场价:</label>
								<div class="col-sm-4">
									<input  type="text" id="bazaarPrice" name="bazaarPrice" class="form-control" datatype="*" value="${nGoodsDetaislPage.bazaarPrice }" />
									<span class="Validform_checktip"></span>
								</div>
								<label  class="col-sm-2 control-label" style="text-align:center;">物流重量:</label>
								<div class="col-sm-4">
									<input  type="text" id="weight" name="weight" placeholder="请输入商品重量"  class="form-control" datatype="*" value="${nGoodsDetaislPage.weight }" />
									<span class="Validform_checktip"></span>
								</div>
							</div>
							<div class="form-group mno">
								<label  class="col-sm-2 control-label" style="text-align:center;">商品描述:</label>
								<div class="col-sm-4" style="width:60%;" >
								<textarea placeholder="建议300字以内" id="contents" name="contents"  class="form-control" style="height:100px;" datatype="*1-300" >${nGoodsDetaislPage.contents }</textarea>
								<span class="Validform_checktip"></span>
								</div>
							</div>
							
						<%--	<div class="form-group mno">
									<label  class="col-sm-2 control-label" style="text-align:center;">商品主图:
										 <input id="picOne" name="picOne" datatype="*" type="hidden" value="${nGoodsDetaislPage.picOne }"></label>
									<div class="col-sm-4" style="width: 50%;">
									 <div style=" width: 139px;height: 139px;float: left;display: inline-block;margin-right: 10%;margin-bottom: 2%;">
											 <div class="hidden">
		     							      <input id="fileimgpic_one" type="file" onchange="Javascript:standardChange('30','750','352',1,'fileimgpic_one','picOne','pic_oneimg','pic_oneclose');" style="display: none;">
		     							    </div>
		         							<div class="goods-sku-s-b-r-c-img" style="display: inline-block;">
				          							<a href="javascript:filebtn('fileimgpic_one');" style="cursor: pointer; display: inline-block; width: 139px;height: 139px;">
					  							        <img id="pic_oneimg" src="${nGoodsDetaislPage.picOne }" style="width: 139px;height: 139px; overflow: hidden;" >
					  							    </a>
		         							       <span  id="pic_oneclose" onclick="del_pic('pic_oneclose','pic_oneimg','picOne',1);" class="goods-sku-s-b-r-c-img-close" style="right: -105px;display:none;" />
		         							 </div>
	         							</div>
	         							 <div style="  width: 50%;float: left;display: inline-block;margin-right: 2%;margin-bottom: 2%;">
	         							<p style="margin-bottom: 20px;">首页大图 </p><p><!-- react-text: 11864 -->a. 尺寸<!-- /react-text --><span style="color: red;">750 x 352px</span></p><p><!-- react-text: 11867 -->b. 大小<!-- /react-text --><span style="color: red;">100k</span><!-- react-text: 11869 -->以内<!-- /react-text --></p><p><!-- react-text: 11871 -->c. 图片格式仅支持<!-- /react-text --><span style="color: red;">JPG,PNG</span><!-- react-text: 11873 -->格式<!-- /react-text --></p><p>d. 图片背景应以纯白为主, 商品图案居中显示</p><p>e. 图片不可以添加任何品牌相关文字或logo</p>
	         							</div>
									</div>
							</div>--%>
							
							
							<div class="form-group mno">
								<label  class="col-sm-2 control-label" style="text-align:center;">商品活动图: <input id="picTwo" name="picTwo" datatype="*" type="hidden" value="${nGoodsDetaislPage.picTwo }"></label>
								<div class="col-sm-4" style="width: 50%;">
									<div style=" width: 139px;height: 139px;float: left;display: inline-block;margin-right: 10%;margin-bottom: 2%;">
										<div class="hidden">
											  <input id="fileimgpic_two" type="file" onchange="Javascript:commonChange('30','30000','30000',1,'fileimgpic_two','picTwo','pic_twoimg','pic_twoclose');" style="display: none;">
										</div>
										<div class="goods-sku-s-b-r-c-img" style="display: inline-block;">
											<a href="javascript:filebtn('fileimgpic_two');" style="cursor: pointer; display: inline-block; width: 139px;height: 139px;">
												 <img id="pic_twoimg" src="${nGoodsDetaislPage.picTwo }" style="width: 139px;height: 139px; overflow: hidden;" >
											</a>
											<span  id="pic_twoclose" onclick="del_pic('pic_twoclose','pic_twoimg','picTwo',1);" class="goods-sku-s-b-r-c-img-close" style="right: -105px;display:none;" />
										</div>
									</div>
									<div style="  width: 50%;float: left;display: inline-block;margin-right: 2%;margin-bottom: 2%;">
										<p style="margin-bottom: 20px;">商品活动图 </p><p><!-- react-text: 11864 -->a. 尺寸<!-- /react-text --><span style="color: red;">360 x 360px</span></p><p><!-- react-text: 11867 -->b. 大小<!-- /react-text --><span style="color: red;">100k</span><!-- react-text: 11869 -->以内<!-- /react-text --></p><p><!-- react-text: 11871 -->c. 图片格式仅支持<!-- /react-text --><span style="color: red;">JPG,PNG</span><!-- react-text: 11873 -->格式<!-- /react-text --></p><p>d. 图片背景应以纯白为主, 商品图案居中显示</p><p>e. 图片不可以添加任何品牌相关文字或logo</p>
									</div>
								</div>
							</div>
							<div class="form-group mno" style="margin-top: 20px;">
									<label  class="col-sm-2 control-label" style="text-align:center;">商品轮播图:
										<input id="carouselOne" name="carouselOne"type="hidden" datatype="*"  value=""></label>
								
									<div class="col-sm-4" id="carouselist">
									
									   <div id="carouseldiv1" style=" width: 109px;height: 109px;float: left;display: inline-block;margin-right: 2%;margin-bottom: 2%;">
											 <div class="hidden">
		    							      <input id="carousel" name="carousel" type="hidden" value="${nGoodsDetaislPage.carousel }">
		    							      <input id="fileimgcarousel1" type="file" onchange="Javascript:carouselChange('30','30000','30000',1,'fileimgcarousel1','carousel','imgcarousel1','closecarousel1');" style="display: none;">
		    							    </div>
		        							<div class="goods-sku-s-b-r-c-img" style="display: inline-block;">
				          							<a href="javascript:filebtn('fileimgcarousel1');" style="cursor: pointer; display: inline-block; width: 109px;height: 109px;">
					  							        <img id="imgcarousel1" src="plug-in/ui/images/shangchuan.png" style="width: 109px;height: 109px; overflow: hidden;" >
					  							    </a>
		        							       <span  id="closecarousel1" onclick="carouseldel_pic('carouseldiv1','carousel',1);" class="goods-sku-s-b-r-c-img-close" style="right:-74px;display:none;" />
		        							 </div>
	        							 </div> 
	        							
									</div>
									
									<div class="col-sm-4"><p>商品轮播图</p><p><!-- react-text: 11904 -->a. 尺寸<!-- /react-text --><span style="color: red;">宽:480~960px, 高:480~960px</span></p><p><!-- react-text: 11909 -->b. 大小<!-- /react-text --><span style="color: red;">1M</span></p><p><!-- react-text: 11912 -->c. 数量限制在<!-- /react-text --><span style="color: red;">10张</span><!-- react-text: 11914 -->以内<!-- /react-text --></p><p><!-- react-text: 11916 -->d. 图片格式仅支持<!-- /react-text --><span style="color: red;">JPG,PNG</span><!-- react-text: 11918 -->格式<!-- /react-text --></p><p><!-- react-text: 11920 -->e. 上传<!-- /react-text --><span style="color: red;">主轮播图</span><!-- react-text: 11922 -->时自动生成缩略图和高清缩略图<!-- /react-text --></p><p>f. 主轮播图背景为纯白色（服饰除外）,图片上不添加任何文字</p><p><!-- react-text: 11925 -->g. 点击上传时，支持<!-- /react-text --><span style="color: red;">批量上传</span><!-- react-text: 11927 -->轮播图，主轮播图除外<!-- /react-text --></p>
									</div>
							</div>
							<div class="form-group mno" style="margin-top: 20px;">
									<label  class="col-sm-2 control-label" style="text-align:center;">商品详情图: 
										<input id="detailsOne" name="detailsOne" datatype="*" type="hidden"  value="">
									</label>
									
									<div class="col-sm-4" id="detailslist">
									   <div id="detailsdiv1" style=" width: 109px;height: 109px;float: left;display: inline-block;margin-right: 2%;margin-bottom: 2%;">
											 <div class="hidden">
		    							      <input id="details" name="details" type="hidden" value="">
		    							      <input id="fileimgdetails1" type="file" onchange="Javascript:detailsChange('30','30000','30000',1,'fileimgdetails1','details','imgdetails1','closedetails1');" style="display: none;">
		    							    </div>
		        							<div class="goods-sku-s-b-r-c-img" style="display: inline-block;">
				          							<a href="javascript:filebtn('fileimgdetails1');" style="cursor: pointer; display: inline-block; width: 109px;height: 109px;">
					  							        <img id="imgdetails1" src="plug-in/ui/images/shangchuan.png" style="width: 109px;height: 109px; overflow: hidden;" >
					  							    </a>
		        							       <span  id="closedetails1" onclick="detailsdel_pic('detailsdiv1','details',1);" class="goods-sku-s-b-r-c-img-close" style="right:-74px;display:none;" />
		        							 </div>
	        							 </div> 
									</div>
									<div class="col-sm-4">
									<p>商品详情图</p><p><!-- react-text: 11943 -->a. 尺寸要求宽度处于<!-- /react-text --><span style="color: red;">480~1200px</span><!-- react-text: 11945 -->，高度<!-- /react-text --><span style="color: red;">1~1500px</span><!-- react-text: 11947 -->之间<!-- /react-text --></p><p><!-- react-text: 11949 -->b. 大小<!-- /react-text --><span style="color: red;">1M</span><!-- react-text: 11951 -->以内<!-- /react-text --></p><p><!-- react-text: 11953 -->c. 数量限制在<!-- /react-text --><span style="color: red;">20张</span><!-- react-text: 11955 -->之间<!-- /react-text --></p><p><!-- react-text: 11957 -->d. 图片格式仅支持<!-- /react-text --><span style="color: red;">JPG,PNG</span><!-- react-text: 11959 -->格式<!-- /react-text --></p><p><!-- react-text: 11961 -->e. 点击上传时，支持<!-- /react-text --><span style="color: red;">批量上传</span><!-- react-text: 11963 -->详情图<!-- /react-text --></p>
									</div>
									
							</div>
							<div class="panel-heading" style="background-color: #f5f5f5;margin-bottom:15px;margin-top:15px;">商品规格与库存</div>
							
							<div class="form-group mno" >
								<label  class="col-sm-2 control-label" style="width:16%;text-align:center;">请选择规则型号:</label>
								<div class="col-sm-4" >
								<input id="standardOnelist" name="standardOnelist" type="hidden" value="${nGoodsDetaislPage.standardOnelist }" />
								<input id="standardTwolist" name="standardTwolist" type="hidden" value="${nGoodsDetaislPage.standardTwolist }" />
								   <select name="standard" id="standard" style="width:157px;"class="form-control" onchange="Javascript:selectChange();">
								    <option value="0">单品</option>
								    <option value="1">一种规格</option>
									<option value="2">二种规格</option>
									</select>
								</div>
							</div>
							<div class="form-group mno" id="guigeone" style="margin-top:25px;display:none;" >
								<label  class="col-sm-2 control-label" style="margin-left:50px;text-align:center;">规格1:</label>
								<div class="col-sm-4" style="width:165px;" >
								   <select name="standardOne" id="standardOne"style="width:157px;" class="form-control" onchange="Javascript:geOne();">
									</select>
								</div>
								<div  >
									<ul class="g-s-s-b-r-c-o-s-add first" id="Onecontent" >
								    	<li class="g-s-s-b-r-c-o-s-a-btn" id="oneadd"><a href="javascript:onebtn();" >+添加</a></li>
									</ul>
								</div>
							</div>
							<div class="form-group mno" id="guigeoneinput"  style="margin-top:5px;margin-left:150px;display:none;" >
								<div class="col-sm-4"  >
								
								<input type="text" name="inputone" id="inputone" style="width:50%;"
								placeholder="请输入规格名称" value="" class="form-control">
								<button type="button" id="guigeonebtn" class="btn btn-default" id="formReturn" data-dismiss="modal" 
								style="background-color:#dcd6dc;" onclick="getOnebtn();">确定</button>
								</div>
						   </div>
							<div class="form-group mno" id="guigetwo" style="margin-top:25px;display:none;">
								<label  class="col-sm-2 control-label" style="margin-left:50px;text-align:center;">规格2:</label>
								<div class="col-sm-4" style="width:165px;">
								   <select name="standardTwo" id="standardTwo"style="width:157px;" class="form-control" onchange="Javascript:geTwo();">
									</select>
								</div>
								<div >
									<ul class="g-s-s-b-r-c-o-s-add first" id="Twocontent" >
									<li class="g-s-s-b-r-c-o-s-a-btn" id="twoadd"><a href="javascript:twobtn();" >+添加</a></li>
									</ul>
					        	</div>
							</div>
							<div class="form-group mno" id="guigetwoinput"  style="margin-top:5px;margin-left:150px;display:none;" >
									<div class="col-sm-4"  >
									<input type="text" name="inputtwo" id="inputtwo" style="width:50%;"
									placeholder="请输入规格名称" value="" class="form-control">
									<button type="button" id="guigeonebtn" class="btn btn-default" id="formReturn" data-dismiss="modal" 
									style="background-color:#dcd6dc;" onclick="getTwobtn();">确定</button>
									</div>
							   </div>
								<div class="form-group mno" style="margin-top:50px;">
									<label  class="col-sm-2 control-label" style="text-align:center;">批量设置:</label>
									<div class="col-sm-4" style="width:15%">
									<input  type="text" id="sum_number" name="sum_number"  placeholder="库存增减"class="form-control" />
									</div>
									<div class="col-sm-4"  style="width:15%" >
									<input  type="text" id="tuan_price" name="tuan_price" placeholder="团购价"class="form-control" />
									</div>
									<div class="col-sm-4" style="width:15%" >
									<input  type="text" id="unit_price" name="unit_price"  placeholder="单买价"  class="form-control" />
									</div>
									<button type="button" id="guigetwobtn" class="btn btn-default" id="formReturn" 
									data-dismiss="modal" style="background-color:red;" onclick="Listbtn();" >确定</button>
								</div>
							   <div class="form-group mno" style="margin-top:20px;margin-bottom:30px;">
							   <table class="table table-striped">
								<thead>
									<tr id="tabletitle">
									<th style="display:none;" id="tableone">123</th>
									<th style="display:none;" id="tabletwo">123</th>
									<th>库存增减</th>
									<th>团购价</th>
									<th>单买价</th>
									<th>会员价</th>
									<th>商家编码</th>
									<th>SKU预览图</th>
									<th>状态</th>
									<th>操作</th>
									</tr>
								</thead>
								<tbody id="htmcontent">
								<c:if test="${fn:length(nstandarddetails)  > 0 }">
								<c:forEach items="${nstandarddetails}" var="poVal" varStatus="stuts">
									<tr>
										 <c:if test="${not empty poVal.standardOnename}">
											   <td>${poVal.standardOnename } </td>
											  <input id="nstandarddetails[${stuts.index }].standardOnename" name="nstandarddetails[${stuts.index }].standardOnename" type="hidden" value="${poVal.standardOnename }">
										 </c:if>
										  <c:if test="${not empty poVal.standardTwoname}">
		            						  <input id="nstandarddetails[${stuts.index }].standardTwoname" name="nstandarddetails[${stuts.index }].standardTwoname" type="hidden" value="${poVal.standardTwoname }">
		            						  <td>${poVal.standardTwoname } </td>
	            						  </c:if>	
		          						  <td><input type="text" onchange="Javascript:getSumNumbers();"  id="nstandarddetails[${stuts.index }].sumNumber" name="nstandarddetails[${stuts.index }].sumNumber" value="${poVal.sumNumber }"  datatype="n" class="pdd-form-input" style="width: 75px;"></td>
		          						  <td><input type="text" id="nstandarddetails[${stuts.index }].tuanPrice" name="nstandarddetails[${stuts.index }].tuanPrice"   datatype="ns2" class="pdd-form-input" value="${poVal.tuanPrice }" style="width: 75px;"></td>
		          						  <td><input type="text"  id="nstandarddetails[${stuts.index }].unitPrice" name="nstandarddetails[${stuts.index }].unitPrice"  datatype="ns2" class="pdd-form-input" value="${poVal.unitPrice }" style="width: 75px;"></td>
										  <td><input type="text"  id="nstandarddetails[${stuts.index }].memberPrice" name="nstandarddetails[${stuts.index }].memberPrice"  datatype="ns2" class="pdd-form-input" value="${poVal.memberPrice }" style="width: 75px;"></td>
										  <td><input type="text"  id="nstandarddetails[${stuts.index }].goodsCode" name="nstandarddetails[${stuts.index }].goodsCode"  datatype="*0-100" class="pdd-form-input" value="${poVal.goodsCode }" style="width: 75px;"></td>
	          							  <td style="width:100px;">
	          							    <div class="hidden">
	          							      <input id="nstandarddetails[${stuts.index }].picUrl" name="nstandarddetails[${stuts.index }].picUrl" type="hidden"  value="${poVal.picUrl }" >
	          							      <input id="fileimg${stuts.index+1 }" type="file" onchange="Javascript:commonChange1('30','30000','30000',${stuts.index+1 },'fileimg${stuts.index+1 }','nstandarddetails[${stuts.index }].picUrl','pic_urlimg${stuts.index+1 }','skuimgclose${stuts.index+1 }');" style="display: none;">
	          							    </div>
		          							<div class="goods-sku-s-b-r-c-img" style="display: inline-block;">
				          							<a href="javascript:filebtn('fileimg${stuts.index+1 }');" style="cursor: pointer; display: inline-block; width: 40px; height: 40px;">
				          						    <c:if test="${not empty poVal.picUrl}">
					  							        <img id="pic_urlimg${stuts.index+1 }" src="${poVal.picUrl }" width="40" height="40">
					  							  	</c:if>	
					  							   <c:if test="${ empty poVal.picUrl}">
				  							        <img id="pic_urlimg${stuts.index+1 }" src="plug-in/ui/images/shangchuan.png" width="40" height="40">
				  							    	</c:if>	
					  							    </a>
		          							       <span  id="skuimgclose${stuts.index+1 }" onclick="del_pic('skuimgclose${stuts.index+1 }','pic_urlimg${stuts.index+1 }','picUrl',${stuts.index+1 });" class="goods-sku-s-b-r-c-img-close"  style="display:none;" />
		          							 </div>
	          							  </td>
	          							 <input id="jishu" name="jishu" type="hidden" />
	          							  <input id="nstandarddetails[${stuts.index }].shangxiatype" name="nstandarddetails[${stuts.index }].shangxiatype" type="hidden" value="${poVal.shangxiatype}" />
										  <td>
											  <div  id="shangxiatypename${stuts.index+1 }">
											  <c:if test="${poVal.shangxiatype==0}">   已上架</c:if>
											  <c:if test="${poVal.shangxiatype!=0}">   已下架</c:if> 
											  </div>
										  </td>
										  <td>
										  <div  id="shangxia${stuts.index+1 }" onclick="Shangbtn('nstandarddetails[${stuts.index }].shangxiatype','shangxiatypename${stuts.index+1 }','shangxia${stuts.index+1 }');" class="shang">
										  <c:if test="${poVal.shangxiatype==0}">下架</c:if>
										  <c:if test="${poVal.shangxiatype!=0}">上架</c:if> 
										  </div
										  ></td>
										</tr>
									</c:forEach>
									</c:if>	
								</tbody>
							    </table>
							 </div>
							<div class="form-group mno">
							    <input id="tuanprices" name="tuanprices" type="hidden" />
							    <input id="unitprices" name="unitprices" type="hidden" />
								<input id="memberprices" name="memberprices" type="hidden" />
								<label  class="col-sm-2 control-label" style="text-align:center;">总库存:</label>
								<div class="col-sm-4" style="width:15%">
								<input  type="text" id="sumNumbers" name="sumNumbers" readonly class="form-control" datatype="n" value="${nGoodsDetaislPage.sumNumbers }" />
								<span class="Validform_checktip"></span>
								</div>
								<label  class="col-sm-2 control-label" style="text-align:center;">商家编号:</label>
								<div class="col-sm-4"  style="width:15%" >
								<input  type="text" id="codes" name="codes" class="form-control" datatype="*0-100" value="${nGoodsDetaislPage.codes }" />
								<span class="Validform_checktip"></span>
								</div>
							</div>
							<div class="panel-heading" style="background-color: #f5f5f5;margin-bottom:15px;margin-top:15px;" >团信息</div>
							<div class="form-group mno">
								<label  class="col-sm-2 control-label" style="text-align:center;">团购人数:</label>
								<div class="col-sm-4" style="width:15%">
								<input  type="text" id="groupPurchase" name="groupPurchase" class="form-control" datatype="n" value="${nGoodsDetaislPage.groupPurchase }" />
								<span class="Validform_checktip"></span>
								</div>
								<label  class="col-sm-2 control-label" style="text-align:center;">单次限量:</label>
								<div class="col-sm-4"  style="width:15%" >
								<input  type="text" id="setBounds" name="setBounds" class="form-control" 
								readonly="readonly"datatype="n" value="${nGoodsDetaislPage.setBounds }" />
								<span class="Validform_checktip"></span>
								</div>
								<%--
								<input   id="xiangou" name="xiangou" class="form-control"  type="hidden" value="${nGoodsDetaislPage.xiangou }" />
								
								<label  class="col-sm-2 control-label" style="text-align:center;">限购次数:</label>
								<div class="col-sm-4" style="width:15%" >
								<input  type="text" id="xiangou" name="xiangou" class="form-control"  type="hidden" value="${nGoodsDetaislPage.xiangou }" />
								<span class="Validform_checktip"></span>
								</div>
								--%>
								<label  class="col-sm-2 control-label" style="text-align:center;">限购次数:</label>
								<div class="col-sm-4" style="width:15%" >
								<input  type="text" id="xiangou" name="xiangou" class="form-control"  datatype="n" value="${nGoodsDetaislPage.xiangou }" />
								<span class="Validform_checktip"></span>
								</div>
							</div>
							
							
							<div class="form-group mno">
								<div class="col-sm-offset-1 col-sm-6">
									<button type="button" class="btn btn-default" id="formReturn" data-dismiss="modal" onclick="doUrl('nGoodsDetaislController.do?auditlist')">返回</button>
									<button type="button" class="btn btn-primary"  onclick="geSpan()" id="formSubmit">提交</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</body>

</html>
<script type="text/javascript" src="plug-in/ui/js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="plug-in/ui/js/forminit.p3.js"></script>
<script type="text/javascript" >
    $(function () {
        var isMember = "${nGoodsDetaislPage.isMember}";
        var isOnlyOne = "${nGoodsDetaislPage.isOnlyOne}";
        if(isMember == 1){
            $("#isOnlyOneLable").show();
            $("#isOnlyOneDiv").show();
            if(isOnlyOne == "0"){
                $("#returnMoneyDiv").show();
            }
            var crabNumberOptions = document.getElementById("crabNumber").children;
            crabNumberOptions[1].selected = true;
        }else{
            $("#isOnlyOneLable").hide();
            $("#isOnlyOneDiv").hide();
            var options1 = document.getElementById('isOnlyOne').children;
            options1[2].selected=true;
            var options2 = document.getElementById('returnMoney').children;
            options2[1].selected=true;

            $("#crabNumberDiv").show();
            $("#crabNumberLable").show();
        }
        $("#isMember").change(function(){
            var options=$("#isMember option:selected").val();
            if(options == "1"){
                $("#isOnlyOneLable").show();
                $("#isOnlyOneDiv").show();
                $("#crabNumberLable").hide();
                $("#crabNumberDiv").hide();
                var crabNumberOptions = document.getElementById("crabNumber").children;
                crabNumberOptions[1].selected = true;
            }else{
                $("#isOnlyOneLable").hide();
                $("#isOnlyOneDiv").hide();
                $("#returnMoneyDiv").hide();
                var options2 = document.getElementById('isOnlyOne').children;
                options2[2].selected=true;
                var options2 = document.getElementById('returnMoney').children;
                options2[1].selected=true;

                $("#crabNumberDiv").show();
                $("#crabNumberLable").show();
            }
        });
        $("#isOnlyOne").change(function () {
            var optionValue = $("#isOnlyOne option:selected").val();
            if(optionValue == "0"){
                $("#returnMoneyDiv").show();
            }else{
                $("#returnMoneyDiv").hide();
                var options2 = document.getElementById('returnMoney').children;
                options2[1].selected=true;
            }
        });
        var starttype = "${nGoodsDetaislPage.starttype}";
        if(starttype == "2"){
            $("#levelDiv").show();
        }else{
            $("#levelDiv").hide();
        }
    });
function starttypeChange(){
	var starttvalue=document.getElementById("starttype").value;
	//alert(starttvalue);
	//var selectvalue=$("#standard").find("option:selected").text();
	if(starttvalue=="1"){
		$("#setBounds").attr("readOnly",true); //不可编辑，可以传值;
		document.getElementById("setBounds").value="1";
		$("#oneLevel").val("0");
		$("#twoLevel").val("0");
		$("#threeLevel").val("0");
		$("#referralBonus").val("0");
		$("#levelDiv").hide();
	}else{
		$("#setBounds").attr("readOnly",false); //不可编辑，可以传值;
		$("#levelDiv").show();
	}
}
function geSpan(){
	//var picOne=document.getElementById("picOne").value;
	var carousellist=document.getElementsByName("carousel");
	var detailslist=document.getElementsByName("details");
	var jishulist= document.getElementsByName("jishu");
	/*if(picOne=="" || picOne==null){
		alert("请上传商品主图");
	}*/
	if(carousellist[0].value=="" || carousellist[0].value==null){
		//alert("请上传商品轮播图");
	}else{
		document.getElementById("carouselOne").value="123";
	}
	if(detailslist[0].value=="" || detailslist[0].value==null){
		//alert("请上传商品详情图");
	}else{
		document.getElementById("detailsOne").value="123";
	}
	var tuan_pricemin=0; 
	var tuan_pricemax=0; 
        for(var i=0;i<jishulist.length;i++){
        	var tuanPrice=document.getElementById("nstandarddetails["+i+"].tuanPrice").value;
        //	console.log("=0.01="+parseFloat(0.01)+"=0.1="+parseFloat(0.1)+"=1="+parseFloat(1)+"=1.1="+parseFloat(1.1)+"=2="+parseFloat(2));
        	if(i==0){
        		tuan_pricemin=parseFloat(tuanPrice);
        		tuan_pricemax=parseFloat(tuanPrice);
//        		console.log('i='+i+'=tuan_pricemin='+tuan_pricemin+'=tuan_pricemax='+tuan_pricemax+"=1="+parseInt(tuanPrice));
        	}
        	if(parseFloat(tuanPrice)<parseFloat(tuan_pricemin)){
        		tuan_pricemin=parseFloat(tuanPrice);
        	}
        	if(parseFloat(tuanPrice)>parseFloat(tuan_pricemax)){
        		tuan_pricemax=parseFloat(tuanPrice);
        	}
		}	
       // console.log('i='+i+'=tuanPrice='+tuanPrice);
        if(tuan_pricemin==tuan_pricemax){
        	document.getElementById("tuanprices").value=tuan_pricemin;
        }else{
        	document.getElementById("tuanprices").value=tuan_pricemin+"~"+tuan_pricemax;
        }
        var unit_pricemin=0; 
        var unit_pricemax=0; 
        for(var i=0;i<jishulist.length;i++){
        	var unitPrice=document.getElementById("nstandarddetails["+i+"].unitPrice").value;
        	if(i==0){
        		unit_pricemin=parseFloat(unitPrice);
        		unit_pricemax=parseFloat(unitPrice);
        	}
        	if(parseFloat(unitPrice)<parseFloat(unit_pricemin)){
        		unit_pricemin=parseFloat(unitPrice);
        	}
        	if(parseFloat(unitPrice)>parseFloat(unit_pricemax)){
        		unit_pricemax=parseFloat(unitPrice);
        	}
		}
        if(unit_pricemin==unit_pricemax){
        	document.getElementById("unitprices").value=unit_pricemin;
        }else{
        	document.getElementById("unitprices").value=unit_pricemin+"~"+unit_pricemax;
        }
		var member_pricemin=0;
		var member_pricemax=0;
		for(var i=0;i<jishulist.length;i++){
			var memberPrice=document.getElementById("nstandarddetails["+i+"].memberPrice").value;
			if(i==0){
				member_pricemin=parseFloat(memberPrice);
				member_pricemax=parseFloat(memberPrice);
			}
			if(parseFloat(memberPrice)<member_pricemin){
				member_pricemin=parseFloat(memberPrice);
			}
			if(parseFloat(memberPrice)>member_pricemax){
				member_pricemax=parseFloat(memberPrice);
			}
		}
		if(member_pricemin==member_pricemax){
			document.getElementById("memberprices").value=member_pricemin;
		}else{
			document.getElementById("memberprices").value=member_pricemin+"~"+member_pricemax;
		}
        var groupPurchase= document.getElementById("groupPurchase").value;
    	if(parseFloat(groupPurchase)<2){
     		//console.log("团购人数不能少于2人="+groupPurchase);
     		alert("团购人数不能少于2人");
    	    return false;
     	}
}
    function  getSumNumbers(){
    	var jishulist= document.getElementsByName("jishu");
    	var sum=0;
    	for(var i=0;i<jishulist.length;i++){
			//sumNumberlist[i].value=sum_number;
    		var sumNumber=document.getElementById("nstandarddetails["+i+"].sumNumber").value;
    	//	alert(isNumber(sumNumber));
    		if(isNumber(sumNumber))
    		   sum+=parseInt(sumNumber);
		}
    	document.getElementById("sumNumbers").value=sum;
    }
    function isNumber(value) {
        var patrn = /^(-)?\d+(\.\d+)?$/;
        if (patrn.exec(value) == null || value == "") {
            return false
        } else {
            return true
        }
    }
var standardarray;
		$(function(){
			var  starttype="${nGoodsDetaislPage.starttype}";
	  		$("#starttype").val(starttype);  
	  		if(starttype=="1"){
	  			 $("#setBounds").attr("readOnly",true); //不可编辑，可以传值;
	  			// document.getElementById("setBounds").value="1";
	  		}else{
	  			 $("#setBounds").attr("readOnly",false); //不可编辑，可以传值;
	  		}
			//省市区下拉
			/*$("#province").regionselect({
					url:'<%=basePath%>/nGoodsDetaislController.do?regionSelect'
			});*/
			///console.log('${goods}');
		   //$("#jingying").append('${goods}');
			$.ajax({
		  		async : true,
		  		cache : false,
		  		type : 'POST',
		  		datatype:"json",
		  		url : 'uplodfil.do?dictionarray',// 请求的action路径
		  		data : {typegroupcode:'standard_one'},//多条件排序sort:createDate,userNameorder:asc,desc
		  		error : function() {    // 请求失败处理函数
		  		},
		  		success : function(data) {
		  		var 	data=eval('(' + data + ')');
		  		standardarray=data.content;
		  		var  standard="${nGoodsDetaislPage.standard}";
		  		$("#standard").val(standard);  
		  		NewselectChange();
		  		}
		      });
			  if(location.href.indexOf("load=detail")!=-1){
					$("#formSubmit").hide();
					 $('#formReturn').removeAttr("disabled");
					
				}
		});
		var onenumb=0;
		var twonumb=0;
		//规格下拉框
				function	NewselectChange(){
					var selectvalue=document.getElementById("standard").value;
					if(selectvalue=="0"){
						 document.getElementById("guigeone").style.display="none";//隐藏
						 document.getElementById("guigetwo").style.display="none";//隐藏
					}else if(selectvalue=="1"){
						 document.getElementById("guigeone").style.display="block";//显示
						 document.getElementById("guigetwo").style.display="none";//隐藏
						 document.getElementById("inputone").value="";
						 document.getElementById("standardOnelist").value="";
						 $("#Onecontent").html('<li class="g-s-s-b-r-c-o-s-a-btn" id="oneadd"><a href="javascript:onebtn();">+添加</a></li>');
					}else{
						 document.getElementById("guigeone").style.display="block";//显示
						 document.getElementById("guigetwo").style.display="block";//显示
						 document.getElementById("inputtwo").value="";
						 document.getElementById("standardTwolist").value="";
						 $("#Twocontent").html('<li class="g-s-s-b-r-c-o-s-a-btn" id="twoadd"><a href="javascript:twobtn();">+添加</a></li>');
					}
					
				var standhtml='<option value="" selected="selected">请选择规格</option>';
					for ( var i = 0;i<standardarray.length;i++) {
			  			var obj = standardarray[i];
			  			//alert(obj.code+"="+obj.codename);
			  			standhtml+=' <option value="'+obj.code+'" >'+obj.codename+'</option>';
			  		}
					$("#standardOne").html(standhtml);
					$("#standardTwo").html(standhtml);
				
					 document.getElementById("guigeoneinput").style.display="none";//隐藏
					 document.getElementById("guigetwoinput").style.display="none";//隐藏
					 //选择规格
						var standard_one="${nGoodsDetaislPage.standardOne}";
						var standard_two="${nGoodsDetaislPage.standardTwo}";
						if(standard_one!=null && standard_one!="")
							$("#standardOne").val(standard_one);  
						if(standard_two!=null && standard_two!="")
							$("#standardTwo").val(standard_two);  
						//规格列表
						var standardOnelist="${nGoodsDetaislPage.standardOnelist}";
						var standardTwolist="${nGoodsDetaislPage.standardTwolist}";
						document.getElementById("standardOnelist").value=standardOnelist;
						document.getElementById("standardTwolist").value=standardTwolist;
						 var array_ones="";
						 var array_twos="";
						if(standardOnelist.length>0){
					      standardOnelist=standardOnelist.substring(2,standardOnelist.length-2);
					      array_ones=standardOnelist.split("],],"); //字符分割 
					      var oneselectvalue=$("#standardOne").find("option:selected").text();
					      $("#tableone").html(oneselectvalue);
					      document.getElementById("tableone").style.display="";//显示
						}
						if(standardTwolist.length>0){
						   standardTwolist=standardTwolist.substring(2,standardTwolist.length-2);
						   array_twos=standardTwolist.split("],],"); //字符分割 
						   var twoselectvalue=$("#standardTwo").find("option:selected").text();
						   $("#tabletwo").html(twoselectvalue);
						   document.getElementById("tabletwo").style.display="";//显示
						}
						 for (i=0;i<array_ones.length ;i++ ) 
		  				  { 
							 onenumb=i;
							 var conte='<li  class="spec-name">'
										+'<p id="onep'+onenumb+'" >' +array_ones[i]+'</p>'
										+'<span  class="g-s-s-b-r-c-o-s-a-close" style="display: inline-block;" onclick="deleteRow($(this),\'onep'+onenumb+'\',\'1\');">'
										+'</span>'
										+'</li>';
							var d1 = document.getElementById('oneadd');
							d1.insertAdjacentHTML("beforeBegin",conte);  
		  				  }
						 for (i=0;i<array_twos.length ;i++ ) 
						 { 
							 twonumb=i;
								var conte='<li  class="spec-name">'
										+'<p id="twop'+twonumb+'" >' +array_twos[i]+'</p>'
										+'<span  class="g-s-s-b-r-c-o-s-a-close" style="display: inline-block;" onclick="deleteRow($(this),\'twop'+twonumb+'\',\'2\');">'
										+'</span>'
										+'</li>';
									    // $("#Twocontent").append(conte);
								var d1 = document.getElementById('twoadd');
								d1.insertAdjacentHTML("beforeBegin",conte);  
						 }
						/* getListpage();
						if(selectvalue=="0" || selectvalue=="1" ){
						   geDuotable();
						}*/
				}
		//规格下拉框
		function	selectChange(){
			var selectvalue=document.getElementById("standard").value;
			//var selectvalue=$("#standard").find("option:selected").text(); 
			//console.log(selectvalue);
			if(selectvalue=="0"){
				 document.getElementById("guigeone").style.display="none";//隐藏
				 document.getElementById("guigetwo").style.display="none";//隐藏
				 geDantable();
			}else if(selectvalue=="1"){
				 document.getElementById("guigeone").style.display="block";//显示
				 document.getElementById("guigetwo").style.display="none";//隐藏
				 document.getElementById("inputone").value="";
				 document.getElementById("standardOnelist").value="";
				 $("#Onecontent").html('<li class="g-s-s-b-r-c-o-s-a-btn" id="oneadd"><a href="javascript:onebtn();">+添加</a></li>');
			}else{
				 document.getElementById("guigeone").style.display="block";//显示
				 document.getElementById("guigetwo").style.display="block";//显示
				 document.getElementById("inputtwo").value="";
				 document.getElementById("standardTwolist").value="";
				 $("#Twocontent").html('<li class="g-s-s-b-r-c-o-s-a-btn" id="twoadd"><a href="javascript:twobtn();">+添加</a></li>');
			}
			
		//var standhtml='<option value="" selected="selected">请选择规格</option>';
		var standhtml='<option value="" selected="selected">请选择规格</option>';
			for ( var i = 0;i<standardarray.length;i++) {
	  			var obj = standardarray[i];
	  			//alert(obj.code+"="+obj.codename);
	  			standhtml+=' <option value="'+obj.code+'" >'+obj.codename+'</option>';
	  		}
			$("#standardOne").html(standhtml);
			$("#standardTwo").html(standhtml);
		
			 document.getElementById("guigeoneinput").style.display="none";//隐藏
			 document.getElementById("guigetwoinput").style.display="none";//隐藏
			 geDantable();
			 
		}
		function getListpage(){
			var listPage="${listPage}";
			alert(listPage);
		}
		//规格1新增
		function onebtn(){
			var a=document.getElementById("standardOne").value;
			if(a!=null && a!="" ){
			 document.getElementById("guigeoneinput").style.display="block";//显示
			}else{
				alert("请选择规格1");
			}
			
		}
		//规格2新增
		function twobtn(){
			var a=document.getElementById("standardTwo").value;
			if(a!=null && a!="" ){
			 document.getElementById("guigetwoinput").style.display="block";//显示
			}else{
				alert("请选择规格2");
			}
			
		}
		//规格1确定
		function getOnebtn(){
			var  a=document.getElementById("inputone").value.replace(/(^\s*)|(\s*$)/g, "");
			var t=document.getElementById("standardOnelist").value;
			if(a.length>20){
				alert("规格字数超过20");
			}else if(a.length<1){
        		alert("规格不能为空");
			}else{
				if(isContains(t,a)){
					alert("规格名称重复");
				}else{
					document.getElementById("inputone").value="";
					document.getElementById("standardOnelist").value=t+"],"+a+"],";
					document.getElementById("guigeoneinput").style.display="none";//显示
					
					/*var conte='<span style="margin-left:10px;background-color:#428bca;padding:5px;color:white;">'
					             +a
					             +'</span>';*/
					var conte='<li  class="spec-name">'
					+'<p id="onep'+onenumb+'" >' +a+'</p>'
					+'<span  class="g-s-s-b-r-c-o-s-a-close" style="display: inline-block;" onclick="deleteRow($(this),\'onep'+onenumb+'\',\'1\');">'
					+'</span>'
					+'</li>';
				    // $("#Onecontent").append(conte);
					var d1 = document.getElementById('oneadd');
					d1.insertAdjacentHTML("beforeBegin",conte);  
				     geDuotable();
				     onenumb++;
//			         // var onenumb=0;
				}
			}
		}
		//规格2确定
        function getTwobtn(){
        	var a=document.getElementById("inputtwo").value.replace(/(^\s*)|(\s*$)/g, "");
        	var t=document.getElementById("standardTwolist").value;
        	if(a.length>20){
				alert("规格字数超过20");
        	}else if(a.length<1){
        		alert("规格不能为空");
			}else{
				if(isContains(t,a)){
					alert("规格名称重复");
				}else{
	        	document.getElementById("inputtwo").value="";
				document.getElementById("standardTwolist").value=t+"],"+a+"],";
				document.getElementById("guigetwoinput").style.display="none";//显示
				/*var conte='<span style="margin-left:10px;background-color:#428bca;padding:5px;color:white;">'
			             +a
			             +'</span>';
		          $("#Twocontent").append(conte);*/
				var conte='<li  class="spec-name">'
						+'<p id="twop'+twonumb+'" >' +a+'</p>'
						+'<span  class="g-s-s-b-r-c-o-s-a-close" style="display: inline-block;" onclick="deleteRow($(this),\'twop'+twonumb+'\',\'2\');">'
						+'</span>'
						+'</li>';
					    // $("#Twocontent").append(conte);
						var d1 = document.getElementById('twoadd');
						d1.insertAdjacentHTML("beforeBegin",conte);  
		          geDuotable();
		          twonumb++;
//		          var onenumb=0;
//		  		var twonumb=0;
				}
			}
		}
        //删除规格
        function deleteRow(obj,id,type) {
            //obj.parents(".rep").remove();
        	//document.getElementsByTagName("p")[0].innerHTML 
        	var t=document.getElementById(id).innerText;
        	var two=document.getElementById("standardTwolist").value;
        	var one=document.getElementById("standardOnelist").value;
        	obj.parent().remove();
        	console.log("t="+t);
        	if(type=="1"){
        		document.getElementById("standardOnelist").value=one.replace("],"+t+"],","");
        	}else{
        		document.getElementById("standardTwolist").value=two.replace("],"+t+"],","");
        	}
        	//var a = str.replace(reg,"");
        	   geDuotable();
        }
        //是否包含某个字符串
        function isContains(str, substr) {
          //  return str.contains(substr);
            return str.indexOf("],"+substr+"],") >= 0;
        }
    	//规格1监听
		function geOne(){
			document.getElementById("standardOnelist").value="";
			document.getElementById("guigeoneinput").style.display="none";//显示
			 onenumb=0;
			
			 $("#Onecontent").html('<li class="g-s-s-b-r-c-o-s-a-btn" id="oneadd"><a href="javascript:onebtn();">+添加</a></li>');
		}
		//规格2监听
		function geTwo(){
			document.getElementById("standardTwolist").value="";
			document.getElementById("guigetwoinput").style.display="none";//显示
			   $("#Twocontent").html('<li class="g-s-s-b-r-c-o-s-a-btn" id="twoadd"><a href="javascript:twobtn();">+添加</a></li>');
			twonumb=0;
		}
		var detials="";
		var htmcontent='';
		//多规格商品
		function geDuotable(){
		var a=$("#standardOne").find("option:selected").text(); 
		var b=$("#standardTwo").find("option:selected").text(); 
		var standardOnelist=document.getElementById("standardOnelist").value; 
		var standardTwolist=document.getElementById("standardTwolist").value; 
		// console.log(standardOnelist.length+"=2="+standardTwolist.length);
		 var array_ones="";
		 var array_twos="";
		if(standardOnelist.length>0){
	      standardOnelist=standardOnelist.substring(2,standardOnelist.length-2);
	      array_ones=standardOnelist.split("],],"); //字符分割 
		}
		if(standardTwolist.length>0){
		   standardTwolist=standardTwolist.substring(2,standardTwolist.length-2);
		   array_twos=standardTwolist.split("],],"); //字符分割 
		}
		 // console.log(standardOnelist+"=1="+standardTwolist);
			 var tabletitle='';
			 if(a!=null && a!="" && a!="请选择规格"){
			   tabletitle+='<th>'+a+'</th>';
			 }
			 if(b!=null && b!="" && b!="请选择规格"){
			 tabletitle+='<th>'+b+'</th>';
			 }
            tabletitle+='<th>库存增减</th>'
                +'<th>团购价</th>'
                +'<th>单买价</th>'
                +'<th>会员价</th>'
                +'<th>商家编码</th>'
                +'<th>SKU预览图</th>'
                +'<th>状态</th>'
                +'<th>操作</th>';
			  $("#tabletitle").html(tabletitle);
			  console.log(array_ones.length+"=="+ array_twos.length);
			  htmcontent='';
			  if(array_ones.length >0 && array_twos.length>0){
				  var k=0;
				
                	 for (i=0;i<array_ones.length ;i++ ) 
	   				  { 
	                		 for (j=0;j<array_twos.length ;j++ ) 
		   	   				  { 
	                			 k++;
	                			 var n=k-1;
	                			  htmcontent+='<tr >';
	            				  htmcontent+='<td>'+array_ones[i]+'</td>'
	            						  +'<input id="nstandarddetails['+n+'].standardOnename" name="nstandarddetails['+n+'].standardOnename" type="hidden" value="'+array_ones[i]+'">'
	            						  +'<input id="nstandarddetails['+n+'].standardTwoname" name="nstandarddetails['+n+'].standardTwoname" type="hidden" value="'+array_twos[j]+'">'
	            						  +'<td>'+array_twos[j]+'</td>'
	            						  +'<td><input type="text" onchange="Javascript:getSumNumbers();"  id="nstandarddetails['+n+'].sumNumber" name="nstandarddetails['+n+'].sumNumber" value="0" datatype="n" class="pdd-form-input" style="width: 75px;"><span class="Validform_checktip"></span></td>'
	            						  +'<td><input type="text"   id="nstandarddetails['+n+'].tuanPrice" name="nstandarddetails['+n+'].tuanPrice" value="0"  datatype="ns2" class="pdd-form-input" style="width: 75px;"><span class="Validform_checktip"></span></td>'
	            						  +'<td><input type="text"   id="nstandarddetails['+n+'].unitPrice" name="nstandarddetails['+n+'].unitPrice" value="0"  datatype="ns2" class="pdd-form-input" style="width: 75px;"><span class="Validform_checktip"></span></td>'
                                          +'<td><input type="text"   id="nstandarddetails['+n+'].memberPrice" name="nstandarddetails['+n+'].memberPrice" value="0"  datatype="ns2" class="pdd-form-input" style="width: 75px;"><span class="Validform_checktip"></span></td>'
	            						  +'<td><input type="text"   id="nstandarddetails['+n+'].goodsCode" name="nstandarddetails['+n+'].goodsCode" value="0"  datatype="*0-36" class="pdd-form-input" style="width: 75px;"><span class="Validform_checktip"></span></td>'
	            						  +'<td style="width:100px;">'
	          							    +'<div class="hidden">'
	            				               +'<input id="nstandarddetails['+n+'].picUrl" name="nstandarddetails['+n+'].picUrl" type="hidden" value="">'
	          							       +'<input id="fileimg'+k+'" type="file" onchange="Javascript:commonChange1(\'30\',\'30000\',\'30000\','+k+',\'fileimg'+k+'\',\'nstandarddetails['+n+'].picUrl\',\'pic_urlimg'+k+'\',\'skuimgclose'+k+'\');" style="display: none;">'
	          							    +'</div>'
	          							    +'<div class="goods-sku-s-b-r-c-img" style="display: inline-block;">'
		          							     +'<a href="javascript:filebtn(\'fileimg'+k+'\');" style="cursor: pointer; display: inline-block; width: 40px; height: 40px;">'
				          							 +'<img id="pic_urlimg'+k+'" src="plug-in/ui/images/shangchuan.png" width="40" height="40">'
					  							 +'</a>'
					  							 +'<span  id="skuimgclose'+k+'" onclick="del_pic(\'skuimgclose'+k+'\',\'pic_urlimg'+k+'\',\'nstandarddetails['+n+'].picUrl\','+k+');" class="goods-sku-s-b-r-c-img-close"  style="display:none;" />'
		          						    +'</div>'
		          						  +'</td>'
		          						   +'<input id="jishu" name="jishu" type="hidden" />'
          							      +'<input id="nstandarddetails['+n+'].shangxiatype" name="nstandarddetails['+n+'].shangxiatype" type="hidden" value="0" />'
									      +'<td><div  id="shangxiatypename'+k+'"> 已上架</div></td>'
									      +'<td><div  id="shangxia'+k+'" onclick="Shangbtn(\'nstandarddetails['+n+'].shangxiatype\',\'shangxiatypename'+k+'\',\'shangxia'+k+'\');" data-text="下架" class="shang">下架</div></td>'
//	            						  +'<td>已上架</td>'
//	            						  +'<td><button type="button" id="guigetwobtn" class="btn btn-default" data-dismiss="modal" style="background-color:red;" onclick="Listbtn();">确定</button></td>'
	            				          +'</tr>';
	            				
		   	   				  } 
	   				  }
                	  $("#htmcontent").html(htmcontent);
				 
			  }else  if(array_ones.length >0) {
				  geDetails(array_ones,1);
			  }else  if(array_twos.length >0) {
				  geDetails(array_twos,2);
			  }else{
				  $("#htmcontent").html(""); 
			  }
			  getSumNumbers();
		}
		function geDetails(str,type){
			htmcontent='';
			  var k=0;
			 for (i=0;i<str.length ;i++ ){ 
				 k++;
				 var n=k-1;
				  htmcontent+='<tr >	';
				  if(type==1){
				    htmcontent+='<input id="nstandarddetails['+n+'].standardOnename" name="nstandarddetails['+n+'].standardOnename" type="hidden" value="'+str[i]+'">';
				  }else{
					  htmcontent+='<input id="nstandarddetails['+n+'].standardTwoname" name="nstandarddetails['+n+'].standardTwoname" type="hidden" value="'+str[i]+'">'; 
				  }
				  htmcontent+='<td>'+str[i]+'</td>'
						  +'<td><input type="text" onchange="Javascript:getSumNumbers();"  id="nstandarddetails['+n+'].sumNumber" name="nstandarddetails['+n+'].sumNumber" value="0"  datatype="n"class="pdd-form-input" style="width: 75px;"><span class="Validform_checktip"></span></td>'
						  +'<td><input type="text"   id="nstandarddetails['+n+'].tuanPrice" name="nstandarddetails['+n+'].tuanPrice" value="0"  datatype="ns2" class="pdd-form-input" style="width: 75px;"><span class="Validform_checktip"></span></td>'
						  +'<td><input type="text"   id="nstandarddetails['+n+'].unitPrice" name="nstandarddetails['+n+'].unitPrice" value="0"  datatype="ns2" class="pdd-form-input" style="width: 75px;"><span class="Validform_checktip"></span></td>'
						  +'<td><input type="text"   id="nstandarddetails['+n+'].memberPrice" name="nstandarddetails['+n+'].memberPrice" value="0"  datatype="ns2" class="pdd-form-input" style="width: 75px;"><span class="Validform_checktip"></span></td>'
						  +'<td><input type="text"   id="nstandarddetails['+n+'].goodsCode" name="nstandarddetails['+n+'].goodsCode" value="0"  datatype="*0-36" class="pdd-form-input" style="width: 75px;"><span class="Validform_checktip"></span></td>'
                         +'<td style="width:100px;">'
						    +'<div class="hidden">'
				               +'<input id="nstandarddetails['+n+'].picUrl" name="nstandarddetails['+n+'].picUrl" type="hidden" value="">'
						       +'<input id="fileimg'+k+'" type="file" onchange="Javascript:commonChange1(\'30\',\'30000\',\'30000\','+k+',\'fileimg'+k+'\',\'nstandarddetails['+n+'].picUrl\',\'pic_urlimg'+k+'\',\'skuimgclose'+k+'\');" style="display: none;">'
						    +'</div>'
						    +'<div class="goods-sku-s-b-r-c-img" style="display: inline-block;">'
  							     +'<a href="javascript:filebtn(\'fileimg'+k+'\');" style="cursor: pointer; display: inline-block; width: 40px; height: 40px;">'
          							 +'<img id="pic_urlimg'+k+'" src="plug-in/ui/images/shangchuan.png" width="40" height="40">'
	  							 +'</a>'
	  							 +'<span  id="skuimgclose'+k+'" onclick="del_pic(\'skuimgclose'+k+'\',\'pic_urlimg'+k+'\',\'nstandarddetails['+n+'].picUrl\','+k+');" class="goods-sku-s-b-r-c-img-close"  style="display:none;" />'
  						    +'</div>'
  						  +'</td>'
  						   +'<input id="jishu" name="jishu" type="hidden" />'
  						   +'<input id="nstandarddetails['+n+'].shangxiatype" name="nstandarddetails['+n+'].shangxiatype" type="hidden" value="0" />'
					      +'<td><div  id="shangxiatypename'+k+'"> 已上架</div></td>'
					      +'<td><div  id="shangxia'+k+'" onclick="Shangbtn(\'nstandarddetails['+n+'].shangxiatype\',\'shangxiatypename'+k+'\',\'shangxia'+k+'\');" data-text="下架" class="shang">下架</div></td>'
				          +'</tr>';
				 
			 }
			 $("#htmcontent").html(htmcontent);
		}
		
		//单商品
		function geDantable(){
			  var k=1;
			 var tabletitle='';
			 tabletitle+='<th>库存增减</th>'
				       +'<th>团购价</th>'
				       +'<th>单买价</th>'
                 	   +'<th>会员价格</th>'
				       +'<th>商家编码</th>'
				       +'<th>SKU预览图</th>'
				       +'<th>状态</th>'
				       +'<th>操作</th>';
			  $("#tabletitle").html(tabletitle);
			  var n=0;
			  htmcontent='';
			  htmcontent+='<tr >	'
					  +'<td><input type="text" onchange="Javascript:getSumNumbers();"  id="nstandarddetails['+n+'].sumNumber" name="nstandarddetails['+n+'].sumNumber" value="0"  datatype="n" class="pdd-form-input" style="width: 75px;"><span class="Validform_checktip"></span></td>'
					  +'<td><input type="text"   id="nstandarddetails['+n+'].tuanPrice" name="nstandarddetails['+n+'].tuanPrice" value="0"  datatype="ns2" class="pdd-form-input" style="width: 75px;"><span class="Validform_checktip"></span></td>'
					  +'<td><input type="text"   id="nstandarddetails['+n+'].unitPrice" name="nstandarddetails['+n+'].unitPrice" value="0"  datatype="ns2" class="pdd-form-input" style="width: 75px;"><span class="Validform_checktip"></span></td>'
                      +'<td><input type="text"   id="nstandarddetails['+n+'].memberPrice" name="nstandarddetails['+n+'].memberPrice" value="0"  datatype="ns2" class="pdd-form-input" style="width: 75px;"><span class="Validform_checktip"></span></td>'
					  +'<td><input type="text"   id="nstandarddetails['+n+'].goodsCode" name="nstandarddetails['+n+'].goodsCode" value="0"  datatype="*0-36" class="pdd-form-input" style="width: 75px;"><span class="Validform_checktip"></span></td>'
 	            	  +'<td style="width:100px;">'
					    +'<div class="hidden">'
			               +'<input id="nstandarddetails['+n+'].picUrl" name="nstandarddetails[0].picUrl" type="hidden" value="">'
					       +'<input id="fileimg'+k+'" type="file" onchange="Javascript:commonChange1(\'30\',\'30000\',\'30000\','+k+',\'fileimg'+k+'\',\'nstandarddetails['+n+'].picUrl\',\'pic_urlimg'+k+'\',\'skuimgclose'+k+'\');" style="display: none;">'
					    +'</div>'
					    +'<div class="goods-sku-s-b-r-c-img" style="display: inline-block;">'
						     +'<a href="javascript:filebtn(\'fileimg'+k+'\');" style="cursor: pointer; display: inline-block; width: 40px; height: 40px;">'
      							 +'<img id="pic_urlimg'+k+'" src="plug-in/ui/images/shangchuan.png" width="40" height="40">'
  							 +'</a>'
  							 +'<span  id="skuimgclose'+k+'" onclick="del_pic(\'skuimgclose'+k+'\',\'pic_urlimg'+k+'\',\'nstandarddetails['+n+'].picUrl\','+k+');" class="goods-sku-s-b-r-c-img-close"  style="display:none;" />'
					    +'</div>'
					  +'</td>'
					  +'<input id="jishu" name="jishu" type="hidden" />'
					  +'<input id="nstandarddetails['+n+'].shangxiatype" name="nstandarddetails['+n+'].shangxiatype" type="hidden" value="0" />'
				      +'<td><div  id="shangxiatypename'+k+'"> 已上架</div></td>'
				      +'<td><div  id="shangxia'+k+'" onclick="Shangbtn(\'nstandarddetails['+n+'].shangxiatype\',\'shangxiatypename'+k+'\',\'shangxia'+k+'\');" data-text="下架" class="shang">下架</div></td>'
			          +'</tr>';
			  $("#htmcontent").html(htmcontent);
		}
		//批量设置商品库存，团购价，单价
		function Listbtn(){
			var jishulist= document.getElementsByName("jishu");
			/*var sumNumberlist= document.getElementsByName("sumNumber");
			var tuanPricelist= document.getElementsByName("tuanPrice");
			var unitPricelist= document.getElementsByName("unitPrice");*/
			var sum_number= document.getElementById("sum_number").value;
			var tuan_price= document.getElementById("tuan_price").value;
			var unit_price= document.getElementById("unit_price").value;
			if(sum_number!=null && sum_number!=""){
				for(var i=0;i<jishulist.length;i++){
					//sumNumberlist[i].value=sum_number;
					document.getElementById("nstandarddetails["+i+"].sumNumber").value=sum_number;
				}
				getSumNumbers();
			}
			if(tuan_price!=null && tuan_price!=""){
                for(var i=0;i<jishulist.length;i++){
                	//tuanPricelist[i].value=tuan_price;
                	document.getElementById("nstandarddetails["+i+"].tuanPrice").value=tuan_price;
				}		
			}
			if(unit_price!=null && unit_price!=""){
                for(var i=0;i<jishulist.length;i++){
                	//unitPricelist[i].value=unit_price;
                	document.getElementById("nstandarddetails["+i+"].unitPrice").value=unit_price;
				}	
			}
		}
		//上下属性
		function Shangbtn(index,shangxiatypename,shangxia){
			//alert("1");
			//0已上架1已下架2售罄
		//	var shangxiatype= document.getElementById("shangxiatype").value;
			//var aryya=  document.getElementsByName("shangxiatype");
			// console.log("index="+index);
			//index--;
			// console.log("index1="+index);
			var t=document.getElementById(index).value;
			if(t=="0"){
				document.getElementById(shangxiatypename).innerHTML="已下架";
				//document.getElementById("shangxiatype").value='1';
				//aryya[index].value='1';
				document.getElementById(index).value='1';
				document.getElementById(shangxia).innerHTML="上架";
				document.getElementById(shangxia).setAttribute("class","xia");
			}
			else{
				document.getElementById(shangxiatypename).innerHTML="已上架";
				//document.getElementById("shangxiatype").value='0';
				document.getElementById(index).value='0';
				document.getElementById(shangxia).innerHTML="下架";
				document.getElementById(shangxia).setAttribute("class","shang");
			}
			
		}
		function filebtn(id){
			
			var fileimg = document.getElementById(id); 
			fileimg.click(); 
		}
</script>