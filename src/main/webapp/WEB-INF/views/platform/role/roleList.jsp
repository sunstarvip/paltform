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
Role List!!!   <a href="/platform/platform/account/role/add">新增</a><a href="/logout">注销</a>

<table border="1px">
    <%--列头--%>
    <thead>
    <tr>
        <th>角色名称name</th>
        <th>角色描述description</th>
    </tr>
    </thead>
    <%--数据块--%>
    <tbody>
    <c:forEach var="role" items="${rolePage.content}" varStatus="status">
        <tr>
            <td>${role.name}</td>
            <td>${role.description}</td>
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
