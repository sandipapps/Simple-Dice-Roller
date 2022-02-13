package com.sandipbhattacharya.simplediceroller;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Store the Thread sleep time in an integer variable
    int delayTime = 20;
    // Store the number of Dice roll animations per execution
    int rollAnimations = 40;
    // Store the ids for Dice images in an integer array
    int[] diceImages = new int[]{R.drawable.d1, R.drawable.d2, R.drawable.d3, R.drawable.d4, R.drawable.d5, R.drawable.d6};
    // Define a Random object
    Random random = new Random();
    // Declare View object references
    TextView tvHelp;
    ImageView die1;
    ImageView die2;
    LinearLayout diceContainer;
    // Declare a MediaPlayer object reference
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // From the onCreate() method, find the Views
        tvHelp = findViewById(R.id.tvHelp);
        diceContainer = findViewById(R.id.diceContainer);
        die1 = findViewById(R.id.die1);
        die2 = findViewById(R.id.die2);
        // Instantiate the MediaPlayer object
        mp = MediaPlayer.create(this, R.raw.roll);
        // Attach OnClickListener with diceContainer
        diceContainer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    // In a try block call rollDice() method to show the
                    // dice roll animation. We'll define this method below.
                    rollDice();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void rollDice() {
        // Define a Runnable object
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // In the run() method, use a for loop to iterate
                // some code to show rolling dice animation
                for (int i = 0; i < rollAnimations; i++) {
                    // Generate two random numbers between 1 and 6
                    // and store them in two integer variables
                    int dice1 = random.nextInt(6) + 1;
                    int dice2 = random.nextInt(6) + 1;
                    // Get the Image ids from diceImages array
                    // using the above random numbers as array-index.
                    // Then, set the ImageViews for die1 and die2 with them.
                    die1.setImageResource(diceImages[dice1-1]);
                    die2.setImageResource(diceImages[dice2-1]);
                    try {
                        // In a try block sleep the thread for a
                        // smooth animation
                        Thread.sleep(delayTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        // Define a Thread object and pass the runnable object
        // in the constructor.
        Thread thread = new Thread(runnable);
        // Start the thread. This will cause the run() method to be called
        // where all the dice rolling animation happens.
        thread.start();
        // If the MediaPlayer object is not null then start playing
        // the music.
        if (mp != null) {
            mp.start();
        }
    }
}