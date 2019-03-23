package com.example.smuzychuk.todocontentprovider.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.smuzychuk.todocontentprovider.R;

/**
 * Created by sergii on 23.03.19.
 */

public abstract class BaseDialog implements DialogInterface.OnDismissListener {

    private static final String TAG = BaseDialog.class.getSimpleName();

    private OnApplyListener onApplyListener;

    public void show(Context context, Bundle bundle) {
        View dialogView = createView(context, bundle);
        Dialog dialog = createDialog(context, dialogView);
        dialog.show();
    }

    private Dialog createDialog(Context context, final View dialogView) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setOnDismissListener(this);
        builder.setView(dialogView)
                // Add action buttons
                .setPositiveButton(R.string.apply, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if (onApplyListener != null) {
                            onApplyListener.onDialogResult(extractResult(dialogView));
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        return builder.create();
    }

    private View createView(Context context, Bundle bundle) {
        // Get the layout inflater
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate and set the layout for the dialog
        // Pass null as the parent dialogView because its going in the dialog layout
        final View dialogView = inflater.inflate(getLayoutId(), null);
        populateView(dialogView, bundle);
        return dialogView;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        Log.d(TAG, "onDismiss: ");
    }

    protected abstract Bundle extractResult(View dialogView);

    protected abstract void populateView(View dialogView, Bundle bundle);

    protected abstract int getLayoutId();

    public void setOnApplyListener(OnApplyListener onApplyListener) {
        this.onApplyListener = onApplyListener;
    }

    public interface OnApplyListener {
        void onDialogResult(Bundle bundle);
    }
}
