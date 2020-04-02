<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTf-8" %>
<%
    String path = request.getContextPath();
    pageContext.setAttribute("path", path);
%>
<script src="${path}/static/echarts/echarts.min.js"></script>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
    $.ajax({
        url:'${path}/user/findRegisterDevelopment',
        method:'post',
        data:{},
        dataType:'json',
        success:function (data) {

            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: '持明法洲用户注册趋势图'
                },
                tooltip: {},
                legend: {
                    data:['男','女']
                },
                xAxis: {
                    data: ["最近一天","最近一周","最近一月","最近一年"]
                },
                yAxis: {},
                series: [{
                    name: '男',
                    type: 'bar',
                    data: data.man
                },
                {
                    name: '女',
                    type: 'bar',
                    data: data.woman
                }

                ]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
    })
    var goEasy = new GoEasy({
        host:'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
        appkey: "BC-6a103e6f384541fabea52ff5f802b2ed", //替换为您的应用appkey
    });
    //接收推送信息
    goEasy.subscribe({
        channel: "cmfzDevelopment", //替换为您自己的channel
        onMessage: function (message) {
            console.log("Channel:" + message.channel + " content:" + message.content);
            let data = JSON.parse(message.content);

            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: '持明法洲用户注册趋势图'
                },
                tooltip: {},
                legend: {
                    data:['男','女']
                },
                xAxis: {
                    data: ["最近一天","最近一周","最近一月","最近一年"]
                },
                yAxis: {},
                series: [{
                    name: '男',
                    type: 'bar',
                    data: data.man
                },
                    {
                        name: '女',
                        type: 'bar',
                        data: data.woman
                    }

                ]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
    });




</script>