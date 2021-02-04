<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>register.jsp</title>
    </head>
    <body>
        <form action="LoginServlet" method="POST">
		<table border="0" align="left">
			<tr>
				<td>Please enter your username, password and email to register.</td>
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
                                <td>Email：</td>
				<td><input type="text" name="email" value=""/></td>
			</tr>
                        <tr>
                                <td><input type="submit" value="Register" /></td>
			</tr>
                        
		</table>
	</form>
    </body>
</html>
