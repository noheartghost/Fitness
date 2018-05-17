package app.fitness.com.fitness.Activities;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.fitness.com.fitness.MyAdapters.DealListAdapter;
import app.fitness.com.fitness.R;

public class DealActivity extends TabActivity implements AdapterView.OnItemClickListener,DealListAdapter.Callback {

    private ListView ListView_No,ListView_Yes,ListView_All;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //获得TabHost对象
        TabHost tah = getTabHost();

        // from(this)从TabActivity获取LayoutInflater
        // R.layout.main 存放Tab布局
        // 通过TabHost获得存放Tab标签页内容的FrameLayout
        // 是否将inflate 加到根布局元素上
        LayoutInflater.from(this).inflate(R.layout.activity_deal, tah.getTabContentView());

        //设置Tab标签的内容和显示内容
        tah.addTab(tah.newTabSpec("tab1").setIndicator("未使用").setContent(R.id.list_no));
        tah.addTab(tah.newTabSpec("tab2").setIndicator("已使用").setContent(R.id.list_yes));
        tah.addTab(tah.newTabSpec("tab3").setIndicator("全部订单").setContent(R.id.list_all));

        initBottom();

        ListView_No = (ListView) findViewById(R.id.list_no);
        ListView_Yes = (ListView) findViewById(R.id.list_yes);
        ListView_All = (ListView) findViewById(R.id.list_all);

        List<Map<String, Object>> list_no=getNoData();
        List<Map<String, Object>> list_yes=getYesData();
        List<Map<String, Object>> list_all=getAllData();

        ListView_No.setAdapter(new DealListAdapter(this, list_no,this));
        ListView_Yes.setAdapter(new DealListAdapter(this, list_yes,this));
        ListView_All.setAdapter(new DealListAdapter(this, list_all,this));


    }

    //响应listview中item点击事件
    @Override
    public void onItemClick(AdapterView<?> arg0,View v,int position,long id){

    }
    //接口方法，响应listview按钮点击事件
    @Override
    public void click(View v){
        Intent intent = new Intent(DealActivity.this,MyFriendsActivity.class);
        startActivity(intent);
    }

    //初始化底部菜单栏
    private void initBottom(){
        ImageView jingxuan_img = (ImageView) findViewById(R.id.jingxuan_img);
        TextView jingxuan_txt = (TextView)  findViewById(R.id.bottom_jx_tv);
        jingxuan_img.setImageResource(R.drawable.jingxuan_p);
        jingxuan_txt.setTextColor(Color.WHITE);
        jingxuan_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DealActivity.this, ChosenActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                DealActivity.this.finish();
            }
        });

        ImageView quanzi_img = (ImageView) findViewById(R.id.quanzi_img);
        TextView quanzi_txt = (TextView)  findViewById(R.id.bottom_qz_tv);
        quanzi_img.setImageResource(R.drawable.quanzi_p);
        quanzi_txt.setTextColor(Color.WHITE);
        quanzi_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DealActivity.this, RoundActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                DealActivity.this.finish();
            }
        });

        ImageView dingdan_img = (ImageView) findViewById(R.id.dingdan_img);
        TextView dingdan_txt = (TextView)  findViewById(R.id.bottom_dd_tv);
        dingdan_img.setImageResource(R.drawable.dingdan);
        dingdan_txt.setTextColor(Color.BLACK);

        ImageView wode_img = (ImageView) findViewById(R.id.wode_img);
        TextView wode_txt = (TextView)  findViewById(R.id.bottom_wd_tv);
        wode_img.setImageResource(R.drawable.wode_p);
        wode_txt.setTextColor(Color.WHITE);
        wode_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DealActivity.this, MineActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                DealActivity.this.finish();
            }
        });
    }

    public List<Map<String, Object>> getNoData(){
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
        for (int i = 0; i < 10; i++) {
            //deal_name,deal_class,deal_time,deal_state
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("deal_head", R.drawable.gym1);
            map.put("deal_name", "宝力豪健身（大悦城南区）");
            map.put("deal_class", "单次健身卡");
            map.put("deal_time", "2017/07/1"+i);
            map.put("deal_state", "未消费");
            list.add(map);
        }
        return list;
    }

    public List<Map<String, Object>> getYesData(){
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
        for (int i = 0; i < 10; i++) {
            //deal_name,deal_class,deal_time,deal_state
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("deal_head", R.drawable.gym2);
            map.put("deal_name", "宝力豪健身（大悦城南区）");
            map.put("deal_class", "单次健身卡");
            map.put("deal_time", "2017/06/1"+i);
            map.put("deal_state", "已消费");
            list.add(map);
        }
        return list;
    }

    public List<Map<String, Object>> getAllData(){
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
        for (int i = 0; i < 10; i++) {
            //deal_name,deal_class,deal_time,deal_state
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("deal_head", R.drawable.gym3);
            map.put("deal_name", "宝力豪健身（大悦城南区）");
            map.put("deal_class", "单次健身卡");
            map.put("deal_time", "2017/08/1"+i);
            map.put("deal_state", "未/已消费");
            list.add(map);
        }
        return list;
    }

}
