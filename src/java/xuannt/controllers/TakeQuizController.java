/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xuannt.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import xuannt.dtos.QuestionDTO;
import xuannt.dtos.SubjectDTO;
import xuannt.models.DAO;

/**
 *
 * @author tienx
 */
public class TakeQuizController extends HttpServlet {

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
            String subjectID = request.getParameter("subjectID");
            List<SubjectDTO> listSubject = (List<SubjectDTO>) session.getAttribute("LISTSUBJECT");
            String userEmail = (String) session.getAttribute("USEREMAIL");
            SubjectDTO subject = null;
            for (SubjectDTO subjectDTO : listSubject) {
                if(subjectDTO.getSubjectID().equals(subjectID)){
                    subject = subjectDTO;
                    break;
                }
            }
            DAO dao = new DAO();
            
            List<QuestionDTO> quiz = (List<QuestionDTO>) session.getAttribute("QUIZ");
            if(quiz == null) {
                quiz = dao.takeQuiz(subject);
                for (QuestionDTO questionDTO : quiz) {
                    questionDTO.setAnswerContent(dao.getAnswerContent(questionDTO.getQuestionID()));
                }
                
                if(subject != null) {
                if (quiz.size() == subject.getNumQuestion()) {
                    int quizID ;
                                                          
                    String dateString = (String) session.getAttribute("TIMEOUT");
                    if(dateString==null){
                        Calendar date = Calendar.getInstance();
                        
                        long timeInSecs = date.getTimeInMillis();
                        Date afterAddingMins = new Date(timeInSecs + (subject.getTime() * 60 * 1000));
                        dateString = new SimpleDateFormat("MMM, dd yyyy HH:mm:ss").format(afterAddingMins);
                        quizID = dao.createHistory(subject, quiz, userEmail);
                    }else {
                        quizID = (int) session.getAttribute("QUIZID");
                    }
                    
                    session.setAttribute("QUESTIONINQUIZ", quiz.get(0));
                    session.setAttribute("INDEXQUIZ", 1);
                    session.setAttribute("INDEXISMAX", false);
                    session.setAttribute("TIMEOUT", dateString);
                    session.setAttribute("QUIZ", quiz);
                    session.setAttribute("QUIZID", quizID);
                    url = SUCCESS;
                    
                } else {
                    request.setAttribute("INVALID", "Not enough question in bank question");
                }
            }
                
            }else {
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
