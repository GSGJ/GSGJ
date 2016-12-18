package com.example.chenjunfan.gsgj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import function.MySecondAdapter;

/**
 * Created by chenjunfan on 2016/12/7.
 */

public class SecondActivity extends Activity implements View.OnClickListener {
    ImageView reIV;
    TextView titleTV;
    ListView mainLV;
    ArrayList<String> itemlist;
    MySecondAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id",0);
        init();
        reIV.setOnClickListener(this);
        mainLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(SecondActivity.this,ThirdActivity.class);
                intent.putExtra("id",itemlist.get(i));
                startActivity(intent);
            }
        });


    }
    public void init()
    {


        reIV = (ImageView) findViewById(R.id.IV_sd_re);
        titleTV = (TextView) findViewById(R.id.TV_sd_title);
        mainLV = (ListView) findViewById(R.id.LV_sd_main);
        titleTV.setText("体育馆");

            itemlist = new ArrayList<String>();

            itemlist.add("足球");
            itemlist.add("排球");
            itemlist.add("篮球");
            itemlist.add("网球");
            itemlist.add("羽毛球");
            itemlist.add("乒乓球");

            adapter = new MySecondAdapter(this,itemlist);
            mainLV.setAdapter(adapter);
        }






    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.IV_sd_re:
                finish();
                break;
        }
    }
}
