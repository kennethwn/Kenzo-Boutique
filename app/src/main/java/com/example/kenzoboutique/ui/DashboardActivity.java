package com.example.kenzoboutique.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kenzoboutique.R;
import com.example.kenzoboutique.data.repository.AuthRepository;
import com.example.kenzoboutique.data.repository.CartRepository;
import com.example.kenzoboutique.data.repository.SessionRepository;
import com.example.kenzoboutique.ui.collection.CollectionFragment;
import com.example.kenzoboutique.ui.history.HistoryFragment;
import com.example.kenzoboutique.ui.home.HomeFragment;
import com.example.kenzoboutique.ui.order.cart.CartActivity;
import com.example.kenzoboutique.ui.profile.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class DashboardActivity extends AppCompatActivity {
  private Intent intent;
  private ImageView shoppingCart;
  private TextView counterCart;
  private BottomNavigationView navbar;
  private CartRepository cartRepository;
  private AuthRepository authRepository;
  private HomeFragment homeFragment = new HomeFragment();
  private CollectionFragment collectionFragment = new CollectionFragment();
  private HistoryFragment historyFragment = new HistoryFragment();
  private UserFragment userFragment = new UserFragment();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_dashboard);
    getSupportFragmentManager().beginTransaction().replace(R.id.templateLayout, homeFragment).commit();

    cartRepository = new CartRepository(this);
    authRepository = new AuthRepository(this);
    navbar = (BottomNavigationView) findViewById(R.id.bottomNavbar);
    shoppingCart = (ImageView) findViewById(R.id.shoppingCart);
    counterCart = (TextView) findViewById(R.id.counterCart);
    initialize();

    getIntent();
    navbarConfiguration();
  }

  @Override
  protected void onResume() {
    super.onResume();
    initCounterHandler();
  }

  private void initialize() {
    shoppingCart = (ImageView) findViewById(R.id.shoppingCart);
    shoppingCart.setOnClickListener(v -> {
      Intent intent = new Intent(this, CartActivity.class);
      startActivity(intent);
    });

    initCounterHandler();
  }

  private void navbarConfiguration() {
    navbar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.home) setFragment(1);
        else if (item.getItemId() == R.id.collection) setFragment(2);
        else if (item.getItemId() == R.id.history) setFragment(3);
        else if (item.getItemId() == R.id.profile) setFragment(4);
        return true;
      }
    });
  }

  private void setFragment(int fragmentId) {
    switch (fragmentId) {
      case 1:
        getSupportFragmentManager().beginTransaction().replace(R.id.templateLayout, homeFragment).commit();
        break;
      case 2:
        getSupportFragmentManager().beginTransaction().replace(R.id.templateLayout, collectionFragment).commit();
        break;
      case 3:
        getSupportFragmentManager().beginTransaction().replace(R.id.templateLayout, historyFragment).commit();
        break;
      case 4:
        getSupportFragmentManager().beginTransaction().replace(R.id.templateLayout, userFragment).commit();
        break;
      default:
        getSupportFragmentManager().beginTransaction().replace(R.id.templateLayout, homeFragment).commit();
        break;
    }
  }

  private void initCounterHandler() {
    int totalCartItems = cartRepository.getAllItemsByUserId(authRepository.getCurrentUser().getId()).size();
    if (totalCartItems == 0) counterCart.setVisibility(View.INVISIBLE);
    else {
      counterCart.setVisibility(View.VISIBLE);
      if (totalCartItems >= 10) counterCart.setText("9+");
      else counterCart.setText(String.valueOf(totalCartItems));
    }
  }
}