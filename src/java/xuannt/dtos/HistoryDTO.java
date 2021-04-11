/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xuannt.dtos;

import java.io.Serializable;

/**
 *
 * @author tienx
 */
public class HistoryDTO implements Serializable{
    private int historyID;		
    private String userEmail;
    private String subjectID;
    private int numOfCorrect;
    private float point;

    public HistoryDTO(int historyID, String userEmail, String subjectID, int numOfCorrect, float point) {
        this.historyID = historyID;
        this.userEmail = userEmail;
        this.subjectID = subjectID;
        this.numOfCorrect = numOfCorrect;
        this.point = point;
    }
    
    

    public int getHistoryID() {
        return historyID;
    }

    public void setHistoryID(int historyID) {
        this.historyID = historyID;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public int getNumOfCorrect() {
        return numOfCorrect;
    }

    public void setNumOfCorrect(int numOfCorrect) {
        this.numOfCorrect = numOfCorrect;
    }

    public float getPoint() {
        return point;
    }

    public void setPoint(float point) {
        this.point = point;
    }
    
    
}
