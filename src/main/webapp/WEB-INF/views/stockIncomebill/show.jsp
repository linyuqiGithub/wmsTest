<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>信息管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css"/>
    <link href="/style/common_style.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/jquery/jquery.validate.min.js"></script>
    <script type="text/javascript" src="/js/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
    <script type="text/javascript" src="/js/plugins/artDialog/iframeTools.js"></script>
    <script>
        $(function () {
            $("*").prop("readOnly",true);
        })
    </script>
</head>
<body>
<!-- =============================================== -->
<%@include file="/WEB-INF/views/common/common_msg.jsp" %>
<s:form name="editForm" namespace="/" action="stockIncomebill_saveOrUpdate" method="post" id="editForm" theme="simple">
    <div id="container">
        <div id="nav_links">
            <span style="color: #1A5CC6;">入库订单查看</span>
            <div id="page_close">
                <a>
                    <img src="images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
                </a>
            </div>
        </div>
        <div class="ui_content">
            <table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
                <s:hidden name="stockIncomebill.id"></s:hidden>
                <tr>
                    <td class="ui_text_rt" width="140">入库订单编号</td>
                    <td class="ui_text_lt">
                        <s:textfield name="stockIncomebill.sn" cssClass="ui_input_txt02"></s:textfield>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">入库仓库</td>
                    <td class="ui_text_lt">
                        <s:textfield name="stockIncomebill.depot.name" cssClass="ui_input_txt02"></s:textfield>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">业务时间</td>
                    <td class="ui_text_lt">
                    <s:date name="stockIncomebill.vdate" var="vdate" format="yyyy-MM-dd"/>
                        <s:textfield name="stockIncomebill.vdate" class="ui_input_txt02" value="%{#vdate}"/>
                    </td>
                </tr>

                <tr>
                    <td class="ui_text_rt" width="140">明细</td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <table class="edit_table" cellspacing="0" cellpadding="0" border="0" style="width: auto">
                            <thead>
                            <tr>
                                <th width="10"></th>
                                <th width="200">货品</th>
                                <th width="120">品牌</th>
                                <th width="80">价格</th>
                                <th width="80">数量</th>
                                <th width="80">金额小计</th>
                                <th width="150">备注</th>
                            </tr>
                            </thead>
                            <tbody id="edit_table_body">
                                <s:iterator value="stockIncomebill.items">
                                    <tr>
                                        <td></td>
                                        <td>
                                            <s:textfield disabled="true" readonly="true" cssClass="ui_input_txt04"
                                                         name="product.name" tag="name"/>
                                            <img src="/images/common/search.png" class="searchproduct"/>
                                            <s:hidden name="product.id" tag="pid"/>
                                        </td>
                                        <td><span tag="brand"><s:property value="product.brand.name"/></span></td>
                                        <td><s:textfield tag="costPrice" name="costPrice"
                                                         cssClass="ui_input_txt04"/></td>
                                        <td><s:textfield tag="number" name="number"
                                                         cssClass="ui_input_txt04"/></td>
                                        <td><span tag="amount"><s:property value="amount"/></span></td>
                                        <td><s:textfield tag="remark" name="remark"
                                                         cssClass="ui_input_txt04"/></td>
                                    </tr>
                                </s:iterator>

                            </tbody>
                        </table>
                    </td>
                </tr>

                <tr>
                    <td>&nbsp;</td>
                    <td class="ui_text_lt">
                        &nbsp;<input type="button" value="返回列表界面" onclick="history.back()" class="ui_input_btn01"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</s:form>
</body>
</html>