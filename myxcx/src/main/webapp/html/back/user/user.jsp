<%--
  Created by IntelliJ IDEA.
  User: qiao
  Date: 2019/12/1
  Time: 22:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<script type="text/javascript">
    $(function () {
        $("#userTable").jqGrid(
            {
                url: "${pageContext.request.contextPath}/user/showAllUser",
                datatype: "json",
                colNames: ['编号', '电话', '密码', "安全码", '状态', '用户头像', '用户名字', '用户昵称', '性别', '个性签名', '所在地区', '注册时间', '最后登录时间', '操作'],
                colModel: [
                    {name: 'id', align: "center", hidden: true},
                    {name: 'phone', align: "center"},
                    {name: 'password', align: "center"},
                    {name: 'salt', algin: "center",},
                    {
                        name: 'status', align: "center", formatter: function (data) {
                            if (data == "1") {
                                return "活跃";
                            } else return "冻结";
                        }
                    },
                    {
                        name: 'photo', align: "center", formatter: function (data) {
                            return "<img style='width: 100%' src='" + data + "'/>"
                        }
                    },
                    {name: 'name', align: "center"},
                    {name: 'nickName', align: "center",},
                    {name: 'sex', align: "center",},
                    {name: 'sign', align: "center",},
                    {name: 'location', align: "center",},
                    {name: 'rigestDate', align: "center",},
                    {name: 'lastLogin', align: "center",},
                    {
                        name: 'option', formatter: function (cellvalue, options, rowObject) {
                            var result = '';
                            result += "<a href='javascript:void(0)' onclick=\"showModel('" + rowObject.id + "')\" class='btn btn-lg' title='查看详情'> <span class='glyphicon glyphicon-th-list'></span></a>";
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
                editurl: "${pageContext.request.contextPath}/user/editUser"
            });
        $("#userTable").jqGrid('navGrid', "#userPager", {
            add: false,
            edit: false,
            del: false,
            //deltext: "删除"
        });
    })

</script>
<div class="page-header">
    <h2><strong>用户信息管理</strong></h2>
</div>

<div class="panel">
    <table id="userTable"></table>
    <div id="userPager" style="width: auto;height: 50px"></div>
</div>


