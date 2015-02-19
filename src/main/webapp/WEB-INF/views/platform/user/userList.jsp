<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.DarKnight.com.cn/jsp-extends" prefix="inheritance" %>
<%--<c:set var="ctx" value="${pageContext.request.contextPath}"/>--%>
<% //重定义父页面name=centerContent的内容 %>
<inheritance:override name="centerContent">
    <table id="userTable"></table>
</inheritance:override>

<inheritance:override name="scriptBlock">
    <script>
        $('#userTable').datagrid({
            title: '用户列表',
            iconCls: 'icon-save',
            //是否截取超长字符串
            nowrap: false,
            //是否显示行背景
            striped: true,
            collapsible: true,
            //请求数据地址
            url: '${ctx}/platform/account/user/dataGrid',
            //主键属性
            idField: 'id',
            frozenColumns: [[
                {field: 'id',checkbox: true},
                {title: '账户名称',field: 'accountName',width: 80}
            ]],
            columns: [[
                {title: '基本信息',colspan: 4},
                {title: '操作',field: 'opt',width: 100,align: 'center', rowspan: 2}
            ],[
                {title: '用户昵称',field: 'name',width: 120},
                {title: '联系电话',field: 'addr',width: 220},
                {title: '邮箱地址',field: 'mailAddress',width: 150},
                {title: '联系地址',field: 'address',width: 100}
            ]],
            //是否分页
            pagination: true,
            //起始页码 默认为1 后台传递参数为 page
            pageNumber: 0,
            //是否显示行号
            rownumbers: true,
            //请求数据时额外携带的参数
            queryParams: {},
            //加载数据时显示的文字
            //loadMsg: '正在加载数据…',
            toolbar: [{
                id: 'btnadd',
                text: '新增',
                iconCls: 'icon-add',
                handler: function(){
                    $('#btnsave').linkbutton('enable');
                    alert('add')
                }
            },{
                id: 'btncut',
                text: '删除',
                iconCls: 'icon-cancel',
                handler: function(){
                    $('#btnsave').linkbutton('enable');
                    alert('cut')
                }
            }]

        });

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
    </script>
</inheritance:override>
<!-- extends from base.jsp -->
<%@ include file="/WEB-INF/views/base/base.jsp" %>
