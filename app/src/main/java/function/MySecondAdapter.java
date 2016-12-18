package function;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chenjunfan.gsgj.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenjunfan on 2016/12/16.
 */

public class MySecondAdapter extends BaseAdapter{
    private List<String> itemList;
    private LayoutInflater mInfalter;
    int[] image = {R.drawable.bg_secondmenu_zuqiu,R.drawable.bg_secondmenu_paiqiu,
            R.drawable.bg_secondmenu_lanqiu,R.drawable.bg_secondmenu_wangqiu,R.drawable.bg_secondmenu_yumaoqiu,
            R.drawable.bg_secondmenu_pingpangqiu};
    public  MySecondAdapter(Context context, List<String> itemList)
    {
        this.itemList=itemList;
        mInfalter =  LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return image.length;
    }

    @Override
    public Object getItem(int i) {
        return image.length;
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
            convertView = mInfalter.inflate(R.layout.item_secondmenu,null);
            viewHolder.picIV = (ImageView) convertView.findViewById(R.id.IV_ims_pic);
            viewHolder.titleTV = (TextView) convertView.findViewById(R.id.TV_ims_title);

            convertView.setTag(viewHolder);

        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.titleTV.setText(itemList.get(position));
        viewHolder.picIV.setImageResource(image[position]);

        return convertView;
    }
    class ViewHolder{
        public TextView titleTV;
        public ImageView picIV;
    }
}
