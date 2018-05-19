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
    <title>回收站</title>
</head>
<body>
<div class="container-fluid">
    <!-- 标题行 -->
    <div class="row">
        <div class="col-md-10 col-md-offset-2">
            <h2>回收站管理</h2>
        </div>
    </div>
    <!-- 筛选行 -->
    <div class="row">
        <form class="form-inline" action="selectTrash" method="post" id="select_trash">
            <div class="col-md-4 col-md-offset-2">
                <div class="form-group">
                    <label for="name">姓名</label>
                    <input type="text" class="form-control" id="name" placeholder="xxx">
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label for="tel">电话</label>
                    <input type="tel" class="form-control" id="tel" placeholder="188xxxxxxxx">
                </div>
            </div>
            <div class="col-md-4 col-md-offset-2">
                <div class="form-group">
                    <label for="address">地址</label>
                    <input type="text" class="form-control" id="address" placeholder="山东省泰安市">
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label for="email">邮箱</label>
                    <input type="email" class="form-control" id="email" placeholder="xxxx@163.com">
                </div>
            </div>

        </form>
    </div>

    <div class="row">
        <div class="col-md-4 col-md-offset-8">
            <button type="submit" class="btn btn-default" id="find_card_btn">查询</button>
            <button type="button" class="btn btn-danger" id="delete_card_btn">删除</button>
        </div>
    </div>
    <!-- 表格 -->
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <table class="table table-hover" id="card_table">
                <thead>
                <tr>
                    <th>姓名</th>
                    <th>电话</th>
                    <th>地址</th>
                    <th>邮箱</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.cards}" var="card">
                    <tr>
                        <td>${card.name}</td>
                        <td>${card.tel}</td>
                        <td>${card.address}</td>
                        <td>${card.email}</td>
                        <td>
                            <button class="btn btn-info btn-sm rec_btn" id="${card.id}">
                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>恢复
                            </button>
                            <button class="btn btn-danger btn-sm del_btn1"  id="${card.id}">
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
    // 名片回收站恢复
    $(document).on("click",".rec_btn",function () {
        var id = $(this).attr('id');
        $.ajax({
            url:"recoverCard",
            type:"POST",
            data:"id=" + id,
            dataType:"text",
            success:function (result) {
                alert(result);
                window.location.href="listTrash";
            }
        });
    });

    // 回收站内名片删除
    $(document).on("click",".del_btn1",function () {
        var id = $(this).attr('id');
        $.ajax({
            url:"deleteTrash",
            type:"POST",
            data:"id=" + id,
            dataType:"text",
            success:function (result) {
                alert(result);
                window.location.href="listTrash";
            }
        });
    });

    // 回收站名片模糊查询S
    $("#card_find_btn").click(function () {
        $("#select_trash").submit();
    });
</script>
</body>
</html>
