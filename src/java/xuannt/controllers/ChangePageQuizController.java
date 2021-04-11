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
public class ChangePageQuizController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String INVALID = "index.jsp";
    private static final String SUCCESS = "quiz.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = INVALID;
        try {
            HttpSession session = request.getSession();
            List<QuestionDTO> quiz = (List<QuestionDTO>) session.getAttribute("QUIZ");
            if(quiz == null) {
                request.setAttribute("INVALID", "The quiz is ended");
            } else {                
                int index = (int) session.getAttribute("INDEXQUIZ");
                String action = request.getParameter("action");
                if (action.equals("previos")) {
                    index--;
                    if(index==0){
                        index=1;
                    }
                    
                } else if (action.equals("next")) {
                    index++;
                    if(index>quiz.size()){    
                        index=quiz.size();
                    }
                }
                
                if(index==quiz.size()){
                    session.setAttribute("INDEXISMAX", true);
                }else {
                    session.setAttribute("INDEXISMAX", false);
                }
                session.setAttribute("INDEXQUIZ", index);
                session.setAttribute("QUESTIONINQUIZ", quiz.get(index-1));
                url = SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
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
