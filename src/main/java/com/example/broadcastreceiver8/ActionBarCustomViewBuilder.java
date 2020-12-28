package com.example.broadcastreceiver8;

import android.graphics.drawable.ColorDrawable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import static androidx.appcompat.app.ActionBar.DISPLAY_SHOW_CUSTOM;

public class ActionBarCustomViewBuilder {
    private View.OnClickListener backButtonListen;
    private View.OnClickListener rightButtonListen;
    private View.OnClickListener titleButtonListen;
    int rightResourceId;
    int leftResourceId;
    String title;
    int color = 0;


    int themeColor = 0;
    int titleColor = 0;
    private AppCompatActivity activity;
    Layout.Alignment titleAlignment;
    private String rightTitle;

    public ActionBarCustomViewBuilder(AppCompatActivity activity) {
        this.activity = activity;
    }

    public ActionBarCustomViewBuilder withOnBackListen(int resId, View.OnClickListener backButtonListen) {
        this.backButtonListen = backButtonListen;
        this.leftResourceId = resId;
        return this;
    }

    public ActionBarCustomViewBuilder withRightItem(int resId, View.OnClickListener rightButtonListen){
        return this.withRightItem(resId, null, rightButtonListen);
    }

    public ActionBarCustomViewBuilder withRightItem(int resId, String title, View.OnClickListener rightButtonListen){
        this.rightButtonListen = rightButtonListen;
        this.rightResourceId = resId;
        this.rightTitle = title;
        return this;
    }

    public ActionBarCustomViewBuilder withTitle(String title) {
        return this.withTitle(title, null);
    }

    public ActionBarCustomViewBuilder withTitle(String title, View.OnClickListener titleClickListen){
        return withTitle(title, titleClickListen, Layout.Alignment.ALIGN_CENTER);
    }

    public ActionBarCustomViewBuilder withTitle(String title, View.OnClickListener titleButtonListen, Layout.Alignment alignment){
        this.titleAlignment = alignment;
        this.title = title;
        this.titleButtonListen = titleButtonListen;
        return this;
    }

    public ActionBarCustomViewBuilder withBackgroundColor(int color){
        this.color = color;
        return this;
    }

    public ActionBarCustomViewBuilder withTitleColor(int color){
        this.titleColor = color;
        return this;
    }

    public ActionBarCustomViewBuilder withThemeColor(int color){
        this.themeColor = color;
        return this;
    }

    public View build(boolean finishOnBack) {
        LayoutInflater inflater = LayoutInflater.from(activity.getApplicationContext());
        View v = inflater.inflate(this.titleAlignment == Layout.Alignment.ALIGN_CENTER? R.layout.actionbar_layout: R.layout.action_bar_left_title, new LinearLayout(activity.getApplicationContext()), false);
        ImageButton leftButton = v.findViewById(R.id.action_button_left);
        ImageButton rightButton = v.findViewById(R.id.right_button);
        TextView titleView = v.findViewById(R.id.action_textview);
        TextView leftTitleView = v.findViewById(R.id.left_title);
        TextView rightTextView = v.findViewById(R.id.right_text);
        View leftItemLayout  =  v.findViewById(R.id.ll_left);
        if (this.leftResourceId != 0) {

            leftButton.setImageResource(leftResourceId);
//            if(finishOnBack){
//                leftButton.setPadding(20,0,0,0);
//                titleView.setGravity(Gravity.LEFT|Gravity.CENTER);
//                titleView.setPadding(20,0,0,0);
//            }
        }
//        if(this.rightResourceId != 0){
//            rightButton.setCropToPadding(true);
//            rightTextView.setGravity(Gravity.LEFT|Gravity.CENTER);
//            rightTextView.setPadding(20,0,0,0);
//        }
        if (this.backButtonListen != null) {
            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    backButtonListen.onClick(v);
                    //activity.finish();
                }
            };
            leftItemLayout.setOnClickListener(!finishOnBack? this.backButtonListen : listener);
            leftButton.setOnClickListener(!finishOnBack? this.backButtonListen : listener);
            leftTitleView.setOnClickListener(!finishOnBack? this.backButtonListen : listener);
        }
        else if (finishOnBack) {
            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.finish();
                }
            };
            leftItemLayout.setOnClickListener(listener);
            leftButton.setOnClickListener(listener);
            leftTitleView.setOnClickListener(listener);
        }
        if (this.rightResourceId > 0) {
            rightButton.setImageResource(this.rightResourceId);
            rightButton.setCropToPadding(true);
        }
        if (this.rightTitle != null) {
            rightTextView.setText(this.rightTitle);
        }
        if (this.rightButtonListen != null) {
            rightButton.setOnClickListener(rightButtonListen);
            rightTextView.setOnClickListener(rightButtonListen);
        }
        if (this.color != 0) {
            v.setBackgroundColor(this.color);
        }
        if (this.title != null) {
            if (this.titleAlignment == Layout.Alignment.ALIGN_NORMAL) {
                leftTitleView.setText(title);
                titleView.setText("");
            } else {
                leftTitleView.setText("");
                titleView.setText(title);
            }
        }
        if(this.titleButtonListen != null){
            titleView.setOnClickListener(titleButtonListen);
        }
        if (this.titleColor != 0) {
            titleView.setTextColor(this.titleColor);
        }
        if (this.themeColor != 0) {
            titleView.setTextColor(this.themeColor);
            rightTextView.setTextColor(this.themeColor);
            if (this.themeColor == ContextCompat.getColor(this.activity, R.color.white) && this.leftResourceId == 0) {
                leftButton.setImageResource(R.mipmap.btn_nav_back_white);
            }
        }
        return v;
    }

    public View buildAndAttachToActionBar() {
        return buildAndAttachToActionBar(true);
    }

    public View buildAndAttachToActionBar(boolean finishOnBack) {
        ActionBar bar  = activity.getSupportActionBar();
        if (bar == null) {
            return null;
        }
        bar.setBackgroundDrawable(new ColorDrawable(color == 0 ? ContextCompat.getColor(activity, R.color.transparent): color));
        bar.setElevation(0);
        bar.setDisplayOptions(DISPLAY_SHOW_CUSTOM);
        View v = this.build(finishOnBack);
        if (v != null) {
            bar.setCustomView(v);
        }
        return v;
    }
}
