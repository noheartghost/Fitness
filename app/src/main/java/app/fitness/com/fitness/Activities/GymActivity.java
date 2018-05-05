package app.fitness.com.fitness.Activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.fitness.com.fitness.Adapters.GetCoachPhotos;
import app.fitness.com.fitness.Adapters.GetGymAllCard;
import app.fitness.com.fitness.Adapters.GetGymCoaches;
import app.fitness.com.fitness.Adapters.GetGymInfo;
import app.fitness.com.fitness.App.MyApp;
import app.fitness.com.fitness.MyAdapters.HorizontalListViewAdapter;
import app.fitness.com.fitness.R;
import app.fitness.com.fitness.tools.HorizontalListView;
import app.fitness.com.fitness.util.Coach;
import app.fitness.com.fitness.util.GymCardType;

public class GymActivity extends AppCompatActivity {

    public String GETCARDDETIAL = "get_card_detial";

    private MyApp myApp;

    private static final String TAG = "GymActivity";
    private Calendar calendar; // 通过Calendar获取系统时间
    private int mYear;
    private int mMonth;
    private int mDay;

    private int gymId, gymPhotoId;
    public static final int GYM_MOST_POPULAR = 0;
    public List<Coach> coachList = new ArrayList<Coach>();
    public List<Bitmap> coachPhotoList = new ArrayList<Bitmap>();
    public List<GymCardType> gymCardTypes = new ArrayList<GymCardType>();

