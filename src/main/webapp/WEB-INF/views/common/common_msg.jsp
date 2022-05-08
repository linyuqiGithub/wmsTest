<%@ page language="java" contentType="text/html;charset=UTF-8"%>
 <script>
	 $(function () {
		 var data = "<s:property value="actionMessages[0]"/>";
         //加载页面时如果提示信息为空，则不会弹出该dialog
		 if(data){
		     $.dialog({
                 title:"温馨提示",
				 content:data,
				 ok:true
			 })
		 }
     });

     $(function () {
         var data = "<s:property value="actionErrors[0]"/>";
         //加载页面时如果错误信息为空，则不会弹出该dialog
         if(data){
             $.dialog({
                 title:"温馨提示",
                 content:data,
                 ok:true
             })
         }
     })
 </script>