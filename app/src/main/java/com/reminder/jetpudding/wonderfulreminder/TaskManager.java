package com.reminder.jetpudding.wonderfulreminder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.*;

public class TaskManager extends AppCompatActivity{
    List<Task> taskList;
    Alarm alarm;
    AddTask addtask;
    DeleteTask deletetask;
    EditTask edittask;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        init();
    }

    // TaskListにアラーム鳴らすやつがあるかチェック
    // *****[未修整]******
    public void checkAlarm(){
	    Date currentDate;
	
    }
    public void AddExecute(){
	addtask.AddTask(taskList);
    }
    public void DeleteExecute(){
	deletetask.DeleteTask(taskList);
    }
    public void EditExecute(){
	edittask.EditTask();
    }
    // *******************

    //初期化
    private void init(){
        //taskSet初期化
        taskList=Task.getAllTasks();

        //databaseの初期化と接続
        TaskDB db=new TaskDB(this);
        addtask=new AddTask(db);
        deletetask=new DeleteTask(db);
        edittask=new EditTask(db);

        // Alarm
        alarm = new Alarm();

        //画面遷移
    }
}
