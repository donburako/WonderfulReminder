package com.reminder.jetpudding.wonderfulreminder;

/**
 * Created by yukari1210 on 2016/07/14.
 */

import android.app.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import java.util.*;
import android.media.*;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AlartAlarm extends Activity{
    private MediaPlayer mp;
    Alarm alarm=new Alarm();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alart_alarm);

        AlertDialog.Builder dialogBuilder=new AlertDialog.Builder(AlartAlarm.this);
        dialogBuilder.setTitle("リマインダー");
        dialogBuilder.setMessage("時間になりました");
        dialogBuilder.setPositiveButton("OK",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog,int which){ /* 何もしない */ }
        });

        onStart();
        dialogBuilder.create().show();
    }
    @Override
    public void onStart(){
        super.onStart();
        if(mp==null){
            mp=MediaPlayer.create(this,R.raw.test);
        }
        mp.start();

        TextView taskName = (TextView) findViewById(R.id.textview_alartalarm_taskname);
        TextView taskDetail = (TextView) findViewById(R.id.textview_alartalarm_taskdetail);

        taskName.setText("task name");
        taskDetail.setText("task detail");

        Toast.makeText(getApplicationContext(), "アラームすたーと", Toast.LENGTH_LONG).show();

        int debugnum = getIntent().getIntExtra("TASK_NUM", -5);
        //TaskManager.deleteExecute(debugnum, getApplicationContext());
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        stopAndRelease();

        Intent intent = new Intent(this, MainReminderActivity.class);
        startActivity(intent);
    }
    private void stopAndRelease(){
        if(mp!=null){
            mp.stop();
            mp.release();
        }
    }

    public void stopClick(View v){ onDestroy(); }
}
