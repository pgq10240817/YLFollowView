package app.yhpl.widgets;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

import java.lang.ref.WeakReference;

/**
 * Created by pugq on 2018/4/10.
 */

public class YLRevealFinishedIceCreamSandwich extends AnimatorListenerAdapter {
    int mFeaturedLayerType;
    int mLayerType;
    WeakReference<IYLRevealAnimator> mReference;

    public YLRevealFinishedIceCreamSandwich(IYLRevealAnimator target, int layerType) {
        this.mReference = new WeakReference(target);
        this.mLayerType = ((View) target).getLayerType();
        this.mFeaturedLayerType = layerType;
    }

    public void onAnimationStart(Animator animation) {
        IYLRevealAnimator target = (IYLRevealAnimator) this.mReference.get();
        if (target != null) {
            ((View) target).setLayerType(this.mFeaturedLayerType, null);
            target.onRevealAnimationStart();
        }
    }

    public void onAnimationCancel(Animator animation) {
        IYLRevealAnimator target = (IYLRevealAnimator) this.mReference.get();
        if (target != null) {
            ((View) target).setLayerType(this.mLayerType, null);
            target.onRevealAnimationCancel();
        }
    }

    public void onAnimationEnd(Animator animation) {
        IYLRevealAnimator target = (IYLRevealAnimator) this.mReference.get();
        if (target != null) {
            ((View) target).setLayerType(this.mLayerType, null);
            target.onRevealAnimationEnd();
        }
    }
}