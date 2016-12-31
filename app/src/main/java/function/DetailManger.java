package function;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.chenjunfan.gsgj.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by chenjunfan on 2016/12/16.
 */

public class DetailManger {
    List<Integer> venuenum;
    String result;
    public void searchNum(final String date, final String time,final String genname, final Context context, final Handler handler)
    {
        final ProgressDialog prodialog=new ProgressDialog(context);
        prodialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        prodialog.setIndeterminate(true);
        prodialog.setCancelable(false);
        prodialog.setMessage("正在查询可用场馆");
        prodialog.show();


        final Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                URLConnection conn = null;
                try {
                    String Url;
                    Url = "http://" + context.getResources().getText(R.string.ip_address) + ":8080/VenueManager/GetAreaServlet" + "?date="+date+"&time="+time+"&venuename="+ URLEncoder.encode(genname,"UTF-8") ;
                    Log.i("url", Url);
                    URL url = new URL(Url);
                    conn = url.openConnection();
                    conn.setRequestProperty("Accept-Charset", "UTF-8");
                    conn.setRequestProperty("contentType", "UTF-8");
                    conn.setReadTimeout(1000);
                    conn.setConnectTimeout(3000);
                    InputStreamReader reader = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader br = new BufferedReader(reader);
                    String str = br.readLine();
                    Log.d("InputStream",str);
                    if(str.equals("all_venue_is_full"))
                    {
                        Message msg = new Message();
                        msg.what=1;
                        msg.obj = str;
                        prodialog.cancel();
                        handler.sendMessage(msg);

                    }
                    else if(str.equals("not_found"))
                    {
                        Message msg = new Message();
                        msg.obj = "";
                        prodialog.cancel();
                        handler.sendMessage(msg);
                    }
                    else {
                        Gson gson = new Gson();
                        venuenum= gson.fromJson(str, new TypeToken<List<Integer>>() {
                        }.getType());
                        Message msg = new Message();
                        msg.what=1;
                        msg.obj=venuenum;
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


            }
        });
        t.start();
    }

    public void reserve(final String date,final String time,final String venue,final String num,final String account,final Context context,final Handler handler)
    {
        final ProgressDialog prodialog=new ProgressDialog(context);
        prodialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        prodialog.setIndeterminate(true);
        prodialog.setCancelable(false);
        prodialog.setMessage("正在通信");

        final Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String Url;
                    Url = "http://" + context.getResources().getText(R.string.ip_address) + ":8080/VenueManager/ReserveServlet" + "?account="+account+"&venuename="+URLEncoder.encode(venue,"UTF-8")+"&date="+date+"&time="+time+"&area="+num ;
                    Log.i("url", Url);
                    URL url = new URL(Url);
                    URLConnection conn = url.openConnection();
                    conn.setRequestProperty("Accept-Charset", "UTF-8");
                    conn.setRequestProperty("contentType", "UTF-8");
                    conn.setReadTimeout(1000);
                    conn.setConnectTimeout(3000);
                    InputStreamReader reader = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader br = new BufferedReader(reader);
                    String str = br.readLine();
                    Log.d("InputStream",str);
                    if(str.equals(""))
                    {
                        Message msg = new Message();
                        msg.obj = "";
                        prodialog.cancel();
                        handler.sendMessage(msg);

                    }
                    else if(str.equals(""))
                    {
                        Message msg = new Message();
                        msg.obj = "";
                        prodialog.cancel();
                        handler.sendMessage(msg);
                    }
                    else {
                        Message msg = new Message();
                        msg.what=2;
                        msg.obj=str;
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


            }
        });
        t.start();
    }

}
