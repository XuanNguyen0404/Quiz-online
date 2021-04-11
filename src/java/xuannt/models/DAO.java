/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xuannt.models;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import xuannt.dbs.MyConnection;
import xuannt.dtos.HistoryDTO;
import xuannt.dtos.QuestionDTO;
import xuannt.dtos.SubjectDTO;
import xuannt.dtos.UserDTO;

/**
 *
 * @author tienx
 */
public class DAO implements Serializable{
    private Connection con;
    private PreparedStatement preStm;
    private ResultSet rs;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    
    public DAO(){
        
    }
    
    public void closeConnection() throws Exception{
        if(rs!=null){
            rs.close();
        }if(preStm!=null){
            preStm.close();
        }if(con!=null){
            con.close();
        }
    }
    
    public UserDTO checkLogin (String email,  String password) throws Exception{
        UserDTO dto = null;
        try {
            String sql = "select Email, Username, Role, Status\n" +
                    "from tblUser " +
                    "where Email = ? and Password = ?";
            con = MyConnection.getMyConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, email);
            preStm.setString(2, password);
            rs = preStm.executeQuery();
            if(rs.next()){
                String username = rs.getString("Username");
                String role = rs.getString("Role");
                String status = rs.getString("Status");
                dto = new UserDTO(email, username, role, status);
            }
        }finally{
            closeConnection();
        }
        return dto;
    }
    
    public boolean registerAccount(String email, String username, String password) throws Exception{
        boolean result = false;
        try {
            String sql = "insert into tblUser (Email, Username, Password, Role, Status)\n"
                    + "values (?,?,?,?,?)";
            con = MyConnection.getMyConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, email);
            preStm.setString(2, username);
            preStm.setString(3, password);
            preStm.setString(4, "student");
            preStm.setString(5, "new");
            result = preStm.executeUpdate()>0;
        } finally{
            closeConnection();
        }
        return result;
    }
    
    public List<SubjectDTO> getSubject() throws Exception{
        List<SubjectDTO> list = null;
        try {
            String sql = "select SubjectID, SubjectName, NumQuestion, Time\n"
                    + "from tblSubject";
            con = MyConnection.getMyConnection();
            preStm = con.prepareStatement(sql);
            rs = preStm.executeQuery();
            list = new ArrayList<>();
            while(rs.next()){
                String subjectID = rs.getString("SubjectID");
                String subjectName = rs.getString("SubjectName");
                int numQuestion = rs.getInt("NumQuestion");
                int time = rs.getInt("Time");
                SubjectDTO dto = new SubjectDTO(subjectID, subjectName, numQuestion, time);
                list.add(dto);
            }
        } finally{
            closeConnection();
        }
        return list;
    }
    
    public int getNumberPageOfQuestions(int numItemInPage, String searchName ,String searchStatus, String searchSubjectID) throws Exception{
        try {
            String sql = "select count(QuestionID) as total from tblQuestion \n" +
                    "where "+searchName+" and "+searchStatus+" and "+searchSubjectID+" ";
            con = MyConnection.getMyConnection();
            preStm = con.prepareStatement(sql);
            rs = preStm.executeQuery();
            if(rs.next()){
                int total = rs.getInt("total");
                int countPage = total/numItemInPage;
                if(total % numItemInPage!=0){
                    countPage++;
                }
                return countPage;
            }
        } finally{
            closeConnection();
        }
        return 0;
    }
    
    public int getNumberPageOfHistory(int numItemInPage, String searchSubjectID, String userEmail) throws Exception{
        try {
            String sql = "select count(HistoryID) as total from tblHistory \n" +
                    "where "+searchSubjectID+" and UserEmail = ? ";
            con = MyConnection.getMyConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, userEmail);
            rs = preStm.executeQuery();
            if(rs.next()){
                int total = rs.getInt("total");
                int countPage = total/numItemInPage;
                if(total % numItemInPage!=0){
                    countPage++;
                }
                return countPage;
            }
        } finally{
            closeConnection();
        }
        return 0;
    }
    
    public List<String> getAnswerContent(String questionID) throws Exception{
        List<String> list = null;
        try {
            String sql = "select AnswerID, AnswerContent\n"
                    + "from tblAnswer\n"
                    + "where QuestionID = ?";
            list = new ArrayList<>();
            con = MyConnection.getMyConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, questionID);
            rs = preStm.executeQuery();
            while(rs.next()){
                String answerContent = rs.getString("AnswerContent");                
                list.add(answerContent);
            }
        } finally{
            closeConnection();
        }
        return list;
    }
    
    public List<QuestionDTO> searchQuestion (int numItemInPage, int index , String searchName ,String searchStatus, String searchSubjectID) throws Exception{
        List<QuestionDTO> list = null;
        try {            
            String sql = "select QuestionID, QuestionContent, AnswerCorrect, CreateDate, SubjectID, Status\n" +
                "from tblQuestion\n" +
                "where "+searchName+" and "+searchStatus+" and "+searchSubjectID+ " "+
                "order by CreateDate DESC\n" +
                "	offset ? rows\n" +
                "	fetch next ? rows only";            
            list = new ArrayList<>();
            con = MyConnection.getMyConnection();

            preStm = con.prepareStatement(sql);
            preStm.setInt(1, (index - 1) * numItemInPage);
            preStm.setInt(2, numItemInPage);
            rs = preStm.executeQuery();
            while (rs.next()) {
                String questionID = rs.getString("QuestionID");
                String questionContent = rs.getString("QuestionContent");
                String answerCorrect = rs.getString("AnswerCorrect");
                Timestamp timestamp = rs.getTimestamp("CreateDate");
                LocalDateTime createDate = timestamp.toLocalDateTime();
                String subjectID = rs.getString("SubjectID");
                boolean status = rs.getBoolean("Status");               
                QuestionDTO dto = new QuestionDTO(questionID, questionContent, answerCorrect, createDate, subjectID, status);
                list.add(dto);
            }
            
        } finally{
            closeConnection();
        }
        return list;
    }
    
    public List<HistoryDTO> searchHistory (int numItemInPage, int index , String searchSubjectID, String userEmail) throws Exception{
        List<HistoryDTO> list = null;
        try {            
            String sql = "select HistoryID, SubjectID, NumOfCorrect, Point\n" +
                "from tblHistory\n" +
                "where "+searchSubjectID+" and UserEmail = ? "+
                "order by HistoryID DESC\n" +
                "	offset ? rows\n" +
                "	fetch next ? rows only";
            list = new ArrayList<>();
            con = MyConnection.getMyConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, userEmail);
            preStm.setInt(2, (index-1)*numItemInPage);
            preStm.setInt(3, numItemInPage);
            rs = preStm.executeQuery();
            while(rs.next()){
                int historyID = rs.getInt("HistoryID");
                String subjectID = rs.getString("SubjectID");
                int numOfCorrect = rs.getInt("NumOfCorrect");
                float point = rs.getFloat("Point");
                HistoryDTO dto = new HistoryDTO(historyID, userEmail, subjectID, numOfCorrect, point);               
                list.add(dto);
            }
        } finally{
            closeConnection();
        }
        return list;
    }
    
    public void createQuestion (QuestionDTO dto) throws Exception{
        try {
            String sqlQuestion = "insert into tblQuestion (QuestionID,QuestionContent,AnswerCorrect,CreateDate,SubjectID,Status)\n"
                    + "values (?,?,?,?,?,?)";
            String sqlAnswer = "insert into tblAnswer (QuestionID, AnswerContent)\n"
                    + "output inserted.AnswerID\n"
                    + "values (?,?)";
            con = MyConnection.getMyConnection();
            con.setAutoCommit(false);
            
            preStm = con.prepareStatement(sqlQuestion);
            preStm.setString(1, dto.getQuestionID());
            preStm.setString(2, dto.getQuestionContent());
            preStm.setString(3, dto.getAnswerCorrect());
            preStm.setString(4, dto.getCreateDate().format(formatter));
            preStm.setString(5, dto.getSubjectID());
            preStm.setBoolean(6, dto.isStatus());
            preStm.executeUpdate();

            for (String answer : dto.getAnswerContent()) {
                preStm = con.prepareStatement(sqlAnswer);
                preStm.setString(1, dto.getQuestionID());
                preStm.setString(2, answer);
                preStm.executeQuery();
            }

            con.commit();      
            
        } finally{
            closeConnection();
        }
    }
    
    public void updateQuestion(QuestionDTO dto) throws Exception{
        try {
            String sqlQuestion = "update tblQuestion\n"
                    + "set QuestionContent = ?,\n"                    
                    + "    AnswerCorrect = ?,\n"
                    + "    CreateDate = ?,\n"
                    + "    SubjectID = ?,\n"
                    + "    Status = ?\n"
                    + "where QuestionID = ?";
            String sqlAnswerID = "select AnswerID from tblAnswer where QuestionID = ?";
            String sqlAnswer = "update tblAnswer\n"
                    + "set AnswerContent = ?\n"
                    + "where AnswerID = ?";
            con = MyConnection.getMyConnection();
            con.setAutoCommit(false);
            try {
                preStm = con.prepareStatement(sqlQuestion);
                preStm.setString(1, dto.getQuestionContent());
                preStm.setString(2, dto.getAnswerCorrect());
                preStm.setString(3, dto.getCreateDate().format(formatter));
                preStm.setString(4, dto.getSubjectID());
                preStm.setBoolean(5, dto.isStatus());
                preStm.setString(6, dto.getQuestionID());
                preStm.executeUpdate();
                
                List<Integer> listAnswerID = new ArrayList<>();
                preStm = con.prepareStatement(sqlAnswerID);                    
                preStm.setString(1, dto.getQuestionID());
                rs = preStm.executeQuery();
                while(rs.next()){
                    int id = rs.getInt("AnswerID");
                    listAnswerID.add(id);
                }
                
                for (int i=0; i<4; i++) {
                    preStm = con.prepareStatement(sqlAnswer);                    
                    preStm.setString(1, dto.getAnswerContent().get(i));
                    preStm.setInt(2, listAnswerID.get(i));
                    preStm.executeUpdate();
                }
                con.commit();
            } catch (Exception e) {
                e.printStackTrace();
                con.rollback();
            }
            
            
        } finally{
            closeConnection();
        }
    }
    
    public void deleteQuestion (String questionID) throws Exception{
        try {
            String sql = "update tblQuestion\n"
                    + "set Status = 0\n"
                    + "where QuestionID = ?";
            con = MyConnection.getMyConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, questionID);
            preStm.executeUpdate();
        } finally{
            closeConnection();
        }
    }
    
    public List<QuestionDTO> takeQuiz(SubjectDTO subject) throws Exception{
        List<QuestionDTO> list = null;
        try {
            String sqlQuiz = "select top "+ subject.getNumQuestion() +"  QuestionID, QuestionContent,AnswerCorrect\n" +
                        "from tblQuestion\n" +
                        "where SubjectID = ? and Status=1\n" +
                        "order by NEWID()";
            
            list = new ArrayList<>();
            con = MyConnection.getMyConnection();
            preStm = con.prepareStatement(sqlQuiz);            
            preStm.setString(1, subject.getSubjectID());
            rs = preStm.executeQuery();
            while (rs.next()) {
                String questionID = rs.getString("QuestionID");
                String questionContent = rs.getString("QuestionContent");                
                String answerCorrect = rs.getString("AnswerCorrect");                                               
                QuestionDTO dto = new QuestionDTO(questionID, questionContent, answerCorrect, null, false);
                list.add(dto);
            }

        } finally{
            closeConnection();
        }
        return list;
    }
    
    public int createHistory (SubjectDTO subject, List<QuestionDTO> quiz, String userEmail ) throws Exception{
        int quizID = 0;
        try {
            String sqlHistory = "insert into tblHistory (UserEmail,SubjectID,NumOfCorrect,Point)\n" +
                            "output inserted.HistoryID\n" +
                            "values (?,?,0,0)";
            String sqlHistoryDetail = "insert into tblHistoryDetail (HistoryID,QuestionID,Answer,Result)\n"
                    + "output inserted.HistoryDetailID\n"
                    + "values (?,?,?,?)";
            String sqlHistoryID = "select MAX(HistoryID) as max from tblHistory ";
            con = MyConnection.getMyConnection();
            con.setAutoCommit(false);
            try {
                preStm = con.prepareStatement(sqlHistory);
                preStm.setString(1, userEmail);
                preStm.setString(2, subject.getSubjectID());                
                preStm.executeQuery();
                
                preStm = con.prepareStatement(sqlHistoryID);
                rs = preStm.executeQuery();
                if(rs.next()) quizID = rs.getInt("max");
                
                
                for(QuestionDTO questionDTO : quiz){
                    preStm = con.prepareStatement(sqlHistoryDetail);
                    preStm.setInt(1, quizID);
                    preStm.setString(2, questionDTO.getQuestionID());
                    preStm.setNull(3, 0);
                    preStm.setBoolean(4, false);
                    preStm.executeQuery();
                }
                
                con.commit();
            } catch (Exception e) {
                e.printStackTrace();
                con.rollback();
            }           
            
        } finally{
            closeConnection();
        }
        return quizID;
    }
    
    public void finishQuiz(List<QuestionDTO> quiz, int quizID, int numCorrect, float point) throws Exception{
        try {
            String sqlHistory = "update tblHistory\n"
                    + "set NumOfCorrect = ?,\n"
                    + "    Point = ?\n"
                    + "where HistoryID = ?";
            String sqlHistoryDetail = "update tblHistoryDetail\n"
                    + "set  Answer = ?,\n"
                    + "     Result = ?\n"
                    + "where HistoryID = ? and QuestionID = ?";
            con = MyConnection.getMyConnection();
            con.setAutoCommit(false);
            
            try {
                preStm = con.prepareStatement(sqlHistory);
                preStm.setInt(1, numCorrect);
                preStm.setFloat(2, point);
                preStm.setInt(3, quizID);
                preStm.executeUpdate();
                
                
                for (QuestionDTO answer : quiz) {
                    preStm = con.prepareStatement(sqlHistoryDetail);
                    
                    preStm.setString(1, answer.getAnswer());
                    
                    preStm.setBoolean(2, answer.isResult());
                    preStm.setInt(3, quizID);
                    preStm.setString(4, answer.getQuestionID());
                    preStm.executeUpdate();
                }
                
                
                con.commit();
            } catch (Exception e) {
                e.printStackTrace();
                con.rollback();
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            closeConnection();
        }
    }
    
    public List<QuestionDTO> getHistoryDetail (int historyID) throws Exception{
        List<QuestionDTO> list = null;
        try {
            String sql = "select tblQuestion.QuestionID, QuestionContent,AnswerCorrect, Answer, Result\n" +
                "from tblHistoryDetail inner join tblQuestion on tblHistoryDetail.QuestionID= tblQuestion.QuestionID\n" +
                "where HistoryID = ?";
            list = new ArrayList<>();
            con = MyConnection.getMyConnection();
            preStm = con.prepareStatement(sql);
            preStm.setInt(1, historyID);
            rs = preStm.executeQuery();
            while(rs.next()){               
                String questionID = rs.getString("QuestionID");
                String questionContent = rs.getString("QuestionContent");
                String answerCorrect = rs.getString("AnswerCorrect");
                String answer = rs.getString("Answer");
                boolean result = rs.getBoolean("Result");
                QuestionDTO dto = new QuestionDTO( questionID, questionContent, answerCorrect, answer, result);
                list.add(dto);
            }
            
        } finally{
            closeConnection();
        }
        return list;
    }
    
}
