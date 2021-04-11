/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xuannt.controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import xuannt.dtos.QuestionDTO;
import xuannt.models.DAO;

/**
 *
 * @author tienx
 */
public class CreateQuestionController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private static final String SUCCESS = "SearchQuestionController";
    private static final String INVALID = "createQuestion.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = SUCCESS;
        String questionID = null;
        String questionContent = null;
        String answer1 = null;
        String answer2 = null;
        String answer3 = null;
        String answer4 = null;
        String answerCorrect = null;
        String subjectID = null;
        String status = null;
                
        try {
            questionID = request.getParameter("questionID");
            questionContent = request.getParameter("questionContent");
            answer1 = request.getParameter("answer1");
            answer2 = request.getParameter("answer2");
            answer3 = request.getParameter("answer3");
            answer4 = request.getParameter("answer4");
            List<String> answerContent = new ArrayList<>();
            answerContent.add(answer1);
            answerContent.add(answer2);
            answerContent.add(answer3);
            answerContent.add(answer4);
            answerCorrect = request.getParameter("answerCorrect");            
            subjectID = request.getParameter("subjectID");
            status = request.getParameter("status");
            boolean statusBoolean = Boolean.parseBoolean(status);
            DAO dao = new DAO();
            
            String answerCorrectFull;
            if(answerCorrect.equals("1")){
                answerCorrectFull = answer1;
            }else if(answerCorrect.equals("2")){
                answerCorrectFull = answer2;
            }else if(answerCorrect.equals("3")){
                answerCorrectFull = answer3;
            }else {
                answerCorrectFull = answer4;
            }
            
            QuestionDTO dto = new QuestionDTO(questionID, questionContent, answerContent, answerCorrectFull, LocalDateTime.now(), subjectID, statusBoolean);
            dao.createQuestion(dto);
            
            
        } catch (Exception e) {
            if(e.getMessage().contains("duplicate")){
                request.setAttribute("QUESTIONID", questionID);
                request.setAttribute("QUESTIONCONTENT", questionContent);
                request.setAttribute("ANSWER1", answer1);
                request.setAttribute("ANSWER2", answer2);
                request.setAttribute("ANSWER3", answer3);
                request.setAttribute("ANSWER4", answer4);
                request.setAttribute("ANSWERCORRECT", answerCorrect);
                request.setAttribute("SUBJECTID", subjectID);
                request.setAttribute("STATUS", status);
                request.setAttribute("INVALID", "ID duplicate");
                url = INVALID;
            }else{
                e.printStackTrace();
            }
        }finally{
            request.getRequestDispatcher(url).forward(request, response);
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
