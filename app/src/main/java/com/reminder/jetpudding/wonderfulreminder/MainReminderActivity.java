package com.reminder.jetpudding.wonderfulreminder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

public class MainReminderActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_reminder); // 描画するレイアウトの指定(画面に表示する命令ではないことに注意)
        TaskManager.setMrAct(this); // taskManagerと連結
    }

    public void toAddTaskActivity(View view){
        Intent intent = new Intent(MainReminderActivity.this, AddActivity.class);
        startActivity(intent);
    }
}
