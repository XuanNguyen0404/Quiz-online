/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xuannt.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author tienx
 */
public class QuestionDTO implements Serializable{
    private String questionID;
    private String questionContent;
    private List<String> answerContent;
    private String answerCorrect;
    private LocalDateTime createDate;
    private String subjectID;
    private boolean status;
    private String answer;
    private boolean result;  

    public QuestionDTO(String questionID, String questionContent, String answerCorrect, LocalDateTime createDate, String subjectID, boolean status) {
        this.questionID = questionID;
        this.questionContent = questionContent;
        this.answerCorrect = answerCorrect;
        this.createDate = createDate;
        this.subjectID = subjectID;
        this.status = status;
    }

    public QuestionDTO(String questionID, String questionContent, String answerCorrect, String answer, boolean result) {
        this.questionID = questionID;
        this.questionContent = questionContent;
        this.answerCorrect = answerCorrect;
        this.answer = answer;
        this.result = result;
    }

    public QuestionDTO(String questionID, String questionContent, List<String> answerContent, String answerCorrect, LocalDateTime createDate, String subjectID, boolean status) {
        this.questionID = questionID;
        this.questionContent = questionContent;
        this.answerContent = answerContent;
        this.answerCorrect = answerCorrect;
        this.createDate = createDate;
        this.subjectID = subjectID;
        this.status = status;
    }
 

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public List<String> getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(List<String> answerContent) {
        this.answerContent = answerContent;
    }

    public String getAnswerCorrect() {
        return answerCorrect;
    }

    public void setAnswerCorrect(String answerCorrect) {
        this.answerCorrect = answerCorrect;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    

    
    
    
    
    
}
