package com.bignerdranch.android.geoquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button _trueButton;
    private Button _falseButton;
    private Button _nextButton;
    private TextView _questionTextView;
    private int _currentIndex = 0;
    private Question[] _questionBank = new Question[] {
            new Question(R.string.question_ocean, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_america, true),
            new Question(R.string.question_asia, true)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        this._trueButton = (Button) findViewById(R.id.true_button);
        this._trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                QuizActivity.this.checkAnswer(true);
            }
        });

        this._falseButton = (Button) findViewById(R.id.false_button);
        this._falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                QuizActivity.this.checkAnswer(false);
            }
        });

        this._questionTextView = (TextView) findViewById(R.id.question_text_view);
        this._questionTextView.setText(this._questionBank[this._currentIndex].getTextResId());

        this._nextButton = (Button) findViewById(R.id.next_button);
        this._nextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick (View v) {
                QuizActivity.this._currentIndex = (QuizActivity.this._currentIndex + 1) % QuizActivity.this._questionBank.length;
                QuizActivity.this._questionTextView.setText(QuizActivity.this._questionBank[QuizActivity.this._currentIndex].getTextResId());
            }
        });
    }

    //================================ onCreate Helpers ===========================

    private void checkAnswer(final boolean pressedTrue) {
        final boolean correctAnswer = this._questionBank[this._currentIndex].isAnswerTrue();
        int toastId = 0;
        if(pressedTrue == correctAnswer) {
            toastId = R.string.correct_text;
        } else {
            toastId = R.string.incorrect_text;
        }

        Toast.makeText(this, toastId,Toast.LENGTH_SHORT).show();
    }

    //=============================================================================

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
