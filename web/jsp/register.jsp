<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="locale.locale" var="locale"/>

    <fmt:message bundle="${locale}" key="locale.text.signup" var="signupMessage"/>
    <fmt:message bundle="${locale}" key="locale.text.login" var="loginLabel"/>
    <fmt:message bundle="${locale}" key="locale.text.password" var="passwordLabel"/>
    <fmt:message bundle="${locale}" key="locale.button.signup" var="signupButton"/>
    <fmt:message bundle="${locale}" key="locale.text.haveAccountAlready" var="loginNow"/>
    <fmt:message bundle="${locale}" key="locale.text.signin" var="signIn"/>

    <title>${signupMessage}</title>
</head>
<body>
<form name="signupForm" method="POST" action="app">
    <input type="hidden" name="command" value="register"/>
    ${loginLabel}<br/>
    <input type="text" name="login" maxlength="20" minlength="4"/>
    <br/>${passwordLabel}<br/>
    <input type="password" name="password" maxlength="32" minlength="10"/>
    <br/>
    <input type="submit" value="${signupButton}"/>
    <br/>
</form>
</body>
</html>
