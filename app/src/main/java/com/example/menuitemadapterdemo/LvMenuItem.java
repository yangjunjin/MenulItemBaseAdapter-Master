package com.example.menuitemadapterdemo;

import android.text.TextUtils;

/**
 * author : yangjunjin
 * date   : 2019/7/10 0:18
 */
public class LvMenuItem {

    private static final int NO_ICON = 0;
    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_NO_ICON = 1;
    public static final int TYPE_SEPARATOR = 2;

    public int type;
    public String name;
    public int icon;

    public LvMenuItem() {
        this(null);
    }

    public LvMenuItem(String name) {
        this(NO_ICON, name);
    }

    public LvMenuItem(int icon, String name) {
        this.icon = icon;
        this.name = name;

        if (icon == NO_ICON && TextUtils.isEmpty(name)) {
            type = TYPE_SEPARATOR;
        } else if (icon == NO_ICON) {
            type = TYPE_NO_ICON;
        } else {
            type = TYPE_NORMAL;
        }
        if (type != TYPE_SEPARATOR && TextUtils.isEmpty(name)) {
            throw new IllegalArgumentException("you need set a name for a non-SEPARATOR item");
        }
    }
}
