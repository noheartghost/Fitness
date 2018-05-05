
package app.fitness.com.fitness.Activities;

import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;

import java.util.Map;

import app.fitness.com.fitness.R;
import app.fitness.com.fitness.util.pay.OrderInfoUtil2_0;
import app.fitness.com.fitness.util.pay.PayResult;

public class Pay extends AppCompatActivity {

    private Button btnPay;
    private static final int SDK_PAY_FLAG = 1001;
    private String RSA_PRIVATE = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDQbinRkdJ9eGaLhYUW0H7/u30lr7rREmfFsj5EZqQT10ZXrunoQ+3xy6jOeX/7A8uPUUF9c49bvt0NqEPKPhoClninnJDjWoynMp7faG1zSvb8xpnPHsgvGN0hQeOO384R9W5ZctJBefj4BaSGWq6soQDJy17XgBZC5Si1NtbJsGsTVFYJSK+6eFK1ai+mgY8lItf4Qt/n2370uUngotNIf9wfG52WwCpUC/qrv82jdR0GbdpxeeasqWg3IF+7TaPyzsGZWcDgYPQm+W1MyKdOSep+WlhJExUErLDp8upMfJMPu4vIQ7kgionPb/YnJjnMWb36Z6BA232s0Oqrk9VlAgMBAAECggEBAIVvQU5tMHgq8Fs5624jpGpLuV67FbprMgwahEp9+2TIWLwhupV7h4VYgIVOu2QTlen2dxq9NHXjxtjLdDqxby1g73U+gnNKsOkJOzSDQi6hO2WwLx+t8TJVlJuJ9wisttxAZ5jULU7Fq2H9Gsr4mwLhQAPwMpN76MSCuIDqzq7nHohhPlRqOIsLZH/nhGl+mH61tUXS+3ERmVNE7FTrFy1TZLV6+5p/v8ytY/oFLBzm1CGVREG62HBvt8pxXV1ct2LtKfvyrjJB0Rnv0cJzZhlZ2ZJ6yvKnTatC96RhN5P4sFABkSef6Uj2BiwkD86ccooIoaLjAKyOxFKUuh8wR7ECgYEA7RffHyJaUaswQDpJQgMl52ocU92FLlJ1O12Bd8hNrcqWkQLTCAYYReZsf6ZNZJ3xMeWoqBbikKonA0TotWbJ1vEi9meehwHZdzvJhC7MVs9DzRG7vXjcpOrm4XWACeSyqpcuVwCPxRCnlUfm56SOZri8jgla5Ftlv4voF7EcOV8CgYEA4Q0nahKKzrAlMrOtrcKUfGBqPb5Jm8nLPlzGbjAbqyOwae469XMBf7+roYnSJN2qeXbeM8BMcYt0EPl7yP4UIutu8VW6FxvQXqf3ShIE83Q2TMubhVOnBfDpjFdHF29UJRerrs4wRk6ElJk9qk4X1Gdh22Z9ciq6ow2x9Dh4M7sCgYAiRT4SIQQPJACAeCxYp13WOmdH1BBJJtAdOaHaL5DS2yKyaUcZtM/TYsAyH1a2gYH4pWqgiVZMdNvYxCAjAG2SbR6RoV6vwxus+W5ideVBcwoo3TRW6d5y1CV1V2efhngw4lNnbgRaBtNCZ7c/qnCO9osCuOVzdqaZbl6o9gfPXwKBgQCK2GO1xITQ6cm5UzNW3ZJgfdduCmU9cS0sZ26g8ptPZbBkWm8TmYWWPDl2ayI0TnHriFOkmAJRlxjflBwzr9XaiWgkCqDJuwY+L+CYWS+mx87saD2l7ys/thdQ11Pr+Gpwi/8G1Pzhk3WmfDiYRp/bEoFELCI++m+1QzDEHMbEGwKBgQDbMCVkl/4NYHI3BfbHSgoY9o7MPfFDnHAZKwGDsMQzFZtNNTmzyhYASon4Ybxnkymzk3mnNq6dVMHJk8/pfMXvjQoUL53fk/RZ8egKEBtvnBO/uz9VLdt0ckOL5fhIVsvN6c7y5TIh063LlJYAN2NCKVbv6QA7dxOos3NM97hwtQ==" ;
    public static final String APPID = "2018021302189892";

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SDK_PAY_FLAG:
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    //同步获取结果
                    String resultInfo = payResult.getResult();
                    Log.i("Pay", "Pay:" + resultInfo);
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(Pay.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Pay.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        btnPay = (Button) findViewById(R.id.btnPay);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "click",Toast.LENGTH_LONG).show();
                //秘钥验证的类型 true:RSA2 false:RSA
                boolean rsa = false;
                //构造支付订单参数列表
                Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa);
                //构造支付订单参数信息
                String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
                //对支付参数信息进行签名
                String sign = OrderInfoUtil2_0.getSign(params, RSA_PRIVATE, rsa);
                //订单信息
                final String orderInfo = orderParam + "&" + sign;
                //异步处理
                Runnable payRunnable = new Runnable() {

                    @Override
                    public void run() {
                        //新建任务
                        PayTask alipay = new PayTask(Pay.this);
                        //获取支付结果
                        Map<String, String> result = alipay.payV2(orderInfo, true);
                        Message msg = new Message();
                        msg.what = SDK_PAY_FLAG;
                        msg.obj = result;
                        mHandler.sendMessage(msg);
                    }
                };
                // 必须异步调用
                Thread payThread = new Thread(payRunnable);
                payThread.start();

            }
        });
    }



//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btnPay:
//
//        }
//    }

    //获取版本
    private String getPlayVersion(PayTask payTask){
        return payTask.getVersion();
    }

}
