package com.example.chenjunfan.gsgj;

/**
 * Created by chenjunfan on 2016/12/5.
 */
        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.os.Handler;
        import android.os.Message;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import function.PieChartManger;
        import function.UsersTable;

public class FragmentPage3 extends Fragment implements View.OnClickListener{

    ImageView touxiangIV,reIV;
    TextView nameTV;
    Button dqyyBT,lsyyBT,logoutBT,tongjiBT;
    Activity activity = getActivity();

    UsersTable user;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_3, null);
        Intent intent = getActivity().getIntent();
        UsersTable user = null;

        init(view,intent);
        dqyyBT.setOnClickListener(this);
        lsyyBT.setOnClickListener(this);
        logoutBT.setOnClickListener(this);
        tongjiBT.setOnClickListener(this);


        return view;
    }

    void init(View view, Intent intent)
    {
        touxiangIV = (ImageView) view.findViewById(R.id.IV_f3_touxiang);
        nameTV = (TextView) view.findViewById(R.id.TV_f3_name);
        dqyyBT = (Button) view.findViewById(R.id.BT_f3_dqyy);
        lsyyBT = (Button) view.findViewById(R.id.BT_f3_lsyy);
        logoutBT = (Button) view.findViewById(R.id.BT_f3_logout);
        tongjiBT = (Button) view.findViewById(R.id.BT_f3_statistics);

        user = (UsersTable) intent.getSerializableExtra("user");
        if(user!=null)
            nameTV.setText("学号："+user.getUsers_account()+"\n 姓名:"+user.getUsers_name());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.BT_f3_dqyy:
                Intent intent = new Intent(getContext(),DingdanActivity.class);
                intent.putExtra("type","当前预约");
                startActivity(intent);
                break;
            case R.id.BT_f3_lsyy:
                Intent intent2 = new Intent(getContext(),DingdanActivity.class);
                intent2.putExtra("type","历史预约");
                startActivity(intent2);
                break;
            case R.id.BT_f3_statistics:
                PieChartManger pieChartManger = new PieChartManger(getActivity(),getContext(),handler,user.getUsers_account());
                pieChartManger.getdate();
                break;
            case R.id.BT_f3_logout:
                Intent intent4 = new Intent(getContext(),LoginActivity.class);
                startActivity(intent4);
                getActivity().finish();
                break;

        }
    }

    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.obj!="success")
            Toast.makeText(getContext(),msg.obj.toString(), Toast.LENGTH_SHORT).show();
        }
    };
}
