package app.fitness.com.fitness.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yinyanting on 2017/10/24.
 */

public class CoachClass {
    private CreateTime beginTime;
    private CreateTime endTime;
    private CreateTime orderCreateTime;
    private int alreadyPeople,
            classId,
            coachId,
            day,
            likecount,
            maxPeople;
    private double coachLocationX,
            coachLocationY,
            distance;
    private String coachName,
            coachPhoneNumber,
            introduce,
            name;
    private List<Integer> photos = new ArrayList<Integer>();
    private boolean recommend;
    private List<String> types = new ArrayList<String>();

    public CreateTime getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(CreateTime orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public CreateTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(CreateTime beginTime) {
        this.beginTime = beginTime;
    }

    public CreateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(CreateTime endTime) {
        this.endTime = endTime;
    }

    public int getAlreadyPeople() {
        return alreadyPeople;
    }

    public void setAlreadyPeople(int alreadyPeople) {
        this.alreadyPeople = alreadyPeople;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getCoachId() {
        return coachId;
    }

    public void setCoachId(int coachId) {
        this.coachId = coachId;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getLikecount() {
        return likecount;
    }

    public void setLikecount(int likecount) {
        this.likecount = likecount;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    public double getCoachLocationX() {
        return coachLocationX;
    }

    public void setCoachLocationX(double coachLocationX) {
        this.coachLocationX = coachLocationX;
    }

    public double getCoachLocationY() {
        return coachLocationY;
    }

    public void setCoachLocationY(double coachLocationY) {
        this.coachLocationY = coachLocationY;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public String getCoachPhoneNumber() {
        return coachPhoneNumber;
    }

    public void setCoachPhoneNumber(String coachPhoneNumber) {
        this.coachPhoneNumber = coachPhoneNumber;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Integer> photos) {
        this.photos = photos;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }




}
