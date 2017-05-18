package com.arman.astro.ui.base;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arman.astro.R;

import static android.R.id.message;

/**
 * Created by Arman on 5/15/2017.
 */

public class BaseActivity extends AppCompatActivity implements IBaseView {

    protected ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

    }

    @Override
    public void showLoading() {
        if(progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideLoading() {
        if(progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onError(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView
                .findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.white));
        snackbar.show();
    }
}
