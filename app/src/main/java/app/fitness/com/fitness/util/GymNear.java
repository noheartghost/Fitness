package app.fitness.com.fitness.util;

import java.sql.Blob;
import java.util.List;

import static android.R.attr.name;

/**
 * Created by Yinyanting on 2017/9/2.
 */

public class GymNear {
    private Blob avatar;
    private double distance;
    private String email;
    private List<Integer> gymPhotos;
    private String introduce;
    private boolean isrecommend;
    private String location;
    private double locationX;
    private double locationY;
    private String name;
    private String password;
    private String phoneNumber;
    private boolean sex;
    private List<String> tags;
    private int type;
    private int userId;

    public boolean isrecommend() {
        return isrecommend;
    }

    public void setIsrecommend(boolean isrecommend) {
        this.isrecommend = isrecommend;
    }

    public Blob getAvatar() {
        return avatar;
    }

    public void setAvatar(Blob avatar) {
        this.avatar = avatar;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Integer> getGymPhotos() {
        return gymPhotos;
    }

    public void setGymPhotos(List<Integer> gymPhotos) {
        this.gymPhotos = gymPhotos;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getLocationX() {
        return locationX;
    }

    public void setLocationX(double locationX) {
        this.locationX = locationX;
    }

    public double getLocationY() {
        return locationY;
    }

    public void setLocationY(double locationY) {
        this.locationY = locationY;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
