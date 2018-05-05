package app.fitness.com.fitness.MyAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import app.fitness.com.fitness.R;

public class QuanListAdapter extends BaseAdapter implements View.OnClickListener{

    private List<Map<String, Object>> data;
    private LayoutInflater layoutInflater;
    private Callback mCallback;
    private Context context;
    public QuanListAdapter(Context context, List<Map<String, Object>> data, Callback callback){
        this.context=context;
        this.data=data;
        this.layoutInflater=LayoutInflater.from(context);
        this.mCallback = callback;
    }

    //自定义接口，用于回调点击事件到activity
    public interface Callback {
        public void click(View v);
    }

    /**
     * 组件集合，对应list.xml中的控件
     * @author Administrator
     */
    public final class Zujian{
        public TextView quan_name;
        public TextView quan_feature;
        public Button quan_btn;
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
            convertView=layoutInflater.inflate(R.layout.card_list_item, null);
            zujian.quan_name=(TextView)convertView.findViewById(R.id.quan_name);
            zujian.quan_feature=(TextView)convertView.findViewById(R.id.quan_cont);
            zujian.quan_btn = (Button)convertView.findViewById(R.id.quan_btn);
            convertView.setTag(zujian);
        }else{
            zujian=(Zujian)convertView.getTag();
        }
        //绑定数据
        zujian.quan_name.setText((String)data.get(position).get("quan_name"));
        zujian.quan_feature.setText((String)data.get(position).get("quan_cont"));
        zujian.quan_btn.setOnClickListener(this);
        zujian.quan_btn.setTag(position);
        return convertView;
    }

    //响应点击事件，调用自定义接口，并传入view
    @Override
    public void onClick(View v){
        mCallback.click(v);
    }

}