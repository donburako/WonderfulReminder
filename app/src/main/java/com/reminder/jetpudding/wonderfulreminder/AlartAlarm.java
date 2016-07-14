package com.reminder.jetpudding.wonderfulreminder;

/**
 * Created by yukari1210 on 2016/07/14.
 */

import android.app.*;
import android.content.DialogInterface;
import android.os.Bundle;

public class AlartAlarm extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_reminder);

        AlertDialog.Builder dialogBuilder=new AlertDialog.Builder(this);
        dialogBuilder.setTitle("リマインダー");
        dialogBuilder.setMessage("時間になりました");
        dialogBuilder.setPositiveButton("OK",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog,int which){
                dialog.cancel();
            }
        });

        dialogBuilder.setCancelable(true);
        AlertDialog Dialog=dialogBuilder.create();
        Dialog.show();
    }
}
