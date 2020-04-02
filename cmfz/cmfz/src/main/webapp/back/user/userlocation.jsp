<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTf-8" %>
<%
    String path = request.getContextPath();
    pageContext.setAttribute("path", path);
%>
<script src="${path}/static/echarts/echarts.min.js"></script>
<script src="${path}/static/echarts/china.js"></script>

<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>
<script>
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    $.get('${path}/user/userLocation', function (chinaJson) {
        var option = {
            title: {
                text: '全国地图大数据'
            },
            tooltip : {
                trigger: 'item'
            },
            //左侧小导航图标
            visualMap: {
                show : true,
                x: 'left',
                y: 'center'
            },
            series: [{
                name: '用户分布图',
                type: 'map',
                mapType: 'china',
                roam:true,
                label:{
                    normal:{
                        show:false,
                    },
                    emphasis:{
                        show:false,
                    }
                },
                data:chinaJson,
            }]
        };
        console.log(chinaJson);
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    },'json');

    var goEasy1 = new GoEasy({
        host:'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
        appkey: "BC-6a103e6f384541fabea52ff5f802b2ed", //替换为您的应用appkey
    });
    //接收推送信息
    goEasy1.subscribe({
        channel: "cmfzLocation", //替换为您自己的channel
        onMessage: function (message) {
            console.log("Channel:" + message.channel + " content:" + message.content);
            let data = JSON.parse(message.content);

            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: '全国地图大数据'
                },
                tooltip : {
                    trigger: 'item'
                },
                //左侧小导航图标
                visualMap: {
                    show : true,
                    x: 'left',
                    y: 'center'
                },
                series: [{
                    name: '用户分布图',
                    type: 'map',
                    mapType: 'china',
                    roam:true,
                    label:{
                        normal:{
                            show:false,
                        },
                        emphasis:{
                            show:false,
                        }
                    },
                    data:data,
                }]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
    });
</script>