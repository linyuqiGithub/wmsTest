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
    <title>PSS-采购入库单管理</title>
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
                    //业务起始时间的最大值不能超过业务的结束时间,若没有结束时间,则不能大于当前时间
                    maxDate: $("input[name='qo.endDate']").val() || new Date()
                });
            });
            $("input[name='qo.endDate']").addClass("Wdate").click(function () {
                WdatePicker({
                    //业务时间最大值不能超过当前时间
                    maxDate: new Date(),
                    //结束时间最小是不能小于业务起始时间
                    minDate: $("input[name='qo.beginDate']").val()
                });
            });
        });
    </script>
</head>
<body>
<%@include file="/WEB-INF/views/common/common_msg.jsp" %>
<s:form id="searchForm" action="stockIncomebill" namespace="/" method="post" theme="simple">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        业务时间
                        <s:date name="qo.beginDate" var="beginDate" format="yyyy-MM-dd"/>
                        <s:textfield name="qo.beginDate" class="ui_input_txt02" value="%{#beginDate}"/>
                        ~
                        <s:date name="qo.endDate" var="endDate" format="yyyy-MM-dd"/>
                        <s:textfield name="qo.endDate" class="ui_input_txt02" value="%{#endDate}"/>
                        仓库
                        <s:select list="#depots" listKey="id" listValue="name" name="qo.depotId"
                                  headerKey="-1" headerValue="全部" class="ui_select01"/>
                        状态
                        <s:select list="#{'0':'未审核','1':'已审核'}" name="qo.status" headerKey="-1"
                                  headerValue="全部" class="ui_select01"/>
                    </div>
                    <div id="box_bottom">
                        <input type="button" value="查询" class="ui_input_btn01 btn_page" data-page="1"/>
                        <input type="button" value="新增" class="ui_input_btn01 btn_input"
                               data-url="<s:url namespace="/" action="stockIncomebill_input"/>"/>
                            <%--          <input type="button" value="批量删除" class="ui_input_btn01 btn_batchDelete"
                                             data-url="<s:url namespace="/" action="stockIncomebill_batchDelete"/>"/>--%>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all"/></th>
                        <th>入库单编号</th>
                        <th>业务时间</th>
                        <th>仓库</th>
                        <th>入库总数量</th>
                        <th>入库总金额</th>
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
                            <td><s:property value="depot.name"/></td>
                            <td><s:property value="totalNumber"/></td>
                            <td><s:property value="totalAmount"/></td>
                            <td><s:property value="inputUser.name"/></td>
                            <td><s:property value="auditor.name"/></td>

                            <s:if test="status == 0">
                                <td>
                                    <span style="color: green">未审核</span>
                                </td>
                                <td>
                                    <s:url namespace="" action="stockIncomebill_audit" var="auditUrl">
                                        <%--url携带的参数,可以将id值封装到action中提供了getter的stockIncomebill对象中--%>
                                        <%--需要使用ajax发送请求的链接才需要使用url标签,否则可以直接使用struts的a标签--%>
                                        <s:param name="stockIncomebill.id" value="id"></s:param>
                                    </s:url>
                                    <a href="javascript:;" class="btn_audit" data-url="<s:property value="#auditUrl"/>">
                                        审核
                                    </a>
                                    <s:a namespace="" action="stockIncomebill_input">
                                        <s:param name="stockIncomebill.id" value="id"></s:param>
                                        编辑</s:a>
                                    <s:url namespace="" action="stockIncomebill_delete" var="deleteUrl">
                                        <s:param name="stockIncomebill.id" value="id"></s:param>
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
                                    <%--跳转到show页面,因为已经审核,所以该页面只能查看,不能编辑--%>
                                    <s:a namespace="" action="stockIncomebill_show">
                                        <s:param name="stockIncomebill.id" value="id"></s:param>
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
