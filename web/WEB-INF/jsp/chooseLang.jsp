<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <%--<fmt:setLocale value="${sessionScope.local}"/>--%>
    <fmt:setBundle basename="locale.locale" var="locale"/>

    <fmt:message bundle="${locale}" key="locale.text.chooseLang" var="chooseLang"/>
    <fmt:message bundle="${locale}" key="locale.text.enLang" var="eng"/>
    <fmt:message bundle="${locale}" key="locale.text.ruLang" var="rus"/>

    <title>${signupMessage}</title>
</head>
<body>
<form name="changeLangForm" method="POST" action="app">
    <input type="hidden" name="command" value="changeLang"/>
    ${chooseLang}: <a href="">${rus}</a> <a href="">${eng}</a>
</form>
</body>
</html>
