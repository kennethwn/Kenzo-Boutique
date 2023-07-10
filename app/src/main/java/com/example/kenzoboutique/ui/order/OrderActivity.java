package com.example.kenzoboutique.ui.order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kenzoboutique.R;
import com.example.kenzoboutique.data.model.Cart;
import com.example.kenzoboutique.data.model.Product;
import com.example.kenzoboutique.data.model.User;
import com.example.kenzoboutique.data.repository.AuthRepository;
import com.example.kenzoboutique.data.repository.CartRepository;
import com.example.kenzoboutique.utils.ViewUtils;


public class OrderActivity extends AppCompatActivity {
  private Product product;
  private Cart cart;
  private TextView btnBack;
  private ImageView productImage;
  private TextView productRate, productName, productPrice;
  private CartRepository cartRepository;
  private AuthRepository authRepository;
  private AppCompatButton btnAddToCart;
  private RadioGroup sizeType;
  private RadioButton sizeRadio;
  private String selectedSize;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_order);

    Intent intent = getIntent();
    product = (Product) intent.getSerializableExtra("DATA_PRODUCT");

    cartRepository = new CartRepository(this);
    authRepository = new AuthRepository(this);
    btnBack = (TextView) findViewById(R.id.btnBack);
    productImage = (ImageView) findViewById(R.id.productImage);
    productRate = (TextView) findViewById(R.id.productRate);
    productName = (TextView) findViewById(R.id.productName);
    productPrice = (TextView) findViewById(R.id.productPrice);
    sizeType = (RadioGroup) findViewById(R.id.sizeType);
    btnAddToCart = (AppCompatButton) findViewById(R.id.btnAddToCart);

    initialize();
  }

  private void initialize() {
    btnBack.setOnClickListener(v -> {onBackPressed();});

    productImage.setImageResource(product.getImage());
    productRate.setText(product.getRate());
    productName.setText(product.getName());
    productPrice.setText(product.getPriceRupiahFormat());

    btnAddToCart.setOnClickListener(v -> {
      if (!isSizeSelected()) return;
      else {
        cart = new Cart(authRepository.getCurrentUser().getId(), product, selectedSize);
        cartRepository.addToCart(cart);
        Toast.makeText(this, "Product added to cart", Toast.LENGTH_SHORT).show();
        onBackPressed();
      }
    });

    sizeType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(RadioGroup group, int checkedId) {
        sizeRadio = findViewById(checkedId);
        selectedSize = sizeRadio.getText().toString().trim();
      }
    });
  }

  private boolean isSizeSelected() {
    if (sizeType.getCheckedRadioButtonId() == -1) {
      ViewUtils.createWarningDialog(this, "Warning", "Choose your size!");
      return false;
    }
    return true;
  }
}