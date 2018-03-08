package com.dayal.sqlitedemo.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.dayal.sqlitedemo.Model.Contact;
import com.dayal.sqlitedemo.Util.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper{

    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME,null,Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
          String CREATE_CONTACT_TABLE="CREATE TABLE "+ Util.TABLE_NAME+"(" + Util.KEY_ID + " INTEGER PRIMARY KEY," +
                    Util.KEY_NAME + " TEXT," + Util.KEY_PHONE_NUMBER + " TEXT " + ")";
              db.execSQL(CREATE_CONTACT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS" + Util.TABLE_NAME);

        //create a new table
        onCreate(db);
    }
    //add contact
    public void addContact(Contact contact){
         SQLiteDatabase db=this.getWritableDatabase();
        //adding new name & phNo
        ContentValues value=new ContentValues();
        value.put(Util.KEY_NAME,contact.getName());
        value.put(Util.KEY_PHONE_NUMBER,contact.getPhnNo());
        //insert to row
        db.insert(Util.TABLE_NAME,null,value);
        db.close();
    }
    //retrieve a contact by passing an id
    public Contact getContact(int id){
         SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.query(Util.TABLE_NAME,new String[]{Util.KEY_ID,Util.KEY_NAME,Util.KEY_PHONE_NUMBER},Util.KEY_ID + "=?",
                new String[]{String.valueOf(id)},null,null,null,null);

        if(cursor!=null)
        cursor.moveToFirst();
        Contact contact=new Contact(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2));    // constructer : int rollNo,String name, String phnNo
      //  Log.i("details","cursor at pos: " + String.valueOf(cursor.isFirst()));
        return contact;
    }
     //retrieve all contacts
    public List<Contact> getAllContacts(){
        SQLiteDatabase db=this.getReadableDatabase();
        List<Contact> contactList=new ArrayList<>();

        String selectAll="SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor=db.rawQuery(selectAll,null);

        //loop through all contacts
        if(cursor.moveToFirst()){
            do{
                Contact contact=new Contact();
                contact.setRollNo(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhnNo(cursor.getString(2));
                contactList.add(contact);
            }while(cursor.moveToNext());
          cursor.close();
        }
        return contactList;
    }
    public int updateContact(Contact contact){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Util.KEY_NAME, contact.getName());
        values.put(Util.KEY_PHONE_NUMBER,contact.getPhnNo());
        // update row
        return db.update(Util.TABLE_NAME,values,Util.KEY_ID + "=?",new String[]{String.valueOf(contact.getRollNo())});
    }

    //delete a contact
    public void deleteContact(Contact contact){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(Util.TABLE_NAME, Util.KEY_ID + "=?", new String[]{String.valueOf(contact.getRollNo())});
        db.close();
    }

    // get contatcs count
    public int getContactsCount(){
        String countQuery="SELECT * FROM " + Util.TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(countQuery,null);
        return cursor.getCount();
    }
}
