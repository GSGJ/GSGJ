package com.example.chenjunfan.gsgj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class LoginActivity extends AppCompatActivity {

    private Button loginBT;
    private EditText idET, pwET;
    private ListView mainLV;


    public void Init()
    {
        loginBT = (Button) findViewById(R.id.bt_lg_login);
        idET = (EditText) findViewById(R.id.et_lg_id);
        pwET = (EditText) findViewById(R.id.et_lg_pw);
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
