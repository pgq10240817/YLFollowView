package app.yhpl.widgets;

/**
 * Created by pugq on 2018/4/16.
 */

public interface IYLFollowViewStateDelegate {
    void onYLFollowViewStateLoading(YLFollowView followView, YLLoadingTextView textView);

    void onYLFollowViewStateFollowed(YLFollowView followView, YLLoadingTextView textView);

    void onYLFollowViewStateFollow(YLFollowView followView, YLLoadingTextView textView);

}