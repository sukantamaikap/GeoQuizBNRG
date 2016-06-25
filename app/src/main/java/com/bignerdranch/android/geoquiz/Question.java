package com.bignerdranch.android.geoquiz;

/**
 * Created by smaikap on 6/12/16.
 */
public class Question {
    private int _textResId;
    private boolean _answerTrue;
    private boolean _cheated;

    public Question (int textResId, boolean answerTrue) {
        this._textResId = textResId;
        this._answerTrue = answerTrue;
    }

    public boolean isAnswerTrue () {
        return this._answerTrue;
    }

    public int getTextResId () {
        return this._textResId;
    }

    public void setAnswerTrue (boolean answerTrue) {
        this._answerTrue = answerTrue;
    }

    public void setTextResId (int textResId) {
        this._textResId = textResId;
    }

    public boolean isCheated() {
        return this._cheated;
    }

    public void setCheated(final boolean cheated) {
        if (!this._cheated) {
            this._cheated = cheated;
        }
    }
}
