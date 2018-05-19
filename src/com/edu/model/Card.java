package com.edu.model;

public class Card {
    
    private int id;
    
    private String name;
    
    private String tel;
    
    private String address;
    
    private String email;
    
    private int userId; // 关联用户id  显示信息
    
    private int isDelete; // 0 表示未放入回收站 1 表示放入回收站
    
    public Card() {
        
    }

    public Card(int id, String name, String tel, String address, String email, int userId, int isDelete) {
        this.id = id;
        this.name = name;
        this.tel = tel;
        this.address = address;
        this.email = email;
        this.userId = userId;
        this.isDelete = isDelete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int idDelete) {
        this.isDelete = idDelete;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", name:'" + name + '\'' +
                ", tel:'" + tel + '\'' +
                ", address:'" + address + '\'' +
                ", email:'" + email + '\'' +
                ", userId:" + userId +
                ", idDelete:" + isDelete +
                '}';
    }
}
