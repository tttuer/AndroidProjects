package com.example.jayyoungyang.tutorial05_listview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class ImageAdapter extends ArrayAdapter<String> {
    // 이미지 어댑터의 생성자 생성
    ImageAdapter(Context context, String[] items) {
        super(context, R.layout.image_layout, items);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater imageInflater = LayoutInflater.from(getContext());
        View view = imageInflater.inflate(R.layout.image_layout, parent, false);
        String item = getItem(position);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        // 텍스트 뷰에 가져올 아이템을 넣어준다.
        textView.setText(item);
        if(item == "망고 쥬스")
            imageView.setImageResource(R.mipmap.img_mango);
        if(item == "토마토 쥬스")
            imageView.setImageResource(R.mipmap.img_tomato);
        if(item == "포도 쥬스")
            imageView.setImageResource(R.mipmap.img_grape);

        // 지정된 뷰를 반환함으로써 함수를 종료한다.
        return view;
    }
}
