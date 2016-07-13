package com.reminder.jetpudding.wonderfulreminder;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.SQLException;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.*;

/**
 * Created by yukari1210 on 2016/07/05.
 */

public class AddTask {
    private SQLiteDatabase db;
    private TaskDB taskdb;
    private final String TABLE_NAME = "WR_DB";

    // コンストラクタ
    public AddTask(TaskDB taskdb){
        this.taskdb = taskdb;
    }

    // TaskManagerからの呼び出し
    public boolean execute(Task task){
        return add(task);
    }

    // Taskの追加
    private boolean add(Task task){
        // ---[手順]---
        // 1. taskdbからdbを開く
        // 2. TaskをByteに変換
        // 3. ContentValuesでインサート文作成
        // 4. dbにインサート
        // 5. dbをclose
        // ------------

        // 1. taskdbからdbを開く
        db = taskdb.getWritableDatabase();

        // 2. TaskをByteに変換
        byte[] taskByte = null;
        try{
            ByteArrayOutputStream byteos = new ByteArrayOutputStream();
            ObjectOutputStream objos = new ObjectOutputStream(byteos);
            objos.writeObject(task);
            objos.close(); byteos.close();
            taskByte = byteos.toByteArray();
        }catch(java.io.IOException e){
            e.printStackTrace();
        }

        // 3. ContentValuesでインサート文作成
        ContentValues values = new ContentValues();
        values.put("TASK", taskByte);

        // 4. dbにインサート
        long result = db.insert(TABLE_NAME, null, values);

        // 失敗した場合
        if(result == -1) throw new SQLException("Failed to insert row");

        // 5. dbをclose
        db.close();

        return result != -1;
    }
}
