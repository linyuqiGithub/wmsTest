<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s"  uri="/struts-tags"  %>
<%--<div class="ui_tb_h30">
	<div class="ui_flt" style="height: 30px; line-height: 30px;">
		共有
		<span class="ui_txt_bold04"><s:property value="#pageResult.totalCount" /></span>
		条记录，当前第
		<span class="ui_txt_bold04"><s:property value="#pageResult.currentPage" />
		/
		<s:property value="#pageResult.totalPage" /></span>
		页
	</div>
	<div class="ui_frt">
		<!--    如果是第一页，则只显示下一页、尾页 -->
		<input type="button" value="首页" class="ui_input_btn01 page_btn" data-page="1"/>
		<input type="button" value="上一页" class="ui_input_btn01 page_btn" data-page="${pageResult.prevPage}"/>
		<input type="button" value="下一页" class="ui_input_btn01 page_btn" data-page="${pageResult.nextPage}"/>
		<input type="button" value="尾页" class="ui_input_btn01 page_btn" data-page="${pageResult.totalPage}"/>
		
		<!--     如果是最后一页，则只显示首页、上一页 -->
		<s:select list="{10,20,50}" value="#pageResult.pageSize" name="qo.pageSize" cssClass="ui_select02"></s:select>
		转到第<input type="text" name="qo.currentPage" value="${qo.currentPage}" class="ui_input_txt01" />页
			 <input type="button" class="ui_input_btn01 page_btn" value="跳转"/>
	</div>
</div>--%>

<div class="ui_tb_h30">
	<div class="ui_flt" style="height: 30px; line-height: 30px;">
		共有
		<span class="ui_txt_bold04"><s:property value="#pageResult.totalCount"/></span>
		条记录，当前第
		<span class="ui_txt_bold04"><s:property value="#pageResult.currentPage"/>/<s:property value="#pageResult.totalPage"/></span>
		页
	</div>
	<div class="ui_frt">
		<input type="button" value="首页" class="ui_input_btn01 btn_page" data-page="1"/>
		<input type="button" value="上一页" class="ui_input_btn01 btn_page"  data-page='<s:property value="#pageResult.prevPage"/>'/>
		<input type="button" value="下一页" class="ui_input_btn01 btn_page"  data-page='<s:property value="#pageResult.nextPage"/>'/>
		<input type="button" value="尾页" class="ui_input_btn01 btn_page"  data-page='<s:property value="#pageResult.totalPage"/>'/>

		<s:select list="{5,10,20}" name="qo.pageSize" cssClass="ui_select02"/>
		<%-- 决定了当前页是第几页 注意：textfield在jQuery中最终解析为input元素 --%>
		转到第<s:textfield name="qo.currentPage" cssClass="ui_input_txt01"/>页
		<input type="button" class="ui_input_btn01 btn_page"  value="跳转"/>
	</div>
</div>