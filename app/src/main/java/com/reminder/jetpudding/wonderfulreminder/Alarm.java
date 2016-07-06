package com.reminder.jetpudding.wonderfulreminder;
import android.media.MediaPlayer;
import java.util.Date;
/**
 * Created by mbpusr on 2016/07/01.
 */
public class Alarm {
    private MediaPlayer mp;

    public void ring(){
     //  mp = MediaPlayer.create(this, R.raw.test);
        // resのrawディレクトリにtest.mp3を置いた場合(今はまだファイルもフォルダもないのでエラー状態)
        mp.start();
    }
    public boolean matchtime(Date endtime, Date currenttime){
       // if(endtime.equals(currenttime.TaskManager)){
            //TaskManagerを入れてくれればエラーは消えるはず！
            return true;
       //}else{
         //   return false;
        //}
      }
    }

