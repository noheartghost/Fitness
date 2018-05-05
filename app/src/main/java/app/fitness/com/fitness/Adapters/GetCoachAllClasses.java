package app.fitness.com.fitness.Adapters;

import android.os.Handler;
import android.os.Message;

import app.fitness.com.fitness.tools.NewPost;

/**
 * Created by Yinyanting on 2017/10/24.
 */

public class GetCoachAllClasses extends Thread {

    public static final String TAG = "GetCoachAllClasses";

    int coachid;
    Handler handler;
    Message message;
    String url = "http://47.94.0.163:8080/fitness/Get500ClassesServlet";

    public GetCoachAllClasses(int coachid, Handler handler){
        this.coachid = coachid;
        this.handler = handler;
        message = this.handler.obtainMessage();
    }

    @Override
    public void run() {
        NewPost newPost = new NewPost();
        String param = "coachid="+coachid;
        String result = "";
        result = newPost.sendPost(url, param);
        if (result.isEmpty()) {
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
