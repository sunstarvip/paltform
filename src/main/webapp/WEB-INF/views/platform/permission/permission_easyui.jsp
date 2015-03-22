<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.DarKnight.com.cn/jsp-extends" prefix="inheritance" %>

<%-- 用户新增对话框 --%>
<inheritance:override name="addDialog">
    <div class="easyui-dialog" id="permissionDialog"
         closed="true" buttons="#dlg-buttons"
         style="padding:10px 20px">
        <form id="permissionForm" method="post">
            <%-- Hidden属性 begin--%>
            <%-- 角色ID --%>
            <input name="id" type="hidden">
            <%-- Hidden属性 end--%>

            <table cellpadding="5">
                <tr>
                    <td>权限名称:</td>
                    <td>
                        <input class="easyui-validatebox" name="name" type="text" required="true">
                    </td>
                </tr>
                <tr>
                    <td>权限描述:</td>
                    <td>
                        <textarea name="description"></textarea>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="Permission.save()">保存</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="Permission.cancel()">取消</a>
    </div>
</inheritance:override>


<!-- 继承父类 base.jsp -->
<%@ include file="/WEB-INF/views/platform/permission/permissionList_easyui.jsp" %>