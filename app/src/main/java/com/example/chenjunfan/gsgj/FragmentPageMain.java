package com.example.chenjunfan.gsgj;

/**
 * Created by chenjunfan on 2016/12/5.
 */


        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;

public class FragmentPageMain extends Fragment implements View.OnClickListener {
    private ImageView tiyuIV,tushuIV,shiyanIV,jiaoshiIV;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_main, null);

        Init(view);
        tiyuIV.setOnClickListener(this);
        tushuIV.setOnClickListener(this);
        shiyanIV.setOnClickListener(this);
        jiaoshiIV.setOnClickListener(this);



        return view;
    }



    public void Init(View view)
    {
        tiyuIV= (ImageView) view.findViewById(R.id.IV_fm_tiyu);
        tushuIV= (ImageView) view.findViewById(R.id.IV_fm_tushu);
        shiyanIV= (ImageView) view.findViewById(R.id.IV_fm_shiyan);
        jiaoshiIV= (ImageView) view.findViewById(R.id.IV_fm_jiaoshi);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        intent = new Intent(view.getContext(),SecondActivity.class);
        Log.d("Onclick","t");
        switch (view.getId()) {
            case R.id.IV_fm_tiyu:
                intent.putExtra("id",view.getId());
                Log.d("1","t");
                startActivity(intent);
                break;
            case R.id.IV_fm_tushu:
                intent.putExtra("id",view.getId());
                Log.d("2","t");
                startActivity(intent);
                break;
            case R.id.IV_fm_shiyan:
                intent.putExtra("id",view.getId());
                Log.d("3","t");
                startActivity(intent);
                break;
            case R.id.IV_fm_jiaoshi:
                intent.putExtra("id",view.getId());
                Log.d("4","t");
                startActivity(intent);
                break;
        }
    }
}
