<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/plugins/Highcharts-4.0.4/highcharts.js"></script>

    <title>PSS-销售报表</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
    <script>
        $(function () {
            $('#container').highcharts({
                //父标题
                title: {
                    text: '销售报表',
                    x: -20 //center
                },
                //子标题
                subtitle: {
                    text: '按<s:property value="#groupByName"/>分组',
                    x: -20
                },
                //X轴
                xAxis: {
                    //x轴应当输出的数据,这里应该是页面中对应的分组数据
                    /*escapeHtml=false表示特殊字符原样输出*/
                    categories: <s:property value="#groupByList"  escapeHtml="false"/>
                },
                //Y轴
                yAxis: {
                    title: {
                        text:'销售总额'
                    },
                    plotLines: [{
                        value: 0,//表示原点的值为0
                        width: 1,//表示高度为1的颜色是#808080，貌似没什么用
                        color: '#808080'
                    }]
                },
                tooltip: {
                    /*鼠标放在数据上后显示的单位*/
                    valueSuffix: '元'
                },
                //图例说明
                legend: {
                    //垂直排列图例说明
                    layout: 'vertical',
                    //图例说明放在右边
                    align: 'right',
                    //居中
                    verticalAlign: 'middle',
                    //边框宽度
                    borderWidth: 0
                },
                //存放数据的地方
                series: [{
                    name: '销售额',
                    data: <s:property value="#totalAmount" escapeHtml="false"/>
                }]
            });
        });
    </script>
</head>
<body>
<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
</body>
</html>
