<jsp:useBean class="bean.User" id="user" scope="session"></jsp:useBean>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>login.jsp</title>
    </head>
    <body>
        <form action="LoginServlet" method="POST">
            <table border="0" align="left">
                <tr>
                    <td>Welcome to the quiz, please login.</td>
                </tr>
                <tr>
                    <td>Username：</td>
                    <td><input type="text" name="user" value=""/></td>
                </tr>
                <tr>
                    <td>Password：</td>
                    <td><input type="password" name="password" value=""/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Login" /></td>
                </tr>
                <tr>
                    <td>If you don't have an account, please 
                    <a href="register.jsp" target="_blank">Register</a></td>
                </tr>
                        
            </table>
        </form>
    </body>
</html>
