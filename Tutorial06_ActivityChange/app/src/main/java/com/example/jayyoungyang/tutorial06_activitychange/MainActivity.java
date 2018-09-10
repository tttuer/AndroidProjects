package com.example.jayyoungyang.tutorial06_activitychange;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 사용자가 이름을 입력하면 그 이름을 저장하는 객체
        final EditText nameText = (EditText) findViewById(R.id.nameText);
        Button nameButton = (Button) findViewById(R.id.nameButton);

        // 버튼이 눌렸을 때 이벤트 처리
        nameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // nameText에 입력된 문자 값을 가져와서 스트링 형태로 변형한 후 name에 저장
                String name = nameText.getText().toString();
                // Intent는 새로운 창을 열기 위해서 필요하다.
                Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                // name이라는 문자를 보낼 수 있도록 한다.
                intent.putExtra("nameText", name);
                // 만들었던 intent를 실행한다.
                startActivity(intent);
            }
        });
    }
}
