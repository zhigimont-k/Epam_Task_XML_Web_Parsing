<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form name="loginForm" method="POST" action="">
    <input type="hidden" name="command" value="login"/>
    Login:<br>
    <input type="text" name="login" value=""/>
    Password:<br>
    <input type="password" name="password" value=""/>
    <br>
    ${errorLoginPassMessage}
    <br>
    ${wrongAction}
    <br>
    ${nullPage}
    <br>
    <input type="submit" value="Login">
</form>
</body>
</html>
