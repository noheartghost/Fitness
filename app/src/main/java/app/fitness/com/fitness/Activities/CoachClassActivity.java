package app.fitness.com.fitness.Activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import app.fitness.com.fitness.Adapters.GetCoachAllClasses;
import app.fitness.com.fitness.R;
import app.fitness.com.fitness.util.CoachClass;

public class CoachClassActivity extends AppCompatActivity {

    private static final String TAG = "CoachClassActivity";
    List<Map<String, Object>> list;
    int CoachId;
    ArrayList<CoachClass> coachClasses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_class);

        //返回
        ImageView coachclass_backimg = (ImageView)findViewById(R.id.coachclass_backimg);
        coachclass_backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CoachClassActivity.this.finish();
            }
        });

        CoachId = getIntent().getIntExtra("coach_id",0);

        GridView gridView=(GridView)findViewById(R.id.class_gridView);
        getData();
//        gridView.setAdapter(new ClassGridAdapter(this, list));

    }

    public void getData(){
        /**
         * 暂时显示的内容
         */
//        for (int i = 0; i < 49; i++) {
//            Map<String, Object> map=new HashMap<String, Object>();
//            if(i%5 == 1){
//                map.put("class_itembg", R.drawable.shi_0);
//                map.put("class_txt", "减脂");
//            }
//            else if(i%5 == 3){
//                map.put("class_itembg", R.drawable.shi_0);
//                map.put("class_txt", "增肌");
//            }
//            else{
//                map.put("class_itembg", R.drawable.kong_0);
//                map.put("class_txt", " ");
//            }
//            list.add(map);
//        }
        /**
         * 在网络上获取的内容
         */
        new Thread(new GetCoachAllClasses(CoachId,new Handler(new ReserveCoachHandler()))).start();
    }

    public class ReserveCoachHandler implements android.os.Handler.Callback{

        @Override
        public boolean handleMessage(Message msg) {
            if (msg.arg1==0){
                Log.i(TAG, "handleMessage: "+msg.obj.toString());
                getCoachClassInfo(msg.obj.toString());
            }else if (msg.arg1==1){
                Log.i(TAG, "handleMessage: fails");
            }
            return true;
        }
    }

    private void getCoachClassInfo(String s) {
        Log.i(TAG, "getCoachClassInfo");
        Gson gson = new Gson();
        coachClasses = gson.fromJson(s, new TypeToken<List<CoachClass>>() {
        }.getType());
        Log.i(TAG, "getCoachClassInfo: "+coachClasses.get(0).getName());

    }
}

