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
                        <%--<select class="easyui-combotree" name="permissionList.id" editable="false"--%>
                                <%--url="${ctx}/platform/account/role/getPermissionTree?roleId=${role.id}"--%>
                                <%--multiple="true" checkbox="true" method="get"--%>
                                <%--data-options="panelHeight:'auto'" style="width:155px;">--%>
                        <%--</select>--%>
                        <select id="parentId" name="permissionList.id" style="width:156px;" />
                    </td>
                </tr>
            </table>
        </form>

        <script type="text/javascript" src="${ctx}/static/project/platform/base/base.js" ></script>
        <script type="text/javascript" src="${ctx}/static/project/platform/role/role.js" ></script>
        <script>
            /**
            * 选中权限树中当前角色ID下绑定的权限节点
             */
            function selectTreeNode() {
                var roleId = "${role.id}";
                if(!!roleId) {
                    $.getJSON('${ctx}/platform/account/role/getPermissionIdList',
                            {roleId: roleId},
                            function(resultData) {
                                if(!!resultData) {
                                    $('#parentId').combotree('setValues', resultData);
                                }
                            }
                    )
                }
            }
            //页面JS初始化
            $(function() {
                $('#parentId').combotree({
                    url:'${ctx}/platform/account/permission/permissionTree',
                    method: 'get',
                    editable: false,
                    multiple: true,
                    checkbox: true,
                    panelHeight: 'auto',
                    onLoadSuccess: selectTreeNode,
                    icons: [{
                        iconCls: 'icon-clear',
                        handler: function(e){
                            $(e.data.target).combotree('clear');
                        }
                    }]
                });
            });

        </script>
    </body>
</inheritance:override>

<%--继承父类 base.jsp--%>
<%@ include file="/WEB-INF/views/base/base.jsp" %>
