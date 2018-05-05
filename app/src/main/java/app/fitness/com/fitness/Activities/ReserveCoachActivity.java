package app.fitness.com.fitness.Activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import app.fitness.com.fitness.Adapters.GetCoachPrice;
import app.fitness.com.fitness.Adapters.ReserveCoach;
import app.fitness.com.fitness.R;

public class ReserveCoachActivity extends AppCompatActivity {

    private static final String TAG = "ReserveCoachActivity";
    Button ReserveCoach;
   // TextView ReserveCoachPrice;
    int CoachId;
    String CoachName;
    private Button st_time,en_time;
    private TextView sum_price_tv;

    private Calendar calender;
    private Date date1,date2,sub_date;
    private int mYear;
    private int mMonth;
    private int mDay;
    private long sum_price;
    private int per_price=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_coach);

        //返回
        ImageView reserve_backimg = (ImageView)findViewById(R.id.reserve_backimg);
        reserve_backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReserveCoachActivity.this.finish();
            }
        });
        //搜索
        ImageView reserve_search = (ImageView)findViewById(R.id.reserve_search);
        reserve_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReserveCoachActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        sum_price_tv = (TextView)findViewById(R.id.sum_coach_price);

        per_price = 300;

        //选择时间
        calender = Calendar.getInstance();
        st_time = (Button)findViewById(R.id.st_time_btn);
        st_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(ReserveCoachActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int month, int day) {
                                // TODO Auto-generated method stub
                                mYear = year;
                                mMonth = month;
                                mDay = day;
                                st_time.setText(new StringBuilder()
                                        .append(mYear)
                                        .append("-")
                                        .append((mMonth + 1) < 10 ? "0"
                                                + (mMonth + 1) : (mMonth + 1))
                                        .append("-")
                                        .append((mDay < 10) ? "0" + mDay : mDay));
                                try{
                                    //Toast.makeText(getApplicationContext(),st_time.getText().toString(),Toast.LENGTH_LONG).show();
                                    date1 = df.parse(st_time.getText().toString());
                                    //Toast.makeText(getApplicationContext(),""+date1,Toast.LENGTH_LONG).show();
                                    if(date1.getTime()<date2.getTime()){
                                        long diff = date2.getTime()-date1.getTime();
                                        sum_price = diff/(1000*60*60*24)*per_price;
                                        sum_price_tv.setText("总计："+sum_price+"元");
                                    }
                                }catch(Exception e){
                                    //Toast.makeText(getApplicationContext(),"失败！！！",Toast.LENGTH_LONG).show();
                                }
                            }
                        }, calender.get(Calendar.YEAR), calender
                        .get(Calendar.MONTH), calender
                        .get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        calender = Calendar.getInstance();
        en_time = (Button)findViewById(R.id.en_time_btn);
        en_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(ReserveCoachActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int month, int day) {
                                // TODO Auto-generated method stub
                                mYear = year;
                                mMonth = month;
                                mDay = day;
                                en_time.setText(new StringBuilder()
                                        .append(mYear)
                                        .append("-")
                                        .append((mMonth + 1) < 10 ? "0"
                                                + (mMonth + 1) : (mMonth + 1))
                                        .append("-")
                                        .append((mDay < 10) ? "0" + mDay : mDay));
                                try{
                                    date2 = df.parse(en_time.getText().toString());
                                    //Toast.makeText(getApplicationContext(),date2+"",Toast.LENGTH_LONG).show();
                                    if(date1.getTime()<date2.getTime()){
                                        long diff = date2.getTime()-date1.getTime();
                                        sum_price = diff/(1000*60*60*24)*per_price;
                                        sum_price_tv.setText("总计："+sum_price+"元");
                                    }
                                }catch(Exception e){
                                   // Toast.makeText(getApplicationContext(),"失败！！！",Toast.LENGTH_LONG).show();
                                }
                            }
                        }, calender.get(Calendar.YEAR), calender
                        .get(Calendar.MONTH), calender
                        .get(Calendar.DAY_OF_MONTH)).show();

            }
        });



        ReserveCoach = (Button) findViewById(R.id.reserve_coach_reserve);
        //ReserveCoachPrice = (TextView) findViewById(R.id.reserve_coach_price);
        CoachId = getIntent().getIntExtra("coach_id",0);
        CoachName = getIntent().getStringExtra("coach_name");
        new Thread(new GetCoachPrice(CoachId,new Handler(new ReserveCoachHandler()))).start();
        ReserveCoach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new ReserveCoach(new Handler(new ReserveCoachHandler()))).start();
            }
        });

    }


    public class ReserveCoachHandler implements android.os.Handler.Callback{

        @Override
        public boolean handleMessage(Message msg) {
            if (msg.arg1==0){
                Log.i(TAG, "handleMessage: "+msg.obj.toString());
                Toast.makeText(getApplicationContext(),"预约成功",Toast.LENGTH_LONG).show();
            }else if (msg.arg1==1){
                Log.i(TAG, "handleMessage: order fails");
                Toast.makeText(getApplicationContext(),"预约失败",Toast.LENGTH_LONG).show();
            }else if (msg.arg1==2){
                Log.i(TAG, "handleMessage: 获取价格成功"+msg.obj.toString());
                //ReserveCoachPrice.setText(msg.obj.toString());

            }else if (msg.arg1==3){
                Log.i(TAG, "handleMessage: 获取价格失败");
            }
            return true;
        }
    }

}
