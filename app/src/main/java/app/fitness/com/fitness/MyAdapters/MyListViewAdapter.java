package app.fitness.com.fitness.MyAdapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

import app.fitness.com.fitness.R;

public class MyListViewAdapter extends BaseAdapter implements ListAdapter {
    private List<String> data;
    private int layout;
    private Context context;
    private ImageView iv = null;
    private TextView tv = null;

    public MyListViewAdapter(List<String> data, int layout, Context context) {
        this.data = data;
        this.layout = layout;
        this.context = context;

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return data.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public View getView(int arg0, View view, ViewGroup arg2) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(layout, null);
            iv = (ImageView) view.findViewById(R.id.iv);
            tv = (TextView) view.findViewById(R.id.tv);

            view.setTag(new ObjectClass(iv, tv));

        } else {
            ObjectClass objectclass = (ObjectClass) view.getTag();
            iv = objectclass.iv;
            tv = objectclass.text;

        }
        if ( arg0 % 3 == 0)
            iv.setImageResource(R.drawable.gym1);
        else if ( arg0 % 3 == 1)
            iv.setImageResource(R.drawable.gym2);
        else if ( arg0 % 3 == 2)
            iv.setImageResource(R.drawable.gym3);
        tv.setText(data.get(arg0));
        return view;
    }

    private final class ObjectClass {

        ImageView iv = null;
        TextView text = null;

        public ObjectClass(ImageView iv, TextView text) {
            this.iv = iv;
            this.text = text;
        }
    }

}

