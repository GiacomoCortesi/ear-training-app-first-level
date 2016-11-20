package com.example.eartrainingappfirstlevel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.MediaPlayer;
import java.util.Random;
import android.widget.Button;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.ButtonPlay);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                decideInterval();
            }
        });
    }

public void decideInterval() {
    int randomNum = randInt(1, 12);
    switch (randomNum) {
        //Minor second
        case 1: {
            int lowerNoteInt = randInt(1, 12);
            int higherNoteInt = lowerNoteInt + 1;
            playInterval(lowerNoteInt, higherNoteInt);
            break;
        }
        //Major second
        case 2: {
            int lowerNoteInt = randInt(1, 11);
            int higherNoteInt = lowerNoteInt + 2;
            playInterval(lowerNoteInt, higherNoteInt);
            break;
        }
        //Minor third
        case 3: {
            int lowerNoteInt = randInt(1, 10);
            int higherNoteInt = lowerNoteInt + 3;
            playInterval(lowerNoteInt, higherNoteInt);
            break;
        }
        //Major third
        case 4: {
            int lowerNoteInt = randInt(1, 9);
            int higherNoteInt = lowerNoteInt + 4;
            playInterval(lowerNoteInt, higherNoteInt);
            break;
        }
        //Perfect fourth
        case 5: {
            int lowerNoteInt = randInt(1, 8);
            int higherNoteInt = lowerNoteInt + 5;
            playInterval(lowerNoteInt, higherNoteInt);
            break;
        }
        //Augmented fourth
        case 6: {
            int lowerNoteInt = randInt(1, 7);
            int higherNoteInt = lowerNoteInt + 6;
            playInterval(lowerNoteInt, higherNoteInt);
            break;
        }
        //Perfect fifth
        case 7: {
            int lowerNoteInt = randInt(1, 6);
            int higherNoteInt = lowerNoteInt + 7;
            playInterval(lowerNoteInt, higherNoteInt);
            break;
        }
        //Minor sixth
        case 8: {
            int lowerNoteInt = randInt(1, 5);
            int higherNoteInt = lowerNoteInt + 8;
            playInterval(lowerNoteInt, higherNoteInt);
            break;
        }
        //Major sixth
        case 9: {
            int lowerNoteInt = randInt(1, 4);
            int higherNoteInt = lowerNoteInt + 9;
            playInterval(lowerNoteInt, higherNoteInt);
            break;
        }
        //Minor seventh
        case 10: {
            int lowerNoteInt = randInt(1, 3);
            int higherNoteInt = lowerNoteInt + 10;
            playInterval(lowerNoteInt, higherNoteInt);
            break;
        }
        //Major seventh
        case 11: {
            int lowerNoteInt = randInt(1, 2);
            int higherNoteInt = lowerNoteInt + 11;
            playInterval(lowerNoteInt, higherNoteInt);
            break;
        }
        //Perfect octave
        case 12: {
            int lowerNoteInt = randInt(1, 1);
            int higherNoteInt = lowerNoteInt + 12;
            playInterval(lowerNoteInt, higherNoteInt);
            break;
        }
    }
}

    // The following method generates a random integer between min and max
    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    protected void playInterval(int lowerNoteInt, int higherNoteInt) {
        String lowerNoteStr = Integer.toString(lowerNoteInt);
        String higherNoteStr = Integer.toString(higherNoteInt);
        int resIdLowerNote = this.getResources().
                getIdentifier("sound_" + lowerNoteStr, "raw", this.getPackageName());
        int resIdHigherNote = this.getResources().
                getIdentifier("sound_" + higherNoteStr, "raw", this.getPackageName());
        final MediaPlayer mediaPlayer1 = MediaPlayer.create(this, resIdLowerNote);
        final MediaPlayer mediaPlayer2 = MediaPlayer.create(this, resIdHigherNote);
        mediaPlayer1.start();
        mediaPlayer2.start();
    }
}

