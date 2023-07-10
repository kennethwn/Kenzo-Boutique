package com.example.kenzoboutique.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DatabaseUtils extends SQLiteOpenHelper {
  private static final int DB_VERSION = 1;
  private static final String DB_NAME = "kenzo";
  protected SQLiteDatabase db;
  protected Cursor cursor;
  protected String query;

  public DatabaseUtils(@Nullable Context context) {
    super(context, DB_NAME, null, DB_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    query = "CREATE TABLE users ("
            + "user_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
            + "fullname TEXT,"
            + "username TEXT,"
            + "password TEXT"
            + ")";
    db.execSQL(query);

    query = "CREATE TABLE cart ("
            + "cart_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
            + "user_id INTEGER,"
            + "checkout_id TEXT,"
            + "product_name TEXT,"
            + "product_price INTEGER,"
            + "quantity INTEGER,"
            + "size TEXT"
            + ")";
    db.execSQL(query);

    query = "CREATE TABLE checkout ("
            + "checkout_id TEXT PRIMARY KEY NOT NULL,"
            + "user_id INTEGER,"
            + "timestamp TEXT,"
            + "total_price INTEGER"
            + ")";
    db.execSQL(query);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    query = "DROP TABLE IF EXISTS users"; db.execSQL(query);
    query = "DROP TABLE IF EXISTS cart"; db.execSQL(query);
    query = "DROP TABLE IF EXISTS checkout"; db.execSQL(query);
    onCreate(db);
  }
}
