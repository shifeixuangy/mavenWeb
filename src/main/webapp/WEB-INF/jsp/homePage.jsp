<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/11/27
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>

<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set><html>
<head>
    <title>mapper</title>
    <link rel="stylesheet" href="${ctx}/static/css/bootstrap-3.3.5.min.css"/>
    <script type="text/javascript" src="${ctx}/static/js/jquery-1.11.0.min.js"></script>
</head>
<body>
<p style="color: blue">首页</p>

<button type="button" onclick="seleteData()">查询</button>
<button type="button" onclick="updateData()">修改</button>
<button type="button" onclick="deleteData()">删除</button>
<button type="button" onclick="viewData()">查看</button>
<script type="application/javascript">
    function seleteData() {
        $.ajax({
            url: '/mybatis/select',
            type: 'POST', //GET
            async: false,    //或false,是否异步
            data: $("#registerUser").serializeArray(),
            timeout: 5000,    //超时时间
            dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
            beforeSend: function (xhr) {
                console.log(xhr)
                console.log('发送前')
            },
            success: function (data, jqXHR) {
                console.log(data)
                console.log(jqXHR);
                alert("注册成功");
            },
            error: function (data, xhr, textStatus) {
                console.log(data);
                console.log('错误');

            },
            complete: function () {
                console.log('结束')
            }
        })
    }
    function updateData() {
        $.ajax({
            url: '/mybatis/update',
            type: 'POST', //GET
            async: false,    //或false,是否异步
            data: $("#registerUser").serializeArray(),
            timeout: 5000,    //超时时间
            dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
            beforeSend: function (xhr) {
                console.log(xhr)
                console.log('发送前')
            },
            success: function (data, jqXHR) {
                console.log(data)
                console.log(jqXHR);
                alert("注册成功");
            },
            error: function (data, xhr, textStatus) {
                console.log(data);
                console.log('错误');

            },
            complete: function () {
                console.log('结束')
            }
        })
    }
    function deleteData() {
        $.ajax({
            url: '/mybatis/delete',
            type: 'POST', //GET
            async: false,    //或false,是否异步
            data: $("#registerUser").serializeArray(),
            timeout: 5000,    //超时时间
            dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
            beforeSend: function (xhr) {
                console.log(xhr)
                console.log('发送前')
            },
            success: function (data, jqXHR) {
                console.log(data)
                console.log(jqXHR);
                alert("注册成功");
            },
            error: function (data, xhr, textStatus) {
                console.log(data);
                console.log('错误');

            },
            complete: function () {
                console.log('结束')
            }
        })
    }

</script>
</body>
</html>
