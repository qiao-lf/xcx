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
        $("#edTable").jqGrid(
            {
                url: "${pageContext.request.contextPath}/ed/showAllEwordsByPage",
                datatype: "json",
                colNames: ['词汇信息', '词汇解释'],
                colModel: [
                    {name: 'eword', align: "center",editable: true,},
                    {name: 'cword', align: "center", editable: true,},
                ],
                rowNum: 8,
                rowList: [8, 10, 20],
                pager: '#edPager',
                mtype: "post",
                viewrecords: true,
                sortorder: "desc",
                styleUI: "Bootstrap",
                autowidth: true,
                multiselect: true,
                height: "500px",
                editurl: "${pageContext.request.contextPath}/ed/editEwords"
            });
        $("#edTable").jqGrid('navGrid', "#edPager", {
            add: true,
            edit: false,
            del: false,
            addtext: "添加", edittext: "修改", deltext: "删除"
        });
    })

</script>
<div class="page-header">
    <h2><strong>词汇信息管理</strong></h2>
</div>

<div class="panel">
    <table id="edTable"></table>
    <div id="edPager" style="width: auto;height: 50px"></div>
</div>