package app.yhpl.widgets;

/**
 * Created by pugq on 2018/4/10.
 */

public interface IYLRevealAnimator {

    void attachRevealInfo(RevealInfo revealInfo);

    float getRevealRadius();

    void onRevealAnimationCancel();

    void onRevealAnimationEnd();

    void onRevealAnimationStart();

    void setRevealRadius(float f);
}
