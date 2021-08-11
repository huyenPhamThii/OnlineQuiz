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
 * This class contains a method that get parameter userName, password, email,
 * userType from the HTTP request; calls <code>AccountDAOImpl</code> to check
 * account is existed, insert new <code>Account</code> to Account table. Servlet
 * will directly respond to <code>Error.jsp</code> when any error occurs
 * <p>
 * Bugs: None
 *
 * @author Pham Thi Huyen
 */
public class RegisterController extends HttpServlet {

    /**
     * This method forward to the <code>Register.jsp</code> page
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
        request.getRequestDispatcher("Register.jsp").forward(request, response);
    }

    /**
     * This method that get parameter userName, password, email, userType from
     * the HTTP request; calls <code>AccountDAOImpl</code> to check account is
     * existed, insert new <code>Account</code> to Account table. Servlet will
     * directly respond to <code>Error.jsp</code> when any error occurs when any
     * error occurs
     *
     * @param request stores attributes: username, password, email, userType,
     * error to send to <code>Register.jsp</code> if register fail. Otherwise
     * store session user. It is a <code> javax.servlet.http.HttpServletRequest
     * </code>
     * @param response sending a response to client's web browser. It is a
     * <code> javax.servlet.http.HttpServletResponse</code>
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountDAO accountDAO = new AccountDAOImpl();
        try {
            String userName = request.getParameter("userName").trim().toLowerCase();
            String password = request.getParameter("password").trim();
            int type = Integer.parseInt(request.getParameter("userType").trim());
            String email = request.getParameter("email").trim();

            //check account is existed
            boolean isExisted = accountDAO.isExisted(userName);

            //check empty field and length of input
            int userNameLength = userName.length();
            int passwordLength = password.length();

            if (isExisted || userName.isEmpty() || password.isEmpty() || email.isEmpty() || userNameLength > 20 || passwordLength > 20) {
                request.setAttribute("userName", userName);
                request.setAttribute("email", email);
                request.setAttribute("userType", type);
                if (isExisted) {
                    request.setAttribute("msg", "UserName already exist");
                } else if (userName.isEmpty()) {
                    request.setAttribute("msg", "UserName must be not empty");
                } else if (password.isEmpty()) {
                    request.setAttribute("msg", "Password must be not empty");
                } else if (email.isEmpty()) {
                    request.setAttribute("msg", "Email must be not empty");
                } else if (userNameLength > 20) {
                    request.setAttribute("msg", "UserName must be less than 20 characters");
                } else if (passwordLength > 20) {
                    request.setAttribute("msg", "Password must be less than 20 characters");
                }
                request.getRequestDispatcher("Register.jsp").forward(request, response);
            } else {
                //insert new account to database
                accountDAO.register(new Account(userName, password, email, type));
                //create session and set account to session
                HttpSession session = request.getSession();
                session.setAttribute("account", new Account(userName, password, email, type));
                
                request.setAttribute("success", "Register successfully!");
                request.getRequestDispatcher("Register.jsp").forward(request, response);
            }
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
