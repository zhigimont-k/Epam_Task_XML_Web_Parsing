<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="locale.locale" var="locale"/>

    <fmt:message bundle="${locale}" key="locale.text.chooseParser" var="chooseParser"/>
    <fmt:message bundle="${locale}" key="locale.text.SAXParser" var="SAX"/>
    <fmt:message bundle="${locale}" key="locale.text.DOMParser" var="DOM"/>
    <fmt:message bundle="${locale}" key="locale.text.StAXParser" var="StAX"/>

    <title>${chooseParser}</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/app" method="get">
    <input type="hidden" name="xmlPath" value="data/papers.xml">
    <input type="hidden" name="xsdPath" value="data/papers.xsd">
    <input type="hidden" name="command" value="parse">
    <button type="submit" name="parserType" value="DOM">${DOM}</button>
    <button type="submit" name="parserType" value="SAX">${SAX}</button>
    <button type="submit" name="parserType" value="StAX">${StAX}</button>
</body>
</html>
