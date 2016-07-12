package com.reminder.jetpudding.wonderfulreminder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.*;

public class TaskManager extends AppCompatActivity{

    ////////////////////////////
    // static space           //
    // ---------------------- //
    // 値の管理等はstaticでや //
    // ることにすればよいかと //
    ////////////////////////////

    /*---[ PARAMETER ]---*/
    private static List<Task> taskList;
    private static Alarm alarm;
    private static AddTask addtask;
    private static DeleteTask deletetask;
    private static EditTask edittask;
    private static MainReminderActivity mrAct;
    private static AddActivity adAct;
    private static EditActivity ediAct;

    /*---[ SETTER ]---*/
    public static void setTaskList(List<Task> tl){ taskList = tl; }
    public static void setAddtask(AddTask at){ addtask = at; }
    public static void setEdittask(EditTask et){ edittask = et; }
    public static void setDeletetask(DeleteTask dt){ deletetask = dt; }
    public static void setAlarm(Alarm al){ alarm = al; }
    public static void setMrAct(MainReminderActivity mr){ mrAct = mr; }
    public static void setAdAct(AddActivity ad){ adAct = ad; }
    public static void setEdiAct(EditActivity ed){ ediAct = ed; }

    /*---[ GETTER ]---*/
    public static int getTaskListSize(){ return taskList.size(); }

    // TaskListにアラーム鳴らすやつがあるかチェック
    // *****[未修整]******

    public static void checkAlarm(){
        Date currentDate;

    }

    // ********************

    public static void addExecute(Task task){ addtask.execute(task); }
    public static void deleteExecute(Task task){ deletetask.execute(task); }
    public static void editExecute(Task before, Task after){ edittask.execute(before,after); }




    //////////////////////
    // not static space //
    // ---------------- //
    // 初期化担当！     //
    //////////////////////

    // 実行時に開始したいからonCreateを書いたけど…それは適切なのか…？
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        init();
    }

    //初期化
    private void init(){
        TaskDB db=new TaskDB(this);

        // static spaceの方のTaskManagerにインスタンスを送る
        TaskManager.setTaskList(Task.getAllTasks(db));
        TaskManager.setAddtask(new AddTask(db));
        TaskManager.setDeletetask(new DeleteTask(db));
        TaskManager.setEdittask(new EditTask(db));

        // Alarm
        TaskManager.setAlarm(new Alarm());

        //画面はこっちじゃなくそれぞれの画面の方でやってもらう
    }
}
