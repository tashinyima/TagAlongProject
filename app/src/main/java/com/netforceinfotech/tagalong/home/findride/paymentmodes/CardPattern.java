package com.netforceinfotech.tagalong.home.findride.paymentmodes;

/**
 * Created by JitendraSingh on 11/24/2016.
 */

public class CardPattern {

    // VISA
    public static final String VISA = "4[0-9]{12}(?:[0-9]{3})?";
    public static final String VISA_VALID = "^4[0-9]{12}(?:[0-9]{3})?$";

    // MasterCard
    public static final String MASTERCARD = "^(?:5[1-5][0-9]{2}|222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12}$";
    public static final String MASTERCARD_SHORT = "^(?:222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)";
    public static final String MASTERCARD_SHORTER = "^(?:5[1-5])";
    public static final String MASTERCARD_VALID = "^(?:5[1-5][0-9]{2}|222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12}$";

    // American Express
    public static final String AMERICAN_EXPRESS = "^3[47][0-9]{0,13}";
    public static final String AMERICAN_EXPRESS_VALID = "^3[47][0-9]{13}$";




}
