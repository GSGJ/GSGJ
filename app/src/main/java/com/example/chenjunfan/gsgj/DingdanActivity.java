package com.example.chenjunfan.gsgj;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import function.DingdanManger;
import function.MyDingdanAdapter;
import function.PersonalInfoTable;

/**
 * Created by chenjunfan on 2016/12/19.
 */

public class DingdanActivity extends Activity implements View.OnClickListener {
    ImageView reBT;
    TextView  titleTV;
    ListView mList;
    String type,account;
    List <PersonalInfoTable> itemList;
    MyDingdanAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dingdan);
        init();

        DingdanManger dingdanManger = new DingdanManger();
        dingdanManger.getDingdan(type,account,DingdanActivity.this,handler);
    }
    private void init()
    {
        titleTV = (TextView) findViewById(R.id.TV_dd_title);
        reBT = (ImageView) findViewById(R.id.IV_dd_re);
        mList = (ListView) findViewById(R.id.LV_dd_main);
        SharedPreferences pre = getSharedPreferences("currentUser", MODE_PRIVATE);
        account=pre.getString("account", "");
        reBT.setOnClickListener(this);

        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        titleTV.setText(type);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(msg.what==1) {
                itemList = (List<PersonalInfoTable>) msg.obj;
                adapter = new MyDingdanAdapter(DingdanActivity.this,itemList);
                mList.setAdapter(adapter);
            }

        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.IV_dd_re:
                finish();
                break;
        }
    }
}
