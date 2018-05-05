package app.fitness.com.fitness.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import app.fitness.com.fitness.App.MyApp;
import app.fitness.com.fitness.R;
import app.fitness.com.fitness.util.CardType;
import app.fitness.com.fitness.util.GymCardType;

public class OrderDetailActivity extends AppCompatActivity {
    public String GETCARDDETIAL = "get_card_detial";
    public static String TAG = "OrderDetailActivity";

    public TextView OrderName,
            OrderLocation,
            OrderTime,
            OrderUser,
            OrderTelephone,
            OrderExpectedArriveTime,
            OrderCardType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        //返回
        ImageView orderdetail_back = (ImageView)findViewById(R.id.orderdetail_back);
        orderdetail_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderDetailActivity.this.finish();
            }
        });
        //搜索
        ImageView orderdetail_search = (ImageView)findViewById(R.id.orderdetail_search);
        orderdetail_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderDetailActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        OrderName = (TextView) findViewById(R.id.order_detail_name);
        OrderLocation = (TextView) findViewById(R.id.order_detail_location);
        OrderTime = (TextView) findViewById(R.id.order_detail_time);
        OrderUser = (TextView) findViewById(R.id.order_detail_user);
        OrderTelephone = (TextView) findViewById(R.id.order_detail_telephone);
        OrderExpectedArriveTime = (TextView) findViewById(R.id.order_detail_expected_arrival_time);
        OrderCardType = (TextView) findViewById(R.id.order_detail_type);

        GymCardType gymCardType = (GymCardType) getIntent().getSerializableExtra(GETCARDDETIAL);
        String gymLocation = getIntent().getStringExtra("gymLocation");
        Log.i(TAG, "onCreate: "+gymCardType.getCardName());
        CardType cardType = new CardType();
        OrderCardType.setText(
                cardType.getCardType(
                        gymCardType.getType()
                )
        );

        OrderName.setText(gymCardType.getCardName());
        OrderLocation.setText(gymLocation);
        /**
         * 此处的时间属性后期可能会修改一下
         */
        OrderTime.setText((CharSequence) gymCardType.getOptionalBeginTime());
        MyApp myApp = (MyApp) getApplicationContext();
        OrderUser.setText(myApp.getUser().getName());



        LinearLayout order_submit = (LinearLayout) findViewById(R.id.order_submit);
        order_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 在这里引入支付宝或者微信控件
                 */
                Toast.makeText(getApplicationContext(),"预定成功！！！",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
