package com.reminder.jetpudding.wonderfulreminder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.*;

public class TaskManager extends AppCompatActivity{
    List<TaskSet> taskSetList;
    AddTask addtask;
    DeleteTask deletetask;
    EditTask edittask;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        init();
    }

    public void checkAlarm(){
	Date currentDate;
	
    }
    public void AddExecute(){
	addtask.AddTask(taskSetList);
    }
    public void DeleteExecute(){
	deletetask.DeleteTask(taskSetList);
    }
    public void EditExecute(){
	edittask.EditTask();
    }

    //初期化
    private void init(){
        //taskSet初期化
        taskSetList=TaskSet.getAllTaskSet();

        //databaseの初期化と接続(現時点ではコンストラクタ未作成のためエラー発生中)
        TaskDB db=new TaskDB();
        addtask=new AddTask(db);
        deletetask=new DeleteTask(db);
        edittask=new EditTask(db);

        //画面遷移
    }
}
