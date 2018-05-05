package app.fitness.com.fitness.Adapters;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;

import app.fitness.com.fitness.tools.NewPost;
import app.fitness.com.fitness.tools.StringChange;

import static android.R.attr.name;
import static android.R.attr.password;
import static android.R.attr.phoneNumber;
import static java.lang.String.valueOf;

/**
 * Created by Yinyanting on 2017/9/25.
 */

public class LikePost extends Thread {

    public static final String TAG = "LikePost";

    Handler handler;
    Message message;
    String url = "http://47.94.0.163:8080/fitness/LikePostServlet";
    public int postid;

    public LikePost(int postid,
                    Handler handler){
        this.postid = postid;
        this.handler = handler;
        message = this.handler.obtainMessage();
    }

    @Override
    public void run() {
        String param = "postid=" + postid;
        String result = "";
        NewPost newPost = new NewPost();
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
