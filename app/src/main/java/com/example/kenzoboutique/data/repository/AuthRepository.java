package com.example.kenzoboutique.data.repository;

import android.content.Context;

import com.example.kenzoboutique.data.model.User;
import com.example.kenzoboutique.utils.StringUtils;

public class AuthRepository {
  private static SessionRepository sessionRepository;
  private static UserRepository userRepository;
  private static User currentUser;

  public AuthRepository(Context context) {
    sessionRepository = new SessionRepository(context);
    userRepository = new UserRepository(context);
  }

  public User getCurrentUser() {
    if (currentUser == null) {
      currentUser = userRepository.findUserById(sessionRepository.getSession());
    }
    return currentUser;
  }

  public boolean isUserLoggedIn() { return getCurrentUser() != null; }

  public boolean login(String username, String password) {
    User user = userRepository.findUserByUsername(username);
    if (user == null || !user.checkPassword(password)) return false;
    sessionRepository.saveSession(user.getId());
    currentUser = user;
    return true;
  }

  public boolean register(String fullname, String username, String password) {
    User user = new User(fullname, username, StringUtils.hashing(password));
    userRepository.addUser(user);
    return true;
  }

  public static void logout() {
    currentUser = null;
    sessionRepository.clearSession();
  }
}
