package app.fitness.com.fitness.Adapters;

import android.os.Handler;
import android.os.Message;

import java.security.NoSuchAlgorithmException;

import app.fitness.com.fitness.tools.NewPost;
import app.fitness.com.fitness.tools.StringChange;

import static android.R.attr.password;

/**
 * Created by Yinyanting on 2017/10/24.
 */

public class GetCoachPrice extends Thread  {

    public static final String TAG = "GetCoachPrice";

    int coachid;
    Handler handler;
    Message message;
    String url = "http://47.94.0.163:8080/fitness/GetCoachPriceServlet";

    public GetCoachPrice(int coachid, Handler handler){
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
