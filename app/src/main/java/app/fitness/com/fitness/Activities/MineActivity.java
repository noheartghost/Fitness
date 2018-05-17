package app.fitness.com.fitness.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import app.fitness.com.fitness.App.MyApp;
import app.fitness.com.fitness.R;

public class MineActivity extends AppCompatActivity {

    private LinearLayout friends_ll,collection_ll,cardbox_ll;
    private ImageView minePhoto;
    private TextView mineName;
    private MyApp myApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);

        minePhoto = (ImageView) findViewById(R.id.mine_photo);
        mineName = (TextView) findViewById(R.id.mine_name);
        myApp = (MyApp)getApplicationContext();

        minePhoto.setImageBitmap(myApp.getUserImage());
        mineName.setText(myApp.getUser().getName());


        friends_ll = (LinearLayout) findViewById(R.id.friends_ll);
        friends_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MineActivity.this, InviteFriendsActivity.class);
                startActivity(intent);
            }
        });

        collection_ll = (LinearLayout) findViewById(R.id.collection_ll);
        collection_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MineActivity.this, MyCollectionActivity.class);
                startActivity(intent);
            }
        });

        cardbox_ll = (LinearLayout) findViewById(R.id.cardbox_ll);
        cardbox_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MineActivity.this, CardBoxActivity.class);
                startActivity(intent);
            }
        });

        initBottom();

        //个人信息
        TextView mydetails = (TextView)findViewById(R.id.mydetails);
        mydetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MineActivity.this,DevelopingActivity.class);
                startActivity(intent);
            }
        });
        //设置
        LinearLayout set_ll = (LinearLayout) findViewById(R.id.set_ll);
        set_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MineActivity.this,DevelopingActivity.class);
                startActivity(intent);
            }
        });
        //用户协议
        LinearLayout userdeal_ll = (LinearLayout) findViewById(R.id.userdeal_ll);
        userdeal_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MineActivity.this,UserAgreementActivity.class);
                startActivity(intent);
            }
        });
        //联系我们
        LinearLayout contactus_ll = (LinearLayout) findViewById(R.id.contactus_ll);
        contactus_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MineActivity.this,ContactUsActivity.class);
                startActivity(intent);
            }
        });
        //关于我们
        LinearLayout aboutus_ll = (LinearLayout) findViewById(R.id.aboutus_ll);
        aboutus_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MineActivity.this,AboutUsActivity.class);
                startActivity(intent);
            }
        });
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
                Intent intent = new Intent(MineActivity.this, ChosenActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                MineActivity.this.finish();
            }
        });

        ImageView quanzi_img = (ImageView) findViewById(R.id.quanzi_img);
        TextView quanzi_txt = (TextView)  findViewById(R.id.bottom_qz_tv);
        quanzi_img.setImageResource(R.drawable.quanzi_p);
        quanzi_txt.setTextColor(Color.WHITE);
        quanzi_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MineActivity.this, RoundActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                MineActivity.this.finish();
            }
        });

        ImageView dingdan_img = (ImageView) findViewById(R.id.dingdan_img);
        TextView dingdan_txt = (TextView)  findViewById(R.id.bottom_dd_tv);
        dingdan_img.setImageResource(R.drawable.dingdan_p);
        dingdan_txt.setTextColor(Color.WHITE);
        dingdan_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MineActivity.this, DealActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                MineActivity.this.finish();
            }
        });

        ImageView wode_img = (ImageView) findViewById(R.id.wode_img);
        TextView wode_txt = (TextView)  findViewById(R.id.bottom_wd_tv);
        wode_img.setImageResource(R.drawable.wode);
        wode_txt.setTextColor(Color.BLACK);
    }

}
