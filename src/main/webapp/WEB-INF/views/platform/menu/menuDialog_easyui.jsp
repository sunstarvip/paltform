<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.DarKnight.com.cn/jsp-extends" prefix="inheritance" %>

<%--重定义父页面name=body的内容--%>
<inheritance:override name="body">
    <body>
    <form id="menuForm" method="post">
            <%-- Hidden属性 begin--%>
            <%-- 角色ID --%>
        <input name="id" type="hidden" value="${menu.id}">
            <%-- Hidden属性 end--%>

        <table cellpadding="5">
            <tr>
                <td>菜单名称:</td>
                <td>
                    <input class="easyui-validatebox" name="name" type="text" required="true" value="${menu.name}">
                </td>
            </tr>
            <tr>
                <td>菜单URL:</td>
                <td>
                    <input class="easyui-validatebox" name="urlPath" type="text" value="${menu.urlPath}">
                </td>
            </tr>
            <tr>
                <td>父级菜单:</td>
                <td>
                    <select id="parentId" name="parent.id" style="width:156px;" />
                </td>
            </tr>
        </table>
    </form>

    <script type="text/javascript" src="${ctx}/static/project/base/base.js" ></script>
    <script type="text/javascript" src="${ctx}/static/project/platform/menu/menu.js" ></script>
    <script>
        function selectTreeNode() {
            var parentId = "${menu.parent.id}";
            if(!!parentId) {
                $('#parentId').combotree('setValue', parentId);
            }
        }
        //页面JS初始化
        $(function() {
//            selectTreeNode();
            $('#parentId').combotree({
                onBeforeSelect: function(node) {
                    var selfNodeId = "${menu.id}";
                    if(selfNodeId==node['id']) {
                        alert('请勿选择自己！');
                        return false;
                    }
                },
                onLoadSuccess: selectTreeNode,
                url:'${ctx}/platform/system/menu/menuTree',
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