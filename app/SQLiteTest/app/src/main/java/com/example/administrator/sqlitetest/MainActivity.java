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

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.io.DataOutput;
import java.util.List;

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

        Button button = (Button)findViewById(R.id.create_book);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.getDatabase();
                Toast.makeText(MainActivity.this,"table Book create succeded",Toast.LENGTH_SHORT).show();
            }
        });

        Button addDataButton = (Button)findViewById(R.id.add_data);
        addDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setName("The Lost Symbol");
                book.setAuthor("Dan Brown");
                book.setPages(510);
                book.setPrice(19.95);
                book.setPress("Unkonw");
                book.save();
            }
        });
        Button updateDataButton = (Button)findViewById(R.id.update_data);
        updateDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Book bookUpdate = LitePal.find(Book.class,1);
               bookUpdate.setPrice(10.99);
               bookUpdate.save();
            }
        });

        Button queryDataButton = (Button)findViewById(R.id.qurey_data);
        queryDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> books = LitePal.findAll(Book.class);
                for (Book book: books) {
                    Log.d("MainActivity","book name is " + book.getName() );
                    Log.d("MainActivity","book author is " + book.getAuthor() );
                    Log.d("MainActivity","book pages is " + book.getPages() );
                    Log.d("MainActivity","book price is " + book.getPress() );
                }
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
