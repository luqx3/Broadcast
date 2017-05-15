package com.example.hp.lab4;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by HP on 2016/10/14.
 */
public class DynamicActivity extends Activity {

    private boolean isRegistered = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dynamic_layout);
        final Button register=(Button)findViewById(R.id.register);
        final Button send=(Button)findViewById(R.id.send);
        final EditText editText=(EditText) findViewById(R.id.edittext);
        final DynamicReceiver dynamicReceiver=new DynamicReceiver();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DynamicActivity.this.isRegistered){
                    register.setText("Register Broadcast");//设置register按键的内容
                    unregisterReceiver(dynamicReceiver);//注销广播
                    DynamicActivity.this.isRegistered=false;
                }
                else{
                    register.setText("Unregister Broadcast");
                    //注册广播，以及广播的action
                    registerReceiver(dynamicReceiver,new IntentFilter("com.example.lab4.dynamicreceiver"));
                    DynamicActivity.this.isRegistered=true;

                }
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isRegistered){
                    Intent intent=new Intent("com.example.lab4.dynamicreceiver");
                    Bundle bundle=new Bundle();
                    bundle.putString("message",editText.getText().toString());
                    intent.putExtras(bundle);
                    DynamicActivity.this.sendBroadcast(intent);
                }

            }
        });
    }
}
