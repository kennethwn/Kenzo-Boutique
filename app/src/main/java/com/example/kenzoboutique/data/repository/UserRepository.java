package com.example.kenzoboutique.data.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.kenzoboutique.data.model.User;
import com.example.kenzoboutique.utils.DatabaseUtils;

public class UserRepository extends DatabaseUtils {
  private static User user;

  public UserRepository(@Nullable Context context) {
    super(context);
  }

  public User findUserById(int id) {
    super.db = this.getReadableDatabase();
    super.query = "SELECT * FROM users WHERE user_id = ?";
    super.cursor = super.db.rawQuery(super.query, new String[]{String.valueOf(id)});

    user = null;
    if (super.cursor.moveToFirst()) {
      user = getUserData(super.cursor);
    }

    super.cursor.close();
    return user;
  }

  public User findUserByUsername(String username) {
    super.db = this.getReadableDatabase();
    super.query = "SELECT * FROM users WHERE username = ?";
    super.cursor = super.db.rawQuery(super.query, new String[] {username});

    user = null;
    if (super.cursor.moveToFirst()) {
      user = getUserData(super.cursor);
    }

    super.cursor.close();
    return user;
  }

  public void addUser(User user) {
    super.db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put("fullname", user.getFullname());
    values.put("username", user.getUsername());
    values.put("password", user.getPassword());
    super.db.insert("users", null, values);
    super.db.close();
  }

  public User getUserData(Cursor cursor) {
    int id = cursor.getInt(cursor.getColumnIndexOrThrow("user_id"));
    String fullname = cursor.getString(cursor.getColumnIndexOrThrow("fullname"));
    String username = cursor.getString(cursor.getColumnIndexOrThrow("username"));
    String password = cursor.getString(cursor.getColumnIndexOrThrow("password"));
    return new User(id, fullname, username, password);
  }
}
