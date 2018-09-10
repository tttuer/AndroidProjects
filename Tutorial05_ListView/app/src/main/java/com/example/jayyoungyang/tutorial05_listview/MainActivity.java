package com.example.jayyoungyang.tutorial05_listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] items = {"망고 쥬스", "토마토 쥬스", "포도 쥬스"};
//        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        ListAdapter adapter = new ImageAdapter(this, items);
        ListView listView = (ListView) findViewById(R.id.listView);
        // listView에 어댑터 설정
        listView.setAdapter(adapter);

        // 아이템을 클릭했을때 이벤트를 발생 시킨다.
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // parent에 있는 특정한 아이템을 가져온다.
                        // 그 이후에 String item에 저장한다.
                        String item = String.valueOf(parent.getItemAtPosition(position));
                        // 출력을 Toast로 해준다.
                        // show() 함수를 이용해 보여준다.
                        Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}
