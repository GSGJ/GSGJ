package function;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.chenjunfan.gsgj.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by chenjunfan on 2016/12/14.
 */

public class ThirdMenuManager {
    public List<Map<String, String>> getItemList() {
        return itemList;
    }

    public void setItemList(List<Map<String, String>> itemList) {
        this.itemList = itemList;
    }

    public List<ItemBean> getItemBeanList() {
        return itemBeanList;
    }

    public void setItemBeanList(List<ItemBean> itemBeanList) {
        this.itemBeanList = itemBeanList;
    }

    public Map<String, String> getItemMap() {
        return itemMap;
    }

    public void setItemMap(Map<String, String> itemMap) {
        this.itemMap = itemMap;
    }

    private List<Map<String, String>> itemList = new ArrayList<Map<String, String>>();
    private List<ItemBean> itemBeanList = new ArrayList<ItemBean>();
    private Map<String, String> itemMap = new HashMap<String, String>();

    public void getListdate(final String str, final Context context, final Handler handler) {
        final ProgressDialog prodialog = new ProgressDialog(context);
        prodialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        prodialog.setIndeterminate(true);
        prodialog.setCancelable(false);
        prodialog.setMessage("正在刷新~");
        prodialog.show();

//        final Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    String Url;
//                    Url = "http://" + context.getResources().getText(R.string.ip_address) + ":8080/VenueManager/specificVenueServlet" + "?venueType="+str ;
//                    Log.i("url", Url);
//                    URL url = new URL(Url);
//                    URLConnection conn = url.openConnection();
//                    conn.setRequestProperty("Accept-Charset", "UTF-8");
//                    conn.setRequestProperty("contentType", "UTF-8");
//                    conn.setReadTimeout(1000);
//                    conn.setConnectTimeout(3000);
//                    InputStreamReader reader = new InputStreamReader(conn.getInputStream(), "UTF-8");
//                    BufferedReader br = new BufferedReader(reader);
//                    String str = br.readLine();
//                    Log.d("InputStream",str);
//                    if(str.equals("null_error"))
//                    {
//                        Message msg = new Message();
//                        msg.obj = "账户不存在！";
//                        prodialog.cancel();
//                        handler.sendMessage(msg);
//
//                    }
//                    else if(str.equals("not_found"))
//                    {
//                        Message msg = new Message();
//                        msg.obj = "密码错误！";
//                        prodialog.cancel();
//                        handler.sendMessage(msg);
//                    }
//                    else {
//                        Gson gson = new Gson();
//                        itemList= gson.fromJson(str, new TypeToken<List<Map<String,String>>>() {
//                        }.getType());
//                        for(int i=0;i<itemList.size();i++)
//                        {
//                            itemBeanList.add(new ItemBean(itemList.get(i).get("venueName"),itemList.get(i).get("bookNumber")));
//                        }
//                        Message msg = new Message();
//                        msg.obj = "success";
//                        handler.sendMessage(msg);
//                        prodialog.cancel();
//                    }
//                } catch (Exception e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                    Message msg = new Message();
//                    msg.obj = "获取数据失败，请检查网络设置或稍后再试";
//                    handler.sendMessage(msg);
//                    prodialog.cancel();
//                }
//
//
//            }
//        });
//        t.start();

        AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {

                try {
                    String Url;
                    Url = "http://" + context.getResources().getText(R.string.ip_address) + ":8080/VenueManager/specificVenueServlet" + "?venueType=" + str;
                    Log.i("url", Url);
                    URL url = new URL(Url);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestProperty("Accept-Charset", "UTF-8");
                    conn.setRequestProperty("contentType", "UTF-8");
                    conn.setReadTimeout(1000);
                    conn.setConnectTimeout(3000);
                    InputStreamReader reader = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader br = new BufferedReader(reader);
                    String str = br.readLine();
                    Log.d("InputStream", str);
                    if (str.equals("null_error")) {
                        Message msg = new Message();
                        msg.obj = "账户不存在！";
                        prodialog.cancel();
                        handler.sendMessage(msg);

                    } else if (str.equals("not_found")) {
                        Message msg = new Message();
                        msg.obj = "密码错误！";
                        prodialog.cancel();
                        handler.sendMessage(msg);
                    } else {
                        Gson gson = new Gson();
                        itemList = gson.fromJson(str, new TypeToken<List<Map<String, String>>>() {
                        }.getType());
                        for (int i = 0; i < itemList.size(); i++) {
                            itemBeanList.add(new ItemBean(itemList.get(i).get("venueName"), itemList.get(i).get("bookNumber")));
                        }
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




                return null;
            }


        };
        asyncTask.execute();


    }
}
