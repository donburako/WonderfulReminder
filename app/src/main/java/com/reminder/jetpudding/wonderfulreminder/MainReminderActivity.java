package com.reminder.jetpudding.wonderfulreminder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.*;

public class MainReminderActivity extends AppCompatActivity {

    Intent intent=new Intent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_reminder);
    }
    public void onClickDecide(){
        //Main画面へ遷移するように設定
    }
}
