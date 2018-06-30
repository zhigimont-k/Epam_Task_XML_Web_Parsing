<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="locale.locale" var="locale"/>

    <fmt:message bundle="${locale}" key="locale.text.resultTable.id" var="id"/>
    <fmt:message bundle="${locale}" key="locale.text.resultTable.title" var="title"/>
    <fmt:message bundle="${locale}" key="locale.text.resultTable.monthly" var="monthly"/>
    <fmt:message bundle="${locale}" key="locale.text.resultTable.gloss" var="gloss"/>
    <fmt:message bundle="${locale}" key="locale.text.resultTable.volume" var="volume"/>
    <fmt:message bundle="${locale}" key="locale.text.resultTable.subscrIndex" var="subscriptionIndex"/>
    <fmt:message bundle="${locale}" key="locale.text.resultTable.firstPublication" var="publicationDate"/>

    <title>Result</title>
</head>
<body>
<div>
    <table>
    <tr>
        <th>${id}</th>
        <th>${title}</th>
        <th>${monthly}</th>
        <th>${gloss}</th>
        <th>${volume}</th>
        <th>${subscriptionIndex}</th>
        <th>${publicationDate}</th>
    </tr>
    <c:forEach var="paper" items="${resultSet}">

        <tr>
            <td>${paper.id}</td>
            <td>${paper.title}</td>
            <td>${paper.monthly}</td>
            <td>${paper.gloss}</td>
            <td>${paper.volume}</td>
            <td>${paper.subscriptionIndex}</td>
            <td>${paper.firstPublicationDate}</td>
        </tr>
    </c:forEach>
    </table>
</div>
</body>
</html>
