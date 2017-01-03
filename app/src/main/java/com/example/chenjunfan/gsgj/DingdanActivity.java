package com.example.chenjunfan.gsgj;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import function.DingdanManger;
import function.MyCalendar;
import function.MyDingdanAdapter;
import function.PersonalInfoTable;

/**
 * Created by chenjunfan on 2016/12/19.
 */

public class DingdanActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener, PopupWindow.OnDismissListener {
    ImageView reBT;
    TextView  titleTV;
    ListView mList;
    String type,account,num;
    List <PersonalInfoTable> itemList;
    MyDingdanAdapter adapter;
    LayoutInflater inflater = null;
    String date,time,venueName;
    View popview = null;
    PopupWindow pop= null;

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

        if(type.equals("当前预约")){
            Toast.makeText(this, "提示：点击项目可取消预约", Toast.LENGTH_SHORT).show();
            mList.setOnItemClickListener(this);
        }
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
            else if(msg.what==2)
            {
                if(msg.obj.equals("success")) {
                    Toast.makeText(DingdanActivity.this, "取消预约成功,请手动删除所添加的日历项", Toast.LENGTH_LONG).show();
                    Intent i = new Intent();
                    ComponentName cn = null;
                    if(Integer.parseInt (Build.VERSION.SDK ) >=8){
                        cn = new ComponentName("com.android.calendar","com.android.calendar.LaunchActivity");
                    }
                    else{
                        cn = new ComponentName("com.google.android.calendar","com.android.calendar.LaunchActivity");
                    }
                    i.setComponent(cn);
                    startActivity(i);
                }
                else
                    Toast.makeText(DingdanActivity.this,"取消预约失败，请重试",Toast.LENGTH_SHORT).show();
                finish();
                pop.dismiss();
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
            case R.id.BT_popq_yes:
                new DingdanManger().quxiaoDingdan(date,time,venueName,account,DingdanActivity.this,handler);
                break;
            case R.id.BT_popq_no:
                pop.dismiss();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        inflater = LayoutInflater.from(this);
        popview = inflater.inflate(R.layout.popupwindows_quxiaodingdan,null);
        Button yesBT,noBT;
        TextView titleTV;
        yesBT = (Button) popview.findViewById(R.id.BT_popq_yes);
        noBT = (Button) popview.findViewById(R.id.BT_popq_no);
        titleTV = (TextView) popview.findViewById(R.id.TV_popq_title);
        date = itemList.get(i).getDate();
        time = itemList.get(i).getTime();
        num=itemList.get(i).getNum();
        venueName = itemList.get(i).getVenueName();

        titleTV.setText("您想要取消\n<"+itemList.get(i).getVenueName()+">的预约吗？");

        yesBT.setOnClickListener(this);
        noBT.setOnClickListener(this);

        pop = new PopupWindow(popview, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,false);

        pop.setBackgroundDrawable(new BitmapDrawable());
        backgroundAlpha(0.5f);
        pop.setOutsideTouchable(true);
        pop.setFocusable(true);
        pop.setOnDismissListener(this);
        pop.showAtLocation(popview, Gravity.CENTER, 0, 0);

    }


    public void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }

    @Override
    public void onDismiss() {
        backgroundAlpha(1f);
    }
}
