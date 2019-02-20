package com.example.administrator.sqlitetest;

import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.DataOutput;

public class MainActivity extends AppCompatActivity {

        private MyDatabaseHelper myDatabaseHelper;
        final  private  int REQUEST_CODE_WRITE_EXTERNAL_STORAGE = 321;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int hasWriteExternalStoragePermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWriteExternalStoragePermission != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_WRITE_EXTERNAL_STORAGE);
            return;
        }
        myDatabaseHelper = new MyDatabaseHelper(this,"/sdcard/Android/data/BookStore.db",null,8);
        Button button = (Button)findViewById(R.id.create_book);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDatabaseHelper.getWritableDatabase();
            }
        });
        Button addDataButton = (Button)findViewById(R.id.add_data);
        addDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name","The Da vinci Code");
                values.put("author","Dan Brown");
                values.put("pages",454);
                values.put("price",16.96);
                db.insert("Book",null,values);
                values.clear();

                values.put("name","The Lost Symbol");
                values.put("author","Dan Brown");
                values.put("pages",500);
                values.put("price",19.95);
                db.insert("Book",null,values);
                Toast.makeText(MainActivity.this,"Book insert succeded",Toast.LENGTH_SHORT).show();
            }
        });
        Button updateDataButton = (Button)findViewById(R.id.update_data);
        updateDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();

                ContentValues values2 = new ContentValues();
                values2.put("name","The Da Vinci Code");
                db.update("book", values2, "name = ?", new String[]{"The Da vinci Code"});
                Toast.makeText(MainActivity.this,"Book update succeded",Toast.LENGTH_SHORT).show();

                ContentValues values = new ContentValues();
                values.put("price",10.99);
                db.update("Book",values,"name = ?", new String[]{"The Da Vinci Code"});
            }
        });

        Button queryDataButton = (Button)findViewById(R.id.qurey_data);
        queryDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
                Cursor cursor = db.query("Book",null,null,null,null,null,null);
                if (cursor.moveToFirst()) {
                    do {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d("MainActivity","book name is " + name );
                        Log.d("MainActivity","book author is " + author );
                        Log.d("MainActivity","book pages is " + pages );
                        Log.d("MainActivity","book price is " + price );
                    } while (cursor.moveToNext());
                }
                cursor.close();
            }
        });
    }

    @Override
    public  void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_WRITE_EXTERNAL_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this,"Write external storage granted.",Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Toast.makeText(MainActivity.this,"Write external storage denied.",Toast.LENGTH_SHORT).show();
                }
                break;
                default:
                    super.onRequestPermissionsResult(requestCode,permissions,grantResults);
                    break;
        }
    }
}
