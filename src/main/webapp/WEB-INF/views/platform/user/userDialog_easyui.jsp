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
              <tr id="passwordRow">
                  <td>密码: </td>
                  <td>
                      <input class="easyui-validatebox" name="password" value="${user.password}"
                             type="text" required="true">
                  </td>
              </tr>
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
          </table>
      </form>

  </body>
</inheritance:override>

<%--继承父类 base.jsp--%>
<%@ include file="/WEB-INF/views/base/base.jsp" %>
