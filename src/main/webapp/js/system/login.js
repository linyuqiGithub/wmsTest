$(function() {
	$("#login_sub").click(function() {
		$("#submitForm").submit();
	});
	$('#pwd').keydown(enterLogin);
});

function enterLogin(e) { // 传入 event
	var e = e || window.event;
	//按下回车之后提交
	if (e.keyCode == 13) {
		$("#submitForm").submit();
	}
}