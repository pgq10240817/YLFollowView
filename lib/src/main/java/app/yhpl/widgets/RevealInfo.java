package app.yhpl.widgets;

/**
 * Created by pugq on 2018/4/10.
 */

public class RevealInfo {
    public final int centerX;
    public final int centerY;
    public final float endRadius;
    public final Object payload;
    public final float startRadius;

    public RevealInfo(Object payload, int centerX, int centerY, float startRadius, float endRadius) {
        this.payload = payload;
        this.centerX = centerX;
        this.centerY = centerY;
        this.startRadius = startRadius;
        this.endRadius = endRadius;
    }

}
