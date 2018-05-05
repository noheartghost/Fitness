package app.fitness.com.fitness.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import app.fitness.com.fitness.R;

public class UserAgreementActivity extends AppCompatActivity {

    private ImageView useragrbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_agreement);

        //返回按钮
        useragrbtn = (ImageView)findViewById(R.id.useragreement_backimg);
        useragrbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserAgreementActivity.this.finish();
            }
        });
    }
}
