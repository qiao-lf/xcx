<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTf-8" %>
<%
    String path = request.getContextPath();
    pageContext.setAttribute("path", path);
%>
<script>
    $(function () {
        //表格初始化
        $('#adminlist').jqGrid({
            url:"${path}/admin/findAll",
            /*width:800,*/
            height:300,
            autowidth: true,//自适应外部容器的宽度
            styleUI:"Bootstrap",//设置为bootstrap的风格
            datatype:"json",//数据类型为json
            mtype:"post",//设置请求方式，默认为get
            colNames: ["用户名","密码","角色权限","操作"],//列名称为数组
            colModel:[
                {name:"username",search: false,key:true,index:"guruId"},
                {name: "password",editable: true,},
                {name:"roles" ,editable: true,width:300,search: false,formatter(value, options, rowObject){
                   var result="";
                    //配置角色权限显示
                       var roles =  rowObject.roles;
                       //遍历角色信息
                       roles.forEach(function (r) {
                           result += "<strong style='color: blue'>"+r.roleName+": </strong>"
                          var resources =  r.resources;
                          //遍历权限信息
                          resources.forEach(function (re) {
                              result += re.resourceName + "  ,";
                          })
                           result +="<br>"
                       })
                        return result;
                    }},
                {//配置操作列对象
                    formatter:function (value, options, rowObject) {
                        var usernameForAdmin = rowObject.username;
                        return `<button class="btn btn-success" onclick="editRow('`+usernameForAdmin+`');">修改</button>&nbsp;&nbsp;&nbsp;<button class="btn btn-danger" onclick="delRow('`+usernameForAdmin+`');">删除</button>`;
                    },
                    search: false,
                    width:200,
                },
            ],//列数组配置列对象
            pager:"#pager",//设置分页工具栏
            //注意：1，一旦设置分页工具栏之后再根据指定的url查询时自动向后台传递page(当前页默认为1),rows(当前每页显示的信息条数),
            rowNum:5,
            rowList:[5,10,20],//生成可以指定显示每页展示多少条的下拉列表
            viewrecords:true,//是否显示总的信息条数
            caption:"管理员列表",//表格的标题
            cellEdit: true,//开启表格单元的编辑功能,配合colModel的列对象的editable属性来使用,同时要设置editurl才能编辑
            editurl:"${path}/admin /alter",//开启编辑时执行编辑操作的url路径  添加  修改  删
            toolbar:[true,'top'],
            gridComplete:function () {//给表格加入一个完成事件
                var btn = "<button class='btn btn-success' onclick='saveAdmin();'>添加管理员</button>&nbsp;&nbsp;"

                $("#t_adminlist").empty().append(btn)
            }


        }).navGrid('#pager',//开启分页工具栏
            {add:true,edit:true,del:true,search:true,refresh:true},//开启编辑操作
            {closeAfterEdit:true,height:600,width:600,editCaption:"编辑管理员信息"},//配置对象:编辑面板
            {closeAfterAdd:true,height:600,width:600,addCaption:"添加管理员信息"},//配置对象:添加面板
            {},//删除的配置
            {
                sopt:['eq','ne','cn']//配置搜索条件
            },
        );


    })

    //添加
    function saveRow() {
        $("#adminlist").jqGrid('editGridRow', "new", {
            height : 500,
            reloadAfterSubmit : true,
            closeAfterAdd: true,//添加后关闭窗口
            afterSubmit:function (response, postDate) {
                var guruId = response.responseJSON.guruId;
                $.ajaxFileUpload({
                    url:"${path}/guru/upload",
                    datatype:'json',
                    type: 'post',
                    data: {guruId:guruId},
                    fileElementId: 'guruImage',
                    success:function (data) {
                        //输出上传成功
                        alert("资料提交成功！");
                        //jqgrid重新载入
                        $('#gurulist').trigger('reloadGrid');//刷新表格页面
                    }
                });
                return postDate;
            }
        });
    }
    //修改
    function editRow(id) {
        $("#adminlist").jqGrid('editGridRow', id, {
            height : 500,
            reloadAfterSubmit : true,
            closeAfterEdit: true,//修改后关闭窗口
            afterSubmit:function (response, postDate) {
                var guruId = response.responseJSON.guruId;
                $.ajaxFileUpload({
                    url:"${path}/guru/upload",
                    datatype:'json',
                    type: 'post',
                    data: {guruId:guruId},
                    fileElementId: 'guruImage',
                    success:function (data) {
                        //输出上传成功
                        alert("资料提交成功！");
                        //jqgrid重新载入
                        $('#gurulist').trigger('reloadGrid');//刷新表格页面
                    }
                });
                return postDate;
            }
        });

    }
    // 删除
    function delRow(id) {
        if(window.confirm("确定要删除此上师吗？")){
            $.post("${path}/guru/alter",{guruId:id,oper:'del'},function (data) {

                $('#adminlist').trigger('reloadGrid');//刷新表格页面
            })
        }
    }
</script>

<div class="col-sm-12">
    <!--创建表格-->
    <table id="adminlist"></table>

    <!--分页工具栏-->
    <div id="pager"></div>
</div>