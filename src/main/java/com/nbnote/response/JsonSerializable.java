package com.nbnote.response;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by K on 2017. 9. 3..
 */

public interface JsonSerializable {
    public JSONObject toJson() throws JSONException;
}
