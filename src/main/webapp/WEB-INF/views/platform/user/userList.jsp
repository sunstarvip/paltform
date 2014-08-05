<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="locale" value="${pageContext.request.locale}"/>
<html>
<head>
    <title>DarKnight支撑平台</title>
</head>
<body>
User List!!!   <a href="/platform/platform/account/user/add">新增</a><a href="/logout">注销</a>

<table border="1px">
    <%--列头--%>
    <thead>
    <tr>
        <th>账号名称accountName</th>
        <th>用户名称name</th>
        <th>用户密码password</th>
        <th>用户Salt</th>
    </tr>
    </thead>
    <%--数据块--%>
    <tbody>
    <c:forEach var="user" items="${userPage.content}" varStatus="status">
    <tr>
        <td>${user.accountName}</td>
        <td>${user.name}</td>
        <td>${user.password}</td>
        <td>${user.salt}</td>
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
