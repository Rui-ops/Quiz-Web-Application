<jsp:useBean class="bean.User" id="user" scope="session"></jsp:useBean>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>score.jsp</title>
    </head>

    <body>
            <%
            try {
                javax.naming.Context initContext = new javax.naming.InitialContext();
                javax.naming.Context envContext = (javax.naming.Context) initContext.lookup("java:/comp/env");
                javax.sql.DataSource ds = (javax.sql.DataSource) envContext.lookup("jdbc/mysql");
                java.sql.Connection conn = ds.getConnection();
                java.sql.ResultSet rs = null;
                java.sql.Statement statement;
                statement = conn.createStatement();
                String sql = "select * from questions";
                rs = statement.executeQuery(sql);
//                rs.last();
//                int rowCount = rs.getRow();
//                rs.beforeFirst();
                int x = 1;
                int score = 0;
                while (rs.next()) {
                    
                    String re = "question" + x;
                    String option[] = request.getParameterValues(re);
                    String str = "";
                    for (int i = 0; i < option.length; i++) {
                        if (i == 0) {
                            str = option[i];
                        } else {
                            str += option[i];
                        } 
                    }
                    String answer = rs.getString("answer");
                    
                    out.println("<p>Your answer for "+ re + " is: " + str + "</p>");
//                    out.println(str);
                    x++;
                    if (str.equals(answer)){
                        score++;
                        out.println("Correct!");
                    }
                    else{
                        out.println("Wrong!");
                    }
                    out.println("Correct answer is: "+answer);
                }
                out.println("<p>Well done with the quiz! Your score is :" + score + "</p>");
            } catch (Exception e) {
                out.println(e.getMessage());
            }
        %>
        <form action="LoginServlet" method="GET">
            <input type="submit" value="Try again">
        </form>
    </body>
</html>
