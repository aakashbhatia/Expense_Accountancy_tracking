package com.example.aakashbhatia.demo_2_textbox;
public class table_savings {
    private String familyid = "";
    private int saving_id = 0;
    private int userid = 0;
    private String saving_type = "";
    private int saving_amount = 0;
    private String startdate = "";
    private String enddate = "";
    private String description = "";

    public table_savings() {}

    public String getFamilyid() {return familyid;}
    public int getSaving_id() {return saving_id;}
    public int getUserid() {return userid;}
    public String getSaving_type() {return saving_type;}
    public int getSaving_amount() {return saving_amount;}
    public String getStartdate() {return startdate;}
    public String getEnddate() {return enddate;}
    public String getDescription() {return description;}

    public void setFamilyid(String familyid) {this.familyid = familyid;}
    public void setSaving_id(int saving_id) {this.saving_id = saving_id;}
    public void setUserid(int userid) {this.userid = userid;}
    public void setSaving_type(String saving_type) {this.saving_type = saving_type;}
    public void setSaving_amount(int saving_amount) {this.saving_amount = saving_amount;}
    public void setStartdate(String startdate) {this.startdate = startdate;}
    public void setEnddate(String enddate) {this.enddate = enddate;}
    public void setDescription(String description) {this.description = description;}
}