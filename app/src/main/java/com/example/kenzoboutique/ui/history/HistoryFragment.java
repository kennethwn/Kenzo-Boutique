package com.example.kenzoboutique.ui.history;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kenzoboutique.R;
import com.example.kenzoboutique.data.repository.AuthRepository;
import com.example.kenzoboutique.data.repository.CheckoutRepository;


public class HistoryFragment extends Fragment {
  private RecyclerView recyclerView;
  private HistoryAdapter adapter;
  private CheckoutRepository checkoutRepository;
  private AuthRepository authRepository;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_history, container, false);
    checkoutRepository = new CheckoutRepository(getContext());
    authRepository = new AuthRepository(getContext());
    recyclerView = view.findViewById(R.id.historyListTemplate);
    initiRecyclerViewHandler();
    return view;
  }

  private void initiRecyclerViewHandler() {
    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
    recyclerView.setLayoutManager(layoutManager);
    adapter = new HistoryAdapter(getContext(), checkoutRepository.getAllCheckoutItemsByUserId(authRepository.getCurrentUser().getId()));
    recyclerView.setAdapter(adapter);
  }
}