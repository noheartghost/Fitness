package app.fitness.com.fitness.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.security.NoSuchAlgorithmException;

import app.fitness.com.fitness.Adapters.Register;
import app.fitness.com.fitness.App.MyApp;
import app.fitness.com.fitness.R;
import app.fitness.com.fitness.tools.StringChange;
import app.fitness.com.fitness.util.User;

public class RegisterActivity extends Activity {

    private static final String TAG = "RegisterActivity";
    private EditText name,phoneNumber,password,passwordTwice,gendar,email;
    private Button setregisbtn;
    private Button setbackbtn;
    //gender
    private RadioGroup gender_group;
    private RadioButton gender_male,gender_female;
    private int gender=0;//初始化为男
    MyApp myApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        myApp = (MyApp) getApplicationContext();
        name = (EditText) findViewById(R.id.register_name);
        phoneNumber = (EditText) findViewById(R.id.register_phone);
        password = (EditText) findViewById(R.id.register_password);
        passwordTwice = (EditText) findViewById(R.id.register_password_twice);
        email = (EditText) findViewById(R.id.register_email);

        //gender
        gender_group = (RadioGroup)findViewById(R.id.gender_group);
        gender_male = (RadioButton)findViewById(R.id.gender_male);
        gender_female =(RadioButton)findViewById(R.id.gender_female);

        gender_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == gender_male.getId())
                    gender = 0;
                else
                    gender = 1;
            }
        });

        setregisbtn = (Button)findViewById(R.id.setregisbtn);
        setbackbtn = (Button)findViewById(R.id.setbackbtn);
        setregisbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //后期需要对gender处理，前台需要增加单选框，后台的类型需要统一
                Toast.makeText(getApplicationContext(), "开始注册", Toast.LENGTH_SHORT).show();
//                测试所用
//                new Register("aaa",
//                        "14122768965",
//                        "123",
//                        true,
//                        "14122768965@163.com",
//                        new Handler(new RegisterHandler()),
//                        getApplicationContext()
//                ).start();
                if(isInfoRight()){
                    try {
                        String passAfterEccrypt = StringChange.eccrypt(password.getText().toString());
                        new Register(name.getText().toString(),
                                phoneNumber.getText().toString(),
                                passAfterEccrypt,
                                true,
                                email.getText().toString(),
                                new Handler(new RegisterHandler())
                        ).start();
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }


                }



            }
        });
        setbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                RegisterActivity.this.finish();
            }
        });
    }

    /**
     * 需要初步判断填写的信息是否正确
     * @return
     */
    public boolean isInfoRight(){
        if(!password.getText().toString().equals(passwordTwice.getText().toString())){
            Toast.makeText(getApplicationContext(),"两次密码输入不一致",Toast.LENGTH_SHORT).show();
            return false;
        }else if(name.getText()==null||
                phoneNumber.getText()==null||
                password.getText()==null||
                passwordTwice.getText()==null||
                email.getText()==null){
            Toast.makeText(getApplicationContext(),"请填写完整信息",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * 处理注册的返回信息
     */
    public class RegisterHandler implements Handler.Callback {

        @Override
        public boolean handleMessage(Message message) {
            if (message.arg1==0) {
//                Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG).show();
                Log.i(TAG, "register success");
                int userId = (int) message.obj;
                User user = new User();
                user.setName(name.getText().toString());
                user.setEmail(email.getText().toString());
                //后期需要修改
                user.setSex(true);
                user.setPhoneNumber(phoneNumber.getText().toString());
                user.setUserId(userId);
                myApp.setUser(user);

                Intent intent = new Intent(RegisterActivity.this, ChosenActivity.class);
                startActivity(intent);
                RegisterActivity.this.finish();

            }else if (message.arg1==1) {
                Log.i(TAG, "register fail ");
            }
            return true;
        }
    }

}
