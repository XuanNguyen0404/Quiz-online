/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xuannt.controllers;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import xuannt.dtos.UserDTO;
import xuannt.models.DAO;

/**
 *
 * @author tienx
 */
public class LoginController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String INVALID = "login.jsp";
    private static final String SUCCESS = "LoadSubjectController";
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = INVALID;
        try {
            HttpSession session = request.getSession();
            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");
            DAO dao = new DAO();
            
            // ma hoa SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder(2 * encodedhash.length);
            for (int i = 0; i < encodedhash.length; i++) {
                String hex = Integer.toHexString(0xff & encodedhash[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            String passwordEncrypted = hexString.toString();
            
            UserDTO dto = dao.checkLogin(email, passwordEncrypted);
            if(dto!=null){
                if(!dto.getStatus().equals("deleted")){
                    
                    if(dto.getRole().equals("admin")){
                        session.setAttribute("ROLE", dto.getRole());
                        session.setAttribute("USERNAME", dto.getUsername());
                        session.setAttribute("USEREMAIL", dto.getEmail());
                        url = SUCCESS;
                    }else if(dto.getRole().equals("student")){
                        session.setAttribute("ROLE", dto.getRole());
                        session.setAttribute("USERNAME", dto.getUsername());
                        session.setAttribute("USEREMAIL", dto.getEmail());
                        url = SUCCESS;
                    }else{
                        request.setAttribute("INVALID", "Your role is invalid");
                    }
                }else{
                    request.setAttribute("INVALID", "Your account is deleted");
                }
     
            }else{
                request.setAttribute("INVALID", "Wrong Email or password");
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
