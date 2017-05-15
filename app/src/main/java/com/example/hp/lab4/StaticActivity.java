package com.example.hp.lab4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 2016/10/14.
 */
public class StaticActivity extends Activity {

    private List<Fruit> fruit_list =new ArrayList<Fruit>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.static_layout);


        final ListView list_id=(ListView) findViewById(R.id.listview);
        initFruit();
        final FruitAdapter fruitAdapter=new FruitAdapter(this,fruit_list);
        list_id.setAdapter(fruitAdapter);

        list_id.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent("com.example.lab4.staticreceiver");
                intent.putExtra("id",fruit_list.get(position).getResourceId());
                intent.putExtra("name",fruit_list.get(position).getName());
                sendBroadcast(intent);
            }
        });



    }

    void initFruit(){
        fruit_list.add(new Fruit(R.mipmap.apple,"Apple"));
        fruit_list.add(new Fruit(R.mipmap.banana,"Banana"));
        fruit_list.add(new Fruit( R.mipmap.cherry,"Cherry"));
        fruit_list.add(new Fruit(R.mipmap.coco,"Coco"));
        fruit_list.add(new Fruit( R.mipmap.kiwi,"Kiwi"));
        fruit_list.add(new Fruit( R.mipmap.orange,"Orange"));
        fruit_list.add(new Fruit(R.mipmap.pear,"Pear"));
        fruit_list.add(new Fruit( R.mipmap.strawberry,"Strawberry"));
        fruit_list.add(new Fruit( R.mipmap.watermelon,"Watermelon"));
    }
}
