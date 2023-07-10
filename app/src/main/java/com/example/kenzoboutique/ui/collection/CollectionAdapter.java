package com.example.kenzoboutique.ui.collection;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kenzoboutique.R;
import com.example.kenzoboutique.data.array.ArrayProductModel;
import com.example.kenzoboutique.ui.order.OrderActivity;


public class CollectionAdapter extends BaseAdapter {
  private final Context context;
  private LayoutInflater inflater;

  public CollectionAdapter(Context context) {
    this.context = context;
  }

  @Override
  public int getCount() {
    return ArrayProductModel.getProduct().size();
  }

  @Override
  public Object getItem(int position) {
    return ArrayProductModel.getInstance().get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    ViewHolder holder;

    if (inflater == null) {
      inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    if (convertView == null) {
      convertView = inflater.from(context).inflate(R.layout.collection_list, parent, false);
      holder = new ViewHolder(convertView);
      convertView.setTag(holder);
    } else {
      holder = (ViewHolder) convertView.getTag();
    }

    holder.image.setImageResource(ArrayProductModel.getProduct().get(position).getImage());
    holder.name.setText(ArrayProductModel.getProduct().get(position).getName());
    holder.rate.setText(ArrayProductModel.getProduct().get(position).getRate());
    holder.price.setText(ArrayProductModel.getProduct().get(position).getPriceRupiahFormat());

    convertView.setOnClickListener(v -> {
      Intent intent = new Intent(context, OrderActivity.class);
      intent.putExtra("DATA_PRODUCT", ArrayProductModel.getProduct().get(position));
      context.startActivity(intent);
    });

    return convertView;
  }

  private static class ViewHolder {
    private final ImageView image;
    private final TextView name;
    private final TextView rate;
    private final TextView price;

    private ViewHolder(View view) {
      this.image = view.findViewById(R.id.productImage);
      this.name = view.findViewById(R.id.productName);
      this.rate = view.findViewById(R.id.productRate);
      this.price = view.findViewById(R.id.productPrice);
    }
  }
}
