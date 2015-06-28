package com.asynhkm.productchecker.Checker;

import android.content.Context;
import android.content.SharedPreferences;

import com.asynhkm.productchecker.Util.Tool;

/**
 * Created by Hesk on 30/12/2014.
 */
public class HKMCheckerPlugable {

    private Context ctx;
    private SharedPreferences SP;
    private String
            licenseKey = "",
            productKey = "",
            mac_id = "";

    public HKMCheckerPlugable(String productkey, Context ctx) {
        SP = ctx.getApplicationContext().getSharedPreferences(param.DATAGROUP, Context.MODE_PRIVATE);
        mac_id = Tool.get_mac_address(ctx);
        licenseKey = SP.getString(param.SAVE_ACH, "");
        this.productKey = productkey;
        this.ctx = ctx;
    }

    /**
     * the net is just started now
     */
    public void netStartCheck(CheckerCB callback) {
        final CheckerTask re = new CheckerTask(this.ctx, callback, SP);
        re.setMac(mac_id);
        if (licenseKey.isEmpty()) {
            re.setStatusRequest(param.request_status.registration);
            //check with the license from the customer
            re.setProductKey(productKey)
                    .setRequestUrl(param.devReg());
        } else {
            re.setStatusRequest(param.request_status.check);
            //check and issue a new license or register
            re.setLicenseKey(licenseKey)
                    .setRequestUrl(param.devCheck());
        }
        re.execute();
    }

    /**
     * public initialization
     *
     * @param productKey
     * @param ctx
     * @return
     */
    public static HKMCheckerPlugable init(String productKey, Context ctx) {
        if (productKey.equalsIgnoreCase("")) Tool.trace(ctx, "has to have the product key entered");
        HKMCheckerPlugable instance = new HKMCheckerPlugable(productKey, ctx);
        return instance;
    }

    /**
     * chain method
     *
     * @param license_token_key
     * @return
     */
    public HKMCheckerPlugable licensed(String license_token_key) {
        licenseKey = license_token_key;
        return this;
    }

}
