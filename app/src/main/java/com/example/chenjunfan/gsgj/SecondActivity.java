package com.example.chenjunfan.gsgj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.health.PackageHealthStats;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by chenjunfan on 2016/12/7.
 */

public class SecondActivity extends Activity implements View.OnClickListener {
    ImageView reIV;
    TextView titleTV;
    ListView mainLV;
    ArrayList<String> itemlist;
    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id",0);
        Init(id);
        reIV.setOnClickListener(this);
        mainLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(SecondActivity.this, itemlist.get(i).toString(),Toast.LENGTH_SHORT).show();
            }
        });


    }
    public void Init(int id)
    {


        reIV = (ImageView) findViewById(R.id.IV_sd_re);
        titleTV = (TextView) findViewById(R.id.TV_sd_title);
        mainLV = (ListView) findViewById(R.id.LV_sd_main);

        if(id == R.id.IV_fm_tiyu) {
            itemlist = new ArrayList<String>();

            itemlist.add("足球");
            itemlist.add("排球");
            itemlist.add("篮球");
            itemlist.add("网球");
            itemlist.add("羽毛球");
            itemlist.add("乒乓球");

            adapter = new ArrayAdapter(this, R.layout.item_secondmenu, R.id.TV_ims_title, itemlist);
            mainLV.setAdapter(adapter);
        }

        switch (id)
        {
            case R.id.IV_fm_jiaoshi:
                titleTV.setText("教室");
                break;
            case R.id.IV_fm_shiyan:
                titleTV.setText("实验室");
                break;
            case R.id.IV_fm_tiyu:
                titleTV.setText("体育馆");
                break;
            case R.id.IV_fm_tushu:
                titleTV.setText("图书馆");
                break;
        }

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
