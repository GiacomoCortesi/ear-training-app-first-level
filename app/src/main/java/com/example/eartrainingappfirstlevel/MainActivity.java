package com.example.eartrainingappfirstlevel;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
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

import static android.R.attr.defaultValue;

/*To be solved:
- Avoid the crash of the app when pressing the sequence Play->Submit Button without checking any Radio Button. <-- Fixed!
- If you first press the submit button before pressing the play button(even if you checked a Radio Button) nothing happens.
  Possible solution: Using a Toast <-- Fixed
- Find a way to register and count correct and wrong answers.
- You have to re-play the same interval up to the moment in which you press the Submit Button <-- Study preferences
- Determine the statistic that makes the user jumps from a level to another one.
 */

/*Optimizations:
- Consider defining objects such as an Interval object.
- Consider performing the computation of "decideInterval()" before pressing the button.
 */

/*Remember: Now the activity name is Main Activity (activity_main), of course when you'll develop the whole app the name will
change (e.g. First Level: Intervals), hence be carefull and modify the code where is needed.
 */

/* Still Working on:
you could think to use the same flag both for the check of point 2 (already done) and for implementing the repetition
of the same played interval until submit button is pressed.
Main Issue:
You should initialize back to zero the flag inside the OnClick method of the submit button, but you are not allowed to do so!!
 */

public class MainActivity extends AppCompatActivity{

    private RadioGroup radioGroupIntervals1;
    private RadioGroup radioGroupIntervals2;
    private RadioButton radioButtonInterval;
    private boolean submittedAnswer;

    final static int MIN_SECOND = 1;
    final static int MAJ_SECOND = 2;
    final static int MIN_THIRD = 3;
    final static int MAJ_THIRD = 4;
    final static int PERF_FOURTH = 5;
    final static int AUG_FOURTH = 6;
    final static int PERF_FIFTH = 7;
    final static int MIN_SIXTH = 8;
    final static int MAJ_SIXTH = 9;
    final static int MIN_SEVENTH = 10;
    final static int MAJ_SEVENTH = 11;
    final static int PERF_OCTAVE = 12;

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

    }

    @Override
    protected void onStart()
    {
        super.onStart();
    }

    @Override
    protected void onResume()
    {

        super.onResume();

        ImageButton button = (ImageButton) findViewById(R.id.PlayButton);
        submittedAnswer = true;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decideInterval();
            }
        });

        Button submitButton = (Button) findViewById(R.id.submitButton);

        //The following code displays an error Toast when pressing the submit button without playing the interval
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,
                        R.string.exception1, Toast.LENGTH_LONG).show();
            }
        });
    }

    //The following method determines which interval will be played in a random way
    public void decideInterval() {

        String interval = "";
        int lowerNoteInt = 0;
        int higherNoteInt = 0;
        SharedPreferences sharedPref = getSharedPreferences("savedInterval", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        if(submittedAnswer) {

            final int randomNum = randInt(1, 12);
            switch (randomNum) {
                //Minor second
                case 1: {
                    interval = "Minor Second";
                    lowerNoteInt = randInt(1, 12);
                    higherNoteInt = lowerNoteInt + MIN_SECOND;
                    break;
                }
                //Major second
                case 2: {
                    interval = "Major Second";
                    lowerNoteInt = randInt(1, 11);
                    higherNoteInt = lowerNoteInt + MAJ_SECOND;
                    break;
                }
                //Minor third
                case 3: {
                    interval = "Minor Third";
                    lowerNoteInt = randInt(1, 10);
                    higherNoteInt = lowerNoteInt + MIN_THIRD;
                    break;
                }
                //Major third
                case 4: {
                    interval = "Major Third";
                    lowerNoteInt = randInt(1, 9);
                    higherNoteInt = lowerNoteInt + MAJ_THIRD;
                    break;
                }
                //Perfect fourth
                case 5: {
                    interval = "Perfect Fourth";
                    lowerNoteInt = randInt(1, 8);
                    higherNoteInt = lowerNoteInt + PERF_FOURTH;
                    break;
                }
                //Augmented fourth
                case 6: {
                    interval = "Augmented Fourth";
                    lowerNoteInt = randInt(1, 7);
                    higherNoteInt = lowerNoteInt + AUG_FOURTH;
                    break;
                }
                //Perfect fifth
                case 7: {
                    interval = "Perfect Fifth";
                    lowerNoteInt = randInt(1, 6);
                    higherNoteInt = lowerNoteInt + PERF_FIFTH;
                    break;
                }
                //Minor sixth
                case 8: {
                    interval = "Minor Sixth";
                    lowerNoteInt = randInt(1, 5);
                    higherNoteInt = lowerNoteInt + MIN_SIXTH;
                    break;
                }
                //Major sixth
                case 9: {
                    interval = "Major Sixth";
                    lowerNoteInt = randInt(1, 4);
                    higherNoteInt = lowerNoteInt + MAJ_SIXTH;
                    break;
                }
                //Minor seventh
                case 10: {
                    interval = "Minor Seventh";
                    lowerNoteInt = randInt(1, 3);
                    higherNoteInt = lowerNoteInt + MIN_SEVENTH;
                    break;
                }
                //Major seventh
                case 11: {
                    interval = "Major Seventh";
                    lowerNoteInt = randInt(1, 2);
                    higherNoteInt = lowerNoteInt + MAJ_SEVENTH;
                    break;
                }
                //Perfect Octave
                case 12: {
                    interval = "Perfect Octave";
                    lowerNoteInt = randInt(1, 1);
                    higherNoteInt = lowerNoteInt + PERF_OCTAVE;
                    break;
                }
            }
            editor.putInt("lowerNote", lowerNoteInt);
            editor.putInt("higherNote", higherNoteInt);
            editor.putString("interval", interval);
            editor.apply();
            playInterval(lowerNoteInt, higherNoteInt);
            submittedAnswer = false;
            defineAnswer(interval);
        }
        else{
            int savedLowerNote = sharedPref.getInt("lowerNote", defaultValue);
            int savedHigherNote = sharedPref.getInt("higherNote", defaultValue);
            String savedInterval = sharedPref.getString("interval", interval);
            playInterval(savedLowerNote, savedHigherNote);
            defineAnswer(savedInterval);
        }
}

    // The following method generates a random integer between min and max
    public static int randInt(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
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

    public void defineAnswer(final String playedInterval) {

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
                radioButtonInterval = (RadioButton) findViewById(realCheck);

                //The following if condition check if a Radio Button has been checked or not
                if (realCheck == -1)
                {
                    Toast.makeText(MainActivity.this,
                            R.string.exception2, Toast.LENGTH_LONG).show();
                    submittedAnswer = false;
                }
                else
                {
                    if (radioButtonInterval.getText().toString().equals(playedInterval))
                    {
                        showDialogCorrectAnswer();
                    }
                    else
                    {
                        showDialogWrongAnswer(playedInterval);
                    }
                    submittedAnswer = true;

                    //This is in order to clear the selection of the Radio Button after the answer has been given
                    radioGroupIntervals1.clearCheck();
                    radioGroupIntervals2.clearCheck();
                }
            }
        });
    }

    protected void showDialogCorrectAnswer(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.correct_answer)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        onResume();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    protected String showDialogWrongAnswer(String playedInterval){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getResources().getString(R.string.wrong_answer) + "\n" + playedInterval)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        onResume();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
        return playedInterval;
    }
}





