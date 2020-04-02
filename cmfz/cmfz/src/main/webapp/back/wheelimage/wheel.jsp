<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTf-8" %>
<%
    String path = request.getContextPath();
    pageContext.setAttribute("path", path);
%>
<script>
    $(function () {
        //表格初始化
        $('#wheellist').jqGrid({
            url:"${path}/wheel/findAll",
            /*width:800,*/
            height:300,
            autowidth: true,//自适应外部容器的宽度
            styleUI:"Bootstrap",//设置为bootstrap的风格
            datatype:"json",//数据类型为json
            mtype:"post",//设置请求方式，默认为get
            colNames: ["图片Id","图片原始名","图片实际名","图片状态","目录","图片描述","超链接","操作"],//列名称为数组
            colModel:[
                {name:"imageId",search: false,key:true,index:"imageId"},
                {name: "orgName",editable: true,},
                {name:"newName" ,search: false,formatter:function (data) {
                        return "<img style='width: 100%' src='"+data+"' />";
                    },editable:true,edittype:"file",editoptions: {enctype:"multipart/form-data"}},
                {name:"imageStatus" ,editable: true,editoptions:{required:true},search: false,formatter:function (data) {
                        if(data=='1'){
                            return "正常";
                        }else {
                            return "冻结";
                        }
                    },edittype:"select",editoptions:{value:"1:正常;0:冻结"}},
                {name:"imageDir" ,editable: false,search: false},
                {name:"imageSummary",editable:true,editoptions:{required:true}},
                {name:"imageHref",editable:true,search:false,editoptions:{required:true}},
                {//配置操作列对象
                    formatter:function (value, options, rowObject) {
                        var imageId = rowObject.imageId;
                        return `<button class="btn btn-success" onclick="editRow('`+imageId+`');">修改</button>&nbsp;&nbsp;&nbsp;<button class="btn btn-danger" onclick="delRow('`+imageId+`');">删除</button>`;
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
            editurl:"${path}/wheel/alter",//开启编辑时执行编辑操作的url路径  添加  修改  删除
            multiselect:true,
            toolbar:[true,'top'],
            gridComplete:function () {//给表格加入一个完成事件
                var btn = "<button class='btn btn-success' onclick='saveRow();'>添加图片</button>&nbsp;&nbsp;" +
                    "<button class='btn btn-success' ><a href='${path}/wheel/download'>导出图片信息</a></button>&nbsp;&nbsp;"
                +"<button class='btn btn-success' ><a href='${path}/wheel/downloadModel'>导出图片模板</a></button>&nbsp;&nbsp;"
                +"<button class='btn btn-success' onclick='upload()' type='file'>导入图片信息</button>&nbsp;&nbsp;";
                $("#t_wheellist").empty().append(btn)
            }


        }).navGrid('#pager',//开启分页工具栏
            {add:true,edit:true,del:true,search:true,refresh:true},//开启编辑操作
            {closeAfterEdit:true,height:600,width:600,editCaption:"编辑图片信息",
                afterSubmit:function (response, postDate) {
                    var imageId = response.responseJSON.imageId;
                    $.ajaxFileUpload({
                        url:"${path}/wheel/upload",
                        datatype:'json',
                        type: 'post',
                        data: {imageId:imageId},
                        fileElementId: 'newName',
                        success:function (data) {
                            //输出上传成功
                            alert("上传成功！");
                            //jqgrid重新载入
                            $('#wheellist').trigger('reloadGrid');//刷新表格页面
                        }
                    });
                    return postDate;
                }
            },//配置对象:编辑面板
            {closeAfterAdd:true,height:600,width:600,addCaption:"添加图片信息",
                afterSubmit:function (response,postDate) {//提交表单后
                var imageId = response.responseJSON.imageId;
                console.log(response.responseJSON.imageId);
                    $.ajaxFileUpload({
                        url:"${path}/wheel/upload",
                        datatype: 'json',
                        type:'post',
                        data:{imageId:imageId},
                        //指定上传表单控件的name,
                        fileElementId:'newName',
                        success:function (data) {
                            //输出上传成功
                            alert("上传成功！");
                            //jqgrid重新载入
                            $('#wheellist').trigger('reloadGrid');//刷新表格页面
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
        $("#wheellist").jqGrid('editGridRow', "new", {
            height : 500,
            reloadAfterSubmit : true,
            closeAfterAdd: true,//添加后关闭窗口
        });
    }
    //修改
    function editRow(id) {
        $("#wheellist").jqGrid('editGridRow', id, {
            height : 500,
            reloadAfterSubmit : true,
            closeAfterEdit: true,//修改后关闭窗口
        });

    }
    // 删除
    function delRow(id) {
        if(window.confirm("确定要删除此图片吗？")){
            $.post("${path}/wheel/alter",{imageId:id,oper:'del'},function (data) {

                $('#wheellist').trigger('reloadGrid');//刷新表格页面
            })
        }
    }
</script>

<div class="col-sm-12">
    <!--创建表格-->
    <table id="wheellist"></table>

    <!--分页工具栏-->
    <div id="pager"></div>
</div>