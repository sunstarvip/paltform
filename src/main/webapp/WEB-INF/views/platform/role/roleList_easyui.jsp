<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.DarKnight.com.cn/jsp-extends" prefix="inheritance" %>
<%--<c:set var="ctx" value="${pageContext.request.contextPath}"/>--%>
<%--重定义父页面name=centerContent的内容--%>
<inheritance:override name="centerContent">
    <%--<table id="userTable"></table>--%>
    <table class="easyui-datagrid" id="roleTable" title="角色列表"
           iconCls='icon-save' rownumbers="true" fitColumns="true"
           pagination="true" datapagesize="10"
           url="${ctx}/platform/account/role/dataGrid"
           toolbar="#toolbar"
           data-options="singleSelect: true,
           collapsible: true, nowrap: false, striped: true,
           idField: 'id', method: 'post'">
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
                <th field="name" width="80" align="center">角色名称</th>
            </tr>
        </thead>
        <%-- 非冻结行 --%>
        <thead>
            <tr>
                <th field="description" width="120" align="center">角色描述</th>
            </tr>
        </thead>
    </table>
    <%-- 搜索区域 --%>
    <div id="toolbar" style="padding:2px 5px;">
        <span id="buttonBlock" align="left">
            <%-- 新增 --%>
            <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newRole()"></a>
            <%-- 编辑 --%>
            <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editRole()"></a>
            <%-- 删除 --%>
            <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteRole()"></a>
        </span>
        <span id="searchBlock" align="right">
            角色名称: <input id="searchName" name="searchName" style="width:110px">
            <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch()">查询</a>
        </span>
    </div>
    <%-- 用户新增对话框 --%>
    <inheritance:block name="addDialog">
    </inheritance:block>
</inheritance:override>

<inheritance:override name="scriptBlock">
    <%-- 用户新增对话框对应JS --%>
    <inheritance:block name="addScript">
    </inheritance:block>

    <script>

        function doSearch(){
            $('#roleTable').datagrid('load',{
                name: $('#searchName').val()
            });
        }

        //页面JS初始化
        $(function() {

        });

    </script>
</inheritance:override>
<!-- 继承父类 base.jsp -->
<%@ include file="/WEB-INF/views/base/base.jsp" %>
