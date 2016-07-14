package com.reminder.jetpudding.wonderfulreminder;
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
}

