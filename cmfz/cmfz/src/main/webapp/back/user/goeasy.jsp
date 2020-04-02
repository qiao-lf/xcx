<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTf-8" %>
<%
    String path = request.getContextPath();
    pageContext.setAttribute("path", path);
%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>聊天室</title>
    <!--[if lte IE 8]>//如果需要支持低版本的IE8及以下
    <script  type="text/javascript" src="https://cdn.goeasy.io/json2.js"></script>
    <![endif]-->
    <script type="text/javascript" src="https://cdn.goeasy.io/goeasy-1.0.3.js"></script>
    <!--引入bootstrap的css样式-->
    <link rel="stylesheet" href="${path}/static/css/bootstrap.min.css">
    <!--引入jQuery-->
    <script src="${path}/static/js/jquery-3.4.1.min.js"></script>
    <!--引入bootstrap的js-->
    <script src="${path}/static/js/bootstrap.min.js"></script>
    <script>
        var goEasy = new GoEasy({
            host:'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
            appkey: "BC-6a103e6f384541fabea52ff5f802b2ed", //替换为您的应用appkey
        });

        goEasy.subscribe({
            channel: "cmfz", //替换为您自己的channel
            onMessage: function (message) {
                console.log(message.content);
                let parse = JSON.parse(message.content);
                if(parse.username!=$('#username').val()){
                    let msg ="<div class=\"msg-left pull-left\" align=\"left\">" +parse.username+":"+ parse.msg+"</div>";
                    $('#content').append(msg);
                }

            }
        });
        function sendMsg() {

             let msg =$('#msg').val();
             let val = $('#username').val();
             if(val==null||val==""){
                 alert("请输入用户名！");
                 return;
             }
             let mssg = `{"username":"`+val+`","msg":"`+msg+`"}`;
            let divmsg = "<div class=\"msg-right  pull-right\" align=\"right\">" +msg+":"+ val+"</div>";
             goEasy.publish({
                channel: "cmfz", //替换为您自己的channel
                message: mssg.toString()
            });
            $('#content').append(divmsg);
            $('#msg').val("");
        }
    </script>
    <style type="text/css">
        .msg-left{
            border: black solid 1px;
            width: 500px;
            margin-left: 10px;
            margin-top: 10px;
            border-radius: 8px;
            text-align: left;
        }
        .msg-right{
            border: black solid 1px;
            width: 500px;
            margin-right: 10px;
            margin-top: 10px;
            border-radius: 8px;
            text-align: right;

        }

    </style>
</head>
<body>
<div class="container-fluid" style="margin-top: 50px">
    <div class="col-sm-offset-2 col-sm-8">
        <input type="text" id="username" class="form-control" placeholder="请输入你的用户名...">
        <div style="border: black solid 1px;height: 500px" id="content">

        </div>
        <div class="input-group">
            <input type="text" id="msg" class="form-control" placeholder="请输入...">
            <span class="input-group-btn">
        <button class="btn btn-default" type="button" onclick="sendMsg();">发送!</button>
      </span>
        </div><!-- /input-group -->
    </div><!-- /.col-lg-6 -->

</div>
</body>
</html>