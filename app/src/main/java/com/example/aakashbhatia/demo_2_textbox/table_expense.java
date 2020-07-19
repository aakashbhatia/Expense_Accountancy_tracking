package com.example.aakashbhatia.demo_2_textbox;

public class table_expense {

    private String familyid = "abc";
    private int expense_id = 0;
    private int userid = 0;
    private String expense_category = "";
    private int expense_amount = 0;
    private String note = "";
    private String bill_image = "";
    private String date_time = "";


    public table_expense() {
    }

    public String getFamilyid() {
        return familyid;
    }

    public int getExpense_id() {
        return expense_id;
    }

    public int getUserid() {
        return userid;
    }

    public String getExpense_category() {
        return expense_category;
    }

    public int getExpense_amount() {
        return expense_amount;
    }

    public String getNote() {
        return note;
    }

    public String getBill_image() {
        return bill_image;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setFamilyid(String familyid) {
        this.familyid = familyid;
    }

    public void setExpense_id(int expense_id) {
        this.expense_id = expense_id;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setExpense_category(String expense_category) {
        this.expense_category = expense_category;
    }

    public void setExpense_amount(int expense_amount) {
        this.expense_amount = expense_amount;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setBill_image(String bill_image) {
        this.bill_image = bill_image;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }
}

