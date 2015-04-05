<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.DarKnight.com.cn/jsp-extends" prefix="inheritance" %>

<%-- 用户新增对话框 --%>
<inheritance:override name="addDialog">
    <div class="easyui-dialog" id="userDialog"
         closed="true" buttons="#dlg-buttons"
         style="padding:10px 20px">
        <%--<div>用户信息</div>--%>
        <form id="userForm" method="post">
            <%-- Hidden属性 begin--%>
            <%-- 用户ID --%>
            <input name="id" type="hidden">
            <%-- Hidden属性 end--%>

            <table cellpadding="5">
                <tr>
                    <td>账户名称:</td>
                    <td>
                        <input class="easyui-validatebox" name="accountName" type="text"
                               required="true" validType="remote['${ctx}/platform/account/user/checkAccountName', 'accountName']">
                    </td>
                </tr>
                <tr id="passwordRow">
                    <td>密码:</td>
                    <td>
                        <input class="easyui-validatebox" name="password" type="text" required="true">
                    </td>
                </tr>
                <tr>
                    <td>用户昵称:</td>
                    <td>
                        <input class="easyui-validatebox" name="name" type="text">
                    </td>
                </tr>
                <tr>
                    <td>联系电话:</td>
                    <td>
                        <input name="phoneNum" type="text">
                    </td>
                </tr>
                <tr>
                    <td>邮箱地址:</td>
                    <td>
                        <input class="easyui-validatebox" name="mailAddress" validType="email" type="text">
                    </td>
                </tr>
                <tr>
                    <td>联系地址:</td>
                    <td>
                        <input class="easyui-validatebox" name="address" type="text">
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="user.save()">保存</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="user.cancel()">取消</a>
    </div>
</inheritance:override>

<!-- 继承父类 base.jsp -->
<%@ include file="/WEB-INF/views/platform/user/userList_easyui.jsp" %>