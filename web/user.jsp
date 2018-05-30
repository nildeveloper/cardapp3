<%--
  Created by IntelliJ IDEA.
  User: null
  Date: 2018/5/15
  Time: 22:00
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <title>用户管理</title>
</head>
<body>
<div class="container-fluid">
    <!-- 标题行 -->
    <div class="row">
        <div class="col-md-10 col-md-offset-2">
            <h2>用户管理</h2>
        </div>
    </div>
    <!-- 表格 -->
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <table class="table table-hover" id="user_table">
                <thead>
                <tr>
                    <th>编号</th>
                    <th>姓名</th>
                    <th>密码</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.users}" var="user">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.password}</td>
                        <td>
                            <button class="btn btn-info btn-sm reset_btn" id="${user.id}">
                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>重置
                            </button>
                            <button class="btn btn-danger btn-sm del_btn1"  id="${user.id}">
                                <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>删除
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script>
    // 回收站内名片删除
    $(document).on("click",".del_btn1",function () {
        var id = $(this).attr('id');
        $.ajax({
            url:"deleteUser",
            type:"POST",
            data:"id=" + id,
            dataType:"text",
            success:function (result) {
                alert(result);
                window.location.href="listUser";
            }
        });
    });
    
    // 用户密码重置
    $(document).on("click",".reset_btn",function () {
        var id = $(this).attr('id');
        $.ajax({
            url:"resetUser",
            type:"POST",
            data:"id=" + id,
            dataType:"text",
            success:function (result) {
                alert(result);
                window.location.href="listUser";
            }
        });
    });
    
</script>
</body>
</html>
