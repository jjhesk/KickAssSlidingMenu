package com.asynhkm.productchecker.schema;

/**
 * Created by Hesk on 1/12/2014.
 */
public class ReturnResult {
    private int result;
    private String message;
    private String status;
    private int timestamp;
    private boolean success;

    public ReturnResult() {
    }

    public ReturnResult(String message) {
        this.message = message;
    }

    public boolean isError() {
        return !success;
    }

    public String getMsg() {
        return message;
    }


}
