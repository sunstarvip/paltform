<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.DarKnight.com.cn/jsp-extends" prefix="inheritance" %>

<%-- 用户新增对话框 --%>
<inheritance:override name="addDialog">
    <div class="easyui-dialog" id="userDialog"
         closed="true" buttons="#dlg-buttons"
         style="padding:10px 20px">
        <%--<div>用户信息</div>--%>
        <form id="userform" method="post">
            <%-- Hidden属性 begin--%>
            <%-- 用户ID --%>
            <input name="id" type="hidden">
            <%-- Hidden属性 end--%>

            <table cellpadding="5">
                <tr>
                    <td>账户名称:</td>
                    <td>
                        <input class="easyui-validatebox" name="accountName" type="text" required="true">
                    </td>
                </tr>
                <tr id="passwordRow">
                    <td>密码:</td>
                    <td>
                        <input class="easyui-validatebox" name="password" type="text" required="true">
                    </td>
                </tr>
                <tr>
                    <td>用户昵称:</td>
                    <td>
                        <input class="easyui-validatebox" name="name" type="text">
                    </td>
                </tr>
                <tr>
                    <td>联系电话:</td>
                    <td>
                        <input name="phoneNum" type="text">
                    </td>
                </tr>
                <tr>
                    <td>邮箱地址:</td>
                    <td>
                        <input class="easyui-validatebox" name="mailAddress" validType="email" type="text">
                    </td>
                </tr>
                <tr>
                    <td>联系地址:</td>
                    <td>
                        <input class="easyui-validatebox" name="address" type="text">
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">保存</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#userDialog').dialog('close')">取消</a>
    </div>
</inheritance:override>

<%-- 用户新增对话框对应JS --%>
<inheritance:override name="addScript">
    <script>
        //保存用户与更新用户路径
        var url='';

        //新增用户
        function newUser() {
            $('#userDialog').dialog('open').dialog('setTitle', '新增用户');
            $('#userform').form('clear');
            url = '${ctx}/platform/account/user/save';
        }

        //编辑用户
        function editUser() {
            var row = $('#userTable').datagrid('getSelected');
            if(row) {
                $('#userDialog').dialog('open').dialog('setTitle','编辑用户');
                $('#userform').form('load', row);
                $('#passwordRow').hide();
                url = '${ctx}/platform/account/user/update';
            }
        }

        //保存用户
        function saveUser() {
            $('#userform').form('submit',{
                url: url,
                onSubmit: function(){
                    return $(this).form('validate');
                },
                success: function(result){
//                    var result = eval('('+result+')');
                    if (result=='fail'){
                        $.messager.show({
                            title: 'Error',
                            msg: result
                        });
                    }else {
                        // 关闭对话框
                        $('#userDialog').dialog('close');
                        // 重载用户信息列表
                        $('#userTable').datagrid('reload');
                    }
                }
            });
        }

        //删除用户
        function deleteUser() {
            var row = $('#userTable').datagrid('getSelected');
            if (row){
                $.messager.confirm('删除用户','是否确认删除选中用户？',
                    function(e) {
                        if(e) {
                            $.post('delete',
                                {userId: row.id},
                                function(result) {
                                    if (result=='sucess') {
                                        // 重载用户信息列表
                                        $('#userTable').datagrid('reload');
                                    }else {
                                        // 展示错误信息
                                        $.messager.show({
                                            title: 'Error',
                                            msg: result
                                        });
                                    }
                                },
                            'json');
                        }
                    }
                );
            }
        }
    </script>

</inheritance:override>

<!-- 继承父类 base.jsp -->
<%@ include file="/WEB-INF/views/platform/user/userList_easyui.jsp" %>