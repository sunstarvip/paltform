<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.DarKnight.com.cn/jsp-extends" prefix="inheritance" %>

<inheritance:override name="cssLink">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/core/css/user/user.css" >
</inheritance:override>

<inheritance:override name="scriptSrc">
</inheritance:override>

<inheritance:override name="body">
<body>
    <div>
        <form class="form" id="userForm" action="/platform/platform/account/user/save" method="post" modelAttribute="user">

            <div class="fitem">
                <label>登录名称: </label>
                <input name="accountName" datatype="*" nullmsg="请输入登录名称！" errormsg="请输入正确的登录名称！">
                <span class="Validform_checktip"></span>
            </div>
            <div class="fitem">
                <label>密码: </label>
                <input name="password" >
            </div>
            <div class="fitem">
                <label>用户名称: </label>
                <input name="name">
            </div>
        </form>
    </div>

    <script>
        //JS声明
        function saveUser() {
            $("#userForm").submit();
        }

        //页面JS初始化
        $(function() {
            //启用验证
            $(".form").Validform();

        });
    </script>
</body>
</inheritance:override>

<!-- 继承父类 base.jsp -->
<%@ include file="/WEB-INF/views/base/base.jsp" %>