package com.example.hp.lab4;

/**
 * Created by HP on 2016/10/14.
 */
public class Fruit {
    private int resourceId;
    private String name;

    public Fruit(int resourceId,String name ){
        this.resourceId=resourceId;
        this.name=name;
    }
    public String getName(){
        return name;
    }

    public int getResourceId(){
        return resourceId;
    }

}
