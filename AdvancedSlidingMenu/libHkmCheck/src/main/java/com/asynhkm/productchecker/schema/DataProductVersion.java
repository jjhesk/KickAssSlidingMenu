package com.asynhkm.productchecker.schema;

/**
 * Created by Hesk on 30/12/2014.
 */
public class DataProductVersion {
    private String
            clientID,
            siteURL,
            licensePerson,
            product,
            createdAt,
            checked,
            expire,
            key,
            licenseHash;
    private ReturnResult rr;
    private boolean
            brandingRemoval,
            demoDisplay,
            useExpiration,
            licenseStatusLive;

    public String getLicenseKey() {
        return key;
    }

    public String getExpiration() {
        return expire;
    }

    public boolean isDemo() {
        return demoDisplay;
    }

    public boolean isbrandingRemoved() {
        return brandingRemoval;
    }

    public void setRR(ReturnResult rlr) {
        rr = rlr;
    }

    public boolean isError() {
        return rr != null;
    }

    public String getErrorMsg() {
        return rr.getMsg();
    }
}
