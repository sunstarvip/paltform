<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.DarKnight.com.cn/jsp-extends" prefix="inheritance" %>
<%--<c:set var="ctx" value="${pageContext.request.contextPath}"/>--%>

<%--重定义父页面name=scriptSrc的内容--%>
<inheritance:override name="scriptSrc">
    <script type="text/javascript" src="${ctx}/static/plugins/Moment/moment.js" ></script>
    <script type="text/javascript" src="${ctx}/static/project/platform/base/base.js" ></script>
    <script type="text/javascript" src="${ctx}/static/project/platform/menu/menu.js" ></script>
</inheritance:override>

<%--重定义父页面name=body的内容--%>
<inheritance:override name="body">
    <body>
    <%--<table id="userTable"></table>--%>
    <table class="easyui-datagrid" id="menuTable" title="系统菜单列表"
           iconCls='icon-save' rownumbers="true" fitColumns="true"
           url="${ctx}/platform/system/menu/dataGrid"
           toolbar="#toolbar" collapsible="false" border="false"
           singleSelect="true" nowrap="false" striped="true"
           idField="id" pagination="true" pageNumber="1" pageSize="12"
           pageList="[12,20,30,40,50]" loadMsg="数据载入中…">
        <%-- 第一行 --%>
        <thead>
            <tr>
                <th colspan="3" data-options="align:'center'">基本信息</th>
                <th rowspan="2" data-options="field: 'opt', align:'center'">操作</th>
            </tr>
        </thead>
        <%-- 第二行 --%>
        <%-- 冻结行 --%>
        <thead data-options="frozen:true">
            <tr>
                <th field="id" align="center" data-options="checkbox: true">ID</th>
                <th field="name" width="80" align="center">菜单名称</th>
            </tr>
        </thead>
        <%-- 非冻结行 --%>
        <thead>
            <tr>
                <th field="createTime" formatter="formatterDate" width="60" align="center">创建时间</th>
                <th field="updateTime" formatter="formatterDate" width="60" align="center">更新时间</th>
                <th field="urlPath" width="240" align="left">菜单URL</th>
            </tr>
        </thead>
    </table>
    <%-- 搜索区域 --%>
    <div id="toolbar" style="padding:2px 5px;">
        <span id="buttonBlock" align="left">
            <%-- 新增 --%>
            <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="menu.add('新增系统菜单')"></a>
            <%-- 编辑 --%>
            <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="menu.edit('编辑系统菜单')"></a>
            <%-- 删除 --%>
            <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="menu.delete()"></a>
        </span>
        <span id="searchBlock" align="right">
            菜单名称: <input id="searchName" name="searchName" style="width:110px">
            <%--菜单类型: <input id="searchType" name="searchType" style="width:110px">--%>
            <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch()">查询</a>
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

        function doSearch() {
            var searchKeyMap= {
                name: 'searchName'
            }
            menu.doSearch(searchKeyMap)
        }

        // 定义全局JS对象
        var menu = new Menu('${ctx}', 'menuTable', 'menuDialog', 'menuForm', 'menuTree');

        //页面JS初始化
        $(function() {
            var menuBtns = [{
                id: 'menuSave',
                text: '保存',
                iconCls: 'icon-ok',
                handler:function(){
                    menu.save(menu);
                }
            },{
                id: 'menuCancel',
                text: '取消',
                iconCls: 'icon-cancel',
                handler:function(){
                    menu.cancel(menu);
                }
            }];

            menu.initDialog({title: '系统菜单', buttons: menuBtns, href: '${ctx}/platform/system/menu/dialogPage'});
//            parent.$('#menuSave').click(function() {menu.save(menu)});
//            parent.$('#menuCancel').click(function() {menu.cancel(menu)});
        });

    </script>

    </body>
</inheritance:override>

<!-- 继承父类 base.jsp -->
<%@ include file="/WEB-INF/views/base/base.jsp" %>
