package com.example.administrator.listviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Fruit> fruitList = new ArrayList<>();
    private String[] data = {
            "Apple","Banana","Orange","Watermelon",
            "Pear", "Grape", "Pineapple","Strawberry", "Cherry","Mango",
            "Apple","Banana","Orange","Watermelon",
            "Pear", "Grape", "Pineapple","Strawberry", "Cherry","Mango"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruit();
        FruitAdapter adapter = new FruitAdapter(MainActivity.this,R.layout.fruit_item,fruitList);
        ListView listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit = fruitList.get(position);
                Toast.makeText(MainActivity.this,fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void initFruit(){
        for (int i = 0; i < 2; i++){
            Fruit apple = new Fruit("Apple",R.mipmap.app_pic);
            fruitList.add(apple);
            Fruit bananna = new Fruit("Bananna",R.mipmap.banana_pic);
            fruitList.add(bananna);
            Fruit orange = new Fruit("Orange",R.mipmap.orange_pic);
            fruitList.add(orange);
            Fruit watermelon = new Fruit("Watermelon",R.mipmap.watermelon_pic);
            fruitList.add(watermelon);
            Fruit pear = new Fruit("Pear",R.mipmap.pear_pic);
            fruitList.add(pear);
            Fruit graper = new Fruit("Grape",R.mipmap.grape_pic);
            fruitList.add(graper);
            Fruit pineapple = new Fruit("Pineapple",R.mipmap.pineapple_pic);
            fruitList.add(pineapple);
            Fruit strawberry = new Fruit("Strawberrt",R.mipmap.strawberry_pic);
            fruitList.add(strawberry);
            Fruit cherry = new Fruit("Cherry",R.mipmap.cherry_pic);
            fruitList.add(cherry);
            Fruit mangeo = new Fruit("Cherry",R.mipmap.mango_pic);
            fruitList.add(mangeo);
        }
    }
}
