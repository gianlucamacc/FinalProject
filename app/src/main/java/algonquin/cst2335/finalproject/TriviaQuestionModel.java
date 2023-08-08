package algonquin.cst2335.finalproject;

import java.util.ArrayList;

/**
 * The TriviaQuestionModel class represents a trivia question along with its details such as category, question,
 * difficulty, correct answer, and a list of possible answer choices.
 */
public class TriviaQuestionModel {

    private String category;
    private String question;
    private String difficulty;
    private String correctAnswer;
    private ArrayList<String> answerList = new ArrayList<String>();

    /**
     * Constructs a TriviaQuestionModel object with the specified parameters.
     *
     * @param category       The category of the trivia question.
     * @param question       The text of the trivia question.
     * @param difficulty     The difficulty level of the trivia question.
     * @param correctAnswer  The correct answer to the trivia question.
     * @param answerList     A list of possible answer choices for the trivia question.
     */
    public TriviaQuestionModel(String category, String question, String difficulty, String correctAnswer, ArrayList<String> answerList) {
        this.category = category;
        this.question = question;
        this.difficulty = difficulty;
        this.correctAnswer = correctAnswer;
        this.answerList = answerList;
    }

    /**
     * Retrieves the category of the trivia question.
     *
     * @return The category of the trivia question.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the category of the trivia question.
     *
     * @param category The new category to set for the trivia question.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Retrieves the text of the trivia question.
     *
     * @return The text of the trivia question.
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Sets the text of the trivia question.
     *
     * @param question The new text to set for the trivia question.
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * Retrieves the difficulty level of the trivia question.
     *
     * @return The difficulty level of the trivia question.
     */
    public String getDifficulty() {
        return difficulty;
    }

    /**
     * Sets the difficulty level of the trivia question.
     *
     * @param difficulty The new difficulty level to set for the trivia question.
     */
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Retrieves the correct answer to the trivia question.
     *
     * @return The correct answer to the trivia question.
     */
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Sets the correct answer for the trivia question.
     *
     * @param correctAnswer The new correct answer to set for the trivia question.
     */
    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    /**
     * Retrieves the list of possible answer choices for the trivia question.
     *
     * @return The list of possible answer choices for the trivia question.
     */
    public ArrayList<String> getAnswerList() {
        return answerList;
    }

    /**
     * Sets the list of possible answer choices for the trivia question.
     *
     * @param answerList The new list of possible answer choices to set for the trivia question.
     */
    public void setAnswerList(ArrayList<String> answerList) {
        this.answerList = answerList;
    }
}
