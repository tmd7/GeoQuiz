package com.tlepberghenov.marat.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    private static final String EXTRA_ANSWER_IS_TRUE = "com.tlepberghenov.marat.geoquiz.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN = "com.tlepberghenov.marat.geoquiz.qeoquiz.answer_shown";
    private boolean mAnswerIsTrue;

    private TextView mAnserTextVieaw;
    private Button mShowAnswerButton;

    public static Intent newIntent(Context packageContext, boolean answerIsTrue) {
        Intent intent = new Intent(packageContext, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return intent;
    }

    public static boolean wasAnswerShow(Intent result) {
        return result.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mAnserTextVieaw = (TextView) findViewById(R.id.answer_text_view);
        mShowAnswerButton = (Button) findViewById(R.id.show_answer_button);
        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAnswerIsTrue) {
                    mAnserTextVieaw.setText(R.string.true_button);
                } else {
                    mAnserTextVieaw.setText(R.string.false_button);
                }
                setAnswerShowResult(true);
            }
        });
    }

    private void setAnswerShowResult(boolean isAnswerShow) {
        Intent data =new Intent();
        data.putExtra(EXTRA_ANSWER_IS_TRUE, isAnswerShow);
    }
}
