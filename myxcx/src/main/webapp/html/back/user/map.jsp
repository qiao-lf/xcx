<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
    $(function () {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('userMap'));
        var option = {
            title: {
                text: '用户分布图',
                subtext: '纯属虚构',
                left: 'center'
            },
            tooltip: {
                trigger: 'item'
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: ['男', '女']
            },
            visualMap: {
                left: 'left',
                top: 'bottom',
                text: ['高', '低'],           // 文本，默认为数值文本
                calculable: true
            },
            toolbox: {
                show: true,
                orient: 'vertical',
                left: 'right',
                top: 'center',
                feature: {
                    mark: {show: true},
                    dataView: {show: true, readOnly: false},
                    restore: {show: true},
                    saveAsImage: {show: true}
                }
            },
            series: []
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        $.get("${pageContext.request.contextPath}/user/showUsersLocation", function (data) {
            myChart.setOption({
                series: [{
                    name: '男',
                    type: 'map',
                    mapType: 'china',
                    roam: false,
                    label: {
                        normal: {
                            show: false
                        },
                        emphasis: {
                            show: true
                        }
                    },
                    data: data.man
                }, {
                    name: '女',
                    type: 'map',
                    mapType: 'china',
                    roam: false,
                    label: {
                        normal: {
                            show: false
                        },
                        emphasis: {
                            show: true
                        }
                    },
                    data: data.women
                }]
            })
        }, "json")
    })


</script>
<div id="userMap" style="width: 600px;height:400px;"></div>


<!--使用地图分布多个数据有一个bug 就是第一个数据如果不能完全覆盖地图，第二个数据在未覆盖的地图上无法显示
这是服务器原装的问题 并且 作为一个商业化产品 如果不能实现全国覆盖 就没有意义
-->