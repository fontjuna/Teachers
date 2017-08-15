package com.example.myapplication.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

/**
 * Created by junsuk on 2017. 8. 3..
 */

public class MyAlertDialogFragment extends DialogFragment{

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("삭제");
        builder.setMessage("정말 삭제 하시겠습니까?");
        builder.setCancelable(false);
        builder.setPositiveButton("예", null);
        builder.setNegativeButton("아니오", null);
        return builder.create();
    }
}
