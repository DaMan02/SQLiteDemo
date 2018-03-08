package com.dayal.sqlitedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.dayal.sqlitedemo.Data.DatabaseHandler;
import com.dayal.sqlitedemo.Model.Contact;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db=new DatabaseHandler(this);
        //insert new contacts
        Log.i("insert","inserting..");
        db.addContact(new Contact("Manjeet","7474747478"));
        db.addContact(new Contact("Amit","8635282738"));
        db.addContact(new Contact("Man","1223477838"));
        db.addContact(new Contact("Jeet","0837236738"));
        // read contacts
        Log.i("read","reading...");
        List<Contact> contactList=db.getAllContacts();
         Log.i("count",String.valueOf(db.getContactsCount()));
        //get one contact
       Contact oneContact=db.getContact(5);
        oneContact.setName("Dayal");
        oneContact.setPhnNo("8821000000");
        int newContactRow=db.updateContact(oneContact);
         Log.i("update","Updated row: " + String.valueOf(newContactRow) + " Name: " + oneContact.getName() + ",Phone no.: " + oneContact.getPhnNo());

       db.deleteContact(oneContact);
        Log.i("details","CONTACT DELETED !");
        for(Contact c:contactList){
            String log= "Roll no:"+ c.getRollNo() + ", Name:" + c.getName() + ", Phone no.:" + c.getPhnNo();
            Log.i("details",log);
        }

    }
}
