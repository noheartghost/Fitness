package app.fitness.com.fitness.Adapters;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import app.fitness.com.fitness.tools.NewPost;

/**
 * Created by Yinyanting on 2017/8/28.
 * 在登录成功之后从网络端获取用户头像
 * 成功获取用户头像后将头像存储在本地后跳转
 */

public class GetUserPhoto extends Thread {

    public static final String TAG = "GetUserPhoto";
    String url = "http://47.94.0.163:8080/fitness/GetRegularUserAvatarServlet";
    Handler handler;
    Message message;
    int userid;

    public GetUserPhoto(Handler handler, int userid ){
        this.handler = handler;
        this.userid = userid;
        message = this.handler.obtainMessage();
    }

    public void run() {
        String param = "userid="+userid;
        NewPost newPost = new NewPost();
        Bitmap result = newPost.sendPostAndGetPhoto(url, param);
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
