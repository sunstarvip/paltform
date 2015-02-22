<%--
  Created by IntelliJ IDEA.
  User: DarKnight
  Date: 2014/8/1
  Time: 0:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add User</title>
</head>
<body>
<form id="userForm" action="/platform/platform/account/user/save" method="post" modelAttribute="user">
    登录名称: <input name="accountName" type="text" value="${user.accountName}" />
    密码: <input name="password" type="password" value="${user.password}" />
    用户名称: <input name="name" type="text" value="${user.name}" />
    <button type="submit">新增</button>
</form>
</body>
</html>
