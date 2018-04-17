package app.yhpl.widgets;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.TextView;


/**
 * Created by pugq on 2018/4/10.
 */

public class YLFollowView extends FrameLayout implements IYLRevealAnimator {

    private float radius;
    private boolean mIntercept;
    private float lastX;
    private float lastY;
    private YLLoadingTextView mTextView;
    private Path mRevealPath;
    private RevealInfo mRevealInfo;
    @YLFollowState
    private int state = YLFollowState.FOLLOW;
    private IYLFollowViewStateDelegate mDelegate;


    public YLFollowView(@NonNull Context context) {
        this(context, null);
    }

    public YLFollowView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public YLFollowView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mDelegate = new DefaultYLFollowViewDelegate(context, attrs, defStyleAttr);
        mRevealPath = new Path();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTextView = (YLLoadingTextView) findViewById(android.R.id.title);
        if (mTextView == null) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                if (child instanceof TextView) {
                    mTextView = (YLLoadingTextView) child;
                    break;
                }
            }
        }
        onStateEnter(getState(), false);
    }


    public boolean isIntercept() {
        return mIntercept;
    }

    public int getState() {
        return state;
    }


    public void onStateEnter(int state) {
        onStateEnter(state, true);
    }

    public void onStateEnter(int state, boolean isNeedAnimation) {
        this.state = state;

        switch (state) {
            case YLFollowState.FOLLOW:
                if (mDelegate != null) {
                    mDelegate.onYLFollowViewStateFollow(this, mTextView);
                }
                break;
            case YLFollowState.FOLLOWED:
                if (mDelegate != null) {
                    mDelegate.onYLFollowViewStateFollowed(this, mTextView);
                }
                break;
            case YLFollowState.LOADING:
                if (mDelegate != null) {
                    mDelegate.onYLFollowViewStateLoading(this, mTextView);
                }
                break;
            default:
                break;
        }
        if (isNeedAnimation) {
            startAnimator(state);
        }
    }


    private int getMaxRadius() {
        int w = getWidth();
        int h = getHeight();
        return (int) Math.hypot(w, h);
    }

    private void startAnimator(int state) {
        if (mIntercept) {
            Animation animation = getAnimation();
            if (animation != null) {
                animation.cancel();
            }
        }
        if (state == YLFollowState.FOLLOW || state == YLFollowState.FOLLOWED) {
            final int startR = getMaxRadius();
            Animator animator = RevealHelper.createCircularReveal(this, state, (int) lastX,
                    (int) lastY, 0, startR, View.LAYER_TYPE_SOFTWARE);
            animator.setDuration(400);
            animator.start();
        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction() & MotionEvent.ACTION_MASK;
        if (action == MotionEvent.ACTION_DOWN) {
            lastX = ev.getX();
            lastY = ev.getY();
        }
        return super.dispatchTouchEvent(ev);
    }


    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        if (!this.mIntercept) {
            return super.drawChild(canvas, child, drawingTime);
        }
        int state = canvas.save();
        this.mRevealPath.reset();
        this.mRevealPath.addCircle(this.lastX, this.lastY, this.radius, Path.Direction.CW);
        canvas.clipPath(this.mRevealPath);
        boolean isInvalided = super.drawChild(canvas, child, drawingTime);
        canvas.restoreToCount(state);
        return isInvalided;
    }


    @Override
    public void attachRevealInfo(RevealInfo info) {
        this.mRevealInfo = info;
    }


    @Override
    public void setRevealRadius(float radius) {
        this.radius = radius;
        postInvalidate();
    }

    @Override
    public float getRevealRadius() {
        return this.radius;
    }

    @Override
    public void onRevealAnimationCancel() {
        mIntercept = false;
    }

    @Override
    public void onRevealAnimationEnd() {
        setBackgroundDrawable(null);
        mIntercept = false;
    }

    @Override
    public void onRevealAnimationStart() {
        mIntercept = true;
    }
}
