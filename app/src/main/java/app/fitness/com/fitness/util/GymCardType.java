package app.fitness.com.fitness.util;

import java.io.Serializable;

/**
 * Created by Yinyanting on 2017/10/18.
 */

public class GymCardType  implements Serializable {
    private String cardName;
    private int gymCardTypeId, gymId, type;
    private CreateTime optionalBeginTime, optionalEndTime;
    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public int getGymCardTypeId() {
        return gymCardTypeId;
    }

    public void setGymCardTypeId(int gymCardTypeId) {
        this.gymCardTypeId = gymCardTypeId;
    }

    public int getGymId() {
        return gymId;
    }

    public void setGymId(int gymId) {
        this.gymId = gymId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public CreateTime getOptionalBeginTime() {
        return optionalBeginTime;
    }

    public void setOptionalBeginTime(CreateTime optionalBeginTime) {
        this.optionalBeginTime = optionalBeginTime;
    }

    public CreateTime getOptionalEndTime() {
        return optionalEndTime;
    }

    public void setOptionalEndTime(CreateTime optionalEndTime) {
        this.optionalEndTime = optionalEndTime;
    }
}
