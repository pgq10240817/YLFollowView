package app.yhpl.widgets;

import android.animation.Animator;
import android.animation.ObjectAnimator;

/**
 * Created by pugq on 2018/4/10.
 */

public class RevealHelper {

    public static Animator createCircularReveal(IYLRevealAnimator view, Object padload, int centerX, int centerY, float startRadius, float endRadius, int layerType) {
        view.attachRevealInfo(new RevealInfo(padload, centerX, centerY, startRadius, endRadius));
        ObjectAnimator reveal = ObjectAnimator.ofFloat(view, new YLRevealRadius(), new float[]{startRadius, endRadius});
        reveal.addListener(new YLRevealFinishedIceCreamSandwich(view, layerType));
        return reveal;
    }
}
