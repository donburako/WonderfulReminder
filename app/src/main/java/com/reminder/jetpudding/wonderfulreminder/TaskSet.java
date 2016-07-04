package com.reminder.jetpudding.wonderfulreminder;
import java.util.*;

public class TaskSet{
    private String taskname;
    private Date endtime;
    private String detail;
    private int num;
    boolean isRing;
    Alarm alarm=new Alarm();
    Task task=new Task(num, taskname, endtime, detail);
    public void getTask(){
	taskname = task.getTaskName();
	endtime = task.getEndTime();
	detail = task.getDetail();
	num = task.getNumber();
    }
    public void getAlarm(){
        Date currenttime=new Date();
        isRing=alarm.matchtime(currenttime);

    }
    public void TaskSet(int num, String taskname, String detail, Date endtime,boolean isRing){
        this.num=num;
        this.taskname=taskname;
        this.detail=detail;
        this.endtime=endtime;
        this.isRing=isRing;
    }

}
