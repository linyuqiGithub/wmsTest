<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>信息管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css"/>
    <link href="/style/common_style.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/jquery/jquery.validate.min.js"></script>
    <script type="text/javascript" src="/js/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
    <script type="text/javascript" src="/js/plugins/artDialog/iframeTools.js"></script>
    <script type="text/javascript" src="/js/plugins/My97DatePicker/WdatePicker.js"></script>
    <script>
        //使用on编程的原因,防止添加明细克隆行的时候,点击事件消失,当然也可以在该克隆方法为clone(true)保留事件
        $(function () {
            $("#edit_table_body").on("click", ".searchproduct", function () {
                //获取到当前行
                var currentTr = $(this).closest("tr");
                var url = "/product_selectProduct.action";
                //调用open方法
                $.dialog.open(url, {
                    //id只是一个标志，防止重复弹出相同弹框，并没有什么实际作用
                    id: "selectProduct",
                    title: "货品选择",
                    width: 980,
                    height: 680,
                    //关闭之前会调用function函数
                    close: function () {
                        //获取selectProduct传递过来的参数
                        var productJson = $.dialog.data("productJson");
                        //把数据存放到对应的空间中
                        if (productJson) {
                            currentTr.find("[tag=name]").val(productJson.name);
                            currentTr.find("[tag=pid]").val(productJson.id);
                            currentTr.find("[tag=brand]").html(productJson.brandName);
                            currentTr.find("[tag=costPrice]").val(productJson.costPrice);
                        }
                    }
                });
            }).on("change", "[tag=costPrice],[tag=number]", function () {
                //计算金额小计
                //获取到当前行
                //从当前元素依次向父级元素查找,找到最想匹配的元素
                var currentTr = $(this).closest("tr");
                //查找后代元素,计算总金额
                var amount = currentTr.find("[tag=costPrice]").val() * currentTr.find("[tag=number]").val();
                //将计算后的总金额显示在页面中,并且调用toFixed(2)保留两位小数
                currentTr.find("[tag=amount]").html(amount.toFixed(2));
            }).on("click", ".removeItem", function () {
                //找到当前行
                var currentTr = $(this).closest("tr");
                //判断当前他tbody有多少个tr,如果只有一个tr,则清空数据,否则删除当前行
                if ($("#edit_table_body tr").size() > 1) {
                    //删除当前行
                    currentTr.remove();
                } else {
                    //清空数据
                    currentTr.find("[tag=name],[tag=pid],[tag=costPrice],[tag=number],[tag=remark]").val("");
                    currentTr.find("[tag=brand],[tag=amount]").html("");
                }
            });

            //添加明细
            $(".appendRow").click(function () {
                //克隆一行
                var cloneTr = $("#edit_table_body tr:first").clone();
                //清空数据
                cloneTr.find("[tag=name],[tag=pid],[tag=costPrice],[tag=number],[tag=remark]").val("");
                cloneTr.find("[tag=brand],[tag=amount]").html("");
                //添加到tbody末尾
                cloneTr.appendTo($("#edit_table_body"));
            });
            //表单提交事件,用于设置对应元素的name属性的index
            $("#editForm").submit(function () {
                //遍历tbody的每一行tr元素,用户给其中的input元素设置正确的name属性
                //用于提交表单之前,遍历edit_table_body中的每一行(每一个订单明细),将每一列的name设置为正确格式
                // ,用于提交时订单明细数据的正确封装
                $.each($("#edit_table_body tr"), function (index, item) {
                    $(item).find("[tag=pid]").prop("name", "orderbill.items[" + index + "].product.id");
                    $(item).find("[tag=costPrice]").prop("name", "orderbill.items[" + index + "].costPrice");
                    $(item).find("[tag=number]").prop("name", "orderbill.items[" + index + "].number");
                    $(item).find("[tag=remark]").prop("name", "orderbill.items[" + index + "].remark");
                });

            });
                //给日期添加样式和点击事件
                $("input[name='orderbill.vdate']").addClass("Wdate").click(function () {
                    WdatePicker(
                    );
                });
        });

    </script>
