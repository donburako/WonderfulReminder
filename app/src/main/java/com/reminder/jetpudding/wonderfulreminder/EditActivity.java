package com.reminder.jetpudding.wonderfulreminder;

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
    public void change(View view){
        finish();
    }
    public void toDelete(View view){
        finish();
    }
}
