<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <link href="/style/jquery.fancybox.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
    <script type="text/javascript" src="/js/plugins/fancybox/jquery.fancybox.js"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <script type="text/javascript" src="/js/common_page.js"></script>
    <title>PSS-货品管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
    <script type="text/javascript">
       $(function () {
        //fancybox
        $(".fancybox").fancybox();
       })
    </script>
</head>
<body>
<%@include file="/WEB-INF/views/common/common_msg.jsp" %>
<s:form id="searchForm" action="product" namespace="/" method="post" theme="simple">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        货品编号/货品名称
                        <s:textfield name="qo.keyword" class="ui_input_txt02"/>
                        品牌名称
                        <s:select list="#brands" listKey="id" listValue="name" name="qo.brandId" headerKey="-1" headerValue="全部" class="ui_select01"/>
                    </div>
                    <div id="box_bottom">
                        <input type="button" value="查询" class="ui_input_btn01 btn_page" data-page="1"/>
                        <input type="button" value="新增" class="ui_input_btn01 btn_input"
                               data-url="<s:url namespace="/" action="product_input"/>"/>
                            <%--          <input type="button" value="批量删除" class="ui_input_btn01 btn_batchDelete"
                                             data-url="<s:url namespace="/" action="product_batchDelete"/>"/>--%>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all"/></th>
                        <th>货品图片</th>
                        <th>货品名称</th>
                        <th>货品编号</th>
                        <th>货品品牌</th>
                        <th>成本价</th>
                        <th>销售价</th>
                        <th>操作</th>
                    </tr>
                    <tbody>
                    <s:iterator value="#pageResult.data">
                        <tr>
                            <td><input type="checkbox" name="IDCheck" class="acb" data-oid="<s:property value="id"/>"/>
                            </td>
                            <td>
                                <%-- 点击后显示的是高清原图 --%>
                                <a class="fancybox" href='<s:property value="imagePath"/>' title='<s:property value="name"/>'>
                                <%-- 没点击显示的是压缩后的图片 --%>
                                <img src="<s:property value="smallImagePath"/>" width="55px"/>
                            </a>

                            </td>
                            <td><s:property value="name"/></td>
                            <td><s:property value="sn"/></td>
                            <td><s:property value="brand.name"/></td>
                            <td><s:property value="costPrice"/></td>
                            <td><s:property value="salePrice"/></td>
                            <td>
                                <s:a namespace="" action="product_input">
                                    <s:param name="product.id" value="id"></s:param>
                                    编辑</s:a>
                                <s:url namespace="" action="product_delete" var="deleteUrl">
                                    <s:param name="product.id" value="id"></s:param>
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
        <%@include file="/WEB-INF/views/common/common_page.jsp" %>
    </div>
</s:form>
</body>
</html>
