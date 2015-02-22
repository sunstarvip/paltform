<%--顶部属性每个页面需要单独引用, 无法继承--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.DarKnight.com.cn/jsp-extends" prefix="inheritance" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="locale" value="${pageContext.request.locale}"/>
<html>
<head>
    <inheritance:block name="title">
        <title>DarKnight支撑平台</title>
    </inheritance:block>

    <%-- Meta --%>
    <inheritance:block name="meta">
    </inheritance:block>

    <%-- CSS --%>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/plugins/EasyUI/EasyUI1.4.1/themes/default/easyui.css" >
    <link rel="stylesheet" type="text/css" href="${ctx}/static/plugins/EasyUI/EasyUI1.4.1/themes/icon.css" >
    <inheritance:block name="cssLink">
    </inheritance:block>

    <%-- JS --%>
    <script type="text/javascript" src="${ctx}/static/plugins/jQuery/jQuery2.x/jquery-2.1.3.min.js" ></script>
    <script type="text/javascript" src="${ctx}/static/plugins/EasyUI/EasyUI1.4.1/jquery.easyui.min.js" ></script>
    <script type="text/javascript" src="${ctx}/static/plugins/EasyUI/EasyUI1.4.1/locale/easyui-lang-zh_CN.js" ></script>
    <inheritance:block name="scriptSrc">
    </inheritance:block>

</head>

<inheritance:block name="body">
<body class="easyui-layout" id="mainPanel" >

    <%-- North Block --%>
    <inheritance:block name="north">
        <div data-options="region:'north',split:true" style="height:100px;">
            <inheritance:block name="northContent">

            </inheritance:block>
        </div>
    </inheritance:block>

    <%-- South Block --%>
    <inheritance:block name="south">
        <div data-options="region:'south',title:'South Title',split:true,collapsed:true" style="height:100px;">
            <inheritance:block name="southContent">

            </inheritance:block>
        </div>
    </inheritance:block>
    <%--<div data-options="region:'south',title:'South Title',split:true,collapsed:true" style="height:100px;"></div>--%>

    <%-- East Block --%>
    <inheritance:block name="east">
        <div data-options="region:'east',title:'East',split:true,collapsed:true" style="width:100px;">
            <inheritance:block name="eastContent">

            </inheritance:block>
        </div>
    </inheritance:block>
    <%--<div data-options="region:'east',title:'East',split:true,collapsed:true" style="width:100px;"></div>--%>

    <%-- West Block --%>
    <inheritance:block name="west">
        <div data-options="region:'west',title:'West',split:true" style="width:200px;">
            <inheritance:block name="westContent">

            </inheritance:block>
        </div>
    </inheritance:block>
    <%--<div data-options="region:'west',title:'West',split:true" style="width:200px;"></div>--%>

    <%-- Center Block --%>
    <inheritance:block name="center">
        <div data-options="region:'center',title:'center title'" style="padding:5px;">
            <inheritance:block name="centerContent">

            </inheritance:block>
        </div>
    </inheritance:block>
    <%--<div data-options="region:'center',title:'center title'" style="padding:5px;"></div>--%>


<inheritance:block name="scriptBlock">
    <script>
    </script>
</inheritance:block>

</body>
</inheritance:block>
</html>

