package com.example.kenzoboutique.utils;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;

import com.example.kenzoboutique.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ViewUtils {
  private static SweetAlertDialog dialog;

  public static void createSuccessDialog(Context context, String title, String msg) {
    dialog = new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE);
    dialog.setTitleText(title);
    dialog.setContentText(msg);
    dialog.show();
  }

  public static void createErrorDialog(Context context, String title, String msg) {
    dialog = new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
    dialog.setTitleText(title);
    dialog.setContentText(msg);
    dialog.show();
  }

  public static void createWarningDialog(Context context, String title, String msg) {
    dialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE);
    dialog.setTitleText(title);
    dialog.setContentText(msg);
    dialog.show();
  }

  public static SweetAlertDialog createCustomCartDialog(Context context, Integer icon, String title, String msg) {
    dialog = new SweetAlertDialog(context, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
    dialog.setCustomImage(icon);
    dialog.setTitleText(title);
    dialog.setContentText(msg);
    return dialog;
  }

  public static void clearErrorMessageOnType(TextInputLayout layout, TextInputEditText editText) {
    editText.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {}
      @Override
      public void afterTextChanged(Editable s) {layout.setError(null);}
    });
  }
}
