package function;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.chenjunfan.gsgj.R;

import java.util.List;

/**
 * Created by chenjunfan on 2016/12/9.
 */

public class MyAdapter extends BaseAdapter {
    private List<ItemBean> mList;
    private LayoutInflater mInfalter;

    public MyAdapter(Context context,List<ItemBean> list) {
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

            convertView.setTag(viewHolder);

        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ItemBean bean = mList.get(position);
        viewHolder.titleTV.setText(bean.getTitle());
        viewHolder.numTV.setText("可预约数："+bean.getNum());

        return convertView;
    }
    class ViewHolder{
        public TextView titleTV,numTV;
    }
}
