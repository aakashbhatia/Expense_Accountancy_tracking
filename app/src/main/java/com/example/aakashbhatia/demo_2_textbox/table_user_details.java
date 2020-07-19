package com.example.aakashbhatia.demo_2_textbox;

public class table_user_details {

    private String familyid = "";
    private int userid = 0;
    private int salary = 0;
    private int currentunusedsalary = 0;
    private String username = "";
    private int head = 0;
    private String password = "";
    private String email = "";

    public table_user_details() {
    }

    public void setFamilyid(String familyid) {
        this.familyid = familyid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setCurrentunusedsalary(int currentunusedsalary) {
        this.currentunusedsalary = currentunusedsalary;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setHead(int head) {
        this.head = head;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFamilyid() {
        return familyid;
    }

    public int getUserid() {
        return userid;
    }

    public int getSalary() {
        return salary;
    }

    public int getCurrentunusedsalary() {
        return currentunusedsalary;
    }

    public String getUsername() {
        return username;
    }

    public int getHead() {
        return head;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
