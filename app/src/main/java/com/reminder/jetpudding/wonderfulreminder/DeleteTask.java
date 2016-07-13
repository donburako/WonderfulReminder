package com.reminder.jetpudding.wonderfulreminder;

/**
 * Created by yukari1210 on 2016/07/05.
 */
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.*;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DeleteTask {
    private SQLiteDatabase db;
    private TaskDB taskdb;
    private final String TABLE_NAME = "WR_DB";

    // コンストラクタ
    public DeleteTask(TaskDB taskdb){ this.taskdb = taskdb; }

    // TaskManagerからの呼び出し
    public boolean execute(Task task){
        return delete(task);
    }

    // Taskの削除
    private boolean delete(Task task){
        // ---[手順]---
        // 1. taskdbからdbを開く
        // 2. TaskをByteに変換
        // 3. dbからデリート
        // 4. dbをclose
        // ------------

        // 1. taskdbからdbを開く
        db = taskdb.getWritableDatabase();

        // 2. TaskをByteに変換
        /* [7/13]現在不使用
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
        */

        // 3. dbからデリート
        long result = db.delete(TABLE_NAME, "ID = ?", new String[]{ String.valueOf(task.getNumber()) });

        // 失敗した場合
        if(result == -1) throw new SQLException("Failed to delete row");

        // 何も削除されなかった場合
        if(result == 0) throw new SQLException("Task to delete is not find");

        // 4. dbをclose
        db.close();

        return result != -1;
    }
}
