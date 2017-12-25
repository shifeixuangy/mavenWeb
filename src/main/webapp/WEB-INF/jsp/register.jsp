<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/11/24
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
    <title>注册页面</title>
    <link rel="stylesheet" href="${ctx}/static/css/bootstrap-3.3.5.min.css"/>
    <script type="text/javascript" src="${ctx}/static/js/jquery-1.11.0.min.js"></script>
    <style type="text/css">
        .box1 {
            background: url(${ctx}/static/images/pikaqiu.jpg) no-repeat top center
        }

        .box2 {
            width: 367px;
            height: 410px;
            margin-top: 160px;
            border: 2px solid;
            border-radius: 25px;
            padding: 46px;
            margin-left: 42%;
        }

        .center1 {
            margin-left: 35.666667%;
        }
    </style>
</head>
<body class="container-fluid box1">

<div class="box2">
    <form class=" form-horizontal" id="registerUser">
        <div class="form-group">
            <label for="loginName" style="color: black">用户名</label>
            <input type="text" name="loginName" class="form-control" id="loginName" placeholder="0-4个字符">
        </div>
        <div class="form-group">
            <label for="password" style="color: black">密码</label>
            <input type="text" name="password" class="form-control" id="password" placeholder="数字、字母、下划线">
        </div>
        <div class="form-group">
            <label for="sex" style="color: black">性别</label>
            <input type="text" name="sex" class="form-control" id="sex" placeholder="填入数字">
        </div>
        <div class="form-group">
            <label for="age" style="color: black">年龄</label>
            <input type="text" name="age" class="form-control" id="age" placeholder="0-99岁">
        </div>

        <div class="form-group">
            <div class="center1">
                <button type="button" onclick="register()" style="color: green" class="btn btn-default">注册</button>
            </div>
        </div>
    </form>
</div>
</body>
<script>

    function register() {
        $.ajax({
            url: '/user/register',
            type: 'POST', //GET
            async: false,    //或false,是否异步
            data: $("#registerUser").serializeArray(),
            timeout: 5000,    //超时时间
            dataType: 'text',    //返回的数据格式：json/xml/html/script/jsonp/text
            beforeSend: function (xhr) {
                console.log(xhr)
                console.log('发送前')
            },
            success: function (data, jqXHR) {
                console.log(data)
                console.log(jqXHR);
                alert("注册成功");
                window.location.href ='http://localhost:8080/user/loginView';
            },
            error: function (data, xhr, textStatus) {
                console.log(data);
                console.log('错误');
                window.location.href ='http://localhost:8080/user/registerView';
            },
            complete: function () {
                console.log('结束')
            }
        })
    }


</script>
</html>
