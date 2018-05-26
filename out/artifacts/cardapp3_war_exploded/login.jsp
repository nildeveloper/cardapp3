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
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/signin.css" rel="stylesheet" >
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <title>登录</title>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <h1>网上名片管理系统</h1>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12" style="height:400px;">
            <form class="form-signin" method="post" action="userlogin">
                <h2 class="form-signin-heading" align="center">登录</h2>
                <label for="username" class="sr-only">用户名</label>
                <input type="text" id="username" name="username" class="form-control" placeholder="请输入用户名" required autofocus>
                <span class="help-block" style="color: red;">${requestScope.message}</span>
                <label for="password" class="sr-only">Password</label>
                <input type="password" id="password" name="password" class="form-control" placeholder="请输入密码" required>
                <div class="checkbox">
                    <label>
                        <input type="checkbox" value="true" name="rememberMe">记住密码</label>
                    </label>
                    <a href="" data-toggle="modal" data-target="#registerModal" style="float: right">立即注册</a>
                </div>
                <button class="btn btn-lg btn-primary btn-block" id="sub_btn" type="submit">登录</button>
            </form>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="registerModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">用户注册</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="userregister" method="post" id="register_form">
                    <div class="form-group">
                        <label for="username1" class="col-sm-2 control-label">用户名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="username1" name="username" placeholder="请输入用户名" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password1" class="col-sm-2 control-label">密码</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="password1" name="password"  placeholder="请输入密码" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password2" class="col-sm-2 control-label">确认密码</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="password2" placeholder="请输入再次密码" required>
                        </div>
                    </div>
                    <input type="hidden" name="authrity" value="2">
                    <span class="help-block" style="color: red;">${requestScope.message}</span>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="tijiao">提交</button>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    $(function () {
        $("#tijiao").click(function () {
            var password1 = $("#password1").text();
            var password2 = $("#password2").text();
            if (password1 != password2) {
                alert("两次密码输入不一致！");
                return false;
            } else {
                $("#register_form").submit();
            }
        });
    });
    
</script>
</html>
