package app.fitness.com.fitness.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import app.fitness.com.fitness.R;

public class InviteFriendsActivity extends AppCompatActivity {

    private ImageView invitefri_btn;
    private Button invitefri_btn1,invitefri_btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_friends);

        //返回按钮
        invitefri_btn = (ImageView)findViewById(R.id.inviteFriends_backimg);
        invitefri_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InviteFriendsActivity.this.finish();
            }
        });
        //邀请好友，有冗余
        invitefri_btn1 = (Button)findViewById(R.id.invitefri_btn1);
        invitefri_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InviteFriendsActivity.this, MyFriendsActivity.class);
                startActivity(intent);
            }
        });
        invitefri_btn2 = (Button)findViewById(R.id.invitefri_btn2);
        invitefri_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InviteFriendsActivity.this, MyFriendsActivity.class);
                startActivity(intent);
            }
        });
    }
}
