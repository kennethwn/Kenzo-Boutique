package com.example.kenzoboutique.ui.profile;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kenzoboutique.R;
import com.example.kenzoboutique.data.model.User;
import com.example.kenzoboutique.data.repository.AuthRepository;
import com.example.kenzoboutique.ui.login.LoginActivity;

import org.w3c.dom.Text;

public class UserFragment extends Fragment {
  private TextView btnSignOut, name, username;
  private User user;
  private AuthRepository authRepository;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_user, container, false);

    authRepository = new AuthRepository(getContext());
    name = (TextView) view.findViewById(R.id.nameOfUser);
    username = (TextView) view.findViewById(R.id.username);
    btnSignOut = (TextView) view.findViewById(R.id.btnSignOut);
    user = authRepository.getCurrentUser();
    initialize();

    return view;
  }

  private void initialize() {
    name.setText(user.getFullname());

    username.setText(user.getUsername());

    btnSignOut.setOnClickListener(v -> {
      AuthRepository.logout();
      Intent intent = new Intent(getContext(), LoginActivity.class);
      startActivity(intent); getActivity().finish();
    });
  }
}