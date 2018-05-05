package app.fitness.com.fitness.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.server.converter.StringToIntConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import app.fitness.com.fitness.Adapters.GetUserPhoto;
import app.fitness.com.fitness.Adapters.LoginAdapter;
import app.fitness.com.fitness.App.MyApp;
import app.fitness.com.fitness.R;
import app.fitness.com.fitness.util.GymNear;
import app.fitness.com.fitness.util.User;

import static android.R.attr.type;
import static app.fitness.com.fitness.R.drawable.person;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";
    EditText usernamedt;
    EditText passwordedt;
    Button loginbtn;
    Button regisbtn;
    MyApp myApp;

    final String url = "http://192.168.1.103:8080/fitness/LoginServlet";//47.94.0.163
    String username, password;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myApp = (MyApp)getApplicationContext();
        usernamedt = (EditText) findViewById(R.id.usernamedt);
        passwordedt = (EditText) findViewById(R.id.passwordedt);
        loginbtn = (Button) findViewById(R.id.loginbtn);
        regisbtn = (Button) findViewById(R.id.regisbtn);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = "王小明";//usernamedt.getText().toString();
                password = "123";//passwordedt.getText().toString();
                /**
                 * 需要在这里加入对输入框的判断，如果输入格式正确的话才可以进行接下来的登录操作，否则直接清空输入框
                 */
                new Thread(new LoginAdapter(username,password,new Handler(new LoginHandler()))).start();
            }
        });
        regisbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                LoginActivity.this.finish();
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Login Page") // TODO: Define a title for the contentPicture shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    public class LoginHandler implements Handler.Callback {

        @Override
        public boolean handleMessage(Message message) {
            if (message.arg1==0) {
                Log.i(TAG, "login success");
                Log.i(TAG, message.obj.toString());
                //将获取到的string分解到user的各个属性中，再存储到全局变量中
                try {
                    myApp.setUser(segmentPostResult(message.obj.toString()));
                    new Thread(new GetUserPhoto(new Handler(new LoginHandler()),myApp.getUser().getUserId())).start();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else if (message.arg1==1) {
                Log.i(TAG, "login fail ");
                usernamedt.setText("");
                passwordedt.setText("");
                Toast.makeText(getApplicationContext(),"输入有误，请重新输入！",Toast.LENGTH_SHORT).show();
            }else if (message.arg1==2) {
                Log.i(TAG, "get photo success ");
                Intent intent = new Intent(LoginActivity.this, ChosenActivity.class);
                myApp.setUserImage((Bitmap) message.obj);
                startActivity(intent);
                LoginActivity.this.finish();
            }else if (message.arg1==3) {
                Log.i(TAG, "get photo fail ");
            }

            return true;
        }
    }

    /**
     * 根据网络传输返回的字符串将对应结果放到user的各项属性中
     * @param postResult
     * @return
     */
    private User segmentPostResult(String postResult) throws JSONException {
        User user = new User();
//        Gson gson = new Gson();
//        User user = gson.fromJson(postResult, new TypeToken<User>() {
//        }.getType());

        JSONObject jSONObject = new JSONObject(postResult).getJSONObject("user");

        String email = jSONObject.getString("email");
        String userName = jSONObject.getString("name");
//        保留在本地的是加密过的密码
        String password = jSONObject.getString("password");
        String phoneNumber = jSONObject.getString("phoneNumber");
        int points = jSONObject.getInt("points");
        boolean sex =  jSONObject.getBoolean("sex");
        int type = jSONObject.getInt("type");
        int userId = jSONObject.getInt("userId");

//        Log.i(TAG, "segmentPostResult: "+jSONObject.length());
//        Log.i(TAG, "segmentPostResult: "+email);
//        Log.i(TAG, "segmentPostResult: "+userName);
//        Log.i(TAG, "segmentPostResult: "+password);
//        Log.i(TAG, "segmentPostResult: "+phoneNumber);
//        Log.i(TAG, "segmentPostResult: "+points);
//        Log.i(TAG, "segmentPostResult: "+type);
//        Log.i(TAG, "segmentPostResult: "+userId);
//        Log.i(TAG, "segmentPostResult: "+sex);

        user.setEmail(email);
        user.setName(userName);;
        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);
        user.setPoints(points);
        user.setType(type);
        user.setUserId(userId);
        user.setSex(sex);

        return user;

    }

}
