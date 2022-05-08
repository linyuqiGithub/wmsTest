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
    <title>PSS-产品订单管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
    <script>
        $(function () {
            //给业务时间添加样式
            $("input[name='qo.beginDate']").addClass("Wdate").click(function () {
                WdatePicker({
                    //限制开始日期最大不能超过查询的截止日期或者今天
                    maxDate: $("input[name='qo.endDate']").val() || new Date()
                });
            });
            $("input[name='qo.endDate']").addClass("Wdate").click(function () {
                WdatePicker({
                    //限制截止日期最大不能超过今天
                    maxDate: new Date(),
                    //限制截至日期最小不能小于查询的开始日期
                    minDate: $("input[name='qo.beginDate']").val()
                });
            });
        });

    </script>
</head>
<body>
<%@include file="/WEB-INF/views/common/common_msg.jsp" %>
<s:form id="searchForm" action="orderbill" namespace="/" method="post" theme="simple">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        业务时间
                        <s:date name="qo.beginDate" var="beginDate" format="yyyy-MM-dd"/>
                        <%-- 将上面固定格式的日期显示在下面的输入框中 --%>
                        <s:textfield name="qo.beginDate" class="ui_input_txt02" value="%{#beginDate}"/>
                        ~
                        <s:date name="qo.endDate" var="endDate" format="yyyy-MM-dd"/>
                        <s:textfield name="qo.endDate" class="ui_input_txt02" value="%{#endDate}"/>
                        供应商
                        <s:select list="#suppliers" listKey="id" listValue="name" name="qo.supplierId"
                                  headerKey="-1" headerValue="全部" class="ui_select01"/>
                        状态
                        <s:select list="#{'0':'未审核','1':'已审核'}" name="qo.status" headerKey="-1"
                                  headerValue="全部" class="ui_select01"/>
                    </div>
                    <div id="box_bottom">
                        <input type="button" value="查询" class="ui_input_btn01 btn_page" data-page="1"/>
                        <input type="button" value="新增" class="ui_input_btn01 btn_input"
                               data-url="<s:url namespace="/" action="orderbill_input"/>"/>
                            <%--          <input type="button" value="批量删除" class="ui_input_btn01 btn_batchDelete"
                                             data-url="<s:url namespace="/" action="orderbill_batchDelete"/>"/>--%>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all"/></th>
                        <th>订单编号</th>
                        <th>业务时间</th>
                        <th>供应商</th>
                        <th>采购数量</th>
                        <th>采购金额</th>
                        <th>录入人</th>
                        <th>审核人</th>
                        <th>审核状态</th>
                        <th>操作</th>
                    </tr>
                    <tbody>
                    <s:iterator value="#pageResult.data">
                        <tr>
                            <td><input type="checkbox" name="IDCheck" class="acb" data-oid="<s:property value="id"/>"/>
                            </td>
                            <td><s:property value="sn"/></td>
                            <td><s:date name="vdate" format="yyyy-MM-dd"/></td>
                            <td><s:property value="supplier.name"/></td>
                            <td><s:property value="totalNumber"/></td>
                            <td><s:property value="totalAmount"/></td>
                            <td><s:property value="inputUser.name"/></td>
                            <td><s:property value="auditor.name"/></td>

                            <s:if test="status == 0">
                                <td>
                                    <span style="color: green">未审核</span>
                                </td>
                                <td>
                                    <s:url namespace="" action="orderbill_audit" var="auditUrl">
                                        <s:param name="orderbill.id" value="id"></s:param>
                                    </s:url>
                                    <a href="javascript:;" class="btn_audit" data-url="<s:property value="#auditUrl"/>">
                                        审核
                                    </a>
                                    <s:a namespace="" action="orderbill_input">
                                        <s:param name="orderbill.id" value="id"></s:param>
                                        编辑</s:a>
                                    <s:url namespace="" action="orderbill_delete" var="deleteUrl">
                                        <s:param name="orderbill.id" value="id"></s:param>
                                    </s:url>
                                    <a href="javascript:;" class="btn_delete"
                                       data-url="<s:property value="#deleteUrl"/>">
                                        删除
                                    </a>
                                </td>
                            </s:if>
                            <s:else>
                                <td>
                                    <span style="color: red">已审核</span>
                                </td>
                                <td>
                                    <s:a namespace="" action="orderbill_show">
                                        <s:param name="orderbill.id" value="id"></s:param>
                                        查看</s:a>
                                </td>
                            </s:else>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
            </div>
        </div>
        <%@include file="/WEB-INF/views/common/common_page.jsp" %>
    </div>
</s:form>
</body>
</html>
