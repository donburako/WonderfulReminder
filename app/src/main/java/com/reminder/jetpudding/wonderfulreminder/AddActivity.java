package com.reminder.jetpudding.wonderfulreminder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.content.*;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.*;
import com.reminder.jetpudding.wonderfulreminder.R.id.*;

import static com.reminder.jetpudding.wonderfulreminder.AddTask.*;

public class AddActivity extends AppCompatActivity {


     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add); // レイアウトを適用
         TaskManager.setAdAct(this); // TaskManagerと連結
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
        Task task= new Task(TaskManager.getTaskListSize(),taskName,endCal.getTime(),detail);

        // TaskAdd
        TaskManager.addExecute(task);

        // Main画面に戻ろう
        Intent intent = new Intent(AddActivity.this, MainReminderActivity.class);
        startActivity(intent);
    }
}
