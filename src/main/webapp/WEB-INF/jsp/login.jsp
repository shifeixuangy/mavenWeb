<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/11/24
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
    <title>用户登录</title>
    <link rel="stylesheet" href="${ctx}/static/css/bootstrap-3.3.5.min.css"/>
    <style type="text/css">
        .box1 {
            background: url(${ctx}/static/images/ballz.jpg) no-repeat top center
        }
        .box2 {
            width: 367px;
            height: 308px;
            margin-top: 160px;
            border: 2px solid;
            border-radius: 25px;
            padding: 46px;
            margin-left: 42%;
        }
        .center1{
            margin-left: 35.666667%;
        }
        .connect{
            position: relative;
            left: 9px;
        }
    </style>
</head>
<body class="container-fluid box1">
<div class="box2">
    <form class=" form-horizontal" action="/user/login" method="post">
        <div class="form-group">
            <label for="loginName" style="color: black">用户名</label>
            <input type="text" name="loginName" class="form-control" id="loginName" placeholder="Jane Doe">
        </div>
        <div class="form-group">
            <label for="password" style="color: black">密码</label>
            <input type="text" name="password" class="form-control" id="password" placeholder="Jane Doe">
        </div>

        <div class="form-group">
            <div class="center1">
                <button type="submit" class="btn btn-default">登录</button>
            </div>
        </div>
    </form>
    <a  class="center1 connect" href="http://localhost:8080/user/registerView">注册</a>
</div>
</body>
</html>
