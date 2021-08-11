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
import entity.Answer;
import entity.Question;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class contains doGet method to forward to <code>MakeQuiz.jsp</code>
 * page; doPost method that gets parameter content, opt1,2,3,4, answer from the
 * HTTP request; call QuestionDAOImpl to insert question into Question table and
 * correct answer to answer table; Servlet will directly respond to
 * <code>Error.jsp</code> when any error occurs
 * <p>
 * Bugs: None
 *
 * @author Pham Thi Huyen
 */
public class MakeQuizController extends HttpServlet {

    /**
     * This method forward to the <code>MakeQuiz.jsp</code> page
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
        request.getRequestDispatcher("MakeQuiz.jsp").forward(request, response);
    }

    /**
     * This method that gets parameter content, opt1,2,3,4, answer from the HTTP
     * request; check user selected answer or not, check all field empty or not;
     * call QuestionDAOImpl to insert question into Question table and correct
     * answer to answer table then forward to <code>MakeQuiz.jsp</code> with
     * message; Servlet will directly respond to <code>Error.jsp</code> when any
     * error occurs
     *
     * @param request stores attributes: content, opt1,2,3,4, mess error to send
     * to JSP if user does not selected any answer; It is a
     * <code> javax.servlet.http.HttpServletRequest</code>
     * @param response sending a response to client's web browser. It is a
     * <code> javax.servlet.http.HttpServletResponse</code>
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            QuestionDAO questionDAO = new QuestionDAOImpl();
            Question question;

            String content = request.getParameter("txtContent").trim();

            //get list of options
            ArrayList<String> optionList = new ArrayList<>();
            String option1 = request.getParameter("txtOption1").trim();
            String option2 = request.getParameter("txtOption2").trim();
            String option3 = request.getParameter("txtOption3").trim();
            String option4 = request.getParameter("txtOption4").trim();
            optionList.add(option1);
            optionList.add(option2);
            optionList.add(option3);
            optionList.add(option4);

            //get answer in checkbox
            String[] answerList = request.getParameterValues("answer");

            //check is selected answer
            boolean isSelectAnswer = false;
            if (answerList != null) {
                isSelectAnswer = true;
            }

            //check field empty and length
            int contentLength = content.length();
            int opt1Length = option1.length();
            int opt2Length = option2.length();
            int opt3Length = option3.length();
            int opt4Length = option4.length();

            if (!isSelectAnswer
                    || content.isEmpty()
                    || request.getParameter("txtOption1").trim().isEmpty()
                    || request.getParameter("txtOption2").trim().isEmpty()
                    || request.getParameter("txtOption3").trim().isEmpty()
                    || request.getParameter("txtOption4").trim().isEmpty()
                    || contentLength > 100
                    || opt1Length > 100
                    || opt2Length > 100
                    || opt3Length > 100
                    || opt4Length > 100) {
                question = new Question(content);
                request.setAttribute("question", question);
                if (!isSelectAnswer) {
                    request.setAttribute("msg", "Please choose the right answer!!!");
                } else if (content.isEmpty()) {
                    request.setAttribute("msg", "Please enter content!!!");
                } else if (request.getParameter("txtOption1").trim().isEmpty()) {
                    request.setAttribute("msg", "Please enter option1!!!");
                } else if (request.getParameter("txtOption2").trim().isEmpty()) {
                    request.setAttribute("msg", "Please enter option2!!!");
                } else if (request.getParameter("txtOption3").trim().isEmpty()) {
                    request.setAttribute("msg", "Please enter option3!!!");
                } else if (request.getParameter("txtOption4").trim().isEmpty()) {
                    request.setAttribute("msg", "Please enter option4!!!");
                } else if (contentLength > 100) {
                    request.setAttribute("msg", "Content must be less than 100 characters");
                } else if (opt1Length > 100) {
                    request.setAttribute("msg", "Option must be less than 100 characters");
                } else if (opt2Length > 100) {
                    request.setAttribute("msg", "Option must be less than 100 characters");
                } else if (opt3Length > 100) {
                    request.setAttribute("msg", "Option must be less than 100 characters");
                } else if (opt4Length > 100) {
                    request.setAttribute("msg", "Option musts be less than 100 characters");
                }
                request.getRequestDispatcher("MakeQuiz.jsp").forward(request, response);
                return;
            }

            //get account from session
            Account account = (Account) request.getSession(false).getAttribute("account");

            //insert question to question table and correct answer to answer table
            questionDAO.inserQuestion(new Question(content, account.getUserName()), new Answer(optionList) ,answerList);
            request.setAttribute("success", "Inserted successfully");

            request.getRequestDispatcher("MakeQuiz.jsp").forward(request, response);
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
