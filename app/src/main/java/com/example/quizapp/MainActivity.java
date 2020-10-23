package com.example.quizapp;


import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import org.jsoup.Jsoup;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

import cz.msebera.android.httpclient.Header;

import static android.graphics.Color.*;
import static com.example.quizapp.R.color.wrong_color;

class TrackScore{
    static int points;
    static int answeredCorrect;
    static int answeredWrong;
    static int quesNo;
    static boolean checkAnswer(String answerPressed, String correctAnswer)
    {
        TrackScore.quesNo++;
        if(answerPressed.equals(correctAnswer))
        {
            TrackScore.points++;
            TrackScore.answeredCorrect++;
            return true;
        }
        else{
            TrackScore.points--;
            TrackScore.answeredWrong--;
            return false;
        }
    }
}

public class MainActivity extends AppCompatActivity {

    // Consttants:
    String QUIZ_URL = "https://opentdb.com/api.php?amount=10&type=multiple";
    long MIN_TIME = 5000;
    float MIN_DISTANCE = 1000;

    // Member variables:
    TextView mTextView;
    TextView mScoreView;
    Button ansBtn1;
    Button ansBtn2;
    Button ansBtn3;
    Button ansBtn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        letsDoSomeNetworking();
    }

    private void letsDoSomeNetworking() {

        Log.d("Quiz", "onss: ");
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(QUIZ_URL, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("Quiz", "oooonSuccess: " + response.toString());

                try {
                    QuestionData[] QuestionData = QuestionsBankModel.fromJason(response);
                    updateUI(QuestionData);
                } catch (JSONException | UnsupportedEncodingException e) {
                    Log.e("Quiz", "ERRORR:: " + e.toString() );
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e("Quiz", "Fail" + throwable.toString());
//                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }

    private void updateUI(QuestionData[] QuestionData) throws UnsupportedEncodingException {

        mScoreView = (TextView) findViewById(R.id.score_view);
        mTextView = (TextView) findViewById(R.id.ques_view);
        ansBtn1 = (Button) findViewById(R.id.ans1_btn);
        ansBtn2 = (Button) findViewById(R.id.ans2_btn);
        ansBtn3 = (Button) findViewById(R.id.ans3_btn);
        ansBtn4 = (Button) findViewById(R.id.ans4_btn);
        ansBtn1.setAllCaps(false);
        ansBtn1.setTypeface(Typeface.MONOSPACE, Typeface.BOLD);
        ansBtn2.setAllCaps(false);
        ansBtn2.setTypeface(Typeface.MONOSPACE, Typeface.BOLD);
        ansBtn3.setAllCaps(false);
        ansBtn3.setTypeface(Typeface.MONOSPACE, Typeface.BOLD);
        ansBtn4.setAllCaps(false);
        ansBtn4.setTypeface(Typeface.MONOSPACE, Typeface.BOLD);

        changeQuestion( QuestionData);




    }

    private void changeQuestion(final QuestionData[] QuestionData) {
        if(TrackScore.quesNo == 10)
        {
            resetScore();
        }

        mTextView.setText(QuestionData[TrackScore.quesNo].getmQuestion());
        ansBtn1.setText(QuestionData[TrackScore.quesNo].getmAnsawer1());
        ansBtn2.setText(QuestionData[TrackScore.quesNo].getmAnsawer2());
        ansBtn3.setText(QuestionData[TrackScore.quesNo].getmAnsawer3());
        ansBtn4.setText(QuestionData[TrackScore.quesNo].getmAnsawer4());
        mScoreView.setText(Integer.toString(TrackScore.points));

//        ansBtn1.setBackgroundColor(android.R.drawable.btn_default);
//        ansBtn2.setBackgroundColor(android.R.drawable.btn_default);
//        ansBtn3.setBackgroundColor(android.R.drawable.btn_default);
//        ansBtn4.setBackgroundColor(android.R.drawable.btn_default);

        ansBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean check = TrackScore.checkAnswer(ansBtn1.getText().toString(), QuestionData[TrackScore.quesNo].getmCorrectAnswer().toString());
                if(check)
                {
                    ansBtn1.setBackgroundColor(parseColor("#8CC83E"));
                }
                else{
                    ansBtn1.setBackgroundColor(parseColor("#E54F48"));
                }
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        // Actions to do after 5 seconds
                        ansBtn1.setBackgroundColor(parseColor("#BDBCB6"));
                        changeQuestion(QuestionData);
                    }
                }, 2000);


            }
        });
        ansBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean check = TrackScore.checkAnswer(ansBtn2.getText().toString(), QuestionData[TrackScore.quesNo].getmCorrectAnswer().toString());
                if(check)
                {
                    ansBtn2.setBackgroundColor(parseColor("#8CC83E"));
                }
                else{
                    ansBtn2.setBackgroundColor(parseColor("#E54F48"));
                }
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        // Actions to do after 5 seconds
                        ansBtn2.setBackgroundColor(parseColor("#BDBCB6"));
                        changeQuestion(QuestionData);
                    }
                }, 2000);


            }
        });

        ansBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean check = TrackScore.checkAnswer(ansBtn3.getText().toString(), QuestionData[TrackScore.quesNo].getmCorrectAnswer().toString());
                if(check)
                {
                    ansBtn3.setBackgroundColor(parseColor("#8CC83E"));
                }
                else{
                    ansBtn3.setBackgroundColor(parseColor("#E54F48"));
                }
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        // Actions to do after 5 seconds
                        ansBtn3.setBackgroundColor(parseColor("#BDBCB6"));
                        changeQuestion(QuestionData);
                    }
                }, 2000);


            }
        });

        ansBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean check = TrackScore.checkAnswer(ansBtn4.getText().toString(), QuestionData[TrackScore.quesNo].getmCorrectAnswer().toString());
                if(check)
                {
                    ansBtn4.setBackgroundColor(parseColor("#8CC83E"));
                }
                else{
                    ansBtn4.setBackgroundColor(parseColor("#E54F48"));
                }
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        // Actions to do after 5 seconds
                        ansBtn4.setBackgroundColor(parseColor("#BDBCB6"));
                        changeQuestion(QuestionData);
                    }
                }, 2000);




            }
        });
    }

    private void resetScore() {
        TrackScore.points = 0;
        TrackScore.answeredWrong = 0;
        TrackScore.answeredCorrect = 0;
        TrackScore.quesNo = 0;
        letsDoSomeNetworking();
    }
}













