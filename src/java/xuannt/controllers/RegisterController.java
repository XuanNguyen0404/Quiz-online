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
import xuannt.models.DAO;

/**
 *
 * @author tienx
 */
public class RegisterController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private static final String INVALID = "register.jsp";
    private static final String SUCCESS = "login.jsp";
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = INVALID;
        String email = "";
        String username = "";
        boolean valid = true;
        try {
            email = request.getParameter("txtEmail");
            username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            String confirmPassword = request.getParameter("txtConfirm");
            DAO dao = new DAO();
            
            if(!password.equals(confirmPassword)){
                request.setAttribute("INVALID", "Password is not matched");
                valid = false;
            }
            
            if(valid){
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
                 
                if(dao.registerAccount(email, username, passwordEncrypted)){
                    url = SUCCESS;
                }else{
                    request.setAttribute("INVALID", "Register error");
                }
            }
            
            
        } catch (Exception e) {
            if(e.getMessage().contains("duplicate")){
                request.setAttribute("EMAIL", email);
                request.setAttribute("USERNAME", username);
                request.setAttribute("INVALID", "Email already exists");
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
