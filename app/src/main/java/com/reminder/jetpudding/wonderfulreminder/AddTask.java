package com.reminder.jetpudding.wonderfulreminder;

import android.content.ContentValues;
import android.database.Cursor;
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
    public Task execute(Task task){
        return add(task);
    }

    // Taskの追加
    private Task add(Task task){

        // taskdbからdbを開く
        db = taskdb.getWritableDatabase();

        // taskの中身を調節(numberを)
        // antoincrementを解除したので、管理できるはず…
        Cursor c = db.rawQuery("SELECT MAX(ID) FROM WR_DB", null);
        int id = -1;
        if (c != null && c.moveToLast()){ id = c.getInt(0) + 1; }
        //TaskManager.getAdAct().makeDialog("LAST_INSERT_ROWID()", "rowId="+(rowId));
        task.setNumber(id);

        // tasknumberが-1のままだったらエラー
        if(task.getNumber()==-1) throw new SQLException("Failed set taskNumber");

        // TaskをByteに変換
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
        values.put("ID",id);
        values.put("TASK", taskByte);

        // 4. dbにインサート
        long result = db.insert(TABLE_NAME, null, values);

        // 失敗した場合
        if(result == -1) throw new SQLException("Failed to insert row");

        // 5. dbをclose
        db.close();

        return task;
    }
}
