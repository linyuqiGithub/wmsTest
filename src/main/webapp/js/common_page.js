
$(function(){
	//监听高级查询提交按钮、首页、上一页、下一页、尾页按钮、跳转按钮
	$(".btn_page").click(function(){
		//按钮的data-page值赋值给跳转到第几页的input输入框(textfield在jQuery中最终解析为input元素)
		/**
		 * 如果监听的是跳转按钮，需要将跳转到第几页的input输入框的值赋值给自己。因为跳转按钮没有data-page
		 * 会导致val($(this).data("page"))是undifine，再赋值给跳转到第几页的input输入框，提交表单就会报错
		 */
		$("input[name='qo.currentPage']").val($(this).data("page") || $("input[name='qo.currentPage']").val());
		//提交当前表单
		$("#searchForm").submit();
	});
	//页面大小下拉框
	$("select[name='qo.pageSize']").change(function(){
        $("#searchForm").submit();
	})
});