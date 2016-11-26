package com.example.eartrainingappfirstlevel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.view.View;
import android.widget.RadioGroup;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Random;

/*To be solved:
- Avoid the crash of the app when pressing the sequence Play->Submit Button without checking any Radio Button.
- If you first press the submit button before pressing the play button(even if you checked a Radio Button) nothing happens.
  Possible solution: Using a Toast.
- Find a way to register and count correct and wrong answers.
- Determine the statistic that makes the user jumps from a level to another one.
 */

/*Remember: Now the activity name is Main Activity (activity_main), of course when you'll develop the whole app the name will
change (e.g. First Level: Intervals), hence be carefull and modify the code where is needed.
 */

public class MainActivity extends AppCompatActivity{

    private RadioGroup radioGroupIntervals1;
    private RadioGroup radioGroupIntervals2;
    private RadioButton radioButtonIntervals1;
    private int flag;

    private RadioGroup.OnCheckedChangeListener listener1 = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                radioGroupIntervals2.setOnCheckedChangeListener(null); // remove the listener before clearing so we don't throw that stackoverflow exception
                radioGroupIntervals2.clearCheck(); // clear the second RadioGroup!
                radioGroupIntervals2.setOnCheckedChangeListener(listener2); //reset the listener
                Log.e("XXX2", "do the work");
            }
        }
    };

    private RadioGroup.OnCheckedChangeListener listener2 = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                radioGroupIntervals1.setOnCheckedChangeListener(null);
                radioGroupIntervals1.clearCheck();
                radioGroupIntervals1.setOnCheckedChangeListener(listener1);
                Log.e("XXX2", "do the work");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroupIntervals1 = (RadioGroup) findViewById(R.id.radioGroupIntervals1);
        radioGroupIntervals2 = (RadioGroup) findViewById(R.id.radioGroupIntervals2);

        // this is so we can start fresh, with no selection on both RadioGroups
        radioGroupIntervals1.clearCheck();
        radioGroupIntervals2.clearCheck();

        radioGroupIntervals1.setOnCheckedChangeListener(listener1);
        radioGroupIntervals2.setOnCheckedChangeListener(listener2);

        ImageButton button = (ImageButton) findViewById(R.id.PlayButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                decideInterval();
            }
        });
    }

    //The following method determines which interval will be played in a random way
    public void decideInterval() {

        int randomNum = randInt(1, 12);
        String Interval;
        flag = 1;

        switch (randomNum) {
        //Minor second
        case 1: {
            Interval = "Minor Second";
            int lowerNoteInt = randInt(1, 12);
            int higherNoteInt = lowerNoteInt + 1;
            playInterval(lowerNoteInt, higherNoteInt);
            addListenerOnButton(Interval, flag);
            break;
        }
        //Major second
        case 2: {
            Interval = "Major Second";
            int lowerNoteInt = randInt(1, 11);
            int higherNoteInt = lowerNoteInt + 2;
            playInterval(lowerNoteInt, higherNoteInt);
            addListenerOnButton(Interval, flag);
            break;
        }
        //Minor third
        case 3: {
            Interval = "Minor Third";
            int lowerNoteInt = randInt(1, 10);
            int higherNoteInt = lowerNoteInt + 3;
            playInterval(lowerNoteInt, higherNoteInt);
            addListenerOnButton(Interval, flag);
            break;
        }
        //Major third
        case 4: {
            Interval = "Major Third";
            int lowerNoteInt = randInt(1, 9);
            int higherNoteInt = lowerNoteInt + 4;
            playInterval(lowerNoteInt, higherNoteInt);
            addListenerOnButton(Interval, flag);
            break;
        }
        //Perfect fourth
        case 5: {
            Interval = "Perfect Fourth";
            int lowerNoteInt = randInt(1, 8);
            int higherNoteInt = lowerNoteInt + 5;
            playInterval(lowerNoteInt, higherNoteInt);
            addListenerOnButton(Interval, flag);
            break;
        }
        //Augmented fourth
        case 6: {
            Interval = "Augmented Fourth";
            int lowerNoteInt = randInt(1, 7);
            int higherNoteInt = lowerNoteInt + 6;
            playInterval(lowerNoteInt, higherNoteInt);
            addListenerOnButton(Interval, flag);
            break;
        }
        //Perfect fifth
        case 7: {
            Interval = "Perfect Fifth";
            int lowerNoteInt = randInt(1, 6);
            int higherNoteInt = lowerNoteInt + 7;
            playInterval(lowerNoteInt, higherNoteInt);
            addListenerOnButton(Interval, flag);
            break;
        }
        //Minor sixth
        case 8: {
            Interval = "Minor Sixth";
            int lowerNoteInt = randInt(1, 5);
            int higherNoteInt = lowerNoteInt + 8;
            playInterval(lowerNoteInt, higherNoteInt);
            addListenerOnButton(Interval, flag);
            break;
        }
        //Major sixth
        case 9: {
            Interval = "Major Sixth";
            int lowerNoteInt = randInt(1, 4);
            int higherNoteInt = lowerNoteInt + 9;
            playInterval(lowerNoteInt, higherNoteInt);
            addListenerOnButton(Interval, flag);
            break;
        }
        //Minor seventh
        case 10: {
            Interval = "Minor Seventh";
            int lowerNoteInt = randInt(1, 3);
            int higherNoteInt = lowerNoteInt + 10;
            playInterval(lowerNoteInt, higherNoteInt);
            addListenerOnButton(Interval, flag);
            break;
        }
        //Major seventh
        case 11: {
            Interval = "Major Seventh";
            int lowerNoteInt = randInt(1, 2);
            int higherNoteInt = lowerNoteInt + 11;
            playInterval(lowerNoteInt, higherNoteInt);
            addListenerOnButton(Interval, flag);
            break;
        }
        //Perfect Octave
        case 12: {
            Interval = "Perfect Octave";
            int lowerNoteInt = randInt(1, 1);
            int higherNoteInt = lowerNoteInt + 12;
            playInterval(lowerNoteInt, higherNoteInt);
            addListenerOnButton(Interval, flag);
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

    public void addListenerOnButton(final String playedInterval, final int flag) {

        /*The problem of the Radio Group is that, being a subset of the Linear Layout, doesn't allow to have multiple columns.
        To solve that we have to create two different Radio Groups and try to use them as one. Set up of the RGs:*/
        radioGroupIntervals1 = (RadioGroup) findViewById(R.id.radioGroupIntervals1);
        radioGroupIntervals2 = (RadioGroup) findViewById(R.id.radioGroupIntervals2);
        radioGroupIntervals1.clearCheck(); // this is so we can start fresh, with no selection on both RadioGroups
        radioGroupIntervals2.clearCheck();
        radioGroupIntervals1.setOnCheckedChangeListener(listener1);
        radioGroupIntervals2.setOnCheckedChangeListener(listener2);

        Button submitButton = (Button) findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId1 = radioGroupIntervals1.getCheckedRadioButtonId();
                int selectedId2 = radioGroupIntervals2.getCheckedRadioButtonId();
                int realCheck = selectedId1 == -1 ? selectedId2 : selectedId1;

                // find the radio button by returned id
                radioButtonIntervals1 = (RadioButton) findViewById(realCheck);

                if (flag == 0)
                {
                    Toast.makeText(MainActivity.this,
                            R.string.exception, Toast.LENGTH_LONG).show();
                }
                else
                {
                    if (radioButtonIntervals1.getText().equals(playedInterval))
                    {
                        Toast.makeText(MainActivity.this,
                                R.string.correct_answer, Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,
                                getResources().getString(R.string.wrong_answer) + "\n" + playedInterval, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }
}




