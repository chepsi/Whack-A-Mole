package com.kokonetworks.theapp;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

class Field extends LinearLayout {
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private SquareButton[] holes = new SquareButton[9];
    private int currentHole;
    private Listener listener;

    private int score;
    private Mole mole;

    private final int ACTIVE_TAG_KEY = 873374234;

    public Field(Context context) {
        super(context);
        initializeViews(context);
    }

    public Field(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initializeViews(context);
    }

    public Field(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeViews(context);
    }

    public Field(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initializeViews(context);
    }

    public int totalHoles() {
        return holes.length;
    }

    private void initializeViews(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_field, this, true);

        holes[0] = (SquareButton) findViewById(R.id.hole1);
        holes[1] = (SquareButton) findViewById(R.id.hole2);
        holes[2] = (SquareButton) findViewById(R.id.hole3);
        holes[3] = (SquareButton) findViewById(R.id.hole4);
        holes[4] = (SquareButton) findViewById(R.id.hole5);
        holes[5] = (SquareButton) findViewById(R.id.hole6);
        holes[6] = (SquareButton) findViewById(R.id.hole7);
        holes[7] = (SquareButton) findViewById(R.id.hole8);
        holes[8] = (SquareButton) findViewById(R.id.hole9);

    }

    private void resetScore() {
        score = 0;
    }

    public void startGame() {
        resetScore();
        resetField();
        for (SquareButton squareButton : holes) {
            squareButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean active = (boolean) view.getTag(ACTIVE_TAG_KEY);
                    if (active) {
                        score += mole.getCurrentLevel() * 2;
                    } else {
                        mole.stopHopping();
                        listener.onGameEnded(score);
                    }
                }
            });
        }

        mole = new Mole(this);
        mole.startHopping();
    }

    public int getCurrentHole() {
        return currentHole;
    }

    private void resetField() {
        for (SquareButton squareButton : holes) {
            squareButton.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.hole_inactive));
            squareButton.setTag(ACTIVE_TAG_KEY, false);
        }
    }

    public void setActive(int hole) {
        mainHandler.post(() -> {
            resetField();
            holes[hole].setBackground(ContextCompat.getDrawable(getContext(), R.drawable.hole_active));
            holes[hole].setTag(ACTIVE_TAG_KEY, true);
            currentHole = hole;
        });
    }

    public Listener getListener() {
        return listener;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener {
        void onGameEnded(int score);

        void onLevelChange(int level);
    }
}