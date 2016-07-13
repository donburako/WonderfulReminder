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

public class EditTask {
    private SQLiteDatabase db;
    private TaskDB taskdb;
    private final String TABLE_NAME = "WR_DB";

    // コンストラクタ
    public EditTask(TaskDB taskdb){ this.taskdb = taskdb; }

    // TaskManagerからの呼び出し
    public boolean execute(Task before, Task after){ return edit(before, after); }

    // Taskの更新
    private boolean edit(Task before, Task after){
        // ---[手順]---
        // 1. taskdbからdbを開く
        // 2. TaskをByteに変換
        // 3. ContentValuesでアップデート文作成
        // 4. dbをアップデート
        // 5. dbをclose
        // ------------

        // 1. taskdbからdbを開く
        db = taskdb.getWritableDatabase();

        // 2. TaskをByteに変換
        byte[] taskByteAfter = null;
        try{
            // after
            ByteArrayOutputStream byteosA = new ByteArrayOutputStream();
            ObjectOutputStream objosA = new ObjectOutputStream(byteosA);
            objosA.writeObject(after);
            objosA.close(); byteosA.close();
            taskByteAfter = byteosA.toByteArray();
        }catch(java.io.IOException e){
            e.printStackTrace();
        }

        // 3. ContentValuesでアップデート文作成
        ContentValues values = new ContentValues();
        values.put("TASK", taskByteAfter);

        // 4. dbをアップデート
        long result = db.update(TABLE_NAME, values, "ID = ?", new String[]{ String.valueOf(before.getNumber()) });

        // 失敗した場合
        if(result == -1) throw new SQLException("Failed to update row");

        // 5. dbをclose
        db.close();

        return result != -1;
    }
}
