package com.reminder.jetpudding.wonderfulreminder;

import java.util.*;

/**
 * Created by yukari1210 on 2016/07/05.
 */

public class AddTask {
    TaskDB db;
    public AddTask(TaskDB db){
        this.db=db;
    }
    public void execute(Task task){
        db.add(task);
    }
}
