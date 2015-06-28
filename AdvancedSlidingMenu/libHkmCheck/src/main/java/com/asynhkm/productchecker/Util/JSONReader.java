package com.asynhkm.productchecker.Util;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONReader {
	private JSONObject j;
	private final static String key_user_name = "user_name", code = "id_code",
			expiry = "offer_expiry_date", stock_name = "stock_name",
			is_payment_done = "payment_done", warn = "warnings";

	public JSONReader(JSONObject j) {
		this.j = j;
	}

	public String to_dialog_string() throws JSONException {
		StringBuilder htmlContent = new StringBuilder();

		htmlContent.append("Name:");
		htmlContent.append(j.getString(key_user_name));
		htmlContent.append("\nID: ");
		htmlContent.append(j.getString(code));
		htmlContent.append("\nExpiry Date: ");
		htmlContent.append(j.getString(expiry));
		htmlContent.append("\nStock Title: ");
		htmlContent.append(j.getString(stock_name));

		String result = htmlContent.toString();
		return result;
	}

	public String to_dialog_string(boolean on_text_field) throws JSONException {
		StringBuilder htmlContent = new StringBuilder();

		htmlContent.append("Name:");
		htmlContent.append(j.getString(key_user_name));
		htmlContent.append("\nID: ");
		htmlContent.append(j.getString(code));
		htmlContent.append("\nExpiry Date: ");
		htmlContent.append(j.getString(expiry));
		htmlContent.append("\nStock Title: ");
		htmlContent.append(j.getString(stock_name));
		htmlContent.append("\nExtra Information: ");
		htmlContent.append(j.getString(is_payment_done));
		if (on_text_field) {
			htmlContent.append("\nWarnings: ");
			htmlContent.append(j.getString(warn));
		}
		String result = htmlContent.toString();
		return result;
	}
}
