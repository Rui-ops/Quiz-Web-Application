package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.*;

public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
        HttpSession session = request.getSession(true);
        session.setAttribute("user", new bean.User());
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String username = request.getParameter("user");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String req_question = request.getParameter("question");
        boolean userExist = false;
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/mysql");
            Connection conn = ds.getConnection();
            if (email != null) {
                String sql_rows = "select id from users order by id DESC limit 1";
                PreparedStatement statement_rows = conn.prepareStatement(sql_rows);
                ResultSet resultSet_rows = statement_rows.executeQuery(sql_rows);
                int id = 0;
                while (resultSet_rows.next()) {
                    id = Integer.parseInt(resultSet_rows.getString(1)) + 1;
                }
                resultSet_rows.close();
                PreparedStatement statement = conn.prepareStatement("insert into users(id, username, password, email, grade)values(?,?,?,?,?)");
                statement.setString(1, "" + id);
                statement.setString(2, username);
                statement.setString(3, password);
                statement.setString(4, email);
                statement.setString(5, "0");
                int resultSet = statement.executeUpdate();
                response.setContentType("text/html;charset=UTF-8");
                RequestDispatcher rd = request.getRequestDispatcher("registersucc.jsp");
                rd.forward(request, response);
            } else {
                if (req_question == null) {
                    String sql = "select password from users where username = '" + username + "'";
                    PreparedStatement statement = conn.prepareStatement(sql);
                    ResultSet resultSet = statement.executeQuery(sql);
                    while (resultSet.next()) {
                        String DBpassword = resultSet.getString(1);
                        if (password.equals(DBpassword)) {
                            userExist = true;
                        }
                    }
                    resultSet.close();
                    if (userExist && !username.equals("admin")) {
                        try {
                            response.setContentType("text/html");
                            String sql_question = "select * from questions";
                            Statement statement_question = conn.createStatement();
                            ResultSet resultSet_question = statement_question.executeQuery(sql_question);
                            out.println("Try to solve these multiple-choice questions!");
                            out.println("<form action=\"Score.jsp\" method=\"POST\">");
                            int i = 1;
                            while (resultSet_question.next()) {
                                String question = resultSet_question.getString("question");
                                String a = resultSet_question.getString("option_a");
                                String b = resultSet_question.getString("option_b");
                                String c = resultSet_question.getString("option_c");
                                String d = resultSet_question.getString("option_d");
                                out.println("<p>" + "Question " + i + ": " + question + "=" + "</p>");
                                out.println("<p><input type=\"checkbox\" name=\"question" + i + "\" value=\"a\" />" + a);
                                out.println("<p><input type=\"checkbox\" name=\"question" + i + "\" value=\"b\" />" + b);
                                out.println("<p><input type=\"checkbox\" name=\"question" + i + "\" value=\"c\" />" + c);
                                out.println("<p><input type=\"checkbox\" name=\"question" + i + "\" value=\"d\" />" + d + "</p>");
                                i++;
                            }
                            out.println("<input type=\"submit\"></form>");
                        } catch (Exception e) {
                            out.println(e.getMessage());
                        }
                    } else if (userExist && username.equals("admin")) {
                        response.setContentType("text/html;charset=UTF-8");
                        RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
                        rd.forward(request, response);
                    } else {
                        response.setContentType("text/html;charset=UTF-8");
                        RequestDispatcher rd = request.getRequestDispatcher("loginfail.jsp");
                        rd.forward(request, response);
                    }
                }
                else{
                    String option_a = request.getParameter("a");
                    String option_b = request.getParameter("b");
                    String option_c = request.getParameter("c");
                    String option_d = request.getParameter("d");
                    String answer = request.getParameter("answer");
                    String sql_rows = "select id from questions order by id DESC limit 1";
                    PreparedStatement statement_rows = conn.prepareStatement(sql_rows);
                    ResultSet resultSet_rows = statement_rows.executeQuery(sql_rows);
                    int id = 0;
                    while (resultSet_rows.next()) {
                        id = Integer.parseInt(resultSet_rows.getString(1)) + 1;
                    }
                    resultSet_rows.close();
                    PreparedStatement statement = conn.prepareStatement("insert into questions(id, question, option_a, option_b, option_c, option_d, answer)values(?,?,?,?,?,?,?)");
                    statement.setString(1, "" + id);
                    statement.setString(2, req_question);
                    statement.setString(3, option_a);
                    statement.setString(4, option_b);
                    statement.setString(5, option_c);
                    statement.setString(6, option_d);
                    statement.setString(7, answer);
                    int resultSet = statement.executeUpdate();
                    response.setContentType("text/html;charset=UTF-8");
                    RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
                    rd.forward(request, response);
                }

            }
        } catch (Exception e) {
            out.println(e.getMessage());
        }
    }
}
