<%@page pageEncoding="utf-8" isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="page" value="${pageContext.request.contextPath}"></c:set>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="${page}/static/boot/css/bootstrap.min.css">
    <link rel="stylesheet" href="${page}/static/boot/css/back.css">
    <link rel="stylesheet" href="${page}/static/jqgrid/css/trirand/ui.jqgrid-bootstrap.css">
    <link rel="stylesheet" href="${page}/static/jqgrid/css/jquery-ui.css">
    <script src="${page}/static/boot/js/jquery-2.2.1.min.js"></script>
    <script src="${page}/static/boot/js/bootstrap.min.js"></script>
    <script src="${page}/static/jqgrid/js/trirand/src/jquery.jqGrid.js"></script>
    <script src="${page}/static/jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
    <script src="${page}/static/boot/js/ajaxfileupload.js"></script>
    <script charset="utf-8" src="${page}/kindeditor/kindeditor-all.js"></script>
    <script charset="utf-8" src="${page}/kindeditor/lang/zh-CN.js"></script>
    <script charset="UTF-8" src="${page}/echarts/echarts.min.js"></script>
    <script src="${page}/echarts/china.js" charset="UTF-8"></script>
    <title>持名法舟后台管理系统</title>
    <style>
        body {
            padding-top: 70px;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            //ajax  退出
            $("#exit").click(function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/ad/exit",
                    type: "POST",
                    datatype: "JSON",
                    success: function (data) {
                        console.log("sc");
                        location.href = "${pageContext.request.contextPath}/html/login.jsp";
                    }
                });
            });
        })
    </script>
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">持名法舟后台管理系统</a>
        </div>
        <div>
            <!--向右对齐-->
            <ul class="nav navbar-nav navbar-right">
                <li><a href="">欢迎:<shiro:principal></shiro:principal></a></li>
                <li><a id="exit" href="javascript:;">退出登录</a></li>
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
                                <h4>用户管理</h4>
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li>
                                    <a href="javascript:$('#centerLay').load('${page}/jsp/back/user/user.jsp')">用户信息管理</a>
                                </li>
                                <li>
                                    <a href="javascript:$('#centerLay').load('${page}/jsp/back/user/qushi.jsp')">用户注册趋势</a>
                                </li>
                                <li>
                                    <a href="javascript:$('#centerLay').load('${page}/jsp/back/user/map.jsp')">用户注册分布</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseTwo">
                                <h4>轮播图管理</h4>
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="javascript:$('#centerLay').load('${page}/jsp/back/banner/banner.jsp')">轮播图信息</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseThree">
                                <h4>上师管理</h4>
                            </a>
                        </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="javascript:$('#centerLay').load('${page}/jsp/back/guru/guru.jsp')">上师信息</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseFour">
                                <h4>文章管理</h4>
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFour" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li><a a href="javascript:$('#centerLay').load('${page}/jsp/back/article/article.jsp')">文章列表</a>
                                </li>
                                <%--                                <li><a>文章搜索</a></li>--%>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseFive">
                                <h4>专辑管理</h4>
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFive" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li><a a
                                       href="javascript:$('#centerLay').load('${page}/jsp/back/album/album.jsp')">专辑信息</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xs-10" id="centerLay">
            <div class="container container-fluid">
                <div class="jumbotron">
                    <h2>欢迎使用持名法舟后台管理系统！</h2>
                </div>
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
                            <img src="${page}/static/img/timg.jpg" alt="First slide" style="height: 80%;width: 100%">
                            <div class="carousel-caption">标题 1</div>
                        </div>
                        <div class="item">
                            <img src="${page}/static//img/后台bj.jpg" alt="Second slide" style="height: 80%;width: 100%">
                            <div class="carousel-caption">标题 2</div>
                        </div>
                        <div class="item">
                            <img src="${page}/static//img/timg.jpg" alt="Third slide" style="height: 80%;width: 100%">
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

        </div>
    </div>
</div>
<div class="panel-footer navbar-fixed-bottom" style="height:50px;width:100%;clear:both;margin-top:-50px;">
    <h5 style="text-align: center">百知教育 @baizhiedu.com.cn</h5>
</div>


<script>
    $(function () {
        //页面初始化的时候把  模态框中的数据封装
        $.get("${pageContext.request.contextPath}/guru/showGuruList", function (data) {
            var option = "<option value='0'>通用文章</option>";
            data.forEach(function (guru) {
                option += "<option value='" + guru.id + "'>" + guru.nickName + "</option>"
            });
            $("#guruId").html(option);
        }, "json");
        $("#status").empty();
        $("#status").append("<option value='1'>展示</option><option value='2' >冻结</option>");
    });
    KindEditor.ready(function (K) {
        window.editor = K.create('#editor_id', {
            width: '600px',
            // 1. 指定图片上传路径
            uploadJson: "${pageContext.request.contextPath}/article/uploadImg",
            allowFileManager: true,
            fileManagerJson: "${pageContext.request.contextPath}/article/showAllImgs",
            afterBlur: function () {
                this.sync();
            }
        });
    });
</script>
<!-- KindEditor模态框 -->
<div class="modal fade" id="kind" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 750px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">文章信息</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="kindfrm" method="post" class="form-horizontal">
                    <div class="form-group">
                        <input type="hidden" name="id" id="id" class="form-control">
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label">标题</label>
                        <div class="col-sm-5">
                            <input type="text" name="title" id="title" placeholder="请输入标题" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label">封面</label>
                        <div class="col-sm-5">
                            <input type="file" name="cover" id="cover" placeholder="请选择封面" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label">所属上师</label>
                        <div class="col-sm-5">
                            <select class="form-control" name="guruId" id="guruId">

                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label">状态</label>
                        <div class="col-sm-5">
                            <select class="form-control" name="status" id="status">

                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label">文章发布日期</label>
                        <div class="col-sm-5">
                            <input type="date" name="publishDate" id="publishDate" placeholder="请输入日期"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-12">
                            <textarea id="editor_id" name="content" style="width:700px;height:300px;"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer" id="modal_foot">

            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

</body>
</html>