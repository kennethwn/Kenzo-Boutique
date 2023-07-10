package com.example.kenzoboutique.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kenzoboutique.R;
import com.example.kenzoboutique.data.repository.AuthRepository;
import com.example.kenzoboutique.ui.DashboardActivity;
import com.example.kenzoboutique.ui.register.RegisterActivity;
import com.example.kenzoboutique.utils.ViewUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class LoginActivity extends AppCompatActivity {
  private TextInputLayout usernameLayout, passwordLayout;
  private TextInputEditText edtUsername, edtPassword;
  private AppCompatButton btnSignIn;
  private TextView hyperlinkSignUp;
  private AuthRepository authRepository;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    authRepository = new AuthRepository(this);
    usernameLayout = (TextInputLayout) findViewById(R.id.username_input);
    passwordLayout = (TextInputLayout) findViewById(R.id.password_input);
    edtUsername = (TextInputEditText) findViewById(R.id.username_edit_text);
    edtPassword = (TextInputEditText) findViewById(R.id.password_edit_text);
    btnSignIn = (AppCompatButton) findViewById(R.id.btnSignIn);
    hyperlinkSignUp = (TextView) findViewById(R.id.textToSignUp);
    formConfiguration();

    initialize();
  }

  @Override
  protected void onStart() {
    super.onStart();
    if (authRepository.isUserLoggedIn()) {
      Intent intent = new Intent(this, DashboardActivity.class);
      startActivity(intent); finish();
    }
  }

  private void initialize() {
    btnSignIn.setOnClickListener(v -> {
      String txtUsername = edtUsername.getText().toString().trim();
      String txtPassword = edtPassword.getText().toString().trim();
      if (!validateEmptyField(txtUsername, txtPassword)) return;
      if (authRepository.login(txtUsername, txtPassword)) {
        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
        startActivity(intent); finish();
      }
      else if (!authRepository.login(txtUsername, txtPassword)) {
        ViewUtils.createErrorDialog(this, "Oops..", "Credential not found!");
      }
    });

    hyperlinkSignUp.setOnClickListener(v -> {
      Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
      startActivity(intent); finish();
    });
  }

  private void formConfiguration() {
    ViewUtils.clearErrorMessageOnType(usernameLayout, edtUsername);
    ViewUtils.clearErrorMessageOnType(passwordLayout, edtPassword);
  }

  private boolean validateEmptyField(String username, String password) {
    boolean flag = true;
    if (username == null || username.trim().isEmpty()) {
      usernameLayout.setError("Username is required");
      flag = false;
    }
    if (password == null || password.trim().isEmpty()) {
      passwordLayout.setError("Password is required");
      flag = false;
    }
    return flag;
  }
}