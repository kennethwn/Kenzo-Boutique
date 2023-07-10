package com.example.kenzoboutique.data.array;

import com.example.kenzoboutique.data.model.Product;

import java.util.ArrayList;

public class ArrayProductModel {
  private static ArrayList<Product> products;

  public static ArrayList<Product> getInstance() {
    if (products == null) products = new ArrayList<Product>();
    return products;
  }

  public static ArrayList<Product> getProduct() {
    return products;
  }

  public static void addProduct(Product products) {
    ArrayProductModel.products.add(products);
  }
}
