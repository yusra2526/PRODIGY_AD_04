package com.yusra.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Color;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initially hide the result button
        Button resultButton = findViewById(R.id.button);
        resultButton.setVisibility(View.INVISIBLE);
    }

    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if (!gameActive) {
            gameReset(view);
            return;
        }
        if (gameState[tappedImage] == 2) {
            gameState[tappedImage] = activePlayer;
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("Turn: Player 2");
                status.setTextColor(Color.BLUE);
            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("Turn: Player 1");
                status.setTextColor(Color.GREEN);
            }
        }

        // Check if any player has won
        for (int[] winPosition : winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 2) {
                gameActive = false;
                String winnerStr;
                Button resultButton = findViewById(R.id.button);
                resultButton.setVisibility(View.VISIBLE); // Show result button

                if (gameState[winPosition[0]] == 0) {
                    winnerStr = "Player 1 wins";
                    resultButton.setBackgroundColor(Color.GREEN);
                } else {
                    winnerStr = "Player 2 wins";
                    resultButton.setBackgroundColor(Color.BLUE);
                }

                resultButton.setText(winnerStr);
                return;
            }
        }

        // Check for a draw
        boolean draw = true;
        for (int state : gameState) {
            if (state == 2) {
                draw = false;
                break;
            }
        }
        if (draw) {
            gameActive = false;
            Button resultButton = findViewById(R.id.button);
            resultButton.setVisibility(View.VISIBLE); // Show result button
            resultButton.setText("It's a draw");
            resultButton.setBackgroundColor(Color.MAGENTA);
        }
    }

    public void gameReset(View view) {
        gameActive = true;
        activePlayer = 0;
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("Turn: Player 1");
        status.setTextColor(Color.GREEN);  // Reset color to default

        Button resultButton = findViewById(R.id.button);
        resultButton.setText("");
        resultButton.setBackgroundColor(Color.TRANSPARENT);  // Reset color to default
        resultButton.setVisibility(View.INVISIBLE); // Hide result button
    }
}
