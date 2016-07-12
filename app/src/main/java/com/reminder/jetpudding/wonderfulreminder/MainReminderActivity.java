package com.reminder.jetpudding.wonderfulreminder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainReminderActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_reminder); // 描画するレイアウトの指定(画面に表示する命令ではないことに注意)
        TaskManager.setMrAct(this); // taskManagerと連結
    }

    // onStartはonCreateより遅く実行される？
    protected void onStart(){
        List<String> taskList = TaskManager.getTaskNames();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.activity_main_reminder, taskList);

        ListView listView = (ListView) findViewById(R.id.listview_tasklist);
        listView.setAdapter(adapter);
    }

    public void toAddTaskActivity(View view){
        Intent intent = new Intent(MainReminderActivity.this, AddActivity.class);
        startActivity(intent);
    }
}
