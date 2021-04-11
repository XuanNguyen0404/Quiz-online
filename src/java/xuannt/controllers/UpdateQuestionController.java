/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xuannt.controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
public class UpdateQuestionController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String questionID = request.getParameter("questionID");
            String questionContent = request.getParameter("questionContent");
            String answer1 = request.getParameter("answer1");
            String answer2 = request.getParameter("answer2");
            String answer3 = request.getParameter("answer3");
            String answer4 = request.getParameter("answer4");
            List<String> answerContent = new ArrayList<>();
            answerContent.add(answer1);
            answerContent.add(answer2);
            answerContent.add(answer3);
            answerContent.add(answer4);
            String answerCorrect = request.getParameter("answerCorrect");
            String createDate = request.getParameter("createDate").replace("T", " ");            
            String subjectID = request.getParameter("subjectID");
            String status = request.getParameter("status");
            boolean statusBoolean = Boolean.parseBoolean(status);
            DAO dao = new DAO();
            
            String answerCorrectFull ;
            if(answerCorrect.equals("1")){
                answerCorrectFull = answer1;
            }else if(answerCorrect.equals("2")){
                answerCorrectFull = answer2;
            }else if(answerCorrect.equals("3")){
                answerCorrectFull = answer3;
            }else {
                answerCorrectFull = answer4;
            }
            
            QuestionDTO dto = new QuestionDTO(questionID, questionContent,answerContent, answerCorrectFull, LocalDateTime.parse(createDate, formatter), subjectID, statusBoolean);
            dao.updateQuestion(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            request.getRequestDispatcher("SearchQuestionController").forward(request, response);
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
