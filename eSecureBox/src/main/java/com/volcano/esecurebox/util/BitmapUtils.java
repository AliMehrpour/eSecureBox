// Copyright (c) 2015 Volcano. All rights reserved.
package com.volcano.esecurebox.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION_CODES;
import android.view.View;

import com.volcano.esecurebox.VlApplication;
import com.volcano.esecurebox.model.Field;
import com.volcano.esecurebox.widget.CircleDrawable;

/**
 * A utility class for working with bitmaps, colors and pictures
 */
public final class BitmapUtils {

    /**
     * Make a color darker
     * @param argb The main color
     * @param value Float value [0...1]
     * @return The darker color code
     */
    public static int getDarkenColor(int argb, float value) {
        return adjustColorBrightness(argb, value);
    }

    /**
     * Make a color lighter
     * @param argb The main color
     * @param value Float value [0...1]
     * @return The lighter color code
     */
    public static int getLightenColor(int argb, float value) {
        return adjustColorBrightness(argb, 1 + value);
    }

    private static int adjustColorBrightness(int argb, float factor) {
        final float[] hsv = new float[3];
        Color.colorToHSV(argb, hsv);

        hsv[2] = Math.min(hsv[2] * factor, 1f);

        return Color.HSVToColor(Color.alpha(argb), hsv);
    }

    /**
     * Get color code from a string
     * @param color The hex color string
     * @return The color code
     */
    public static int getColor(String color) {
        return Color.parseColor(String.format("#%s", color));
    }

    /**
     * Return a drawable object associated with a particular resource ID
     * @param id The desired resource identifier
     */
    @TargetApi(VERSION_CODES.LOLLIPOP)
    public static Drawable getDrawable(int id) {
        if (Utils.hasLollipopApi()) {
            return VlApplication.getInstance().getResources().getDrawable(id, null);
        }
        else {
            //noinspection deprecation
            return VlApplication.getInstance().getResources().getDrawable(id);
        }
    }

    /**
     * Set a drawable as view background
     * @param view The view
     * @param drawable The {@link Drawable} object
     */
    @TargetApi(VERSION_CODES.JELLY_BEAN)
    public static void setBackground(View view, Drawable drawable) {
        if (Utils.hasJellyBeanApi()) {
            view.setBackground(drawable);
        }
        else {
            //noinspection deprecation
            view.setBackgroundDrawable(drawable);
        }
    }

    /**
     * Get an identifier by string name
     * @param context The context
     * @param name The identifier name
     * @return The identifier
     */
    public static int getDrawableIdentifier(Context context, String name)
    {
        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }

    /**
     * Return a drawable related to {@link Field} object.
     * <p>
     * If could find related icon in assets, return related drawable
     * <p>
     * If failed to find an icon, return a filled colored circle which display {@param ch} in it
     * <p>
     * If doesn't supply a character, return a filled colored circle
     * @param context The context
     * @param iconName The identifier of icon
     * @return The Drawable, null if isn't valid
     */
    public static Drawable getFieldDrawable(Context context, String iconName, Character ch, int color) {
        int resourceId = 0;
        if (iconName != null) {
            resourceId = BitmapUtils.getDrawableIdentifier(context, iconName);
        }

        Drawable drawable = null;

        if (resourceId != 0) {
            drawable = context.getResources().getDrawable(resourceId);
        }
        else if (ch != null) {
            drawable = new CircleDrawable(Color.TRANSPARENT, CircleDrawable.FILL, ch.toString(), color);
        }
        else if (color != 0) {
            drawable = new CircleDrawable(color, CircleDrawable.FILL);
        }

        return drawable;
    }


    /**
     * @param color The color
     * @return A {@link android.graphics.drawable.ColorDrawable}
     */
    public static ColorDrawable getColorDrawable(int color) {
        return new ColorDrawable(color);
    }

}
