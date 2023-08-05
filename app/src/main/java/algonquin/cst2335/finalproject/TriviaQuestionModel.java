package algonquin.cst2335.finalproject;

import java.util.ArrayList;

public class TriviaQuestionModel {

    private String category;

    private String question;

    private String difficulty;

    private String correctAnswer;

    private ArrayList<String> answerList = new ArrayList<String>();


    public TriviaQuestionModel(String category, String question, String difficulty, String correctAnswer, ArrayList<String> answerList) {
        this.category = category;
        this.question = question;
        this.difficulty = difficulty;
        this.correctAnswer = correctAnswer;
        this.answerList = answerList;

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
    public ArrayList<String> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(ArrayList<String> answerList) {
        this.answerList = answerList;
    }
}
