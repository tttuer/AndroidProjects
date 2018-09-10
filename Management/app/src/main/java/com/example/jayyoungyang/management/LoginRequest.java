package com.example.jayyoungyang.management;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {

    final static private String URL = "http://jay1103.cafe24.com/Login.php";
    private Map<String, String> parameters;


    public LoginRequest(String userID, String userPassword, Response.Listener<String> listener) {
        // 해당 URL에 포스트 방식으로 전달하게 된다.
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        // userID에는 받은 userID를 들어가게 해준다.
        parameters.put("userID", userID);
        parameters.put("userPassword", userPassword);
    }

    @Override
    public Map<String, String> getParams(){
        return parameters;
    }
}
