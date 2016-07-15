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
    public boolean execute(int num){
        return delete(num);
    }

    // Taskの削除
    private boolean delete(int num){
        // -5が来た場合(Alarm鳴ってそれを削除する時にうまくいかなかった時)
        if(num==-5) throw new SQLException("Alarm is OK. But this Task cannot delete.");

        // taskdbからdbを開く
        db = taskdb.getWritableDatabase();

        // dbからデリート
        long result = db.delete(TABLE_NAME, "ID = ?", new String[]{ String.valueOf(num) });

        // 失敗した場合
        if(result == -1) throw new SQLException("Failed to delete row");

        // 何も削除されなかった場合
        if(result == 0) throw new SQLException("Task to delete is not find");

        // 4. dbをclose
        db.close();

        return result != -1;
    }

}
