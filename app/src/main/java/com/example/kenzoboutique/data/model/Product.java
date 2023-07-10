package com.example.kenzoboutique.data.model;

import com.example.kenzoboutique.R;
import com.example.kenzoboutique.utils.StringUtils;

import java.io.Serializable;

public class Product implements Serializable {
  private Integer image;
  private String name;
  private Integer price;
  private String rate;

  public Product(Integer image, String name, Integer price, String rate) {
    this.image = image;
    this.name = name;
    this.price = price;
    this.rate = rate;
  }

  public Product(String name, Integer price) {
    this(null, name, price, null);
  }

  public int getImage() {
    return image;
  }

  public void setImage(int image) {
    this.image = image;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getPrice() {
    return price;
  }

  public String getPriceRupiahFormat() {
    return StringUtils.rupiahFormatter(getPrice());
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public String getRate() {
    return rate;
  }

  public void setRate(String rate) {
    this.rate = rate;
  }

  public Integer resolveImage() {
    if (name.equals("Burberry Coat")) return R.drawable.burberry_coat;
    if (name.equals("Programmer Tee")) return R.drawable.premium_tee;
    if (name.equals("Versace Tee")) return R.drawable.versace_tee;
    if (name.equals("Lacoste Long")) return R.drawable.lacoste_long;
    if (name.equals("Adidas x Bape")) return R.drawable.adidas_x_bape;
    if (name.equals("Hoodie Bape")) return R.drawable.bape_cammo;
    if (name.equals("Kenzo Crewneck")) return R.drawable.kenzo_crewneck;
    if (name.equals("Supreme White")) return R.drawable.supreme_white_hoodie;
    if (name.equals("Kenzo Patch")) return R.drawable.kenzo_patch;
    if (name.equals("Supreme x LV")) return R.drawable.supreme_x_lv_hoodie;
    if (name.equals("Bape Galaxy Tee")) return R.drawable.bape_galaxy;
    if (name.equals("Supreme Red")) return R.drawable.supreme_red_crewneck;
    if (name.equals("Bape Camo Tee")) return R.drawable.bape_tee_cammo;
    if (name.equals("Hoodie Supreme")) return R.drawable.supreme_hoodie;
    if (name.equals("Kenzo Woman")) return R.drawable.kenzo_woman;
    return null;
  }
}
