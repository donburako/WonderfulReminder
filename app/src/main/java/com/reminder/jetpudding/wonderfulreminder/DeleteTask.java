package com.reminder.jetpudding.wonderfulreminder;

/**
 * Created by yukari1210 on 2016/07/05.
 */
import java.util.*;
import android.database.sqlite.SQLiteDatabase;

public class DeleteTask {

    TaskDB db;

    public DeleteTask(TaskDB db){
        this.db=db;
    }
    public void execute(Task task){
        db.delete(task);
    }
}
