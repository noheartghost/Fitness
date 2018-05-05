package app.fitness.com.fitness.Adapters;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;

import app.fitness.com.fitness.tools.NewPost;

/**
 * Created by Yinyanting on 2017/9/23.
 */

public class GetGymInfo extends Thread {

    public static final String TAG = "GetGymInfo";
    String url = "http://47.94.0.163:8080/fitness/GetGymPhotoServlet";
    Handler handler;
    Message message;
    int gymphotosid;

    public GetGymInfo(int gymphotosid, Handler handler){
        this.gymphotosid = gymphotosid;
        this.handler = handler;
        message = this.handler.obtainMessage();
    }

    public void run() {
//        gymphotosid = 1;
        String param = "gymphotosid="+gymphotosid;
        NewPost newPost = new NewPost();
        Bitmap result = newPost.sendPostAndGetPhoto(url, param);
        if (result==null) {
            message.arg1 = 1;
            handler.sendMessage(message);
        }else {
            message.arg1 = 0;
            message.obj = result;
            handler.sendMessage(message);
        }
        super.run();
    }

}
