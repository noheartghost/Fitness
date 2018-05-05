package app.fitness.com.fitness.App;

import android.app.Application;
import android.graphics.Bitmap;

import app.fitness.com.fitness.util.GymNear;
import app.fitness.com.fitness.util.User;

/**
 * Created by Yinyanting on 2017/8/24.
 * 存储全局变量
 * 针对于在APP中需要反复利用但不轻易改变的class
 * 后续会不断添加
 * 尽量添加封装好的类而不是基本数据结构
 */

public class MyApp extends Application{
    private User user;

    private Bitmap userImage;

    private GymNear gymMostPopular;

    private Bitmap gymMostPopularBitmap;

    public Bitmap getGymMostPopularBitmap() {
        return gymMostPopularBitmap;
    }

    public void setGymMostPopularBitmap(Bitmap gymMostPopularBitmap) {
        this.gymMostPopularBitmap = gymMostPopularBitmap;
    }

    public GymNear getGymMostPopular() {
        return gymMostPopular;
    }

    public void setGymMostPopular(GymNear gymMostPopular) {
        this.gymMostPopular = gymMostPopular;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Bitmap getUserImage() {
        return userImage;
    }

    public void setUserImage(Bitmap userImage) {
        this.userImage = userImage;
    }


}
