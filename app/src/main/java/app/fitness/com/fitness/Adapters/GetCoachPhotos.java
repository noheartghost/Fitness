package app.fitness.com.fitness.Adapters;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import app.fitness.com.fitness.tools.NewPost;

/**
 * Created by Yinyanting on 2017/10/18.
 */

public class GetCoachPhotos extends Thread {
    public static final String TAG = "GetCoachPhotos";
    String url = "http://47.94.0.163:8080/fitness/GetCoachAvatarServlet";
    Handler handler;
    Message message;
    List<Integer> photoListId;

    public GetCoachPhotos(List<Integer> photoListId, Handler handler){
        this.photoListId = photoListId;
        this.handler = handler;
        message = this.handler.obtainMessage();
    }

    @Override
    public void run() {
        NewPost newPost = new NewPost();
        List<Bitmap> gymPhotoList = new ArrayList<Bitmap>();
        for(int i=0;i<photoListId.size();i++){
            String param = "coachid="+photoListId.get(i);
            Bitmap result = null;
            result = newPost.sendPostAndGetPhoto(url, param);
            if(result==null)
                Log.i(TAG, "run: photo null");
            else
                Log.i(TAG, "run: photo not null");
            gymPhotoList.add(result);
        }
        if (gymPhotoList==null) {
            message.arg1 = 5;
            handler.sendMessage(message);
        }else {
            message.arg1 = 4;
            message.obj = gymPhotoList;
            Log.i(TAG, "run: success");
            handler.sendMessage(message);
        }

        super.run();
    }
}
