package com.reminder.jetpudding.wonderfulreminder;
import java.util.*;

public class TaskManager{
    Alarm alarm=new Alarm();
    TaskSet TaskSetList=new TaskSet();
    AddTask addtask=new AddTask();
    DeleteTask deletetask=new DeleteTask();
    EditTask edittask=new EditTask();
    public void checkAlarm(){
	Date currentDate;
	
    }
    public void AddExecute(){
	addtask.AddTask(TaskSetList);
    }
    public void DeleteExecute(){
	deletetask.DeleteTask(TaskSetList);
    }
    public void EditExecute(){
	edittask.EditTask();
    }

}
