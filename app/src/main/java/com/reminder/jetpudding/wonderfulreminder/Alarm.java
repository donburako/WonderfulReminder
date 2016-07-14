package com.reminder.jetpudding.wonderfulreminder;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.media.MediaPlayer;

import java.util.Calendar;
import java.util.Date;
/**
 * Created by mbpusr on 2016/07/01.
 */
public class Alarm {
    private MediaPlayer mp;

    public void ring(Context context){
        mp = MediaPlayer.create(context, R.raw.test);
        mp.start();
        //アラート通知

    }

    //アラームストップ
    private void stopAndRelaese(){
            mp.stop();
            mp.release();
    }

    public boolean matchtime(Date date, Calendar currenttime) {
        Calendar cal = Calendar.getInstance();
        //Calendarクラスのインスタンスを生成
        cal.setTime(date);
            int diff = cal.compareTo(currenttime);
            if (diff == 0) {
                //TaskManagerを入れてくれればエラーは消えるはず！
                return true;
            } else {
                return false;
            }

    }

    public void addAlarm(int alarmHour, int alarmMinute){
        // アラームを設定する
        mAlarmSender = this.getPendingIntent();

        // アラーム時間設定
        Calendar cal = Calendar.getInstance();
        //cal.setTimeInMillis(System.currentTimeMillis());

        // 設定した時刻をカレンダーに設定
        for(Task t:taskList) {//これでtaskList全部回せる
            //カレンダー変数への変換
            cal.setTime(t.getEndTime());

            cal.set(cal.YEAR,current.MONTH,current.DATE,current.HOUR,current.MINUTE);

            //時刻になったらring()で音鳴らす
            setAlarm(alarm);
        }
        cal.set(Calendar.HOUR_OF_DAY, alarmHour);
        cal.set(Calendar.MINUTE, alarmMinute);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        // 過去だったら明日にする
        if(cal.getTimeInMillis() < System.currentTimeMillis()){
            cal.add(Calendar.DAY_OF_YEAR, 1);
        }
        Toast.makeText(c, String.format("%02d時%02d分に起こします", alarmHour, alarmMinute), Toast.LENGTH_LONG).show();

        am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), mAlarmSender);
        Log.v(TAG, cal.getTimeInMillis()+"ms");
        Log.v(TAG, "アラームセット完了");
    }

    public void stopAlarm() {
        // アラームのキャンセル
        Log.d(TAG, "stopAlarm()");
        am.cancel(mAlarmSender);
        spm.updateToRevival();
    }

    private PendingIntent getPendingIntent() {
        // アラーム時に起動するアプリケーションを登録
        Intent intent = new Intent(c, MyAlarmService.class);
        PendingIntent pendingIntent = PendingIntent.getService(c, PendingIntent.FLAG_ONE_SHOT, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return pendingIntent;
    }
}

