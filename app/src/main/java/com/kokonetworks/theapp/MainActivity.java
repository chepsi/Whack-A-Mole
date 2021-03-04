package com.kokonetworks.theapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;


public class MainActivity extends AppCompatActivity {

    private Field field;
    private TextView tvLevel;
    private TextView tvScore;
    private RecyclerView highScoreRV;
    private HighScoreAdapter highScoreAdapter;

    private Button btnStart;
    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        field = findViewById(R.id.field);
        tvLevel = findViewById(R.id.tvLevel);
        btnStart = findViewById(R.id.btnStart);
        tvScore = findViewById(R.id.tvScore);
        highScoreRV = findViewById(R.id.rvHighScore);

        initAdapter();
        setEventListeners();
        attachObservers();

    }

    void initAdapter(){
        highScoreAdapter = new HighScoreAdapter();
        highScoreRV.setAdapter(highScoreAdapter);
    }

    void attachObservers(){
        mainActivityViewModel.getHighScores().observe(this, highScores ->{
            highScoreAdapter.setHighScores(highScores);
        });
    }

    void setEventListeners(){
        btnStart.setOnClickListener(view -> {
            btnStart.setVisibility(View.GONE);
            tvScore.setVisibility(View.GONE);
            field.startGame();
        });

        field.setListener(listener);


    }

    private final Field.Listener listener = new Field.Listener() {

        @Override
        public void onGameEnded(int score) {
            btnStart.setVisibility(View.VISIBLE);
            tvScore.setVisibility(View.VISIBLE);
            tvScore.setText(String.format(getString(R.string.your_score), score));
            mainActivityViewModel.setHighScore(score);
        }

        @Override
        public void onLevelChange(int level) {
            tvLevel.setText(String.format(getString(R.string.level), level));
        }
    };
}