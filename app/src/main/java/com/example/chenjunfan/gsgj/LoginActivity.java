package com.example.chenjunfan.gsgj;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import function.UsersTable;

public class LoginActivity extends AppCompatActivity {

    private Button loginBT;
    private EditText idET, pwET;
    private ListView mainLV;
    UsersTable user = new UsersTable();
    Activity activity = this;
    Handler  handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            super.handleMessage(msg);

            String str= (String) msg.obj;

            Toast.makeText(LoginActivity.this,str, Toast.LENGTH_SHORT).show();


        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        init();

    }

    public void init()
    {
        loginBT = (Button) findViewById(R.id.bt_lg_login);
        idET = (EditText) findViewById(R.id.et_lg_id);
        pwET = (EditText) findViewById(R.id.et_lg_pw);

        SharedPreferences pre = getSharedPreferences("remeberUser",MODE_PRIVATE);
        idET.setText(pre.getString("userId",""));
        pwET.setText(pre.getString("passwd",""));



    }

    public void PressLogin(View view)
    {
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        user.login(LoginActivity.this,activity,idET,pwET,handler);

    }
}
