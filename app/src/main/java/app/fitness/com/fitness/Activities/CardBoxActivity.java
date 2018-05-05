package app.fitness.com.fitness.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.fitness.com.fitness.MyAdapters.HorizontalListViewAdapter;
import app.fitness.com.fitness.MyAdapters.QuanListAdapter;
import app.fitness.com.fitness.R;
import app.fitness.com.fitness.tools.HorizontalListView;

public class CardBoxActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,QuanListAdapter.Callback {

    private ListView quan_list = null;
    private ImageView cardbox_backimg;
    private Button quan_btn;

    //横向列表
    private HorizontalListViewAdapter hlva;
    private HorizontalListView hlv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_box);

        quan_list = (ListView)findViewById(R.id.quan_list);
        List<Map<String, Object>> list=getData();
        quan_list.setAdapter(new QuanListAdapter(this, list,this));
        quan_list.setOnItemClickListener(this);

        //横向卡片列表
        hlv=(HorizontalListView)findViewById(R.id.card_list);
        hlva=new HorizontalListViewAdapter(this,getCardListData());
        hlva.notifyDataSetChanged();
        hlv.setAdapter(hlva);

        //返回按钮
        cardbox_backimg = (ImageView)findViewById(R.id.cardbox_backimg);
        cardbox_backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardBoxActivity.this.finish();
            }
        });

    }
    public List<Map<String, Object>> getData(){
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
        for (int i = 0; i < 6; i++) {
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("quan_name", i+1+"0元代金券");
            map.put("quan_cont", "消费满"+100*(i+1)+"元可用");
            list.add(map);
        }
        return list;
    }

    //横向列表数据
    public List<Map<String, Object>> getCardListData(){
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
        for (int i = 0; i < 5; i++) {
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("coachimg", R.drawable.ka);
            list.add(map);
        }
        return list;
    }

    //响应ListView中的item的点击事件
    @Override
    public void onItemClick(AdapterView<?> arg0,View v,int position,long id){

    }
    //接口方法，响应listview按钮点击事件
    @Override
    public void click(View v) {
        Intent intent = new Intent(CardBoxActivity.this,DealActivity.class);
        startActivity(intent);
    }

}
