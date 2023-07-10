package com.example.kenzoboutique.data.repository;

import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.Nullable;

import com.example.kenzoboutique.data.model.Cart;
import com.example.kenzoboutique.data.model.Checkout;
import com.example.kenzoboutique.utils.DatabaseUtils;

import java.util.ArrayList;

public class CheckoutRepository extends DatabaseUtils {

  public CheckoutRepository(@Nullable Context context) {
    super(context);
  }

  public void addCheckout(Checkout checkout) {
    super.db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put("checkout_id", checkout.getCheckoutId());
    values.put("user_id", checkout.getUserId());
    values.put("timestamp", checkout.getTimestamp());
    values.put("total_price", checkout.getTotalPrice());
    super.db.insert("checkout", null, values);
    super.db.close();
  }

  public ArrayList<Checkout> getAllCheckoutItemsByUserId(int user_id) {
    ArrayList<Checkout> checkoutArrayList = new ArrayList<Checkout>();
    super.db = this.getReadableDatabase();
    super.query = "user_id = ? AND checkout_id IS NOT NULL";

    super.cursor = super.db.query(
            "checkout",
            null,
            super.query,
            new String[] {String.valueOf(user_id)},
            null,
            null,
            null
    );

    if (super.cursor.moveToFirst()) {
      do {
        String id = super.cursor.getString(super.cursor.getColumnIndexOrThrow("checkout_id"));
        String timestamp = super.cursor.getString(super.cursor.getColumnIndexOrThrow("timestamp"));
        Integer totalPrice = super.cursor.getInt(super.cursor.getColumnIndexOrThrow("total_price"));
        Checkout checkout = new Checkout(id, user_id, timestamp, totalPrice);
        checkoutArrayList.add(checkout);
      } while (super.cursor.moveToNext());
    }

    super.cursor.close();
    super.db.close();
    return checkoutArrayList;
  }
}
