/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eplant.config;

/**
 *
 * @author HP
 */
public final class UserSession {

    private static UserSession instance;

    private String userName;
    private String role;

    private UserSession(String userName, String role) {
        this.userName = userName;
        this.role = role;
    }

    public static UserSession getInstace(String userName, String role) {
        if(instance == null) {
            instance = new UserSession(userName, role);
        }
        return instance;
    }

    public String getUserName() {
        return userName;
    }

    public String getRole() {
        return role;
    }

    public void cleanUserSession() {
        userName = "";// or null
        role = "";// or null
        
        instance =null;
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "userName='" + userName + '\'' +
                ", role=" + role +
                '}';
    }
}
