<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.DarKnight.com.cn/jsp-extends" prefix="inheritance" %>

<inheritance:override name="body">
<body>
    <div style="width:400px;height:280px;padding:10px 20px">
        <form id="userForm" action="/platform/platform/account/user/save" method="post" modelAttribute="user">
            <div class="fitem">
                <label>登录名称: </label>
                <input name="accountName" class="easyui-validatebox" required="true">
            </div>
            <div class="fitem">
                <label>密码: </label>
                <input name="password" class="easyui-validatebox" required="true">
            </div>
            <div class="fitem">
                <label>用户名称: </label>
                <input name="name">
            </div>
        </form>
    </div>

    <div id="dlg-buttons">
        <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">Save</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
    </div>

    <script>
        //JS声明
        function saveUser() {
            $("#userForm").submit();
        }

        //页面JS初始化
        $(function() {

        });
    </script>
</body>
</inheritance:override>

<!-- 继承父类 base.jsp -->
<%@ include file="/WEB-INF/views/base/base.jsp" %>