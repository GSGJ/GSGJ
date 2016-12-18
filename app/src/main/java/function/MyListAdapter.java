package function;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chenjunfan.gsgj.R;

import java.util.List;

/**
 * Created by chenjunfan on 2016/12/9.
 */

public class MyListAdapter extends BaseAdapter {
    private List<ItemBean> mList;
    private LayoutInflater mInfalter;

    public MyListAdapter(Context context, List<ItemBean> list) {
        mList = list;
        mInfalter = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.size();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null)
        {
            viewHolder = new ViewHolder();
            convertView = mInfalter.inflate(R.layout.item_thirdmenu,null);
            viewHolder.numTV = (TextView) convertView.findViewById(R.id.TV_imt_num);
            viewHolder.titleTV = (TextView) convertView.findViewById(R.id.TV_imt_title);
            viewHolder.picTV = (ImageView) convertView.findViewById(R.id.IV_imt_pic);
            convertView.setTag(viewHolder);

        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ItemBean bean = mList.get(position);
        viewHolder.titleTV.setText(bean.getTitle());
        viewHolder.numTV.setText("可预约数："+bean.getNum());
        if(bean.getTitle().indexOf("教室")!=-1)
        {
            viewHolder.picTV.setImageResource(R.drawable.pic_jiaoshi);
        }
        else if(bean.getTitle().indexOf("篮球")!=-1)
        {
            viewHolder.picTV.setImageResource(R.drawable.pic_lanqiu);
        }
        else if(bean.getTitle().indexOf("排球")!=-1)
        {
            viewHolder.picTV.setImageResource(R.drawable.pic_paiqiu);
        }
        else if(bean.getTitle().indexOf("乒乓")!=-1)
        {
            viewHolder.picTV.setImageResource(R.drawable.pic_pingpang);
        }
        else if(bean.getTitle().indexOf("实验")!=-1)
        {
            viewHolder.picTV.setImageResource(R.drawable.pic_shiyan);
        }
        else if(bean.getTitle().indexOf("研讨")!=-1)
        {
            viewHolder.picTV.setImageResource(R.drawable.pic_tushu);
        }
        else if(bean.getTitle().indexOf("网球")!=-1)
        {
            viewHolder.picTV.setImageResource(R.drawable.pic_wangqiu);
        }
        else if(bean.getTitle().indexOf("羽毛")!=-1)
        {
            viewHolder.picTV.setImageResource(R.drawable.pic_yumao);
        }
        else if(bean.getTitle().indexOf("足球")!=-1)
        {
            viewHolder.picTV.setImageResource(R.drawable.pic_zuqiu);
        }
        else{
            viewHolder.picTV.setImageResource(R.drawable.ic_launcher);
        }

        return convertView;
    }
    class ViewHolder{
        public TextView titleTV,numTV;
        public ImageView picTV;
    }
}
