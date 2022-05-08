<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="/style/common_style.css" rel="stylesheet" type="text/css">
<link rel="/stylesheet" type="text/css" href="style/authority/jquery.fancybox-1.3.4.css" media="screen"/>
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<script type="text/javascript" src="/js/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>
<script type="text/javascript" src="/js/common_page.js"></script>

<title>PSS-权限管理</title>
<style>
	.alt td{ background:black !important;}
</style>
</head>
<%@include file="/WEB-INF/views/common/common_msg.jsp"%>
<body>
	<s:form id="searchForm" action="permission" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_bottom">
							<input type="button" value="批量删除" class="ui_input_btn01 batch_delete" data-url='<s:url namespace="/" action="permission_batchDelete"/>'/>
							<!-- <input type="button" value="查询" class="ui_input_btn01 page_btn"/> -->
							<input type="button" value="加载权限" class="ui_input_btn01 loadpermission_btn" data-url='<s:url action="permission_reload" />'/>
						</div>
					</div>
				</div>
			</div>
			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
						<tr>
							<th width="30"><input type="checkbox" id="all"/></th>
							<th>编号</th>
							<th>权限表达式</th>
							<th>权限名称</th>
							<th></th>
						</tr>
						<tbody>
							<s:iterator value="#pageResult.data">
								<tr>
									<td><input type="checkbox" name="IDCheck" class="acb" data-id="<s:property value="id"/>"/></td>
									<td><s:property value="id"/></td>
									<td style="text-align:left;padding-left: 50px;"><s:property value="expression"/></td>
									<td><s:property value="name"/></td>
									<td>
										<s:url namespace="/" action="permission_delete" var="deleteUrl">
											<s:param name="permission.id" value="id"/>
										</s:url>
										<a href="javascript:;" class="btn_delete" data-url="<s:property value="#deleteUrl"/>">删除</a>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
				<%@include file="/WEB-INF/views/common/common_page.jsp"%>
			</div>
		</div>
	</s:form>
</body>
</html>
