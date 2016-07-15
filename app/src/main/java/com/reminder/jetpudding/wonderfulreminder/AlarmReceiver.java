package com.reminder.jetpudding.wonderfulreminder;

import android.content.*;

/**
 * Created by mbpusr on 2016/07/14.
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // アラームを受け取って起動するActivityを指定、起動
        Intent notification = new Intent(context, AlartAlarm.class);
        int debugnum = intent.getIntExtra("TASK_NUM", -5);
        notification.putExtra("TASK_NUM", debugnum); // -5のままきたら失敗してるってこと
        // 画面起動に必要
        notification.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(notification);
    }
}
