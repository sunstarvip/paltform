<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.DarKnight.com.cn/jsp-extends" prefix="inheritance" %>
<%--<c:set var="ctx" value="${pageContext.request.contextPath}"/>--%>

<%--重定义父页面name=scriptSrc的内容--%>
<inheritance:override name="scriptSrc">
    <script type="text/javascript" src="${ctx}/static/project/base/base.js" ></script>
    <script type="text/javascript" src="${ctx}/static/project/platform/user/user.js" ></script>
</inheritance:override>

<%--重定义父页面name=centerContent的内容--%>
<inheritance:override name="body">
    <body>
        <table class="easyui-datagrid" id="userTable" title="用户列表"
               iconCls='icon-save' rownumbers="true" fitColumns="true"
               url="${ctx}/platform/account/user/dataGrid"
               toolbar="#toolbar" collapsible="false" border="false"
               singleSelect="true" nowrap="false" striped="true"
               idField="id" pagination="true" pageNumber="1" pageSize="12"
               checkOnSelect="true" selectOnCheck="true"
               pageList="[12,20,30,40,50]" loadMsg="数据载入中…"
               data-options="onLoadSuccess: loadSuccessFun" >
            <%-- 第一行 --%>
            <thead>
                <tr>
                    <th colspan="4" align="center">基本信息</th>
                    <th field="opt" formatter="addOptBtns" rowspan="2" width="80" align="center">操作</th>
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
                    <th field="name" width="80" align="center">用户昵称</th>
                    <th field="phoneNum" width="50" align="center">手机号码</th>
                    <th field="mailAddress" width="100" align="center">邮箱地址</th>
                    <th field="address" width="100" align="center">联系地址</th>
                </tr>
            </thead>
        </table>
            <%-- 搜索区域 --%>
        <div id="toolbar" style="padding:2px 5px;">
            <span id="buttonBlock" align="left">
                <%-- 新增 --%>
                <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="user.add('新增用户')"></a>
                <%-- 编辑 --%>
                <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="user.edit('编辑用户')"></a>
                <%-- 保存 --%>
                <%--<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true"></a>--%>
                <%-- 删除 --%>
                <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="user.delete()"></a>
            </span>
            <span id="searchBlock" align="right">
                账户名称: <input id="searchAccountName" name="searchAccountName" style="width:110px">
                手机号码: <input id="searchPhoneNum" name="searchPhoneNum" style="width:110px">
                <a href="#" class="easyui-linkbutton" iconCls="icon-search"
                   onclick="doSearch()">查询</a>
                <a href="#" class="easyui-linkbutton" iconCls="icon-clear"
                   onclick="doReset()">重置</a>
            </span>
        </div>

        <script>
            // 定义搜索功能
            function doSearch(){
                var searchKeyMap= {
                    accountName: 'searchAccountName',
                    phoneNum: 'searchPhoneNum'
                }
                user.doSearch(searchKeyMap);
            }

            // 定义重置搜索条件功能
            function doReset(){
                $('#searchAccountName').val('');
                $('#searchPhoneNum').val('');
            }

            // 定义DataGrid行样式
            function getRowStyle(index, row) {
                // 行数为row.listprice
                return "align:'left';";
            }

            // 定义用户密码重置为默认密码功能
            function resetPwd(userId) {
//                var userId = row['id'];
                $.post(
                        '${ctx}/platform/account/user/resetPwd',
                        {userId: userId},
                        function(result) {
                            var result = eval('('+result+')');
                            if(!!result && result['status']=='success') {
                                var successOtp = {
                                    title: '操作成功',
                                    msg: result['msgInfo'],
                                    timeout: 5000
                                }
                                $.messager.show(successOtp);
                            }else {
                                var failOtp = {
                                    title: '操作失败',
                                    msg: result['msgInfo'],
                                    timeout: 5000
                                }
                                $.messager.show(failOtp);
                            }
                        },
                        'text'
                );
            }


            // 定义行内操作按钮
            function addOptBtns(value, row, index) {
                var userId = row['id'];
                var resetPwdBtnStr = '<a href="#" class="resetBtn" onclick="resetPwd(\''+userId+'\');"></a>';
//                var resetPwdBtnStr = '<a href="#" class="optBtn" onclick="alert('+row['id']+');"></a>';
                return resetPwdBtnStr;
            }

            // 当easyui自动渲染页面成功后的操作
            function loadSuccessFun() {
                $('.resetBtn').linkbutton({
                    text: '重置密码',
                    iconCls: 'icon-reload',
                    height: 18,  // DataGrid默认行内高度为18px
                    plain: true
                });

            }

            // 定义全局JS对象
            var user = new User('${ctx}', 'userTable', 'userDialog', 'userForm');

            //页面JS初始化
            $(function() {
                var userBtns = [{
                    id: 'userSave',
                    text: '保存',
                    iconCls: 'icon-ok',
                    handler:function(){
                        user.save(user);
                    }
                },{
                    id: 'userCancel',
                    text: '取消',
                    iconCls: 'icon-cancel',
                    handler:function(){
                        user.cancel(user);
                    }
                }];

                user.initDialog({title: '系统角色', buttons: userBtns, href: '${ctx}/platform/account/user/dialogPage'});
            });

        </script>
    </body>
</inheritance:override>

<!-- 继承父类 base.jsp -->
<%@ include file="/WEB-INF/views/base/base.jsp" %>
