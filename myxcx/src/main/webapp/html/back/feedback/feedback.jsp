
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<script type="text/javascript">
    $(function () {
        $("#fbTable").jqGrid(
            {
                url: "${pageContext.request.contextPath}/fb/showAllFeedbackByPage",
                datatype: "json",
                colNames: ['编号', '用户编号', '反馈信息', "处理状态",  '上传时间', '操作'],
                colModel: [
                    {name: 'id', align: "center", hidden: true},
                    {name: 'userid', align: "center"},
                    {name: 'message', align: "center"},
                    {name: 'status', align: "center",},
                    {name: 'createtime', align: "center",},
                    {
                        name: 'option', formatter: function (cellvalue, options, rowObject) {
                            var result = '';
                            result += "<a href='javascript:void(0)' onclick=\"updateUserStatus('" + rowObject.id + "')\" class='btn btn-lg' title='处理'> <span class='btn btn-danger'>处理</span></a>";
                            return result;
                        }
                    },
                ],
                rowNum: 5,
                rowList: [5, 10, 20],
                pager: '#fbPager',
                mtype: "post",
                viewrecords: true,
                sortorder: "desc",
                styleUI: "Bootstrap",
                autowidth: true,
                multiselect: true,
                height: "500px",
                editurl: "${pageContext.request.contextPath}/fb/editUser"
            });
        $("#fbTable").jqGrid('navGrid', "#fePager", {
            add: false,
            edit: false,
            del: false,
            //deltext: "删除"
        });
    })
    function updateUserStatus(s) {
        console.log(s);
        $.ajax({
            url:"${pageContext.request.contextPath}/fb/updateStatus",
            type:"POST",
            datatype:"JSON",
            data: {id:s},
            success:function (data) {
                console.log(data);
                jQuery("#fbTable").trigger("reloadGrid");
            }
        })

    }
</script>
<div class="page-header">
    <h2><strong>反馈信息管理</strong></h2>
</div>

<div class="panel">
    <table id="fbTable"></table>
    <div id="febPager" style="width: auto;height: 50px"></div>
</div>


