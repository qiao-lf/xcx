<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '持名法舟用户注册趋势图'
        },
        tooltip: {},
        legend: {
            data: ['男', '女']
        },
        xAxis: {
            data: ["1天", "7天", "30天", "1年"]
        },
        yAxis: {},
        series: [],
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    // Ajax异步数据回显
    $.get("${pageContext.request.contextPath}/user/qushi", function (data) {

        //var parse = JSON.parse(data);
        //console.log(parse);
        console.log(data)
        myChart.setOption({
            series: [
                {
                    name: '男',
                    type: 'bar',
                    data: data.men,
                }, {
                    name: '女',
                    type: 'bar',
                    data: data.women,
                }
            ]
        })
    }, "json")
</script>
