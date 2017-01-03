package function;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.chenjunfan.gsgj.R;
import com.example.chenjunfan.gsgj.StatisticsActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * Created by chenjunfan on 2017/1/3.
 */

public class PieChartManger {
    private GennameAndNumAndroid[] array;
    Activity activity;
    Context context;
    Handler handler;
    String account;

    public PieChartManger(Activity activity, Context context, Handler handler, String account) {
        this.activity = activity;
        this.context = context;
        this.handler = handler;
        this.account = account;
    }

    public void getdate() {
        final ProgressDialog prodialog = new ProgressDialog(context);
        prodialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        prodialog.setIndeterminate(true);
        prodialog.setCancelable(false);
        prodialog.setMessage("正在刷新~");
        prodialog.show();
        AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                HttpURLConnection connection = null;
                try {
                    String Url;
                    Url = "http://" + context.getResources().getText(R.string.ip_address) + "/VenueManager/ShowPieServlet" + "?android=true&accountOrName="+account+"&actionCode=find" ;
                    Log.i("url", Url);
                    Log.d("decode", URLDecoder.decode(Url,"UTF-8"));
                    URL url = new URL(Url);
                    connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("Accept-Charset", "UTF-8");
                    connection.setRequestProperty("contentType", "UTF-8");
                    connection.setReadTimeout(1000);
                    connection.setConnectTimeout(3000);
                    InputStream in = connection.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while((line = br.readLine())!=null)
                    {
                        response.append(line);
                    }
                    Log.d("InputStream", response.toString());
                    if (response.toString().equals("null_error")) {
                        Message msg = new Message();
                        msg.obj = "账户不存在！";
                        prodialog.cancel();
                        handler.sendMessage(msg);

                    } else if (response.toString().equals("not_found")) {
                        Message msg = new Message();
                        msg.obj = "密码错误！";
                        prodialog.cancel();
                        handler.sendMessage(msg);
                    } else {
                        Gson gson = new Gson();
                        array = gson.fromJson(response.toString(), new TypeToken<GennameAndNumAndroid[]>() {
                        }.getType());
                        String []name = new String[4];
                        float []num = new float[4];
                        for(int i =0 ; i<4;i++)
                        {
                            name[i] = array[i].getName();
                            num[i] = array[i].getNum();
                        }
                        Intent intent = new Intent(activity, StatisticsActivity.class);
                        intent.putExtra("name",name);
                        intent.putExtra("num",num);
                        activity.startActivity(intent);
                        Message msg = new Message();
                        msg.obj = "success";
                        handler.sendMessage(msg);
                        prodialog.cancel();
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    Message msg = new Message();
                    msg.obj = "获取数据失败，请检查网络设置或稍后再试";
                    handler.sendMessage(msg);
                    prodialog.cancel();
                }
                finally {
                    connection.disconnect();
                }




                return null;
            }


        };
        asyncTask.execute();


    }
}
