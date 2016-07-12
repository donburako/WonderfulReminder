package com.reminder.jetpudding.wonderfulreminder;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
    /* 仮でnewしておく… */
    private static List<Task> taskList;// = new ArrayList<Task>();
    private static Alarm alarm;// = new Alarm();
    private static AddTask addtask;// = new AddTask(null);
    private static DeleteTask deletetask;// = new DeleteTask(null);
    private static EditTask edittask;// = new EditTask(null);
    private static MainReminderActivity mrAct;// = new MainReminderActivity();
    private static AddActivity adAct;// = new AddActivity();
    private static EditActivity ediAct;// = new EditActivity();

    /*---[ SETTER ]---*/
    public static void setTaskList(List<Task> tl){ taskList = (ArrayList<Task>) tl; }
    public static void setAlarm(Alarm al){ alarm = al; }
    public static void setAddtask(AddTask at){ addtask = at; }
    public static void setEdittask(EditTask et){ edittask = et; }
    public static void setDeletetask(DeleteTask dt){ deletetask = dt; }
    public static void setMrAct(MainReminderActivity mr){ mrAct = mr; }
    public static void setAdAct(AddActivity ad){ adAct = ad; }
    public static void setEdiAct(EditActivity ed){ ediAct = ed; }

    /*---[ GETTER ]---*/
    // Activity
    public static MainReminderActivity getMrAct(){ return mrAct; }
    public static AddActivity getAdAct(){ return adAct; }
    public static EditActivity getEdiAct(){ return ediAct; }
    // taskList
    public static List<Task> getTaskList(){ return taskList; }
    // taskの数
    public static int getTaskListSize(){ return taskList.size(); }
    // tasknameのリスト 必要？？？
    public static List<String> getTaskNames(){
        List<String> taskNames = new ArrayList<>();
        for(Task t:taskList)
            { taskNames.add(t.getTaskName()); }
        return taskNames;
    }
    // DEBUG用 全部値が設定されているか？ -> 全部あらかじめnewしてるんじゃそりゃtrueになるわな
    public static boolean isInit()
        { return taskList!=null && alarm!=null && addtask!=null && edittask!=null && deletetask!=null
                    && mrAct!=null && adAct!=null && ediAct!=null;}


    /*---[ FUNCTION ]---*/
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
        //init();
    }

    //初期化
    public void init(Context context){
        TaskDB db=new TaskDB(context);

        // static spaceの方のTaskManagerにインスタンスを送る
        TaskManager.setTaskList(Task.getAllTasks(db));
        TaskManager.setAddtask(new AddTask(db));
        TaskManager.setDeletetask(new DeleteTask(db));
        TaskManager.setEdittask(new EditTask(db));

        // Alarm
        TaskManager.setAlarm(new Alarm());

        //画面はこっちじゃなくそれぞれの画面の方でやってもらう

        // /mrAct.makeDialog("From TaskManager.init()", "init() finished!");
    }

}
