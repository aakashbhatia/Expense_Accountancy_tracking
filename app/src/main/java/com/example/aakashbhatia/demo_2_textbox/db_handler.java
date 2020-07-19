package com.example.aakashbhatia.demo_2_textbox;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

public class db_handler extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "eat.db";

    public static final String TABLE_USERDEATILS = "user_details";
    public static final String COLUMN_FAMILYID = "familyid";
    public static final String COLUMN_USERID = "userid";
    public static final String COLUMN_SALARY = "salary";
    public static final String COLUMN_UNUSEDSALARY = "currentunusedsalary";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_HEAD = "head";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_EMAIL = "email";

    public static final String TABLE_EXPENSE = "expense";
    public static final String COLUMN_FAMILYID_EXPENSE = "familyid";
    public static final String COLUMN_EXPENSEID = "expenseid";
    public static final String COLUMN_USERID_EXPENSE = "userid";
    public static final String COLUMN_EXPENSECATEGORY = "expensecategory";
    public static final String COLUMN_EXPENSEAMOUNT = "expenseamount";
    public static final String COLUMN_NOTE = "note";
    public static final String COLUMN_BILLIMAGE = "billimage";
    public static final String COLUMN_DATETIME = "datetime";

    public static final String TABLE_SAVINGS = "savings";
    public static final String COLUMN_FAMILYID_SAVINGS = "familyid";
    public static final String COLUMN_SAVINGID = "savingid";
    public static final String COLUMN_USERID_SAVINGS = "userid";
    public static final String COLUMN_SAVINGTYPE = "savingtype";
    public static final String COLUMN_SAVINGAMOUNT = "savingamount";
    public static final String COLUMN_STARTDATE = "startdate";
    public static final String COLUMN_ENDDATE = "enddate";
    public static final String COLUMN_SAVINGDESCRIPTION = "savingdescription";

    public static final String MY_PREFS_NAME = "MyPrefsFile";
    public Context con;
    /*public boolean first=false;*/

    public db_handler(Context c, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(c, DATABASE_NAME, factory, DATABASE_VERSION);
        con = c;
        /*SharedPreferences prefs = context.getSharedPreferences("MyPrefsFile",MODE_PRIVATE);
        first = prefs.getBoolean("first",true);
        prefs.edit().putBoolean("first",false).commit();*/
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_USERDEATILS + "( " +
                COLUMN_USERID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FAMILYID + " TEXT, " +
                COLUMN_SALARY + " INTEGER, " +
                COLUMN_UNUSEDSALARY + " INTEGER, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_HEAD + " INTEGER, " +
                COLUMN_PASSWORD + " TEXT, " +
                COLUMN_EMAIL + " TEXT );";
        db.execSQL(query);

        String query_expense="CREATE TABLE " + TABLE_EXPENSE + "( " +
                COLUMN_USERID_EXPENSE + " INTEGER, " +
                COLUMN_FAMILYID_EXPENSE + " TEXT, " +
                COLUMN_EXPENSEID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_EXPENSECATEGORY + " TEXT, " +
                COLUMN_EXPENSEAMOUNT + " INTEGER, " +
                COLUMN_NOTE + " TEXT, " +
                COLUMN_BILLIMAGE + " TEXT, " +
                COLUMN_DATETIME + " TEXT);";
        db.execSQL(query_expense);

        String query_saving="CREATE TABLE " + TABLE_SAVINGS + "( " +
                COLUMN_USERID_SAVINGS + " INTEGER, " +
                COLUMN_FAMILYID_SAVINGS + " TEXT, " +
                COLUMN_SAVINGID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_SAVINGTYPE + " TEXT, " +
                COLUMN_SAVINGAMOUNT + " INTEGER, " +
                COLUMN_STARTDATE + " TEXT, " +
                COLUMN_ENDDATE + " TEXT, " +
                COLUMN_SAVINGDESCRIPTION + " TEXT);";
        db.execSQL(query_saving);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERDEATILS);
        db.execSQL("DROP TABLE IF EXITS" + TABLE_EXPENSE);
        db.execSQL("DROP TABLE IF EXITS" + TABLE_SAVINGS);
        onCreate(db);
    }
    public void addexpense(table_expense expense)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_FAMILYID_EXPENSE, expense.getFamilyid());
        values.put(COLUMN_USERID_EXPENSE, expense.getUserid());
        values.put(COLUMN_EXPENSECATEGORY, expense.getExpense_category());
        values.put(COLUMN_EXPENSEAMOUNT, expense.getExpense_amount());
        values.put(COLUMN_NOTE, expense.getNote());
        values.put(COLUMN_BILLIMAGE, expense.getBill_image());
        values.put(COLUMN_DATETIME, expense.getDate_time());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_EXPENSE, null, values);
        SharedPreferences prefs = con.getSharedPreferences(MY_PREFS_NAME,MODE_PRIVATE);
        String query_add_expense = "UPDATE " + TABLE_USERDEATILS + " SET " +
                COLUMN_UNUSEDSALARY + " = " + prefs.getInt("unused", 0) +
                " WHERE " + COLUMN_USERID + " = " + prefs.getInt("userid", -1);
        db.execSQL(query_add_expense);
        db.close();
    }

    public void adduser(table_user_details user){
        ContentValues values = new ContentValues();
        values.put(COLUMN_FAMILYID, user.getFamilyid());
        values.put(COLUMN_SALARY, user.getSalary());
        values.put(COLUMN_UNUSEDSALARY, user.getCurrentunusedsalary());
        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_HEAD, user.getHead());
        values.put(COLUMN_PASSWORD, user.getPassword());
        values.put(COLUMN_EMAIL, user.getEmail());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_USERDEATILS, null, values);
        db.close();
    }
    public void addsavings(table_savings savings)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_FAMILYID, savings.getFamilyid());
        values.put(COLUMN_USERID_SAVINGS, savings.getUserid());
        values.put(COLUMN_SAVINGTYPE, savings.getSaving_type());
        values.put(COLUMN_SAVINGAMOUNT, savings.getSaving_amount());
        values.put(COLUMN_STARTDATE, savings.getStartdate());
        values.put(COLUMN_ENDDATE, savings.getEnddate());
        values.put(COLUMN_SAVINGDESCRIPTION, savings.getDescription());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_SAVINGS, null, values);
        db.close();
    }

    public void deleteuser(int userid) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_USERDEATILS + " WHERE " + COLUMN_USERID + "=" + userid + ";");
        db.close();
    }
    public void deleteexpense(int userid) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_EXPENSE + " WHERE " + COLUMN_USERID_EXPENSE + "=" + userid + ";");
        db.close();
    }
    public void deletesaving(int userid) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_SAVINGS + " WHERE " + COLUMN_USERID + "=" + userid + ";");
        db.close();
    }

    public String databasetostring(){
        String dbstring = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_USERDEATILS;
        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();
        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("familyid"))!=null){
                dbstring += "Family ID -> "; dbstring += c.getString(c.getColumnIndex("familyid")); dbstring += "\n";
                dbstring += "User ID -> "; dbstring += c.getString(c.getColumnIndex("userid")); dbstring += "\n";
                dbstring += "Salary -> "; dbstring += c.getString(c.getColumnIndex("salary")); dbstring += "\n";
                dbstring += "Current Unused Salary -> "; dbstring += c.getString(c.getColumnIndex("currentunusedsalary")); dbstring += "\n";
                dbstring += "User Name -> "; dbstring += c.getString(c.getColumnIndex("username")); dbstring += "\n";
                dbstring += "Is Head -> "; dbstring += c.getString(c.getColumnIndex("head")); dbstring += "\n";
                dbstring += "Password -> "; dbstring += c.getString(c.getColumnIndex("password")); dbstring += "\n";
                dbstring += "Email ID -> "; dbstring += c.getString(c.getColumnIndex("email")); dbstring += "\n";
                dbstring += "\n\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbstring;
    }
    public String databasetostring_expense(){
        String dbstring = "";
        SQLiteDatabase db = getWritableDatabase();
        String query_expense = "SELECT * FROM " + TABLE_EXPENSE;
        Cursor c = db.rawQuery(query_expense,null);
        c.moveToFirst();
        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("expenseid"))!=null){
                /*dbstring += "Family ID -> "; dbstring += c.getString(c.getColumnIndex("familyid")); dbstring += "\n";
                dbstring += "Expense id -> "; dbstring += c.getString(c.getColumnIndex("expenseid")); dbstring += "\n";
                dbstring += "User id -> "; dbstring += c.getString(c.getColumnIndex("userid")); dbstring += "\n";*/
                dbstring += "expense category -> "; dbstring += c.getString(c.getColumnIndex("expensecategory")); dbstring += "\n";
                dbstring += "expense amount -> "; dbstring += c.getString(c.getColumnIndex("expenseamount")); dbstring += "\n";
                dbstring += "note -> "; dbstring += c.getString(c.getColumnIndex("note")); dbstring += "\n";
                dbstring += "date time -> "; dbstring += c.getString(c.getColumnIndex("datetime")); dbstring += "\n";
                dbstring += "\n\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbstring;
    }
    public String databasetostring_saving(){
        String dbstring = "";
        SQLiteDatabase db = getWritableDatabase();
        String query_saving = "SELECT * FROM " + TABLE_SAVINGS;
        Cursor c = db.rawQuery(query_saving,null);
        c.moveToFirst();
        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("savingid"))!=null){
                dbstring += "Family ID -> "; dbstring += c.getString(c.getColumnIndex("familyid")); dbstring += "\n";
                dbstring += "Saving id -> "; dbstring += c.getString(c.getColumnIndex("savingid")); dbstring += "\n";
                dbstring += "User id -> "; dbstring += c.getString(c.getColumnIndex("userid")); dbstring += "\n";
                dbstring += "Saving type -> "; dbstring += c.getString(c.getColumnIndex("savingtype")); dbstring += "\n";
                dbstring += "Saving amount -> "; dbstring += c.getString(c.getColumnIndex("savingamount")); dbstring += "\n";
                dbstring += "Start date -> "; dbstring += c.getString(c.getColumnIndex("startdate")); dbstring += "\n";
                dbstring += "End date -> "; dbstring += c.getString(c.getColumnIndex("enddate")); dbstring += "\n";
                dbstring += "Description -> "; dbstring += c.getString(c.getColumnIndex("savingdescription")); dbstring += "\n";
                dbstring += "\n\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbstring;
    }
    public boolean login(String email, String pwd){
        boolean flag=false;
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_USERDEATILS;
        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();
        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex(COLUMN_USERID))!=null){
                String dbemail,dbpwd;
                dbemail=c.getString(c.getColumnIndex(COLUMN_EMAIL));
                dbpwd=c.getString(c.getColumnIndex(COLUMN_PASSWORD));
                if(email.equals(dbemail) && pwd.equals(dbpwd)) {
                    flag = true;
                    SharedPreferences.Editor editor = con.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putInt("userid", c.getInt(c.getColumnIndex(COLUMN_USERID)));
                    editor.putString("familyid", c.getString(c.getColumnIndex(COLUMN_FAMILYID)));
                    editor.putInt("salary", c.getInt(c.getColumnIndex(COLUMN_SALARY)));
                    editor.putInt("unused", c.getInt(c.getColumnIndex(COLUMN_UNUSEDSALARY)));
                    editor.putString("username", c.getString(c.getColumnIndex(COLUMN_USERNAME)));
                    editor.putInt("head", c.getInt(c.getColumnIndex(COLUMN_HEAD)));
                    editor.putString("password", c.getString(c.getColumnIndex(COLUMN_PASSWORD)));
                    editor.putString("email", c.getString(c.getColumnIndex(COLUMN_EMAIL)));
                    editor.commit();
                }
            }
            c.moveToNext();
        }
        db.close();
        return flag;
    }
    public int count_expense(int uid,String type) {
        Toast.makeText(con, "uid = " + uid + "\n type = " + type, Toast.LENGTH_LONG).show();
        int count = 0;
        String query;
        if (type.equals("all")) {
            query = "SELECT * FROM " + TABLE_EXPENSE + " WHERE " + COLUMN_USERID_EXPENSE + " = " + uid;
        } else {
            query = "SELECT * FROM " + TABLE_EXPENSE + " WHERE " + COLUMN_USERID_EXPENSE + " = " + uid + " AND " + COLUMN_EXPENSECATEGORY + " = " + type;
        }
        Toast.makeText(con, "query = " + query, Toast.LENGTH_LONG).show();
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        int i = 0;
        String dbstring = "";
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("expenseid")) != null) {
                /*dbstring += "Family ID -> "; dbstring += c.getString(c.getColumnIndex("familyid")); dbstring += "\n";*/
                dbstring += "Expense id -> ";
                dbstring += c.getString(c.getColumnIndex("expenseid"));
                dbstring += "\n";
                dbstring += "User id -> ";
                dbstring += c.getString(c.getColumnIndex("userid"));
                dbstring += "\n";
                dbstring += "expense category -> ";
                dbstring += c.getString(c.getColumnIndex("expensecategory"));
                dbstring += "\n";
                dbstring += "expense amount -> ";
                dbstring += c.getString(c.getColumnIndex("expenseamount"));
                dbstring += "\n";
                dbstring += "note -> ";
                dbstring += c.getString(c.getColumnIndex("note"));
                dbstring += "\n";
                dbstring += "date time -> ";
                dbstring += c.getString(c.getColumnIndex("datetime"));
                dbstring += "\n";
                dbstring += "\n\n";
            }
        }
        /*while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex(COLUMN_EXPENSEID))!=null && c.getInt(c.getColumnIndex(COLUMN_USERID_EXPENSE)) == uid){
                Toast.makeText(con, "count"+i+" = " + count, Toast.LENGTH_LONG).show();
                count = count + c.getInt(c.getColumnIndex(COLUMN_EXPENSEAMOUNT));
                }
                i=i+1;
            }*/
            c.moveToNext();
            db.close();
            Toast.makeText(con, dbstring, Toast.LENGTH_LONG).show();
            return count;
        }
        public int getuserid()
        {
            int temp=0;
            SQLiteDatabase db = getWritableDatabase();
            String query = "SELECT * FROM " + TABLE_USERDEATILS;
            Cursor c = db.rawQuery(query,null);
            c.moveToFirst();
            while(!c.isAfterLast()){
                temp = c.getInt(c.getColumnIndex("userid"));
                c.moveToNext();
            }
            db.close();
            return temp;
        }
}
