package com.example.kenzoboutique.data.model;

import com.example.kenzoboutique.utils.StringUtils;

public class User {
  private Integer id;
  private String fullname;
  private String username;
  private String password;


  public User(Integer id, String fullname, String username, String password) {
    this.id = id;
    this.fullname = fullname;
    this.username = username;
    this.password = password;
  }

  public User(String fullname, String username, String password) {
    this(null, fullname, username, password);
  }

  public Integer getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getFullname() {
    return fullname;
  }

  public String getPassword() {
    return password;
  }

  public Boolean checkPassword(String password) {
    if (this.password == null) return false;
    return StringUtils.validateHash(password, this.password);
  }
}
