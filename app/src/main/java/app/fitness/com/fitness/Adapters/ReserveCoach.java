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
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;

import app.fitness.com.fitness.tools.NewPost;
import app.fitness.com.fitness.tools.StringChange;
import app.fitness.com.fitness.util.CreateTime;

import static android.R.attr.password;
import static java.lang.String.valueOf;

/**
 * Created by Yinyanting on 2017/10/23.
 */

public class ReserveCoach extends Thread {

    public static final String TAG = "ReserveCoach";
    String url = "http://47.94.0.163:8080/fitness/ReserveCoachServlet";
    Handler handler;
    Message message;
    public int coachid,
            userid;
    public String beginTime, endTime, createTime, arriveTime;
    public double price;
    String contactPhone;

    public ReserveCoach(Handler handler){
        this.coachid = 17;
        this.userid = 9;
        this.beginTime = "1509465600000";
        this.endTime = "1511107200000";
        this.createTime = "150848957000";
        this.price = 2980.0;
        this.arriveTime = "15094600000";
        this.contactPhone = "13312756541";
        this.handler = handler;
        message = this.handler.obtainMessage();
    }

    public ReserveCoach(int coachid,
                        int userid,
                        String beginTime,
                        String endTime,
                        String createTime,
                        String arriveTime,
                        String contactPhone,
                        double price,
                        Handler handler){
        this.coachid = coachid;
        this.userid = userid;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.createTime = createTime;
        this.price = price;
        this.arriveTime = arriveTime;
        this.contactPhone = contactPhone;
        this.handler = handler;
        message = this.handler.obtainMessage();
    }

    @Override
    public void run(){
        NewPost newPost = new NewPost();
        String param = "coachid="+coachid+"&"
                + "userid="+userid+"&"
                + "beginTime="+beginTime+"&"
                + "createTime="+createTime+"&"
                + "endTime="+endTime+"&"
                + "price="+price+"&"
                + "arriveTime="+arriveTime+"&"
                + "contactPhone="+contactPhone;
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
