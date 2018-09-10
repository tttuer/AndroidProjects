package com.example.jayyoungyang.management;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class DeleteRequest extends StringRequest {

    final static private String URL = "http://jay1103.cafe24.com/Delete.php";
    private Map<String, String> parameters;

    public DeleteRequest(String userID, Response.Listener<String> listener) {
        // POST방식인 메소드 방식으로 보내도록 한다.
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
    }

    @Override
    public Map<String, String> getParams() {
        return parameters;
    }

}
