package app.fitness.com.fitness.Adapters;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import app.fitness.com.fitness.tools.NewPost;

/**
 * Created by Yinyanting on 2017/10/18.
 */

public class GetGymAllCard extends Thread {

    public static final String TAG = "GetGymAllCard";
    String url = "http://47.94.0.163:8080/fitness/GetAllCardOfGYMServlet";
    Handler handler;
    Message message;
    int gymid;

    public GetGymAllCard(int gymid, Handler handler){
        this.gymid = gymid;
        this.handler = handler;
        message = this.handler.obtainMessage();
    }

    @Override
    public void run() {
        NewPost newPost = new NewPost();
        String param = "gymid="+gymid ;
        String result = "";
        result = newPost.sendPost(url, param);
        if (result.isEmpty()) {
            message.arg1 = 7;
            handler.sendMessage(message);
        }else {
            message.arg1 = 6;
            message.obj = result;
            Log.i(TAG, "run: "+ result);
            handler.sendMessage(message);
        }
        super.run();
    }

}
