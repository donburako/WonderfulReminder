package com.reminder.jetpudding.wonderfulreminder;
import java.util.*;

/**
 * Created by mbpusr on 2016/07/01.
 */
public class Task {
    private int num;

    private String taskname;

    private Date endtime;

    private String detail;

    public Task(int num,String taskname,Date endtime,String detail) {
        this.num=num;
        this.taskname=taskname;
        this.endtime = endtime;
        this.detail=detail;

    }

    public int getNumber(){
        return num;
    }

    public void setNumber(int num) {
        this.num = num;
    }

    public String getTaskName(){
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public Date getEndTime(){
        return endtime;
    }

    public void setEndTime(Date endtime) {
        this.endtime=endtime;
    }

    public String getDetail(){
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public static List<Task> getAllTasks(){
        List<Task> allTasks = new ArrayList<Task>();
        // 全てのTaskを読み込んでリストにして返す
        // ******[未実装]*****
        return allTasks; // 仮
    }
}

