<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.DarKnight.com.cn/jsp-extends" prefix="inheritance" %>

<%--重定义父页面name=body的内容--%>
<inheritance:override name="body">
    <body>
    <form id="permissionForm" method="post">
            <%-- Hidden属性 begin--%>
            <%-- 角色ID --%>
        <input name="id" type="hidden" value="${permission.id}">
            <%-- Hidden属性 end--%>

        <table cellpadding="5">
            <tr>
                <td>权限名称:</td>
                <td>
                    <input class="easyui-validatebox" name="name" value="${permission.name}"
                           type="text" required="true" >
                </td>
            </tr>
            <tr>
                <td>权限描述:</td>
                <td>
                    <textarea name="description" >${permission.description}</textarea>
                </td>
            </tr>
        </table>
    </form>

    </body>
</inheritance:override>

<%--继承父类 base.jsp--%>
<%@ include file="/WEB-INF/views/base/base.jsp" %>