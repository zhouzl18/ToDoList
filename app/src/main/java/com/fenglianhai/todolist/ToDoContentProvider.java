package com.fenglianhai.todolist;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

/**
 * Created by FengLianhai on 2015/5/22.
 */
public class ToDoContentProvider extends ContentProvider {

    public static final Uri CONTENT_URI = Uri.parse("content://com.fenglianhai.todoprovider/todoitems");

    public static final String KEY_ID = "_id";
    public static final String KEY_TASK = "task";
    public static final String KEY_CREATION_DATE = "creation_date";

    private MySQLiteOpenHelper myDBOpenHelper;

    private static final int ALLROWS = 1;
    private static final int SINGLE_ROW = 2;

    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.fenglianhai.todoprovider", "todoitems", ALLROWS);
        uriMatcher.addURI("com.fenglianhai.todoprovider", "todoitems/#", SINGLE_ROW);
    }



    @Override
    public boolean onCreate() {
        myDBOpenHelper = new MySQLiteOpenHelper(getContext(),
                MySQLiteOpenHelper.DATABASE_TABLE, null, MySQLiteOpenHelper.DATABASE_VERSION);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    private static class MySQLiteOpenHelper extends SQLiteOpenHelper{
        private static final String DATABASE_NAME = "todoDatabase.db";
        private static final String DATABASE_TABLE = "todoItemTable";
        private static final int DATABASE_VERSION = 1;

        private static final String DATABASE_CREATE = "create table " + DATABASE_TABLE + " (" +
                KEY_ID + " integer primary key autoincrement, " +
                KEY_TASK + " text not null, " +
                KEY_CREATION_DATE + " long);";

        public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w("TaskAdapter", "Upgrading from version " + oldVersion + " to " +
                    newVersion + " which will destroy all old data");
            //最简单的方式：删除旧表，创建一个新表
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }
}
