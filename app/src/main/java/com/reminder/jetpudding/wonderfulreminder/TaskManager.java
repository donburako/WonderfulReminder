package com.reminder.jetpudding.wonderfulreminder;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import java.util.*;

public class TaskManager{

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
    private static Context context;
    private static boolean isInit = false;

    /*---[ SETTER ]---*/
    public static void setTaskList(List<Task> tl){ taskList = (ArrayList<Task>) tl; }
    public static void setAlarm(Alarm al){ alarm = al;alarm.ring(context);}
    public static void setAddtask(AddTask at){ addtask = at; }
    public static void setEdittask(EditTask et){ edittask = et; }
    public static void setDeletetask(DeleteTask dt){ deletetask = dt; }
    public static void setMrAct(MainReminderActivity mr){ mrAct = mr; }
    public static void setAdAct(AddActivity ad){ adAct = ad; }
    public static void setEdiAct(EditActivity ed){ ediAct = ed; }
    public static void setContext(Context c){ context = c; }

    /*---[ GETTER ]---*/
    // Activity
    public static MainReminderActivity getMrAct(){ return mrAct; }
    public static AddActivity getAdAct(){ return adAct; }
    public static EditActivity getEdiAct(){ return ediAct; }
    // isInit
    public static boolean getIsInit(){ return isInit; }
    // taskList
    public static List<Task> getTaskList(){ return taskList; }
    // taskの数
    public static int getTaskListSize(){ return taskList.size(); }


    /*---[ FUNCTION ]---*/
    // TaskListにアラーム鳴らすやつがあるかチェック
    // *****[未修整]******

    public static void checkAlarm(){
        Date currentDate;

    }

    // ********************

    public static void addExecute(Task task){ taskList.add(addtask.execute(task)); }
    public static void deleteExecute(Task task){ if(deletetask.execute(task))
        taskList.remove(task);
    }
    public static void editExecute(Task before, Task after)
        { if(edittask.execute(before,after))taskList.set(taskList.indexOf(before), after); }

    public static void initOk(){ isInit = true; }



    //////////////////////
    // not static space //
    // ---------------- //
    // 初期化担当！     //
    //////////////////////

    // 実行時に開始したいからonCreateを書いたけど…それは適切なのか…？
    /*
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //init();
    }
    */

    //初期化
    public void init(Context context){
        TaskManager.setContext(context);

        TaskDB db=new TaskDB(context);

        // static spaceの方のTaskManagerにインスタンスを送る
        TaskManager.setTaskList(Task.getAllTasks(db));
        TaskManager.setAddtask(new AddTask(db));
        TaskManager.setDeletetask(new DeleteTask(db));
        TaskManager.setEdittask(new EditTask(db));

        // Alarm
        TaskManager.setAlarm(new Alarm());
    }

}
