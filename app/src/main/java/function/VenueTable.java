package function;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.chenjunfan.gsgj.DetailActivity;
import com.example.chenjunfan.gsgj.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class VenueTable implements Serializable {
	private int venue_id; //自增馆室id号
	private int venuegen_id; //非自增馆室统称id号
	private String venue_name; //馆室名称
	private String venue_addr; //馆室地址
	private String venue_opentime; //馆室开放时间
	//private String venue_closetime; //馆室关闭时间
	private int venue_sumarea; //馆室总区域数
	private int venue_maxnum; //馆室最大可容纳人数
	private int venue_isopen; //馆室是否开放（0 为关闭，非0 为开放）
	private String venue_notice; //馆室公告

	public int getVenue_id() {
		return venue_id;
	}

	public void setVenue_id(int venue_id) {
		this.venue_id = venue_id;
	}

	public int getVenuegen_id() {
		return venuegen_id;
	}

	public void setVenuegen_id(int venuegen_id) {
		this.venuegen_id = venuegen_id;
	}

	public String getVenue_name() {
		return venue_name;
	}

	public void setVenue_name(String venue_name) {
		this.venue_name = venue_name;
	}

	public String getVenue_opentime() {
		return venue_opentime;
	}

	public void setVenue_opentime(String venue_opentime) {
		this.venue_opentime = venue_opentime;
	}

	//	public String getVenue_closetime() {
//		return venue_closetime;
//	}
//	public void setVenue_closetime(String venue_closetime) {
//		this.venue_closetime = venue_closetime;
//	}
	public int getVenue_maxnum() {
		return venue_maxnum;
	}

	public void setVenue_maxnum(int venue_maxnum) {
		this.venue_maxnum = venue_maxnum;
	}

	public int getVenue_isopen() {
		return venue_isopen;
	}

	public void setVenue_isopen(int venue_isopen) {
		this.venue_isopen = venue_isopen;
	}

	public String getVenue_notice() {
		return venue_notice;
	}

	public void setVenue_notice(String venue_notice) {
		this.venue_notice = venue_notice;
	}

	public String getVenue_addr() {
		return venue_addr;
	}

	public void setVenue_addr(String venue_addr) {
		this.venue_addr = venue_addr;
	}

	public int getVenue_sumarea() {
		return venue_sumarea;
	}

	public void setVenue_sumarea(int venue_sumarea) {
		this.venue_sumarea = venue_sumarea;
	}

	public void getDetial(final String name, final Context context, final Activity activity, final Handler handler, final String avanum) {
		final ProgressDialog prodialog = new ProgressDialog(context);
		prodialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		prodialog.setIndeterminate(true);
		prodialog.setCancelable(false);
		prodialog.setMessage("正在刷新~");

		final Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					String Url;
					Url = "http://" + context.getResources().getText(R.string.ip_address) + ":8080/VenueManager/venueInfoServlet" + "?venueName=" + URLEncoder.encode(name,"UTF-8");
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
						VenueInfoTable info = gson.fromJson(str, new TypeToken<VenueInfoTable>() {}.getType());
						Intent intent = new Intent(context, DetailActivity.class);
						intent.putExtra("info", info);
						intent.putExtra("avanum",avanum);
						context.startActivity(intent);
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
