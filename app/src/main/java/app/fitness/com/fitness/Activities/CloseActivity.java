package app.fitness.com.fitness.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.fitness.com.fitness.Adapters.GetCloseGym;
import app.fitness.com.fitness.Adapters.GetGymPhoto;
import app.fitness.com.fitness.MyAdapters.CloseGridAdapter;
import app.fitness.com.fitness.R;
import app.fitness.com.fitness.util.GymNear;

public class CloseActivity extends AppCompatActivity {

    public static final String TAG = "CloseActivity";
    private GridView gridView = null;
    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>() ;
    List<GymNear> listAll=new ArrayList<GymNear>();
    List<Bitmap> gymPhoto = new ArrayList<Bitmap>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_close);

        //返回按钮
        ImageView close_backimg = (ImageView)findViewById(R.id.close_backimg);
        close_backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CloseActivity.this.finish();
            }
        });
        //搜索按钮
        ImageView chosen_search_img = (ImageView) findViewById(R.id.chosen_search_btn);
        chosen_search_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CloseActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        gridView=(GridView)findViewById(R.id.gridView);

        getData();


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

//        for (int i = 0; i < 6; i++) {
//            Map<String, Object> map=new HashMap<String, Object>();
//            map.put("grid_img", R.drawable.gym1);
//            map.put("grid_name", "宝力豪健身");
//            map.put("grid_feature", "特色优点等等等等等");
//            list.add(map);
//        }
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
                setPhotoAndData();
            }else if (message.arg1==3) {
                //获取列表失败
                Log.i(TAG, "handleMessage: photo fail");

            }
            return true;
        }
   }

   public void getActivityList(String Input){
//       List<Map<String, Object>> listShow=new ArrayList<Map<String,Object>>();
       Gson gson = new Gson();
       listAll = gson.fromJson(Input, new TypeToken<List<GymNear>>() {
       }.getType());
       /**
        * 在成功获取列表之后，需要根据id获取健身房照片
        */
       List<Integer> gymPhotoId = new ArrayList<Integer>();
       for(int i = 0; i < listAll.size(); i++){
           gymPhotoId.add(listAll.get(i).getUserId());
       }

       new Thread(new GetGymPhoto(new Handler(new GetCloseGymHandler()), gymPhotoId)).start();

       /**
        * 测试所用
        */
//       Log.i(TAG, "getActivityList: "+listAll.size());
//       Log.i(TAG, "getActivityList: "+listAll.get(2).getEmail());
//       Log.i(TAG, "getActivityList: "+listAll.get(2).getIntroduce());
//       Log.i(TAG, "getActivityList: "+listAll.get(2).getLocation());
//       Log.i(TAG, "getActivityList: "+listAll.get(2).getName());
//       Log.i(TAG, "getActivityList: "+listAll.get(2).getPassword());
//       Log.i(TAG, "getActivityList: "+listAll.get(2).getPhoneNumber());
//       Log.i(TAG, "getActivityList: "+listAll.get(2).getAvatar());
//       Log.i(TAG, "getActivityList: "+listAll.get(2).getDistance());
//       Log.i(TAG, "getActivityList: "+listAll.get(2).getGymPhotos());
//       Log.i(TAG, "getActivityList: "+listAll.get(2).getLocationX());
//       Log.i(TAG, "getActivityList: "+listAll.get(2).getLocationY());
//       Log.i(TAG, "getActivityList: "+listAll.get(2).getTags());
//       Log.i(TAG, "getActivityList: "+listAll.get(2).getType());
//       Log.i(TAG, "getActivityList: "+listAll.get(2).getUserId());
//       Log.i(TAG, "getActivityList: "+listAll.get(2).isSex());
//       Log.i(TAG, "getActivityList: "+listAll.get(2).isrecommend());


    }
   public void setPhotoAndData(){
       int recommend = 0;
       for (int i = 0; i < listAll.size(); i++) {
           Log.i(TAG, "map: "+i);
           Map<String, Object> map=new HashMap<String, Object>();
           map.put("grid_img", gymPhoto.get(i));
           map.put("grid_name", listAll.get(i).getName());
           map.put("grid_feature", listAll.get(i).getIntroduce());
           if(listAll.get(i).isrecommend())
               recommend = i;
           list.add(map);
       }
       BitmapDrawable drawable = new BitmapDrawable(gymPhoto.get(recommend));
       findViewById(R.id.close_reco_photo).setBackground(drawable);
       final TextView textView = (TextView) findViewById(R.id.close_reco_textId);
       textView.setText(listAll.get(recommend).getName());
       final TextView textView2 = (TextView) findViewById(R.id.close_reco_feature);
       textView2.setText(listAll.get(recommend).getIntroduce());

       gridView.setAdapter(new CloseGridAdapter(this, list));
   }

}
