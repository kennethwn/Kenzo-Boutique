package com.example.kenzoboutique.ui.history;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kenzoboutique.R;
import com.example.kenzoboutique.data.model.Cart;
import com.example.kenzoboutique.data.model.Checkout;
import com.example.kenzoboutique.data.repository.AuthRepository;
import com.example.kenzoboutique.data.repository.CartRepository;
import com.example.kenzoboutique.ui.order.cart.CartAdapter;

import java.util.ArrayList;

public class HistoryDetailActivity extends AppCompatActivity {
  private Intent intent;
  private TextView btnBack, checkoutId, timestamp;
  private Checkout checkout;
  private CartRepository cartRepository;
  private ArrayList<Cart> carts;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_history_detail);

    intent = getIntent();
    checkout = (Checkout) intent.getSerializableExtra("HISTORY");

    cartRepository = new CartRepository(this);
    checkoutId = findViewById(R.id.checkoutId);
    timestamp = findViewById(R.id.checkoutDate);
    btnBack = findViewById(R.id.btnBack);

    initialize();
  }

  private void initialize() {
    btnBack.setOnClickListener(v -> {onBackPressed();});
    checkoutId.setText("INV/"+checkout.getCheckoutId());
    timestamp.setText(checkout.getTimestamp());
  }
}