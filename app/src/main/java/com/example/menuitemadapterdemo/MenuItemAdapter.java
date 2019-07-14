package com.example.menuitemadapterdemo;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.core.widget.TextViewCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * author : yangjunjin
 * date   : 2019/7/10 0:27
 */
public class MenuItemAdapter extends BaseAdapter {
    private final int mIconSize;
    private LayoutInflater mInflater;
    private Context mContext;

    public MenuItemAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mContext = context;

        mIconSize = 24;//24dp
    }

    private List<LvMenuItem> mItems = new ArrayList<LvMenuItem>(
            Arrays.asList(
                    new LvMenuItem(R.mipmap.ic_launcher, "Home"),
                    new LvMenuItem(),
                    new LvMenuItem("Sub Items"), new LvMenuItem(R.mipmap.ic_launcher, "Home"),
                    new LvMenuItem(),
                    new LvMenuItem("Sub Items"), new LvMenuItem(R.mipmap.ic_launcher, "Home"),
                    new LvMenuItem(),
                    new LvMenuItem("Sub Items")
            ));


    @Override
    public int getCount() {
        return mItems.size();
    }


    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).type;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LvMenuItem item = mItems.get(position);
        switch (item.type) {
            case LvMenuItem.TYPE_NORMAL:
                if (convertView == null) {
                    convertView = mInflater.inflate(R.layout.design_drawer_item, parent,
                            false);
                }
                TextView itemView = (TextView) convertView;
                itemView.setText(item.name);
                Drawable icon = mContext.getResources().getDrawable(item.icon);
                setIconColor(icon);
                if (icon != null) {
                    icon.setBounds(0, 0, mIconSize, mIconSize);
                    TextViewCompat.setCompoundDrawablesRelative(itemView, icon, null, null, null);
                }

                break;
            case LvMenuItem.TYPE_NO_ICON:
                if (convertView == null) {
                    convertView = mInflater.inflate(R.layout.design_drawer_item_subheader,
                            parent, false);
                }
                TextView subHeader = (TextView) convertView;
                subHeader.setText(item.name);
                break;
            case LvMenuItem.TYPE_SEPARATOR:
                if (convertView == null) {
                    convertView = mInflater.inflate(R.layout.design_drawer_item_separator,
                            parent, false);
                }
                break;
        }

        return convertView;
    }

    public void setIconColor(Drawable icon) {
        int textColorSecondary = android.R.attr.textColorSecondary;
        TypedValue value = new TypedValue();
        if (!mContext.getTheme().resolveAttribute(textColorSecondary, value, true)) {
            return;
        }
        int baseColor = mContext.getResources().getColor(value.resourceId);
        icon.setColorFilter(baseColor, PorterDuff.Mode.MULTIPLY);
    }
}