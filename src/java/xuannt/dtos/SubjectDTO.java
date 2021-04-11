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
public class SubjectDTO implements Serializable{
    private String subjectID;		
    private String subjectName;	
    private int numQuestion;		
    private int time;

    public SubjectDTO(String subjectID, String subjectName, int numQuestion, int time) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.numQuestion = numQuestion;
        this.time = time;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getNumQuestion() {
        return numQuestion;
    }

    public void setNumQuestion(int numQuestion) {
        this.numQuestion = numQuestion;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    
    
}
