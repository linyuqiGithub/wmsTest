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

    <title>订货报表</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
    <script>
        $(function () {
            $("input[name='oqo.beginDate']").addClass("Wdate").click(function () {
                WdatePicker({
                    //限制最大日期为结束日期,若结束日期没有值,则最大日期为当前日期
                    maxDate: $("input[name='oqo.endDate']").val() || new Date()
                });
            });
            $("input[name='oqo.endDate']").addClass("Wdate").click(function () {
                WdatePicker({
                    //结束日期为最大值为当前日期,最小日期为开始日期的值
                    maxDate: new Date(),
                    minDate: $("input[name='oqo.beginDate']").val()
                });
            });
            //导出报表
            $(".btn_export").click(function (){
                var sqo = $(this).data("sqo");
                window.location.href="chart_exportOrderExcel.action?"+sqo;
            })
        });
    </script>
</head>
<body>
<%@include file="/WEB-INF/views/common/common_msg.jsp" %>
<s:form id="searchForm" action="chart_orderChart" namespace="/" method="post" theme="simple">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        业务时间
                        <s:date name="oqo.beginDate" var="beginDate" format="yyyy-MM-dd"/>
                        <s:textfield name="oqo.beginDate" class="ui_input_txt02" value="%{#beginDate}"/>
                        ~
                        <s:date name="oqo.endDate" var="endDate" format="yyyy-MM-dd"/>
                        <s:textfield name="oqo.endDate" class="ui_input_txt02" value="%{#endDate}"/>
                        货品
                        <s:textfield name="oqo.keyword" class="ui_input_txt02" placeholder="货品编码/货品名称"/>
                        供应商
                        <s:select list="#suppliers" listKey="id" listValue="name" name="oqo.supplierId"
                                  headerKey="-1" headerValue="全部" class="ui_select01"/>
                        品牌
                        <s:select list="#brands" listKey="id" listValue="name" name="oqo.brandId"
                                  headerKey="-1" headerValue="全部" class="ui_select01"/>
                        分组
                        <%--由于types本来封装的就是一个map集合,自带键值对,所以不需要提供listKey="id" listValue="name"字段--%>
                        <%--下拉框会自动根据键值对进行匹配--%>
                        <%--将选中的键key(e.name,p.name等等)赋值给oqo.groupBy,并提交给后台--%>
                        <%--后台mapper将提交过来的数据用#{groupBy}填充进sql语句中进行高级查询--%>
                        <s:select list="#types" name="oqo.groupBy" class="ui_select01"/>
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
                    </tr>
                    <tbody>
                    <s:iterator value="#list">
                        <tr>
                            <td><input type="checkbox" name="IDCheck" class="acb" data-oid="<s:property value="id"/>"/>
                            </td>
                            <%--这里的groupBy是OrderChartVO中的groupBy,也就是sql中的别名,用于封装OrderChartVO对象的groupBy分组类型字段--%>
                            <td><s:property value="groupBy"/></td>
                            <td><s:property value="totalNumber"/></td>
                            <td><s:property value="totalAmount"/></td>
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
