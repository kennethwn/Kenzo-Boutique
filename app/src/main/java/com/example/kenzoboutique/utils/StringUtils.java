package com.example.kenzoboutique.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class StringUtils {

  public static String rupiahFormatter(Integer price) {
    Locale localeID = new Locale("id", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
    return formatRupiah.format(price);
  }

  public static String hashing(String str) {
    String salt = BCrypt.withDefaults().hashToString(12, str.toCharArray());
    return salt;
  }

  public static Boolean validateHash(String str, String strHashed) {
    BCrypt.Result result = BCrypt.verifyer().verify(str.toCharArray(), strHashed);
    if (result.verified) return true;
    return false;
  }

  public static Boolean alphabeticChecker(String str) {
    Pattern pattern = Pattern.compile("^[a-zA-Z ]+$");
    Matcher matcher = pattern.matcher(str);
    return matcher.matches();
  }

  public static String generateUUID() {
    return UUID.randomUUID().toString();
  }

  public static String generateTimestamp() {
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault());
    return dateFormat.format(calendar.getTime()) + " WIB";
  }
}
