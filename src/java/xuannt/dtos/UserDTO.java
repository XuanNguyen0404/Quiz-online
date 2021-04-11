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
public class UserDTO implements Serializable{
    private String email;
    private String username ;
    private String role;
    private String status ;

    public UserDTO(String email, String username, String role, String status) {
        this.email = email;
        this.username = username;
        this.role = role;
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
}
