package app.fitness.com.fitness.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import app.fitness.com.fitness.R;

public class AboutUsActivity extends AppCompatActivity {

    private ImageView about_backimg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        about_backimg = (ImageView)findViewById(R.id.about_backimg);
        about_backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AboutUsActivity.this.finish();
            }
        });
    }
}
