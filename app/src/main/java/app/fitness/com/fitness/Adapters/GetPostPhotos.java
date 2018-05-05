package app.fitness.com.fitness.Adapters;

/**
 * Created by Yinyanting on 2017/9/24.
 */

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import app.fitness.com.fitness.tools.NewPost;

/**
 * Created by Yinyanting on 2017/9/3.
 */

public class GetPostPhotos extends Thread {
    public static final String TAG = "GetGymPhoto";
    String url = "http://47.94.0.163:8080/fitness/GetPostPhotoServlet";
    Handler handler;
    Message message;
    List<List<Integer>> postPhotoId;

    public GetPostPhotos(Handler handler, List<List<Integer>> postPhotoId){
        this.postPhotoId = postPhotoId;
        this.handler = handler;
        message = this.handler.obtainMessage();
    }

    @Override
    public void run() {
        NewPost newPost = new NewPost();
        List<List<Bitmap>> gymPhotoList = new ArrayList<List<Bitmap>>();
        for (int i = 0; i < postPhotoId.size(); i++) {
            List<Bitmap> gymPhoto = new ArrayList<Bitmap>();
            for (int i1 = 0; i1 < postPhotoId.get(i).size(); i1++) {
                String param = "postPhotosId="+postPhotoId.get(i).get(i1);
                Bitmap result = null;
                result = newPost.sendPostAndGetPhoto(url, param);
                if(result==null)
                    Log.i(TAG, "run: photo null");
                else
                    Log.i(TAG, "run: photo not null,i:  "+i+"i1:  "+i1);
                gymPhoto.add(result);
            }
            gymPhotoList.add(gymPhoto);
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
