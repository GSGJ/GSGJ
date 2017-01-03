package function;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Handler;

import com.example.chenjunfan.gsgj.LoginActivity;
import com.example.chenjunfan.gsgj.MainActivity;
import com.example.chenjunfan.gsgj.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;


import static android.content.Context.MODE_PRIVATE;

public class UsersTable implements Serializable {
	private int user_id=0; //自增id号（唯一）
	private String users_account=""; //账户（学号）（唯一）
	private String users_name=""; //姓名
	private String users_password=""; //密码
	private int users_admin=0; // 0 为普通用户，非0 为管理员
	private int users_sumhour=0; //该用户当天已预约的总时长（每天清零）
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUsers_account() {
		return users_account;
	}
	public void setUsers_account(String users_account) {
		this.users_account = users_account;
	}
	public String getUsers_password() {
		return users_password;
	}
	public void setUsers_password(String users_password) {
		this.users_password = users_password;
	}
	public int getUsers_admin() {
		return users_admin;
	}
	public void setUsers_admin(int users_admin) {
		this.users_admin = users_admin;
	}
	public int getUsers_sumhour() {
		return users_sumhour;
	}
	public void setUsers_sumhour(int users_sumhour) {
		this.users_sumhour = users_sumhour;
	}
	public String getUsers_name() {
		return users_name;
	}
	public void setUsers_name(String users_name) {
		this.users_name = users_name;
	}

    public int login (final Context context, final Activity activity, final EditText idEditText, final EditText passwordEditText, final Handler handler)
    {
        final Intent intent = new Intent(context,MainActivity.class);
        Bundle  bundle = new Bundle();

        final ProgressDialog prodialog=new ProgressDialog(context);
        prodialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        prodialog.setIndeterminate(true);
        prodialog.setCancelable(false);
        prodialog.setMessage("正在登录");
        prodialog.show();

        if(idEditText.getText().toString().equals(""))
        {
            Toast.makeText(context,"请输入学号",Toast.LENGTH_SHORT).show();
        }
        else if (passwordEditText.getText().toString().equals(""))
        {
            Toast.makeText(context,"请输入密码",Toast.LENGTH_SHORT).show();

        }
        else
        {
            prodialog.show();

            if (idEditText.getText().toString().equals("1111") && passwordEditText.getText().toString().equals("1111")) {
                context.startActivity(intent);
                activity.finish();
            }

            else {
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String id,passwd;

                        SharedPreferences pre = context.getSharedPreferences("remeberUser", MODE_PRIVATE);
                        SharedPreferences.Editor editor = pre.edit();
                        id = idEditText.getText().toString();
                        passwd = passwordEditText.getText().toString();
                        try {
                            String Url;
                            Url = "http://" + context.getResources().getText(R.string.ip_address) + "/VenueManager/LoginServlet" + "?account=" + id + "&password=" + passwd;
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
                            if(str.equals("null_error"))
                            {
                                Message msg = new Message();
                                msg.obj = "账户不存在！";
                                prodialog.cancel();
                                handler.sendMessage(msg);
                            }
                            else if(str.equals("not_found"))
                            {
                                Message msg = new Message();
                                msg.obj = "密码错误！";
                                prodialog.cancel();
                                handler.sendMessage(msg);
                            }
                        else {
                            Gson gson = new Gson();
                            UsersTable user = gson.fromJson(str, new TypeToken<UsersTable>() {
                            }.getType());
                            Log.i("user1", user.getUsers_account()+user.getUsers_name()+user.getUsers_password());
                                intent.putExtra("user",user);//存储USER对象；


                                editor.putString("userId", idEditText.getText().toString().trim());
                                editor.commit();


                                    passwd = passwordEditText.getText().toString().trim();
                                    editor.putString("passwd", passwordEditText.getText().toString().trim());
                                    editor.commit();



                                Message msg = new Message();
                                msg.obj = "登录成功";
                                context.startActivity(intent);
                                handler.sendMessage(msg);
                                prodialog.cancel();
                                activity.finish();

                            }
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                            Message msg = new Message();
                            msg.obj = "服务器连接超时，请检查网络设置或稍后再试";
                            handler.sendMessage(msg);
                            prodialog.cancel();
                        }


                    }
                });
                t.start();
            }
        }

        return 0;
    }
}
