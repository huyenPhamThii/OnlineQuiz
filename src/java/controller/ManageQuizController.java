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
import entity.Account;
import entity.Question;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class contains a method to display all questions belong to account that
 * login; get parameter pageIndex from the HTTP request; calls
 * <code>QuestionDAOImpl</code> to get number of page, number of total question
 * and get list of questions with pagination. Servlet will directly respond to
 * <code>Error.jsp</code> when any error occurs
 * <p>
 * Bugs: None
 *
 * @author Pham Thi Huyen
 */
public class ManageQuizController extends HttpServlet {

    /**
     * This method that get parameter pageIndex from the HTTP request; calls
     * <code>QuestionDAOImpl</code> to get number of page, number of total
     * question and get list of questions with pagination. Servlet will directly
     * respond to <code>Error.jsp</code> when any error occurs
     *
     * @param request stores attributes: pageIndex, numberOfPage,
     * totalQuestions, questionList, error to send to JSP. It is a
     * <code> javax.servlet.http.HttpServletRequest</code>
     * @param response sending a response to client's web browser. It is a
     * <code> javax.servlet.http.HttpServletResponse</code>
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //number of question in each page
    private final static int PAGE_SIZE = 3;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            QuestionDAO questionDAO = new QuestionDAOImpl();

            //In case pageIndexRaw is null means user doesn't click to pagination
            //so, auto display page number 1.
            int pageIndex = 1;
            String pageIndexRaw = request.getParameter("pageIndex");
            if (pageIndexRaw != null) {
                pageIndex = Integer.parseInt(pageIndexRaw);
            }
            request.setAttribute("pageIndex", pageIndex);

            //get account from session
            Account account = (Account) request.getSession(false).getAttribute("account");

            //Get number of page
            request.setAttribute("numberOfPage", questionDAO.getNumberOfPage(account.getUserName(), PAGE_SIZE));

            //get list of question with each account
            List<Question> questionList = questionDAO.getQuestionByPaging(account.getUserName(), pageIndex, PAGE_SIZE);
            request.setAttribute("questionList", questionList);

            //get total of question with each account
            request.setAttribute("totalQuestions", questionDAO.getNumberOfQuestionByAccount(account.getUserName()));

            request.getRequestDispatcher("ManageQuiz.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("error", ex);
            request.getRequestDispatcher("Error.jsp").forward(request, response);
        }

    }

}
