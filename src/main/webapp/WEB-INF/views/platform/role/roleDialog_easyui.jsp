<%--
  Created by IntelliJ IDEA.
  User: DarKnight
  Date: 2015/4/10
  Time: 23:00
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.DarKnight.com.cn/jsp-extends" prefix="inheritance" %>

<%--重定义父页面name=body的内容--%>
<inheritance:override name="body">
    <body>
        <form id="roleForm" method="post">
            <%-- Hidden属性 begin--%>
            <%-- 角色ID --%>
            <input name="id" type="hidden" value="${role.id}">
            <%-- Hidden属性 end--%>

            <table cellpadding="5">
                <tr>
                    <td>角色名称:</td>
                    <td>
                        <input class="easyui-validatebox" name="name" value="${role.name}"
                        type="text" required="true">
                    </td>
                </tr>
                <tr>
                    <td>角色描述:</td>
                    <td>
                        <textarea name="description">${role.description}</textarea>
                    </td>
                </tr>
                <tr>
                    <td>所属权限: </td>
                    <td>
                        <select class="easyui-combobox" name="permissionList.id" editable="false"
                              url="${ctx}/platform/account/role/getPermissionList?roleId=${role.id}"
                              multiple="true" method="get" valueField="id" textField="text"
                              data-options="panelHeight:'auto'" style="width:155px;">
                        </select>
                    </td>
                </tr>
            </table>
        </form>

    </body>
</inheritance:override>

<%--继承父类 base.jsp--%>
<%@ include file="/WEB-INF/views/base/base.jsp" %>
