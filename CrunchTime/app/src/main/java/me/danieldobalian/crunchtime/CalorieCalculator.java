package me.danieldobalian.crunchtime;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;


public class CalorieCalculator extends AppCompatActivity {

    /* Order: Push Ups, Jogging, Situps, Jumping Jacks */
    int[] EXERCISE = {350, 12, 200, 10};

    /* Joggins and JJ's are timed */
    int[] IS_TIMED = {0, 1, 0, 1};

    /* Constants used for mapping min/reps radiogroup */
    int REPS = 2;
    int TIMED = 1;

    EditText input;
    TextView result;
    TextView pushResult, jogResult, jjResult, sitResult, eqResult;
    Button go;
    double inputNum, outputNum, conversions[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_calculator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        input = (EditText)findViewById(R.id.input);
        result = (TextView)findViewById(R.id.output);
        pushResult = (TextView)findViewById(R.id.pushupOut);
        jogResult = (TextView)findViewById(R.id.joggingOut);
        jjResult = (TextView)findViewById(R.id.jjOut);
        sitResult = (TextView)findViewById(R.id.situpOut);
        eqResult = (TextView)findViewById(R.id.eqOut);
        go = (Button)findViewById(R.id.calcButton);

        /* Used when radio group is toggled to display unit to be entered */
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.ExerciseGroup);
        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (IS_TIMED[(checkedId % 10) - 1] == 1) {
                    input.setHint("Time (Minutes)");
                } else {
                    input.setHint("Reps");
                }
            }
        });

        /* Calculate button is selected */
        go.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                /* Gets the id of selected radio button */
                /* Pushing (1), Jogging(2), Situps(3), JJ's(4) */
                int id = ((RadioGroup)findViewById( R.id.ExerciseGroup )).getCheckedRadioButtonId();
                id %= 10;

                /* Checks for empty input */
                if (input.getText().toString().equals("") || input.getText().toString() == null )
                    return;

                inputNum = Double.parseDouble(input.getText().toString());
                outputNum = inputNum / EXERCISE[id-1] * 100;
                result.setText(String.format("%.1f",outputNum) + " Calories");
                result.setTextColor(Color.BLACK);

                /* Sets results of equivalent workouts */
                pushResult.setText("Pushups: " + String.format("%.0f", outputNum/100*EXERCISE[0])
                        + " Reps");
                pushResult.setTypeface(null, Typeface.BOLD);

                jogResult.setText("Jogging: " + String.format("%.1f", outputNum / 100 * EXERCISE[1])
                        + " Minutes");
                jogResult.setTypeface(null, Typeface.BOLD);

                jjResult.setText("Jumping Jacks: " + String.format("%.0f", outputNum / 100 * EXERCISE[3])
                        + " Minutes");
                jjResult.setTypeface(null, Typeface.BOLD);

                sitResult.setText("Situps: " + String.format("%.1f", outputNum / 100 * EXERCISE[2])
                        + " Reps");
                sitResult.setTypeface(null, Typeface.BOLD);

                eqResult.setText("Equivalent Workouts");
                eqResult.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
            }
        });

        /* Deletes text when input is selected */
        input.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                EditText et = (EditText) findViewById(R.id.input);
                et.setText("");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calorie_calculator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
