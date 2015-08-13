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
            <tr>
                <td>父级权限:</td>
                <td>
                    <select id="parentId" name="parent.id" style="width:156px;" />
                </td>
            </tr>
        </table>
    </form>

    <script type="text/javascript" src="${ctx}/static/project/base/base.js" ></script>
    <script type="text/javascript" src="${ctx}/static/project/platform/permission/permission.js" ></script>
    <script>
        function selectTreeNode() {
            var parentId = "${permission.parent.id}";
            if(!!parentId) {
                $('#parentId').combotree('setValue', parentId);
            }
        }
        //页面JS初始化
        $(function() {
//            selectTreeNode();
            $('#parentId').combotree({
                onBeforeSelect: function(node) {
                    var selfNodeId = "${permission.id}";
                    if(selfNodeId==node['id']) {
                        alert('请勿选择自己！');
                        return false;
                    }
                },
                onLoadSuccess: selectTreeNode,
                url:'${ctx}/platform/account/permission/permissionTree',
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