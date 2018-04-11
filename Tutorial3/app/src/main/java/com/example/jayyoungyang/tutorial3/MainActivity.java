package com.example.jayyoungyang.tutorial3;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String[] items = {"Mango Juice", "Tomato Juice", "Grape Juice"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button listButton = (Button) findViewById(R.id.listButton);
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 팝업 창 뜨게 하기
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                // 팝업 제목 설정
                builder.setTitle("List");
                // 리스트 내용 안에 있는 내용 클릭했을 때의 처리
                builder.setItems(items, new DialogInterface.OnClickListener(){
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       // 자신이 클릭한 것을 Toast로 띄워주는 방법
                       // Toast란 어플리케이션 아래에 잠깐 나타나는 창을 말한다.
                       Toast.makeText(getApplicationContext(), items[i], Toast.LENGTH_SHORT).show();
                   }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        Button exitButton = (Button) findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                // 선언해준 빌더에 메세지 설정.
                builder.setMessage("Do you really want to exit?");
                builder.setTitle("Exit")
                        // 사용자가 임의적으로 종료할 수 없게 한다.
                        // 즉 창 바깥을 눌렀을 때 알림창이 없어지지 않도록 한다는 것이다.
                        .setCancelable(false)
                        // Yes 버튼과 No 버튼을 만들어 준다.
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                // Yes 버튼을 클릭했을 때 함수가 종료되고 프로그램 전체가 종료될 수 있도록
                                // finish 함수를 이용한다.
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                // 버튼을 눌렀을 때 dialog창이 꺼질 수 있도록 해주면 된다
                                // 그 방법은 cancel 함수를 부르면 된다.
                                dialog.cancel();
                            }
                        });
                // 알림 창을 띄우는 역할을 한다.
                AlertDialog alertDialog = builder.create();
                // dialog의 제목을 설정해 준다.
                alertDialog.setTitle("Exit Alert");
                // 알림창을 띄워준다.
                alertDialog.show();
            }
        });

    }


}
