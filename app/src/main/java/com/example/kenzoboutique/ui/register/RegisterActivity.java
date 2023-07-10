package com.example.kenzoboutique.ui.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kenzoboutique.R;
import com.example.kenzoboutique.data.repository.AuthRepository;
import com.example.kenzoboutique.data.repository.UserRepository;
import com.example.kenzoboutique.ui.DashboardActivity;
import com.example.kenzoboutique.ui.login.LoginActivity;
import com.example.kenzoboutique.utils.StringUtils;
import com.example.kenzoboutique.utils.ViewUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {
  private TextInputLayout fullnameLayout, usernameLayout, passwordLayout, confirmPassLayout;
  private TextInputEditText edtFullname, edtUsername, edtPassword, edtConfirmPass;
  private AppCompatButton btnSignUp;
  private TextView hyperlinkSignIn;
  private UserRepository userRepository;
  private AuthRepository authRepository;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);

    userRepository = new UserRepository(this);
    authRepository = new AuthRepository(this);
    fullnameLayout = (TextInputLayout) findViewById(R.id.fullname_name_input);
    usernameLayout = (TextInputLayout) findViewById(R.id.username_input);
    passwordLayout = (TextInputLayout) findViewById(R.id.password_input);
    confirmPassLayout = (TextInputLayout) findViewById(R.id.confirm_password_input);
    edtFullname = (TextInputEditText) findViewById(R.id.fullname_edit_text);
    edtUsername = (TextInputEditText) findViewById(R.id.username_edit_text);
    edtPassword = (TextInputEditText) findViewById(R.id.password_edit_text);
    edtConfirmPass = (TextInputEditText) findViewById(R.id.confirm_password_edit_text);
    btnSignUp = (AppCompatButton) findViewById(R.id.btnSignUp);
    hyperlinkSignIn = (TextView) findViewById(R.id.textToSignIn);
    formConfiguration();

    initialize();
  }

  @Override
  protected void onStart() {
    super.onStart();
    if (authRepository.isUserLoggedIn()) {
      Intent intent = new Intent(RegisterActivity.this, DashboardActivity.class);
      startActivity(intent); finish();
    }
  }

  private void initialize() {
    btnSignUp.setOnClickListener(v -> {
      String txtFullname = edtFullname.getText().toString().trim();
      String txtUsername = edtUsername.getText().toString().trim();
      String txtPassword = edtPassword.getText().toString().trim();
      String txtConfirmPass = edtConfirmPass.getText().toString().trim();
      if (!validateFormInput(
        txtFullname,
        txtUsername,
        txtPassword,
        txtConfirmPass
      )) return;
      if (authRepository.register(txtFullname, txtUsername, txtPassword)) {
        Toast.makeText(this, "Register succeed!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent); finish();
      }
      else ViewUtils.createErrorDialog(this, "Failed", "Failed to create account!");
    });

    hyperlinkSignIn.setOnClickListener(v -> {
      Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
      startActivity(intent); finish();
    });
  }

  private void formConfiguration() {
    ViewUtils.clearErrorMessageOnType(fullnameLayout, edtFullname);
    ViewUtils.clearErrorMessageOnType(usernameLayout, edtUsername);
    ViewUtils.clearErrorMessageOnType(passwordLayout, edtPassword);
    ViewUtils.clearErrorMessageOnType(confirmPassLayout, edtConfirmPass);
  }

  private boolean validateFormInput(
    String fullname,
    String username,
    String password,
    String confirmPass
  ) {
    boolean flag = true;
    if (!validateEmptyField(fullname, username, password, confirmPass)) return false;
    if (!validateFullname(fullname)) flag = false;
    if (!validateUsername(username)) flag = false;
    if (!validatePassword(password, confirmPass)) flag = false;
    return flag;
  }

  private boolean validateEmptyField(
    String fullname,
    String username,
    String password,
    String confirmPass
  ) {
    boolean flag = true;
    if (fullname == null || fullname.trim().isEmpty()) {
      fullnameLayout.setError("Fullname is required");
      flag = false;
    }
    if (username == null || username.trim().isEmpty()) {
      usernameLayout.setError("Username is required");
      flag = false;
    }
    if (password == null || password.trim().isEmpty()) {
      passwordLayout.setError("Password is required");
      flag = false;
    }
    if (confirmPass == null || confirmPass.trim().isEmpty()) {
      confirmPassLayout.setError("Confirm Password is required");
      flag = false;
    }
    return flag;
  }

  private boolean validateFullname(String fullname) {
    boolean flag = true;
    if (fullname.length() < 3) {
      fullnameLayout.setError("Fullname is too short");
      flag = false;
    }
    if (!StringUtils.alphabeticChecker(fullname)) {
      fullnameLayout.setError("Fullname must only contains alhpabet");
      flag = false;
    }
    return flag;
  }

  private boolean validateUsername(String username) {
    boolean flag = true;
    if (username.length() < 6) {
      usernameLayout.setError("Username is too short");
      flag = false;
    }
    if (userRepository.findUserByUsername(username) != null) {
      usernameLayout.setError("Username is exists");
      flag = false;
    }
    return flag;
  }

  private boolean validatePassword(String password, String confirmPassword) {
    boolean flag = true;
    if (password.length() < 6) {
      passwordLayout.setError("Password is too short");
      flag = false;
    }
    if (!password.equals(confirmPassword)) {
      confirmPassLayout.setError("Confirm Password didn't match");
      flag = false;
    }
    return flag;
  }
}