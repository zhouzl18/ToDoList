package com.fenglianhai.todolist;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class ToDoListActivity extends ActionBarActivity implements NewItemFragment.OnNewItemAddedListener{

    private ArrayList<ToDoItem> todoItems;
    private ToDoItemAdapter aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        //获取fragment的引用
        FragmentManager fm = getSupportFragmentManager();
        ToDoListFragment toDoListFragment = (ToDoListFragment) fm.findFragmentById(R.id.ToDoListFragment);


        todoItems = new ArrayList<>();
        //创建adapter
        int layoutID = R.layout.todolist_item;
        aa = new ToDoItemAdapter(this, layoutID, todoItems);

        toDoListFragment.setListAdapter(aa);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_to_do_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * todoItem
     * @param newItem
     */
    @Override
    public void onNewItemAdded(String newItem) {
        todoItems.add(0, new ToDoItem(newItem));
        aa.notifyDataSetChanged();
    }
}
