package com.asynhkm.productchecker.Checker;

/**
 * Created by Hesk on 30/12/2014.
 */
public class param {
    private static final String production_domain = "http://async777.com";
    private static final String dev_domain = "http://async777.com";
    private static final String request_check = "/api/license/check/";
    private static final String request_registration = "/api/license/registration/";

    public static String devReg() {
        return dev_domain + request_registration;
    }

    public static String proReg() {
        return production_domain + request_registration;
    }

    public static String devCheck() {
        return dev_domain + request_check;
    }

    public static String proCheck() {
        return production_domain + request_check;
    }

    public static final String SAVE_ACH = "license_key";
    public static final String DATAGROUP = "license_data_acm";

    public static enum request_status {
        registration, check
    }
}
