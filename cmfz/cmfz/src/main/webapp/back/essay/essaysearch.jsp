<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTf-8" %>
<%
    String path = request.getContextPath();
    pageContext.setAttribute("path", path);
%>
<html>
<head>
    <title>文章搜索</title>
    <script>
        $(function () {

        })
        function toSearch() {
            let searchString = $('#exampleInputAmount').val();
            $.ajax({
                url:'${path}/essay/search',
                method:'post',
                data:{searchString:searchString,page:1,size:5},
                dataType:"json",
                success:function (data) {
                    $('#contentHtml').empty();
                    console.log(data);
                    var essays =  data.essays;
                    essays.forEach(function (essay) {
                        let ul = " <ul>\n" +
                            "                <li>"+essay.essayTittle+"</li>\n" +
                            "                <li>"+essay.author+"</li>\n" +
                            "                <li>"+essay.essayContent+"</li>\n" +
                            "                <li>"+essay.essayTime+"</li>\n" +
                            "            </ul>";
                        $('#contentHtml').append(ul);
                    })
                   var p ="<p>共"+data.totalHits+"条记录</p>"
                }
            })
        }
    </script>
</head>
<body>
<div class="row">
    <div class="col-sm-8 col-sm-offset-2" style="text-align: center"><h1>文章搜索</h1></div>
    <div class="col-sm-offset-2 col-sm-7">
            <div class="form-group">
                <label class="sr-only" for="exampleInputAmount">请输入关键词</label>
                <div class="input-group">
                    <div class="input-group-addon">检索文章</div>
                    <input type="text" name="searchString" class="form-control" id="exampleInputAmount"  placeholder="请输入关键词">
                    <input type="hidden" name="pageNum" value="1"/>
                    <input type="hidden" name="pageSize" value="10"/>
                </div>
            </div>

    </div>
    <div class="col-sm-2" style="margin-left: 0;"> <button type="button" onclick="toSearch();" id="submit" class="btn btn-primary">检索</button> </div>
</div>
<div class="row">
    <div class="col-sm-2">

    </div>
    <div class=" col-sm-10" id="contentHtml">


    </div>
</div>
</body>
</html>
