package com.example.jayyoungyang.tutorial06_activitychange;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_sub);
        // 만들어뒀던 nameText를 가져온다.
        TextView nameText = (TextView) findViewById(R.id.nameText);

        // intent로 넘어온 값을 가져올 수 있도록 한다.
        Intent intent = getIntent();
        // nameText에 intent로 가져온 값들 중에 nameText에 해당하는 값을 저장한다.
        // 이 이후 manifests에 들어가서 환경 설정을 해준다.
        nameText.setText(intent.getStringExtra("nameText").toString());

        Button button = (Button) findViewById(R.id.backButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent1);
            }
        });
    }
}
