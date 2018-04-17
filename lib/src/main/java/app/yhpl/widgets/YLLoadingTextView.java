package app.yhpl.widgets;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by pugq on 2018/4/10.
 */

public class YLLoadingTextView extends AppCompatTextView {
    private YLCircularProgressDrawable mDrawable;
    private boolean mIsLoading = false;

    public YLLoadingTextView(Context context) {
        this(context, null);
    }

    public YLLoadingTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public YLLoadingTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDrawable();
    }

    private int dp2px(float dp) {
        return (int) (Resources.getSystem().getDisplayMetrics().density * dp + 0.5f);
    }

    private void initDrawable() {
        if (this.mDrawable == null) {
            this.mDrawable = new YLCircularProgressDrawable(getContext());
            this.mDrawable.setCenterRadius(dp2px(7.0f));
            this.mDrawable.setStrokeWidth(dp2px(1.5f));
            this.mDrawable.setCallback(this);
        }
    }


    @Override
    public void setTextColor(int color) {
        super.setTextColor(color);
        this.mDrawable.setColorSchemeColors(new int[]{color});
    }

    @Override
    public void setTextColor(ColorStateList colors) {
        super.setTextColor(colors);
        initDrawable();
        this.mDrawable.setColorSchemeColors(new int[]{colors.getDefaultColor()});
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (visibility == 0) {
            if (this.mIsLoading && !this.mDrawable.isRunning()) {
                this.mDrawable.start();
            }
        } else if (this.mDrawable.isRunning()) {
            this.mDrawable.stop();
        }
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mDrawable.setBounds(0, 0, w, h);
    }

    public void toggleLoading(boolean isLoading) {
        this.mIsLoading = isLoading;
        if (isLoading) {
            mDrawable.start();
        } else {
            mDrawable.stop();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.mIsLoading) {
            this.mDrawable.draw(canvas);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!this.mIsLoading) {
            super.onDraw(canvas);
        }
    }

    @Override
    protected boolean verifyDrawable(Drawable who) {
        return who == this.mDrawable || super.verifyDrawable(who);
    }

}
