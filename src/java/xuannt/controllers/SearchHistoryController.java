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
import xuannt.dtos.HistoryDTO;
import xuannt.models.DAO;

/**
 *
 * @author tienx
 */
public class SearchHistoryController extends HttpServlet {

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
            final String conditionNull = "HistoryID is not null";
            HttpSession session = request.getSession();            
            String indexString = request.getParameter("index");
            int index = 1;
            if(indexString!=null){
                index = Integer.parseInt(indexString);
            }
            String searchSubject = "All";
            String userEmail = (String) session.getAttribute("USEREMAIL");
            DAO dao = new DAO();
            String action = request.getParameter("action");
            
            if(action!=null){
                if(action.equals("ChangePage")){
                    searchSubject = (String) session.getAttribute("SEARCHSUBJECT");
                }else if(action.equals("Search")){
                    searchSubject = request.getParameter("searchSubject");
                }
            }
            
            session.setAttribute("SEARCHSUBJECT", searchSubject);
            
            if(searchSubject==null || searchSubject.equalsIgnoreCase("All")) {
                searchSubject = conditionNull;
            }else {
                searchSubject = "SubjectID = " + " '"+searchSubject+"' ";
                
            }
            
            int numPage = dao.getNumberPageOfHistory(5, searchSubject, userEmail);
            List<HistoryDTO> listHistory = dao.searchHistory(5, index, searchSubject, userEmail);
            session.setAttribute("LISTHISTORY", listHistory);
            session.setAttribute("NUMPAGE", numPage);
            session.setAttribute("INDEXHISTORYPAGE", index);
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            request.getRequestDispatcher("history.jsp").forward(request, response);
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
