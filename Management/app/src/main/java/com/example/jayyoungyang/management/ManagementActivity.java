package com.example.jayyoungyang.management;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ManagementActivity extends AppCompatActivity {

    private ListView listView;
    private UserListAdapter adapter;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);
//        TextView userListTextView = (TextView) findViewById(R.id.userListTextView);
        // 넘겨준 것들을 받는다.
        Intent intent = getIntent();
//        userListTextView.setText(intent.getStringExtra("userList"));
        listView = (ListView) findViewById(R.id.listView);
        userList = new ArrayList<User>();

//        userList.add(new User("홍길동", "홍길동", "홍길동", "20"));
//        userList.add(new User("김감례", "김감례", "김감례", "22"));
//        userList.add(new User("펭귄", "펭귄", "펭귄", "25"));

        adapter = new UserListAdapter(getApplicationContext(), userList);
        listView.setAdapter(adapter);

        // 실제 가져오는 데이터베이스를 바탕으로 리스트를 채운다.
        try {
            // JSONObject에서 웹 데이터를 가져오기 때문이다.
            // userList로 넘어오는 데이터들을 받아온다.
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("userList"));
            // json을 작성할때 배열 이름을 response라고 지었기 때문에 response를 받아준다.
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            int count = 0;
            String userID, userPassword, userName, userAge;
            while(count < jsonArray.length()) {
                JSONObject object = jsonArray.getJSONObject(count);
                userID = object.getString("userID");
                userPassword = object.getString("userPassword");
                userName = object.getString("userName");
                userAge = object.getString("userAge");

                // 유저 객체를 만들고 그것을 추가해주면 된다.
                User user = new User(userID, userPassword, userName, userAge);
                userList.add(user);
                count++;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}
