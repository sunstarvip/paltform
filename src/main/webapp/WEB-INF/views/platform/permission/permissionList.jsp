<%--
  Created by IntelliJ IDEA.
  User: DarKnight
  Date: 2014/5/26 0026
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DarKnight支撑平台</title>
</head>
<body>
Permission List!!!   <a href="/platform/platform/account/permission/add">新增</a><a href="/logout">注销</a>

<table border="1px">
    <%--列头--%>
    <thead>
    <tr>
        <th>权限名称name</th>
        <th>权限描述description</th>
    </tr>
    </thead>
    <%--数据块--%>
    <tbody>
    <c:forEach var="permission" items="${permissionPage.content}" varStatus="status">
        <tr>
            <td>${permission.name}</td>
            <td>${permission.description}</td>
        </tr>
    </c:forEach>
    </tbody>
    <%--表格尾--%>
    <tfoot>
    <tr>
    </tr>
    </tfoot>

</table>
</body>
</html>
