package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.*;
import java.sql.ResultSet;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author ritch
 */
public class WebQuiz extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
        HttpSession session = request.getSession(true);
        session.setAttribute("user", new bean.User());
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("user");
        try {
            if (request.getParameter("user") != null) {
                Context initContext = new InitialContext();
                Context envContext = (Context) initContext.lookup("java:/comp/env");
                DataSource ds = (DataSource) envContext.lookup("jdbc/mysql");
                Connection conn = ds.getConnection();
                String sql = "select * from question";
//            PrepareStatement
                ResultSet resultSet;
                Statement statement;
                statement = conn.createStatement();
                resultSet = statement.executeQuery(sql);
                out.println("Try to solve these multiple-choice questions!");
                out.println("<form action=\"Score.jsp\" method=\"POST\">");
                while (resultSet.next()) {
                    int i = 1;
                    String question = resultSet.getString("quest");
                    String a = resultSet.getString("a");
                    String b = resultSet.getString("b");
                    String c = resultSet.getString("c");
                    String d = resultSet.getString("d");

                    out.println("<p>" + "Question " + i + ": " + question + "=" + "</p>");
                    out.println("<p><input type=\"checkbox\" name=\"question" + i + "\" value=\"a\" />" + a);
                    out.println("<p><input type=\"checkbox\" name=\"question" + i + "\" value=\"b\" />" + b);
                    out.println("<p><input type=\"checkbox\" name=\"question" + i + "\" value=\"c\" />" + c);
                    out.println("<p><input type=\"checkbox\" name=\"question" + i + "\" value=\"d\" />" + d + "</p>");
                    i++;
                }
                out.println("<input type=\"submit\"></form>");
            }
            else{
                out.println(request.getParameter("question1"));
            }

        } catch (Exception e) {
            out.println(e.getMessage());
        }
//        RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
//        rd.forward(request, response);

    }
    

}
