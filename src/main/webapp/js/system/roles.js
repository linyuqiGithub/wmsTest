$(function () {
    /**
     * 权限列表移动操作
     */
    //选中的移动
    $("#select").click(function () {
        $(".all_permissions option:selected").appendTo(".selected_permissions");
    });
    //全部移动
    $("#selectAll").click(function () {
        $(".all_permissions option").appendTo(".selected_permissions");
    });
    //选中的移除
    $("#deselect").click(function () {
        $(".selected_permissions option:selected").appendTo(".all_permissions");
    });
    //全部移除
    $("#deselectAll").click(function () {
        $(".selected_permissions option").appendTo(".all_permissions");
    });

    //提交保存表单时执行该响应函数(防止将权限移动至右侧后右侧的权限处于非选择状态)
    //下拉框没有处于selected状态的话，数据不会被提交
    $("#editForm").submit(function () {
        //将右侧下拉框该角色拥有的权限全部设置为选中(这样才能保证保存时将右侧的全部权限进行保存)
        $(".selected_permissions option").prop("selected", true);
    });
    //进入编辑页面后，右侧已经拥有的权限，左侧的所有权限不应包含右侧已拥有的权限
    if ($(".selected_permissions option").size() > 0) {
        //获取所有被选择的权限的value,返回一个数组
        var ids = $.map($(".selected_permissions option"), function (item1) {
            return $(item1).val();
        });
        $.each($(".all_permissions option"), function (item2) {
            //判断所有角色列表(左列表)中的角色是否仍然包含被选择的角色(右列表)
            //通过$.inArray(value,ids)方法,判断value是否在ids数组中,在则返回所在数组的索引,不存在则返回-1
            if ($.inArray($(item2).val(), ids) != -1) {
                //已经存在,需要移除
                $(item2).remove();
            }
        })
    }

    /**
     * 菜单移动操作
     */
    $("#mselect").click(function () {
        $(".all_menus option:selected").appendTo(".selected_menus");
    });

    $("#mselectAll").click(function () {
        $(".all_menus option").appendTo(".selected_menus");
    });

    $("#mdeselect").click(function () {
        $(".selected_menus option:selected").appendTo(".all_menus");
    });

    $("#mdeselectAll").click(function () {
        $(".selected_menus option").appendTo(".all_menus");
    });


    $("#editForm").submit(function () {
        if ($(".selected_menus option:selected").size() != $(".selected_menus option")) {
            $(".selected_menus option").prop("selected", true);
        }
    });

    //获取所有被选择的角色的value,返回一个数组
    if ($(".selected_menus option").size() > 0) {
        var ids = $.map($(".selected_menus option"), function (item1) {
            return $(item1).val();
        });
        $.each($(".all_menus option"), function (index, item2) {
            //判断所有角色列表(左列表)中的角色是否仍然包含被选择的角色(右列表)
            //若不存在则返回-1
            if ($.inArray($(item2).val(), ids) != -1) {
                //已经存在,需要移除
                $(item2).remove();
            }
        })
    }
});