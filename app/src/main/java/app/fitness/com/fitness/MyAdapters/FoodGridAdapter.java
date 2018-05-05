package app.fitness.com.fitness.MyAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import app.fitness.com.fitness.R;

public class FoodGridAdapter extends BaseAdapter {

    private List<Map<String, Object>> data;
    private LayoutInflater layoutInflater;
    private Context context;
    public FoodGridAdapter(Context context,List<Map<String, Object>> data){
        this.context=context;
        this.data=data;
        this.layoutInflater=LayoutInflater.from(context);
    }
    /**
     * 组件集合，对应list.xml中的控件
     * @author Administrator
     */
    public final class Zujian{
        public ImageView grid_img;
        public TextView grid_name;
        public TextView grid_feature;
    }
    @Override
    public int getCount() {
        return data.size();
    }
    /**
     * 获得某一位置的数据
     */
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }
    /**
     * 获得唯一标识
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Zujian zujian=null;
        if(convertView==null){
            zujian=new Zujian();
            //获得组件，实例化组件
            convertView=layoutInflater.inflate(R.layout.close_grid_item, null);
            zujian.grid_img=(ImageView)convertView.findViewById(R.id.grid_img);
            zujian.grid_name=(TextView)convertView.findViewById(R.id.grid_name);
            zujian.grid_feature=(TextView)convertView.findViewById(R.id.grid_feature);
            convertView.setTag(zujian);
        }else{
            zujian=(Zujian)convertView.getTag();
        }
        //绑定数据
        // zujian.grid_img.setImageBitmap((Bitmap)data.get(position).get("grid_img"));
//        zujian.grid_img.setImageResource((Bitmap)data.get(position).get("grid_img"));
        zujian.grid_name.setText((String)data.get(position).get("grid_name"));
        zujian.grid_feature.setText((String)data.get(position).get("grid_feature"));
        return convertView;
    }

}