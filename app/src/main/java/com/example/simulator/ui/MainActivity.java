package com.example.simulator.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.simulator.Domain.Team;
import com.example.simulator.R;
import com.example.simulator.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

     setupMatchesList();
        setupMatchesRefresh();
        setupFloatingButton();
    }

    private void setupMatchesList(){
        //TODO listar as partidas, consumindo nossa API.
    }

    private void setupMatchesRefresh(){
        //TODO atualizar as partidas na açao sswipe;
    }

    private void setupFloatingButton(){
        //TODO evento clique e simulaçao de partidas;
    }
}