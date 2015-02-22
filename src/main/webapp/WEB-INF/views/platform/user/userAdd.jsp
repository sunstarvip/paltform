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

            <%--<table id="dg" title="My Users" class="easyui-datagrid"--%>
                   <%--url="get_users.php"--%>
                   <%--toolbar="#toolbar"--%>
                   <%--rownumbers="true" fitColumns="true" singleSelect="true">--%>
                <%--<thead>--%>
                <%--<tr>--%>
                    <%--<th field="firstname" width="50">First Name</th>--%>
                    <%--<th field="lastname" width="50">Last Name</th>--%>
                    <%--<th field="phone" width="50">Phone</th>--%>
                    <%--<th field="email" width="50">Email</th>--%>
                <%--</tr>--%>
                <%--</thead>--%>
            <%--</table>--%>
            <%--<div id="toolbar">--%>
                <%--<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">New User</a>--%>
                <%--<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">Edit User</a>--%>
                <%--<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">Remove User</a>--%>
            <%--</div>--%>
        </form>
    </div>

    <%--<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"--%>
         <%--buttons="#dlg-buttons">--%>
        <%--<div class="ftitle">User Information</div>--%>
        <%--<form id="userForm" method="post">--%>
            <%--<div class="fitem">--%>
                <%--<label>First Name:</label>--%>
                <%--<input name="firstname" class="easyui-validatebox" required="true">--%>
            <%--</div>--%>
            <%--<div class="fitem">--%>
                <%--<label>Last Name:</label>--%>
                <%--<input name="lastname" class="easyui-validatebox" required="true">--%>
            <%--</div>--%>
            <%--<div class="fitem">--%>
                <%--<label>Phone:</label>--%>
                <%--<input name="phone">--%>
            <%--</div>--%>
            <%--<div class="fitem">--%>
                <%--<label>Email:</label>--%>
                <%--<input name="email" class="easyui-validatebox" validType="email">--%>
            <%--</div>--%>
        <%--</form>--%>
    <%--</div>--%>
    <%--<div id="dlg-buttons">--%>
        <%--<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">Save</a>--%>
        <%--<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">Cancel</a>--%>
    <%--</div>--%>

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