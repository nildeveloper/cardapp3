<%--
  Created by IntelliJ IDEA.
  User: null
  Date: 2018/5/5
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="css/main.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/main.js"></script>
    <title>欢迎</title>
</head>
<body>
<%--导航条--%>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" id="title" href="frame">网上名片管理系统</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right" id="test">
                <li id="li1"class="nav0 nav1" title="list"><a href="#" id="list_page">名片管理</a></li>
                <li id="li2" class="nav1" title="listTrash"><a href="#" id="trash_page">回收站</a></li>
                <li>
                    <a href="#" style="font-size: 12px; color: white" class="disable">
                        欢迎<font color="red" size="2px">${sessionScope.username}</font>登录
                    </a>
                </li>
                <li><a  href="login.jsp" style="font-size: 12px; color: white" >退出</a></li>
            </ul>
        </div>
    </div>
</nav>

<%--页面主体--%>
<div class="row" id="main">
    <div class="col-md-12">
        <iframe scrolling="no" frameborder="0" id="main_frame"></iframe>
    </div>
</div>

<%--页脚--%>
<div class="row" id="bottom">
    <div class="col-md-12">
        <div align="center"><a href="http://www.nildeveloper.cn">Esay Blog</a> Copyright@2018
            <br> lhl 版权所有
        </div>
    </div>
</div>
</body>
</html>
