<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.DarKnight.com.cn/jsp-extends" prefix="inheritance" %>
<%--<c:set var="ctx" value="${pageContext.request.contextPath}"/>--%>
<%--重定义父页面name=centerContent的内容--%>
<inheritance:override name="centerContent">
    <%--<table id="userTable"></table>--%>
    <table class="easyui-datagrid" id="userTable" title="用户列表"
           iconCls='icon-save' rownumbers="true" fitColumns="true"
           pagination="true" datapagesize="10"
           url="${ctx}/platform/account/user/dataGrid"
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
                <th field="accountName" width="80" align="center">账户名称</th>
            </tr>
        </thead>
        <%-- 非冻结行 --%>
        <thead>
            <tr>
                <th field="name" width="120" align="center">用户昵称</th>
                <th field="phoneNum" width="80" align="center">联系电话</th>
                <th field="mailAddress" width="150" align="center">邮箱地址</th>
                <th field="address" width="100" align="center">联系地址</th>
            </tr>
        </thead>
    </table>
    <%-- 搜索区域 --%>
    <div id="toolbar" style="padding:2px 5px;">
        <span id="buttonBlock" align="left">
            <%-- 新增 --%>
            <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()"></a>
            <%-- 编辑 --%>
            <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()"></a>
            <%-- 保存 --%>
            <a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true"></a>
            <%-- 删除 --%>
            <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteUser()"></a>
        </span>
        <span id="searchBlock" align="right">
            Date From: <input class="easyui-datebox" style="width:110px">
            To: <input class="easyui-datebox" style="width:110px">
            Language:
            <select class="easyui-combobox" panelHeight="auto" style="width:100px">
                <option value="java">Java</option>
                <option value="c">C</option>
                <option value="basic">Basic</option>
                <option value="perl">Perl</option>
                <option value="python">Python</option>
            </select>
            <a href="#" class="easyui-linkbutton" iconCls="icon-search">Search</a>
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

        //页面JS初始化
        $(function() {

            //user table初始化
            <%--$('#userTable').datagrid({--%>
                <%--title: '用户列表',--%>
                <%--iconCls: 'icon-save',--%>
                <%--//是否截取超长字符串--%>
                <%--nowrap: false,--%>
                <%--//是否显示行背景--%>
                <%--striped: true,--%>
                <%--collapsible: true,--%>
                <%--//请求数据地址--%>
                <%--url: '${ctx}/platform/account/user/dataGrid',--%>
                <%--//主键属性--%>
                <%--idField: 'id',--%>
                <%--frozenColumns: [[--%>
                    <%--{field: 'id',checkbox: true},--%>
                    <%--{title: '账户名称',field: 'accountName',width: 80}--%>
                <%--]],--%>
                <%--columns: [[--%>
                    <%--{title: '基本信息',colspan: 4},--%>
                    <%--{title: '操作',field: 'opt',width: 100,align: 'center', rowspan: 2}--%>
                <%--],[--%>
                    <%--{title: '用户昵称',field: 'name',width: 120},--%>
                    <%--{title: '联系电话',field: 'phoneNum',width: 220},--%>
                    <%--{title: '邮箱地址',field: 'mailAddress',width: 150},--%>
                    <%--{title: '联系地址',field: 'address',width: 100}--%>
                <%--]],--%>
                <%--//是否分页--%>
                <%--pagination: true,--%>
                <%--//起始页码 默认为1 后台传递参数为 page--%>
                <%--pageNumber: 0,--%>
                <%--//是否显示行号--%>
                <%--rownumbers: true,--%>
                <%--//请求数据时额外携带的参数--%>
                <%--queryParams: {},--%>
                <%--//加载数据时显示的文字--%>
                <%--//loadMsg: '正在加载数据…',--%>
                <%--toolbar: [{--%>
                    <%--id: 'btnadd',--%>
                    <%--text: '新增',--%>
                    <%--iconCls: 'icon-add',--%>
                    <%--handler: function(){--%>
                        <%--//$('#btnsave').linkbutton('enable');--%>
<%--//                        addUser();--%>
<%--//                        var add_user_dialog = $('#addUser').dialog('open');--%>
                    <%--}--%>
                <%--},{--%>
                    <%--id: 'btncut',--%>
                    <%--text: '删除',--%>
                    <%--iconCls: 'icon-cancel',--%>
                    <%--handler: function(){--%>
<%--//                        $('#btnsave').linkbutton('enable');--%>
<%--//                        alert('cut')--%>
                    <%--}--%>
                <%--}]--%>

            <%--});--%>

            //通过函数调用设置分页控件
//        var userPage = $('#userTable').datagrid('getPager');
//        $(userPage).pagination({
//            pageSize: 10,//每页显示的记录条数，默认为10
//            pageList: [10, 15, 20],//可以设置每页记录条数的列表
//            beforePageText: '第',//页数文本框前显示的汉字
//            afterPageText: '页    共 {pages} 页',
//            displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
//            /*onBeforeRefresh:function(){
//             $(this).pagination('loading');
//             alert('before refresh');
//             $(this).pagination('loaded');
//             }*/
//        });
        });

    </script>
</inheritance:override>
<!-- 继承父类 base.jsp -->
<%@ include file="/WEB-INF/views/base/base.jsp" %>
