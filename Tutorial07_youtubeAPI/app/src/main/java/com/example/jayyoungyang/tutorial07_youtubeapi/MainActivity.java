package com.example.jayyoungyang.tutorial07_youtubeapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class MainActivity extends YouTubeBaseActivity {

    YouTubePlayerView youtubeView;
    Button button;
    // 이벤트 처리를 위해서 리스너를 받아준다.
    YouTubePlayer.OnInitializedListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.youtubeButton);
        youtubeView = (YouTubePlayerView) findViewById(R.id.youtubeView);
        // 이후에 리스너에 이벤트 처리를 해준다.
        listener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                // 성공을 할 경우 유튜브 영상을 보여주도록 한다.
                // "" 안에는 원하는 영상의 아이디 선택해 주면 된다.
                youTubePlayer.loadVideo("h4ika4Dq34I");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // "" 안에는 api키를 복사해주면 된다.
                youtubeView.initialize("AIzaSyDE6j69zFZ5ZN1m_iC_Agaa8aDSSgO294k", listener);
            }
        });
    }
}
