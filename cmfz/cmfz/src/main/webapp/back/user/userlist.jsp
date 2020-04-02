<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTf-8" %>
<%
    String path = request.getContextPath();
    pageContext.setAttribute("path", path);
%>
<script>
    $(function () {
        //表格初始化
        $('#userlist').jqGrid({
            url:"${path}/user/findAll",
            /*width:800,*/
            height:300,
            autowidth: true,//自适应外部容器的宽度
            styleUI:"Bootstrap",//设置为bootstrap的风格
            datatype:"json",//数据类型为json
            mtype:"post",//设置请求方式，默认为get
            colNames: ["用户id","username","密码","法号","性别","头像","签名","上次登录时间","手机号","用户状态","盐","地址","注册时间","操作"],//列名称为数组
            colModel:[
                {name:"userId",search: false,key:true,index:"userId"},
                {name: "username",},
                {name:"password" },
                {name:"cfUseranme" },
                {name:"sex" },
                {name:"headImage" },
                {name:"idiograph" },
                {name:"loginTime" },
                {name:"mobilePhone" },
                {name:"userStatus" ,editable: true,editoptions:{required:true},search: false,formatter:function (data) {
                        if(data=='1'){
                            return "正常";
                        }else {
                            return "冻结";
                        }
                    },edittype:"select",editoptions:{value:"1:正常;0:冻结"}},
                {name:"salut" ,editable: false,search: false},
                {name:"location",editable:false,editoptions:{required:true}},
                {name:"register",editable:false,search:false,editoptions:{required:true}},
                {//配置操作列对象
                    formatter:function (value, options, rowObject) {
                        var userId = rowObject.userId;
                        console.log(userId);
                        return `<button class="btn btn-success" onclick="editRow('`+userId+`');">修改</button>&nbsp;&nbsp;&nbsp;`;
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
            caption:"用户列表",//表格的标题
            cellEdit: true,//开启表格单元的编辑功能,配合colModel的列对象的editable属性来使用,同时要设置editurl才能编辑
            editurl:"${path}/user/alter",//开启编辑时执行编辑操作的url路径  添加  修改  删除
            multiselect:true,

        }).navGrid('#pager',//开启分页工具栏
            {add:true,edit:false,del:false,search:true,refresh:true},//开启编辑操作
            {closeAfterEdit:true,height:600,width:600,editCaption:"编辑图片信息",},//配置对象:编辑面板
            {closeAfterAdd:true,height:600,width:600,addCaption:"添加图片信息",
                afterSubmit:function (response,postDate) {//提交表单后
                    var userId = response.responseJSON.userId;
                    console.log(response.responseJSON.userId);
                    $.ajaxFileUpload({
                        url:"${path}/user/upload",
                        datatype: 'json',
                        type:'post',
                        data:{userId:userId},
                        //指定上传表单控件的name,
                        fileElementId:'headImage',
                        success:function (data) {
                            //输出上传成功
                            alert("添加成功！");
                            //jqgrid重新载入
                            $('#userlist').trigger('reloadGrid');//刷新表格页面
                        }
                    });//ajaxFileUpload
                    return postDate;
                }},//配置对象:添加面板
            {},//删除的配置
            {
                sopt:['eq','ne','cn']//配置搜索条件
            },
        );


    })
    //添加
    function saveRow() {
        $("#userlist").jqGrid('editGridRow', "new", {
            height : 500,
            reloadAfterSubmit : true,
            closeAfterAdd: true,//添加后关闭窗口
        });
    }
    //修改
    function editRow(userId) {
        var data1 = $('#userlist').jqGrid("getRowData",userId);
        console.log(data1);
       $.ajax({
           url:'${path}/user/alter',
           method:'post',
           data: {oper:'edit',userId:data1.userId,userStatus:data1.userStatus},
           datatype:'json',
           success:function (data) {
               $("#userlist").trigger("reloadGrid");//重新加载jqGird
           }
       })

    }

</script>

<div class="col-sm-12">
    <!--创建表格-->
    <table id="userlist"></table>

    <!--分页工具栏-->
    <div id="pager"></div>
</div>