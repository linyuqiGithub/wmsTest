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
    <script type="text/javascript" src="/js/common_page.js"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <title>PSS-系统菜单管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
</head>
<body>
<%@include file="/WEB-INF/views/common/common_msg.jsp" %>
<s:debug/>
<s:form id="searchForm" action="systemMenu" namespace="/" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_bottom">
                                <%-- 通过拼接url的方式传递参数qo.parentId，防止丢失父菜单 --%>
                                <s:url  var="myUrl" namespace="/" action="systemMenu_input">
                                    <s:param name="qo.parentId" value="qo.parentId"/>
                                </s:url>
                        <input type="button" value="新增" class="ui_input_btn01 btn_input"
                               data-url="<s:property value="myUrl"/>"/>
                    </div>
                </div>
            </div>
        </div>
        <%--若进入的是根目录菜单,那么返回的parentMenus为null,所有只有根目录这一行--%>
        当前:
        <s:a namespace="/" action="systemMenu">根目录</s:a>
        <s:iterator value="#parentMenus"> >
            <s:a namespace="/" action="systemMenu">
                <s:param name="qo.parentId" value="id"/>
                <s:property value="name"/>
            </s:a>
        </s:iterator>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all"/></th>
                        <th>编号</th>
                        <th>菜单编码</th>
                        <th>菜单名称</th>
                        <th>父目录</th>
                        <th>URL</th>
                        <th>操作</th>
                    </tr>
                    <tbody>
                    <s:iterator value="#pageResult.data">
                        <tr>
                            <td><input type="checkbox" name="IDCheck" class="acb" data-oid="<s:property value="id"/>"/>
                            <td><s:property value="id"/></td>
                            <td><s:property value="sn"/></td>
                            <td><s:property value="name"/></td>
                            <td><s:property value="parentName"/></td>
                            <td><s:property value="url"/></td>
                            <td>
                                <s:a namespace="/" action="systemMenu_input">
                                    <s:param name="systemMenu.id" value="id"/>
                                    <s:param name="qo.parentId" value="qo.parentId"/>
                                    编辑</s:a>

                                <s:url namespace="/" action="systemMenu_delete" var="deleteUrl">
                                    <s:param name="systemMenu.id" value="id"/>
                                    <%-- 由于删除菜单发送的是ajax请求，完成删除操作后是当前页面reload，所以不需要传递父菜单id，也不会丢失当前页面 --%>
                                </s:url>
                                <a href="javascript:;" class="btn_delete" data-url="<s:property value="#deleteUrl"/>">
                                    删除

                                <%-- 将当前菜单的id作为父菜单的id，查询的结果就为该id下的子菜单 --%>
                                <s:a namespace="/" action="systemMenu">
                                    <s:param name="qo.parentId" value="id"/>
                                    查看子菜单
                                </s:a>
                            </td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
            </div>
        </div>
        <s:hidden name="qo.parentId"/>
        <%@include file="/WEB-INF/views/common/common_page.jsp" %>
    </div>
</s:form>
</body>
</html>
