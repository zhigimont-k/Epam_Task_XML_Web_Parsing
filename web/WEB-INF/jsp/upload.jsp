<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="locale.locale" var="locale"/>

    <fmt:message bundle="${locale}" key="locale.text.uploadFile" var="uploadFile"/>
    <fmt:message bundle="${locale}" key="locale.button.uploadFile" var="uploadButton"/>

    <title>${uploadFile}</title>
</head>
<body>${uploadFile}:
<form action="upload" method="POST" enctype="multipart/form-data">
    <input type="file" name="file"/> <br>
    <button type="submit" name="upload">${uploadButton}</button>
</form>
</body>
</html>
