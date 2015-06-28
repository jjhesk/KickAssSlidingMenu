package com.asynhkm.productchecker.schema;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by Hesk on 3/12/2014.
 * <p/>
 * <p/>
 * <p/>
 * final Type typeOfrouteNode = new TypeToken<List<RouteNode>>() {
 * }.getType();
 * <p/>
 * final ArrayList<RouteNode> contextnode_r = c.deserialize(j.get("routenode").getAsJsonArray(), typeOfrouteNode);
 */
public class RDataDeserialize implements JsonDeserializer<ReturnData> {
    @Override
    public ReturnData deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        ReturnData runtime;
        try {
            String t = json.getAsString();
            if (t.equalsIgnoreCase("")) {
                runtime = new ReturnData();
            } else {
                final JsonObject jj = json.getAsJsonObject();
                runtime = context.deserialize(jj, ReturnData.class);
            }
            //  final JsonObject jj = json.getAsJsonObject();
            runtime = context.deserialize(json, ReturnData.class);
        } catch (JsonParseException e) {
            runtime = new ReturnData();
            throw e;
        } catch (Exception e) {
            runtime = new ReturnData();
            // throw new ParseException(e.toString());
        }
        return runtime;
        //  return json.getAsJsonPrimitive().;
    }
}

