package com.bignerdranch.android.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static final String EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown";
    private static final String TAG = "CheatActivity";

    private TextView _answerTextView;
    private Button _showAnswer;
    private TextView _deviceVersion;
    private boolean _answerIsTrue;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick (View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        this._answerTextView = (TextView) findViewById(R.id.answer_text_view);
        if (savedInstanceState != null) {
            this._answerIsTrue = savedInstanceState.getBoolean(EXTRA_ANSWER_IS_TRUE);
            this._answerTextView.setText(String.valueOf(this._answerIsTrue));
            this.setAnswerShownResult(true);
        } else {
            this._answerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        }

        this._showAnswer = (Button) findViewById(R.id.show_answer_button);
        this._showAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                if (CheatActivity.this._answerIsTrue) {
                    CheatActivity.this._answerTextView.setText(R.string.true_button);
                } else {
                    CheatActivity.this._answerTextView.setText(R.string.false_button);
                }
                CheatActivity.this.setAnswerShownResult(true);
            }
        });

        this._deviceVersion = (TextView) findViewById(R.id.device_version);
        this._deviceVersion.setText("API Level : " + Build.VERSION.SDK_INT);
    }

    @Override
    public void onSaveInstanceState(final Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        Log.i(TAG, "inside onSaveInstanceState()");
        saveInstanceState.putBoolean(EXTRA_ANSWER_IS_TRUE, this._answerIsTrue);
    }

    private void setAnswerShownResult (final boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }
    public static Intent newIntent(final Context packageContext, final boolean answerIsTrue) {
        Intent i = new Intent(packageContext, CheatActivity.class);
        i.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return i;
    }

    public static boolean wasAnswerShown(final Intent data) {
        return data.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }
}
