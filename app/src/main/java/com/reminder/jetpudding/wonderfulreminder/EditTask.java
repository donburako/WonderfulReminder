package com.reminder.jetpudding.wonderfulreminder;

/**
 * Created by yukari1210 on 2016/07/05.
 */
import java.util.*;
import android.database.sqlite.SQLiteDatabase;

public class EditTask {
    TaskDB db;
     public EditTask(TaskDB db){
        this.db=db;
    }
    public void execute(Task before,Task after){
        db.edit(before,after);
    }
}
