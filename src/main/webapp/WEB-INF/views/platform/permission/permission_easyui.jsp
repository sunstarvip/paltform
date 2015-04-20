<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.DarKnight.com.cn/jsp-extends" prefix="inheritance" %>
<%--<c:set var="ctx" value="${pageContext.request.contextPath}"/>--%>

<%--重定义父页面name=scriptSrc的内容--%>
<inheritance:override name="scriptSrc">
    <script type="text/javascript" src="${ctx}/static/plugins/Moment/moment.js" ></script>
    <script type="text/javascript" src="${ctx}/static/project/platform/base/base.js" ></script>
    <script type="text/javascript" src="${ctx}/static/project/platform/permission/permission.js" ></script>
</inheritance:override>

<%--重定义父页面name=centerContent的内容--%>
<inheritance:override name="body">
    <body>
        <table class="easyui-datagrid" id="permissionTable" title="权限列表"
               iconCls='icon-save' rownumbers="true" fitColumns="true"
               url="${ctx}/platform/account/permission/dataGrid"
               toolbar="#toolbar" collapsible="false" border="false"
               singleSelect="true" nowrap="false" striped="true"
               idField="id" pagination="true" pageNumber="1" pageSize="12"
               pageList="[12,20,30,40,50]" loadMsg="数据载入中…">
                <%-- 第一行 --%>
            <thead>
            <tr>
                <th colspan="4" data-options="align:'center'">基本信息</th>
                <th rowspan="2" data-options="field: 'opt', align:'center'">操作</th>
            </tr>
            </thead>
                <%-- 第二行 --%>
                <%-- 冻结行 --%>
            <thead data-options="frozen:true">
            <tr>
                <th field="id" align="center" data-options="checkbox: true">ID</th>
                <th field="name" width="80" align="center">权限名称</th>
            </tr>
            </thead>
                <%-- 非冻结行 --%>
            <thead>
            <tr>
                <th field="createTime" formatter="formatterDate" width="60" align="center">创建时间</th>
                <th field="updateTime" formatter="formatterDate" width="60" align="center">更新时间</th>
                <th field="parent" formatter="getParentName" width="80" align="left">父级权限</th>
                <th field="description" width="160" align="center">权限描述</th>
            </tr>
            </thead>
        </table>
            <%-- 搜索区域 --%>
        <div id="toolbar" style="padding:2px 5px;">
            <span id="buttonBlock" align="left">
                <%-- 新增 --%>
                <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="permission.add('新增权限')"></a>
                <%-- 编辑 --%>
                <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="permission.edit('编辑权限')"></a>
                <%-- 删除 --%>
                <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="permission.delete()"></a>
            </span>
            <span id="searchBlock" align="right">
                权限名称: <input id="searchName" name="searchName" style="width:110px">
                <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="permission.doSearch('searchName')">查询</a>
            </span>
        </div>

        <script>
            // 时间格式化JS
            function formatterDate(val, row) {
                if(!!val) {
                    return moment(val).format('YYYY-MM-DD HH:mm:ss');
                }else {
                    return "无";
                }
            }

            // 获取父级菜单名称
            function getParentName(val, row) {
                if(!!val) {
                    return val['name'];
                }else {
                    return "无";
                }
            }

            // 定义全局JS对象
            var permission = new Permission('${ctx}', 'permissionTable', 'permissionDialog', 'permissionForm');

            //页面JS初始化
            $(function() {
                var permissionBtns = [{
                    id: 'permissionSave',
                    text: '保存',
                    iconCls: 'icon-ok',
                    handler:function(){
                        permission.save(permission);
                    }
                },{
                    id: 'permissionCancel',
                    text: '取消',
                    iconCls: 'icon-cancel',
                    handler:function(){
                        permission.cancel(permission);
                    }
                }];

                permission.initDialog({title: '系统权限', buttons: permissionBtns, href: '${ctx}/platform/account/permission/dialogPage'});
            });

        </script>
    </body>
</inheritance:override>

<!-- 继承父类 base.jsp -->
<%@ include file="/WEB-INF/views/base/base.jsp" %>
