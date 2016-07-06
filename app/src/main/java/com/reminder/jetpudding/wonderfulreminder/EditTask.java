package com.reminder.jetpudding.wonderfulreminder;

/**
 * Created by yukari1210 on 2016/07/05.
 */
import java.util.*;
import android.database.sqlite.SQLiteDatabase;

public class EditTask {
    SQLiteDatabase db;

    // コンストラクタ
    public EditTask(TaskDB taskdb){
        this.db = taskdb.getWritableDatabase();
    }

    // TaskManagerからの呼び出し
    public void execute(Task before, Task after){ edit(before, after);
    }

    // Taskの追加
    private void edit(Task before, Task after){
        //db.execSQL(/*ここで操作*/);
    }
}
