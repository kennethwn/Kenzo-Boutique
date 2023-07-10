package com.example.kenzoboutique.ui.order.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kenzoboutique.R;
import com.example.kenzoboutique.data.model.Cart;

import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {
  private Context context;
  private ArrayList<Cart> cartArrayList;

  public CartAdapter(Context context, ArrayList<Cart> cartArrayList) {
    this.context = context;
    this.cartArrayList = cartArrayList;
  }

  public Integer getTotalPrice() {
    Integer total = 0;
    for (Cart cart : cartArrayList) {
      total += cart.getProduct().getPrice();
    }
    return total;
  }

  public ArrayList<Cart> getCarts() {return cartArrayList;}

  @Override
  public int getCount() {
    return cartArrayList.size();
  }

  @Override
  public Object getItem(int position) {
    return cartArrayList.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    ViewHolder holder;

    if (convertView == null) {
      convertView = LayoutInflater.from(context).inflate(R.layout.cart_list, parent, false);
      holder = new ViewHolder(convertView);
      convertView.setTag(holder);
    } else {
      holder = (ViewHolder) convertView.getTag();
    }

    Cart cart = cartArrayList.get(position);
    holder.productImage.setImageResource(cart.getProduct().resolveImage());
    holder.productName.setText(cart.getProduct().getName());
    holder.productSize.setText("Size : " + cart.getSize());
    holder.productPrice.setText(cart.getProduct().getPriceRupiahFormat());

    return convertView;
  }

  private class ViewHolder {
    private ImageView productImage;
    private TextView productName;
    private TextView productSize;
    private TextView productPrice;


    private ViewHolder(View view) {
      this.productImage = view.findViewById(R.id.productImage);
      this.productName = view.findViewById(R.id.productName);
      this.productSize = view.findViewById(R.id.productSize);
      this.productPrice = view.findViewById(R.id.productPrice);
    }
  }
}
