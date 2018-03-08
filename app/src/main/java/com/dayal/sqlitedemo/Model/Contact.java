package com.dayal.sqlitedemo.Model;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Contact  {
    private String name;
    private int rollNo;
    private String phnNo;

    public Contact() {
    }

    public Contact( int rollNo,String name, String phnNo) {
        this.name = name;
        this.rollNo = rollNo;
        this.phnNo = phnNo;
    }

    public Contact(String name, String phnNo) {
        this.name = name;
        this.phnNo = phnNo;
    }

    public String getName() {
        return name;
    }

    public int getRollNo() {
        return rollNo;
    }

    public String getPhnNo() {
        return phnNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public void setPhnNo(String phnNo) {
        this.phnNo = phnNo;
    }
}
