<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.DarKnight.com.cn/jsp-extends" prefix="inheritance" %>
<%--<c:set var="ctx" value="${pageContext.request.contextPath}"/>--%>

<%--重定义父页面name=scriptSrc的内容--%>
<inheritance:override name="scriptSrc">
    <script type="text/javascript" src="${ctx}/static/project/platform/base/base.js" ></script>
</inheritance:override>

<%--重定义父页面name=centerContent的内容--%>
<inheritance:override name="centerContent">
    <%-- 用户新增对话框 --%>
    <inheritance:block name="addDialog">
    </inheritance:block>
</inheritance:override>

<inheritance:override name="centerScript">
    <%-- 用户新增对话框对应脚本 --%>
    <inheritance:block name="dialogScript">
    </inheritance:block>
</inheritance:override>
<!-- 继承父类 base.jsp -->
<%@ include file="/WEB-INF/views/base/base.jsp" %>
