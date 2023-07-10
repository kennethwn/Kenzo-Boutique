package com.example.kenzoboutique.ui.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.kenzoboutique.R;
import com.example.kenzoboutique.data.model.Product;
import com.example.kenzoboutique.ui.map.MapActivity;
import com.example.kenzoboutique.ui.order.OrderActivity;


public class HomeFragment extends Fragment {
  private LinearLayout outletStoreLy;
  private LinearLayout layoutKenzo, layoutSupremeLvLy, layoutBapeGalaxy;
  private LinearLayout layoutBurberry, layoutProgrammer, layoutLacoste, layoutVersace;
  private Product kenzo, supreme, bape, burberry, programmer, lacoste, versace;
  private View view;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment_home, container, false);

    outletStoreLy = (LinearLayout) view.findViewById(R.id.outletId);
    layoutKenzo = (LinearLayout) view.findViewById(R.id.kenzoPatchHome);
    layoutSupremeLvLy = (LinearLayout) view.findViewById(R.id.supremeLvHome);
    layoutBapeGalaxy = (LinearLayout) view.findViewById(R.id.bapeGalaxyHome);
    layoutBurberry = (LinearLayout) view.findViewById(R.id.burberryCoatHome);
    layoutProgrammer = (LinearLayout) view.findViewById(R.id.programmerTeeLayout);
    layoutVersace = (LinearLayout) view.findViewById(R.id.versaceTeeHome);
    layoutLacoste = (LinearLayout) view.findViewById(R.id.lacosteLayout);

    kenzo = new Product(R.drawable.kenzo_patch, "Kenzo Patch", 1200000, "4.9 (66 reviews)");
    supreme = new Product(R.drawable.supreme_x_lv_hoodie, "Supreme x LV", 10000000, "4.7 (46 reviews)");
    bape = new Product(R.drawable.bape_galaxy, "Bape Galaxy Tee", 2000000, "4.5 (51 reviews)");
    burberry = new Product(R.drawable.burberry_coat, "Burberry Coat", 25000000, "5 (666 reviews)");
    programmer = new Product(R.drawable.premium_tee, "Programmer Tee", 4000000, "4.9 (99 reviews)");
    versace = new Product(R.drawable.versace_tee, "Versace Tee", 7500000, "4.5 (29 reviews)");
    lacoste = new Product(R.drawable.lacoste_long, "Lacoste Long", 3300000, "4.0 (8 reviews)");

    initialize();
    return view;
  }

  private void initialize() {
    outletStoreLy.setOnClickListener(v -> {
      Intent intent = new Intent(getActivity(), MapActivity.class);
      requireActivity().startActivity(intent);
    });

    moveToOrderActivity(layoutKenzo, kenzo);
    moveToOrderActivity(layoutSupremeLvLy, supreme);
    moveToOrderActivity(layoutBapeGalaxy, bape);
    moveToOrderActivity(layoutBurberry, burberry);
    moveToOrderActivity(layoutProgrammer, programmer);
    moveToOrderActivity(layoutVersace, versace);
    moveToOrderActivity(layoutLacoste, lacoste);
  }

  private void moveToOrderActivity(LinearLayout layout, Product product) {
    layout.setOnClickListener(v -> {
      Intent intent = new Intent(getActivity(), OrderActivity.class);
      intent.putExtra("DATA_PRODUCT", product);
      requireActivity().startActivity(intent);
    });
  }
}