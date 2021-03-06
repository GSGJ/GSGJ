package com.example.chenjunfan.gsgj;

import java.util.ArrayList;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;

import function.GennameAndNumAndroid;

public class StatisticsActivity extends Activity {

    private PieChart mChart;
    private String[] name;
    private float[] num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        Intent intent = getIntent();
        name=intent.getStringArrayExtra("name");
        num=intent.getFloatArrayExtra("num");

        mChart = (PieChart) findViewById(R.id.chart);
        if(name != null&&num!=null) {
            PieData mPieData = getPieData(4,num,name);
            showChart(mChart, mPieData);
        }
    }

    private void showChart(PieChart pieChart, PieData pieData) {
//        pieChart.setHoleColorTransparent(true);

        pieChart.setHoleRadius(60f);  //半径
        pieChart.setTransparentCircleRadius(64f); // 半透明圈
        //pieChart.setHoleRadius(0)  //实心圆

        pieChart.setDescription("");
//        pieChart.setDescriptionTextSize(150);


        // mChart.setDrawYValues(true);
        pieChart.setDrawCenterText(true);  //饼状图中间可以添加文字

        pieChart.setDrawHoleEnabled(true);

        pieChart.setRotationAngle(90); // 初始旋转角度

        // draws the corresponding description value into the slice
        // mChart.setDrawXValues(true);

        // enable rotation of the chart by touch
        pieChart.setRotationEnabled(true); // 可以手动旋转

        // display percentage values
        pieChart.setUsePercentValues(true);  //显示成百分比
        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener
//      mChart.setOnChartValueSelectedListener(this);
        // mChart.setTouchEnabled(false);

//      mChart.setOnAnimationListener(this);

        pieChart.setCenterText("个人预定历史统计图\n（单位：%）");  //饼状图中间的文字

        //设置数据
        pieChart.setData(pieData);
        // undo all highlights
//      pieChart.highlightValues(null);
//      pieChart.invalidate();

        Legend mLegend = pieChart.getLegend();  //设置比例图
        mLegend.setPosition(LegendPosition.ABOVE_CHART_RIGHT);  //最右边显示
      //mLegend.setForm(Legend.LegendForm.LINE);  //设置比例图的形状，默认是方形
        mLegend.setXEntrySpace(40f);
        mLegend.setYEntrySpace(100f);
        pieChart.animateXY(1000, 1000);  //设置动画
        // mChart.spin(2000, 0, 360);
    }

    /**
     *
     * @param count 分成几部分
     *
     */
    private PieData getPieData(int count,float[] num,String[] name) {

        ArrayList<String> xValues = new ArrayList<String>();  //xVals用来表示每个饼块上的内容

//
//        xValues.add(str1);
//        xValues.add(str2);
//        xValues.add(str3);
//        xValues.add(str4);//饼块上显示成Quarterly1, Quarterly2, Quarterly3, Quarterly4

        ArrayList<Entry> yValues = new ArrayList<Entry>();  //yVals用来表示封装每个饼块的实际数据

        // 饼图数据
        /**
         * 将一个饼形图分成四部分， 四部分的数值比例为14:14:34:38
         * 所以 14代表的百分比就是14%
         */
//        float quarterly1 = num1;
//        float quarterly2 = num2;
//        float quarterly3 = num3;
//        float quarterly4 = num4;

//        yValues.add(new Entry(quarterly1, 0));
//        yValues.add(new Entry(quarterly2, 1));
//        yValues.add(new Entry(quarterly3, 2));
//        yValues.add(new Entry(quarterly4, 3));
        for(int i=0;i<num.length;i++)
        {
            if(num[i]==0)
                continue;
            xValues.add(name[i]);
            yValues.add(new Entry(num[i],i));
        }

        //y轴的集合
        PieDataSet pieDataSet = new PieDataSet(yValues, ""/*显示在比例图上*/);
        pieDataSet.setSliceSpace(0f); //设置个饼状图之间的距离

        ArrayList<Integer> colors = new ArrayList<Integer>();

        // 饼图颜色
        colors.add(Color.rgb(249,194,111));
        colors.add(Color.rgb(160,216,239));
        colors.add(Color.rgb(244,180,208));
        colors.add(Color.rgb(237,241,187));

        pieDataSet.setColors(colors);
        pieDataSet.setValueTextSize(13);



        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float px = 5 * (metrics.densityDpi / 160f);
        pieDataSet.setSelectionShift(px); // 选中态多出的长度

        PieData pieData = new PieData(xValues, pieDataSet);

        return pieData;
    }
}
