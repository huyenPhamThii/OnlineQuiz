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

import dao.QuestionDAO;
import dao.impl.QuestionDAOImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class contains a method that calls <code>QuestionDAOImpl</code> to
 * getNumberOfAllQuestion; get parameter isSubmit from the HTTP request. Servlet
 * will directly respond to <code>Error.jsp</code> when any error occurs
 * <p>
 * Bugs: None
 *
 * @author Pham Thi Huyen
 */
public class TakeQuizController extends HttpServlet {

    /**
     * This method that calls QuestionDAOImpl to getNumberOfAllQuestion; then
     * turn to TakeQuiz.jsp if don't have any error. Servlet will directly
     * respond to "Error.jsp" when any error occurs
     *
     * @param request stores attributes: numOfQuestion, error to send to
     * <code>TakeQuiz.jsp</code>. It is a <code> javax.servlet.http.HttpServletRequest
     * </code>
     * @param response sending a response to client's web browser. It is a
     * <code> javax.servlet.http.HttpServletResponse</code>
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            QuestionDAO questionDAO = new QuestionDAOImpl();
            int totalQuestion = questionDAO.getNumberOfAllQuestion();
            request.setAttribute("totalQuestion", totalQuestion);
            request.getRequestDispatcher("TakeQuiz.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("error", ex);
            request.getRequestDispatcher("Error.jsp").forward(request, response);
        }
    }

    /**
     * This method parameter numOfQuestion from the HTTP request then forward to
     * doQuiz. Servlet will directly respond to <code>Error.jsp</code>
     * when any error occurs
     *
     * @param request stores attributes: numOfQuestion, error. It is a
     * <code>javax.servlet.http.HttpServletRequest</code>
     * @param response sending a response to client's web browser. It is a
     * <code> javax.servlet.http.HttpServletResponse</code>
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setAttribute("numberOfQuestion", Integer.parseInt(request.getParameter("numOfQuestion")));
            request.getRequestDispatcher("doQuiz").forward(request, response);
        } catch (NumberFormatException ex) {
            request.setAttribute("error", "TakeQuizController: " + ex);
            request.getRequestDispatcher("Error.jsp").forward(request, response);
        }
    }

}
