<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTf-8" %>
<%
    String path = request.getContextPath();
    pageContext.setAttribute("path", path);
%>
<script>
    $(function () {
        $("#albumList").jqGrid({
            url:'${path}/album/findAll',
            datatype: "json",
            height: 500,
            colNames:['专辑id','专辑名', '封面', '作者','播音','评分星级','发布日期','章节数','简介'],
            colModel:[
                {name:'albumId',key:true},
                {name:'albumName',editable:true},
                {name:'albumCover',formatter:function (data) {
                        return "<img style='width: 100%' src='"+data+"' />";
                    },editable:true,edittype:'file',editoptions:{enctype:'multipart/form-data'}},
                {name:'author',editable:true},
                {name:'announcer',editable:true},
                {name:'score',editable:true},
                {name:'alibumDate',editable:true},
                {name:'albumNum',editable:true},
                {name:'summary',editable:true}
            ],
            rowNum:8,
            rowList:[8,10,20,30],
            pager: '#albumPager',
            sortname: 'albumId',
            viewrecords: true,
            sortorder: "desc",
            multiselect: false,
            subGrid: true,
            caption: "专辑管理",
            autowidth:true,
            styleUI:"Bootstrap",
            editurl:'${path}/album/alter',
            subGridRowExpanded: function(subgrid_id, row_id) {//重写创建子表格方法
                addsubGridRow(subgrid_id,row_id);
            },
            // 删除表格方法
            subGridRowColapsed : function(subgrid_id, row_id) {

            }
        });
        $("#albumList").jqGrid('navGrid','#albumPager',
            {edit:true,add:true,del:true},
            {closeAfterEdit:true,},//edit
            {closeAfterAdd:true,afterSubmit:function (response, postDate) {
                var albumId = response.responseJSON.albumId;
                    console.log(response.responseJSON);
                    $.ajaxFileUpload({
                        url:'${path}/album/upload',
                        data: {albumId:albumId },
                        datatype:'json',
                        type:'post',
                        fileElementId:'albumCover',
                        success:function (data) {
                            //输出上传成功
                            alert("上传成功！");
                            //jqgrid重新载入
                            $('#albumList').trigger('reloadGrid');//刷新表格页面
                        }
                    });//ajaxFileUpload
                    return postDate;
                }},//add
            {},//del
            {sopt:['eq','ne','cn']},//配置搜索条件
        );
        /**
         * 创建子表格
         * @param subgrid_id 子表格的区域的id
         * @param row_id 子表格所属的行id
         */
        function addsubGridRow(subgrid_id, row_id){
            console.log(row_id);
            var  subgridTable = subgrid_id+"table";//子表格的表id
            var  subgridPager = subgrid_id+"Pager";//子表格的工具栏id
            $("#"+subgrid_id).html("<table id='"+subgridTable+"'></table><div id='"+subgridPager+"'></div>");
            //配置子表格
            $("#"+subgridTable).jqGrid({
                url: '${path}/chapter/findOneChapters?albumId='+row_id,
                datatype: 'json',
                colNames: ['章节id','音频','章节名','大小','时长','操作'],
                colModel: [
                    {name:"chapterId",key:true,width:10},
                    {name:"voiceurl"},
                    {name:"chapterName",width:70,editable:true},
                    {name:"size",width:70},
                    {name:"timeLong",width:70},
                    {name:"voiceurl",width:200,formatter:function (data) {
                            return"<audio src='"+data+"' controls=\"controls\"></audio><a href='${path}/chapter/download?voiceurl="+data+"'><span class=\"glyphicon glyphicon-download\"></span></a>";
                        },editable:true,edittype:'file',editoptions:{enctype:'multipart/form-data'}}
                ],
                rowNum:20,
                pager: '#'+subgridPager,
                height: '100%',
                autowidth:true,
                styleUI:"Bootstrap",
                editurl: '${path}/chapter/alter?albumId='+row_id,
            });
            //配置子页的菜单栏
            $("#"+subgridTable).jqGrid('navGrid','#'+subgridPager,
                {edit:true,add:true,del:true},
                {closeAfterEdit:true,},//edit
                {closeAfterAdd:true,afterSubmit:function (response, postDate) {
                        var chapterId = response.responseJSON.chapterId;
                        console.log(response.responseJSON);
                        $.ajaxFileUpload({
                            url:'${path}/chapter/upload',
                            data: {chapterId:chapterId },
                            datatype:'json',
                            type:'post',
                            fileElementId:'voiceurl',
                            success:function (data) {
                                //输出上传成功
                                alert("上传成功！");
                                //jqgrid重新载入
                                $("#"+subgridTable).trigger('reloadGrid');//刷新表格页面
                            }
                        });//ajaxFileUpload
                        return postDate;
                    }},//add
                {},
                {sopt:['eq','ne','cn']},//配置搜索条件
            );//配置子页的菜单栏结束
        }


    })
</script>


<div class="col-sm-12">
    <%--创建表格--%>
    <table id="albumList"></table>
    <%--工具栏 --%>
    <div id="albumPager"></div>
</div>