/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Callum Cameron
 * @date 27/03/2020
 * @version 1.0
 */
public class TrueFalseQuestion extends Question {
    private final boolean correctAnswer;
    private boolean chosenAnswer;
    boolean correct;
    boolean trueFalse;
    private final String convertedAnswers;
    private final String[] answers;
    
    public TrueFalseQuestion(String questionId, int chapterNumber, 
        String questionText, boolean correctAnswer){
        super(questionId, chapterNumber, questionText);
        this.answers = new String[2];
        convertedAnswers = String.join(",", answers);
        this.correctAnswer = correctAnswer;
    }
    @Override
    public boolean isAnswerCorrect() {
    if (correctAnswer == chosenAnswer) {
        correct = true; } 
    return correct;
    }
    
    /**
     *
     * @return
     */
    @Override
    public boolean isTrueFalse() {
        trueFalse = true;
        return trueFalse;
    }
    //Getters
    @Override
    public String getAnswers() {
        return convertedAnswers;
    }
    public boolean getCorrectAnswer() {
        return correctAnswer;
    } 
    public boolean getChosenAnswer() {
        return chosenAnswer;
    }

    //Setters
    public void setChosenAnswer(boolean chosenAnswer) {
        this.chosenAnswer = chosenAnswer;
    }
}
