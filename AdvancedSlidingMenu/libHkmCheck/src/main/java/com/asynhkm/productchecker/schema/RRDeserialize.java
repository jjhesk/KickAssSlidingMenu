package com.asynhkm.productchecker.schema;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by Hesk on 2/12/2014.
 */
public class RRDeserialize implements JsonDeserializer<ReturnResult> {
    @Override
    public ReturnResult deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        return null;
        //  return json.getAsJsonPrimitive().;
    }
}
