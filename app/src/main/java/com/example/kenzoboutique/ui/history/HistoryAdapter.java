package com.example.kenzoboutique.ui.history;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kenzoboutique.R;
import com.example.kenzoboutique.data.model.Checkout;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
  private Context context;
  private ArrayList<Checkout> checkouts;

  public HistoryAdapter(Context context, ArrayList<Checkout> checkouts) {
    this.context = context;
    this.checkouts = checkouts;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_shopping_list, parent,false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Checkout checkout = checkouts.get(position);
    holder.checkoutId.setText("INV/"+checkout.getCheckoutId());
    holder.timestamp.setText(checkout.getTimestamp());

    holder.listLayout.setOnClickListener(v -> {
      Intent intent = new Intent(context, HistoryDetailActivity.class);
      intent.putExtra("HISTORY", checkout);
      context.startActivity(intent);
    });
  }

  @Override
  public int getItemCount() {
    return checkouts.size();
  }

  protected class ViewHolder extends RecyclerView.ViewHolder {
    private TextView checkoutId;
    private TextView timestamp;
    private LinearLayout listLayout;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      checkoutId = (TextView) itemView.findViewById(R.id.checkoutId);
      timestamp = (TextView) itemView.findViewById(R.id.checkoutTimestamp);
      listLayout = (LinearLayout) itemView.findViewById(R.id.listLayout);
    }
  }
}
