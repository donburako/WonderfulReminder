package com.reminder.jetpudding.wonderfulreminder;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainReminderActivity extends AppCompatActivity {
    private boolean isInit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_reminder); // 描画するレイアウトの指定(画面に表示する命令ではないことに注意)

        if(!isInit) {
            new TaskManager().init(getApplicationContext()); // taskManagerの初期化
            TaskManager.setMrAct(this); // taskManagerと連結
        }

        List<Task> taskList = TaskManager.getTaskList();
        ListAdapter adapter = new ListAdapter(getApplicationContext(), taskList);

        ListView listView = (ListView) findViewById(R.id.listview_tasklist);
        listView.setAdapter(adapter);

        makeDialog("DEBUG", "taskList.size() = "+taskList.size());
    }


    public void toAddTaskActivity(View view){
        Intent intent = new Intent(MainReminderActivity.this, AddActivity.class);
        startActivity(intent);
    }

    public void makeDialog(String title, String msg){
        // 確認ダイアログの作成
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainReminderActivity.this);
        alertDialog.setTitle(title);
        alertDialog.setMessage(msg);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // OKのとき
            }
        });
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // NOのとき
            }
        });
        alertDialog.create().show();
    }

    public void makeDeleteOkDialog(final Task item){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainReminderActivity.this);
        alertDialog.setTitle("タスク削除");
        alertDialog.setMessage("本当に削除しますか？");
        alertDialog.setPositiveButton("削除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TaskManager.deleteExecute(item); // 削除実行
            }
        });
        alertDialog.setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // キャンセルなら何もなし
            }
        });
        alertDialog.create().show();
    }

}

class ListAdapter extends ArrayAdapter<Task>{
        private LayoutInflater mInflater;
        private TextView mTitle;
        private Button mButton;

        public ListAdapter(Context context, List<Task> objects) {
            super(context, 0, objects);
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.listview_layout, null);
            }
            final Task item = this.getItem(position);
            if(item != null){
                mTitle = (TextView)convertView.findViewById(R.id.taskNameText);
                mTitle.setText(item.getTaskName());
                // 編集ボタン
                mButton = (Button)convertView.findViewById(R.id.toEditButton);
                mButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent i = new Intent(getContext(), EditActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.putExtra("nowTask", item);
                        getContext().startActivity(i);
                    }
                });
                // 削除ボタン
                mButton = (Button)convertView.findViewById(R.id.toDeleteButton);
                mButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        TaskManager.getMrAct().makeDeleteOkDialog(item);
                    }
                });
            }
            return convertView;
        }
}

