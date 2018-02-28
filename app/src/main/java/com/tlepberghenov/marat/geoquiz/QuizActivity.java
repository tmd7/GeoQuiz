package com.tlepberghenov.marat.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    private static final String TAG ="QuizActivite";
    private static final String KEY_INDEX ="index";
    private static final String KEY_NOTE_I ="noteIndex";
    private static final String KEY_NOTE ="note";
    private static final String KEY_BUTTON_E = "setButtonEnabled";

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mCheatButton;
    private TextView mQuestionTextView;

    private int mCurrentIndex = 0;
    private int mNoteIndex = 0;
    private boolean mButtonEnabled = false;
    private int mNote = 0;

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
            new Question(R.string.question_australia, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_ocean, true)
    };

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onSaveInstanceState(Bundle saveInstanceState){
        super.onSaveInstanceState(saveInstanceState);
        Log.d(TAG, "onSaveInstanceStage");
        saveInstanceState.putInt(KEY_INDEX, mCurrentIndex);
        saveInstanceState.putInt(KEY_NOTE_I, mNoteIndex);
        saveInstanceState.putInt(KEY_NOTE, mNote);
        saveInstanceState.putBoolean(KEY_BUTTON_E, mButtonEnabled);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);

        if (savedInstanceState !=null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        mQuestionTextView = findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(v -> {
            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
            updateQuestion();
        });

        mTrueButton = (Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(v -> QuizActivity.this.checkAnswer(true));

        mFalseButton = (Button)findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(v -> checkAnswer(false));

        mNextButton = (Button)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(v -> {
            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
            updateQuestion();
            mButtonEnabled = true;
            mTrueButton.setEnabled(mButtonEnabled);
            mFalseButton.setEnabled(mButtonEnabled);
        });

        mCheatButton = (Button)findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start CheatActivity
            }
        });

        updateQuestion();
    }

    private  void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId = 0;

        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
            mNoteIndex++;
        } else {
            messageResId = R.string.incorrect_toast;
        }

        mButtonEnabled = false;
        mTrueButton.setEnabled(mButtonEnabled);
        mFalseButton.setEnabled(mButtonEnabled);

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();

        if (mCurrentIndex == mQuestionBank.length - 1) {
            mNote = (int) mNoteIndex * 100 / mQuestionBank.length;
            String text = "Your correct answers are " + mNote + "%";
            Toast noteToast = Toast.makeText(this, text,Toast.LENGTH_LONG);
            noteToast.setGravity(Gravity.TOP, 0, 0);
            noteToast.show();
            mNoteIndex = 0;
            mNote = 0;
        }
    }


}
