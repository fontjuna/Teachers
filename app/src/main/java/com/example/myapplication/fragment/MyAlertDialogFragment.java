package com.example.myapplication.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

/**
 * Created by junsuk on 2017. 8. 3..
 */

public class MyAlertDialogFragment extends DialogFragment {

    private DialogInterface.OnClickListener mDialogButtonClickListener;

    public static MyAlertDialogFragment newInstance(DialogInterface.OnClickListener listener) {
        Bundle args = new Bundle();
        MyAlertDialogFragment fragment = new MyAlertDialogFragment();
        fragment.setArguments(args);
        fragment.setDailogButtonClickListener(listener);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("삭제");
        builder.setMessage("정말 삭제 하시겠습니까?");
        builder.setCancelable(false);
        builder.setPositiveButton("예", mDialogButtonClickListener);
        builder.setNegativeButton("아니오", null);
        return builder.create();
    }

    public void setDailogButtonClickListener(DialogInterface.OnClickListener listener) {
        mDialogButtonClickListener = listener;
    }
}
