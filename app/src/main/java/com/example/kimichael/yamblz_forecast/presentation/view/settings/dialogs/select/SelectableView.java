package com.example.kimichael.yamblz_forecast.presentation.view.settings.dialogs.select;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.kimichael.yamblz_forecast.R;
import com.jakewharton.rxbinding2.view.RxView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sinjvf on 05.08.2017.
 */

public class SelectableView extends LinearLayout implements Checkable {
    @BindView(R.id.button)
    RadioButton button;

    private boolean isChecked = false;

    public SelectableView(Context context) {
        this(context, null);
    }

    public SelectableView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelectableView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(getContext(), R.layout.item_select, this);
        ButterKnife.bind(this);
        RxView.clicks(this).subscribe(click -> toggle());
    }

    @Override
    public void setChecked(boolean b) {
        button.setChecked(b);
        this.isChecked = b;
    }

    @Override
    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public void toggle() {
        this.isChecked = !this.isChecked;
        setChecked(isChecked);
    }



}
