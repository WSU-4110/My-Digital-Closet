package com.example.mydigitalcloset;

import android.app.Application;
import android.widget.ArrayAdapter;

import java.security.SecureRandom;
import java.util.ArrayList;

public class Module extends Application {
    public ArrayList<String> garrlist = new ArrayList<>();
    public ArrayAdapter<String> garrAdp;
    public String gvalue_id;
    public String gvalue_Name;

    public String getGvalue_id() { return gvalue_id; }
    public void setGvalue_id(String id) { this.gvalue_id = id; }

    public String getGvalue_Name() { return gvalue_Name; }
    public void setGvalue_Name(String name) { this.gvalue_Name = name; }
}
