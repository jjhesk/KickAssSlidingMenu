package com.asynhkm.productchecker.Checker;

import com.asynhkm.productchecker.schema.DataProductVersion;

/**
 * Created by Hesk on 30/12/2014.
 */
public interface CheckerCB {

    void tr_success(DataProductVersion pv, param.request_status sta);

    void tr_fail(DataProductVersion pv);
}
