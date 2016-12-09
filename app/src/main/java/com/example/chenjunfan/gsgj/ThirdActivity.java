package com.example.chenjunfan.gsgj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import function.ItemBean;
import function.MyAdapter;

/**
 * Created by chenjunfan on 2016/12/9.
 */

public class ThirdActivity extends Activity implements View.OnClickListener {
    ImageView reIV;
    TextView titleTV;
    ListView listView;
    MyAdapter myAdapter;
    String id;
    private List<ItemBean> itemBeanList =new ArrayList<ItemBean>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Intent intent = getIntent();
        id=intent.getStringExtra("id");
        Init(id);
        reIV.setOnClickListener(this);
        myAdapter = new MyAdapter(ThirdActivity.this,itemBeanList);
        listView.setAdapter(myAdapter);

    }

    public void Init(String id)
    {

        reIV = (ImageView) findViewById(R.id.IV_td_re);
        titleTV = (TextView) findViewById(R.id.TV_td_title);
        listView = (ListView) findViewById(R.id.LV_td_main);
        titleTV.setText(id);

        for(int i=0;i<10;i++)
        {
            itemBeanList.add(new ItemBean("东区"+id+i,i+""));
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.IV_td_re:
                finish();
                break;
        }
    }
}
