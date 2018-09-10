package com.example.jayyoungyang.tutorial09_imageslider;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Adapter extends PagerAdapter {

    // 이미지를 담을 공간을 만들어준다.
    private int[] images = {R.drawable.one, R.drawable.two, R.drawable.three};
    private LayoutInflater inflater;
    private Context context;

    // 마지막으로 어탭터 생성자를 만들어주면 된다.
    public Adapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
//        이미지의 개수를 의미한다.
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        // 오브젝트를 리니어레이아웃으로 바꿔주었을때 view와 같은지를 반환해주는 작업을 한다.
        return view == ((LinearLayout) object);
    }

    // 각각의 이미지를 인스턴스화 해준다.
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // inflater 초기화
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.slider, container, false);
        ImageView imageView = (ImageView) v.findViewById(R.id.imageView);
        TextView textView = (TextView) v.findViewById((R.id.textView));
        // 현재 해당하는 페이지의 이미지를 보여주도록 한다.
        imageView.setImageResource(images[position]);
        // 현재 가리키고 있는 이미지가 몇번째인지 알려주는 텍스트 설정
        textView.setText((position + 1) + "번째 이미지입니다.");
        // 컨테이너에 해당 이미지를 추가해준다.
        container.addView(v);
        // 뷰를 반환해준다.
        return v;
    }

    // 아이템 파괴를 담당한다.
    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        container.invalidate();
    }
}
