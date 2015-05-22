package com.fenglianhai.todolist;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2015/4/27.
 */
public class ToDoItem {
    String task;
    Date created;

    public String getTask() {
        return task;
    }

    public Date getCreated() {
        return created;
    }

    public ToDoItem(String task){
        this(task, new Date(System.currentTimeMillis()));
    }

    public ToDoItem(String task, Date created){
        this.task = task;
        this.created = created;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String dateStr = sdf.format(created);
        return "(" + dateStr + ")" + task;
    }
}
