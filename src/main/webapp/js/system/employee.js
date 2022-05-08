$(function(){
	/*$("#all").click(function(){
		selectOrClearAllCheckbox(this);
	});
	
	$(".btn_batchDelete").click(function(){
		if($("input[name='IDCheck']:checked").size()<=0){
			art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'至少选择一条', ok:true,});
			return;
		}
		var selected=[];
		var refreshLocation=$(this).attr("data-refresh-href");
		$("input[name='IDCheck']:checked").each(function(index,item){
			selected[index]=$(item).val();
		});
		$.ajax({
	        url : $(this).attr("data-href"),
	        data: $.param({"ids":selected},true),
	        type : "POST",
	        dataType : 'json',
	        success : function (result){
	        	art.dialog({icon:'info', title:'提示', drag:false, resize:false, content:'批量删除成功', 
	        		ok:function(){window.location.href=refreshLocation}});
	        }
		});
	});*/
        //校验规则
	    $("#editForm").validate({
			rules:{
				"employee.name":{
					required:true,
					minlength: 2
				},
				"employee.password":{
					required:true,
					minlength: 2
				},
				"repassword":{
					//需要和密码相同
					equalTo:"#password"
				},
				"employee.email":{
				    email:true,
				},
				"age":{
					digits:true,
					range:[18,60]
				}
			},
			messages:{
				"employee.name":{
					required:"用户名不能为空",
					minlength:"用户名长度必须超过两位"
				},
				"employee.password":{
					required:"密码不能为空",
					minlength:"密码长度必须超过两位"
				},
                "repassword":{
                    equalTo:"两次输入的密码必须相同"
                },
                "employee.email":{
					email:"必须输入正确的email格式"
				},
                "age":{
                    digits:"必须输入整数",
                    range:"年龄必须在18-60之间"
                }
			}

		});

	//移动列表
	$("#select").click(function(){
		$(".all_roles option:selected").appendTo($(".selected_roles"));
	})
    $("#selectAll").click(function(){
    	$(".all_roles option").appendTo($(".selected_roles"));
	})
	$("#deselect").click(function(){
		$(".selected_roles option:selected").appendTo($(".all_roles"))
	})
	$("#deselectAll").click(function(){
		$(".selected_roles option").appendTo($(".all_roles"));
	})

	//提交表单之前现将所有将要提交的option设置为被选择
	$("#editForm").submit(function(){
		if($(".selected_roles option:selected").size()!=$(".selected_roles option")){
			$(".selected_roles option").prop("selected",true);
		}
	});

	//获取所有被选择的角色的value,返回一个数组
	if($(".selected_roles option").size()>0){
		var ids =$.map($(".selected_roles option"),function(item1){
			return $(item1).val();
		});
		$.each($(".all_roles option"),function (index,item2) {
			//判断所有角色列表(左列表)中的角色是否仍然包含被选择的角色(右列表)
			//若不存在则返回-1
			if($.inArray($(item2).val(),ids) != -1){
                 //已经存在,需要移除
				$(item2).remove();
			}
        })
	}
});