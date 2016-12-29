package com.example.chenjunfan.gsgj;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.CalendarContract;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import function.DetailManger;
import function.MyCalendar;
import function.VenueInfoTable;
import function.VenueTable;

/**
 * Created by chenjunfan on 2016/12/14.
 */

public class DetailActivity extends Activity implements View.OnClickListener, PopupWindow.OnDismissListener {
    TextView totalnumTV,titleTV,locTV,noticeTV,avanumTV,yuyuebtTV;
    FrameLayout picFL;
    boolean checkflag1=false,checkflag2=false,checkflag3=false;
    ImageView reIV;
    LayoutInflater inflater;
    View popview;
    PopupWindow pop;
    DetailManger detailManger = new DetailManger();
    VenueTable venue = new VenueTable();
    String date,time,num;
    Date currentdate;
    ArrayAdapter<String> dateadapter;
    private List<String> datelist = new ArrayList<String>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        init();

        yuyuebtTV.setOnClickListener(this);
        reIV.setOnClickListener(this);
    }
    void init()
    {
        Intent intent = getIntent();
        VenueInfoTable info = (VenueInfoTable) intent.getSerializableExtra("info");
        venue = info.venueTable;
        currentdate= info.dt;

        totalnumTV = (TextView) findViewById(R.id.TV_dt_totalnum);
        titleTV = (TextView) findViewById(R.id.TV_dt_title);
        locTV = (TextView) findViewById(R.id.TV_dt_loc);
        noticeTV = (TextView) findViewById(R.id.TV_dt_notice);
        avanumTV = (TextView) findViewById(R.id.TV_dt_avanum);
        yuyuebtTV = (TextView) findViewById(R.id.TV_dt_yuyuebt);
        picFL = (FrameLayout) findViewById(R.id.FL_dt_pic);
        reIV = (ImageView) findViewById(R.id.IV_dt_re);

        totalnumTV.setText("总场馆数："+venue.getVenue_sumarea());
        titleTV.setText(venue.getVenue_name());
        locTV.setText("位置："+venue.getVenue_addr());
        noticeTV.setText(venue.getVenue_notice());
        avanumTV.setText("可预约数:"+intent.getStringExtra("avanum"));
    }

    @Override



    public void onClick(View view) {



        switch (view.getId())
        {


            case R.id.TV_dt_yuyuebt:

                inflater = LayoutInflater.from(this);
                popview = inflater.inflate(R.layout.popupwindow_first,null);

                pop = new PopupWindow(popview, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,false);

                pop.setBackgroundDrawable(new BitmapDrawable());
                backgroundAlpha(0.5f);
                pop.setOutsideTouchable(true);
                pop.setFocusable(true);
                RadioGroup dateRG,timeRG;
                final Button nextBT;


                dateRG = (RadioGroup) popview.findViewById(R.id.RG_popf_date);
                for(int i=0; i<3; i++)
                {
                    RadioButton tempButton = new RadioButton(this);
                    // 设置RadioButton的背景图片
                            // 设置按钮的样式
                                // 设置文字距离按钮四周的距离
                    SimpleDateFormat df=new SimpleDateFormat("yyyy年MM月dd日");
                    buttonStyle(tempButton,df.format(new Date(currentdate.getTime() + (i+1) * 24 * 60 * 60 * 1000)));
                    dateRG.addView(tempButton,800,150);
                }
                dateRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        // TODO Auto-generated method stub
                        checkflag1=true;
                        RadioButton tempButton = (RadioButton)popview.findViewById(checkedId); // 通过RadioGroup的findViewById方法，找到ID为checkedID的RadioButton
                        SimpleDateFormat df=new SimpleDateFormat("yyyy年MM月dd日");
                        Date temptime = new Date();
                        try {
                            temptime=df.parse(tempButton.getText().toString());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        df = new SimpleDateFormat("yyyy-MM-dd");
                        date = df.format(temptime);


                    }
                });


                /*时间段RadioGroup:*/

                timeRG = (RadioGroup) popview.findViewById(R.id.RG_popf_time);
                for(int i=0; i<1; i++)
            {
                RadioButton tempButton = new RadioButton(this);
                // 设置RadioButton的背景图片
                // 设置按钮的样式
                buttonStyle(tempButton, "8:00-10:00");
                timeRG.addView(tempButton, 800, 150);
                RadioButton tempButton2 = new RadioButton(this);
                buttonStyle(tempButton2, "10:00-12:00");
                timeRG.addView(tempButton2, 800, 150);
                RadioButton tempButton3 = new RadioButton(this);
                buttonStyle(tempButton3, "14:00-16:00");
                timeRG.addView(tempButton3, 800, 150);
                RadioButton tempButton4 = new RadioButton(this);
                buttonStyle(tempButton4, "16:00-18:00");
                timeRG.addView(tempButton4, 800, 150);
            }

                timeRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        // TODO Auto-generated method stub
                        checkflag2=true;
                        RadioButton tempButton = (RadioButton)popview.findViewById(checkedId); // 通过RadioGroup的findViewById方法，找到ID为checkedID的RadioButton
                        time = tempButton.getText().toString();


                    }
                });

                nextBT = (Button) popview.findViewById(R.id.BT_popf_next);
                nextBT.setOnClickListener(this);
                pop.showAtLocation(popview, Gravity.CENTER_HORIZONTAL,0,0);
                pop.setOnDismissListener(this);
                break;
            case R.id.IV_dt_re:
                finish();
                break;
            case R.id.BT_popf_next:
                if(checkflag1!=true&&checkflag2 != true)
                {
                    Toast.makeText(DetailActivity.this,"请选择日期和时间", Toast.LENGTH_SHORT).show();
                }
                else
                    detailManger.searchNum(date,time,titleTV.getText().toString(),DetailActivity.this,handler);
                break;
            case R.id.BT_pops_sure:
                if(checkflag3==false)
                {
                    Toast.makeText(DetailActivity.this,"请选择场地编号：", Toast.LENGTH_SHORT).show();
                }
                else {
                    SharedPreferences pre = getSharedPreferences("currentUser", MODE_PRIVATE);
                    detailManger.reserve(date, time, titleTV.getText().toString(), num, pre.getString("account", ""), DetailActivity.this, handler);
                }
                break;




        }

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
        checkflag1=false;
        checkflag2=false;
        checkflag3=false;
    }

    public RadioButton buttonStyle(RadioButton tempButton,String str)
    {
        tempButton.setTextColor(Color.BLACK);
        tempButton.setPadding(80, 0, 0, 0);                 // 设置文字距离按钮四周的距离
        tempButton.setText(str);
        tempButton.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
        return tempButton;
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1)
            {
                if(msg.obj.equals("all_venue_is_full"))
                {
                    Toast.makeText(DetailActivity.this,"该时间段已无空闲场馆，请选择其他时间段", Toast.LENGTH_LONG).show();
                    pop.dismiss();
                }
                else {
                    List<Integer> venuenum = (List<Integer>) msg.obj;
                    pop.dismiss();
                    popview = inflater.inflate(R.layout.popupwindow_second, null);
                    pop = new PopupWindow(popview, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
                    pop.setBackgroundDrawable(new BitmapDrawable());
                    backgroundAlpha(0.5f);
                    pop.setOutsideTouchable(true);
                    pop.setFocusable(true);
                    pop.setOnDismissListener(DetailActivity.this);

                    RadioGroup numRG = (RadioGroup) popview.findViewById(R.id.RG_pops_num);
                    for (int i = 0; i < venuenum.size(); i++) {
                        RadioButton tempButton = new RadioButton(DetailActivity.this);
                        buttonStyle(tempButton, "场地 " + venuenum.get(i));
                        numRG.addView(tempButton, 800, 150);
                    }
                    numRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            // TODO Auto-generated method stub
                            checkflag3=true;
                            RadioButton tempButton = (RadioButton) popview.findViewById(checkedId); // 通过RadioGroup的findViewById方法，找到ID为checkedID的RadioButton
                            num = tempButton.getText().toString().substring(3);
                        }
                    });

                    Button sureBT = (Button) popview.findViewById(R.id.BT_pops_sure);
                    sureBT.setOnClickListener(DetailActivity.this);
                    pop.showAtLocation(popview, Gravity.CENTER_HORIZONTAL, 0, 0);
                }
            }
            else if(msg.what==2)
            {
                if(msg.obj.equals("add_error"))
                {
                    Toast.makeText(DetailActivity.this,"预约失败！可能当前场地已被预约，请重新查看", Toast.LENGTH_LONG).show();
                    pop.dismiss();
                }
                else if(msg.obj.equals("add_success"))
                {
                    pop.dismiss();
                    Toast.makeText(DetailActivity.this, "预约成功，已添加日历提醒，请到系统日历查看详情", Toast.LENGTH_LONG).show();
                    finish();
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            if(time.equals("8:00-10:00"))
                            {
                                currentdate.setTime(currentdate.getTime()+8*60*60*1000);
                            }
                            else if (time.equals("10:00-12:00"))
                            {
                                currentdate.setTime(currentdate.getTime()+10*60*60*1000);
                            }
                            else if (time.equals("14:00-16:00"))
                            {
                                currentdate.setTime(currentdate.getTime()+14*60*60*1000);
                            }
                            else if (time.equals("16:00-18:00"))
                            {
                                currentdate.setTime(currentdate.getTime()+16*60*60*1000);
                            }

                            MyCalendar myCalendar = new MyCalendar();
                            myCalendar.addCalendarEvent(DetailActivity.this,"预约了"+titleTV.getText(),"馆室管家提醒您，您已预约了"+date+" "+time+"的"+titleTV.getText()+" 场地编号为:"+num+",请按时到场签到哟~ 我们将会在1个小时前闹钟提醒您，若要删除闹钟请编辑日历项",currentdate);
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
                    });
                    t.start();

                }
            }
            else
            {
                String str = (String) msg.obj;

                Toast.makeText(DetailActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        }
    };
}
