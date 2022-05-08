<%@ page language="java" contentType="text/html;charset=UTF-8"%>
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
    <%-- $表示从数据模型中根据key去找value --%>
    <title>PSS-${objectCNName}管理</title>
    <style>
        .alt td{ background:black !important;}
    </style>
</head>
<body>
<%@include file="/WEB-INF/views/common/common_msg.jsp"%>
 <s:form id="searchForm" action="${objectName}" namespace="/" method="post" theme="simple">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
 <%--                   <div id="box_center">
                        姓名/邮箱
                        <s:textfield name="qo.keyword" class="ui_input_txt02"/>
                        所属部门
                        <s:select list="#depts" listKey="id" listValue="name" name="qo.deptId"
                                  headerKey="-1" headerValue="全部" class="ui_select01"/>
                    </div>--%>
                    <div id="box_bottom">
<%--
                        <input type="button" value="查询" class="ui_input_btn01 btn_page"  data-page="1"/>
--%>
                        <input type="button" value="新增" class="ui_input_btn01 btn_input"
                               data-url="<s:url namespace="/" action="${objectName}_input"/>"/>
              <%--          <input type="button" value="批量删除" class="ui_input_btn01 btn_batchDelete"
                               data-url="<s:url namespace="/" action="${objectName}_batchDelete"/>"/>--%>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all" /></th>
                        <th>编号</th>
                        <%-- 定义一个变量，接收数据模型里面的表达式 --%>
                        <%-- 变量h获取到数据模型中的fieldMap(存放了类的属性英文名和中文名的键值对) --%>
                        <#assign h = fieldMap>
                            <%-- 定义一个变量keys，h?keys代表获取到h(fieldMap)中的所有key(属性英文名)的集合 --%>
                            <#assign keys = h?keys>
                                <%-- 遍历keys集合，把每次遍历的数据赋值给key(属性英文名) --%>
                                <#list keys as key>
                                    <%-- 根据key(属性英文名)获取h(fieldMap)的value(属性中文名) --%>
                                    <th>${h[key]}</th>
                                </#list>
                        <th>操作</th>
                    </tr>
                    <tbody>
                    <s:iterator value="#pageResult.data">
                        <tr>
                            <td><input type="checkbox" name="IDCheck" class="acb" data-oid="<s:property value="id"/>"/></td>
                            <td><s:property value="id"/></td>
                            <#list keys as key>
                                <td><s:property value="${key}"/> </td>
                            </#list>
                            <td>
                                <s:a namespace="" action="${objectName}_input">
                                    <s:param name="${objectName}.id" value="id"></s:param>
                                    编辑</s:a>
                                <s:url namespace="" action="${objectName}_delete" var="deleteUrl">
                                    <s:param name="${objectName}.id" value="id"></s:param>
                                </s:url>
                                <a href="javascript:;" class="btn_delete" data-url="<s:property value="#deleteUrl"/>">
                                    删除
                                </a>
                            </td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
            </div>
        </div>
        <%@include file="/WEB-INF/views/common/common_page.jsp"%>
    </div>
</s:form>
</body>
</html>
