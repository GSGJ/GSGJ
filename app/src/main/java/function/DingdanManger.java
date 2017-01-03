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
import java.util.List;

/**
 * Created by chenjunfan on 2016/12/19.
 */

public class DingdanManger {
    public void getDingdan(final String type, final String userName, final Context context, final Handler handler) {
        final ProgressDialog prodialog = new ProgressDialog(context);
        prodialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        prodialog.setIndeterminate(true);
        prodialog.setCancelable(false);
        prodialog.setMessage("正在查询可用场馆");
        prodialog.show();
        final Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String Url;
                    if (type.equals("当前预约"))
                        Url = "http://" + context.getResources().getText(R.string.ip_address) + "/VenueManager/personalBookInfoServlet" + "?type=new" + "&userName=" + userName;
                    else
                        Url = "http://" + context.getResources().getText(R.string.ip_address) + "/VenueManager/personalBookInfoServlet" + "?type=old" + "&userName=" + userName;
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
                    Log.d("InputStream", str);
                    if (str.equals("all_venue_is_full")) {
                        Message msg = new Message();
                        msg.what = 1;
                        msg.obj = str;
                        prodialog.cancel();
                        handler.sendMessage(msg);

                    } else if (str.equals("not_found")) {
                        Message msg = new Message();
                        msg.obj = "";
                        prodialog.cancel();
                        handler.sendMessage(msg);
                    } else {
                        Gson gson = new Gson();
                        List<PersonalInfoTable> venuelist = gson.fromJson(str, new TypeToken<List<PersonalInfoTable>>() {
                        }.getType());
                        Message msg = new Message();
                        msg.what = 1;
                        msg.obj = venuelist;
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

    public void quxiaoDingdan(final String date,final String time,final String venueName,final String user,final Context context,final Handler handler) {
        final ProgressDialog prodialog = new ProgressDialog(context);
        prodialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        prodialog.setIndeterminate(true);
        prodialog.setCancelable(false);
        prodialog.setMessage("正在取消预约");
        prodialog.show();
        final Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String Url;
                    Url = "http://" + context.getResources().getText(R.string.ip_address) + "/VenueManager/DeleteReserveServlet" + "?date="+date+"&time="+time+"&venueName="+ URLEncoder.encode(venueName,"UTF-8")+"&accountOrName="+user;
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
                    Log.d("InputStream", str);
                    if (str.equals("all_venue_is_full")) {
                        Message msg = new Message();
                        msg.what = 1;
                        msg.obj = str;
                        prodialog.cancel();
                        handler.sendMessage(msg);

                    } else if (str.equals("not_found")) {
                        Message msg = new Message();
                        msg.obj = "";
                        prodialog.cancel();
                        handler.sendMessage(msg);
                    } else {
                        Gson gson = new Gson();
                        String  result= gson.fromJson(str, new TypeToken<String>() {
                        }.getType());
                        Message msg = new Message();
                        msg.what = 2;
                        msg.obj = result;
                        handler.sendMessage(msg);
                        prodialog.cancel();
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    Message msg = new Message();
                    msg.what=2;
                    msg.obj = "获取数据失败，请检查网络设置或稍后再试";
                    handler.sendMessage(msg);
                    prodialog.cancel();
                }


            }
        });
        t.start();
    }

}
