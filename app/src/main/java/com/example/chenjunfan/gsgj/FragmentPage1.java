package com.example.chenjunfan.gsgj;

/**
 * Created by chenjunfan on 2016/12/5.
 */


        import android.app.Activity;
        import android.os.Bundle;
        import android.os.Handler;
        import android.os.Message;
        import android.support.v4.app.Fragment;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.ImageView;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.List;

        import function.MyRmAdapter;
        import function.RmItemBean;
        import function.ThirdMenuManager;
        import function.VenueTable;

public class FragmentPage1 extends Fragment implements AdapterView.OnItemClickListener {
    TextView titleTV;
    ImageView reBT;
    ListView mainLV;
    List<RmItemBean> rmItemBeenList;
    MyRmAdapter myRmAdapter;
    Activity activity = getActivity();

    ThirdMenuManager thirdMenuManager = new ThirdMenuManager();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_third, null);
        init(view);
        mainLV.setOnItemClickListener(this);



        return view;
    }

    void init(View view)
    {
        titleTV= (TextView) view.findViewById(R.id.TV_td_title);
        reBT = (ImageView) view.findViewById(R.id.IV_td_re);
        mainLV = (ListView) view.findViewById(R.id.LV_td_main);

        titleTV.setText("本月热门场馆排行榜");
        reBT.setVisibility(View.GONE);
        thirdMenuManager.setRmitemBeanList(new ArrayList<RmItemBean>());
        thirdMenuManager.getRmListdate(getContext(),handler);
    }

    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.obj.equals("success")) {
                rmItemBeenList = thirdMenuManager.getRmitemBeanList();
                myRmAdapter = new MyRmAdapter(getContext(), rmItemBeenList);
                mainLV.setAdapter(myRmAdapter);
            }
            else
                Toast.makeText(getContext(),msg.obj.toString(), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        new VenueTable().getDetial(rmItemBeenList.get(i).getTitle(),getContext(),activity,handler,rmItemBeenList.get(i).getNum());
    }
}