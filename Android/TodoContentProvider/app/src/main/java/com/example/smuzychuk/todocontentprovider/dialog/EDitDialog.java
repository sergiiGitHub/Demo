package com.example.smuzychuk.todocontentprovider.dialog;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.smuzychuk.todocontentprovider.R;

/**
 * Created by sergii on 23.03.19.
 */

public class EDitDialog extends BaseDialog {

    public static final String KEY_TITLE = "title";
    public static final String KEY_DETAIL = "detail";
    public static final String KEY_POSITION = "position";
    private EditText tvTitle;
    private EditText tvDetail;
    private int position;

    @Override
    protected Bundle extractResult(View dialogView) {
        String title = tvTitle.getText().toString();
        String detail = tvDetail.getText().toString();

        final Bundle bundle = new Bundle();
        bundle.putString(KEY_TITLE, title);
        bundle.putString(KEY_DETAIL, detail);
        bundle.putInt(KEY_POSITION, position);
        return bundle;
    }

    @Override
    protected void populateView(View dialogView, Bundle bundle) {
        tvTitle = dialogView.findViewById(R.id.title);
        tvDetail = dialogView.findViewById(R.id.detail);
        String strTitle = bundle.getString(KEY_TITLE);
        String strDetail = bundle.getString(KEY_DETAIL);
        position = bundle.getInt(KEY_DETAIL);

        tvTitle.setText(strTitle);
        tvDetail.setText(strDetail);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_signin;
    }
}
