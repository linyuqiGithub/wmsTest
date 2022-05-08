//ajax发送请求参数是一个数组时,禁用方括号[]
jQuery.ajaxSettings.traditional = true;
$(function () {
    /** table鼠标悬停换色* */
    /*	// 如果鼠标移到行上时，执行函数
     $(".table tr").mouseover(function() {
     $(this).css({background : "#CDDAEB"});
     $(this).children('td').each(function(index, ele){
     $(ele).css({color: "#1D1E21"});
     });
     }).mouseout(function() {
     $(this).css({background : "#FFF"});
     $(this).children('td').each(function(index, ele){
     $(ele).css({color: "#909090"});
     });
     });*/

    //加载页面时将所有checkbox设置为未勾选
    $(".acb").prop("checked", false);

    //新增按钮
    $(".btn_input").click(function () {
        //通过自定义的data-url属性将对应struts标签的url标签传递过来，跳转到指定的url
        window.location.href = $(this).data("url");
    });

    //全选按钮
    $("#all").click(function () {
        //获取全选按钮的checked值(选中状态值)
        var checked = $(this).prop("checked");
        //将页面中的所有复选框的选中值设置为全选按钮的checked值
        $(".acb").prop("checked", checked);
    });

    //批量删除
    $(".batch_delete").click(function () {
        //获取批量删除跳转的url
        var url = $(this).data("url");
        //获取所有选中复选框的jQuery行元素,checkedIds表示的是所有选中的jQuery行元素数组
        var checkedIds = $(".acb:checked");
        //没有选择数据就点击批量删除按钮
        if (checkedIds.size() == 0) {
            $.dialog({
                title: "温馨提示",
                content: "亲,请选择需要删除的数据",
                ok: true
            });
            //这里使用return使函数不再往下执行
            return;
        }
        //ids表示批量删除中所有选中数据的id值的数组
        var ids = $.map(checkedIds, function (item) {
            //遍历每一个选中的dom对象:行元素item
            //返回每一个行元素item的自定义属性data-id(值为当前行的id值)
            return $(item).data("id");
        });
        $.dialog({
            title: "温馨提示",
            content: "亲,确定要删除数据吗?",
            //点击确认之后执行function函数
            ok: function () {
                //发送ajax请求。当参数是一个数组时，封装时数组的每一个数据以ids[]=id值进行封装，这样会导致action中list集合无法接收该参数。
                //使用jQuery.ajaxSettings.traditional = true 禁用方括号[]。
                //Action中响应的数据将存储在参数data中
                $.get(url, {ids: ids}, function (data) {
                    $.dialog({
                        title: "温馨提示",
                        content: data,
                        ok: function () {
                            //重新加载页面
                            window.location.reload();
                        }
                    })
                });
            },
            //点击取消则不发送ajax请求
            cancel: true
        })
    });

    //删除操作
    $(".btn_delete").click(function () {
        //获取用于发送请求的url
        var url = $(this).data("url");
        $.dialog({
            title: "温馨提示",
            content: "亲,确定要执行删除操作吗?",
            ok: function () {
                //url中已经有id参数,所以不需要额外传递参数
                $.get(url, function (data) {
                    $.dialog({
                        title: "温馨提示",
                        content: data,
                        ok: function () {
                            window.location.reload();
                        }
                    })
                });
            },
            cancel: true
        })
    })


});
//加载权限
$(function () {
    $(".loadpermission_btn").click(function () {
        var url = $(this).data("url");
        console.log(url);
        $.dialog({
            title: "温馨提示",
            content: "亲,重新加载权限可能要耗费较长的时间,你确定加载吗?",
            icon: "face-smile",
            ok: function () {
                $.get(url, function (data) {
                    $.dialog({
                        title: "温馨提示",
                        content: data,
                        ok: function () {
                            window.location.reload();
                        }
                    })
                })
            },
            cancel: true
        });
        /*
         jQuery.ajax({
         url:$(this).data("href"),
         type : "POST",
         dataType : 'json',
         success : function (result){
         art.dialog({
         icon:'succeed',
         title:'提示',
         drag:false,
         resize:false,
         content:'重新加载成功',
         ok:function(){
         window.location.reload();}});
         }
         });*/
    });
});


//审核
$(function () {
    $(".btn_audit").click(function () {
        var url = $(this).data("url");
        $.dialog({
            title: "温馨提示",
            content: "亲,确定要执行审核操作吗?",
            ok: function () {
                //点击确认按钮后发送ajax请求
                $.get(url, function (data) {
                    //响应后弹出确认框
                    $.dialog({
                        title: "温馨提示",
                        content: data,
                        ok: function () {
                            //点击确认后刷新页面
                            window.location.reload();
                        }
                    })
                });
            },
            cancel: true
        });
    });
});