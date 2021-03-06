<%--
  Created by IntelliJ IDEA.
  User: DarKnight
  Date: 2015/4/10
  Time: 23:00
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.DarKnight.com.cn/jsp-extends" prefix="inheritance" %>

<%--重定义父页面name=body的内容--%>
<inheritance:override name="body">
  <body>
      <form id="userForm" method="post">
          <%-- Hidden属性 begin--%>
          <%-- 用户ID --%>
          <input name="id" type="hidden" value="${user.id}">
          <input name="password" type="hidden" value="${user.password}">
          <%-- Hidden属性 end--%>

          <table cellpadding="5">
              <tr>
                  <td>账户名称: </td>
                  <td>
                      <input class="easyui-validatebox" name="accountName" value="${user.accountName}"
                             type="text" required="true"
                             validType="remote['${ctx}/platform/account/user/checkAccountName?id=${user.id}', 'accountName']"
                             missingMessage="请输入账户名称" invalidMessage="该账户名称已存在！" >
                  </td>
              </tr>
              <%--<tr id="passwordRow">--%>
                  <%--<td>密码: </td>--%>
                  <%--<td>--%>
                      <%--<input class="easyui-validatebox" name="password" value="${user.password}"--%>
                             <%--type="text" required="true">--%>
                  <%--</td>--%>
              <%--</tr>--%>
              <tr>
                  <td>用户昵称: </td>
                  <td>
                      <input class="easyui-validatebox" name="name" value="${user.name}" type="text">
                  </td>
              </tr>
              <tr>
                  <td>手机号码: </td>
                  <td>
                      <input class="easyui-numberbox easyui-validatebox" name="phoneNum" value="${user.phoneNum}"
                             type="text" validType="length[11, 11]"
                             invalidMessage="请输入正确的手机号码！" >
                  </td>
              </tr>
              <tr>
                  <td>邮箱地址: </td>
                  <td>
                      <input class="easyui-validatebox" name="mailAddress" value="${user.mailAddress}"
                             validType="email" type="text">
                  </td>
              </tr>
              <tr>
                  <td>联系地址: </td>
                  <td>
                      <input class="easyui-validatebox" name="address" value="${user.address}"
                             type="text">
                  </td>
              </tr>
              <tr>
                  <td>所属角色: </td>
                  <td>
                      <%--<select class="easyui-combobox" name="roleList.id" editable="false"--%>
                              <%--url="${ctx}/platform/account/user/getRoleList?userId=${user.id}"--%>
                              <%--multiple="true" method="get" valueField="id" textField="text"--%>
                              <%--data-options="panelHeight:'auto'" style="width:155px;">--%>
                      <%--</select>--%>
                      <select id="parentId" name="roleList.id" style="width:156px;" />
                  </td>
              </tr>
              <tr>
                  <td>注: </td>
                  <td>新建用户密码默认为 123456</td>
              </tr>
          </table>
      </form>

      <%--<script type="text/javascript" src="${ctx}/static/project/platform/base/base.js" ></script>--%>
      <%--<script type="text/javascript" src="${ctx}/static/project/platform/user/user.js" ></script>--%>
      <script>
          //页面JS初始化
          $(function() {
              $('#parentId').combobox({
                  url:'${ctx}/platform/account/role/getRoleList?userId=${user.id}',
                  method: 'get',
                  editable: false,
                  multiple: true,
                  valueField: 'id',
                  textField: 'text',
                  panelHeight: 'auto',
                  icons: [{
                      iconCls: 'icon-clear',
                      handler: function(e){
                          $(e.data.target).combobox('clear');
                      }
                  }]
              });
          });

      </script>
  </body>
</inheritance:override>

<%--继承父类 base.jsp--%>
<%@ include file="/WEB-INF/views/base/base.jsp" %>
