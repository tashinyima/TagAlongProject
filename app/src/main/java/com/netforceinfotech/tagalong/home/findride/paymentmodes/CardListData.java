package com.netforceinfotech.tagalong.home.findride.paymentmodes;

import java.util.List;

/**
 * Created by JitendraSingh on 11/24/2016.
 */

public class CardListData {

    public String cardNumber;
    public String cardUserId;

    public CardListData(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}



