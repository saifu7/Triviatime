package com.example.quizapp;

import android.util.Log;
import android.widget.Switch;
import org.jsoup.Jsoup;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

class QuestionData{
    public String mQuestion;
    public  String mAnsawer1;
    public  String mAnsawer2;
    public  String mAnsawer3;
    public  String mAnsawer4;
    public String mCorrectAnswer;

    QuestionData(){
        this.mQuestion = "";
        this.mAnsawer1 = "";
        this.mAnsawer2 = "";
        this.mAnsawer3 = "";
        this.mAnsawer4 = "";
        this.mCorrectAnswer = "";
    }


    public String getmAnsawer1() {
        return mAnsawer1;
    }

    public String getmAnsawer2() {
        return mAnsawer2;
    }

    public String getmAnsawer3() {
        return mAnsawer3;
    }

    public String getmAnsawer4() {
        return mAnsawer4;
    }

    public String getmCorrectAnswer() {
        return mCorrectAnswer;
    }

    public String getmQuestion() {
        return mQuestion;
    }
}
public class QuestionsBankModel {

    public static QuestionData[] fromJason(JSONObject JsonObject) throws JSONException {
        JSONArray quesArray = JsonObject.getJSONArray("results");

        QuestionData[] QuestionBank = new QuestionData[10];
        for(int i = 0; i < quesArray.length(); i++) {
            JSONObject question = quesArray.getJSONObject(i);
            QuestionBank[i] = new QuestionData();
            QuestionBank[i].mQuestion = Jsoup.parse(question.getString("question")).text();
//            Log.d("Quiz", "fromJason: " + QuestionBank[i].mQuestion );
//            Log.d("Quiz", "fromJason: " + QuestionBank[i].mQuestion.toString() );

            QuestionBank[i].mCorrectAnswer = Jsoup.parse(question.getString("correct_answer")).text();
            JSONArray IncorrectAnswer = question.getJSONArray("incorrect_answers");
            String[] IncorrectArr = {IncorrectAnswer.get(0).toString(), IncorrectAnswer.get(1).toString(), IncorrectAnswer.get(2).toString() };
            Random rand = new Random();
            switch(rand.nextInt(4)){
                case 0:
                    QuestionBank[i].mAnsawer1 = QuestionBank[i].mCorrectAnswer;
                    QuestionBank[i].mAnsawer2 = Jsoup.parse(IncorrectArr[0]).text();
                    QuestionBank[i].mAnsawer3 = Jsoup.parse(IncorrectArr[1]).text();
                    QuestionBank[i].mAnsawer4 = Jsoup.parse(IncorrectArr[2]).text();

                    break;
                case 1:
                    QuestionBank[i].mAnsawer2 = QuestionBank[i].mCorrectAnswer;
                    QuestionBank[i].mAnsawer1 = Jsoup.parse(IncorrectArr[0]).text();
                    QuestionBank[i].mAnsawer3 = Jsoup.parse(IncorrectArr[1]).text();
                    QuestionBank[i].mAnsawer4 = Jsoup.parse(IncorrectArr[2]).text();
                    break;
                case 2:
                    QuestionBank[i].mAnsawer3 = QuestionBank[i].mCorrectAnswer;
                    QuestionBank[i].mAnsawer2 = Jsoup.parse(IncorrectArr[1]).text();
                    QuestionBank[i].mAnsawer1 = Jsoup.parse(IncorrectArr[0]).text();
                    QuestionBank[i].mAnsawer4 = Jsoup.parse(IncorrectArr[2]).text();
                    break;
                case 3:
                    QuestionBank[i].mAnsawer4 = QuestionBank[i].mCorrectAnswer;
                    QuestionBank[i].mAnsawer2 = Jsoup.parse(IncorrectArr[1]).text();
                    QuestionBank[i].mAnsawer3 = Jsoup.parse(IncorrectArr[2]).text();
                    QuestionBank[i].mAnsawer1 = Jsoup.parse(IncorrectArr[0]).text();
                    break;
            }
        }


        return QuestionBank;
    }
}

