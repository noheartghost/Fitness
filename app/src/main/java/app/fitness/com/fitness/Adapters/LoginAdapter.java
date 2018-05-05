package app.fitness.com.fitness.Adapters;


import android.os.Handler;
import android.os.Message;

import java.security.NoSuchAlgorithmException;

import app.fitness.com.fitness.tools.NewPost;
import app.fitness.com.fitness.tools.StringChange;

/**
 * Created by Yinyanting on 2017/8/24.
 * used to login
 * need username and password
 */

public class LoginAdapter extends Thread{
    
    public static final String TAG = "LoginAdapter";

    String userName, password;
    Handler handler;
    Message message;
    String url = "http://47.94.0.163:8080/fitness/LoginServlet";

    public LoginAdapter(String userName, String password, Handler handler){
        this.userName = userName;
        this.password = password;
        this.handler = handler;
        message = this.handler.obtainMessage();
    }

    @Override
    public void run() {
        try {
            NewPost newPost = new NewPost();
            StringChange stringChange = new StringChange();
            /**
             * 之后在参数中添加的是password_afterchange，删除掉下面一行就可以了
             */
            String password_afterchange = stringChange.eccrypt(password);
            password = ",�b�Y\u0007[�K\u0007\u0015-#Kp";
            String param = "name="+userName+"&password="+password_afterchange;
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
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        super.run();

    }
}
