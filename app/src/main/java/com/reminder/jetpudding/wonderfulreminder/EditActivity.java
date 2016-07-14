package com.reminder.jetpudding.wonderfulreminder;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit); // レイアウトを適用
        TaskManager.setEdiAct(this); // TaskManagerと連結
    }
    //public void change(View view){ TaskManager.editExecute(); }
    //public void toDelete(View view){
    //    finish();
    //}

    //private Task makeTask()

    public void makeDialog(String title, String msg){
        // 確認ダイアログの作成
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(EditActivity.this);
        alertDialog.setTitle(title);
        alertDialog.setMessage(msg);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // OKのとき
            }
        });
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // NOのとき
            }
        });
        alertDialog.create().show();
    }

}
