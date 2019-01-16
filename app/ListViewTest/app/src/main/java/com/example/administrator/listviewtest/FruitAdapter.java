package com.example.administrator.listviewtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FruitAdapter extends ArrayAdapter<Fruit> {
    private int resourceId;
    public FruitAdapter( Context context, int textViesResourced, List<Fruit> objects) {
        super(context, textViesResourced, objects);
        resourceId = textViesResourced;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Fruit fruit = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView fruitIamge = (ImageView)view.findViewById(R.id.fruit_image);
        TextView fruitName = (TextView)view.findViewById(R.id.fruit_name);
        fruitIamge.setImageResource(fruit.getImageId());
        fruitName.setText(fruit.getName());
        return view;
    }
}
