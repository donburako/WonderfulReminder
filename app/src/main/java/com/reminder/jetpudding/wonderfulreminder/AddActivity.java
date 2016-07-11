package com.reminder.jetpudding.wonderfulreminder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.content.*;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.*;
import com.reminder.jetpudding.wonderfulreminder.R.id.*;

public class AddActivity extends AppCompatActivity {

    EditText TaskName=(EditText)findViewById(R.id.taskname);
    EditText Detail=(EditText)findViewById(R.id.detail);
    DatePicker EndTime=(DatePicker)findViewById(R.id.datePicker1);
    String taskName=TaskName.getText().toString();
    Date endTime=EndTime.getValue();

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }


    public void toOK(View view){
       Task task=Task(1,taskName,endTime,Detail);
       finish();
    }
}
