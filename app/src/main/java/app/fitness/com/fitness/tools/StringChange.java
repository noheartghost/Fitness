package app.fitness.com.fitness.tools;

import android.support.annotation.NonNull;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Yinyanting on 2017/8/30.
 */

public class StringChange {

    public static final String TAG= "StringChange";

    @NonNull
    public static String eccrypt(String info) throws NoSuchAlgorithmException {
        //根据MD5算法生成MessageDigest对象
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] srcBytes = info.getBytes();
        //使用srcBytes更新摘要
        md5.update(srcBytes);
        //完成哈希计算，得到result
        byte[] resultBytes = md5.digest();
        String result = new String(resultBytes);
        Log.i(TAG, "eccrypt: "+ result);
        return result.trim();
    }

    public static String timestampToString(Timestamp timestamp){
        String tsStr = "";
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            //方法一
            tsStr = sdf.format(timestamp);
            return tsStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
