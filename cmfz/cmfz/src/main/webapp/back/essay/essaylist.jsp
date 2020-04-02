<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTf-8" %>
<%
    String path = request.getContextPath();
    pageContext.setAttribute("path", path);
%>

<script >
    KindEditor.ready(function (K) {
        K.create("#editor_id", {
            uploadJson: "${path}/essay/upload",
            afterBlur: function () {
                this.sync();
            },
            allowFileManager:true,
            fileManagerJson : '${path}/essay/imgManager'
        })
    });
    // 打开模态框
    function addArticle() {
        $("#kindfrm")[0].reset();//清空表单内容
        KindEditor.html("#editor_id", "");//清空富文本的内容
        $("#modal_foot").html("<button type=\"button\" class=\"btn btn-danger\" data-dismiss=\"modal\">关闭</button>"+
            "<button type=\"button\" class=\"btn btn-primary\" onclick=\"insertArticle()\">提交</button>")
        $("#myModal").modal("show");//展示模态框
    }
    // 编辑文章
    function showModel(id) {
        var data = $("#essayTable").jqGrid("getRowData",id);
        console.log(data);
        $("#title").val(data.essayTittle);
        $("#status").val(data.essayCover);
        $("#id").val(data.essayId);
        KindEditor.html("#editor_id",data.essayContent);
        $("#modal_footer").html("<button type=\"button\" class=\"btn btn-danger\" data-dismiss=\"modal\">关闭</button>"+
            "<button type=\"button\" class=\"btn btn-primary\" onclick=\"updateArticle()\">修改</button>")
        $("#myModal").modal("show");
    };
    // 添加文章
    function insertArticle() {
        $.ajax({
            url:"${path}/essay/add",
            type:"post",
            datatype:"json",
            data:$("#kindfrm").serialize(),
            success:function (data) {
                $("#myModal").modal("hide");
                $("#essayTable").trigger("reloadGrid");
            }
        })
    }
    function updateArticle() {
        $.ajax({
            url:"${path}/essay/alter",
            type:"post",
            datatype:"json",
            data:$("#kindfrm").serialize(),
            success:function (data) {
                $("#myModal").modal("hide");//收起模态框
                $("#essayTable").trigger("reloadGrid");//重新加载jqGird
            }
        })
    }
    //元素加载完毕
        $(function () {
        $.ajax({
            url:"${path}/guru/findAll",
            method:'post',
            datatype:'json',
            data:{},
            success:function (data) {
                console.log(data);
                var opt = "";
                data.forEach(function (d) {
                    opt += "<option value='"+d.guruName+"'>"+d.guruName+"</option>";
                })
                $('#guruId').html(opt);
            }
        });
        //表格初始化
        $('#essayTable').jqGrid({
            url:"${path}/essay/findAll",
            /*width:800,*/
            height:300,
            autowidth: true,//自适应外部容器的宽度
            styleUI:"Bootstrap",//设置为bootstrap的风格
            datatype:"json",//数据类型为json
            mtype:"post",//设置请求方式，默认为get
            colNames: ["id","内容","标题","状态","上传时间","作者","操作"],//列名称为数组
            colModel:[
                {name:"essayId",search: false,key:true,hidden:true},
                {name:"essayContent",search: false,hidden:true},
                {name:"essayTittle",search: false,index:"essayTittle"},
                {name: "essayCover",editable: true, edittype:"select",editoptions:{value:"展示:展示;不展示:不展示"}},
                {name:"essayTime" ,search: false, edittype:"date"},
                {name:"author" ,editable: true,editoptions:{required:true},search: false,
                    edittype:"select",editoptions:{dataUrl:'${path}/guru/findAllForJq'}},
                {//配置操作列对象
                    formatter:function (value, options, rowObject) {
                        var  id =rowObject.essayId;
                        console.log(rowObject.essayId);
                        return `<a href="javascript:void(0)" onclick="showModel('`+id+`')" class="btn btn-lg" title="查看详情"> <span class="glyphicon glyphicon-th-list"></span></a>`;
                    },
                    search: false,
                    width:200,
                },
            ],//列数组配置列对象
            pager:"#pager",
            //注意：1，一旦设置分页工具栏之后再根据指定的url查询时自动向后台传递page(当前页默认为1),rows(当前每页显示的信息条数),
            rowNum:5,
            rowList:[5,10,20],//生成可以指定显示每页展示多少条的下拉列表
            viewrecords:true,//是否显示总的信息条数
            caption:"用户列表",//表格的标题
            cellEdit: true,//开启表格单元的编辑功能,配合colModel的列对象的editable属性来使用,同时要设置editurl才能编辑
            editurl:"${path}/essay/alter",//开启编辑时执行编辑操作的url路径  添加  修改  删除
            multiselect:true,


        }).navGrid('#pager',//开启分页工具栏
            {add:false,edit:true,del:true,search:true,refresh:true},//开启编辑操作
            {closeAfterEdit:true,height:600,width:600,editCaption:"编辑文章信息",reloadAfterSubmit: true},//配置对象:编辑面板
            {closeAfterAdd:true,height:600,width:600,addCaption:"添加诗词信息",reloadAfterSubmit: true},//配置对象:添加面板
            {},//删除的配置
            {
                sopt:['cn']//配置搜索条件
            },
        );




        })

</script>

<div class="col-sm-12">

    <!--标签页--->
    <ul class="nav nav-tabs">
        <li role="presentation" class="active"><a id="userlista" href="#userList" role="tab" data-toggle="tab">文章列表</a></li>
        <li role="presentation" data-toggle="modal" data-target="#myModal"><a href="#">添加文章</a></li>
    </ul>
    <!-- 标签页内容-->
    <div class="tab-content">
        <!--部门列表显示内容-->
        <div role="tabpanel"  class="tab-pane active" id="userList">
            <!--面板-->
            <div class="panel panel-default">
                <!--表格-->
                <!-- Table -->
                <table class="table" id="essayTable">
                </table>
                <!--分页工具栏-->
                <div id="pager"></div>
            </div><!--面板结束-->
        </div>

    </div>

</div>

<!--添加文章模态框-->
<div class="modal fade" id="myModal"  role="dialog" aria-labelledby="myModalLabel" >

    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width: 1000px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel" onclick="addArticle();">添加文章</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="kindfrm" method="post" class="form-horizontal">
                    <div class="form-group">
                        <input type="hidden" name="essayId" id="id" class="form-control" value="927696f22111412c8b0026e590fcf9f0">
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label">标题</label>
                        <div class="col-sm-5">
                            <input type="text" name="essayTittle" id="title" placeholder="请输入标题" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label">状态</label>
                        <div class="col-sm-5">
                            <select class="form-control" name="essayCover" id="status">
                                <option value="展示">展示</option>
                                <option value="不展示">不展示</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label">作者</label>
                        <div class="col-sm-5">
                            <select class="form-control" name="author" id="guruId">

                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label">文章</label>
                        <div class="col-sm-5">
                           <textarea id="editor_id" name="essayContent" style="width:700px;height:300px;">

                            </textarea>
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer" id="modal_footer" >
                <button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="insertArticle()">提交</button>
            </div>
        </div>
    </div>
</div>


