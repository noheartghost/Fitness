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

public class RoundAdapter extends BaseAdapter {

    private List<Map<String, Object>> data;
    private LayoutInflater layoutInflater;
    private Context context;
    public RoundAdapter(Context context,List<Map<String, Object>> data){
        this.context=context;
        this.data=data;
        this.layoutInflater=LayoutInflater.from(context);
    }
    /**
     * 组件集合，对应list.xml中的控件
     * @author Administrator
     */
    public final class Zujian{
        public ImageView image;
        public TextView title;
        public TextView time;
        public ImageView contentPicture;
        public TextView content;
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
            convertView=layoutInflater.inflate(R.layout.round_list_item, null);
            zujian.image=(ImageView)convertView.findViewById(R.id.head_image);
            zujian.title=(TextView)convertView.findViewById(R.id.title);
            zujian.time=(TextView)convertView.findViewById(R.id.time);
            zujian.contentPicture =(ImageView)convertView.findViewById(R.id.content_image);
            zujian.content = (TextView) convertView.findViewById(R.id.content);
            convertView.setTag(zujian);
        }else{
            zujian=(Zujian)convertView.getTag();
        }
        //绑定数据
        zujian.image.setBackgroundResource((Integer)data.get(position).get("image"));
        zujian.title.setText((String)data.get(position).get("title"));
        zujian.time.setText((String)data.get(position).get("time"));
        zujian.contentPicture.setBackgroundResource((Integer)data.get(position).get("contentPicture"));
        zujian.content.setText((String) data.get(position).get("content"));
        return convertView;
    }

}