package com.reminder.jetpudding.wonderfulreminder;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.*;

/**
 * Created by yukari1210 on 2016/07/05.
 */

public class AddTask {
    private SQLiteDatabase db;
    private final String TABLE_NAME = "WR_DB";

    // コンストラクタ
    public AddTask(TaskDB taskdb){
        this.db = taskdb.getWritableDatabase();
    }

    // TaskManagerからの呼び出し
    public void execute(Task task){
        add(task);
    }

    // Taskの追加
    private void add(Task task){
        ContentValues values = new ContentValues();
        values.put("TASK", task.toByteArray());
    }
}
