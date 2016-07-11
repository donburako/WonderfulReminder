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

    EditText TaskName=(EditText)findViewById(R.id.taskname);
    EditText Detail=(EditText)findViewById(R.id.detail);
    DatePicker EndTime=(DatePicker)findViewById(R.id.datePicker1);
    String taskName=TaskName.getText().toString();
    long loendTime=EndTime.getYear()+EndTime.getMonth()+EndTime.getDayOfMonth();//まだ年と月と日しかとれていない
    Date enddate = new Date(loendTime);
    String detail=Detail.getText().toString();
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }


    public void toOK(View view){
       Task task= new Task(1,taskName,enddate,detail);
        //AddTask addtask=new Anew AddTask.execute(task);
       finish();
    }
}
