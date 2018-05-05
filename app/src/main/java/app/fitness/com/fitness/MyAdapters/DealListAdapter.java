package app.fitness.com.fitness.MyAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import app.fitness.com.fitness.R;

public class DealListAdapter extends BaseAdapter implements OnClickListener {

    private List<Map<String, Object>> data;
    private LayoutInflater layoutInflater;
    private Callback mCallback;
    private Context context;

    //自定义接口，用于回调按钮点击事件
    public interface Callback{
        public void click(View v);
    }
    public DealListAdapter(Context context,List<Map<String, Object>> data,Callback callback){
        this.context=context;
        this.data=data;
        this.layoutInflater=LayoutInflater.from(context);
        this.mCallback = callback;
    }



    /**
     * 组件集合，对应list.xml中的控件
     * @author Administrator
     */
    public final class Zujian{
        public ImageView deal_head;
        public TextView deal_name,deal_class,deal_time,deal_state;
        public Button callfriend_btn,callcoach_btn;
    }

    //响应点击事件，调用子定义接口，并传入view
    @Override
    public void onClick(View v){
        mCallback.click(v);
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
            convertView=layoutInflater.inflate(R.layout.deal_list_item, null);
            zujian.deal_head=(ImageView)convertView.findViewById(R.id.deal_head);
            zujian.deal_name=(TextView)convertView.findViewById(R.id.deal_name);
            zujian.deal_class=(TextView)convertView.findViewById(R.id.deal_class);
            zujian.deal_time=(TextView)convertView.findViewById(R.id.deal_time);
            zujian.deal_state=(TextView)convertView.findViewById(R.id.deal_state);

            zujian.callcoach_btn = (Button)convertView.findViewById(R.id.callcoach_btn);
            zujian.callfriend_btn = (Button)convertView.findViewById(R.id.callfriend_btn);

            convertView.setTag(zujian);
        }else{
            zujian=(Zujian)convertView.getTag();
        }
        //绑定数据
        zujian.deal_head.setImageResource((Integer)data.get(position).get("deal_head"));
        zujian.deal_name.setText((String)data.get(position).get("deal_name"));
        zujian.deal_class.setText((String)data.get(position).get("deal_class"));
        zujian.deal_time.setText((String)data.get(position).get("deal_time"));
        zujian.deal_state.setText((String)data.get(position).get("deal_state"));

        zujian.callcoach_btn.setOnClickListener(this);
        zujian.callcoach_btn.setTag(position);
        zujian.callfriend_btn.setOnClickListener(this);
        zujian.callfriend_btn.setTag(position);

        return convertView;
    }



}