</head>
<body>
<!-- =============================================== -->
<%@include file="/WEB-INF/views/common/common_msg.jsp" %>
<s:form name="editForm" namespace="/" action="orderbill_saveOrUpdate" method="post" id="editForm" theme="simple">
    <div id="container">
        <div id="nav_links">
            <span style="color: #1A5CC6;">产品订单编辑</span>
            <div id="page_close">
                <a>
                    <img src="images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
                </a>
            </div>
        </div>
        <div class="ui_content">
            <table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
                <s:hidden name="orderbill.id"></s:hidden>
                <tr>
                    <td class="ui_text_rt" width="140">订单编号</td>
                    <td class="ui_text_lt">
                        <s:textfield name="orderbill.sn" cssClass="ui_input_txt02"></s:textfield>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">供应商</td>
                    <td class="ui_text_lt">
                        <%--将供应商id传递到action中的orderbill对象之前,supplier对象是null,参数拦截器将orderbill.supplier.id
                        注入supplier对象后,则生成了supplier对象--%>
                        <s:select list="#suppliers" name="orderbill.supplier.id" listKey="id" listValue="name"
                                  cssClass="ui_select01"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">业务时间</td>
                    <td class="ui_text_lt">
                        <%--date标签用于将日期生成固定的格式,而textfield标签则负责将日期显示和参数的封装--%>
                        <s:date name="orderbill.vdate" var="vdate" format="yyyy-MM-dd"/>
                        <s:textfield name="orderbill.vdate" class="ui_input_txt02" value="%{#vdate}"/>
                    </td>
                </tr>

                <tr>
                    <td class="ui_text_rt" width="140">明细</td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="button" value="添加明细" class="ui_input_btn01 appendRow"/>
                        <table class="edit_table" cellspacing="0" cellpadding="0" border="0" style="width: auto">
                            <thead>
                            <tr>
                                <th width="10"></th>
                                <th width="200">货品</th>
                                <th width="120">品牌</th>
                                <th width="80">价格</th>
                                <th width="80">数量</th>
                                <th width="80">金额小计</th>
                                <th width="150">备注</th>
                                <th width="60"></th>
                            </tr>
                            </thead>
                            <tbody id="edit_table_body">
                            <%--表示是新增,明细只需要一行--%>
                            <s:if test="orderbill.id == null">
                                <tr>
                                    <td></td>
                                    <td>
                                        <s:textfield disabled="true" readonly="true" cssClass="ui_input_txt04"
                                                     tag="name"/>
                                        <img src="/images/common/search.png" class="searchproduct"/>
                                        <%--将items用数组表示,保证将数据封装到items集合中的同一个orderbillitem中--%>
                                        <s:hidden name="orderbill.items[0].product.id" tag="pid"/>
                                    </td>
                                    <td><span tag="brand"></span></td>
                                    <td><s:textfield tag="costPrice" name="orderbill.items[0].costPrice"
                                                     cssClass="ui_input_txt04"/></td>
                                    <td><s:textfield tag="number" name="orderbill.items[0].number"
                                                     cssClass="ui_input_txt04"/></td>
                                    <td><span tag="amount"></span></td>
                                    <td><s:textfield tag="remark" name="orderbill.items[0].remark"
                                                     cssClass="ui_input_txt02"/></td>
                                    <td>
                                        <a href="javascript:;" class="removeItem">删除明细</a>
                                    </td>
                                </tr>
                            </s:if>
                            <s:else>
                                <%--表示是编辑,则遍历items明细集合--%>
                                <s:iterator value="orderbill.items">
                                    <tr>
                                        <td></td>
                                        <td>
                                            <s:textfield disabled="true" readonly="true" cssClass="ui_input_txt04"
                                                         name="product.name" tag="name"/>
                                            <img src="/images/common/search.png" class="searchproduct"/>
                                            <%--不需要显示product的id,只是用于帮助product对象的生成--%>
                                            <s:hidden name="product.id" tag="pid"/>
                                        </td>
                                            <%--<s:property value=""/>标签只是用于值的显示,不会进行参数的封装,所以不会生成brand对象--%>
                                            <%--brand和amount的值可以通过查找和计算得出,所以不需要进行参数的封装--%>
                                        <td><span tag="brand"><s:property value="product.brand.name"/></span></td>
                                        <td><s:textfield tag="costPrice" name="costPrice"
                                                         cssClass="ui_input_txt04"/></td>
                                        <td><s:textfield tag="number" name="number"
                                                         cssClass="ui_input_txt04"/></td>
                                        <td><span tag="amount"><s:property value="amount"/></span></td>
                                        <td><s:textfield tag="remark" name="remark"
                                                         cssClass="ui_input_txt04"/></td>
                                        <td>
                                            <a href="javascript:;" class="removeItem">删除明细</a>
                                        </td>
                                    </tr>
                                </s:iterator>
                            </s:else>
                            </tbody>
                        </table>
                    </td>
                </tr>

                <tr>
                    <td>&nbsp;</td>
                    <td class="ui_text_lt">
                        &nbsp;<input type="submit" value="确定保存" class="ui_input_btn01"/>
                        &nbsp;<input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</s:form>
</body>
</html>