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
import xuannt.dtos.QuestionDTO;
import xuannt.models.DAO;

/**
 *
 * @author tienx
 */
public class ViewHistoryController extends HttpServlet {

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
            String historyIDString = request.getParameter("historyID"); 
            String numOfCorrectString = request.getParameter("numOfCorrect");
            String pointString = request.getParameter("point");
            int historyID;
            int numOfCorrect;
            float point;
            if(historyIDString==null){
                historyID = (int) request.getAttribute("historyID");
                numOfCorrect = (int) request.getAttribute("numOfCorrect");
                point = (float) request.getAttribute("point");
            }else{
                historyID = Integer.parseInt(historyIDString);
                numOfCorrect = Integer.parseInt(numOfCorrectString);
                point = Float.parseFloat(pointString);
            }
             
            DAO dao = new DAO();
            
            List<QuestionDTO> listHistoryDetail = dao.getHistoryDetail(historyID);
            for (QuestionDTO questionDTO : listHistoryDetail) {
                questionDTO.setAnswerContent(dao.getAnswerContent(questionDTO.getQuestionID()));
            }
            request.setAttribute("LISTHISTORYDETAIL", listHistoryDetail);
            request.setAttribute("NUMOFCORRECT", numOfCorrect);
            request.setAttribute("POINT", point);
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            request.getRequestDispatcher("historyDetail.jsp").forward(request, response);
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
