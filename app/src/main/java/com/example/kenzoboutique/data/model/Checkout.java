package com.example.kenzoboutique.data.model;

import java.io.Serializable;

public class Checkout implements Serializable {
  private String checkoutId;
  private Integer userId;
  private String timestamp;
  private Integer totalPrice;

  public Checkout(String checkoutId, Integer userId, String timestamp, Integer totalPrice) {
    this.checkoutId = checkoutId;
    this.userId = userId;
    this.timestamp = timestamp;
    this.totalPrice = totalPrice;
  }

  public String getCheckoutId() {
    return checkoutId;
  }

  public Integer getUserId() {
    return userId;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public Integer getTotalPrice() {
    return totalPrice;
  }
}
