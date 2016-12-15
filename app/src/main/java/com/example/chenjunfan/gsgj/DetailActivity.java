package com.example.chenjunfan.gsgj;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import function.VenueTable;

/**
 * Created by chenjunfan on 2016/12/14.
 */

public class DetailActivity extends Activity implements View.OnClickListener, PopupWindow.OnDismissListener {
    TextView totalnumTV,titleTV,locTV,noticeTV,avanumTV,yuyuebtTV;
    FrameLayout picFL;
    ImageView reIV;
    LayoutInflater inflater;
    View popview;
    PopupWindow pop;
    VenueTable venue = new VenueTable();
    String date,time,num;
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
        venue = (VenueTable) intent.getSerializableExtra("venue");

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
                Button nextBT;


                dateRG = (RadioGroup) popview.findViewById(R.id.RG_popf_date);
                for(int i=0; i<3; i++)
                {
                    RadioButton tempButton = new RadioButton(this);
                    // 设置RadioButton的背景图片
                            // 设置按钮的样式
                    tempButton.setTextColor(Color.BLACK);
                    tempButton.setPadding(80, 0, 0, 0);                 // 设置文字距离按钮四周的距离
                    tempButton.setText("12月 " + i+1 +"日");
                    tempButton.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
                    dateRG.addView(tempButton,800,150);
                }
                dateRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        // TODO Auto-generated method stub
                        RadioButton tempButton = (RadioButton)popview.findViewById(checkedId); // 通过RadioGroup的findViewById方法，找到ID为checkedID的RadioButton
                        date=tempButton.getText().toString();
                    }
                });



                timeRG = (RadioGroup) popview.findViewById(R.id.RG_popf_time);
                for(int i=0; i<4; i++)
                {
                    RadioButton tempButton = new RadioButton(this);
                    // 设置RadioButton的背景图片
                    // 设置按钮的样式
                    tempButton.setTextColor(Color.BLACK);
                    tempButton.setPadding(80, 0, 0, 0);                 // 设置文字距离按钮四周的距离
                    tempButton.setText("时间段      ：" + i+1);
                    tempButton.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
                    timeRG.addView(tempButton,800,150);
                }
                dateRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        // TODO Auto-generated method stub
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
                pop.dismiss();
                popview = inflater.inflate(R.layout.popupwindow_second,null);
                pop = new PopupWindow(popview, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,false);
                pop.setBackgroundDrawable(new BitmapDrawable());
                backgroundAlpha(0.5f);
                pop.setOutsideTouchable(true);
                pop.setFocusable(true);
                pop.setOnDismissListener(this);

                RadioGroup numRG = (RadioGroup) popview.findViewById(R.id.RG_pops_num);
                for(int i=0; i<3; i++)
                {
                    RadioButton tempButton = new RadioButton(this);
                    // 设置RadioButton的背景图片
                    // 设置按钮的样式
                    tempButton.setTextColor(Color.BLACK);
                    tempButton.setPadding(80, 0, 0, 0);                 // 设置文字距离按钮四周的距离
                    tempButton.setText("场地        :" + (i+1) +"");
                    tempButton.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
                    numRG.addView(tempButton,800,150);
                }
                numRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        // TODO Auto-generated method stub
                        RadioButton tempButton = (RadioButton)popview.findViewById(checkedId); // 通过RadioGroup的findViewById方法，找到ID为checkedID的RadioButton
                        num=tempButton.getText().toString();
                    }
                });

                Button sureBT = (Button) popview.findViewById(R.id.BT_pops_sure);
                sureBT.setOnClickListener(this);
                pop.showAtLocation(popview, Gravity.CENTER_HORIZONTAL,0,0);
                break;
            case R.id.BT_pops_sure:
                pop.dismiss();
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
    }
}
