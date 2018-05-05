package app.fitness.com.fitness.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.fitness.com.fitness.MyAdapters.FoodGridAdapter;
import app.fitness.com.fitness.R;

public class FoodActivity extends AppCompatActivity {

    private GridView gridView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        gridView=(GridView)findViewById(R.id.food_gridView);
        List<Map<String, Object>> list=getData();
        gridView.setAdapter(new FoodGridAdapter(this, list));

        //返回按钮
        ImageView food_backimg = (ImageView)findViewById(R.id.food_backimg);
        food_backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoodActivity.this.finish();
            }
        });

        //搜索按钮
        ImageView food_search = (ImageView)findViewById(R.id.food_search);
        food_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FoodActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public List<Map<String, Object>> getData(){
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
        for (int i = 0; i < 6; i++) {
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("grid_img", R.drawable.food1);
            map.put("grid_name", "均衡饮食");
            map.put("grid_feature", "特色优点等等等等等");
            list.add(map);
        }
        return list;
    }
}
