package com.cg.feedback.user.dto;;

public class LoginCredentials{

    private String id;

    private String password;

    private String role;

    public LoginCredentials() {
        super();
    }
    public LoginCredentials(String id, String password, String role) {
        this.id = id;
        this.password = password;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "LoginCredentials [password=" + password + ", role=" + role + ", id=" + id + "]";
    }


}