    /**
     * 控件
     * @param savedInstanceState
     */
    private TextView orderfit, ordercoach, gymName, gymLocation,cardName, cardPrice;
    private ImageView gymPhoto, coach_img, backimg ;
    private ScrollView gym_scroll ;
    private Button mtimebutton, order_btn ;
    //横向列表
    private HorizontalListViewAdapter hlva;
    private HorizontalListView hlv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym);

        myApp = (MyApp) getApplicationContext();

        //返回按钮
        ImageView gym_backimg = (ImageView)findViewById(R.id.gym_backimg);
        gym_backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GymActivity.this.finish();
            }
        });

        //搜索按钮
        ImageView gym_search = (ImageView)findViewById(R.id.gym_search);
        gym_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GymActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        orderfit = (TextView) findViewById(R.id.orderfit);
        ordercoach = (TextView) findViewById(R.id.ordercoach);
        gymPhoto = (ImageView) findViewById(R.id.gym_photo);
        gymName = (TextView) findViewById(R.id.gym_name);
        gymLocation = (TextView) findViewById(R.id.gym_location);
        coach_img = (ImageView) findViewById(R.id.coach_img);
        cardName = (TextView)findViewById(R.id.gym_card_name);
        cardPrice = (TextView) findViewById(R.id.gym_card_price);
        //横向教练列表
        hlv=(HorizontalListView)findViewById(R.id.horizontallistview1);
        hlva=new HorizontalListViewAdapter(this,getCoachListData());
        hlva.notifyDataSetChanged();
        hlv.setAdapter(hlva);
        hlv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),position+"",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(GymActivity.this, CoachDetailActivity.class);
                /**
                 * 暂时传递教练列表中的第一个
                 */
                intent.putExtra("coach_avator",coachPhotoList.get(0));
                intent.putExtra("coach_character",coachList.get(0).getCharacteristic());
                intent.putExtra("coach_introduce",coachList.get(0).getIntroduce());
                intent.putExtra("coach_gym_name",gymName.getText().toString());
                intent.putExtra("coach_name", coachList.get(0).getName());
                intent.putExtra("coach_id", coachList.get(0).getUserId());
                startActivity(intent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Intent intent = getIntent();

        /**
         * 如果是从主页的本周最热场馆进来的，就直接从全局变量中获取各项属性
         * 否则就通过intent获取各项信息
         */
        if (intent.getIntExtra("gymType", 0)== GYM_MOST_POPULAR) {
            gymName.setText(myApp.getGymMostPopular().getName());
            gymLocation.setText(myApp.getGymMostPopular().getLocation());
            gymId = intent.getIntExtra("gymId",1);
            gymPhotoId = intent.getIntExtra("gymPhotoId",1);
            Log.i(TAG, "onCreate: gymid="+gymId);
            Log.i(TAG, "onCreate: gymPhotoId="+gymPhotoId);
        }else{

        }

//        获取健身房各项信息
        getGymInfo();

        coach_img = (ImageView) findViewById(R.id.coach_img);
        coach_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GymActivity.this, CoachListActivity.class);
                startActivity(intent);
            }
        });


        calendar = Calendar.getInstance();
        mtimebutton = (Button) findViewById(R.id.time_btn);
        mtimebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(GymActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int month, int day) {
                                // TODO Auto-generated method stub
                                mYear = year;
                                mMonth = month;
                                mDay = day;
                                // 更新EditText控件日期 小于10加0
                                mtimebutton.setText(new StringBuilder()
                                        .append(mYear)
                                        .append("-")
                                        .append((mMonth + 1) < 10 ? "0"
                                                + (mMonth + 1) : (mMonth + 1))
                                        .append("-")
                                        .append((mDay < 10) ? "0" + mDay : mDay));
                            }
                        }, calendar.get(Calendar.YEAR), calendar
                        .get(Calendar.MONTH), calendar
                        .get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        final Handler handler = new Handler();
        gym_scroll = (ScrollView) findViewById(R.id.gym_scroll);
        orderfit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        gym_scroll.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                });
            }
        });

        order_btn = (Button) findViewById(R.id.order_btn);
        order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GymActivity.this, OrderDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(GETCARDDETIAL, gymCardTypes.get(0));
                intent.putExtras(bundle);
                intent.putExtra("gymLocation", myApp.getGymMostPopular().getLocation());
                startActivity(intent);

            }
        });

        coach_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CoachDetailActivity.class);
                /**
                 * 暂时传递教练列表中的第一个
                 */
                intent.putExtra("coach_avator",coachPhotoList.get(0));
                intent.putExtra("coach_character",coachList.get(0).getCharacteristic());
                intent.putExtra("coach_introduce",coachList.get(0).getIntroduce());
                intent.putExtra("coach_gym_name",gymName.getText().toString());
                intent.putExtra("coach_name", coachList.get(0).getName());
                intent.putExtra("coach_id", coachList.get(0).getUserId());
                startActivity(intent);
            }
        });

    }

    //横向列表数据
    public List<Map<String, Object>> getCoachListData(){
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("coachimg", R.drawable.coach);
            list.add(map);
        }
        return list;
    }

    private void getGymInfo() {
        new Thread(new GetGymInfo(gymPhotoId, new Handler(new GetGymInfoHandler()))).start();
        new Thread(new GetGymAllCard(gymId,new Handler(new GetGymInfoHandler()))).start();
    }


    public class GetGymInfoHandler implements android.os.Handler.Callback{

        @Override
        public boolean handleMessage(Message message) {
            if (message.arg1==0) {
                //成功获取列表
                Log.i(TAG, "handleMessage: 0");
                gymPhoto.setImageBitmap((Bitmap) message.obj);
                //开始获取教练列表
                getCoachList();
            }else if (message.arg1==1) {
                //获取列表失败
                Log.i(TAG, "handleMessage: fail");

            }else if (message.arg1==2) {
                Log.i(TAG, "handleMessage: coach list success");
                Log.i(TAG, "handleMessage: "+message.obj.toString());
                getCoachInfo(message.obj.toString());
            }else if (message.arg1==3) {
                //获取列表失败
                Log.i(TAG, "handleMessage: coach list fail");
            }else if (message.arg1==4) {
                //获取教练头像成功
                Log.i(TAG, "handleMessage: coach photo list success");
                coachPhotoList = (List<Bitmap>) message.obj;
                /**
                 * 暂时先显示第一个教练的头像
                 * 之后将这里的布局变成教练列表
                 * 显示图像列表，用一个for循环实现
                 */
                coach_img.setImageBitmap(coachPhotoList.get(0));
            }else if (message.arg1==5) {
                //获取教练头像失败
                Log.i(TAG, "handleMessage: coach photo list fail");
            }else if (message.arg1==6) {
                //获取健身卡成功
                Log.i(TAG, "handleMessage: card list success");
                getCardInfo(message.obj.toString());
            }else if (message.arg1==7) {
                //获取健身卡失败
                Log.i(TAG, "handleMessage: card list fail");
            }
            return true;
        }

        private void getCardInfo(String s) {
            Log.i(TAG, "getCardInfo: " + s);
            Gson gson = new Gson();
            gymCardTypes = gson.fromJson(s, new TypeToken<List<GymCardType>>() {
            }.getType());
            for (GymCardType g:
                    gymCardTypes) {
                Log.i(TAG, "getCardInfo: cardName     "+g.getCardName());
            }
            /**
             * 在此处修改控件内容
             */
            cardName.setText(gymCardTypes.get(0).getCardName());
            cardPrice.setText(String.valueOf(gymCardTypes.get(0).getPrice()));
        }

        private void getCoachInfo(String s) {
            Log.i(TAG, "getCoachInfo: "+s);
            Gson gson = new Gson();
            coachList = gson.fromJson(s, new TypeToken<List<Coach>>() {
            }.getType());
            List<Integer> photoListId = new ArrayList<Integer>();
            for (Coach c:
                 coachList) {
                Log.i(TAG, "getCoachInfo: coachName     "+c.getName());
                photoListId.add(c.getUserId());
            }
            new Thread(new GetCoachPhotos(photoListId,new Handler(new GetGymInfoHandler()))).start();
        }

        private void getCoachList() {
            new Thread(new GetGymCoaches(gymId, new Handler(new GetGymInfoHandler()))).start();
        }
    }
}
