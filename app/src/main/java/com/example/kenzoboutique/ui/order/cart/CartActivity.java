package com.example.kenzoboutique.ui.order.cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kenzoboutique.R;
import com.example.kenzoboutique.data.model.Cart;
import com.example.kenzoboutique.data.model.Checkout;
import com.example.kenzoboutique.data.repository.AuthRepository;
import com.example.kenzoboutique.data.repository.CartRepository;
import com.example.kenzoboutique.data.repository.CheckoutRepository;
import com.example.kenzoboutique.ui.DashboardActivity;
import com.example.kenzoboutique.utils.StringUtils;
import com.example.kenzoboutique.utils.ViewUtils;

import java.util.ArrayList;


public class CartActivity extends AppCompatActivity {
  private AuthRepository authRepository;
  private CartRepository cartRepository;
  private CheckoutRepository checkoutRepository;
  private ListView recyclerView;
  private CartAdapter adapter;
  private TextView btnBack, totalPrice;
  private AppCompatButton btnCheckout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cart);

    authRepository = new AuthRepository(this);
    cartRepository = new CartRepository(this);
    checkoutRepository = new CheckoutRepository(this);

    recyclerView = (ListView) findViewById(R.id.cartListTemplate);
    btnBack = (TextView) findViewById(R.id.btnBack);
    totalPrice = (TextView) findViewById(R.id.totalPrice);
    btnCheckout = (AppCompatButton) findViewById(R.id.btnCheckout);
    adapter = new CartAdapter(this, cartRepository.getAllItemsByUserId(authRepository.getCurrentUser().getId()));

    listConfig();
    initialize();
  }

  private void listConfig() {
    if (isFinishing()) return;
    if (adapter.getCount() == 0) {
      ViewUtils.createCustomCartDialog(this, R.drawable.sad_avatar, "Oops..", "Cart is empty!")
        .setConfirmButton("Shop Now", sweetAlertDialog -> {
          Intent intent = new Intent(this, DashboardActivity.class);
          startActivity(intent); finish();
        }).show();
    }
  }

  private void initialize() {
    btnBack.setOnClickListener(v -> {onBackPressed();});

    totalPrice.setText(StringUtils.rupiahFormatter(adapter.getTotalPrice()));

    recyclerView.setAdapter(adapter);

    btnCheckout.setOnClickListener(v -> {
      Checkout checkout = new Checkout(
              StringUtils.generateUUID(),
              authRepository.getCurrentUser().getId(),
              StringUtils.generateTimestamp(),
              adapter.getTotalPrice()
      );
      checkoutRepository.addCheckout(checkout);
      ArrayList<Cart> carts = adapter.getCarts();
      for (Cart cart : carts) {
        cartRepository.updateCheckoutId(cart.getCartId(), cart.getCheckoutId());
      }
      cartRepository.deleteById(authRepository.getCurrentUser().getId());
      Toast.makeText(this, "Thank you for your order", Toast.LENGTH_SHORT).show();
      onBackPressed();
    });
  }
}