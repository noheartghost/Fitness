package app.fitness.com.fitness.Adapters;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import app.fitness.com.fitness.tools.NewPost;

/**
 * Created by Yinyanting on 2017/9/24.
 */

public class GetPostAndComment extends Thread {

    public static final String TAG = "GetPostAndComment";
    String url = "http://47.94.0.163:8080/fitness/GetFriendsPostServlet";
    Handler handler;
    Message message;
    int userid, cursor;

    public GetPostAndComment(int userid, Handler handler){
        this.userid = userid;
        this.cursor = 0;
        this.handler = handler;
        message = this.handler.obtainMessage();
    }

    public GetPostAndComment(int userid, Handler handler,int cursor){
        this.userid = userid;
        this.cursor = cursor;
        this.handler = handler;
        message = this.handler.obtainMessage();
    }

    @Override
    public void run() {
        NewPost newPost = new NewPost();
        String param = "userid="+userid+"&cursor="+cursor;
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
