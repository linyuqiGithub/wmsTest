<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <script type="text/javascript" src="/js/common_page.js"></script>
    <script type="text/javascript" src="/js/plugins/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="/js/plugins/artDialog/iframeTools.js"></script>


    <title>PSS-销售报表</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
    <script>
        $(function () {
            $("input[name='sqo.beginDate']").addClass("Wdate").click(function () {
                WdatePicker({
                    maxDate: $("input[name='sqo.endDate']").val() || new Date()
                });
            });
            $("input[name='sqo.endDate']").addClass("Wdate").click(function () {
                WdatePicker({
                    maxDate: new Date(),
                    minDate: $("input[name='sqo.beginDate']").val()
                });
            });
            //图表绑定改变事件
            $("#chart_select").change(function () {
                if('Line'==$(this).val()){//线性图
                    //$("#searchForm").serialize()将表单的所有参数和值序列化成字符串传递
                    var url = "/chart_saleChartByLine.action?"+$("#searchForm").serialize();
                    $.dialog.open(url,{
                        id:"saleChart",
                        title:"销售报表",
                        width:900,
                        height:400
                    });
                }else if('Pie'==$(this).val()){//饼状图
                    var url = "/chart_saleChartByPie.action?"+$("#searchForm").serialize();
                    $.dialog.open(url,{
                        id:"saleChart",
                        title:"销售报表",
                        width:900,
                        height:400
                    });
                }
            });
            //导出报表
            $(".btn_export").click(function (){
                var sqo = $(this).data("sqo");
               window.location.href="chart_exportSaleExcel.action?"+sqo;
            })


        });
    </script>
</head>
<body>
<%@include file="/WEB-INF/views/common/common_msg.jsp" %>
<s:form id="searchForm" action="chart_saleChart" namespace="/" method="post" theme="simple">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        业务时间
                        <s:date name="sqo.beginDate" var="beginDate" format="yyyy-MM-dd"/>
                        <s:textfield name="sqo.beginDate" class="ui_input_txt02" value="%{#beginDate}"/>
                        ~
                        <s:date name="sqo.endDate" var="endDate" format="yyyy-MM-dd"/>
                        <s:textfield name="sqo.endDate" class="ui_input_txt02" value="%{#endDate}"/>
                        货品
                        <s:textfield name="sqo.keyword" class="ui_input_txt02" placeholder="货品编码/货品名称"/>
                        客户
                        <s:select list="#clients" listKey="id" listValue="name" name="sqo.clientId"
                                  headerKey="-1" headerValue="全部" class="ui_select01"/>

                        品牌
                        <s:select list="#brands" listKey="id" listValue="name" name="sqo.brandId"
                                  headerKey="-1" headerValue="全部" class="ui_select01"/>
                        &nbsp;&nbsp;&nbsp;图表
                        <s:select list="#{'Line':'线性图','Pie':'饼状图'}" class="ui_select01" id="chart_select"/>
                        分组
                        <s:select list="#types" name="sqo.groupBy" class="ui_select01"/>
                    </div>
                    <div id="box_bottom">
                        <input type="button" value="查询" class="ui_input_btn01 btn_page" data-page="1"/>
                        <input type="button" value="导出报表" class="ui_input_btn01 btn_export" data-sqo="<s:property value="sqo"/>"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all"/></th>
                        <th>分组类型</th>
                        <th>采购总数量</th>
                        <th>采购总金额</th>
                        <th>毛利润</th>
                    </tr>
                    <tbody>
                    <s:iterator value="#list">
                        <tr>
                            <td><input type="checkbox" name="IDCheck" class="acb" data-oid="<s:property value="id"/>"/>
                            </td>
                            <td><s:property value="groupBy"/></td>
                            <td><s:property value="totalNumber"/></td>
                            <td><s:property value="totalAmount"/></td>
                            <td><s:property value="grossProfit"/></td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</s:form>
</body>
</html>
