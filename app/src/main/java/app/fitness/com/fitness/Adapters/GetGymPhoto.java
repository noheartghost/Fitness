package app.fitness.com.fitness.Adapters;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import app.fitness.com.fitness.tools.NewPost;

/**
 * Created by Yinyanting on 2017/9/3.
 */

public class GetGymPhoto extends Thread {
    public static final String TAG = "GetGymPhoto";
    String url = "http://47.94.0.163:8080/fitness/GetGymAvatarServlet";
    Handler handler;
    Message message;
    List<Integer> gymidList;

    public GetGymPhoto(Handler handler, List<Integer> gymidList){
        this.gymidList = gymidList;
        this.handler = handler;
        message = this.handler.obtainMessage();
    }

    @Override
    public void run() {
        NewPost newPost = new NewPost();
        List<Bitmap> gymPhotoList = new ArrayList<Bitmap>();
        for(int i=0;i<gymidList.size();i++){
            String param = "gymid="+gymidList.get(i);
            Bitmap result = null;
            result = newPost.sendPostAndGetPhoto(url, param);
            if(result==null)
                Log.i(TAG, "run: photo null");
            else
                Log.i(TAG, "run: photo not null");
            gymPhotoList.add(result);
        }
        if (gymPhotoList==null) {
            message.arg1 = 3;
            handler.sendMessage(message);
        }else {
            message.arg1 = 2;
            message.obj = gymPhotoList;
            Log.i(TAG, "run: success");
            handler.sendMessage(message);
        }
        super.run();
    }
}
