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
import java.net.URLDecoder;
import java.net.URLEncoder;
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
    private List<RmItemBean> RmitemBeanList = new ArrayList<RmItemBean>();
    private Map<String, String> itemMap = new HashMap<String, String>();

    public List<RmItemBean> getRmitemBeanList() {
        return RmitemBeanList;
    }

    public void setRmitemBeanList(List<RmItemBean> rmitemBeanList) {
        RmitemBeanList = rmitemBeanList;
    }

    public void getListdate(final String str, final Context context, final Handler handler) {
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
                    URLEncoder.encode(str,"GBK");
                    Url = "http://" + context.getResources().getText(R.string.ip_address) + "/VenueManager/specificVenueServlet" + "?venueType="+URLEncoder.encode(str,"UTF-8"); ;
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
                        itemList = gson.fromJson(response.toString(), new TypeToken<List<Map<String, String>>>() {
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
                finally {
                    connection.disconnect();
                }




                return null;
            }


        };
        asyncTask.execute();


    }

    public void getRmListdate(final Context context, final Handler handler) {
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
                    Url = "http://" + context.getResources().getText(R.string.ip_address) + "/VenueManager/hotVenueServlet" + "?type=phone";
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

                    Gson gson = new Gson();
                    itemList = gson.fromJson(response.toString(), new TypeToken<List<Map<String,String>>>() {
                    }.getType());
                    for (int i = 0; i < itemList.size(); i++) {
                        RmitemBeanList.add(new RmItemBean(itemList.get(i).get("venueName"),itemList.get(i).get("bookNumber"), itemList.get(i).get("reserveNum")));
                    }
                    Message msg = new Message();
                    msg.obj = "success";
                    handler.sendMessage(msg);
                    prodialog.cancel();

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
