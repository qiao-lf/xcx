<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTf-8" %>
<%
    String path = request.getContextPath();
    pageContext.setAttribute("path", path);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title></title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="robots" content="all,follow">
    <!-- Bootstrap CSS-->
    <link rel="stylesheet" href="${path}/static/vendor/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome CSS-->
    <link rel="stylesheet" href="${path}/static/vendor/font-awesome/css/font-awesome.min.css">
    <!-- Custom Font Icons CSS-->
    <link rel="stylesheet" href="${path}/static/css/font.css">
    <!-- Google fonts - Muli-->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Muli:300,400,700">
    <!-- theme stylesheet-->
    <link rel="stylesheet" href="${path}/static/css/style.default.css" id="theme-stylesheet">
    <!-- Custom stylesheet - for your changes-->
    <link rel="stylesheet" href="${path}/static/css/custom.css">
    <!-- Favicon-->
    <link rel="shortcut icon" href="${path}/static/img/favicon.ico">
    <!-- Tweaks for older IEs--><!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
    <script src="${path}/static/js/jquery-3.4.1.min.js"></script><![endif]-->
    <script>
        $(function () {
            $('#submit').click(function () {
                var username = $('#login-username').val();
                var password = $('#login-password').val();
                console.log(username);
                console.log(password);
                $.ajax({
                    url:"${path}/admin/login",
                    data:{"username":username,"password":password},
                    method:'get',
                    datatype:'json',
                    success:function (data) {
                        console.log(data);
                        if('"success"'==data){
                            window.location.replace("${path}/back/main.jsp");
                        }else {
                            $('#errorMsg').text(data);
                        }
                    }
                })
            })
        })
    </script>
</head>
<body>
<div class="login-page">
    <div class="container d-flex align-items-center">
        <div class="form-holder has-shadow">
            <div class="row">
                <!-- Logo & Information Panel-->
                <div class="col-lg-6">
                    <div class="info d-flex align-items-center">
                        <div class="content">
                            <div class="logo">
                                <h1>持明法洲</h1>
                            </div>
                            <p>后台管理系统</p>
                        </div>
                    </div>
                </div>
                <!-- Form Panel    -->
                <div class="col-lg-6">
                    <div class="form d-flex align-items-center">
                        <div class="content">
                                <div class="form-group">
                                    <input id="login-username" type="text" name="username" required data-msg="请输入管理员账户" class="input-material">
                                    <label for="login-username" class="label-material">管理员账户</label>
                                </div>
                                <div class="form-group">
                                    <input id="login-password" type="password" name="password"  required data-msg="请输入密码" class="input-material">
                                    <label for="login-password" class="label-material">密码</label>
                                </div>
                                <button type="button" class="btn btn-primary" id="submit">登录</button><span id="errorMsg" style="color:red;"></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="copyrights text-center">
        <p>Design by <a href="https://bootstrapious.com/p/bootstrap-4-dark-admin" class="external">鴻宬</a></p>
        <!-- Please do not remove the backlink to us unless you support further theme's development at https://bootstrapious.com/donate. It is part of the license conditions. Thank you for understanding :)-->
    </div>
</div>
<!-- JavaScript files-->
<script src="${path}/static/vendor/jquery/jquery.min.js"></script>
<script src="${path}/static/vendor/popper.js/umd/popper.min.js"> </script>
<script src="${path}/static/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="${path}/static/vendor/jquery.cookie/jquery.cookie.js"> </script>
<script src="${path}/static/vendor/chart.js/Chart.min.js"></script>
<script src="${path}/static/vendor/jquery-validation/jquery.validate.min.js"></script>
<script src="${path}/static/js/front.js"></script>
</body>
</html>