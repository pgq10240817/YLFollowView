package app.yhpl.widgets;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)

public @interface YLFollowState {
    int FOLLOWED = 0;//已经关注
    int FOLLOW = FOLLOWED + 1;//未关注
    int LOADING = FOLLOW + 1;


}
