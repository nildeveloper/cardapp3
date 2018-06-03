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
                <li id="li1"class="nav0 nav1" title="list"><a href="#" id="list_page"><span class="glyphicon glyphicon-envelope"></span>&nbsp;&nbsp;名片管理</a></li>
                <li id="li2" class="nav1" title="listTrash"><a href="#" id="trash_page"><span class="glyphicon glyphicon-trash "></span>&nbsp;&nbsp;回收站</a></li>
                <li id="li3" class="nav2"><a href="#" id="edit_pwd"><span class="glyphicon glyphicon-pencil "></span>&nbsp;&nbsp;修改密码</a></li>
                <li class="active">
                    <a href="#" class="disable">
                        欢迎<font color="red">${sessionScope.username}</font>登录
                    </a>
                </li>
                <li><a href="logout"><span class="glyphicon glyphicon-off"></span>&nbsp;&nbsp;注销</a></li>
                <%--<li><a  href="login.jsp" style="font-size: 12px; color: white" >退出</a></li>--%>
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
        <div align="center"><a href="http://www.nildeveloper.cn">Easy Blog</a> Copyright@2018
            <br> lhl 版权所有
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="editPwdModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">密码修改</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="editPwd" method="post" id="register_form">
                    <div class="form-group">
                        <label for="password2" class="col-sm-2 control-label">新密码</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="password2" name="password2"  placeholder="请输入新密码" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password3" class="col-sm-2 control-label">确认密码</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="password3" placeholder="请再次输入新密码" required>
                        </div>
                    </div>
                    <span class="help-block" id="msg" style="color: red;">${requestScope.message}</span>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="submit">提交</button>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    // 弹出修改密码模态框
    $(".nav2").click(function () {
        $('#editPwdModal').modal({
            backdrop: "static"
        })
        
    });

    // 点击修改密码提交按钮发送ajax请求修改密码
    $(document).on("click","#submit",function () {
        var password2 = $("#password2").val()
        var password3 = $("#password3").val()
        if (password2 != password3) {
            alert("两次密码输入不一致！");
            return false;
        } else {
            $.ajax({
                url:"editPwd",
                type:"POST",
                data:"password2=" + password2,
                dataType:"text",
                success:function (result) {
                    $("#msg").text(result);
                }
            });
        }
    });
</script>
</html>
