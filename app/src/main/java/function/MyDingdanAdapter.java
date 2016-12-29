package function;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.chenjunfan.gsgj.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by chenjunfan on 2016/12/19.
 */

public class MyDingdanAdapter extends BaseAdapter {
    private List<PersonalInfoTable> itemList;
    private LayoutInflater mInfalter;


    public MyDingdanAdapter(Context context,List<PersonalInfoTable> itemList)
    {
        this.itemList = itemList;
        mInfalter =  LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int i) {
        return itemList.size();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
    if(convertView == null)
    {
        viewHolder = new MyDingdanAdapter.ViewHolder();
        convertView = mInfalter.inflate(R.layout.item_dingdan,null);
        viewHolder.venueTV = (TextView) convertView.findViewById(R.id.TV_imd_venue);
        viewHolder.yytimeTV = (TextView) convertView.findViewById(R.id.TV_imd_yytime);
        viewHolder.ddtimeTV = (TextView) convertView.findViewById(R.id.TV_imd_ddtime);

        convertView.setTag(viewHolder);

    }
    else
    {
        viewHolder = (MyDingdanAdapter.ViewHolder) convertView.getTag();
    }
        Date ddtime = null,yytime=null;



        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0");
        try {
            ddtime = format.parse(itemList.get(i).getNow_time());
        } catch (ParseException e) {e.printStackTrace();
            Log.d("getNow_time()","unsuccess");

        }

        SimpleDateFormat ddformat = new SimpleDateFormat("订单生成时间：yyyy年MM月dd日 HH:mm:ss");
        Log.d("getNow_time()",ddformat.format(ddtime));
        viewHolder.ddtimeTV.setText(ddformat.format(ddtime));
        String str =itemList.get(i).getDate()+itemList.get(i).getTime();
        format = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
        try {
            yytime = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat yyformat1 = new SimpleDateFormat("预约时间段：yyyy年MM月dd日 HH:mm");
        SimpleDateFormat yyformat2 = new SimpleDateFormat("-HH:mm");
        str = yyformat1.format(yytime);
        yytime.setTime(yytime.getTime()+2*60*60*1000);
        str = str+yyformat2.format(yytime);
        viewHolder.yytimeTV.setText(str);
        viewHolder.venueTV.setText(itemList.get(i).getVenueName());




        return convertView;
}
class ViewHolder{
    public TextView venueTV,yytimeTV,ddtimeTV;

}
}
