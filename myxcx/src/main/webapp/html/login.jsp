<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>英语学习小程序后台管理</title>
    <link href="favicon.ico" rel="shortcut icon"/>
    <link href="${pageContext.request.contextPath}/static/boot/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/static/boot/js/jquery-2.2.1.min.js"></script>
    <script>
        function login() {
            $.ajax({
                url: "${pageContext.request.contextPath}/ad/login",
                type: "POST",
                datatype: "JSON",
                data: $("#loginForm").serialize(),
                success: function (data) {
                    if (data != null && data != "") {
                        $("#msg").html("<span class='error'>" + data + "</span>")
                    } else {
                        location.href = "${pageContext.request.contextPath}/html/back/main.jsp";
                    }
                }
            })
        }
    </script>
</head>
<body style=" background: url(${pageContext.request.contextPath}/static/img/timg.jpg); background-size: 100%;">


<div class="modal-dialog" style="margin-top: 10%;">
    <div class="modal-content">
        <div class="modal-header">

            <h4 class="modal-title text-center" id="myModalLabel">英语学习小程序后台管理</h4>
        </div>
        <form id="loginForm" method="post" action="${pageContext.request.contextPath}/ad/login">
            <div class="modal-body" id="model-body">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="用户名" autocomplete="off" name="username">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" placeholder="密码" autocomplete="off" name="password">
                </div>
                <%--                <div class="form-group">--%>
                <%--                    <input type="text" class="form-control" placeholder="验证码" autocomplete="off" name="code">--%>
                <%--                </div>--%>
                <%--                <div class="form-group">--%>
                <%--                    <img id="num" src="${pageContext.request.contextPath}/captcha/code"--%>
                <%--                         onclick="document.getElementById('num').src = '${pageContext.request.contextPath}/captcha/code?'+(new Date()).getTime()"/>--%>
                <%--                    &lt;%&ndash;                <a href="javascript:;" onclick="document.getElementById('num').src = '${pageContext.request.contextPath}/captcha/code?'+(new Date()).getTime()"></a>&ndash;%&gt;--%>

                <%--                </div>--%>
                <span id="msg" style="color:red"></span>
            </div>
            <div class="modal-footer">
                <div class="form-group">
                    <button type="button" class="btn btn-primary form-control" id="log" onclick="login()">登录</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
