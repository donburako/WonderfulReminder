package com.reminder.jetpudding.wonderfulreminder;


/**
 * Created by yukari1210 on 2016/07/05.
 */

////////////////
// TaskDB
// ------------
// DataBaseとの通信を一括担当
////////////////

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import java.util.*;

public class TaskDB extends SQLiteOpenHelper{

    // DataBase作成時の実行文
    final private String createTableSql = "Create table WR_DB ("
                                            +"ID INTEGER PRIMARY KEY" //  not AUTOINCREMENT now
                                            +",TASK BLOB NOT NULL)";

    // databaseがない時にonCreateが呼び出される→呼び出されたらdatabaseを作ればよい
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(createTableSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // databaseがversionを変えるときに呼び出されるらしい
        // 関係ないので空白
    }

    // コンストラクタ
    public TaskDB(Context c){
        super(c, "WR_DB", null, 1); // 引数: context, DB名, nullでよし, ver数
    }

}
