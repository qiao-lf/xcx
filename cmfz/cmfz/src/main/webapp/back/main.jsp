<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTf-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
    pageContext.setAttribute("path", path);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!--引入bootstrap的css样式-->
    <link rel="stylesheet" href="${path}/static/css/bootstrap.min.css">
    <!--引入jqgrid的css-->
    <link rel="stylesheet" href="${path}/static/css/ui.jqgrid-bootstrap.css">
    <!--引入jQuery-->
    <script src="${path}/static/js/jquery-3.4.1.min.js"></script>
    <!--引入jqgrid-->
    <script src="${path}/static/js/jquery.jqGrid.min.js"></script>
    <!--引入jqgird国际化-->
    <script src="${path}/static/js/grid.locale-cn.js"></script>
    <!--引入bootstrap的js-->
    <script src="${path}/static/js/bootstrap.min.js"></script>
    <%--文件上传--%>
    <script src="${path}/static/js/ajaxfileupload.js"></script>
    <%--goeasy资源引入--%>
    <!--[if lte IE 8]>//如果需要支持低版本的IE8及以下
    <script  type="text/javascript" src="https://cdn.goeasy.io/json2.js"></script>
    <![endif]-->
    <script type="text/javascript" src="https://cdn.goeasy.io/goeasy-1.0.3.js"></script>
    <%--引入kindeditor相关--%>
    <link rel="stylesheet" href="${path}/static/kindeditor/themes/default/default.css" />
    <script charset="utf-8" src="${path}/static/kindeditor/kindeditor-all.js"></script>
    <script charset="utf-8" src="${path}/static/kindeditor/lang/zh-CN.js"></script>
    <script>
        KindEditor.ready(function (K) {
            K.create("#editor_id", {
                uploadJson: "${path}/essay/upload",
                afterBlur: function () {
                    this.sync();
                }
            })
        });
    </script>
    <title>持名法舟后台管理系统</title>
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">持名法舟后台管理系统</a>
        </div>
        <div>
            <!--向右对齐-->
            <ul class="nav navbar-nav navbar-right">
                <li><a href="">欢迎:<shiro:authenticated><shiro:principal></shiro:principal></shiro:authenticated>
                    <shiro:notAuthenticated>请先登录</shiro:notAuthenticated>
                </a></li>
                <li><a href="">退出登录</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container-fluid">
    <div class="row">
        <div class="col-xs-2">
            <div class="panel-group" id="accordion">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseOne">
                                <h3>用户管理</h3>
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="javascript:$('#contenter').load('${path}/back/user/userlist.jsp')">用户信息管理</a></li>
                                <li><a href="javascript:$('#contenter').load('${path}/back/user/userchart.jsp')">用户注册趋势</a></li>
                                <li><a href="javascript:$('#contenter').load('${path}/back/user/userlocation.jsp')">用户注册分布</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseTwo">
                                <h3>轮播图管理</h3>
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="javascript:$('#contenter').load('${path}/back/wheelimage/wheel.jsp');">轮播图信息</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseThree">
                                <h3>上师管理</h3>
                            </a>
                        </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="javascript:$('#contenter').load('${path}/back/guru/gurulist.jsp')">上师信息</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseFour">
                                <h3>文章管理</h3>
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFour" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="javascript:$('#contenter').load('${path}/back/essay/essaylist.jsp')">文章信息</a></li>
                                <li><a href="javascript:$('#contenter').load('${path}/back/essay/essaysearch.jsp')">文章搜索</a></li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseFive">
                                <h3>专辑管理</h3>
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFive" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="javascript:$('#contenter').load('${path}/back/album/albumlist.jsp')">专辑信息</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <%--超级管理员显示的管理员模块--%>
                <shiro:hasRole name="admin">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion" href="#collapseSix">
                                    <h3>管理员权限管理</h3>
                                </a>
                            </h4>
                        </div>
                        <div id="collapseSix" class="panel-collapse collapse">
                            <div class="panel-body">
                                <ul class="nav nav-pills nav-stacked">
                                    <li><a href="javascript:$('#contenter').load('${path}/back/admin/adminlist.jsp')">管理员信息</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </shiro:hasRole>

            </div>
        </div>
        <div class="col-xs-10">
            <div id="contenter">
                <div class="container">
                    <div class="jumbotron">
                        <h2>欢迎使用持名法舟后台管理系统！</h2>
                    </div>
                </div>
                <div class="col-sm-offset-1 col-sm-10">
                    <div id="myCarousel" class="carousel slide">
                        <!-- 轮播（Carousel）指标 -->
                        <ol class="carousel-indicators">
                            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                            <li data-target="#myCarousel" data-slide-to="1"></li>
                            <li data-target="#myCarousel" data-slide-to="2"></li>
                        </ol>
                        <!-- 轮播（Carousel）项目 -->
                        <div class="carousel-inner">
                            <div class="item active">
                                <img src="${path}/static/image/壁纸3.jpg"  alt="First slide">
                                <div class="carousel-caption">标题 1</div>
                            </div>
                            <div class="item">
                                <img src="${path}/static/image/壁纸4.jpg" alt="Second slide">
                                <div class="carousel-caption">标题 2</div>
                            </div>
                            <div class="item">
                                <img src="${path}/static/image/壁纸5.jpg" alt="Third slide">
                                <div class="carousel-caption">标题 3</div>
                            </div>
                        </div>
                        <!-- 轮播（Carousel）导航 -->
                        <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>
                </div>
            </div><%--contenter--%>

        </div>
    </div>
</div>
<div class="panel-footer">
    <h4 style="text-align: center">百知教育 @baizhiedu.com.cn</h4>
</div>
</body>
</html>