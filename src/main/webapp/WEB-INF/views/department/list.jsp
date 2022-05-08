<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="/style/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<script type="text/javascript" src="/js/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
<script type="text/javascript" src="/js/common_page.js"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>
<title>PSS-部门管理</title>
<style>
	.alt td{ background:black !important;}
</style>

</head>
<%@include file="/WEB-INF/views/common/common_msg.jsp"%>
<body>
	<s:form id="searchForm" action="department" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top"></div>
						<div id="box_bottom">
							<input type="button" value="批量删除" class="ui_input_btn01 batch_delete" data-url='<s:url namespace="/" action="department_batchDelete"/>'/>
							<input type="button" value="新增" class="ui_input_btn01 btn_input" data-url='<s:url namespace="/" action="department_input"/>'/> 
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
							<th>部门名称</th>
							<th>部门代码</th>
							<th>操作</th>
						</tr>
						<s:iterator value="#pageResult.data">
						<tbody>
							<tr>
								<td><input type="checkbox" name="IDCheck" class="acb" data-id="<s:property value="id"/>"/></td>
								<td><s:property value="id"/></td>
								<td><s:property value="name"/></td>
								<td><s:property value="sn"/></td>
								<td>
									<s:a action="department_input">
									   <s:param name="department.id" value="id"/>
									  编辑
									</s:a>

									<s:url namespace="/" action="department_delete" var="deleteUrl">
										<s:param name="department.id" value="id"/>
									</s:url>
									<a href="javascript:;" class="btn_delete" data-url="<s:property value="#deleteUrl"/>">删除</a>
								</td>
							</tr>
						</tbody>
					   </s:iterator>
					</table>
				</div>
				<%@include file="/WEB-INF/views/common/common_page.jsp"%>
			</div>
		</div>
	</s:form>
</body>
</html>
