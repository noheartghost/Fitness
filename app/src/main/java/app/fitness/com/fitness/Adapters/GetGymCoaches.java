package app.fitness.com.fitness.Adapters;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import app.fitness.com.fitness.tools.NewPost;

/**
 * Created by Yinyanting on 2017/9/27.
 */

public class GetGymCoaches extends Thread {
    public static final String TAG = "GetGymInfo";
    String url = "http://47.94.0.163:8080/fitness/GetCoachsOfGYMServlet";
    Handler handler;
    Message message;
    int gymid;

    public GetGymCoaches(int gymid, Handler handler){
        this.gymid = gymid;
        this.handler = handler;
        message = this.handler.obtainMessage();
    }

    @Override
    public void run() {
        Log.i(TAG, "run: "+gymid);
        gymid = 12;
        String param = "gymid="+gymid;
        NewPost newPost = new NewPost();
        String result = newPost.sendPost (url, param);
        if (result==null) {
            message.arg1 = 3;
            handler.sendMessage(message);
        }else {
            message.arg1 = 2;
            message.obj = result;
            handler.sendMessage(message);
        }

        super.run();
    }
}
