package app.yhpl.widgets;

import android.util.Property;

/**
 * Created by pugq on 2018/4/10.
 */

public class YLRevealRadius extends Property<IYLRevealAnimator, Float> {
    public YLRevealRadius() {
        super(Float.class, "revealRadius");
    }

    public void set(IYLRevealAnimator object, Float value) {
        object.setRevealRadius(value.floatValue());
    }

    public Float get(IYLRevealAnimator object) {
        return Float.valueOf(object.getRevealRadius());
    }
}