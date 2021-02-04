
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>admin.jsp</title>
    </head>
    <body>
        <form action="LoginServlet" method="POST">
            <table border="0" align="left">
                <tr>
                    <td>Welcome! You can add more questions here.</td>
                </tr>
                <tr>
                    <td>Question: </td>
                    <td><input type="text" name="question" value=""/></td>
                </tr>
                <tr>
                    <td>Answer a: </td>
                    <td><input type="text" name="a" value=""/></td>
                </tr>
                <tr>
                    <td>Answer b: </td>
                    <td><input type="text" name="b" value=""/></td>
                </tr>
                <tr>
                    <td>Answer c: </td>
                    <td><input type="text" name="c" value=""/></td>
                </tr>
                <tr>
                    <td>Answer d: </td>
                    <td><input type="text" name="d" value=""/></td>
                </tr>
                <tr>
                    <td>Correct answer: </td>
                    <td><input type="text" name="answer" value=""/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Sumbit" /></td>
                </tr>
                
            </table>
        </form>
    </body>
</html>
