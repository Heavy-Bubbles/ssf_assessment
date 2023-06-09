package vttp2023.batch3.ssf.frontcontroller.models;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class User implements Serializable{

    @NotBlank(message = "You must enter a username!")
    @Size(min = 2, message = "Username must be at least 2 characters!")
    private String username;

    @NotBlank(message = "You must enter a password!")
    @Size(min = 2, message = "Password must be at least 2 characters!")
    private String password;

    private boolean authenticated = false;
    private int errorCount;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isAuthenticated() {
        return authenticated;
    }
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.authenticated = false;
        this.errorCount = 0;
    }
    public User() {
        this.authenticated = false;
        this.errorCount = 0;
    }
    public int getErrorCount() {
        return errorCount;
    }
    public void setErrorCount(int errorCount) {
        this.errorCount = errorCount;
    }

    
}
