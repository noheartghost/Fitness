package app.fitness.com.fitness.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import app.fitness.com.fitness.R;

public class ContactUsActivity extends AppCompatActivity {

    private ImageView contactus_backimg, online_iv, phone_iv, qq_iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        //返回
        contactus_backimg = (ImageView)findViewById(R.id.contactus_backimg);
        contactus_backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactUsActivity.this.finish();
            }
        });
        //在线客服
        online_iv = (ImageView)findViewById(R.id.online_iv);
        online_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactUsActivity.this, DevelopingActivity.class);
                startActivity(intent);
            }
        });
        //电话客服
        phone_iv = (ImageView)findViewById(R.id.phone_iv);
        phone_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:13102252331"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        //QQ客服
        qq_iv = (ImageView)findViewById(R.id.qq_iv);
        qq_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new  AlertDialog.Builder(ContactUsActivity.this)
                        .setTitle("QQ客服" )
                        .setMessage("客服QQ号：573780442" )
                        .setPositiveButton("确定" ,  null )
                        .show();
            }
        });
    }
}
