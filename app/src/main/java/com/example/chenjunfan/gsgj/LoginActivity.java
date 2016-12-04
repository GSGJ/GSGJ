package com.example.chenjunfan.gsgj;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private Button BTlogin,BTregist;
    private EditText ETid,ETpw;


    public void Init()
    {
        BTlogin = (Button) findViewById(R.id.bt_lg_login);
        BTregist = (Button) findViewById(R.id.bt_lg_regist);
        ETid = (EditText) findViewById(R.id.et_lg_id);
        ETpw = (EditText) findViewById(R.id.et_lg_pw);
    }

    public void PressLogin(View view)
    {
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Init();

    }
}
