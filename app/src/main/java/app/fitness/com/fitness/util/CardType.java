package app.fitness.com.fitness.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yinyanting on 2017/10/23.
 */

public class CardType {
    private List<String> CardTypeList ;

    public CardType(){
        CardTypeList = new ArrayList<String>();
        CardTypeList.add("单次健身卡");
        CardTypeList.add("周卡");
        CardTypeList.add("月卡");
        CardTypeList.add("年卡");
        CardTypeList.add("其他种类的卡");
    }

    public String getCardType(int i){
        return CardTypeList.get(i);
    }
}
