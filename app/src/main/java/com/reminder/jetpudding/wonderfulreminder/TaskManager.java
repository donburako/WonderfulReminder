package com.reminder.jetpudding.wonderfulreminder;
import android.app.AlarmManager;
import android.app.PendingIntent;
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
    private static AddTask addtask;
    private static DeleteTask deletetask;
    private static EditTask edittask;
    private static MainReminderActivity mrAct;
    private static AddActivity adAct;
    private static EditActivity ediAct;
    private static boolean isInit = false;
    private static Alarm alarm;


    /*---[ SETTER ]---*/

    public static void setTaskList(List<Task> tl){ taskList = (ArrayList<Task>) tl; }
    public static void setAddtask(AddTask at){ addtask = at; }
    public static void setEdittask(EditTask et){ edittask = et; }
    public static void setDeletetask(DeleteTask dt){ deletetask = dt; }
    public static void setMrAct(MainReminderActivity mr){ mrAct = mr; }
    public static void setAdAct(AddActivity ad){ adAct = ad; }
    public static void setEdiAct(EditActivity ed){ ediAct = ed; }
    public static void setAlarm(Alarm a){ alarm = a; }


    /*---[ GETTER ]---*/

    // Activity
    public static MainReminderActivity getMrAct(){ return mrAct; }
    public static AddActivity getAdAct(){ return adAct; }
    public static EditActivity getEdiAct(){ return ediAct; }
    public static boolean getIsInit(){ return isInit; }
    public static List<Task> getTaskList(){ return taskList; }
    public static int getTaskListSize(){ return taskList.size(); }
    public static Alarm getAlarm(){ return alarm; }


    /*---[ FUNCTION ]---*/

    public static void addExecute(Task task, Context c){ taskList.add(addtask.execute(task)); alarm.addAlarm(task,c);}
    public static void deleteExecute(int num, Context c){
        if(deletetask.execute(num)){ removeTaskById(taskList,num); alarm.stopAlarm(c, num); }
    }
    public static void editExecute(Task before, Task after)
        { if(edittask.execute(before,after))taskList.set(taskList.indexOf(before), after); }

    public static void initOk(){ isInit = true; }

    // deleteをnumのみでやることにしたので、intでもlistから削除できるようにメソッドづくり
    private static boolean removeTaskById(List<Task> taskList, int num){
        for(Task t:taskList) if(t.getNumber()==num){ taskList.remove(t); return true; }
        return false;
    }



    //////////////////////
    // not static space //
    // ---------------- //
    // 初期化担当！     //
    //////////////////////

    //初期化
    public void init(Context context) {
        TaskDB db = new TaskDB(context);

        // static spaceの方のTaskManagerにインスタンスを送る
        TaskManager.setTaskList(Task.getAllTasks(db));
        TaskManager.setAddtask(new AddTask(db));
        TaskManager.setDeletetask(new DeleteTask(db));
        TaskManager.setEdittask(new EditTask(db));

        // Alarm
        TaskManager.setAlarm(new Alarm());

        }
    }
