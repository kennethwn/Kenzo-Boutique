package com.example.kenzoboutique.data.model;

public class Cart {
  private Integer cart_id;
  private Integer user_id;
  private String checkout_id;
  private Product product;
  private Integer qty;
  private String size;

  public Cart(
    Integer cart_id,
    Integer user_id,
    String checkout_id,
    Product product,
    Integer qty,
    String size
  ) {
    this.cart_id = cart_id;
    this.user_id = user_id;
    this.checkout_id = checkout_id;
    this.product = product;
    this.qty = qty;
    this.size = size;
  }

  public Cart(Integer user_id, Product product, String size) {
    this(null, user_id, null, product, 1, size);
  }

  public Integer getCartId() {
    return cart_id;
  }

  public Integer getUserId() {
    return user_id;
  }

  public String getCheckoutId() {
    return checkout_id;
  }

  public Product getProduct() {
    return product;
  }

  public Integer getQty() {
    return qty;
  }

  public String getSize() {
    return size;
  }
}
