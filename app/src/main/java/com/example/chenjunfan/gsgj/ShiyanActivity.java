package com.example.chenjunfan.gsgj;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by chenjunfan on 2016/12/7.
 */

public class ShiyanActivity extends Activity implements View.OnClickListener {
    ImageView reIV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shiyan);
        Init();
        reIV.setOnClickListener(this);
    }
    public void Init()
    {
        reIV = (ImageView) findViewById(R.id.IV_sy_re);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.IV_sy_re:
                finish();
                break;
        }
    }
}
