package com.bignerdranch.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button _trueButton;
    private Button _falseButton;
    private Button _cheatButton;
    private ImageButton _nextButton;
    private ImageButton _previousButton;
    private TextView _questionTextView;
    private int _currentIndex = 0;

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private static final int REQUEST_CODE_CHEAT = 0;

    private Question[] _questionBank = new Question[] {
            new Question(R.string.question_ocean, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_america, true),
            new Question(R.string.question_asia, true)
    };

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");
        setContentView(R.layout.activity_quiz);

        if (savedInstanceState != null) {
            this._currentIndex = savedInstanceState.getInt(KEY_INDEX);
        }

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
        this._questionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                QuizActivity.this.showNextQuestion();
            }
        });

        this._nextButton = (ImageButton) findViewById(R.id.next_button);
        this._nextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick (View v) {
                QuizActivity.this.showNextQuestion();
            }
        });

        this._previousButton = (ImageButton) findViewById(R.id.previous_button);
        this._previousButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick (View v) {
                QuizActivity.this._currentIndex = QuizActivity.this._currentIndex == 0 ? (QuizActivity.this._questionBank.length - 1) : (QuizActivity.this._currentIndex -1);
                QuizActivity.this._questionTextView.setText(QuizActivity.this._questionBank[QuizActivity.this._currentIndex].getTextResId());
            }
        });

        this._cheatButton = (Button) findViewById(R.id.cheat_button);
        this._cheatButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick (View v) {
                final boolean answerIsTrue = QuizActivity.this._questionBank[QuizActivity.this._currentIndex].isAnswerTrue();
                Intent i = CheatActivity.newIntent(QuizActivity.this, answerIsTrue);
                startActivityForResult(i, REQUEST_CODE_CHEAT);
            }
        });
    }

    //================================ onCreate Helpers ===========================

    private void checkAnswer(final boolean pressedTrue) {
        final boolean correctAnswer = this._questionBank[this._currentIndex].isAnswerTrue();
        final boolean cheated = this._questionBank[this._currentIndex].isCheated();
        int toastId = 0;

        if (cheated) {
            toastId = R.string.judgement_toast;
        } else {
            if (pressedTrue == correctAnswer) {
                toastId = R.string.correct_text;
            } else {
                toastId = R.string.incorrect_text;
            }
        }

        Toast.makeText(this, toastId,Toast.LENGTH_SHORT).show();
    }

    private void showNextQuestion() {
        this._currentIndex = (this._currentIndex + 1) % this._questionBank.length;
        this._questionTextView.setText(this._questionBank[this._currentIndex].getTextResId());
    }

    //=============================================================================

    @Override
    public void onSaveInstanceState(final Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        Log.i(TAG, "inside onSaveInstanceState()");
        saveInstanceState.putInt(KEY_INDEX, this._currentIndex);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
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

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_CODE_CHEAT) {
            if (data == null) {
                return;
            }
            this._questionBank[this._currentIndex].setCheated(CheatActivity.wasAnswerShown(data));
        }
    }
}
