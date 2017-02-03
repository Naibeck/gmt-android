package com.ctb.gmt.naibeck.guiamultiturismocentroamerica.utility;

import android.support.annotation.NonNull;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;

public class AnimationUtil {
    public static final int LOGO_ANIMATION_INTERVAL = 800;
    public static final int HOME_BACKGROUND_ANIMATION = 400;

    public static Animation getFadeInAnimation(int interval) {
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        fadeIn.setDuration(interval);
        return fadeIn;

    }
}
