package com.example.hp.lab4;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by HP on 2016/10/14.
 */
public class FruitAdapter extends BaseAdapter {

    private Context context;
    private List<Fruit> listItem;

    public FruitAdapter(Context context,List<Fruit> listItem){
        this.context=context;
        this.listItem=listItem;
    }

    @Override
    public int getCount(){
        if(listItem==null){
            return 0;
        }
        return listItem.size();
    }
    //获得某个数据
    @Override
    public Fruit getItem(int i){
        if(listItem==null){
            return null;
        }
        return listItem.get(i);
    }
    //获得数据项的位置
    @Override
    public long getItemId(int i){
        return i;
    }

    @Override

    //获得数据项的布局样式，最重要的一个方法
    public View getView(int i, View view, ViewGroup viewGroup){
        if(view==null){
            //通过inflate的方法加载布局，
            view= LayoutInflater.from(context).inflate(R.layout.item,null);
        }
        //得到单项布局中textview的id
        ImageView fruit_image=(ImageView) view.findViewById(R.id.fruit_image);
        TextView fruit_name=(TextView) view.findViewById(R.id.fruit_name);

        //从数据列表中取出对应的数据并赋值给对应的textview
        fruit_image.setImageResource(listItem.get(i).getResourceId());
        fruit_name.setText(listItem.get(i).getName());
        return view;
    }


}
