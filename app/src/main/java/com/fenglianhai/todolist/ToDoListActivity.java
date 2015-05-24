package com.fenglianhai.todolist;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;


public class ToDoListActivity extends AppCompatActivity implements
        NewItemFragment.OnNewItemAddedListener, LoaderManager.LoaderCallbacks<Cursor>{

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

        //初始化loader
        getSupportLoaderManager().initLoader(0, null, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportLoaderManager().restartLoader(0, null, this);
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
     * @param newItem 新的任务
     */
    @Override
    public void onNewItemAdded(String newItem) {
        /*todoItems.add(0, new ToDoItem(newItem));
        aa.notifyDataSetChanged();*/
        ContentValues newValues= new ContentValues();
        newValues.put(ToDoContentProvider.KEY_TASK, newItem);
        ContentResolver cr = getContentResolver();
        cr.insert(ToDoContentProvider.CONTENT_URI, newValues);
        getSupportLoaderManager().restartLoader(0, null, this);
    }


    @Override
    public android.support.v4.content.Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this,
                ToDoContentProvider.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<Cursor> loader, Cursor data) {
        int keyTaskIndex = data.getColumnIndexOrThrow(ToDoContentProvider.KEY_TASK);
        todoItems.clear();
        while(data.moveToNext()){
            ToDoItem newItem = new ToDoItem(data.getString(keyTaskIndex));
            todoItems.add(newItem);
        }
        aa.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<Cursor> loader) {

    }
}
