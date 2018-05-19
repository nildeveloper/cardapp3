package com.edu.model;

public class User {

    private int id;

    private String username;

    private String password;

    int authrity; // 1 管理员 2 普通用户

    public User() {

    }

    public User(String username, String password, int authrity) {
        this.username = username;
        this.password = password;
        this.authrity = authrity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getAuthrity() {
        return authrity;
    }

    public void setAuthrity(int authrity) {
        this.authrity = authrity;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authrity=" + authrity +
                '}';
    }

}

