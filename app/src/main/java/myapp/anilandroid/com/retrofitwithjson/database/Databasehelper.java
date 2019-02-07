package myapp.anilandroid.com.retrofitwithjson.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import myapp.anilandroid.com.retrofitwithjson.model.Employee;

public class Databasehelper extends SQLiteOpenHelper {
   //Database name
    public static final String DATABASE_NAME = "Employee.db";

    //Table name
    public static final String TABLE_NAME = "employee_table";
    //columns name
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "DOB";
    public static final String COL_4 = "DESIGNATION";
    public static final String COL_5 = "CONTACTNUMBER";
    public static  final String COL_6 = "EMAIL";
    public static final String COL_7 = "SALARY";
    //
    public Databasehelper(Context context) {
        super( context, DATABASE_NAME, null, 1 );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
     db.execSQL( "create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,DOB TEXT,DESIGNATION TEXT,CONTACTNUMBER TEXT,EMAIL TEXT,SALARY TEXT)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

    public int getEmployeeCount(){
        String countQuery="SELECT * FROM "+ TABLE_NAME;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery( countQuery,null );
        int count=cursor.getCount();
        cursor.close();
        return count;
    }
    public boolean insertData(String name,String dob,String designation,String contactnumber,String email,String salary ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,dob);
        contentValues.put(COL_4,designation);
        contentValues.put( COL_5,contactnumber );
        contentValues.put( COL_6,email );
        contentValues.put( COL_7 ,salary);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
    public List<Employee> getAllEmployeeData(){
        List<Employee> emplist=new ArrayList<>(  );
        String selectQuery="SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery( selectQuery,null );
        if(cursor.moveToFirst()){
            do{
                Employee employee=new Employee();
                employee.setName( cursor.getString( cursor.getColumnIndex( COL_2 ) ) );
                employee.setDob( cursor.getString( cursor.getColumnIndex( COL_3 ) ) );
                employee.setDesignation( cursor.getString( cursor.getColumnIndex( COL_4 ) ) );
                employee.setContact_number( cursor.getString( cursor.getColumnIndex( COL_5 ) ) );
                employee.setEmail( cursor.getString( cursor.getColumnIndex( COL_6 ) ) );
                employee.setSalary( cursor.getString( cursor.getColumnIndex( COL_7 ) ) );
                emplist.add( employee );
            }while (cursor.moveToNext());
        }
        db.close();
        return emplist;
    }

    public boolean updateData(String id,String name,String dob,String designation,String contactnumber,String email,String salary) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,dob);
        contentValues.put(COL_4,designation);
        contentValues.put( COL_5,contactnumber );
        contentValues.put( COL_6,email );
        contentValues.put( COL_7,salary );
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }
    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }




}
