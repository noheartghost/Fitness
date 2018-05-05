package app.fitness.com.fitness.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import app.fitness.com.fitness.Adapters.GetCloseGym;
import app.fitness.com.fitness.Adapters.GetGymPhoto;
import app.fitness.com.fitness.App.MyApp;
import app.fitness.com.fitness.R;
import app.fitness.com.fitness.util.GymNear;

public class ChosenActivity extends AppCompatActivity {

    private static final String TAG = "ChosenActivity";

    public static final int GYMNOSTPOPULAR = 0;

    public MyApp myApp;

    private TabHost tabHost;//声明TabHost组件的对象
    private ImageButton closebtn;
    private ImageView chosen_search_img;

    /**
     * 前期未找到，做后端时候添加的部件
     * @param savedInstanceState
     */
    private ImageButton haopingbtn;
    private ImageView haoping_search_img;
    private ImageButton leibiebtn;
    private ImageView leibie_search_img;
    private ImageButton alarmbtn;
    private ImageView alarm_search_img;

    /**
     * 获取最热场馆，需要先获取附近的全部场馆，再获取列表中的第一个
     */
    List<GymNear> listAll=new ArrayList<GymNear>();
    List<Bitmap> gymPhoto = new ArrayList<Bitmap>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosen);

        myApp = (MyApp)getApplicationContext();

        getData();

        //附近
        closebtn = (ImageButton) findViewById(R.id.id_tab_weixin_img);
        closebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChosenActivity.this, CloseActivity.class);
                startActivity(intent);
            }
        });

        //搜索按钮
        chosen_search_img = (ImageView) findViewById(R.id.chosen_search_btn);
        chosen_search_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChosenActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        tabHost=(TabHost)findViewById(android.R.id.tabhost);//获取tabHost对象
        tabHost.setup();//初始化TabHost组件

        initBottom();

        LayoutInflater inflater=LayoutInflater.from(this);//声明并实例化一个LayoutInflater对象
        inflater.inflate(R.layout.tab1, tabHost.getTabContentView());
        inflater.inflate(R.layout.tab2, tabHost.getTabContentView());
        inflater.inflate(R.layout.tab3, tabHost.getTabContentView());
        inflater.inflate(R.layout.tab4, tabHost.getTabContentView());
        tabHost.addTab(tabHost.newTabSpec("tab01")
                .setIndicator("精选场馆")
                .setContent(R.id.linearLayout1));//添加第一个标签页
        tabHost.addTab(tabHost.newTabSpec("tab02")
                .setIndicator("明星私教")
                .setContent(R.id.linearLayout2));//添加第二个标签页
        tabHost.addTab(tabHost.newTabSpec("tab03")
                .setIndicator("健身秘诀")
                .setContent(R.id.linearLayout3));//添加第三个标签页
        tabHost.addTab(tabHost.newTabSpec("tab04")
                .setIndicator("热门课程")
                .setContent(R.id.linearLayout4));//添加第三个标签页

