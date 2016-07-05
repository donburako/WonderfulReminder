package com.reminder.jetpudding.wonderfulreminder;
import java.util.*;

public class TaskSet{
    private Task task;
    //boolean isRing;
    private Alarm alarm;

    public Task getTask(){
	    return task;
    }
    public Alarm getAlarm(){
        return alarm;
    }
    public TaskSet(Task task,Alarm alarm){
        this.task=task;
        this.alarm=alarm;
        }

    public static List<TaskSet> getAllTaskSet(){
        // databaseに接続、ぜんぶのデータをTaskSetに格納して、Listを返す。
    }

}
