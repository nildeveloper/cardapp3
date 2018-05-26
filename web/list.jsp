<%--
  Created by IntelliJ IDEA.
  User: null
  Date: 2018/5/11
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <title>列表</title>
</head>
<body>
<%--card编辑模态框--%>
<div class="modal fade" id="cardUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel1">卡片编辑</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="updateCard" method="post" id="update_form">
                    <input type="hidden" name="id" id="id_update_input">
                    <input type="hidden" name="isDelete" id="isdelete_update_input">
                    <div class="form-group">
                        <label for="name_update_input" class="col-sm-2 control-label">姓名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="name" id="name_update_input"  placeholder="请输入姓名">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="tel_update_input" class="col-sm-2 control-label">电话</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="tel" id="tel_update_input" placeholder="18800000000">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="address_update_input" class="col-sm-2 control-label">地址</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="address" id="address_update_input" placeholder="xx省xx市">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email_update_input" class="col-sm-2 control-label">邮箱</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="email" id="email_update_input" placeholder="example@163.com">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-10">
                            <input type="hidden" name="userId" value="<%=session.getAttribute("userId")%>">
                            <span class="help-block" style="color: red;">${requestScope.message}</span>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="card_update_btn">提交</button>
            </div>
        </div>
    </div>
</div>

<%--card添加模态框--%>
<div class="modal fade" id="cardAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">卡片添加</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="addCard" method="post" id="add_form">
                    <div class="form-group">
                        <label for="name_add_input" class="col-sm-2 control-label">姓名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="name" id="name_add_input" placeholder="请输入姓名">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="tel_add_input" class="col-sm-2 control-label">电话</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="tel" id="tel_add_input" placeholder="18800000000">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="address_add_input" class="col-sm-2 control-label">地址</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="address" id="address_add_input" placeholder="xx省xx市">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email_add_input" class="col-sm-2 control-label">邮箱</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="email" id="email_add_input" placeholder="example@163.com">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-10">
                            <input type="hidden" name="userId" value="<%=session.getAttribute("userId")%>">
                            <span class="help-block" style="color: red;">${requestScope.message}</span>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="card_save_btn">保存</button>
            </div>
        </div>
    </div>
</div>

<div class="container-fluid">
    <!-- 标题行 -->
    <div class="row">
        <div class="col-md-10 col-md-offset-2">
            <h2>卡片管理</h2>
        </div>
    </div>
    <!-- 筛选行 -->
    <div class="row">
        <form class="form-inline" action="selectCard" method="post" id="select_form">
            <div class="col-md-4 col-md-offset-2">
                <div class="form-group">
                    <label for="name">姓名</label>
                    <input type="text" class="form-control" name="name" id="name" placeholder="xxx">
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label for="tel">电话</label>
                    <input type="tel" class="form-control" name="tel" id="tel" placeholder="188xxxxxxxx">
                </div>
            </div>
            <div class="col-md-4 col-md-offset-2">
                <div class="form-group">
                    <label for="address">地址</label>
                    <input type="text" class="form-control" id="address" name="address" placeholder="山东省泰安市">
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label for="email">邮箱</label>
                    <input type="text" class="form-control" id="email" name="email" placeholder="xxxx@163.com">
                </div>
            </div>

        </form>
    </div>
    <!-- 按钮行 -->
    <div class="row">
        <div class="col-md-4 col-md-offset-8">
            <button type="submit" class="btn btn-default" id="find_card_btn">查询</button>
            <button type="button" class="btn btn-primary" id="add_card_btn" data-toggle="modal" data-target="#cardAddModal">新增</button>
            <button type="button" class="btn btn-danger" id="delete_card_btn">删除</button>
        </div>
    </div>
    <!-- 表格 -->
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <table class="table table-hover" id="card_table">
                <thead>
                <tr>
                    <th><input type="checkbox" id="checkall"></th>
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
                    <td><input type='checkbox' class='check_item' value="${card.id}"></td>
                    <td>${card.name}</td>
                    <td>${card.tel}</td>
                    <td>${card.address}</td>
                    <td>${card.email}</td>
                    <td>
                        <button class="btn btn-info btn-sm edit_btn" id="${card.id}">
                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>编辑
                        </button>
                        <button class="btn btn-danger btn-sm del_btn"  id="${card.id}">
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
</body>
<script>
    // 名片添加
    $("#card_save_btn").click(function () {
        $("#add_form").submit();
//        window.location.href="list";
    });
    
    // 名片编辑
    $("#card_update_btn").click(function () {
        $("#update_form").submit();
    });
    
    // 编辑名片时首先查出原数据  ajax 异步
    $(document).on("click",".edit_btn",function () {
        var id = $(this).attr('id');
        $.ajax({
            url:"findCard", 
            type:"POST",
            data:"id=" + id,
            dataType:"json",
            success:function (result) {
//                alert(result.toString());
                // {"address":"山东枣庄","idDelete":0,"name":"汪呜","tel":"18812341234","id":8,"userId":1,"email":"1185578056@qq.com"}
                $("#id_update_input").attr("value",result.id);
                $("#isdelete_update_input").attr("value",result.isDelete);
                $("#name_update_input").attr("value",result.name);
                $("#tel_update_input").attr("value",result.tel);
                $("#address_update_input").attr("value",result.address);
                $("#email_update_input").attr("value",result.email);
                $('#cardUpdateModal').modal({
                    backdrop: "static"
                })
            }
        });
    });
    
    // 名片放入回收站
    $(document).on("click",".del_btn",function () {
        var id = $(this).attr('id');
        $.ajax({
            url:"deleteCard",
            type:"POST",
            data:"id=" + id,
            dataType:"text",
            success:function (result) {
                alert(result);
                window.location.href="list";
            }
        });
    });

    // 模糊查询
    $("#find_card_btn").click(function () {
        $("#select_form").submit();
    });

    
    // 全选/全不选
    $("#checkall").click(function() {
        // prop:  获取dom元素原生属性
        $(".check_item").prop("checked", $(this).prop("checked"));
    });
    $(document).on("click", ".check_item", function () {
        var flag = $(".check_item:checked").length==$(".check_item").length;  // 页面数据是否全选
        $("#checkall").prop("checked",flag);
    });
    
    // 将勾选选项放入回收站
    $("#delete_card_btn").click(function () {
        var idstr = "";
        $.each($(".check_item:checked"),function () {
            //组装卡片id字符串
            idstr += $(this).val()+"-";
        });
        if (confirm("确定删除？")) {
            // 批量删除
            $.ajax({
                url: "deleteCard",
                type: "POST",
                data: "id=" + idstr,
                dataType: "text",
                success: function (result) {
                    alert(result);
                    window.location.href = "list";
                }
            });
        }
    });
</script>
</html>
