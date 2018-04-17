package app.yhpl.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import app.yhpl.widgets.YLFollowState;
import app.yhpl.widgets.YLFollowView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private YLFollowView followView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        followView = findViewById(R.id.followView);
        followView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final int oldState = followView.getState();
        followView.onStateEnter(YLFollowState.LOADING);
        followView.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (oldState == YLFollowState.FOLLOW) {
                    followView.onStateEnter(YLFollowState.FOLLOWED);
                } else {
                    followView.onStateEnter(YLFollowState.FOLLOW);
                }
            }
        }, 2000);
    }
}
