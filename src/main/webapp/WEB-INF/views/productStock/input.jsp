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
</head>
<body>
<!-- =============================================== -->
<%@include file="/WEB-INF/views/common/common_msg.jsp"%>
<s:form name="editForm" namespace="/" action="productStock_saveOrUpdate" method="post" id="editForm" theme="simple">
    <div id="container">
        <div id="nav_links">
            <span style="color: #1A5CC6;">产品库存编辑</span>
            <div id="page_close">
                <a>
                    <img src="images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
                </a>
            </div>
        </div>
        <div class="ui_content">
            <table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
                <s:hidden name="productStock.id"></s:hidden>
                            <tr>
                                <td class="ui_text_rt" width="140">库存总金额</td>
                                <td class="ui_text_lt">
                                    <s:textfield name="productStock.amount" cssClass="ui_input_txt02" ></s:textfield>
                                </td>
                            </tr>
                            <tr>
                                <td class="ui_text_rt" width="140">货品</td>
                                <td class="ui_text_lt">
                                    <s:textfield name="productStock.product" cssClass="ui_input_txt02" ></s:textfield>
                                </td>
                            </tr>
                            <tr>
                                <td class="ui_text_rt" width="140">库存出库时间</td>
                                <td class="ui_text_lt">
                                    <s:textfield name="productStock.outcomeDate" cssClass="ui_input_txt02" ></s:textfield>
                                </td>
                            </tr>
                            <tr>
                                <td class="ui_text_rt" width="140">库存数量</td>
                                <td class="ui_text_lt">
                                    <s:textfield name="productStock.storeNumber" cssClass="ui_input_txt02" ></s:textfield>
                                </td>
                            </tr>
                            <tr>
                                <td class="ui_text_rt" width="140">库存价格</td>
                                <td class="ui_text_lt">
                                    <s:textfield name="productStock.price" cssClass="ui_input_txt02" ></s:textfield>
                                </td>
                            </tr>
                            <tr>
                                <td class="ui_text_rt" width="140">仓库</td>
                                <td class="ui_text_lt">
                                    <s:textfield name="productStock.depot" cssClass="ui_input_txt02" ></s:textfield>
                                </td>
                            </tr>
                            <tr>
                                <td class="ui_text_rt" width="140">库存入库时间</td>
                                <td class="ui_text_lt">
                                    <s:textfield name="productStock.incomeDate" cssClass="ui_input_txt02" ></s:textfield>
                                </td>
                            </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td class="ui_text_lt">
                        &nbsp;<input type="submit" value="确定保存" class="ui_input_btn01"/>
                        &nbsp;<input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</s:form>
</body>
</html>