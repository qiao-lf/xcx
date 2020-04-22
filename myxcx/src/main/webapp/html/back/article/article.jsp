<%--
  Created by IntelliJ IDEA.
  User: qiao
  Date: 2019/11/29
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<script type="text/javascript">
    $(function () {
        $("#articleTable").jqGrid(
            {
                url: "${pageContext.request.contextPath}/ar/showAllArticleByPage",
                datatype: "json",
                colNames: ['编号', '标题', '标题解释', "内容", '内容解释', '上传时间'],
                colModel: [
                    {name: 'id', align: "center", hidden: true},
                    {name: 'title', align: "center"},
                    {name: 'ctitle', align: "center"},
                    {name: 'earticle', align: "center"},
                    {name: 'carticle', align: "center"},
                    {name: 'createdate', align: "center"},
                ],
                rowNum: 5,
                rowList: [5, 10, 20],
                pager: '#articlePager',
                mtype: "post",
                viewrecords: true,
                sortorder: "desc",
                styleUI: "Bootstrap",
                autowidth: true,
                multiselect: true,
                height: "500px",
                editurl: "${pageContext.request.contextPath}/ar/editArticle"
            });
        $("#articleTable").jqGrid('navGrid', "#articlePager", {
            add: false,
            edit: false,
            del: false,
            addtext: "添加",
            //deltext: "删除"
        });
    })
    // 打开模态框
    function addArticle() {
        $("#kindfrm")[0].reset();
        KindEditor.html("#editor_id", "");
        $("#modal_foot").html("<button type=\"button\" class=\"btn btn-danger\" data-dismiss=\"modal\">关闭</button>" +
            "<button type=\"button\" class=\"btn btn-primary\" onclick=\"insertArticle()\">提交</button>")
        $("#kind").modal("show");
    }
    // 添加文章
    function insertArticle() {
        $.ajaxFileUpload({
            url: "${pageContext.request.contextPath}/ar/addArticle",
            datatype: "json",
            type: "post",
            fileElementId: "cover",
            // ajaxFileUpload 不支持序列化数据上传id=111&&title="XXX"
            //                只支持 Json格式上传数据
            // 解决方案 : 1.更改 ajaxFileUpload 源码 2. 手动封装Json格式
            data: {
                title: $("#title").val(),
                ctitle: $("#ctitle").val(),
                earticle: $("#earticle").val(),
                carticle: $("#carticle").val(),
            },

            success: function (data) {
                console.log("sc");
                $("#kind").modal("hide");
                $("#articleTable").trigger("reloadGrid");
            }
        })
    }
</script>
<div class="page-header">
    <h2><strong>文章管理</strong></h2>
</div>
<ul class="nav nav-tabs">
    <li class="active"><a>文章列表</a></li>
    <li><a onclick="addArticle()">添加文章</a></li>
</ul>
<div class="panel">
    <table id="articleTable"></table>
    <div id="articlePager" style="width: auto;height: 50px"></div>
</div>
