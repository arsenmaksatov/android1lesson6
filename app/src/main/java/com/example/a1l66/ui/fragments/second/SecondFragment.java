package com.example.a1l66.ui.fragments.second;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a1l66.R;
import com.example.a1l66.databinding.FragmentSecondBinding;
import com.example.a1l66.model.Model;
import com.example.a1l66.ui.fragments.first.FirstFragment;

import java.util.Locale;

public class SecondFragment extends Fragment {
    Model modelData;
    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupListener();
        listeners();
        getData();
    }

    private void setupListener() {
        binding.btnToSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SecondFragment secondFragment = new SecondFragment();
                getParentFragmentManager().beginTransaction().replace(R.id.container_fragment, secondFragment).commit();

            }
        });
    }

    private void listeners() {
        binding.btnToSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textView = (TextView) binding.timer;
                CountDownTimer Timer = new CountDownTimer(6000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        textView.setText(String.format(Locale.getDefault(), "%d ", millisUntilFinished / 1000L));
                    }

                    public void onFinish() {
                        save();
                    }
                }.start();
            }
        });
    }

    private void save() {
        String data = binding.etData.getText().toString().trim();
        if (data.isEmpty()) {
            binding.etData.setError("Input text");
        } else {
            modelData = new Model(data);
            Bundle bundle = new Bundle();
            bundle.putSerializable("key", modelData);
            FirstFragment firstFragment = new FirstFragment();
            firstFragment.setArguments(bundle);
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.container_fragment, firstFragment).commit();
        }
    }

    private void getData() {
        if (getArguments() != null) {
            modelData = (Model) getArguments().getSerializable("key1");
            binding.etData.setText(modelData.getData());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}