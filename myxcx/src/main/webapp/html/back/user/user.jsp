
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<script type="text/javascript">
    $(function () {
        $("#userTable").jqGrid(
            {
                url: "${pageContext.request.contextPath}/us/showAllUserByPage",
                datatype: "json",
                colNames: ['编号', '电话', '密码', "昵称", '真实姓名', '状态', '注册信息', '操作'],
                colModel: [
                    {name: 'id', align: "center", hidden: true},
                    {name: 'phone', align: "center"},
                    {name: 'password', align: "center"},
                    {name: 'nickname', algin: "center",},
                    {name: 'name', align: "center"},
                    {name: 'status', align: "center",},
                    {name: 'createdata', align: "center",},
                    {
                        name: 'option', formatter: function (cellvalue, options, rowObject) {
                            var result = '';
                            result += "<a href='javascript:void(0)' onclick=\"updateUserStatus('" + rowObject.id + "')\" class='btn btn-lg' title='修改'> <span class='btn btn-danger'>修改</span></a>";
                            return result;
                        }
                    },
                ],
                rowNum: 5,
                rowList: [5, 10, 20],
                pager: '#userPager',
                mtype: "post",
                viewrecords: true,
                sortorder: "desc",
                styleUI: "Bootstrap",
                autowidth: true,
                multiselect: true,
                height: "500px",
                editurl: "${pageContext.request.contextPath}/us/editUser"
            });
        $("#userTable").jqGrid('navGrid', "#userPager", {
            add: false,
            edit: false,
            del: false,
            //deltext: "删除"
        });
    })
    function updateUserStatus(s) {
        console.log(s);
        $.ajax({
            url:"${pageContext.request.contextPath}/us/updateStatus",
            type:"POST",
            datatype:"JSON",
            data: {id:s},
            success:function (data) {
                console.log(data);
                jQuery("#userTable").trigger("reloadGrid");
            }
        })

    }
</script>
<div class="page-header">
    <h2><strong>用户信息管理</strong></h2>
</div>

<div class="panel">
    <table id="userTable"></table>
    <div id="userPager" style="width: auto;height: 50px"></div>
</div>


