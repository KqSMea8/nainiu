<%--
  Created by IntelliJ IDEA.
  User: shishanshan
  Date: 2018/9/27
  Time: 12:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>Title</title>
    <link type="text/css" rel="StyleSheet" href="plug-in/dtree/dtree.css">
    <script type="text/javascript" src="plug-in/dtree/dtree.js"></script>
    <script type="text/javascript" src="plug-in/jquery/jquery-1.8.3.min.js"></script>
</head>
<body>
<div style="margin-top: 20px">
					<span>
						<a href="javascript: d.openAll();">全部展开<a/>
						&nbsp;&nbsp;|&nbsp;&nbsp;
						<a href="javascript: d.closeAll();">全部折叠</a>
					</span>
</div>
${dtreeJson}
</body>
</html>
