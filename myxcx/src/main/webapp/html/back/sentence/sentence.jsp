<%--
  Created by IntelliJ IDEA.
  User: qiao
  Date: 2020/4/22
  Time: 9:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<script type="text/javascript">
    $(function () {
        $("#seTable").jqGrid(
            {
                url: "${pageContext.request.contextPath}/se/showAllSentenceByPage",
                datatype: "json",
                colNames: ['短句编号','短句信息', '短句解释','上传时间'],
                colModel: [
                    {name: 'id', align: "center", hidden: true},
                    {name: 'esentence', align: "center",editable: true,},
                    {name: 'csentence', align: "center", editable: true,},
                    {name: 'createdata', align: "center"},
                ],
                rowNum: 8,
                rowList: [8, 10, 20],
                pager: '#sePager',
                mtype: "post",
                viewrecords: true,
                sortorder: "desc",
                styleUI: "Bootstrap",
                autowidth: true,
                multiselect: true,
                height: "500px",
                editurl: "${pageContext.request.contextPath}/se/editSentence"
            });
        $("#seTable").jqGrid('navGrid', "#sePager", {
            add: true,
            edit: false,
            del: false,
            addtext: "添加", edittext: "修改", deltext: "删除"
        });
    })

</script>
<div class="page-header">
    <h2><strong>短句信息管理</strong></h2>
</div>

<div class="panel">
    <table id="seTable"></table>
    <div id="sePager" style="width: auto;height: 50px"></div>
</div>