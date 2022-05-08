/*
 /!* zTree插件加载目录的处理  *!/
 var zTree;

 var setting = {
 callback: {
 beforeClick: function(treeId, treeNode) {
 $("#rightMain").attr("src",treeNode.path);
 return false;
 }
 },
 data: {
 simpleData: {
 enable:true,
 idKey: "id",
 pIdKey: "pId",
 rootPId: ""
 }
 }
 };

 function loadMenu(resourceType, treeObj){
 menus={
 "business":[{"isParent":true,"name":"业务模块","sn":"business"}],
 "systemManage":[{"id":1,"pId":0,"name":"系统管理"},
 {"id":2,"pId":1,"name":"员工管理","path":"/employee.action"},
 {"id":3,"pId":1,"name":"部门管理","path":"/department.action"},
 {"id":4,"pId":1,"name":"权限管理","path":"/permission.action"},
 {"id":5,"pId":1,"name":"角色管理","path":"/role.action"},
 {"id":6,"pId":1,"name":"系统菜单管理","path":"/systemMenu.action"},],
 "charts":[{"isParent":true,"name":"报表","sn":"charts"}]
 };
 // 将返回的数据赋给zTree
 $.fn.zTree.init($("#dleft_tab1"),setting, menus[resourceType]);
 zTree = $.fn.zTree.getZTreeObj("dleft_tab1");
 }

 //加载当前日期
 function loadDate(){
 var time = new Date();
 var myYear = time.getFullYear();
 var myMonth = time.getMonth() + 1;
 var myDay = time.getDate();
 if (myMonth < 10) {
 myMonth = "0" + myMonth;
 }
 document.getElementById("day_day").innerHTML = myYear + "." + myMonth + "."	+ myDay;
 }

 /!**
 * 隐藏或者显示侧边栏
 * 
 **!/
 function switchSysBar(flag){
 var side = $('#side');
 var left_menu_cnt = $('#left_menu_cnt');
 if( flag==true ){	// flag==true
 left_menu_cnt.show(500, 'linear');
 side.css({width:'280px'});
 $('#top_nav').css({width:'77%', left:'304px'});
 $('#main').css({left:'280px'});
 }else{
 if ( left_menu_cnt.is(":visible") ) {
 left_menu_cnt.hide(10, 'linear');
 side.css({width:'60px'});
 $('#top_nav').css({width:'100%', left:'60px', 'padding-left':'28px'});
 $('#main').css({left:'60px'});
 $("#show_hide_btn").find('img').attr('src', '/images/common/nav_show.png');
 } else {
 left_menu_cnt.show(500, 'linear');
 side.css({width:'280px'});
 $('#top_nav').css({width:'77%', left:'304px', 'padding-left':'0px'});
 $('#main').css({left:'280px'});
 $("#show_hide_btn").find('img').attr('src', '/images/common/nav_hide.png');
 }
 }
 }

 //面板切换
 $(function(){
 /!*loadDate();*!/
 //给li元素绑定一个点击事件
 $('#TabPage2 li').click(function(){
 //回到过去,去掉所有样式
 $('#TabPage2 li').removeClass("selected");
 $.each($('#TabPage2 li'),function (index,item) {
 $(item).find("img").prop('src', '/images/common/'+ (index+1) +'.jpg')
 })
 //图标的改变
 $(this).find("img").prop('src', '/images/common/'+ ($(this).index()+1) +'_hover.jpg');
 //修改菜单标题
 $('#nav_module').find('img').prop('src', '/images/common/module_'+ ($(this).index()+1) +'.png');
 //改变样式
 $(this).addClass("selected");

 //点击事件 加载系统对应的菜单
 var sn=$(this).data("rootmenu");
 // console.log(sn);
 loadMenu(sn);
 });

 // 显示隐藏侧边栏
 $("#show_hide_btn").click(function () {
 switchSysBar();
 });

 /!*var index = $(this).index();
 $(this).find('img').attr('src', '/images/common/'+ (index+1) +'_hover.jpg');*!/
 /!*$(this).css({background:'#fff'});
 $('#nav_module').find('img').attr('src', '/images/common/module_'+ (index+1) +'.png');
 $('#TabPage2 li').each(function(i, ele){
 if( i!=index ){
 $(ele).find('img').attr('src', '/images/common/'+ (i+1) +'.jpg');
 $(ele).css({background:'#044599'});
 }*!/


 });
 $(function(){
 //初始化的时候显示业务模块
 loadMenu("business");
 });

 /!*	// 显示隐藏侧边栏
 $("#show_hide_btn").click(function() {
 switchSysBar();
 });*!/
 */
