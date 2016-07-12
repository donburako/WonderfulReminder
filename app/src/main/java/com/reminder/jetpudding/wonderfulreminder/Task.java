package com.reminder.jetpudding.wonderfulreminder;
import android.database.SQLException;
import android.database.sqlite.SQLiteCursor;
import android.widget.DatePicker;
import android.widget.EditText;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
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

    // 全てのtaskを読み込んでリストにして返す
    public static List<Task> getAllTasks(TaskDB taskdb){
        List<Task> allTasks = new ArrayList<>(); // 全てのtaskを入れるList

        try{
            // SQL文の実行、それをCursorに格納
            SQLiteCursor c = (SQLiteCursor) taskdb.getWritableDatabase().rawQuery
                    ("SELECT * FROM WR_DB;", null);

            // cからデータを抽出してTaskにキャスト
            ObjectInputStream ois; // byte[]をTaskへキャストするための…
            c.moveToFirst(); // 最初のデータの行へカーソルを移動
            for(int i = 0; i<c.getCount(); i++){
                ois = new ObjectInputStream(new ByteArrayInputStream(c.getBlob(1)));
                allTasks.add((Task) ois.readObject()); ois.close();
                c.moveToNext();
            }

        }catch(java.io.IOException e){ e.printStackTrace();
        }catch(java.lang.ClassNotFoundException e){ e.printStackTrace();
        }catch(SQLException e){ e.printStackTrace();

        }

        return allTasks;
    }
}

