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

        pen = this.getPendingIntent(context, task.getNumber());
        am  = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        // アラーム時間設定
        Calendar cal = Calendar.getInstance();

        // 設定した時刻をカレンダーに設定
        cal.setTime(task.getEndTime());
        am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),pen);
    }

    public void stopAlarm(Context c, int num) {
        // アラームのキャンセル
        am = (AlarmManager)c.getSystemService(Context.ALARM_SERVICE);
        am.cancel(getPendingIntent(c, num));
        //spm.updateToRevival();
    }

    private PendingIntent getPendingIntent(Context context, int num) {
        // アラーム時に起動するアプリケーションを登録
        Intent intent = new Intent(context, MyAlarmService.class);
        PendingIntent pendingIntent = PendingIntent.getService(context, num, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return pendingIntent;
    }
}