//        本周精选场馆，需要传入场馆的id
        ImageView jingxuan_iv = (ImageView) findViewById(R.id.jingxuan_iv);
        jingxuan_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChosenActivity.this, GymActivity.class);
                /**
                 * 测试所用
                 */
                intent.putExtra("gymId",myApp.getGymMostPopular().getUserId());
                intent.putExtra("gymPhotoId", myApp.getGymMostPopular().getGymPhotos().get(0));
                intent.putExtra("gymType",GYMNOSTPOPULAR);
                startActivity(intent);
            }
        });

        ImageView starcoach_iv = (ImageView) findViewById(R.id.starcoach_iv);
        starcoach_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChosenActivity.this,DevelopingActivity.class);
                startActivity(intent);
            }
        });
        ImageView fittips_iv = (ImageView) findViewById(R.id.fittips_iv);
        fittips_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChosenActivity.this,DevelopingActivity.class);
                startActivity(intent);
            }
        });
        ImageView fitclass_iv = (ImageView) findViewById(R.id.fitclass_iv);
        fitclass_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChosenActivity.this,DevelopingActivity.class);
                startActivity(intent);
            }
        });

        //好评如潮
        ImageButton haoping_img = (ImageButton)findViewById(R.id.haoping_img);
        haoping_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChosenActivity.this, DevelopingActivity.class);
                startActivity(intent);
            }
        });

        //场馆类别
        ImageButton leibie_img = (ImageButton)findViewById(R.id.leibie_img);
        leibie_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChosenActivity.this, DevelopingActivity.class);
                startActivity(intent);
            }
        });

        //健康饮食
        ImageButton alarm_img = (ImageButton) findViewById(R.id.alarm_img);
        alarm_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChosenActivity.this, FoodActivity.class);
                startActivity(intent);
            }
        });
    }
    //初始化底部菜单栏
    private void initBottom(){
        ImageView jingxuan_img = (ImageView) findViewById(R.id.jingxuan_img);
        TextView jingxuan_txt = (TextView)  findViewById(R.id.bottom_jx_tv);
        jingxuan_img.setImageResource(R.drawable.jingxuan);
        jingxuan_txt.setTextColor(Color.BLACK);

        ImageView quanzi_img = (ImageView) findViewById(R.id.quanzi_img);
        TextView quanzi_txt = (TextView)  findViewById(R.id.bottom_qz_tv);
        quanzi_img.setImageResource(R.drawable.quanzi_p);
        quanzi_txt.setTextColor(Color.WHITE);
        quanzi_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChosenActivity.this, RoundActivity.class);
                startActivity(intent);
                ChosenActivity.this.finish();
            }
        });

        ImageView dingdan_img = (ImageView) findViewById(R.id.dingdan_img);
        TextView dingdan_txt = (TextView)  findViewById(R.id.bottom_dd_tv);
        dingdan_img.setImageResource(R.drawable.dingdan_p);
        dingdan_txt.setTextColor(Color.WHITE);
        dingdan_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChosenActivity.this, DealActivity.class);
                startActivity(intent);
                ChosenActivity.this.finish();
            }
        });

        ImageView wode_img = (ImageView) findViewById(R.id.wode_img);
        TextView wode_txt = (TextView)  findViewById(R.id.bottom_wd_tv);
        wode_img.setImageResource(R.drawable.wode_p);
        wode_txt.setTextColor(Color.WHITE);
        wode_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChosenActivity.this, MineActivity.class);
                startActivity(intent);
                ChosenActivity.this.finish();
            }
        });
    }

    /**
     * 获取最近健身房列表
     * @return
     */
    public void getData(){
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
        /**
         * 获取手机现在的经纬度
         */
        double locationx = 117.175483;
        double locationy = 39.109469;
        Handler handler = new Handler();
        new Thread(new GetCloseGym(locationx,locationy,new Handler(new GetCloseGymHandler()))).start();
    }

    public class GetCloseGymHandler implements android.os.Handler.Callback{

        @Override
        public boolean handleMessage(Message message) {
            if (message.arg1==0) {
                //成功获取列表
                Log.i(TAG, "handleMessage: "+ message.obj.toString());
                getActivityList(message.obj.toString());
            }else if (message.arg1==1) {
                //获取列表失败
                Log.i(TAG, "handleMessage: fail");

            }else if (message.arg1==2) {
                Log.i(TAG, "handleMessage: photo success");
                gymPhoto = (List<Bitmap>) message.obj;
                Log.i(TAG, "gymPhoto size: "+gymPhoto.size());
                myApp.setGymMostPopularBitmap(gymPhoto.get(0));
            }else if (message.arg1==3) {
                //获取列表失败
                Log.i(TAG, "handleMessage: photo fail");

            }
            return true;
        }
    }

    public void getActivityList(String Input){
//       List<Map<String, Object>> listShow=new ArrayList<Map<String,Object>>();
        Log.i(TAG, "getActivityList: "+Input);
        Gson gson = new Gson();
        listAll = gson.fromJson(Input, new TypeToken<List<GymNear>>() {
        }.getType());
        /**
         * 在成功获取列表之后，需要根据id获取健身房照片
         */
        for (GymNear gym :
                listAll) {
            if (gym.isrecommend()) {
                myApp.setGymMostPopular(gym);
                Log.i(TAG, "getActivityList: isrececommend"+gym.getUserId());
            }else
                Log.i(TAG, "getActivityList: not recomend"+gym.getUserId());
        }
        myApp.setGymMostPopular(listAll.get(0));

        List<Integer> gymPhotoId = new ArrayList<Integer>();
        gymPhotoId.add(listAll.get(0).getUserId());
        Log.i(TAG, "getActivityList   getUserId : "+listAll.get(0).getUserId());

        new Thread(new GetGymPhoto(new Handler(new GetCloseGymHandler()), gymPhotoId)).start();
    }
}
