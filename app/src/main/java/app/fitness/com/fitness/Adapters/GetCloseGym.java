package app.fitness.com.fitness.Adapters;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.security.NoSuchAlgorithmException;

import app.fitness.com.fitness.Activities.CloseActivity;
import app.fitness.com.fitness.tools.NewPost;
import app.fitness.com.fitness.tools.StringChange;

import static android.R.attr.password;

/**
 * Created by Yinyanting on 2017/9/1.
 */

public class GetCloseGym extends Thread {

    public static final String TAG = "GetCloseGym";
    String url = "http://47.94.0.163:8080/fitness/GetNear300DetailGymServlet";
    Handler handler;
    Message message;
    int userid;
    double locationx,locationy;

    public GetCloseGym(double locationx, double locationy, Handler handler){
        this.locationx = locationx;
        this.locationy = locationy;
        this.handler = handler;
        message = this.handler.obtainMessage();
    }

    @Override
    public void run() {

        NewPost newPost = new NewPost();
        String param = "locationx="+locationx+"&locationy="+locationy;
        String result = "";
        result = newPost.sendPost(url, param);
        if (result.isEmpty()) {
            message.arg1 = 1;
            handler.sendMessage(message);
        }else {
            message.arg1 = 0;
            message.obj = result;
            Log.i(TAG, "run: "+ result);
            handler.sendMessage(message);
        }

        super.run();
    }
}
