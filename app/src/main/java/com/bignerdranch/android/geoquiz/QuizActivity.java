package com.bignerdranch.android.geoquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button _trueButton;
    private Button _falseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        this._trueButton = (Button) findViewById(R.id.true_button);
        this._trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Toast.makeText(QuizActivity.this, R.string.correct_text, Toast.LENGTH_LONG).show();
            }
        });

        this._falseButton = (Button) findViewById(R.id.false_button);
        this._falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Toast.makeText(QuizActivity.this, R.string.incorrect_text, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
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
