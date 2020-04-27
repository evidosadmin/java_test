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
 * @description The MultipleChoiceQuestion class adds extra functionality
 * inherited from the Question class to represent one multiple-choice
 * question for a test. It adds extra details about the answers.
 */
public class MultipleChoiceQuestion extends Question {
    String answer1;
    String answer2;
    String answer3;
    String answer4;
    String[] answers = new String[4];
    boolean correct;
    boolean trueFalse;
    private char correctAnswer;
    String convertedAnswers;
    
  public MultipleChoiceQuestion(String questionId, int chapterNumber, 
        String questionText, String answer1, String answer2, String answer3,
        String answer4, char correctAnswer)
  {
      super(questionId, chapterNumber, questionText);
      this.answer1 = answer1;
      this.answer2 = answer2;
      this.answer3 = answer3;
      this.answer4 = answer4;
      this.answers[0] = answer1;
      this.answers[1] = answer2;
      this.answers[2] = answer3;
      this.answers[3] = answer4;
      this.correctAnswer = correctAnswer;
      //Joins answers into a string with ',' as a seperator
      convertedAnswers = String.join(",", answers);
  }
        
    //Overriden methods
    @Override
    public boolean isTrueFalse() {
    //Returns false for all MultipleChoiceQuestion
        trueFalse = false;
        return trueFalse;
    }
    @Override
    public String getAnswers() {
    //Returns answers as a String
        return convertedAnswers;
    }
    @Override
    public boolean isAnswerCorrect() {
    //Returns true if chosen answer is correct
        if (correctAnswer == chosenAnswer) {
            correct = true;} 
        return correct;
    }
    
    //Getters
    public char getCorrectAnswer() {
        return correctAnswer;
    } 
    public char getChosenAnswer() {
        return chosenAnswer;
    }
    
    //Setters
    public void setChosenAnswer(char chosenAnswer) {
        this.chosenAnswer = chosenAnswer;
    }

}
