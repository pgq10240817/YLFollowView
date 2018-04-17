package app.yhpl.widgets;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;

/**
 * Created by pugq on 2018/4/16.
 */

public class DefaultYLFollowViewDelegate implements IYLFollowViewStateDelegate {
    private String followText;
    private String followedText;
    private ColorStateList followTextColor, followedTextColor;
    private int defaultFollowColor, defaultFollowedColor;
    private Drawable followMask, followedMask;
    private Drawable followBackGround, followedBackGround;

    private DefaultYLFollowViewDelegate() {

    }

    public DefaultYLFollowViewDelegate(Context context, AttributeSet attrs, int defStyleAttr) {
        int defaultFollowColor = getAttrData(context, R.attr.colorPrimary);
        int defaultFollowedColor = getAttrData(context, R.attr.colorPrimaryDark);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.YLFollowView, defStyleAttr, 0);
        followTextColor = a.getColorStateList(R.styleable.YLFollowView_ylFolloweViewFollowTextColor);
        followedTextColor = a.getColorStateList(R.styleable.YLFollowView_ylFolloweViewFollowedTextColor);

        followText = a.getString(R.styleable.YLFollowView_ylFolloweViewFollowText);
        followedText = a.getString(R.styleable.YLFollowView_ylFolloweViewFollowedText);
        followBackGround = a.getDrawable(R.styleable.YLFollowView_ylFolloweViewFollowBackGround);
        followedBackGround = a.getDrawable(R.styleable.YLFollowView_ylFolloweViewFollowedBackGround);
        followMask = a.getDrawable(R.styleable.YLFollowView_ylFolloweViewFollowMask);
        followedMask = a.getDrawable(R.styleable.YLFollowView_ylFolloweViewFollowedMask);
        a.recycle();
    }

    private int getAttrData(Context context, int attr) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(attr, typedValue, true);
        return typedValue.data;
    }

    @Override
    public void onYLFollowViewStateLoading(YLFollowView followView, YLLoadingTextView textView) {
        textView.setText("");
        textView.toggleLoading(true);
    }

    @Override
    public void onYLFollowViewStateFollowed(YLFollowView followView, YLLoadingTextView textView) {
        if (followMask != null) {
            followView.setBackgroundDrawable(followMask);
        }

        textView.toggleLoading(false);
        textView.setText(followedText);
        if (followedTextColor != null) {
            textView.setTextColor(followedTextColor);
        }
        if (followedBackGround != null) {
            textView.setBackgroundDrawable(followedBackGround);
        }
    }

    @Override
    public void onYLFollowViewStateFollow(YLFollowView followView, YLLoadingTextView textView) {
        if (followedMask != null) {
            followView.setBackgroundDrawable(followedMask);
        }
        textView.toggleLoading(false);
        textView.setText(followText);
        if (followTextColor != null) {
            textView.setTextColor(followTextColor);
        }
        if (followBackGround != null) {
            textView.setBackgroundDrawable(followBackGround);
        }
    }
}
