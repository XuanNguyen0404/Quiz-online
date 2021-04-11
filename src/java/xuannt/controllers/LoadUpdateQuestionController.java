/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xuannt.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import xuannt.dtos.QuestionDTO;

/**
 *
 * @author tienx
 */
public class LoadUpdateQuestionController extends HttpServlet {

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
            HttpSession session = request.getSession();
            String questionID = request.getParameter("questionID");
            List<QuestionDTO> list = (List<QuestionDTO>) session.getAttribute("LISTQUESTION");
            for (QuestionDTO questionDTO : list) {
                if(questionDTO.getQuestionID().equals(questionID)) {
                    request.setAttribute("QUESTIONID", questionID);
                    request.setAttribute("QUESTIONCONTENT", questionDTO.getQuestionContent());
                    request.setAttribute("ANSWER1", questionDTO.getAnswerContent().get(0));
                    request.setAttribute("ANSWER2", questionDTO.getAnswerContent().get(1));
                    request.setAttribute("ANSWER3", questionDTO.getAnswerContent().get(2));
                    request.setAttribute("ANSWER4", questionDTO.getAnswerContent().get(3));
                    request.setAttribute("ANSWERCORRECT", questionDTO.getAnswerCorrect());
                    request.setAttribute("CREATEDATE", questionDTO.getCreateDate());
                    request.setAttribute("SUBJECTID", questionDTO.getSubjectID());
                    request.setAttribute("STATUS", questionDTO.isStatus());
                    break;
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            request.getRequestDispatcher("updateQuestion.jsp").forward(request, response);
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
