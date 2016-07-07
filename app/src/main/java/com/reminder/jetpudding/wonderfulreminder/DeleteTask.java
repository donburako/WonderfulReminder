package com.reminder.jetpudding.wonderfulreminder;

/**
 * Created by yukari1210 on 2016/07/05.
 */
import java.util.*;
import android.database.sqlite.SQLiteDatabase;

public class DeleteTask {
    SQLiteDatabase db;

    // コンストラクタ
    public DeleteTask(TaskDB taskdb){
        this.db = taskdb.getWritableDatabase();
    }

    // TaskManagerからの呼び出し
    public void execute(Task task){
        //delete(task);
    }

    // Taskの追加
    //private void delete(Task task){
    //    db.execSQL(/*ここで操作*/);
    //}
}
