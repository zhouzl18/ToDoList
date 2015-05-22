package com.fenglianhai.todolist;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/4/29.
 */
public class ToDoItemAdapter extends ArrayAdapter<ToDoItem> {

    int resource;

    public ToDoItemAdapter(Context context, int resource, List<ToDoItem> items){
        super(context, resource, items);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout toDoView;

        ToDoItem item = getItem(position);
        String taskStr = item.getTask();
        Date createdDate = item.getCreated();
        SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
        String dateStr = sdf.format(createdDate);

        if(convertView == null){
            toDoView = new LinearLayout(getContext());
            LayoutInflater.from(getContext()).inflate(resource, toDoView, true);
            //LayoutInflater li =(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }else {
            toDoView = (LinearLayout) convertView;
        }
        TextView dateView = (TextView) toDoView.findViewById(R.id.rowDate);
        TextView taskView = (TextView) toDoView.findViewById(R.id.row);

        dateView.setText(dateStr);
        taskView.setText(taskStr);

        return toDoView;
    }
}
