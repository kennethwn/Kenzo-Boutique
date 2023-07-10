package com.example.kenzoboutique.data.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.kenzoboutique.data.model.Cart;
import com.example.kenzoboutique.data.model.Product;
import com.example.kenzoboutique.utils.DatabaseUtils;

import java.util.ArrayList;

public class CartRepository extends DatabaseUtils {
  private static Product product;
  private static Cart cart;

  public CartRepository(@Nullable Context context) {
    super(context);
  }

  public void addToCart(Cart cart) {
    super.db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put("user_id", cart.getUserId());
    values.putNull("checkout_id");
    values.put("product_name", cart.getProduct().getName());
    values.put("product_price", Integer.parseInt(String.valueOf(cart.getProduct().getPrice())));
    values.put("quantity", cart.getQty());
    values.put("size", cart.getSize());
    super.db.insert("cart", null, values);
    super.db.close();
  }

  public void updateCheckoutId(int userId, String checkoutId) {
    super.db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put("checkout_id", checkoutId);
    super.query = "user_id = ?";
    super.db.update("cart", values, super.query, new String[] {String.valueOf(userId)});
    super.db.close();
  }

  public void deleteById(int userId) {
    super.db = this.getWritableDatabase();
    super.query = "user_id=?";
    db.delete("cart", super.query, new String[]{String.valueOf(userId)});
    db.close();
  }

  public ArrayList<Cart> getAllItemsByUserId(int user_id) {
    ArrayList<Cart> cartArrayList = new ArrayList<Cart>();
    super.db = this.getReadableDatabase();

    super.query = "user_id = ? AND checkout_id IS NULL";
    super.cursor = super.db.query(
            "cart",
            null,
            super.query,
            new String[] {String.valueOf(user_id)},
            null,
            null,
            null
    );

    if (super.cursor.moveToFirst()) {
      do {
        Integer id = super.cursor.getInt(super.cursor.getColumnIndexOrThrow("cart_id"));
        String checkoutId = super.cursor.getString(super.cursor.getColumnIndexOrThrow("checkout_id"));
        String productName = super.cursor.getString(super.cursor.getColumnIndexOrThrow("product_name"));
        Integer productPrice = super.cursor.getInt(super.cursor.getColumnIndexOrThrow("product_price"));
        Integer quantity = super.cursor.getInt(super.cursor.getColumnIndexOrThrow("quantity"));
        String size = super.cursor.getString(super.cursor.getColumnIndexOrThrow("size"));
        product = new Product(productName, productPrice);
        cart = new Cart(id, user_id, checkoutId, product, quantity, size);
        cartArrayList.add(cart);
      } while (cursor.moveToNext());
    }

    super.cursor.close();
    super.db.close();
    return cartArrayList;
  }

  public ArrayList<Cart> getAllItemsByCheckoutId(String checkout_id) {
    ArrayList<Cart> cartArrayList = new ArrayList<Cart>();
    super.db = this.getReadableDatabase();

    super.query = "checkout_id = ?";
    super.cursor = super.db.query(
            "cart",
            null,
            super.query,
            new String[] {checkout_id},
            null,
            null,
            null
    );

    if (super.cursor.moveToFirst()) {
      do {
        Integer id = super.cursor.getInt(super.cursor.getColumnIndexOrThrow("cart_id"));
        Integer userId = super.cursor.getInt(super.cursor.getColumnIndexOrThrow("user_id"));
        String productName = super.cursor.getString(super.cursor.getColumnIndexOrThrow("product_name"));
        Integer productPrice = super.cursor.getInt(super.cursor.getColumnIndexOrThrow("product_price"));
        Integer quantity = super.cursor.getInt(super.cursor.getColumnIndexOrThrow("quantity"));
        String size = super.cursor.getString(super.cursor.getColumnIndexOrThrow("size"));
        product = new Product(productName, productPrice);
        cart = new Cart(id, userId, checkout_id, product, quantity, size);
        cartArrayList.add(cart);
      } while (cursor.moveToNext());
    }

    super.cursor.close();
    super.db.close();
    return cartArrayList;
  }
}
