package com.asynhkm.productchecker.schema;

/**
 * Created by Hesk on 1/12/2014.
 */
public class ReturnData {
    private String qr_a, qr_b, trace_id, offer_expiry_date, distribution, product_name, extension, status, ID, stock_id, vstatus, handle_mac_address, handle_terminal_id, handle_requirement, names, action_taken_by, user, vcoin, obtained, address, claim_time, time, msg, product_image;
    private boolean isError = false, verified = false;
    private int result, timestamp;

    public ReturnData() {
    }

    public String getProductString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Product:");
        sb.append(product_name);
        sb.append("(" + extension + ")");
        sb.append("\nStock ID:");
        sb.append(stock_id);
        sb.append("\nAddress:");
        sb.append(address);
        sb.append("\n");
        return sb.toString();
    }

    public String getErrorMessage() {
        return msg;
    }

    public void setError(String e) {
        msg = e;
        isError = true;
        verified = false;
    }

    public void setVerified() {
        verified = true;
        isError = false;
    }

    public boolean isVerified() {
        return verified;
    }

    public boolean isError() {
        return isError;
    }

    public int checkQR(String hash) throws Exception {
        if (hash.equalsIgnoreCase(qr_a)) return 2;
        else if (hash.equalsIgnoreCase(qr_b)) return 1;
        else throw new Exception("Not an valid QR, please check it again");
    }

    public String getQR1() {
        return qr_a;
    }

    public String getQR2() {
        return qr_b;
    }

    public String getTrace() {
        return trace_id;
    }

    public String getProductImageURL() {
        return product_image;
    }

    public String getProductName() {
        return product_name;
    }

    public String getExtension() {
        return extension;
    }

    public String getImageURL() {
        return product_image;
    }

    public String getOtherData() {
        StringBuilder sb = new StringBuilder();
        sb.append("Stock ID:");
        sb.append(stock_id);
        sb.append("\nAddress:");
        sb.append(address);
        sb.append("\n");
        return sb.toString();
    }
}
