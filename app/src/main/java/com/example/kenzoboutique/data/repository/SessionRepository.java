package com.example.kenzoboutique.data.repository;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionRepository {
  private SharedPreferences sharedPreferences;
  private SharedPreferences.Editor editor;
  public static final String SHARED_PREFERENCE = "session";
  public static final String USER_SESSION = "user_session";

  public SessionRepository(Context context) {
    sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE, context.MODE_PRIVATE);
    editor = sharedPreferences.edit();
  }

  public void saveSession(int id) {
    editor.putInt(USER_SESSION, id).apply();
  }

  public void clearSession() {
    editor.remove(USER_SESSION).apply();
  }

  public int getSession() {
    return sharedPreferences.getInt(USER_SESSION, -1);
  }
}
