package com.reminder.jetpudding.wonderfulreminder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

public class MainReminderActivity extends AppCompatActivity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_reminder);

    }

    public void toAddTaskActivity(View view){
        Intent intent = new Intent(MainReminderActivity.this, AddActivity.class);
        startActivity(intent);
    }
}
