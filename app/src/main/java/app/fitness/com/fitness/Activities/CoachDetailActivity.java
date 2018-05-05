package app.fitness.com.fitness.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.fitness.com.fitness.MyAdapters.RoundAdapter;
import app.fitness.com.fitness.R;

public class CoachDetailActivity extends AppCompatActivity {

    public ImageView photo;
    public TextView Chara, Info, Gym,CoachName;
    public int coachId;
    public String  coach_avator,
            coach_character,
            coach_introduce,
            coach_gym_name,
            coach_name;

    public static String TAG = "CoachDetailActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_detail);

        //返回
        ImageView coachdetail_backimg = (ImageView)findViewById(R.id.coachdetail_backimg);
        coachdetail_backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CoachDetailActivity.this.finish();
            }
        });
        //搜索
        ImageView coachdetail_search = (ImageView)findViewById(R.id.coachdetail_search);
        coachdetail_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CoachDetailActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        photo = (ImageView) findViewById(R.id.coach_detail_photo);
        Chara = (TextView) findViewById(R.id.coach_detail_chara);
        Info = (TextView) findViewById(R.id.coach_detail_info);
        Gym = (TextView) findViewById(R.id.coach_detail_gym);
        CoachName = (TextView) findViewById(R.id.coach_detail_name);

        Intent intent = getIntent();
        coach_character = intent.getStringExtra("coach_character");
        coach_introduce = intent.getStringExtra("coach_introduce");
        coach_gym_name = intent.getStringExtra("coach_gym_name");
        coach_name = intent.getStringExtra("coach_name");
        coachId = intent.getIntExtra("coach_id",0);

        photo.setImageBitmap((Bitmap) intent.getParcelableExtra("coach_avator"));
        Chara.setText(coach_character);
        Info.setText(coach_introduce);
        Gym.setText(coach_gym_name);
        CoachName.setText(coach_name);



        ListView listView=(ListView)findViewById(R.id.coach_round_list);
        List<Map<String, Object>> list=getData();
        listView.setAdapter(new RoundAdapter(this, list));

        TextView coach_class_txt = (TextView) findViewById(R.id.coach_class_txt);
        coach_class_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CoachDetailActivity.this,CoachClassActivity.class);
                intent.putExtra("coach_id",coachId);
                startActivity(intent);
            }
        });

        TextView ordercoach = (TextView) findViewById(R.id.ordercoach);
        ordercoach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ReserveCoachActivity.class);
                intent.putExtra("coach_id",coachId);
                intent.putExtra("coach_name",coach_name);
                startActivity(intent);
            }
        });

    }

    public List<Map<String, Object>> getData(){
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
        for (int i = 0; i < 5; i++) {
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("image", R.drawable.head);
            map.put("title", "Melissa House");
            map.put("time", "5"+i+"分钟前");
            map.put("contentPicture", R.drawable.round);
            list.add(map);
        }
        return list;
    }

    public class CoachDetailHandler implements android.os.Handler.Callback{

        @Override
        public boolean handleMessage(Message msg) {
            if (msg.arg1==0){
                Log.i(TAG, "handleMessage: "+msg.obj.toString());
                Toast.makeText(getApplicationContext(),"预约成功",Toast.LENGTH_LONG).show();
            }else{
                Log.i(TAG, "handleMessage: order fails");
                Toast.makeText(getApplicationContext(),"预约失败",Toast.LENGTH_LONG).show();

            }
            return true;
        }
    }
}

