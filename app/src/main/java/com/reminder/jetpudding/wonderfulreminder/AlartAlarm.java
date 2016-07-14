package com.reminder.jetpudding.wonderfulreminder;

/**
 * Created by yukari1210 on 2016/07/14.
 */

import android.app.*;
import android.content.DialogInterface;
import android.os.Bundle;
import java.util.*;
import android.media.*;
import android.widget.Toast;

public class AlartAlarm extends Activity{
    private MediaPlayer mp;
    Alarm alarm=new Alarm();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_reminder);

        AlertDialog.Builder dialogBuilder=new AlertDialog.Builder(this);
        AlertDialog Dialog=dialogBuilder.create();
        dialogBuilder.setTitle("リマインダー");
        dialogBuilder.setMessage("時間になりました");
        onStart();
        dialogBuilder.setPositiveButton("OK",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog,int which){
                onDestroy();
                dialog.cancel();
            }
        });

        dialogBuilder.setCancelable(true);

        Dialog.show();

    }
    @Override
    public void onStart(){
        super.onStart();
        if(mp==null){
            mp=MediaPlayer.create(this,R.raw.test);
        }
        mp.start();
        Toast.makeText(getApplicationContext(), "アラームすたーと", Toast.LENGTH_LONG).show();
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        stopAndRelease();
    }
    private void stopAndRelease(){
        if(mp!=null){
            mp.stop();
            mp.release();
        }
    }
}
