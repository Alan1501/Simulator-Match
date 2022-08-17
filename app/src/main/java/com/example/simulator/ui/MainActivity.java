package com.example.simulator.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.simulator.Domain.Match;
import com.example.simulator.Domain.Team;
import com.example.simulator.R;
import com.example.simulator.data.MatchesAPI;
import com.example.simulator.databinding.ActivityMainBinding;
import com.example.simulator.ui.adapter.MatchesAdapter;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MatchesAPI MatchesAPI;
    private MatchesAdapter matchesAdapter;
    //private MatchesAdapter matchesAdapter = new MatchesAdapter(Collections.emptyList());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupHttpClient();
        setupMatchesList();
        setupMatchesRefresh();
        setupFloatingActionButton();
    }

    private void setupHttpClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://alanandcode.github.io/matches-simulator-api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MatchesAPI = retrofit.create(MatchesAPI.class);
    }

    private void setupMatchesList(){
        binding.rvMatches.setHasFixedSize(true);
        binding.rvMatches.setLayoutManager(new LinearLayoutManager(this));
        findMatchesFromApi();
    }



    private void setupMatchesRefresh(){
        binding.srlMatches.setOnRefreshListener(this::findMatchesFromApi);
    }

    private void setupFloatingActionButton() {
        binding.botao.setOnClickListener(view -> {
            view.animate().rotationBy(360).setDuration(1000).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {

                    //TODO implementar o algoritimo simulaçao paratidas
                }
            });
        });

    }
     private void findMatchesFromApi() {
        binding.srlMatches.setRefreshing(true);
            MatchesAPI.getMatches().enqueue(new Callback<List<Match>>() {
                @Override
                public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                    if(response.isSuccessful()){
                        List<Match> matches = response.body();
                        Log.i("Simulator", "Deu tudo certo ! partidas = " + matches.size());
                        matchesAdapter = new MatchesAdapter(matches);
                        binding.rvMatches.setAdapter(matchesAdapter);
                    } else {
                        showErrorMessage();
                    }
binding.srlMatches.setRefreshing(false);
                }

                @Override
                public void onFailure(Call<List<Match>> call, Throwable t) {
                    showErrorMessage();
                    binding.srlMatches.setRefreshing(false);
                }
            });
        }

    private void showErrorMessage(){
        Snackbar.make(binding.botao, R.string.error_api, Snackbar.LENGTH_LONG).show();

    }
}