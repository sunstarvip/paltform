<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.DarKnight.com.cn/jsp-extends" prefix="inheritance" %>

<%-- 用户新增对话框 --%>
<inheritance:override name="addDialog">
    <div class="easyui-dialog" id="roleDialog"
         closed="true" buttons="#dlg-buttons"
         style="padding:10px 20px">
        <form id="roleform" method="post">
            <%-- Hidden属性 begin--%>
            <%-- 角色ID --%>
            <input name="id" type="hidden">
            <%-- Hidden属性 end--%>

            <table cellpadding="5">
                <tr>
                    <td>角色名称:</td>
                    <td>
                        <input class="easyui-validatebox" name="name" type="text" required="true">
                    </td>
                </tr>
                <tr>
                    <td>角色描述:</td>
                    <td>
                        <textarea name="description"></textarea>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveRole()">保存</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#roleDialog').dialog('close')">取消</a>
    </div>
</inheritance:override>

<%-- 用户新增对话框对应JS --%>
<inheritance:override name="addScript">
    <script>
        //保存用户与更新用户路径
        var url='';

        //新增角色
        function newRole() {
            $('#roleDialog').dialog('open').dialog('setTitle', '新增角色');
            $('#roleform').form('clear');
            url = '${ctx}/platform/account/role/save';
        }

        //编辑角色
        function editRole() {
            var row = $('#roleTable').datagrid('getSelected');
            if(row) {
                $('#roleDialog').dialog('open').dialog('setTitle','编辑角色');
                $('#roleform').form('load', row);
                url = '${ctx}/platform/account/role/update';
            }
        }

        //保存角色
        function saveRole() {
            $('#roleform').form('submit',{
                url: url,
                onSubmit: function(){
                    return $(this).form('validate');
                },
                success: function(result){
                    if (result['status']=='success'){
                        // 关闭对话框
                        $('#roleDialog').dialog('close');
                        // 重载用户信息列表
                        $('#roleTable').datagrid('reload');
                    }else {
                        $.messager.show({
                            title: 'Error',
                            msg: result
                        });
                    }
                }
            });
        }

        //删除用户
        function deleteRole() {
            var row = $('#roleTable').datagrid('getSelected');
            if (row){
                $.messager.confirm('删除角色','是否确认删除选中角色？',
                    function(e) {
                        if(e) {
                            $.post('${ctx}/platform/account/role/delete',
                                {roleId: row.id},
                                function(result) {
                                    if (result['status']=='success') {
                                        // 重载角色信息列表
                                        $('#roleTable').datagrid('reload');
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
<%@ include file="/WEB-INF/views/platform/role/roleList_easyui.jsp" %>