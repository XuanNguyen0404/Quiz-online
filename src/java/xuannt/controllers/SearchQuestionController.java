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
import xuannt.models.DAO;

/**
 *
 * @author tienx
 */
public class SearchQuestionController extends HttpServlet {

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
            final String conditionNull = "QuestionID is not null";
            HttpSession session = request.getSession();            
            String indexString = request.getParameter("index");
            int index = 1;
            if(indexString!=null){
                index = Integer.parseInt(indexString);
            }
            String searchName = "";           
            String searchStatus = "All";
            String searchSubject = "All";
            DAO dao = new DAO();
            String action = request.getParameter("action");
            
            if(action!=null){
                if(action.equals("ChangePage")){
                    searchName = (String) session.getAttribute("SEARCHNAME");
                    searchStatus = (String) session.getAttribute("SEARCHSTATUS");
                    searchSubject = (String) session.getAttribute("SEARCHSUBJECT");
                }else if(action.equals("Search")){
                    searchName = request.getParameter("searchName");
                    searchStatus = request.getParameter("searchStatus");
                    searchSubject = request.getParameter("searchSubject");
                }
            }           
            
            session.setAttribute("SEARCHNAME", searchName);
            session.setAttribute("SEARCHSTATUS", searchStatus);
            session.setAttribute("SEARCHSUBJECT", searchSubject);
            
            if(searchName==null || searchName.equals("")) {
                searchName = conditionNull;
            }else{
                searchName = "QuestionContent like "+ " '%"+searchName+"%' ";
            }
            
            if(searchStatus==null || searchStatus.equalsIgnoreCase("All")) {
                searchStatus = conditionNull;
            }else {
                if(searchStatus.equalsIgnoreCase("true")){
                    searchStatus = "Status = 1";
                }else {
                    searchStatus = "Status = 0";
                }
                
            }
            
            if(searchSubject==null || searchSubject.equalsIgnoreCase("All")) {
                searchSubject = conditionNull;
            }else {
                searchSubject = "SubjectID = " + " '"+searchSubject+"' ";
                
            }
            int numPage = dao.getNumberPageOfQuestions(5, searchName, searchStatus, searchSubject);
            List<QuestionDTO> listQuestion = dao.searchQuestion(5, index, searchName, searchStatus, searchSubject);
            for (QuestionDTO questionDTO : listQuestion) {
                questionDTO.setAnswerContent(dao.getAnswerContent(questionDTO.getQuestionID()));
            }
            session.setAttribute("LISTQUESTION", listQuestion);
            session.setAttribute("NUMPAGE", numPage);
            session.setAttribute("INDEXQUESTIONPAGE", index);
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            request.getRequestDispatcher("admin.jsp").forward(request, response);
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
