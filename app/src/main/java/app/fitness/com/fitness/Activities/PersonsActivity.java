package app.fitness.com.fitness.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.fitness.com.fitness.MyAdapters.RoundAdapter;
import app.fitness.com.fitness.R;

public class PersonsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persons);

        ListView listView=(ListView)findViewById(R.id.person_round_list);
        List<Map<String, Object>> list=getData();
        listView.setAdapter(new RoundAdapter(this, list));

    }

    public List<Map<String, Object>> getData(){
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
        for (int i = 0; i < 5; i++) {
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("image", R.drawable.head);
            map.put("title", "Melissa House");
            map.put("time", "5"+i+"分钟前");
            map.put("content","fdgdfgfgfhfhf");
            map.put("contentPicture", R.drawable.round);
            list.add(map);
        }
        return list;
    }
}