/////////////////////////////////////////////////////////////////////////////////////////////////
//加载当前日期
function loadDate() {
    var time = new Date();
    var myYear = time.getFullYear();
    var myMonth = time.getMonth() + 1;
    var myDay = time.getDate();
    if (myMonth < 10) {
        myMonth = "0" + myMonth;
    }
    $("#day_day").html(myYear + "." + myMonth + "."
        + myDay);
}

/**
 * 隐藏或者显示侧边栏(这东西没有讲，让我们直接用)
 *
 */
function switchSysBar(flag) {
    var side = $('#side');
    var left_menu_cnt = $('#left_menu_cnt');
    if (flag == true) { // flag==true
        left_menu_cnt.show(500, 'linear');
        side.css({
            width: '280px'
        });
        $('#top_nav').css({
            width: '77%',
            left: '304px'
        });
        $('#main').css({
            left: '280px'
        });
    } else {
        if (left_menu_cnt.is(":visible")) {
            left_menu_cnt.hide(10, 'linear');
            side.css({
                width: '60px'
            });
            $('#top_nav').css({
                width: '100%',
                left: '60px',
                'padding-left': '28px'
            });
            $('#main').css({
                left: '60px'
            });
            $("#show_hide_btn").find('img').attr('src',
                '/images/common/nav_show.png');
        } else {
            left_menu_cnt.show(500, 'linear');
            side.css({
                width: '280px'
            });
            $('#top_nav').css({
                width: '77%',
                left: '304px',
                'padding-left': '0px'
            });
            $('#main').css({
                left: '280px'
            });
            $("#show_hide_btn").find('img').attr('src',
                '/images/common/nav_hide.png');
        }
    }
}

// =====================================
//zTree
var setting = {
    data: {
        simpleData: {
            //开启简单模式
            enable: true
        }
    },callback:{
        onClick:function(event, treeId, treeNode){
            if(treeNode.action){
                $("#rightMain").prop("src",treeNode.action+".action");
                $("#here_area").html("当前位置："+treeNode.name);
            }
        }
    },
    async: {
        //开启异步加载模式
        enable: true,
        //异步加载发送的请求的地址
        url: "/systemMenu_queryMenuByparentSn.action",
        //发送请求中传递的请求参数
        autoParam: ["sn=qo.parentSn"]
    }
};

var zNodes ={
    //isParent:true判断为父节点
    business: {id:1,pId:0,name:"业务模块",isParent:true,sn:"business"},
    system:{id:1,pId:0,name:"系统模块",isParent:true,sn:"system"},
    chart:{id:1,pId:0,name:"报表模块",isParent:true,sn:"chart"}
};


function loadMenu(menuName){
    //调用zThree
    //第一个参数为zThree用于显示的位置,第二个参数为配置,第三个参数为调用加载菜单函数时根据传入的根目录名字选择加载哪个根目录
    $.fn.zTree.init($("#dleft_tab1"), setting, zNodes[menuName]);
}
// =====================================
$(function () {
    // 加载日期
    loadDate();
    //默认加载业务模块
    loadMenu("business");
    // ======================================
    // ======================================

    // 切换菜单按钮样式和标题
    //给li元素绑定一个点击事件
    $("#TabPage2 li").click(function () {
        //每次点击之后先将所有li的样式全部清除
        //遍历每一个li，执行function函数
        $.each($("#TabPage2 li"), function (index, item) {
            //清除selected样式(背景变回蓝色),并设置图片为/images/common/1.jpg/2.jsp/3.jsp
            $(item).removeClass("selected").children("img").prop("src", "/images/common/" + ($(item).index() + 1) + ".jpg");
        });
        //获取当前点击的是第几个菜单
        var index = $(this).index() + 1;//第N个菜单,从0开始
        //设置selected样式(背景变成白色)并设置图片为1_hover.jpg
        $(this).addClass("selected");
        //将图片设置为/images/common/1_hover.jsp/2_hover.jpg/3_hover.jpg
        $(this).children("img").prop("src", "/images/common/" + index + "_hover.jpg");

        //切换标题
        $("#nav_module").children("img").prop("src","/images/common/module_"+index+".png");
        //====
        //获取当前根目录的名字,通过zThree异步加载获取当前根目录的所有子菜单
        loadMenu($(this).data("rootmenu"));
    });

    // ======================================
    // 显示隐藏侧边栏
    $("#show_hide_btn").click(function () {
        switchSysBar();
    });
});