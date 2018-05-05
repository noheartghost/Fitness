package app.fitness.com.fitness.Adapters;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

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

import app.fitness.com.fitness.tools.StringChange;

import static java.lang.String.valueOf;

/**
 * Created by Yinyanting on 2017/9/4.
 */

public class Register extends Thread {

    public static final String TAG = "Register";

    public String name;
    public String phoneNumber ;
    public String password ;
    public String passwordTwice;
    public boolean gender ;
    public String email ;
    Handler handler;
    Message message;
    String url = "http://47.94.0.163:8080/fitness/RegisterServlet";

    public Register(String name,
                    String phoneNumber,
                    String password,
                    boolean gender,
                    String email,
                    Handler handler){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.gender = gender;
        this.email = email;
        this.handler = handler;
        message = this.handler.obtainMessage();
    }

    @Override
    public void run() {

        HttpClient httpclient = new DefaultHttpClient();

        try {

            HttpPost httppost = new HttpPost(url);

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setCharset(Charset.forName(HTTP.UTF_8));//设置请求的编码格式
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);//设置浏览器兼容模式
            int count=0;

            ContentType contentType = ContentType.create(HTTP.PLAIN_TEXT_TYPE, HTTP.UTF_8);


            builder.addTextBody("email", email);//设置请求参数
            builder.addTextBody("sex", valueOf(gender));//设置请求参数
            builder.addTextBody("phoneNumber",phoneNumber);

            StringBody stringBody=new StringBody(name,contentType);
            builder.addPart("name", stringBody);

            StringChange stringChange = new StringChange();
            String password_afterchange = stringChange.eccrypt(password);
            stringBody=new StringBody(password_afterchange,contentType);
            builder.addPart("password", stringBody);

            Timestamp t=new Timestamp(115, 1,1, 10, 0, 0, 0);
            System.out.println(t.getTime());
            builder.addTextBody("beginTime", valueOf(t.getTime()));
            Timestamp t2=new Timestamp(115, 3, 3, 12, 0, 0, 0);
            builder.addTextBody("endTime", valueOf(t2.getTime()));
            HttpEntity entity = builder.build();// 生成 HTTP POST 实体
            httppost.setEntity(entity);

            HttpResponse response = httpclient.execute(httppost);

            int statusCode = response.getStatusLine().getStatusCode();

            if(statusCode == HttpStatus.SC_OK){
                System.out.println("服务器正常响应.....");
                HttpEntity resEntity = response.getEntity();
                String r =   EntityUtils.toString(resEntity);
//                Log.i(TAG, "r: "+r);
                int result = Integer.valueOf(r);
                Log.i(TAG, "run: "+result);
                if (result>0) {
                    message.arg1 = 0;
                    message.obj = result;
                    handler.sendMessage(message);
                }else{
                    message.arg1 = 1;
                    handler.sendMessage(message);
                }
                entity.consumeContent();
            }else
                Log.i(TAG, "run: 服务器不能响应....."+ statusCode);

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.getConnectionManager().shutdown();
            } catch (Exception ignore) {

            }
        }

    }
}
