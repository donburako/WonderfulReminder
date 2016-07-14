package com.reminder.jetpudding.wonderfulreminder;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import static com.reminder.jetpudding.wonderfulreminder.TaskManager.*;

/**
 * Created by mbpusr on 2016/07/01.
 */
public class Alarm{
    AlarmManager am;
    PendingIntent pen;

    public void addAlarm(Task task,Context context){
        // アラームを設定する

        pen = this.getPendingIntent(context);
        am  = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        // アラーム時間設定
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());

        // 設定した時刻をカレンダーに設定
            //cal.setTime(task.getEndTime());
        cal.setTime(task.getEndTime());
        int hour=cal.HOUR_OF_DAY;
        int min=cal.MINUTE;
        cal.set(cal.HOUR_OF_DAY,hour);
        cal.set(cal.MINUTE,min);
        cal.set(cal.SECOND, 0);
        cal.set(cal.MILLISECOND, 0);

        // 過去だったら明日にする
        if(cal.getTimeInMillis() < System.currentTimeMillis()){
            cal.add(Calendar.DAY_OF_YEAR, 1);
        }
        Toast.makeText(context, String.format("%02d時%02d分に起こします", hour, min), Toast.LENGTH_LONG).show();


        am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),pen);
    }

    public void stopAlarm() {
        // アラームのキャンセル
        am.cancel(pen);
        //spm.updateToRevival();
    }

    private PendingIntent getPendingIntent(Context context) {
        // アラーム時に起動するアプリケーションを登録
        Intent intent = new Intent(context, MyAlarmService.class);
        PendingIntent pendingIntent = PendingIntent.getService(context, PendingIntent.FLAG_ONE_SHOT, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return pendingIntent;
    }
}

