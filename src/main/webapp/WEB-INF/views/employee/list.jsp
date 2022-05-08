<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="style/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<script type="text/javascript" src="/js/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>
<script type="text/javascript" src="/js/common_page.js"></script>
<title>PSS-账户管理</title>
<style>
	.alt td{ background:black !important;}
</style>

</head>
<%-- 提示信息页面 --%>
<%@include file="/WEB-INF/views/common/common_msg.jsp"%>

<body>
    <%-- struts标签会自动给相应的action加上后缀.action --%>
	<s:form id="searchForm" action="employee" method="post" theme="simple">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">
							姓名/邮箱
							<s:textfield name="qo.keyword" cssClass="ui_input_txt02"/>
							所属部门
							<s:select list="#depts" name="qo.deptId"
								headerKey="-1" headerValue="全部部门"
								listKey="id" listValue="name" cssClass="ui_select01"/>
						</div>
						<div id="box_bottom">
							<input type="button" value="查询" class="ui_input_btn01 btn_page" data-page="1"/>
							<input type="button" value="批量删除" class="ui_input_btn01 batch_delete" data-url='<s:url namespace="/" action="employee_batchDelete"/>'/>
							<input type="button" value="新增" class="ui_input_btn01 btn_input" data-url='<s:url namespace="/" action="employee_input"/>'/> 
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
							<th>用户名</th>
							<th>EMAIL</th>
							<th>年龄</th>
							<th>所属部门</th>
							<th>角色</th>
							<th></th>
						</tr>
						<s:iterator value="#pageResult.data">
						<tbody>
							<tr>
								<td><input type="checkbox" name="IDCheck" class="acb" data-id="<s:property value="id"/>"/></td>
								<td><s:property value="id"/></td>
								<td><s:property value="name"/></td>
								<td><s:property value="email"/></td>
								<td><s:property value="age"/></td>
								<td><s:property value="dept.name"/></td>
								<td>
								<s:if test="roles != null">
								<s:iterator value="roles">
								<s:property value="name"/>
								</s:iterator>
								</s:if>	
								</td>
								<td>
									<s:a namespace="/" action="employee_input"><s:param name="employee.id" value="id"/>编辑</s:a>

									<%--删除链接拼接的url,传递的id参数也拼接在url中,ajax调用时不需要在额外传递参数--%>
									<s:url namespace="/" action="employee_delete" var="deleteUrl">
										<s:param name="employee.id" value="id"/>
									</s:url>
									<%--commonAll.js中通过类选择器获取当前链接,并绑定事务,再通过获取自定义属性获取需要跳转的action和参数--%>
									<a href="javascript:;" class="btn_delete" data-url="<s:property value="#deleteUrl"/>">删除</a>
								</td>
							</tr>
						</tbody>
						</s:iterator>
					</table>
				</div>
			</div>
			<%@include file="/WEB-INF/views/common/common_page.jsp"%>
		</div>
	</s:form>
</body>
</html>
