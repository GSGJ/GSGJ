package com.example.chenjunfan.gsgj;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by chenjunfan on 2016/12/9.
 */

public class ThirdActivity extends Activity implements View.OnClickListener {
    ImageView reIV;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_third);

        Init();
        reIV.setOnClickListener(this);

    }

    public void Init()
    {
        reIV = (ImageView) findViewById(R.id.IV_td_re);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.IV_sd_re:
                finish();
                break;
        }
    }
}
