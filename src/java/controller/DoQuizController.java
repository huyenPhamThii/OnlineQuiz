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

import context.DBContext;
import dao.AnswerDAO;
import dao.QuestionDAO;
import dao.impl.AnswerDAOImpl;
import dao.impl.QuestionDAOImpl;
import entity.Answer;
import entity.Question;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This class contains a method that get time per quiz in context; get parameter
 * numOfQuestion from the HTTP request; calculate time; calls
 * <code>QuestionDAOImpl</code> to get 'n' <code>Question</code> then forward to
 * <code>TakeQuiz.jsp</code>. Servlet will directly respond to
 * <code>Error.jsp</code> when any error occurs
 * <p>
 * Bugs: None
 *
 * @author Pham Thi Huyen
 */
public class DoQuizController extends HttpServlet {

    /**
     * This method that get time per quiz in context; get parameter
     * numOfQuestion from the HTTP request; calculate time; call
     * <code>QuestionDAOImpl</code> to get 'n' <code>Question</code> then
     * forward to <code>TakeQuiz.jsp</code>. Servlet will directly respond to
     * <code>Error.jsp</code> when any error occurs
     *
     * @param request stores attributes: answerList,numOfQuestion, min, sec,
     * lstQuestions, error to send to <code>TakeQuizAnswer.jsp</code>. It is a
     * <code> javax.servlet.http.HttpServletRequest</code>
     * @param response sending a response to client's web browser. It is a
     * <code> javax.servlet.http.HttpServletResponse</code>
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            DBContext dBContext = new DBContext();
            QuestionDAO questionDAO = new QuestionDAOImpl();
            AnswerDAO answerDAO = new AnswerDAOImpl();
            HttpSession httpSession = request.getSession();

            //get time per question
            int timePerQuestion = Integer.parseInt(dBContext.getTimePerQuestion());
            
            //get number of question that user has choose from takeQuizController
            int numOfQuestion = (int) request.getAttribute("numberOfQuestion");
            System.out.println("num of ques: " + numOfQuestion);
            
            //calculate the total time
            int totalTime = numOfQuestion * timePerQuestion;

            //get list of random question in quiz from qustion table in the database
            ArrayList<Question> randomQuestionList = questionDAO.getRandomQuestion(numOfQuestion);
            
//            ArrayList<Answer> listOpt = new ArrayList<>();

            //get list of correct answer for list of random question from answer in the database
            List<String> correctAnswerList = new ArrayList<>();
            for (Question question : randomQuestionList) {
                correctAnswerList.add(answerDAO.getCorrectAnsByQuesID(question.getId()));
//                listOpt.add(answerDAO.getOptListByQuesID(question.getId()));
            }
          

            request.setAttribute("totalTime", totalTime);
            request.setAttribute("randomQuestionList", randomQuestionList);
            httpSession.setAttribute("correctAnswerList", correctAnswerList);
            

            request.getRequestDispatcher("DoQuiz.jsp").forward(request, response);

        } catch (Exception ex) {
            request.setAttribute("error", "DoQuizController: " +ex);
            request.getRequestDispatcher("Error.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
