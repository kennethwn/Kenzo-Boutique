package com.example.kenzoboutique.ui.collection;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.kenzoboutique.R;
import com.example.kenzoboutique.data.array.ArrayProductModel;
import com.example.kenzoboutique.data.model.Product;

public class CollectionFragment extends Fragment {
  private GridView gridView;
  private View view;
  private CollectionAdapter adapter;
  private Boolean isDataAdded = false;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment_collection, container, false);
    setupProduct();
    gridView = view.findViewById(R.id.templateCollectionView);
    adapter = new CollectionAdapter(getContext());
    gridView.setAdapter(adapter);
    return view;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    isDataAdded = false;
    ArrayProductModel.getInstance().clear();
  }

  @Override
  public void onStop() {
    super.onStop();
    isDataAdded = false;
    ArrayProductModel.getInstance().clear();
  }

  private void setupProduct() {
    if (!isDataAdded) {
      ArrayProductModel.getInstance().add(new Product(R.drawable.kenzo_patch, "Kenzo Patch", 1200000, "4.9 (66 reviews)"));
      ArrayProductModel.getInstance().add(new Product(R.drawable.supreme_x_lv_hoodie, "Supreme x LV", 10000000, "4.7 (46 reviews)"));
      ArrayProductModel.getInstance().add(new Product(R.drawable.bape_galaxy, "Bape Galaxy Tee", 2000000, "4.5 (51 reviews)"));
      ArrayProductModel.getInstance().add(new Product(R.drawable.adidas_x_bape, "Adidas x Bape", 6000000, "4.7 (61 reviews)"));
      ArrayProductModel.getInstance().add(new Product(R.drawable.burberry_coat, "Burberry Coat", 25000000, "5 (666 reviews)"));
      ArrayProductModel.getInstance().add(new Product(R.drawable.premium_tee, "Programmer Tee", 4000000, "4.9 (99 reviews)"));
      ArrayProductModel.getInstance().add(new Product(R.drawable.versace_tee, "Versace Tee", 7500000, "4.5 (29 reviews)"));
      ArrayProductModel.getInstance().add(new Product(R.drawable.lacoste_long, "Lacoste Long", 3300000, "4.0 (8 reviews)"));
      ArrayProductModel.getInstance().add(new Product(R.drawable.kenzo_crewneck, "Kenzo Crewneck", 3000000, "4.5 (51 reviews)"));
      ArrayProductModel.getInstance().add(new Product(R.drawable.bape_cammo, "Hoodie Bape", 4000000, "4.9 (123 reviews)"));
      ArrayProductModel.getInstance().add(new Product(R.drawable.supreme_white_hoodie, "Supreme White", 2500000, "4.8 (89 reviews)"));
      ArrayProductModel.getInstance().add(new Product(R.drawable.supreme_red_crewneck, "Supreme Red", 3500000, "4.8 (90 reviews)"));
      ArrayProductModel.getInstance().add(new Product(R.drawable.bape_tee_cammo, "Bape Camo Tee", 1000000, "4.4 (53 reviews)"));
      ArrayProductModel.getInstance().add(new Product(R.drawable.supreme_hoodie, "Hoodie Supreme", 6000000, "4.9 (232 reviews)"));
      ArrayProductModel.getInstance().add(new Product(R.drawable.kenzo_woman, "Kenzo Woman", 850000, "4.1 (27 reviews)"));
      isDataAdded = true;
    }
  }
}