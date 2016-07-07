package com.reminder.jetpudding.wonderfulreminder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

public class MainReminderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_reminder);

    }

    public void toAddTaskActivity(View view){
        setContentView(R.layout.add);
    }
}
