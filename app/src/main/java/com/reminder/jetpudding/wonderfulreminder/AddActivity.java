package com.reminder.jetpudding.wonderfulreminder;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.content.*;
import android.widget.*;
import java.util.*;
import com.reminder.jetpudding.wonderfulreminder.R.id.*;

import static com.reminder.jetpudding.wonderfulreminder.AddTask.*;

public class AddActivity extends AppCompatActivity {


     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add); // レイアウトを適用
         TaskManager.setAdAct(this); // TaskManagerと連結

         //スヌーズの実装の際に必要
         Spinner spinner=(Spinner)findViewById(R.id.spinner);
         int idx=spinner.getSelectedItemPosition();
         String snoozeItem=(String)spinner.getSelectedItem();
    }


    public void toOK(View view){
        // 入力値の取得
        EditText taskNameText=(EditText)findViewById(R.id.taskname);
        EditText detailText=(EditText)findViewById(R.id.detail);
        DatePicker endTime=(DatePicker)findViewById(R.id.datePicker1);

        // タスク名取得
        String taskName=taskNameText.getText().toString();
        // 詳細取得
        String detail=detailText.getText().toString();

        // 時間を取得→Calendarで指定
        Calendar endCal = Calendar.getInstance();
        endCal.set(endTime.getYear(), endTime.getMonth(), endTime.getDayOfMonth());

        // Task作成
        Task task= new Task(TaskManager.getNowId(),taskName,endCal.getTime(),detail);

        // ** debug **
        makeDialog("AddTask", "Task: num="+task.getNumber()+" name="+taskName+" detail="+detail+" time="+endTime.getYear()+"/"+endTime.getMonth()+"/"+endTime.getDayOfMonth());

        // TaskAdd
        TaskManager.addExecute(task);

        // Main画面に戻ろう
        Intent intent = new Intent(AddActivity.this, MainReminderActivity.class);
        startActivity(intent);

    }

    public void makeDialog(String title, String msg) {
        // 確認ダイアログの作成
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(AddActivity.this);
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
                Intent intent = new Intent(AddActivity.this, MainReminderActivity.class);
                startActivity(intent);
            }
        });
        alertDialog.create().show();
    }
}
