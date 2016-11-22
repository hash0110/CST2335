package com.example.adam.lab1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ChatDatabaseHelper extends SQLiteOpenHelper{

    protected static String DATABASE_NAME = "chat_db";
    protected static int VERSION_NUM = 3;
    public final static String KEY_ID = "ID", KEY_MESSAGE = "Message", TABLE = "Chat";

    ChatDatabaseHelper(Context ctx){
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }

    public void onCreate(SQLiteDatabase db){
        Log.i("ChatDatabaseHelper", "Calling: onCreate");
        db.execSQL("CREATE TABLE " + TABLE + " (" + KEY_ID + " int AUTO_INCREMENT, "
                + KEY_MESSAGE + " text, PRIMARY KEY (" + KEY_ID + "))");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("ChatDatabaseHelper", "Calling: onUpgrade, oldVersion=" + oldVersion + " newVersion=" + newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }


}
