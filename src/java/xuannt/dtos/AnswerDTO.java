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
public class AnswerDTO implements Serializable{
    private int id;
    private String answerContent;

    public AnswerDTO(int id, String answerContent) {
        this.id = id;
        this.answerContent = answerContent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }
    
    
}
