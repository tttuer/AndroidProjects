package com.example.jayyoungyang.management;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText idText = (EditText) findViewById(R.id.idText);
        final EditText passwordText = (EditText) findViewById(R.id.passwordText);

        final Button loginButton = (Button) findViewById(R.id.loginButton);
        final TextView registerButton = (TextView) findViewById(R.id.registerButton);

        // 버튼을 클릭했을 때 일어나게 해주는 이벤트 반응
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 현재 로그인 액티비티에서 레지스터 액티비티로 넘어갈 수 있게 해주는 것이다.
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                // 이렇게 해줌으로써 레지스터 액티비티로 넘어갈 수 있게 해준다.
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userID = idText.getText().toString();
                final String userPassword = passwordText.getText().toString();

                // 리스폰스가 건너오면 그 리스폰스를 저장할 수 있도록 해준다.
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            // success라는 값을 찾았으면 true값을 반환한다.
                            boolean success = jsonResponse.getBoolean("success");
                            if(success) {
                                // success가 true이면 userID, userPassword를 받아온다.
                                String userID = jsonResponse.getString("userID");
                                String userPassword = jsonResponse.getString("userPassword");

                                //값을 받아왔으면 로그인에 성공한 것이기 때문에 메인 엑티비티로 넘어갈 수 있도록 해준다.
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                // 인텐트에 각각의 변수값들을 저장해준다.
                                intent.putExtra("userID", userID);
                                intent.putExtra("userPassword", userPassword);
                                LoginActivity.this.startActivity(intent);
                            } else {
                                // 로그인에 실패했다면 실패했다는 다이얼로그를 띄워준다.
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("로그인에 실패하였습니다.")
                                        .setNegativeButton("다시 시도", null)
                                        .create()
                                        .show();
                            }
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest loginRequest = new LoginRequest(userID, userPassword, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });
    }
}
