/**
 * Copyright(C) 2021, Pham Thi Huyen
 * J3.L.P0001
 * Online Quiz
 *
 * Record of change:
 * DATE          VERSION    AUTHOR      DESCRIPTION
 * 2021-07-10    1.0        HuyenPT     First Implement
 * 2021-07-13    2.0        HuyenPT     Fix header class, header method comment
 * 2021-07-15    3.0        HuyenPT     Fix header class, header method comment
 */
package controller;

import dao.AccountDAO;
import dao.impl.AccountDAOImpl;
import entity.Account;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This class contains a method that get parameter username, password from the
 * HTTP request; calls AccountDAOImpl to get <code>Account</code> by userName
 * and password. Servlet will directly respond to <code>Error.jsp</code> when
 * any error occurs
 * <p>
 * Bugs: None
 *
 * @author Pham Thi Huyen
 */
public class LoginController extends HttpServlet {

    /**
     * This method forward to the <code>Login.jsp</code> page
     *
     * @param request It is a
     * <code> javax.servlet.http.HttpServletRequest</code>
     * @param response sending a response to client's web browser. It is a
     * <code> javax.servlet.http.HttpServletResponse</code>
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Login.jsp").forward(request, response);
    }

    /**
     * This method get parameter username, password from the HTTP request; calls
     * AccountDAOImpl to get <code>Account</code> by userName and password; then
     * forward to the <code>Login.jsp</code>. Servlet will directly respond to
     * <code>Error.jsp</code> when any error occurs
     *
     * @param request stores attributes: userName, mess, error to send to JSP if
     * user login fail. Store session user if login success. It is a
     * <code> javax.servlet.http.HttpServletRequest</code>
     * @param response sending a response to client's web browser. It is a
     * <code> javax.servlet.http.HttpServletResponse</code>
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        AccountDAO accountDAO = new AccountDAOImpl();
        try {
            //remove all session
            request.getSession().invalidate();

            String userName = request.getParameter("userName").trim();
            String password = request.getParameter("password").trim();

            //check empty field and length of input
            int userNameLength = userName.length();
            int passwordLength = password.length();

            if (userName.isEmpty() || password.isEmpty() || userNameLength > 20 || passwordLength > 20) {
                if (userName.isEmpty()) {
                    request.setAttribute("msg", "UserName must be not empty.");
                } else if (password.isEmpty()) {
                    request.setAttribute("msg", "Password must be not empty");
                } else if (userNameLength > 20) {
                    request.setAttribute("msg", "UserName must less than 20 characters");
                } else if (passwordLength > 20) {
                    request.setAttribute("msg", "Password must less than 20 characters");
                }
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }

            //check is correct account
            Account account = accountDAO.login(userName, password);

            if (account != null) {
                if (account.getUserName().equals(userName) && account.getPassword().equals(password)) {
                    //create session and set account to session
                    HttpSession session = request.getSession();
                    session.setAttribute("account", account);
                } else {
                    request.setAttribute("userName", userName);
                    request.setAttribute("msg", "Wrong username or password. Please check again!");
                }
            } else {
                request.setAttribute("userName", userName);
                request.setAttribute("msg", "Wrong username or password. Please check again!");
            }

            request.getRequestDispatcher("Login.jsp").forward(request, response);

        } catch (Exception ex) {
            request.setAttribute("error", ex);
            request.getRequestDispatcher("Error.jsp").forward(request, response);
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
