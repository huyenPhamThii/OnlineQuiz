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

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This class that gets parameter answer, answerList from the HTTP request;
 * check answer of user and calculate point. Servlet will directly respond to
 * <code>Error.jsp</code> when any error occurs
 * <p>
 * Bugs: None
 *
 * @author Pham Thi Huyen
 */
public class ShowResultController extends HttpServlet {

    /**
     * This method that gets parameter answer, answerList from the HTTP request;
     * check answer of user and calculate point. Servlet will directly respond
     * to <code>Error.jsp</code> when any error occurs
     *
     * @param request stores attributes: point, pointPercent, error to send to
     * <code>TakeQuizPass.jsp</code>. It is a
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
            HttpSession httpSession = request.getSession();

            //get list of correct answer from session
            List<String> correctAnswerList = (List<String>) httpSession.getAttribute("correctAnswerList");

            //get user's answer
            String[] userAnswer_Raw;
            List<String> userAnswerList = new ArrayList<>();

            //get user's selected options in each question then add to user's answer list
            for (int i = 0; i <= correctAnswerList.size(); i++) {
                String userAnser = "";
                userAnswer_Raw = request.getParameterValues(i + "Answer");
                if (userAnswer_Raw != null) {
                    for (String string : userAnswer_Raw) {
                        userAnser += string;
                    }
                    userAnswerList.add(userAnser);
                }
            }

            //get number of correct answer
            int numOfCorrectAnswer = 0;
            if (!userAnswerList.isEmpty()) {
                for (int i = 0; i < correctAnswerList.size(); i++) {
                    if (correctAnswerList.get(i).equals(userAnswerList.get(i))) {
                        numOfCorrectAnswer++;
                    }
                }
            }

            //calculate the correct point
            DecimalFormat decimalFormat = new DecimalFormat("#.#");
            double correctPoint = numOfCorrectAnswer * 10.0 / correctAnswerList.size();
            String resultStatus = correctPoint >= 5 ? "Passed" : "Not Passed";

            request.setAttribute("percent", Math.round(correctPoint * 10) + "%");
            request.setAttribute("correctPoint", decimalFormat.format(correctPoint));
            request.setAttribute("resultStatus", resultStatus);
            request.getRequestDispatcher("ShowResult.jsp").forward(request, response);
        } catch (IOException | ServletException ex) {
            request.setAttribute("error", ex);
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
