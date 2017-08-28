package com.androidkejar.moviegrid;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by denail on 6/2/2017.
 */

@Database(name = MyDatabase.NAME, version = MyDatabase.VERSION)
public class MyDatabase {
    public static final String NAME = "MyDataBase";

    public static final int VERSION = 1;
}
