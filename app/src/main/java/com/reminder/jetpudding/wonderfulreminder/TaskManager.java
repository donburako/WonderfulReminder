package com.reminder.jetpudding.wonderfulreminder;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

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
    public static void setAlarm(Alarm al){ alarm = al;al.ring(context);}
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
    //alarm
    public static Alarm getAlarm(){return alarm;}


    /*---[ FUNCTION ]---*/
    // TaskListにアラーム鳴らすやつがあるかチェック
    // *****[未修整]******

    AlarmManager am;
    private PendingIntent mAlarmSender;

    private static final String TAG = TaskManager.class.getSimpleName();

    public TaskManager(Context c){
        // 初期化
        this.context = context;
        am = (AlarmManager)c.getSystemService(Context.ALARM_SERVICE);
    }

    public void addAlarm(){
        // アラームを設定する
        mAlarmSender = this.getPendingIntent();

        // アラーム時間設定

        Calendar cal = Calendar.getInstance();
        //cal.setTimeInMillis(System.currentTimeMillis());

        // 設定した時刻をカレンダーに設定
        for(Task t:taskList) {//これでtaskList全部回せる
            //カレンダー変数への変換
            cal.setTime(t.getEndTime());

            //時刻になったらring()で音鳴らす
            cal.set(Calendar.HOUR_OF_DAY, cal.HOUR_OF_DAY);
            cal.set(Calendar.MINUTE, cal.MINUTE);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
        }

        // 過去だったら明日にする
        if(cal.getTimeInMillis() < System.currentTimeMillis()){
            cal.add(Calendar.DAY_OF_YEAR, 1);
        }
        Toast.makeText(context, String.format("%02d時%02d分に鳴らします", cal.HOUR_OF_DAY, cal.MINUTE), Toast.LENGTH_LONG).show();

        am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), mAlarmSender);
        Log.v(TAG, cal.getTimeInMillis()+"ms");
        Log.v(TAG, "アラームセット完了");
    }

    public void stopAlarm() {
        // アラームのキャンセル
        Log.d(TAG, "stopAlarm()");
        am.cancel(mAlarmSender);
        //spm.updateToRevival();
    }

    private PendingIntent getPendingIntent() {
        // アラーム時に起動するアプリケーションを登録
        Intent intent = new Intent(context, AlartAlarm.class);
        PendingIntent pendingIntent = PendingIntent.getService(context, PendingIntent.FLAG_ONE_SHOT, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return pendingIntent;
    }

    public static void checkAlarm(){
        //現在の時間取得
        Date date = new Date();
        Calendar current = Calendar.getInstance();
        //Date型からカレンダー型に変換
        current.setTime(date);
        //current.set(current.YEAR,current.MONTH,current.DATE,current.HOUR,current.MINUTE);

        //matchtime()メソッドで現在の時刻と設定時刻を比べる

        for(Task t:taskList) {//これでtaskList全部回せる
            if (alarm.matchtime(t.getEndTime(), current)) {
                //時刻になったらring()で音鳴らす
                setAlarm(alarm);
            }
        }
        //Task.getAllTasks(db).endtime;
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
    public void init(Context context) {
        TaskManager.setContext(context);

        TaskDB db = new TaskDB(context);

        // static spaceの方のTaskManagerにインスタンスを送る
        TaskManager.setTaskList(Task.getAllTasks(db));
        TaskManager.setAddtask(new AddTask(db));
        TaskManager.setDeletetask(new DeleteTask(db));
        TaskManager.setEdittask(new EditTask(db));

        // Alarm
        //TaskManager.setAlarm(new Alarm());

        //Alarm確かめっぱなし


        }
    }
