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
<s:debug/>
<%@include file="/WEB-INF/views/common/common_msg.jsp"%>
<s:form name="editForm" namespace="/" action="systemMenu_saveOrUpdate" method="post" id="editForm" theme="simple">
    <s:hidden name="systemMenu.id"/>
    <div id="container">
        <div id="nav_links">
            <span style="color: #1A5CC6;">系统菜单编辑</span>
            <div id="page_close">
                <a>
                    <img src="images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
                </a>
            </div>
        </div>
        <div class="ui_content">
            <table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
                            <tr>
                                <td class="ui_text_rt" width="140">上级菜单</td>
                                <td class="ui_text_lt">
                                    <%-- 从context区域取出Action的input方法中存入的父菜单的菜单名 --%>
                                    <s:textfield name="parentName" readonly="true" disabled="true" cssClass="ui_input_txt02" />
                                    <%-- 用于提交保存时，保证新增后的菜单仍属于当前父菜单下，否则会保存到根目录下--%>
                                    <s:hidden name="qo.parentId"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="ui_text_rt" width="140">菜单名称</td>
                                <td class="ui_text_lt">
                                    <s:textfield name="systemMenu.name" cssClass="ui_input_txt02" />
                                </td>
                            </tr>
                            <tr>
                                <td class="ui_text_rt" width="140">菜单编码</td>
                                <td class="ui_text_lt">
                                    <s:textfield name="systemMenu.sn" cssClass="ui_input_txt02" />
                                </td>
                            </tr>
                            <tr>
                                <td class="ui_text_rt" width="140">URL</td>
                                <td class="ui_text_lt">
                                    <s:textfield name="systemMenu.url" cssClass="ui_input_txt02" />
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