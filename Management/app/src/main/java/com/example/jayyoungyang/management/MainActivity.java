package com.example.jayyoungyang.management;

import android.app.FragmentManagerNonConfig;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView idText = (TextView) findViewById(R.id.idText);
        TextView passwordText = (TextView) findViewById(R.id.passwordText);
        TextView welcomeMessage = (TextView) findViewById(R.id.welcomeMessage);
        Button managementButton = (Button) findViewById(R.id.managementButton);

        // 인텐트로 넘어온 값을 저장할 수 있도록 한다.
        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");
        String userPassword = intent.getStringExtra("userPassword");
        String message = "환영합니다 " + userID + "님!";

        idText.setText(userID);
        passwordText.setText(userPassword);
        welcomeMessage.setText(message);

        // 만약 유저 아이디가 어드민과 같지 않다면
        if(!userID.equals("admin")) {
//            // 버튼을 비활성화 해준다.
//            managementButton.setEnabled(false);
            // 아예 버튼이 보이지 않게 하는 방법도 있다.
            // 이렇게 하면 아예 버튼이 보이지 않는다.
            managementButton.setVisibility(View.GONE);
        }

        // 회원 관리 버튼을 눌렀을 때 밑에서 작성한 파싱이 일어나는 과정이 일어나도록 한다.
        managementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BackgroundTask().execute();
            }
        });
    }

    // 이제 회원 관리 버튼을 클릭했을 때 실제 회원 정보에 해당하는 정보가 와야한다.
    // 그래서 내부 클래스를 만들어준다.
    class BackgroundTask extends AsyncTask<Void, Void, String> {
        String target;

        // 실질적으로 실행이 되는 부분
        @Override
        protected String doInBackground(Void... voids) {
            try{
                // 해당 url에 접속할 수 있도록 url을 만들어준다.
                URL url = new URL(target);
                // 자바에서 파싱할때 사용되는 부분이다.
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = httpURLConnection.getInputStream();
                // 버퍼리더를 넣어서 하나씩 읽어 올 수 있도록 한다.
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                // 매 열마다 입력을 받을 수 있도록 해준다.
                String temp;
                // 매 열마다 담겨서 이 스트링 빌드안에 담겨지게 된다.
                StringBuilder stringBuilder = new StringBuilder();
                // 해당 열이 널값이 아닐때까지 읽어라
                // 즉 모든 열값을 읽겠다는 말이다.
                while((temp = bufferedReader.readLine()) != null) {
                    // 스트링 빌더에 해당 열을 추가해준다.
                    stringBuilder.append(temp + "\n");
                }
                // 마지막으로 버퍼스트림과 인풋스트림을 닫아준다.
                bufferedReader.close();
                inputStream.close();
                // http도 닫아준다.
                httpURLConnection.disconnect();
                // 마지막으로 해당 문자열들의 집합을 반환해준다.
                return stringBuilder.toString().trim();
            } catch(Exception e) {
                e.printStackTrace();
            }
            // 만약 오류가 났을 경우 null값을 반환할 수 있도록 한다.
            return null;
        }

        // 초기화 하는 과정
        @Override
        protected void onPreExecute() {
            target = "http://jay1103.cafe24.com/List.php";
        }

        // 상속은 받지만 사용하진 않는다.
        @Override
        public void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        // 모든 파싱작업이 끝난이후 다음 액티비티로 넘어갈 수 있도록 해준다.
        @Override
        public void onPostExecute(String result) {
            Intent intent = new Intent(MainActivity.this, ManagementActivity.class);
            // 파싱한 유저 리스트를 그대로 넘겨준다.
            intent.putExtra("userList", result);
            MainActivity.this.startActivity(intent);
        }
    }
}
