package com.example.chenjunfan.gsgj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import function.ItemBean;
import function.MyAdapter;
import function.ThirdMenuManager;
import function.VenueTable;

/**
 * Created by chenjunfan on 2016/12/9.
 */

public class ThirdActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {
    ImageView reIV;
    TextView titleTV;
    ListView listView;
    MyAdapter myAdapter;
    String id;
    ThirdMenuManager tmManger = new ThirdMenuManager();
    Activity activity = this;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Intent intent = getIntent();
        id=intent.getStringExtra("id");
        init(id);
        reIV.setOnClickListener(this);
        myAdapter = new MyAdapter(ThirdActivity.this,tmManger.getItemBeanList());
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(this);

    }

    public void init(String id)
    {

        reIV = (ImageView) findViewById(R.id.IV_td_re);
        titleTV = (TextView) findViewById(R.id.TV_td_title);
        listView = (ListView) findViewById(R.id.LV_td_main);
        titleTV.setText(id);

        tmManger.getListdate(titleTV.getText().toString(),ThirdActivity.this,handler);


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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        new VenueTable().getDetial(tmManger.getItemBeanList().get(i).getTitle(),ThirdActivity.this,activity,handler,tmManger.getItemBeanList().get(i).getNum());

    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            super.handleMessage(msg);

            if(msg.obj.toString().equals("success"))
            {
                myAdapter.notifyDataSetChanged();
            }
            else {

                String str = (String) msg.obj;

                Toast.makeText(ThirdActivity.this, str, Toast.LENGTH_SHORT).show();
            }


        }
    };
}
