package com.example.jayyoungyang.management;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText idText = (EditText) findViewById(R.id.idText);
        final EditText passwordText = (EditText) findViewById(R.id.passwordText);
        final EditText nameText = (EditText) findViewById(R.id.nameText);
        final EditText ageText = (EditText) findViewById(R.id.ageText);

        Button registerButton = (Button) findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID = idText.getText().toString();
                String userPassword = passwordText.getText().toString();
                String userName = nameText.getText().toString();
                int userAge = Integer.parseInt(ageText.getText().toString());

                // 특정 요청을 한 후에 리스너에서 그 값을 다를 수 있게 해준다.
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success  = jsonResponse.getBoolean("success");
                            // 회원가입이 성공했을 경우 알림창이 뜰 수 있게 만들어 준다.
                            if(success) {
                                // 알림창은 이 RegisterActivity에 뜨게 된다.
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                // 알림창에는 회원들록이 성공했습니다라고 뜨게 한다.
                                builder.setMessage("회원 등록에 성공했습니다.")
                                        .setPositiveButton("확인", null)
                                        .create()
                                        .show();

                                // 그 이후 로그인 화면으로 돌아갈 수 있도록 해준다.
//                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
////                                RegisterActivity.this.startActivity(intent);
                                // 회원가입이 성공하면 회원가입 액티비티가 종료되게된다.
                                finish();
                            } else {
                                // 알림창은 이 RegisterActivity에 뜨게 된다.
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                // 알림창에는 회원들록이 실패했습니다라고 뜨게 한다.
                                builder.setMessage("회원 등록에 실패했습니다..")
                                        .setNegativeButton("다시 시도 ", null)
                                        .create()
                                        .show();
                            }
                        } catch(JSONException e){
                            // 오류가 발생했을 경우에 오류를 출력할 수 있게 해준다.
                            e.printStackTrace();
                        }
                    }
                };

                // 회원가입을 신청하는 부분
                RegisterRequest registerRequest = new RegisterRequest(userID, userPassword, userName, userAge, responseListener);
                // 큐에 넣어서 인터넷에 접속하는 방식
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                // 이렇게 해주면 버튼을 클릭했을 때 리퀘스트가 실행이 된다.
                queue.add(registerRequest);
            }
        });
    }
}
