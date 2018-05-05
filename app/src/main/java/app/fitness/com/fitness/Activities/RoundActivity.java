package app.fitness.com.fitness.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.fitness.com.fitness.Adapters.GetPostAndComment;
import app.fitness.com.fitness.Adapters.GetPostPhotos;
import app.fitness.com.fitness.App.MyApp;
import app.fitness.com.fitness.MyAdapters.RoundAdapter;
import app.fitness.com.fitness.R;
import app.fitness.com.fitness.util.PostAndComments;


public class RoundActivity extends AppCompatActivity {

    private static final String TAG = "RoundActivity";

//    对应显示列表数据的控件
    private ListView listView = null;
//    定义一次性显示帖子的个数
    public int CURSOR = 10;
    public List<Map<String, Object>> list;
//    网络获取的帖子以及评论列表
    public List<PostAndComments> postAndComments;
//    对于一个帖子可能会有多张图片，所以为二层列表
//    现在先显示一张图片，获取全部图片
    public List<List<Bitmap>> postAndCommentPictures;

    public MyApp myApp;

//    数据全部获取完成的开关
    public boolean isFinished = false;

//    图标控件
    public ImageView loveImage,reviewImage;

    private ImageView editiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round);

        myApp = (MyApp) getApplicationContext();

        initBottom();

        listView=(ListView)findViewById(R.id.round_list);
        list = new ArrayList<Map<String, Object>>();
        getData();

        editiv = (ImageView)findViewById(R.id.editiv);
        editiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RoundActivity.this, EditQuanziActivity.class);
                startActivity(intent);
            }
        });

//        loveImage= (ImageView) findViewById(R.id.love_image);
//        reviewImage = (ImageView) findViewById(R.id.review_image);
//
//        loveImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                while (!isFinished){}
//                Log.i(TAG, "onClick: can click");
//
//            }
//        });
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
                Intent intent = new Intent(RoundActivity.this, ChosenActivity.class);
                startActivity(intent);
                RoundActivity.this.finish();
            }
        });

        ImageView quanzi_img = (ImageView) findViewById(R.id.quanzi_img);
        TextView quanzi_txt = (TextView)  findViewById(R.id.bottom_qz_tv);
        quanzi_img.setImageResource(R.drawable.quanzi);
        quanzi_txt.setTextColor(Color.BLACK);

        ImageView dingdan_img = (ImageView) findViewById(R.id.dingdan_img);
        TextView dingdan_txt = (TextView)  findViewById(R.id.bottom_dd_tv);
        dingdan_img.setImageResource(R.drawable.dingdan_p);
        dingdan_txt.setTextColor(Color.WHITE);
        dingdan_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RoundActivity.this, DealActivity.class);
                startActivity(intent);
                RoundActivity.this.finish();
            }
        });

        ImageView wode_img = (ImageView) findViewById(R.id.wode_img);
        TextView wode_txt = (TextView)  findViewById(R.id.bottom_wd_tv);
        wode_img.setImageResource(R.drawable.wode_p);
        wode_txt.setTextColor(Color.WHITE);
        wode_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RoundActivity.this, MineActivity.class);
                startActivity(intent);
                RoundActivity.this.finish();
            }
        });
    }


    public void getData(){

        new Thread(
                new GetPostAndComment(
                        myApp.getUser().getUserId(),new Handler(new GetPostAndCommentHandler())
                )
        ).start();
    }

    public class GetPostAndCommentHandler implements android.os.Handler.Callback{

        @Override
        public boolean handleMessage(Message message) {
            if (message.arg1==0) {
                //成功获取列表
                Log.i(TAG, "handleMessage: "+ message.obj.toString());
                extractPostAndComment(message.obj.toString());

            }else if (message.arg1==1) {
                //获取列表失败
                Log.i(TAG, "handleMessage: fail");

            }else if (message.arg1==2) {
                Log.i(TAG, "handleMessage: photo success");
                postAndCommentPictures = (List<List<Bitmap>>) message.obj;
                Log.i(TAG, "gymPhoto size: "+ postAndCommentPictures.size());
                showData();
            }else if (message.arg1==3) {
                //获取列表失败
                Log.i(TAG, "handleMessage: photo fail");
            }
            return true;
        }
    }

    /**
     * 将网络字符串解析成对象
     * @param Input
     */
    private void extractPostAndComment(String Input) {
        Gson gson = new Gson();
        postAndComments = gson.fromJson(Input, new TypeToken<List<PostAndComments>>() {
        }.getType());
        Log.i(TAG, "extractPostAndComment: "+postAndComments.get(0).getPost().getTitle());

        /**
         * 在成功获取列表之后，需要根据id获取帖子图片列表
         */
        List<List<Integer>> postPhotoId = new ArrayList<>();
        for(int i = 0; i < postAndComments.size(); i++){
            postPhotoId.add(postAndComments.get(i).getPost().getPhotos());
        }
        Log.i(TAG, "extractPostAndComment: "+postAndComments.size());
        new Thread(new GetPostPhotos(new Handler(new GetPostAndCommentHandler()),postPhotoId)).start();
    }

    public void showData(){
        for (int i = 0; i < postAndComments.size(); i++) {
            Log.i(TAG, "showData: "+i);
            Map<String, Object> map=new HashMap<String, Object>();
            /**
             * 将前台改好之后，需要将图片全部显示，不再是第一张图片
             */
            map.put("image", R.drawable.head);
            map.put("title", postAndComments.get(i).getPost().getTitle());
            long timetemp = postAndComments.get(i).getPost().getCreateTime().getTime();
            Timestamp time = new Timestamp(timetemp);
            map.put("time", time.toString());
            map.put("content",postAndComments.get(i).getPost().getContent());
            if (postAndCommentPictures.get(i).size()==0) {
                map.put("contentPicture", R.drawable.round);
            }else
                map.put("contentPicture", postAndCommentPictures.get(i).get(0));
            list.add(map);
        }
        listView.setAdapter(new RoundAdapter(this, list));
        isFinished = true;

    }

}
